package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by shashank on 30-04-2015.
 */
public class FractionEditorQuestionCreation {

    @FindBys({@FindBy(css = "div.rect-column")})
    List<WebElement> rectangleBlockCount;//List of number of blocks in rectangle
    public List<WebElement> getRectangleBlockCount(){return rectangleBlockCount;}


    @FindBys({@FindBy(css = "div.rect-container")})
    List<WebElement> rectangleCount;//Number of rectangle
    public List<WebElement> getRectangleCount(){return rectangleCount;}

    @FindBys({@FindBy(css = "div#canvas >canvas")})
    List<WebElement> circleCount;//Number of Circle
    public List<WebElement> getCircleCount(){return circleCount;}



    @FindBys({@FindBy(css = "div.rect-row")})
    List<WebElement> rowCount;//Number of rows in
    public List<WebElement> getRowCount(){return rowCount;}


    @FindBys({@FindBy(css = "div.rect-column")})
    List<WebElement> columnCount;//Number of rows in
    public List<WebElement> getColumnCount(){return columnCount;}


    @FindBy(css = "select#fractionType")
    WebElement fractionModel;//Dropdown Fraction model
    public WebElement getFractionModel() {
        return fractionModel;
    }

    @FindBy(css = "input#count")
    WebElement numberOfFigures;// Count text box
    public WebElement getNumberOfFigures() {
        return numberOfFigures;
    }

    @FindBy(css = "input#sector")
    WebElement numberOfSector;// Count text box
    public WebElement getNumberOfSector() {
        return numberOfSector;
    }

    @FindBy(css = "input#row")
    WebElement numberOfRow;//Row text box
    public WebElement getNumberOfRow() {
        return numberOfRow;
    }
    @FindBy(css = "input#column")
    WebElement numberOfColumn;// Column text box
    public WebElement getNumberOfColumn() {
        return numberOfColumn;
    }

    @FindBy(css = "div.as-assignment-modal-msg")
    WebElement messageWhileChangingFractionModel;// Column text box
    public WebElement getMessageWhileChangingFractionModel() {
        return messageWhileChangingFractionModel;
    }

    @FindBy(css = "div[class='as-modal-yes-btn yes-navigate add-question-page']")
    WebElement yesButton;// Column text box
    public WebElement getYesButton() {
        return yesButton;
    }


    @FindBy(css = "div[class='lsm-createAssignment-done selected']")
    WebElement button_review;// 'Review' Button
    public WebElement getButton_review() {return button_review;}










}
