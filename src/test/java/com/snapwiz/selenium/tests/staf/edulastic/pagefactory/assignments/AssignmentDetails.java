package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by pragyas on 16-02-2016.
 */
public class AssignmentDetails {

    @FindBy(xpath = "//ol[@class='breadcrumb']/li")
    public List<WebElement> labels_pageHeader;//PAge header labels (Dashboard and Assignment Details)

    @FindBy(css = ".ellipsis.m-b-xs")
    public WebElement name_assignment;//Assignment name

    @FindBy(css= ".ed-icon.icon-delete")
    public WebElement deleteButton;

    @FindBy(css = "input#delete-confm-txt")
    public WebElement deleteTextBox;

    @FindBy(css = "div[class='as-modal-yes-btn delete-assignment']")
    public WebElement yesButtonOnDeletePopUp;

    @FindBy(css="assessment-collaboration")
    public WebElement btnCollaborate;

    @FindBy(css = ".as-assignment-edit")
    public WebElement btnEdit;

    @FindBy(css = ".as-assignment-delete")
    public WebElement btnDelete;







}
