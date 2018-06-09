package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.LsLms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by rashmi on 02-02-2016.
 */
public class BlackBoard {

    @FindBy(xpath = ".//*[@id='myBlackBoard']/a")
    WebElement myBlackBoard;//my black board in profile dropdown
    public WebElement getMyBlackBoard(){ return myBlackBoard; }

    @FindBy(className = "nav-ls-black-board")
    public WebElement MyProfileBlackboardIcon;

    @FindBy(css = "span[class='ls---black-board-go-back-to-my-blackboard-text']")
    public WebElement goBackToMyBlackBoard;//go back to my black board in main navigator

    @FindBy(className = "ls---black-board-go-back-to-my-blackboard")
    public WebElement MainNavigatorBlackboardIcon;



    @FindBy(css = "div[class='ls-inst-dashboard-assignment-popup-button ls--create-custom-assignment-view']")
    WebElement createCustomAssingnmentsbutton;
    public WebElement getCreateCustomAssingnmentsbutton(){
        return createCustomAssingnmentsbutton;
    }

    @FindBy(css = "div[class='ls-inst-dashboard-assignment-popup-button ls--question-banks-view']")
    WebElement usePrecreatedAssignmentbutton;
    public WebElement getUsePrecreatedAssignmentbutton(){
        return usePrecreatedAssignmentbutton;
    }

    @FindBy(css ="div[class='ls-inst-dashboard-assignment-popup-button ls--custom-file-assignment-view']" )
    WebElement createFileBasedAssignment;
    public WebElement getCreateFileBasedAssignment(){
        return createFileBasedAssignment;
    }

    @FindBy(css = "div[class='ls-inst-dashboard-assignment-popup-button ls--custom-discussion-assignment-view']")
    public WebElement DashboardDiscussionAssignmentButton;
    
}
