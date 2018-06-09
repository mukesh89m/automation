package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.snapwiz.selenium.tests.staf.framework.utility.Properties;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.apphelper.Lesson;
import com.snapwiz.selenium.tests.staf.microlearning.apphelper.NavigatorHelper;
import com.snapwiz.selenium.tests.staf.microlearning.apphelper.RobotHelper;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.AuthoringPage;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.LessonPage;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.SideMenuLink;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import cucumber.runtime.RuntimeOptions;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by mukesh on 24/8/16.
 */
public class CreateLesson {

    Actions actions = new Actions(CucumberDriver.getWebDriver());
    LessonPage lesson = PageFactory.initElements(CucumberDriver.getWebDriver(), LessonPage.class);
    AuthoringPage authoringPage = PageFactory.initElements(CucumberDriver.getWebDriver(), AuthoringPage.class);
    SideMenuLink sideMenuLink = PageFactory.initElements(CucumberDriver.getWebDriver(), SideMenuLink.class);

    public static String lessonName = "";


    @Then("^Lesson pop-up should be displayed$")
    public void lessonPopUpShouldBeDisplayed() throws Throwable {
        Assert.assertEquals("Lesson pop up is not displaying when i click on the create lesson link", "×", lesson.lesson_close_icon.getText().trim());
    }

    @When("^I enter lesson name \"([^\"]*)\" in lesson name textbox$")
    public void iEnterLessonNameInLessonNameTextbox(String name) throws Throwable {
        lessonName = name + StringUtil.generateRandomString(5, StringUtil.Mode.ALPHANUMERIC);
        lesson.lessonName_textbox.sendKeys(lessonName);
    }

    @When("^I select instructional level \"([^\"]*)\"$")
    public void iSelectInstructionalLevel(String level) throws Throwable {
        if (level.equals("Beginner Level")) {
            lesson.beginner_radio_button.click();
        }
        if (level.equals("Intermediate Level")) {
            lesson.intermediate_radio_button.click();
        }
        if (level.equals("Expert Level")) {
            lesson.expert_radio_button.click();
        }
    }

    @When("^I enter tags \"([^\"]*)\" in lesson pop up$")
    public void iEnterTagsInLessonPopUp(String tag) throws Throwable {

        String[] value = tag.split(",");
        for (String ele : value) {
            lesson.enterTag_textbox.sendKeys(ele);
            Thread.sleep(2000);
            new RobotHelper().sendKeyBoardKeys("^");
        }
    }

    @When("^I click on the Save button in lesson pop up$")
    public void iClickOnTheSaveButtonInLessonPopUp() throws Throwable {
        lesson.lesson_save_button.click();

    }

    @Then("^I should see Lesson created successfully$")
    public void IshouldseeLessoncreatedsuccessfully() throws Throwable {
        Thread.sleep(4000);
        new NavigatorHelper().navigateTo("Authoring");
        Boolean lessonFound = Boolean.FALSE;
        WebDriverUtil.clickOnElementUsingJavascript(authoringPage.draft_link); //click on the draft link
        Thread.sleep(3000);
        for (WebElement ele : authoringPage.allLessonCard_title) {
            if (ele.getAttribute("title").equals(lessonName)) {
                lessonFound = Boolean.TRUE;
                break;
            }
        }
        if (lessonFound == Boolean.FALSE) {
            Assert.fail("Lesson has not created!!!");
        }
    }


    @Then("^I should see validation message for name as \"([^\"]*)\"$")
    public void iShouldSeeValidationMessageForNameAs(String message) throws Throwable {
        Assert.assertEquals("Error message is not displaying corect for empty lesson name", message, lesson.lesson_creation_error_message.get(0).getText().trim());

    }

    @And("^I should see validation message for instructional level as \"([^\"]*)\"$")
    public void iShouldSeeValidationMessageForInstructionalLevelAs(String message) throws Throwable {
        Assert.assertEquals("Error message is not displaying corect for empty InstructionalLevel", message, lesson.lesson_creation_error_message.get(1).getText().trim());

    }

