package com.snapwiz.selenium.tests.staf.orion.pagefactory.eTextbook;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/*
 * Created by sumit on 29/12/14.
 */
public class LessonPage {

    @FindBy(className = "assignment-cart-wrapper")
    WebElement assignmentCart;//assignment cart in e-textbook

    public WebElement getAssignmentCart() {
        return assignmentCart;
    }

    @FindBy(className = "media__body")
    WebElement chapterName;//chapter name

    public WebElement getChapterName() {
        return chapterName;
    }

    @FindBy(xpath = "//a[contains(@title,'1.1: The Science of Biology')]/following-sibling::div[2]")
    WebElement lessonIcon;//click on">" icon next to the lesson name

    public WebElement getLessonIcon() {
        return lessonIcon;
    }

    @FindBy(xpath = "//a[contains(@title,'1.1: The Science of Biology')]/following-sibling::div[1]/div")
    WebElement openTabNewLessonIcon;//click on"-->" icon next to the lesson name

    public WebElement getOpenTabNewLessonIcon() {
        return openTabNewLessonIcon;
    }

    @FindBy(xpath = "//*[@id='ch01-sec1-1']")
    WebElement lessonTitle;//lesson title

    public WebElement getLessonTitle() {
        return lessonTitle;
    }

    @FindBy(xpath = "//a[contains(@title,'Diagnostic - Ch 1: The Study of Life')]/following-sibling::div[2]")
    WebElement diagnosticIcon;//diagnostictest '>' icon

    public WebElement getDiagnosticIcon() {
        return diagnosticIcon;
    }

    @FindBy(xpath = "(//div[contains(@class,'pointer-arrow chapter-badge toc-sprite')])[8]")
    WebElement diagnosticArrow;//diagnostic test '-->' icon

    public WebElement getDiagnosticArrow() {
        return diagnosticArrow;
    }

    @FindBy(xpath = "//div[contains(@class,'pointer-arrow chapter-badge toc-sprite')]")
    public List<WebElement> arrowMark;//  '-->' icon


    @FindBy(xpath = "//span[@class='cms-content-question-review-list-label']")
    WebElement diagnosticTestTitle;//diagnostic test title

    public WebElement getDiagnosticTestTitle() {
        return diagnosticTestTitle;
    }

    @FindBy(xpath = "(//div[contains(@class,'pointer-arrow chapter-badge toc-sprite')])[2]")
    WebElement practiceTestArrow;//practice test '>' icon

    public WebElement getPracticeTestArrow() {
        return practiceTestArrow;
    }

    @FindBy(xpath = "//span[contains(text(),'(P1) Personalized Practice - Ch 1: The Study of Life')]")
    WebElement practiceTestTitle;//practice test title

    public WebElement getPracticeTestTitle() {
        return practiceTestTitle;
    }

    @FindBy(xpath = "//a[contains(@title,'1.1 Concept Check')]/following-sibling::div[3]")
    WebElement staticPracticeIcon;//static practice '>' icon

    public WebElement getStaticPracticeIcon() {
        return staticPracticeIcon;
    }

    @FindBy(xpath = "//a[contains(@title,'0.2 Concept Check')]/following-sibling::div[3]")
    public WebElement staticPracticeIcon_LS;//static practice '>' icon for ls


    @FindBy(xpath = "//a[contains(@title,'1.1 Concept Check')]/following-sibling::div[1]/div[1]")
    WebElement staticPracticeArrow;//static practice '-->' icon

    public WebElement getStaticPracticeArrow() {
        return staticPracticeArrow;
    }

    @FindBy(xpath = "//span[contains(text(),'(P1.1) 1.1 Concept Check')]")
    WebElement staticTestTitle;//static test title

    public WebElement getStaticTestTitle() {
        return staticTestTitle;
    }

    @FindBy(xpath = "//a[contains(@title,'Assignment_IT18_22')]/following-sibling::div[3]")
    WebElement gradeableAssignmentIcon;//gradeble assignment '>' icon

    public WebElement getGradeableAssignmentIcon() {
        return gradeableAssignmentIcon;
    }

    @FindBy(xpath = "//a[contains(@title,'Assignment_IT18_22')]/following-sibling::div[1]/div[1]")
    WebElement gradeableAssignmentArrow;////gradeble assignment '-->' icon

