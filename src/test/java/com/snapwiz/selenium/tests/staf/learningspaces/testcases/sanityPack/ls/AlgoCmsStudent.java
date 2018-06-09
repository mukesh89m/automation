package com.snapwiz.selenium.tests.staf.learningspaces.testcases.sanityPack.ls;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R237.LangaugePaletteForQuestionCreation;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by priyanka on 18-11-2015.
 */
public class AlgoCmsStudent extends Driver {

    @Test(priority=1,enabled = true)
    public void trueFalseQuestionTypes()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //tc row no 2
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            create(2,"qtn-type-true-false-img");
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
            List<WebElement> ansChoiceText = driver.findElements(By.xpath("//label[@class='true-false-student-content-text']"));
            int index=0;
            for(WebElement ele:ansChoiceText){
                if(ele.getText().trim().equals("True")){
                    break;
                }
                index++;

            }
            System.out.println("Index:"+index);
            List<WebElement> ansChoiceOption = preview.trueFalseAnswerLabel;
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ansChoiceOption.get(index));

            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it right"
            Assert.assertEquals(preview.notificationMsg.getText().trim(), "You got it right.", "Message  \"You got it right\" is not displaying ");

            preview.trueFalseAnswerLabel.get(1).click();//click on the B answer which is incorrect
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it wrong"
            Assert.assertEquals(preview.notificationMsg.getText().trim(), "You got it wrong.", "Message  \"You got it wrong\" is not displaying ");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase trueFalseQuestionTypes of class LsCmsStudent" + e);
        }
    }


    @Test(priority=2,enabled = true)
    public void multipleChoiceQuestionTypes()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //tc row no 8
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            create(8,"qtn-multiple-choice-img");
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
            List<WebElement> ansChoiceText = driver.findElements(By.xpath("//div[@class='preview-single-select-answer-choice-text']"));
            int index=0;
            for(WebElement ele:ansChoiceText){
                if(ele.getText().trim().equals("3")){
                    break;
                }
                index++;

            }
            System.out.println("Index:"+index);
            List<WebElement> ansChoiceOption = preview.selectMultipleChoice;
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ansChoiceOption.get(index));

            preview.checkAnswer_button.click();//click on the check Answer button
            //Expected #1. It should show message as "You got it right"
            Assert.assertEquals(preview.notificationMsg.getText().trim(), "You got it right.", "Message  \"You got it wrong\" is not displaying ");
            //8. Click on preview button, select the wrong answer and click on "Check answer" button
            preview.selectMultipleChoice.get(0).click();//click on the A answer which is incorrect byDefault
            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);
            //Expected #1. It should show message as "You got it wrong"
            Assert.assertEquals(preview.notificationMsg.getText().trim(), "You got it wrong.", "Message  \"You got it right\" is not displaying ");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase trueFalseQuestionTypes of class LsCmsStudent" + e);
        }
    }

    @Test(priority=3,enabled = true)
    public void multipleSelectionQuestionTypes()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //tc row no 13
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            create(13,"qtn-multiple-selection-img");
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
            List<WebElement> ansChoiceOption = preview.multipleSelection;
            List<WebElement> ansChoiceText = driver.findElements(By.xpath("//div[@class='preview-multiple-select-answer-choice-text']"));
            for(int i=0;i<ansChoiceText.size()-1;i++){
                System.out.println(ansChoiceText.get(i).getText());
                if(ansChoiceText.get(i).getText().trim().equals("3")){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ansChoiceOption.get(i));
                    Thread.sleep(3000);

                }
            }

            preview.checkAnswer_button.click();//click on the check Answer button
            //Expected #1. It should show message as "You got it right"
            Assert.assertEquals(preview.notificationMsg.getText().trim(), "You got it right.", "Message  \"You got it right.\" is not displaying ");
            Thread.sleep(2000);

            List<WebElement> ansChoiceOption2 = preview.multipleSelection;
            List<WebElement> ansChoiceText2 = driver.findElements(By.xpath("//div[@class='preview-multiple-select-answer-choice-text']"));
            for(int i=0;i<ansChoiceText2.size()-1;i++){
                if(ansChoiceText2.get(i).getText().trim().equals("1")){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ansChoiceOption2.get(i));
                    Thread.sleep(3000);
                    break;

                }
            }
            //8. Click on preview button, select the wrong answer and click on "Check answer" button
            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);

            //Expected #1. It should show message as "You got it wrong"
            Assert.assertEquals(preview.notificationMsg.getText().trim(), "You got it wrong.", "Message  \"You got it wrong.\" is not displaying ");
            Thread.sleep(1000);
            List<WebElement> lst = driver.findElements(By.cssSelector("div[class='multiple-select-choice-icon-preview multiple-select-choice-icon-deselect'] >span"));

            lst.get(0).click();
            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(4000);
            Assert.assertEquals(preview.notificationMsg.getText().trim(), "You got it partially correct.", "Message  \"You got it Partially Correct.\" is not displaying ");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase trueFalseQuestionTypes of class LsCmsStudent" + e);
        }
    }

    @Test(priority=4,enabled = true)
    public void textEntryQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 18
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                Preview preview = PageFactory.initElements(driver, Preview.class);

                create(18,"qtn-text-entry-img");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
                preview.textEntryField.sendKeys("2");
                preview.checkAnswer_button.click();//click on answer choice
                String wrongNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");
                Thread.sleep(2000);
                preview.textEntryField.clear();
                preview.textEntryField.sendKeys("3");
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(1000);
                String rightNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");
            } catch (Exception e) {
                Assert.fail("Exception in testcase textEntryQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }
    @Test(priority=5,enabled = true)
    public void advancedNumericQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //TC row no 24
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                Preview preview = PageFactory.initElements(driver, Preview.class);
                create(24,"qtn-numeric-advanced-img");

                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
                preview.textEntryFields.get(0).sendKeys("3");
                preview.textEntryFields.get(1).sendKeys("3");
                preview.checkAnswer_button.click();//click on answer choice
                new UIElement().waitAndFindElement(preview.notificationMsg);
                Assert.assertEquals(preview.notificationMsg.getText().trim(), "You got it right.", " \"You got it right\" It is not showing the message based on the selected option");
                Thread.sleep(2000);
                preview.textEntryFields.get(0).clear();
                preview.textEntryFields.get(1).clear();
                preview.textEntryFields.get(0).sendKeys("5");
                preview.textEntryFields.get(1).sendKeys("5");
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(4000);
                new UIElement().waitAndFindElement(preview.gotItWrong);
                Assert.assertEquals(preview.gotItWrong.getText().trim(), "You got it wrong.", " \"You got it wrong.\"It is not showing the message based on the selected option");

                preview.textEntryFields.get(1).clear();
                preview.textEntryFields.get(1).sendKeys("3");
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(3000);
                new UIElement().waitAndFindElement(preview.notificationMsg);
                Assert.assertEquals(preview.notificationMsg.getText().trim(), "You got it partially correct.", "Message  \"You got it Partially Correct.\" is not displaying ");


            } catch (Exception e) {
                Assert.fail("Exception in TC advancedNumericQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }


    @Test(priority=6,enabled = true)
    public void numericEntryWithUnitsQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 30
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                Preview preview = PageFactory.initElements(driver, Preview.class);
                create(24,"qtn-text-entry-numeric-units-img");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
                preview.numericEntryDropDown.click();
                preview.numericEntryTextBox.sendKeys("3");
                preview.numericEntryTextBoxs.get(1).sendKeys("3");

                Select select=new Select(driver.findElement(By.xpath("(//select[@class='question-raw-content-dropdown numeric-unit-preview-selectbox'])[2]")));
                select.selectByIndex(1);
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(3000);
                Assert.assertEquals(preview.notificationMsg.getText().trim(), "You got it right.", " \"You got it right\" It is not showing the message based on the selected option");
                Thread.sleep(2000);
                preview.numericEntryTextBoxs.get(1).sendKeys("10");
                Select select1=new Select(driver.findElement(By.xpath("(//select[@class='question-raw-content-dropdown numeric-unit-preview-selectbox'])[2]")));
                select1.selectByIndex(2);
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(3000);
                Assert.assertEquals(preview.notificationMsg.getText().trim(), "You got it partially correct.", "Message  \"You got it Partially Correct.\" is not displaying ");

                preview.numericEntryTextBox.clear();
                preview.numericEntryTextBox.sendKeys("10");
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(3000);
                Assert.assertEquals(preview.notificationMsg.getText().trim(), "You got it wrong.", " \"You got it wrong.\"It is not showing the message based on the selected option");




            } catch (Exception e) {
                Assert.fail("Exception in test case numericEntryWithUnitsQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }


    @Test(priority=7,enabled = true)
    public void expressionEvaluatorQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 36
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                Preview preview = PageFactory.initElements(driver, Preview.class);

                create(36, "qtn-expression-evaluator-img");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
                preview.expressionEvaluator.click();
                new Click().clickByid("answer_math_edit");
                driver.findElement(By.className("wrs_focusElement")).sendKeys("3");
                driver.findElement(By.id("wiris-answer-container-save-choice1")).click();
                preview.checkAnswer_button.click();//click on answer choice
                String wrongNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(wrongNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");
                Thread.sleep(2000);
                preview.expressionEvaluator.click();
                new Click().clickByid("answer_math_edit");
                new QuestionCreate().enterValueInMathMLEditor("Square root", "5");
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(1000);
                String rightNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(rightNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");
            } catch (Exception e) {
                Assert.fail("Exception in test case expressionEvaluatorQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }


    @Test(priority=8,enabled = true)
    public void essayQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 41
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                Preview preview = PageFactory.initElements(driver, Preview.class);
                create(24,"qtn-essay-type");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
                preview.mathEditor.click();
                preview.cancelButton.click();
                preview.language.click();

            } catch (Exception e) {
                Assert.fail("Exception in test case expressionEvaluatorQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }


    @Test(priority=9,enabled = true)
    public void textEntryWithDropDownQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 45
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                Preview preview = PageFactory.initElements(driver, Preview.class);
                create(24,"qtn-text-entry-drop-down-img");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
                preview.textEntryDropDown.click();
                preview.checkAnswer_button.click();//click on answer choice
                String rightNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");
                Thread.sleep(2000);
                driver.findElement(By.xpath("(//select[@class='question-raw-content-dropdown'])[1]")).click();
                WebElement ele= driver.findElement(By.xpath("(//select[@class='question-raw-content-dropdown'])[1]"));
                Select sel = new Select(ele);
                sel.selectByIndex(2);
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(2000);
                String wrongNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(wrongNotificationMessage, "You got it wrong.", " It is not showing the message based on the selected option");

            } catch (Exception e) {
                Assert.fail("Exception in test case expressionEvaluatorQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }

    @Test(priority=11,enabled = true)
    public void dragAndDropQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 54
                Preview preview = PageFactory.initElements(driver, Preview.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                create(54,"qtn-dradanddrop");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
                List<WebElement> answerstodrag = driver.findElements(By.cssSelector("div[class='dnd-preview-draggable-answer ui-draggable ui-draggable-handle']"));
                Actions ac = new Actions(driver);
                ac.clickAndHold(answerstodrag.get(1))
                        .moveToElement(driver.findElement(By.id("dragged-ans-choice")))
                        .release()
                        .build()
                        .perform();
                preview.checkAnswer_button.click();//click on answer choice
                String rightNotificationMessage = preview.notificationMsg.getText();
                Assert.assertEquals(rightNotificationMessage, "You got it right.", " It is not showing the message based on the selected option");

                ac.clickAndHold(answerstodrag.get(0))
                        .moveToElement(driver.findElement(By.id("dragged-ans-choice")))
                        .release()
                        .build()
                        .perform();
                preview.checkAnswer_button.click();//click on answer choice
                Thread.sleep(3000);
                Assert.assertEquals(preview.notificationMsg.getText().trim(), "You got it wrong.", " \"You got it wrong.\"It is not showing the message based on the selected option");

            } catch (Exception e) {
                Assert.fail("Exception in test case dragAndDropQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }


    @Test(priority=12,enabled = true)
    public void matchTheFollowingQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 62
                Preview preview = PageFactory.initElements(driver, Preview.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                create(62,"qtn-matchthefollowing");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
                List<WebElement> answerSize=driver.findElements(By.cssSelector("div[class='match-answer match-answer-draggable cursor-pointer ui-draggable ui-draggable-handle']"));
                Assert.assertEquals(answerSize.size(),7,"Question is not saved after Distractor and new Row");
            } catch (Exception e) {
                Assert.fail("Exception in test case matchTheFollowingQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }


    @Test(priority=13,enabled = true)
    public void labelAnImageTextQuestionTypes() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 68
                Preview preview = PageFactory.initElements(driver, Preview.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                new Assignment().create(68);
                new Assignment().addQuestions(68, "labelAnImageText", "");
                manageContent.preview_Button.click();//click on preview
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                }
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));

            } catch (Exception e) {
                Assert.fail("Exception in test case labelAnImageTextQuestionTypes of class LsCmsStudent" + e);
            }
        }

    }



    @Test(priority=14,enabled = true)
    public void varificationOfAlgoStudentSide() {
        {
            WebDriver driver=Driver.getWebDriver();
            try {
                //tc row no 68
                Preview preview = PageFactory.initElements(driver, Preview.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                new Assignment().create(70);
                new Assignment().addQuestions(70, "multiplechoice", "");
                new Assignment().addQuestions(70, "multipleselection", "");
                //new Assignment().addQuestions(70,"textentry","");
                new Assignment().addQuestions(70, "textdropdown", "");
                new Assignment().addQuestions(70, "numericentrywithunits", "");
                new Assignment().addQuestions(70, "advancednumeric", "");
                new Assignment().addQuestions(70, "expressionevaluator", "");
                new Assignment().addQuestions(70, "essay", "");
                new Assignment().addQuestions(70, "draganddrop", "");
                new Assignment().addQuestions(70, "matchthefollowing", "");
                new Assignment().addQuestions(70, "labelAnImageText", "");

                new LoginUsingLTI().ltiLogin("70");
                new Assignment().assignToStudent(70);


                new LoginUsingLTI().ltiLogin("70_1");
                new Assignment().submitAssignmentAsStudent(70);

            } catch (Exception e) {
                Assert.fail("Exception in test case varificationOfAlgoStudentSide of class LsCmsStudent" + e);
            }
        }

    }

    public void create(int dataIndex, String questionType) {
        Config.readconfiguration();
        WebDriver driver=Driver.getWebDriver();
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
                    Thread.sleep(10000);
                    if (driver.findElements(By.xpath("//div[contains(@title,'" + chapterName + "')]")).size() > 1) {
                        System.out.println("inside if:" + chapterName);
                        new TopicOpen().openCMSLastChapter();
                    } else {
                        System.out.println("inside else:" + chapterName);
                        List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                        for (WebElement chapters : allChapters) {
                            if (chapters.getText().contains(chapterName)) {
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters);
                                Thread.sleep(4000);
                                break;
                            }

                        }
                    }

                }
                driver.findElement(By.cssSelector("div.create-practice")).click();

                if (practice_type == null) {
                    new Click().clickByclassname("create-regular-assessment-popup-item"); //click on the create regular assessment
                } else {
                    new Click().clickByclassname("create-file-assessment-popup-item");//click on the create file assessment
                }
                new UIElement().waitAndFindElement(By.cssSelector("a[selectedid='Adaptive Component Diagnostic']"));
                new Click().clickBycssselector("a[selectedid='Adaptive Component Diagnostic']");
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

                    if (questionType.equals("qtn-type-true-false-img")) //1.For true-false type question
                    {
                        trueFalseQuestions(dataIndex);
                    }

                    if (questionType.equals("qtn-multiple-choice-img")) //2.For multiple choice type question
                    {
                        multipleChoice(dataIndex);
                    }

                    if (questionType.equals("qtn-multiple-selection-img")) //3.For multiple selection type question
                    {
                        multipleSelection(dataIndex);
                    }
                    if (questionType.equals("qtn-text-entry-img")) //4.For textEntry type question
                    {
                        textEntry(dataIndex);
                    }

                    if (questionType.equals("qtn-numeric-advanced-img")) //5.For Advanced Numeric type question
                    {
                        advancedNumeric(dataIndex);
                    }

                    if (questionType.equals("qtn-text-entry-numeric-units-img")) //6.For Numeric With Units type question
                    {
                        numericEntryWithUnits(dataIndex);
                    }

                    if (questionType.equals("qtn-essay-type")) //7.For Essay type question
                    {
                        essay(dataIndex);
                    }
                    if (questionType.equals("qtn-text-entry-drop-down-img")) //8.For text DropDown type question
                    {
                        textDropDown(dataIndex);
                    }
                    if (questionType.equals("qtn-dradanddrop")) //9.For  DropDown type question
                    {
                        dragAndDrop(dataIndex);
                    }
                    if (questionType.equals("qtn-matchthefollowing")) //9.For  DropDown type question
                    {
                        matchTheFollowing(dataIndex);
                    }
                    if (questionType.equals("qtn-expression-evaluator-img")) //1.For expression-evaluator type question
                    {
                        expressionEvaluator(dataIndex);
                    }
                }

            } else {
                Assert.fail("CMS login failed");
            }
            if (!(Config.browser.equalsIgnoreCase("firefox"))) {
                driver.quit();
                new ReInitDriver().startDriver("firefox");
            }
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper create in class AssignmentCreate", e);
        }
    }


    public void trueFalseQuestions(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));


            new Click().clickbylistid("qtn-type-true-false-img", 0);//click on True/False type question
            new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']");//check shuffle answer choice
            new WebDriverWait(driver, 1200).until(ExpectedConditions.presenceOfElementLocated(By.className("true-false-answer-select")));
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).clear();//type the question
            driver.findElement(By.id("question-raw-content")).sendKeys(questiontext);//type the question
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option A
            Thread.sleep(2000);

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper QuestionCreate in method trueFalseQuestions.", e);
        }
    }

    public void saveQuestion(String learningobjective,String solutionText, String hintText, String useWriteBoard, String difficultylevel, String variationLevel,String withImageInHint,String withImageInSolution, int dataIndex, String score) {
        try {
            WebDriver driver=Driver.getWebDriver();
            if (useWriteBoard != null) {
                new Click().clickBycssselector("#content-writeboard > #writeboard");//check the use writeboard check box
            }
            if (difficultylevel != null) {
                new Click().clickbylinkText("Difficulty Level");
                new UIElement().waitAndFindElement(By.linkText(difficultylevel));
                new Click().clickbylinkText(difficultylevel);
            }
            if (learningobjective != null)
                associateTlo(dataIndex, learningobjective);//add TLO

            if (solutionText == null || solutionText.equals("true")) {
                if (withImageInSolution != null) {
                    if (withImageInSolution.equals("true"))
                        driver.findElement(By.id("content-solution")).sendKeys("Solution Text");
                } else
                    new TextSend().textsendbyid("Solution Text", "content-solution");
            }


            if (score != null) {
                driver.findElement(By.id("questionScore")).clear();
                new TextSend().textsendbyid(score, "questionScore");
            }

            if (hintText == null || hintText.equals("true")) {
                if (withImageInHint != null) {
                    if (withImageInHint.equals("true"))
                        driver.findElement(By.id("content-hint")).sendKeys("Hint Text");
                    else
                        new TextSend().textsendbyid("Hint Text", "content-hint");
                } else
                    new TextSend().textsendbyid("Hint Text", "content-hint");
            }

            String algorithmic = ReadTestData.readDataByTagName("", "algorithmic", Integer.toString(dataIndex));
            if (algorithmic != null) {
                if (algorithmic.equals("true")) {
                    Thread.sleep(3000);
                    WebElement element = driver.findElement(By.id("algorithmic"));
                    driver.findElement(By.xpath("//html/body")).click();
                    new ScrollElement().scrollToViewOfElement(element);
                    new Click().clickBycssselector("span[class='algorithmicExpendOptions parameters-expand']"); //click on the + icon to expan
                    driver.findElement(By.className("ace_text-input")).sendKeys("var " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "a = 1;\n" +
                            "var " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "b = 2;\n" +
                            "var " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "c = " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "a+" + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "b;");
                }
            }

            if (variationLevel != null) {
                new Click().clickbyxpath("//div[@class='footer-bloomcode']/div/a");
                new Click().clickbyxpath("//div[@class='footer-bloomcode']//ul/li/a[@rel='" + variationLevel + "']");
            }
            new Click().clickbylinkText("Draft"); //click on Draft optio
            new Click().clickbylinkText("Publish");    //click on Publish option if isPublished is null or true
            new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
            new Click().clickByid("saveQuestionDetails1");//click on save button
        }

        catch (Exception e)
        {
            Assert.fail("Exception while saving a question",e);
        }
    }

    public void associateTlo(int dataIndex, String learningobjective)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            driver.findElement(By.id("learing-objectives-span")).click(); //Clicking on Learning objective drop-down from footer
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id("link-add-learning-objectives")));
            driver.findElement(By.id("link-add-learning-objectives")).click(); //clicking on 'Add Learning Objective' link
            Thread.sleep(2000);
            if(learningobjective.equals("true"))
                driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[3]/label")).click();
            else {
                WebElement element = driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[" + learningobjective + "]/label"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[" + learningobjective + "]/label")).click();
                Thread.sleep(3000);
            }
            new Click().clickBycssselector("span.add-collection-to-subtopic");//click on Associate
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper QuestionCreate in method associateTlo.", e);
        }
    }


    public void multipleChoice(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));

            new Click().clickbylistid("qtn-multiple-choice-img", 0);//click on Multiple Choice type question
            new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-checked']");//Uncheck shuffle answer choice
            new Click().clickByid("question-mc-raw-content");//click on Question
            driver.findElement(By.id("question-mc-raw-content")).sendKeys(questiontext);//type the question
            new Click().clickBycssselector("div[class='single-select-choice-icon single-select-choice-icon-deselect']"); //select correct answer as A
            List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("$c");
            answerOptions.get(1).sendKeys("$b");
            answerOptions.get(2).sendKeys("$a");
            answerOptions.get(3).sendKeys("5");

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper QuestionCreate in method multipleChoice.", e);
        }
    }

    public void multipleSelection(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));

            new Click().clickbylistid("qtn-multiple-selection-img", 0);//click on Multiple Selection type question
            new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-checked']");//Uncheck shuffle answer choice
            new Click().clickByid("question-ms-raw-content");//click on Question
            driver.findElement(By.id("question-ms-raw-content")).sendKeys(questiontext);//type the question
            List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("$c");
            answerOptions.get(1).sendKeys("$c");
            answerOptions.get(2).sendKeys("$a");
            answerOptions.get(3).sendKeys("$b");;
            new Click().clickByid("swuploadclose");//close pop-up
            List<WebElement> multipleSelections = driver.findElements(By.cssSelector("div[class='multiple-select-choice-icon multiple-select-choice-icon-deselect']"));
            multipleSelections.get(0).click(); multipleSelections.get(1).click(); //selecting option A and B as correct answers
            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating multiple selection question type", e);
        }
    }

    public void textEntry(int dataIndex) {
        try {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));


            new Click().clickbylistid("qtn-text-entry-img", 0);//click on Text Entry type question
            new Click().clickByid("question-raw-content");//click on Question
            Thread.sleep(5000);
            driver.findElement(By.id("question-raw-content")).sendKeys(questiontext);//type the question
            new Click().clickByid("question-raw-content");//click on Question
            new TextSend().textsendbycssSelector("$c", "input[class='numeric_text_entry_input get-user-entry is-value-changed-txt-entry']");
            new Click().clickBycssselector("span[class='btn sty-green save-language-text accept_answer text_entry_accept_answer']"); //click on Accept answer button

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, withImageInHint, withImageInSolution, dataIndex, score);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating multiple selection question type", e);
        }
    }


    public void advancedNumeric(int dataIndex) {
        try {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));


            new Click().clickByid("qtn-numeric-advanced-img");//click on Advanced Numeric question
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.id("question-edit")));
            Thread.sleep(3000);
            driver.findElement(By.id("question-raw-content")).sendKeys(questiontext);//type the question
            List<WebElement> elements=driver.findElements(By.cssSelector("input[class='numeric_correct_text_entry_input num-entry-ans border-color-gray']"));
            for(WebElement ele:elements){
                if(ele.isDisplayed()){
                    ele.sendKeys("$c");
                }
            }
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer accept_answer_maple_numeric']"); //click on accept answer

            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.id("question-edit")));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='re-icon re-maplenumeric re-entry-box redactor-btn-image']")));
            Thread.sleep(3000);
            List<WebElement> elements1=driver.findElements(By.cssSelector("input[class='numeric_correct_text_entry_input num-entry-ans border-color-gray']"));
            for(WebElement ele:elements1){
                if(ele.isDisplayed()){
                    ele.sendKeys("$c");
                }
            }

            List<WebElement> acceptAnswer=driver.findElements(By.cssSelector("span[class='done-button btn sty-green tab_event_widget accept_answer accept_answer_maple_numeric']"));
            for(WebElement ele:acceptAnswer){
                if(ele.isDisplayed()){
                    ele.click();
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);


        } catch (Exception e) {
            Assert.fail("Exception while creating Advanced Numeric Question",e);
        }
    }
    public void numericEntryWithUnits(int dataIndex) {
        try {
            WebDriver driver=Driver.getWebDriver();
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));


            new Click().clickbylistid("qtn-text-entry-numeric-units-img", 0);//click on Numeric Entry with Units type question
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.id("question-edit")));
            Thread.sleep(2000);
            driver.findElement(By.id("question-raw-content")).sendKeys("Numeric Entry With Units " + questiontext);//type the question
            new TextSend().textsendbycssSelector("$c", "input[class='numeric_unit_correct_answer numeric_text_entry numeric_text_entry_input is-value-changed']");
            new Click().clickBycssselector("div[class='unit-arrow-down add-more-num-entry-unit']"); //click on add more units
            new Click().clickBycssselector("li[value='feet']"); //select feet
            new Click().clickByclassname("unit_tick_image"); //click on the tick mark for unit
            new Click().clickBycssselector("div[class='add-more-num-entry-unit unit-arrow-down']"); //click on add more units
            new Click().clickBycssselector("li[value='yards']"); //select yards
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget num_ent_unit_accept_answer']"); //click on accept answer


            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.id("question-edit")));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='re-icon re-numericunit re-entry-box redactor-btn-image']")));
            Thread.sleep(2000);
            List<WebElement> elements1=driver.findElements(By.cssSelector("input[class='numeric_unit_correct_answer numeric_text_entry numeric_text_entry_input is-value-changed']"));
            for(WebElement ele:elements1){
                if(ele.isDisplayed()){
                    ele.sendKeys("$c");
                }
            }

            new Click().clickBycssselector("div[class='unit-arrow-down add-more-num-entry-unit']"); //click on add more units
            new Click().clickbylistcssselector("li[value='feet']", 2); //select feet
            List<WebElement> tickMark=driver.findElements(By.className("unit_tick_image"));
            for(WebElement ele:tickMark){
                if(ele.isDisplayed()){
                    ele.click();
                }
            }
            new Click().clickbylistcssselector("div[class='add-more-num-entry-unit unit-arrow-down']", 1); //click on add more units
            new Click().clickbylistcssselector("li[value='yards']", 2); //select yards
            List<WebElement> acceptAnswer=driver.findElements(By.cssSelector("span[class='done-button btn sty-green tab_event_widget num_ent_unit_accept_answer']"));
            for(WebElement ele:acceptAnswer){
                if(ele.isDisplayed()){
                    ele.click();
                }
            }

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, withImageInHint, withImageInSolution, dataIndex, score);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating Numeric Entry With Units question",e);
        }
    }

    public void essay(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));


            new Click().clickbylistid("qtn-essay-type", 0);//click on Essay type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Essay "+questiontext);//type the question
            new Click().clickByid("essay-question-height");//click on Min line height textbox
            new TextSend().textsendbyid("2", "essay-question-height");
            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e) {
            Assert.fail("Exception while creating essay question type",e);
        }
    }
    public void textDropDown(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));

            new Click().clickbylistid("qtn-text-entry-drop-down-img", 0);//click on Text Entry Drop Down type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Text Drop Down "+questiontext);//type the question
            new Click().clickByclassname("text-drop-val");
            new TextSend().textsendbyid("$c","ans1");

            new Click().clickByclassname("select-icon-text-drop-down"); //select answer 1 as correct answer

            driver.findElements(By.className("text-drop-val")).get(1).click();
            driver.findElements(By.id("ans1")).get(1).sendKeys("$a");

            driver.findElements(By.className("text-drop-val")).get(2).click();
            driver.findElements(By.id("ans1")).get(2).sendKeys("$b");

            new Click().clickBycssselector("span[class='done-button btn sty-green text-drop-accept accept_answer']"); //click on accept answer button

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e) {
            Assert.fail("Exception while creating text entry drop down question",e);
        }
    }

    public void dragAndDrop(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));

            new Click().clickByid("qtn-dnd-type");//click on Drag and Drop type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Drag and Drop " + questiontext);//type the question
            List<WebElement> answers = driver.findElements(By.className("answer"));
            answers.get(0).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","5");


            answers.get(1).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("$c", "answer_choice_txt");

            answers.get(2).click();
            new Click().clickByid("answer_image_edit");
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-answer-choice-image");
            new UIElement().waitAndFindElement(By.cssSelector("img[class='answer-choice-image-size']"));
            answers.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","10");

            List<WebElement> answerstodrag = driver.findElements(By.className("answer"));
            Actions ac = new Actions(driver);
            answerstodrag.get(1).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();
            driver.findElement(By.id("isShuffleAnswerChoice")).click();

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, withImageInHint, withImageInSolution, dataIndex, score);

        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating Drag and Drop Question",e);
        }
    }
    public void enterValueInMathMLEditor(String operation,String value)
    {
        WebDriver driver=Driver.getWebDriver();
        try {

            driver.findElement(By.cssSelector("button[title='"+operation+"']")).click();
            driver.findElement(By.className("wrs_focusElement")).sendKeys(value);
            driver.findElement(By.id("wiris-answer-container-save-choice1")).click();
        }
        catch (Exception e){
            Assert.fail("Exception while entering value in Math ML editor");
        }
    }

    public void matchTheFollowing(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));

            new Click().clickByid("qtn-mtf-type");//click on Match the Following type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Match the Following " + questiontext);//type the question
            List<WebElement> lhsboxes = driver.findElements(By.cssSelector("div[class='dnd-match-lhs box']")); //Fetching all the lhs boxes

            lhsboxes.get(0).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","5");

            lhsboxes.get(1).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            lhsboxes.get(2).click();
            new Click().clickByid("answer_image_edit");
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-answer-choice-image");
            new UIElement().waitAndFindElement(By.cssSelector("img[class='answer-choice-img answer-choice-image-size']"));
            lhsboxes.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","6");

            lhsboxes.get(4).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","7");

            //Filling RHS

            List<WebElement> rhsboxes = driver.findElements(By.cssSelector("div[class='dnd-match-rhs box']")); //Fetching all the lhs boxes

            rhsboxes.get(0).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","5");

            rhsboxes.get(1).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            rhsboxes.get(2).click();
            new Click().clickByid("answer_image_edit");
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-answer-choice-image");
            new UIElement().waitAndFindElement(By.cssSelector("img[class='answer-choice-img answer-choice-image-size']"));
            rhsboxes.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","6");

            rhsboxes.get(4).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","7");

            driver.findElement(By.xpath(".//*[@id='add-distractor']/span/span")).click();
            List<WebElement> rhsboxes1 = driver.findElements(By.cssSelector("div[class='dnd-match-rhs box']")); //Fetching all the lhs boxes
            rhsboxes1.get(5).click();
            Thread.sleep(2000);
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            driver.findElement(By.xpath(".//*[@id='add-new-row']/span/span")).click();
            List<WebElement> rhsboxes2 = driver.findElements(By.cssSelector("div[class='dnd-match-rhs box']")); //Fetching all the lhs boxes
            rhsboxes2.get(6).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("100", "answer_choice_txt");

            List<WebElement> lhsboxes1 = driver.findElements(By.xpath("//tr[@class='distractor']/following-sibling::tr/td/div/div")); //Fetching all the lhs boxes
            lhsboxes1.get(0).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("100", "answer_choice_txt");

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, withImageInHint, withImageInSolution, dataIndex, score);

        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating Match the Following Question",e);
        }
    }
    public void expressionEvaluator(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));

            new Click().clickByid("qtn-expression-evaluator-img");//click on Expression Evaluator type question
            new Click().clickByid("question-raw-content");//click on Question
            Thread.sleep(6000);
            driver.findElement(By.id("question-raw-content")).sendKeys(questiontext);//type the question
            new Click().clickByid("question-raw-content");//click on Question
            new Click().clickByid("answer_math_edit");
            driver.findElement(By.className("wrs_focusElement")).sendKeys(Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "c");
            driver.findElement(By.id("wiris-answer-container-save-choice1")).click();
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']"); //click on accept answer
            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, withImageInHint, withImageInSolution, dataIndex, score);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating Expression Evaluator Question",e);
        }
    }
}