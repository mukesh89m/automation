package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by pragya on 06-04-2015.
 */
public class GraphingQuestionCreation {


    @FindBy(id = "question-raw-content")
    WebElement textBox_QuestionField;//Question edit box to type the question or to get the default value
    public WebElement getTextBox_QuestionField(){return textBox_QuestionField;}

    @FindBy(xpath = "(//a[@title = 'Insert Image'])[1]")
    WebElement image_textEditor;//Image icon to upload image in text editor
    public WebElement getImage_textEditor(){return image_textEditor;}

    @FindBy(xpath = "(//a[@title = 'Math Editor'])[1]")
    WebElement mathml_textEditor;//Mathml icon to enter mathml in text editor
    public WebElement getMathml_textEditor(){return mathml_textEditor;}

    @FindBy(xpath = "//div[@id = 'question-raw-content']/img")
    WebElement uploadedImage_insideQuestionEditbox;//Uploaded image inside question edit bix
    public WebElement getUploadedImage_insideQuestionEditbox(){return uploadedImage_insideQuestionEditbox;}

    @FindBy(className = "mrow")
    WebElement enteredMathMl;//Entered Mathml inside question text editor
    public WebElement getEnteredMathMl(){return enteredMathMl;}

    @FindBy(xpath = "//a[@title='Un-Do']")
    WebElement undo;// Undo inside text editor
    public WebElement getUndo(){return undo;}

    @FindBy(xpath = "//a[@title='Re-Do']")
    WebElement redo;// URedo inside text editor
    public WebElement getRedo(){return redo;}

    @FindBy(css = "span[class='style5 left-graph-options-title']")
    WebElement options_text;//Options text on the left below the question body
    public WebElement getOptions_text(){return options_text;}

    @FindBy(id="is-show-grid")
    WebElement showGrid;//Show Grid to identify show Grid checkbox
    public WebElement getShowGrid(){return showGrid;}

    @FindBy(id="is-show-labels")
    WebElement showLabels;//Show Labels to identify show Labels checkbox
    public WebElement getShowLabels(){return showLabels;}

    @FindBy(id="gridSize")
    WebElement editbox_gridSize;//Grid size edit box to get the default value
    public WebElement getEditbox_gridSize(){return editbox_gridSize;}

    @FindBy(id="zoomIn")
    WebElement zoomIn_pixel;// Plus button to increase the pixel
    public WebElement getZoomIn_pixel(){return zoomIn_pixel;}

    @FindBy(id="zoomOut")
    WebElement zoomOut_pixel;// Minus button to increase the pixel
    public WebElement getZoomOut_pixel(){return zoomOut_pixel;}

    @FindBy(id="unit")
    WebElement editbox_unitDistance;//Unit distance edit box to enter unit distance
    public WebElement getEditbox_unitDistance(){return editbox_unitDistance;}

    @FindBy(xpath = "//div[@class='plotting-chk-options']/div[1]")
    WebElement drawAnswerModeOption;//Draw Answer mode option
    public WebElement getDrawAnswerModeOption(){return drawAnswerModeOption;}

    @FindBys(@FindBy(className = "plotting-graph-options-des"))
    List<WebElement> list_tools;//List of tools present beside Draw answer mode option
    public List<WebElement> getList_tools(){return list_tools;}

    @FindBys(@FindBy(xpath = "//td[starts-with(@class,'plotting-draw-icons')]"))
    List<WebElement> list_toolsInGrid;//List of tools present on the question layer
    public List<WebElement> getList_toolsInGrid(){return list_toolsInGrid;}

    @FindBy(xpath = "//div[starts-with(@class,'graph-question-layer question-layer')]")
    WebElement questionLayer;//Question layer
    public WebElement getQuestionLayer(){return questionLayer;}

    @FindBy(css = "div[class='graph-answer-layer question-layer']")
    WebElement answerLayer;//Question layer
    public WebElement getAnswerLayer(){return answerLayer;}