    public WebElement getGradeableAssignmentArrow() {
        return gradeableAssignmentArrow;
    }

    @FindBy(className = "question-no")
    WebElement question;//question name for gradable

    public WebElement getQuestion() {
        return question;
    }

    @FindBy(className = "control-label")
    WebElement questionNonGradable;//question name for nongradable

    public WebElement getQuestionNonGradable() {
        return questionNonGradable;
    }

    @FindBy(xpath = "//span[contains(text(),'1.1: Discuss the scientific basis for the study of biology - Practice')]")
    WebElement tloTestTitle;//tlo practicetest title

    public WebElement getTloTestTitle() {
        return tloTestTitle;
    }

    @FindBy(xpath = "//a[contains(@title,'1.1: The Science of Biology')]")
    WebElement forScienceAndBiology;

    public WebElement getForScienceAndBiology() {
        return forScienceAndBiology;
    }

    @FindBy(xpath = "//a[contains(@title,'Diagnostic - Ch 1: The Study of Life')]")
    WebElement forDiagnostic;//diagnostictest name

    public WebElement getForDiagnostic() {
        return forDiagnostic;
    }

    @FindBy(xpath = "//a[contains(@title,'1.1: Discuss the scientific basis for the study of biology')]")
    WebElement forTloPractice;//tlo practice name

    public WebElement getForTloPractice() {
        return forTloPractice;
    }

    @FindBy(xpath = "//a[contains(@title,'1.1 Concept Check')]")
    WebElement forStaticPractice;//static practice name

    public WebElement getForStaticPractice() {
        return forStaticPractice;
    }

    @FindBy(className = "toc-assignment-ellipsis")
    WebElement forAssignmentName;

    public WebElement getForAssignmentName() {
        return forAssignmentName;
    }

    @FindBy(xpath = "//*[contains(@id,'sbToggle')]")
    WebElement classSectionDropDown;

    public WebElement getClassSectionDropDown() {
        return classSectionDropDown;
    }

    @FindBy(linkText = "Biology's 101")
    WebElement className;

    public WebElement getClassName() {
        return className;
    }

    @FindBy(className = "assign-options")
    WebElement assignForImage;

    public WebElement getAssignForImage() {
        return assignForImage;
    }

    @FindBy(className = "assign-this-text")
    WebElement assignThisLinkForImage;

    public WebElement getAssignThisLinkForImage() {
        return assignThisLinkForImage;
    }


    @FindBy(className = "extended-due-date-title")
    WebElement extendedDueDate; //extended due date field in assignments tab of e-Textbook

    public WebElement getExtendedDueDate() {
        return extendedDueDate;
    }

    @FindBy(className = "ls_assessment_link")
    WebElement assignmentName;

    public WebElement getAssignmentName() {
        return assignmentName;
    }

    @FindBy(css = "span[title='Assignments']")
    WebElement assignmentsTab;//assignments tab

    public WebElement getAssignmentsTab() {
        return assignmentsTab;
    }

    @FindBy(id = "ls-resource-course-level")
    WebElement courseLevelHeader;

    public WebElement getCourseLevelHeader() {
        return courseLevelHeader;
    }

    @FindBy(id = "ls-resource-chapter-level")
    WebElement chapterLevelHeader;

    public WebElement getChapterLevelHeader() {
        return chapterLevelHeader;
    }

    @FindBy(id = "ls-resource-section-level")
    WebElement sectionLevelHeader;

    public WebElement getSectionLevelHeader() {
        return sectionLevelHeader;
    }

    @FindBy(xpath = "//a[contains(@title,'Introduction')]")
    WebElement introduction;

    public WebElement getIntroduction() {
        return introduction;
    }

    @FindBy(xpath = "//a[contains(@title,'1.2: Themes and Concepts of')]")
    WebElement secondLesson;

    public WebElement getSecondLesson() {
        return secondLesson;
    }

    @FindBy(xpath = "(//div[@class='media__body'])[4]")
    WebElement fourthChapter;

    public WebElement getFourthChapter() {
        return fourthChapter;
    }

    @FindBy(xpath = "//a[contains(@title,'4.1: Studying Cells')]")
    WebElement fourthChapterFirstLesson;

    public WebElement getFourthChapterFirstLesson() {
        return fourthChapterFirstLesson;
    }

