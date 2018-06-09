package com.snapwiz.selenium.tests.staf.edulastic.apphelpers;

import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.*;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.*;
import gherkin.lexer.Th;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/*
 * Created by Sumit on 9/4/2014.
 */
public class Assignment extends Driver {

    /*
    Author Sumit
    createWithOnlyName assignment
     */
    public void create(int dataIndex, String questionType) {
        try {
            WebDriver driver=Driver.getWebDriver();
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String daassessmentname = ReadTestData.readDataByTagName("", "daassessmentname", Integer.toString(dataIndex));
            String elo=ReadTestData.readDataByTagName("", "elo", Integer.toString(dataIndex));
            String createthrough=ReadTestData.readDataByTagName("", "createthrough", Integer.toString(dataIndex));

            if(daassessmentname!=null) {
                if (daassessmentname.equals("true")) {
                    new Navigator().navigateTo("districtAssessmentsView");//navigate to the assessments
                }
            }
            else
            {
                if(createthrough!=null){
                    if(createthrough.equals("dashboard")) {

                        new Navigator().navigateTo("dashboard");    //go to dashboard
                    }
                    else if(createthrough.equals("myAssessments")) {
                        new Navigator().navigateTo("myAssessments");    //go to My assessment
                    }
                }

                else {
                    new Navigator().navigateTo("assignment");    //go to Assignments
                }
            }

            if(createthrough!=null)
            {
                if(createthrough.equals("dashboard"))
                {
                    new Click().clickByXpath("(//a[starts-with(@class,'btn btn-blue btn-rounded')])[2]");//click on Create New Assignment button

                }
                else{
                    new Click().clickByXpath("//a[starts-with(@class,'btn btn-blue btn-rounded')]");//click on Create New Assignment button

                }
            }
            else{

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();" , driver.findElement(By.xpath("//a[starts-with(@class,'btn btn-blue btn-rounded')]")));//click on Create New Assignment button
            }

            driver.findElement(By.id("new-assessment-name")).clear();
            driver.findElement(By.id("new-assessment-name")).sendKeys(assessmentname);//give the assignment title
            new Click().clickByid("new-assessment-name");
            new Click().clickByXpath("//html/body");
            Thread.sleep(3000);
            new Click().clickByid("create-assessment-with-val");//click on Create
            new Click().clickBycssselector("button[class^='btn btn-rounded btn-default authorQuestion']");//Click on Author Question


            if(questionType.equals("all"))
            {
                new QuestionCreate().trueFalseQuestions(dataIndex);
                new QuestionCreate().multipleChoice(dataIndex);
                new QuestionCreate().multipleSelection(dataIndex);
                new QuestionCreate().textEntry(dataIndex);
                new QuestionCreate().textDropDown(dataIndex);
                new QuestionCreate().essay(dataIndex);
                new QuestionCreate().numericEntryWithUnits(dataIndex);
                new QuestionCreate().numeric(dataIndex);
                new QuestionCreate().expressionEvaluator(dataIndex);
                new QuestionCreate().dragAndDrop(dataIndex);
                new QuestionCreate().labelAnImageText(dataIndex);
                new QuestionCreate().labelAnImageDropdown(dataIndex);
                new QuestionCreate().resequence(dataIndex);
                new QuestionCreate().clozeMatrix(dataIndex);
                new QuestionCreate().graphPlotter(dataIndex);
                new QuestionCreate().clozeFormula(dataIndex);
                new QuestionCreate().numberline(dataIndex);
                new QuestionCreate().linePlot(dataIndex);
                new QuestionCreate().graphing(dataIndex);
                new QuestionCreate().rangePlotter(dataIndex);
                new QuestionCreate().fractionEditor(dataIndex);
                new QuestionCreate().matchTheFollowing(dataIndex);
                new QuestionCreate().classification(dataIndex);
                new QuestionCreate().passageBasedQuestions(dataIndex);
                new QuestionCreate().matchingTables(dataIndex);
                new QuestionCreate().sentenceResponse(dataIndex);
                new QuestionCreate().sentencecorrection(dataIndex);
                new QuestionCreate().graphPlacement(dataIndex);
                new QuestionCreate().pictograph(dataIndex);
                new QuestionCreate().multipart(dataIndex,"multipleChoiceAndSelectionAndEssay");
                new QuestionCreate().multipart(dataIndex,"textEntryAndDropdown");
                new QuestionCreate().multipart(dataIndex,"numeric");


            }
            if (questionType.equals("truefalse"))
                new QuestionCreate().trueFalseQuestions(dataIndex);

            if (questionType.equals("multiplechoice"))
                new QuestionCreate().multipleChoice(dataIndex);

            if(questionType.equals("multipleselection"))
                new QuestionCreate().multipleSelection(dataIndex);

            if(questionType.equals("textentry"))
                new QuestionCreate().textEntry(dataIndex);

            if(questionType.equals("textdropdown"))
                new QuestionCreate().textDropDown(dataIndex);

            if(questionType.equals("numericentrywithunits"))
                new QuestionCreate().numericEntryWithUnits(dataIndex);

            if(questionType.equals("numeric"))
                new QuestionCreate().numeric(dataIndex);

            if(questionType.equals("expressionevaluator"))
                new QuestionCreate().expressionEvaluator(dataIndex);

            if(questionType.equals("matchthefollowing"))
                new QuestionCreate().matchTheFollowing(dataIndex);

            if(questionType.equals("draganddrop"))
                new QuestionCreate().dragAndDrop(dataIndex);

            if(questionType.equals("clozeformula"))
                new QuestionCreate().clozeFormula(dataIndex);

            if(questionType.equals("graphplotter"))
                new QuestionCreate().graphPlotter(dataIndex);

            if(questionType.equals("clozematrix"))
                new QuestionCreate().clozeMatrix(dataIndex);

            if(questionType.equals("resequence"))
                new QuestionCreate().resequence(dataIndex);

            if(questionType.equals("essay"))
                new QuestionCreate().essay(dataIndex);

            if(questionType.equals("labelanimagetext"))
                new QuestionCreate().labelAnImageText(dataIndex);

            if(questionType.equals("labelanimagedropdown"))
                new QuestionCreate().labelAnImageDropdown(dataIndex);

            if(questionType.equals("numberline"))
                new QuestionCreate().numberline(dataIndex);

            if(questionType.equals("classification"))
                new QuestionCreate().classification(dataIndex);

            if (questionType.equals("sentenceresponse"))
                new QuestionCreate().sentenceResponse(dataIndex);

            if (questionType.equals("matchingtables"))
                new QuestionCreate().matchingTables(dataIndex);

            if (questionType.equals("passage"))
                new QuestionCreate().passageBasedQuestions(dataIndex);

            if (questionType.equals("lineplot"))
                new QuestionCreate().linePlot(dataIndex);

            if (questionType.equals("rangeplotter"))
                new QuestionCreate().rangePlotter(dataIndex);

            if (questionType.equals("fractioneditor"))
                new QuestionCreate().fractionEditor(dataIndex);

            if  (questionType.equals("graphing"))
                new QuestionCreate().graphing(dataIndex);

            if(questionType.equals("graphplacement"))
                new QuestionCreate().graphPlacement(dataIndex);

            if(questionType.equals("pictograph"))
                new QuestionCreate().pictograph(dataIndex);

            if(questionType.equals("sentencecorrection"))
                new QuestionCreate().sentencecorrection(dataIndex);

            if(questionType.equals("multipart"))
                new QuestionCreate().multipart(dataIndex,"multipleChoiceAndSelectionAndEssay");//Essay will be added only if grade release option is explicitly by teacher

            WebDriverUtil.waitForAjax(driver,60);

        } catch (Exception e) {

            Assert.fail("Exception in app helper Assignment in method createWithOnlyName.", e);
        }
    }
    /*
    @Author Sumit
    Add question to a existing Assessment
     */
    public String addQuestion(int dataIndex, String questionType) {
        String rubricName=null;
        try {
            WebDriver driver=Driver.getWebDriver();
            String daassessmentname = ReadTestData.readDataByTagName("", "daassessmentname", Integer.toString(dataIndex));
            String elo=ReadTestData.readDataByTagName("", "elo", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String adQuestionAfterAssign = ReadTestData.readDataByTagName("", "adquestionafterassign", Integer.toString(dataIndex));

            MyAssessments myAssessments=null;
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);

            WebDriverUtil.waitForAjax(driver,60);
//            Thread.sleep(9000);
            if(!getWebDriver().getCurrentUrl().contains("#addQuestion/close")) {
                if (daassessmentname != null) {
                    if (daassessmentname.equals("true")) {
                        new Navigator().navigateTo("districtAssessmentsView");//navigate to the assessments
                        int index = 0;
                        List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='col-xs-8 col-sm-8 col-md-6 col-lg-8 p-l-none ad-dist_title'] >h3"));//WILL BE chenge on E9
                        for (WebElement assessment : allAssessment) {
                            if (assessment.getText().equals(assessmentname)) {
                                break;
                            } else
                                index++;
                        }
                        new Click().clickbylistcssselector("span.as-edit", index);//click on Assignment
                    }
                } else {
                    new Navigator().navigateTo("myAssessments");//Navigate to my assessment page
                    WebDriverUtil.waitForAjax(driver, 30);
                    myAssessments = PageFactory.initElements(driver, MyAssessments.class);
                    if (adQuestionAfterAssign != null) {
                        if (adQuestionAfterAssign.equals("true")) {
                            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");//Click on published

                        }
                    } else {
                        new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");//Click on Draft
                    }


                    WebDriverUtil.waitForAjax(driver, 60);
                    if (myAssessments.assignmentList.size() == 0) {
                        driver.navigate().refresh();
                        Thread.sleep(5000);
                        myAssessments.draft.click();//Click on Draft
                        WebDriverUtil.waitForAjax(driver, 60);
                        if (myAssessments.assignmentList.size() == 0) {
                            driver.findElement(By.xpath("//input[@id='published']/..")).click(); //click on the publish
                            WebDriverUtil.waitForAjax(driver, 60);
                            myAssessments.draft.click();//Click on Draft
                            WebDriverUtil.waitForAjax(driver, 60);
                        }
                    }

                    if (adQuestionAfterAssign == null) {
                        if (myAssessments.assignmentList.size() > 1) {
                            driver.navigate().refresh();
                            Thread.sleep(5000);
                            myAssessments.draft.click();//Click on Draft
                            WebDriverUtil.waitForAjax(driver, 60);
                            if (myAssessments.assignmentList.size() == 0) {
                                driver.findElement(By.xpath("//input[@id='published']/..")).click(); //click on the publish
                                WebDriverUtil.waitForAjax(driver, 60);
                                myAssessments.draft.click();//Click on Draft
                                WebDriverUtil.waitForAjax(driver, 60);
                            }
                        }
                    }

                    WebDriverUtil.waitForAjax(driver, 60);
                    int index = 0;
                    List<WebElement> allAssessment = myAssessments.assignmentList;//WILL BE chenge on E9
                    for (WebElement assessment : allAssessment) {
                        if (assessment.getText().equals(assessmentname)) {
                            break;
                        } else
                            index++;
                    }

                    WebDriverUtil.clickOnElementUsingJavascript(myAssessments.assignmentList.get(index));//click on Assignment
                    WebDriverUtil.waitForAjax(driver, 60);

                    new Click().clickByXpath("//i[@class='ed-icon icon-edit-assesment']/following-sibling::span");//Click on edit button

//                WebDriverUtil.waitForAjax(driver,60);

                    if (assessmentDetailsPage.confirmation_popUp.size() > 0) {
                        assessmentDetailsPage.YesButton_confirmationPopUp.click();//Click on yes on confirmation pop up
                    }

                }

                new Click().clickByid("assessments-back-button");//Click on Add question
            }
            new Click().clickBycssselector("button[class^='btn btn-rounded btn-default authorQuestion']");//Click on Author Question
            WebDriverUtil.waitForAjax(driver,60);

            if (questionType.equals("truefalse"))
                new QuestionCreate().trueFalseQuestions(dataIndex);

            if(questionType.equals("multiplechoice"))
                new QuestionCreate().multipleChoice(dataIndex);

            if(questionType.equals("multipleselection"))
                new QuestionCreate().multipleSelection(dataIndex);

            if(questionType.equals("textentry"))
                new QuestionCreate().textEntry(dataIndex);

            if(questionType.equals("textdropdown"))
                new QuestionCreate().textDropDown(dataIndex);

            if(questionType.equals("numericentrywithunits"))
                new QuestionCreate().numericEntryWithUnits(dataIndex);

            if(questionType.equals("numeric"))
                new QuestionCreate().numeric(dataIndex);

            if(questionType.equals("expressionevaluator"))
                new QuestionCreate().expressionEvaluator(dataIndex);

            if(questionType.equals("matchthefollowing"))
                new QuestionCreate().matchTheFollowing(dataIndex);

            if(questionType.equals("draganddrop"))
                new QuestionCreate().dragAndDrop(dataIndex);

            if(questionType.equals("clozeformula"))
                new QuestionCreate().clozeFormula(dataIndex);

            if(questionType.equals("graphplotter"))
                new QuestionCreate().graphPlotter(dataIndex);

            if(questionType.equals("clozematrix"))
                new QuestionCreate().clozeMatrix(dataIndex);

            if(questionType.equals("resequence"))
                new QuestionCreate().resequence(dataIndex);

            if(questionType.equals("essay"))
                rubricName= new QuestionCreate().essay(dataIndex);

            if(questionType.equals("labelanimagetext"))
                new QuestionCreate().labelAnImageText(dataIndex);

            if(questionType.equals("labelanimagedropdown"))
                new QuestionCreate().labelAnImageDropdown(dataIndex);

            if(questionType.equals("numberline"))
                new QuestionCreate().numberline(dataIndex);

            if(questionType.equals("classification"))
                new QuestionCreate().classification(dataIndex);

            if (questionType.equals("sentenceresponse"))
                new QuestionCreate().sentenceResponse(dataIndex);

            if (questionType.equals("matchingtables"))
                new QuestionCreate().matchingTables(dataIndex);

            if (questionType.equals("passage"))
                rubricName = new QuestionCreate().passageBasedQuestions(dataIndex);

            if (questionType.equals("lineplot"))
                new QuestionCreate().linePlot(dataIndex);

            if (questionType.equals("rangeplotter"))
                new QuestionCreate().rangePlotter(dataIndex);

            if (questionType.equals("fractioneditor"))
                new QuestionCreate().fractionEditor(dataIndex);

            if  (questionType.equals("graphing"))
                new QuestionCreate().graphing(dataIndex);

            if(questionType.equals("graphplacement"))
                new QuestionCreate().graphPlacement(dataIndex);

            if(questionType.equals("pictograph"))
                new QuestionCreate().pictograph(dataIndex);

            if(questionType.equals("sentencecorrection"))
                new QuestionCreate().sentencecorrection(dataIndex);

            if(questionType.equals("multipart"))
                new QuestionCreate().multipart(dataIndex,"multipleChoiceAndSelectionAndEssay");


        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method createWithOnlyName.", e);
        }
        return rubricName;
    }




    public void create(int dataIndex, String questionType,String assessmentname) {
        try {
            WebDriver driver=Driver.getWebDriver();

            String daassessmentname = ReadTestData.readDataByTagName("", "daassessmentname", Integer.toString(dataIndex));
            String elo=ReadTestData.readDataByTagName("", "elo", Integer.toString(dataIndex));

            if(daassessmentname!=null) {
                if (daassessmentname.equals("true")) {
                    new Navigator().navigateTo("districtAssessments");//navigate to the assessments
                }
            }
            else
            {
                new Navigator().navigateTo("assignment");    //go to Assignments
            }

            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on Create New Assignment button
            driver.findElement(By.id("new-assessment-name")).clear();
            driver.findElement(By.id("new-assessment-name")).sendKeys(assessmentname);//give the assignment title
            new Click().clickByid("new-assessment-name");
            new Click().clickByXpath("//html/body");
            new Click().clickByid("create-assessment-with-val");//click on Create
            if(elo==null) {
                new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//click on Create link
            }
            else
            {
                List<WebElement> elolist=driver.findElements(By.cssSelector("span.lsm-tlo-heading"));
                Iterator<WebElement> itr=elolist.iterator();
                int count=0;
                while(itr.hasNext())
                {
                    if(itr.next().getText().contains(elo)) {
                        driver.findElements(By.cssSelector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']")).get(count).click();
                        break;
                    }
                    count++;
                }
            }


            if(questionType.equals("all"))
            {
                new QuestionCreate().trueFalseQuestions(dataIndex);
                new QuestionCreate().multipleChoice(dataIndex);
                new QuestionCreate().multipleSelection(dataIndex);
                new QuestionCreate().textEntry(dataIndex);
                new QuestionCreate().textDropDown(dataIndex);
                new QuestionCreate().essay(dataIndex);
                new QuestionCreate().numericEntryWithUnits(dataIndex);
                new QuestionCreate().numeric(dataIndex);
                new QuestionCreate().expressionEvaluator(dataIndex);
                new QuestionCreate().dragAndDrop(dataIndex);
                new QuestionCreate().labelAnImageText(dataIndex);
                new QuestionCreate().labelAnImageDropdown(dataIndex);
                new QuestionCreate().resequence(dataIndex);
                new QuestionCreate().clozeMatrix(dataIndex);
                new QuestionCreate().graphPlotter(dataIndex);
                new QuestionCreate().clozeFormula(dataIndex);
                new QuestionCreate().numberline(dataIndex);
                new QuestionCreate().linePlot(dataIndex);
                new QuestionCreate().graphing(dataIndex);
                new QuestionCreate().rangePlotter(dataIndex);
                new QuestionCreate().fractionEditor(dataIndex);
                new QuestionCreate().matchTheFollowing(dataIndex);
                new QuestionCreate().classification(dataIndex);
                new QuestionCreate().passageBasedQuestions(dataIndex);
                new QuestionCreate().matchingTables(dataIndex);
                new QuestionCreate().sentenceResponse(dataIndex);
                new QuestionCreate().sentencecorrection(dataIndex);
                new QuestionCreate().graphPlacement(dataIndex);
                new QuestionCreate().pictograph(dataIndex);
                new QuestionCreate().multipart(dataIndex,"MultipleChoiceAndSelection");
                new QuestionCreate().multipart(dataIndex,"textEntryAndDropdown");
                new QuestionCreate().multipart(dataIndex,"advanceNumeric");

            }
            if (questionType.equals("truefalse"))
                new QuestionCreate().trueFalseQuestions(dataIndex);

            if (questionType.equals("multiplechoice"))
                new QuestionCreate().multipleChoice(dataIndex);

            if(questionType.equals("multipleselection"))
                new QuestionCreate().multipleSelection(dataIndex);

            if(questionType.equals("textentry"))
                new QuestionCreate().textEntry(dataIndex);

            if(questionType.equals("textdropdown"))
                new QuestionCreate().textDropDown(dataIndex);

            if(questionType.equals("numericentrywithunits"))
                new QuestionCreate().numericEntryWithUnits(dataIndex);

            if(questionType.equals("numeric"))
                new QuestionCreate().numeric(dataIndex);

            if(questionType.equals("expressionevaluator"))
                new QuestionCreate().expressionEvaluator(dataIndex);

            if(questionType.equals("matchthefollowing"))
                new QuestionCreate().matchTheFollowing(dataIndex);

            if(questionType.equals("draganddrop"))
                new QuestionCreate().dragAndDrop(dataIndex);

            if(questionType.equals("clozeformula"))
                new QuestionCreate().clozeFormula(dataIndex);

            if(questionType.equals("graphplotter"))
                new QuestionCreate().graphPlotter(dataIndex);

            if(questionType.equals("clozematrix"))
                new QuestionCreate().clozeMatrix(dataIndex);

            if(questionType.equals("resequence"))
                new QuestionCreate().resequence(dataIndex);

            if(questionType.equals("essay"))
                new QuestionCreate().essay(dataIndex);

            if(questionType.equals("labelanimagetext"))
                new QuestionCreate().labelAnImageText(dataIndex);

            if(questionType.equals("labelanimagedropdown"))
                new QuestionCreate().labelAnImageDropdown(dataIndex);

            if(questionType.equals("numberline"))
                new QuestionCreate().numberline(dataIndex);

            if(questionType.equals("classification"))
                new QuestionCreate().classification(dataIndex);

            if (questionType.equals("sentenceresponse"))
                new QuestionCreate().sentenceResponse(dataIndex);

            if (questionType.equals("matchingtables"))
                new QuestionCreate().matchingTables(dataIndex);

            if (questionType.equals("passage"))
                new QuestionCreate().passageBasedQuestions(dataIndex);

            if (questionType.equals("lineplot"))
                new QuestionCreate().linePlot(dataIndex);

            if (questionType.equals("rangeplotter"))
                new QuestionCreate().rangePlotter(dataIndex);

            if (questionType.equals("fractioneditor"))
                new QuestionCreate().fractionEditor(dataIndex);

            if  (questionType.equals("graphing"))
                new QuestionCreate().graphing(dataIndex);

            if(questionType.equals("graphplacement"))
                new QuestionCreate().graphPlacement(dataIndex);

            if(questionType.equals("pictograph"))
                new QuestionCreate().pictograph(dataIndex);

            if(questionType.equals("sentencecorrection"))
                new QuestionCreate().sentencecorrection(dataIndex);

            if(questionType.equals("multipart"))
                new QuestionCreate().multipart(dataIndex,"multipleChoiceAndSelection");

        } catch (Exception e) {

            Assert.fail("Exception in app helper Assignment in method createWithOnlyName.", e);
        }
    }
    /*
    @Author Sumit
    Add question to a existing Assessment
     */
    public void addQuestion(int dataIndex, String questionType,String assessmentname) {
        try {
            WebDriver driver=Driver.getWebDriver();
            String elo=ReadTestData.readDataByTagName("", "elo", Integer.toString(dataIndex));
            MyAssessments myAssessments=PageFactory.initElements(driver,MyAssessments.class);

            new Navigator().navigateTo("myAssessments");//Navigate to my assessment page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");//Click on Draft
            int index = 0;
            List<WebElement> allAssessment = myAssessments.assignmentList;//WILL BE chenge on E9
            for (WebElement assessment : allAssessment) {
                if (assessment.getText().equals(assessmentname)) {
                    break;
                } else
                    index++;
            }
            myAssessments.assignmentList.get(index).click();//click on Assignment
            new Click().clickByXpath("//i[@class='ed-icon icon-edit-assesment']");//Click on edit button
            new Click().clickByid("assessments-back-button");
            if(elo==null) {
                new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//click on Create link
            }
            else
            {
                List<WebElement> elolist=driver.findElements(By.cssSelector("span.lsm-tlo-heading"));
                Iterator<WebElement> itr=elolist.iterator();
                int count=0;
                while(itr.hasNext())
                {
                    if(itr.next().getText().contains(elo)) {
                        driver.findElements(By.cssSelector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']")).get(count).click();
                        break;
                    }
                    count++;
                }
            }
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys(assessmentname);//give the assignment name
            if (questionType.equals("truefalse"))
                new QuestionCreate().trueFalseQuestions(dataIndex);

            if (questionType.equals("multiplechoice"))
                new QuestionCreate().multipleChoice(dataIndex);

            if(questionType.equals("multipleselection"))
                new QuestionCreate().multipleSelection(dataIndex);

            if(questionType.equals("textentry"))
                new QuestionCreate().textEntry(dataIndex);

            if(questionType.equals("textdropdown"))
                new QuestionCreate().textDropDown(dataIndex);

            if(questionType.equals("numericentrywithunits"))
                new QuestionCreate().numericEntryWithUnits(dataIndex);

            if(questionType.equals("numeric"))
                new QuestionCreate().numeric(dataIndex);

            if(questionType.equals("expressionevaluator"))
                new QuestionCreate().expressionEvaluator(dataIndex);

            if(questionType.equals("matchthefollowing"))
                new QuestionCreate().matchTheFollowing(dataIndex);

            if(questionType.equals("draganddrop"))
                new QuestionCreate().dragAndDrop(dataIndex);

            if(questionType.equals("clozeformula"))
                new QuestionCreate().clozeFormula(dataIndex);

            if(questionType.equals("graphplotter"))
                new QuestionCreate().graphPlotter(dataIndex);

            if(questionType.equals("clozematrix"))
                new QuestionCreate().clozeMatrix(dataIndex);

            if(questionType.equals("resequence"))
                new QuestionCreate().resequence(dataIndex);

            if(questionType.equals("essay"))
                new QuestionCreate().essay(dataIndex);

            if(questionType.equals("labelanimagetext"))
                new QuestionCreate().labelAnImageText(dataIndex);

            if(questionType.equals("labelanimagedropdown"))
                new QuestionCreate().labelAnImageDropdown(dataIndex);

            if(questionType.equals("numberline"))
                new QuestionCreate().numberline(dataIndex);

            if(questionType.equals("classification"))
                new QuestionCreate().classification(dataIndex);

            if (questionType.equals("sentenceresponse"))
                new QuestionCreate().sentenceResponse(dataIndex);

            if (questionType.equals("matchingtables"))
                new QuestionCreate().matchingTables(dataIndex);

            if (questionType.equals("passage"))
                new QuestionCreate().passageBasedQuestions(dataIndex);

            if (questionType.equals("lineplot"))
                new QuestionCreate().linePlot(dataIndex);

            if (questionType.equals("rangeplotter"))
                new QuestionCreate().rangePlotter(dataIndex);

            if (questionType.equals("fractioneditor"))
                new QuestionCreate().fractionEditor(dataIndex);

            if  (questionType.equals("graphing"))
                new QuestionCreate().graphing(dataIndex);

            if(questionType.equals("graphplacement"))
                new QuestionCreate().graphPlacement(dataIndex);

            if(questionType.equals("pictograph"))
                new QuestionCreate().pictograph(dataIndex);

            if(questionType.equals("sentencecorrection"))
                new QuestionCreate().sentencecorrection(dataIndex);

            if(questionType.equals("multipart"))
                new QuestionCreate().multipart(dataIndex,"multipleChoiceAndSelection");


        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method createWithOnlyName.", e);
        }
    }



    public void assignByDA(int dataIndex, String classCode) {
        String appendCharacterBuild=System.getProperty("UCHAR");
        WebDriver driver=Driver.getWebDriver();
        try {
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String className = ReadTestData.readDataByTagName("", "className", Integer.toString(dataIndex));
            String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String accessibleAfterTime=ReadTestData.readDataByTagName("", "accessibleAfterTime", Integer.toString(dataIndex));
            String accessibleBeforeTime=ReadTestData.readDataByTagName("", "accessibleBeforeTime", Integer.toString(dataIndex));
            String gradereleaseoption = ReadTestData.readDataByTagName("", "gradereleaseoption", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
            String tags = ReadTestData.readDataByTagName("", "tags", Integer.toString(dataIndex));
            String showdetailedreport = ReadTestData.readDataByTagName("", "showdetailedreport", Integer.toString(dataIndex));
            String privacy = ReadTestData.readDataByTagName("", "privacy", Integer.toString(dataIndex));
            String removeExistingClass = ReadTestData.readDataByTagName("", "removeexistingclass", Integer.toString(dataIndex));
            String expireDueDate = ReadTestData.readDataByTagName("", "expireduedate", Integer.toString(dataIndex));
            String whitelistuser=ReadTestData.readDataByTagName("","whitelistuser",Integer.toString(dataIndex));
            String selectgrade=ReadTestData.readDataByTagName("", "selectgrade", Integer.toString(dataIndex));
            String assignasassessment=ReadTestData.readDataByTagName("", "assignasassessment", Integer.toString(dataIndex));

            MyAssessments myAssessments=PageFactory.initElements(driver,MyAssessments.class);
            new Navigator().navigateTo("myAssessments");//Navigate to my assessment page

            if(selectgrade!=null)
            {
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("select2-search__field")));
                Thread.sleep(1000);
                driver.findElement(By.xpath("//*[contains(@id,'"+selectgrade+"')]")).click();

            }
            WebDriverUtil.waitForAjax(driver,60);
            Thread.sleep(9000);
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");//Click on Draft
            int index = 0;
            if(myAssessments.assignmentList.size()==0)
            {
                driver.navigate().refresh();
                WebDriverUtil.waitForAjax(driver,60);
                new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");//Click on Draft

            }
            List<WebElement> allAssessment = myAssessments.assignmentList;
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assignmentname))
                    break;
                else
                    index++;

            }
            Thread.sleep(2000);

            WebDriverUtil.clickOnElementUsingJavascript(myAssessments.assignmentList.get(index));//click on Assignment
            new Click().clickByXpath("//i[@class='ed-icon icon-edit-assesment']/following-sibling::span");//Click on edit button

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("assessments-use-button")));//click on next button to assign
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@id='assign-to-class']/parent::div")));
            WebDriverUtil.waitForAjax(driver, 60);

            int classIndex=0;
            if(classCode!=null)
            {
//                Thread.sleep(1000);
//                for (WebElement ele:driver.findElements(By.cssSelector("#class-name"))){
//                    System.out.println("csid:"+ele.getAttribute("class-sec-data").substring(0,5));
//                    if(ele.getAttribute("class-sec-data").substring(0,5).trim().equals(classCode)){
//                        break;
//                    }
//                    classIndex++;
//                }
                WebElement chkBox=driver.findElement(By.xpath("//*[@id='class-name' and starts-with(@class-sec-data,'"+classCode.trim()+"')]/preceding-sibling::span"));
                WebDriverUtil.scrollIntoView(chkBox,false);
                Thread.sleep(1000);
//                WebDriverUtil.waitTillVisibilityOfElement(chkBox, 60);
                if(chkBox.isDisplayed())
                    chkBox.click();
                else WebDriverUtil.clickOnElementUsingJavascript(chkBox);
//                JavascriptExecutor jse = (JavascriptExecutor)driver;
//                jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElements(By.xpath("(//span[@class='label-span'])[position()>2]")).get(classIndex));
//                WebDriverUtil.clickOnElementUsingJavascript(driver.findElements(By.xpath("(//span[@class='label-span'])[position()>2]")).get(classIndex));
            }
            else   {
                driver.findElement(By.cssSelector(".label-span")).click(); //click on the select all checkbox

            }


            if(selectgrade!=null)
            {
                if(selectgrade.equals("true"))
                {
                    driver.findElement(By.className("select2-search__field")).click();
                    Thread.sleep(1000);
                    driver.findElement(By.xpath("//*[contains(@id,'"+selectgrade+"')]")).click();
                }
            }

            if (gradable.equals("true")) {
                if (gradereleaseoption != null)
                {
                    if(driver.findElements(By.className("p-r-md")).size()>0)
                    {
                        WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("p-r-md")));
                    }
                    if (gradereleaseoption.equals("On assignment submission"))
                        new Click().clickByXpath("//span[text()='Auto release grades on submission']/../div");//click on On assignment submission
                    if (gradereleaseoption.equals("Explicitly by teacher"))
                        new Click().clickByXpath("//span[text()='Explicit Release by Admin']/../div");//click on Explicitly by teacher
                }
            }
            else
            {
                if(driver.findElements(By.className("p-r-md")).size()>0)
                {
                    driver.findElement(By.className("p-r-md")).click();
                }
                new Click().clickByXpath("//input[@id='show-grades-ques-no']/following-sibling::ins");//click on 'Show Grades' as No
            }

            WebDriverUtil.waitUntilTextToBePresentInElement(driver.findElement(By.xpath("//label[text()='Due Date']")),"Due Date",60);
            try {
                driver.findElement(By.id("assessment-close-date")).click();
            } catch (Exception e) {
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("assessment-close-date")));//click on Due Date field
            }

            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//span[@title='Next Month']")),5);
            new Click().clickByXpath("//span[@title='Next Month']");
            Thread.sleep(1000);
            new Click().clickByXpath("//*[text()='"+duedate+"']");//select due date
            new Click().clickByXpath("//div[@class='btn btn-rounded btn-blue pull-right done-btn']");

            driver.findElement(By.id("as-description")).clear();
            driver.findElement(By.id("as-description")).sendKeys(description);//add description
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("as-tag")));
            driver.findElement(By.id("as-tag")).sendKeys(tags);//add tags

            //click on show detailed report to student yes button
            if(showdetailedreport!=null && Boolean.parseBoolean(showdetailedreport)){
                driver.findElement(By.cssSelector("#headingOne a")).click();
                WebElement eleShowDetailRepToStudentYes=driver.findElement(By.cssSelector(".student-yes-button-label>span"));
                WebDriverUtil.waitTillVisibilityOfElement(eleShowDetailRepToStudentYes,30);
                new Actions(driver).moveToElement(eleShowDetailRepToStudentYes).click(eleShowDetailRepToStudentYes).click().perform();
            }

            if (privacy != null) {
                if (privacy.equalsIgnoreCase("Private"))
                    new Click().clickByXpath("//input[@id='private']/parent::div");//click on Private Option

                if (privacy.equalsIgnoreCase("Public"))
                    new Click().clickByXpath("//input[@id='public']/parent::div");//click on With All Option

                if (privacy.equalsIgnoreCase("district")){
                    new Click().clickByXpath("//input[@id='district']/parent::div");//click on District Option
                }

                if (privacy.equalsIgnoreCase("school")) {
                    new Click().clickByXpath("//input[@id='school']/parent::div");//click on school Option
                }
            }


            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("assign-to-class-btn")));//click on assign button
            WebDriverUtil.waitForAjax(driver,60);
            Thread.sleep(5000);
            try {
                if (driver.findElement(By.xpath("//div[@class='modal-header']")).isDisplayed()){
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("//button[@class='as-modal-yes-btn yes-update-assignment']"))); //click on yes
                }
            }
            catch (Exception e){

            }

            new Click().clickByid("view-assign-button");//Click on view button

            ReportUtil.log("Description","Assessment is successfully assigned by DA","pass");

        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method assignToStudent.", e);
        }
    }


    /*
    @Author Sumit
    Assign Assignment to students/ class section
    */
    public void assignToStudent(int dataIndex, String... appendCharacter) {
        String appendCharacterBuild=System.getProperty("UCHAR");
        WebDriver driver=Driver.getWebDriver();
        try {
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String className = ReadTestData.readDataByTagName("", "className", Integer.toString(dataIndex));
            String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String accessibleAfterTime=ReadTestData.readDataByTagName("", "accessibleAfterTime", Integer.toString(dataIndex));
            String accessibleBeforeTime=ReadTestData.readDataByTagName("", "accessibleBeforeTime", Integer.toString(dataIndex));
            String gradereleaseoption = ReadTestData.readDataByTagName("", "gradereleaseoption", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
            String tags = ReadTestData.readDataByTagName("", "tags", Integer.toString(dataIndex));
            String privacy = ReadTestData.readDataByTagName("", "privacy", Integer.toString(dataIndex));
            String removeExistingClass = ReadTestData.readDataByTagName("", "removeexistingclass", Integer.toString(dataIndex));
            String expireDueDate = ReadTestData.readDataByTagName("", "expireduedate", Integer.toString(dataIndex));
            String whitelistuser=ReadTestData.readDataByTagName("","whitelistuser",Integer.toString(dataIndex));
            String selectgrade=ReadTestData.readDataByTagName("", "selectgrade", Integer.toString(dataIndex));
            String assignasassessment=ReadTestData.readDataByTagName("", "assignasassessment", Integer.toString(dataIndex));
            String daassessmentname = ReadTestData.readDataByTagName("", "daassessmentname", Integer.toString(dataIndex));
            String assessmenttype=ReadTestData.readDataByTagName("","assessmenttype",Integer.toString(dataIndex));
            String editmasterycriteria=ReadTestData.readDataByTagName("", "editmasterycriteria", Integer.toString(dataIndex));
            MyAssessments myAssessments=PageFactory.initElements(driver,MyAssessments.class);
            AssignmentDetails assignmentDetails=PageFactory.initElements(driver,AssignmentDetails.class);



            new Navigator().navigateTo("myAssessments");//Navigate to my assessment page
            WebDriverUtil.waitForAjax(driver,60);
            new WebDriverWait(driver,60).until(ExpectedConditions.invisibilityOfElementLocated(By.className("blockUI")));
            while(driver.findElements(By.cssSelector("div.blockUI.blockOverlay")).size()>0){
                Thread.sleep(1000);
                System.out.println("overlay present");
            }

            myAssessments.draft.click();
            WebDriverUtil.waitForAjax(driver,60);

            if(selectgrade!=null)
            {
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("select2-search__field")));
                Thread.sleep(1000);
                driver.findElement(By.xpath("//*[contains(@id,'"+selectgrade+"')]")).click();

            }

            int index = 0;
            if(myAssessments.assignmentList.size()==0)
            {
                driver.navigate().refresh();
                Thread.sleep(8000);
                WebDriverUtil.clickOnElementUsingJavascript(myAssessments.draft);//Click on Draft
                WebDriverUtil.waitForAjax(driver,60);
                if(myAssessments.assignmentList.size()==0){
                    driver.findElement(By.xpath("//input[@id='published']/..")).click(); //click on the publish
                    WebDriverUtil.waitForAjax(driver,60);
                    myAssessments.draft.click();//Click on Draft
                    WebDriverUtil.waitForAjax(driver,60);
                }
                Thread.sleep(9000);
                new Click().clickByXpath("//input[@id='draft']/..");//Click on Draft
            }


            List<WebElement> allAssessment = myAssessments.assignmentList;
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assignmentname))
                    break;
                else
                    index++;

            }


            WebDriverUtil.clickOnElementUsingJavascript(myAssessments.assignmentList.get(index));//click on Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignmentDetails.btnEdit);//Click on edit button

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("assessments-use-button")));//click on next button to assign
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@id='assign-now']/parent::div")));

            Assign assign=PageFactory.initElements(driver,Assign.class);

            if(editmasterycriteria!=null)
            {
                new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[id='mastery-accordion']")));
                assign.editMasteryCriteria.click();
                WebDriverUtil.waitTillVisibilityOfElement(assign.dropdown_masteryCriteria,30);
                Thread.sleep(2000);
                assign.dropdown_masteryCriteria.click();//Click on mastery criteria drop down
                new WebDriverUtil().selectItemByVisibleText(assign.dropdown_masteryCriteria,"Questions with % threshold");

                Thread.sleep(2000);
                if(assign.minimumNumberOfQuestion.getText().equals("3"))
                {
                    Thread.sleep(500);
                    assign.reduceMinimumNumberOfQuestion.click();
                    Thread.sleep(500);
                    assign.reduceMinimumNumberOfQuestion.click();
                }
                if(assign.minimumNumberOfQuestion.getText().equals("5"))
                {
                    Thread.sleep(500);
                    assign.reduceMinimumNumberOfQuestion.click();
                    Thread.sleep(500);
                    assign.reduceMinimumNumberOfQuestion.click();
                    Thread.sleep(500);
                    assign.reduceMinimumNumberOfQuestion.click();
                    Thread.sleep(500);
                    assign.reduceMinimumNumberOfQuestion.click();
                }
            }

            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String shareName;
            if(assignasassessment==null) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@id='assign-now']/parent::div")));
                //driver.findElement(By.xpath("//input[@id='assign-now']/parent::div")).click();
                Thread.sleep(1000);
                boolean removeExistingShare = true;
                if (removeExistingClass != null) {
                    removeExistingShare = false;
                }
                if (appendCharacter.length == 0) {
                    shareName = className;
                    if (shareWithClass != null) {
                        if (shareWithClass.equalsIgnoreCase("true")) {
                            shareName = className;
                        }
                    }
                    new ShareWith().share(dataIndex, shareName, removeExistingShare);
                } else {
                    for (int i = 0; i < appendCharacter.length; i++) {
                        if (shareWithClass != null) {
                            if (shareWithClass.equalsIgnoreCase("true")) {
                                if (appendCharacterBuild != null) {
                                    shareName = methodName + "class" + appendCharacter[i] + appendCharacterBuild;
                                } else {
                                    shareName = methodName + "class" + appendCharacter[i];
                                }
                            } else {
                                if (appendCharacterBuild != null) {
                                    shareName = methodName + "st" + appendCharacter[i] + appendCharacterBuild;
                                } else {
                                    shareName = methodName + "st" + appendCharacter[i];
                                }
                            }
                        } else {
                            if (appendCharacterBuild != null) {
                                shareName = methodName + "st" + appendCharacter[i] + appendCharacterBuild;
                            } else {
                                shareName = methodName + "st" + appendCharacter[i];
                            }
                        }
                        if (i > 0) removeExistingShare = false;
                        new ShareWith().share(dataIndex, shareName, removeExistingShare);
                    }
                }

                if (gradable.equals("true")) {
                    if (gradereleaseoption != null)
                    {
                        if(driver.findElements(By.className("p-r-md")).size()>0)
                        {
                            driver.findElement(By.className("p-r-md")).click();
                        }
                        if (gradereleaseoption.equals("On assignment submission"))
                            new Click().clickByXpath("//input[@id='as-grade-release-on-submission']/parent::div");//click on On assignment submission
                        if (gradereleaseoption.equals("Explicitly by teacher"))
                            new Click().clickByXpath("//input[@id='as-grade-release-by-teacher']/parent::div");//click on Explicitly by teacher
                    }
                }
                else
                {
                    if(driver.findElements(By.className("p-r-md")).size()>0)
                    {
                        driver.findElement(By.className("p-r-md")).click();
                    }
                   // new Click().clickByXpath("//input[@id='show-grades-ques-no']/following-sibling::ins");//click on 'Show Grades' as No
                }
                if (accessibleafter != null) {

                    new Click().clickByXpath("//input[@id='start-later']/parent::div");//Click on Later
                    new Click().clickByid("lsm_assignment_accessible_date");
                    new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
                    new Click().clickbylinkText(accessibleafter);
                }
                if (accessibleAfterTime != null) {
                    new Click().clickByXpath("//input[@id='lsm_assignment_accessible_time']");
                    new Click().clickByXpath("//li[@class='ui-timepicker-selected']/following::li");
                }
                if (accessibleBeforeTime != null) {
                    new Click().clickByXpath("//input[@id='lsm_assignment_accessible_time']");
                    new Click().clickByXpath("//li[@class='ui-timepicker-selected']/preceding-sibling::li");
                }

                if (expireDueDate != null)
                {
                    Calendar calendar = Calendar.getInstance();
                    int currentDate = calendar.get(Calendar.DATE);
                    int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                    int currentMinute = calendar.get(Calendar.MINUTE);
                    String selectMinute = String.valueOf(currentMinute + 5);
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("a");
                    String amOrPm = sdf.format(date);

                    if(Integer.parseInt(selectMinute)<10)
                    {
                        selectMinute= String.valueOf("0"+selectMinute) ;
                    }

                    if(currentHour>12)
                    {
                        currentHour=currentHour-12;
                    }

                    new Click().clickByid("lsm_assignment_due_date");//click on Due Date field
                    driver.findElement(By.xpath("//a[text()='" + currentDate + "']")).click();//Select the current date

                    driver.findElement(By.id("lsm_assignment_due_time")).clear();
                    driver.switchTo().activeElement().sendKeys(currentHour + ":" + selectMinute + amOrPm);//enter the time 5 minuted more than current time

                } else {
                    WebDriverUtil.waitUntilTextToBePresentInElement(driver.findElement(By.xpath("//label[text()='Due Date']")),"Due Date",60);
                    try {
                        driver.findElement(By.id("assessment-close-date")).click();
                    } catch (Exception e) {
                        WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("assessment-close-date")));//click on Due Date field
                    }
                    WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//span[@title='Next Month']")),5);
                    new Click().clickByXpath("//span[@title='Next Month']");
                    Thread.sleep(2000);
                    new Click().clickByXpath("//*[text()='"+duedate+"']");//select due date
                    new Click().clickByXpath("//div[@class='btn btn-rounded btn-blue pull-right done-btn']");
                }
            }
            else
            {
                driver.findElement(By.xpath("//input[@id='assign-later']/parent::div")).click();
                Thread.sleep(1000);
            }
            driver.findElement(By.id("as-description")).click();
            driver.findElement(By.id("as-description")).clear();
            driver.findElement(By.id("as-description")).sendKeys(String.valueOf(description));//add description
