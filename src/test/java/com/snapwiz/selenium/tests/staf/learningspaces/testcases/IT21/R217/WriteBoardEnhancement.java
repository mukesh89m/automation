package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT21.R217;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.PerformanceTab;
//import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Writeboard.WriteBoard;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

/**
 * Created by Dharaneesha on 6/19/15.
 */
public class WriteBoardEnhancement extends Driver{

    @Test(priority = 1,enabled = true)
    public void checkCMSPreviewPage(){
        try{
            int dataIndex = 9;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            /*Row No - 9 : "1. Login to CMS.
            2. Create an assessment with question of type ""True or False"" with ""Workboard"" enabled.
            3. Click on Preview.
            4. Click on ""Workboard"" icon."*/

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "multiplechoice", "");
            new Assignment().addQuestions(dataIndex, "multipleselection", "");
            new Assignment().openAssessmentInCMS(9);
            for(int a=0;a<3;a++){
                if(a==0){
                    validatePreviewPage(dataIndex);
                }else{
                    manageContent.jumpToQuestion.click();
                    WebDriverWait wait = new WebDriverWait(driver,120);
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//div[@class='overview'])[2]")));
                    Thread.sleep(2000);
                    new Click().clickbylinkText(""+(a+1));
                    wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("current-question-position"),""+(a+1)));
                    validatePreviewPage(dataIndex);
                }
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'checkCMSPreviewPage' in the class 'WriteBoardEnhancement',",e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void checkCMSPreviewPageWithLengthyQuestion(){
        try{
            int dataIndex = 19;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            /*Row No - 9 : "1. Login to CMS.
            2. Create an assessment with question of type ""True or False"" with ""Workboard"" enabled.
            3. Click on Preview.
            4. Click on ""Workboard"" icon."*/

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "multiplechoice", "");
            new Assignment().addQuestions(dataIndex, "multipleselection", "");
            new Assignment().openAssessmentInCMS(dataIndex);
            for(int a=0;a<3;a++){
                if(a==0){
                    validatePreviewPage(dataIndex);
                }else{
                    manageContent.jumpToQuestion.click();
                    WebDriverWait wait = new WebDriverWait(driver,60);
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='overview']//a")));
                    Thread.sleep(2000);
                    new Click().clickbylinkText("" + (a + 1));
                    wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("current-question-position"), "" + (a + 1)));
                    validatePreviewPage(dataIndex);
                }
            }

        }catch(Exception e){
            Assert.fail("Exception in method 'checkCMSPreviewPage' in the class 'checkCMSPreviewPageWithLengthyQuestion',",e);
        }
    }


    @Test(priority = 3,enabled = true)
    public void checkCMSPreviewPageWithMediaInQuestionData(){
        try{
            int dataIndex = 27;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            /*Row No - 9 : "1. Login to CMS.
            2. Create an assessment with question of type ""True or False"" with ""Workboard"" enabled.
            3. Click on Preview.
            4. Click on ""Workboard"" icon."*/

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "multiplechoice", "");
            new Assignment().addQuestions(dataIndex, "multipleselection", "");
            new Assignment().openAssessmentInCMS(dataIndex);

            for(int a=0;a<1;a++){
                if(a==0){
                    validatePreviewPage(dataIndex);
                }else{
                    manageContent.jumpToQuestion.click();
                    WebDriverWait wait = new WebDriverWait(driver,60);
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='overview']//a")));
                    new Click().clickbylinkText(""+(a+1));
                    wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("current-question-position"),""+(a+1)));
                    validatePreviewPage(dataIndex);
                }
            }

        }catch(Exception e){
            Assert.fail("Exception in method 'checkCMSPreviewPage' in the class 'WriteBoardEnhancement',",e);
        }
    }


    @Test(priority = 4,enabled = true)
    public void validateWorkBoardEnhancementInDiagAssessment(){
        try{
            int dataIndex = 35;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            WriteBoard writeBoard = PageFactory.initElements(driver, WriteBoard.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

           /*Row No - 35 :""1. Login to student user.
            2. Navigate to E-textbook.
            3. Go to ""Diagnostic"" assessment.
            4. Start attempting, click on ""+ workboard"" button."*/
            new Assignment().createChapter(dataIndex);
            new Assignment().publishChapter(dataIndex);
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "multiplechoice", "");
            new Assignment().addQuestions(dataIndex, "multipleselection", "");
            new LoginUsingLTI().ltiLogin(""+dataIndex); //Creating user student
            new Navigator().NavigateTo("e-Textbook");
            new DiagnosticTest().startTest(4, dataIndex);
            validateWorkBoard_DiagTest(dataIndex);
        }catch(Exception e){
            Assert.fail("Exception in method 'checkCMSPreviewPage' in the class 'validateWorkBoardEnhancementInDiagAssessment',",e);
        }
    }



    @Test(priority = 5,enabled = true)
    public void validateWorkBoardEnhancementInDiagAssessmentForExpandedQuestion(){
        try{
            int dataIndex = 40;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            WriteBoard writeBoard = PageFactory.initElements(driver, WriteBoard.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

           /* Row No - 40 : "1. Login to student user.
            2. Navigate to E-textbook.
            3. Go to ""Diagnostic"" assessment.
            4. Start attempting, click on ""+ workboard"" button."*/
            new Assignment().createChapter(dataIndex);
            new Assignment().publishChapter(dataIndex);
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "multiplechoice", "");
            new Assignment().addQuestions(dataIndex, "multipleselection", "");
            new LoginUsingLTI().ltiLogin(""+dataIndex); //Creating user student
            new Navigator().NavigateTo("e-Textbook");
            new DiagnosticTest().startTest(4, dataIndex);
            validateWorkBoard_DiagTest(dataIndex);
        }catch(Exception e){
            Assert.fail("Exception in method 'validateWorkBoardEnhancementInDiagAssessmentForExpandedQuestion' in the class 'validateWorkBoardEnhancementInDiagAssessment',",e);
        }
    }


    @Test(priority = 5,enabled = true)
    public void validateWorkBoardEnhancementInPerformanceSummary(){
        try{
            int dataIndex = 48;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            WriteBoard writeBoard = PageFactory.initElements(driver, WriteBoard.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

           /*Row  No - 48: "1. Login to student.
            2. Go to any question and add workboard data and complete DIAGNOSTIC assessment.
            3. It lands to Performance summary page.
            4. Click on question card, where workboard data is added."*/
            new Assignment().createChapter(dataIndex,1);
            new Assignment().publishChapter(dataIndex);
            new Assignment().create(dataIndex);
            new Assignment().addQuestionsWithCustomizedQuestionText(48, "qtn-multiple-choice-img", "", 1);
            new Assignment().addQuestionsWithCustomizedQuestionText(48, "qtn-type-true-false-img", "", 10);
          /*  new Assignment().addQuestions(dataIndex, "multiplechoice", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");*/
            new LoginUsingLTI().ltiLogin(""+dataIndex); //Creating user student
            new Navigator().NavigateTo("e-Textbook");
            new DiagnosticTest().startTest(4, dataIndex);
            new WriteBoard().drawSquareInWriteBoardInStudentSide(4);
            assignments.icon_workBoardClose.click();
            Thread.sleep(2000);
            new DiagnosticTest().attemptAllCorrect(3,false,false);
            new ClickOnquestionCard().clickonquestioncard(12,dataIndex);
            Thread.sleep(2000);


            //Expected - 1 : "View your work" button should be seen.
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            //Row No - 49 : 5. Click on "View your work" button.
            //Expected - 3. "Instructor feedback" icon in the tool bar should be disabled.
            assignmentResponsesPage.getWriteBoardFeedback().click();
            int iframeSize = driver.findElements(By.tagName("iframme")).size();
            System.out.println("iframeSize : " + iframeSize);


            driver.switchTo().frame(0);
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");
            }
            driver.switchTo().defaultContent();

            //Row No - 50 : 6. Click on "Close" icon
            //Expected  - 4. It should close the workboard overlay.
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();
            assignments.icon_workBoardClose.click();

           /*Row No - 57 : "1. Login to student.
            2. Go to any question and add workboard data and complete DIAGNOSTIC assessment.
            3. Navigate to ""PROFICIENCY"" Report.
            4. Click on question, where workboard data is added."*/
            new LoginUsingLTI().ltiLogin(""+dataIndex); //Creating user student
            new Navigator().NavigateTo("Proficiency Report");
            new ClickOnquestionCard().clickonquestioncard(12,dataIndex);

            //Expected - 1 : "View your work" button should be seen.
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            //Row No - 58 : 5. Click on "View your work" button.
            //Expected - 3. "Instructor feedback" icon in the tool bar should be disabled.
            assignmentResponsesPage.getWriteBoardFeedback().click();

            driver.switchTo().frame(0);
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");
            }
            driver.switchTo().defaultContent();

            //Row No - 60 : 6. Click on "Close" icon
            //Expected  - 4. It should close the workboard overlay.
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();
            assignments.icon_workBoardClose.click();


            //Row No - 61 : 7. Click on Question bar for which workboard data is added.
            assignments.getIcon_performancePage.click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("body.ROLE_USER")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("body.ROLE_USER")).click();
            Thread.sleep(2000);
            driver.findElements(By.tagName("rect")).get(16).click();
            Thread.sleep(2000);
            //Expected - 1 : "View your work" button should be seen.
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            //Row No - 62 : 5. Click on "View your work" button.
            //Expected - 3. "Instructor feedback" icon in the tool bar should be disabled.
            assignmentResponsesPage.getWriteBoardFeedback().click();


            driver.switchTo().frame(0);
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");
            }
            driver.switchTo().defaultContent();

            //Row No - 64 : 6. Click on "Close" icon
            //Expected  - 4. It should close the workboard overlay.
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();
            assignments.icon_workBoardClose.click();

        }catch(Exception e){
            Assert.fail("Exception in method 'checkCMSPreviewPage' in the class 'validateWorkBoardEnhancementInPerformanceSummary',",e);
        }
    }



     /*@Test(priority = 6,enabled = true)
    public void validateWorkBoardEnhancementInAdapPracticeAssessment(){
        try{
            int dataIndex = 66;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            WriteBoard writeBoard = PageFactory.initElements(driver, WriteBoard.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

            *//*Row No - 66: "1. Login to student user.
            2. Navigate to E-textbook.
            3. Go to ""Practice"" assessment.
            4. Start attempting, click on ""workboard"" button."*//*

            new Assignment().createChapter(dataIndex,1);
            Thread.sleep(2000);
            new Assignment().publishChapter(dataIndex);
            new Assignment().create(67);
            new Assignment().addQuestions(67, "multiplechoice", "");
            new Assignment().addQuestions(67, "multipleselection", "");
            new Assignment().create(dataIndex);
            new LoginUsingLTI().ltiLogin(""+dataIndex); //Creating user student
            new Navigator().NavigateTo("e-Textbook");
            new DiagnosticTest().startTest(4, dataIndex);

            new DiagnosticTest().attemptAllCorrect(3,false,false);
            new TOCShow().chaptertree();
            new PracticeTest().startTest(0,dataIndex);
            for(int i = 0; i < 3; i++) {
                (new PracticeTest()).AttemptCorrectAnswer(0,null);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'checkCMSPreviewPage' in the class 'validateWorkBoardEnhancementInDiagAssessment',",e);
        }
    }

*/

    @Test(priority = 6,enabled = true)
    public void validateWorkBoardEnhancementInAdapPracticeAssessment1(){
        try{
            int dataIndex = 66;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            WriteBoard writeBoard = PageFactory.initElements(driver, WriteBoard.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            /*Row No - 66: "1. Login to student user.
            2. Navigate to E-textbook.
            3. Go to ""Practice"" assessment.
            4. Start attempting, click on ""workboard"" button."*/

            new Assignment().createChapter(dataIndex,1);
            Thread.sleep(2000);
            new Assignment().publishChapter(dataIndex);
            new Assignment().create(67);
            new Assignment().create(dataIndex);
            new Assignment().addQuestionsWithCustomizedQuestionText(dataIndex,"truefalse","",1);

            new LoginUsingLTI().ltiLogin(""+dataIndex); //Creating user student
            new TOCShow().chaptertree();//click on toc
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");

            List<WebElement> allChapter = driver.findElements(By.xpath("//div[@title='"+chapterName+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allChapter.get(allChapter.size()-1));
            Thread.sleep(3000);

           // new TopicOpen().openLastChapter();
            Thread.sleep(2000);
            new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
            List<WebElement> conf =  driver.findElements(By.id(Integer.toString(1)));
            for(WebElement c : conf) {
                if(c.isDisplayed()) {
                    c.click();
                    break;
                }
            }
            List<WebElement> startButtons =  driver.findElements(By.xpath("//input[@class='practice-assigned ls-assessment-continue-btn orion-adaptive-practice-tab-btn']"));
            for(WebElement startButton : startButtons) {
                if(startButton.isDisplayed()) {
                    startButton.click();
                    break;
                }
            }
            Thread.sleep(3000);
            new DiagnosticTest().attemptAllCorrect(0,false,false);

            new TOCShow().chaptertree();//click on toc
            List<WebElement> allChapter1 = driver.findElements(By.xpath("//div[@title='"+chapterName+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allChapter1.get(allChapter1.size()-1));
            Thread.sleep(3000);

            // new TopicOpen().openLastChapter();

            new PracticeTest().startTest();



            //Row N0 - 72 : 6. Attempt a question and it takes to Review page of that question.
            //Expected - 7. View your work" button should be seen.
            validateWorkBoard_adaptivePracticeTest(dataIndex);
            assignments.button_true.click();
            Thread.sleep(2000);
            new Click().clickByid("submit-practice-question-button");
            Thread.sleep(2000);
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            //Row N0 - 73 : 7. Click on "view your work" button.
            //Expected - 8. "Workboard" data should be seen.
            assignmentResponsesPage.getWriteBoardFeedback().click();
            boolean questionContent = courseStreamPage.getQuestion().isDisplayed();
            System.out.println("questionContent : " + questionContent);
            String questionText = courseStreamPage.getQuestion().getText();
            Assert.assertEquals(questionContent,true,"Overlay should be covered completely with Question content area.");
            if(!questionText.contains(questiontext)){
                Assert.fail("Overlay should be covered completely with Question content area.");
            }


            /*Row No - 76 : "8. Attempt few questions and QUIT the assessment, it lands on Performance summary page.
            9. Click on question card, where workboard data is added."*/

            //Expected - 1. "View your work" button should be seen.
            assignments.icon_workBoardClose.click();
            new Click().clickByid("next-practice-question-button");
            Thread.sleep(2000);
            for(int i = 0; i < 13; i++) {
                (new PracticeTest()).AttemptCorrectAnswer(0,null);
            }

            new DiagnosticTest().quitTestAndViewReport();
            new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@id, 'question_card_id_')]"));
            List<WebElement> allelement=driver.findElements(By.xpath("//*[starts-with(@id, 'question_card_id_')]"));
            new ClickOnquestionCard().clickonquestioncard(allelement.size(),dataIndex);
            Thread.sleep(2000);


            //Expected - 1 : "View your work" button should be seen.
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            //Row No - 49 : 5. Click on "View your work" button.
            //Expected - 3. "Instructor feedback" icon in the tool bar should be disabled.
            assignmentResponsesPage.getWriteBoardFeedback().click();
            driver.switchTo().frame(0);
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");
            }
            driver.switchTo().defaultContent();

            //Row No - 50 : 6. Click on "Close" icon
            //Expected  - 4. It should close the workboard overlay.
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();
            assignments.icon_workBoardClose.click();

            /*Row N0 - 85 : "1. Login to student.
            2. Go to any question and add workboard data and complete ADAPTIVE PRACTICE assessment.
            3. Navigate to ""PROFICIENCY"" Report.
            4. Click on question, where workboard data is added."*/
            new LoginUsingLTI().ltiLogin(""+dataIndex); //Creating user student
            new Navigator().NavigateTo("Proficiency Report");
            new UIElement().waitAndFindElement(By.xpath("/*//*[starts-with(@id, 'question_card_id_')]"));
            allelement=driver.findElements(By.xpath("/*//*[starts-with(@id, 'question_card_id_')]"));
            new ClickOnquestionCard().clickonquestioncard(allelement.size()-1,dataIndex);

            //Expected - 1 : "View your work" button should be seen.
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            //Row No - 58 : 5. Click on "View your work" button.
            //Expected - 3. "Instructor feedback" icon in the tool bar should be disabled.
            assignmentResponsesPage.getWriteBoardFeedback().click();

            driver.switchTo().frame(0);
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");
            }
            driver.switchTo().defaultContent();

            //Row No - 60 : 6. Click on "Close" icon
            //Expected  - 4. It should close the workboard overlay.
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();
            assignments.icon_workBoardClose.click();


            //Row No - 61 : 7. Click on Question bar for which workboard data is added.
            assignments.getIcon_performancePage.click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("body.ROLE_USER")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("body.ROLE_USER")).click();
            Thread.sleep(2000);
            driver.findElements(By.tagName("rect")).get(16).click();
            Thread.sleep(2000);
            //Expected - 1 : "View your work" button should be seen.
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            //Row No - 62 : 5. Click on "View your work" button.
            //Expected - 3. "Instructor feedback" icon in the tool bar should be disabled.
            assignmentResponsesPage.getWriteBoardFeedback().click();


            driver.switchTo().frame(0);
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");
            }
            driver.switchTo().defaultContent();

            //Row No - 64 : 6. Click on "Close" icon
            //Expected  - 4. It should close the workboard overlay.
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();
            assignments.icon_workBoardClose.click();



            //Row No - 94 : "PRACTICE" reach through "Metacognitive Report"
            /*Row No - 94 : "1. Login to student user.
            2. Navigate to ""Metacognitive Report"".
            3. Mousehover on Dot and click on ""Practice"" button.
            4. It will be taken to Adpative Practice assessment.
            5. Attempt a question by adding ""workboard"" data."*/

            //Expected - 1. Repeat 21.7.1.8 Testcase ID.
            new LoginUsingLTI().ltiLogin(""+dataIndex); //Creating user student
            new PracticeTest().openPracticeTestThroughMetaCognitiveReport(dataIndex);
            validateWorkBoard_adaptivePracticeTest(dataIndex);
            assignments.button_true.click();
            Thread.sleep(2000);
            new Click().clickByid("submit-practice-question-button");
            Thread.sleep(2000);
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            //Row N0 - 73 : 7. Click on "view your work" button.
            //Expected - 8. "Workboard" data should be seen.
            assignmentResponsesPage.getWriteBoardFeedback().click();
            questionContent = courseStreamPage.getQuestion().isDisplayed();
            System.out.println("questionContent : " + questionContent);
            questionText = courseStreamPage.getQuestion().getText();
            Assert.assertEquals(questionContent,true,"Overlay should be covered completely with Question content area.");
            if(!questionText.contains(questiontext)){
                Assert.fail("Overlay should be covered completely with Question content area.");
            }


            /*Row No - 76 : "8. Attempt few questions and QUIT the assessment, it lands on Performance summary page.
            9. Click on question card, where workboard data is added."*/

            //Expected - 1. "View your work" button should be seen.
            assignments.icon_workBoardClose.click();
            new Click().clickByid("next-practice-question-button");
            Thread.sleep(2000);
            for(int i = 0; i < 13; i++) {
                (new PracticeTest()).AttemptCorrectAnswer(0,null);
            }

            new DiagnosticTest().quitTestAndViewReport();
            new UIElement().waitAndFindElement(By.xpath("/*//*[starts-with(@id, 'question_card_id_')]"));
            allelement=driver.findElements(By.xpath("/*//*[starts-with(@id, 'question_card_id_')]"));
            new ClickOnquestionCard().clickonquestioncard(allelement.size(),dataIndex);
            Thread.sleep(2000);


            //Expected - 1 : "View your work" button should be seen.
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            //Row No - 49 : 5. Click on "View your work" button.
            //Expected - 3. "Instructor feedback" icon in the tool bar should be disabled.
            assignmentResponsesPage.getWriteBoardFeedback().click();
            driver.switchTo().frame(0);
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");
            }
            driver.switchTo().defaultContent();

            //Row No - 50 : 6. Click on "Close" icon
            //Expected  - 4. It should close the workboard overlay.
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();
            assignments.icon_workBoardClose.click();


            //Row N0 - 95 : "PRACTICE" reach through "PRODUCTIVITY Report"

            /*Row N0 - 95 :"1. Login to student user.
            2. Navigate to ""Metacognitive Report"".
            3. Mousehover on Dot and click on ""Practice"" button.
            4. It will be taken to Adaptive Practice assessment.
            5. Attempt a question by adding ""workboard"" data."*/


            new PracticeTest().openPracticeTestThroughProductivityReport(dataIndex);
            validateWorkBoard_adaptivePracticeTest(dataIndex);
            assignments.button_true.click();
            Thread.sleep(2000);
            new Click().clickByid("submit-practice-question-button");
            Thread.sleep(2000);
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            // : 7. Click on "view your work" button.
            //Expected - 8. "Workboard" data should be seen.
            assignmentResponsesPage.getWriteBoardFeedback().click();
            questionContent = courseStreamPage.getQuestion().isDisplayed();
            System.out.println("questionContent : " + questionContent);
             questionText = courseStreamPage.getQuestion().getText();
            Assert.assertEquals(questionContent,true,"Overlay should be covered completely with Question content area.");
            if(!questionText.contains(questiontext)){
                Assert.fail("Overlay should be covered completely with Question content area.");
            }


            /* "8. Attempt few questions and QUIT the assessment, it lands on Performance summary page.
            9. Click on question card, where workboard data is added."*/

            //Expected - 1. "View your work" button should be seen.
            assignments.icon_workBoardClose.click();
            new Click().clickByid("next-practice-question-button");
            Thread.sleep(2000);
            for(int i = 0; i < 13; i++) {
                (new PracticeTest()).AttemptCorrectAnswer(0,null);
            }

            new DiagnosticTest().quitTestAndViewReport();
            new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@id, 'question_card_id_')]"));
            allelement=driver.findElements(By.xpath("//*[starts-with(@id, 'question_card_id_')]"));
            new ClickOnquestionCard().clickonquestioncard(allelement.size(),dataIndex);
            Thread.sleep(2000);


            //Expected - 1 : "View your work" button should be seen.
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            //5. Click on "View your work" button.
            //Expected - 3. "Instructor feedback" icon in the tool bar should be disabled.
            assignmentResponsesPage.getWriteBoardFeedback().click();
            driver.switchTo().frame(0);
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");
            }
            driver.switchTo().defaultContent();

            // 6. Click on "Close" icon
            //Expected  - 4. It should close the workboard overlay.
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();
            assignments.icon_workBoardClose.click();





            // "PRACTICE" reach through "MOST CHALLENGING ACTIVITIES Report"

            /* "1. Login to student user.
            2. Navigate to ""Most Challenging Activities"".
            3. Click on "">"" on chapter for which Practice should be attempted under ""View By Chapters""[View By Objectives""].
            4. Click on ""Practice"" button, it takes to ""Adaptive Practice"" assessment."*/


            //Expected - 3. Repeat 21.7.1.8 Testcase ID.

            new PracticeTest().openPracticeTestThroughMostChallengingActivitiesReport(dataIndex);
            validateWorkBoard_adaptivePracticeTest(dataIndex);
            assignments.button_true.click();
            Thread.sleep(2000);
            new Click().clickByid("submit-practice-question-button");
            Thread.sleep(2000);
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            //7. Click on "view your work" button.
            //Expected - 8. "Workboard" data should be seen.
            assignmentResponsesPage.getWriteBoardFeedback().click();
            questionContent = courseStreamPage.getQuestion().isDisplayed();
            System.out.println("questionContent : " + questionContent);
            questionText = courseStreamPage.getQuestion().getText();
            Assert.assertEquals(questionContent,true,"Overlay should be covered completely with Question content area.");
            if(!questionText.contains(questiontext)){
                Assert.fail("Overlay should be covered completely with Question content area.");
            }


            /*"8. Attempt few questions and QUIT the assessment, it lands on Performance summary page.
            9. Click on question card, where workboard data is added."*/

            //Expected - 1. "View your work" button should be seen.
            assignments.icon_workBoardClose.click();
            new Click().clickByid("next-practice-question-button");
            Thread.sleep(2000);
            for(int i = 0; i < 13; i++) {
                (new PracticeTest()).AttemptCorrectAnswer(0,null);
            }

            new DiagnosticTest().quitTestAndViewReport();
            new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@id, 'question_card_id_')]"));
            allelement=driver.findElements(By.xpath("//*[starts-with(@id, 'question_card_id_')]"));
            new ClickOnquestionCard().clickonquestioncard(allelement.size(),dataIndex);
            Thread.sleep(2000);


            //Expected - 1 : "View your work" button should be seen.
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            //5. Click on "View your work" button.
            //Expected - 3. "Instructor feedback" icon in the tool bar should be disabled.
            assignmentResponsesPage.getWriteBoardFeedback().click();
            driver.switchTo().frame(0);
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");
            }
            driver.switchTo().defaultContent();

            // 6. Click on "Close" icon
            //Expected  - 4. It should close the workboard overlay.
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();
            assignments.icon_workBoardClose.click();





        }catch(Exception e){
            Assert.fail("Exception in method 'checkCMSPreviewPage' in the class 'validateWorkBoardEnhancementInDiagAssessment',",e);
        }
    }




    @Test(priority = 7,enabled = true)
    public void validateWorkBoardEnhancementInStaticAssessment(){
        try{
            int dataIndex = 97;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            WriteBoard writeBoard = PageFactory.initElements(driver, WriteBoard.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            List<WebElement> frameElementsList = driver.findElements(By.tagName("iframe"));
            System.out.println("frameElementsList :  " + frameElementsList.size());
            /*ROw No - 97 : "1. Login to student user.
            2. Navigate to E-textbook.
            3. Go to ""Static"" assessment.
            4. Start attempting, click on ""workboard"" button."*/

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "multiplechoice", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");

            new LoginUsingLTI().ltiLogin("" + dataIndex); //Creating user student
            new Assignment().startStaticAssignmentFromeTextbook(dataIndex, 0);
            validateWorkBoard_staticTest(dataIndex);
            new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.WriteBoard().drawSquareInWriteBoardInStudentSide(dataIndex);
            //Row No - 103 : 6. Attempt a question and it takes to Review page of that question.
            //Expected - 7. View your work" button should be seen.7.
            assignments.icon_workBoardClose.click();
            Thread.sleep(5000);
            List<WebElement> submitElementsList = driver.findElements(By.cssSelector("input[value = 'Submit Answer']"));
            System.out.println("submitElementsList size : " + submitElementsList.size());
            for(int a=0;a<submitElementsList.size();a++){
                if(submitElementsList.get(a).isDisplayed()){
                    //submitElementsList.get(a).click();
                    new Click().clickByElement(submitElementsList.get(a));
                    Thread.sleep(5000);
                    break;
                }
            }

            //Expected -1 : 7. View your work" button should be seen.
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }

            //Row No - 102 : 5. Click on close icon.

            assignmentResponsesPage.getWriteBoardFeedback().click();
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            System.out.println("iframes size : " + iframes.size());
            //driver.switchTo().frame(1);
            assignments.icon_workBoardClose.click();
            //driver.switchTo().defaultContent();
            Thread.sleep(5000);



            /*Row No - 106 : "8. Complete the assessment, it lands on Performance summary page.
            9. Click on question card, where workboard data is added."*/


            //Expected  - 1. "View your work" button should be seen.
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            //Row No - 107 : 5. Click on "view your work" button.
            assignmentResponsesPage.getWriteBoardFeedback().click();
            driver.switchTo().frame(0);
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");
            }
            driver.switchTo().defaultContent();

            //Row No - 109 : 6. Click on "Close" icon
            //Expected  - 4. It should close the workboard overlay.
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();
            assignments.icon_workBoardClose.click();


            new LoginUsingLTI().ltiLogin("97_2"); //Creating user instructor
            new Assignment().assignToStudent(dataIndex);
            new LoginUsingLTI().ltiLogin("97_1"); //Creating user student
            new Assignment().submitAssignmentAsStudent(dataIndex);
            Thread.sleep(5000);
            driver.findElements(By.tagName("rect")).get(16).click();
            Thread.sleep(2000);
            //Expected - 1 : "View your work" button should be seen.
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            //Row No - 111 : 5. Click on "View your work" button.
            //Expected - 3. "Instructor feedback" icon in the tool bar should be disabled.
            assignmentResponsesPage.getWriteBoardFeedback().click();


            driver.switchTo().frame(0);
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");
            }
            driver.switchTo().defaultContent();

            //Row No - 113 : 6. Click on "Close" icon
            //Expected  - 4. It should close the workboard overlay.
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();
            assignments.icon_workBoardClose.click();


            new Navigator().NavigateTo("Proficiency Report");
            new ClickOnquestionCard().clickonquestioncard(12,dataIndex);

            //Expected - 1 : "View your work" button should be seen.
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            //Row No - 116 : 5. Click on "View your work" button.
            //Expected - 3. "Instructor feedback" icon in the tool bar should be disabled.
            assignmentResponsesPage.getWriteBoardFeedback().click();

            driver.switchTo().frame(0);
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");
            }
            driver.switchTo().defaultContent();

            //Row No - 118 : 6. Click on "Close" icon
            //Expected  - 4. It should close the workboard overlay.
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();
            assignments.icon_workBoardClose.click();


            //Row No - 119 : 7. Click on Question bar for which workboard data is added.
            assignments.getIcon_performancePage.click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("body.ROLE_USER")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("body.ROLE_USER")).click();
            Thread.sleep(2000);
            driver.findElements(By.tagName("rect")).get(16).click();
            Thread.sleep(2000);
            //Expected - 1 : "View your work" button should be seen.
            if(!assignmentResponsesPage.getWriteBoardFeedback().getText().contains("View your work")){
                Assert.fail("View your work\" button should be seen.");
            }


            //Row No - 120 : 5. Click on "View your work" button.
            //Expected - 3. "Instructor feedback" icon in the tool bar should be disabled.
            assignmentResponsesPage.getWriteBoardFeedback().click();


            driver.switchTo().frame(0);
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");
            }
            driver.switchTo().defaultContent();

            //Row No - 122 : 6. Click on "Close" icon
            //Expected  - 4. It should close the workboard overlay.
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();
            assignments.icon_workBoardClose.click();


        }catch(Exception e){
            Assert.fail("Exception in method 'checkCMSPreviewPage' in the class 'validateWorkBoardEnhancementInDiagAssessment',",e);
        }
    }



    @Test(priority = 8,enabled = true)
    public void validateWorkBoardEnhancementInGradableAssignments(){
        try{
            int dataIndex = 124;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            WriteBoard writeBoard = PageFactory.initElements(driver, WriteBoard.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            /*Row No - 124 : "1. Login to student user.
            2. Navigate to Assignments page.
            3. Start attempting, click on ""workboard"" button."*/

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "multiplechoice", "");
            new LoginUsingLTI().ltiLogin("124_1"); //Creating user instructor
            new Assignment().assignToStudent(dataIndex);
            new LoginUsingLTI().ltiLogin("124_2"); //Creating user student
            new Navigator().NavigateTo("Course Stream");
            new Click().clickBycssselector("span[assignmentname='" + assessmentName + "']");//click on Assignment
            Thread.sleep(5000);
            validateWorkBoard_staticTest(dataIndex);
            new WriteBoard().drawSquareInWriteBoardInStudentSide(dataIndex);

        }catch(Exception e){
            Assert.fail("Exception in method 'checkCMSPreviewPage' in the class 'validateWorkBoardEnhancementInDiagAssessment',",e);
        }
    }


    @Test(priority = 9,enabled = true)
    public void validateWorkBoardEnhancementIfStudentAddsAndInstDoesNot(){
        try{
            int dataIndex = 143;
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);

            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            /*Row No - 143 : "1. Login to student.
            2. Go to any assignment and start attempting and complete it.
            3. Click on ""Workboard"" button and add ""Workboard"" data.
            "*/
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "multiplechoice", "");
            new LoginUsingLTI().ltiLogin("143_1"); //Creating user instructor
            new Assignment().assignToStudent(dataIndex);
            new LoginUsingLTI().ltiLogin("143_2"); //Creating user student
            new Navigator().NavigateTo("Course Stream");
            new Click().clickBycssselector("span[assignmentname='" + assessmentName + "']");//click on Assignment
            Thread.sleep(5000);
            validateWorkBoard_staticTest(dataIndex);
            new WriteBoard().drawSquareInWriteBoardInStudentSide(dataIndex);
            assignments.icon_workBoardClose.click();
            new Assignment().nextButtonInQuestionClick();
            new Click().clickbylinkText("Finish Assignment");



            /*Row N0 - 144 : "4. Login to instructor.
            5. Instructor should not have added ""feedback"" from ""Workboard"".
            6. Login to student and go to question where only student is added ""Workboard"" data.
            "*/

            //Expected - 2. "View your work" button should not be seen with "Instructor feedback" icon over button.
            new LoginUsingLTI().ltiLogin("143_1"); //Creating user instructor
            new Assignment().switchToAssignmentResponsePage(dataIndex);
            new MouseHover().mouserhover("idb-gradebook-question-content");
            Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));
            Thread.sleep(4000);
            new WriteBoard().drawSquareInWriteBoardInstructorSide(dataIndex);
            //assignmentResponsesPage.getSaveButton().click();
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("143_2"); //Creating user student
            new Navigator().NavigateTo("Course Stream");
            new Click().clickBycssselector("span[assignmentname='" + assessmentName + "']");//click on Assignment
            new ClickOnquestionCard().clickonquestioncard(1,dataIndex);


            //Expected -1 2. "View your work" button should not be seen with "Instructor feedback" icon over button.
            if(driver.findElements(By.className("whiteboard-feedback-teacher")).size()!=0){
                Assert.fail("2. \"View your work\" button should not be seen with \"Instructor feedback\" icon over button.");
            }



            //Row No - 145 : 7. Click on "View your work" button.
            new UIElement().waitAndFindElement(assignmentResponsesPage.plusWorkBoard);
            assignmentResponsesPage.plusWorkBoard.click();
            Thread.sleep(2000);
            driver.switchTo().frame(questions.frame);
            //Expected - 3. "Workboard" overlay should be seen open with student added workboard data.
            if(!assignments.workBoard_overlay.isDisplayed()){
                Assert.fail("It should open workboard overlay.");
            }


            //Expected - 2 .4. "Instructor feedback" icon should be Disabled.
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("\"Instructor feedback\" icon should be Disabled.");

            }
           driver.switchTo().defaultContent();



        }catch(Exception e){
            Assert.fail("Exception in method 'checkCMSPreviewPage' in the class 'validateWorkBoardEnhancementIfStudentAddsAndInstDoesNot" +
                    "',",e);
        }
    }






    @Test(priority = 10,enabled = true)
    public void validateWorkBoardEnhancementIfStudentAndInstAdds(){
        try{
            int dataIndex = 147;
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);

            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            /*Row No - 147 : "1. Login to student.
            2. Go to any assignment and start attempting and complete it.
            3. Click on ""Workboard"" button and add ""Workboard"" data.
            "*/

            //Expected - 1.  "Workboard" data should be added.
            String feedback = "This is a feeback";
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "multiplechoice", "");
            new LoginUsingLTI().ltiLogin("147_1"); //Creating user instructor
            new Assignment().assignToStudent(dataIndex);
            new LoginUsingLTI().ltiLogin("147_2"); //Creating user student
            new Navigator().NavigateTo("Course Stream");
            new Click().clickBycssselector("span[assignmentname='" + assessmentName + "']");//click on Assignment
            Thread.sleep(5000);
            validateWorkBoard_staticTest(dataIndex);
            new WriteBoard().drawSquareInWriteBoardInStudentSide(dataIndex);
            assignments.icon_workBoardClose.click();
            new Assignment().nextButtonInQuestionClick();
            new Click().clickbylinkText("Finish Assignment");



            /*Row No - 148 : "4. Login to instructor.
            5. Instructor should have added ""feedback"" from ""Workboard"".
            6. Login to student and go to question where both student and instructor are added ""Workboard"" data.
            "*/


            /*"Expected - 2. ""View your work"" button should be seen with ""Instructor feedback"" icon over link.
                    [The “feedback” icon communicates to the student that Instructor has added some feedback for this question.]."*/
            new LoginUsingLTI().ltiLogin("147_1"); //Creating user instructor
            new Assignment().switchToAssignmentResponsePage(dataIndex);
            new MouseHover().mouserhover("idb-gradebook-question-content");
            Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));
            Thread.sleep(4000);
            new WriteBoard().enterTextInWriteBoardInInstructorSide("text",dataIndex);
            assignmentResponsesPage.getSaveButton().click();

            new LoginUsingLTI().ltiLogin("147_1"); //Creating user instructor
            new Assignment().releaseGrades(147,"Release Grade for All");

            new LoginUsingLTI().ltiLogin("147_2"); //Creating user student
            new Navigator().NavigateTo("Course Stream");
            new Click().clickBycssselector("span[assignmentname='" + assessmentName + "']");//click on Assignment
            new ClickOnquestionCard().clickonquestioncard(1,dataIndex);


            //Expected -1 2. "View your work" button should not be seen with "Instructor feedback" icon over button.
            if(driver.findElements(By.className("whiteboard-feedback-teacher")).size()==0){
                Assert.fail("\"2. \"\"View your work\"\" button should be seen with \"\"Instructor feedback\"\" icon over link.\n" +
                        "[The “feedback” icon communicates to the student that Instructor has added some feedback for this question.].\"");
            }



            //Row No - 145 : 7. Click on "View your work" button.
            new UIElement().waitAndFindElement(assignmentResponsesPage.plusWorkBoard);
            assignmentResponsesPage.plusWorkBoard.click();
            Thread.sleep(2000);
            driver.switchTo().frame(questions.frame);
            //Expected - 3. "Workboard" overlay should be seen open with student added workboard data.
            if(!assignments.workBoard_overlay.isDisplayed()){
                Assert.fail("It should open workboard overlay.");
            }


            //Row No - 150 : 8. Click on "Instructor feedback" icon.
            //Expected - 1 : 4. Instructor's workboard feedback data should be displayed along with student's data.
            //Expected - 2 : 5. "Instructor feedback" icon should be " Enabled ".
            assignmentResponsesPage.teacherFeedback.click();
            Thread.sleep(2000);
            if(!assignmentResponsesPage.teacherFeedback.getAttribute("class").equals("button tools selected")){
                System.out.println("Instructor feedback icon is not clicked");
            }


            if(!assignmentResponsesPage.teacherFeedback.isEnabled()){
                Assert.fail("\"Instructor feedback\" icon should be \" Enabled \".");

            }
            driver.switchTo().defaultContent();



        }catch(Exception e){
            Assert.fail("Exception in method 'checkCMSPreviewPage' in the class 'validateWorkBoardEnhancementIfStudentAddsAndInstDoesNot" +
                    "',",e);
        }
    }


    @Test(priority = 11,enabled = true)
    public void validateWorkBoardEnhancementIfStudentDoesNotAddAndInstAdds(){
        try{
            int dataIndex = 152;
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);

            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            /*Row N0 - 152 : "1. Login to student.
            2. Go to any assignment and start attempting and complete it.
            3. Click on ""Workboard"" button but do not add ""Workboard"" data.
            "*/
            //Expected - 1.  "1.  "Workboard" data should not be there.
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "multiplechoice", "");
            new LoginUsingLTI().ltiLogin("152_1"); //Creating user instructor
            new Assignment().assignToStudent(dataIndex);
            new LoginUsingLTI().ltiLogin("152_2"); //Creating user student
            new Navigator().NavigateTo("Course Stream");
            new Click().clickBycssselector("span[assignmentname='" + assessmentName + "']");//click on Assignment
            Thread.sleep(5000);
            validateWorkBoard_staticTest(dataIndex);
            new Assignment().nextButtonInQuestionClick();
            new Click().clickbylinkText("Finish Assignment");



            /*Row No - 153 : "4. Login to instructor.
            5. Instructor should have added ""feedback"" from ""Workboard"".
            6. Login to student and go to question where student is not added with ""Workboard"" data.
            "*/

            new LoginUsingLTI().ltiLogin("152_1"); //Creating user instructor
            new Assignment().switchToAssignmentResponsePage(dataIndex);
            new MouseHover().mouserhover("idb-gradebook-question-content");
            Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));
            Thread.sleep(4000);
            new WriteBoard().enterTextInWriteBoardInInstructorSide("text",dataIndex);
            assignmentResponsesPage.getSaveButton().click();


            new LoginUsingLTI().ltiLogin("152_1"); //Creating user instructor
            new Assignment().releaseGrades(152,"Release Grade for All");

            new LoginUsingLTI().ltiLogin("152_2"); //Creating user student
            new Navigator().NavigateTo("Course Stream");
            new Click().clickBycssselector("span[assignmentname='" + assessmentName + "']");//click on Assignment
            new ClickOnquestionCard().clickonquestioncard(1,dataIndex);


            //Expected - "2. ""View your work"" button should be seen with ""Instructor feedback"" icon over link."
            if(driver.findElements(By.className("whiteboard-feedback-teacher")).size()==0){
                Assert.fail("\"2. \"\"View your work\"\" button should be seen with \"\"Instructor feedback\"\" icon over link.\n" +
                        "[The “feedback” icon communicates to the student that Instructor has added some feedback for this question.].\"");
            }



            //Row No - 154 : 7. Click on "View your work" button.
            new UIElement().waitAndFindElement(assignmentResponsesPage.plusWorkBoard);
            assignmentResponsesPage.plusWorkBoard.click();
            Thread.sleep(2000);
            driver.switchTo().frame(questions.frame);
            //Expected - 3. "3. "Workboard" overlay should be seen open with student added workboard data.
            if(!assignments.workBoard_overlay.isDisplayed()){
                Assert.fail("It should open workboard overlay.");
            }


            //Row No - 155 : 8. Click on "Instructor feedback" icon.
            //Expected - 1 : 4. Instructor's workboard feedback data should be displayed along with student's data.
            //Expected - 2 : 5. "Instructor feedback" icon should be " Enabled ".
            assignmentResponsesPage.teacherFeedback.click();
            Thread.sleep(2000);
            if(!assignmentResponsesPage.teacherFeedback.getAttribute("class").equals("button tools selected")){
                System.out.println("Instructor feedback icon is not clicked");
            }


            if(!assignmentResponsesPage.teacherFeedback.isEnabled()){
                Assert.fail("\"Instructor feedback\" icon should be \" Enabled \".");

            }
            driver.switchTo().defaultContent();

        }catch(Exception e){
            Assert.fail("Exception in method 'validateWorkBoardEnhancementIfStudentDoesNotAddAndInstAdds' in the class 'validateWorkBoardEnhancementIfStudentAddsAndInstDoesNot" +
                    "',",e);
        }
    }




    public void validatePreviewPage(int dataIndex){
        try{

            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            WriteBoard writeBoard = PageFactory.initElements(driver, WriteBoard.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new UIElement().waitAndFindElement(manageContent.preview_Button);
            String winHandle = driver.getWindowHandle();
            manageContent.preview_Button.click();
            Set<String> allWindows = driver.getWindowHandles();
            for(String curWindow : allWindows){
                driver.switchTo().window(curWindow);
            }
            new UIElement().waitAndFindElement(assignmentResponsesPage.plusWorkBoard);
            assignmentResponsesPage.plusWorkBoard.click();
            Thread.sleep(2000);
            driver.switchTo().frame(0);
            //Thread.sleep(2000);
            //Expected - 1 : 1. It should open workboard overlay.
            boolean isWriteBoardDisplayed = driver.findElement(By.id("zwibbler")).isDisplayed();
            if(!assignments.workBoard_overlay.isDisplayed()){
                Assert.fail("It should open workboard overlay.");
            }

            //Expected-2 : 2. Workboard tool bar should contain "Instructor  Feedback" icon.
            if(!assignments.icon_instructorFeedback.isDisplayed()){
                Assert.fail("Workboard tool bar should contain \"Instructor  Feedback\" icon.");

            }

            //3. "Instructor feedback" icon should be disabled in the workboard tool bar.
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");

            }
            driver.switchTo().defaultContent();

            //Expected - 4. Overlay should be covered completely with Question content area.
            boolean questionContent = courseStreamPage.getQuestion().isDisplayed();
            System.out.println("questionContent : " + questionContent);
            String questionText = courseStreamPage.getQuestion().getText();
            Assert.assertEquals(questionContent,true,"Overlay should be covered completely with Question content area.");
            if(!questionText.contains(questiontext)){
              Assert.fail("Overlay should be covered completely with Question content area.");
            }


            //Expected - 5 : There should be "No" "Tooltip" Feedback icon.




            //Expected - 6 : 6. There should be "Close" icon to close the "Workboard" overlay.
            Assert.assertEquals(assignments.icon_workBoardClose.isDisplayed(), true, "icon_workBoardClose is not displayed");
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();

            //Row No - 15 : 5. Click on Performance tab.
            //Expected - 7. "Performance" tab should be not touching the "Workboard overlay.
            performanceTab.tab_performance.click();
            Point location = driver.findElements(By.xpath("//div[@class = 'cms-question-preview-sidebar-title-sectn']")).get(2).getLocation();
            Dimension size = driver.findElements(By.xpath("//div[@class = 'cms-question-preview-sidebar-title-sectn']")).get(2).getSize();
           // Assert.assertEquals("" + location, "(952, 580)", "Performance tab should be not touching the \"Workboard overlay.");
            Assert.assertEquals(""+size, "(190, 30)", "Performance tab should be not touching the \"Workboard overlay.");

            /*Row No - 16 : "6. Click on ""Close"" icon for ""Workboard"" overlay.
            7. Click on ""Performance"" tab."*/
            //Expected  - 8. "Performance" tab Expand to Collapse should work as existing.
            assignments.icon_workBoardClose.click();
            driver.findElements(By.xpath("//div[@class = 'cms-question-preview-sidebar-title-sectn']")).get(2).click();
            Assert.assertEquals(performanceTab.tab_performance.isDisplayed(),true,"performanceTab.tab_performance.isDisplayed() 1");

            //Expected - 2:  "Solution" ,"Hint","Check answer" buttons should work as existing functionality.
            currentAssignments.hint_Button.click();
            Assert.assertEquals(preview.hintContent.getText(), "Hint Text", "'Hint' button should work as existing functionality.");

            currentAssignments.solution_Button.click();
            Assert.assertEquals(preview.solutionContent.getText(), "Solution Text", "'Solution Text' button should work as existing functionality.");

            /*assignments.button_true.click();
            currentAssignments.checkAnswer_Button.click();
            Assert.assertEquals(currentAssignments.footerText.getText(),"You got it right.","'Check Answer' button should work as existing functionality.");
*/
            driver.close();
            driver.switchTo().window(winHandle);

        }catch (Exception e){
            Assert.fail("Exception in the method 'validatePreviewPage' in the class 'WriteBoardEnhancement'",e);
        }
    }


    public void validateWorkBoard_DiagTest(int dataIndex){
        try{

            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            WriteBoard writeBoard = PageFactory.initElements(driver, WriteBoard.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));


            new UIElement().waitAndFindElement(assignmentResponsesPage.plusWorkBoard);
            Thread.sleep(3000);
            assignmentResponsesPage.plusWorkBoard.click();
            Thread.sleep(2000);
            List<WebElement> frameElementsList = driver.findElements(By.tagName("iframe"));
            System.out.println("frameElementsList :  " + frameElementsList.size());
            driver.switchTo().frame(0);
            Thread.sleep(2000);
            //Expected - 1 : 1. It should open workboard overlay.
            boolean isWriteBoardDisplayed = driver.findElement(By.id("zwibbler")).isDisplayed();
            if(!assignments.workBoard_overlay.isDisplayed()){
                Assert.fail("It should open workboard overlay.");
            }

            //Expected-2 : 2. Workboard tool bar should contain "Instructor  Feedback" icon.
            if(!assignments.icon_instructorFeedback.isDisplayed()){
                Assert.fail("Workboard tool bar should contain \"Instructor  Feedback\" icon.");

            }

            //3. "Instructor feedback" icon should be disabled in the workboard tool bar.
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");

            }
            driver.switchTo().defaultContent();

            //Expected - 4. Overlay should be covered completely with Question content area.
            boolean questionContent = courseStreamPage.getQuestion().isDisplayed();
            System.out.println("questionContent : " + questionContent);
            String questionText = courseStreamPage.getQuestion().getText();
            Assert.assertEquals(questionContent,true,"Overlay should be covered completely with Question content area.");
            if(!questionText.contains(questiontext)){
                Assert.fail("Overlay should be covered completely with Question content area.");
            }






            //Expected - 6 : 6. There should be "Close" icon to close the "Workboard" overlay.
            Assert.assertEquals(assignments.icon_workBoardClose.isDisplayed(), true, "icon_workBoardClose is not displayed");
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();

            //Expected - 3. "Workboard overlay should not be seen on "Performance" bar.
            System.out.println("List : " + driver.findElements(By.cssSelector("span[title = 'Performance']")).size());
            Point location = driver.findElement(By.cssSelector("span[title = 'Performance']")).getLocation();
            Dimension size = driver.findElement(By.cssSelector("span[title = 'Performance']")).getSize();
            System.out.println("LOcation : "+ location);
            System.out.println("size : "+ size);
           // Assert.assertEquals("" + location, "(929, 69)", "Performance tab should be not touching the \"Workboard overlay.");