    @FindBy(xpath = "//a[contains(@title,'4.2: Prokaryotic Cells')]")
    WebElement fourthChapterSecondLesson;

    public WebElement getFourthChapterSecondLesson() {
        return fourthChapterSecondLesson;
    }

    @FindBy(xpath = "//a[@title='ResourceName_11']")
    WebElement resourceName;

    public WebElement getResourceName() {
        return resourceName;
    }

    @FindBy(xpath = "//img[@src='/webresources/images/ls/wiley-W.png']")
    WebElement wImage;

    public WebElement getwImage() {
        return wImage;
    }

    @FindBy(css = "a[class='ls-right-publisher-name ellipsis']")
    WebElement learningSpace;

    public WebElement getLearningSpace() {
        return learningSpace;
    }

    @FindBy(className = "ls-side-publisher")
    WebElement wileyLabel;

    public WebElement getWileyLabel() {
        return wileyLabel;
    }

    @FindBy(css = "i.close-study-plan-icon.close-study-plan")
    WebElement crossIcon;

    public WebElement getCrossIcon() {
        return crossIcon;
    }

    @FindBy(className = "assign-all-lessons")//bulk assign icon in the right top before "close" icon in etextbook
            WebElement allLessonAssign;

    public WebElement getAllLessonAssign() {
        return allLessonAssign;
    }

    @FindBy(className = "delete-learning-activity")//delete icon for lesson while assigning all lesson
            List<WebElement> deleteIcon;

    public List<WebElement> getDeleteIcon() {
        return deleteIcon;
    }

    @FindBy(className = "assignment-learning-activity")// lesson names while assigning all lesson
            List<WebElement> allLessonNames;

    public List<WebElement> getAllLessonNames() {
        return allLessonNames;
    }

    @FindBy(className = "assignment-learning-activity")// edit icon while assigning all lesson
            WebElement editIcon;

    public WebElement getEditIcon() {
        return editIcon;
    }

    @FindBy(className = "ir-ls-assign-this-edit-link")
    public WebElement chapterName_edit; //edit link besides chapter name

    @FindBy(xpath = "//textarea[@class='ir-ls-assign-this-header-edit-text']")
    public WebElement chapterName_textBox; //chapter name text box

    @FindBy(css = "span[class='btn sty-green submit-assign']")
    public WebElement assign_button;

    @FindBy(css = "div[class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label']")
    public List<WebElement> assignTo_label; //assign to label name

    @FindBy(className = "item-text")
    public List<WebElement> assignTo_content; //assign to content having instructor

    @FindBy(className = "yes-remove-assignmentcart-cart")
    public WebElement deleteLesson; // yes link for delete lesson

    @FindBy(className = "static-assessment-retake")
    WebElement retakeLink; // retake link for static assessment

    public WebElement getRetakeLink() {
        return retakeLink;
    }

    @FindBy(className = "maininput")
    public WebElement assignTo_textBox; //assignTo textBox field

    @FindBy(xpath = "//ul[@id='share-with_feed']/li/em")
    public WebElement suggestion;  //class section suggestion

    @FindBy(xpath = "//*[starts-with(@rel, 'cls_')]")
    public WebElement suggestion1;

    @FindBy(xpath = "//a[@id='assign-cancel']")
    public WebElement cancel_button; //cancel button of assign lesson page

    @FindBy(css = "a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan chapter-tree-selected']")
    public WebElement toc;

    @FindBy(className = "media__body")
    public List<WebElement> selectChapter; //select chapter in etextbook

    @FindBy(css = "span[class='toc-icon completed']")
    public WebElement selectLesson; //select lesson from etextbook


    @FindBys({@FindBy(xpath = "//div[@class='chapter-heading']")})
    List<WebElement> label_ChapterElementsList;

    public List<WebElement> getLabel_ChapterElementsList() {
        return label_ChapterElementsList;
    }

    @FindBy(css = "a[data-type = 'diagnostic_assessment']")
    public WebElement linkDiagnosticTest; // Diagnostic test link

    @FindBy(xpath = "//a[contains(@title,'Assignment_IT18_20')]/following-sibling::div[3]")
    WebElement gradeableAssignmentIcon1;//gradeble assignment '>' icon

    public WebElement getGradeableAssignmentIcon1() {
        return gradeableAssignmentIcon1;
    }

