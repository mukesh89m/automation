package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.Epic46;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.GraphingQuestionCreation;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview.PreviewPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pragya on 06-04-2015.
 */
public class Graphing extends Driver{


    @Test(priority = 1,enabled = true)
    public void defaultLandingPage(){
        try{
            String appendChar = "a";
            int dataIndex = 33;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver,GraphingQuestionCreation.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on Create your first assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create button

            //Expected - 1 :  Question Selection page should be displayed
            //           2 : "Graphing" question should be listed

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type

            //TC row no. - 5 : Clicking on the Graphing question type
            //Expected - 1 :  “Enter question text” should be shown by default in the question body
            Assert.assertEquals(graphing.getTextBox_QuestionField().getText(), "Enter Question Text", "By default 'Enter question text' is not displayed in the question body");

            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext);//Enter the question

           //Expected - 2.1 : Teacher can enter text in the question body using the redactor.
            Assert.assertEquals(graphing.getTextBox_QuestionField().getText(), questiontext, "Teacher is not able to enter text");

            //Expected - 2.2 : Teacher can enter other elements in the question body using the redactor.
            graphing.getTextBox_QuestionField().sendKeys(" extraText");
            driver.findElement(By.xpath("//html/body")).click();
            Assert.assertEquals(graphing.getTextBox_QuestionField().getText(),questiontext+" extraText","extra text is not added to the question text");

            graphing.getTextBox_QuestionField().click();
            graphing.getUndo().click();//Click on un do
            Assert.assertEquals(graphing.getTextBox_QuestionField().getText(), questiontext,"'extraText is not removed after clicking on un do'");

            graphing.getRedo().click();//Click on re do
            Assert.assertEquals(graphing.getTextBox_QuestionField().getText(),questiontext+" extraText","'extra text is not added after clicking on un do'");

            graphing.getImage_textEditor().click();//Click on image inside text editor
            new QuestionCreate().fileUploadInQuestionCreation("33");//Upload an image in text editor
            new WebDriverWait(driver,120).until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));

            graphing.getMathml_textEditor().click();//Click on mathml inside text editor
            new QuestionCreate().enterValueInMathMLEditor("Square root","4");//Enter mathml value in text editor

            if(!graphing.getUploadedImage_insideQuestionEditbox().getAttribute("src").contains("img")){
                Assert.fail("Image is not uploaded");
            }

            if(!graphing.getEnteredMathMl().getText().replaceAll("[\\t\\n\\r]"," ").contains("4")) {
                Assert.fail("Entered Mathml value is not displayed");
            }

            //TC row no. - 9 : Options should be displayed as following
            //Expected - 1 : "Options" Text should be displayed at the left side and below the question body.
            Assert.assertEquals(graphing.getOptions_text().getText(),"Options","'Options' text is not displayed");

            //Expected - 2 : Below the Options text  3 checkboxes should be present.
            //a. Show grid
            //b.  Show labels
            Assert.assertEquals(graphing.getShowGrid().getAttribute("type"),"checkbox","'Show Grid' checkbox is not present");
            Assert.assertEquals(graphing.getShowLabels().getAttribute("type"),"checkbox","'Show Labels checkbox is not present'");


            //TC row no. - 14 : Canvas areas should have the following.
            //Expected - 1 : "Grid size" default value is 40px
            Assert.assertEquals(graphing.getEditbox_gridSize().getAttribute("value"),"40","Default value of grid size is not 40");

             graphing.getZoomIn_pixel().click();//Click on plus button

            //Expected - 2 : " px" should have the "+" and "-" buttons, so that pixel can be increased or decreased.
            Assert.assertEquals(graphing.getEditbox_gridSize().getAttribute("value"),"50","Grid size is not increased after clicking on plus button");

            graphing.getZoomOut_pixel().click();//Click on minus button

            Assert.assertEquals(graphing.getEditbox_gridSize().getAttribute("value"),"40","Grid size is not decreases after clicking on minus button");

            //Expected - 3 : "Unit distance" should have the distance between each units. Default value is 1.
            Assert.assertEquals(graphing.getEditbox_unitDistance().getAttribute("value"),"1","Unit Distance default value is not displayed as 1");

            //Expected - 5 :  " Draw answer mode Options" should be displayed below the first line elements.(grid size,px, unit distance).
            Assert.assertEquals(graphing.getDrawAnswerModeOption().getText().trim(),"Draw answer mode options","'Draw answer mode options' is not displayed");

            //Expected - 6 : Tools should be displayed next to the " Draw answer mode Options"
            Assert.assertEquals(graphing.getList_tools().get(0).getText(),"Point","'Point' tool is not displayed");
            Assert.assertEquals(graphing.getList_tools().get(1).getText(),"Line","'Line' tool is not displayed");
            Assert.assertEquals(graphing.getList_tools().get(2).getText(),"Segment","'Segment' tool is not displayed");
            Assert.assertEquals(graphing.getList_tools().get(3).getText(),"Ray","'Ray' tool is not displayed");
            Assert.assertEquals(graphing.getList_tools().get(4).getText(),"Circle","'Circle' tool is not displayed");
            Assert.assertEquals(graphing.getList_tools().get(5).getText(),"Vector","'Vector' tool is not displayed");
            Assert.assertEquals(graphing.getList_tools().get(6).getText(),"Polygon","'Polygon' tool is not displayed");
            Assert.assertEquals(graphing.getList_tools().get(7).getText(),"Hyperbola","'Hyperbola' tool is not displayed");

            //TC row no. - 21 : Tools in the tool bar
            //Expected - 1 : A tool bar must be displayed on the question layer with the following options
            //Point,Line,Segment,Ray,Circle,Vector,Polygon,Hyperbola
            Assert.assertEquals(graphing.getList_toolsInGrid().get(0).getAttribute("title"),"point","'point' tool is not displayed");
            Assert.assertEquals(graphing.getList_toolsInGrid().get(1).getAttribute("title"),"line","'line' tool is not displayed");
            Assert.assertEquals(graphing.getList_toolsInGrid().get(2).getAttribute("title"),"segment","'segment' tool is not displayed");
            Assert.assertEquals(graphing.getList_toolsInGrid().get(3).getAttribute("title"),"ray","'ray' tool is not displayed");
            Assert.assertEquals(graphing.getList_toolsInGrid().get(4).getAttribute("title"),"circle","'circle' tool is not displayed");
            Assert.assertEquals(graphing.getList_toolsInGrid().get(5).getAttribute("title"),"vector","'vector' tool is not displayed");
            Assert.assertEquals(graphing.getList_toolsInGrid().get(6).getAttribute("title"),"Polygon","'polygon' tool is not displayed");
            Assert.assertEquals(graphing.getList_toolsInGrid().get(7).getAttribute("title"),"Hyperbola","'hyperbola' tool is not displayed");

            //Expected - 2 : Below the tools, Question Layer and Answer layer should be displayed.
            if(!graphing.getQuestionLayer().getText().contains("Question Layer")){
                Assert.fail("Question layer is not displayed");
            }
            if(!graphing.getAnswerLayer().getText().contains("Answer Layer")){
                Assert.fail("Answer layer is not displayed");
            }

            //Expected - 3 : Undo, Redo and Reset buttons should be displayed in the toolbar
            Assert.assertEquals(graphing.getButtonsInGrid().get(0).getText(),"Undo","Undo button is not displayed");
            Assert.assertEquals(graphing.getButtonsInGrid().get(1).getText(),"Redo","Redo button is not displayed");
            Assert.assertEquals(graphing.getButtonsInGrid().get(2).getText(),"Reset","Reset button is not displayed");


        }catch (Exception e){
            Assert.fail("Exception in testcase 'defaultLandingPage' in class ' Graphing '", e);

        }
    }


    @Test(priority = 2,enabled = true)
    public void creatingGraphCanvasAndQuestionLayer(){
         try{
            String appendChar = "a";
            int dataIndex = 173;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver,GraphingQuestionCreation.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type
            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext);//Enter the question

            //Expected - 1 : Graph canvas consists of labels
            Assert.assertEquals(graphing.getLabelsInGrid().get(1).getText(),"-3","Label is not displayed on graph canvas");
            Assert.assertEquals(graphing.getLabelsInGrid().get(7).getText(),"3","Label is not displayed on graph canvas");
            Assert.assertEquals(graphing.getLabelsInGrid().get(29).getText(),"-6","Label is not displayed on graph canvas");
            Assert.assertEquals(graphing.getLabelsInGrid().get(18).getText(),"6","Label is not displayed on graph canvas");

            graphing.getZoomIn_pixel().click();//Click on plus button
            graphing.getEditbox_unitDistance().click();
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

            //TC row no. - 49 : Using the Point tool
            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool

            //Expected - The Point tool should be highlighted
             Assert.assertEquals(graphing.getToolInGrid_enabled().getAttribute("title"),"point","point tool is not highlighted");

             graphing.getGridArea().click();//Click on grid area

            //Expected - The point should be placed on the graph for the selected coordinate
            if(graphing.getLabelsInGrid().get(25).getText().contains("A")||graphing.getLabelsInGrid().get(25).getText().contains("2")) {
            }else {
                Assert.fail("The point is not placed on the graph for selected coordinate");
            }

            graphing.getButtonsInGrid().get(2).click();//Click on reset button
            graphing.getZoomIn_pixel().click();//Click on plus button
            graphing.getEditbox_unitDistance().click();
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

            //TC row no. - 51 : Using the Line tool
            graphing.getList_toolsInGrid().get(1).click();//Click on line tool

            //Expected - The Line tool should be highlighted
             Assert.assertEquals(graphing.getToolInGrid_enabled().getAttribute("title"),"line","line tool is not highlighted");

            graphing.getLabelsInGrid().get(22).click();
            new DragAndDrop().dragAndDrop(graphing.getLabelsInGrid().get(22),graphing.getLabelsInGrid().get(16));
            graphing.getLabelsInGrid().get(16).click();

            //Expected - The initial point should be placed and a line should be drawn from that point
            Assert.assertEquals(graphing.getLabelsInGrid().get(22).getText(),"4","Initial point is not placed on the given index");
            Assert.assertEquals(graphing.getLabelsInGrid().get(16).getText(),"16","last point is not placed on the given index");

             graphing.getButtonsInGrid().get(2).click();//Click on reset button
             graphing.getZoomIn_pixel().click();//Click on plus button
             graphing.getEditbox_unitDistance().click();
             driver.switchTo().activeElement().clear();
             driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

             //TC row no. - 54 : Using the Segment tool
             graphing.getList_toolsInGrid().get(2).click();//Click on segment tool

             //Expected - The segment tool should be highlighted
             Assert.assertEquals(graphing.getToolInGrid_enabled().getAttribute("title"),"segment","segment tool is not highlighted");

             graphing.getLabelsInGrid().get(22).click();
             new DragAndDrop().dragAndDrop(graphing.getLabelsInGrid().get(22),graphing.getLabelsInGrid().get(16));
             graphing.getLabelsInGrid().get(16).click();

            //Expected -  The segment should be drawn on the graph joining the 2 selected points on the graph  graphing.getLabelsInGrid().get(22).click();
             Assert.assertEquals(graphing.getLabelsInGrid().get(22).getText(),"4","Initial point is not placed on the given index");
             Assert.assertEquals(graphing.getLabelsInGrid().get(16).getText(),"16","last point is not placed on the given index");

             graphing.getButtonsInGrid().get(2).click();//Click on reset button
             graphing.getZoomIn_pixel().click();//Click on plus button
             graphing.getEditbox_unitDistance().click();
             driver.switchTo().activeElement().clear();
             driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

             //TC row no. - Using the Circle tool
             graphing.getList_toolsInGrid().get(4).click();//Click on circle tool

             //Expected - The Circle tool should be highlighted
             Assert.assertEquals(graphing.getToolInGrid_enabled().getAttribute("title"),"circle","circle tool is not highlighted");

             graphing.getLabelsInGrid().get(22).click();
             new DragAndDrop().dragAndDrop(graphing.getLabelsInGrid().get(22), graphing.getLabelsInGrid().get(16));
             graphing.getLabelsInGrid().get(16).click();

             //Expected - A Circle should be drawn on the graph with first point as the centre and Second point as the radius of the circle
             Assert.assertEquals(graphing.getLabelsInGrid().get(22).getText(), "4", "Initial point is not placed on the given index");
             Assert.assertEquals(graphing.getLabelsInGrid().get(16).getText(), "16", "last point is not placed on the given index");

             graphing.getButtonsInGrid().get(2).click();//Click on reset button
             graphing.getZoomIn_pixel().click();//Click on plus button
             graphing.getEditbox_unitDistance().click();
             driver.switchTo().activeElement().clear();
             driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

             //TC row no. - 60 : Using the Ray tool
             graphing.getList_toolsInGrid().get(3).click();//Click on ray tool

             //Expected - The Ray tool should be highlighted
             Assert.assertEquals(graphing.getToolInGrid_enabled().getAttribute("title"),"ray","ray tool is not highlighted");

             graphing.getLabelsInGrid().get(22).click();
             new DragAndDrop().dragAndDrop(graphing.getLabelsInGrid().get(22),graphing.getLabelsInGrid().get(16));
             graphing.getLabelsInGrid().get(16).click();

             //Expected - A ray should be connecting the 2 points on the Graph
             Assert.assertEquals(graphing.getLabelsInGrid().get(22).getText(),"4","Initial point is not placed on the given index");
             Assert.assertEquals(graphing.getLabelsInGrid().get(16).getText(),"16","last point is not placed on the given index");

             graphing.getButtonsInGrid().get(2).click();//Click on reset button
             graphing.getZoomIn_pixel().click();//Click on plus button
             graphing.getEditbox_unitDistance().click();
             driver.switchTo().activeElement().clear();
             driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

             //TC row no. - 63 : Using the Vector tool
             graphing.getList_toolsInGrid().get(5).click();//Click on vector tool

             //Expected - The vector tool should be highlighted
             Assert.assertEquals(graphing.getToolInGrid_enabled().getAttribute("title"),"vector","vector tool is not highlighted");

             graphing.getLabelsInGrid().get(22).click();
             new DragAndDrop().dragAndDrop(graphing.getLabelsInGrid().get(22),graphing.getLabelsInGrid().get(16));
             graphing.getLabelsInGrid().get(16).click();

             //Expected - A vector should be drawn connecting the 2 points
             Assert.assertEquals(graphing.getLabelsInGrid().get(22).getText(),"4","Initial point is not placed on the given index");
             Assert.assertEquals(graphing.getLabelsInGrid().get(16).getText(),"16","last point is not placed on the given index");

             graphing.getButtonsInGrid().get(2).click();//Click on reset button
             graphing.getZoomIn_pixel().click();//Click on plus button
             graphing.getEditbox_unitDistance().click();
             driver.switchTo().activeElement().clear();
             driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

             //TC row no. - 66 : Using the Polygon tool
             graphing.getList_toolsInGrid().get(6).click();//Click on polygon tool

             //Expected - The polygon tool should be highlighted
             Assert.assertEquals(graphing.getToolInGrid_enabled().getAttribute("title"),"Polygon","Polygon tool is not highlighted");

             graphing.getLabelsInGrid().get(22).click();
             graphing.getLabelsInGrid().get(16).click();
             Actions ac = new Actions(driver);
             ac.moveToElement(graphing.getLabelsInGrid().get(22)).moveByOffset(0,30).click().build().perform();
             ac.moveByOffset(5,5).click().build().perform();
             graphing.getLabelsInGrid().get(22).click();

             //Expected - A polygon should be drawn with the first point as the initial point, and then immediately another point.
             Assert.assertEquals(graphing.getLabelsInGrid().get(22).getText(),"4","1st point is not placed on the given index");
             Assert.assertEquals(graphing.getLabelsInGrid().get(16).getText(),"16","2nd point is not placed on the given index");
             Assert.assertEquals(graphing.getLabelsInGrid().get(27).getText(),"C","3rd point is not placed on the given index");

            //Expected - The author should be able to  keep creating sides of the polygon and  close the polygon by clicking on an already created point.
            Assert.assertEquals(graphing.getGetPolygon().isDisplayed(),true,"Polygon is not closed");

             graphing.getButtonsInGrid().get(2).click();//Click on reset button

             graphing.getList_toolsInGrid().get(7).click();//Click on hyperbola tool

             //Expected - 1. Select the Hyperbola tool from the tool bar
             Assert.assertEquals(graphing.getToolInGrid_enabled().getAttribute("title"),"Hyperbola","Hyperbola tool is not highlighted");

         }catch (Exception e){
             Assert.fail("Exception in testcase 'creatingGraphCanvasAndQuestionLayer' in class ' Graphing '", e);

        }
    }


    @Test(priority = 3,enabled = true)
    public void editTool(){
        try{
            String appendChar = "a";
            int dataIndex = 320;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver,GraphingQuestionCreation.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type
            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext);//Enter the question

            graphing.getZoomIn_pixel().click();//Click on plus button
            graphing.getEditbox_unitDistance().click();
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool
            graphing.getLabelsInGrid().get(22).click();

            graphing.getList_toolsInGrid().get(8).click();//Click on edit tool
            Thread.sleep(2000);

            graphing.getPointPlacedOnGraph().click();//Click on the point placed on graph
            graphing.getEditbox_point().clear();
            driver.switchTo().activeElement().sendKeys("B");//Edit the name of the point
            driver.findElement(By.xpath("//html/body")).click();

            graphing.getPointPlacedOnGraph();//Click on the point placed on graph
            new DragAndDrop().dragAndDrop(graphing.getPointPlacedOnGraph(),graphing.getLabelsInGrid().get(16));

            //Expected - The object should be moved and the changes should be reflected on the Graph
            Assert.assertEquals(graphing.getLabelsInGrid().get(16).getText(),"12","Point is not dropped to the given place");
            Assert.assertEquals(graphing.getLabelsInGrid().get(25).getText(),"B","Point name is not edited");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'editTool' in class ' Graphing '", e);

        }
    }


    @Test(priority = 4,enabled = true)
    public void buttonsOperation(){
        try{
            String appendChar = "a";
            int dataIndex = 412;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver,GraphingQuestionCreation.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type
            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext);//Enter the question

            graphing.getZoomIn_pixel().click();//Click on plus button
            graphing.getEditbox_unitDistance().click();
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool
            graphing.getLabelsInGrid().get(22).click();//Place the point on graph
            graphing.getButtonsInGrid().get(0).click();//Click on undo button

            //TC row no. - 75 : Using the Undo button
            boolean pointDisplayed = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//*[@fill='#2B85C0']"));

            //Expected - The last performed action should be undone
            Assert.assertEquals(pointDisplayed,false,"Point is still displayed on graph after clicking on undo");

            //TC row no. - Using the Redo button
            graphing.getButtonsInGrid().get(1).click();//Click on redo button

            //Expected - The last done actions should be redone
            Assert.assertEquals(graphing.getPointPlacedOnGraph().isDisplayed(),true,"Point is not appearing after clicking on redo");

            //TC row no. - 77 : Using the Reset button
            graphing.getButtonsInGrid().get(2).click();//Click on reset button

            //Expected - 1 : The Graph should be set to Default value
            Assert.assertEquals(graphing.getEditbox_gridSize().getAttribute("value"),"40","Grid size is not changed to default value");
            Assert.assertEquals(graphing.getEditbox_unitDistance().getAttribute("value"),"1","Unit distance is not changed to default value");
            boolean pointDisplayedAfterReset = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//*[@fill='#2B85C0']"));

            //Expected - 2 : All the objects drawn on the Graph should be Removed
            Assert.assertEquals(pointDisplayedAfterReset,false,"Point is still displayed on graph after clicking on reset");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'buttonsOperation' in class ' Graphing '", e);
        }
    }


    @Test(priority = 5,enabled = true)
    public void annotation(){
        try{
            String appendChar = "a";
            int dataIndex = 484;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type

            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext);//Enter the question

            graphing.getZoomIn_pixel().click();//Click on plus button
            graphing.getEditbox_unitDistance().click();
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool
            graphing.getLabelsInGrid().get(22).click();//Place the point on graph
            graphing.getButtonsInGrid().get(0).click();//Click on undo button

            //TC row no. - 81 : Adding MathML annotation on the Canvas area
            graphing.getAddAnnotation().click();//Click on add annotation link

            graphing.getMathml_annotation().click();//Click on mathml option
            enterMathmlValue("Square root", "4");
            Thread.sleep(1000);
            graphing.getSave_mathml().click();//Click on save button to save mathml

            new WebDriverWait(driver,20).until(ExpectedConditions.invisibilityOfElementLocated(By.id("expression-annotation-save")));

            //Expected - The MathMl equation should be added as annotation
            Assert.assertTrue(graphing.getAdded_annotation().getText().trim().replaceAll("[\\t\\n\\r]", "").substring(0,1).equals("4"),"Mathml equation is not added as annotation");

            graphing.getAnnotation_content().click();//Click on annotation content
            graphing.getDrag_annotation().click();//Click on drag button
            new DragAndDrop().dragAndDrop(graphing.getDrag_annotation(),graphing.getGridArea());//Drop the mathml annotation on graph area

            //Expected - The annotation must be added
            if(!graphing.getDroppedAnnotation().getText().replaceAll("[\\t\\n\\r]", "").contains("4")){
                Assert.fail("Annotation is not dropped to graph area");
            }

            String annotationPosition = graphing.getDroppedAnnotation_position().getAttribute("style");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", graphing.getDragAnnotation_graphArea());
            graphing.getDragAnnotation_graphArea().click();
            new DragAndDrop().dragAndDrop(graphing.getDragAnnotation_graphArea(),graphing.getLabelsInGrid().get(12));//Move annotation to another place

            //Expected - The annotation area must be moved
            String changedAnnotationPosition = graphing.getDroppedAnnotation_position().getAttribute("style");

            if(changedAnnotationPosition.equals(annotationPosition)){
                Assert.fail("Annotation area is not moved");
            }

            graphing.getButtonsInGrid().get(2).click();//Click on reset button
            graphing.getZoomIn_pixel().click();//Click on plus button
            graphing.getEditbox_unitDistance().click();
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

            //TC row no. - 87 : Adding Text annotation on the Canvas area
            graphing.getAddAnnotation().click();//Click on add annotation link
            graphing.getText_annotation().click();//Click on text
            graphing.getAnnotation_textInputbox().sendKeys("AnnotationText");
            driver.findElement(By.xpath("//html/body")).click();

            //Expected - The text should be saved
            Assert.assertEquals(graphing.getAdded_annotation().getText(),"AnnotationText","Added text annotation is not saved");

            graphing.getAnnotation_content().click();//Click on annotation content
            graphing.getDrag_annotation().click();//Click on drag button

            new DragAndDrop().dragAndDrop(graphing.getDrag_annotation(),graphing.getGridArea());//Drop added annotation to the graph area

            String annotationPositionText = graphing.getDroppedAnnotation_position().getAttribute("style");

            //Expected - The annotation must be added
            Assert.assertEquals(graphing.getDroppedAnnotation().getText(),"AnnotationText","Annotation is not added on the graph");

            graphing.getDragAnnotation_graphArea().click();
            new DragAndDrop().dragAndDrop(graphing.getDragAnnotation_graphArea(),graphing.getLabelsInGrid().get(12));//Move the annotation on graph area

            //Expected - The annotation area must be moved
            String changedAnnotationPositionText = graphing.getDroppedAnnotation_position().getAttribute("style");

            if(changedAnnotationPositionText.equals(annotationPositionText)){
                Assert.fail("Annotation area is not moved");
            }

            graphing.getButtonsInGrid().get(2).click();//Click on reset button
            graphing.getZoomIn_pixel().click();//Click on plus button
            graphing.getEditbox_unitDistance().click();
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

            //TC row no - Adding Image as annotation on the Canvas area
            graphing.getAddAnnotation().click();//Click on add annotation link
            graphing.getImage_annotation().click();//Click on image
            imageUpload("484");
            new WebDriverWait(driver,60).until(ExpectedConditions.invisibilityOfElementLocated(By.id("annotation-upload-image")));

            //Expected - The image must appear as an annotation
           if(!graphing.getAdded_imageAnnotationContent().getAttribute("src").contains("img")){
                Assert.fail("Added image is not appearing as an annotation");
            }

            graphing.getAnnotation_content().click();
            graphing.getDrag_annotation().click();

            new DragAndDrop().dragAndDrop(graphing.getDrag_annotation(),graphing.getGridArea());//Drop the annotation to graph area

            String annotationPositionImage = graphing.getDroppedAnnotation_position().getAttribute("style");

            //Expected - The annotation must be added
            if(!graphing.getDropped_imageAnnotation().getAttribute("src").contains("img")){
                Assert.fail("Annotation is not added");
            }

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", graphing.getDragAnnotation_graphArea());
            graphing.getDragAnnotation_graphArea().click();
            new DragAndDrop().dragAndDrop(graphing.getDragAnnotation_graphArea(),graphing.getLabelsInGrid().get(12));//Move the annotation on graph area

            //Expected - The annotation area must be moved
            String changedAnnotationPositionImage = graphing.getDroppedAnnotation_position().getAttribute("style");

            if(changedAnnotationPositionImage.equals(annotationPositionImage)){
                Assert.fail("Annotation area is not moved");
            }

        }catch (Exception e){
            Assert.fail("Exception in testcase 'annotation' in class ' Graphing '", e);

        }
    }


    @Test(priority = 6,enabled = true)
    public void selectAndCancelAnnotation(){
        try{

            String appendChar = "a";
            int dataIndex = 648;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type

            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext);//Enter the question

            graphing.getZoomIn_pixel().click();//Click on plus button
            graphing.getEditbox_unitDistance().click();
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

            graphing.getAddAnnotation().click();//Click on add annotation link

            //Expected - The following options should be displayed
            //a. Adding mathML equation
            //b. Adding text
            //c. Adding Image
            Assert.assertEquals(graphing.getMathml_annotation().isDisplayed(),true,"mathml equation is not displayed");
            Assert.assertEquals(graphing.getText_annotation().isDisplayed(),true,"text is not displayed");
            Assert.assertEquals(graphing.getImage_annotation().isDisplayed(),true,"image equation is not displayed");

            graphing.getMathml_annotation().click();

            //Expected - MathML editor should be displayed
            if(graphing.getMathmlEditor().size()==0){
                Assert.fail("Mathml editor is not displayed");
            }

            graphing.getMathml_cancel().click();//Click on cancel button
            graphing.getAddAnnotation().click();//Click on add annotation link

            //Expected - The following options should be displayed
            //a. Adding mathML equation
            // b. Adding text
            //c. Adding Image
            Assert.assertEquals(graphing.getMathml_annotation().isDisplayed(),true,"mathml equation is not displayed");
            Assert.assertEquals(graphing.getText_annotation().isDisplayed(),true,"text is not displayed");
            Assert.assertEquals(graphing.getImage_annotation().isDisplayed(),true,"image equation is not displayed");

            graphing.getMathml_annotation().click();//Click on mathml option

            //Expected - MathML editor should be displayed
            if(graphing.getMathmlEditor().size()==0){
                Assert.fail("Mathml editor is not displayed");
            }

            graphing.getMathml_cancel().click();//Click on cancel button
            graphing.getText_annotation().click();//Click on text option

            graphing.getAnnotation_textInputbox().click();
            driver.switchTo().activeElement().sendKeys("AnnotationText");
            driver.findElement(By.xpath("//html/body")).click();

            //Expected - Cursor should be displayed and the field should be editable
            Assert.assertEquals(graphing.getAdded_annotation().getText(),"AnnotationText","Text field is not editable");

            graphing.getAnnotation_content().click();//Click on annotation
            graphing.getDelete_annotation().click();//Click on delete annotation

            graphing.getAddAnnotation().click();//Click on add annotation link

            //Expected - The following options should be displayed
            //a. Adding mathML equation
            // b. Adding text
            //c. Adding Image
            Assert.assertEquals(graphing.getMathml_annotation().isDisplayed(),true,"mathml equation is not displayed");
            Assert.assertEquals(graphing.getText_annotation().isDisplayed(),true,"text is not displayed");
            Assert.assertEquals(graphing.getImage_annotation().isDisplayed(),true,"image equation is not displayed");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'selectAndCancelAnnotation' in class ' Graphing '", e);

        }
    }


    @Test(priority = 7,enabled = true)
    public void edit(){
        try {
            String appendChar = "a";
            int dataIndex = 753;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type

            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext);//Enter the question

            graphing.getZoomIn_pixel().click();//Click on plus button
            graphing.getEditbox_unitDistance().click();
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool
            graphing.getLabelsInGrid().get(22).click();//Place the point on the grid

            graphing.getList_toolsInGrid().get(8).click();//Click on edit tool

            //Expected - Edit tool should be highlighted
            Assert.assertEquals(graphing.getToolInGrid_enabled().getAttribute("title"),"edit object","edit object tool is not highlighted");

            graphing.getPointPlacedOnGraph().click();//Click on the point placed on graph

            //Expected - Show object check box should be displayed
            Assert.assertEquals(graphing.getShowObject().getAttribute("type"),"checkbox","Show object checkbox is not present");

            //Expected - Show label check box should be displayed
            Assert.assertEquals(graphing.getShowLabels().getAttribute("type"),"checkbox","Show label checkbox is not present");

            graphing.getList_toolsInGrid().get(0).click();
            graphing.getLabelsInGrid().get(14).click();//Place the 2nd point on the grid
            graphing.getLabelsInGrid().get(18).click();//Place the 3rd point on the grid

            //Expected - The object label should be shown (A, B, C...)
            Assert.assertEquals(graphing.getLabelsInGrid().get(25).getText(),"A","The object label is not shown as expected");
            Assert.assertEquals(graphing.getLabelsInGrid().get(26).getText(),"B","The object label is not shown as expected");
            Assert.assertEquals(graphing.getLabelsInGrid().get(27).getText(),"C","The object label is not shown as expected");

            graphing.getList_toolsInGrid().get(8).click();//Click on edit tool
            graphing.getList_pointPlacedOnGraph().get(0).click();//Click on 1st point A

            graphing.getEditbox_point().click();
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("D");//Change the name of the point a 'D'
            driver.findElement(By.xpath("/html/body")).click();

            //Expected - Cursor should be displayed and the field should be editable
            Assert.assertEquals(graphing.getLabelsInGrid().get(25).getText(),"D","The object label is not editable");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'edit' in class ' Graphing '", e);

        }
    }


    @Test(priority = 8,enabled = true)
    public void answerLayer(){
        try{
            String appendChar = "a";
            int dataIndex = 834;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type

            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext);//Enter the question

            graphing.getZoomIn_pixel().click();//Click on plus button
            graphing.getEditbox_unitDistance().click();
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

            Select sel = new Select(graphing.getDrawingPromptCombo());
            sel.selectByValue("Using drawing tools");

            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool
            graphing.getLabelsInGrid().get(22).click();//Place the point on the grid

            graphing.getAnswerLayer().click();//Click on answer layer

            //Expected - Position  tool will not be visible.
            Assert.assertEquals(graphing.getPositionTool_disabled().isDisplayed(),true,"Position tool is not disabled");

            //Edit object should be enabled
            Assert.assertEquals(graphing.getEditObject().isEnabled(), true, "Edit object is not enabled");

            //Expected - Author should be able to click on " Answer layer".
            Assert.assertEquals(graphing.getPointPlaced_answerLayer().isDisplayed(),true,"Author is not able to click on answer layer so that color of point is not changed");

            //Expected - By default point and line are enabled
            Assert.assertEquals(graphing.getToolInGrid_enabled().getAttribute("title"),"point","by default point is not enabled");
            Assert.assertEquals(graphing.getList_toolsInGrid().get(1).getAttribute("title"),"line","by default line is not enabled");
            Assert.assertEquals(graphing.getList_disabledTools().get(0).getAttribute("title"),"segment","by default segment is not disabled");

            graphing.getList_toolsCheckbox().get(2).click();//Click on segment tool

            //Expected - Author should be able to select tools by enabling the answering tools checkboxes.
            //Expected - Only those tools that are enabled for graph options will be available for the author
            Assert.assertEquals(graphing.getList_toolsInGrid().get(0).getAttribute("title"),"point","point is not enabled");
            Assert.assertEquals(graphing.getList_toolsInGrid().get(1).getAttribute("title"),"line","line is not enabled");
            Assert.assertEquals(graphing.getToolInGrid_enabled().getAttribute("title"),"segment","Segment tool is not enabled and highlighted");
            Assert.assertEquals(graphing.getList_disabledTools().get(0).getAttribute("title"),"ray","ray is not disabled");
            Assert.assertEquals(graphing.getList_disabledTools().get(1).getAttribute("title"),"circle","circle is not disabled");
            Assert.assertEquals(graphing.getList_disabledTools().get(2).getAttribute("title"),"vector","vector is not disabled");
            Assert.assertEquals(graphing.getList_disabledTools().get(3).getAttribute("title"),"Polygon","Polygon is not disabled");
            Assert.assertEquals(graphing.getList_disabledTools().get(4).getAttribute("title"),"Hyperbola","Hyperbola is not disabled");

            graphing.getList_toolsCheckbox().get(2).click();//Click on segment tool

            //Expected - Base question objects should be in black.
            Assert.assertEquals(graphing.getPointPlaced_answerLayer().isDisplayed(),true,"base question is not displayed in black");

            graphing.getGridArea().click();//Place the point on grid area

            //Expected - Correct objects (lines or points) should be shown in green over base objects
            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(0).isDisplayed(),true,"correct object is not displayed in black color");

            graphing.getLabelsInGrid().get(13).click();

            //Expected - 1 : Author can create one or more points to indicate the correct answer for the question.
            if(graphing.getList_pointPlaced_answerLayer_green().size()!=2){
                Assert.fail("points are not placed on answer layer");
            }

            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(0).isDisplayed(),true,"Point is not placed on answer layer");
            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(1).isDisplayed(),true,"Point is not placed on answer layer");

            //Expected - Correct answers should not be labeled and they should always be visible
            if(!graphing.getLabelsInGrid().get(26).getText().equals("")){
                Assert.fail("Correct answer is labeled");
            }

            graphing.getQuestionLayer().click();//Click on question layer
            graphing.getButtonsInGrid().get(2).click();//Click on reset button
            graphing.getButton_yes().click();//Click on yes button
            graphing.getZoomIn_pixel().click();//Click on plus button
            graphing.getEditbox_unitDistance().click();
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

            graphing.getAnswerLayer().click();//Click on answer layer

            graphing.getList_toolsInGrid().get(1).click();//Click on line tool
            graphing.getLabelsInGrid().get(22).click();
            graphing.getLabelsInGrid().get(16).click();

            graphing.getLabelsInGrid().get(18).click();
            graphing.getLabelsInGrid().get(20).click();

            //Expected - Author can create one or more lines to indicate the correct answer for the question.

            if(graphing.getList_pointPlaced_answerLayer_green().size()-2!=4){
                Assert.fail("lines are not displayed on answer layer");
            }

            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(0).isDisplayed(),true,"line is not displayed");
            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(1).isDisplayed(),true,"line is not displayed");
            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(2).isDisplayed(),true,"line is not displayed");
            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(3).isDisplayed(),true,"line is not displayed");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'answerLayer' in class ' Graphing '", e);
    }
    }


    @Test(priority = 9,enabled = true)
    public void buttonsOnAnswerLayer(){
        try{
            String appendChar = "a";
            int dataIndex = 997;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type

            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext);//Enter the question

            graphing.getZoomIn_pixel().click();//Click on plus button
            graphing.getEditbox_unitDistance().click();
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("2");//Enter the unit distance value

            Select sel = new Select(graphing.getDrawingPromptCombo());
            sel.selectByValue("Using drawing tools");

            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool
            graphing.getLabelsInGrid().get(22).click();//Place the point on the grid inside question layer

            graphing.getAnswerLayer().click();//Click on answer layer
            graphing.getLabelsInGrid().get(16).click();//Place the point on grid inside answer area

            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(0).isDisplayed(),true,"Point is not placed on grid area");

            //TC row no. - 120 : Using Undo button in the answer layer

            graphing.getButtonsInGrid().get(0).click();//Click on undo button

            //Expected - Undo button will undo the last action on answer canvas only

             boolean pointFound= new BooleanValue().presenceOfElement(dataIndex,By.xpath("//*[@fill='#1E9301']"));

            Assert.assertEquals(pointFound,false,"Point place on question layer is still displayed on graph after clicking on undo");
            Assert.assertEquals(graphing.getPointPlaced_answerLayer().isDisplayed(),true,"Point placed on question layer is not displayed after clicking on undo");

            graphing.getButtonsInGrid().get(1).click();//Click on redo button

            //Expected - Redo button will redo the action on answer canvas only
            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(0).isDisplayed(),true,"Point is not displayed after clicking on redo button");

            //TC row no. - 121 : Using Reset button in the answer layer
            graphing.getButtonsInGrid().get(2).click();//Click on reset button
            Thread.sleep(2000);

            //Expected - Reset button will remove all the objects which are drawn while the tab “Enter Answer” is active.
            boolean pointAfterReset = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//*[@fill='#1E9301']"));
            Assert.assertEquals(pointAfterReset,false,"Point is still displayed on answer layer after clicking on reset");
            Assert.assertEquals(graphing.getAnswerLayer_enabled().isDisplayed(),true,"Answer layer is not enabled");

            graphing.getLabelsInGrid().get(16).click();//Place the point on grid inside answer area

            graphing.getQuestionLayer().click();//Click on question layer
            graphing.getList_toolsInGrid().get(8).click();//Click on edit tool

            graphing.getZoomIn_pixel().click();//Click on plus button
            graphing.getButton_yes().click();//Click on yes button
            graphing.getEditbox_unitDistance().click();
            graphing.getEditbox_unitDistance().clear();
            driver.switchTo().activeElement().sendKeys("3");//Change the value of unit distance

            //Expected - Clicking on edit should allow the user to edit the all the objects like unit distance, grid.
            Assert.assertEquals(graphing.getEditbox_gridSize().getAttribute("value"),"60","Grid size is not edited");
            Assert.assertEquals(graphing.getEditbox_unitDistance().getAttribute("value"),"3","Unit distance is not edited");

             graphing.getAnswerLayer().click();//Click on answer layer

            //If edit is selected after drawing the correct answers, then the user has to enter the correct answer(s) again by redrawing lines or points on the graph.
            if(graphing.getList_pointPlaced_answerLayer_green().size()!=0){
                Assert.fail("Point is displayed on answer layer after editing");
            }

            boolean pointAfterEdit = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//*[@fill='#1E9301']"));
            Assert.assertEquals(pointAfterEdit,false,"Point is still displayed on answer layer after editing");


        }catch (Exception e){
            Assert.fail("Exception in testcase 'buttonsOnAnswerLayer' in class ' Graphing '", e);

        }
    }


    @Test(priority = 10,enabled = true)
    public void commonParameter(){
        try{
            String appendChar = "a";
            int dataIndex = 1093;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);
            ClassificationQuestionCreation classification = PageFactory.initElements(driver,ClassificationQuestionCreation.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            graphing.getButton_save().click();//Click on save button
            Thread.sleep(4000);

            //Error message should appear
            Assert.assertEquals(graphing.getMessage_text().getText(),"Question title should not be empty","error message is not displayed correctly");

            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext);//Enter the question

            //Expected - 1 :  "Show write board" should be disabled by default
            Assert.assertEquals(graphing.getWriteboard_unchecked().isDisplayed(),true,"Show writeboard is not disabled by default");

            //Expected - 2 : Show grid  should be displayed if its checked.
            Assert.assertEquals(graphing.getShowGrid().getAttribute("checked"),"true","Show grid checkbox is not checked");

            //Expected - 3 : Show labels  should be displayed if its checked.
            Assert.assertEquals(graphing.getShowLabels().getAttribute("checked"),"true","Show Labels checkbox is not checked");

            graphing.getButton_save().click();//Click on save button
            Thread.sleep(2000);

            //Error message should appear
            Assert.assertEquals(graphing.getMessage_text().getText(),"Please draw at least one object on the graph as the correct answer.","error message is not displayed correctly");

            Select sel = new Select(graphing.getDrawingPromptCombo());
            sel.selectByValue("Using drawing tools");

            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool
            graphing.getGridArea().click();//Click on grid area

            graphing.getButton_save().click();//Click on save button
            Thread.sleep(2000);

            //Error message should appear
            Assert.assertEquals(graphing.getMessage_text().getText(),"Please draw at least one object on the graph as the correct answer.","error message is not displayed correctly");

            graphing.getAnswerLayer().click();//Click on answer layer
            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool
            graphing.getGridArea().click();//Click on grid area

            graphing.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Saved.']")));

            //Expected - Question should be saved by clicking on the Save button.
            Assert.assertEquals(graphing.getMessage_text().getText(),"Saved.","Question is not saved");

            //TC row no. - 130 : Other parameters
            //Expected - 1 : One question should be worth one point by default.
            Assert.assertEquals(previewPage.getQuestionPoint_previewPage().getAttribute("value"),"1","Question is not carrying one point");

            String winHandleBefore = driver.getWindowHandle();

            graphing.getButton_preview().click();//Click on Preview button

            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            graphing.getPreview_submit().click();

            driver.close();
            driver.switchTo().window(winHandleBefore);//Switch the driver control back to main window

            //Expected - 2 : Questions should have solution and hint links
            if(!graphing.getLabel_solution().getText().contains("Solution")){
                Assert.fail("Solution label is not displayed");
            }

            if(!graphing.getLabel_hint().getText().contains("Hint")){
                Assert.fail("Hint label is not displayed");
            }
            graphing.getEditbox_solution().sendKeys("Solution Text");
            graphing.getEditbox_hint().sendKeys("Hint Text");

            Assert.assertEquals(graphing.getEditbox_solution().getText(),"Solution Text","Text is not entered in solution edit box");
            Assert.assertEquals(graphing.getEditbox_hint().getText(),"Hint Text","Text is not entered in hint edit box");

            Select select = new Select(graphing.getDifficulty_level());

            //Expected - 3 : Questions can be tagged with difficulty levels
            select.selectByVisibleText("Easy");
            select.selectByVisibleText("Medium");
            select.selectByVisibleText("Hard");
            Thread.sleep(2000);

            classification.getLearningObjective().click();//Click on learning objective

            //Expected - 4 : Questions can be tagged with LOs
            if(!classification.getList_tloElo_learningObjective().get(0).getText().trim().contains("1.OA"))
            {
                Assert.fail("Expected TLO is not displayed");
            }

            if(!classification.getList_tloElo_learningObjective().get(1).getText().trim().contains("1.OA.A.1"))
            {
                Assert.fail("Expected ELO is not displayed");
            }

            int defaultListSize = classification.getList_tloElo_learningObjective().size();

            if(defaultListSize!=2)
            {
                Assert.fail("Learning Objective list size is not equal to 2");
            }

            classification.getAdd_learningObjetive().click();//Click on add Learning Objective link
            classification.getCheckbox_elo1_OA_A_2().click();//Click on 1.OA.A.2 ELO
            classification.getButton_associate().click();//Click on Associate button
            Thread.sleep(2000);

            classification.getLearningObjective().click();//Click on learning objective
            classification.getLearningObjective().click();//Click on learning objective
            Thread.sleep(2000);

            if(!classification.getList_tloElo_learningObjective().get(2).getText().trim().contains("1.OA.A.2"))
            {
                Assert.fail("Expected ELO is not displayed");
            }

            int listSizeAfterAddingTLO = classification.getList_tloElo_learningObjective().size();

            if(listSizeAfterAddingTLO!=3)
            {
                Assert.fail("Learning Objective list size is not equal to 3");
            }
            classification.getCloseButton_learningObjective().click();//Close the learning objective

        }catch (Exception e){
            Assert.fail("Exception in testcase 'commonParameter' in class ' Graphing '", e);

        }
    }


    @Test(priority = 11,enabled = true)
    public void editQuestion(){
        try{
            String appendChar = "a";
            int dataIndex = 1269;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            AssignmentReview assessmentReview= PageFactory.initElements(driver,AssignmentReview.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));

            Thread.sleep(2000);
            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext);//Enter the question

            graphing.getZoomIn_pixel().click();//Click on plus to increase grid size
            graphing.getEditbox_unitDistance().clear();
            graphing.getEditbox_unitDistance().sendKeys("2");//Enter unit distance value as 2

            Select sel = new Select(graphing.getDrawingPromptCombo());
            sel.selectByValue("Using drawing tools");

            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool
            graphing.getGridArea().click();//Click on grid area

            graphing.getAnswerLayer().click();//Click on answer layer
            graphing.getLabelsInGrid().get(16).click();//Place the point on graph

            graphing.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Saved.']")));

            graphing.getQuestionLayer().click();//Click on question layer

            //Expected - The question must be saved with all the Entered values
            Assert.assertEquals(graphing.getTextBox_QuestionField().getText(),questiontext,"Entered question is not saved");
            Assert.assertEquals(graphing.getPointPlacedOnGraph().getAttribute("fill"),"#2B85C0","Point placed on question layer is not saved");

            graphing.getAnswerLayer().click();//Click on answer layer

            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(0).getAttribute("fill"),"#1E9301","Point placed on answer layer is not saved");

            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[starts-with(@class,'btn btn-rounded btn-default lsm-createAssignment-done m-r-md selected')]")));
            graphing.getButton_review().click();//Click on review button

            //>>>>>>Instructor short view
            //Expected - Only question title should be displayed.
            if(!listPage.getCreateAssignmentQuestionName().getText().contains(questiontext)){
                Assert.fail("Question title is not displayed");
            }
            //Expected -  Question Type should be displayed as “Graphing Question”
            Assert.assertEquals(listPage.getQuestionType().getText(),"Graphing","Question type is not displayed");

            String owner = listPage.getLabelValue_Ownwer().getText();
            //Expected - Owner of the question must be displayed
            Assert.assertEquals(listPage.getLabelValue_Ownwer().getText(),"You","Owner of the question is not displayed");

            //Expected - Question ID must be displayed
            String questionId= listPage.getLabelValue_QuestionID().getText();
            if(listPage.getLabelValue_QuestionID().getText().length()==0)
            {
                Assert.fail("Question id is not displayed");

            }
            //Expected - Corresponding TLO and ELO under which the question falls must be displayed
            if(!listPage.getLabelValue_StandardTlo().getText().contains("1.OA, 1.OA.A.1"))
            {
                Assert.fail("Tlo and Elo is not displayed");
            }
            listPage.getArrowLink().click();//Select the assignment


            //Expected - Following option should be displayed(Instructor Question Expanded View)
            //1. Edit link should be displayed
            //2. Delete link should be displayed
            //3. Preview link should be displayed
            //4. Duplicate link should be displayed
            //5.Question ID and question owner should be displayed
            //6.The canvas with the graph and objects should be displayed
            Assert.assertEquals(assignments.getEditLink().isDisplayed(),true,"Edit link is not displayed");
            Assert.assertEquals(assignments.getDuplicate_link().isDisplayed(),true,"Duplicate link is not displayed");
            Assert.assertEquals(assignments.getDelete_link().isDisplayed(),true,"Delete link is not displayed");
            Assert.assertEquals(assessmentReview.getQuestionId().getText(),questionId,"Question id is not displayed as in instructor short view");
            Assert.assertEquals(assessmentReview.getOwner().getText(),owner,"Owner is not displayed as in instructor short view");

            if(graphing.getList_pointPlaced_answerLayer().size()!=1){
                Assert.fail("one Object is not displayed on graph as placed by instructor");
            }
            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer().get(0).getAttribute("fill"),"#000000","Placed object is not displayed");

            if(graphing.getList_pointPlaced_answerLayer_green().size()!=1){
                Assert.fail("one Object is not displayed on graph as placed by instructor");

            }
            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(0).getAttribute("fill"),"#1E9301","Placed object is not displayed");


            assignments.getEditLink().click();//Click on edit link

            //Expected - The question should be displayed and all the values should be populated
            Assert.assertEquals(graphing.getTextBox_QuestionField().getText(),questiontext,"Entered question is not saved");
            Assert.assertEquals(graphing.getPointPlacedOnGraph().getAttribute("fill"),"#2B85C0","Point placed on question layer is not saved");

            graphing.getAnswerLayer().click();//Click on answer layer

            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(0).getAttribute("fill"),"#1E9301","Point placed on answer layer is not saved");

            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext+"Edited");

            graphing.getQuestionLayer().click();//Click on question layer

            graphing.getList_toolsInGrid().get(8).click();//Click on edit object tool
            graphing.getPointPlacedOnGraph().click();
            new DragAndDrop().dragAndDrop(graphing.getPointPlacedOnGraph(),graphing.getLabelsInGrid().get(16));//Drop the point to other place

            //Expected - Instructor should be able to make changes to all the fields in the question
            Assert.assertEquals(graphing.getTextBox_QuestionField().getText(),questiontext+"Edited","Question is not edited");

            if(graphing.getList_pointPlacedOnGraph().size()!=1){
                Assert.fail("Point is not edited on question layer");
            }

            graphing.getButton_save().click();//Click on save button

            //Expected -  question should be saved with the new Values
            Assert.assertEquals(graphing.getTextBox_QuestionField().getText(),questiontext+"Edited","Question is not edited");

            if(graphing.getList_pointPlacedOnGraph().size()!=1){
                Assert.fail("Point is not edited on question layer");
            }
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[starts-with(@class,'btn btn-rounded btn-default lsm-createAssignment-done m-r-md selected')]")));
            graphing.getButton_review().click();//Click on review button
            listPage.getButton_addMore().click();//Click on add more button
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type

            graphing.getTextBox_QuestionField().sendKeys("Graphing:Question2");
            graphing.getGridArea().click();//Place the point on grid area

            graphing.getAnswerLayer().click();//Click on answer layer
            graphing.getGridArea().click();//Place the point on grid area on answer layer

            graphing.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[starts-with(@class,'btn btn-rounded btn-default lsm-createAssignment-done m-r-md selected')]")));
            Thread.sleep(2000);
            graphing.getButton_review().click();//Click on review button
            Thread.sleep(2000);

            listPage.getList_dragQuestion().get(1).click();//Click on drag area
            new DragAndDrop().dragAndDrop(listPage.getList_dragQuestion().get(1),listPage.getList_dragQuestion().get(0));

            //Expected - Re-ordering in list view should be supported
            Assert.assertEquals(listPage.getList_questionBody().get(0).getText(),"Q1: Graphing:Question2","Questions are not re ordered");
            Assert.assertEquals(listPage.getList_questionBody().get(1).getText(),"Q2: "+questiontext+"Edited","Questions are not re ordered");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'editQuestion' in class ' Graphing '", e);

        }
    }


    @Test(priority = 12,enabled = true)
    public void reorderingWithPassageBased(){
        try{
            String appendChar = "a";
            int dataIndex = 1469;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));

            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext+"1");//Enter the question

            graphing.getZoomIn_pixel().click();//Click on plus to increase grid size
            graphing.getEditbox_unitDistance().clear();
            graphing.getEditbox_unitDistance().sendKeys("2");//Enter unit distance value as 2

            Select sel = new Select(graphing.getDrawingPromptCombo());
            sel.selectByValue("Using drawing tools");

            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool
            graphing.getGridArea().click();//Click on grid area

            graphing.getAnswerLayer().click();//Click on answer layer
            graphing.getLabelsInGrid().get(16).click();//Place the point on graph

            graphing.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[starts-with(@class,'btn btn-rounded btn-default lsm-createAssignment-done m-r-md selected')]")));
            graphing.getButton_review().click();//Click on review button

            listPage.getButton_addMore().click();//Click on add more button
            tloListPage.getButton_create().click();//Click on create button
            createPassageBasedQuestion(dataIndex);

            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[starts-with(@class,'btn btn-rounded btn-default lsm-createAssignment-done m-r-md selected')]")));
            Thread.sleep(2000);
            graphing.getButton_review().click();//Click on review button

            //Expected -  Reordering should not be supported if the graphing question is linked to a passage
            Assert.assertEquals(listPage.getList_dragQuestion().get(0).isDisplayed(),false,"Drag button is displayed to reorder the question");
            Assert.assertEquals(listPage.getList_dragQuestion().get(1).isDisplayed(),false,"Drag button is displayed to reorder the question");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'reorderingWithPassageBased' in class ' Graphing '", e);
        }
    }


    @Test(priority = 13,enabled = true)
    public void preview(){
        try{
            String appendChar = "a";
            int dataIndex = 1541;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment  createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));

            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext+"1");//Enter the question

            graphing.getZoomIn_pixel().click();//Click on plus to increase grid size
            graphing.getEditbox_unitDistance().clear();
            graphing.getEditbox_unitDistance().sendKeys("2");//Enter unit distance value as 2

            Select sel = new Select(graphing.getDrawingPromptCombo());
            sel.selectByValue("Using drawing tools");

            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool
            graphing.getGridArea().click();//Click on grid area

            graphing.getAnswerLayer().click();//Click on answer layer
            graphing.getLabelsInGrid().get(16).click();//Place the point on graph

            graphing.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.className("as-question-teacher-preview-button")));
            graphing.getButton_preview().click();//Click on preview button

            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }

            //Expected - 1 : A new tab must be opened
            //Expected - 2 : The question should be displayed only with the graph canvas and answer layer only (without answers)
            boolean questionPointFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//*[@fill='#000000']"));
            Assert.assertEquals(questionPointFound,true,"question point is not found as placed by instructor");

            boolean answerPointFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//*[@fill='#1E9301']"));
            Assert.assertEquals(answerPointFound,false,"answer point is found");

             graphing.getLabelsInGrid().get(16).click();//Place the point on grid area

            //Expected - 3 : Instructor should be abe to attempt the questions as student would do.
            Assert.assertEquals(graphing.getPointPlacedOnGraph().getAttribute("fill"),"#2B85C0","Instructor is not able to attempt the question");

            graphing.getPreview_submit().click();//Click on submit button

            //Expected - 4 : Teacher should be able to submit the answers, by clicking on "Submit"
            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(0).getAttribute("fill"),"#1E9301","Instructor is not able to submit the answer");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'preview' in class ' Graphing '", e);

        }
    }


    @Test(priority = 14,enabled = true)
    public void studentQuestionAttempt(){
        try{
            String appendChar = "a";
            int dataIndex = 1626;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//SignUp as a student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));

            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext+"1");//Enter the question

            graphing.getZoomIn_pixel().click();//Click on plus to increase grid size
            graphing.getEditbox_unitDistance().clear();
            graphing.getEditbox_unitDistance().sendKeys("2");//Enter unit distance value as 2

            Select sel = new Select(graphing.getDrawingPromptCombo());
            sel.selectByValue("Using drawing tools");

            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool
            graphing.getGridArea().click();//Click on grid area

            graphing.getAnswerLayer().click();//Click on answer layer
            graphing.getLabelsInGrid().get(16).click();//Place the point on graph

            graphing.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[starts-with(@class,'btn btn-rounded btn-default lsm-createAssignment-done m-r-md selected')]")));
            graphing.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getAssignment().click();//Select the assignment

            //Expected -  The student should see the graph canvas and question layer only.
            Assert.assertEquals(graphing.getGraph_canvas().isDisplayed(),true,"Graph canvas is not displayed");
            Assert.assertEquals(graphing.getPointPlaced_answerLayer().getAttribute("fill"),"#000000","Question layer is not displayed");

            boolean answerLayer = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//*[@fill='#1E9301']"));
            Assert.assertEquals(answerLayer,false,"Answer layer is present on student side");

            //Expected - Student should see the tools which are allowed by teacher  using enable graphing options
            Assert.assertEquals(graphing.getList_toolsInGrid().get(0).getAttribute("title"),"point","Point tool is not displayed as enabled by instructor");
            Assert.assertEquals(graphing.getList_toolsInGrid().get(1).getAttribute("title"),"line","Line tool is not displayed as enabled by instructor");

            if(graphing.getList_toolsInGrid().size()!=2+1){
                Assert.fail("Two tools enabled by instructor are not displayed");
            }

            graphing.getLabelsInGrid().get(16).click();//Place the point on canvas

            new MouseHover().mouserhoverbywebelement(graphing.getPointPlaced_previewPage());

            boolean coordinateFound = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//div[text()='(16.00, 0)']"));
            //Expected - When student is drawing a point, the X and Y coordinate of the object must not be shown. Only the object should be shown.
            Assert.assertEquals(coordinateFound,false,"Coordinate of object is displayed");

            //Expected - Object should be in Blue color with no labels
            Assert.assertEquals(graphing.getPointPlaced_previewPage().getAttribute("fill"),"#2B85C0","Point placed by student is not displayed in blue color");

            for(int i=0;i<graphing.getLabelsInGrid().size();i++){
                if(graphing.getLabelsInGrid().get(i).getText().contains("B")){
                Assert.fail("Object is place with label");
            }
        }
            graphing.getDelete_studentAttempt().click();

            graphing.getReset_studentAttempt().click();//Click on reset button

            boolean pointFoundAfterReset = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//*[@fill='#2B85C0']"));

            //Expected - Student should be able to reset and draw again.
            Assert.assertEquals(pointFoundAfterReset,false,"Student is not able to reset as point as point is still appearing after clicking on reset");

            graphing.getLabelsInGrid().get(16).click();//Place the point again on canvas

            Assert.assertEquals(graphing.getPointPlaced_previewPage().getAttribute("fill"),"#2B85C0","Student is not able to draw again after reset");

            graphing.getDelete_studentAttempt().click();//Click on delete button
            graphing.getPointPlaced_previewPage().click();//Click on point to delete the point

            //Expected - Student can select the object using mouse and delete the objects
            boolean pointFoundAfterDelete = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//*[@fill='#2B85C0']"));
            Assert.assertEquals(pointFoundAfterDelete,false,"Student is not able to delete the point as point is still appearing after clicking on delete");

            graphing.getDelete_studentAttempt().click();
            graphing.getPointPlaced_answerLayer().click();//Click on the point placed by instructor on question layer to delete

            //Expected - Student should not be able to alter anything on the question layer or graph canvas
            boolean questionPointFoundAfterDelete = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//*[@fill='#000000']"));
            Assert.assertEquals(questionPointFoundAfterDelete,true,"Student is able to delete question point placed by instrutor");

            boolean scratchpadFound = false;
            try{
                driver.findElement(By.id("show-your-work-label"));//Search for scratchpad
                scratchpadFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - Scratchpad should not appear if instructor has not checked the scratchpad checkbox
            Assert.assertEquals(scratchpadFound,false,"Scratchpad is displayed even if instructor has not checked the checkbox");


        }catch (Exception e){
            Assert.fail("Exception in testcase 'studentQuestionAttempt' in class ' Graphing '", e);

        }
    }


    @Test(priority = 15,enabled = true)
    public void commonControl(){
        try{
            String appendChar = "a";
            int dataIndex = 1738;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//SignUp as a student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));

            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext+"1");//Enter the question

            graphing.getZoomIn_pixel().click();//Click on plus to increase grid size
            graphing.getEditbox_unitDistance().clear();
            graphing.getEditbox_unitDistance().sendKeys("2");//Enter unit distance value as 2

            Select sel = new Select(graphing.getDrawingPromptCombo());
            sel.selectByValue("Using drawing tools");

            graphing.getCheckbox_showGrid().click();//Uncheck show grid checkbox

            graphing.getCheckbox_scratchpad().click();//Check the scratchpad checkbox

            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool
            graphing.getGridArea().click();//Click on grid area

            graphing.getAnswerLayer().click();//Click on answer layer
            graphing.getGridArea().click();//Place the point on graph
            graphing.getCheckbox_segmentTool().click();//Click the checkbox for segment tool

            graphing.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[starts-with(@class,'btn btn-rounded btn-default lsm-createAssignment-done m-r-md selected')]")));
            graphing.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getAssignment().click();//Select the assignment

            String str = new RandomString().randomstring(5);

            //Expected - Writeboard if enabled by teacher then it should be available for student to use
            new WriteBoard().enterTextInWriteBoard(str,dataIndex);


          /*  boolean dataSaved =new WriteBoard().verifyWriteBoardDataIsSavedForNumberLine(str);
            Assert.assertEquals(dataSaved,true,"WriteBoard data is not saved");

            assessments.getShowOrHideYourWork().click();//Click on Show your work

            new WriteBoard().deleteWriteBoardData();
            boolean dataDeleted = new WriteBoard().verifyWriteBoardDataIsDeletedForNumberLine(str);
            Assert.assertEquals(dataDeleted,true,"WriteBoard data is not deleted");*/

            //Expected - Grid should not be shown if the option “Show Grid” is unchecked
            boolean gridFound = new BooleanValue().presenceOfElement(dataIndex,By.className("div[class='jsx-graph-bottom jxgbox jsx-graph-canvas']"));
            Assert.assertEquals(gridFound,false,"Grid is shown even if the option 'Show Grid' is unchecked");

            //Expected - Student should see the tools which are allowed by teacher  using enable graphing options
            Assert.assertEquals(graphing.getList_toolsInGrid().get(0).getAttribute("title"),"point","Point tool is not displayed as enabled by instructor");
            Assert.assertEquals(graphing.getList_toolsInGrid().get(1).getAttribute("title"),"line","Line tool is not displayed as enabled by instructor");
            Assert.assertEquals(graphing.getList_toolsInGrid().get(2).getAttribute("title"),"segment","Line tool is not displayed as enabled by instructor");

            if(graphing.getList_toolsInGrid().size()!=3+1){
                Assert.fail("Three tools enabled by instructor are not displayed");
            }

        }catch (Exception e){
            Assert.fail("Exception in testcase 'commonControl' in class ' Graphing '", e);

        }
    }


    @Test(priority = 16,enabled = true)
    public void studentAttemptShowLabelsUnchecked(){
        try{
            String appendChar = "a1";
            int dataIndex = 1864;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//SignUp as a student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext);//Enter the question

            graphing.getZoomIn_pixel().click();//Click on plus to increase grid size
            graphing.getEditbox_unitDistance().clear();
            graphing.getEditbox_unitDistance().sendKeys("2");//Enter unit distance value as 2

            Select sel = new Select(graphing.getDrawingPromptCombo());
            sel.selectByValue("Using drawing tools");

            graphing.getCheckbox_showLabels().click();//Uncheck Show Labels checkbox

            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool
            graphing.getGridArea().click();//Click on grid area

            graphing.getAnswerLayer().click();//Click on answer layer
            graphing.getGridArea().click();//Place the point on graph

            graphing.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[starts-with(@class,'btn btn-rounded btn-default lsm-createAssignment-done m-r-md selected')]")));
            graphing.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getAssignment().click();//Select the assignment

            //Expected -  When student clicks on assessment ,the corresponding question will be shown
            Assert.assertEquals(previewPage.getQuestionText().getText(),questiontext,"Question assigned by instructor is not displayed");

            //Expected - If the option “Show Labels” is unchecked, then axis labels should not be displayed
            Assert.assertEquals(graphing.getLabelsInGrid().get(0).isDisplayed(),false,"Labels are shown even if 'Show labels was unchecked on instructor side'");
            Assert.assertEquals(graphing.getLabelsInGrid().get(1).isDisplayed(),false,"Labels are shown even if 'Show labels was unchecked on instructor side'");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'studentAttemptShowLabelsUnchecked' in class ' Graphing '", e);

        }

    }


    @Test(priority = 17,enabled = true)
    public void evaluationGradable(){
        try{
            String appendChar = "a";
            int dataIndex = 1960;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver,AssessmentResponses.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//SignUp as a student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext);//Enter the question

            graphing.getZoomIn_pixel().click();//Click on plus to increase grid size
            graphing.getEditbox_unitDistance().clear();
            graphing.getEditbox_unitDistance().sendKeys("2");//Enter unit distance value as 2

            Select sel = new Select(graphing.getDrawingPromptCombo());
            sel.selectByValue("Using drawing tools");

            graphing.getList_toolsInGrid().get(2).click();//Click on segment tool

            //Place a segment
            graphing.getLabelsInGrid().get(23).click();
            graphing.getLabelsInGrid().get(21).click();

            graphing.getAnswerLayer().click();//Click on answer layer

            graphing.getCheckbox_segmentTool().click();//Check the checkbox for segment tool
            graphing.getList_toolsInGrid().get(2).click();//Click on segment tool in the grid

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",graphing.getCheckbox_scratchpad());

            //Place a segment on answer layer
            graphing.getLabelsInGrid().get(18).click();
            graphing.getLabelsInGrid().get(16).click();

            //Place another segment on answer layer
            graphing.getLabelsInGrid().get(23).click();
            graphing.getLabelsInGrid().get(21).click();

            graphing.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[starts-with(@class,'btn btn-rounded btn-default lsm-createAssignment-done m-r-md selected')]")));
            graphing.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            Thread.sleep(2000);
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getAssignment().click();//Select the assignment

            graphing.getList_toolsInGrid().get(2).click();//Select segment tool

            //Draw the segment
            graphing.getLabelsInGrid().get(18).click();
            graphing.getLabelsInGrid().get(16).click();

            //Draw another segment
            graphing.getLabelsInGrid().get(15).click();
            graphing.getLabelsInGrid().get(13).click();

            assessments.getButton_submit().click();//Click on submit button
            assessments.getFinal_submit().click();//Click on submit button

            assessments.getButton_continue().click();//Click on continue button

            //Expected - If default point is set, then student should get one point for every correct object
            //Expected -  Student should  get 0 point for every incorrect object
            Assert.assertEquals(assessments.getScore().getAttribute("value"),"0.5","Score is not 0.5 as expected");

            new Navigator().logout();//Log out

            //Instructor Gradebook View
            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment

            assessments.getView_grades().click();//click on view grades
            assessmentResponses.getResponse_question().click();//Click on score assigned to student

            //Expected - It should show the objects in green color which are correctly answered by the student.
            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(0).getTagName(),"line","Correct answer is not displayed in green color");

            for(int i=1;i<graphing.getList_pointPlaced_answerLayer_green().size();i++){
                if(graphing.getList_pointPlaced_answerLayer_green().get(i).equals("line")){
                    Assert.fail("More than one green line is present even if there is only one correct answer given by student");
                }
            }

            //Expected - It should show the objects in red color which are wrongly answered by the student.
            Assert.assertEquals(graphing.getLine_redColor().get(0).getTagName(),"line","Wrong answer is not displayed in red color");

            for(int i=1;i<graphing.getLine_redColor().size();i++){
                if(graphing.getLine_redColor().get(i).equals("line")){
                    Assert.fail("More than one red line is present even if there is only one wrong answer given by student");
                }
            }

            //Expected -  The grades should be displayed on the right side top corner
            Assert.assertEquals(assessments.getScore().getAttribute("value"),"0.5","Score is not displayed");

            assessments.getLink_showCorrectAnswer().click();//Click on show correct answer link

            //Expected -  Instructor should  see the correct answer
            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(1).getTagName(),"line","Correct answer is not displayed in green color");

            for(int i=2;i<graphing.getList_pointPlaced_answerLayer_green().size();i++){
                if(graphing.getList_pointPlaced_answerLayer_green().get(i).equals("line")){
                    Assert.fail("More than two green line is present even if there is only two correct answer given by instructor");
                }
            }

            assessments.getLink_showYourAnswer().click();//Click on show your answer link

            //Expected - Instructor should  see the  answers marked by the student
            if(graphing.getList_pointPlaced_answerLayer_green().size()!=3){
                Assert.fail("one line is not displayed as attempted by student");
            }

            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(0).getTagName(),"line","Answer is not displayed as given by student");

            for(int i=1;i<graphing.getList_pointPlaced_answerLayer_green().size();i++){
                if(graphing.getList_pointPlaced_answerLayer_green().get(i).equals("line")){
                    Assert.fail("More than one green line is present even if there is only one correct answer given by student");
                }
            }

            new Navigator().logout();//Log out

            //Student Report View
            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getAssignment().click();//Select the assignment
            assessments.getBarGraph_question().click();//Click on bar graph

            //Expected - 1 : The question should be displayed along with the answers
            //Expected - 2 : The values drawn by students should be visible on the canvas
            //Expected - 3 : The right answers should have a green border and background
            //Expected - 4 : Incorrect answer should have red border and background
            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer().get(0).getTagName(),"line","question is not displayed");
            Assert.assertEquals(graphing.getLine_redColor().get(0).getTagName(),"line","Incorrect answer is not dsiplayed in red color");
            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(0).getTagName(),"line","Correct answer is nt displayed in green color");

            for(int i=1;i<graphing.getList_pointPlaced_answerLayer().size();i++){
                  if(graphing.getList_pointPlaced_answerLayer().get(i).equals("line")){
                      Assert.fail("More than one question line is displayed even if there is only one question line is drawn by instructor");
                  }
            }

            for(int i=1;i<graphing.getLine_redColor().size();i++){
                if(graphing.getLine_redColor().get(i).equals("line")){
                    Assert.fail("More than one red line is displayed even if there is only one wrong line is drawn by student");
                }
            }

            for(int i=1;i<graphing.getList_pointPlaced_answerLayer_green().size();i++){
                if(graphing.getList_pointPlaced_answerLayer_green().get(i).equals("line")){
                    Assert.fail("More than one green line is displayed even if there is only one correct line is drawn by student");
                }
            }

            assessments.getLink_showCorrectAnswer().click();//Click on show correct answer link

            //Expected -  Student should  see the correct answer
            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(1).getTagName(),"line","Correct answer is not displayed in green color");

            for(int i=2;i<graphing.getList_pointPlaced_answerLayer_green().size();i++){
                if(graphing.getList_pointPlaced_answerLayer_green().get(i).equals("line")){
                    Assert.fail("More than two green line is present even if there is only two correct answer given by instructor");
                }
            }

            assessments.getLink_showYourAnswer().click();//Click on show your answer link

            //Expected - Student should  see the  answers marked by him
            if(graphing.getList_pointPlaced_answerLayer_green().size()!=3){
                Assert.fail("one line is not displayed as attempted by student");
            }

            Assert.assertEquals(graphing.getList_pointPlaced_answerLayer_green().get(0).getTagName(),"line","Answer is not displayed as given by student");

            for(int i=1;i<graphing.getList_pointPlaced_answerLayer_green().size();i++){
                if(graphing.getList_pointPlaced_answerLayer_green().get(i).equals("line")){
                    Assert.fail("More than one green line is present even if there is only one correct answer given by student");
                }
            }

        }catch (Exception e){
            Assert.fail("Exception in testcase 'evaluationGradable' in class ' Graphing '", e);

        }
    }


    @Test(priority = 18,enabled = true)
    public void evaluationNonGradable(){
        try{
            String appendChar = "a";
            int dataIndex = 2183;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver,AssessmentResponses.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//SignUp as a student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));

            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext);//Enter the question

            graphing.getZoomIn_pixel().click();//Click on plus to increase grid size
            graphing.getEditbox_unitDistance().clear();
            graphing.getEditbox_unitDistance().sendKeys("2");//Enter unit distance value as 2

            Select sel = new Select(graphing.getDrawingPromptCombo());
            sel.selectByValue("Using drawing tools");

            graphing.getList_toolsInGrid().get(2).click();//Click on segment tool

            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");            //Place a segment
            graphing.getLabelsInGrid().get(23).click();
            graphing.getLabelsInGrid().get(21).click();

            graphing.getAnswerLayer().click();//Click on answer layer

            graphing.getCheckbox_segmentTool().click();//Check the checkbox for segment tool
            graphing.getList_toolsInGrid().get(2).click();//Click on segment tool in the grid

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",graphing.getCheckbox_scratchpad());

            //Place a segment on answer layer
            graphing.getLabelsInGrid().get(18).click();
            graphing.getLabelsInGrid().get(16).click();

            //Place another segment on answer layer
            graphing.getLabelsInGrid().get(23).click();
            graphing.getLabelsInGrid().get(21).click();

            graphing.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[starts-with(@class,'btn btn-rounded btn-default lsm-createAssignment-done m-r-md selected')]")));
            graphing.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getAssignment().click();//Select the assignment

            graphing.getList_toolsInGrid().get(2).click();//Select segment tool

            //Draw the segment
            graphing.getLabelsInGrid().get(18).click();
            graphing.getLabelsInGrid().get(16).click();

            //Draw another segment
            graphing.getLabelsInGrid().get(15).click();
            graphing.getLabelsInGrid().get(13).click();

            assessments.getButton_submit().click();//Click on submit button
            assessments.getFinal_submit().click();//Click on submit button

            assessments.getButton_continue().click();//Click on continue button

           //Scoreboard should not be displayed if the assessment is non gradable
            boolean scoreBoardFound = new BooleanValue().presenceOfElement(dataIndex,By.className("question-performance-score"));
            Assert.assertEquals(scoreBoardFound,false,"Scoreboard is displayed even if the assessment is not gradable");

            new Navigator().logout();//Log out

            //Instructor Gradebook View
            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment

            assessments.getView_grades().click();//click on view grades
            assessmentResponses.getResponse_question().click();//Click on score assigned to student

            //Expected - ScoreBoard should not be displayed if the assessment is non gradable(Scoreboard should display as per e10)
            boolean scoreBoardFoundInstructorSide = new BooleanValue().presenceOfElement(dataIndex,By.className("question-performance-score"));
            Assert.assertEquals(scoreBoardFoundInstructorSide,true,"Scoreboard is not displayed ");


        }catch (Exception e){
            Assert.fail("Exception in testcase 'evaluationNonGradable' in class ' Graphing '", e);

        }
    }


    @Test(priority = 19,enabled = true)
    public void evaluationWithDistractors(){
        try{
            String appendChar = "a";
            int dataIndex = 2305;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver,AssessmentResponses.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//SignUp as a student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create button

            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_Graphing().click();//Click on graphing question type
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            graphing.getTextBox_QuestionField().click();
            graphing.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext);//Enter the question

            graphing.getZoomIn_pixel().click();//Click on plus to increase grid size
            graphing.getEditbox_unitDistance().clear();
            graphing.getEditbox_unitDistance().sendKeys("2");//Enter unit distance value as 2

            Select sel = new Select(graphing.getDrawingPromptCombo());
            sel.selectByValue("Using drawing tools");

            graphing.getList_toolsInGrid().get(2).click();//Click on segment tool

            //Place a segment
            graphing.getLabelsInGrid().get(23).click();
            graphing.getLabelsInGrid().get(21).click();

            graphing.getAnswerLayer().click();//Click on answer layer

            graphing.getCheckbox_segmentTool().click();//Check the checkbox for segment tool
            graphing.getList_toolsInGrid().get(2).click();//Click on segment tool in the grid

           ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",graphing.getCheckbox_scratchpad());

            //Place a segment on answer layer
            graphing.getLabelsInGrid().get(18).click();
            graphing.getLabelsInGrid().get(16).click();

            //Place another segment on answer layer
            graphing.getLabelsInGrid().get(23).click();
            graphing.getLabelsInGrid().get(21).click();

            graphing.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[starts-with(@class,'btn btn-rounded btn-default lsm-createAssignment-done m-r-md selected')]")));
            graphing.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getAssignment().click();//Select the assignment

            graphing.getList_toolsInGrid().get(2).click();//Select segment tool

            //Draw the segment
            graphing.getLabelsInGrid().get(18).click();
            graphing.getLabelsInGrid().get(16).click();

            //Draw another segment
            graphing.getLabelsInGrid().get(15).click();
            graphing.getLabelsInGrid().get(13).click();

            graphing.getList_toolsInGrid().get(0).click();//Click on point tool

            graphing.getLabelsInGrid().get(6).click();//Place the point on the graph

            assessments.getButton_submit().click();//Click on submit button
            assessments.getFinal_submit().click();//Click on submit button

            assessments.getButton_continue().click();//Click on continue button

            //Expected - Student should get the correct score as per the formula= (#of correct object/total no of object by teacher+#of distractors)*Point for the question
            Assert.assertEquals(assessments.getScore().getAttribute("value"),"0.33","Score is not 0.33 as expected");

            new Navigator().logout();//Log out

            //Instructor Gradebook View
            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment

            assessments.getView_grades().click();//click on view grades
            assessmentResponses.getResponse_question().click();//Click on score assigned to student

            //Expected - Student should get the correct score as per the formula= (#of correct object/total no of object by teacher+#of distractors)*Point for the question
            Assert.assertEquals(assessments.getScore().getAttribute("value"),"0.33","Score is not 0.33 as expected");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'evaluationWithDistractors' in class ' Graphing '", e);

        }
    }





     public void createPassageBasedQuestion(int dataIndex){
         try
         {
             String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
             CreateAssignment createAssessment = PageFactory.initElements(driver,CreateAssignment.class);

             createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
             new Click().clickByid("qtn-passage-type");//click on passage based question
             new TextSend().textsendbyid("Header pf the Paragraph", "passage_title");
             String passage = new RandomString().randomstring(25);
             new TextSend().textsendbyid(passage, "question-edit-passage-text");
             new Click().clickByid("saveQuestionDetails1");//click on save button
             Thread.sleep(2000);
             new Click().clickByclassname("add-question-text");//click on Add a New Question for this passage
             Thread.sleep(2000);
             new Click().clickByXpath("(//span[@data-skillidentifier='1.OA.A.1'])[3]");//Click on create
             new Click().clickByid("qtn-true-false-type");//click on True false type question
             new Click().clickByid("question-raw-content");//click on Question
             driver.findElement(By.id("question-raw-content")).sendKeys("True False "+questiontext);//type the question
             new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option A

             new Click().clickByid("saveQuestionDetails1");//click on save button
         }
         catch (Exception e) {
             Assert.fail("Exception while passage based question type",e);
         }

     }

    public void enterMathmlValue(String operation, String value)
    {
        try {

            driver.findElement(By.cssSelector("button[title='" + operation + "']")).click();
            driver.findElement(By.className("wrs_focusElement")).sendKeys(value);
        }
        catch (Exception e){
            Assert.fail("Exception while entering value in Math ML editor", e);
        }
    }

        public void imageUpload(String dataIndex)
        {
            try{
                GraphingQuestionCreation graphingQuestionCreation = PageFactory.initElements(driver,GraphingQuestionCreation.class);

                String filename = ReadTestData.readDataByTagName("", "filename",dataIndex);
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();", graphingQuestionCreation.getButton_selectFile());
                new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
                Thread.sleep(4000);
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();", graphingQuestionCreation.getButton_uploadImage());
                Thread.sleep(3000);
            }catch (Exception e)
            {
                Assert.fail("Exception while uploaing a file",e);
            }
        }



}
