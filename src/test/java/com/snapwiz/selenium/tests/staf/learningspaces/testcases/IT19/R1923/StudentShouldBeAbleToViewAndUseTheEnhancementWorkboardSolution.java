package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1923;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by priyanka on 2/27/2015.
 */
public class StudentShouldBeAbleToViewAndUseTheEnhancementWorkboardSolution extends Driver {
    @Test(priority = 1, enabled = true)
    public void toViewAndUseWorkboardEnabledQuestionInDiagnosticAssessment() {

        try {
            //tc row no 195
            CourseOutline courseOutline=PageFactory.initElements(driver,CourseOutline.class);
            Questions questions = PageFactory.initElements(driver,Questions.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(195));
            WebDriverWait wait=new WebDriverWait(driver,200);
            new Assignment().createChapter(195,1);//create a chapter
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select a course
            Thread.sleep(2000);
            courseOutline.courseOutline.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@title,'Add New Chapter')]")));
            //publish chapter
            List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label node course-chapter-label-unpublished')]"));
            for (WebElement chapters : allChapters) {
                if (chapters.getText().contains(chapterName)) {
                    Locatable hoverItem = (Locatable) chapters;
                    Mouse mouse = ((HasInputDevices) driver).getMouse();
                    mouse.mouseMove(hoverItem.getCoordinates());
                }
            }
            Thread.sleep(500);
            courseOutline.editButtonAtChapter.click();
            Thread.sleep(500);
            courseOutline.checkBoxToPublishChapter.click();//click on publish
            courseOutline.saveButton.click();//click on save
            Thread.sleep(5000);
            courseOutline.saveMyChanges.click();
            Thread.sleep(2000);
            create(195);//Create an assignment
            addQuestions(195, "writeboard", "");
            new LoginUsingLTI().ltiLogin("195");//login as student
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().openLastChapter();
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
            Thread.sleep(5000);
            String workBoard = questions.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "It is not take to the question preview page.");
            questions.plusWorkBoard.click();//click on +workboard
            String crossIcon =questions.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(questions.frame);//switch to frame
            boolean toolPanel = questions.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            questions.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 195);
            boolean workBoard1 = questions.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            String crossIcon1 = questions.crossIcon.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            questions.crossIcon.click();//click on 'x'
            questions.expandIcon.click();//click on expand icon
            boolean workBoard2=questions.plusWorkBoard.isEnabled();
            if(workBoard2==false){
                Assert.fail("Workboard button is not disabled on expanding the center area");
            }
            questions.expandIcon.click();//click on collapse icon
            questions.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(questions.frame);
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
            driver.switchTo().defaultContent();//switch to main window
            questions.crossIcon.click();//click on 'x'
            new DiagnosticTest().attemptAllCorrect(0,false,false);
            new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']")));
            questions.questionCard.get(0).click();//click on 2nd card
            String showYourWorkLabel = questions.plusWorkBoard.getText();
            Assert.assertEquals(showYourWorkLabel, "+ View your work", "'View your work' button is not available");
            questions.plusWorkBoard.click();//click on view your work
            String crossIcon2 =questions.crossIcon.getText();
            Assert.assertEquals(crossIcon2, "x", "An overlay on top of the question with workboard editor is not displaying");
            questions.crossIcon.click();//click on 'x'
            questions.question_card.get(0).click();//click on 1st card



        } catch (Exception e) {
            Assert.fail("Exception in test case toViewAndUseWorkboardEnabledQuestionInDiagnosticAssessment of class StudentShouldBeAbleToViewAndUseTheEnhancementWorkboardSolution", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void toViewAndUseWorkboardEnabledQuestionInPersonalisedPractice() {

        try {
            //tc row no 214
            Questions questions = PageFactory.initElements(driver,Questions.class);
            new Assignment().create(214);//Create an assignment
            new Assignment().addQuestions(214, "multiplechoice", "");
            new Assignment().addQuestions(214, "multipleselection", "");
            new LoginUsingLTI().ltiLogin("214");//login as student
            new TOCShow().chaptertree();//click on toc
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
            new TopicOpen().openLastChapter();
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
            new LoginUsingLTI().ltiLogin("214");//login as student
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().openLastChapter();

            new PracticeTest().startTest();
            Thread.sleep(3000);

            //tc row no 216

            String workBoard = questions.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "The +workboard button is not displaying in top right corner at a row above question content");
            questions.plusWorkBoard.click();//click on +workboard
            String crossIcon =questions.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(questions.frame);//switch to frame
            boolean toolPanel = questions.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            questions.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 214);
            boolean workBoard1 = questions.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            String crossIcon1 = questions.crossIcon.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            questions.crossIcon.click();//click on 'x'
            Thread.sleep(2000);
            boolean overlay = new BooleanValue().presenceOfElement(214, By.id("main-controls"));
            Assert.assertEquals(overlay,false,"Clicking on close icon is not closing the overlay");
            questions.expandIcon.click();//click on expand icon
            boolean workBoard2=questions.plusWorkBoard.isEnabled();
            if(workBoard2==false){
                Assert.fail("Workboard button is not disabled on expanding the center area");
            }
            questions.expandIcon.click();//click on collapse icon

            //tc row no 226

            questions.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(questions.frame);
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
            driver.switchTo().defaultContent();//switch to main window
            questions.crossIcon.click();//click on 'x'
            for(int i = 0; i < 3; i++)
            {
                new PracticeTest().AttemptCorrectAnswer(0,"214");

            }
            questions.quit_Icon.click();//click on quit
            questions.viewReportLink.click();//click on view report
            Thread.sleep(2000);

            //tc row no 233

            questions.questionCard.get(2).click();//click on 3rd question card
            String showYourWorkLabel = questions.plusWorkBoard.getText();
            Assert.assertEquals(showYourWorkLabel, "+ View your work", "'View your work' button is not available");
            questions.plusWorkBoard.click();//click on view your work
            String crossIcon2 =questions.crossIcon.getText();
            Assert.assertEquals(crossIcon2, "x", "An overlay on top of the question with workboard editor is not displaying");
            questions.crossIcon.click();//click on 'x'
            boolean overlay2 = new BooleanValue().presenceOfElement(214, By.id("main-controls"));
            Assert.assertEquals(overlay2,false,"Clicking on close icon is not closing the overlay");

            //tc row no 238

            questions.questionCard.get(0).click();//click on 3rd question card
            Thread.sleep(4000);
            boolean viewYourWork = new BooleanValue().presenceOfElement(214, By.id("show-your-work-label"));
            Assert.assertEquals(viewYourWork,false,"viewYourWork link is displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case toViewAndUseWorkboardEnabledQuestionInPersonalisedPractice of class StudentShouldBeAbleToViewAndUseTheEnhancementWorkboardSolution", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void toViewAndUseWorkboardEnabledQuestionInStaticTest() {

        try {
            //tc row no 239
            Questions questions = PageFactory.initElements(driver,Questions.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            new Assignment().create(239);//Create an assignment
            new Assignment().addQuestions(239, "multiplechoice", "");
            new Assignment().addQuestions(239, "multipleselection", "");
            new LoginUsingLTI().ltiLogin("239");//login as student
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().chapterOpen(2);
            new SelectCourse().selectInvisibleAssignment("staticAssessment_239");
            Thread.sleep(5000);

            //Tc row no 241
            String workBoard = questions.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "The +workboard button is not displaying in top right corner at a row above question content");
            questions.plusWorkBoard.click();//click on +workboard
            String crossIcon =questions.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(questions.frame);//switch to frame
            boolean toolPanel = questions.toolControl.isDisplayed();
            if (toolPanel == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            questions.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 214);
            boolean workBoard1 = questions.plusWorkBoard.isDisplayed();
            if (workBoard1 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            String crossIcon1 = questions.crossIcon.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            questions.crossIcon.click();//click on 'x'
            Thread.sleep(2000);
            boolean overlay = new BooleanValue().presenceOfElement(239, By.id("main-controls"));
            Assert.assertEquals(overlay,false,"Clicking on close icon is not closing the overlay");
            questions.expandIcon.click();//click on expand icon
            boolean workBoard2=questions.plusWorkBoard.isEnabled();
            if(workBoard2==false){
                Assert.fail("Workboard button is not disabled on expanding the center area");
            }
            questions.expandIcon.click();//click on collapse icon

            //tc row no 251

            questions.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(questions.frame);
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
            //questions.textEntryPopUp.click();//click in middle
            driver.switchTo().defaultContent();//switch to main window
            questions.crossIcon.click();//click on 'x'

            //tc row no 252

            assignments.getArrowDropDown().click();//click on summary dropdown
            assignments.getQuestionCount().get(1).click();//click on 2nd question
            Thread.sleep(2000);
            String workBoard3 = questions.plusWorkBoard.getText();
            Assert.assertEquals(workBoard3, "+ Workboard", "The +workboard button is not displaying in top right corner at a row above question content");
            questions.plusWorkBoard.click();//click on +workboard
            String crossIcon2 =questions.crossIcon.getText();
            Assert.assertEquals(crossIcon2, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(questions.frame);//switch to frame
            boolean toolPanel1 = questions.toolControl.isDisplayed();
            if (toolPanel1 == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            questions.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 239);
            boolean workBoard4 = questions.plusWorkBoard.isDisplayed();
            if (workBoard4 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            String crossIcon3 = questions.crossIcon.getText();
            Assert.assertEquals(crossIcon3, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            questions.crossIcon.click();//click on 'x'

            //tc row no 261
            assignments.getArrowDropDown().click();//click on summary dropdown
            assignments.getQuestionCount().get(0).click();//click on 1st question
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            Thread.sleep(3000);
            String workBoard5 = questions.plusWorkBoard.getText();
            Assert.assertEquals(workBoard5, "+ Workboard", "The +workboard button is not displaying in top right corner at a row above question content");
            questions.plusWorkBoard.click();//click on +workboard
            String crossIcon4 =questions.crossIcon.getText();
            Assert.assertEquals(crossIcon4, "x", "An overlay on top of the question with workboard editor is not displaying");
            questions.crossIcon.click();//click on 'x'
            Thread.sleep(2000);
            boolean overlay2 = new BooleanValue().presenceOfElement(214, By.id("main-controls"));
            Assert.assertEquals(overlay2,false,"Clicking on close icon is not closing the overlay");
            new Assignment().submitButtonInQuestionClick();//click on submit
            new Assignment().nextButtonInQuestionClick();//click on next
            preview.selectMultipleChoice.get(0).click();//click on 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            new Assignment().nextButtonInQuestionClick();//click on next
            //tc row no 266
            new Assignment().submitButtonInQuestionClick();//click on submit
            Thread.sleep(4000);
            boolean viewYourWork = new BooleanValue().presenceOfElement(214, By.id("show-your-work-label"));
            Assert.assertEquals(viewYourWork,false,"The +workboard button in top right corner of question panel is displaying over review screen");
            driver.findElement(By.xpath("//input[@title='Finish']")).click();//click on the finish button

            //tc row no 267

            questions.question_card.get(2).click();//click on 3rd question card
            String showYourWorkLabel = questions.plusWorkBoard.getText();
            Assert.assertEquals(showYourWorkLabel, "+ View your work", "'View your work' button is not available");
            questions.plusWorkBoard.click();//click on view your work
            String crossIcon5 =questions.crossIcon.getText();
            Assert.assertEquals(crossIcon5, "x", "An overlay on top of the question with workboard editor is not displaying");
            questions.crossIcon.click();//click on 'x'
            boolean overlay3 = new BooleanValue().presenceOfElement(239, By.id("main-controls"));
            Assert.assertEquals(overlay3,false,"Clicking on close icon is not closing the overlay");

            //tc row no 272

            questions.question_card.get(0).click();//click on 3rd question card
            Thread.sleep(4000);
            boolean viewYourWork1 = new BooleanValue().presenceOfElement(239, By.id("show-your-work-label"));
            Assert.assertEquals(viewYourWork1,false,"viewYourWork link is displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case toViewAndUseWorkboardEnabledQuestionInStaticTest of class StudentShouldBeAbleToViewAndUseTheEnhancementWorkboardSolution", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void checkingWorkBoardFeaturesForTheAssignmentsInStudentAccount() {

        try {
            //tc row no 307
            Questions questions = PageFactory.initElements(driver,Questions.class);
            new Assignment().create(307);//Create an assignment
            new Assignment().addQuestions(307, "multiplechoice", "");
            new LoginUsingLTI().ltiLogin("307");//login as student
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().chapterOpen(2);
            new SelectCourse().selectInvisibleAssignment("staticAssessment_307");
            Thread.sleep(5000);
            String workBoard = questions.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "The +workboard button is not displaying in top right corner at a row above question content");
            questions.plusWorkBoard.click();//click on +workboard
            String crossIcon =questions.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(questions.frame);//switch to frame
            boolean toolPanel1 = questions.toolControl.isDisplayed();
            if (toolPanel1 == false) {
                Assert.fail("The tools panel of the workboard editor is not displaying in the top");
            }
            driver.switchTo().defaultContent();//switch to main window
            questions.crossIcon.click();//click on 'x'
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 307);
            boolean workBoard4 = questions.plusWorkBoard.isDisplayed();
            if (workBoard4 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            String crossIcon1 = questions.crossIcon.getText();
            Assert.assertEquals(crossIcon1, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            questions.crossIcon.click();//click on 'x'
            Thread.sleep(2000);
            boolean overlay = new BooleanValue().presenceOfElement(307, By.id("main-controls"));
            Assert.assertEquals(overlay,false,"Clicking on close icon is not closing the overlay");
            questions.expandIcon.click();//click on expand icon
            boolean workBoard2=questions.plusWorkBoard.isEnabled();
            if(workBoard2==false){
                Assert.fail("Workboard button is not disabled on expanding the center area");
            }
            questions.expandIcon.click();//click on collapse icon
            questions.plusWorkBoard.click();//click on +workboard
            String crossIcon3 = questions.crossIcon.getText();
            Assert.assertEquals(crossIcon3, "x", "A close icon in red color is not displaying in the top right corner of the overlay.");
            questions.crossIcon.click();//click on 'x'

            questions.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(questions.frame);
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
            driver.switchTo().defaultContent();//switch to main window
            questions.crossIcon.click();//click on 'x'

        } catch (Exception e) {
            Assert.fail("Exception in test case checkingWorkBoardFeaturesForTheAssignmentsInStudentAccount of class StudentShouldBeAbleToViewAndUseTheEnhancementWorkboardSolution", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void toViewAndUseWriteBoardQuestionsUnderAssignment() {

        try {
            //tc row no 320
            Questions questions = PageFactory.initElements(driver,Questions.class);
            new LoginUsingLTI().ltiLogin("320");//login as student
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().chapterOpen(2);
            new SelectCourse().selectInvisibleAssignment("staticAssessment_307");
            Thread.sleep(5000);
            String workBoard = questions.plusWorkBoard.getText();
            Assert.assertEquals(workBoard, "+ Workboard", "The +workboard button is not displaying in top right corner at a row above question content");
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 320);
            boolean workBoard4 = questions.plusWorkBoard.isDisplayed();
            if (workBoard4 == true) {
                Assert.fail("It is showing '+Work board' button when pop_up is shown.");
            }
            questions.crossIcon.click();//click on 'x'
            questions.plusWorkBoard.click();//click on +workboard
            driver.switchTo().frame(questions.frame);//switch to frame
            questions.pencilButton.click();
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
        } catch (Exception e) {
            Assert.fail("Exception in test case toViewAndUseWriteBoardQuestionsUnderAssignment of class StudentShouldBeAbleToViewAndUseTheEnhancementWorkboardSolution", e);
        }
    }

    public void create(int dataIndex) {
        try {
            String course = "";
            String overrideDefaultLogin = ReadTestData.readDataByTagName("", "overrideDefaultLogin", Integer.toString(dataIndex));
            String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
            String course_name = ReadTestData.readDataByTagName("", "course_name", Integer.toString(dataIndex));
            String practice_type = ReadTestData.readDataByTagName("", "practice_type", Integer.toString(dataIndex));

            String overrideDefaultQuestionCreate = ReadTestData.readDataByTagName("", "overrideDefaultQuestionCreate", Integer.toString(dataIndex));

            course = course_name;
            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
            String reservforassignment = ReadTestData.readDataByTagName("", "reservforassignment", Integer.toString(dataIndex));

            if (overrideDefaultLogin == null)
                new DirectLogin().CMSLogin();
            else {
                String role = ReadTestData.readDataByTagName("", "role", Integer.toString(dataIndex));
                new DBConnect().Connect();
                ResultSet rs = DBConnect.st.executeQuery("select id,username from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = '" + role + "') limit 1,1;");
                String username = "";
                while (rs.next()) {
                    username = rs.getString("username");
                }
                new DirectLogin().directLoginWithCreditial(username, "snapwiz", 0);
                new Click().clickbyxpath("(//select[@name='product-drop-down']/following-sibling::div/a)[1]");
                new Click().clickbylinkText("Learning Space + Adaptive Component");
                DBConnect.conn.close();
            }

            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                //driver.findElement(By.cssSelector("img[alt='"+course+"']")).click();

                if (chapterName == null) {
                    // driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                    new Click().clickBycssselector("div.course-chapter-label.node");
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    List<WebElement> allChapterswithSameName = driver.findElements(By.xpath("//div[contains(@title,'"+chapterName+"')]"));
                    driver.findElements(By.xpath("//div[contains(@title, '"+chapterName+"')]")).get(allChapterswithSameName.size()-1).click();
                    /*for (WebElement chapters : allChapters) {
                        if (chapters.getText().contains(chapterName)) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters);
                            Thread.sleep(4000);
                            break;
                        }

                    }*/

                }
                driver.findElement(By.cssSelector("div.create-practice")).click();

                if (practice_type == null) {
                    new Click().clickByclassname("create-regular-assessment-popup-item"); //click on the create regular assessment
                } else {
                    new Click().clickByclassname("create-file-assessment-popup-item");//click on the create file assessment
                }

                new Click().clickbylinkText("Adaptive Component Diagnostic");
                new Click().clickbylinkText(questiontype);
                int popup = 1;
                try {
                    WebDriverWait wait = new WebDriverWait(driver, 3);
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='cms-notification-message-body']")));

                } catch (Exception e) {
                    popup = 0;
                }
                //int popup = driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
                if (popup == 0) {
                    driver.findElement(By.id("assessmentName")).click();
                    driver.findElement(By.id("assessmentName")).clear();
                    driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
                    driver.findElement(By.id("questionSetName")).clear();
                    driver.findElement(By.id("questionSetName")).sendKeys(questionset);

                    if (reservforassignment == null) {
                        driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
                    }

                    if(overrideDefaultQuestionCreate ==null){
                        new QuestionCreate().trueFalseQuestions(dataIndex);
                    }else{
                        if(overrideDefaultQuestionCreate.equalsIgnoreCase("essay")){
                            new QuestionCreate().essay(dataIndex);
                        } else if(overrideDefaultQuestionCreate.equalsIgnoreCase("multipart")){
                            new QuestionCreate().multiPartQuestion(dataIndex);
                        }
                    }
                }

            } else {
                Assert.fail("CMS login failed");
            }
            if (!(Config.browser.equalsIgnoreCase("firefox"))) {
                driver.quit();
                startDriver("firefox");
            }
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper create in class AssignmentCreate", e);
        }
    }





    public void addQuestions(int dataIndex, String questionType, String assignmentname) {
        try {
            String course = "";
            assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String overrideDefaultLogin = ReadTestData.readDataByTagName("", "overrideDefaultLogin", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
            String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
            String course_name = ReadTestData.readDataByTagName("", "course_name", Integer.toString(dataIndex));
            String subSection_level = ReadTestData.readDataByTagName("", "subSection_level", Integer.toString(dataIndex));
            String chapterIndex = ReadTestData.readDataByTagName("", "chapterIndex", Integer.toString(dataIndex));
            String topicIndex = ReadTestData.readDataByTagName("", "topicIndex", Integer.toString(dataIndex));

            course = course_name;

            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }
            if (overrideDefaultLogin == null)
                new DirectLogin().CMSLogin();
            else {
                String role = ReadTestData.readDataByTagName("", "role", Integer.toString(dataIndex));
                new DBConnect().Connect();
                ResultSet rs = DBConnect.st.executeQuery("select id,username from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = '" + role + "') limit 1,1;");
                String username = "";
                while (rs.next()) {
                    username = rs.getString("username");
                    //System.out.println(username);
                }
                new DirectLogin().directLoginWithCreditial(username, "snapwiz", dataIndex);
                new Click().clickbyxpath("(//select[@name='product-drop-down']/following-sibling::div/a)[1]");
                new Click().clickbylinkText("Learning Space + Adaptive Component");
            }
            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    List<WebElement> allChapterswithSameName = driver.findElements(By.xpath("//div[contains(@title,'" + chapterName + "')]"));
                    driver.findElements(By.xpath("//div[contains(@title, '" + chapterName + "')]")).get(allChapterswithSameName.size() - 1).click();
                }
                Thread.sleep(3000);
                if (subSection_level != null) {
                    new SelectCourse().expandChapterTreeByIndex(dataIndex, Integer.parseInt(chapterIndex));
                    new SelectCourse().selectTopicByIndex(dataIndex, Integer.parseInt(topicIndex));

                }
                //driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                boolean assignmentExists = false;
                List<WebElement> elements = driver.findElements(By.xpath("//div[@title='" + assignmentname + "']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elements.get(elements.size()-1));
                Thread.sleep(5000);
                try {
                    new Click().clickByElement(elements.get(elements.size()-1));
                    //elements.get(elements.size() - 1).click();
                    assignmentExists = true;
                }
                catch (Exception e) {

                }

                if (assignmentExists == true)
                    addQuestionLink();
                Thread.sleep(2000);
                if (assignmentExists == true) {
                    if (questionType.equalsIgnoreCase("all")) {
                        new QuestionCreate().trueFalseQuestions(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().multipleChoice(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().multipleSelection(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().essay(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().writeBoard(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().audio(dataIndex);
                    } else if (questionType.equals("truefalse") || questionType.equals("qtn-type-true-false-img"))
                        new QuestionCreate().trueFalseQuestions(dataIndex);

                    else if (questionType.equals("multiplechoice") || questionType.equals("qtn-multiple-choice-img"))
                        new QuestionCreate().multipleChoice(dataIndex);

                    else if (questionType.equals("multipleselection") || questionType.equals("qtn-multiple-selection-img"))
                        new QuestionCreate().multipleSelection(dataIndex);

                    else if (questionType.equals("essay") || questionType.equals("qtn-essay-type"))
                        new QuestionCreate().essay(dataIndex);

                    else if (questionType.equals("writeboard") || questionType.equals("qtn-writeboard-type-new"))
                        new QuestionCreate().writeBoard(dataIndex);

                    else if (questionType.equals("audio") || questionType.equals("qtn-audio-type"))
                        new QuestionCreate().audio(dataIndex);

                }

            } //if condition  ends here
            if (!(Config.browser.equalsIgnoreCase("firefox"))) {
                driver.quit();
                startDriver("firefox");
            }
        } catch (Exception e) {

            Assert.fail("Exception in create in Apphelper addQuestions in class AssignmentCreate", e);
        }
    }

    public void addQuestionLink() {
        try {
            Thread.sleep(2000);
            new Click().clickByid("questionOptions");
            Thread.sleep(2000);
            new Click().clickByid("addQuestion");
        } catch (Exception e) {
            Assert.fail("Exception while clicking on add questions link in CMS", e);
        }

    }

}