    @FindBy(xpath = "//a[contains(@title,'Assignment_IT18_20')]/following-sibling::div[1]/div[1]")
    WebElement gradeableAssignmentArrow1;////gradeble assignment '-->' icon

    public WebElement getGradeableAssignmentArrow1() {
        return gradeableAssignmentArrow1;
    }

    @FindBy(xpath = "//a[contains(@title,'Assignment_IT18_34')]/following-sibling::div[3]")
    WebElement gradeableAssignmentIcon2;//gradeble assignment '>' icon

    public WebElement getGradeableAssignmentIcon2() {
        return gradeableAssignmentIcon2;
    }

    @FindBy(xpath = "//a[contains(@title,'Assignment_IT18_34')]/following-sibling::div[1]/div[1]")
    WebElement gradeableAssignmentArrow2;////gradeble assignment '-->' icon

    public WebElement getGradeableAssignmentArrow2() {
        return gradeableAssignmentArrow2;
    }

    @FindBy(xpath = "//a[contains(@title,'Assignment_IT18_36')]/following-sibling::div[3]")
    WebElement gradeableAssignmentIcon3;//gradeble assignment '>' icon

    public WebElement getGradeableAssignmentIcon3() {
        return gradeableAssignmentIcon3;
    }

    @FindBy(xpath = "//a[contains(@title,'Assignment_IT18_36')]/following-sibling::div[1]/div[1]")
    WebElement gradeableAssignmentArrow3;////gradeble assignment '-->' icon

    public WebElement getGradeableAssignmentArrow3() {
        return gradeableAssignmentArrow3;
    }

    @FindBy(xpath = "//div[@class='overview']/li[1]")
    WebElement className1;

    public WebElement getClassName1() {
        return className1;
    }


    @FindBy(xpath = "//a[@class='ls-prev']")
    List<WebElement> previousChapterFooter;

    public List<WebElement> getPreviousChapterFooter() {
        return previousChapterFooter;
    }

    @FindBy(xpath = "//a[@class='ls-next']")
    List<WebElement> nextChapterFooter;

    public List<WebElement> getNextChapterFooter() {
        return nextChapterFooter;
    }

    @FindBy(xpath = "//div[@class='al-notification-message-body']/div")
    WebElement notificationMessageOnclickOfPreviousFooter;

    public WebElement getNotificationMessageOnclickOfPreviousFooter() {
        return notificationMessageOnclickOfPreviousFooter;
    }

    @FindBy(xpath = "//div[@class='al-notification-message-body']/div")
    List<WebElement> list_notificationMessage;

    public List<WebElement> getList_notificationMessage() {
        return list_notificationMessage;
    }

    @FindBy(xpath = "//span[contains(@title,'Introduction')]")
    WebElement introductionTab;

    public WebElement getIntroductionTab() {
        return introductionTab;
    }

    @FindBy(xpath = "//span[@class='tab_icon lesson-icon']")
    List<WebElement> tabsOpenCount;

    public List<WebElement> getTabsOpenCount() {
        return tabsOpenCount;
    }

    @FindBy(xpath = "//a[@title='Introduction']")
    List<WebElement> introductionUnderLesson;

    public List<WebElement> getIntroductionUnderLesson() {
        return introductionUnderLesson;
    }

    @FindBy(xpath = "//div[@class='chapter-heading']")
    List<WebElement> chapterCount;

    public List<WebElement> getChapterCount() {
        return chapterCount;
    }


    @FindBy(css = "a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']")
    WebElement tocLink;//assignments tab

    public WebElement getToc() {
        return tocLink;
    }


    @FindBy(css = "div[class='reload-assessment-notification-option assessment-notification-option-no']")
    WebElement noLink;//assignments tab

    public WebElement getNoLink() {
        return noLink;
    }


    @FindBy(css = "div[class='reload-assessment-notification-option assessment-notification-option-yes']")
    WebElement yesLink;//assignments tab

    public WebElement getYesLink() {
        return yesLink;
    }


    @FindBy(xpath = "//span[@class='ls-dashboard-activities-chaptername']")
    List<WebElement> chapterOnDashboard;

    public List<WebElement> getChapterName_Dashboard() {
        return chapterOnDashboard;
    }

    @FindBy(xpath = "//div[@class='completed-subevents']/div/p")
    List<WebElement> chapterOnMyActivity;

