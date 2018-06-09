package com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by murthis on 22-12-2015.
 */
public class QuestionType {

    private WebDriver driver;
    public QuestionType(WebDriver driver){
        this.driver=driver;

        //Initialise Elements
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "qtn-type-true-false-img")
    private WebElement TrueFalse;

    @FindBy(id = "qtn-multiple-choice-img")
    private WebElement MultipleChoice;

    @FindBy(id = "qtn-multiple-selection-img")
    private WebElement MultipleSelection;

    @FindBy(id = "qtn-text-entry-img")
    private WebElement TextEntry;

    @FindBy(id = "qtn-text-entry-numeric-units-img")
    private WebElement NumericEntryWithUnits;

    @FindBy(id = "qtn-text-entry-drop-down-img")
    private WebElement TextDropDown;

    @FindBy(id = "qtn-numeric-maple-img")
    private WebElement AdvancedNumeric;

    @FindBy(id = "qtn-math-symbolic-notation-img")
    private WebElement ExpressionEvaluator;

    @FindBy(id = "qtn-essay-type")
    private WebElement EssayTypeQuestion;

    @FindBy(id = "qtn-writeboard-type-new")
    private WebElement WriteBoard;

    public boolean select(String type){
        boolean isClicked=true;
        try {
            switch (type.trim().replace(" ", "").toLowerCase()) {
                case "true/false":
                    TrueFalse.click();
                    break;
                case "multiplechoice":
                    MultipleChoice.click();
                    break;
                case "multipleselection":
                    MultipleSelection.click();
                    break;
                case "textentry":
                    TextEntry.click();
                    break;
                case "numericentryw/units":
                    NumericEntryWithUnits.click();
                    break;
                case "textdropdown":
                    TextDropDown.click();
                    break;
                case "advancednumeric":
                    AdvancedNumeric.click();
                    break;
                case "expressionevaluator":
                    ExpressionEvaluator.click();
                    break;
                case "essaytypequestion":
                    EssayTypeQuestion.click();
                    break;
                case "writeboard":
                    WriteBoard.click();
                    break;
                default:
                    System.out.println("The choice " + type + "is not matching with any case. please check your type.");
                    isClicked = false;
            }

        }
        catch (Exception ex){
            System.out.println("There is a exception in the method "+ex.getMessage());
            isClicked=false;
        }
        return isClicked;
    }

}
