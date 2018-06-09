package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.NewQuestionDataEntry;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import  com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;


public class Assignment extends Driver {
    static String appendChar = System.getProperty("UCHAR");

    public Assignment() {
        Config.readconfiguration();
    }

    public void create(int dataIndex) {

        WebDriver driver = Driver.getWebDriver();
        try {
            if (appendChar == null) {
                appendChar = "b";
            }
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
                if (chapterName == null) {
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
               /* int popup = 1;
                WebDriver driver=Driver.getWebDriver();try{
                    WebDriverWait wait = new WebDriverWait(driver, 3);
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='cms-notification-message-body']")));

                } catch (Exception e) {
                    popup = 0;
                }
                //int popup = driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
                if (popup == 0) {*/
                driver.findElement(By.id("assessmentName")).click();
                driver.findElement(By.id("assessmentName")).clear();
                driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
                driver.findElement(By.id("questionSetName")).clear();
                driver.findElement(By.id("questionSetName")).sendKeys(questionset);

                if (reservforassignment == null) {
                    driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
                }

                //This will create true false question byDefault if you don't provide Parameter as overrideDefaultQuestionCreate
                if (overrideDefaultQuestionCreate == null) {
                    new QuestionCreate().trueFalseQuestions(dataIndex);
                } else {

                    //This will create question based on the supplied parameter
                    if (overrideDefaultQuestionCreate.equals("multiplechoice") || overrideDefaultQuestionCreate.equals("qtn-multiple-choice-img"))
                        new QuestionCreate().multipleChoice(dataIndex);

                    else if (overrideDefaultQuestionCreate.equals("multipleselection") || overrideDefaultQuestionCreate.equals("qtn-multiple-selection-img"))
                        new QuestionCreate().multipleSelection(dataIndex);

                    else if (overrideDefaultQuestionCreate.equals("textentry") || overrideDefaultQuestionCreate.equals("qtn-text-entry-img"))
                        new QuestionCreate().textEntry(dataIndex);

                    else if (overrideDefaultQuestionCreate.equals("textdropdown") || overrideDefaultQuestionCreate.equals("qtn-text-entry-drop-down-img"))
                        new QuestionCreate().textDropDown(dataIndex);

                    else if (overrideDefaultQuestionCreate.equals("numericentrywithunits") || overrideDefaultQuestionCreate.equals("qtn-text-entry-numeric-units-img"))
                        new QuestionCreate().numericEntryWithUnits(dataIndex);

                    else if (overrideDefaultQuestionCreate.equals("advancednumeric"))
                        new QuestionCreate().advancedNumeric(dataIndex);

                    else if (overrideDefaultQuestionCreate.equals("expressionevaluator"))
                        new QuestionCreate().expressionEvaluator(dataIndex);

                    else if (overrideDefaultQuestionCreate.equals("essay") || overrideDefaultQuestionCreate.equals("qtn-essay-type"))
                        new QuestionCreate().essay(dataIndex);

                    else if (overrideDefaultQuestionCreate.equals("writeboard") || overrideDefaultQuestionCreate.equals("qtn-writeboard-type-new"))
                        new QuestionCreate().writeBoard(dataIndex);

                    else if (overrideDefaultQuestionCreate.equals("audio") || overrideDefaultQuestionCreate.equals("qtn-audio-type"))
                        new QuestionCreate().audio(dataIndex);

                    else if (overrideDefaultQuestionCreate.equals("multipart") || overrideDefaultQuestionCreate.equals("qtn-multi-part"))
                        new QuestionCreate().multiPartQuestion(dataIndex);

                    else if (overrideDefaultQuestionCreate.equals("draganddrop") || overrideDefaultQuestionCreate.equals("qtn-dradanddrop"))
                        new QuestionCreate().dragAndDrop(dataIndex);

                    else if (overrideDefaultQuestionCreate.equals("matchthefollowing") || overrideDefaultQuestionCreate.equals("qtn-matchthefollowing"))
                        new QuestionCreate().matchTheFollowing(dataIndex);

                    else if (overrideDefaultQuestionCreate.equals("labelAnImageText") || overrideDefaultQuestionCreate.equals("qtn-lbl-on-img-type"))
                        new QuestionCreate().labelAnImageText(dataIndex);

                    else if (overrideDefaultQuestionCreate.equals("labelAnImageDropdown") || overrideDefaultQuestionCreate.equals("qtn-lbl-dropdown-type"))
                        new QuestionCreate().labelAnImageDropdown(dataIndex);
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

    public void addQuestions(int dataIndex, String questionType, String assignmentname) {
        WebDriver driver = Driver.getWebDriver();
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
                    List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                    for (WebElement chapters : allChapters) {
                        if (chapters.getText().contains(chapterName)) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters);
                            Thread.sleep(4000);
                            break;
                        }

                    }

                }
                Thread.sleep(3000);
                if (subSection_level != null) {
                    new SelectCourse().expandChapterTreeByIndex(dataIndex, Integer.parseInt(chapterIndex));
                    new SelectCourse().selectTopicByIndex(dataIndex, Integer.parseInt(topicIndex));

                }
                //driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                boolean assignmentExists = false;
                List<WebElement> elements = driver.findElements(By.xpath("//div[@title='" + assignmentname + "']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elements.get(elements.size() - 1));
                Thread.sleep(5000);
                try {
                    new Click().clickByElement(elements.get(elements.size() - 1));
                    //elements.get(elements.size() - 1).click();
                    assignmentExists = true;
                    System.out.println("assignmentExists:" + assignmentExists);
                } catch (Exception e) {

                }
             /*   for (WebElement content : elements) {
                    if (content.getText().trim().equals(assignmentname)) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", content);
                        assignmentExists = true;
                        break;
                    }
                }*/
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
                        new QuestionCreate().textEntry(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().textDropDown(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().numericEntryWithUnits(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().advancedNumeric(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().expressionEvaluator(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().essay(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().writeBoard(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().audio(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().labelAnImageText(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().labelAnImageDropdown(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().dragAndDrop(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().matchTheFollowing(dataIndex);

                    } else if (questionType.equals("truefalse") || questionType.equals("qtn-type-true-false-img"))
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

                    else if (questionType.equals("advancednumeric"))
                        new QuestionCreate().advancedNumeric(dataIndex);

                    else if (questionType.equals("expressionevaluator"))
                        new QuestionCreate().expressionEvaluator(dataIndex);

                    else if (questionType.equals("essay") || questionType.equals("qtn-essay-type"))
                        new QuestionCreate().essay(dataIndex);

                    else if (questionType.equals("writeboard") || questionType.equals("qtn-writeboard-type-new"))
                        new QuestionCreate().writeBoard(dataIndex);

                    else if (questionType.equals("audio") || questionType.equals("qtn-audio-type"))
                        new QuestionCreate().audio(dataIndex);

                    else if (questionType.equals("multipart") || questionType.equals("qtn-multi-part"))
                        new QuestionCreate().multiPartQuestion(dataIndex);

                    else if (questionType.equals("draganddrop") || questionType.equals("qtn-dradanddrop"))
                        new QuestionCreate().dragAndDrop(dataIndex);

                    else if (questionType.equals("matchthefollowing") || questionType.equals("qtn-matchthefollowing"))
                        new QuestionCreate().matchTheFollowing(dataIndex);

                    else if (questionType.equals("labelAnImageText") || questionType.equals("qtn-lbl-on-img-type"))
                        new QuestionCreate().labelAnImageText(dataIndex);

                    else if (questionType.equals("labelAnImageDropdown") || questionType.equals("qtn-lbl-dropdown-type"))
                        new QuestionCreate().labelAnImageDropdown(dataIndex);
                }

            } //if condition  ends here
            if (!(Config.browser.equalsIgnoreCase("firefox"))) {
                driver.quit();
                new ReInitDriver().startDriver("firefox");
            }
        } catch (Exception e) {

            Assert.fail("Exception in create in Apphelper addQuestions in class AssignmentCreate", e);
        }
    }

    //create assignment at topic level instructor value false
    public void createassignmentontopiclevel(int dataIndex, int topic, int chapter) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String course = Config.course;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            new DirectLogin().CMSLogin();
            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                List<WebElement> allchapter = (List<WebElement>) ((JavascriptExecutor) driver).executeScript("return arguments[0]", driver.findElements(By.cssSelector("div[class='expand-chapter-tree expand']")));
                int index1 = 0;
                for (WebElement elements : allchapter) {

                    if (index1 == chapter) {
                        elements.click();
                        break;
                    }
                    index1++;
                }

                List<WebElement> alltopic = (List<WebElement>) ((JavascriptExecutor) driver).executeScript("return arguments[0]", driver.findElements(By.cssSelector("div[class='course-topic-label node']")));
                int index = 0;
                for (WebElement element : alltopic) {

                    if (index == topic) {
                        element.click();
                        break;
                    }
                    index++;
                }
                new UIElement().waitAndFindElement(By.cssSelector("div.create-practice"));
                driver.findElement(By.cssSelector("div.create-practice")).click();
                driver.findElement(By.id("assessmentName")).click();
                driver.findElement(By.id("assessmentName")).clear();
                driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
                driver.findElement(By.id("questionSetName")).clear();
                driver.findElement(By.id("questionSetName")).sendKeys(questionset);

                driver.findElement(By.id("qtn-type-true-false-img")).click();


                driver.findElement(By.id("question-raw-content")).click();


                driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                driver.switchTo().defaultContent();

                Actions action = new Actions(driver);
                WebElement we = driver.findElement(By.id("choice1"));
                action.moveToElement(we).build().perform();
                driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
                new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
                new ComboBox().selectValue(3, questiontype);
                int popup = driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
                if (popup == 0) {
                    if (difficultylevel != null) {
                        new ComboBox().selectValue(7, difficultylevel);
                    }
                    if (learningobjective != null) {
                        driver.findElement(By.id("learing-objectives-span")).click();
                        driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div/div[1]/div[3]/div/div/div[4]/div/div[3]/div[1]/div/div[1]/label")).click();
                        driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
                        driver.findElement(By.cssSelector("span.cancel-collection")).click();

                    }
                    driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
                    new ComboBox().selectValue(4, "Publish");

                    driver.findElement(By.id("saveQuestionDetails1")).click();
                }//if condition for popup ends here
                driver.quit();
                new ReInitDriver().startDriver("firefox");

            }
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper createassignmentontopiclevel in class AssignmentCreate", e);
        }

    }

    //create assignment at subtopic level instructor value false
    public void createresourcesatsubtopiclevel(int dataIndex, int topictoexpand, int chapter, int subtopic) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String course = Config.course;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            new DirectLogin().CMSLogin();
            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                List<WebElement> allchapter = (List<WebElement>) ((JavascriptExecutor) driver).executeScript("return arguments[0]", driver.findElements(By.cssSelector("div[class='expand-chapter-tree expand']")));
                int index1 = 0;
                for (WebElement elements : allchapter) {
                    if (index1 == chapter) {
                        elements.click();
                        break;
                    }
                    index1++;
                }
                List<WebElement> alltopic = (List<WebElement>) ((JavascriptExecutor) driver).executeScript("return arguments[0]", driver.findElements(By.cssSelector("div[class='expand-topic-tree expand']")));
                int index = 0;
                for (WebElement element : alltopic) {

                    if (index == topictoexpand) {
                        element.click();
                        break;
                    }
                    index++;
                }
                List<WebElement> allsubtopictopic = (List<WebElement>) ((JavascriptExecutor) driver).executeScript("return arguments[0]", driver.findElements(By.cssSelector("div[class='course-subtopic-label node']")));
                int index2 = 0;
                for (WebElement element1 : allsubtopictopic) {

                    if (index2 == subtopic) {
                        element1.click();
                        break;
                    }
                    index2++;
                }
                new UIElement().waitAndFindElement(By.cssSelector("div.create-practice"));
                driver.findElement(By.cssSelector("div.create-practice")).click();
                driver.findElement(By.id("assessmentName")).click();
                driver.findElement(By.id("assessmentName")).clear();
                driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
                driver.findElement(By.id("questionSetName")).clear();
                driver.findElement(By.id("questionSetName")).sendKeys(questionset);

                driver.findElement(By.id("qtn-type-true-false-img")).click();


                driver.findElement(By.id("question-raw-content")).click();


                driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                driver.switchTo().defaultContent();

                Actions action = new Actions(driver);
                WebElement we = driver.findElement(By.id("choice1"));
                action.moveToElement(we).build().perform();
                driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
                new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
                new ComboBox().selectValue(3, questiontype);
                int popup = driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
                if (popup == 0) {
                    if (difficultylevel != null) {
                        new ComboBox().selectValue(7, difficultylevel);
                    }
                    if (learningobjective != null) {
                        driver.findElement(By.id("learing-objectives-span")).click();
                        driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div/div[1]/div[3]/div/div/div[4]/div/div[3]/div[1]/div/div[1]/label")).click();
                        driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
                        driver.findElement(By.cssSelector("span.cancel-collection")).click();

                    }
                    driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
                    new ComboBox().selectValue(4, "Publish");

                    driver.findElement(By.id("saveQuestionDetails1")).click();
                }//if condition for popup ends here
                driver.quit();
                new ReInitDriver().startDriver("firefox");

            }
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper createresourcesatsubtopiclevel in class AssignmentCreate", e);
        }

    }


    public void addPassagetypequestion(int dataIndex, String passageType, String assignmentname, String questionType) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String optiontext = ReadTestData.readDataByTagName("", "optiontext", Integer.toString(dataIndex));
            String answertext = ReadTestData.readDataByTagName("", "answertext", Integer.toString(dataIndex));
            String numerictext = ReadTestData.readDataByTagName("", "numerictext", Integer.toString(dataIndex));
            String unitoption = ReadTestData.readDataByTagName("", "unitoption", Integer.toString(dataIndex));
            String tolrence = ReadTestData.readDataByTagName("", "tolrence", Integer.toString(dataIndex));
            String maplenumeric = ReadTestData.readDataByTagName("", "maplenumeric", Integer.toString(dataIndex));
            String setname = ReadTestData.readDataByTagName("", "setname", Integer.toString(dataIndex));
            String passage = ReadTestData.readDataByTagName("", "passage", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String course = Config.course;
            new DirectLogin().CMSLogin();
            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                } else {
                    List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                    for (WebElement chapters : allChapters) {
                        if (chapters.getText().contains(chapterName)) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters);
                            break;
                        }

                    }

                }
                List<WebElement> elements = driver.findElements(By.className("collection-assessment-name"));
                for (WebElement content : elements) {
                    if (content.getText().trim().equals(assignmentname)) {

                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", content);
                        break;
                    }
                }

                driver.findElement(By.id("questionOptions")).click();
                new UIElement().waitAndFindElement(By.id("addQuestion"));
                driver.findElement(By.id("addQuestion")).click();
                new UIElement().waitAndFindElement(By.id(passageType));
                driver.findElement(By.id(passageType)).click();


                if (passageType.equals("qtn-passage-based-img")) {
                    //Adding Question set name
                    new UIElement().waitAndFindElement(By.id("edit-question-set-name"));
                    driver.findElement(By.id("edit-question-set-name")).click();
                    driver.findElement(By.id("edit-question-set-name")).sendKeys(setname);
                    driver.findElement(By.id("assessment-edit-save-btn")).click();
                    new UIElement().waitAndFindElement(By.id("passage-directions-content"));
                    // Adding Passage title
                    driver.findElement(By.id("passage-directions-content")).click();
                    driver.switchTo().frame("iframe-question-passage-direction").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    driver.switchTo().defaultContent();
                    // Adding a passage
                    driver.findElement(By.id("passage-raw-content")).click();
                    driver.switchTo().frame("iframe-question-edit-passage-text").findElement(By.xpath("/html/body")).sendKeys(passage);
                    driver.switchTo().defaultContent();
                    driver.findElement(By.id("question-editor-outer-wrapper")).click();
                    new UIElement().waitAndFindElement(By.id(questionType));
                    // Adding different types of questions

                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id(questionType)));
                    //		driver.findElement(By.id(questionType)).click();
                    if (questionType.equals("qtn-type-true-false-img")) //1.For true-false type question
                    {
                        driver.findElement(By.id("question-raw-content")).click();
                        driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        driver.switchTo().defaultContent();
                        //Setting choice 1 as correct answer
                        Actions action = new Actions(driver);
                        WebElement we = driver.findElement(By.id("choice1"));
                        action.moveToElement(we).build().perform();
                        driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
                    }
                    if (questionType.equals("qtn-multiple-choice-img")) //2. For multiple choice question.
                    {
                        driver.findElement(By.id("question-raw-content")).click();
                        driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        driver.switchTo().defaultContent();
                        //Adding choice 1
                        driver.findElement(By.id("choice1")).click();
                        driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys(answertext);
                        driver.switchTo().defaultContent();
                        //Adding choice 2
                        driver.findElement(By.id("choice2")).click();
                        driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys(answertext);
                        driver.switchTo().defaultContent();
                        //Adding choice 3
                        driver.findElement(By.id("choice3")).click();
                        driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys(answertext);
                        driver.switchTo().defaultContent();
                        //Adding choice 4
                        driver.findElement(By.id("choice4")).click();
                        driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys(answertext);
                        driver.switchTo().defaultContent();
                        //Setting choice 2 as correct answer
                        Actions action = new Actions(driver);
                        WebElement we = driver.findElement(By.id("choice2"));
                        action.moveToElement(we).build().perform();
                        driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
                    }
                    if (questionType.equals("qtn-multiple-selection-img"))  //3. For multiple sections
                    {
                        driver.findElement(By.id("question-raw-content")).click();
                        driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        driver.switchTo().defaultContent();
                        //Adding choice 1
                        driver.findElement(By.id("choice1")).click();
                        driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys(answertext);
                        driver.switchTo().defaultContent();
                        //Adding choice 2
                        driver.findElement(By.id("choice2")).click();
                        driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys(answertext);
                        driver.switchTo().defaultContent();
                        //Adding choice 3
                        driver.findElement(By.id("choice3")).click();
                        driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys(answertext);
                        driver.switchTo().defaultContent();
                        //Adding choice 4
                        driver.findElement(By.id("choice4")).click();
                        driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys(answertext);
                        driver.switchTo().defaultContent();
                        //Setting choice 2 as correct answer
                        Actions action1 = new Actions(driver);
                        WebElement we1 = driver.findElement(By.id("choice2"));
                        action1.moveToElement(we1).build().perform();
                        driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
                        //Setting choice 4 as correct answer
                        Actions action2 = new Actions(driver);
                        WebElement we2 = driver.findElement(By.id("choice4"));
                        action2.moveToElement(we2).build().perform();
                        driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
                    }
                    if (questionType.equals("qtn-text-entry-img")) // 4.For Text Entry type.
                    {
                        driver.findElement(By.id("question-raw-content")).click();
                        driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        driver.switchTo().defaultContent();
                        // Adding correct answer choice
                        driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
                        driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
                        driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(answertext);
                        new UIElement().waitAndFindElement(By.id("right-alt-container-1"));
                        // Rest two boxes are filled automatically.
                        //Adding alternate answer choice.
                        driver.findElement(By.id("right-alt-container-1")).click();
                        driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
                        driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
                        driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);

                        driver.findElement(By.id("done-button")).click(); // Accept answer

                    }
                    if (questionType.equals("qtn-text-entry-drop-down-img")) //5. For text entry drop down
                    {
                        driver.findElement(By.id("question-raw-content")).click();
                        driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        driver.switchTo().defaultContent();
                        // Adding Entry 1
                        driver.findElement(By.id("entry-0")).click();
                        driver.findElement(By.id("unit-name-edit-entry-0")).clear();
                        driver.findElement(By.id("unit-name-edit-entry-0")).sendKeys(optiontext);
                        new UIElement().waitAndFindElement(By.id("entry-1"));
                        // Accepting answer
                        WebElement menuitem = driver.findElement(By.id("entry-1"));
                        Locatable hoverItem = (Locatable) menuitem;
                        Mouse mouse = ((HasInputDevices) driver).getMouse();
                        mouse.mouseMove(hoverItem.getCoordinates());
                        List<WebElement> selectanswerticks = driver.findElements(By.className("mark-selected"));
                        selectanswerticks.get(1).click(); //select second option as correct answer

                        // Adding Entry 2
                        Actions action = new Actions(driver);
                        action.doubleClick(driver.findElement(By.id("entry-1")));
                        action.perform();
                        driver.findElement(By.id("unit-name-edit-entry-1")).clear();
                        driver.findElement(By.id("unit-name-edit-entry-1")).sendKeys(optiontext);
                        new UIElement().waitAndFindElement(By.id("entry-2"));

                        // Adding Entry 3
                        action = new Actions(driver);
                        action.doubleClick(driver.findElement(By.id("entry-2")));
                        action.perform();
                        new UIElement().waitAndFindElement(By.id("unit-name-edit-entry-2"));
                        driver.findElement(By.id("unit-name-edit-entry-2")).clear();
                        driver.findElement(By.id("unit-name-edit-entry-2")).sendKeys(optiontext);
                        new UIElement().waitAndFindElement(By.id("add-new-entry"));
                        //clicking on add more entry
                        driver.findElement(By.id("add-new-entry")).click();
                        //Adding entry 4
                        action.doubleClick(driver.findElement(By.id("entry-3")));
                        driver.findElement(By.id("unit-name-edit-entry-3")).clear();
                        driver.findElement(By.id("unit-name-edit-entry-3")).sendKeys(optiontext);
                        // Accepting answer
                        driver.findElement(By.id("done-button")).click();
                    }

                    if (questionType.equals("qtn-text-entry-numeric-img")) // 6. For numeric text entry
                    {
                        driver.findElement(By.id("question-raw-content")).click();
                        driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        driver.switchTo().defaultContent();
                        // Adding correct answer choice
                        driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
                        driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
                        driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(numerictext);
                        driver.switchTo().defaultContent();
                        new UIElement().waitAndFindElement(By.id("right-alt-container-1"));
                        // Rest two boxes are filled automatically.
                        //Adding alternate answer choice.
                        driver.findElement(By.id("right-alt-container-1")).click();
                        driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
                        driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
                        driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
                        // Adding tolerance
                        driver.findElement(By.id("tolerance-ans-text")).click();
                        driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
                        new UIElement().waitAndFindElement(By.id("done-button"));
                        driver.findElement(By.id("done-button")).click(); // Accept answer

                    }
                    if (questionType.equals("qtn-text-entry-numeric-units-img")) //7. Adding text entry numeric with units.
                    {
                        driver.findElement(By.id("question-raw-content")).click();
                        driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        driver.switchTo().defaultContent();
                        // Adding correct answer choice
                        driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
                        driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
                        driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(numerictext);

                        new UIElement().waitAndFindElement(By.id("add-more-entry"));
                        // Rest two boxes are filled automatically.
                        driver.findElement(By.id("add-more-entry")).click();
                        new UIElement().waitAndFindElement(By.tagName("li"));
                        // Selecting particular unit
                        List<WebElement> unitvalues = driver.findElements(By.tagName("li"));
                        for (WebElement units : unitvalues) {
                            //System.out.println(units.getText());
                            if (units.getText().equals(unitoption)) {
                                units.click();
                                break;
                            }
                        }
                        driver.findElement(By.id("done-button")).click(); // Accept answer

                    }

                    if (questionType.equals("qtn-numeric-maple-img")) //8. Adding maple numeric question
                    {
                        driver.findElement(By.id("question-raw-content")).click();
                        driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        driver.switchTo().defaultContent();
                        // Adding correct answer choice
                        driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
                        driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
                        driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(maplenumeric);

                        new UIElement().waitAndFindElement(By.id("right-alt-container-1"));
                        // Rest two boxes are filled automatically.
                        //Adding alternate answer choice.
                        driver.findElement(By.id("right-alt-container-1")).click();
                        driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
                        driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
                        driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
                        // Adding tolerance
                        driver.findElement(By.id("tolerance-ans-text")).click();
                        driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
                        new UIElement().waitAndFindElement(By.id("done-button"));
                        // Accept answer
                        driver.findElement(By.id("done-button")).click();
                    }

                    if (questionType.equals("qtn-math-symbolic-notation-img")) // 9. Adding maple symbolic question
                    {
                        driver.findElement(By.id("question-raw-content")).click();
                        driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        driver.switchTo().defaultContent();
                        new UIElement().waitAndFindElement(By.cssSelector("div[class='right-container math-correct-answer-input-container']"));
                        // Adding Correct answer
                        driver.findElement(By.cssSelector("div[class='right-container math-correct-answer-input-container']")).click();
                        new UIElement().waitAndFindElement(By.cssSelector("button[title='Square root']"));
                        driver.findElement(By.cssSelector("button[title='Square root']")).click();
                        driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(numerictext);
                        driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();

                        // Adding Alternate answer
                        driver.findElement(By.id("right-alt-container-1")).click();
                        List<WebElement> allAnswer = driver.findElements(By.className("display-correct-answer-math-editor-wrapper"));
                        allAnswer.get(1).click();
                        new UIElement().waitAndFindElement(By.cssSelector("button[title='Square root']"));
                        driver.findElement(By.cssSelector("button[title='Square root']")).click();
                        driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(numerictext);
                        driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
                        // Accept answer
                        driver.findElement(By.id("done-button")).click();

                    }

                    //((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.cssSelector("div[id='qtn-essay-type']")));