    public List<WebElement> getChapterOnMyActivity() {
        return chapterOnMyActivity;
    }

    @FindBy(className = "tab_title")
    public List<WebElement> orionAdaptive_link;

    @FindBy(xpath = "//a[@title='1A.2 Concept Check']/../div[3]")
    public WebElement static_arrow;

    @FindBy(xpath = "//a[@title='1A.4: Early Modern Europe']/../div[3]")
    public WebElement static_arrow1A_4; //Ls static arrow

    @FindBy(xpath = "//div[@class='toc-actions-div']/div[3][@title='Try it']")
    public WebElement tryIt_link;

    @FindBy(xpath = "//div[@class='toc-actions-div']/div[2][@title='Assign This']")
    public WebElement assignThis_link;

    @FindBy(xpath = "//div[@class='toc-actions-div']/div[1]")
    public WebElement pointer_arrow;

    @FindBy(id = "ir-ls-assign-dialog")
    public WebElement assign_popup;

    @FindBy(xpath = "(//div[@class='inner-assessment-block diagnostic_assessment'])[2]/descendant::div[1]")
    public WebElement diagnostic_Arrow;


    @FindBy(xpath = "(//div[@class='inner-assessment-block diagnostic_assessment'])[1]/descendant::div[1]")
    public WebElement diagnostic_Arrow1; //diagnostic arrow for first chapter


    @FindBy(xpath = "(//div[@class='inner-assessment-block adaptive_assessment'])[2]/span[2]/descendant::div[1]")
    public WebElement practice_Arrow;

    @FindBy(xpath = "(//div[@class='inner-assessment-block adaptive_assessment'])/span[2]/descendant::div[1]")
    public WebElement practice_arow1;
    //a[@title='IT201_static Assessment_348']/following-sibling::div[1]

    @FindBy(xpath = "(//div[@data-id='study-plan-tab'])[1]")
    public WebElement studyPlanTab;//Study plan tab

    @FindBy(xpath = "(//div[@data-id='orion-adaptive-practice-tab'])[1]")
    public WebElement orionAdaptivePracticeTabActive;//Orion Adaptive Practice tab

    @FindBy(className = "selected-chapter-name")
    public WebElement chapterNameInHeader;//Chapter name in study plan header

    @FindBy(xpath = "//span[@title='ORION Adaptive Practice']")
    public WebElement orionAdaptivePracticeTab;//ORION Adaptive Practice tab

    @FindBy(xpath = "//a[@title='2.1 Concept Check']")
    public WebElement staticPracticeName_2nd;//2nd static practice name

    @FindBy(xpath = "//input[@value='true-false-student-answer-select']")
    public WebElement button_submit;//submit button on question attempt page

    @FindBy(xpath = "//input[@value='Begin Diagnostic']")
    public List<WebElement> beginDiagnostic;//Begin diagnostic button under 'ORION Adaptive Practice tab'

    @FindBy(className = "al-diag-test-footer-content")
    public WebElement footer_content;//Footer on performance summary after attempting diagnostic test

    @FindBy(id = "practice-test-button")
    public List<WebElement> button_practiceTest;//Practice test button under 'ORION Adaptive Practice tab'

    @FindBy(xpath = "(//li[@title='1.1 The Science of Biology'])[2]")
    public WebElement adaptivePracticeTestName;//Adaptive Practice Test name

    @FindBy(css = "div.ls-proficiency")
    public List<WebElement> list_proficiency;//Proficiency value below section name under 'ORION Adaptive Practice tab'

    @FindBy(className = "toc-adaptive-icon")
    public List<WebElement> adaptiveLogo;//Adaptive logo

    @FindBy(xpath = "//a[@title='1.1: Discuss the scientific basis for the study of biology - Practice']")
    public WebElement adaptiveAssessmentSectionName;//Adaptive assessment section name in 2nd column

    @FindBy(className = "ls-practice-arrow")
    public List<WebElement> arrows;//Arrows on the right hand side under 'ORION Adaptive Practice tab'

    @FindBy(className = "tlo_adaptive_assessment")
    public List<WebElement> adaptiveAssessment;//Practice Adaptive assessment

    @FindBy(id = "performance-chart")
    public WebElement performanceSummaryPage;//Performance summary page

    @FindBy(className = "ls-proficiency-percentage")
    public List<WebElement> proficiencyPercentage;//Proficiency Percentage

