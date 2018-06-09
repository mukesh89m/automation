package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1910;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
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
 * Created by priyanka on 2/20/2015.
 */
public class StudentShouldBeAbleToSearchForResources extends Driver{

    @Test(priority = 1, enabled = true)
    public void theUserShouldBeAbleToSearchForResourceName() {
        try {
            //tc row no 120
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new ResourseCreate().resourseCreate(120,0);//create chpterlevel resource
            new LoginUsingLTI().ltiLogin("120");//login as student
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
            Assert.fail("Exception in test case theUserShouldBeAbleToSearchForResourceName in class StudentShouldBeAbleToSearchForResources", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void theUserShouldBeAbleToSearchForResourceDescription() {
        try {
            //tc row no 122
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new ResourseCreate().resourseCreate(122,0);//create chpterlevel resource
            new LoginUsingLTI().ltiLogin("122");//login as student
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            Thread.sleep(2000);
            tocSearch.getTocSearch().sendKeys("Description_ResourceName_122");
            tocSearch.getSearch_Icon().click();

            String resourceHeading = tocSearch.getGlossaryHeading().getText();
            String highlightedText = tocSearch.getHighlightedText().getAttribute("title");
            if (!resourceHeading.equals(highlightedText)) {
                Assert.fail("The search text is not highlighted.");
            }
            String descriptionText=tocSearch.getHighlightedText().getText();
            if(!descriptionText.contains("Description")){
                Assert.fail("The search result is not related to the entered resource Description");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case theUserShouldBeAbleToSearchForResourceDescription in class StudentShouldBeAbleToSearchForResources", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void theUserShouldBeAbleToSearchForSemanticTags() {
        try {
            //tc row no 124
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("124");//login as student
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            Thread.sleep(2000);
            tocSearch.getTocSearch().sendKeys("ResourceName");
            tocSearch.getDropDownItem().click();
            String resourceHeading = tocSearch.getGlossaryHeading().getText();
            if(!resourceHeading.contains("ResourceName_")) {
                Assert.fail("The search result is not related to the entered resource name");
            }            String resourceHeading1 = tocSearch.getGlossaryHeading().getText();
            String highlightedText = tocSearch.getHighlightedText().getAttribute("title");
            if (!resourceHeading1.equals(highlightedText)) {
                Assert.fail("The search text is not highlighted.");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case theUserShouldBeAbleToSearchForSemanticTags in class StudentShouldBeAbleToSearchForResources", e);
        }
    }


    @Test(priority = 4, enabled = true)
    public void autoSuggestShouldBeEnhancedToSuggestBasedOnTheResourceName() {
        try {
            //tc row no 126
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("126");//login as student
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
            Assert.fail("Exception in test case autoSuggestShouldBeEnhancedToSuggestBasedOnTheResourceName in class StudentShouldBeAbleToSearchForResources", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void autoSuggestShouldBeEnhancedToSuggestBasedOnTheSemanticTags() {
        try {
            //tc row no 130
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("130");//login as student
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
            Assert.fail("Exception in test case autoSuggestShouldBeEnhancedToSuggestBasedOnTheSemanticTags in class StudentShouldBeAbleToSearchForResources", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void theUserShouldBeAbleToClickOnTheSearchIconToInitiateTheSearch() {
        try {
            //tc row no 134
            TocSearch tocSearch;
            new ResourseCreate().resourseCreate(122,0);//create chpterlevel resource
            new ResourseCreate().createResourceAtCourseLevel(134, "Biology");//create courselevel resource
            new LoginUsingLTI().ltiLogin("134");//login as student
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            Thread.sleep(2000);
            tocSearch.getTocSearch().sendKeys("resourcename_122");
            tocSearch.getSearch_Icon().click();
            String courseHeading = tocSearch.getCourseLevel().getText();
            Assert.assertEquals(courseHeading, "Course Level", "CourseLevel Heading is not displaying");
            WebElement scroll=driver.findElement(By.className("search-list-chapter-level"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            Thread.sleep(1000);
            String chapterHeading = tocSearch.getChapterLevel().getText();
            Assert.assertEquals(chapterHeading, "Chapter Level", "ChapterLevel Heading is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case theUserShouldBeAbleToClickOnTheSearchIconToInitiateTheSearch in class StudentShouldBeAbleToSearchForResources", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void theCategoriesOfSearchResultsMustContainSearchBasedCriteriaAsAGroup() {
        try {
            //tc row no 138
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("138");//login as student
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
            Assert.fail("Exception in test case theCategoriesOfSearchResultsMustContainSearchBasedCriteriaAsAGroup in class StudentShouldBeAbleToSearchForResources", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void theFormatForEveryCardEntryForACourseLevelSearchResults() {
        try {
            //tc row no 142
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("142");//login as student
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            Thread.sleep(2000);
            tocSearch.getTocSearch().sendKeys("resourcename_122");
            tocSearch.getSearch_Icon().click();
            String animationText2 = tocSearch.getAnimationtext2().getText();
            if (!animationText2.contains("ResourceName_122")) {
                Assert.fail("Description is not at the 2nd line");
            }

            String animationText3 = tocSearch.getAnimationtext2().getText();
            if (!animationText3.contains("ResourceName_122")) {
                Assert.fail(" Description with highlighted search text is not displayed at line 2");
            }


        } catch (Exception e) {
            Assert.fail("Exception in test case theFormatForEveryCardEntryForACourseLevelSearchResults in class StudentShouldBeAbleToSearchForResources", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void theFormatForEveryCardEntryForAChapterLevelSearchResults() {
        try {
            //tc row no 153
            TocSearch tocSearch;
            tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("142");//login as student
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
            Assert.fail("Exception in test case theFormatForEveryCardEntryForAChapterLevelSearchResults in class StudentShouldBeAbleToSearchForResources", e);
        }
    }


}