                    if (questionType.equals("qtn-essay-type")) // 10. Adding Essay type question
                    {

                        driver.findElement(By.id("question-raw-content")).click();
                        driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        driver.switchTo().defaultContent();
                        //Adding height for text entry.
                        driver.findElement(By.id("essay-question-height")).click();
                        driver.findElement(By.id("essay-question-height")).sendKeys(numerictext);

                    }

                }
                if (useWriteBoard != null) {
                    new UIElement().waitAndFindElement(By.id("display-write-board-checkbox"));
                    driver.findElement(By.id("display-write-board-checkbox")).click();//check the use writeboard check box

                }

                new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
                new ComboBox().selectValue(3, "Publish");
                driver.findElement(By.id("saveQuestionDetails1")).click();
                driver.quit();
                new ReInitDriver().startDriver("firefox");


            }
        } catch (Exception e) {

            Assert.fail("Exception in create in Apphelper addQuestions in class AssignmentCreate", e);
        }

    }


    public void assignThisFormFillOtherDetails(String dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            new TextSend().textsendbyid("Description", "additional-notes");
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String totalPoints = ReadTestData.readDataByTagName("", "totalPoints", dataIndex);
            String gradable = ReadTestData.readDataByTagName("", "gradable", dataIndex);

            if(gradable!=null){
                if(gradable.equals("false")){
                    try{
                        WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")));
                    }catch(Exception e){
                        WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-dw-gradable-label-check selected']")));

                    }
                }else{
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-dw-gradable-label-check selected']")));
                }
            }
            if (driver.findElements(By.id("total-points")).size() != 0) {
                if(driver.findElement(By.id("total-points")).isDisplayed()){
                    driver.findElement(By.id("total-points")).sendKeys(totalPoints);
                }
            }

            driver.findElement(By.id("due-date")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            driver.findElement(By.linkText(duedate)).click();
            new UIElement().waitAndFindElement(By.id("due-time"));
            driver.findElement(By.id("due-time")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[text()='07:30 PM']")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[starts-with(normalize-space(@class),'btn sty-green submit-assign')]")));
            new UIElement().waitTillInvisibleElement(By.xpath("//span[starts-with(normalize-space(@class),'btn sty-green submit-assign')]"));

        } catch (Exception e) {
            Assert.fail("Exception in method 'fillOtherDetailsInAssignThisForm' in class 'Assignment'", e);
        }
    }

    public void assignToStudent(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String gradeBookWeighting = ReadTestData.readDataByTagName("", "gradeBookWeighting", Integer.toString(dataIndex));
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
            String policyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(dataIndex));
            String policyDescription = ReadTestData.readDataByTagName("", "policyDescription", Integer.toString(dataIndex));
            String scorePerQuestion = ReadTestData.readDataByTagName("", "scorePerQuestion", Integer.toString(dataIndex));
            String ordering = ReadTestData.readDataByTagName("", "ordering", Integer.toString(dataIndex));
            String immediateFeedBack = ReadTestData.readDataByTagName("", "immediateFeedBack", Integer.toString(dataIndex));
            String numberOfAttempt = ReadTestData.readDataByTagName("", "numberOfAttempt", Integer.toString(dataIndex));
            String showAnswerAtAttemptNumber = ReadTestData.readDataByTagName("", "showAnswerAtAttemptNumber", Integer.toString(dataIndex));
            String gradeReleaseOption = ReadTestData.readDataByTagName("", "gradeReleaseOption", Integer.toString(dataIndex));
            String showHintsAtAttemptNumber = ReadTestData.readDataByTagName("", "showHintsAtAttemptNumber", Integer.toString(dataIndex));
            String showReadingContentLinkAtAttemptNumber = ReadTestData.readDataByTagName("", "showReadingContentLinkAtAttemptNumber", Integer.toString(dataIndex));
            String showSolutionAtAttemptNumber = ReadTestData.readDataByTagName("", "showSolutionAtAttemptNumber", Integer.toString(dataIndex));


            System.out.println("policyName:" + policyName);
            if (policyName == null) {
                System.out.println("Inside Policy Name");
                policyName = "ByDefault Policy Name";
            }

            if (policyDescription == null) {
                policyDescription = "Policy Description text";
            }
            if (scorePerQuestion == null) {
                scorePerQuestion = "1";
            }

            if (immediateFeedBack == null) {
                immediateFeedBack = "false";
            }


            if (numberOfAttempt == null) {
                numberOfAttempt = "1";
            }
            if (showAnswerAtAttemptNumber == null) {
                showAnswerAtAttemptNumber = "";
            }
            if (gradeReleaseOption == null) {
                gradeReleaseOption = "Release explicitly by the instructor";
            }
            if (showHintsAtAttemptNumber == null) {
                showHintsAtAttemptNumber = "";
            }
            if (showReadingContentLinkAtAttemptNumber == null) {
                showReadingContentLinkAtAttemptNumber = "";
            }
            if (showSolutionAtAttemptNumber == null) {
                showSolutionAtAttemptNumber = "";
            }


            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new Navigator().NavigateTo("Assignments");

            driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();
            new UIElement().waitAndFindElement(By.cssSelector("div[class='tab active']"));
            if (driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources")) {//Opening All Resources tab if not opened after clicking on New Assignment button

                driver.findElement(By.cssSelector("span[title='All Resources']")).click();
            }
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getUsePreCreatedAssignment_button());//click on Use Pre-created Assignment on Create New assignment pop-up
            new UIElement().waitAndFindElement(By.id("all-resource-search-textarea"));
            //Adding assignment to search
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='assign-this']"));
            Thread.sleep(5000);
            List<WebElement> assignThisElements = driver.findElements(By.cssSelector("span[class='assign-this']"));
            for (int a=0;a<assignThisElements.size();a++){
                if(assignThisElements.get(a).isDisplayed()){
                    assignThisElements.get(a).click();
                    break;
                }
            }

            new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, true);
            new UIElement().waitAndFindElement(By.id("due-time"));

            driver.findElement(By.id("due-time")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[text()='07:30 PM']")));

            driver.findElement(By.id("due-date")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.linkText(duedate)));

            if (gradeable.equals("false")) {
                new UIElement().waitAndFindElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")));
            }
            if (gradeable.equals("true") && assignmentpolicy != null) {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            }
            if (gradeable.equals("true")) {
                if (policyName.equals("ByDefault Policy Name")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                    new Click().clickbyxpath("//a[@rel='addNewAssignmentPolicy']");
                    new AssignmentPolicy().createAssignmentPolicyWhileAssigning(policyName, policyDescription, scorePerQuestion, ordering, Boolean.parseBoolean(immediateFeedBack), numberOfAttempt, showAnswerAtAttemptNumber, gradeReleaseOption, showHintsAtAttemptNumber, showReadingContentLinkAtAttemptNumber, showSolutionAtAttemptNumber);
                }

            }

            if (accessibleafter != null) {
                driver.findElement(By.id("accessible-date")).click();
                driver.findElement(By.xpath("//a[@title='Next']")).click();
                driver.findElement(By.linkText(accessibleafter)).click();
            }

            new UIElement().waitAndFindElement(By.id("additional-notes"));
            driver.findElement(By.id("additional-notes")).clear();
            driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);

            if (gradeBookWeighting != null) {
                if (gradeBookWeighting.equals("true")) {
                    new Click().clickbylinkText("Uncategorized");
                    new UIElement().waitAndFindElement(By.linkText("No Assignment Category"));
                    new Click().clickbylinkText("Practice");
                }
            }

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")));
            new UIElement().waitTillInvisibleElement(By.cssSelector("span[class='btn sty-green submit-assign']"));

        } catch (Exception e) {
            Assert.fail("Exception in  assignToStudent in AppHelper class Assignment", e);
        }
    }

    public void assignOrionAdaptivePracticeToStudent(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String gradeBookWeighting = ReadTestData.readDataByTagName("", "gradeBookWeighting", Integer.toString(dataIndex));
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
            String policyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(dataIndex));
            String policyDescription = ReadTestData.readDataByTagName("", "policyDescription", Integer.toString(dataIndex));
            String scorePerQuestion = ReadTestData.readDataByTagName("", "scorePerQuestion", Integer.toString(dataIndex));
            String ordering = ReadTestData.readDataByTagName("", "ordering", Integer.toString(dataIndex));
            String immediateFeedBack = ReadTestData.readDataByTagName("", "immediateFeedBack", Integer.toString(dataIndex));
            String numberOfAttempt = ReadTestData.readDataByTagName("", "numberOfAttempt", Integer.toString(dataIndex));
            String showAnswerAtAttemptNumber = ReadTestData.readDataByTagName("", "showAnswerAtAttemptNumber", Integer.toString(dataIndex));
            String gradeReleaseOption = ReadTestData.readDataByTagName("", "gradeReleaseOption", Integer.toString(dataIndex));
            String showHintsAtAttemptNumber = ReadTestData.readDataByTagName("", "showHintsAtAttemptNumber", Integer.toString(dataIndex));
            String showReadingContentLinkAtAttemptNumber = ReadTestData.readDataByTagName("", "showReadingContentLinkAtAttemptNumber", Integer.toString(dataIndex));
            String showSolutionAtAttemptNumber = ReadTestData.readDataByTagName("", "showSolutionAtAttemptNumber", Integer.toString(dataIndex));

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, true);


            new UIElement().waitAndFindElement(By.id("due-time"));

            driver.findElement(By.id("due-time")).click();
            driver.findElement(By.xpath("//li[text()='07:30 PM']")).click();
            Thread.sleep(9000);
            driver.findElement(By.id("due-date")).click();

            Thread.sleep(9000);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")));
            Thread.sleep(9000);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.linkText(duedate)));
            Thread.sleep(2000);
            if (gradeable.equals("false")) {
                new UIElement().waitAndFindElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")));
            }
            if (gradeable.equals("true") && assignmentpolicy != null) {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            }

            if (accessibleafter != null) {
                driver.findElement(By.id("accessible-date")).click();
                driver.findElement(By.xpath("//a[@title='Next']")).click();
                driver.findElement(By.linkText(accessibleafter)).click();
            }

            if (gradeBookWeighting != null) {
                if (gradeBookWeighting.equals("true")) {
                    new Click().clickbylinkText("Uncategorized");
                    new UIElement().waitAndFindElement(By.linkText("No Assignment Category"));
                    new Click().clickbylinkText("Practice");
                }
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[starts-with(@class,'btn sty-green submit-assign')]")));
            Thread.sleep(1000);
        } catch (Exception e) {
            Assert.fail("Exception in  assignToStudent in AppHelper class Assignment", e);
        }
    }

    public void assignAssignmentWithDueDate(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {

            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String gradeBookWeighting = ReadTestData.readDataByTagName("", "gradeBookWeighting", Integer.toString(dataIndex));
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
            String policyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(dataIndex));
            String policyDescription = ReadTestData.readDataByTagName("", "policyDescription", Integer.toString(dataIndex));
            String scorePerQuestion = ReadTestData.readDataByTagName("", "scorePerQuestion", Integer.toString(dataIndex));
            String ordering = ReadTestData.readDataByTagName("", "ordering", Integer.toString(dataIndex));
            String immediateFeedBack = ReadTestData.readDataByTagName("", "immediateFeedBack", Integer.toString(dataIndex));
            String numberOfAttempt = ReadTestData.readDataByTagName("", "numberOfAttempt", Integer.toString(dataIndex));
            String showAnswerAtAttemptNumber = ReadTestData.readDataByTagName("", "showAnswerAtAttemptNumber", Integer.toString(dataIndex));
            String gradeReleaseOption = ReadTestData.readDataByTagName("", "gradeReleaseOption", Integer.toString(dataIndex));
            String showHintsAtAttemptNumber = ReadTestData.readDataByTagName("", "showHintsAtAttemptNumber", Integer.toString(dataIndex));
            String showReadingContentLinkAtAttemptNumber = ReadTestData.readDataByTagName("", "showReadingContentLinkAtAttemptNumber", Integer.toString(dataIndex));
            String showSolutionAtAttemptNumber = ReadTestData.readDataByTagName("", "showSolutionAtAttemptNumber", Integer.toString(dataIndex));


            System.out.println("policyName:" + policyName);
            if (policyName == null) {
                System.out.println("Inside Policy Name");
                policyName = "ByDefault Policy Name";
            }

            if (policyDescription == null) {
                policyDescription = "Policy Description text";
            }
            if (scorePerQuestion == null) {
                scorePerQuestion = "1";
            }

            if (immediateFeedBack == null) {
                immediateFeedBack = "false";
            }

            if (numberOfAttempt == null) {
                numberOfAttempt = "1";
            }
            if (showAnswerAtAttemptNumber == null) {
                showAnswerAtAttemptNumber = "";
            }
            if (gradeReleaseOption == null) {
                gradeReleaseOption = "Release explicitly by the instructor";
            }
            if (showHintsAtAttemptNumber == null) {
                showHintsAtAttemptNumber = "";
            }
            if (showReadingContentLinkAtAttemptNumber == null) {
                showReadingContentLinkAtAttemptNumber = "";
            }
            if (showSolutionAtAttemptNumber == null) {
                showSolutionAtAttemptNumber = "";
            }


            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String url = driver.getCurrentUrl();
            if (!url.contains("eTextBook")) {
                new Navigator().NavigateTo("Assignments");
                driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();
                new UIElement().waitAndFindElement(By.cssSelector("div[class='tab active']"));
                if (driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources")) {//Opening All Resources tab if not opened after clicking on New Assignment button

                    driver.findElement(By.cssSelector("span[title='All Resources']")).click();
                }
                currentAssignments.getUsePreCreatedAssignment_button().click();//click on Use Pre-created Assignment on Create New assignment pop-up
                new UIElement().waitAndFindElement(By.id("all-resource-search-textarea"));
                //Adding assignment to search
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();


                new UIElement().waitAndFindElement(By.cssSelector("span[class='assign-this']"));
                Thread.sleep(9000);
                List<WebElement> eleList = driver.findElements(By.cssSelector("span[class='assign-this']"));
                for(int a=0;a<eleList.size();a++){
                    if(eleList.get(a).isDisplayed()){
                        eleList.get(a).click();
                        break;
                    }
                }
                //driver.findElement(By.cssSelector("span[class='assign-this']")).click();
            }
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, true);


            new UIElement().waitAndFindElement(By.id("due-time"));
            DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            Calendar now = Calendar.getInstance();
            //add minutes to current date using Calendar.add method
            now.add(Calendar.MINUTE, Integer.parseInt(duetime));
            String calenderFormat = dateFormat.format(now.getTime());
            System.out.println("calenderFormat:" + calenderFormat);

            driver.findElement(By.id("due-time")).click();//click on dur time
            driver.findElement(By.id("due-time")).sendKeys(calenderFormat);//click on dur time

            driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(5000);
            List<WebElement> defaultsDate = driver.findElements(By.cssSelector("a[class='ui-state-default ui-state-highlight ui-state-hover']"));
            for (WebElement defaultdate : defaultsDate) {
                if (defaultdate.isDisplayed()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", defaultdate);
                    break;
                }
            }

            if (gradeable.equals("false")) {
                new UIElement().waitAndFindElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")));
            }

            if (gradeable.equals("true") && assignmentpolicy != null) {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            }
            if (gradeable.equals("true")) {
                if (policyName.equals("ByDefault Policy Name")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                    new Click().clickbyxpath("//a[@rel='addNewAssignmentPolicy']");
                    new AssignmentPolicy().createAssignmentPolicyWhileAssigning(policyName, policyDescription, scorePerQuestion, ordering, Boolean.parseBoolean(immediateFeedBack), numberOfAttempt, showAnswerAtAttemptNumber, gradeReleaseOption, showHintsAtAttemptNumber, showReadingContentLinkAtAttemptNumber, showSolutionAtAttemptNumber);
                }

            }

            if (accessibleafter != null) {
                driver.findElement(By.id("accessible-date")).click();
                driver.findElement(By.xpath("//a[@title='Next']")).click();
                driver.findElement(By.linkText(accessibleafter)).click();
            }

            new UIElement().waitAndFindElement(By.id("additional-notes"));
            driver.findElement(By.id("additional-notes")).clear();
            driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);

            if (gradeBookWeighting != null) {
                if (gradeBookWeighting.equals("true")) {
                    new Click().clickbylinkText("Uncategorized");
                    new Click().clickbylinkText("Practice");
                }
            }
            if (gradeBookWeighting != null && gradeBookWeighting.equals("No Assignment Category")) {
                new Click().clickbylinkText("Uncategorized");
                new Click().clickbylinkText("No Assignment Category");
            }


            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[contains(@class,'btn sty-green submit-assign')]")));
            new UIElement().waitTillInvisibleElement(By.xpath("//span[contains(@class,'btn sty-green submit-assign')]"));

        } catch (Exception e) {
            Assert.fail("Exception in apphelper method assignAssignmentWithDueDate in class AssignLesson", e);
        }
    }

    public void AssignAssessmentwithgradingPolicy(int dataIndex, boolean sharewithstudent) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
            String asignmentReference = ReadTestData.readDataByTagName("", "asignmentReference", Integer.toString(dataIndex));
            String policyname = ReadTestData.readDataByTagName("", "resourcesname", Integer.toString(dataIndex));
            String navigateTo = ReadTestData.readDataByTagName("", "navigateTo", Integer.toString(dataIndex));
            String policyDescription = ReadTestData.readDataByTagName("", "policyDescription", Integer.toString(dataIndex));
            String scorePerQuestion = ReadTestData.readDataByTagName("", "scorePerQuestion", Integer.toString(dataIndex));
            String ordering = ReadTestData.readDataByTagName("", "ordering", Integer.toString(dataIndex));
            String immediateFeedBack = ReadTestData.readDataByTagName("", "immediateFeedBack", Integer.toString(dataIndex));
            String numberOfAttempt = ReadTestData.readDataByTagName("", "numberOfAttempt", Integer.toString(dataIndex));
            String showAnswerAtAttemptNumber = ReadTestData.readDataByTagName("", "showAnswerAtAttemptNumber", Integer.toString(dataIndex));
            String gradeReleaseOption = ReadTestData.readDataByTagName("", "gradeReleaseOption", Integer.toString(dataIndex));
            String showHintsAtAttemptNumber = ReadTestData.readDataByTagName("", "showHintsAtAttemptNumber", Integer.toString(dataIndex));
            String showReadingContentLinkAtAttemptNumber = ReadTestData.readDataByTagName("", "showReadingContentLinkAtAttemptNumber", Integer.toString(dataIndex));
            String showSolutionAtAttemptNumber = ReadTestData.readDataByTagName("", "showSolutionAtAttemptNumber", Integer.toString(dataIndex));
            String policyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(dataIndex));


            System.out.println("policyName:" + policyName);
            if (policyName == null) {
                System.out.println("Inside Policy Name");
                policyName = "ByDefault Policy Name";
            }

            if (policyDescription == null) {
                policyDescription = "Policy Description text";
            }
            if (scorePerQuestion == null) {
                scorePerQuestion = "1";
            }

            if (immediateFeedBack == null) {
                immediateFeedBack = "false";
            }


            if (numberOfAttempt == null) {
                numberOfAttempt = "1";
            }
            if (showAnswerAtAttemptNumber == null) {
                showAnswerAtAttemptNumber = "";
            }
            if (gradeReleaseOption == null) {
                gradeReleaseOption = "Release explicitly by the instructor";
            }
            if (showHintsAtAttemptNumber == null) {
                showHintsAtAttemptNumber = "";
            }
            if (showReadingContentLinkAtAttemptNumber == null) {
                showReadingContentLinkAtAttemptNumber = "";
            }
            if (showSolutionAtAttemptNumber == null) {
                showSolutionAtAttemptNumber = "";
            }

            if (navigateTo != null) {
                if (navigateTo.equals("My Question Bank")) {
                    new Navigator().NavigateTo("My Question Bank");

                }
            } else {
                new Navigator().NavigateTo("Question Banks");
                //Adding assignment to search
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            }
            new UIElement().waitAndFindElement(By.className("assign-this"));
            driver.findElement(By.className("assign-this")).click();
            if (sharewithstudent == true) {
                new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, false);
            }
            new UIElement().waitAndFindElement(By.id("due-time"));
            driver.findElement(By.id("due-time")).click();
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals(duetime)) {
                    time.click();
                    break;
                }
            }
            driver.findElement(By.id("due-date")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            driver.findElement(By.linkText(duedate)).click();
            if (gradeable.equals("false")) {
                new UIElement().waitAndFindElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")));
            }
            if (gradeable.equals("true") && assignmentpolicy != null) {
                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("Select an Assignment Policy")));
                new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            }

            if (gradeable.equals("true")) {
                if (policyName.equals("ByDefault Policy Name")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                    new Click().clickbyxpath("//a[@rel='addNewAssignmentPolicy']");
                    new AssignmentPolicy().createAssignmentPolicyWhileAssigning(policyName, policyDescription, scorePerQuestion, ordering, Boolean.parseBoolean(immediateFeedBack), numberOfAttempt, showAnswerAtAttemptNumber, gradeReleaseOption, showHintsAtAttemptNumber, showReadingContentLinkAtAttemptNumber, showSolutionAtAttemptNumber);
                }

            }
            if (accessibleafter != null) {
                driver.findElement(By.id("accessible-date")).click();
                driver.findElement(By.linkText(accessibleafter)).click();
            }

            if (asignmentReference != null) {

                if (System.getProperty("UCHAR") == null) {
                    policyname = policyname + LoginUsingLTI.appendChar;
                } else {
                    policyname = policyname + System.getProperty("UCHAR");
                }
                driver.findElement(By.linkText("Select your Assignment Reference")).click();
                new UIElement().waitAndFindElement(By.cssSelector("a[title='" + policyname + "']"));
                new Click().clickBycssselector("a[title='" + policyname + "']");
            }
            new UIElement().waitAndFindElement(By.id("additional-notes"));
            driver.findElement(By.id("additional-notes")).clear();
            driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);
            driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();
            new UIElement().waitTillInvisibleElement(By.cssSelector("span[class='btn sty-green submit-assign']"));

        } catch (Exception e) {
            Assert.fail("Exception in  assignToStudent in AppHelper class Assignment", e);
        }
    }

    public void updateAssignment(int dataIndex, boolean addShareWith) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String accessibleAfter = ReadTestData.readDataByTagName("", "accessibleAfter", Integer.toString(dataIndex));

            new Navigator().NavigateTo("Current Assignments");
            //Selecting third and fourth filter values
            driver.findElement(By.linkText("All Assignment Status")).click();
            new UIElement().waitAndFindElement(By.linkText("Available for Students"));
            driver.findElement(By.linkText("Available for Students")).click();
            new UIElement().waitAndFindElement(By.linkText("All Activity"));
            driver.findElement(By.linkText("All Activity")).click();
            if (gradeable == null)
                driver.findElement(By.linkText("Question Practice")).click();
            else if (gradeable.equals("false"))
                driver.findElement(By.linkText("Question Practice")).click();
            else
                driver.findElement(By.linkText("Question Assignment")).click();

            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));

            List<WebElement> assignmentNames = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            int index = 0;
            for (int i = 0; i < assignmentNames.size(); i++) {
                if (assignmentNames.get(i).getText().substring(7).equals(assignmentname)) {
                    break;
                }
                index++;
            }
            driver.findElements(By.cssSelector("span[class='assign-more']")).get(index).click(); //click on Update Assignment in Assignments page only
            new UIElement().waitAndFindElement(By.className("assignment-update-reassign"));
            new Click().clickByclassname("assignment-update-reassign");//click on Reassign
            if (addShareWith == true)
                new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, false);

            if (duedate != null) {
                driver.findElement(By.id("due-date")).click();
                new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
                driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
                new UIElement().waitAndFindElement(By.linkText(duedate));
                driver.findElement(By.linkText(duedate)).click();


            }
            if (accessibleAfter != null) {
                driver.findElement(By.id("accessible-date")).click();
                new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
                driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
                new UIElement().waitAndFindElement(By.linkText(accessibleAfter));
                driver.findElement(By.linkText(accessibleAfter)).click();
            }
            new UIElement().waitAndFindElement(By.cssSelector("span[class='btn sty-green submit-assign tab-view']"));
            driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign tab-view']")).click();
            driver.findElement(By.cssSelector("span[class='confirm-submit-yes submit-assign']")).click();


        } catch (Exception e) {
            Assert.fail("Exception in app helper updateAssignment in class Assignment", e);
        }

    }

    public void submitAssignmentAsStudent(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String submitassignment = ReadTestData.readDataByTagName("", "submitassignment", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String usehint = ReadTestData.readDataByTagName("", "usehint", Integer.toString(dataIndex));
            String answerchoice = ReadTestData.readDataByTagName("", "answerchoice", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Course Stream");
            int helppage = driver.findElements(By.className("close-help-page")).size();
            if (helppage == 1)
                driver.findElement(By.className("close-help-page")).click();
            new Click().clickBycssselector("span[assignmentname='" + assessmentname + "']");//click on Assignment
            Thread.sleep(20000);
            boolean useHint = false;//by default useHint is set to false
            if (usehint != null)//read the tag from testdata
                useHint = true;//set useHint to true
            if (answerchoice == null)
                answerchoice = "correct";
            int timer = 1;
            while (timer != 0) {
                Thread.sleep(3000);
                String questionText = (new WebDriverWait(driver, 200)).until(ExpectedConditions.presenceOfElementLocated(By.id("question-edit"))).getText();
                if (questionText.contains("True False"))
                    new AttemptQuestion().trueFalse(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Multiple Choice"))
                    new AttemptQuestion().multipleChoice(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Multi Selection"))
                    new AttemptQuestion().multipleSelection(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Text Entry"))
                    new AttemptQuestion().textEntry(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Text Drop Down"))
                    new AttemptQuestion().textDropDown(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Numeric Entry With Units"))
                    new AttemptQuestion().numericEntryWithUnits(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Advanced Numeric"))
                    new AttemptQuestion().advancedNumeric(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Expression Evaluator"))
                    new AttemptQuestion().expressionEvaluator(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Essay"))
                    new AttemptQuestion().essay(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Write Board"))
                    new AttemptQuestion().writeBoard(useHint, answerchoice, dataIndex);

                boolean lastQuestionNotToSubmitAssignment = submitAnswer(submitassignment, dataIndex);
                if (lastQuestionNotToSubmitAssignment == true) {
                    timer = 0;
                    System.out.println("inside lastQuestionNotToSubmitAssignment  ");
                } else {
                    try {
                        driver.findElement(By.cssSelector("span[id='timer-label']"));
                    } catch (Exception e) {
                        timer = 0;
                    }
                }
            }
        } catch (Exception e) {
            Assert.fail("Exception in app helper submitAssignmentAsStudent in class Assignment", e);
        }
    }

    public void attemptingQuestionsAsStudent() {
        WebDriver driver = Driver.getWebDriver();
        try {
            int timer = 1;

            while (timer == 1) {
                timer = driver.findElements(By.id("assessmentTimer")).size();
                if (timer == 0)
                    break;
                int valueofoption = driver.findElements(By.className("qtn-label")).size();
                if (valueofoption >= 1)
                    driver.findElement(By.className("qtn-label")).click();

			/*	List<WebElement> textbox = driver.findElements(By.tagName("input"));
				forloop:
				for(WebElement box : textbox)
				{
					if(box.isDisplayed() == true)
					{
						box.sendKeys("abcd");
						break forloop;
					}
				}*/
                int submitbutton = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).size();
                if (submitbutton >= 1)
                    driver.findElement(By.linkText("Submit")).click(); //submitting the assignment as student
                else
                    driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).click();//click on next button

            }


        } catch (Exception e) {
            Assert.fail("Exception in app helper attemptingQuestionAsStudent in class Assignment", e);
        }
    }

    public void statusValidate(int dataIndex, String expectedStatus) {
        WebDriver driver = Driver.getWebDriver();
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String statuscolor = ReadTestData.readDataByTagName("", "statuscolor", Integer.toString(dataIndex));
        new Navigator().NavigateTo("Assignments");
        int index = 0;
        List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
        for (WebElement element : assignments) {
            if (element.getText().contains(assessmentname)) {
                break;
            }
            index++;
        }
        List<WebElement> status = driver.findElements(By.className("ls-assignment-status"));

        if (!status.get(index).getText().contains(expectedStatus))
            Assert.fail("Status of the asignment in Instructor Dashboard is " + status.get(index).getText() + " which is not equal to expected status: " + expectedStatus);
        if (statuscolor != null) {
            if (expectedStatus.contains("Status:  Grading in Progress")) {
                WebElement statuscolors = driver.findElement(By.cssSelector("span[title='Grading in Progress']"));
                if (!statuscolors.getCssValue("color").equals(statuscolor))
                    Assert.fail("Status color is " + statuscolors.getCssValue("color") + " which is not equal to expected color " + statuscolor);
            }
            if (expectedStatus.contains("Status:  Review in Progress")) {
                WebElement statuscolors = driver.findElement(By.cssSelector("span[title='Review in Progress']"));
                if (!statuscolors.getCssValue("color").equals(statuscolor))
                    Assert.fail("Status color is " + statuscolors.getCssValue("color") + " which is not equal to expected color " + statuscolor);
            }
            if (expectedStatus.contains("Status:  Graded") || expectedStatus.contains("Status:  Reviewed")) {
                WebElement statuscolors = driver.findElement(By.cssSelector("span[class='ls-assignment-status-grades-released']"));
                if (!statuscolors.getCssValue("color").equals(statuscolor))
                    Assert.fail("Status color is " + statuscolors.getCssValue("color") + " which is not equal to expected color " + statuscolor);
            }
        }
        //List<WebElement> firstblock =  driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-first not-started']"));
        //System.out.println(firstblock.get(index).getText());
    }


    public int statusBoxCount(int dataIndex, String boxName) {
        String[] cntarray = {""};
        WebDriver driver = Driver.getWebDriver();
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        new Navigator().NavigateTo("Assignments");
        int index = 0;
        List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
        for (WebElement element : assignments) {
            if (element.getText().contains(assessmentname)) {
                break;
            }
            index++;
        }
        if (boxName.equals("Not Started")) {
            List<WebElement> notstartedbox = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-first not-started']"));

            String cnt = notstartedbox.get(index).getText();
            cntarray = cnt.split("\\n");
        }
        if (boxName.equals("In Progress")) {
            List<WebElement> inprogress = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-second in-progress']"));

            String cnt = inprogress.get(index).getText();
            cntarray = cnt.split("\\n");
        }
        if (boxName.equals("Submitted")) {
            List<WebElement> submitted = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-third submitted']"));

            String cnt = submitted.get(index).getText();
            cntarray = cnt.split("\\n");
        }
        if (boxName.equals("Graded")) {
            List<WebElement> graded = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));

            String cnt = graded.get(index).getText();
            cntarray = cnt.split("\\n");
        }
        if (boxName.equals("Reviewed")) {
            List<WebElement> graded = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));

            String cnt = graded.get(index).getText();
            cntarray = cnt.split("\\n");
        }
        return Integer.parseInt(cntarray[0]);

    }

/*
    * @Author Mukesh
    * this method will verify the assignment status of instructor
    * */

    public void verifyClassAssignmentStatus(int dataIndex, String status) {

        WebDriver driver = Driver.getWebDriver();
        try {
            boolean found = false;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            int index = 0;
            List<WebElement> assignments = currentAssignments.getList_assignmentName();

            for (WebElement element : assignments) {
                System.out.println("assignments" + element.getText());
                if (element.getText().contains(assessmentName)) {
                    break;
                }
                index++;
            }
            System.out.println(index);
            List<WebElement> allStatus = currentAssignments.class_assignmentStatus;
            System.out.println("Status:" + status);
            System.out.println(allStatus.get(index).getAttribute("title"));
            if (allStatus.get(index).getAttribute("title").equals(status)) {
                found = true;
            }
            if (found == false)
                Assert.fail("Class status is not as per action performed by student");

        } catch (Exception e) {
            Assert.fail("Exception in method verifyClassAssignmentStatus of class Assignment", e);
        }


    }

      /*
    * @Author Mukesh
    * this method will verify the assignment status of student in assignment page
    * */

    public void statusBoxCheckInStudentAssignmentPage(int dataIndex, String count, String status) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    System.out.println(assessmentname);
                    break;
                }
                index++;
            }

            List<WebElement> allStatus = driver.findElements(By.xpath("//div[@class='ls-stduent-activity-cards-holder']//div[position()>2]"));
            if (!allStatus.get(index).getText().replaceAll("[\n\r]", "").equals(count + status)) {
                Assert.fail("Not Started box doesn't display the count of students who have not yet started the assignment");
            }


        } catch (Exception e) {
            Assert.fail("Exception in app helper statusBoxCheckInStudentAssignmentPage in class Assignment", e);
        }
    }


    public void checkStatusCountInStudentAssignmentPage(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String notStartedCount = ReadTestData.readDataByTagName("", "notstarted", Integer.toString(dataIndex));
            String inProgressCount = ReadTestData.readDataByTagName("", "inprogress", Integer.toString(dataIndex));
            String submittedCount = ReadTestData.readDataByTagName("", "submitted", Integer.toString(dataIndex));
            String reviewedCount = ReadTestData.readDataByTagName("", "reviewed", Integer.toString(dataIndex));
            String gradedCount = ReadTestData.readDataByTagName("", "graded", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    System.out.println(assessmentname);
                    break;
                }
                index++;
            }

            WebElement notstarted = driver.findElement(By.xpath("//li[@id='ls-not-started-assignment']"));

            WebElement inprogress = driver.findElement(By.xpath("//li[@id='ls-in-progress-assignment']"));

            WebElement submitted = driver.findElement(By.xpath("//li[@id='ls-submitted-assignment']"));

            WebElement reviewed = driver.findElement(By.xpath("//li[@id='ls-reviewed-assignment']"));

            WebElement graded = driver.findElement(By.xpath("//li[@id='ls-graded-assignment']"));

            if (!notstarted.getText().replaceAll("[\n\r]", "").contains(notStartedCount + "Not Started")) {
                Assert.fail("Not Started box doesn't display the count of students who have not yet started the assignment");
            } else {
                if (!inprogress.getText().replaceAll("[\n\r]", "").contains(inProgressCount + "In Progress")) {
                    Assert.fail("In Progress box doesnt display the count of students who are currently taking the assignment");
                } else {
                    if (!submitted.getText().replaceAll("[\n\r]", "").contains(submittedCount + "Submitted")) {
                        Assert.fail("Submitted box doesnt display the count of Students who have submitted the assignments");
                    } else {
                        if (!reviewed.getText().replaceAll("[\n\r]", "").contains(reviewedCount + "Reviewed")) {
                            Assert.fail("Reviewed box doesnt display the count of student for whom non gradable assessment has been reviewed");
                        } else {
                            if (!graded.getText().replaceAll("[\n\r]", "").contains(gradedCount + "Graded")) {
                                Assert.fail("Graded box doesn't display the count of student for whom assessment has been graded");
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            Assert.fail("Exception in app helper checkStautsCountInStudentAssignmentPage in class Assignment", e);
        }
    }


    public void statusBoxCheckInInstructorDashBoard(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String notStartedCount = ReadTestData.readDataByTagName("", "notstarted", Integer.toString(dataIndex));
            String inProgressCount = ReadTestData.readDataByTagName("", "inprogress", Integer.toString(dataIndex));
            String submittedCount = ReadTestData.readDataByTagName("", "submitted", Integer.toString(dataIndex));
            String reviewedCount = ReadTestData.readDataByTagName("", "reviewed", Integer.toString(dataIndex));
            String gradedCount = ReadTestData.readDataByTagName("", "graded", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }

            List<WebElement> notstarted = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-first not-started']"));

            List<WebElement> inprogress = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-second in-progress']"));

            List<WebElement> submitted = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-third submitted']"));

            List<WebElement> reviewed = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));

            List<WebElement> graded = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));

            if (!notstarted.get(index).getText().replaceAll("[\n\r]", "").contains(notStartedCount + "Not Started")) {
                Assert.fail("Not Started box doesn't display the count of students who have not yet started the assignment");
            }
            if (!inprogress.get(index).getText().replaceAll("[\n\r]", "").contains(inProgressCount + "In Progress")) {
                Assert.fail("In Progress box doesnt display the count of students who are currently taking the assignment");
            }
            if (!submitted.get(index).getText().replaceAll("[\n\r]", "").contains(submittedCount + "Submitted")) {
                Assert.fail("Submitted box doesnt display the count of Students who have submitted the assignments");
            }
            if (gradeable.equals("false")) {
                if (!reviewed.get(index).getText().replaceAll("[\n\r]", "").contains(reviewedCount + "Reviewed")) {
                    Assert.fail("Reviewed box doesnt display the count of student for whom non gradable assessment has been reviewed");
                }
            } else {
                if (!graded.get(index).getText().replaceAll("[\n\r]", "").contains(gradedCount + "Graded")) {
                    Assert.fail("Graded box doesn't display the count of student for whom assessment has been graded");
                }
            }

        } catch (Exception e) {
            Assert.fail("Exception in app helper statusBoxCheckInInstructorDashBoard in class Assignment", e);
        }
    }

    public void releaseGrades(int dataIndex, String releaseAction) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> viewresponseslink = driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
            //viewresponseslink.get(2).click();

            driver.findElement(By.cssSelector("div[title='" + releaseAction + "']")).click();
        } catch (Exception e) {
            Assert.fail("Exception in app helper releaseGrades in class Assignment", e);
        }
    }

    public void gradesValidation(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Assignments");
            new ComboBox().selectValue(3, "Graded");
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            int index = 0;
            List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> viewgradeslink = driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
            if (!viewgradeslink.get(index).isDisplayed())
                Assert.fail("View Grades link not displayed");
            if (!viewgradeslink.get(index).getText().equals("View Grades"))
                Assert.fail("View Grades link text is not as expected");

            List<WebElement> graphs = driver.findElements(By.className("ls-assignment-performance-graph")); //validating the grade graph by clicking it
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", graphs.get(index));

            List<WebElement> xaxis = driver.findElements(By.className("ls-assignment-performance-graph"));
            if (!xaxis.get(index).getText().contains("Number of Students"))
                Assert.fail("x-axis of grade graph not shown as 'Number of Students'");

            List<WebElement> yaxis = driver.findElements(By.className("ls-assignment-performance-graph"));
            if (!yaxis.get(index).getText().contains("Score Range"))
                Assert.fail("y-axis of grade graph not shown as 'Score Range'");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewgradeslink.get(index));
            new UIElement().waitAndFindElement(By.className("idb-gradebook-header-div"));
            if (!driver.findElement(By.className("idb-gradebook-header-div")).getText().equals("Assignment Responses"))
                Assert.fail("The Assignment Responses tab not opened after clicking on the view responses link in the assignments page");
            //viewresponseslink.get(2).click();


        } catch (Exception e) {
            Assert.fail("Exception in app helper gradesValidation in class Assignment", e);
        }
    }

    public void assignmentDetailsValidate(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String fullName = ReadTestData.readDataByTagName("", "fullName", Integer.toString(dataIndex));
            String y[] = fullName.split(" ");
            fullName = y[1] + ", " + y[0];//reverse the name with comma in between
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            List<String> stringarray = new ArrayList<String>();
            List<WebElement> allInstructorName = driver.findElements(By.className("ls-assignment-item-author-name"));
            for (WebElement name : allInstructorName)
                stringarray.add(name.getText());

            if (!stringarray.contains(fullName))
                Assert.fail("Instructor Name is not present above the assignemnt once it is assigned by the instructor");
            stringarray.clear();
            List<WebElement> allAssesmentName = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));

            for (WebElement name : allAssesmentName)
                stringarray.add(name.getText());
            String[] allNames = stringarray.toArray(new String[stringarray.size()]);
            boolean contains = false;
            for (int i = 0; i < allNames.length; i++) {
                String str = allNames[i];
                if (str.contains(assessmentName)) {
                    contains = true;
                    break;
                }
            }
            if (contains == false)
                Assert.fail("Assignment name is not present in Current Assignment page.");
            String message = driver.findElement(By.className("ls-assignment-post-label")).getText();
            if (!message.trim().equals("posted an assignment.")) {
                Assert.fail("Default message 'posted an assignment.' is not displayed in Current Assignment page.");
            }
            int bookmark = driver.findElements(By.cssSelector("span.ls-assignment-bookmark")).size();
            if (bookmark == 0) {
                Assert.fail("Bookmark icon is not present in Current Assignment page.");
            }
            //Check additional details as well

            if (driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']")).size() == 0)
                Assert.fail("'View Student Responses' link not present in current Assignmet Page");

            if (driver.findElements(By.cssSelector("span[class='assign-more']")).size() == 0)
                Assert.fail("'Update Assignment' link not present in current Assignmet Page");

            if (driver.findElements(By.cssSelector("span[class='delete-assigned-task']")).size() == 0)
                Assert.fail("'Un-assign Assignment' link not present in current Assignmet Page");

            if (driver.findElements(By.cssSelector("span[class='try-it']")).size() == 0)
                Assert.fail("'Try it' link not present in current Assignmet Page");

            if (driver.findElements(By.cssSelector("span[class='ls-post-like-link']")).size() == 0)
                Assert.fail("'Like' link not present in current Assignmet Page");

            if (driver.findElements(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']")).size() == 0)
                Assert.fail("'Comments' link not present in current Assignmet Page");

        } catch (Exception e) {
            Assert.fail("Exception in  assignmentValidate in AppHelper class Assignment", e);
        }
    }

    public void provideGRadeToStudent(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String studentname = ReadTestData.readDataByTagName("", "studentname", Integer.toString(dataIndex));
            String y[] = studentname.split(" ");
            studentname = y[1] + ", " + y[0];//reverse the name with comma in between
            System.out.println("Student Name : " + studentname);
            //String gradeLinkIndex = ReadTestData.readDataByTagName("", "gradeLinkIndex", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Assignments");

            int index = 0;
            List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> viewresponseslink = driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
            List<WebElement> menuitem = driver.findElements(By.cssSelector("span[class='idb-gradebook-assignment-username']"));
            for (WebElement user : menuitem) {
                if (user.getText().equals(studentname)) {
                    try {
                        new MouseHover().mouserhoverbywebelement(user);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("idb-grade-now-link")));
                    } catch (Exception e) {
                        new MouseHover().mouserhoverbywebelement(user);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("idb-grade-now-link")));
                    }
                    /*driver.findElement(By.id("idb-grade-now-link")).click();*/
                    new UIElement().waitAndFindElement(By.cssSelector("input[class='idb-grade-points']"));
                    driver.findElement(By.cssSelector("input[class='idb-grade-points']")).clear();
                    new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@class, 'idb-grade-points')]"));
                    driver.findElement(By.xpath("//*[starts-with(@class, 'idb-grade-points')]")).sendKeys("0.6");
                    new UIElement().waitAndFindElement(By.cssSelector("input[class='idb-grade-points']"));
                    driver.findElement(By.cssSelector("input[class='idb-grade-points']")).sendKeys(Keys.TAB);
                    new UIElement().waitAndFindElement(By.id("ls-assignment-not-started-count"));
                    driver.findElement(By.id("ls-assignment-not-started-count")).click(); //clicking on not started box to save the marks
                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in apphelper provideGRadeToStudent in class Assignment", e);
        }
    }


    public void provideGRadeToStudesdnt(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String studentname = ReadTestData.readDataByTagName("", "studentname", Integer.toString(dataIndex));
            String y[] = studentname.split(" ");
            studentname = y[1] + ", " + y[0];//reverse the name with comma in between
            System.out.println("Student Name : " + studentname);
            //String gradeLinkIndex = ReadTestData.readDataByTagName("", "gradeLinkIndex", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Assignments");

            int index = 0;
            List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> viewresponseslink = driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
            List<WebElement> menuitem = driver.findElements(By.cssSelector("span[class='idb-gradebook-assignment-username']"));
            int userCount = 0;
            for (WebElement user : menuitem) {
                if (user.getText().equals(studentname)) {
                    try {
                        userCount++;
                        new MouseHover().mouserhoverbywebelement(user);
                        Thread.sleep(2000);
                        //driver.findElements(By.id("idb-grade-now-link")).get(userCount-1).click();
                        //((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("idb-grade-now-link")));
                        List<WebElement> eleList = driver.findElements(By.id("idb-grade-now-link"));
                        for (int a = 0; a < eleList.size(); a++) {
                            if (eleList.get(a).isDisplayed())
                                eleList.get(a).click();
                        }
                    } catch (Exception e) {
                        new MouseHover().mouserhoverbywebelement(user);
                        Thread.sleep(2000);
                        //((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("idb-grade-now-link")));
                        //driver.findElements(By.id("idb-grade-now-link")).get(userCount-1).click();
                        List<WebElement> eleList = driver.findElements(By.id("idb-grade-now-link"));
                        for (int a = 0; a < eleList.size(); a++) {
                            if (eleList.get(a).isDisplayed())
                                eleList.get(a).click();
                        }
                    }
                    /*driver.findElement(By.id("idb-grade-now-link")).click();*/

                    Thread.sleep(2000);
                    List<WebElement> GradeEditBoxes = driver.findElements(By.cssSelector("input[class='idb-grade-points']"));
                    int count = 0;
                    for (int a = 0; a < GradeEditBoxes.size(); a++) {
                        /*GradeEditBoxes = driver.findElements(By.cssSelector("input[class='idb-grade-points']"));
                        driver.findElement(By.cssSelector("input[class='idb-grade-points']")).clear();
                        Thread.sleep(1000);
                        new UIElement().waitAndFindElement(By.xpath("/*//*[starts-with(@class, 'idb-grade-points')]"));
                        driver.findElement(By.xpath("/*//*[starts-with(@class, 'idb-grade-points')]")).sendKeys("0.6");
                        new UIElement().waitAndFindElement(By.cssSelector("input[class='idb-grade-points']"));
                        driver.findElement(By.cssSelector("input[class='idb-grade-points']")).sendKeys(Keys.TAB);*/

                        GradeEditBoxes.get(a).clear();
                        Thread.sleep(1000);
                        GradeEditBoxes.get(a).sendKeys("0.6");
                        Thread.sleep(1000);
                        System.out.println("A count : " + a);
                        count++;
                        /*if(count==7){
                            driver.findElement(By.xpath("//img[@src = '/webresources/images/al/gradebook-next.png']")).click();
                            Thread.sleep(2000);
                            GradeEditBoxes = driver.findElements(By.cssSelector("input[class='idb-grade-points']"));
                        }*/

                    }
                    System.out.println("COUNT : " + count);
                    if (count == 8) {
                        driver.findElement(By.xpath("//div[@class='idb-gradebook-content-header-next-arrow']//img")).click();
                        //((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='idb-gradebook-content-header-next-arrow']//img")));
                        System.out.println("q");
                        Thread.sleep(2000);
                        WebElement ele = driver.findElement(By.cssSelector("span[class='idb-gradebook-assignment-username']"));
                        new MouseHover().mouserhoverbywebelement(ele);
                        Thread.sleep(2000);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("idb-grade-now-link")));
                        GradeEditBoxes = driver.findElements(By.cssSelector("input[class='idb-grade-points']"));
                        for (int a = 0; a < GradeEditBoxes.size(); a++) {
                            GradeEditBoxes.get(a).clear();
                            Thread.sleep(1000);
                            GradeEditBoxes.get(a).sendKeys("0.6");
                            Thread.sleep(1000);
                        }
                    }

                    new UIElement().waitAndFindElement(By.id("ls-assignment-not-started-count"));
                    driver.findElement(By.id("ls-assignment-not-started-count")).click();
                    break;
                   /* new UIElement().waitAndFindElement(By.cssSelector("input[class='idb-grade-points']"));
                    driver.findElement(By.cssSelector("input[class='idb-grade-points']")).clear();
                    new UIElement().waitAndFindElement(By.xpath("/*//*[starts-with(@class, 'idb-grade-points')]"));
                    driver.findElement(By.xpath("/*//*[starts-with(@class, 'idb-grade-points')]")).sendKeys("0.6");
                    new UIElement().waitAndFindElement(By.cssSelector("input[class='idb-grade-points']"));
                    driver.findElement(By.cssSelector("input[class='idb-grade-points']")).sendKeys(Keys.TAB);
                    new UIElement().waitAndFindElement(By.id("ls-assignment-not-started-count"));
                    driver.findElement(By.id("ls-assignment-not-started-count")).click(); //clicking on not started box to save the marks
                */
                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in apphelper provideGRadeToStudent in class Assignment", e);
        }
    }

    public void likeAssignment(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> likelinks = driver.findElements(By.className("ls-post-like-link"));
            if (!likelinks.get(index).getText().equals("Like")) //Validating if assignment is liked to unlike it
                Assert.fail("The assignment is already liked so can not like it again.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", likelinks.get(index)); //Clicking on like

            List<WebElement> likecounts = driver.findElements(By.className("ls-post-like-count"));
            String likecount = likecounts.get(index).getText(); //Validating link count
            if (!likecount.equals("1"))
                Assert.fail("Like count of the assignment not equal to 1");

            List<WebElement> likelinksafterclicking = driver.findElements(By.className("ls-post-like-link"));
            String likelinkafterclicking = likelinksafterclicking.get(index).getText(); //Validating like changed to unlike
            if (!likelinkafterclicking.equals("Unlike"))
                Assert.fail("Like link not converted to unlike after clicking on it");

        } catch (Exception e) {
            Assert.fail("Exception in apphelper likeAssignment in class Assignment", e);
        }
    }

    public void unlikeAssignment(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> likelinks = driver.findElements(By.className("ls-post-like-link"));
            if (!likelinks.get(index).getText().equals("Unlike")) //Validating if assignment is liked to unlike it
                Assert.fail("The assignment is not liked so can not unlike it.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", likelinks.get(index)); //Clicking on unlike

            List<WebElement> likecounts = driver.findElements(By.className("ls-post-like-count"));
            String likecount = likecounts.get(index).getText(); //Validating link count
            if (!likecount.equals("0"))
                Assert.fail("Like count of the assignment not equal to 1");

            List<WebElement> likelinksafterclicking = driver.findElements(By.className("ls-post-like-link"));
            String likelinkafterclicking = likelinksafterclicking.get(index).getText(); //Validating like changed to unlike
            if (!likelinkafterclicking.equals("Like"))
                Assert.fail("Unlike link not converted to Like after clicking on it");

        } catch (Exception e) {
            Assert.fail("Exception in apphelper likeAssignment in class Assignment", e);
        }
    }


    public void commentAssignment(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String random = new RandomString().randomstring(5);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> allComments = driver.findElements(By.cssSelector("a[title='Comments']"));
            allComments.get(index).click();//click on Comment Link
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[contains(@id,'ls-stream_new_')]//inline")).click();
            Thread.sleep(1000);
            driver.findElement(By.name("comment")).sendKeys(random + Keys.RETURN);
            driver.findElement(By.className("post-comment")).click();
            //Searching for comment posted
            boolean commentfound = false;
            List<WebElement> comments = driver.findElements(By.className("ls-stream-post__comment-text"));
            for (WebElement comment : comments) {
                if (comment.getText().contains(random))  //using contains because gettext is returning the given name along with text posted
                {
                    commentfound = true;
                    break;
                }
            }
            if (commentfound == false)
                Assert.fail("Comment posted on assignment by instructor not found");


        } catch (Exception e) {
            Assert.fail("Exception in apphelper likeAssignment in class Assignment", e);
        }
    }

    public int commentCount(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        new Navigator().NavigateTo("Assignments");
        int index = 0;
        List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
        for (WebElement element : assignments) {
            if (element.getText().contains(assessmentname)) {
                break;
            }
            index++;
        }
        List<WebElement> commentscount = driver.findElements(By.className("ls-stream-post-comment-count"));
        return Integer.parseInt(commentscount.get(index).getText());
    }

    public void assignFormValidate(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();
            new UIElement().waitAndFindElement(By.cssSelector("div[class='tab active']"));
            if (driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources"))    //Opening All Resources tab if not opened after clicking on New Assignment button
                driver.findElement(By.className("tab")).click();

            //Adding assignment to search
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[title='Assign This']"));
            driver.findElement(By.cssSelector("span[title='Assign This']")).click();
		/*List<WebElement> assign = driver.findElements(By.className("assign-this"));

		for(WebElement assignment: assign)
		{

			if(assignment.getText().trim().equals("Assign This"))
			{

				assignment.click();
				break;
			}

		}*/
        } catch (Exception e) {
            Assert.fail("Exception in app helper assignFormValidate in class Assignment ", e);
        }
    }

    public void provideFeedback(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String studentname = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Assignments");
            int index = 0;
            //finding the index of the current assessment
            List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> viewresponseslink = driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
            new UIElement().waitAndFindElement(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            //finding the index of the particular student
            int index1 = 0;
            List<WebElement> usernames = driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            for (WebElement element : usernames) {
                if (element.getText().equals(studentname)) {

                    break;
                }
                index1++;
            }


            List<WebElement> gradebook = driver.findElements(By.className("idb-question-score-wrapper"));

					 /*Locatable hoverItem = (Locatable) gradebook.get(index1);
					 Mouse mouse = ((HasInputDevices) driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());*/
            new MouseHover().mouserhover("idb-gradebook-question-content");
            new UIElement().waitAndFindElement(By.className("ls-view-response-link"));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));

            driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys("This is a FeedbackText");

            // driver.findElement(By.className("view-user-question-performance-save-btn")).click();
            driver.findElement(By.xpath("//span[text()='Save']")).click(); //click on save button

        } catch (Exception e) {
            Assert.fail("Exception in  provideFeedback in AppHelper class Assignment", e);
        }
    }

    public void submitAssignmentAsStudentFromAssignmentsnavigation(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String submitassignment = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Assignments");
            List<WebElement> allElements = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : allElements) {
                if (element.getText().trim().contains(submitassignment)) {
                    element.click();
                    int helppage = driver.findElements(By.className("close-help-page")).size();
                    if (helppage == 1)
                        driver.findElement(By.className("close-help-page")).click();
                    new UIElement().waitAndFindElement(By.className("qtn-label"));
                    driver.findElement(By.className("qtn-label")).click();
                    new UIElement().waitAndFindElement(By.linkText("Submit"));
                    driver.findElement(By.linkText("Submit")).click(); //submitting the assignment as student 1
                    break;
                }
            }
        } catch (Exception e) {
            Assert.fail("Exception in app helper submitAssignmentAsStudent in class Assignment", e);
        }
    }

    public void clickonAssignment(String assignmentName) {
        WebDriver driver = Driver.getWebDriver();
        try {

            if (driver.getCurrentUrl().contains("lsStudentDashBoard")) {
                new Click().clickBycssselector("span[title='" + assignmentName + "']");
                new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='resource-title ']")));

            } else
                new Click().clickBycssselector("span[title='" + assignmentName + "']");
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='resource-title ']")));


        } catch (Exception e) {
            Assert.fail("Exception in apphelper clickonAssignment in class Assignments", e);
        }
    }


    public void createAssignmentAtTopicLevel(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String course = Config.course;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String topicname = ReadTestData.readDataByTagName("", "topicname", Integer.toString(dataIndex));
            String reservforassignment = ReadTestData.readDataByTagName("", "reservforassignment", Integer.toString(dataIndex));
            String practice_type = ReadTestData.readDataByTagName("", "practice_type", Integer.toString(dataIndex));

            new DirectLogin().CMSLogin();
            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                int index = 0;
                //Find the chapter index
                List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                if (chapterName != null) {
                    for (WebElement element : allChapters) {
                        if (element.getText().equals(chapterName)) {
                            break;
                        }
                        index++;
                    }
                } else {
                    allChapters.get(0).click();
                    index = 0;
                }
                //Find the topic under a chapter and click on it
                List<WebElement> expansionSymbol = driver.findElements(By.xpath("//*[starts-with(@class, 'expand-chapter-tree expand')]"));
                expansionSymbol.get(index).click();
                new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@class, 'course-topic-label node')]"));
                //List the topic and click on a topic
                List<WebElement> allTopic = driver.findElements(By.xpath("//*[starts-with(@class, 'course-topic-label node')]"));
                if (topicname != null) {
                    for (WebElement topic : allTopic) {
                        if (topic.getText().equals(topicname)) {
                            topic.click();
                            break;
                        }

                    }
                } else
                    allTopic.get(0).click();
                driver.findElement(By.cssSelector("div.create-practice")).click();
                if (practice_type == null) {
                    new Click().clickByclassname("create-regular-assessment-popup-item"); //click on the create regular assessment
                } else {
                    new Click().clickByclassname("create-file-assessment-popup-item");//click on the create file assessment
                }

                new Click().clickbylinkText("Adaptive Component Diagnostic");
                new Click().clickbylinkText(questiontype);
                int popup = driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
                if (popup == 0) {
                    driver.findElement(By.id("assessmentName")).click();
                    driver.findElement(By.id("assessmentName")).clear();
                    driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
                    driver.findElement(By.id("questionSetName")).clear();
                    driver.findElement(By.id("questionSetName")).sendKeys(questionset);

                    if (reservforassignment == null) {
                        driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
                    }

                    new QuestionCreate().trueFalseQuestions(dataIndex);
                }

            }

        } catch (Exception e) {
            Assert.fail("Exception in  createAssignmentAtTopicLevel in AppHelper class Assignment", e);
        }
    }


    public void clickViewResponse(String assessmentname) {
        WebDriver driver = Driver.getWebDriver();
        try {
            new Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            //click on 'View Responses link'
            List<WebElement> viewresponseslink = driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
        } catch (Exception e) {
            Assert.fail("Exception in  clickViewResponse in AppHelper class Assignment", e);
        }
    }

    public void viewResponseOfDW() {
        WebDriver driver = Driver.getWebDriver();
        try {
            new Navigator().NavigateTo("Assignments");    //navigate to Assignments
            driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();    //click on View Student Responses
            new UIElement().waitAndFindElement(By.className("idb-gradebook-question-content"));
            Actions ac = new Actions(driver);
            try {
                ac.moveToElement(driver.findElement(By.className("idb-gradebook-question-content"))).build().perform();
                new WebDriverWait(driver, 300).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='ls-view-response-link']")));
                ac.moveToElement(driver.findElement(By.cssSelector("div[class='ls-view-response-link']"))).build().perform();
            } catch (Exception e) {
                try {
                    ac.moveToElement(driver.findElement(By.className("idb-gradebook-question-content"))).build().perform();
                    new WebDriverWait(driver, 300).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='ls-view-response-link']")));
                    ac.moveToElement(driver.findElement(By.cssSelector("div[class='ls-view-response-link']"))).build().perform();
                } catch (Exception e1) {
                    ac.moveToElement(driver.findElement(By.className("idb-gradebook-question-content"))).build().perform();
                    new WebDriverWait(driver, 300).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='ls-view-response-link']")));
                    ac.moveToElement(driver.findElement(By.cssSelector("div[class='ls-view-response-link']"))).build().perform();
                }
            }

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ls-view-response-link']")));    //click on View Response link
        } catch (Exception e) {
            Assert.fail("Exception in  clicking view response link of Discussion widget", e);
        }
    }

    public void postCommentInAssessmentResponseTab() {
        WebDriver driver = Driver.getWebDriver();
        try {
            List<WebElement> comments = driver.findElements(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']"));
            comments.get(1).click();
            String random = new RandomString().randomstring(5);
            //Post a comment
            List<WebElement> commentarea = driver.findElements(By.className("ls-textarea-focus"));
            commentarea.get(1).sendKeys(random + Keys.ENTER);
            driver.findElement(By.className("post-comment")).click();
            //Searching for comment posted
            boolean commentfound = false;
            List<WebElement> postedcomments = driver.findElements(By.className("ls-stream-post__comment-text"));
            for (WebElement comment : postedcomments) {
                if (comment.getText().contains(random))  //using contains because gettext is returning the given name along with text posted
                {
                    commentfound = true;
                    break;
                }
            }
            if (commentfound == false)
                Assert.fail("Comment posted on assignment by instructor not found");
        } catch (Exception e) {
            Assert.fail("Exception in  postCommentInAssessmentResponseTab in AppHelper class Assignment", e);
        }
    }

    public void provideGradeToStudentForMultipleQuestions(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String studentname = ReadTestData.readDataByTagName("", "studentname", Integer.toString(dataIndex));
            String y[] = studentname.split(" ");
            studentname = y[1] + ", " + y[0];//reverse the name with comma in between

            String grade = ReadTestData.readDataByTagName("", "grade", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> viewresponseslink = driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
            List<WebElement> menuitem = driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            int gradeLinkIndex = 0;
            for (WebElement user : menuitem) {
                if (user.getText().equals(studentname)) {
                    /*Locatable hoverItem = (Locatable) user;
                    Mouse mouse = ((HasInputDevices) driver).getMouse();
                    mouse.mouseMove(hoverItem.getCoordinates());*/
                    new UIElement().waitAndFindElement(user);
                    new MouseHover().mouserhoverbywebelement(user);
                    new UIElement().waitAndFindElement(By.id("idb-grade-now-link"));
                    List<WebElement> gradenowlinks = driver.findElements(By.id("idb-grade-now-link"));
                    new UIElement().waitAndFindElement(gradenowlinks.get(gradeLinkIndex));
                    //gradenowlinks.get(gradeLinkIndex).click();
                    new Click().clickByElement(gradenowlinks.get(gradeLinkIndex));
                    new UIElement().waitAndFindElement(By.cssSelector("input[class='idb-grade-points']"));
                    List<WebElement> allGradeBox = driver.findElements(By.cssSelector("input[class='idb-grade-points']"));
                    for (WebElement gradeBox : allGradeBox) {
                        new UIElement().waitAndFindElement(gradeBox);
                        gradeBox.clear();
                        if (grade == null) {
                            gradeBox.sendKeys("0.7");
                            new UIElement().waitAndFindElement(gradeBox);
                            gradeBox.sendKeys(Keys.TAB);
                        } else {
                            new UIElement().waitAndFindElement(gradeBox);
                            gradeBox.sendKeys("" + grade + "" + Keys.TAB);
                        }
                    }
                    driver.findElement(By.xpath("/html/body")).click();
                }
                gradeLinkIndex++;
            }

        } catch (Exception e) {
            Assert.fail("Exception in apphelper provideGradeToStudentForMultipleQuestions in class Assignment", e);
        }
    }
    /*
    *
    * @Author Mukesh
    * This method will enter grade of Particular question of Particular student
    * */

    public void enterGradeOnParticularQuestion(int studentIndex, int questionIndex, String sendKey) {
        WebDriver driver = Driver.getWebDriver();
        try {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            List<WebElement> menuitem = driver.findElements(By.xpath("//span[@class='idb-gradebook-assignment-username']"));
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(menuitem.get(studentIndex));
            Thread.sleep(2000);
            List<WebElement> enterGradeLink = driver.findElements(By.id("idb-grade-now-link"));
            Thread.sleep(2000);
            enterGradeLink.get(studentIndex).click();
            List<WebElement> gradeBox = driver.findElements(By.xpath("//input[starts-with(@class,'idb-grade-points')]"));
            Thread.sleep(2000);
            gradeBox.get(questionIndex).clear();
            try {
                gradeBox.get(questionIndex).sendKeys(sendKey);
            } catch (Exception e) {
                currentAssignments.clearGrade_textBox.sendKeys(sendKey);
            }
            Thread.sleep(2000);
            driver.findElement(By.xpath("//html/body")).click();
        } catch (InterruptedException e) {
            Assert.fail("Exception in method enterGradeOnParticularQuestion");
        }

    }


    public void addQuestionsWithCustomizedQuestionText(int dataIndex, String questionType, String assignmentname, int noOfQuestions) {

        WebDriver driver = Driver.getWebDriver();
        try {

            assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
            String course = Config.course;
            new DirectLogin().CMSLogin();
            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null)
                    new Click().clickBycssselector("div.course-chapter-label.node");

                else if (courselevel != null)
                    new Click().clickBycssselector("div[class='course-label node']");

                else {
                    new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                    if (driver.findElements(By.xpath("//*[contains(text(),'" + chapterName + "')]")).size() > 1) {
                        System.out.println("inside if");
                        new TopicOpen().openCMSLastChapter();
                    } else {
                        System.out.println("inside else");
                        List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                        for (WebElement chapters : allChapters) {
                            if (chapters.getText().contains(chapterName)) {
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters);
                                break;
                            }

                        }
                    }

                }

                List<WebElement> elements = driver.findElements(By.xpath("//div[@title='" + assignmentname + "']"));
                try {
                    elements.get(elements.size() - 1).click();
                } catch (Exception e) {

                }
              /*  List<WebElement> elements = driver.findElements(By.className("collection-assessment-name"));
                for (WebElement content : elements) {
                    if (content.getText().trim().equals(assignmentname)) {

                        Thread.sleep(3000);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", content);
                        assignmentExists = true;
                        break;
                    }
                }*/
                /*if(assignmentExists == true) {
                   addQuestionLink();
                }*/
                for (int i = 0; i < noOfQuestions; i++) {
                    if (questionType.equals("truefalse") || questionType.equals("qtn-type-true-false-img")) {
                        addQuestionLink();
                        new QuestionCreate().trueFalseQuestions(dataIndex);
                    }
                    if (questionType.equals("multiplechoice") || questionType.equals("qtn-multiple-choice-img")) {
                        addQuestionLink();
                        new QuestionCreate().multipleChoice(dataIndex);
                    }
                    if (questionType.equals("multipleselection") || questionType.equals("qtn-multiple-selection-img")) {
                        addQuestionLink();
                        new QuestionCreate().multipleSelection(dataIndex);
                    }

                    if (questionType.equals("textentry") || questionType.equals("qtn-text-entry-img")) {
                        addQuestionLink();
                        new QuestionCreate().textEntry(dataIndex);
                    }

                    if (questionType.equals("textdropdown") || questionType.equals("qtn-text-entry-drop-down-img")) {
                        addQuestionLink();
                        new QuestionCreate().textDropDown(dataIndex);
                    }

                    if (questionType.equals("numericentrywithunits") || questionType.equals("qtn-text-entry-numeric-units-img")) {
                        addQuestionLink();
                        new QuestionCreate().numericEntryWithUnits(dataIndex);
                    }

                    if (questionType.equals("advancednumeric")) {
                        addQuestionLink();
                        new QuestionCreate().advancedNumeric(dataIndex);
                    }
                    if (questionType.equals("expressionevaluator")) {
                        addQuestionLink();
                        new QuestionCreate().expressionEvaluator(dataIndex);
                    }

                    if (questionType.equals("essay") || questionType.equals("qtn-essay-type")) {
                        addQuestionLink();
                        new QuestionCreate().essay(dataIndex);
                    }

                    if (questionType.equals("writeboard") || questionType.equals("qtn-writeboard-type-new")) {
                        addQuestionLink();
                        new QuestionCreate().writeBoard(dataIndex);
                    }
                    if (questionType.equals("audio") || questionType.equals("qtn-audio-type")) {
                        addQuestionLink();
                        new QuestionCreate().audio(dataIndex);
                    }
                }
            }
        } catch (Exception e) {

            Assert.fail("Exception in create in Apphelper addQuestions in class AssignmentCreate", e);
        }
    }

    /*
     * @Author Sumit
     * Method will create a chapter and will publish the chapter
     */
    public void createChapter(int dataIndex, int... tloid) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String newChapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));

            String course = Config.course;
            new DirectLogin().CMSLogin();
            driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
            new UIElement().waitAndFindElement(By.id("manage-toc"));
            Thread.sleep(4000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("manage-toc")));
            Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div.add-new.add-new-chapter > span.f1")));
            Thread.sleep(3000);
            driver.switchTo().activeElement().sendKeys(newChapterName);
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("div[id='body-content-wrapper']")).click();
            Thread.sleep(3000);
            List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label node course-chapter-label-unpublished')]"));
            for (WebElement chapters : allChapters) {
                if (chapters.getText().contains(newChapterName)) {
                    Locatable hoverItem = (Locatable) chapters;
                    Mouse mouse = ((HasInputDevices) driver).getMouse();
                    mouse.mouseMove(hoverItem.getCoordinates());
                }
            }
            //associate TLO
            if (tloid.length > 0) {
                driver.findElement(By.xpath("//span[@class='tree-node-associate-icon']")).click();//click on the tlo icon
                WebDriverWait wait = new WebDriverWait(driver, 30);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[title='Add Learning Objective']")));
                driver.findElement(By.cssSelector("span[title='Add Learning Objective']")).click();
                Thread.sleep(500);
                if (learningobjective.equals("true"))
                    driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[3]/label")).click();
                else {
                    if (learningobjective.equals("associateTLO")) {
                        List<WebElement> element = driver.findElements(By.xpath("//div[@class='taxonomyStructureContent']/div/label"));

                        new ScrollElement().scrollToElementUsingCoordinates(element.get(element.size() - 1));
                        // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element.get(element.size() - 1));
                        element.get(element.size() - 1).click();
                        Thread.sleep(3000);
                    }
                }
                new Click().clickBycssselector("span.add-collection-to-subtopic");//click on Associate
            }
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper createChapter in class AssignmentCreate", e);
        }
    }

    public void OpenAssignment(String assignmentname, int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            String course = Config.course;
            new DirectLogin().CMSLogin();
            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                } else {
                    List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                    for (WebElement chapters : allChapters) {
                        if (chapters.getText().contains(chapterName)) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters);
                            break;
                        }

                    }

                }

                List<WebElement> elements = driver.findElements(By.cssSelector("div[title = '" + assessmentName + "']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elements.get(elements.size() - 1));
            }
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper OpenAssignment in class AssignmentCreate", e);
        }
    }

    public void openAssignmentFromAssignmentPage(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();//click on assignment

            new UIElement().waitAndFindElement(By.className("question-label"));//Waiting for 180 seconds to let the assignment open
        } catch (Exception e) {

        }

    }


    public void openAssignmentFromQuestionBanksPageAsInstructor(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {

            new Navigator().NavigateTo("Question Banks");//navigate to Question Banks
            new Assignment().searchAssessmentInQuestionBanks(dataIndex);
            driver.findElement(By.className("ls-preview")).click();//click on preview button
            new UIElement().waitAndFindElement(By.className("question-label")); //Waiting for 180 seconds to let the assignment open

        } catch (Exception e) {

        }

    }

    public void duplicatQuestion() {
        WebDriver driver = Driver.getWebDriver();
        try {
            new Click().clickByid("questionOptions");
            new Click().clickByid("copyQuestion");
            new Click().clickBycssselector("a[title='Draft']");
            new Click().clickBycssselector("a[rel='80']");
            // new ComboBox().selectValue(4, "Publish");
            Thread.sleep(2000);
            driver.findElement(By.id("saveQuestionDetails1")).click();
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper duplicatQuestion in class AssignmentCreate", e);
        }
    }


    //assign assignment from Assignemnt tab of eTextbook
    public void assignAssignmentFromEtextBook(String dataIndex, int assignThisIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", dataIndex);
            new Navigator().NavigateTo("e-Textbook");//go to e-Textbook
            new TOCShow().tocHide(); //close the TOC
            new Navigator().navigateToTab("Assignments");   //go to Assignments tab
            List<WebElement> allAssignThisButton = driver.findElements(By.cssSelector("span[class='ls-assign-this-sprite-right-tab assign-resource-bg']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allAssignThisButton.get(assignThisIndex));//click on AssignThis button
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, true);
            new UIElement().waitAndFindElement(By.id("due-time"));

            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("due-time")));
            Thread.sleep(9000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[text()='07:30 PM']")));
            Thread.sleep(3000);


            driver.findElement(By.id("due-date")).click();//click on due date
            new UIElement().waitAndFindElement(By.cssSelector("a[title='Next']"));
            driver.findElement(By.cssSelector("a[title='Next']")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            driver.findElement(By.linkText(duedate)).click();//click on specified date

            if(gradeable!=null){
                if(gradeable.equals("false")){
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")));
                }
            }
            new UIElement().waitAndFindElement(By.id("additional-notes"));
            driver.findElement(By.id("additional-notes")).clear();
            driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            new Click().clickBycssselector("span[class='btn sty-green submit-assign']");
            new UIElement().waitTillInvisibleElement(By.cssSelector("span[class='btn sty-green submit-assign']"));
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper assignAssignmentFromEtextBook in class Assignment", e);
        }
    }



    public void assignAssignmentFromEtextBookInStudyPlan(String dataIndex, int assignThisIndex) {
        WebDriver driver=Driver.getWebDriver();try{
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);

            new Navigator().NavigateTo("e-Textbook");//go to e-Textbook
            List<WebElement> staticAssessmentBlockList = driver.findElements(By.cssSelector("div[class='inner-assessment-block static_assessment']"));
            for(int a =0;a<staticAssessmentBlockList.size();a++){
                System.out.println("1 : " + staticAssessmentBlockList.get(a).getText().trim());
                if(staticAssessmentBlockList.get(a).getText().trim().equals(assessmentName)){
                    WebElement rightArrowElement = staticAssessmentBlockList.get(a).findElement(By.className("ls-inner-arw"));
                    rightArrowElement.click();
                    break;
                }
            }
            driver.findElement(By.cssSelector("div[class='toc-assign-this open-assign-window']")).click();
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, true);
            Thread.sleep(9000);
            new UIElement().waitAndFindElement(By.id("due-time"));

            driver.findElement(By.id("due-time")).click();//click on dur time
            new UIElement().waitAndFindElement(By.xpath("//li"));
            Thread.sleep(9000);

            driver.findElement(By.xpath("//ul[@class = 'ui-timepicker-list']//li")).click();
            driver.findElement(By.id("due-date")).click();//click on due date
            new UIElement().waitAndFindElement(By.cssSelector("a[title='Next']"));
            driver.findElement(By.cssSelector("a[title='Next']")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            driver.findElement(By.linkText(duedate)).click();//click on specified date

            if (gradeable != null) {
                if (gradeable.equals("false")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")));
                }
            }
            new UIElement().waitAndFindElement(By.id("additional-notes"));
            driver.findElement(By.id("additional-notes")).clear();
            driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            new Click().clickbyxpath("//span[starts-with(@class,'btn sty-green submit-assign')]");

            //btn sty-green submit-assign toc-assessment-submit
            new UIElement().waitTillInvisibleElement(By.xpath("//span[starts-with(@class,'btn sty-green submit-assign')]"));
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper assignAssignmentFromEtextBookInStudyPlan in class Assignment", e);
        }
    }


    //open assignment from Assignment tan from eTextBook
    public void openAssignmentFromAssignmentTab(int openLinkIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            new Navigator().NavigateTo("e-Textbook");//go to e-Textbook
            new TOCShow().tocHide(); //close the TOC
            new Navigator().navigateToTab("Assignments");   //go to Assignments tab
            List<WebElement> allOpenLink = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-cycle-bg']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOpenLink.get(openLinkIndex));//click on open link
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper openAssignmentFromAssignmentTab in class Assignment", e);
        }
    }

    public void assignAssignmentFromAssignmentTab(int assignLink, String assessmentName) {
        WebDriver driver = Driver.getWebDriver();
        try {
            new Navigator().NavigateTo("e-Textbook");//go to e-Textbook
            new TOCShow().tocHide(); //close the TOC
            new Navigator().navigateToTab("Assignments");   //go to Assignments tab
            int pos = 0;
            List<WebElement> allOpenLink = driver.findElements(By.className("ls_assessment_link"));
            for (int a = 0; a < allOpenLink.size(); a++) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allOpenLink.get(a));
                if ((allOpenLink.get(a).getText().equals(assessmentName))) {
                    System.out.println("assessmentName:" + assessmentName);
                    break;
                }
                pos++;

            }

            List<WebElement> rightArrowElementsList = driver.findElements(By.className("ls-assignment-show-assign-this-block"));
            new Click().clickByElement(rightArrowElementsList.get(pos));
            // List<WebElement> assignThisELementsList = driver.findElements(By.cssSelector("span[class='ls-assign-this-sprite-right-tab assign-resource-bg']"));
            List<WebElement> assignThisELementsList = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-cycle-bg']"));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", assignThisELementsList.get(pos));//click on open link
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper assignAssignmentFromAssignmentTab in class Assignment", e);
        }
    }

    //open the 1st assignment from Course stream
    public void openAssignmentFromCourseStream(String dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            new Navigator().NavigateTo("Course Stream");
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='ls-stream-assignment-title']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='ls-stream-assignment-title']")));//click on Assignment
            new UIElement().waitTillInvisibleElement(By.cssSelector("span[class='ls-stream-assignment-title']"));
            //new WebDriverWait(driver, 800).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper openAssignmentFromCourseStream in class Assignment", e);
        }
    }

    //Author Sumit On 13/08/2014
    //submit the assignment with immediate feedback enabled using Assignment Policy.
    public void submitAssignmentWithImmediateFeedBack(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String submitassignment = ReadTestData.readDataByTagName("", "submitassignment", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Course Stream");
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-stream-assignment-title']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='ls-stream-assignment-title']")));//click on Assignment
            new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));

            int helppage = driver.findElements(By.className("close-help-page")).size();
            if (helppage == 1)
                driver.findElement(By.className("close-help-page")).click();

            int timer = 1;
            while (timer != 0) {
                String questionText = new TextFetch().textfetchbyid("question-edit");
                if (questionText.contains("True False"))
                    new AttemptQuestion().trueFalse(false, "correct", dataIndex);

                if (questionText.contains("Multiple Choice"))
                    new AttemptQuestion().multipleChoice(false, "correct", dataIndex);

                if (questionText.contains("Multi Selection"))
                    new AttemptQuestion().multipleSelection(false, "correct", dataIndex);

                if (questionText.contains("Text Entry"))
                    new AttemptQuestion().textEntry(false, "correct", dataIndex);

                if (questionText.contains("Text Drop Down"))
                    new AttemptQuestion().textDropDown(false, "correct", dataIndex);

                if (questionText.contains("Numeric Entry With Units"))
                    new AttemptQuestion().numericEntryWithUnits(false, "correct", dataIndex);

                if (questionText.contains("Advanced Numeric"))
                    new AttemptQuestion().advancedNumeric(false, "correct", dataIndex);

                if (questionText.contains("Expression Evaluator"))
                    new AttemptQuestion().expressionEvaluator(false, "correct", dataIndex);

                if (questionText.contains("Essay"))
                    new AttemptQuestion().essay(false, "correct", dataIndex);

                if (questionText.contains("Write Board"))
                    new AttemptQuestion().writeBoard(false, "correct", dataIndex);


                boolean lastQuestionNotToSubmitAssignment = submitAnswer(submitassignment, dataIndex);
                System.out.println("lastQuestionNotToSubmitAssignment: " + lastQuestionNotToSubmitAssignment);

                if (lastQuestionNotToSubmitAssignment == true) {
                    driver.findElement(By.linkText("Finish Assignment")).click();
                    System.out.println("clicked finish");
                    timer = 0;
                } else
                    timer = driver.findElements(By.id("assessmentTimer")).size();
            }
        } catch (Exception e) {
            Assert.fail("Exception in app helper submitAssignmentAsStudent in class Assignment", e);
        }
    }


    public void deleteAssignment() {
        WebDriver driver = Driver.getWebDriver();
        try {
            new UIElement().waitAndFindElement(By.cssSelector("span[title='Un-assign Assignment']"));
            driver.findElement(By.cssSelector("span[title='Un-assign Assignment']")).click(); //clicking on delete link for the first assignment in summary page
            driver.findElement(By.cssSelector("div[class='as-modal-yes-btn delete-button']")).click(); //click on yes button in the alert which comes
        } catch (Exception e) {
            Assert.fail("Exception in app helper deleteAssignment in class Assignment", e);
        }
    }

    public boolean submitAnswer(String submitassignment, int dataIndex) {
        String questionType = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
        String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));

        boolean lastQuestionNotToSubmitAssignment = true;
        String submitAnswer = "";
        String finishedAssignment = "";
        try {
            if (questionType.equals("Static Practice")) {
                if (gradeable != null) {
                    {
                        if (gradeable.equals("true")) {
                            submitAnswer = "btn btn--primary btn--large btn--next";
                            Thread.sleep(2000);
                            finishedAssignment = "btn btn--primary btn--large btn--submit";
                        } else {
                            submitAnswer = "btn btn--primary btn--large btn--next long-text-button ";
                            Thread.sleep(2000);
                            finishedAssignment = "btn btn--primary btn--large btn--submit long-text-button";
                        }
                    }
                }
            }

            if (submitassignment == null || submitassignment.equalsIgnoreCase("true")) {
                WebDriver driver = Driver.getWebDriver();
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='" + submitAnswer + "']")));
                    lastQuestionNotToSubmitAssignment = false;

                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='" + finishedAssignment + "']")));

                }

            }


        } catch (Exception e) {
            Assert.fail("Exception while attempting assignment", e);
        }
        return lastQuestionNotToSubmitAssignment;

    }

    public void addQuestionLink() {
        WebDriver driver = Driver.getWebDriver();
        try {
            new UIElement().waitAndFindElement(By.id("questionOptions"));
            new Click().clickByid("questionOptions");
            new UIElement().waitAndFindElement(By.id("addQuestion"));
            new Click().clickByid("addQuestion");
        } catch (Exception e) {
            Assert.fail("Exception while clicking on add questions link in CMS", e);
        }

    }

    public void nextButtonInQuestionClick() {
        WebDriver driver = Driver.getWebDriver();
        try {
            if (driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button ']")).size() > 0) //next
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button ']")));

            else if (driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).size() > 0) //next
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")));
            else if (driver.findElements(By.cssSelector("div[class='ls-static-practice-test-next-button']")).size() > 0)//click on Next for static assessment
            {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ls-static-practice-test-next-button']")));
            } else
                Assert.fail("Next button not found in assignment");

        } catch (Exception e) {
            Assert.fail("Exception while clicking on next button while attempting an assignment", e);
        }
    }

    public void previousButtonInQuestionClick() {
        WebDriver driver = Driver.getWebDriver();
        try {
            if (driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--previous long-text-button']")).size() > 0) //next
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--previous long-text-button']")));

            else if (driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--previous']")).size() > 0) //next
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--previous']")));

            else
                Assert.fail("Previous button not found in assignment");

        } catch (Exception e) {
            Assert.fail("Exception while clicking on next button while attempting an assignment", e);
        }
    }

    public void submitButtonInQuestionClick() {
        WebDriver driver = Driver.getWebDriver();
        try {
            if (driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).size() > 0) //Finish Assignment
            {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")));
            } else if (driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).size() > 0)//click on Finish Assignment
            {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")));
            } else if (driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large next-or-submit-link']")).size() > 0)//click on Finish Assignment
            {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large next-or-submit-link']")));
            } else if (driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size() > 0)//click on Submit for static assessment
            {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")));
            } else if (driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).size() > 0)//click on Submit for static assessment
            {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")));
            } else if (driver.findElements(By.cssSelector("div[class='ls-static-practice-test-submit-button']")).size() > 0)//click on Submit for static assessment
            {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ls-static-practice-test-submit-button']")));
            } else
                Assert.fail("Submit button not found in assignment");
        } catch (Exception e) {
            Assert.fail("Exception while clicking on submit button while attempting an assignment", e);
        }

    }

    public void extendDueDate(String dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            String newduedate = ReadTestData.readDataByTagName("", "newduedate", dataIndex);
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            if (assessmentname != null) {
                driver.findElement(By.linkText("All Assignment Status")).click();
                new UIElement().waitAndFindElement(By.linkText("Available for Students"));
                driver.findElement(By.linkText("Available for Students")).click();
                new UIElement().waitAndFindElement(By.linkText("All Activity"));

                driver.findElement(By.linkText("All Activity")).click();
                new UIElement().waitAndFindElement(By.linkText("Question Practice"));
                if (gradeable == null)
                    driver.findElement(By.linkText("Question Practice")).click();
                else if (gradeable.equals("false"))
                    driver.findElement(By.linkText("Question Practice")).click();
                else
                    driver.findElement(By.linkText("Question Assignment")).click();

                new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));

                List<WebElement> assignmentNames = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
                int index = 0;
                for (int i = 0; i < assignmentNames.size(); i++) {
                    if (assignmentNames.get(i).getText().equals(assessmentname)) {
                        break;
                    }
                    index++;
                }
                driver.findElements(By.cssSelector("span[class='assign-more']")).get(index).click(); //click on Update Assignment in Assignments page only
                currentAssignments.getReassign_button().click();    //click on Re-assign button
            } else {
                currentAssignments.getUpdateAssignment_button().click();//click on Update Assignment at first index
            }
            currentAssignments.getExtendDueDateTab().click();   //go to extend due date tab
            new ShareWith().shareInCS(shareWithInitialString, shareName, shareWithClass, context_title, true);  //share with class/student
            driver.findElement(By.id("extended-due-time")).click();
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals(newduedate)) {
                    time.click();
                    break;
                }
            }
            driver.findElement(By.id("extended-due-date")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            new UIElement().waitAndFindElement(By.linkText(newduedate));
            driver.findElement(By.linkText(newduedate)).click();
            currentAssignments.getUpdateButtonInReassign_button().get(0).click();//click on Update Assignment link
            driver.findElement(By.cssSelector("span[class='extend-due-submit-yes']")).click();

        } catch (Exception e) {
            Assert.fail("Exception in app helper extendDueDate in class Assignment", e);
        }

    }

    public void extendDueDateWithDefaultClassSection(String dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            String newduedate = ReadTestData.readDataByTagName("", "newduedate", dataIndex);
            String activityType = ReadTestData.readDataByTagName("", "activityType", dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);

            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            if (assessmentname != null) {

                driver.findElement(By.linkText("All Assignments")).click();
                new UIElement().waitAndFindElement(By.linkText("Question Practice"));
                if (gradeable == null)
                    driver.findElement(By.linkText("Question Practice")).click();
                else if (gradeable.equals("false"))
                    driver.findElement(By.linkText("Question Practice")).click();

                else if (activityType != null && activityType.equals(activityType))
                    driver.findElement(By.linkText(activityType)).click();
                else
                    driver.findElement(By.linkText("Question Assignment")).click();

                if (driver.findElements(By.cssSelector("span[class='ls-leaning-activity instructor-assessment-review']")).size() >= 1) {
                    List<WebElement> assignmentName = driver.findElements(By.cssSelector("span[class='ls-leaning-activity instructor-assessment-review']"));
                    int index = 0;
                    for (int i = 0; i < assignmentName.size(); i++) {
                        if (assignmentName.get(i).getText().equals(assessmentname)) {
                            break;
                        }
                        index++;
                    }
                    currentAssignments.viewStudentResponses.get(index).click(); //click on view student responses

                } else {
                    List<WebElement> assignmentNames = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
                    int index = 0;
                    for (int i = 0; i < assignmentNames.size(); i++) {
                        if (assignmentNames.get(i).getText().equals(assessmentname)) {
                            break;
                        }
                        index++;
                    }
                    currentAssignments.viewStudentResponses.get(index).click(); //click on view student responses
                }
            } else {
                currentAssignments.getViewGrade_link().click();//click on Update Assignment at first index
            }
            assignmentResponsesPage.extendDueDateLabel.click();  //go to extend due date tab
            //new ShareWith().shareInCS(shareWithInitialString, shareName, shareWithClass, context_title, true);  //share with class/student
            driver.findElement(By.id("extended-due-time")).click();
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals(newduedate)) {
                    time.click();
                    break;
                }
            }
            driver.findElement(By.id("extended-due-date")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            new UIElement().waitAndFindElement(By.linkText(newduedate));
            driver.findElement(By.linkText(newduedate)).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", assignmentResponsesPage.updateExtendDueDate);//click on Update Assignment link
            Thread.sleep(1000);

        } catch (Exception e) {
            Assert.fail("Exception in app helper extendDueDateWithDefaultClassSection in class Assignment", e);
        }

    }


    /**
     * This function will update grade after grade released
     *
     * @author Mukesh
     */

    public void updateGradeAfterGradeRelease(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String grade = ReadTestData.readDataByTagName("", "grade", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Current Assignments");// navigate to the assignment summary page
            driver.findElement(By.className("ls-grade-book-assessment")).click();////click on the view grade link
            new UIElement().waitAndFindElement(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            Actions action = new Actions(driver);
            List<WebElement> we = driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            action.moveToElement(we.get(0)).build().perform();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("idb-grade-now-link")));    //click on Enter Grade
            new UIElement().waitAndFindElement(By.cssSelector("input[class='idb-grade-points']"));
            List<WebElement> provideGrade = driver.findElements(By.cssSelector("input[class='idb-grade-points']"));
            for (WebElement eachGrade : provideGrade) {
                eachGrade.clear();
                eachGrade.sendKeys(grade);
                eachGrade.sendKeys(Keys.TAB);

            }
            driver.findElement(By.xpath("/html/body")).click();
            driver.navigate().refresh();
            driver.findElement(By.className("ls-grade-book-assessment")).click();////click on the view grade link

        } catch (Exception e) {
            Assert.fail("Exception in apphelper provideGradeAfterReleasein class Assignment", e);
        }
    }

    /**
     * @Author Mukesh
     * Method will provide feedback for particular student
     */

    public void provideFeedBackForMultipleQuestion(int dataIndex, String sendKeys) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String studentname = ReadTestData.readDataByTagName("", "studentname", Integer.toString(dataIndex));
            String y[] = studentname.split(" ");
            studentname = y[1] + ", " + y[0];//reverse the name with comma in between

            System.out.println("Student Name:" + studentname);
            new Navigator().NavigateTo("Current Assignments");// navigate to the assignment summary page
            new Click().clickByclassname("ls-grade-book-assessment");//click on the view student feedback
            List<WebElement> menuitem = driver.findElements(By.cssSelector("span[class='idb-gradebook-assignment-username']"));
            int feedBackLinkIndex = 0;
            for (WebElement user : menuitem) {
                if (user.getText().equals(studentname)) {
                    break;
                } else {
                    feedBackLinkIndex++;
                }

            }
            System.out.println("feedBackLinkIndex: " + feedBackLinkIndex);
            List<WebElement> provideFeedback = driver.findElements(By.cssSelector("div[class='idb-gradebook-content-coloumn']"));
            for (int i = feedBackLinkIndex; i < provideFeedback.size(); i++) {
                new MouseHover().mouserHoverByClassList("idb-gradebook-question-content", feedBackLinkIndex);

            }
            new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));
            driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys(sendKeys);
            driver.findElement(By.xpath("//span[text()='Save']")).click(); //click on save button
            new TabClose().tabClose(2);//close the tab

        } catch (Exception e) {
            Assert.fail("Exception in method provideFeedBackForMultipleQuestion of class Assignment", e);
        }
    }

    /**
     * @Author Mukesh
     * Method will import question using WPQTI
     */
    public void importQuestionUsingWPQTI(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String course = "";
            String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
            String course_name = ReadTestData.readDataByTagName("", "course_name", Integer.toString(dataIndex));
            String practice_type = ReadTestData.readDataByTagName("", "practice_type", Integer.toString(dataIndex));
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
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
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
                driver.findElement(By.cssSelector("div.create-practice")).click();//click on the create practice link

                if (practice_type == null) {
                    new Click().clickByclassname("create-regular-assessment-popup-item"); //click on the create regular assessment
                } else {
                    new Click().clickByclassname("create-file-assessment-popup-item");//click on the create file assessment
                }

                new Click().clickbylinkText("Adaptive Component Diagnostic");
                new Click().clickbylinkText(questiontype);
                int popup = 1;
                try {
                    driver.findElement(By.cssSelector("div[class='cms-notification-message-body']"));
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

                    new Click().clickByid("import-contents-img");//click on import image


                    try {
                        new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
                        Thread.sleep(15000);
                        new Click().clickByid("start_queue");//click on upload button
                        Thread.sleep(3000);
                        new Click().clickByclassname("proceedToViewAContent");//click on the proceed button
                    } catch (Exception e) {
                        Assert.fail("Exception in uploading the question type using WPQTI", e);
                    }
                }

            }

        } catch (Exception e) {
            Assert.fail("Exception in Apphelper importQuestionUsingWPQTI of class Assignment", e);

        }
    }

    /**
     * @Author Mukesh
     * Method will deActivate question
     */
    public void deActivateQuestion(int dataIndex, String diaAssessment, int noOfQuestion) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String course = "";
            diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
            String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
            String course_name = ReadTestData.readDataByTagName("", "course_name", Integer.toString(dataIndex));
            String deActivateQuestion = ReadTestData.readDataByTagName("", "deActivateQuestion", Integer.toString(dataIndex));
            String test_type = ReadTestData.readDataByTagName("", "test_type", Integer.toString(dataIndex));
            String subSection_level = ReadTestData.readDataByTagName("", "subSection_level", Integer.toString(dataIndex));
            String chapterIndex = ReadTestData.readDataByTagName("", "chapterIndex", Integer.toString(dataIndex));
            String topicIndex = ReadTestData.readDataByTagName("", "topicIndex", Integer.toString(dataIndex));


            course = course_name;
            System.out.println("course: " + course);
            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }

            new DirectLogin().CMSLogin();

            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
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
                if (subSection_level != null) {
                    new SelectCourse().expandChapterTreeByIndex(dataIndex, Integer.parseInt(chapterIndex));
                    new SelectCourse().selectTopicByIndex(dataIndex, Integer.parseInt(topicIndex));

                }
                if (test_type.equals("DS")) {
                    driver.findElement(By.xpath("//div[@title='" + diaAssessment + "']")).click(); //click on the diagnostic test
                }

                if (test_type.equals("PS")) {
                    driver.findElement(By.xpath("//div[@title='" + diaAssessment + "']")).click(); //click on the Practice test

                }

                if (test_type.equals("SS")) {
                    driver.findElement(By.xpath("//div[@title='" + diaAssessment + "']")).click(); //click on the Static test

                }

                new UIElement().waitAndFindElement(By.id("questionOptions"));
                new Click().clickByid("questionOptions"); //click on question option
                new UIElement().waitAndFindElement(By.id("questionRevisions"));
                new Click().clickByid("questionRevisions"); // click on revisions
                if (deActivateQuestion == null) {
                    new Click().clickByid("cms-question-revision-deactivate-button");//click on the deactivate button

                } else {
                    for (int i = 1; i <= noOfQuestion; i++) {
                        new Click().clickbyxpath("//a[@title='Jump To Q#']"); //click on the Jump to Q#
                        WebElement element = driver.findElement(By.xpath("//label[@id='" + Integer.toString(i) + "']"));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                        new UIElement().waitAndFindElement(By.id("questionOptions"));
                        new Click().clickByid("questionOptions"); //click on question option
                        new UIElement().waitAndFindElement(By.id("questionRevisions"));
                        new Click().clickByid("questionRevisions"); // click on revisions
                        new UIElement().waitAndFindElement(By.id("cms-question-revision-deactivate-button"));
                        new Click().clickByid("cms-question-revision-deactivate-button");//click on the deactivate button

                    }
                }

            }
        } catch (Exception e) {

            Assert.fail("Exception in create in Apphelper deActivateQuestion in class AssignmentCreate", e);
        }
    }

    /**
     * @Author Mukesh
     * Method will deActivate Particular Question
     */
    public void deActivateParticularQuestion(int dataIndex, String diaAssessment) {
        WebDriver driver = Driver.getWebDriver();
        try {
            diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", Integer.toString(dataIndex));
            String test_type = ReadTestData.readDataByTagName("", "test_type", Integer.toString(dataIndex));
            String subSection_level = ReadTestData.readDataByTagName("", "subSection_level", Integer.toString(dataIndex));
            String chapterIndex = ReadTestData.readDataByTagName("", "chapterIndex", Integer.toString(dataIndex));
            String topicIndex = ReadTestData.readDataByTagName("", "topicIndex", Integer.toString(dataIndex));
            String QuestionNo = ReadTestData.readDataByTagName("", "QuestionNo", Integer.toString(dataIndex));
            String assessment = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            if (subSection_level == null) {
                driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();

            } else {
                new SelectCourse().expandChapterTreeByIndex(dataIndex, Integer.parseInt(chapterIndex));
                new SelectCourse().selectTopicByIndex(dataIndex, Integer.parseInt(topicIndex));
            }

            boolean assignmentExists = false;
            List<WebElement> elements = driver.findElements(By.className("collection-assessment-name"));
            for (WebElement content : elements) {
                if (content.getText().trim().equals(assessment)) {

                    new UIElement().waitAndFindElement(content);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", content);
                    assignmentExists = true;
                    break;
                }
            }

            if (test_type.equals("DS")) {
                driver.findElement(By.xpath("//div[@title='" + diaAssessment + "']")).click(); //click on the diagnostic test
            }

            if (test_type.equals("PS")) {
                driver.findElement(By.xpath("//div[@title='" + diaAssessment + "']")).click(); //click on the Practice test

            }

            if (test_type.equals("SS")) {
                driver.findElement(By.xpath("//div[@title='" + diaAssessment + "']")).click(); //click on the Static test

            }
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Jump To Q#']")));
            driver.findElement(By.xpath("//a[@title='Jump To Q#']")).click();
            new UIElement().waitAndFindElement(By.linkText(QuestionNo));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.linkText(QuestionNo)));
            driver.findElement(By.linkText(QuestionNo)).click();
            new UIElement().waitAndFindElement(By.id("questionOptions"));
            // new CMSActions().navigateToQuestionNo(Integer.parseInt(QuestionNo));
            new Click().clickByid("questionOptions"); //click on question option
            new Click().clickByid("questionRevisions"); // click on revisions
            new Click().clickByid("cms-question-revision-deactivate-button");//click on the deactivate button


        } catch (Exception e) {

            Assert.fail("Exception in create in Apphelper deActivateParticularQuestion in class AssignmentCreate", e);
        }
    }


    /**
     * @Author Dharaneesha
     * Method will create the new version question
     */
    public void createNewQuestionVersion(int dataIndex, int questionPosition) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String course = "";
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentName", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
            String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
            String course_name = ReadTestData.readDataByTagName("", "course_name", Integer.toString(dataIndex));
            String deActivateQuestion = ReadTestData.readDataByTagName("", "deActivateQuestion", Integer.toString(dataIndex));
            String test_type = ReadTestData.readDataByTagName("", "test_type", Integer.toString(dataIndex));
            String subSection_level = ReadTestData.readDataByTagName("", "subSection_level", Integer.toString(dataIndex));
            String chapterIndex = ReadTestData.readDataByTagName("", "chapterIndex", Integer.toString(dataIndex));
            String topicIndex = ReadTestData.readDataByTagName("", "topicIndex", Integer.toString(dataIndex));
            Questions questions = PageFactory.initElements(driver, Questions.class);

            course = course_name;
            System.out.println("course: " + course);
            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }

            new DirectLogin().CMSLogin();

            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
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
                if (subSection_level != null) {
                    new SelectCourse().expandChapterTreeByIndex(dataIndex, Integer.parseInt(chapterIndex));
                    new SelectCourse().selectTopicByIndex(dataIndex, Integer.parseInt(topicIndex));

                }
                driver.findElement(By.xpath("//div[@title='" + assessmentName + "']")).click(); //click on the test
                if (questionPosition != 1) {
                    new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Jump To Q#']")));
                    driver.findElement(By.xpath("//a[@title='Jump To Q#']")).click();
                    new UIElement().waitAndFindElement(By.linkText(Integer.toString(questionPosition)));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.linkText(Integer.toString(questionPosition))));
                    driver.findElement(By.linkText(Integer.toString(questionPosition))).click();

                }
                // new CMSActions().navigateToQuestionNo(Integer.parseInt(QuestionNo));
                new UIElement().waitAndFindElement(By.id("questionOptions"));
                new Click().clickByid("questionOptions"); //click on question option
                new Click().clickByid("questionRevisions"); // click on revisions
                String questionText = questions.getLabel_NewVersionQuestionText().getText();
                questions.getLink_createNewVersion().click();//Click on link 'Create New Version'
                new UIElement().waitAndFindElement(questions.getLabel_NewVersionQuestionText());
                questions.getLabel_NewVersionQuestionText().click();
                new UIElement().waitAndFindElement(questions.getLabel_NewVersionQuestionText());
                questions.getLabel_NewVersionQuestionText().sendKeys("V1");
                System.out.println("QuestionText 1 : " + questionText);
            }
        } catch (Exception e) {

            Assert.fail("Exception in create in Apphelper deActivateQuestion in class AssignmentCreate", e);
        }
    }


    public void assignFileBasedAssignmentToStudent(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
            String totalpoints = ReadTestData.readDataByTagName("", "totalpoints", Integer.toString(dataIndex));
            String questionBank = ReadTestData.readDataByTagName("", "questionBank", Integer.toString(dataIndex));
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));

            if (questionBank != null) {
                new Navigator().NavigateTo("Assignments");
                driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();

                new UIElement().waitAndFindElement(By.cssSelector("div[class='tab active']"));
                if (driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources")) {//Opening All Resources tab if not opened after clicking on New Assignment button

                    driver.findElement(By.cssSelector("span[title='All Resources']")).click();
                }
                currentAssignments.getUsePreCreatedAssignment_button().click();//click on Use Pre-created Assignment on Create New assignment pop-up
                new UIElement().waitAndFindElement(By.id("all-resource-search-textarea"));
                //Adding assignment to search
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            }

            String currentUrl = driver.getCurrentUrl();
            Thread.sleep(3000);
            if (currentUrl.contains("myAssignments")) {
                driver.findElement(By.id("ls-assign-now-assigment-btn")).click();
            } else {
                List<WebElement> assignThis = driver.findElements(By.cssSelector("span[class='assign-this']"));
                for(int a=0;a<assignThis.size();a++){
                    if(assignThis.get(a).isDisplayed()){
                        assignThis.get(a).click();
                        break;
                    }
                }
            }
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, true);
            Thread.sleep(2000);
            new UIElement().waitAndFindElement(By.id("due-time"));
            driver.findElement(By.id("due-time")).click();
            driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            driver.findElement(By.id("due-date")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            driver.findElement(By.linkText(duedate)).click();

            if (gradable != null) {
                if (gradable.equals("true")) {
                    driver.findElement(By.id("total-points")).sendKeys(totalpoints);  //enter Total Points
                }
            }
            if (accessibleafter != null) {
                driver.findElement(By.id("accessible-date")).click();
                driver.findElement(By.linkText(accessibleafter)).click();
            }

            new UIElement().waitAndFindElement(By.id("additional-notes"));
            driver.findElement(By.id("additional-notes")).clear();
            driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Assign']")));

        } catch (Exception e) {
            Assert.fail("Exception in  assignFileBasedAssignmentToStudent in AppHelper class Assignment", e);
        }
    }

    public void createFileBasedAssessment(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String course = "";
            String overrideDefaultLogin = ReadTestData.readDataByTagName("", "overrideDefaultLogin", Integer.toString(dataIndex));
            String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
            String course_name = ReadTestData.readDataByTagName("", "course_name", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            course = course_name;
            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String promptdetails = ReadTestData.readDataByTagName("", "promptdetails", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
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
            }

            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
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
                new UIElement().waitAndFindElement(By.cssSelector("div.create-practice"));
                driver.findElement(By.cssSelector("div.create-practice")).click();
                new Click().clickByclassname("create-file-assessment-popup-item");//click on the create file assessment
                WebDriverWait wait = new WebDriverWait(driver, 120);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='File Based Assessment']")));
                new Click().clickbyxpath("//a[@id='uploadFile']");
                new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
                driver.findElement(By.xpath("//*[@id='cms-file-assessment-popup-title']")).sendKeys(assessmentname); // give assessment name
                Thread.sleep(2000);
                new UIElement().waitAndFindElement(By.id("question-prompt-raw-content"));
                new TextSend().textsendbyid("" + promptdetails + "", "question-prompt-raw-content");

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ls-delete-file-icon']")));
                new Click().clickbyxpath("//span[@class='cms-file-assessment-popup-save']"); //click on save button

            }

        } catch (Exception e) {
            Assert.fail("Exception in method createFileBasedAssessment of appHelper Assignment", e);
        }
    }


    public void createFileBasedAssessmentAtInstructorSide(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));

            new Navigator().NavigateTo("Current Assignments");//click on dashboard
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            currentAssignments.createFileBasedAssignmentButton.click();//click on file based assignment
            newAssignment.assessmentNameTextBox.click();//click on the text box
            Thread.sleep(2000);
            newAssignment.assessmentName_TextBox.sendKeys(assessmentname);
            Thread.sleep(2000);
            driver.findElement(By.id("uploadFile")).click();
            Thread.sleep(3000);
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(2000);
            new UIElement().waitAndFindElement(By.className("ls-delete-file-icon"));
            driver.findElement(By.id("ls-ins-save-assigment-btn")).click(); //click on save for later
            Thread.sleep(1000);

        } catch (Exception e) {
            Assert.fail("Exception in method createFileBasedAssessmentAtInstructorSide of appHelper Assignment", e);
        }
    }


    /*
 * @Author Mukesh
 * Method will provide Grade From AssignmentResponsePage(Engagement Report)
 */
    public void provideGradeToStudentFromAssignmentResponsePage(int dataIndex) throws InterruptedException {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String chapterIndex = ReadTestData.readDataByTagName("", "chapterIndex", Integer.toString(dataIndex));
            String grade = ReadTestData.readDataByTagName("", "grade", Integer.toString(dataIndex));


            new Navigator().NavigateTo("Engagement Report");//navigate to current Assignment
            new UIElement().waitAndFindElement(By.xpath("(//div[@class='row-non-assigned-status']/div[2]/span)[2]"));
            driver.findElement(By.xpath("(//div[@class='row-non-assigned-status']/div[2]/span)[2]")).click(); //click on the assignment count
            new TopicOpen().chapterOpen(Integer.parseInt(chapterIndex)); //select Chapter from testData
            new SelectCourse().selectInvisibleAssignment(assessmentname); //select assessment from testData
            int gradeLinkIndex = 0;
            List<WebElement> menuitem = driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            for (WebElement user : menuitem) {
                new MouseHover().mouserhoverbywebelement(user);
                List<WebElement> gradenowlinks = driver.findElements(By.id("idb-grade-now-link"));
                new UIElement().waitAndFindElement(gradenowlinks.get(gradeLinkIndex));
                gradenowlinks.get(gradeLinkIndex).click();
                List<WebElement> allGradeBox = driver.findElements(By.cssSelector("input[class='idb-grade-points']"));
                for (WebElement gradeBox : allGradeBox) {
                    new UIElement().waitAndFindElement(gradeBox);
                    gradeBox.clear();
                    if (grade == null) {
                        gradeBox.sendKeys("0.7");
                        new UIElement().waitAndFindElement(gradeBox);
                        gradeBox.sendKeys(Keys.TAB);
                    } else {
                        new UIElement().waitAndFindElement(gradeBox);
                        gradeBox.sendKeys("" + grade + "" + Keys.TAB);
                    }


                }
                new UIElement().waitAndFindElement(By.xpath("/html/body"));
                driver.findElement(By.xpath("/html/body")).click();
            }
            gradeLinkIndex++;

        } catch (Exception e) {
            Assert.fail("Exception in apphelper provideGradeToStudentFromAssignmentResponsePage in class Assignment", e);
        }
    }

    public void assignFileBasedAssignmentFromMyQuestionBank(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
            String defaultDuetime = ReadTestData.readDataByTagName("", "defaultDuetime", Integer.toString(dataIndex));
            String totalpoints = ReadTestData.readDataByTagName("", "totalpoints", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));

            new Navigator().NavigateTo("My Question Bank");
            driver.findElement(By.cssSelector("span[class='assign-this']")).click();
            if (defaultDuetime != null) {
                if (defaultDuetime.equals("true")) {
                    new UIElement().waitAndFindElement(By.id("due-time"));
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                    Calendar now = Calendar.getInstance();
                    //add minutes to current date using Calendar.add method
                    now.add(Calendar.MINUTE, Integer.parseInt(duetime));
                    String calenderFormat = dateFormat.format(now.getTime());
                    System.out.println("calenderFormat:" + calenderFormat);

                    driver.findElement(By.id("due-time")).click();//click on dur time
                    driver.findElement(By.id("due-time")).sendKeys(calenderFormat);//click on dur time

                    driver.findElement(By.id("due-date")).click();//click on due date
                    Thread.sleep(5000);
                    List<WebElement> defaultsDate = driver.findElements(By.cssSelector("a[class='ui-state-default ui-state-highlight ui-state-hover']"));
                    for (WebElement defaultDate : defaultsDate) {
                        if (defaultDate.isDisplayed()) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", defaultDate);
                            break;
                        }
                    }
                }
            } else {

                new UIElement().waitAndFindElement(By.id("due-time"));
                driver.findElement(By.id("due-time")).click();
                driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
                driver.findElement(By.id("due-date")).click();
                driver.findElement(By.cssSelector("a[title='Next']")).click();
                new UIElement().waitAndFindElement(By.linkText(duedate));
                driver.findElement(By.linkText(duedate)).click();
            }
            if (gradable != null) {
                if (gradable.equals("true")) {
                    // ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));    //check gradable chekbox
                    driver.findElement(By.id("total-points")).sendKeys(totalpoints);  //enter Total Points
                }
            }

            if (gradable.equals("false")) {
                new UIElement().waitAndFindElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")));
            }

            if (accessibleafter != null) {
                new UIElement().waitAndFindElement(By.id("accessible-date"));
                driver.findElement(By.id("accessible-date")).click();
                driver.findElement(By.linkText(accessibleafter)).click();
            }

            new UIElement().waitAndFindElement(By.id("additional-notes"));
            //driver.findElement(By.id("additional-notes")).clear();
            ((JavascriptExecutor) driver).executeScript("document.getElementById('additional-notes').value='additionalnote'");
            // driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Assign']")));
            new UIElement().waitTillInvisibleElement(By.xpath("//span[text()='Assign']"));

        } catch (Exception e) {
            Assert.fail("Exception in  assignFileBasedAssignmentFromMyQuestionBank in AppHelper class Assignment", e);
        }
    }


    public void assignCustomAssignmentFromMyQuestionBank(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(dataIndex));
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
            String policyDescription = ReadTestData.readDataByTagName("", "policyDescription", Integer.toString(dataIndex));
            String scorePerQuestion = ReadTestData.readDataByTagName("", "scorePerQuestion", Integer.toString(dataIndex));
            String ordering = ReadTestData.readDataByTagName("", "ordering", Integer.toString(dataIndex));
            String immediateFeedBack = ReadTestData.readDataByTagName("", "immediateFeedBack", Integer.toString(dataIndex));
            String numberOfAttempt = ReadTestData.readDataByTagName("", "numberOfAttempt", Integer.toString(dataIndex));
            String showAnswerAtAttemptNumber = ReadTestData.readDataByTagName("", "showAnswerAtAttemptNumber", Integer.toString(dataIndex));
            String gradeReleaseOption = ReadTestData.readDataByTagName("", "gradeReleaseOption", Integer.toString(dataIndex));
            String showHintsAtAttemptNumber = ReadTestData.readDataByTagName("", "showHintsAtAttemptNumber", Integer.toString(dataIndex));
            String showReadingContentLinkAtAttemptNumber = ReadTestData.readDataByTagName("", "showReadingContentLinkAtAttemptNumber", Integer.toString(dataIndex));
            String showSolutionAtAttemptNumber = ReadTestData.readDataByTagName("", "showSolutionAtAttemptNumber", Integer.toString(dataIndex));
            String policyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(dataIndex));


            if (policyName == null) {
                System.out.println("Inside Policy Name");
                policyName = "ByDefault Policy Name";
            }

            if (policyDescription == null) {
                policyDescription = "Policy Description text";
            }
            if (scorePerQuestion == null) {
                scorePerQuestion = "1";
            }

            if (immediateFeedBack == null) {
                immediateFeedBack = "false";
            }


            if (numberOfAttempt == null) {
                numberOfAttempt = "1";
            }
            if (showAnswerAtAttemptNumber == null) {
                showAnswerAtAttemptNumber = "";
            }
            if (gradeReleaseOption == null) {
                gradeReleaseOption = "Release explicitly by the instructor";
            }
            if (showHintsAtAttemptNumber == null) {
                showHintsAtAttemptNumber = "";
            }
            if (showReadingContentLinkAtAttemptNumber == null) {
                showReadingContentLinkAtAttemptNumber = "";
            }
            if (showSolutionAtAttemptNumber == null) {
                showSolutionAtAttemptNumber = "";
            }

            new Navigator().NavigateTo("My Question Bank");
            driver.findElement(By.cssSelector("span[class='assign-this']")).click();
            new UIElement().waitAndFindElement(By.id("due-time"));
            driver.findElement(By.id("due-time")).click();
            driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            driver.findElement(By.id("due-date")).click();
            driver.findElement(By.cssSelector("a[title='Next']")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            driver.findElement(By.linkText(duedate)).click();

           /* if (gradeable.equals("true")) {
                new UIElement().waitAndFindElement(By.className("ir-ls-assign-dialog-gradable-label-check"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
            }*/
            if (gradeable.equals("false")) {
                new UIElement().waitAndFindElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")));
            }
            if (gradeable.equals("true") && assignmentpolicy != null) {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            }
            if (gradeable.equals("true")) {
                if (policyName.equals("ByDefault Policy Name")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                    new Click().clickbyxpath("//a[@rel='addNewAssignmentPolicy']");
                    new AssignmentPolicy().createAssignmentPolicyWhileAssigning(policyName, policyDescription, scorePerQuestion, ordering, Boolean.parseBoolean(immediateFeedBack), numberOfAttempt, showAnswerAtAttemptNumber, gradeReleaseOption, showHintsAtAttemptNumber, showReadingContentLinkAtAttemptNumber, showSolutionAtAttemptNumber);
                }

            }

            if (accessibleafter != null) {
                new UIElement().waitAndFindElement(By.id("accessible-date"));
                driver.findElement(By.id("accessible-date")).click();
                driver.findElement(By.linkText(accessibleafter)).click();
            }
            new UIElement().waitAndFindElement(By.id("additional-notes"));
            //driver.findElement(By.id("additional-notes")).clear();
            ((JavascriptExecutor) driver).executeScript("document.getElementById('additional-notes').value='additionalnote'");
            // driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Assign']")));
            new UIElement().waitTillInvisibleElement(By.xpath("//span[text()='Assign']"));
        } catch (Exception e) {
            Assert.fail("Exception in  assignFileBasedAssignmentFromMyQuestionBank in AppHelper class Assignment", e);
        }
    }


    public String createNewerVersionQuestion(int dataIndex, int questionPosition) {
        String questionId = null;
        WebDriver driver = Driver.getWebDriver();
        Assignments assignments = PageFactory.initElements(driver, Assignments.class);
        NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
        String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String status = ReadTestData.readDataByTagName("", "status", Integer.toString(dataIndex));

        try {
            OpenAssignment(assessmentName, dataIndex);
            new UIElement().waitAndFindElement(By.className("question-set-add-text"));
            if (!(questionPosition == 1)) {
                assignments.combBox_jumpToQ.click();
                new UIElement().waitAndFindElement(By.linkText("" + questionPosition));
                new Click().clickbylinkText("" + questionPosition);
                WebDriverWait wait = new WebDriverWait(driver, 60);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = '" + questionPosition + "']")));
            }
            questionId = assignments.label_questionID.getText();
            assignments.new_Link.click();
            new UIElement().waitAndFindElement(assignments.option_Revisions);
            assignments.option_Revisions.click();
            new UIElement().waitAndFindElement(assignments.link_CreateNewVersion);
            assignments.link_CreateNewVersion.click();
            new UIElement().waitAndFindElement(assignments.questionsEditBox);
            assignments.questionsEditBox.click();
            Thread.sleep(2000);
            assignments.questionsEditBox.sendKeys(" Updated");
            if (status == null) {

            } else {
                if (status.equalsIgnoreCase("Publish")) {
                    assignments.combBox_publish.click();
                    assignments.link_publish.click();
                }
            }

            newQuestionDataEntry.save_button.click();
        } catch (Exception e) {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in create in Apphelper createNewerVersionQuestion in class AssignmentCreate", e);
        }
        return questionId;
    }


    public void openRevisionSideBar(int dataIndex, int questionPosition) {
        WebDriver driver = Driver.getWebDriver();
        Assignments assignments = PageFactory.initElements(driver, Assignments.class);
        NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver, NewQuestionDataEntry.class);
        String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        try {
            OpenAssignment(assessmentName, dataIndex);
            new UIElement().waitAndFindElement(By.className("question-set-add-text"));
            Thread.sleep(2000);
            assignments.new_Link.click();
            Thread.sleep(2000);
            assignments.option_Revisions.click();
        } catch (Exception e) {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in create in Apphelper openRevisionSideBar in class Assignment", e);
        }
    }


    public void startStaticAssignmentFromeTextbook(int dataIndex, int questionPosition) {
        String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String chapterNameToSearch = ReadTestData.readDataByTagName("", "chapterNameToSearch", Integer.toString(dataIndex));
        WebDriver driver = Driver.getWebDriver();
        try {
            new Navigator().NavigateTo("e-Textbook");

            List<WebElement> allChapters = driver.findElements(By.xpath("//div[starts-with(@class,'chapter-heading')]"));
            for (int a = 0; a < allChapters.size(); a++) {
                if (allChapters.get(a).getText().trim().contains(chapterNameToSearch)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allChapters.get(a));
                    Thread.sleep(5000);
                    break;
                }
            }
            new UIElement().waitAndFindElement(By.xpath(".//*[@title='" + assessmentName + "']"));
            WebElement element = driver.findElement(By.xpath(".//*[@title='" + assessmentName + "']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@title='" + assessmentName + "']")));
        } catch (Exception e) {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in create in Apphelper openRevisionSideBar in class AssignmentCreate", e);
        }
    }


    public void searchAssessmentInQuestionBanks(int dataIndex) {
        String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        WebDriver driver = Driver.getWebDriver();
        try {
            new Click().clickByid("all-resource-search-textarea");//Click on search text box
            new TextSend().textsendbyid("\"" + assessmentName + "\"", "all-resource-search-textarea");//Type as assessment name which we would like to search
            new UIElement().waitAndFindElement(By.id("all-resource-search-button"));
            new Click().clickByid("all-resource-search-button");//Click on Search icon
        } catch (Exception e) {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in create in Apphelper searchAssessmentInQuestionBanks in class AssignmentCreate", e);
        }
    }


    public List<String> getMPQQuestionsStatus(int dataIndex) {
        List<String> statusList = new ArrayList<String>();
        WebDriver driver = Driver.getWebDriver();
        Assignments assignments = PageFactory.initElements(driver, Assignments.class);
        try {
            String[] countArray = assignments.questionsCount.getText().split("f");
            statusList.add(assignments.label_status.getText());
            String count[] = countArray[1].split("[)]");
            for (int a = 1; a < Integer.parseInt(count[0].trim()); a++) {
                Thread.sleep(1000);
                driver.findElement(By.className("next-question-part")).sendKeys(Keys.PAGE_UP);
                new UIElement().waitAndFindElement(assignments.questionsCount);
                assignments.questionsCount.click();
                new UIElement().waitAndFindElement(By.cssSelector("tr[qindex='" + (a + 1) + "']"));
                driver.findElement(By.cssSelector("tr[qindex='" + (a + 1) + "']")).click();
                if (!(a == 2)) {
                    new UIElement().waitAndFindElement(assignments.label_status);
                    Thread.sleep(1000);
                    statusList.add(assignments.label_status.getText());
                }
            }
        } catch (Exception e) {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in create in Apphelper getMPQQuestionStatus in class AssignmentCreate", e);
        }
        Collections.sort(statusList);
        return statusList;
    }


    public void switchToAssignmentResponsePage(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Current Assignments");//navigate to Assignments
            List<WebElement> assessmentsList = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            int pos = 0;
            for (int a = 0; a < assessmentsList.size(); a++) {
                if (assessmentsList.get(a).getAttribute("title").equals("(shor) " + assessmentName)) {
                    pos++;
                    break;
                }
                pos++;
            }
            driver.findElements(By.className("ls-grade-book-assessment")).get(pos - 1).click();
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper getMPQQuestionStatus in class AssignmentCreate", e);
        }

    }

    public void provideGradeAndFeedbackToStudentForMPQ(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String studentname = ReadTestData.readDataByTagName("", "studentname", Integer.toString(dataIndex));
            String marks = ReadTestData.readDataByTagName("", "marks", Integer.toString(dataIndex));
            String feedback = ReadTestData.readDataByTagName("", "feedback", Integer.toString(dataIndex));
            String y[] = studentname.split(" ");
            studentname = y[1] + ", " + y[0];//reverse the name with comma in between
            String grade = ReadTestData.readDataByTagName("", "grade", Integer.toString(dataIndex));
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);

            new Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> viewresponseslink = driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
            List<WebElement> menuitem = driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            for (WebElement user : menuitem) {
                if (user.getText().equals(studentname)) {
                    List<WebElement> mpqQuestionsDisabled = driver.findElements(By.cssSelector("span[class='idb-gradebook-question-content mpq-score-disabled']"));
                    for (int a = 0; a < mpqQuestionsDisabled.size(); a++) {
                        new MouseHover().mouserhoverbywebelement(mpqQuestionsDisabled.get(a));
                        new UIElement().waitAndFindElement(By.className("ls-view-response-link"));
                        new Click().clickByclassname("ls-view-response-link");
                        Thread.sleep(3000);
                        if (marks != null) {
                            List<WebElement> scoreElementsList = driver.findElements(By.className("multi-part-question-score"));
                            for (int b = 0; b < scoreElementsList.size(); b++) {
                                scoreElementsList.get(b).clear();
                                scoreElementsList.get(b).sendKeys(marks);
                            }
                            assignmentResponsesPage.getSaveButton().click();
                        }

                        for (int c = 0; c < assignmentResponsesPage.getTextArea_FeedbackList().size(); c++) {
                            assignmentResponsesPage.getTextArea_FeedbackList().get(c).clear();
                            assignmentResponsesPage.getTextArea_FeedbackList().get(c).sendKeys(feedback);
                        }
                        assignmentResponsesPage.getSaveButton().click();
                        new Click().clickBycssselector("span[id='close-view-responses']");

                    }

                }
            }

        } catch (Exception e) {
            Assert.fail("Exception in apphelper provideGradeToStudentForMultipleQuestions in class Assignment", e);
        }
    }


    public void selectInvisiblebottomAssignment(String assignmentname) {
        WebDriver driver = Driver.getWebDriver();
        try {
            new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@title='" + assignmentname + "']")));
            List<WebElement> elementsList = driver.findElements(By.xpath(".//*[@title='" + assignmentname + "']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementsList.get(elementsList.size() - 1));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementsList.get(elementsList.size() - 1));
        } catch (Exception e) {
            Assert.fail("Exception in method selectInvisiblebottomAssignment in class Assignment", e);
        }
    }

    public void openAssessmentInCMS(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String course = "";
            String overrideDefaultLogin = ReadTestData.readDataByTagName("", "overrideDefaultLogin", Integer.toString(dataIndex));
            String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
            String course_name = ReadTestData.readDataByTagName("", "course_name", Integer.toString(dataIndex));


            course = course_name;
            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));

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
                    if (driver.findElements(By.xpath("//div[@title='" + chapterName + "']")).size() > 1) {
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

            }
            new Assignment().selectInvisiblebottomAssignment(assessmentname);
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper create in class AssignmentCreate", e);
        }
    }


    public void publishChapter(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            List<WebElement> editElementsList = driver.findElements(By.xpath("//img[starts-with(@id,'tree-node-edit-icon-')]"));
            for (int a = 0; a < editElementsList.size(); a++) {
                if (editElementsList.get(a).isDisplayed()) {
                    editElementsList.get(a).click();
                    Thread.sleep(2000);
                    break;
                }
            }
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[normalize-space(@class)='cms-course-tree-edit-publish-content-wrapper cms-course-tree-edit-line-wrapper']//label");
            new Click().clickByid("cms-course-tree-edit-save-btn");
            new Click().clickBycssselector("span.cms-notification-message-ignore-changes.cms-notification-message-save-chater-details > span");
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper publishChapter in class AssignmentCreate", e);
        }
    }


    public void shareCustomAssignment(String shareName) {
        WebDriver driver = Driver.getWebDriver();
        try {
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            myQuestionBank.shareThis.click();//click on share this
            myQuestionBank.shareWithTextBox.click();//click on share text box
            Thread.sleep(2000);
            String y[] = shareName.split(" ");
            shareName = y[1] + ", " + y[0];//reverse the name with comma in between
            System.out.println("shareName:" + shareName);
            myQuestionBank.shareTextBox.sendKeys(shareName);
            List<WebElement> suggestname = driver.findElements(By.xpath("//ul[@id='share-with_feed']/li"));
            for (WebElement name : suggestname) {
                System.out.println("Names " + name.getText());
                if (name.getText().trim().equals(shareName)) {
                    Thread.sleep(2000);
                    //((JavascriptExecutor) driver).executeScript("arguments[0].click();", name);
                    name.click();
                    break;
                }
            }

            myQuestionBank.shareButton.click();//click on share button

        } catch (Exception e) {
            Assert.fail("Exception in app helper shareCustomAssignment in class Assignment", e);
        }
    }


    public void submitAssignmentFromAssignmentsAsStudent(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String submitassignment = ReadTestData.readDataByTagName("", "submitassignment", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String usehint = ReadTestData.readDataByTagName("", "usehint", Integer.toString(dataIndex));
            String answerchoice = ReadTestData.readDataByTagName("", "answerchoice", Integer.toString(dataIndex));
            new Navigator().NavigateTo("Assignments");
            int helppage = driver.findElements(By.className("close-help-page")).size();
            if (helppage == 1)
                driver.findElement(By.className("close-help-page")).click();
            new Click().clickBycssselector("span[title='(shor)  " + assessmentname + "']");//click on Assignment
            Thread.sleep(2000);
            boolean useHint = false;//by default useHint is set to false
            if (usehint != null)//read the tag from testdata
                useHint = true;//set useHint to true
            if (answerchoice == null)
                answerchoice = "correct";
            int timer = 1;
            while (timer != 0) {
                String questionText = (new WebDriverWait(driver, 50)).until(ExpectedConditions.presenceOfElementLocated(By.id("question-edit"))).getText();
                if (questionText.contains("True False"))
                    new AttemptQuestion().trueFalse(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Multiple Choice"))
                    new AttemptQuestion().multipleChoice(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Multi Selection"))
                    new AttemptQuestion().multipleSelection(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Text Entry"))
                    new AttemptQuestion().textEntry(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Text Drop Down"))
                    new AttemptQuestion().textDropDown(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Numeric Entry With Units"))
                    new AttemptQuestion().numericEntryWithUnits(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Advanced Numeric"))
                    new AttemptQuestion().advancedNumeric(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Expression Evaluator"))
                    new AttemptQuestion().expressionEvaluator(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Essay"))
                    new AttemptQuestion().essay(useHint, answerchoice, dataIndex);

                else if (questionText.contains("Write Board"))
                    new AttemptQuestion().writeBoard(useHint, answerchoice, dataIndex);
                boolean lastQuestionNotToSubmitAssignment = submitAnswer(submitassignment, dataIndex);
                if (lastQuestionNotToSubmitAssignment == true)
                    timer = 0;
                else {
                    try {
                        driver.findElement(By.cssSelector("span[id='timer-label']"));
                    } catch (Exception e) {
                        timer = 0;
                    }
                }
            }
        } catch (Exception e) {
            Assert.fail("Exception in app helper submitAssignmentFromAssignmentsAsStudent in class Assignment", e);
        }
    }

    public void attemptQuizWidgetWithMPQ() {
        try {
            WebDriver driver = Driver.getWebDriver();
            int mpQuestionExpand = driver.findElements(By.xpath(".//*[starts-with(@class, 'question-toggle-arrow-icon')]")).size();
            for (int i = 0; i < mpQuestionExpand; i++) {
                System.out.println("i::" + i);
                List<WebElement> mpQuestions = driver.findElements(By.xpath(".//*[starts-with(@class, 'question-toggle-arrow-icon')]"));
                mpQuestions.get(0).click();
                int trueFalse = driver.findElements(By.className("true-false-student-answer-label")).size();
                if (trueFalse > 0) {
                    List<WebElement> attemptTrue = driver.findElements(By.className("true-false-student-answer-label"));
                    attemptTrue.get(0).click();
                }
                int multipleChoiceQs = driver.findElements(By.cssSelector("div[class='single-select-choice-icon-preview single-select-choice-icon-deselect']")).size();
                if (multipleChoiceQs > 0) {
                    List<WebElement> multiChoices = driver.findElements(By.cssSelector("div[class='single-select-choice-icon-preview single-select-choice-icon-deselect']"));
                    multiChoices.get(0).click();
                }
                int nextQuestionPart = driver.findElements(By.className("next-question-part")).size();
                if (nextQuestionPart > 0) {
                    List<WebElement> attemptTrue = driver.findElements(By.className("next-question-part"));
                    attemptTrue.get(i).click();
                }
            }
            int submitAnswer = driver.findElements(By.className("ls-embedded-static-submit-button")).size();
            if (submitAnswer > 0) {
                new Click().clickByclassname("ls-embedded-static-submit-button");
            }
            boolean reTake = driver.findElement(By.className("assessment-widget-retake")).isDisplayed();
            if (reTake == false) {
                Assert.fail("Not able to Submit the Assessment, Retake is not present");
            }
        } catch (Exception e) {
            Assert.fail("Exception in app helper attemptQuizWidgetWithMPQ in class Assignment", e);
        }
    }


    public void selectParticularQuestionInCMS(String questionType, int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String reservforassignment = ReadTestData.readDataByTagName("", "reservforassignment", Integer.toString(dataIndex));
            String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            manageContent.createPractice.click();//click on create practice
            manageContent.createRegularAssessment.click();//click on regular assessment
            new UIElement().waitAndFindElement(By.cssSelector("a[selectedid='Adaptive Component Diagnostic']"));
            new Click().clickBycssselector("a[selectedid='Adaptive Component Diagnostic']");
            new Click().clickbylinkText(questiontype);
            driver.findElement(By.id("assessmentName")).click();
            driver.findElement(By.id("assessmentName")).clear();
            driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
            driver.findElement(By.id("questionSetName")).clear();
            driver.findElement(By.id("questionSetName")).sendKeys(questionset);
            if (reservforassignment == null) {
                driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
            }
            WebElement question = driver.findElement(By.xpath("//div[@title='" + questionType + "']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", question);
        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method selectParticularQuestionInCMS.", e);
        }
    }


    public void verifyFont(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            manageContent.questionTextBox.click();//click on question text box
            manageContent.questionTextBox.click();//click on question text box
            new UIElement().waitAndFindElement(manageContent.textEditor);
            Assert.assertEquals(manageContent.textEditor.getText(), "Text Editor", "A HTML editor is not displaying.");
            new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(manageContent.fontFamilyIcon.get(0)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", manageContent.fontFamilyIcon.get(0));
            Thread.sleep(2000);
            manageContent.questionTextBox.click();//click on question text box
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", manageContent.fontFamilyIcon.get(0));
            Assert.assertEquals(manageContent.fontFamilyList.get(0).isDisplayed(), true, "A list of Option is not displaying");
            Assert.assertEquals(manageContent.openSansFont.get(1).isDisplayed(), true, "default tick mark icon is not available on the left of opensans font.");
            manageContent.monoSpaceFont.get(1).click();//click on mono space font
            Assert.assertEquals(manageContent.activeMonoSpaceFont.get(0).isDisplayed(), true, "A tick mark icon is not showing before the font name.");
            driver.findElement(By.xpath("//div[@id='question-edit']/div/div[1]")).sendKeys(questiontext);//type the question
            /*String font = manageContent.questionTextBox.getCssValue("font-family");
            Assert.assertEquals(font,"monospace");*/

        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method verifyFont.", e);
        }

    }

    public void verifyFontOfDuplicateQuestion() {
        WebDriver driver = Driver.getWebDriver();
        try {
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            manageContent.preview_Button.click();//click on preview
            String parentWindow = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
           /* String font = manageContent.question_Text.getCssValue("font-family");
            Assert.assertEquals(font,"monospace");*/
            driver.close();
            driver.switchTo().window(parentWindow);
            Thread.sleep(1000);
            manageContent.newLink.click();//click on new link
            manageContent.duplicateLink.click();//click on duplicate
            Thread.sleep(2000);
            Assert.assertEquals(manageContent.questionLabel.getText(), "Q2:", "Duplicate of the same Question is not created.");
            /* String font = manageContent.question_Text.getCssValue("font-family");
            Assert.assertEquals(font,"monospace");*/
            manageContent.newLink.click();//click on new link
            manageContent.newLinkOption.click();//click on new link option
            Assert.assertEquals(manageContent.createQuestionText.getText(), "Create Question", "The user is not redirected to the Create Question page.");

        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method verifyFontOfDuplicateQuestion.", e);
        }

    }


    public void selectTextAndVerifyFont(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            manageContent.questionTextBox.click();//click on question text box
            manageContent.questionTextBox.click();//click on question text box
            new UIElement().waitAndFindElement(manageContent.textEditor);
            Assert.assertEquals(manageContent.textEditor.getText(), "Text Editor", "A HTML editor is not displaying.");
            driver.findElement(By.xpath("//div[@id='question-edit']/div/div[1]")).sendKeys(questiontext);//type the question
            Actions actions = new Actions(driver);
            WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            actions.moveToElement(element, 10, 30)
                    .clickAndHold()
                    .moveByOffset(500, 0)
                    .release()
                    .perform();
            manageContent.fontFamilyIcon.get(0).click();//click on font family icon
            Assert.assertEquals(manageContent.fontFamilyList.get(0).isDisplayed(), true, "A list of Option is not displaying");
            Assert.assertEquals(manageContent.openSansFont.get(1).isDisplayed(), true, "default tick mark icon is not available on the left of opensans font.");
            manageContent.monoSpaceFont.get(1).click();//click on mono space font
            Assert.assertEquals(manageContent.activeMonoSpaceFont.get(0).isDisplayed(), true, "A tick mark icon is not showing before the font name.");
            String font = manageContent.questionContent.getCssValue("font-family");
            Assert.assertEquals(font, "monospace", "The font of selected text is not changed in monospace Font.");

        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method selectTextAndVerifyFont.", e);
        }

    }


    public void verifyFontOfPreviewPage() {
        WebDriver driver = Driver.getWebDriver();
        try {
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            manageContent.preview_Button.click();//click on preview
            String parentWindow = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
            String font = manageContent.question_Text.getCssValue("font-family");
            Assert.assertEquals(font, "monospace", "The text is not displaying in the monospace font.");
            driver.close();
            driver.switchTo().window(parentWindow);
            Assert.assertEquals(manageContent.questionLabel.getText(), "Q1:", "Preview window is not closed");

        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method verifyFontOfDuplicateQuestion.", e);
        }

    }

    public void createExpressionEvaluatorQuestion(int dataIndex, String correctAnswer) {
        WebDriver driver = Driver.getWebDriver();
        try {

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            System.out.println("dataIndex::" + dataIndex);

            System.out.println("assessmentname::" + assessmentname);
            System.out.println("questionset::" + questionset);
            System.out.println("questiontext::" + questiontext);

            new DirectLogin().CMSLogin(); // Login as author
            new Navigator().createQuestionPage(dataIndex);
            new Click().clickByid("assessmentName");
            driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
            new Click().clickByid("questionSetName");
            driver.findElement(By.id("questionSetName")).sendKeys(questionset);
            new Click().clickbyxpath(".//*[@id = 'practice-set-parameter-selectbox-wrapper']/div");
            new Click().clickbyxpath(".//*[@title=' Static Practice ']");
            new Click().clickByid("qtn-expression-evaluator-img");
            new Click().clickByid("question-raw-content");
            new Click().clickByid("question-raw-content");
            driver.findElement(By.id("question-raw-content")).sendKeys(questiontext);
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            new Click().clickbyxpath(".//*[@title='Square root']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys(correctAnswer);
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']");

            new Click().clickByid("saveQuestionDetails1");
        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method createExpressionEvaluatorQuestion.", e);
        }
    }

    public void createExpressionEvaluatorQuestionValue(int dataIndex, String correctAnswer) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            System.out.println("dataIndex::" + dataIndex);

            System.out.println("assessmentname::" + assessmentname);
            System.out.println("questionset::" + questionset);
            System.out.println("questiontext::" + questiontext);

            new DirectLogin().CMSLogin(); // Login as author
            new Navigator().createQuestionPage(dataIndex);
            new Click().clickByid("assessmentName");
            driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
            new Click().clickByid("questionSetName");
            driver.findElement(By.id("questionSetName")).sendKeys(questionset);
            new Click().clickbyxpath(".//*[@id = 'practice-set-parameter-selectbox-wrapper']/div");
            new Click().clickbyxpath(".//*[@title=' Static Practice ']");
            new Click().clickByid("qtn-expression-evaluator-img");
            new Click().clickByid("question-raw-content");
            new Click().clickByid("question-raw-content");
            driver.findElement(By.id("question-raw-content")).sendKeys(questiontext);
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            driver.findElement(By.className("wrs_focusElement")).sendKeys(correctAnswer);
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']");

            new Click().clickByid("saveQuestionDetails1");
        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method createExpressionEvaluatorQuestionValue.", e);
        }
    }

    public void createMPQQs(int dataIndex, String questionType, String questionNumber, String correctAnswer, boolean publish) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));

            new DirectLogin().CMSLogin(); // Login as author
            new Navigator().createQuestionPage(dataIndex);
            new Click().clickByid("assessmentName");
            driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
            new Click().clickByid("questionSetName");
            driver.findElement(By.id("questionSetName")).sendKeys(questionset);
            new Click().clickbyxpath(".//*[@id = 'practice-set-parameter-selectbox-wrapper']/div");
            new Click().clickbyxpath(".//*[@title=' Static Practice ']");
            new Click().clickByid("qtn-multi-part");
            Thread.sleep(2000);
            new Click().clickByid("question-mp-raw-content-0");
            driver.findElement(By.id("question-mp-raw-content-0")).sendKeys(questiontext);
            new Click().clickByid("saveQuestionDetails1");
            new Click().clickByclassname("cms-multipart-add-question-part");
            if (questionType.equals("textEntry")) {
                driver.findElement(By.id("qtn-text-entry-img")).click();
                driver.findElement(By.id("mpq-question-part-input-label")).click();
                driver.findElement(By.id("mpq-question-part-input-label")).sendKeys(questionNumber);
                new Click().clickByid("question-raw-content");
                new Click().clickByid("question-raw-content");
                driver.findElement(By.id("question-raw-content")).sendKeys(questiontext);
                driver.findElement(By.cssSelector("input[class='numeric_text_entry_input get-user-entry is-value-changed-txt-entry']")).click();
                driver.findElement(By.cssSelector("input[class='numeric_text_entry_input get-user-entry is-value-changed-txt-entry']")).sendKeys(correctAnswer);
                driver.findElement(By.cssSelector("span[class='btn sty-green save-language-text accept_answer text_entry_accept_answer']")).click();
                Thread.sleep(1000);
                if (selectLanguagePalette != null) {
                    driver.findElements(By.cssSelector("a[title='Select Language Palette']")).get(0).click();
                    Thread.sleep(1000);
                    if (selectLanguagePalette.equalsIgnoreCase("Spanish")) {
                        driver.findElement(By.cssSelector("a[title='Spanish']")).click();
                    }
                    if (selectLanguagePalette.equalsIgnoreCase("Italian")) {
                        driver.findElement(By.cssSelector("a[title='Italian']")).click();
                    }
                    if (selectLanguagePalette.equalsIgnoreCase("French")) {
                        driver.findElement(By.cssSelector("a[title='French']")).click();
                    }
                }
                driver.findElement(By.id("saveQuestionDetails1")).click();
                Thread.sleep(2000);
                driver.findElement(By.id("cms-mpq-back-button")).click();
                Thread.sleep(1000);
                if (publish == true) {
                    new Click().clickBycssselector("a[title='Draft']");
                    new Click().clickBycssselector("a[title='Publish']");
                }
                driver.findElement(By.id("saveQuestionDetails1")).click();
            }
        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method createMPQQs.", e);
        }
    }

    public void addMPQQs(int dataIndex, String questionType, String questionNumber, String correctAnswer, boolean publish) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            String course = "";
            String course_name = ReadTestData.readDataByTagName("", "course_name", Integer.toString(dataIndex));
            String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
            course = course_name;


            new DirectLogin().CMSLogin(); // Login as author

            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }
            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
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

            List<WebElement> listOfAssessments = driver.findElements(By.className("collection-assessment-name"));
            for (int r = 0; r < listOfAssessments.size(); r++) {
                String nameOfAssessment = listOfAssessments.get(r).getAttribute("title");
                if (nameOfAssessment.trim().equals(assessmentname)) {
                    listOfAssessments.get(r).click();
                }
            }
            Thread.sleep(2000);
            driver.findElement(By.id("questionOptions")).click();
            driver.findElement(By.id("addQuestion")).click();
            Thread.sleep(2000);
            new Click().clickByid("qtn-multi-part");
            Thread.sleep(2000);
            new Click().clickByid("question-mp-raw-content-0");
            driver.findElement(By.id("question-mp-raw-content-0")).sendKeys(questiontext);
            new Click().clickByid("saveQuestionDetails1");
            new Click().clickByclassname("cms-multipart-add-question-part");
            if (questionType.equalsIgnoreCase("essay")) {
                new Click().clickByid("qtn-essay-type");
                Thread.sleep(1000);
                driver.findElement(By.id("mpq-question-part-input-label")).click();
                driver.findElement(By.id("mpq-question-part-input-label")).sendKeys(questionNumber);
                new Click().clickByid("question-raw-content");
                new Click().clickByid("question-raw-content");
                driver.findElement(By.id("question-raw-content")).sendKeys(questiontext);
                driver.findElement(By.id("essay-question-height")).click();
                driver.findElement(By.id("essay-question-height")).sendKeys("5");
                driver.findElement(By.id("saveQuestionDetails1")).click();
                Thread.sleep(1000);
            }
            if (questionType.equalsIgnoreCase("textEntry")) {
                driver.findElement(By.id("qtn-text-entry-img")).click();
                driver.findElement(By.id("mpq-question-part-input-label")).click();
                driver.findElement(By.id("mpq-question-part-input-label")).sendKeys(questionNumber);
                new Click().clickByid("question-raw-content");
                new Click().clickByid("question-raw-content");
                driver.findElement(By.id("question-raw-content")).sendKeys(questiontext);
                driver.findElement(By.cssSelector("input[class='numeric_text_entry_input get-user-entry is-value-changed-txt-entry']")).click();
                driver.findElement(By.cssSelector("input[class='numeric_text_entry_input get-user-entry is-value-changed-txt-entry']")).sendKeys(correctAnswer);
                driver.findElement(By.cssSelector("span[class='btn sty-green save-language-text accept_answer text_entry_accept_answer']")).click();
                Thread.sleep(1000);
                if (selectLanguagePalette != null) {
                    driver.findElements(By.cssSelector("a[title='Select Language Palette']")).get(0).click();
                    Thread.sleep(1000);
                    if (selectLanguagePalette.equalsIgnoreCase("Spanish")) {
                        driver.findElement(By.cssSelector("a[title='Spanish']")).click();
                    }
                    if (selectLanguagePalette.equalsIgnoreCase("Italian")) {
                        driver.findElement(By.cssSelector("a[title='Italian']")).click();
                    }
                    if (selectLanguagePalette.equalsIgnoreCase("French")) {
                        driver.findElement(By.cssSelector("a[title='French']")).click();
                    }
                }
            }
            driver.findElement(By.id("cms-mpq-back-button")).click();
            Thread.sleep(1000);
            if (publish == true) {
                new Click().clickBycssselector("a[title='Draft']");
                new Click().clickBycssselector("a[title='Publish']");
                driver.findElement(By.id("saveQuestionDetails1")).click();
            }
        } catch (Exception e) {
            Assert.fail("Exception in app helper Assignment in method createMPQQs.", e);
        }
    }


    /**
     * This method filters the assignment in 'Current Assignments page' based on the parameters supplied in
     *
     * @param chapterNumber    - Chapter Indexes from which we would like to filter the assignments
     * @param section          - Partial text of All Sections dropdown from which we would like to filter the assignments
     * @param assignmentStatus - All Assignment Status such as Reviewed, Graded & so on
     * @param activity         - All Activity such as Question Assignment, Question Practice & so on
     */
    public void filterAssignmentsInCurrentAssignmentsPage(int chapterNumber, String section, String assignmentStatus, String activity) {
        WebDriver driver = Driver.getWebDriver();
        try {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            //Filter the assignments based on chapters & sections
            if (chapterNumber != 0) {
                currentAssignments.linkText_allChapters.click();
                List<WebElement> allAssignmentStatusDropdownOptionsList = driver.findElements(By.xpath("//ul[@class='sbOptions']//a[@title = 'All Chapters']//../..//a"));
                allAssignmentStatusDropdownOptionsList.get(chapterNumber + 1).click();

                if (!section.equals("")) {
                    currentAssignments.linkText_allSections.click();
                    driver.findElement(By.partialLinkText(section)).click();
                }
            }

            //Filter the assignments based on Assignment Status
            if (!assignmentStatus.equals("")) {
                currentAssignments.dropDown_allAssignmentStatus.click();
                driver.findElement(By.linkText(assignmentStatus)).click();
            }

            //Filter the assignments based on  activity
            if (!activity.equals("")) {
                currentAssignments.allActivity_link.click();
                driver.findElement(By.linkText(activity)).click();
            }
        } catch (Exception e) {
            Assert.fail("Exception in method 'filterAssignmentsInCurrentAssignmentsPage' in class 'Current Assignment'", e);
        }
    }


    public void extendedDueDate(String dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            String newduedate = ReadTestData.readDataByTagName("", "newduedate", dataIndex);
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            System.out.println("duetime:" + duetime);
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            if (assessmentname != null) {
                driver.findElement(By.linkText("All Assignments")).click();
                new UIElement().waitAndFindElement(By.linkText("Question Practice"));
                if (gradeable == null)
                    driver.findElement(By.linkText("Question Practice")).click();
                else if (gradeable.equals("false"))
                    driver.findElement(By.linkText("Question Practice")).click();
                else
                    driver.findElement(By.linkText("Question Assignment")).click();

                new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));

                List<WebElement> assignmentNames = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
                int index = 0;
                for (int i = 0; i < assignmentNames.size(); i++) {
                    if (assignmentNames.get(i).getText().equals(assessmentname)) {
                        break;
                    }
                    index++;
                }
                currentAssignments.viewStudentResponses.get(index).click(); //click on view student responses
            } else {
                currentAssignments.getViewGrade_link().click();//click on Update Assignment at first index
            }
            assignmentResponsesPage.extendDueDateLabel.click();//click on extend due date
            new ShareWith().shareIn(shareWithInitialString, shareName, shareWithClass, context_title, true);  //share with class/student
            DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            Calendar now = Calendar.getInstance();
            //add minutes to current date using Calendar.add method
            now.add(Calendar.MINUTE, Integer.parseInt(duetime));
            String calenderFormat = dateFormat.format(now.getTime());
            System.out.println("calenderFormat:" + calenderFormat);

            // driver.findElement(By.id("extended-due-time")).click();//click on dur time
            driver.findElement(By.id("extended-due-time")).clear();
            driver.findElement(By.id("extended-due-time")).sendKeys(calenderFormat);//click on dur time

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", assignmentResponsesPage.updateExtendDueDate);//click on Update Assignment link
            Thread.sleep(1000);

        } catch (Exception e) {
            Assert.fail("Exception in app helper extendDueDate in class Assignment", e);
        }

    }


    public void createTlo(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            new DirectLogin().CMSLogin();
            driver.findElement(By.xpath("//*[@class='content-dropdown']/div/a")).click(); //click on the content dropdown
            driver.findElement(By.xpath("//a[@title='Taxonomy Management']")).click(); //click on the Taxonomy Management
            Thread.sleep(5000);
            new SelectCourse().selectcourse();
            new ScrollElement().scrollBottomOfPage();
            driver.findElement(By.xpath("//div[@type='terminal-learning-objective']")).click(); //click on the terminal learning objective
            String randomText = StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA);
            driver.findElement(By.xpath("//input[@class='terminal-skill-new']")).sendKeys(randomText);
            driver.findElement(By.xpath("/html/body")).click();
            driver.findElement(By.xpath("//*[@class='content-dropdown']/div/a")).click(); //click on the content dropdown
            driver.findElement(By.xpath("//a[@title='Course Management']")).click(); //click on the Taxonomy Management
        } catch (Exception e) {
            Assert.fail("Exception in method createTlo of class Assignment", e);
        }
    }

    public void publishchapter(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select a course
            Thread.sleep(2000);
            courseOutline.courseOutline.click();
            new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@title,'Add New Chapter')]")));
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
            courseOutline.saveMyChanges.click(); //click on save my changes
        } catch (Exception e) {
            Assert.fail("Exception in method publishChapter of class Assignment", e);
        }
    }

    public void clickOnCreateRegularPractice(int dataIndex) {

        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            new SelectCourse().selectChapterByIndex(0);
            driver.findElement(By.cssSelector("div.create-practice")).click();
            new Click().clickByclassname("create-regular-assessment-popup-item"); //click on the create regular assessment
            new UIElement().waitAndFindElement(By.cssSelector("a[selectedid='Adaptive Component Diagnostic']"));
            new Click().clickBycssselector("a[selectedid='Adaptive Component Diagnostic']");
            new Click().clickbylinkText(questiontype);
            driver.findElement(By.id("assessmentName")).click();
            driver.findElement(By.id("assessmentName")).clear();
            driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
            driver.findElement(By.id("questionSetName")).clear();
            driver.findElement(By.id("questionSetName")).sendKeys(questionset);

        } catch (Exception e) {
            Assert.fail("Exception in method clickOnCreateRegularPractice of class assignment", e);
        }

    }

    /**
     * This function assign assignment with and without default due date to particular group
     *
     * @param dataIndex(testdata Index)
     * @author Mukesh
     */
    public void assignAssignmentToGroup(int dataIndex) throws InterruptedException {
        WebDriver driver = Driver.getWebDriver();
        MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
        CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);


        try {
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String studentName = ReadTestData.readDataByTagName("", "studentName", Integer.toString(dataIndex));
            String secondGroupName = ReadTestData.readDataByTagName("", "shareWithInitialString1", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String gradeBookWeighting = ReadTestData.readDataByTagName("", "gradeBookWeighting", Integer.toString(dataIndex));
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
            String policyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(dataIndex));
            String policyDescription = ReadTestData.readDataByTagName("", "policyDescription", Integer.toString(dataIndex));
            String scorePerQuestion = ReadTestData.readDataByTagName("", "scorePerQuestion", Integer.toString(dataIndex));
            String ordering = ReadTestData.readDataByTagName("", "ordering", Integer.toString(dataIndex));
            String immediateFeedBack = ReadTestData.readDataByTagName("", "immediateFeedBack", Integer.toString(dataIndex));
            String numberOfAttempt = ReadTestData.readDataByTagName("", "numberOfAttempt", Integer.toString(dataIndex));
            String showAnswerAtAttemptNumber = ReadTestData.readDataByTagName("", "showAnswerAtAttemptNumber", Integer.toString(dataIndex));
            String gradeReleaseOption = ReadTestData.readDataByTagName("", "gradeReleaseOption", Integer.toString(dataIndex));
            String showHintsAtAttemptNumber = ReadTestData.readDataByTagName("", "showHintsAtAttemptNumber", Integer.toString(dataIndex));
            String showReadingContentLinkAtAttemptNumber = ReadTestData.readDataByTagName("", "showReadingContentLinkAtAttemptNumber", Integer.toString(dataIndex));
            String showSolutionAtAttemptNumber = ReadTestData.readDataByTagName("", "showSolutionAtAttemptNumber", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String defaultDuetime = ReadTestData.readDataByTagName("", "defaultDuetime", Integer.toString(dataIndex));
            String discussionAssignment = ReadTestData.readDataByTagName("", "discussionAssignment", Integer.toString(dataIndex));
            String assignToParticularAssignmet = ReadTestData.readDataByTagName("", "assignToParticularAssignmet", Integer.toString(dataIndex));

            System.out.println("policyName:" + policyName);

            if (policyName == null) {
                System.out.println("Inside Policy Name");
                policyName = "ByDefault Policy Name";
            }

            if (policyDescription == null) {
                policyDescription = "Policy Description text";
            }
            if (scorePerQuestion == null) {
                scorePerQuestion = "1";
            }

            if (immediateFeedBack == null) {
                immediateFeedBack = "false";
            }

            if (numberOfAttempt == null) {
                numberOfAttempt = "1";
            }
            if (showAnswerAtAttemptNumber == null) {
                showAnswerAtAttemptNumber = "";
            }
            if (gradeReleaseOption == null) {
                gradeReleaseOption = "Release explicitly by the instructor";
            }
            if (showHintsAtAttemptNumber == null) {
                showHintsAtAttemptNumber = "";
            }
            if (showReadingContentLinkAtAttemptNumber == null) {
                showReadingContentLinkAtAttemptNumber = "";
            }
            if (showSolutionAtAttemptNumber == null) {
                showSolutionAtAttemptNumber = "";
            }


            new Navigator().NavigateTo("Assignments");
            driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();
            new UIElement().waitAndFindElement(By.cssSelector("div[class='tab active']"));
            if (driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources")) {//Opening All Resources tab if not opened after clicking on New Assignment button
                driver.findElement(By.cssSelector("span[title='All Resources']")).click();
            }
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getUsePreCreatedAssignment_button());//click on Use Pre-created Assignment on Create New assignment pop-up

            if (assignToParticularAssignmet != null) {
                new UIElement().waitAndFindElement(By.id("all-resource-search-textarea"));
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignToParticularAssignmet + "\"");

            }

            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='assign-this']"));
            Thread.sleep(5000);
            driver.findElement(By.cssSelector("span[class='assign-this']")).click();

            if (context_title != null && shareWithClass.equals("false")) {
                List<WebElement> allClassSection = driver.findElements(By.cssSelector("li[class='bit-box']"));
                for (WebElement classSection : allClassSection) {
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElements(By.className("closebutton")).get(driver.findElements(By.className("closebutton")).size() - 1));//click on close symbol
                    String assignToField = new TextFetch().textfetchbyclass("holder");
                    if (assignToField.length() == 0) {
                        break;
                    }
                }
            }


            if (shareWithInitialString != null) {
                driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
                Thread.sleep(2000);
                driver.findElement(By.xpath("//ul[@id = 'share-with_feed']//li")).click();
            }

            if (secondGroupName != null) {
                driver.findElement(By.className("maininput")).sendKeys(secondGroupName);
                Thread.sleep(2000);
                driver.findElement(By.xpath("//ul[@id = 'share-with_feed']//li")).click();
            }

            if (studentName != null) {
                driver.findElement(By.className("maininput")).sendKeys(studentName);
                Thread.sleep(2000);
                driver.findElement(By.xpath("//ul[@id = 'share-with_feed']//li")).click();
            }

            if (defaultDuetime != null) {
                if (defaultDuetime.equals("true")) {
                    new UIElement().waitAndFindElement(By.id("due-time"));
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                    Calendar now = Calendar.getInstance();
                    //add minutes to current date using Calendar.add method
                    now.add(Calendar.MINUTE, Integer.parseInt(duetime));
                    String calenderFormat = dateFormat.format(now.getTime());
                    System.out.println("calenderFormat:" + calenderFormat);

                    driver.findElement(By.id("due-time")).click();//click on dur time
                    driver.findElement(By.id("due-time")).sendKeys(calenderFormat);//click on dur time

                    driver.findElement(By.id("due-date")).click();//click on due date
                    Thread.sleep(5000);
                    List<WebElement> defaultsDate = driver.findElements(By.cssSelector("a[class='ui-state-default ui-state-highlight ui-state-hover']"));
                    for (WebElement defaultDate : defaultsDate) {
                        if (defaultDate.isDisplayed()) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", defaultDate);
                            break;
                        }
                    }
                }
            } else {
                new UIElement().waitAndFindElement(By.id("due-time"));
                driver.findElement(By.id("due-time")).click();
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[text()='07:30 PM']")));

                driver.findElement(By.id("due-date")).click();
                new UIElement().waitAndFindElement(By.linkText(duedate));
                driver.findElement(By.cssSelector("a[title='Next']")).click();
                Thread.sleep(2000);
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.linkText(duedate)));


            }

            if (gradeable != null) {
                if (gradeable.equals("false")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-dw-gradable-label-check selected']")));
                }
                if (gradeable.equals("true") && assignmentpolicy != null) {

                    //click on  Choose your assignment policy dropdown
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                    new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                    driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
                }
                if (gradeable.equals("true")) {
                    if (policyName.equals("ByDefault Policy Name")) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                        new Click().clickbyxpath("//a[@rel='addNewAssignmentPolicy']");
                        new AssignmentPolicy().createAssignmentPolicyWhileAssigning(policyName, policyDescription, scorePerQuestion, ordering, Boolean.parseBoolean(immediateFeedBack), numberOfAttempt, showAnswerAtAttemptNumber, gradeReleaseOption, showHintsAtAttemptNumber, showReadingContentLinkAtAttemptNumber, showSolutionAtAttemptNumber);
                    }

                }
            }

            if (accessibleafter != null) {
                driver.findElement(By.id("accessible-date")).click();
                driver.findElement(By.xpath("//a[@title='Next']")).click();
                driver.findElement(By.linkText(accessibleafter)).click();
            }

            new UIElement().waitAndFindElement(By.id("additional-notes"));
            driver.findElement(By.id("additional-notes")).clear();
            driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);

            if (gradeBookWeighting != null) {
                if (gradeBookWeighting.equals("true")) {
                    new Click().clickbylinkText("Uncategorized");
                    new UIElement().waitAndFindElement(By.linkText("No Assignment Category"));
                    new Click().clickbylinkText("Practice");
                }
            }

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")));
            new UIElement().waitTillInvisibleElement(By.cssSelector("span[class='btn sty-green submit-assign']"));

        } catch (Exception e) {
            Assert.fail("Exception in  assignAssignmentToGroup in AppHelper class Assignment", e);
        }
    }

    /**
     * This function assign discussion assignment with and without default due date to particular group
     *
     * @param dataIndex(testdata Index)
     * @author Mukesh
     */
    public void assignDiscussionAssignmentToGroup(int dataIndex) throws InterruptedException {
        WebDriver driver = Driver.getWebDriver();
        MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
        CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);


        try {
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String studentName = ReadTestData.readDataByTagName("", "studentName", Integer.toString(dataIndex));
            String secondGroupName = ReadTestData.readDataByTagName("", "shareWithInitialString1", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String gradeBookWeighting = ReadTestData.readDataByTagName("", "gradeBookWeighting", Integer.toString(dataIndex));
            String defaultDuetime = ReadTestData.readDataByTagName("", "defaultDuetime", Integer.toString(dataIndex));
            String totalPoints = ReadTestData.readDataByTagName("", "totalPoints", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));

            new Navigator().NavigateTo("My Assignments");

            new UIElement().waitAndFindElement(By.cssSelector("span[class='assign-this']"));
            Thread.sleep(5000);
            driver.findElement(By.cssSelector("span[class='assign-this']")).click();

            if (context_title != null && shareWithClass.equals("false")) {
                List<WebElement> allClassSection = driver.findElements(By.cssSelector("li[class='bit-box']"));
                for (WebElement classSection : allClassSection) {
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElements(By.className("closebutton")).get(driver.findElements(By.className("closebutton")).size() - 1));//click on close symbol
                    String assignToField = new TextFetch().textfetchbyclass("holder");
                    if (assignToField.length() == 0) {
                        break;
                    }
                }
            }


            if (shareWithInitialString != null) {
                driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
                Thread.sleep(2000);
                driver.findElement(By.xpath("//ul[@id = 'share-with_feed']//li")).click();
            }

            if (secondGroupName != null) {
                driver.findElement(By.className("maininput")).sendKeys(secondGroupName);
                Thread.sleep(2000);
                driver.findElement(By.xpath("//ul[@id = 'share-with_feed']//li")).click();
            }

            if (studentName != null) {
                driver.findElement(By.className("maininput")).sendKeys(studentName);
                Thread.sleep(2000);
                driver.findElement(By.xpath("//ul[@id = 'share-with_feed']//li")).click();
            }

            if (defaultDuetime != null) {
                if (defaultDuetime.equals("true")) {
                    new UIElement().waitAndFindElement(By.id("due-time"));
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                    Calendar now = Calendar.getInstance();
                    //add minutes to current date using Calendar.add method
                    now.add(Calendar.MINUTE, Integer.parseInt(duetime));
                    String calenderFormat = dateFormat.format(now.getTime());
                    System.out.println("calenderFormat:" + calenderFormat);

                    driver.findElement(By.id("due-time")).click();//click on dur time
                    driver.findElement(By.id("due-time")).sendKeys(calenderFormat);//click on dur time

                    driver.findElement(By.id("due-date")).click();//click on due date
                    Thread.sleep(5000);
                    List<WebElement> defaultsDate = driver.findElements(By.cssSelector("a[class='ui-state-default ui-state-highlight ui-state-hover']"));
                    for (WebElement defaultDate : defaultsDate) {
                        if (defaultDate.isDisplayed()) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", defaultDate);
                            break;
                        }
                    }
                }
            } else {
                new UIElement().waitAndFindElement(By.id("due-time"));
                driver.findElement(By.id("due-time")).click();
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[text()='07:30 PM']")));

                driver.findElement(By.id("due-date")).click();
                new UIElement().waitAndFindElement(By.linkText(duedate));
                driver.findElement(By.cssSelector("a[title='Next']")).click();
                Thread.sleep(2000);
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.linkText(duedate)));

            }
            if (gradeable != null) {
                if (gradeable.equals("false")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-dw-gradable-label-check selected']")));
                }


                if (accessibleafter != null) {
                    driver.findElement(By.id("accessible-date")).click();
                    driver.findElement(By.xpath("//a[@title='Next']")).click();
                    driver.findElement(By.linkText(accessibleafter)).click();
                }

                if (gradeable != null) {
                    if (gradeable.equals("true")) {
                        if (totalPoints != null) {
                            driver.findElement(By.id("total-points")).sendKeys(totalPoints);
                        }
                    }
                }
                new UIElement().waitAndFindElement(By.id("additional-notes"));
                driver.findElement(By.id("additional-notes")).clear();
                driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);

                if (gradeBookWeighting != null) {
                    if (gradeBookWeighting.equals("true")) {
                        new Click().clickbylinkText("Uncategorized");
                        new UIElement().waitAndFindElement(By.linkText("No Assignment Category"));
                        new Click().clickbylinkText("Practice");
                    }
                }

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")));
                new UIElement().waitTillInvisibleElement(By.cssSelector("span[class='btn sty-green submit-assign']"));

            }
        } catch (Exception e) {
            Assert.fail("Exception in  assignAssignmentToGroup in AppHelper class Assignment", e);
        }
    }

    /**
     * This function create discussion assignment
     *
     * @param dataIndex(testdata Index)
     * @author Mukesh
     */
    public void createDiscussionAssignment(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String discussionAssignment = ReadTestData.readDataByTagName("", "discussionAssignment", Integer.toString(dataIndex));
            String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
            new Navigator().NavigateTo("New Assignment");//Navigate to new assignment
            newAssignment.discussionAssignment.click();//click on discussion assignment

            newAssignment.assessmentNameTextBox.click();//click on Name field
            newAssignment.assessmentName_TextBox.sendKeys(discussionAssignment);//enter name of assignment
            driver.findElement(By.cssSelector("body")).click();//click out side
            Thread.sleep(4000);

            try {
                WebDriverUtil.clickOnElementUsingJavascript(newAssignment.questionContent);//click on question for discussion
            } catch (Exception e) {
                newAssignment.questionContent.click();
            }
            Thread.sleep(3000);
            driver.switchTo().activeElement().sendKeys(description);
            newAssignment.saveForLater_Button.click();
            CustomAssert.assertEquals(newAssignment.notification_message.getText().trim(), "Saved discussion question successfully.", "", "Discussion Assignment created", "Discussion Assignment not created");
        } catch (Exception e) {
            Assert.fail("Exception in  createDiscussionAssignment in AppHelper class Assignment", e);

        }
    }


    public void editDiscussionAssignment(int dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            String discussionAssignment = ReadTestData.readDataByTagName("", "discussionAssignment", Integer.toString(dataIndex));
            String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            new Navigator().NavigateTo("My Assignments");//Navigate to new assignment
            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys(assessmentName);
            questionBank.searchIcon.click();
            currentAssignments.editThisButton.click();//Edit This button
            Thread.sleep(3000);
            newAssignment.assessmentNameTextBox.click();//click on Name field
            newAssignment.assessmentName_TextBox.sendKeys(discussionAssignment);//enter name of assignment
            driver.findElement(By.cssSelector("body")).click();//click out side
            WebDriverUtil.clickOnElementUsingJavascript(newAssignment.questionContent);//click on question for discussion
            Thread.sleep(3000);
            driver.switchTo().activeElement().sendKeys(description);
            newAssignment.saveForLater_Button.click();
        } catch (Exception e) {
            Assert.fail("Exception in  createDiscussionAssignment in AppHelper class Assignment", e);

        }
    }

    /**
     * This function Changed total time value verify in Timed assignment popup.
     *
     * @param dataIndex(testdata Index)
     * @author Mukesh
     */
    public String updateTimeValueOfTimedAssignment(int dataIndex) {
        String updatedValue = "";
        try {
            WebDriver driver = Driver.getWebDriver();
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);

            String updateTime = ReadTestData.readDataByTagName("", "updateTime", Integer.toString(dataIndex));
            String studentName = ReadTestData.readDataByTagName("", "studentname", Integer.toString(dataIndex));
            String y[] = studentName.split(" ");
            studentName = y[1] + ", " + y[0];//reverse the name with comma in between
            new Navigator().NavigateTo("Class Assignments"); //navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link()); //click on the view response link.); //click on the view response link
            int studentIndex = 0;
            List<WebElement> studentNames = assessmentResponses.studentName;
            for (WebElement name : studentNames) {
                if (name.getText().trim().equals(studentName)) {
                    System.out.println("inside");
                    WebDriverUtil.waitTillVisibilityOfElement(name, 60);
                    new MouseHover().mouserhoverbywebelement(name);
                    assignmentResponsesPage.updateTime.get(studentIndex).clear();
                    updatedValue = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value='" + updateTime + "'", assignmentResponsesPage.updateTime.get(studentIndex));
                    // assignmentResponsesPage.updateTime.get(studentIndex).sendKeys(updateTime);
                    WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.updateTime_button.get(studentIndex));
                    break;
                }
                studentIndex++;
            }

        } catch (Exception e) {
            Assert.fail("Exception in  updateTimeValueOfTimedAssignment in AppHelper class Assignment", e);
        }

        return updatedValue;
    }


    /**
     * This function will return status value of grade book page
     *
     * @param dataIndex(testData Index)
     * @param boxName(status box name)
     * @param assignmentName
     * @author Mukesh
     */
    public static String gradeBookStatusBoxCount(int dataIndex, String boxName, String assignmentName) {
        WebDriver driver = Driver.getWebDriver();
        String value = "";
        int index = 1;
        List<WebElement> assignments = driver.findElements(By.cssSelector("div[class='assignment-label ellipsis gbClassPerformanceByAssignment-xAxisLabel']"));
        for (WebElement element : assignments) {
            if (element.getText().trim().equals(assignmentName)) {
                break;
            }
            System.out.println("index:" + value);
            index++;
        }

        if (boxName.equals("DueDate")) {
            List<WebElement> dueDate = driver.findElements(By.cssSelector(".due-date"));
            value = dueDate.get(index).getText().trim();
        }
        if (boxName.equals("DidNotStart")) {
            List<WebElement> didNotStart = driver.findElements(By.cssSelector(".not-start"));
            value = didNotStart.get(index).getText().trim();
        }
        if (boxName.equals("DidNotFinish")) {
            List<WebElement> didNotFinish = driver.findElements(By.cssSelector(".not-finish"));
            value = didNotFinish.get(index).getText().trim();
        }
        if (boxName.equals("Finished")) {
            List<WebElement> finished = driver.findElements(By.cssSelector(".finished"));
            value = finished.get(index).getText().trim();
        }
        if (boxName.equals("AverageTimeSpent")) {
            List<WebElement> averageTimeSpent = driver.findElements(By.cssSelector(".time-spent"));
            value = averageTimeSpent.get(index).getText().trim();
        }
        if (boxName.equals("AverageGrade")) {
            List<WebElement> averageGrade = driver.findElements(By.cssSelector(".grade"));
            value = averageGrade.get(index).getText().trim();
        }
        return value;
    }


    /**
     * This function assigns resource from Resource Tab
     * @author Dharaneesha
     * @param dataIndex(testdata Index)
     */
    public  void assignResourceFromResourceTab(int dataIndex){
        WebDriver driver=Driver.getWebDriver();
        try {
            String resourseName = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));

            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

            new Navigator().openFirstResourceFromResourceTab(0);
            Thread.sleep(9000);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("a[resourcetitle = '" + resourseName + "']")));
            if(gradable!=null){
                if(gradable.equals("false")){
                    driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")).click();
                }
            }
            driver.findElement(By.id("due-time")).click();//click on due time
            driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(5000);
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")), 0);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.linkText(duedate)), 0);
            driver.findElement(By.linkText(duedate)).click();
            myQuestionBank.assignPopUp.click();
        }catch (Exception e){
            Assert.fail("Exception in  assignResourceFromResourceTab in AppHelper class Assignment", e);
        }
    }


    /**
     * This function assigns resource from Resource Tab
     * @author Dharaneesha
     * @param dataIndex(testdata Index)
     */
    public  void assignFileBasedAssignmentFromAssignmentsTab(int dataIndex){
        WebDriver driver=Driver.getWebDriver();
        try {
            String resourseName = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));

            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

            new Navigator().openAssignmentFromAssignmentsTab(0);
            Thread.sleep(9000);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("a[resourcetitle = '" + resourseName + "']")));
            if(gradeable!=null){
                if(gradeable.equals("false")){
                    driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")).click();
                }
            }
            driver.findElement(By.id("due-time")).click();//click on due time
            driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(5000);
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")), 0);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.linkText(duedate)), 0);
            driver.findElement(By.linkText(duedate)).click();
            myQuestionBank.assignPopUp.click();
        }catch (Exception e){
            Assert.fail("Exception in  assignResourceFromResourceTab in AppHelper class Assignment", e);
        }
    }

    public  void assignResourceFromMyResourcePage(int dataIndex){
        WebDriver driver=Driver.getWebDriver();
        try {
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

            new Navigator().NavigateTo("My Resources");
            Thread.sleep(5000);
            myQuestionBank.getAssignThis().click();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.id("due-time")),10);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("due-time")));
            //driver.findElement(By.id("due-time")).click();//click on due time
            driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(5000);
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")), 0);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.linkText(duedate)), 0);
            driver.findElement(By.linkText(duedate)).click();
            myQuestionBank.assignPopUp.click();

        }catch (Exception e){
            Assert.fail("Exception in  assignResourceFromMyResourcePage in AppHelper class Assignment", e);

        }
    }



    public  void assignResourceFromAllResourcesPage(int dataIndex){
        WebDriver driver=Driver.getWebDriver();
        try {
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String resourseName = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));

            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

            new Navigator().NavigateTo("All Resources");
            myQuestionBank.allResourse_textarea.click();
            driver.switchTo().activeElement().sendKeys(resourseName);
            myQuestionBank.search_Button.click();
            WebDriverUtil.waitTillVisibilityOfElement(myQuestionBank.search_Button,10);
            myQuestionBank.getAssignThis().click();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.id("due-time")),10);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("due-time")));
            driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(5000);
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")), 0);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.linkText(duedate)), 0);
            driver.findElement(By.linkText(duedate)).click();
            myQuestionBank.assignPopUp.click();
        }catch (Exception e){
            Assert.fail("Exception in  assignResourceFromAllResourcesPage in AppHelper class Assignment", e);

        }
    }

    public  void assignResourceFromMyAssignmentsPage(int dataIndex){
        WebDriver driver=Driver.getWebDriver();
        try {
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String resourseName = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));

            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);

            new Navigator().NavigateTo("My Assignments");
            List<WebElement> assignThisElementsList = driver.findElements(By.cssSelector("div[class = 'ls-assessment-item-sectn-right ls-my-resource-item-section']"));
            for(int a=0;a<assignThisElementsList.size();a++){
                if(assignThisElementsList.get(a).getText().trim().contains(resourseName)){
                    List<WebElement> spanElementsList = assignThisElementsList.get(a).findElements(By.tagName("span"));
                    for(int b=0;b<spanElementsList.size();b++){
                        if(spanElementsList.get(a).getAttribute("title").equals("Assign This")){
                            spanElementsList.get(a).click();
                            break;
                        }
                    }
                }
            }

            Thread.sleep(9000);
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.id("due-time")),10);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("due-time")));
            driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(5000);
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")), 0);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.linkText(duedate)), 0);
            driver.findElement(By.linkText(duedate)).click();
            Thread.sleep(15000);
            WebDriverUtil.clickOnElementUsingJavascript(myQuestionBank.assignPopUp);
            Thread.sleep(15000);
        }catch (Exception e){
            Assert.fail("Exception in  assignResourceFromMyAssignmentsPage in AppHelper class Assignment", e);

        }
    }


    public void assignAssignmentFromQuestionBanks(int dataIndex) {
        WebDriver driver=Driver.getWebDriver();
        try{
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String gradeBookWeighting = ReadTestData.readDataByTagName("", "gradeBookWeighting", Integer.toString(dataIndex));
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
            String policyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(dataIndex));
            String policyDescription = ReadTestData.readDataByTagName("", "policyDescription", Integer.toString(dataIndex));
            String scorePerQuestion = ReadTestData.readDataByTagName("", "scorePerQuestion", Integer.toString(dataIndex));
            String ordering = ReadTestData.readDataByTagName("", "ordering", Integer.toString(dataIndex));
            String immediateFeedBack = ReadTestData.readDataByTagName("", "immediateFeedBack", Integer.toString(dataIndex));
            String numberOfAttempt = ReadTestData.readDataByTagName("", "numberOfAttempt", Integer.toString(dataIndex));
            String showAnswerAtAttemptNumber = ReadTestData.readDataByTagName("", "showAnswerAtAttemptNumber", Integer.toString(dataIndex));
            String gradeReleaseOption = ReadTestData.readDataByTagName("", "gradeReleaseOption", Integer.toString(dataIndex));
            String showHintsAtAttemptNumber = ReadTestData.readDataByTagName("", "showHintsAtAttemptNumber", Integer.toString(dataIndex));
            String showReadingContentLinkAtAttemptNumber = ReadTestData.readDataByTagName("", "showReadingContentLinkAtAttemptNumber", Integer.toString(dataIndex));
            String showSolutionAtAttemptNumber = ReadTestData.readDataByTagName("", "showSolutionAtAttemptNumber", Integer.toString(dataIndex));


            System.out.println("policyName:"+policyName);
            if(policyName==null){
                System.out.println("Inside Policy Name");
                policyName = "ByDefault Policy Name";
            }

            if(policyDescription==null){
                policyDescription = "Policy Description text";
            }
            if(scorePerQuestion==null){
                scorePerQuestion = "1";
            }

            if(immediateFeedBack==null){
                immediateFeedBack = "false";
            }


            if(numberOfAttempt==null){
                numberOfAttempt = "1";
            }
            if(showAnswerAtAttemptNumber==null){
                showAnswerAtAttemptNumber = "";
            }
            if(gradeReleaseOption==null){
                gradeReleaseOption = "Release explicitly by the instructor";
            }
            if(showHintsAtAttemptNumber==null){
                showHintsAtAttemptNumber = "";
            }
            if(showReadingContentLinkAtAttemptNumber==null){
                showReadingContentLinkAtAttemptNumber = "";
            }
            if(showSolutionAtAttemptNumber==null){
                showSolutionAtAttemptNumber = "";
            }

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new Navigator().NavigateTo("Question Banks");
            new UIElement().waitAndFindElement(By.id("all-resource-search-textarea"));
            //Adding assignment to search
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='assign-this']"));
            Thread.sleep(5000);
            List<WebElement> assignThisElements = driver.findElements(By.cssSelector("span[class='assign-this']"));
            for (int a=0;a<assignThisElements.size();a++){
                if(assignThisElements.get(a).isDisplayed()){
                    assignThisElements.get(a).click();
                    break;
                }
            }

            //driver.findElement(By.cssSelector("span[class='assign-this']")).click();

            //driver.findElement(By.cssSelector("span[class='assign-this']")).click();

            new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, true);


            new UIElement().waitAndFindElement(By.id("due-time"));

            driver.findElement(By.id("due-time")).click();
            //driver.findElement(By.xpath("//li[text()='07:30 PM']")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[text()='07:30 PM']")));

            driver.findElement(By.id("due-date")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.linkText(duedate)));
