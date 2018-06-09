package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.apphelper.VideoSlideHelper;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by navin on 9/14/2016.
 */
public class Authoring {

    Actions actions = new Actions(CucumberDriver.getWebDriver());
    String lessonName = "";
    String totalItems = "";
    int lessonTags = 0;
    int slides = 0;
    String lessonLevel = "";
    AuthoringPage authoringPage = PageFactory.initElements(CucumberDriver.getWebDriver(), AuthoringPage.class);
    VideoPage videoPage = PageFactory.initElements(CucumberDriver.getWebDriver(), VideoPage.class);
    AuthorNewLessonPage authorNewLessonPage = PageFactory.initElements(CucumberDriver.getWebDriver(), AuthorNewLessonPage.class);
    LessonPage lessonpage = PageFactory.initElements(CucumberDriver.getWebDriver(), LessonPage.class);
    LessonSubscribePage lessonSubscribePage = PageFactory.initElements(CucumberDriver.getWebDriver(), LessonSubscribePage.class);
    HeaderPage header = PageFactory.initElements(CucumberDriver.getWebDriver(), HeaderPage.class);
    SignupPage signup = PageFactory.initElements(CucumberDriver.getWebDriver(), SignupPage.class);
    DateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
    String[] tags = null;
    String lessonDescription = "";

    @And("^I am on Authoring page$")
    public void I_am_on_Authoring_page() throws Throwable {
        authoringPage.main_Navigator_authoring_link.click();
    }

    @Then("^I should see ML Logo in Header$")
    public void I_should_see_ML_Logo_in_Header() throws Throwable {
        Assert.assertEquals("I can't see ML Logo in Header in ML home page", true, header.microLessonLogo.isDisplayed());
    }

    @And("^I should see the menu icon in the header of Authoring page$")
    public void I_should_see_the_menu_icon_in_the_header_of_Authoring_page() throws Throwable {
        Assert.assertEquals("Main navigator icon is not there", true, authoringPage.main_Navigator_icon.isDisplayed());
    }

    @And("^I should see the 'Authoring' label in the header of Authoring page$")
    public void I_should_see_the_Authoring_label_in_the_header_of_Authoring_page() throws Throwable {
        Assert.assertEquals("There is a problem with Authoring page Label Authoring", "Authoring", authoringPage.authoring_Page_label.getText().trim());
    }

    @And("^I should see the search box with the search icon in the header of Authoring page$")
    public void I_should_see_the_search_box_with_the_search_icon_in_the_header_of_Authoring_page() throws Throwable {
        Assert.assertEquals("There is problem with Search box placeholder text", "Search", authoringPage.authoring_page_searchBox.getAttribute("placeholder"));
        Assert.assertEquals("Problem with Authoring page search Icon", true, authoringPage.authoring_page_searchIcon.isDisplayed());
    }

    @And("^I should see the 'My Library' Icon with Label in the main navigator$")
    public void I_should_see_the_My_Library_Icon_with_Label_in_the_main_navigator() throws Throwable {
        actions.moveToElement(authoringPage.myLibraryIcon).build().perform();
        Assert.assertEquals("My Library icon is not visible", true, authoringPage.myLibraryIcon.isDisplayed());
        Assert.assertEquals("My library label is not visible", "My Library", authoringPage.mainNavigatorMenuLabels.get(0).getText().trim());
    }

    @And("^I should see the 'Explore' Icon with Label in the main navigator$")
    public void I_should_see_the_Explore_Icon_with_Label_in_the_main_navigator() throws Throwable {
        actions.moveToElement(authoringPage.exploreIcon).build().perform();
        Assert.assertEquals("My Library icon is not visible", true, authoringPage.exploreIcon.isDisplayed());
        Assert.assertEquals("My library label is not visible", "Explore", authoringPage.mainNavigatorMenuLabels.get(1).getText().trim());

    }

    @And("^I should see the 'Authoring' Icon with Label in the main navigator$")
    public void I_should_see_the_Authoring_Icon_with_Label_in_the_main_navigator() throws Throwable {
        actions.moveToElement(authoringPage.authoringIcon).build().perform();
        Assert.assertEquals("My Library icon is not visible", true, authoringPage.authoringIcon.isDisplayed());
        Assert.assertEquals("My library label is not visible", "Authoring", authoringPage.mainNavigatorMenuLabels.get(2).getText().trim());
    }


