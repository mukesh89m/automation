package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by pragyas on 25-07-2016.
 */
public class AddQuestion {


    @FindBy(xpath = "(//input[@class='select2-search__field'])[1]")
    public WebElement editBox_searchQuestion;//Search question edit box

    @FindBy(className = "btn btn-default")
    public WebElement search_QuestionByTag;//Search to get the question

    @FindBy(xpath = "//label[@class='as-grey-checkbox']")
    public List<WebElement> checkbox_question;//checkbox to select questions

    @FindBy(css = "button[class^='btn btn-rounded btn-default authorQuestion']")
    public WebElement button_authorNewQuestion;//Author new Question button

    @FindBy(css=".cancelQuestion>span")
    public WebElement btn_CancelQuestion;

    @FindBy(css=".essay-question-rubric-wrapper>label")
    public WebElement chkbox_GradingRubric;

    @FindBy(css=".new-rubric-label.left-chk-box")
    public WebElement label_CreateNewRubric;

    @FindBy(css="div[class^='use-rubric-label ']")
    public WebElement label_UseExistingRubric;

    @FindBy(css="#name")
    public WebElement editBox_RubricName;

    @FindBy(css="#description")
    public WebElement editBox_RubricDescription;

    @FindBy(css=".create-rubric-link")
    public WebElement btn_RubricSaveAndContinue;

    @FindBy(css="#rubric-search-input")
    public WebElement editBox_RubricSearch;

    @FindBy(css=".as-errorMsg.login-email-message.as-errorMsg-bottom")
    public WebElement errMsg_RubricName;

    @FindBy(css=".create-criteria")
    public WebElement createCriteria;

    @FindBy(css=".name.criteria")
    public WebElement lbl_CriteriaName1;

    @FindBy(css =".desc.criteria" )
    public WebElement lbl_CriteriaDesc1;

    @FindAll(@FindBy(css=".name.rating"))
    public List<WebElement> lstLbl_CriteriaRatings;

    @FindAll(@FindBy(css=".points.rating"))
    public List<WebElement> lstLbl_CriteriaPoints;

    @FindBy(css=".form-control.p-xxs.inline")
    public WebElement editBox_CriteriaName1;

    @FindBy(css=".form-control.p-xss")
    public WebElement editBox_CriteriaDesc1;

    @FindBy(css=".form-control.p-xxs")
    public WebElement editBox_CriteriaRating;

    @FindBy(css=".add-rating-icon")
    public WebElement btn_AddRating;

    @FindBy(css=".add-criteria-btn")
    public WebElement btn_AddCriteria;

    @FindBy(css=".criteria-action-btn>.cancel")
    public WebElement btn_RubricCriteriaCancel;

    @FindBy(css="#save-rubric>span")
    public WebElement btn_RubricCriteriaSave;

    @FindBy(css = ".desc.criteria")
    public WebElement criteria_Description;
    @FindBy(css = ".rubric-name-edit-icon")
    public WebElement btn_RubricNameEdit;

    @FindBy(css = ".desc.rating")
    public List<WebElement> rating_Description;
    @FindBy(css=".rubric-name-container>.name")
    public WebElement lbl_RubricName;

    @FindBy(css = "textarea[name='criteria-desc']")
    public WebElement criteriaDescription_textarea;
    @FindBy(css=".edit-rubric-link")
    public WebElement lnk_EditRubric;

    @FindBy(css = "#save-rubric")
    public WebElement save_rubric_button;
    @FindBy(css=".share-rubric-link")
    public WebElement lnk_ShareRubric;

    @FindBy(css = "span[class^='link-color edit-rubric-link']")
    public WebElement edit_link;

    @FindBy(css = "span[class^='link-color share-link']")
    public WebElement share_link;

    @FindBy(css = "span[class='ed-icon icon-unshare share-rubric']")
    public WebElement unshare_link;

    @FindBy(css = "span[class='ed-icon share-rubric icon-unshare']")
    public WebElement unshare_link_otherTeacher;

    @FindBy(css = "span[class^='link-color delete-rubric-link']")
    public WebElement delete_link;

    @FindBy(css = "div[class^='rubric-name ']")
    public WebElement preview_rubric_name;

    @FindBy(css =".use-rubric-desc")
    public WebElement preview_rubric_description;

    @FindBy(css = "span[class^='ed-icon icon-pencil2 rubric-name-edit-icon link-color']")
    public WebElement rubric_name_edit_icon;

    @FindBy(css = "div[data-id='points']")
    public List<WebElement> edit_ratingPoints;

    @FindBy(css = "input[class='form-control ratingPoint text-center p-none']")
    public WebElement edit_rating_points;

    @FindBy(css = "div[class^='as-modal-yes-btn yes-delete']")
    public WebElement shareRubric_yesLink;

    @FindBy(css = "span[title='Preview']")
    public List<WebElement> previewLink;

    @FindBy(css = "div[class^='rubric-content width']")
    public List<WebElement> rubric_content;

    @FindBy(css = "#rubric-search-input")
    public WebElement rubric_search_textarea;

    @FindBy(css = "#rubric-search-icon")
    public WebElement rubric_search_icon;

    @FindBy(css = "span[class='link-color clone-rubric-link']")
    public WebElement clone_link;

    @FindBy(xpath = "(//div[starts-with(@class,'rubric-options-icon pull-right')])[1]/span")
    public List<WebElement> rubric_options;

    @FindBy(css=".delete-rubric-link")
    public WebElement lnk_DeleteRubric;

    @FindBy(css = ".rubric-label-wrapper >div")
    public List<WebElement> rubricLabel;

    @FindBy(css = ".show-rubric > div")
    public WebElement rubric_Name;

    @FindBy(xpath = "//div[contains(@class,'rubric-content width')]")
    public List<WebElement> rubricNamesCreatedByAuthor;

    @FindBy(xpath = "//div[contains(@class,'rubric-options-icon')]/span")
    public List<WebElement> rubricOptionIcon;

    @FindBy(css = ".row.use-rubric-header > div")
    public List<WebElement> rubric_name;

    @FindBy(css = ".use-rubric-desc")
    public List<WebElement> rubricDescription;

    @FindBy(css = ".desc.criteria")
    public List<WebElement> criteriaDescription;

    @FindBy(css = "div[class^='back-to-search-view']")
    public List<WebElement> backButton;

    @FindBy(id = "rubric-list-content")
    public WebElement rubricNotAvailableMessage;

    @FindBy(className = "row-notification-message")
    public WebElement deleteRubricNotificationMessage;

    @FindBy(css = ".delete-yes.p-r-sm")
    public WebElement yesOnDeleteRubric;

    @FindBy(css = ".as-assignmentReview-headerRight .createQuestion-review-button>button")
    public WebElement reviewAssessment;


}