/*
            if (gradeable.equals("true")) {
                new UIElement().waitAndFindElement(By.className("ir-ls-assign-dialog-gradable-label-check"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
            }*/

            if (gradeable.equals("false")) {
                new UIElement().waitAndFindElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")));
            }
            if (gradeable.equals("true") && assignmentpolicy != null) {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            }
            if(gradeable.equals("true")) {
                if(policyName.equals("ByDefault Policy Name")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                    new Click().clickbyxpath("//a[@rel='addNewAssignmentPolicy']");
                    new AssignmentPolicy().createAssignmentPolicyWhileAssigning(policyName, policyDescription, scorePerQuestion, ordering, Boolean.parseBoolean(immediateFeedBack), numberOfAttempt, showAnswerAtAttemptNumber, gradeReleaseOption, showHintsAtAttemptNumber, showReadingContentLinkAtAttemptNumber, showSolutionAtAttemptNumber);
                }

            }

            if (accessibleafter != null) {
                driver.findElement(By.id("accessible-date")).click();
                driver.findElement(By.xpath("//a[@title='Next']")).click();
                driver.findElement(By.linkText(accessibleafter)).click();
            }

            new UIElement().waitAndFindElement(By.id("additional-notes"));
            driver.findElement(By.id("additional-notes")).clear();
            driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);

            if(gradeBookWeighting!=null){
                if(gradeBookWeighting.equals("true")){
                    new Click().clickbylinkText("Uncategorized");
                    new UIElement().waitAndFindElement(By.linkText("No Assignment Category"));
                    new Click().clickbylinkText("Practice");
                }
            }

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")));
            new UIElement().waitTillInvisibleElement(By.cssSelector("span[class='btn sty-green submit-assign']"));

        } catch (Exception e) {
            Assert.fail("Exception in  assignAssignmentFromQuestionBanks in AppHelper class Assignment", e);
        }
    }




    /**
     * This function will extend due time from Assignment Response Page
     *
     * @param dataIndex(testData Index)
     * @author Mukesh
     */

    public void extentDueTimeFromAssignmentResponsePage(String dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String newDueTime = ReadTestData.readDataByTagName("", "newDueTime", dataIndex);
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link()); //click on the view response link.);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("extend-due-date-label")));//click on the extend due date
            new ShareWith().shareIn(shareWithInitialString, shareName, shareWithClass, context_title, true);  //share with class/student
            DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            Calendar now = Calendar.getInstance();
            //add minutes to current date using Calendar.add method
            now.add(Calendar.MINUTE, Integer.parseInt(newDueTime));
            String calenderFormat = dateFormat.format(now.getTime());
            System.out.println("calenderFormat:" + calenderFormat);
            driver.findElement(By.id("extended-due-time")).click();//click on dur time
            driver.findElement(By.id("extended-due-time")).clear();
            driver.findElement(By.id("extended-due-time")).sendKeys(calenderFormat);//click on due time
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("update-extend-due-date")));

        } catch (Exception e) {
            Assert.fail("Exception in method  extentDueTime of class Assignment", e);

        }
    }


    public void assignCustomAssignmentFromMyQuestionBank(int dataIndex, int x) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(dataIndex));
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(dataIndex));
            String policyDescription = ReadTestData.readDataByTagName("", "policyDescription", Integer.toString(dataIndex));
            String scorePerQuestion = ReadTestData.readDataByTagName("", "scorePerQuestion", Integer.toString(dataIndex));
            String ordering = ReadTestData.readDataByTagName("", "ordering", Integer.toString(dataIndex));
            String immediateFeedBack = ReadTestData.readDataByTagName("", "immediateFeedBack", Integer.toString(dataIndex));
            String numberOfAttempt = ReadTestData.readDataByTagName("", "numberOfAttempt", Integer.toString(dataIndex));
            String showAnswerAtAttemptNumber = ReadTestData.readDataByTagName("", "showAnswerAtAttemptNumber", Integer.toString(dataIndex));
            String gradeReleaseOption = ReadTestData.readDataByTagName("", "gradeReleaseOption", Integer.toString(dataIndex));
            String showHintsAtAttemptNumber = ReadTestData.readDataByTagName("", "showHintsAtAttemptNumber", Integer.toString(dataIndex));
            String showReadingContentLinkAtAttemptNumber = ReadTestData.readDataByTagName("", "showReadingContentLinkAtAttemptNumber", Integer.toString(dataIndex));
            String showSolutionAtAttemptNumber = ReadTestData.readDataByTagName("", "showSolutionAtAttemptNumber", Integer.toString(dataIndex));
            String policyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));

            System.out.println("duedate:" + duedate);
            if (policyName == null) {
                System.out.println("Inside Policy Name");
                policyName = "Policy1";
            }

            if (policyDescription == null) {
                policyDescription = "Policy Description text";
            }
            if (scorePerQuestion == null) {
                scorePerQuestion = "1";
            }

            if (immediateFeedBack == null) {
                immediateFeedBack = "false";
            }

            if (numberOfAttempt == null) {
                numberOfAttempt = "1";
            }
            if (showAnswerAtAttemptNumber == null) {
                showAnswerAtAttemptNumber = "";
            }
            if (gradeReleaseOption == null) {
                gradeReleaseOption = "Release explicitly by the instructor";
            }
            if (showHintsAtAttemptNumber == null) {
                showHintsAtAttemptNumber = "";
            }
            if (showReadingContentLinkAtAttemptNumber == null) {
                showReadingContentLinkAtAttemptNumber = "";
            }
            if (showSolutionAtAttemptNumber == null) {
                showSolutionAtAttemptNumber = "";
            }

            new Navigator().NavigateTo("My Question Bank");
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[class='assign-this']")).click();
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("ir-ls-assign-this-edit-link")));

            driver.findElement(By.className("ir-ls-assign-this-header-edit-text")).clear();
            Thread.sleep(2000);
            driver.findElement(By.className("ir-ls-assign-this-header-edit-text")).sendKeys(x + "GradableAssignment");
            new UIElement().waitAndFindElement(By.id("due-time"));

            DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            Calendar now = Calendar.getInstance();
            //add minutes to current date using Calendar.add method
            now.add(Calendar.MINUTE, Integer.parseInt(duetime));
            String calenderFormat = dateFormat.format(now.getTime());
            System.out.println("calenderFormat:" + calenderFormat);

            driver.findElement(By.id("due-time")).click();//click on dur time
            driver.findElement(By.id("due-time")).sendKeys(calenderFormat);//click on dur time

            driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(5000);
            List<WebElement> defaultsDate = driver.findElements(By.cssSelector("a[class='ui-state-default ui-state-highlight ui-state-hover']"));
            for (WebElement defaultdate : defaultsDate) {
                if (defaultdate.isDisplayed()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", defaultdate);
                    break;
                }
            }


            if (gradeable.equals("false")) {
                new UIElement().waitAndFindElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")));
            }
            if (gradeable.equals("true") && assignmentpolicy != null) {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            }
            if (gradeable.equals("true")) {
                if (policyName.equals("Policy1")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                    new Click().clickbyxpath("//a[@rel='addNewAssignmentPolicy']");
                    new AssignmentPolicy().createAssignmentPolicyWhileAssigning(policyName, policyDescription, scorePerQuestion, ordering, Boolean.parseBoolean(immediateFeedBack), numberOfAttempt, showAnswerAtAttemptNumber, gradeReleaseOption, showHintsAtAttemptNumber, showReadingContentLinkAtAttemptNumber, showSolutionAtAttemptNumber);
                }

            }

            if (accessibleafter != null) {
                new UIElement().waitAndFindElement(By.id("accessible-date"));
                driver.findElement(By.id("accessible-date")).click();
                driver.findElement(By.linkText(accessibleafter)).click();
            }
            new UIElement().waitAndFindElement(By.id("additional-notes"));
            ((JavascriptExecutor) driver).executeScript("document.getElementById('additional-notes').value='additionalnote'");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Assign']")));
            new UIElement().waitTillInvisibleElement(By.xpath("//span[text()='Assign']"));
        } catch (Exception e) {
            Assert.fail("Exception in  assignFileBasedAssignmentFromMyQuestionBank in AppHelper class Assignment", e);
        }
    }


    /**
     * This function will extend due time from Assignment Response Page with Group
     * @param dataIndex(testData Index)
     * @author Mukesh
     */

    public void extentDueTimeFromAssignmentResponsePageWithGroup(String dataIndex) {
        WebDriver driver = Driver.getWebDriver();
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String newDueTime = ReadTestData.readDataByTagName("", "newDueTime", dataIndex);
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link()); //click on the view response link.);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("extend-due-date-label")));//click on the extend due date
            new ShareWith().shareWithGroup(shareWithInitialString, shareName, shareWithClass, context_title, true);  //share with class/student
            DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            Calendar now = Calendar.getInstance();
            //add minutes to current date using Calendar.add method
            now.add(Calendar.MINUTE, Integer.parseInt(newDueTime));
            String calenderFormat = dateFormat.format(now.getTime());
            System.out.println("calenderFormat:" + calenderFormat);
            driver.findElement(By.id("extended-due-time")).click();//click on dur time
            driver.findElement(By.id("extended-due-time")).clear();
            driver.findElement(By.id("extended-due-time")).sendKeys(calenderFormat);//click on due time
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("update-extend-due-date")));

        } catch (Exception e) {
            Assert.fail("Exception in method  extentDueTime of class Assignment", e);

        }
    }



    public void notApplicable(int index) {
        WebDriver driver = Driver.getWebDriver();
        try {
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.student_checkBox.get(index));
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.menuIcon);
            assignmentResponsesPage.notApplicable.click();
            assignmentResponsesPage.yes_NotApplicable.click();
        } catch (Exception e) {
            Assert.fail("Exception in method notApplicable in class AssignmentPerformance", e);
        }

    }
}



