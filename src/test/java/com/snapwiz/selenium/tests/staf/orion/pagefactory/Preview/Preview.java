package com.snapwiz.selenium.tests.staf.orion.pagefactory.Preview;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by mukesh on 28/12/15.
 */
public class Preview {
    @FindBy(id = "cms-question-preview-header-ass-name")
    public WebElement previewHeader;

    @FindBy(className = "question-card-question-content")
    public List<WebElement> questionCard_questionName;

    @FindBy(className = "al-diag-test-question-raw-content")
    public WebElement diagTest_questionPreview;

    @FindBy(className = "al-diag-test-question-label")
    public WebElement question_No;

    @FindBy(id="al-report-content-body-row")
    public WebElement contentReport;

    @FindBy(className = "cms-content-question-review-question-raw-content")
    public WebElement questionName;

    @FindBy(xpath = "//div[@id='al-user-notes-icon-block']")
    public List<WebElement> notesBlock;

    @FindBy(xpath = "//span[@class='al-delete-user-content-notes']")
    public List<WebElement> closeIconOnNotesBlock;

    @FindBy(id = "al-close-notes-wrapper")
    public WebElement closeIconNotePopUp;

    @FindBy(css = "div[class='add-content-error show-content-issues-dialog']")
    public WebElement contentIssue;

    @FindBy(id = "text-area-content-issue")
    public WebElement contentIssueTextArea;

    @FindBy(id = "send-content-issue-btn")
    public  WebElement send_Button;

    @FindBy(id = "send-content-issue")
    public WebElement yesLinkContentIssueNotification;

    @FindBy(id = "back-to-question")
    public WebElement cancelLinkContentIssueNotification;


}