    @When("^I click on the Cancel button in lesson pop up$")
    public void iClickOnTheCancelButtonInLessonPopUp() throws Throwable {
        lesson.lesson_cancel_button.click();
        Thread.sleep(2000);
    }

    @Then("^Lesson creation pop up should be closed$")
    public void lessonCreationPopUpShouldBeClosed() throws Throwable {
        boolean popup = WebDriverUtil.isElementPresent(lesson.lesson_popup);
        Assert.assertFalse("Lesson creation pop up is not getting close", popup);


    }

    @When("^I click on the x icon in lesson pop up$")
    public void iClickOnTheXIconInLessonPopUp() throws Throwable {
        lesson.lesson_close_icon.click();
        Thread.sleep(2000);
    }

    @When("^I click on the Upload image link in lesson pop up$")
    public void iClickOnTheUploadImageLinkInLessonPopUp() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(lesson.upload_image_link);

    }

    @And("^I upload \"([^\"]*)\" image$")
    public void iUploadImage(String filename) throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(lesson.pickFile_link);
        String fileUploadPath = System.getProperty("user.dir") + "/" + Properties.getPropertyValue("FileUploadPath");
        new RobotHelper().sendKeyBoardKeys("$" + fileUploadPath + filename.trim() + "^");
        Thread.sleep(10000);
        WebDriverUtil.clickOnElementUsingJavascript(lesson.upload_button);
        Thread.sleep(10000);
    }

    @And("^Upload file having size more than (\\d+) MB$")
    public void uploadFileHavingSizeMoreThanMB(int arg) throws Throwable {

    }

    @Given("^Create lesson with details in the below table$")
    public void createLessonWithDetailsInTheBelowTable(DataTable datatable) throws Throwable {
        for (Map<String, String> data : datatable.asMaps(String.class, String.class)) {
            lessonName = new Lesson().createLesson(data.get("Lesson Name"), data.get("Instructional Level"), data.get("Tags"), Boolean.parseBoolean(data.get("Upload Image")), data.get("Filename"), data.get("Description"));
        }
    }

    @And("^I click on the \"([^\"]*)\" link in authoring page$")
    public void iClickOnTheLinkInAuthoringPage(String link) throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(CucumberDriver.getWebDriver().findElement(By.xpath("//div[.='" + link + "']")));
        Thread.sleep(4000);
    }


    @When("^I navigate to \"([^\"]*)\" page$")
    public void iNavigateToPage(String menu) throws Throwable {
        new NavigatorHelper().navigateTo(menu);
    }

    @When("^I upload image in lesson pop up$")
    public void iUploadImageInLessonPopUp() throws Throwable {

    }

    @When("^I upload image\"([^\"]*)\" in lesson pop up$")
    public void iUploadImageInLessonPopUp(String filename) throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(lesson.upload_image_link);
        WebDriverUtil.clickOnElementUsingJavascript(lesson.pickFile_link);
        String fileUploadPath = System.getProperty("user.dir") + "/" + Properties.getPropertyValue("FileUploadPath");
        new RobotHelper().sendKeyBoardKeys("$" + fileUploadPath + filename + "^");
        Thread.sleep(10000);
        WebDriverUtil.clickOnElementUsingJavascript(lesson.upload_button);
        WebDriverUtil.waitTillVisibilityOfElement(lesson.succussfulImage_link, 60);

    }

    @Then("^I should see \"([^\"]*)\" link on pop up$")
    public void iShouldSeeLinkOnPopUp(String replaceImage) throws Throwable {
        Assert.assertEquals("Replace image link is not displaying in popup", replaceImage.trim(), lesson.replaceImage_link.getText().trim());

    }

    @When("^I click on Tag text box of lesson popup$")
    public void iClickOnTagTextBoxOfLessonPopup() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(lesson.enterTag_textbox);
    }

    @Then("^I should see text \"([^\"]*)\"$")
    public void iShouldSeeText(String message) throws Throwable {
        Assert.assertEquals("Please enter 2 or more characters suggestion in not displaying!", message.trim(), lesson.tag_results.get(0).getText().trim());
    }


    @Then("^I should see previously added \"([^\"]*)\" in the autosuggestion$")
    public void iShouldSeePreviouslyAddedInTheAutosuggestion(String tag) throws Throwable {
        Thread.sleep(3000);
        ArrayList<String> suggestion = new ArrayList<>();
        for (WebElement element : lesson.tag_results) {
            if (element.isDisplayed()) {
                System.out.println(":" + element.getText().trim());
                suggestion.add(element.getText().trim());
            }
        }

        Boolean lessonFound = Boolean.FALSE;
        for (int i = 0; i < suggestion.size() - 1; i++) {
            if ((suggestion.get(i).contains(tag))) {
                lessonFound = Boolean.TRUE;
            }
        }
        if (lessonFound == Boolean.FALSE) {
            Assert.fail("Previously added tags in the autosuggestion is not displaying:");
        }
    }


    @When("^I enter tag \"([^\"]*)\" in lesson pop up$")
    public void iEnterTagInLessonPopUp(String tag) throws Throwable {
        String[] value = tag.split(",");
        for (String ele : value) {
            lesson.enterTag_textbox.sendKeys(ele);
            Thread.sleep(2000);
        }
    }


    @When("^I select \"([^\"]*)\" from autosuggestion$")
    public void iSelectFromAutosuggestion(String arg) throws Throwable {
        for (WebElement element : lesson.tag_results) {
            if (element.isDisplayed()) {
                if (element.getText().trim().equals(arg)) {
                    element.click();
                    break;
                }
            }
        }
    }

    @Then("^I should see \"([^\"]*)\" added in the tag field$")
    public void iShouldSeeAddedInTheTagField(String tag) throws Throwable {
        Assert.assertEquals("Selected tag is not added in the tag field!!", tag.trim(), lesson.selected_tag.getAttribute("title").trim());

    }

    @When("^I enter more than (\\d+) tags$")
    public void iEnterMoreThanTags(int arg0) throws Throwable {

    }

    @When("^I click on the three vertical dots of the lesson card$")
    public void I_click_on_the_three_vertical_dots_of_the_lesson_card() throws Throwable {
        for (int i = 0; i < 2; i++) {
            if (authoringPage.lessonTitles.get(i).getText().equals(lessonName)) {
                authoringPage.three_vertical_dots.get(i).click();
                break;
            } else {
                Assert.fail("Three dots for Delete option for the lesson card is not available");
            }
        }

    }

    @Then("^I should not see deleted lesson in Draft Tab$")
    public void I_should_not_see_deleted_lesson_in_Draft_Tab() throws Throwable {
        Boolean lessonFound = Boolean.TRUE;
        for (WebElement ele : authoringPage.allLessonCard_title) {
            if (ele.getAttribute("title").equals(lessonName)) {
                lessonFound = Boolean.FALSE;
                break;
            }
        }
        if (lessonFound == Boolean.TRUE) {
            Assert.fail("Lesson has not deleted!!!");
        }
    }

    @Then("^I should see error message as \"([^\"]*)\" based on the Invalid File in pop up$")
    public void iShouldSeeErrorMessageAsBasedOnTheInvalidFileInPopUp(String errorMessage) throws Throwable {
        Assert.assertEquals("Error message is not displaying for invalid format file", errorMessage.trim(), lesson.invalidFileError_message.getText().trim());

    }

    @And("^I should see the lesson saved in the Draft Tab$")
    public void I_should_see_the_lesson_saved_in_the_Draft_Tab() throws Throwable {
        Boolean lessonFound = Boolean.FALSE;
        WebDriverUtil.clickOnElementUsingJavascript(authoringPage.draft_link); //click on the draft link
        Thread.sleep(3000);
        for (WebElement ele : authoringPage.allLessonCard_title) {
            if (ele.getAttribute("title").equals(lessonName)) {
                lessonFound = Boolean.TRUE;
                break;
            }
        }
        if (lessonFound == Boolean.FALSE) {
            Assert.fail("Lesson has not Saved created!!!");
        }

    }

}