    @FindBys(@FindBy(xpath = "//td[starts-with(@class,'plotting-functional-icons')]"))
    List<WebElement> buttonsInGrid;//Undo,Redo,Reset and delete button
    public List<WebElement> getButtonsInGrid(){return buttonsInGrid;}

    @FindBys(@FindBy(className = "JXGtext"))
    List<WebElement> labelsInGrid;//Labels present in the grid
    public List<WebElement> getLabelsInGrid(){return labelsInGrid;}

    @FindBy(css = "div[class='jsx-graph-bottom jxgbox jsx-graph-canvas']")
    WebElement gridArea;//Grid area
    public WebElement getGridArea(){return gridArea;}

    @FindBy(xpath = "(//*[@fill='#2B85C0'])[1]")
    WebElement polygon;//Created polygon in blue color
    public WebElement getGetPolygon(){return polygon;}

    @FindBy(xpath = "//*[@fill='#2B85C0']")
    WebElement pointPlaced_previewPage;//Point placed on preview page in blue colour
    public WebElement getPointPlaced_previewPage(){return pointPlaced_previewPage;}

    @FindBy(className = "jsx-graph-lable-text")
    WebElement editbox_point;//Edit box to edit point
    public WebElement getEditbox_point(){return editbox_point;}

    @FindBy(xpath = "//*[@fill='#2B85C0']")
    WebElement pointPlacedOnGraph;//Point placed on graph
    public WebElement getPointPlacedOnGraph(){return pointPlacedOnGraph;}

    @FindBys(@FindBy(xpath = "//*[@fill='#2B85C0']"))
    List<WebElement> list_pointPlacedOnGraph;//List of points placed on graph
    public List<WebElement> getList_pointPlacedOnGraph(){return list_pointPlacedOnGraph;}

    @FindBy(id="addNewAnnotation")
    WebElement addAnnotation;//Add Annotation link
    public WebElement getAddAnnotation(){return addAnnotation;}

    @FindBy(className = "annotation-expression")
    WebElement mathml_annotation;//Mathl annotation
    public WebElement getMathml_annotation(){return mathml_annotation;}

    @FindBy(className = "annotation-text")
    WebElement text_annotation;//Text annotation
    public WebElement getText_annotation(){return text_annotation;}

    @FindBy(className = "annotation-image")
    WebElement image_annotation;//Image annotation
    public WebElement getImage_annotation(){return image_annotation;}

    @FindBy(id = "annotationContent")
    WebElement annotation_content;//Annotation content
    public WebElement getAnnotation_content(){return annotation_content;}

    @FindBy(className = "annotation-input-box")
    WebElement annotation_textInputbox;//Annotation input box
    public WebElement getAnnotation_textInputbox(){return annotation_textInputbox;}

    @FindBy(id = "dragDndAnnotation")
    WebElement drag_annotation;//Drag button to drag the annotation;
    public WebElement getDrag_annotation(){return drag_annotation;}

    @FindBy(id = "expression-annotation-save")
    WebElement save_mathml;//Save button to save mathml
    public WebElement getSave_mathml(){return save_mathml;}

    @FindBy(className = "answer")
    WebElement added_annotationContent;//Added annotation
    public WebElement getAdded_annotation(){return added_annotationContent;}

    @FindBy(xpath = "//span[@class='answer']/img")
    WebElement added_imageAnnotationContent;//Added annotation
    public WebElement getAdded_imageAnnotationContent(){return added_imageAnnotationContent;}


    @FindBy(className = "annotation-ans-container")
    WebElement droppedAnnotation;//Dropped annotation on graph area
    public WebElement getDroppedAnnotation(){return droppedAnnotation;}

    @FindBy(xpath = "//div[@id='dragged-ans-choice']/img")
    WebElement dropped_imageAnnotation;//Dropped image annotation on graph area
    public WebElement getDropped_imageAnnotation(){return dropped_imageAnnotation;}