    @And("^I should see \"([^\"]*)\" status is selected$")
    public void I_should_see_status_is_selected(String lessonStatus) throws Throwable {

        if (lessonStatus.toLowerCase().equals("published") || lessonStatus.toLowerCase().equals("draft")) {
            CucumberDriver.getWebDriver().findElement(By.cssSelector("//li[@data-status='" + lessonStatus.toLowerCase().trim() + "']")).click();
        } else {
            Assert.fail(lessonStatus + " Does not supported as of now. Please Mention either 'published' or 'draft'");
        }

        if (lessonStatus.toLowerCase().equals("published")) {
            Assert.assertEquals("Published tab is not selected", true, authoringPage.publishedStatus_selected.isDisplayed());
            Assert.assertEquals("Tick for Published is not displayed", true, authoringPage.published_tick.isDisplayed());
            int count = Integer.parseInt(authoringPage.published_count.getText().trim().substring(1));
            String publishedStatusLabel = "Published (" + count + ")";

            if (count >= 0) {
                Assert.assertEquals("Published label with count is not correct", authoringPage.publishedLabel.getText().trim(), publishedStatusLabel);
            } else {
                Assert.fail("Lesson count is invalid (Negative)");
            }
            Assert.assertEquals("Draft label is not displayed while published selected", true, authoringPage.draftStatus_Not_selected.isDisplayed());
        } else if (lessonStatus.toLowerCase().equals("draft")) {
            Assert.assertEquals("Draft tab is not selected", true, authoringPage.draftStatus_selected.isDisplayed());
            Assert.assertEquals("Tick for Draft is not displayed", true, authoringPage.draft_tick.isDisplayed());
            int count = Integer.parseInt(authoringPage.draft_count.getText().trim());
            String draftStatusLabel = "Draft (" + count + ")";
            if (count >= 0) {
                Assert.assertEquals("Published label with count is not correct", authoringPage.draftLabel.getText().trim(), draftStatusLabel);
            } else {
                System.out.println("Lesson count is not correct");
            }
            Assert.assertEquals("Draft label is not displayed while published selected", true, authoringPage.publishedStatus_Not_selected.isDisplayed());

        }

    }

    @And("^I should see the Create Lesson card on the Authoring page$")
    public void I_should_see_the_Create_Lesson_card_on_the_Authoring_page() throws Throwable {
        Assert.assertEquals("Create lesson card is not visible", true, authoringPage.createLessonCard.isDisplayed());
        Assert.assertEquals("Create lesson card title is not correct", "Click to create new micro lesson", authoringPage.createLessonCardTitle.getText().trim());
        Assert.assertEquals("Create lesson card Plus icon is not there", true, authoringPage.createLessonCardPlusSymbol.isDisplayed());
        Assert.assertEquals("", "Create New", authoringPage.createLessonCardCreateNewText.getText().trim());
    }

    @When("^I Mouse hover on the lesson name on the card$")
    public void I_Mouse_hover_on_the_lesson_name_on_the_card() throws Throwable {
        actions.moveToElement(authoringPage.lessonTitles.get(0)).build().perform();
    }

    @Then("^I should see the full lesson name$")
    public void I_should_see_the_full_lesson_name() throws Throwable {
        String tempLessonName = authoringPage.lessonTitles.get(0).getText().trim();
        Assert.assertEquals("Lesson Title is not correct in tooltip", authoringPage.lessonTitles.get(0).getAttribute("title"), tempLessonName);
    }


    @And("^I am on the \"([^\"]*)\" Tab on Authoring page$")
    public void I_am_on_the_Tab_on_Authoring_page(String lessonStatus) throws Throwable {
        lessonStatus = lessonStatus.toLowerCase().trim();
        if (lessonStatus.equals("published") || lessonStatus.equals("draft")) {
            WebDriver driver = CucumberDriver.getWebDriver();
            if (!driver.findElement(By.cssSelector("li[data-status = '" + lessonStatus + "']")).getAttribute("class").contains("selected")) {
                driver.findElement(By.cssSelector("li[data-status = '" + lessonStatus + "']")).click();
            }
        } else {
            Assert.fail(lessonStatus + " Does not supported as of now. Please Mention either 'published' or 'draft'");
        }
    }

    @Then("^I should see the lessons authored by me only$")
    public void I_should_see_the_lessons_authored_by_me_only() throws Throwable {
        //Assert.assertEquals("Length of Lesson name is exceeding the expected length",150, authoringPage.authorNames.get(i).getText().length());
        Assert.assertEquals("Author Label is missing", "Author:", authoringPage.AuthorLabels.get(0).getText().trim());
        Assert.assertEquals("Lessons by another Authors are available", authoringPage.authorNames.get(0).getText().trim(), authoringPage.userName.getText().trim());

    }

    @And("^I should see the details of lesson card for \"([^\"]*)\" Tab in Authoring page$")
    public void I_should_see_the_details_of_lesson_card_for_Tab_in_Authoring_page(String lessonStatus) throws
            Throwable {
        if (lessonStatus.toLowerCase().equals("published")) {
            if (authoringPage.publishedStatus_selected_bydefault.isDisplayed()) {
            } else {
                authoringPage.publishedLabel.click();
            }
            for (int i = 0; i < 2; i++) {
                Assert.assertEquals("Lesson Name is null", true, authoringPage.authorNames.get(i).getText().trim() != null);
                Assert.assertEquals("Error with Published Label on Lesson card of authoring page", "Published", authoringPage.publishedLabel1.get(i).getText().trim());
            }
        } else if (lessonStatus.toLowerCase().equals("draft")) {
            authoringPage.draftLabel.click();
            for (int i = 0; i < 2; i++) {
                Assert.assertEquals("Lesson Name is null", true, authoringPage.authorNames.get(i).getText().trim() != null);
                Assert.assertEquals("Draft icon is not displayed", true, authoringPage.draftIcons.get(i).isDisplayed());
                Assert.assertEquals("Error with In Draft Label on Lesson card of authoring page", "In Draft", authoringPage.draftLabel1.get(i).getText().trim());
                Assert.assertEquals("Three vertical dots are missing", true, authoringPage.three_vertical_dots.get(i).isDisplayed());
            }
        } else {
            Assert.fail("Given " + lessonStatus + " status is Wrong. Please Mention either 'published' or 'draft' in the step");
        }
    }


