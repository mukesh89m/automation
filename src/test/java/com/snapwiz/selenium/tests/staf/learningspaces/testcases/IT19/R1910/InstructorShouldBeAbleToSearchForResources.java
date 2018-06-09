package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1910;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ResourseCreate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
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
public class InstructorShouldBeAbleToSearchForResources extends Driver{
    @Test(priority = 1, enabled = true)
    public void theUserShouldBeAbleToSearchForResourceName() {
        try {
            //tc row no 271
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new ResourseCreate().resourseCreate(271,0);//create chpterlevel resource
            new LoginUsingLTI().ltiLogin("271");//login as instructor
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            Thread.sleep(2000);
            tocSearch.getTocSearch().sendKeys("resourcename_120");
            tocSearch.getSearch_Icon().click();
            String resourceHeading = tocSearch.getGlossaryHeading().getText();
            Assert.assertEquals(resourceHeading, "ResourceName_120", "The search result is not related to the entered resource name");
            tocSearch.getGlossaryHeading().click();
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-description")));
            boolean description=tocSearch.getDescription().isDisplayed();
            if(description==false){
                Assert.fail("Clicking on search result resource is not in a new tab");

            }

        } catch (Exception e) {
            Assert.fail("Exception in test case theUserShouldBeAbleToSearchForResourceName in class InstructorShouldBeAbleToSearchForResources", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void theUserShouldBeAbleToSearchForResourceDescription() {
        try {
            //tc row no 272
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("272");//login as instructor
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            Thread.sleep(2000);
            tocSearch.getTocSearch().sendKeys("IT19_R1910_ResourceName_120");
            tocSearch.getSearch_Icon().click();

            String resourceHeading = tocSearch.getGlossaryHeading().getText();
            String highlightedText = tocSearch.getHighlightedText().getAttribute("title");
            if (!resourceHeading.equals(highlightedText)) {
                Assert.fail("The search text is not highlighted.");
            }
            String descriptionText=tocSearch.getHighlightedText().getText();
            if(!descriptionText.contains("IT19_R1910_ResourceName_120")){
                Assert.fail("The search result is not related to the entered resource Description");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case theUserShouldBeAbleToSearchForResourceDescription in class InstructorShouldBeAbleToSearchForResources", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void theUserShouldBeAbleToSearchForSemanticTags() {
        try {
            //tc row no 275
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("275");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            Thread.sleep(2000);
            tocSearch.getTocSearch().sendKeys("ResourceName");
            tocSearch.getDropDownItem().click();
            String resourceHeading = tocSearch.getGlossaryHeading().getText();
            if(!resourceHeading.contains("ResourceName_")) {
                Assert.fail("The search result is not related to the entered resource name");
            }
            String resourceHeading1 = tocSearch.getGlossaryHeading().getText();
            String highlightedText = tocSearch.getHighlightedText().getAttribute("title");
            if (!resourceHeading1.equals(highlightedText)) {
                Assert.fail("The search text is not highlighted.");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case theUserShouldBeAbleToSearchForSemanticTags in class InstructorShouldBeAbleToSearchForResources", e);
        }
    }


    @Test(priority = 4, enabled = true)
    public void autoSuggestShouldBeEnhancedToSuggestBasedOnTheResourceName() {
        try {
            //tc row no 277
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("277");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            Thread.sleep(2000);
            tocSearch.getTocSearch().sendKeys("ResourceName");
            Thread.sleep(2000);
            boolean autoCompleteBox=tocSearch.getAutoCompleteBox().isDisplayed();
            if(autoCompleteBox==false){
                Assert.fail("Autocomplete layer is not appearing");

            }
            String autoSuggestText=tocSearch.getDropDownItem().getText();
            if(autoSuggestText.equals(" ") || autoSuggestText==null) {
                Assert.fail("The autocomplete box not contain the suggestion list");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case autoSuggestShouldBeEnhancedToSuggestBasedOnTheResourceName in class InstructorShouldBeAbleToSearchForResources", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void autoSuggestShouldBeEnhancedToSuggestBasedOnTheSemanticTags() {
        try {
            //tc row no 281
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("281");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            Thread.sleep(2000);
            tocSearch.getTocSearch().sendKeys("mitos");
            Thread.sleep(2000);
            boolean autoCompleteBox=tocSearch.getAutoCompleteBox().isDisplayed();
            if(autoCompleteBox==false){
                Assert.fail("Autocomplete layer is not appearing");

            }
            String autoSuggestText=tocSearch.getDropDownItem().getText();
            Assert.assertEquals(autoSuggestText,"mitosis (also, karyokinesis)","The autocomplete box not contain the suggestion list");

        } catch (Exception e) {
            Assert.fail("Exception in test case autoSuggestShouldBeEnhancedToSuggestBasedOnTheSemanticTags in class InstructorShouldBeAbleToSearchForResources", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void theUserShouldBeAbleToClickOnTheSearchIconToInitiateTheSearch() {
        try {
            //tc row no 285
            TocSearch tocSearch;
            new ResourseCreate().createResourceAtCourseLevel(2711, "Biology");//create courselevel resource
            new LoginUsingLTI().ltiLogin("285");//login as instructor
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            Thread.sleep(2000);
            tocSearch.getTocSearch().sendKeys("resourcename_120");
            tocSearch.getSearch_Icon().click();
            String courseHeading = tocSearch.getCourseLevel().getText();
            Assert.assertEquals(courseHeading, "Course Level", "CourseLevel Heading is not displaying");
            WebElement scroll=driver.findElement(By.className("search-list-chapter-level"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            String chapterHeading = tocSearch.getChapterLevel().getText();
            Thread.sleep(4000);
            Assert.assertEquals(chapterHeading, "Chapter Level", "ChapterLevel Heading is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case theUserShouldBeAbleToClickOnTheSearchIconToInitiateTheSearch in class InstructorShouldBeAbleToSearchForResources", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void theCategoriesOfSearchResultsMustContainSearchBasedCriteriaAsAGroup() {
        try {
            //tc row no 289
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("289");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            Thread.sleep(2000);
            tocSearch.getTocSearch().sendKeys("resourcename_120");
            tocSearch.getSearch_Icon().click();
            String courseHeading = tocSearch.getCourseLevel().getText();
            Assert.assertEquals(courseHeading, "Course Level", "CourseLevel Heading is not displaying");
            WebElement scroll=driver.findElement(By.className("search-list-chapter-level"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            Thread.sleep(1000);
            String chapterHeading = tocSearch.getChapterLevel().getText();
            Assert.assertEquals(chapterHeading, "Chapter Level", "ChapterLevel Heading is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case theCategoriesOfSearchResultsMustContainSearchBasedCriteriaAsAGroup in class InstructorShouldBeAbleToSearchForResources", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void theFormatForEveryCardEntryForACourseLevelSearchResults() {
        try {
            //tc row no 293
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("293");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            Thread.sleep(2000);
            tocSearch.getTocSearch().sendKeys("resourcename_120");
            tocSearch.getSearch_Icon().click();
            String animationText2 = tocSearch.getAnimationtext2().getText();
            if (!animationText2.contains("ResourceName_120")) {
                Assert.fail("Description is not at the 2nd line");
            }

            String animationText3 = tocSearch.getAnimationtext2().getText();
            if (!animationText3.contains("ResourceName_120")) {
                Assert.fail(" Description with highlighted search text is not displayed at line 2");
            }


        } catch (Exception e) {
            Assert.fail("Exception in test case theFormatForEveryCardEntryForACourseLevelSearchResults in class InstructorShouldBeAbleToSearchForResources", e);
        }
    }
    @Test(priority = 9, enabled = true)
    public void theFormatForEveryCardEntryForAChapterLevelSearchResults() {
        try {
            //tc row no 305
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("305");//login as instructor
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
            Assert.fail("Exception in test case theFormatForEveryCardEntryForAChapterLevelSearchResults in class InstructorShouldBeAbleToSearchForResources", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void searchBrowseNavigateAwayComeBack() {
        try {
            //tc row no 317
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("317");//login as student
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
            tocSearch.getSearch_Icon().click();//click on search
            tocSearch.getLesson().click();//click on lesson icon
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("ch01-sec1-1")));
            boolean lessonText = tocSearch.getLessonText().isDisplayed();
            if (lessonText == false) {
                Assert.fail("Lesson is not expanded after clicking on lesson icon");
            }
            new TOCShow().chaptertree();//click on toc
            boolean searchResult=tocSearch.getSearchResult().isDisplayed();
            if(searchResult==false){
                Assert.fail("Search results should not appear flushed");
            }
            WebElement scroll = driver.findElement(By.xpath("(//i[contains(@class,'search-data-icon search-data-img-icon')])[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            tocSearch.getImage().click();//click on image
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("widget-close")));
            boolean imageCloseIcon = tocSearch.getImageClose_Icon().isDisplayed();
            if (imageCloseIcon == false) {
                Assert.fail("Image is not expanded after clicking on image icon");
            }

            tocSearch.getImageClose_Icon().click();//click on close icon on image
            new TOCShow().chaptertree();//click on toc

            boolean searchResult1=tocSearch.getSearchResult().isDisplayed();
            if(searchResult1==false){
                Assert.fail("Search results should not appear flushed");
            }

            new LoginUsingLTI().ltiLogin("317_1");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
            tocSearch.getSearch_Icon().click();//click on search
            tocSearch.getLesson().click();//click on lesson icon
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("ch01-sec1-1")));
            boolean lessonText1 = tocSearch.getLessonText().isDisplayed();
            if (lessonText1 == false) {
                Assert.fail("Lesson is not expanded after clicking on lesson icon");
            }
            new TOCShow().chaptertree();//click on toc
            boolean searchResult2=tocSearch.getSearchResult().isDisplayed();
            if(searchResult2==false){
                Assert.fail("Search results should not appear flushed");
            }
            WebElement scroll1 = driver.findElement(By.xpath("(//i[contains(@class,'search-data-icon search-data-img-icon')])[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll1);
            tocSearch.getImage().click();//click on image
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("widget-close")));
            boolean imageCloseIcon1 = tocSearch.getImageClose_Icon().isDisplayed();
            if (imageCloseIcon1 == false) {
                Assert.fail("Image is not expanded after clicking on image icon");
            }

            tocSearch.getImageClose_Icon().click();//click on close icon on image
            new TOCShow().chaptertree();//click on toc

            boolean searchResult3=tocSearch.getSearchResult().isDisplayed();
            if(searchResult3==false){
                Assert.fail("Search results should not appear flushed");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case searchBrowseNavigateAwayComeBack in class InstructorShouldBeAbleToSearchForResources", e);
        }
    }

    @Test(priority = 11, enabled = true)
    public void glossaryWithMedia() {
        try {
            //tc row no 321
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("321");//login as student
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
            tocSearch.getSearch_Icon().click();//click on search
            Thread.sleep(1000);
            String glossaryHeading = tocSearch.getGlossaryHeading().getText();
            Assert.assertEquals(glossaryHeading, "GLOSSARY: mitosis (also, karyokinesis)", "Glossary Heading is not displaying");
            boolean mediaElement=tocSearch.getMediaElement().isDisplayed();
            if(mediaElement==false){
                Assert.fail("Media element is not  appear properly over glossary search result");
            }

            new LoginUsingLTI().ltiLogin("321_1");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
            tocSearch.getSearch_Icon().click();//click on search
            Thread.sleep(1000);
            String glossaryHeading1 = tocSearch.getGlossaryHeading().getText();
            Assert.assertEquals(glossaryHeading1, "GLOSSARY: mitosis (also, karyokinesis)", "Glossary Heading is not displaying");
            boolean mediaElement1=tocSearch.getMediaElement().isDisplayed();
            if(mediaElement1==false){
                Assert.fail("Media element is not  appear properly over glossary search result");
            }
        } catch (Exception e) {
            Assert.fail("Exception in test case glossaryWithMedia in class InstructorShouldBeAbleToSearchForResources", e);
        }
    }

    @Test(priority = 12, enabled = true)
    public void research() {
        try {
            //tc row no 323
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("323");//login as student
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
            tocSearch.getSearch_Icon().click();//click on search
            String text=tocSearch.getGlossaryText().getText();
            tocSearch.getTocSearch().clear();
            Thread.sleep(1000);
            tocSearch.getTocSearch().sendKeys("meiosis");
            tocSearch.getSearch_Icon().click();//click on search
            Thread.sleep(2000);
            String text1=tocSearch.getGlossaryText().getText();
            if(text.contains(text1)){
                Assert.fail("Search result is not  get updated based on new search text");
            }

            new LoginUsingLTI().ltiLogin("323_1");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
            tocSearch.getSearch_Icon().click();//click on search
            String text2=tocSearch.getGlossaryText().getText();
            tocSearch.getTocSearch().clear();
            Thread.sleep(1000);
            tocSearch.getTocSearch().sendKeys("meiosis");
            tocSearch.getSearch_Icon().click();//click on search
            Thread.sleep(2000);
            String text3=tocSearch.getGlossaryText().getText();
            if(text2.contains(text3)){
                Assert.fail("Search result is not  get updated based on new search text");
            }
        } catch (Exception e) {
            Assert.fail("Exception in test case research in class InstructorShouldBeAbleToSearchForResources", e);
        }
    }

    @Test(priority = 13, enabled = true)
    public void browseSearch() {
        try {
            //tc row no 329
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("329");//login as student
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            tocSearch.getImageIcon().click();
            Thread.sleep(1000);
            String chapterHeading=tocSearch.getChapterHeading().getText();
            tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
            tocSearch.getSearch_Icon().click();//click on search
            Thread.sleep(1000);
            String chapterHeading1=tocSearch.getChapterHeading().getText();
            if(chapterHeading.contains(chapterHeading1)){
                Assert.fail("Result is not honor browse filter and then search text");
            }
            new LoginUsingLTI().ltiLogin("329_1");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            tocSearch.getImageIcon().click();
            Thread.sleep(1000);
            String chapterHeading3=tocSearch.getChapterHeading().getText();
            tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
            tocSearch.getSearch_Icon().click();//click on search
            Thread.sleep(1000);
            String chapterHeading4=tocSearch.getChapterHeading().getText();
            if(chapterHeading3.contains(chapterHeading4)){
                Assert.fail("Result is not honor browse filter and then search text");
            }
        } catch (Exception e) {
            Assert.fail("Exception in test case browseSearch in class InstructorShouldBeAbleToSearchForResources", e);
        }
    }

    @Test(priority = 14, enabled = true)
    public void browseSearchBrowse() {
        try {
            //tc row no 331
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("331");//login as student
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            tocSearch.getImageIcon().click();
            Thread.sleep(1000);
            tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
            tocSearch.getSearch_Icon().click();//click on search
            Thread.sleep(1000);
            String chapterHeading=tocSearch.getChapterHeading().getText();
            tocSearch.getLessonIcon().click();
            Thread.sleep(1000);
            String chapterHeading1=tocSearch.getChapterHeading().getText();
            if(chapterHeading.contains(chapterHeading1)){
                Assert.fail("Result is not honor search text and new browse filter");
            }
            new LoginUsingLTI().ltiLogin("331_1");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            tocSearch.getImageIcon().click();
            Thread.sleep(1000);
            tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
            tocSearch.getSearch_Icon().click();//click on search
            Thread.sleep(1000);
            String chapterHeading3=tocSearch.getChapterHeading().getText();
            tocSearch.getLessonIcon().click();
            Thread.sleep(1000);
            String chapterHeading4=tocSearch.getChapterHeading().getText();
            if(chapterHeading3.contains(chapterHeading4)){
                Assert.fail("Result is not honor search text and new browse filter");
            }
        } catch (Exception e) {
            Assert.fail("Exception in test case browseSearchBrowse in class InstructorShouldBeAbleToSearchForResources", e);
        }
    }

}