    @FindBy(xpath = "//div[@class='ls-dignostic-text']/descendant::span[2]")
    public List<WebElement> texts_orionAdaptivePractice;//Texts under orion adaptive practice tab

    @FindBy(xpath = "//div[@id='ls-diag-self-rating-outer-wrapper']/div[1]")
    public List<WebElement> text_enterConfidenceLevel;//Enter Confidence level

    @FindBy(xpath = "//div[@id='ls-diag-self-rating-outer-wrapper']/div[2]")
    public List<WebElement> box_confidenceLevel;//Confidence level box

    @FindBy(className = "chapter-container")
    public List<WebElement> chapterContainer;

    @FindBy(css = "div.al-notification-message-body")
    public WebElement roboNotificationMessage;

    @FindBy(id = "close-al-notification-dialog")
    public WebElement closeIconOfRoboNotificationMessage;

    @FindBy(xpath = "//div[@class='chapter-heading study-plan-item-hidden']")
    public WebElement hiddenChapter;

    @FindBy(css = "div[class='blockUI blockOverlay']")
    public WebElement deactivatedChapter;

    @FindBy(css = "div[class='deactivated-toc-title chapter']")
    public WebElement tocTitleDiactivated;

    @FindBy(css = "div[class='deactivated-toc-title section']")
    public WebElement tocTitleDiactivatedSection;

    @FindBy(id = "preview-test-button")
    public List<WebElement> previewButton;

    @FindBy(xpath = "//a[@title='2.1 Concept Check']/../div[3]")
    public WebElement static_arrow1; //static test arrow for biology course

    @FindBy(id = "ls-diag-assessment-continue-btn")
    public WebElement button_continueDiagnostic;//Continue Diagnostic button

    @FindBy(className = "al-diag-chapter-details")
    public WebElement leftQuestionNumber;//Left question number while attempting diagnostic test

    @FindBy(xpath = "//div[@class='tlo-ellipsis-content-wrapper']/a")
    public List<WebElement> sectionLevelAdaptivePractice;//Section level adaptive practice elements

    @FindBy(className = "hide-toc")
    public WebElement closeButton;//Close button on header

    @FindBy(xpath = "//div[@class='chapter-report-score-wrapper']/descendant::span[2]")
    public WebElement proficiencyPercentage_header;//Proficiency percentage on header

    @FindBy(xpath = "//li[@title='Introduction']")
    public WebElement introductionsectionUnderStudyTab;//Introduction sections under study tab

    @FindBy(xpath = "//li[@title='1.1 The Science of Biology']")
    public List<WebElement> section_11UnderStudyTab;//Section 1.1 under study tab

    @FindBy(xpath = "//li[@title='1.2 Themes and Concepts of Biology']")
    public List<WebElement> section_12UnderStudyTab;//Section 1.2 under study tab

    @FindBy(xpath = "(//li[@title='2.1 Atoms, Isotopes, Ions, and Molecules: The Building Blocks'])[1]")
    public WebElement section_21UnderStudyTab;//Section 2.1 under study tab

    @FindBy(xpath = "(//li[@title='2.2 Water'])[1]")
    public WebElement section_22UnderStudyTab;//Section 2.2 under study tab

    @FindBy(xpath = "(//li[@title='2.3 Carbon'])[1]")
    public WebElement section_23UnderStudyTab;//Section 2.3 under study tab

    @FindBy(className = "ls-proficiency-pin")
    public List<WebElement> proficiencyPin;//Proficiency bin in proficiency bar

    @FindBy(className = "ls-proficiency-pin-tooltip-container")
    public List<WebElement> toolTiptext;//Tool tip text

    @FindBy(css = "div[class='assignment-left-cart-wrapper']")
    public WebElement postChapterSection;//Post chapter section

    @FindBy(xpath = "//a[@data-type='assignment']")
    public WebElement postChapterAssignment;//Post Chapter Assignment

    @FindBy(xpath = "//a[@data-type='assignment']")
    public List<WebElement> list_postChapterAssignment;//Post Chapter Assignments

    @FindBy(id = "ls-toc-assignmnet-due-date")
    public WebElement assignment_dueDate;//Assignment due date

    @FindBy(css = "a[class='btn btn--primary btn--large btn--submit long-text-button']")
    public WebElement button_finish;//Finish button