    @Then("^I should see \"([^\"]*)\" and \"([^\"]*)\" buttons for the \"([^\"]*)\"$")
    public void I_should_see_and_buttons_for_the(String button1, String button2, String status) throws Throwable {

        if (status.toLowerCase().equals("draft")) {
            for (int i = 0; i < 2; i++) {
                actions.moveToElement(authoringPage.lessonThumbnails.get(i)).build().perform();
                Assert.assertEquals("Edit button is not displayed on hover", button1.toLowerCase().trim(), authoringPage.edit_Buttons.get(i).getAttribute("title").toLowerCase().trim());
                Assert.assertEquals("Preview button is not displayed on hover", button2.toLowerCase().trim(), authoringPage.preview_Buttons.get(i).getAttribute("title").toLowerCase().trim());
            }
        } else if (status.toLowerCase().equals("published")) {
            for (int i = 0; i < 2; i++) {
                actions.moveToElement(authoringPage.lessonThumbnails.get(i)).build().perform();
                Assert.assertEquals("Copy button is not displayed on hover", button1.toLowerCase().trim(), authoringPage.edit_Buttons.get(i).getAttribute("title").toLowerCase().trim());
                Assert.assertEquals("Preview button is not displayed on hover", button2.toLowerCase().trim(), authoringPage.preview_Buttons.get(i).getAttribute("title").toLowerCase().trim());
            }
        } else {
            Assert.fail("Given " + status + " status is Wrong. Please Mention either 'published' or 'draft' in the step");
        }
    }

    @Then("^I should see the lesson count at \"([^\"]*)\" status is equals to count at All filter label$")
    public void I_should_see_the_lesson_count_at_status_is_equals_to_count_at_All_filter_label(String
                                                                                                       lessonStatus) throws Throwable {

        if (lessonStatus.toLowerCase().trim().equals("published")) {
            Assert.assertEquals("Published count not matching with all filter count", authoringPage.filetrcounts.get(0).getText().trim(), authoringPage.publishStatusLessonCount.getText());
        } else if (lessonStatus.toLowerCase().trim().equals("draft")) {
            Assert.assertEquals("Draft count not matching with all filter count", authoringPage.filetrcounts.get(0).getText().trim(), authoringPage.draftStatusLessonCount.getText());
        } else {
            Assert.fail("Given " + lessonStatus + " status is Wrong. Please Mention either 'published' or 'draft' in the step");
        }

    }