//            new Actions(driver).moveToElement(driver.findElement(By.id("as-description"))).click().sendKeys(description).build().perform();
            driver.findElement(By.xpath("//ul[@class='select2-selection__rendered']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(String.valueOf(tags));//add tags
            if (privacy != null) {
                if (privacy.equalsIgnoreCase("Private"))
                    new Click().clickByXpath("//input[@id='private']/parent::div");//click on Private Option

                if (privacy.equalsIgnoreCase("Public"))
                    new Click().clickByXpath("//input[@id='public']/parent::div");//click on With All Option

                if (privacy.equalsIgnoreCase("district")){
                    new Click().clickByXpath("//input[@id='district']/parent::div");//click on District Option
                }

                if (privacy.equalsIgnoreCase("school")) {
                    new Click().clickByXpath("//input[@id='school']/parent::div");//click on school Option
                }
            }

            //  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("assign-button")));//click on assign button
            driver.findElement(By.id("assign-button")).click();
            WebDriverUtil.waitForAjax(driver,30);

            new Click().clickByid("view-assign-button");//Click on view button
            WebDriverUtil.waitForAjax(driver,30);

            ReportUtil.log("Description","Assessment is successfully assigned","pass");

            //Make instructor as whit list user from black listK
            if(whitelistuser!=null) {
                if (whitelistuser.equals("true")) {
                    whiteListUSer(assignmentname, dataIndex, selectgrade);
                }
            }
        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method assignToStudent.", e);
        }
    }



    public void assignToStudent(int dataIndex, String assignmentname,int number) {
        String appendCharacterBuild=System.getProperty("UCHAR");
        WebDriver driver=Driver.getWebDriver();
        try {
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String className = ReadTestData.readDataByTagName("", "className", Integer.toString(dataIndex));
            String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String accessibleAfterTime=ReadTestData.readDataByTagName("", "accessibleAfterTime", Integer.toString(dataIndex));
            String accessibleBeforeTime=ReadTestData.readDataByTagName("", "accessibleBeforeTime", Integer.toString(dataIndex));
            String gradereleaseoption = ReadTestData.readDataByTagName("", "gradereleaseoption", Integer.toString(dataIndex));
            String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
            String tags = ReadTestData.readDataByTagName("", "tags", Integer.toString(dataIndex));
            String privacy = ReadTestData.readDataByTagName("", "privacy", Integer.toString(dataIndex));
            String removeExistingClass = ReadTestData.readDataByTagName("", "removeexistingclass", Integer.toString(dataIndex));
            String expireDueDate = ReadTestData.readDataByTagName("", "expireduedate", Integer.toString(dataIndex));
            String whitelistuser=ReadTestData.readDataByTagName("","whitelistuser",Integer.toString(dataIndex));
            String selectgrade=ReadTestData.readDataByTagName("", "selectgrade", Integer.toString(dataIndex));
            String assignasassessment=ReadTestData.readDataByTagName("", "assignasassessment", Integer.toString(dataIndex));
            MyAssessments myAssessments=PageFactory.initElements(driver,MyAssessments.class);

            new Navigator().navigateTo("myAssessments");//Navigate to my assessment page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");//Click on Draft
            int index = 0;
            List<WebElement> allAssessment = myAssessments.assignmentList;
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assignmentname))
                    break;
                else
                    index++;

            }
            myAssessments.assignmentList.get(index).click();//click on Assignment
            new Click().clickByXpath("//i[@class='ed-icon icon-edit-assesment']/following-sibling::span");//Click on edit button
            new Click().clickByid("assessments-use-button");//click on next button to assign
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String shareName;
            if(assignasassessment==null) {
                boolean removeExistingShare = true;
                if (removeExistingClass != null) {
                    removeExistingShare = false;
                }

                driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).clear();//short label clear
                driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).sendKeys("sh"+new RandomString().randominteger(2));//short label name
                if (gradable.equals("true")) {
                    // new Click().clickByXpath("//div[starts-with(@class,'icheckbox_square-green')]/descendant::ins");//click on Gradable Checkbox
                    if (gradereleaseoption != null) {
                        if (gradereleaseoption.equals("On assignment submission"))
                            new Click().clickByXpath("//input[@id='as-grade-release-on-submission']/parent::div");//click on On assignment submission
                        if (gradereleaseoption.equals("Explicitly by teacher"))
                            new Click().clickByXpath("//input[@id='as-grade-release-by-teacher']/parent::div");//click on Explicitly by teacher
                    }
                }
                else
                {
                    new Click().clickByXpath("//div[starts-with(@class,'icheckbox_square-green')]/descendant::ins");//click on Gradable Checkbox
                }
                if (accessibleafter != null) {

                    new Click().clickByXpath("//input[@id='start-later']/parent::div");//Click on Later
                    new Click().clickByid("lsm_assignment_accessible_date");
                    new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
                    new Click().clickbylinkText(accessibleafter);
                }
                if (accessibleAfterTime != null) {
                    new Click().clickByXpath("//input[@id='lsm_assignment_accessible_time']");
                    new Click().clickByXpath("//li[@class='ui-timepicker-selected']/following::li");
                }
                if (accessibleBeforeTime != null) {
                    new Click().clickByXpath("//input[@id='lsm_assignment_accessible_time']");
                    new Click().clickByXpath("//li[@class='ui-timepicker-selected']/preceding-sibling::li");
                }

                if (expireDueDate != null) {
                    Calendar calendar = Calendar.getInstance();
                    int currentDate = calendar.get(Calendar.DATE);
                    int currentHour = calendar.get(Calendar.HOUR);
                    int currentMinute = calendar.get(Calendar.MINUTE);
                    int selectMinute = currentMinute + 5;
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("a");
                    String amOrPm = sdf.format(date);

                    new Click().clickByid("lsm_assignment_due_date");//click on Due Date field
                    driver.findElement(By.xpath("//a[text()='" + currentDate + "']")).click();//Select the current date

                    driver.findElement(By.id("lsm_assignment_due_time")).clear();
                    driver.switchTo().activeElement().sendKeys(currentHour + ":" + selectMinute + amOrPm);//enter the time 5 minuted more than current time

                } else {
                    new Click().clickByid("lsm_assignment_due_date");//click on Due Date field
                    new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
                    new Click().clickbylinkText(duedate);//select due date
                    new Click().clickByid("lsm_assignment_due_time");//click on Due Time field
                    List<WebElement> elements = driver.findElements(By.xpath("//li"));
                    for (WebElement time : elements) {
                        if (time.getText().equals(duetime)) {
                            time.click();
                            break;
                        }
                    }
                }
            }
            else
            {
                driver.findElement(By.xpath("//input[@id='assign-later']/parent::div")).click();
                Thread.sleep(1000);
            }
            driver.findElement(By.id("as-description")).click();
            driver.findElement(By.id("as-description")).clear();
            driver.findElement(By.id("as-description")).sendKeys(description);//add description
            driver.findElement(By.id("as-tag")).click();
            driver.findElement(By.id("as-tag")).sendKeys(tags);//add tags
            if (privacy != null) {
                if (privacy.equalsIgnoreCase("Private"))
                    new Click().clickByXpath("//input[@id='private']/parent::div");//click on Private Option

                if (privacy.equalsIgnoreCase("Public"))
                    new Click().clickByXpath("//input[@id='public']/parent::div");//click on With All Option

                if (privacy.equalsIgnoreCase("district")){
                    new Click().clickByXpath("//input[@id='district']/parent::div");//click on District Option
                }

                if (privacy.equalsIgnoreCase("school")) {
                    new Click().clickByXpath("//input[@id='school']/parent::div");//click on school Option
                }
            }
            new Click().clickByid("assign-button");//click on Assign button
            new Click().clickByid("view-assign-button");//Click on view button

            //Make instructor as whit list user from black list
            if(whitelistuser==null)
            {
                whiteListUSer(assignmentname,dataIndex,selectgrade);
            }

        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method assignToStudent.", e);
        }
    }

    WebDriver firefoxdriver;
    public void
    whiteListUSer(String assessmentname,int dataindex,String grade)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String subjectArea=ReadTestData.readDataByTagName("", "subjectArea", Integer.toString(dataindex));
            String privacy=ReadTestData.readDataByTagName("", "privacy", Integer.toString(dataindex));
            firefoxdriver=  new ReinitDriver().startDriver(firefoxdriver);
            MyAssessments myAssessments=PageFactory.initElements(firefoxdriver,MyAssessments.class);
            new Login().directLoginAsInstructor(1,"curator@snapwiz.com",firefoxdriver);
            new Navigator().navigateTo("assessmentLibrary",firefoxdriver);
            WebDriverUtil.waitForAjax(firefoxdriver,60);
            if(grade!=null)
            {
                WebDriverUtil.clickOnElementUsingJavascript(firefoxdriver.findElement(By.className("select2-search__field")));
                Thread.sleep(1000);
                firefoxdriver.findElement(By.xpath("//*[contains(@id,'"+grade+"')]")).click();

            }
            else
            {
                //check grade 1 is selected already, otherwise select it
//                if(driver.findElements(By.cssSelector(".select2-selection__choice")).size()>0) {
                AssessmentLibrary assessmentLibrary = PageFactory.initElements(firefoxdriver, AssessmentLibrary.class);
//                    WebDriverUtil.clickOnElementUsingJavascript(assessmentLibrary.removeGrade);//Remove the existing grade
//                    WebDriverUtil.executeJavascript("$(\"ul[class='select2-selection__rendered']>li:nth-of-type(1)>span\").click()");
                ((JavascriptExecutor)firefoxdriver).executeScript("$('.select2-selection__choice__remove').click()");
//                    ((JavascriptExecutor) firefoxdriver).executeScript("$(\"ul[class='select2-selection__rendered']>li:nth-of-type(1)>span\").click()");
//                    Thread.sleep(2000);
                firefoxdriver.findElement(By.xpath("//*[contains(@id,'Grade 1')]")).click();
                WebDriverUtil.waitForAjax(firefoxdriver,60);
//                }

            }