//            Assert.assertEquals(""+size, "(73, 30)", "Performance tab should be not touching the \"Workboard overlay.");


        }catch (Exception e){
            Assert.fail("Exception in the method 'validatePreviewPage' in the class 'WriteBoardEnhancement'",e);
        }
    }

    public void validateWorkBoard_staticTest(int dataIndex){
        try{

            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            WriteBoard writeBoard = PageFactory.initElements(driver, WriteBoard.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));


            new UIElement().waitAndFindElement(assignmentResponsesPage.plusWorkBoard);
            assignmentResponsesPage.plusWorkBoard.click();
            Thread.sleep(2000);
            List<WebElement> frameElementsList = driver.findElements(By.tagName("iframe"));
            System.out.println("frameElementsList :  " + frameElementsList.size());
            driver.switchTo().frame(0);
            Thread.sleep(2000);
            //Expected - 1 : 1. It should open workboard overlay.
            boolean isWriteBoardDisplayed = driver.findElement(By.id("zwibbler")).isDisplayed();
            if(!assignments.workBoard_overlay.isDisplayed()){
                Assert.fail("It should open workboard overlay.");
            }

            //Expected-2 : 2. Workboard tool bar should contain "Instructor  Feedback" icon.
            if(!assignments.icon_instructorFeedback.isDisplayed()){
                Assert.fail("Workboard tool bar should contain \"Instructor  Feedback\" icon.");

            }

            //3. "Instructor feedback" icon should be disabled in the workboard tool bar.
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");

            }
            driver.switchTo().defaultContent();

            //Expected - 4. Overlay should be covered completely with Question content area.
            boolean questionContent = courseStreamPage.getQuestion().isDisplayed();
            System.out.println("questionContent : " + questionContent);
            String questionText = courseStreamPage.getQuestion().getText();
            Assert.assertEquals(questionContent,true,"Overlay should be covered completely with Question content area.");
            if(!questionText.contains(questiontext)){
                Assert.fail("Overlay should be covered completely with Question content area.");
            }






            //Expected - 6 : 6. There should be "Close" icon to close the "Workboard" overlay.
            Assert.assertEquals(assignments.icon_workBoardClose.isDisplayed(), true, "icon_workBoardClose is not displayed");
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();


            //5. Click on close icon.

            assignments.icon_workBoardClose.click();


        }catch (Exception e){
            Assert.fail("Exception in the method 'validatePreviewPage' in the class 'WriteBoardEnhancement'",e);
        }
    }


    public void validateWorkBoard_adaptivePracticeTest(int dataIndex){
        try{

            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            WriteBoard writeBoard = PageFactory.initElements(driver, WriteBoard.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));


            new UIElement().waitAndFindElement(assignmentResponsesPage.plusWorkBoard);
            assignmentResponsesPage.plusWorkBoard.click();
            Thread.sleep(2000);
            List<WebElement> frameElementsList = driver.findElements(By.tagName("iframe"));
            System.out.println("frameElementsList :  " + frameElementsList.size());
            driver.switchTo().frame(0);
            Thread.sleep(2000);
            //Expected - 1 :1. "Workboard" overlay should be seen.
            boolean isWriteBoardDisplayed = driver.findElement(By.id("zwibbler")).isDisplayed();
            if(!assignments.workBoard_overlay.isDisplayed()){
                Assert.fail("It should open workboard overlay.");
            }

            //Expected-2 : 2. Workboard tool bar should contain "Instructor  Feedback" icon.
            if(!assignments.icon_instructorFeedback.isDisplayed()){
                Assert.fail("Workboard tool bar should contain \"Instructor  Feedback\" icon.");

            }

            //3. "Instructor feedback" icon should be disabled in the workboard tool bar.
            if(assignments.icon_instructorFeedback.isEnabled()){
                Assert.fail("Instructor feedback\" icon should be disabled in the workboard tool bar.");

            }
            driver.switchTo().defaultContent();

            //Expected -  2. "Workboard" overlay should be covered over entire question content.
            boolean questionContent = courseStreamPage.getQuestion().isDisplayed();
            System.out.println("questionContent : " + questionContent);
            String questionText = courseStreamPage.getQuestion().getText();
            Assert.assertEquals(questionContent,true,"Overlay should be covered completely with Question content area.");
            if(!questionText.contains(questiontext)){
                Assert.fail("Overlay should be covered completely with Question content area.");
            }






            //Expected - 6 : 6. There should be "Close" icon to close the "Workboard" overlay.
            Assert.assertEquals(assignments.icon_workBoardClose.isDisplayed(), true, "icon_workBoardClose is not displayed");
            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();


            //Row No - 71: 5. Click on close icon.
            //Expected - 6. It should close workboard overlay.

            assignments.icon_workBoardClose.click();
            assignmentResponsesPage.plusWorkBoard.click();

            //Expected - 3 : 3. User should be able to add "Workboard" data.
            new WriteBoard().drawSquareInWriteBoardInStudentSide(dataIndex);


            //Expected - 4 : 4. Workboard overlay should not be seen on "Performance" bar.
            Point location = driver.findElement(By.xpath("//div[@data-id = 'Performance']")).getLocation();
            Dimension size = driver.findElement(By.xpath("//div[@data-id = 'Performance']")).getSize();
            System.out.println("location : " + location);
            System.out.println("size : " + size);

         //  Assert.assertEquals("" + location, "(921, 65)", "Performance tab should be not touching the \"Workboard overlay.");
          //  Assert.assertEquals(""+size, "(83, 37)", "Performance tab should be not touching the \"Workboard overlay.");

            assignments.icon_workBoardClose.click();



        }catch (Exception e){
            Assert.fail("Exception in the method 'validatePreviewPage' in the class 'WriteBoardEnhancement'",e);
        }
    }



    public void addWriteBoard(int dataIndex){
        try{
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);

            assignmentResponsesPage.plusWorkBoard.click();
            List<WebElement> frameElementsList = driver.findElements(By.tagName("iframe"));
            System.out.println("frameElementsList :  " + frameElementsList.size());
            driver.switchTo().frame(0);
            new Click().clickByid("square-btn");
            driver.switchTo().defaultContent();
            new Click().clickByclassname("confidence-level-i-know-it");
            List<WebElement> submitElementsList = driver.findElements(By.cssSelector("input[type = 'button']"));
            System.out.println("submitElementsList size : " + submitElementsList.size());
            for(int a=0;a<submitElementsList.size();a++){
                if(submitElementsList.get(a).isDisplayed()){
                    //submitElementsList.get(a).click();
                    new Click().clickByElement(submitElementsList.get(a));
                    Thread.sleep(5000);
                    break;
                }
            }


        }catch(Exception e){
            Assert.fail("Exception in the class 'WriteBoardEnhancement' in the method 'addWriteBoard'",e);
        }
    }
}