    @FindBy(className = "swhelp-content-wrapper")
    public WebElement helpPopUp;//Help pop up

    @FindBy(className = "swhelp-title")
    public WebElement helpTitle;//Help title

    @FindBy(className = "swhelp-button-next")
    public WebElement button_next_helpPopUp;//Next button on help pop up

    @FindBy(className = "swhelp-button-cancel")
    public WebElement close_helpPopUp;//Close button on help pop up

    @FindBy(className = "swhelp-restart")
    public WebElement button_help;//Help button

    @FindBy(css = "div[class='swhelp-content-side-text proficiency-text']")
    public WebElement bodyText_helpPopUp;//Help pop up body text

    @FindBy(className = "proficiency-scalling-bar")
    public WebElement proficiencyBar_helpPopUp;//Help pop up proficiency bar

    @FindBy(className = "proficiency-percentage")
    public WebElement proficiencyPercentage_helpPopUp;//Proficiency percentage on help pop up

    @FindBy(className = "swhelp-content-main-text")
    public WebElement textBelowLine_helpPopUp;//Help pop  text below line

    @FindBy(id = "question-reveview-submit")
    public WebElement button_checkAnswer;//Check answer button on instructor side in new window

    @FindBy(xpath = "//div[@class='toc-actions-div']/div[1][@class='pointer-arrow chapter-badge toc-sprite']")
    public WebElement newTab_chapter2;//New tab under chapter 2 static assignment

    @FindBy(xpath = "//span[@title='(P2.1) 2.1 Concept Check']")
    public WebElement title_previewPage;//Preview page title

    @FindBy(xpath = "//span[@title='1A.2 Concept Check']")
    public WebElement title_previewPageLS;//Preview page title ls

    @FindBy(className = "assign-all-lessons")
    public WebElement assignLogo_header;//Assign icon on chapter header on instructor side

    @FindBy(xpath = "(//li[@title='2.2 Water'])[1]")
    public WebElement sectionFormat;//Chapter name format in first column

    @FindBy(className = "ls-inner-arw")
    public List<WebElement> arrowInstructorSide;//Arrows

    @FindBy(xpath = "//a[@data-type='diagnostic_assessment']")
    public List<WebElement> list_diagnosticCard;//List of diagnostic card

    @FindBy(xpath = "//a[@data-type='adaptive_assessment']")
    public List<WebElement> adaptiveAssessmentCard;//Adaptive assessment card under orion adaptive tab

    @FindBy(xpath = "//a[@title='2.2 Concept Check']/../div[3]")
    public WebElement staticArrow_2;//Concept check 2.2

    @FindBy(xpath = "//div[@class='chapter-report-score-wrapper']/descendant::span[1]")
    public WebElement performance_header;//Performance on header in ls course

    @FindBy(xpath = "//input[@title='Finish']")
    public WebElement getButton_finish_lsCourse;//Finish button while attempting questions

    @FindBy(className = "true-false-student-answer-label")
    public List<WebElement> options; //Options while attempting the question

    @FindBy(xpath = "//div[@class='ls-static-practice-test-submit-button']/input")
    public WebElement button_submitStatic;//Submit button

    @FindBy(xpath = "//div[@class='ls-static-practice-test-next-button']/input")
    public WebElement button_next;//Next button while attempting question

    @FindBy(xpath = "//ul[@class='course--toc chapters-dropdown__content js-scrollbar js-fit-height']")
    public WebElement studyPageLSCourse;//Study page in ls course

    @FindBy(css = "span[class='visual-indicator ls-item-qa-visual-indicator-icon adaptive-tab-diagnostic-visual-indicator']")
    public List<WebElement> visualIndicator_Diagnostic;

    @FindBy(css = "span[class='visual-indicator ls-item-qa-visual-indicator-icon adaptive-tab-practice-visual-indicator']")
    public List<WebElement> visualIndicator_Practice;

    @FindBy(css = "span[class='ls-toc-due-date ls-adaptive-toc-due-date']")
    public WebElement dueDate; //Due date

    @FindBy(className = "continue-diagnostic-test")
    public WebElement continue_roboNotification;//Continue button of robo notification

    @FindBy(className = "adaptive-assignment-practice")
    public WebElement text_orionAdaptivePractice;//Orion adaptive practice after completing diagnostic