    @FindBy(className = "drag-drop-zone")
    WebElement dragAnnotation_graphArea;//Drag button to drag the annotation on graph area
    public WebElement getDragAnnotation_graphArea(){return dragAnnotation_graphArea;}

    @FindBy(css = "div[class='wrap-text annotation-dropped ui-draggable ui-resizable']")
    WebElement droppedAnnotation_position;//Dropped annotation to get the annotation position
    public WebElement getDroppedAnnotation_position(){return droppedAnnotation_position;}

    @FindBy(id = "annotation-pick-files")
    WebElement button_selectFile;//Select file button while uploading image
    public WebElement getButton_selectFile(){return button_selectFile;}

    @FindBy(id = "annotation-upload-image")
    WebElement button_uploadImage;//Upload image button to uplaod image
    public WebElement getButton_uploadImage(){return button_uploadImage;}

    @FindBy(className = "wrs_focusElement")
    List<WebElement> mathmlEditor;//Mathml editor
    public List<WebElement> getMathmlEditor(){return mathmlEditor;}

    @FindBy(id = "expression-annotation-cancel")
    WebElement mathml_cancel;//Cancel button to close mathml editor
    public WebElement getMathml_cancel(){return mathml_cancel;}

    @FindBy(id = "delete-dnd-annotation")
    WebElement delete_annotation;//Delete annotation
    public WebElement getDelete_annotation(){return delete_annotation;}

    @FindBy(className = "jsx-graph-is-show-object-checkbox")
    WebElement showObject;//Show object
    public WebElement getShowObject(){return showObject;}

    @FindBy(xpath = "//*[@fill='#000000']")
    WebElement pointPlaced_answerLayer;//Point placed on answer layer in black color
    public WebElement getPointPlaced_answerLayer(){return pointPlaced_answerLayer;}

    @FindBys(@FindBy(xpath = "//*[@fill='#000000']"))
    List<WebElement> list_pointPlaced_answerLayer;//List of points placed on answer layer in black color
    public List<WebElement> getList_pointPlaced_answerLayer(){return list_pointPlaced_answerLayer;}

    @FindBy(xpath = "//td[@class='plotting-draw-icons box-inset-gray-shadow']")
    WebElement toolInGrid_enabled;//Current enabled tool in grid
    public WebElement getToolInGrid_enabled(){return toolInGrid_enabled;}

    @FindBy(id="cursor-type")
    WebElement editObject;//Edit object in the grid
    public WebElement getEditObject(){return editObject;}

    @FindBys(@FindBy(xpath = "//input[@class='plotting-graph-options regular-checkbox']/following-sibling::label"))
    List<WebElement> list_toolsCheckbox;//list of checkboxes for tool
    public List<WebElement> getList_toolsCheckbox(){return list_toolsCheckbox;}

    @FindBys(@FindBy(css = "td[class='plotting-draw-icons disabled']"))
    List<WebElement> list_disabledTools;//List of disabled tools
    public List<WebElement> getList_disabledTools(){return list_disabledTools;}

    @FindBys(@FindBy(xpath = "//*[@fill='#1E9301']"))
    List<WebElement> list_pointPlaced_answerLayer_green;//Point place on answer layer in green color
    public List<WebElement> getList_pointPlaced_answerLayer_green(){return list_pointPlaced_answerLayer_green;}

    @FindBy(css = "td[class='plotting-draw-icons jsx-graph-move-origin-btn disabled']")
    WebElement positionTool_disabled;//Position tol disabled
    public WebElement getPositionTool_disabled(){return positionTool_disabled;}

    @FindBy(css = "div[class='as-modal-yes-btn yes-navigate add-question-page']")
    WebElement button_yes;//yes button on pop up
    public WebElement getButton_yes(){return button_yes;}

    @FindBy(css = "div[class='graph-answer-layer question-layer active-layer']")
    WebElement answerLayer_enabled;//Enabled answer layer
    public WebElement getAnswerLayer_enabled(){return answerLayer_enabled;}