//            Thread.sleep(4000);
            new Common().waitForUIBlockerToClose(firefoxdriver, 60);
            firefoxdriver.findElement(By.xpath("//span[@id='select2-sortSelection-container']")).click();
            List<WebElement> sortElements=firefoxdriver.findElements(By.xpath("//ul[@id='select2-sortSelection-results']/li"));
            sortElements.get(3).click();
            firefoxdriver.findElement(By.id("search-fld")).sendKeys(assessmentname);
            firefoxdriver.switchTo().activeElement().sendKeys(Keys.ENTER);
            //click on sort
            List<WebElement> dropdown=firefoxdriver.findElements(By.cssSelector("span[class='select2-selection select2-selection--single']"));
            Thread.sleep(3000);
            //select sharing level
            if(privacy!=null)
            {
                if(privacy.equals("private"))
                    firefoxdriver.findElements(By.xpath("//input[@name='selected-owner']/following-sibling::ins")).get(0).click();
                if(privacy.equalsIgnoreCase("district"))
                    firefoxdriver.findElements(By.xpath("//input[@name='selected-owner']/following-sibling::ins")).get(2).click();
                if(privacy.equals("school"))
                    firefoxdriver.findElements(By.xpath("//input[@name='selected-owner']/following-sibling::ins")).get(3).click();

                if(privacy.equals("Public"))
                {

                    System.out.println("inside public");
                    new Common().waitForUIBlockerToClose(firefoxdriver,120);
                    firefoxdriver.findElements(By.xpath("//input[@name='selected-owner']/following-sibling::ins")).get(8).click();
                    WebDriverUtil.waitForAjax(firefoxdriver,60);
                    int index=0;
                    List<WebElement> assignmentList=myAssessments.assignmentList;
                    for (WebElement assessment : assignmentList)
                    {
                        if (assessmentname.equals(assessment.getText()))
                            break;
                        else
                            index++;
                    }

                    ((JavascriptExecutor)firefoxdriver).executeScript("document.getElementsByClassName('assessment-name-padding twoline-ellipsis')["+index+"].click();");
                    if(firefoxdriver.findElements(By.cssSelector("span.black-list-user-button")).size()==1)
                    {
                        firefoxdriver.findElement(By.cssSelector("span.black-list-user-button")).click();
                    }
                    firefoxdriver.findElement(By.className("assessment-edit-sharing")).click();
                    Thread.sleep(2000);
                    firefoxdriver.findElement(By.xpath("//input[@id='sharingEnabled']/following-sibling::ins")).click();
                    //   firefoxdriver.findElement(By.id("sharingEnabled")).click();
                    firefoxdriver.findElement(By.id("update-shared-assessment-access-control")).click();
                    WebDriverUtil.waitForAjax(firefoxdriver,60);
                    new Navigator().logout(firefoxdriver);
                    firefoxdriver.quit();
                }
                else
                {
                    int index=0;
                    List<WebElement> assignmentList=myAssessments.assignmentList;
                    for (WebElement assessment : assignmentList)
                    {
                        if (assessmentname.equals(assessment.getText()))
                            break;
                        else
                            index++;
                    }
                    //  new Click().clickbylistcssselector("span.as-edit",index);
                    myAssessments.assignmentList.get(index).click();//click on Assignment
                    if(firefoxdriver.findElements(By.cssSelector("span.black-list-user-button")).size()==1)
                    {
                        firefoxdriver.findElement(By.cssSelector("span.black-list-user-button")).click();
                    }
                    Thread.sleep(1000);
                    new Navigator().logout(firefoxdriver);
                    firefoxdriver.close();
                }
            }


            //select grade and subject area


        }
        catch(Exception e)
        {

            // firefoxdriver.close();
            Assert.fail("exception in white list user", e);
            firefoxdriver.close();



        }
    }
    public void reAssignToStudent(int dataIndex, String... appendCharacter) {
        String appendCharacterBuild=System.getProperty("UCHAR");
        WebDriver driver=Driver.getWebDriver();
        try {
            MyAssessments myAssessments=PageFactory.initElements(firefoxdriver,MyAssessments.class);

            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String className = ReadTestData.readDataByTagName("", "className", Integer.toString(dataIndex));
            String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String accessibleAfterTime=ReadTestData.readDataByTagName("", "accessibleAfterTime", Integer.toString(dataIndex));
            String accessibleBeforeTime=ReadTestData.readDataByTagName("", "accessibleBeforeTime", Integer.toString(dataIndex));
            String gradereleaseoption = ReadTestData.readDataByTagName("", "gradereleaseoption", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
            String tags = ReadTestData.readDataByTagName("", "tags", Integer.toString(dataIndex));
            String privacy = ReadTestData.readDataByTagName("", "privacy", Integer.toString(dataIndex));
            String removeExistingClass = ReadTestData.readDataByTagName("", "removeexistingclass", Integer.toString(dataIndex));
            String expireDueDate = ReadTestData.readDataByTagName("", "expireduedate", Integer.toString(dataIndex));

            new Navigator().navigateTo("assignment");//Navigate to my assignment page
            new Click().clickBycssselector("span.as-assignment-draftTxt");
            int index = 0;

            List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='as-label ellipsis']"));//as per E8
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assignmentname))
                    break;
                else
                    index++;

            }
            new Click().clickbylistcssselector("span.as-edit",index);

            new Click().clickByid("assessments-use-button");//click on next button to assign
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String shareName;
            boolean removeExistingShare = true;
            if(removeExistingClass!=null){
                removeExistingShare = false;
            }
            if(appendCharacter.length == 0) {
                shareName = className;
                if (shareWithClass != null) {
                    if (shareWithClass.equalsIgnoreCase("true")) {
                        shareName = className;
                    }
                }
                new ShareWith().share(dataIndex, shareName, removeExistingShare);
            }
            else {
                for (int i = 0; i < appendCharacter.length; i++) {
                    if (shareWithClass != null) {
                        if (shareWithClass.equalsIgnoreCase("true")) {
                            if(appendCharacterBuild!=null)
                            {
                                shareName = methodName + "class" + appendCharacter[i]+appendCharacterBuild;
                            }
                            else {
                                shareName = methodName + "class" + appendCharacter[i];
                            }
                        } else
                        {
                            if(appendCharacterBuild!=null)
                            {
                                shareName = methodName + "st" + appendCharacter[i] + appendCharacterBuild;
                            }
                            else
                            {
                                shareName = methodName + "st" + appendCharacter[i] ;
                            }
                        }
                    } else
                    {
                        if(appendCharacterBuild!=null)
                        {
                            shareName = methodName + "st" + appendCharacter[i] + appendCharacterBuild;
                        }
                        else
                        {
                            shareName = methodName + "st" + appendCharacter[i] ;
                        }
                    }
                    if (i > 0) removeExistingShare = false;
                    new ShareWith().share(dataIndex, shareName, removeExistingShare);
                }
            }
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).clear();//short label clear
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).sendKeys("sh"+new RandomString().randominteger(2));//short label name

            if (gradable.equals("true")) {
                new Click().clickByXpath("//div[starts-with(@class,'icheckbox_square-green')]/descendant::ins");//click on Gradable Checkbox
                if (gradereleaseoption != null) {
                    if (gradereleaseoption.equals("On assignment submission"))
                        new Click().clickByXpath("//input[@id='as-grade-release-on-submission']/parent::div");//click on On assignment submission
                    if (gradereleaseoption.equals("Explicitly by teacher"))
                        new Click().clickByXpath("//input[@id='as-grade-release-by-teacher']/parent::div");//click on Explicitly by teacher
                }
            }

            if (accessibleafter != null) {

                new Click().clickByXpath("//input[@id='start-later']/parent::div");//Click on Later
                new Click().clickByid("lsm_assignment_accessible_date");
                new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
                new Click().clickbylinkText(accessibleafter);
            }
            if (accessibleAfterTime!= null) {
                new Click().clickByXpath("//input[@id='lsm_assignment_accessible_time']");
                new Click().clickByXpath("//li[@class='ui-timepicker-selected']/following::li");
            }
            if (accessibleBeforeTime!= null) {
                new Click().clickByXpath("//input[@id='lsm_assignment_accessible_time']");
                new Click().clickByXpath("//li[@class='ui-timepicker-selected']/preceding-sibling::li");
            }

            if(expireDueDate!=null){
                Calendar calendar=Calendar.getInstance();
                int currentDate = calendar.get(Calendar.DATE);
                int currentHour = calendar.get(Calendar.HOUR);
                int currentMinute = calendar.get(Calendar.MINUTE);
                int selectMinute = currentMinute+5;
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("a");
                String amOrPm = sdf.format(date);

                new Click().clickByid("lsm_assignment_due_date");//click on Due Date field
                driver.findElement(By.xpath("//a[text()='"+currentDate+"']")).click();//Select the current date

                driver.findElement(By.id("lsm_assignment_due_time")).clear();
                driver.switchTo().activeElement().sendKeys(currentHour+":"+selectMinute+amOrPm);//enter the time 5 minuted more than current time

            }else {
                new Click().clickByid("lsm_assignment_due_date");//click on Due Date field
                new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
                new Click().clickbylinkText(duedate);//select due date
                new Click().clickByid("lsm_assignment_due_time");//click on Due Time field
                List<WebElement> elements = driver.findElements(By.xpath("//li"));
                for (WebElement time : elements) {
                    if (time.getText().equals(duetime)) {
                        time.click();
                        break;
                    }
                }
            }
            driver.findElement(By.id("as-description")).click();
            driver.findElement(By.id("as-description")).clear();
            driver.findElement(By.id("as-description")).sendKeys(description);//add description
            driver.findElement(By.id("as-tag")).click();
            driver.findElement(By.id("as-tag")).sendKeys(tags);//add tags
            if (privacy != null) {
                if (privacy.equalsIgnoreCase("Private"))
                    new Click().clickByXpath("//input[@id='private']/parent::div");//click on Private Option

                if (privacy.equalsIgnoreCase("Public"))
                    new Click().clickByXpath("//input[@id='public']/parent::div");//click on With All Option

                if (privacy.equalsIgnoreCase("District")){
                    new Click().clickByXpath("//input[@id='district']/parent::div");//click on District Option
                }

                if (privacy.equalsIgnoreCase("School")) {
                    new Click().clickByXpath("//input[@id='school']/parent::div");//click on school Option
                }
            }
            new Click().clickByid("assign-button");//click on Assign button
        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method assignToStudent.", e);
        }
    }
    public void editPublishedAssignment(int dataIndex, String... appendCharacter) {
        String appendCharacterBuild=System.getProperty("UCHAR");
        WebDriver driver=Driver.getWebDriver();
        MyAssessments myAssessments=PageFactory.initElements(driver,MyAssessments.class);

        try {
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Navigator().navigateTo("myAssessments");//Navigate to my assignment page
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            int index = 0;
            List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='as-label ellipsis']"));//as per E8
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assignmentname))
                    break;
                else
                    index++;

            }
            myAssessments.assignmentList.get(index);//click on Assignment

            new Click().clickbylinkText("Edit");
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.as-modal-yes-btn.yes-delete")));
            new Click().clickBycssselector("div.as-modal-yes-btn.yes-delete");



        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method assignToStudent.", e);
        }
    }



    public void editPublishedAssessment(int dataIndex, String... appendCharacter) {
        String appendCharacterBuild=System.getProperty("UCHAR");
        WebDriver driver=Driver.getWebDriver();
        try {
            MyAssessments myAssessments=PageFactory.initElements(driver,MyAssessments.class);

            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Navigator().navigateTo("myAssessments");//Navigate to my assignment page
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            int index = 0;
            List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='as-label ellipsis']"));//as per E8
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assignmentname))
                    break;
                else
                    index++;

            }
            myAssessments.assignmentList.get(index);//click on Assignment

            new Click().clickbylinkText("Edit");
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.as-modal-yes-btn.yes-delete")));
            new Click().clickBycssselector("div.as-modal-yes-btn.yes-delete");



        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method assignToStudent.", e);
        }
    }
    public void createAssessmentUsingDraftStatus(int dataIndex) {
        String appendCharacterBuild=System.getProperty("UCHAR");
        WebDriver driver=Driver.getWebDriver();
        try {
            MyAssessments myAssessments=PageFactory.initElements(driver,MyAssessments.class);

            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String className = ReadTestData.readDataByTagName("", "className", Integer.toString(dataIndex));
            String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String accessibleAfterTime=ReadTestData.readDataByTagName("", "accessibleAfterTime", Integer.toString(dataIndex));
            String accessibleBeforeTime=ReadTestData.readDataByTagName("", "accessibleBeforeTime", Integer.toString(dataIndex));
            String gradereleaseoption = ReadTestData.readDataByTagName("", "gradereleaseoption", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
            String tags = ReadTestData.readDataByTagName("", "tags", Integer.toString(dataIndex));
            String privacy = ReadTestData.readDataByTagName("", "privacy", Integer.toString(dataIndex));
            String removeExistingClass = ReadTestData.readDataByTagName("", "removeexistingclass", Integer.toString(dataIndex));
            String expireDueDate = ReadTestData.readDataByTagName("", "expireduedate", Integer.toString(dataIndex));


            new Navigator().navigateTo("myAssessments");//Navigate to my assessment page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");//Click on Draft
            int index = 0;
            //List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='as-label ellipsis']"));//as per E8
            List<WebElement> allAssessment = myAssessments.assignmentList;//WILL BE chenge on E9
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assignmentname))
                    break;
                else
                    index++;

            }
            myAssessments.assignmentList.get(index).click();
            new Click().clickByXpath("//i[@class='ed-icon icon-edit-assesment']/following-sibling::span");//Click on edit button
            new Click().clickByid("assessments-use-button");//click on next button to assign

            driver.findElement(By.id("as-description")).click();
            driver.findElement(By.id("as-description")).clear();
            driver.findElement(By.id("as-description")).sendKeys(description);//add description
            driver.findElement(By.id("as-tag")).click();
            driver.findElement(By.id("as-tag")).sendKeys(tags);//add tags
            if (privacy != null) {
                if (privacy.equalsIgnoreCase("Private"))
                    new Click().clickByXpath("//input[@id='private']/parent::div");//click on Private Option

                if (privacy.equalsIgnoreCase("Public"))
                    new Click().clickByXpath("//input[@id='public']/parent::div");//click on With All Option

                if (privacy.equalsIgnoreCase("district")){
                    new Click().clickByXpath("//input[@id='district']/parent::div");//click on District Option
                }

                if (privacy.equalsIgnoreCase("school")) {
                    new Click().clickByXpath("//input[@id='school']/parent::div");//click on school Option
                }
            }
            new Click().clickByid("assign-button");//click on Assign button
        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method assignToStudent.", e);
        }
    }


    public void startAssignment(int dataIndex) {
        WebDriver driver=Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String usehint = ReadTestData.readDataByTagName("", "usehint", Integer.toString(dataIndex));
            String answerchoice = ReadTestData.readDataByTagName("", "answerchoice", Integer.toString(dataIndex));
            String writeboarddata = ReadTestData.readDataByTagName("", "writeboarddata", Integer.toString(dataIndex));
            String submitFirstAssignment = ReadTestData.readDataByTagName("", "submitFirstAssignment", Integer.toString(dataIndex));
            int index = 0;
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h4.as-title")));
            if (submitFirstAssignment == null || submitFirstAssignment.equals("") || submitFirstAssignment.equals("false")) {
                List<WebElement> allAssignment = driver.findElements(By.cssSelector("h4.as-title"));
                for (WebElement assignment : allAssignment) {

                    int lastLocOfSpace = assignment.getText().lastIndexOf(" ");
                    if (assignment.getText().substring(6, lastLocOfSpace).trim().equals(assessmentname)) {
                        break;
                    } else
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", assignment);
                    index++;
                }
            } else if (submitFirstAssignment.equals("true")) {
                index = 0;
            }
            if (driver.findElements(By.cssSelector("h4.as-title")).size() > 1)
            {
                List<WebElement> alllist=driver.findElements(By.cssSelector("h4.as-title"));
                alllist.get(index).click();
                Thread.sleep(2000);
            }


            else
                driver.findElement(By.cssSelector("h4.as-title")).click();
        }
        catch (Exception e1) {
            Assert.fail("Exception in starting Assignment",e1);
        }
    }

    public void startAssignment(int dataIndex,WebDriver webdriver) {
        try {
            WebDriver driver=Driver.getWebDriver();
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String usehint = ReadTestData.readDataByTagName("", "usehint", Integer.toString(dataIndex));
            String answerchoice = ReadTestData.readDataByTagName("", "answerchoice", Integer.toString(dataIndex));
            String writeboarddata = ReadTestData.readDataByTagName("", "writeboarddata", Integer.toString(dataIndex));
            String submitFirstAssignment = ReadTestData.readDataByTagName("", "submitFirstAssignment", Integer.toString(dataIndex));
            int index = 0;
            new WebDriverWait(webdriver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h4.as-title")));
            if (submitFirstAssignment == null || submitFirstAssignment.equals("") || submitFirstAssignment.equals("false")) {
                List<WebElement> allAssignment = webdriver.findElements(By.cssSelector("h4.as-title"));
                for (WebElement assignment : allAssignment) {

                    int lastLocOfSpace = assignment.getText().lastIndexOf(" ");
                    if (assignment.getText().substring(6, lastLocOfSpace).trim().equals(assessmentname)) {
                        break;
                    } else
                        ((JavascriptExecutor) webdriver).executeScript("arguments[0].scrollIntoView(true);", assignment);
                    index++;
                }
            } else if (submitFirstAssignment.equals("true")) {
                index = 0;
            }
            if (webdriver.findElements(By.cssSelector("h4.as-title")).size() > 1)
            {
                List<WebElement> alllist=webdriver.findElements(By.cssSelector("h4.as-title"));
                alllist.get(index).click();
                Thread.sleep(2000);
            }


            else
                webdriver.findElement(By.cssSelector("h4.as-title")).click();
        }
        catch (Exception e1) {
            Assert.fail("Exception in starting Assignment",e1);
        }
    }
    /*
    @Author Sumit
    Submit Assignment by the student
    */
    public void submitAssignment(int dataIndex) {
        try {
            WebDriver driver=Driver.getWebDriver();
            String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String usehint = ReadTestData.readDataByTagName("", "usehint", Integer.toString(dataIndex));
            String answerchoice = ReadTestData.readDataByTagName("", "answerchoice", Integer.toString(dataIndex));
            String writeboarddata = ReadTestData.readDataByTagName("", "writeboarddata", Integer.toString(dataIndex));
            String submitFirstAssignment = ReadTestData.readDataByTagName("", "submitFirstAssignment", Integer.toString(dataIndex));
            new Navigator().navigateTo("assignment");
            int index = 0;
            new ExplicitWait().explicitWaitByCssSelector("h4.as-title", 10);
            if(submitFirstAssignment == null || submitFirstAssignment.equals("") || submitFirstAssignment.equals("false")) {
                List<WebElement> allAssignment = driver.findElements(By.cssSelector("h4.as-title"));
                for (WebElement assignment : allAssignment) {

                    String assessment=null;

                    assessment=assignment.getText().trim();


                    System.out.println("assessment "+assessment);

                    if (assessment.contains(assessmentname)) {
                        break;
                    } else
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", assignment);
                    index++;
                }
            }
            else if(submitFirstAssignment.equals("true")){
                index = 0;
            }
            if(driver.findElements(By.cssSelector("h4.as-title")).size()>1)
            {
                new Click().clickbylistcssselector("h4.as-title", index);//click on Assignment
            }
            else
            {
                new Click().clickBycssselector("h4.as-title");
            }

            boolean useHint = false;//by default useHint is set to false
            if(usehint != null)//read the tag from testdata
                useHint = true;//set useHint to true

            if(answerchoice == null)
                answerchoice = "correct";



            if (driver.findElement(By.id("assessmentTimer")).isDisplayed()){
                System.out.println("Inside Timer");
                int timer = 1;
                while (timer != 0)
                {
                    String questionText = new TextFetch().textfetchbyid("question-edit");

                    if(questionText.contains("True False"))
                        new AttemptQuestion().trueFalse(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Multiple Choice"))
                        new AttemptQuestion().multipleChoice(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Multi Selection"))
                        new AttemptQuestion().multipleSelection(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Text Entry"))
                        new AttemptQuestion().textEntry(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Text Drop Down"))
                        new AttemptQuestion().textDropDown(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Numeric Type"))
                        new AttemptQuestion().numeric(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Numeric Entry With Units"))
                        new AttemptQuestion().numericEntryWithUnits(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Expression Evaluator"))
                        new AttemptQuestion().expressionEvaluator(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Match the Following"))
                        new AttemptQuestion().matchTheFollowing(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Drag and Drop"))
                        new AttemptQuestion().dragAndDrop(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Classification"))
                        new AttemptQuestion().attemptClassification(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Cloze Formula"))
                        new AttemptQuestion().attemptClozeFormula(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Cloze Matrix"))
                        new AttemptQuestion().attemptClozeMatrix(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Essay"))
                        new AttemptQuestion().essayType(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Graphing"))
                        new AttemptQuestion().attemptGraphing(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Graph Plotter"))
                        new AttemptQuestion().attemptGraphPlotter(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Sentence correction"))
                        new AttemptQuestion().sentenceCorrection(useHint, answerchoice, dataIndex);

                    if (questionText.contains("Sentence Response"))
                        new AttemptQuestion().attemptSentenceResponse(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Label An Image(Text)"))
                        new AttemptQuestion().attemptLabelAnImageText(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Label An Image(Dropdown)"))
                        new AttemptQuestion().attemptLabelAnImageDropdown(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Pictograph"))
                        new AttemptQuestion().attemptpictograph(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Graph Placement"))
                        new AttemptQuestion().graphPlacement(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Resequence"))
                        new AttemptQuestion().attemptResequence(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Number Line"))
                        new AttemptQuestion().attemptNumberLine(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Line Plot"))
                        new AttemptQuestion().attemptLinePlot(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Range Plotter"))
                        new AttemptQuestion().attemptRangePlotter(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Fraction editor"))
                        new AttemptQuestion().attemptFractionEditor(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Matching Tables"))
                        new AttemptQuestion().attemptMatchingTables(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Multipart : MultipleChoice Question"))
                        new AttemptQuestion().multipart(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Multipart : TextDropdown Question"))
                        new AttemptQuestion().multipart(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Multipart : AdvanceNumeric"))
                        new AttemptQuestion().multipart(useHint,answerchoice,dataIndex);

                    if(writeboarddata != null && driver.findElements(By.cssSelector("#question-preview-passage-question-direction")).size()==0 && questionText.contains("Multipart")==false){

                        new WriteBoard().enterTextInWriteBoard(writeboarddata, dataIndex);
                    }

                    try {
                        driver.findElement(By.id("as-take-next-question")).click();//click on next
                    }
                    catch (Exception e) {
                        try {
                            driver.findElement(By.cssSelector("span[class='btn sty-blue save submit-button']")).click();//click on Finish
                        }
                        catch (Exception e1) {
                            Assert.fail("Exception in clicking Finish button while attempting the assignment",e1);
                        }
                    }

                    if(questionText.contains("Expression Evaluator")) Thread.sleep(5000);
                    else Thread.sleep(3000);
                    try {
                        if (driver.findElement(By.id("assessmentTimer")).isDisplayed()) {
                            timer = 1;
                        }else
                            timer=0;
                    }
                    catch (Exception e){
                        timer=0;
                    }
                /*timer = driver.findElements(By.id("assessmentTimer")).size();*/

                }
            }
            else {
                System.out.println("outside Timer");
                while (driver.findElements(By.xpath("//span[@id='as-take-next-question'][@title='Submit']")).size()==0)
                {
                    String questionText = new TextFetch().textfetchbyid("question-edit");

                    if(questionText.contains("True False"))
                        new AttemptQuestion().trueFalse(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Multiple Choice"))
                        new AttemptQuestion().multipleChoice(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Multi Selection"))
                        new AttemptQuestion().multipleSelection(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Text Entry"))
                        new AttemptQuestion().textEntry(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Text Drop Down"))
                        new AttemptQuestion().textDropDown(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Numeric Type"))
                        new AttemptQuestion().numeric(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Numeric Entry With Units"))
                        new AttemptQuestion().numericEntryWithUnits(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Expression Evaluator"))
                        new AttemptQuestion().expressionEvaluator(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Match the Following"))
                        new AttemptQuestion().matchTheFollowing(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Drag and Drop"))
                        new AttemptQuestion().dragAndDrop(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Classification"))
                        new AttemptQuestion().attemptClassification(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Cloze Formula"))
                        new AttemptQuestion().attemptClozeFormula(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Cloze Matrix"))
                        new AttemptQuestion().attemptClozeMatrix(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Essay"))
                        new AttemptQuestion().essayType(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Graphing"))
                        new AttemptQuestion().attemptGraphing(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Graph Plotter"))
                        new AttemptQuestion().attemptGraphPlotter(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Sentence correction"))
                        new AttemptQuestion().sentenceCorrection(useHint, answerchoice, dataIndex);

                    if (questionText.contains("Sentence Response"))
                        new AttemptQuestion().attemptSentenceResponse(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Label An Image(Text)"))
                        new AttemptQuestion().attemptLabelAnImageText(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Label An Image(Dropdown)"))
                        new AttemptQuestion().attemptLabelAnImageDropdown(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Pictograph"))
                        new AttemptQuestion().attemptpictograph(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Graph Placement"))
                        new AttemptQuestion().graphPlacement(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Resequence"))
                        new AttemptQuestion().attemptResequence(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Number Line"))
                        new AttemptQuestion().attemptNumberLine(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Line Plot"))
                        new AttemptQuestion().attemptLinePlot(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Range Plotter"))
                        new AttemptQuestion().attemptRangePlotter(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Fraction editor"))
                        new AttemptQuestion().attemptFractionEditor(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Matching Tables"))
                        new AttemptQuestion().attemptMatchingTables(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Multipart : MultipleChoice Question"))
                        new AttemptQuestion().multipart(useHint, answerchoice, dataIndex);

                    if(questionText.contains("Multipart : TextDropdown Question"))
                        new AttemptQuestion().multipart(useHint,answerchoice,dataIndex);

                    if(questionText.contains("Multipart : AdvanceNumeric"))
                        new AttemptQuestion().multipart(useHint,answerchoice,dataIndex);

                    if(writeboarddata != null && driver.findElements(By.cssSelector("#question-preview-passage-question-direction")).size()==0 && questionText.contains("Multipart")==false){

                        new WriteBoard().enterTextInWriteBoard(writeboarddata, dataIndex);
                    }

                    try {
                        driver.findElement(By.id("as-take-next-question")).click();//click on next
                    }
                    catch (Exception e) {
                        try {
                            driver.findElement(By.cssSelector("span[class='btn sty-blue save submit-button']")).click();//click on Finish
                        }
                        catch (Exception e1) {
                            Assert.fail("Exception in clicking Finish button while attempting the assignment",e1);
                        }
                    }

                }
                Thread.sleep(3000);
                driver.findElement(By.xpath("//span[@id='as-take-next-question'][@title='Submit']")).click(); //click on the submit link
            }

            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'btn sty-blue submit')]")));
            driver.findElement(By.xpath("//span[contains(@class,'btn sty-blue submit')]")).click();//click on Submit



        } catch (Exception e) {
            Assert.fail("Exception in app helper submitAssignment in class Assignment.", e);
        }
    }

    public void attemptQuestions(String questionType,int dataIndex) {
        try {
            WebDriver driver=Driver.getWebDriver();
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String usehint = ReadTestData.readDataByTagName("", "usehint", Integer.toString(dataIndex));
            String answerchoice = ReadTestData.readDataByTagName("", "answerchoice", Integer.toString(dataIndex));
            String writeboarddata = ReadTestData.readDataByTagName("", "writeboarddata", Integer.toString(dataIndex));
            String submitFirstAssignment = ReadTestData.readDataByTagName("", "submitFirstAssignment", Integer.toString(dataIndex));

            boolean useHint = false;//by default useHint is set to false
            if (usehint != null)//read the tag from testdata
                useHint = true;//set useHint to true

            if (answerchoice == null)
                answerchoice = "correct";

            if (questionType.equals("truefalse"))
                new AttemptQuestion().trueFalse(useHint, answerchoice, dataIndex);

            if (questionType.contains("multiplechoice"))
                new AttemptQuestion().multipleChoice(useHint, answerchoice, dataIndex);

            if (questionType.contains("multipleselection"))
                new AttemptQuestion().multipleSelection(useHint, answerchoice, dataIndex);

            if (questionType.contains("textentry"))
                new AttemptQuestion().textEntry(useHint, answerchoice, dataIndex);

            if (questionType.contains("textdropdown"))
                new AttemptQuestion().textDropDown(useHint, answerchoice, dataIndex);

            if (questionType.contains("numericentrywithunits"))
                new AttemptQuestion().numericEntryWithUnits(useHint, answerchoice, dataIndex);

            if (questionType.contains("numeric"))
                new AttemptQuestion().numeric(useHint, answerchoice, dataIndex);

            if (questionType.contains("expressionevaluator"))
                new AttemptQuestion().expressionEvaluator(useHint, answerchoice, dataIndex);

            if (questionType.contains("matchthefollowing"))
                new AttemptQuestion().matchTheFollowing(useHint, answerchoice, dataIndex);

            if (questionType.contains("draganddrop"))
                new AttemptQuestion().dragAndDrop(useHint, answerchoice, dataIndex);

            if (questionType.contains("clozeformula"))
                new AttemptQuestion().attemptClozeFormula(useHint, answerchoice, dataIndex);

            if (questionType.contains("graphplotter"))
                new AttemptQuestion().attemptGraphPlotter(useHint, answerchoice, dataIndex);

            if (questionType.contains("clozematrix"))
                new AttemptQuestion().attemptClozeMatrix(useHint, answerchoice, dataIndex);

            if (questionType.contains("resequence"))
                new AttemptQuestion().attemptResequence(useHint, answerchoice, dataIndex);

            if (questionType.contains("labelanimagetext"))
                new AttemptQuestion().attemptLabelAnImageText(useHint, answerchoice, dataIndex);

            if (questionType.contains("labelanimagedropdown"))
                new AttemptQuestion().attemptLabelAnImageDropdown(useHint, answerchoice, dataIndex);

            if (questionType.contains("numberline"))
                new AttemptQuestion().attemptNumberLine(useHint, answerchoice, dataIndex);

            if (questionType.contains("classification"))
                new AttemptQuestion().attemptClassification(useHint, answerchoice, dataIndex);

            if (questionType.contains("sentenceresponse"))
                new AttemptQuestion().attemptSentenceResponse(useHint, answerchoice, dataIndex);

            if (questionType.contains("matchingtables"))
                new AttemptQuestion().attemptMatchingTables(useHint, answerchoice, dataIndex);

            if (questionType.contains("lineplot"))
                new AttemptQuestion().attemptLinePlot(useHint, answerchoice, dataIndex);

            if (questionType.contains("rangeplotter"))
                new AttemptQuestion().attemptRangePlotter(useHint, answerchoice, dataIndex);

            if (questionType.contains("fractioneditor"))
                new AttemptQuestion().attemptFractionEditor(useHint, answerchoice, dataIndex);

            if (questionType.contains("graphing"))
                new AttemptQuestion().attemptGraphing(useHint, answerchoice, dataIndex);

            if(questionType.equals("essay"))
                new AttemptQuestion().essayType(useHint,answerchoice,dataIndex);

            if(questionType.equals("graphplacement"))
                new AttemptQuestion().graphPlacement(useHint,answerchoice,dataIndex);

            if(questionType.equals("pictograph"))
                new AttemptQuestion().attemptpictograph(useHint,answerchoice,dataIndex);

            if(questionType.equals("sentencecorrection"))
                new AttemptQuestion().sentenceCorrection(useHint,answerchoice,dataIndex);

            if(questionType.equals("multipart"))
                new AttemptQuestion().multipart(useHint,answerchoice,dataIndex);

            try {
                driver.findElement(By.id("as-take-next-question")).click();//click on next
            } catch (Exception e) {
                try {
                    driver.findElement(By.cssSelector("span[class='btn sty-blue save submit-button']")).click();//click on Finish
                } catch (Exception e1) {
                    Assert.fail("Exception in clicking Finish button while attempting the assignment", e1);
                }
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting questions");
        }


    }
    public void submitAssignmentWithMixResponse(int dataIndex,int correctQuestions,int totalNumberOfQuestions) {
        try {
            WebDriver driver=Driver.getWebDriver();
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String newassessmentname = ReadTestData.readDataByTagName("", "newassessmentname", Integer.toString(dataIndex));
            String usehint = ReadTestData.readDataByTagName("", "usehint", Integer.toString(dataIndex));
            String writeboarddata = ReadTestData.readDataByTagName("", "writeboarddata", Integer.toString(dataIndex));
            String submitFirstAssignment = ReadTestData.readDataByTagName("", "submitFirstAssignment", Integer.toString(dataIndex));
            new Navigator().navigateTo("assignment");
            int index = 0;
            new ExplicitWait().explicitWaitByCssSelector("h4.as-title", 30);

            if(newassessmentname!=null)
            {
                assessmentname=newassessmentname;
            }

            if(submitFirstAssignment == null || submitFirstAssignment.equals("") || submitFirstAssignment.equals("false")) {
                List<WebElement> allAssignment = driver.findElements(By.cssSelector("h4.as-title"));
                for (WebElement assignment : allAssignment) {

                    int lastLocOfSpace=assignment.getText().indexOf("IT");
                    if (assignment.getText().contains(assessmentname)) {
                        break;
                    } else
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", assignment);
                    index++;
                }
            }
            else if(submitFirstAssignment.equals("true")){
                index = 0;
            }
            if(driver.findElements(By.cssSelector("h4.as-title")).size()>1)
                new Click().clickbylistcssselector("h4.as-title", index);//click on Assignment
            else
                new Click().clickBycssselector("h4.as-title");

            boolean useHint = false;//by default useHint is set to false
            if(usehint != null)//read the tag from testdata
                useHint = true;//set useHint to true


            Thread.sleep(20000);

            String answerchoice;
            int timer = 1;
            for(int i=1;i<=totalNumberOfQuestions;i++)
            {
                if(correctQuestions>=i)
                    answerchoice = "correct";
                else
                    answerchoice="incorrect";
                new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.id("question-edit")));
                String questionText = new TextFetch().textfetchbyid("question-edit");

                if(questionText.contains("True False"))
                    new AttemptQuestion().trueFalse(useHint, answerchoice, dataIndex);

                if(questionText.contains("Multiple Choice"))
                    new AttemptQuestion().multipleChoice(useHint, answerchoice, dataIndex);

                if(questionText.contains("Multi Selection"))
                    new AttemptQuestion().multipleSelection(useHint, answerchoice, dataIndex);

                if(questionText.contains("Text Entry"))
                    new AttemptQuestion().textEntry(useHint, answerchoice, dataIndex);

                if(questionText.contains("Text Drop Down"))
                    new AttemptQuestion().textDropDown(useHint, answerchoice, dataIndex);

                if(questionText.contains("Numeric Entry With Units"))
                    new AttemptQuestion().numericEntryWithUnits(useHint, answerchoice, dataIndex);

                if(questionText.contains("Numeric Type"))
                    new AttemptQuestion().numeric(useHint, answerchoice, dataIndex);

                if(questionText.contains("Expression Evaluator"))
                    new AttemptQuestion().expressionEvaluator(useHint, answerchoice, dataIndex);

                if(questionText.contains("Match the Following"))
                    new AttemptQuestion().matchTheFollowing(useHint, answerchoice, dataIndex);

                if(questionText.contains("Drag and Drop"))
                    new AttemptQuestion().dragAndDrop(useHint, answerchoice, dataIndex);

                if (questionText.contains("Classification"))
                    new AttemptQuestion().attemptClassification(useHint, answerchoice, dataIndex);

                if(questionText.contains("Cloze Formula"))
                    new AttemptQuestion().attemptClozeFormula(useHint, answerchoice, dataIndex);

                if(questionText.contains("Cloze Matrix"))
                    new AttemptQuestion().attemptClozeMatrix(useHint,answerchoice,dataIndex);

                if(questionText.contains("Essay"))
                    new AttemptQuestion().essayType(useHint, answerchoice, dataIndex);

                if(questionText.contains("Graphing"))
                    new AttemptQuestion().attemptGraphing(useHint,answerchoice,dataIndex);

                if(questionText.contains("Graph Plotter"))
                    new AttemptQuestion().attemptGraphPlotter(useHint,answerchoice,dataIndex);

                if(questionText.contains("Sentence correction"))
                    new AttemptQuestion().sentenceCorrection(useHint, answerchoice, dataIndex);

                if (questionText.contains("Sentence Response"))
                    new AttemptQuestion().attemptSentenceResponse(useHint, answerchoice, dataIndex);

                if(questionText.contains("Label An Image(Text)"))
                    new AttemptQuestion().attemptLabelAnImageText(useHint,answerchoice,dataIndex);

                if(questionText.contains("Label An Image(Dropdown)"))
                    new AttemptQuestion().attemptLabelAnImageDropdown(useHint,answerchoice,dataIndex);

                if(questionText.contains("Pictograph"))
                    new AttemptQuestion().attemptpictograph(useHint, answerchoice, dataIndex);

                if(questionText.contains("Graph Placement"))
                    new AttemptQuestion().graphPlacement(useHint, answerchoice, dataIndex);

                if(questionText.contains("Number Line"))
                    new AttemptQuestion().attemptNumberLine(useHint,answerchoice,dataIndex);

                if(questionText.contains("Line Plot"))
                    new AttemptQuestion().attemptLinePlot(useHint,answerchoice,dataIndex);

                if(questionText.contains("Range Plotter"))
                    new AttemptQuestion().attemptRangePlotter(useHint,answerchoice,dataIndex);

                if(questionText.contains("Fraction editor"))
                    new AttemptQuestion().attemptFractionEditor(useHint,answerchoice,dataIndex);

                if(questionText.contains("Matching Tables"))
                    new AttemptQuestion().attemptMatchingTables(useHint,answerchoice,dataIndex);

                if(questionText.contains("Resequence"))
                    new AttemptQuestion().attemptResequence(useHint,answerchoice,dataIndex);

                if(questionText.contains("Multipart : MultipleChoice Question")) {
                    new AttemptQuestion().multipart(useHint, answerchoice, dataIndex);
                }
                if(questionText.contains("Multipart : TextDropdown Question"))
                    new AttemptQuestion().multipart(useHint,answerchoice,dataIndex);

                if(questionText.contains("Multipart : AdvanceNumeric"))
                    new AttemptQuestion().multipart(useHint,answerchoice,dataIndex);



                if(writeboarddata != null){
                    new WriteBoard().enterTextInWriteBoard(writeboarddata, dataIndex);
                }

                try {
                    Thread.sleep(4000);

                    driver.findElement(By.id("as-take-next-question")).click();//click on next
                }
                catch (Exception e) {
                    try {
                        Thread.sleep(4000);
                        driver.findElement(By.cssSelector("span[class='btn sty-blue save submit-button']")).click();//click on Finish
                    }
                    catch (Exception e1) {
                        Assert.fail("Exception in clicking Finish button while attempting the assignment",e1);
                    }
                }

                if(questionText.contains("Expression Evaluator")) Thread.sleep(5000);
                else Thread.sleep(3000);
                try {
                    if (driver.findElement(By.id("assessmentTimer")).isDisplayed()) {
                        timer = 1;
                    }else
                        timer=0;
                }
                catch (Exception e){
                    timer=0;
                }

            }
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[starts-with(@class,'btn sty-blue submit-button')]")));
            new Common().waitForUIBlockerToClose(60);
            driver.findElement(By.xpath("//span[starts-with(@class,'btn sty-blue submit-button')]")).click();//click on Submit
            ReportUtil.log("Submit Assignment with mix Response","Assignmnet is submitted by student successfully","pass");
        } catch (Exception e) {
            Assert.fail("Exception in app helper submitAssignment in class Assignment.", e);
        }
    }

    /*
    /*
    @Author Sumit
    click On Student Response from Assignment page
    */
    public void clickOnStudentResponse(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String submitFirstAssignment = ReadTestData.readDataByTagName("", "submitFirstAssignment", Integer.toString(dataIndex));
            new Navigator().navigateTo("assignment");
            int index = 0;
            if(submitFirstAssignment == null || submitFirstAssignment.equals("") || submitFirstAssignment.equals("false")) {
                List<WebElement> allAssignment = driver.findElements(By.cssSelector("div.as-label.ellipsis"));
                for (WebElement assignment : allAssignment) {
                    if (assignment.getText().substring(6).trim().equals(assessmentname)) {
                        break;
                    } else
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", assignment);
                    index++;
                }
            }
            else if(submitFirstAssignment.equals("true")) {
                index = 0;
            }
            new Click().clickbylistcssselector("span[class='as-response']", index);//click on Student Response
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper clickOnStudentResponse in class Assignment.", e);
        }
    }
    /*
    @Author Sumit
    Update Assignment from Assignment page
    */
    public void updateAssignment(int dataIndex, String... appendCharacter)
    {
        WebDriver driver=Driver.getWebDriver();
        String appendCharacterBuild=System.getProperty("UCHAR");
        if (appendCharacterBuild!=null)
            appendCharacter[0]=appendCharacter[0]+appendCharacterBuild;
        try
        {
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
            String tags = ReadTestData.readDataByTagName("", "tags", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Navigator().navigateTo("assignment");
            int index = 0;
            List<WebElement> allAssignment = driver.findElements(By.cssSelector("div.as-label.ellipsis"));
            for(WebElement assignment:allAssignment)
            {
                if (assignment.getText().substring(6).trim().equals(assessmentname)) {
                    break;
                } else
                    index++;
            }
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='as-update']")));
            new Click().clickbylistcssselector("span[class='as-update']", index);//click on Update Assignment
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String shareName;
            for(int i = 0; i< appendCharacter.length; i++) {
                if (shareWithClass != null) {
                    if (shareWithClass.equalsIgnoreCase("true")) {
                        shareName = methodName + "class" + appendCharacter[i];
                    } else
                        shareName = methodName + "st" + appendCharacter[i];
                } else
                    shareName = methodName + "st" + appendCharacter[i];

                new ShareWith().share(dataIndex, shareName, true);
            }
            if (accessibleafter != null) {
                new Click().clickByid("lsm_assignment_accessible_date");
                new Click().clickbylinkText(accessibleafter);
            }

            new Click().clickByid("lsm_assignment_due_date");//click on Due Date field
            new Click().clickByid("lsm_assignment_due_date");//click on Due Date field , clicking twice opens the calendar(in WebDriver)
            new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
            new Click().clickbylinkText(duedate);//select due date
            new Click().clickByid("lsm_assignment_due_time");//click on Due Time field
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals(duetime)) {
                    time.click();
                    break;
                }
            }
            driver.findElement(By.id("as-description")).click();
            driver.findElement(By.id("as-description")).sendKeys(description);//add description
            driver.findElement(By.id("as-tag")).click();
            driver.findElement(By.id("as-tag")).sendKeys(tags);//add tags
            new Click().clickByid("assign-button");//click on Assign button
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper updateAssignment in class Assignment.", e);
        }
    }
    /*
    @Author Sumit
    returns the count of each status box present in RHS assignment page
     */
    public int statusBoxCount(int dataIndex, String status)
    {
        String [] cntarray = {""};
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Navigator().navigateTo("assignment");//navigate To Assignments
            int index = 0;
            List<WebElement> allAssignment = driver.findElements(By.cssSelector("div.as-label.ellipsis"));
            for(WebElement assignment:allAssignment)
            {
                if (assignment.getText().substring(6).trim().equals(assessmentname)) {
                    break;
                } else
                    index++;
            }
            if(status.equalsIgnoreCase("Not Started")) {
                String count = new TextFetch().textfetchbylistcssselector("div[class='as-assignment-activity-cards as-assignment-first-card as-not-started']", index);
                cntarray = count.split("\\n");
            }
            if(status.equalsIgnoreCase("In Progress")) {
                String count = new TextFetch().textfetchbylistcssselector("div[class='as-assignment-activity-cards as-assignment-second-card as-in-progress']", index);
                cntarray = count.split("\\n");
            }

            if(status.equalsIgnoreCase("Turned In")) {
                String count = new TextFetch().textfetchbylistcssselector("div[class='as-assignment-activity-cards as-assignment-third-card as-submitted']", index);
                cntarray = count.split("\\n");
            }
            if(status.equalsIgnoreCase("Graded")) {
                String count = new TextFetch().textfetchbylistcssselector("div[class='as-assignment-activity-cards as-assignment-fourth-card as-graded']", index);
                cntarray = count.split("\\n");
            }
            if(status.equalsIgnoreCase("Reviewed")) {
                String count = new TextFetch().textfetchbylistcssselector("div[class='as-assignment-activity-cards as-assignment-fourth-card as-graded']", index);
                cntarray = count.split("\\n");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper statusBox in class Assignment.", e);
        }
        return Integer.parseInt(cntarray[0]);
    }

    /*
    @Author Sumit
    returns the Status of an Assignment
     */
    public String assignmentStatus(int dataIndex)
    {
        String status = null;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String submitFirstAssignment = ReadTestData.readDataByTagName("", "submitFirstAssignment", Integer.toString(dataIndex));
            new Navigator().navigateTo("assignment");//navigate To Assignments
            int index = 0;
            if(submitFirstAssignment==null || submitFirstAssignment.equals("false") || submitFirstAssignment.equals("")) {
                List<WebElement> allAssignment = driver.findElements(By.cssSelector("h4.as-title"));
                for (WebElement assignment : allAssignment) {
                    int lastLocOfSpace=0;
                    String actualAssignmentname;
                    if(assignment.getText().contains("GRADABLE"))
                    {
                        lastLocOfSpace = assignment.getText().lastIndexOf(" ");
                        actualAssignmentname=assignment.getText().substring(6,lastLocOfSpace).trim();
                    }
                    else
                    {
                        actualAssignmentname=assignment.getText().substring(6).trim();
                    }

                    if (actualAssignmentname.equals(assessmentname))
                    {
                        break;
                    } else
                    {
                        index++;
                    }
                }
            }
            else if(submitFirstAssignment.equals("true")) {
                index = 0;
            }
            status = new TextFetch().textfetchbylistclass("status-label", index);//get the status
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper assignmentStatus in class Assignment.", e);
        }
        return status;
    }

    /*
    @Author Sumit
    clicks on release Grades of an Assignment
     */
    public void releaseGrades(int dataIndex,String releaseAction)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            clickOnStudentResponse(dataIndex);//click on Student Response of an Assignment
            //new Click().clickBycssselector("button[class='btn gradebook-left-btn release-feed as-gradebook-sprite']");
            driver.findElement(By.xpath("//button[contains(text(),'"+releaseAction+"')]")).click();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper releaseGrades in class Assignment.",e);
        }
    }
    /*
    @Author Sumit
    Assign an existing Assignment to students
     */
    public void useExistingAssignment(int dataIndex, String... appendCharacter)
    {
        String appendCharacterBuild=System.getProperty("UCHAR");
        WebDriver driver=Driver.getWebDriver();

        try
        {
            String daassessmentname = ReadTestData.readDataByTagName("", "daassessmentname", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String gradereleaseoption = ReadTestData.readDataByTagName("", "gradereleaseoption", Integer.toString(dataIndex));
            String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String owner = ReadTestData.readDataByTagName("", "owner", Integer.toString(dataIndex));
            String tags = ReadTestData.readDataByTagName("", "tags", Integer.toString(dataIndex));
            String selectgrade = ReadTestData.readDataByTagName("", "selectgrade", Integer.toString(dataIndex));
            String privacy = ReadTestData.readDataByTagName("", "privacy", Integer.toString(dataIndex));
            String removeExistingClass = ReadTestData.readDataByTagName("", "removeexistingclass", Integer.toString(dataIndex));
            String newassessmentname = ReadTestData.readDataByTagName("", "newassessmentname", Integer.toString(dataIndex));
            String selectgradeAssignPage = ReadTestData.readDataByTagName("", "selectgradeAssignPage", Integer.toString(dataIndex));

            MyAssessments myAssessments=PageFactory.initElements(driver,MyAssessments.class);


            if(daassessmentname!=null) {
                if (daassessmentname.equals("true")) {
                    new Navigator().navigateTo("assessmentLibrary");//navigate to the assessments library

                    if(owner != null)
                    {
                        if(owner.equalsIgnoreCase("me"))
                        {
                            new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[1]");//checking Me radio button for owner
                        }

                        if(owner.equalsIgnoreCase("community"))
                        {
                            new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[2]"); //checking Me radio button for owner
                        }

                        if(owner.equalsIgnoreCase("district"))
                        {
                            new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[3]"); //checking district radio button for owner
                        }
                    }

                    driver.findElement(By.xpath(".//*[@id='select2-sortSelection-container']")).click();
                    List<WebElement> sortElements=driver.findElements(By.xpath("//ul[@id='select2-sortSelection-results']/li"));
                    sortElements.get(3).click();
                    driver.findElement(By.id("search-fld")).sendKeys(assessmentname);
                    driver.switchTo().activeElement().sendKeys(Keys.ENTER);

                    Thread.sleep(2000);
                    if(selectgrade!=null)
                    {
                        driver.findElement(By.className("select2-search__field")).click();
                        Thread.sleep(1000);
                        driver.findElement(By.xpath("//*[contains(@id,'"+selectgrade+"')]")).click();

                    }
                    Thread.sleep(2000);
                    int index=0;
                    List<WebElement> allAssessment = myAssessments.assignmentList;
                    for (WebElement assessment : allAssessment) {
                        if (assessment.getText().contains(assessmentname)) {
                            break;
                        } else
                            index++;
                    }
                    myAssessments.assignmentList.get(index).click();//click on Assignment
                    new Click().clickBycssselector("span[class='action-label m-l-sm']");

                    driver.findElement(By.id("as-description")).sendKeys(description);//add description
                    driver.findElement(By.id("as-tag")).click();
                    driver.findElement(By.id("as-tag")).sendKeys(tags);//add tags

                    WebDriverUtil.waitUntilTextToBePresentInElement(driver.findElement(By.xpath("//label[text()='Due Date']")),"Due Date",60);
                    try {
                        driver.findElement(By.id("assessment-close-date")).click();
                    } catch (Exception e) {
                        WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("assessment-close-date")));//click on Due Date field
                    }
                    WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//span[@title='Next Month']")),5);
                    new Click().clickByXpath("//span[@title='Next Month']");
                    Thread.sleep(2000);
                    new Click().clickByXpath("//*[text()='"+duedate+"']");//select due date

                    if(selectgradeAssignPage!=null)
                    {
                        driver.findElement(By.className("select2-search__field")).click();
                        Thread.sleep(1000);
                        driver.findElement(By.xpath("//*[contains(@id,'"+selectgradeAssignPage+"')]")).click();
                    }
                }
                new Click().clickByid("assign-to-class-btn");//Click on assign button

                if(driver.findElements(By.className("as-assignment-questions-modal-msg-wrapper")).size()>0)
                {
                    driver.findElement(By.cssSelector("div[class='as-modal-yes-btn yes-delete ']")).click();
                }
            }
            else
            {
                new Navigator().navigateTo("assignment");//navigate To Assignments
                WebDriverUtil.waitForAjax(driver, 60);
                new Click().clickBycssselector("a.btn.btn-blue.btn-rounded");//click on Create New Assignment button
                WebDriverUtil.waitForAjax(driver, 60);
                new Click().clickByid("search-assessment-with-val");//click on Search
                WebDriverUtil.waitForAjax(driver, 60);

                if(owner != null) //select sharing level
                {
                    if(owner.equalsIgnoreCase("me"))
                    {
                        new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[1]");//checking Me radio button for owner
                    }

                    if(owner.equalsIgnoreCase("community"))
                    {
                        new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[2]"); //checking Me radio button for owner
                    }

                    if(owner.equalsIgnoreCase("district"))
                    {
                        new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[3]"); //checking district radio button for owner
                    }

                    if(owner.equalsIgnoreCase("school"))
                    {
                        new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[4]");//checking school radio button for owner

                    }
                    WebDriverUtil.waitForAjax(driver, 60);
                }

                if(selectgrade!=null) //select grade
                {
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("select2-search__field")));
                    Thread.sleep(2000);
                    driver.findElement(By.xpath("//*[contains(@id,'"+selectgrade+"')]")).click();
                    WebDriverUtil.waitForAjax(driver,60);
                }

                //search specific assignment
                driver.findElement(By.id("search-fld")).clear();
                driver.findElement(By.id("search-fld")).sendKeys(assessmentname);
                driver.switchTo().activeElement().sendKeys(Keys.ENTER);
                WebDriverUtil.waitForAjax(driver,60);
                new Common().waitForUIBlockerToClose(120);

                //sort by recency
                WebDriverUtil.waitUntilTextToBePresentInElement(driver.findElement(By.cssSelector("span[title='Rating']")),"Rating",60);
                try {
                    driver.findElement(By.cssSelector("[aria-labelledby='select2-sortSelection-container']>span:nth-child(2)")).click();
                } catch (Exception e) {
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("(//span[@class='selection']/span/span[2])[2]")));
                }
                Thread.sleep(1000);
                driver.findElement(By.xpath("//ul[@class='select2-results__options']/li[.='Recency']")).click();
                WebDriverUtil.waitForAjax(driver, 60);

                //open assignment
                new Common().waitForUIBlockerToClose(60);
                boolean assessmentPresent = false;
                System.out.println(assessmentname);
                List<WebElement> allAssignment =  myAssessments.assignmentList;
                for(WebElement assignment:allAssignment)
                {
                    if (assignment.getText().trim().contains(assessmentname))
                    {
                        assignment.click();//Select the assignment
                        assessmentPresent = true;
                        break;
                    }

                }
                if(!assessmentPresent)
                {
                    Assert.fail("Assessment is not present");
                }

                new Click().clickBycssselector("span[class='action-label m-l-sm']");//Click on Assign
                String methodName = new Exception().getStackTrace()[1].getMethodName();
                boolean removeExistingShare = true;
                if(removeExistingClass!=null){
                    removeExistingShare=false;
                }
                String shareName;
                for(int i=0;i<appendCharacter.length;i++) {
                    if (shareWithClass != null) {
                        if (shareWithClass.equalsIgnoreCase("true")) {
                            if(appendCharacterBuild!=null)
                            {
                                shareName = methodName + "class" + appendCharacter[i] +appendCharacterBuild;
                            }
                            else
                            {  shareName = methodName + "class" + appendCharacter[i] ;}

                        } else {
                            if (appendCharacterBuild != null) {
                                shareName = methodName + "st" + appendCharacter[i] + appendCharacterBuild;
                            } else {
                                shareName = methodName + "st" + appendCharacter[i];
                            }
                        }
                    } else {
                        if (appendCharacterBuild != null) {
                            shareName = methodName + "st" + appendCharacter[i] + appendCharacterBuild;
                        } else
                            shareName = methodName + "st" + appendCharacter[i];
                    }
                    if(i>0)
                        removeExistingShare = false;
                    new ShareWith().share(dataIndex, shareName, removeExistingShare);
                }
                if (gradable.equals("true")) {
                    if (gradereleaseoption != null)
                    {
                        if(driver.findElements(By.className("p-r-md")).size()>0)
                        {
                            driver.findElement(By.className("p-r-md")).click();
                        }
                        if (gradereleaseoption.equals("On assignment submission"))
                            new Click().clickByXpath("//input[@id='as-grade-release-on-submission']/parent::div");//click on On assignment submission
                        if (gradereleaseoption.equals("Explicitly by teacher"))
                            new Click().clickByXpath("//input[@id='as-grade-release-by-teacher']/parent::div");//click on Explicitly by teacher
                    }
                }
                else
                {
                    if(driver.findElements(By.className("p-r-md")).size()>0)
                    {
                        driver.findElement(By.className("p-r-md")).click();
                    }
                    new Click().clickByXpath("//input[@id='show-grades-ques-no']/following-sibling::ins");//click on 'Show Grades' as No
                }
                if (accessibleafter != null) {
                    new Click().clickByid("lsm_assignment_accessible_date");
                    new Click().clickbylinkText(accessibleafter);
                }

                WebDriverUtil.waitUntilTextToBePresentInElement(driver.findElement(By.xpath("//label[text()='Due Date']")),"Due Date",60);
                try {
                    driver.findElement(By.id("assessment-close-date")).click();
                } catch (Exception e) {
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("assessment-close-date")));//click on Due Date field
                }

                WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//span[@title='Next Month']")),5);
                new Click().clickByXpath("//span[@title='Next Month']");
                Thread.sleep(2000);
                new Click().clickByXpath("//*[text()='"+duedate+"']");//select due date                driver.findElement(By.id("as-description")).click();
                driver.findElement(By.id("as-description")).sendKeys(description);//add description
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("as-tag")));
                driver.findElement(By.id("as-tag")).sendKeys(tags);//add tags

                if(newassessmentname!=null)
                {
                    driver.findElement(By.cssSelector(".lsm-createAssignment-input-name.form-control")).clear();
                    driver.findElement(By.cssSelector(".lsm-createAssignment-input-name.form-control")).sendKeys(newassessmentname);
                }

                new Click().clickByid("assign-button");//click on Assign button

                if(driver.findElements(By.className("as-assignment-questions-modal-msg-wrapper")).size()>0)
                {
                    driver.findElement(By.cssSelector("div[class='as-modal-yes-btn yes-delete ']")).click();
                }

            }
            new Click().clickByid("view-assign-button");//Click on view button
            ReportUtil.log("Assign existing assignment to class","Existing assignment is successfully shared to class","pass");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper useExistingAssignment in class Assignment.",e);
        }
    }

    public void assignWhileRemovingEssayType(int dataIndex,String... appendCharacter)
    //Remove essay type question from passage based and multipart question(having essay type question)
    {
        String privacy = ReadTestData.readDataByTagName("", "privacy", Integer.toString(dataIndex));
        String owner = ReadTestData.readDataByTagName("", "owner", Integer.toString(dataIndex));
        String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
        String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
        String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
        String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
        String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
        String gradereleaseoption = ReadTestData.readDataByTagName("", "gradereleaseoption", Integer.toString(dataIndex));
        String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String tags = ReadTestData.readDataByTagName("", "tags", Integer.toString(dataIndex));
        String removeExistingClass = ReadTestData.readDataByTagName("", "removeexistingclass", Integer.toString(dataIndex));
        String selectgrade = ReadTestData.readDataByTagName("", "selectgrade", Integer.toString(dataIndex));


        String appendCharacterBuild=System.getProperty("UCHAR");
        try
        {
            WebDriver driver=Driver.getWebDriver();
            new Navigator().navigateTo("assessmentLibrary");//navigate to the assessments library

            if(owner != null)
            {
                if(owner.equalsIgnoreCase("me"))
                {
                    new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[1]");//checking Me radio button for owner
                }

                if(owner.equalsIgnoreCase("community"))
                {
                    new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[2]"); //checking Me radio button for owner
                }

                if(owner.equalsIgnoreCase("district"))
                {
                    new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[3]"); //checking district radio button for owner
                }
            }

           /* WebDriverUtil.waitUntilTextToBePresentInElement(driver.findElement(By.cssSelector("span[title='Rating']")),"Rating",60);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElements(By.cssSelector(".select2-selection__arrow>b")).get(1));
            List<WebElement> sortElements=driver.findElements(By.xpath("//ul[@id='select2-sortSelection-results']/li"));
            sortElements.get(2).click();*/

            driver.findElement(By.id("search-fld")).sendKeys(assessmentname);
            driver.switchTo().activeElement().sendKeys(Keys.ENTER);
            Thread.sleep(2000);
            if(selectgrade!=null)
            {
                driver.findElement(By.className("select2-search__field")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[contains(@id,'"+selectgrade+"')]")).click();

            }

            MyAssessments myAssessments=PageFactory.initElements(driver,MyAssessments.class);
            int index=0;
            boolean assessmentPresent = false;
            List<WebElement> allAssessment = myAssessments.assignmentList;
          /*  for (WebElement assessment : allAssessment) {
                if (assessment.getText().contains(assessmentname)) {
                    assessmentPresent = true;
                    break;
                } else
                    index++;
            }

            if(!assessmentPresent)
            {
                Assert.fail("Assessment is not present");
            }*/

            myAssessments.assignmentList.get(allAssessment.size()-1).click();//click on Assignment

            new Click().clickbylistcssselector("span[class='action-label m-l-sm']",2);//Click on create a new version


            List<WebElement> questionNames = driver.findElements(By.xpath("//div[contains(@class,'lsm-createAssignment-Question')]/div/following-sibling::div"));
            List<WebElement> crossIcon = driver.findElements(By.cssSelector("label[class^='ed-icon icon-close-icon']"));

            for(int i =0;i<questionNames.size();i++)
            {
                if(questionNames.get(i).getText().contains("Essay"))
                {
                    crossIcon.get(i).click();//Remove essay type and multipart question type
                }
            }

            new Click().clickBycssselector("span[class='as-questionDetails-clickArrow passage-clickArrow']");//Click on add or edit link
            new Click().clickbylinkText("Add More or Edit");//Click on add more or edit link
            Thread.sleep(2000);
            new Click().clickByXpath("(//*[starts-with(@class,'qtn-check ed-icon icon-close-icon')])[5]");//Remove essay tpe question from passage based
            new Click().clickByid("saveQuestionDetails1");//Click on save button
            WebDriverWait wait=new WebDriverWait(driver,60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Successfully saved.']")));
            Thread.sleep(1000);

            new Click().clickByXpath("//button[starts-with(@class,'btn btn-rounded btn-default lsm-createAssignment-done')]");//Click on review assessment


            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("assessments-use-button")));//Click on publish or assign button

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@id='assign-now']/parent::div")));//Click on assign now radio button

            String methodName = new Exception().getStackTrace()[1].getMethodName();
            boolean removeExistingShare = true;
            if(removeExistingClass!=null){
                removeExistingShare=false;

                String shareName;
                for(int i=0;i<appendCharacter.length;i++) {
                    if (shareWithClass != null) {
                        if (shareWithClass.equalsIgnoreCase("true")) {
                            if(appendCharacterBuild!=null)
                            {
                                shareName = methodName + "class" + appendCharacter[i] +appendCharacterBuild;
                            }
                            else
                            {  shareName = methodName + "class" + appendCharacter[i] ;}

                        } else {
                            if (appendCharacterBuild != null) {
                                shareName = methodName + "st" + appendCharacter[i] + appendCharacterBuild;
                            } else {
                                shareName = methodName + "st" + appendCharacter[i];
                            }
                        }
                    } else {
                        if (appendCharacterBuild != null) {
                            shareName = methodName + "st" + appendCharacter[i] + appendCharacterBuild;
                        } else
                            shareName = methodName + "st" + appendCharacter[i];
                    }
                    if(i>0)
                        removeExistingShare = false;
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@id='assign-now']/parent::div")));

                    new ShareWith().share(dataIndex, shareName, removeExistingShare);
                }

                if (gradable.equals("true")) {
                    if (gradereleaseoption != null) {
                        if(driver.findElements(By.className("p-r-md")).size()>0)
                        {
                            driver.findElement(By.className("p-r-md")).click();
                        }
                        if (gradereleaseoption.equals("On assignment submission"))
                            new Click().clickByXpath("//input[@id='as-grade-release-on-submission']/parent::div");//click on On assignment submission
                        if (gradereleaseoption.equals("Explicitly by teacher"))
                            new Click().clickByXpath("//input[@id='as-grade-release-by-teacher']/parent::div");//click on Explicitly by teacher
                    }
                }
                else
                {
                    if(driver.findElements(By.className("p-r-md")).size()>0)
                    {
                        driver.findElement(By.className("p-r-md")).click();
                    }
                    new Click().clickByXpath("//input[@id='show-grades-ques-no']/following-sibling::ins");//click on 'Show Grades' as No
                }
                if (accessibleafter != null) {
                    new Click().clickByid("lsm_assignment_accessible_date");
                    new Click().clickbylinkText(accessibleafter);
                }
                WebDriverUtil.waitUntilTextToBePresentInElement(driver.findElement(By.xpath("//label[text()='Due Date']")),"Due Date",60);
                try {
                    driver.findElement(By.id("assessment-close-date")).click();
                } catch (Exception e) {
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("assessment-close-date")));//click on Due Date field
                }
                WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//span[@title='Next Month']")),5);
                new Click().clickByXpath("//span[@title='Next Month']");
                Thread.sleep(2000);
                new Click().clickByXpath("//*[text()='"+duedate+"']");//select due date
                if (privacy != null) {
                    if (privacy.equalsIgnoreCase("Private"))
                        new Click().clickByXpath("//input[@id='private']/parent::div");//click on Private Option
                    if (privacy.equalsIgnoreCase("district")){
                        new Click().clickByXpath("//input[@id='district']/parent::div");//click on District Option
                    }
                }
                driver.findElement(By.id("as-description")).click();
                driver.findElement(By.id("as-description")).sendKeys(description);//add description


                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("as-tag")));
                driver.findElement(By.id("as-tag")).sendKeys(tags);//add tags
                new Click().clickByid("assign-button");//click on Assign button
                new Click().clickByid("view-assign-button");//Click on view
            }


        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper assignWhileRemovingEssayType in class Assignment.",e);

        }
    }

    /*
    @Author Sumit
    Customize an existing Assignment to students
     */
    public void customizeExistingAssignment(int dataIndex, String questionType)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String newassessmentname = ReadTestData.readDataByTagName("", "newassessmentname", Integer.toString(dataIndex));
            new Navigator().navigateTo("assignment");//navigate To Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +new Assignment button
            new Click().clickBycssselector("div.as-assignment-flow-link-title");//click on use Existing Assignment
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            List<WebElement> allAssignment = driver.findElements(By.cssSelector("div[class='assessment-name ellipsis']"));
            for(WebElement assignment:allAssignment) {
                if (assignment.getText().trim().equals(assessmentname)) {
                    assignment.click();//click on assignment
                    break;
                }
            }
            new Click().clickByid("assessments-customised-button");//click on Customize button
            new Click().clickByid("assessments-back-button");//click on Add More button
            new Click().clickBycssselector("span.lsm-createWithOnlyName-btn.lsm-elo-createWithOnlyName-btn");//click on Create link
            if(newassessmentname != null) {
                driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
                driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys(newassessmentname);//give the assignment a new name
            }
            if (questionType.equals("truefalse"))
                new QuestionCreate().trueFalseQuestions(dataIndex);

            new Click().clickByid("saveQuestionDetails1");//click on save button
            new Click().clickBycssselector("div[class='lsm-createAssignment-done selected']");//click on save button Review Button
            new Click().clickByid("assessments-cancel-button"); //click on Close button
            new Click().clickBycssselector("i[class='as-modal-sprite-img as-modal-rgtArw']");//click on Yes link of pop-up
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper useExistingAssignment in class Assignment.",e);
        }
    }
    /*
    @Author Sumit
    Get status count of each box
    */
    public List<Integer> allStatusBoxCount(int dataIndex)
    {
        List<Integer> allCount = new ArrayList<>();
        WebDriver driver=Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Navigator().navigateTo("assignment");//navigate To Assignments
            int index = 0;
            List<WebElement> allAssignment = driver.findElements(By.cssSelector("div.as-label.ellipsis"));
            for(WebElement assignment:allAssignment)
            {
                if (assignment.getText().substring(6).trim().equals(assessmentname)) {
                    break;
                } else
                    index++;
            }

            String [] cntarray;
            String cnt;
            if(driver.findElements(By.xpath("//div[text()='Assigned']/following-sibling::div")).size()>0) {
                cnt = new TextFetch().textfetchbylistXpath("//div[text()='Assigned']/following-sibling::div", index);
                cntarray = cnt.split("\\n");
                int assignedCount = Integer.parseInt(cntarray[0]);
                allCount.add(assignedCount);
            }

            if(driver.findElements(By.xpath("//div[text()='Turned In']/following-sibling::div")).size()>0) {
                cnt = new TextFetch().textfetchbylistXpath("//div[text()='Turned In']/following-sibling::div", index);
                cntarray = cnt.split("\\n");
                int turnedInCount = Integer.parseInt(cntarray[0]);
                allCount.add(turnedInCount);
            }

            if(driver.findElements(By.xpath("//div[text()='Reviewed']/following-sibling::div")).size()>0) {
                cnt = new TextFetch().textfetchbylistXpath("//div[text()='Reviewed']/following-sibling::div", index);
                cntarray = cnt.split("\\n");
                int reviewedCount = Integer.parseInt(cntarray[0]);
                allCount.add(reviewedCount);
            }

            if(driver.findElements(By.xpath("//div[text()='Graded']/following-sibling::div")).size()>0) {
                cnt = new TextFetch().textfetchbylistXpath("//div[text()='Graded']/following-sibling::div", index);
                cntarray = cnt.split("\\n");
                int gradedCount = Integer.parseInt(cntarray[0]);
                allCount.add(gradedCount);
            }

        } catch (Exception e) {
            Assert.fail("Exception in app helper statusBoxCheckInInstructorDashBoard in class Assignment.", e);
        }
        return allCount;
    }
    /*
    @Author Sumit
    open Assignment from Assignment page
    */
    public void openAssignment(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Navigator().navigateTo("assignment");
            int index = 0;
            new ExplicitWait().explicitWaitByCssSelector("h4.as-title", 10);
            List<WebElement> allAssignment = driver.findElements(By.cssSelector("h4.as-title"));
            for (WebElement assignment : allAssignment)
            {
                if (assignment.getText().trim().equals(assessmentname)) {
                    break;
                } else
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", assignment);
                index++;
            }
            new Click().clickbylistcssselector("h4.as-title", index);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper openAssignment in class Assignment.",e);
        }
    }

    public void selectExistingAssignment() {
        try {
            WebDriver driver=Driver.getWebDriver();
            new Navigator().navigateTo("assignment"); //navigate to assignment
            Thread.sleep(3000);
            new Click().clickByXpath("//a[@class='btn btn-blue btn-rounded']");//click on Create New Assignment button
            new Click().clickByid("search-assessment-with-val");//click on search
        }
        catch (Exception e) {
            Assert.fail("Exception in app helper selectExistingAssignment in class Assignment.",e);
        }
    }

    public void selectExistingRecentAssignment(int dataIndex) {
        WebDriver driver=Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Navigator().navigateTo("assignment"); //navigate to assignment
            Thread.sleep(3000);

            new Click().clickByclassname("instructor-assignment-new-txt");//click on the new Assignment
            new Click().clickByclassname("as-assignment-flow-link-title");//click on the use assignment
            Select select= new Select(driver.findElement(By.xpath("//div[@id='lsm-createAssignment-subject-dropDown']/select")));
            select.selectByIndex(0); //select 2nd subject
            Select select1=new Select(driver.findElement(By.xpath("//div[@id='lsm-createAssignment-grade-dropDown']/select")));
            select1.selectByIndex(1);//select a grade
            new Click().clickByid("one"); //select owner as Community
            Thread.sleep(3000);
            if(driver.findElements(By.cssSelector("div[class='assessment-name ellipsis']")).size()!=0){
                new Click().clickBycssselector("div[class='assessment-name ellipsis']");//click on the assignment
            }else{
                new Click().clickBycssselector("div[class='as-label ellipsis']");//click on the assignment
            }

        }
        catch (Exception e) {
            Assert.fail("Exception in app helper selectExistingRecentAssignment in class Assignment.",e);
        }
    }

    public void selectCommunity()
    {

        try {
            new Click().clickByid("select2-gradeSelection-container");
            new Click().clickByXpath("//li[text()=' Grade 1 ']");//Select Grade 1
            new Click().clickByid("select2-subjectSelection-container");
            new Click().clickByXpath("//span[@title=' Math - Common Core ']");//Select subject
            new Click().clickByXpath("//input[@value='3']/parent::div"); //select owner as Community
        } catch (Exception e) {
            Assert.fail("Exception in app helper selectCommunity in class Assignment.",e);
        }
    }

    public void selectOwner(String dataIndex, String ownerName)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String subject = ReadTestData.readDataByTagName("", "subject",dataIndex);
            String grade = ReadTestData.readDataByTagName("", "grade", dataIndex);
            if(subject != null) {
                Select select = new Select(driver.findElement(By.xpath("//div[@id='lsm-createAssignment-subject-dropDown']/select")));
                select.selectByVisibleText(subject); //select subject
            }
            if(grade != null) {
                new Click().clickByid("select2-gradeSelection-container");//Click on grade
                new Click().clickByXpath("//li[contains(text(),'"+grade+"')]");//select a grade
            }

            if(ownerName.equalsIgnoreCase("Me")) {
                new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[1]");
            }
            if(ownerName.equals("Community"))
            {
                new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[2]");
            }
            if(ownerName.equals("District"))
            {
                new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[3]");
            }
            if(ownerName.equals("School"))
            {
                new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[4]");
            }
        } catch (Exception e) {
            Assert.fail("Exception in app helper selectOwner in class Assignment.",e);
        }
    }

    public void openUseExistingAssignment(int index, String ownerName){
        try{
            WebDriver driver=Driver.getWebDriver();

            String selectgrade = ReadTestData.readDataByTagName("", "selectgrade", Integer.toString(index));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));

            MyAssessments myAssessments=PageFactory.initElements(driver, MyAssessments.class);

            new Navigator().navigateTo("assessmentLibrary");//navigate to the assessments library

            if(ownerName.equalsIgnoreCase("me"))
            {
                new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[1]");//checking Me radio button for owner
            }

            if(ownerName.equalsIgnoreCase("community"))
            {
                new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[2]"); //checking community radio button for owner
            }

            if(ownerName.equalsIgnoreCase("district"))
            {
                new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[3]"); //checking district radio button for owner
            }
            Thread.sleep(2000);
           /* WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("#select2-sortSelection-container")));
            List<WebElement> sortElements=driver.findElements(By.xpath("//ul[@id='select2-sortSelection-results']/li"));
            sortElements.get(2).click();*/
            driver.findElement(By.id("search-fld")).sendKeys(assessmentname);
            driver.switchTo().activeElement().sendKeys(Keys.ENTER);

            Thread.sleep(1000);
            if(selectgrade!=null)
            {
                driver.findElement(By.className("select2-search__field")).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("//*[contains(@id,'"+selectgrade+"')]")).click();

            }
            Thread.sleep(1000);
            int dataIndex=0;
            List<WebElement> allAssessment = myAssessments.assignmentList;
            for (WebElement assessment : allAssessment) {
                if (assessment.getText().equals(assessmentname)) {
                    break;
                } else{
                    dataIndex++;
                }

            }
            myAssessments.assignmentList.get(allAssessment.size()-1).click();//click on Assignment

        }
        catch(Exception e){
            Assert.fail("Exception in app helper openUseExistingAssignment in class Assignment.",e);
        }
    }

    public void assignExistingAssignmentToStudent(int dataIndex, String... appendCharacter) {
        String appendCharacterBuild=System.getProperty("UCHAR");
        WebDriver driver=Driver.getWebDriver();
        if (appendCharacterBuild!=null)
            appendCharacter[0]=appendCharacter[0]+appendCharacterBuild;
        try {
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String className = ReadTestData.readDataByTagName("", "className", Integer.toString(dataIndex));
            String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String gradereleaseoption = ReadTestData.readDataByTagName("", "gradereleaseoption", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
            String tags = ReadTestData.readDataByTagName("", "tags", Integer.toString(dataIndex));
            String privacy = ReadTestData.readDataByTagName("", "privacy", Integer.toString(dataIndex));
            String removeExistingClass=ReadTestData.readDataByTagName("", "removeexistingclass", Integer.toString(dataIndex));

            new Click().clickByListXpath(driver.findElements(By.xpath("//div[@class='font-semi-bold space-15 assign-title']/a")),0);//Select the 1st assessment
            new Click().clickBycssselector("a[class='as-assignment-assign btn btn-blue btn-rounded btn-block m-t']");//Click on Assign
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String shareName;
            boolean removeExistingShare = true;
            if(removeExistingClass!=null){
                removeExistingShare =false;
            }
            if(appendCharacter.length == 0) {
                shareName = className;
                if (shareWithClass != null) {
                    if (shareWithClass.equalsIgnoreCase("true")) {
                        shareName = className;
                    }
                }

                new ShareWith().share(dataIndex, shareName, removeExistingShare);
            }
            else {
                for (int i = 0; i < appendCharacter.length; i++) {
                    if (shareWithClass != null) {
                        if (shareWithClass.equalsIgnoreCase("true")) {
                            shareName = methodName + "class" + appendCharacter[i];
                        } else
                            shareName = methodName + "st" + appendCharacter[i];
                    } else
                        shareName = methodName + "st" + appendCharacter[i];
                    if (i > 0) removeExistingShare = false;
                    new ShareWith().share(dataIndex, shareName, removeExistingShare);
                }
            }
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).clear();//short label clear
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).sendKeys("sh"+new RandomString().randominteger(2));//short label name
            if (gradable.equals("true")) {
                if (gradereleaseoption != null) {
                    if (gradereleaseoption.equals("On assignment submission"))
                        new Click().clickByXpath("//input[@id='as-grade-release-on-submission']/parent::div");//click on On assignment submission
                    if (gradereleaseoption.equals("Explicitly by teacher"))
                        new Click().clickByXpath("//input[@id='as-grade-release-by-teacher']/parent::div");//click on Explicitly by teacher
                }
            }
            else{
                new Click().clickByXpath("//div[starts-with(@class,'icheckbox_square-green')]/descendant::ins");//click on Gradable Checkbox //uncomment the gradable checkbox

            }
            if (accessibleafter != null) {

                new Click().clickByXpath("//input[@id='start-later']/parent::div");//Click on Later
                new Click().clickByid("lsm_assignment_accessible_date");
                new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
                new Click().clickbylinkText(accessibleafter);
            }
            new Click().clickByid("lsm_assignment_due_date");//click on Due Date field
            new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
            new Click().clickbylinkText(duedate);//select due date
            new Click().clickByid("lsm_assignment_due_time");//click on Due Time field
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals(duetime)) {
                    time.click();
                    break;
                }
            }
            driver.findElement(By.id("as-description")).click();
            driver.findElement(By.id("as-description")).sendKeys(description);//add description
            driver.findElement(By.id("as-tag")).click();
            driver.findElement(By.id("as-tag")).sendKeys(tags);//add tags

            new Click().clickByid("assign-button");//click on Assign button
        } catch (Exception e) {
            Assert.fail("Exception in app helper assignExistingAssignmentToStudent in method assignToStudent.", e);
        }
    }

    //click on Next button of while attempting question
    public void clickOnNextButton(int dataIndex)
    {
        try
        {
            new Click().clickByid("as-take-next-question");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper Assignment in method clickOnNextButton.", e);
        }
    }
    //click on Previous button of while attempting question
    public void clickOnQuestion(int questionIndex, int dataIndex)
    {
        try
        {
            Thread.sleep(2000);
            WebDriver driver=Driver.getWebDriver();
            List<WebElement> allQuestions = driver.findElements(By.xpath("//*[starts-with(@class, 'question-link-label')]"));
            allQuestions.get(questionIndex).click();//click on question
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper Assignment in method clickOnPreviousButton.", e);
        }
    }


    public void createQuestionAsAuthor(int dataIndex,String questionType) {

        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String tloName = ReadTestData.readDataByTagName("", "tloName", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
            String reservforassignment = ReadTestData.readDataByTagName("", "reservforassignment", Integer.toString(dataIndex));
            WebDriver driver=Driver.getWebDriver();

            driver.findElement(By.cssSelector("img[alt=\"" + tloName + "\"]")).click();//click on the TLOName
            if (chapterName == null) {
                new Click().clickBycssselector("div.course-chapter-label.node");
            } else if (courselevel != null) {
                new Click().clickBycssselector("div[class='course-label node']");
            } else {
                List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                for (WebElement chapters : allChapters) {
                    if (chapters.getText().contains(chapterName)) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters);
                        break;
                    }

                }

            }
            driver.findElement(By.cssSelector("div.createWithOnlyName-practice")).click();
            new Click().clickbylinkText("Adaptive Component Diagnostic");
            new Click().clickbylinkText(questiontype);
            int popup = driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
            if(popup == 0) {
                driver.findElement(By.id("assessmentName")).click();
                driver.findElement(By.id("assessmentName")).clear();
                driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
                driver.findElement(By.id("questionSetName")).clear();
                driver.findElement(By.id("questionSetName")).sendKeys(questionset);

                if (reservforassignment == null) {
                    driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
                }

                if (questionType.equals("all")) {
                    new QuestionCreate().trueFalseQuestions(dataIndex);
                    new QuestionCreate().multipleChoice(dataIndex);
                    new QuestionCreate().multipleSelection(dataIndex);
                    new QuestionCreate().textEntry(dataIndex);
                    new QuestionCreate().textDropDown(dataIndex);
                    new QuestionCreate().numericEntryWithUnits(dataIndex);
                    new QuestionCreate().numeric(dataIndex);
                    new QuestionCreate().expressionEvaluator(dataIndex);
                    new QuestionCreate().matchTheFollowing(dataIndex);
                    new QuestionCreate().dragAndDrop(dataIndex);
                    new QuestionCreate().clozeFormula(dataIndex);
                    new QuestionCreate().graphPlotter(dataIndex);
                    new QuestionCreate().clozeMatrix(dataIndex);
                    new QuestionCreate().essay(dataIndex);

                }
                if (questionType.equals("truefalse"))
                    new QuestionCreate().trueFalseQuestions(dataIndex);

                if (questionType.equals("multiplechoice"))
                    new QuestionCreate().multipleChoice(dataIndex);

                if (questionType.equals("multipleselection"))
                    new QuestionCreate().multipleSelection(dataIndex);

                if (questionType.equals("textentry"))
                    new QuestionCreate().textEntry(dataIndex);

                if (questionType.equals("textdropdown"))
                    new QuestionCreate().textDropDown(dataIndex);

                if (questionType.equals("numericentrywithunits"))
                    new QuestionCreate().numericEntryWithUnits(dataIndex);

                if (questionType.equals("numeric"))
                    new QuestionCreate().numeric(dataIndex);

                if (questionType.equals("expressionevaluator"))
                    new QuestionCreate().expressionEvaluator(dataIndex);

                if (questionType.equals("matchthefollowing"))
                    new QuestionCreate().matchTheFollowing(dataIndex);

                if (questionType.equals("draganddrop"))
                    new QuestionCreate().dragAndDrop(dataIndex);

                if (questionType.equals("clozeformula"))
                    new QuestionCreate().clozeFormula(dataIndex);

                if (questionType.equals("graphplotter"))
                    new QuestionCreate().graphPlotter(dataIndex);

                if (questionType.equals("clozematrix"))
                    new QuestionCreate().clozeMatrix(dataIndex);

                if (questionType.equals("resequence"))
                    new QuestionCreate().resequence(dataIndex);

                if (questionType.equals("essay"))
                    new QuestionCreate().essay(dataIndex);

            }
        } catch (Exception e) {
            Assert.fail("Exception While Creating ");
        }

    }

    public void addQuestionsAsAuthor(int dataIndex,String questionType,String assignmentname)
    {
        try {
            String course = "";
            assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
            String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
            String course_name = ReadTestData.readDataByTagName("", "course_name", Integer.toString(dataIndex));
            String tloName = ReadTestData.readDataByTagName("", "tloName", Integer.toString(dataIndex));
            course = course_name;

            addQuestionLink();
            if (questionType.equals("truefalse") || questionType.equals("qtn-type-true-false-img"))
                new QuestionCreate().trueFalseQuestions(dataIndex);

            else if (questionType.equals("multiplechoice") || questionType.equals("qtn-multiple-choice-img"))
                new QuestionCreate().multipleChoice(dataIndex);

            else if (questionType.equals("multipleselection") || questionType.equals("qtn-multiple-selection-img"))
                new QuestionCreate().multipleSelection(dataIndex);

            else if (questionType.equals("textentry") || questionType.equals("qtn-text-entry-img"))
                new QuestionCreate().textEntry(dataIndex);

            else if (questionType.equals("textdropdown") || questionType.equals("qtn-text-entry-drop-down-img"))
                new QuestionCreate().textDropDown(dataIndex);

            else if (questionType.equals("numericentrywithunits") || questionType.equals("qtn-text-entry-numeric-units-img"))
                new QuestionCreate().numericEntryWithUnits(dataIndex);

            else if (questionType.equals("numeric"))
                new QuestionCreate().numeric(dataIndex);

            else if (questionType.equals("expressionevaluator"))
                new QuestionCreate().expressionEvaluator(dataIndex);

            else if (questionType.equals("essay") || questionType.equals("qtn-essay-type"))
                new QuestionCreate().essay(dataIndex);

            else if (questionType.equals("writeboard") || questionType.equals("qtn-writeboard-type-new")) {}
            //new QuestionCreate().writeBoard(dataIndex);


        }


        catch(Exception e)
        {

            Assert.fail("Exception in createWithOnlyName in Apphelper addQuestionAsAuthor in class AssignmentCreate",e);
        }
    }
    public void addQuestionLink()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            Thread.sleep(2000);
            new Click().clickByid("questionOptions");
            Thread.sleep(2000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("addQuestion")));
        }
        catch (Exception e)
        {
            Assert.fail("Exception while clicking on add questions link in CMS",e);
        }

    }
    public void createAssessmentAndShareWithDistrict(int index,String ownership)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            new Navigator().navigateTo("districtAssessments");//Navigate to assessment
            new Click().clickByid("new-assessment-button");//click on the +New Assessment
            new Click().clickByListClassName("as-assignment-flow-link-title",1);//click on the  createWithOnlyName New Assignment
            Select sel=new Select(driver.findElement(By.xpath("//div[@id='lsm-createAssignment-subject-dropDown']/select")));
            sel.selectByIndex(0);//select subject
            sel=new Select(driver.findElement(By.xpath("//div[@id='lsm-createAssignment-grade-dropDown']/select")));
            sel.selectByIndex(0);//select a grade
            new Click().clickBycssselector("span[class='lsm-createWithOnlyName-btn lsm-elo-createWithOnlyName-btn']");//click on the createWithOnlyName besides elos
            new Click().clickByid("qtn-true-false-type");//click on the true false question
            new TextSend().textsendbyid("sample true false question"+index+"", "question-raw-content");
            new Click().clickByclassname("true-false-answer-select");
            new TextSend().textsendbyclass("assignmnet"+index+"", "lsm-createAssignment-input-name");
            new Click().clickByid("saveQuestionDetails1");//click on the save button
            new Click().clickBycssselector("div[class='lsm-createAssignment-done selected']");//click on the review button
            new Click().clickByid("assessments-use-button");//click on the next button
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).clear();//short label clear
            new TextSend().textsendbycssSelector("shor","input[class='lsm-assignment-input total_point width50 lsm-assignment-short-label-input']");
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id(ownership)));//ownership Means is District public and Private
            Thread.sleep(2000);
            new Click().clickByid("share-assessment-button");//click on the share button
        } catch (InterruptedException e) {
            Assert.fail("Exception in method createAssessmentAndShareWithDistrict of class AssignmentCreate",e);
        }
    }

    public void submitPreviewQuestion()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            TrueFalseQuestionCreation trueFalse = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);
            trueFalse.getButton_Preview().click();//Click on Preview button
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);
            trueFalse.getPreview_submit().click();


        } catch (Exception e) {
            Assert.fail("Exception in method submitPreviewQuestion of class AssignmentCreate",e);
        }

    }

    public void selectAssessment(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        MyAssessments myAssessments=PageFactory.initElements(driver,MyAssessments.class);
        String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        try {
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception selectAssessment");
        }
        new Navigator().navigateTo("myAssessments");//Navigate to my assessment page
        new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");//Click on Draft
        int index = 0;
        //List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='as-label ellipsis']"));//as per E8
        List<WebElement> allAssessment = myAssessments.assignmentList;//WILL BE chenge on E9
        for (WebElement assessment : allAssessment)
        {
            if (assessment.getText().equals(assignmentname))
                break;
            else
                index++;

        }
        myAssessments.assignmentList.get(index).click();
        new Click().clickByXpath("//i[@class='ed-icon icon-edit-assesment']/following-sibling::span");//Click on edit button
    }


    public String createByDA(int dataIndex, String questionType) {
        String rubricName=null;
        try {
            WebDriver driver=Driver.getWebDriver();
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String selectgrade = ReadTestData.readDataByTagName("", "selectgrade", Integer.toString(dataIndex));
            String saadmin = ReadTestData.readDataByTagName("", "saadmin", Integer.toString(dataIndex));
            String daadmin = ReadTestData.readDataByTagName("", "daadmin", Integer.toString(dataIndex));

            Assessments assesssments = PageFactory.initElements(driver,Assessments.class);

            if(daadmin!=null && Boolean.parseBoolean(daadmin.trim())) {//Navigate to common assessment
                new Navigator().navigateTo("dashboard");
                driver.findElements(By.cssSelector("a[class^='btn btn-blue btn-rounded']")).get(0).click();

            }
            if(saadmin!=null && Boolean.parseBoolean(saadmin.trim())) {//Navigate to common assessment
                new Navigator().navigateTo("dashboard");
                //driver.findElements(By.cssSelector("a[class^='btn btn-blue btn-rounded']")).get(0).click();
                assesssments.button_createCommonAssessment.click();//Click on create common assessment

            }

            Thread.sleep(2000);
            if(selectgrade!=null)
            {
                while (driver.findElements(By.cssSelector("span[class='select2-selection__choice__remove']")).size()>0) {
                    driver.findElement(By.cssSelector("span[class='select2-selection__choice__remove']")).click();
                }
                Thread.sleep(1000);
                try {
                    driver.findElement(By.cssSelector("input[placeholder='All Grades']")).click();

                } catch (Exception e) {
                }
                driver.findElement(By.xpath("//*[text()='"+selectgrade+"']")).click();
            }


            driver.findElement(By.xpath("//span[@class='select2-selection__arrow']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("(//input[@class='select2-search__field'])[2]")).sendKeys("Math - Common Core");
            Thread.sleep(2000);
            new KeysSend().sendKeyBoardKeys("^");

            driver.findElement(By.id("new-assessment-name")).clear();
            driver.findElement(By.id("new-assessment-name")).sendKeys(assessmentname);//give the assignment title
            new Click().clickByid("new-assessment-name");
            new Click().clickByXpath("//html/body");
            new Click().clickByid("create-assessment-with-val");//click on Create
            new Click().clickBycssselector("button[class^='btn btn-rounded btn-default authorQuestion']");//Click on Author Question

            if (questionType.equals("truefalse"))
                new QuestionCreate().trueFalseQuestions(dataIndex);

            if (questionType.equals("multiplechoice"))
                new QuestionCreate().multipleChoice(dataIndex);

            if(questionType.equals("multipleselection"))
                new QuestionCreate().multipleSelection(dataIndex);

            if(questionType.equals("textentry"))
                new QuestionCreate().textEntry(dataIndex);

            if(questionType.equals("textdropdown"))
                new QuestionCreate().textDropDown(dataIndex);

            if(questionType.equals("numericentrywithunits"))
                new QuestionCreate().numericEntryWithUnits(dataIndex);

            if(questionType.equals("numeric"))
                new QuestionCreate().numeric(dataIndex);

            if(questionType.equals("expressionevaluator"))
                new QuestionCreate().expressionEvaluator(dataIndex);

            if(questionType.equals("matchthefollowing"))
                new QuestionCreate().matchTheFollowing(dataIndex);

            if(questionType.equals("draganddrop"))
                new QuestionCreate().dragAndDrop(dataIndex);

            if(questionType.equals("clozeformula"))
                new QuestionCreate().clozeFormula(dataIndex);

            if(questionType.equals("graphplotter"))
                new QuestionCreate().graphPlotter(dataIndex);

            if(questionType.equals("clozematrix"))
                new QuestionCreate().clozeMatrix(dataIndex);

            if(questionType.equals("resequence"))
                new QuestionCreate().resequence(dataIndex);

            if(questionType.equals("essay"))
                rubricName = new QuestionCreate().essay(dataIndex);

            if(questionType.equals("labelanimagetext"))
                new QuestionCreate().labelAnImageText(dataIndex);

            if(questionType.equals("labelanimagedropdown"))
                new QuestionCreate().labelAnImageDropdown(dataIndex);

            if(questionType.equals("numberline"))
                new QuestionCreate().numberline(dataIndex);

            if(questionType.equals("classification"))
                new QuestionCreate().classification(dataIndex);

            if (questionType.equals("sentenceresponse"))
                new QuestionCreate().sentenceResponse(dataIndex);

            if (questionType.equals("matchingtables"))
                new QuestionCreate().matchingTables(dataIndex);

            if (questionType.equals("passage"))
                new QuestionCreate().passageBasedQuestions(dataIndex);

            if (questionType.equals("lineplot"))
                new QuestionCreate().linePlot(dataIndex);

            if (questionType.equals("rangeplotter"))
                new QuestionCreate().rangePlotter(dataIndex);

            if (questionType.equals("fractioneditor"))
                new QuestionCreate().fractionEditor(dataIndex);

            if  (questionType.equals("graphing"))
                new QuestionCreate().graphing(dataIndex);

            if(questionType.equals("graphplacement"))
                new QuestionCreate().graphPlacement(dataIndex);

            if(questionType.equals("pictograph"))
                new QuestionCreate().pictograph(dataIndex);

            if(questionType.equals("sentencecorrection"))
                new QuestionCreate().sentencecorrection(dataIndex);

            if(questionType.equals("multipart"))
                new QuestionCreate().multipart(dataIndex,"multipleChoiceAndSelectionAndEssay");//Essay will be added only if grade release option is explicitly by teacher

        } catch (Exception e) {

            Assert.fail("Exception in app helper Assignment in method createByDA.", e);
        }
        return rubricName;

    }


    public  void addStudentFromManageClass(String userEmail){
        try{
            ManageClass manageClass = PageFactory.initElements(Driver.getWebDriver(),ManageClass.class);

            new Navigator().navigateTo("manageclass");
            Thread.sleep(9000);
            WebDriverUtil.executeJavascript("$(\"span[class='as-manage-class-buttons pull-right btn-group']>a:nth-child(3):eq(0)\").click()");
            manageClass.userNameEmail.sendKeys(userEmail);
            Thread.sleep(2000);
            Driver.getWebDriver().findElement(By.cssSelector("body")).click();
            //  manageClass.userName.click();
            //manageClass.userName.sendKeys(userName);
            manageClass.userPassword.sendKeys("snapwiz");
            manageClass.retypePassword.sendKeys("snapwiz");
            manageClass.yesCreate.click();

        }catch (Exception e){
            Assert.fail("",e);
        }
    }




}