    @FindBy(xpath = "//span[@title='ORION - Ch 2: The Chemical Foundation of Life']")
    public WebElement practiceAssignment_2ndChapter;//Practice assignment opened of 2nd chapter

    @FindBy(xpath = "(//div[@class='inner-assessment-block adaptive_assessment'])[1]/span[2]/descendant::div[1]")
    public WebElement practiceArrow_firstChapter;


    @FindBy(css = "a[class='btn btn--primary btn--large btn--continue']")
    public WebElement continueButton;

    @FindBy(xpath = "(//input[@value='Preview'])[6]/..//following-sibling::div[3]")
    public WebElement adaptivePractice_arrow;


    @FindBy(xpath = "//a[@title='1A.8 Concept Check']/../div[3]")
    public WebElement conceptCheck;

    @FindBy(xpath = "//label[@id='question-content-label']/inline")
    public WebElement monoSpaceText;

    @FindBy(xpath = "//img[@title='Hint']")
    public WebElement hint_button;

    @FindBy(xpath = "//div[@id='al-hint-robo-wrapper-sidebar-scroll']/div[2]/div/inline")
    public WebElement hintText;

    @FindBy(xpath = "//input[@title='Submit Answer']")
    public WebElement submitAnswer;

    @FindBy(xpath = "//img[@title='Solution']")
    public WebElement solution_button;

    @FindBy(xpath = "//div[@id='al-diag-test-solution-content-wrapper']/div[2]/div/inline")
    public WebElement solutionText;

    @FindBy(className = "static-assessment-retake-button ")
    public WebElement retake_Button;

    @FindBy(xpath = "//div[contains(@class,'report-sidebar-question-card-sectn')]")
    public List<WebElement> questionCard;


    @FindBy(xpath = "//label[@class='control-label']/inline")
    public WebElement monospace_Text;

    @FindBy(xpath = "//div[@class='cms-question-preview-question-hint-content']/inline")
    public WebElement hint_Text;

    @FindBy(xpath = "//div[@class='cms-question-preview-question-solution-content']/inline")
    public WebElement solution_Text;

    @FindBy(className = "ptext")
    public List<WebElement> addDiscussionLink_afterHighlight;

    @FindBy(id = "editdiscussion-textINTERIM")
    public WebElement addDiscussion_Text;

    @FindBy(id = "editdiscussion-submit-INTERIM")
    public WebElement submit_Button;

    @FindBy(css = "div[class='link-annotation-1 link-annotation']")
    public WebElement discussion_icon;

    @FindBy(className = "ls-right-user-head")
    public WebElement discussion_post;

    @FindBy(className = "ls-dialog-entry-edit")
    public WebElement discussionPostEdit_link;

    @FindBy(css = "div[class='toggle-widget-size toggle-widget-size-expand']")
    public WebElement expandImage;

    @FindBy(xpath = "//div[@name='comment']")
    public List<WebElement> widgetComment_text;

    @FindBy(className = "jp-play")
    public WebElement play_button;

    @FindBy(className = "assign-options")
    public List<WebElement> assignThisPop;

    @FindBy(className = "assign-this-text")
    public WebElement assignThisText_link;

    @FindBy(css = "div[name='perspective']")
    public WebElement perspective_Comment;

    @FindBy(css = "div[name='perspective-comment']")
    public WebElement perspective_Comments;

    @FindBy(css = "span[class='jumpout-icon ls-jumpout-img']")
    public WebElement jumpIcon;

    @FindBy(css = "div[class='ls-comment-entry  ls-feedback-comment']")
    public WebElement instructorFeedback;

    @FindBy(className = "ls-comment-entry")
    public WebElement perspective_CommentInStudent;

    @FindBy(className = "post-comment")
    public List<WebElement> postPerspective_Comment;

    @FindBy(className = "ls-perspctive-comments-posted")
    public List<WebElement> postPerspective_CommentPosted;

    @FindBy(css = "a[title='Comments']")
    public List<WebElement> perspective_PostCommentInStudent;

    @FindBy(css = "div[class='dw-add-perspective-block redactor_editor redactor-editor-focused']")
    public WebElement dw_add_perspective;


    @FindBy(css = "i[class='audio-content-recorder-icon performance-audio-feedback-icon']")
    public WebElement dw_audioIcon;


}