    @FindBy(css = "label[class='writeboardchkbox as-writeboard-checkbox-unchecked']")
    WebElement writeboard_unchecked;//Writeboard checkbox unchecked
    public WebElement getWriteboard_unchecked(){return writeboard_unchecked;}

    @FindBy(id="saveQuestionDetails1")
    WebElement button_save;//Save button
    public WebElement getButton_save(){return button_save;}

    @FindBy(className = "as-question-teacher-preview-button")
    WebElement button_preview;//Preview button
    public WebElement getButton_preview(){return button_preview;}

    @FindBy(id="question-reveview-submit")
    WebElement preview_submit;//Submit button on Preview page
    public WebElement getPreview_submit(){return preview_submit;}

    @FindBy(id = "content-solution")
    WebElement editbox_solution;//Solution edit box
    public WebElement getEditbox_solution(){return editbox_solution;}

    @FindBy(id = "content-hint")
    WebElement editbox_hint;//Hint edit box
    public WebElement getEditbox_hint(){return editbox_hint;}

    @FindBy(id = "solution")
    WebElement label_solution;//Solution label
    public WebElement getLabel_solution(){return label_solution;}

    @FindBy(id = "hint")
    WebElement label_hint;//Hint label
    public WebElement getLabel_hint(){return label_hint;}

    @FindBy(css="#footer-notification-text>label")
    WebElement message_text;//Message beside the question type
    public WebElement getMessage_text(){return message_text;}

    @FindBy(id="difficulty-level-drop-down")
    WebElement difficulty_level;//Difficulty level drop down
    public WebElement getDifficulty_level(){return difficulty_level;}

    @FindBy(xpath = "//button[starts-with(@class,'btn btn-rounded btn-default lsm-createAssignment-done m-r-md selected')]")
    WebElement button_review;// 'Review' Button
    public WebElement getButton_review() {return button_review;}

    @FindBy(className = "drawing-prompt-combo")
    WebElement drawingPromptCombo;// Drawing prompt combo
    public WebElement getDrawingPromptCombo(){return drawingPromptCombo;}

    @FindBy(className = "jsxgraph-right-section")
    WebElement graph_canvas;//Graph canvas on student attempt side
    public WebElement getGraph_canvas(){return graph_canvas;}

    @FindBy(id = "delete-graph-object")
    WebElement delete_studentAttempt;//Delete button
    public WebElement getDelete_studentAttempt(){return delete_studentAttempt;}

    @FindBy(id = "resetGraphInPreview")
    WebElement reset_studentAttempt;//Reset buttom
    public WebElement getReset_studentAttempt(){return reset_studentAttempt;}

    @FindBy(xpath = "//label[@for='is-show-grid']")
    WebElement checkbox_showGrid;//Show grid checkbox
    public WebElement getCheckbox_showGrid(){return checkbox_showGrid;}

    @FindBy(xpath = "//label[@for='is-show-labels']")
    WebElement checkbox_showLabels;//Show labels checkbox
    public WebElement getCheckbox_showLabels(){return checkbox_showLabels;}

    @FindBy(css="label[class='writeboardchkbox as-writeboard-checkbox-unchecked']")
    WebElement checkbox_scratchpad;//Scratchpad check box
    public WebElement getCheckbox_scratchpad(){return checkbox_scratchpad;}

    @FindBy(xpath = "//label[@for='segmentType']")
    WebElement checkbox_segmentTool;//Segment tool checkbox
    public WebElement getCheckbox_segmentTool(){return checkbox_segmentTool;}

    @FindBy(xpath = "//div[text()='(16.00, 0)']")
    WebElement coordinate_studentAttempt;//Coordinate of object placed by student
    public WebElement getCoordinate_studentAttempt(){return coordinate_studentAttempt;}

    @FindBys(@FindBy(xpath = "//*[@fill='#FF0400']"))
    List<WebElement> line_redColor;//Line in red color
    public List<WebElement> getLine_redColor(){return line_redColor;}








}
