package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by root on 12/29/14.
 */
public class QuestionTypesPage {

    @FindBy(css="a[href='#classic']")
    public WebElement tab_ClassQuestionType;

    @FindBy(css="a[href='#mathtech']")
    public WebElement tab_MathTechEnhanced;

    @FindBy(css="a[href='#elatech']")
    public WebElement tab_ELATechEnhanced;

    @FindBy(id = "qtn-sentence-type")
    WebElement icon_SentenceResponse;// 'Sentence Response' icon to create a Sentence Response question Type
    public WebElement getIcon_SentenceResponse() {
        return icon_SentenceResponse;
    }

    @FindBy(css = ".active>#qtn-passage-type")
    WebElement icon_passageType;// 'Passage Based' icon to create a passage based question Type
    public WebElement getIcon_passageType() {
        return icon_passageType;
    }

    @FindBy(id = "qtn-number-line-type")
    WebElement icon_NumberLine;// 'NumberLine' icon to create a Number Line question Type
    public WebElement getIcon_NumberLine() {return icon_NumberLine;}

    @FindBy(id="qtn-classification-type")
    WebElement icon_Classification;// 'Classification' icon to create a Classification question type
    public WebElement getIcon_Classification(){return icon_Classification;}

    @FindBy(id="qtn-true-false-type")
    WebElement icon_TrueFalseType;// 'True False Question type' icon to create a True False question type
    public WebElement getIcon_TrueFalseType(){return icon_TrueFalseType;}

    @FindBy(id="qtn-matching-tables-type")
    WebElement icon_MatchingTables;// 'Matching Tables Question type' icon to create a Matching question type
    public WebElement getIcon_MatchingTables(){return icon_MatchingTables;}

    @FindBy(id="qtn-graphing-type")
    WebElement icon_Graphing;// 'Graphing question type' icon to create a graphing question type
    public WebElement getIcon_Graphing(){return icon_Graphing;}

    @FindBy(id = "qtn-multiple-choice-type")
    WebElement icon_multipleChoice;// 'Multiple Choice' icon to create multiple choice question type
    public WebElement getIcon_multipleChoice(){return icon_multipleChoice;}

    @FindBy(id = "qtn-multiple-selection-type")
    WebElement icon_multipleSelect;// 'Multiple Select' icon to create multiple choice question type
    public WebElement getIcon_multipleSelect(){return icon_multipleSelect;}

    @FindBy(id = "qtn-text-entry-type")
    WebElement icon_textEntry;// 'Text Entry' icon to create text entry question type
    public WebElement getIcon_textEntry(){return icon_textEntry;}

    @FindBy(id = "qtn-text-drop-down-type")
    WebElement icon_textDropDown;// 'Text Drop Down' icon to create text drop down question type
    public WebElement getIcon_textDropDown(){return icon_textDropDown;}

    @FindBy(id = "qtn-numeric-entry-units-type")
    WebElement icon_numericEntryWithUnits;// 'Numeric entry with units' icon to create numeric entry with units question type
    public WebElement getIcon_numericEntryWithUnits(){return icon_numericEntryWithUnits;}

    @FindBy(id = "qtn-maple-numeric-type")
    WebElement icon_advancedNumeric;// 'Advanced Numeric' icon to create advanced numeric question type
    public WebElement getIcon_advancedNumeric(){return icon_advancedNumeric;}

    @FindBy(id = "qtn-math-symbolic-notation-type")
    WebElement icon_expressionEvaluator;// 'Expression Evaluator' icon to create expression evaluator question type
    public WebElement getIcon_expressionEvaluator(){return icon_expressionEvaluator;}

    @FindBy(xpath = "(//div[@id='qtn-mtf-type'])[2]")
    WebElement icon_matchTheFollowing;// 'Match the Following' icon to create match the following question type
    public WebElement getIcon_matchTheFollowing(){return icon_matchTheFollowing;}

    @FindBy(id = "qtn-dnd-type")
    WebElement icon_dragAndDrop;// 'Drag and Drop' icon to create drag and drop question type
    public WebElement getIcon_dragAndDrop(){return icon_dragAndDrop;}

    @FindBy(id = "qtn-essay-type")
    WebElement icon_essay;// 'Essay' icon to create essay question type
    public WebElement getIcon_essay(){return icon_essay;}

    @FindBy(id = "qtn-lbl-on-img-type")
    WebElement icon_labelAnImageText;// 'Label An Image Text' icon to create label an image text question type
    public WebElement getIcon_labelAnImageText(){return icon_labelAnImageText;}

    @FindBy(id = "qtn-lbl-dropdown-type")
    WebElement icon_labelAnImageDropDown;// 'Label An Image Dropdown' icon to create label an image dropdown question type
    public WebElement getIcon_labelAnImageDropDown(){return icon_labelAnImageDropDown;}

    @FindBy(id = "qtn-resequence-type")
    WebElement icon_resequence;// 'Resequence' icon to create resequence question type
    public WebElement getIcon_resequence(){return icon_resequence;}

    @FindBy(id = "qtn-cloze-matrix-type")
    WebElement icon_clozeMatrix;// 'Cloze Matrix' icon to create cloze matrix question type
    public WebElement getIcon_clozeMatrix(){return icon_clozeMatrix;}

    @FindBy(id = "qtn-graph-type")
    WebElement icon_graphPlotter;// 'Graph plotter' icon to create graph plotter question type
    public WebElement getIcon_graphPlotter(){return icon_graphPlotter;}
}
