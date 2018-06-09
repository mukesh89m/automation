package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Writeboard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by root on 6/22/15.
 */
public class WriteBoard {
    @FindBy(id = "teacher-btn")
    public WebElement icon_instructorFeedback; //instructor feedback icon

    @FindBy(id = "zwibbler")
    public WebElement workBoard_overlay; //workBoard Overlay

    @FindBy(xpath = "//div[@id = 'white-board-view-section-wrapper']//span")
    public WebElement icon_workBoardClose; //workBoard Close Icon

}
