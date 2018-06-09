package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Snapwiz on 24/08/15.
 */
public class EtextBook_appium {
    @FindBy(xpath = "//UIAButton[@name = 'Study']")
    public WebElement button_study;

    @FindBy(xpath = "//UIAButton[@name = 'ORION Adaptive Practice']")
    public WebElement button_OrionAdaptivePractice;

    @FindBy(xpath = "//UIAStaticText[@name = 'lesson : Introduction']")
    public WebElement label_introduction;

    @FindBy(xpath = "//UIAButton[@name = 'Introduction']")
    public WebElement tab_introduction;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[11]")
    public WebElement link_footerNavigation;

    @FindBy(xpath = "//UIAButton[@name = '2.1: Atoms, Isotopes, Ions, and Molecules: The Building Blocks']")
    public WebElement tab_lesson;

    @FindBy(xpath = "//UIALink[@name = 'navigates to Introduction']")
    public WebElement linkFooter_introduction;

    @FindBy(xpath = "//UIALink[@name = 'navigates to 2.1 Concept Check']")
    public WebElement linkFooter_conceptCheck;

    @FindBy(xpath = "//UIAButton[@name = 'P2.1']")
    public WebElement tab_P21;

    @FindBy(xpath = "//UIAButton[@name = 'Begin Diagnostic']")
    public WebElement button_beginDiagnostic;

    @FindBy(xpath = "//UIAButton[@name = 'Submit']")
    public WebElement button_submit;

    @FindBy(xpath = "//UIAButton[@name = 'Diagnostic - Ch 1: The Study of Life']")
    public WebElement tab_diagTest;

   /* @FindBy(xpath = "//UIALink[@name = 'Quit Diagnostic Test']")
    public WebElement icon_quitDiagTest;*/
    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[5]/UIALink[1]")
    public WebElement icon_quitDiagTest;

    @FindBy(xpath = "//UIAStaticText[@name = 'Continue Diagtest Later']")
    public WebElement link_continueDiagtestLater;

    @FindBy(xpath = "//UIAButton[@name = 'Continue Diagnostic']")
    public WebElement button_continueDiagnostic;

    @FindBy(xpath = "//UIAStaticText[@name = 'ORION Adaptive Practice']")
    public WebElement label_OrionAdaptivePractice;

    @FindBy(xpath = "//UIAButton[@name = 'Practice']")
    public WebElement button_practice;


    @FindBy(xpath = "//UIAStaticText[@name = 'Quit. View Diagnostic test practice report']")
    public WebElement link_quit;

    @FindBy(xpath = "//UIAStaticText[@name = 'View Report']")
    public WebElement link_viewReport;


    @FindBy(xpath = "//UIAButton[@name = 'Study']")
    public WebElement button_search;

    @FindBy(xpath = "//UIAStaticText[@name = 'Search']")
    public WebElement label_search;

    @FindBy(xpath = "//UIAButton[@name = 'Next']")
    public WebElement button_next;

    @FindBy(xpath = "//UIAStaticText[@name = 'Open in new tab']")
    public WebElement label_openInNewTab;

    @FindBy(xpath = "//UIAButton[@name = 'Get Started']")
    public WebElement button_getStarted;

    @FindBy(xpath = "//UIAStaticText[@name = 'lesson : Introduction']")
    public WebElement link_introduction;

    @FindBy(xpath = "//UIAStaticText[@name = 'Completed lesson : Introduction']")
    public WebElement link_introduction1;


    @FindBy(xpath = "//UIAButton[@name = 'Discussion']")
    public WebElement tab_discussion;

    @FindBy(xpath = "//UIAButton[@name = 'Add to My Notes']")
    public WebElement tab_bookMark;

    @FindBy(xpath = "//UIAButton[@name = 'Resources']")
    public WebElement tab_Resources;

    @FindBy(xpath = "//UIAButton[@name = 'Assignments']")
    public WebElement tab_assignments;


    @FindBy(xpath = "//UIAStaticText[@name = 'Assignments for this']")
    public WebElement label_assignmentsForThis;

    @FindBy(xpath = "//UIAStaticText[@name = 'Student, iPadIntegration']")
    public WebElement label_studentName;

    @FindBy(xpath = "//UIAStaticText[@name = 'posted an assignment']")
    public WebElement label_postedAnAssignment;


    @FindBy(xpath = "//UIALink[@name = 'Bookmark']")
    public WebElement icon_star;

    @FindBy(xpath = "//UIAStaticText[@name = 'Gradable']")
    public WebElement label_gradable;


    @FindBy(xpath = "//UIAStaticText[@name = 'Not Started']")
    public WebElement label_notStarted;

    @FindBy(xpath = "//UIAStaticText[@name = 'A']")
    public WebElement label_answerChoiceA;


    @FindBy(xpath = "//UIAStaticText[@name = 'B']")
    public WebElement label_answerChoiceB;

    @FindBy(xpath = "//UIAButton[@name = 'Finish']")
    public WebElement button_finish;

    @FindBy(xpath = "//UIAStaticText[@name = 'Finish Assignment']")
    public WebElement button_finishAssignment;

    @FindBy(xpath = "//UIAButton[@name = 'Performance']")
    public WebElement label_performance;

}