    @Then("^I should see the lesson card count is equals to the count at \"([^\"]*)\" status$")
    public void I_should_see_the_lesson_card_count_is_equals_to_the_count_at_status(String lessonStatus) throws
            Throwable {
        if (lessonStatus.toLowerCase().equals("published")) {
            int count = Integer.parseInt(authoringPage.published_count.getText().trim());
            int loopCount = (count / 12);
            for (int i = 0; i <= loopCount; i++) {
                ((JavascriptExecutor) CucumberDriver.getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            }
            Assert.assertEquals("lesson count at Published status and lesson card count not matching", Integer.parseInt(authoringPage.publishStatusLessonCount.getText().trim()), authoringPage.lessonTitles.size());

        } else if (lessonStatus.toLowerCase().equals("draft")) {
            int count = Integer.parseInt(authoringPage.draft_count.getText().trim());
            int loopCount = (count / 12) - 1;
            for (int i = 0; i <= loopCount; i++) {
                ((JavascriptExecutor) CucumberDriver.getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            }
            Assert.assertEquals("lesson count at Draft status and lesson card count not matching", Integer.parseInt(authoringPage.draftStatusLessonCount.getText().trim()), authoringPage.lessonTitles.size());
        } else {
            Assert.fail("Given " + lessonStatus + " status is Wrong. Please Mention either 'published' or 'draft' in the step");
        }
    }

    @Then("^I should see the lesson count at \"([^\"]*)\" status is equals to addition of counts at Beginner Intermediate and Expert label$")
    public void I_should_see_the_lesson_count_at_status_is_equals_to_addition_of_counts_at_Beginner_Intermediate_and_Expert_label
            (String lessonStatus) throws Throwable {
        if (lessonStatus.toLowerCase().equals("published")) {
            int beginnerCount = Integer.parseInt(authoringPage.filetrcounts.get(1).getText().trim());
            int intermediateCount = Integer.parseInt(authoringPage.filetrcounts.get(2).getText().trim());
            int expertCount = Integer.parseInt(authoringPage.filetrcounts.get(3).getText().trim());
            int totalCount = Integer.parseInt(authoringPage.publishStatusLessonCount.getText().trim());
            Assert.assertEquals("Published count not matching with all three filter count", totalCount, beginnerCount + intermediateCount + expertCount);
        } else if (lessonStatus.toLowerCase().equals("draft")) {
            int beginnerCount = Integer.parseInt(authoringPage.filetrcounts.get(1).getText().trim());
            int intermediateCount = Integer.parseInt(authoringPage.filetrcounts.get(2).getText().trim());
            int expertCount = Integer.parseInt(authoringPage.filetrcounts.get(3).getText().trim());
            int totalCount = Integer.parseInt(authoringPage.draftStatusLessonCount.getText().trim());
            Assert.assertEquals("Draft count not matching with all three filter count", totalCount, beginnerCount + intermediateCount + expertCount);
        } else {
            Assert.fail("Given " + lessonStatus + " status is Wrong. Please Mention either 'published' or 'draft' in the step");
        }

    }

    @And("^I click on Exit button on Edit Lesson Details page$")
    public void I_click_on_Exit_button_on_Edit_Lesson_Details_page() throws Throwable {
        authoringPage.exitButton_Edit_Lesson.click();
        Thread.sleep(2000);
    }

    @And("^I click on Publish button$")
    public void I_click_on_Publish_button() throws Throwable {
        authoringPage.publishButton.click();
    }

    @Then("^I should see the Authoring Page$")
    public void I_should_see_the_Authoring_Page() throws Throwable {
        Assert.assertEquals("Authoring page is not displayed", "Authoring", authoringPage.authoring_Page_label.getText().trim());
    }

    @Then("^I should see the Delete option$")
    public void I_should_see_the_Delete_option() throws Throwable {
        Assert.assertEquals("Delete option is not there", "Delete", authoringPage.lessonDeleteOption.getText().trim());
    }


    @When("^I click on the Delete option for the lesson card$")
    public void I_click_on_the_Delete_option_for_the_lesson_card() throws Throwable {
        if (authoringPage.lessonDeleteOption.isDisplayed()) {
            authoringPage.lessonDeleteOption.click();
        } else {
            Assert.fail("!! Unable to Click on Delete Option !!");
        }
    }

    @Then("^I should see the Delete pop up with Delete icon followed by the 'Delete' as the header$")
    public void I_should_see_the_Delete_pop_up_with_Delete_icon_followed_by_the_Delete_as_the_header() throws
            Throwable {
        Thread.sleep(5000);
        Assert.assertEquals("Error with Delete pop up Title", "Delete", authoringPage.deletePopUpTitle.getText().trim());
    }

    @And("^I should see the close mark in the Header Delete popup$")
    public void I_should_see_the_close_mark_in_the_Header_Delete_popup() throws Throwable {
        Assert.assertEquals("Error with Delete pop up close icon", true, authoringPage.deletePopUpCloseIcon.get(0).isDisplayed());
    }


    @And("^I should see the \"([^\"]*)\" \"([^\"]*)\" as pop up message$")
    public void I_should_see_the_as_pop_up_message(String message1, String message2) throws Throwable {
        Assert.assertEquals("Error with Delete popup message1", message1, authoringPage.deletePopupMsg1.getText().trim());
        Assert.assertEquals("Error with Delete popup message1", message2, authoringPage.deletePopupMsg2.getText().trim());
    }

    @And("^I should see the 'No, Cancel' button on the Delete popup$")
    public void I_should_see_the_No_Cancel_button_on_the_Delete_popup() throws Throwable {
        Assert.assertEquals("Error with Delete pop up No Cancel button", "No, Cancel", authoringPage.deletePopUpCloseIcon.get(1).getText().trim());
    }

    @And("^I should see the 'Yes, Delete' button on the Delete popup$")
    public void I_should_see_the_Yes_Delete_button_on_the_Delete_popup() throws Throwable {
        Assert.assertEquals("Error with Delete pop up No Cancel button", "Yes, Delete", authoringPage.yesDeleteButton.getText().trim());
    }

    @When("^I click on 'No, Cancel'$")
    public void I_click_on_No_Cancel() throws Throwable {
        authoringPage.deletePopUpCloseIcon.get(1).click();
    }

    @And("^I click on the close mark of the Delete popup$")
    public void I_click_on_the_close_mark_of_the_Delete_popup() throws Throwable {
        authoringPage.deletePopUpCloseIcon.get(0).click();
    }

    @And("^I click on the 'Yes, Delete' button on the Delete popup$")
    public void I_click_on_the_Yes_Delete_button_on_the_Delete_popup() throws Throwable {
        authoringPage.yesDeleteButton.click();
    }

    @When("^I mouse hover on lesson card thumbnail on the Authoring page and Click on Copy$")
    public void I_mouse_hover_on_lesson_card_thumbnail_on_the_Authoring_page_and_Click_on_Copy() throws Throwable {
        tags = new String[authoringPage.lessonTagsBeforeCopy.size()];
        for (int i = 0; i < authoringPage.lessonTagsBeforeCopy.size(); i++) {
            tags[i] = authoringPage.lessonTagsBeforeCopy.get(i).getText().trim();
        }
        lessonName = authoringPage.lessonTitles.get(0).getText().trim();
        totalItems = authoringPage.totalSlides.get(0).getText().trim().substring(0, 1);
        slides = Integer.parseInt(totalItems);
        actions.moveToElement(authoringPage.lessonThumbnails.get(0)).build().perform();
        authoringPage.edit_Buttons.get(0).click();
    }

    @Then("^I should see the Lesson name as 'Copy of <Original Lesson name>' on Lesson Details Popup$")
    public void I_should_see_the_Lesson_name_as_Copy_of_Original_Lesson_name_on_Lesson_Details_Popup() throws
            Throwable {
        lessonName = "Copy of " + lessonName;
        Assert.assertEquals("", lessonName, lessonpage.lessonName_textbox.getAttribute("value").trim());
    }


    @And("^I should see other lesson details as previous with its \"([^\"]*)\" instructional level$")
    public void I_should_see_other_lesson_details_as_previous_with_its_instructional_level(String level) throws
            Throwable {
//        WebElement level = CucumberDriver.getWebDriver().findElement(By.xpath("//span[text()='" + link + " Level']/../div[@class='iradio_square-green checked']"));
//        Assert.assertEquals("Beginner Instructional Level is not selected on copy lesson", "Beginner", lessonpage.lessonDescription_textbox.getText().trim());
//        Assert.assertEquals("Instructional Level Mismatch", true, level.isDisplayed());

        WebDriver driver = CucumberDriver.getWebDriver();

        Assert.assertEquals("on Copy Level is getting changed to something else on Lesson Details page", "iradio_square-green checked", driver.findElement(By.xpath("//input[@id='" + level.toLowerCase() + "']/..")).getAttribute("class"));
    }

    @And("^I mouse hover on lesson card thumbnail on the Authoring page and Click on Edit$")
    public void I_mouse_hover_on_lesson_card_thumbnail_on_the_Authoring_page_and_Click_on_Edit() throws Throwable {
        lessonName = authoringPage.lessonTitles.get(0).getText().trim();
        actions.moveToElement(authoringPage.lessonThumbnails.get(0)).build().perform();
        authoringPage.edit_Buttons.get(0).click();
    }

    @Then("^I should see the header label as 'Lesson Name'$")
    public void I_should_see_the_header_label_as_Lesson_Name() throws Throwable {
        Assert.assertEquals("Error with Lesson Name on Edit lesson page", lessonName, lessonpage.lessonName_edit_page.getText().trim());
    }

    @And("^I should see the Edit Details button with pen icon$")
    public void I_should_see_the_Edit_Details_button_with_pen_icon() throws Throwable {
        Assert.assertEquals("Error with Edit Details button", "Edit Details", authoringPage.editDetailsButton.getText().trim());
    }

    @And("^I should see the 'Exit' button in the header in edit lesson page$")
    public void I_should_see_the_Exit_button_in_the_header_in_edit_lesson_page() throws Throwable {
        Assert.assertEquals("Error with exit button on Edit lesson page", "Exit", authoringPage.editButtonEditLessonPage.getText().trim());

    }

    @And("^I should see the 'Publish' button in the header in edit lesson page$")
    public void I_should_see_the_Publish_button_in_the_header_in_edit_lesson_page() throws Throwable {
        Assert.assertEquals("Error with publish button on Edit lesson page", "Publish", authoringPage.publishButtonEditLessonPage.getText().trim());
    }

    @And("^I should see the 'Preview' button in the header in edit lesson page$")
    public void I_should_see_the_Preview_button_in_the_header_in_edit_lesson_page() throws Throwable {
        Assert.assertEquals("Error with publish button on Edit lesson page", "Preview", authoringPage.previewButtonEditLessonPage.getText().trim());
    }

    @When("^I mouse hover on lesson card thumbnail on the Authoring page and Click on preview$")
    public void I_mouse_hover_on_lesson_card_thumbnail_on_the_Authoring_page_and_Click_on_preview() throws Throwable {
        lessonName = authoringPage.lessonTitles.get(0).getText().trim();
        totalItems = authoringPage.totalSlides.get(0).getText().trim().substring(0, 1);
        actions.moveToElement(authoringPage.lessonThumbnails.get(0)).build().perform();
        WebDriverUtil.waitTillVisibilityOfElement(authoringPage.preview_Buttons.get(0), 60);
        authoringPage.preview_Buttons.get(0).click();
    }

    @Then("^I should see as header label on Preview page$")
    public void I_should_see_as_header_label_on_Preview_page() throws Throwable {

        String previewPageTitle = authoringPage.previewLabelOfPreviewPage.getText().trim() + " " + lessonName.trim();
        Assert.assertEquals("Error with Preview Page Label", "Preview - " + lessonName.trim(), previewPageTitle.trim());
    }

    @And("^I should see Top section of the slide with Label 'Level - <Level : as added by the author >'$")
    public void I_should_see_Top_section_of_the_slide_with_Label_Level_Level_as_added_by_the_author_() throws
            Throwable {

        if (authoringPage.previewLevel.getText().trim().equals("Level: Beginner")) {
            lessonLevel = "Beginner";
        } else if (authoringPage.previewLevel.getText().trim().equals("Level: Intermediate")) {
            lessonLevel = "Intermediate";
        } else if (authoringPage.previewLevel.getText().trim().equals("Level: Expert")) {
            lessonLevel = "Expert";
        } else {
            Assert.fail("Published Lesson's Level label Not found");
        }
    }

    @And("^I should see Top section of the slide with Label 'Level : \"([^\"]*)\" '$")
    public void I_should_see_Top_section_of_the_slide_with_Label_Level_(String lessonLevel) throws Throwable {
        Assert.assertEquals("Error with Level label on preview lesson page slide", "level: " + lessonLevel.toLowerCase(), authoringPage.previewLevel.getText().trim().toLowerCase().trim());
    }

    @And("^I should see the First slide view on the Preview page$")
    public void I_should_see_the_First_slide_view_on_the_Preview_page() throws Throwable {
        Assert.assertEquals("", true, authoringPage.slidePreview.isDisplayed());
    }


    @And("^I should see Top section of the slide with Label 'Free'$")
    public void I_should_see_Top_section_of_the_slide_with_Label_Free() throws Throwable {
        Assert.assertEquals("Error With Free Label", "Free", authoringPage.freeLabel.getText().trim());
    }

    @And("^I should see Top section of the slide with Label \"([^\"]*)\" button$")
    public void I_should_see_Top_section_of_the_slide_with_Label_button(String lessonStatus) throws
            Throwable {

        if (lessonStatus.toLowerCase().equals("published")) {
            Assert.assertEquals("", lessonStatus.toLowerCase(), authoringPage.slidePreviewPublishedButton.getText().trim().toLowerCase().trim());
        } else if (lessonStatus.toLowerCase().equals("publish")) {
            Assert.assertEquals("", lessonStatus.toLowerCase(), authoringPage.slidePreviewPublishButton.getText().trim().toLowerCase().trim());
        } else {
            Assert.fail("Error with " + lessonStatus + " button on lesson preview page. Please choose from 'published' or 'publish' ");
        }
    }

    @And("^I should see the Full screen button on the slide$")
    public void I_should_see_the_Full_screen_button_on_the_slide() throws Throwable {
        Assert.assertEquals("Expand button is missin on slide preview", true, authoringPage.previewExpandButton.isDisplayed());
    }

    @And("^I should see the label 'Slide x/y' on the slide$")
    public void I_should_see_the_label_Slide_x_y_on_the_slide() throws Throwable {
        Assert.assertEquals("", "Slide 1/" + totalItems.trim(), authoringPage.slideNumberOutOfTotal.getText().trim());

    }

    @And("^I should see the Preview button with forward icon on the slide view$")
    public void I_should_see_the_Preview_button_with_forward_icon_on_the_slide_view() throws Throwable {
        slides = Integer.parseInt(totalItems);
        if (slides == 1) {
            Assert.assertEquals("On only one slide Preview button is not disabled", true, authoringPage.slidePreviewButtonDisabled.isDisplayed());
        } else if (slides > 1) {
            Assert.assertEquals("On more than one slide Preview button is not enabled", true, authoringPage.slidePreviewButtonEnabled.isDisplayed());
        } else {
            Assert.fail("Preview button is not present on slide preview mode");
        }
    }

    @And("^I should be able to navigate through all the slides of the lesson$")
    public void I_should_be_able_to_navigate_through_all_the_slides_of_the_lesson() throws Throwable {

        if (slides > 1) {
            Assert.assertEquals("Preview Button is not Enabled", true, authoringPage.slidePreviewButtonEnabled.isDisplayed());
            for (int i = 0; i < slides - 1; i++) {
                authoringPage.slidePreviewButtonEnabled.click();
            }
        } else if (slides == 1) {
            Assert.assertEquals("Preview Button is not Enabled", true, authoringPage.slidePreviewButtonDisabled.isDisplayed());
        }

    }

    @And("^I should see the Label 'published' - <Date on Which it is published in DD Mon Year>$")
    public void I_should_see_the_Label_published_Date_on_Which_it_is_published_in_DD_Mon_Year() throws Throwable {

        Date date = new Date();
        String date1 = dateFormat.format(date);
        // Assert.assertEquals("", "Published:", authoringPage.labelBelowSlidePreview.get(0).getText().trim().substring(0, 10));

        System.out.println("Published: " + date1);
        System.out.println(authoringPage.publishedLabelWithDate.getText().trim());

        Assert.assertEquals("incorrect published date", "Published: " + date1, authoringPage.publishedLabelWithDate.getText().trim());

    }

/*    @And("^I am on \"([^\"]*)\" Filter$")
    public void I_am_on_Filter(String arg1) throws Throwable {
        int lessonTags = authoringPage.lessonTags.size();
        CucumberDriver.getWebDriver().findElement(By.cssSelector(".ml-lesson-filter-btn.dl-filter-" + arg1 + ".js-lesson-filter-btn.white-bg")).click();
    }*/

    @And("^I am on \"([^\"]*)\" Filter$")
    public void I_am_on_Filter(String level) throws Throwable {
        CucumberDriver.getWebDriver().findElement(By.cssSelector(".ml-lesson-filter-btn.dl-filter-" + level + ".js-lesson-filter-btn.white-bg")).click();

        lessonTags = authoringPage.lessonTags.size();
        String[] tags = new String[lessonTags];
        for (int i = 0; i < lessonTags; i++) {
            tags[i] = authoringPage.lessonTags.get(i).getText().trim();
        }
    }

    @And("^I should see the label 'Author - <Author name>'$")
    public void I_should_see_the_label_Author_Author_name_() throws Throwable {

        System.out.println(authoringPage.authorAuthorName.get(0) + " " + authoringPage.authorAuthorName.get(1));

        Assert.assertEquals("Error with Author Label below the slide preview", "Author: " + authoringPage.userName.getText().trim(), authoringPage.authorAuthorName.get(0).getText().trim() + " " + authoringPage.authorAuthorName.get(1).getText());
    }

    @And("^I should see the tags added to the lesson$")
    public void I_should_see_the_tags_added_to_the_lesson() throws Throwable {

        for (int i = 0; i < lessonTags; i++) {

            Assert.assertEquals("Tags are not matching in case of count and tag name ", authoringPage.lessonTagsInPreview.get(i).getText().trim(), authoringPage.originalLessonTags.get(i).getText().trim());
        }

    }

    @And("^I should see the Description added to the Lesson$")
    public void I_should_see_the_Description_added_to_the_Lesson() throws Throwable {

        Assert.assertEquals("Lesson Description is not correct ", "Lesson Description", authoringPage.previewLessonDescription.getText().trim());

    }

    @When("^I click on Publish button on Lesson Edit Details page$")
    public void I_click_on_Publish_button_on_Lesson_Edit_Details_page() throws Throwable {
        authoringPage.publishButton.click();
        WebDriverUtil.waitForAjax(CucumberDriver.getWebDriver(),60);
    }

    @And("^I should see the lesson published in the Published Tab$")
    public void I_should_see_the_lesson_published_in_the_Published_Tab() throws Throwable {

        Boolean lessonFound = Boolean.FALSE;
//        WebDriverUtil.clickOnElementUsingJavascript(authoringPage.draft_link); //click on the draft link
        Thread.sleep(3000);
        for (WebElement ele : authoringPage.allLessonCard_title) {
            if (ele.getAttribute("title").equals(lessonName)) {
                lessonFound = Boolean.TRUE;
                break;
            }
        }
        if (lessonFound == Boolean.FALSE) {
            Assert.fail("Lesson has not Published created!!!");
        }
    }

    @And("^I mouse hover on the Lesson name in the header$")
    public void I_mouse_hover_on_the_Lesson_name_in_the_header() throws Throwable {
        actions.moveToElement(authoringPage.previewPageLessonName).build().perform();

    }

    @Then("^I should see the full lesson name as tool tip$")
    public void I_should_see_the_full_lesson_name_as_tool_tip() throws Throwable {
        Assert.assertEquals("", lessonName, authoringPage.previewPageLessonName.getText().trim());
    }


    @When("^I click on the Back arrow in the header$")
    public void I_click_on_the_Back_arrow_in_the_header() throws Throwable {
        WebDriverUtil.waitTillVisibilityOfElement(authoringPage.headerBackIcon, 60);
        authoringPage.headerBackIcon.click();
    }

    @And("^I should see Explore All Lesson Page$")
    public void I_should_see_Explore_All_Lesson_Page() throws Throwable {
        Assert.assertEquals("Explore page after login is not displayed", "Explore All LessonsExplore All Lessons", authoringPage.authoring_Page_label.getText().trim());
    }


    @And("^I should see Username \"([^\"]*)\"$")
    public void I_should_see_Username(String userName) throws Throwable {
        Assert.assertEquals("User Name Is not displayed", userName, authoringPage.userName.getText().trim());
    }

    @When("^I click on user name$")
    public void I_click_on_user_name() throws Throwable {
        authoringPage.userName.click();
    }

    @And("^I click on the Logout button$")
    public void I_click_on_the_Logout_button() throws Throwable {
        Thread.sleep(5000);
        Assert.assertEquals("Logout button Label is not correct", "Logout", authoringPage.logoutOption.getText().trim());
        WebDriverUtil.clickOnElementUsingJavascript(authoringPage.logoutOption);
    }

    @Then("^I should see the Home Page$")
    public void I_should_see_the_Home_Page() throws Throwable {
        Assert.assertEquals("Sign Up link is not present in the application page", "Sign Up", signup.signUp_link.getText().trim());
        Assert.assertEquals("Login link is not present in the application page", "Login", signup.login_button.getText().trim());
    }
    @Then("^I should see the Lesson Edit details page$")
    public void I_should_see_the_Lesson_Edit_details_page() throws Throwable {
        Assert.assertEquals("I can't see the Lesson Edit details page",authoringPage.editDetailsButton.isDisplayed(),true);
    }

    @And("^I should be able to add new slide \"([^\"]*)\" to the lesson$")
    public void I_should_be_able_to_add_new_slide_to_the_lesson(String type) throws Throwable {
        if(type.equalsIgnoreCase("Video")) {
            boolean isVideoAdded = new VideoSlideHelper().addVideoSlide("Physics");
            if(!isVideoAdded){
                Assert.fail("I can't add new slide \"([^\"]*)\" to the lesson$");
            }
        }
    }

    @And("^I should be able to delete slide from the lesson$")
    public void I_should_be_able_to_delete_slide_from_the_lesson() throws Throwable {
        int previousSlidesCount  = authorNewLessonPage.slide_containerList.size();
        videoPage.deleteSlide_link.click();
        authorNewLessonPage.btnYesDelete.click();
        int currentSlidesCount  = authorNewLessonPage.slide_containerList.size()-1;
        if(currentSlidesCount>=previousSlidesCount){
            Assert.fail("I can't add new slide \"([^\"]*)\" to the lesson$");
        }

    }

    @And("^I should be able to modify the \"([^\"]*)\" slide details$")
    public void I_should_be_able_to_modify_the_slide_details(String type) throws Throwable {
        if(type.equalsIgnoreCase("Video")) {
            boolean isVideoAdded = new VideoSlideHelper().editVideoSlide("Chemistry");
            if(!isVideoAdded){
                Assert.fail("I can't add new slide \"([^\"]*)\" to the lesson$");
            }
        }
    }

    @Then("^I should see the details as modified in the Lesson Preview page$")
    public void I_should_see_the_details_as_modified_in_the_Lesson_Preview_page() throws Throwable {
        CucumberDriver.getWebDriver().switchTo().frame(videoPage.youtube_frame);
        Assert.assertEquals("I can't see the details as modified in the Lesson Preview page", VideoSlideHelper.videoSlideNameBeforeAdding, videoPage.videoSlideText.getText().trim());
    }

    @And("^I click on Save button on Lesson Details page$")
    public void I_click_on_Save_button_on_Lesson_Details_page() throws Throwable {
        tags = new String[authoringPage.lessonTagsBeforeCopy.size()];
        for (int i = 0; i < authoringPage.lessonTagsBeforeCopy.size(); i++) {
            tags[i] = authoringPage.lessonTagsBeforeCopy.get(i).getText().trim();
        }
        lessonDescription = authoringPage.lessonDescriptionBeforeCopy.getText().trim();
        authoringPage.saveButtonLessonDetails.click();
    }

//    @Then("^I should see the Lesson Edit details page$")
//    public void I_should_see_the_Lesson_Edit_details_page() throws Throwable {
//        Assert.assertEquals("Edit lesson page is not displayed", true, authoringPage.editDetailsButton.isDisplayed());
//    }

    @And("^I should see the header name as updated lesson name$")
    public void I_should_see_the_header_name_as_updated_lesson_name() throws Throwable {
        Assert.assertEquals("On Edit Details page Lesson Name is not correct", lessonName, authoringPage.previewPageLessonName.getText().trim());
    }

    @And("^I should see all the slide of the lesson$")
    public void I_should_see_all_the_slide_of_the_lesson() throws Throwable {
        Assert.assertEquals("All slides have not copied on lesson copy.", slides, authoringPage.slidesOnEditDetailsPage.size());
    }

    @When("^I click on copied lesson$")
    public void I_click_on_copied_lesson() throws Throwable {

//        for (int i = 0; i < authoringPage.originalLessonTags.size(); i++) {
//            tags[i] = authoringPage.originalLessonTags.get(i).getText().trim();
//        }
        Assert.assertEquals("Lesson Copied Not found", lessonName, authoringPage.allLessonCard_title.get(0).getText().trim());
        authoringPage.allLessonCard_title.get(0).click();
    }

    @And("^I should see the publish button on slide preview$")
    public void I_should_see_the_publish_button_on_slide_preview() throws Throwable {
        Assert.assertEquals("Error with Publish button on slide preview", "Publish", authoringPage.slidePreviewPublishButton.getText().trim());
    }

    @And("^I should see the \"([^\"]*)\" button on slide preview$")
    public void I_should_see_the_button_on_slide_preview(String slideButton) throws Throwable {
        Assert.assertEquals("Error with Publish button on slide preview", slideButton.toLowerCase(), authoringPage.slidePreviewPublishButton.getText().trim().toLowerCase());
    }
}
