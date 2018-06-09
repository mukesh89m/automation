package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Snapwiz on 05/08/15.
 */
public class Dashboard_appiumPF {
    /*@FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[4]/UIAImage[1]")
    public WebElement icon_accountUser;*/

    @FindBy(xpath = "//UIAStaticText[@name = 'study - navigate to eTextbook page']")
    public WebElement icon_accountUser;


    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[5]")
    public WebElement icon_close;




    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[23]")
    public WebElement label_gradedAssignments;

    /*@FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[12]")
    public WebElement label_gradedAssignments;*/


     @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[24]")
    public WebElement label_upcoming;





    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[14]")
    public WebElement link1_upcomingAssignment;



    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[11]/UIAStaticText[1]")
    public WebElement link2_upcomingAssignment;



    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[27]")
    public WebElement dueDate1;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[19]")
    public WebElement dueDate2;


    /*@FindBy(xpath = "//UIAApplication[1]/UIAWindow[3]/UIAStatusBar[1]/UIAElement[1]")
    public WebElement icon_mainNavigator;*/



    /*@FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[1]")
    public WebElement icon_logOut;*/

    @FindBy(xpath = "//UIAStaticText[@name = 'iPadIntegration Student']")
    public WebElement icon_logOut;

    @FindBy(xpath = "//UIAStaticText[@name = 'Logout']")
    public WebElement option_logOut;


   /* @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[6]")
    public WebElement option_logOut;*/

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[24]")
    public WebElement label_overAllScore;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[30]")
    public WebElement label_recentlyGraded;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[25]")
    public WebElement value_overAllScore;

    @FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[40]")
    public WebElement value_upComing;


    @FindBy(xpath = "//UIALink[@name = 'Dashboard']")
    public WebElement link_dashBoard;



    @FindBy(xpath = "//UIALink[@name = 'e-Textbook']")
    public WebElement link_eTextBook;


    @FindBy(xpath = "//UIALink[@name = 'Assignments']")
    public WebElement link_assignments;


    @FindBy(xpath = "//UIALink[@name = 'Course Stream']")
    public WebElement link_courseStream;

    @FindBy(xpath = "//UIALink[@name = 'My Notes']")
    public WebElement link_myNotes;

    @FindBy(xpath = "//UIALink[@name = 'study - navigate to eTextbook page']")
    public WebElement link_study;

    @FindBy(xpath = "//UIATextField[@name = 'search chapters']")
    public WebElement textField_searchETextBook;

    @FindBy(xpath = "//UIAStaticText[@name = 'Note']")
    public WebElement text_Note;

    @FindBy(xpath = "//UIAStaticText[@name = 'Share a new...']")
    public WebElement link_ShareANew;

    @FindBy(xpath = "//UIALink[@name = 'All Assignments']")
    public WebElement link_allAsignments;



    /*@FindBy(xpath = "")
    public WebElement icon_accountUser;

    @FindBy(xpath = "")
    public WebElement icon_accountUser;*/




}
