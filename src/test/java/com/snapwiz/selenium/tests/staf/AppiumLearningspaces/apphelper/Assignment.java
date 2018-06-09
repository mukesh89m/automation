package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelperAppium.LoginUsingRandomUser_appium;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.CMS.NewQuestionDataEntry;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.*;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.MouseHover;
import  com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.DiagnosticAssessment.Questions;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.QuestionCreate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Config;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;

public class Assignment extends Driver {

    public ArrayList<String> create(String dataIndex,String... testData) {
        ArrayList<String> assessmentInfoList= new ArrayList<>();
        String questiontext = null;
        try {

            String course = "";
            String overrideDefaultLogin = ReadTestData.readDataByTagName("", "overrideDefaultLogin", dataIndex);
            String practice_type = ReadTestData.readDataByTagName("", "practice_type", dataIndex);
            String overrideDefaultQuestionCreate = ReadTestData.readDataByTagName("", "overrideDefaultQuestionCreate", dataIndex);
            course = Config.course;
            String assessmentame = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String questionset = ReadTestData.readDataByTagName("", "questionset", dataIndex);
            String questiontype = ReadTestData.readDataByTagName("", "questiontype", dataIndex);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", dataIndex);
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", dataIndex);
            String reservforassignment = ReadTestData.readDataByTagName("", "reservforassignment", dataIndex);

            String packageName = new Exception().getStackTrace()[1].getClassName();
            String methodName = new Exception().getStackTrace()[1].getMethodName();

            packageName = packageName.substring(64);
            packageName = packageName.replaceAll("\\.", "_");

            if(assessmentame==null){
                assessmentame  = dataIndex+ "_Assessment_"+packageName+"_"+methodName+"_"+appendChar;
                System.out.println("Assessment Name Created: " + assessmentame);
            }

            if(questionset==null){
                questionset  = dataIndex+ "_questionSet_"+packageName+"_"+methodName +"_"+appendChar;
            }
            if(questiontype==null){
                if(Arrays.asList(testData).contains("Adaptive Component Diagnostic")){
                    questiontype = "Adaptive Component Diagnostic";
                }else if(Arrays.asList(testData).contains("Adaptive Component Practice")){
                    questiontype = "Adaptive Component Practice";
                }else {
                    questiontype = "Static Practice";
                }
            }


            /*if(chapterName==null){
                if(testData.length==0){
                    chapterName = "Ch 1: The Study of Life";
                }else{
                    chapterName  = testData[0];
                }
            }*/


            if (overrideDefaultLogin == null)
                new DirectLogin().CMSLogin();
            else {
                String role = ReadTestData.readDataByTagName("", "role", dataIndex);
                new DBConnect().Connect();
                ResultSet rs = com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect.st.executeQuery("select id,username from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = '" + role + "') limit 1,1;");
                String username = "";
                while (rs.next()) {
                    username = rs.getString("username");
                }
                new DirectLogin().directLoginWithCreditial(username, "snapwiz", "0");
                new Click().clickbyxpath("(//select[@name='product-drop-down']/following-sibling::div/a)[1]");
                new Click().clickbylinkText("Learning Space + Adaptive Component");
                com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect.conn.close();
            }

            String title = Driver.driver.getTitle();
            if (title.equals("Course Content Details")) {
                Driver.driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();

                if (chapterName == null) {
                    if(Arrays.asList(testData).contains("Ch 5: Structure and Function of Plasma Membranes")){
                        new Click().clickBycssselector("div[title='Ch 5: Structure and Function of Plasma Membranes']");

                    }else{
                        new Click().clickBycssselector("div.course-chapter-label.node");
                    }


                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    Thread.sleep(10000);
                    if( Driver.driver.findElements(By.xpath("//div[contains(@title,'"+chapterName+"')]")).size()>1)
                    {
                        System.out.println("inside if:"+chapterName);
                        new TopicOpen().openCMSLastChapter();
                    }
                    else {
                        System.out.println("inside else:"+chapterName);
                        List<WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                        for (WebElement chapters : allChapters) {
                            if (chapters.getText().contains(chapterName)) {
                                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters);
                                Thread.sleep(4000);
                                break;
                            }

                        }
                    }

                }
                Driver.driver.findElement(By.cssSelector("div.create-practice")).click();

                if (practice_type == null) {
                    new Click().clickByclassname("create-regular-assessment-popup-item"); //click on the create regular assessment
                } else {
                    new Click().clickByclassname("create-file-assessment-popup-item");//click on the create file assessment
                }
                new Click().clickbylinkText("Adaptive Component Diagnostic");
                new Click().clickbylinkText(questiontype);
                int popup = 1;
                try {
                    WebDriverWait wait = new WebDriverWait(Driver.driver, 3);
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='cms-notification-message-body']")));

                } catch (Exception e) {
                    popup = 0;
                }
                //int popup = Driver.driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
                if (popup == 0) {
                    Driver.driver.findElement(By.id("assessmentName")).click();
                    Driver.driver.findElement(By.id("assessmentName")).clear();
                    Driver.driver.findElement(By.id("assessmentName")).sendKeys(assessmentame);
                    Driver.driver.findElement(By.id("questionSetName")).clear();
                    Driver.driver.findElement(By.id("questionSetName")).sendKeys(questionset);

                    if (reservforassignment == null) {
                        if(!Arrays.asList(testData).contains("noreserveAssignment")){
                            Driver.driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
                        }
                    }

                    if(overrideDefaultQuestionCreate ==null){
                        questiontext = new QuestionCreate().trueFalseQuestions(dataIndex);
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
                Driver.driver.quit();
                Driver.startDriver();
            }

           /* <assessmentname>IT21Mobile_R218Generic_Assessment_31</assessmentname>
            <questionset>Question Set_IT21Mobile_R218Generic</questionset>
            <questiontext>Test Question_IT21Mobile_R218Generic_31</questiontext>
            <questiontype>Static Practice</questiontype>
            <chapterName>Ch 3: Biological Macromolecules</chapterName>*/

            assessmentInfoList.add(assessmentame);
            assessmentInfoList.add(questionset);
            assessmentInfoList.add(questiontext);
            assessmentInfoList.add(questiontype);
            assessmentInfoList.add(chapterName);

        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper create in class AssignmentCreate", e);
        }
        return  assessmentInfoList;
    }

    public void addQuestions(String dataIndex, String questionType, String assignmentname) {
        try {
            String course = "";
            assignmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String overrideDefaultLogin = ReadTestData.readDataByTagName("", "overrideDefaultLogin", dataIndex);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", dataIndex);
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", dataIndex);
            String course_type = ReadTestData.readDataByTagName("", "course_type", dataIndex);
            String course_name = ReadTestData.readDataByTagName("", "course_name", dataIndex);
            String subSection_level = ReadTestData.readDataByTagName("", "subSection_level", dataIndex);
            String chapterIndex = ReadTestData.readDataByTagName("", "chapterIndex", dataIndex);
            String topicIndex = ReadTestData.readDataByTagName("", "topicIndex", dataIndex);
            course = course_name;



            String packageName = new Exception().getStackTrace()[1].getClassName();
            String methodName = new Exception().getStackTrace()[1].getMethodName();

            packageName = packageName.substring(64);
            packageName = packageName.replaceAll("\\.", "_");

            if(assignmentname==null){
                assignmentname  = dataIndex+ "_Assessment_"+packageName+"_"+methodName+"_"+appendChar;
                System.out.println("Assessment Name : " + assignmentname);
            }





            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }
            if (overrideDefaultLogin == null)
                new DirectLogin().CMSLogin();
            else {
                String role = ReadTestData.readDataByTagName("", "role", dataIndex);
                new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect().Connect();
                ResultSet rs = com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect.st.executeQuery("select id,username from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = '" + role + "') limit 1,1;");
                String username = "";
                while (rs.next()) {
                    username = rs.getString("username");
                    //System.out.println(username);
                }
                new DirectLogin().directLoginWithCreditial(username, "snapwiz", dataIndex);
                new Click().clickbyxpath("(//select[@name='product-drop-down']/following-sibling::div/a)[1]");
                new Click().clickbylinkText("Learning Space + Adaptive Component");
            }
            String title = Driver.driver.getTitle();
            if (title.equals("Course Content Details")) {
                Driver.driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    List<WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                    for (WebElement chapters : allChapters) {
                        if (chapters.getText().contains(chapterName)) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters);
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
                //Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                boolean assignmentExists = false;
                List<WebElement> elements = Driver.driver.findElements(By.xpath("//div[@title='" + assignmentname + "']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elements.get(elements.size()-1));
                Thread.sleep(5000);
                try {
                    new Click().clickByElement(elements.get(elements.size()-1));
                    //elements.get(elements.size() - 1).click();
                    assignmentExists = true;
                    System.out.println("assignmentExists:"+assignmentExists);
                }
                catch (Exception e) {

                }
             /*   for (WebElement content : elements) {
                    if (content.getText().trim().equals(assignmentname)) {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", content);
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
                }

            } //if condition  ends here
            if (!(Config.browser.equalsIgnoreCase("firefox"))) {
                Driver.driver.quit();
                Driver.startDriver();
            }
        } catch (Exception e) {

            Assert.fail("Exception in create in Apphelper addQuestions in class AssignmentCreate", e);
        }
    }

    //create assignment at topic level instructor value false
    public void createassignmentontopiclevel(String dataIndex, int topic, int chapter) {
        try {
            String course = Config.course;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String questionset = ReadTestData.readDataByTagName("", "questionset", dataIndex);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String questiontype = ReadTestData.readDataByTagName("", "questiontype", dataIndex);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", dataIndex);
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            new DirectLogin().CMSLogin();
            String title = Driver.driver.getTitle();
            if (title.equals("Course Content Details")) {
                Driver.driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                List<WebElement> allchapter = (List<WebElement>) ((JavascriptExecutor) Driver.driver).executeScript("return arguments[0]", Driver.driver.findElements(By.cssSelector("div[class='expand-chapter-tree expand']")));
                int index1 = 0;
                for (WebElement elements : allchapter) {

                    if (index1 == chapter) {
                        elements.click();
                        break;
                    }
                    index1++;
                }

                List<WebElement> alltopic = (List<WebElement>) ((JavascriptExecutor) Driver.driver).executeScript("return arguments[0]", Driver.driver.findElements(By.cssSelector("div[class='course-topic-label node']")));
                int index = 0;
                for (WebElement element : alltopic) {

                    if (index == topic) {
                        element.click();
                        break;
                    }
                    index++;
                }
                new UIElement().waitAndFindElement(By.cssSelector("div.create-practice"));
                Driver.driver.findElement(By.cssSelector("div.create-practice")).click();
                Driver.driver.findElement(By.id("assessmentName")).click();
                Driver.driver.findElement(By.id("assessmentName")).clear();
                Driver.driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
                Driver.driver.findElement(By.id("questionSetName")).clear();
                Driver.driver.findElement(By.id("questionSetName")).sendKeys(questionset);

                Driver.driver.findElement(By.id("qtn-type-true-false-img")).click();


                Driver.driver.findElement(By.id("question-raw-content")).click();


                Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                Driver.driver.switchTo().defaultContent();

                Actions action = new Actions(Driver.driver);
                WebElement we = Driver.driver.findElement(By.id("choice1"));
                action.moveToElement(we).build().perform();
                Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
               new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
                new ComboBox().selectValue(3, questiontype);
                int popup = Driver.driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
                if (popup == 0) {
                    if (difficultylevel != null) {
                        new ComboBox().selectValue(7, difficultylevel);
                    }
                    if (learningobjective != null) {
                        Driver.driver.findElement(By.id("learing-objectives-span")).click();
                        Driver.driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div/div[1]/div[3]/div/div/div[4]/div/div[3]/div[1]/div/div[1]/label")).click();
                        Driver.driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
                        Driver.driver.findElement(By.cssSelector("span.cancel-collection")).click();

                    }
                    Driver.driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
                    new ComboBox().selectValue(4, "Publish");

                    Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
                }//if condition for popup ends here
                Driver.driver.quit();
                Driver.startDriver();

            }
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper createassignmentontopiclevel in class AssignmentCreate", e);
        }

    }

    //create assignment at subtopic level instructor value false
    public void createresourcesatsubtopiclevel(String dataIndex, int topictoexpand, int chapter, int subtopic) {
        try {
            String course = Config.course;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String questionset = ReadTestData.readDataByTagName("", "questionset", dataIndex);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String questiontype = ReadTestData.readDataByTagName("", "questiontype", dataIndex);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", dataIndex);
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            new DirectLogin().CMSLogin();
            String title = Driver.driver.getTitle();
            if (title.equals("Course Content Details")) {
                Driver.driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                List<WebElement> allchapter = (List<WebElement>) ((JavascriptExecutor) Driver.driver).executeScript("return arguments[0]", Driver.driver.findElements(By.cssSelector("div[class='expand-chapter-tree expand']")));
                int index1 = 0;
                for (WebElement elements : allchapter) {
                    if (index1 == chapter) {
                        elements.click();
                        break;
                    }
                    index1++;
                }
                List<WebElement> alltopic = (List<WebElement>) ((JavascriptExecutor) Driver.driver).executeScript("return arguments[0]", Driver.driver.findElements(By.cssSelector("div[class='expand-topic-tree expand']")));
                int index = 0;
                for (WebElement element : alltopic) {

                    if (index == topictoexpand) {
                        element.click();
                        break;
                    }
                    index++;
                }
                List<WebElement> allsubtopictopic = (List<WebElement>) ((JavascriptExecutor) Driver.driver).executeScript("return arguments[0]", Driver.driver.findElements(By.cssSelector("div[class='course-subtopic-label node']")));
                int index2 = 0;
                for (WebElement element1 : allsubtopictopic) {

                    if (index2 == subtopic) {
                        element1.click();
                        break;
                    }
                    index2++;
                }
                new UIElement().waitAndFindElement(By.cssSelector("div.create-practice"));
                Driver.driver.findElement(By.cssSelector("div.create-practice")).click();
                Driver.driver.findElement(By.id("assessmentName")).click();
                Driver.driver.findElement(By.id("assessmentName")).clear();
                Driver.driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
                Driver.driver.findElement(By.id("questionSetName")).clear();
                Driver.driver.findElement(By.id("questionSetName")).sendKeys(questionset);

                Driver.driver.findElement(By.id("qtn-type-true-false-img")).click();


                Driver.driver.findElement(By.id("question-raw-content")).click();


                Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                Driver.driver.switchTo().defaultContent();

                Actions action = new Actions(Driver.driver);
                WebElement we = Driver.driver.findElement(By.id("choice1"));
                action.moveToElement(we).build().perform();
                Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
                new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
                new ComboBox().selectValue(3, questiontype);
                int popup = Driver.driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
                if (popup == 0) {
                    if (difficultylevel != null) {
                        new ComboBox().selectValue(7, difficultylevel);
                    }
                    if (learningobjective != null) {
                        Driver.driver.findElement(By.id("learing-objectives-span")).click();
                        Driver.driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div/div[1]/div[3]/div/div/div[4]/div/div[3]/div[1]/div/div[1]/label")).click();
                        Driver.driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();
                        Driver.driver.findElement(By.cssSelector("span.cancel-collection")).click();

                    }
                    Driver.driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
                    new ComboBox().selectValue(4, "Publish");

                    Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
                }//if condition for popup ends here
                Driver.driver.quit();
                Driver.startDriver();

            }
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper createresourcesatsubtopiclevel in class AssignmentCreate", e);
        }

    }


    public void addPassagetypequestion(String dataIndex, String passageType, String assignmentname, String questionType) {
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String optiontext = ReadTestData.readDataByTagName("", "optiontext", dataIndex);
            String answertext = ReadTestData.readDataByTagName("", "answertext", dataIndex);
            String numerictext = ReadTestData.readDataByTagName("", "numerictext", dataIndex);
            String unitoption = ReadTestData.readDataByTagName("", "unitoption", dataIndex);
            String tolrence = ReadTestData.readDataByTagName("", "tolrence", dataIndex);
            String maplenumeric = ReadTestData.readDataByTagName("", "maplenumeric", dataIndex);
            String setname = ReadTestData.readDataByTagName("", "setname", dataIndex);
            String passage = ReadTestData.readDataByTagName("", "passage", dataIndex);
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", dataIndex);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", dataIndex);
            String course = Config.course;
            new DirectLogin().CMSLogin();
            String title = Driver.driver.getTitle();
            if (title.equals("Course Content Details")) {
                Driver.driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                } else {
                    List<WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                    for (WebElement chapters : allChapters) {
                        if (chapters.getText().contains(chapterName)) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters);
                            break;
                        }

                    }

                }
                List<WebElement> elements = Driver.driver.findElements(By.className("collection-assessment-name"));
                for (WebElement content : elements) {
                    if (content.getText().trim().equals(assignmentname)) {

                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", content);
                        break;
                    }
                }

                Driver.driver.findElement(By.id("questionOptions")).click();
                new UIElement().waitAndFindElement(By.id("addQuestion"));
                Driver.driver.findElement(By.id("addQuestion")).click();
                new UIElement().waitAndFindElement(By.id(passageType));
                Driver.driver.findElement(By.id(passageType)).click();


                if (passageType.equals("qtn-passage-based-img")) {
                    //Adding Question set name
                    new UIElement().waitAndFindElement(By.id("edit-question-set-name"));
                    Driver.driver.findElement(By.id("edit-question-set-name")).click();
                    Driver.driver.findElement(By.id("edit-question-set-name")).sendKeys(setname);
                    Driver.driver.findElement(By.id("assessment-edit-save-btn")).click();
                    new UIElement().waitAndFindElement(By.id("passage-directions-content"));
                    // Adding Passage title
                    Driver.driver.findElement(By.id("passage-directions-content")).click();
                    Driver.driver.switchTo().frame("iframe-question-passage-direction").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                    Driver.driver.switchTo().defaultContent();
                    // Adding a passage
                    Driver.driver.findElement(By.id("passage-raw-content")).click();
                    Driver.driver.switchTo().frame("iframe-question-edit-passage-text").findElement(By.xpath("/html/body")).sendKeys(passage);
                    Driver.driver.switchTo().defaultContent();
                    Driver.driver.findElement(By.id("question-editor-outer-wrapper")).click();
                    new UIElement().waitAndFindElement(By.id(questionType));
                    // Adding different types of questions

                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id(questionType)));
                    //		Driver.driver.findElement(By.id(questionType)).click();
                    if (questionType.equals("qtn-type-true-false-img")) //1.For true-false type question
                    {
                        Driver.driver.findElement(By.id("question-raw-content")).click();
                        Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        Driver.driver.switchTo().defaultContent();
                        //Setting choice 1 as correct answer
                        Actions action = new Actions(Driver.driver);
                        WebElement we = Driver.driver.findElement(By.id("choice1"));
                        action.moveToElement(we).build().perform();
                        Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
                    }
                    if (questionType.equals("qtn-multiple-choice-img")) //2. For multiple choice question.
                    {
                        Driver.driver.findElement(By.id("question-raw-content")).click();
                        Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        Driver.driver.switchTo().defaultContent();
                        //Adding choice 1
                        Driver.driver.findElement(By.id("choice1")).click();
                        Driver.driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys(answertext);
                        Driver.driver.switchTo().defaultContent();
                        //Adding choice 2
                        Driver.driver.findElement(By.id("choice2")).click();
                        Driver.driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys(answertext);
                        Driver.driver.switchTo().defaultContent();
                        //Adding choice 3
                        Driver.driver.findElement(By.id("choice3")).click();
                        Driver.driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys(answertext);
                        Driver.driver.switchTo().defaultContent();
                        //Adding choice 4
                        Driver.driver.findElement(By.id("choice4")).click();
                        Driver.driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys(answertext);
                        Driver.driver.switchTo().defaultContent();
                        //Setting choice 2 as correct answer
                        Actions action = new Actions(Driver.driver);
                        WebElement we = Driver.driver.findElement(By.id("choice2"));
                        action.moveToElement(we).build().perform();
                        Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
                    }
                    if (questionType.equals("qtn-multiple-selection-img"))  //3. For multiple sections
                    {
                        Driver.driver.findElement(By.id("question-raw-content")).click();
                        Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        Driver.driver.switchTo().defaultContent();
                        //Adding choice 1
                        Driver.driver.findElement(By.id("choice1")).click();
                        Driver.driver.switchTo().frame("iframe-choice1").findElement(By.xpath("/html/body")).sendKeys(answertext);
                        Driver.driver.switchTo().defaultContent();
                        //Adding choice 2
                        Driver.driver.findElement(By.id("choice2")).click();
                        Driver.driver.switchTo().frame("iframe-choice2").findElement(By.xpath("/html/body")).sendKeys(answertext);
                        Driver.driver.switchTo().defaultContent();
                        //Adding choice 3
                        Driver.driver.findElement(By.id("choice3")).click();
                        Driver.driver.switchTo().frame("iframe-choice3").findElement(By.xpath("/html/body")).sendKeys(answertext);
                        Driver.driver.switchTo().defaultContent();
                        //Adding choice 4
                        Driver.driver.findElement(By.id("choice4")).click();
                        Driver.driver.switchTo().frame("iframe-choice4").findElement(By.xpath("/html/body")).sendKeys(answertext);
                        Driver.driver.switchTo().defaultContent();
                        //Setting choice 2 as correct answer
                        Actions action1 = new Actions(Driver.driver);
                        WebElement we1 = Driver.driver.findElement(By.id("choice2"));
                        action1.moveToElement(we1).build().perform();
                        Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
                        //Setting choice 4 as correct answer
                        Actions action2 = new Actions(Driver.driver);
                        WebElement we2 = Driver.driver.findElement(By.id("choice4"));
                        action2.moveToElement(we2).build().perform();
                        Driver.driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")).click();
                    }
                    if (questionType.equals("qtn-text-entry-img")) // 4.For Text Entry type.
                    {
                        Driver.driver.findElement(By.id("question-raw-content")).click();
                        Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        Driver.driver.switchTo().defaultContent();
                        // Adding correct answer choice
                        Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
                        Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
                        Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(answertext);
                        new UIElement().waitAndFindElement(By.id("right-alt-container-1"));
                        // Rest two boxes are filled automatically.
                        //Adding alternate answer choice.
                        Driver.driver.findElement(By.id("right-alt-container-1")).click();
                        Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
                        Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
                        Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);

                        Driver.driver.findElement(By.id("done-button")).click(); // Accept answer

                    }
                    if (questionType.equals("qtn-text-entry-drop-down-img")) //5. For text entry drop down
                    {
                        Driver.driver.findElement(By.id("question-raw-content")).click();
                        Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        Driver.driver.switchTo().defaultContent();
                        // Adding Entry 1
                        Driver.driver.findElement(By.id("entry-0")).click();
                        Driver.driver.findElement(By.id("unit-name-edit-entry-0")).clear();
                        Driver.driver.findElement(By.id("unit-name-edit-entry-0")).sendKeys(optiontext);
                       new UIElement().waitAndFindElement(By.id("entry-1"));
                        // Accepting answer
                        WebElement menuitem = Driver.driver.findElement(By.id("entry-1"));
                        Locatable hoverItem = (Locatable) menuitem;
                        Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
                        mouse.mouseMove(hoverItem.getCoordinates());
                        List<WebElement> selectanswerticks = Driver.driver.findElements(By.className("mark-selected"));
                        selectanswerticks.get(1).click(); //select second option as correct answer

                        // Adding Entry 2
                        Actions action = new Actions(Driver.driver);
                        action.doubleClick(Driver.driver.findElement(By.id("entry-1")));
                        action.perform();
                        Driver.driver.findElement(By.id("unit-name-edit-entry-1")).clear();
                        Driver.driver.findElement(By.id("unit-name-edit-entry-1")).sendKeys(optiontext);
                        new UIElement().waitAndFindElement(By.id("entry-2"));

                        // Adding Entry 3
                        action = new Actions(Driver.driver);
                        action.doubleClick(Driver.driver.findElement(By.id("entry-2")));
                        action.perform();
                        new UIElement().waitAndFindElement(By.id("unit-name-edit-entry-2"));
                        Driver.driver.findElement(By.id("unit-name-edit-entry-2")).clear();
                        Driver.driver.findElement(By.id("unit-name-edit-entry-2")).sendKeys(optiontext);
                        new UIElement().waitAndFindElement(By.id("add-new-entry"));
                        //clicking on add more entry
                        Driver.driver.findElement(By.id("add-new-entry")).click();
                        //Adding entry 4
                        action.doubleClick(Driver.driver.findElement(By.id("entry-3")));
                        Driver.driver.findElement(By.id("unit-name-edit-entry-3")).clear();
                        Driver.driver.findElement(By.id("unit-name-edit-entry-3")).sendKeys(optiontext);
                        // Accepting answer
                        Driver.driver.findElement(By.id("done-button")).click();
                    }

                    if (questionType.equals("qtn-text-entry-numeric-img")) // 6. For numeric text entry
                    {
                        Driver.driver.findElement(By.id("question-raw-content")).click();
                        Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        Driver.driver.switchTo().defaultContent();
                        // Adding correct answer choice
                        Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
                        Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
                        Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(numerictext);
                        Driver.driver.switchTo().defaultContent();
                        new UIElement().waitAndFindElement(By.id("right-alt-container-1"));
                        // Rest two boxes are filled automatically.
                        //Adding alternate answer choice.
                        Driver.driver.findElement(By.id("right-alt-container-1")).click();
                        Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
                        Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
                        Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
                        // Adding tolerance
                        Driver.driver.findElement(By.id("tolerance-ans-text")).click();
                        Driver.driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
                        new UIElement().waitAndFindElement(By.id("done-button"));
                        Driver.driver.findElement(By.id("done-button")).click(); // Accept answer

                    }
                    if (questionType.equals("qtn-text-entry-numeric-units-img")) //7. Adding text entry numeric with units.
                    {
                        Driver.driver.findElement(By.id("question-raw-content")).click();
                        Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        Driver.driver.switchTo().defaultContent();
                        // Adding correct answer choice
                        Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
                        Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
                        Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(numerictext);

                        new UIElement().waitAndFindElement(By.id("add-more-entry"));
                        // Rest two boxes are filled automatically.
                        Driver.driver.findElement(By.id("add-more-entry")).click();
                        new UIElement().waitAndFindElement(By.tagName("li"));
                        // Selecting particular unit
                        List<WebElement> unitvalues = Driver.driver.findElements(By.tagName("li"));
                        for (WebElement units : unitvalues) {
                            //System.out.println(units.getText());
                            if (units.getText().equals(unitoption)) {
                                units.click();
                                break;
                            }
                        }
                        Driver.driver.findElement(By.id("done-button")).click(); // Accept answer

                    }

                    if (questionType.equals("qtn-numeric-maple-img")) //8. Adding maple numeric question
                    {
                        Driver.driver.findElement(By.id("question-raw-content")).click();
                        Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        Driver.driver.switchTo().defaultContent();
                        // Adding correct answer choice
                        Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).click();
                        Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).clear();
                        Driver.driver.findElement(By.cssSelector("input[id='correct-ans-text']")).sendKeys(maplenumeric);

                        new UIElement().waitAndFindElement(By.id("right-alt-container-1"));
                        // Rest two boxes are filled automatically.
                        //Adding alternate answer choice.
                        Driver.driver.findElement(By.id("right-alt-container-1")).click();
                        Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).click();
                        Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).clear();
                        Driver.driver.findElement(By.cssSelector("input[id='alternate-answer-text']")).sendKeys(optiontext);
                        // Adding tolerance
                        Driver.driver.findElement(By.id("tolerance-ans-text")).click();
                        Driver.driver.findElement(By.id("tolerance-ans-text")).sendKeys(tolrence);
                       new UIElement().waitAndFindElement(By.id("done-button"));
                        // Accept answer
                        Driver.driver.findElement(By.id("done-button")).click();
                    }

                    if (questionType.equals("qtn-math-symbolic-notation-img")) // 9. Adding maple symbolic question
                    {
                        Driver.driver.findElement(By.id("question-raw-content")).click();
                        Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        Driver.driver.switchTo().defaultContent();
                        new UIElement().waitAndFindElement(By.cssSelector("div[class='right-container math-correct-answer-input-container']"));
                        // Adding Correct answer
                        Driver.driver.findElement(By.cssSelector("div[class='right-container math-correct-answer-input-container']")).click();
                        new UIElement().waitAndFindElement(By.cssSelector("button[title='Square root']"));
                        Driver.driver.findElement(By.cssSelector("button[title='Square root']")).click();
                        Driver.driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(numerictext);
                        Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();

                        // Adding Alternate answer
                        Driver.driver.findElement(By.id("right-alt-container-1")).click();
                        List<WebElement> allAnswer = Driver.driver.findElements(By.className("display-correct-answer-math-editor-wrapper"));
                        allAnswer.get(1).click();
                        new UIElement().waitAndFindElement(By.cssSelector("button[title='Square root']"));
                        Driver.driver.findElement(By.cssSelector("button[title='Square root']")).click();
                        Driver.driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys(numerictext);
                        Driver.driver.findElement(By.id("wiris-container-save-formaulaeditor")).click();
                        // Accept answer
                        Driver.driver.findElement(By.id("done-button")).click();

                    }

                    //((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.cssSelector("div[id='qtn-essay-type']")));

                    if (questionType.equals("qtn-essay-type")) // 10. Adding Essay type question
                    {

                        Driver.driver.findElement(By.id("question-raw-content")).click();
                        Driver.driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys(questiontext);
                        Driver.driver.switchTo().defaultContent();
                        //Adding height for text entry.
                        Driver.driver.findElement(By.id("essay-question-height")).click();
                        Driver.driver.findElement(By.id("essay-question-height")).sendKeys(numerictext);

                    }

                }
                if (useWriteBoard != null) {
                    new UIElement().waitAndFindElement(By.id("display-write-board-checkbox"));
                    Driver.driver.findElement(By.id("display-write-board-checkbox")).click();//check the use writeboard check box

                }

                new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
                new ComboBox().selectValue(3, "Publish");
                Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
                Driver.driver.quit();
                Driver.startDriver();


            }
        } catch (Exception e) {

            Assert.fail("Exception in create in Apphelper addQuestions in class AssignmentCreate", e);
        }

    }


    public void assignThisFormFillOtherDetails(String dataIndex) {
        try {
            new TextSend().textsendbyclass("Shor", "input-filed");
            new TextSend().textsendbyid("Description", "additional-notes");
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            Driver.driver.findElement(By.id("due-time")).click();
            List<WebElement> elements = Driver.driver.findElements(By.xpath("//ul[@class='ui-timepicker-list']//li"));
            for (WebElement time : elements) {
                System.out.println("time : " +time.getText());
                if (time.getText().equals(duetime)) {
                    time.click();
                    System.out.println("Entered & Clicked");
                    break;
                }
            }
            Driver.driver.findElement(By.id("due-date")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
            Driver.driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            Driver.driver.findElement(By.linkText(duedate)).click();
            //Driver.driver.findElement(By.xpath("//span[starts-with(normalize-space(@class),'btn sty-green submit-assign')]")).click();

           ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//span[starts-with(normalize-space(@class),'btn sty-green submit-assign')]")));
            new UIElement().waitTillInvisibleElement(By.xpath("//span[starts-with(normalize-space(@class),'btn sty-green submit-assign')]"));
        } catch (Exception e) {
            Assert.fail("Exception in method 'fillOtherDetailsInAssignThisForm' in class 'Assignment'", e);
        }
    }

    public void assignToStudent(String dataIndex, String... testData) {
        try {
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", dataIndex);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", dataIndex);



            if(duedate ==null){
                duedate = "28";
            }



            String packageName = new Exception().getStackTrace()[1].getClassName();
            String methodName = new Exception().getStackTrace()[1].getMethodName();

            packageName = packageName.substring(64);
            packageName = packageName.replaceAll("\\.", "_");

            if(assignmentname==null){
                assignmentname  = dataIndex+ "_Assessment_"+packageName+"_"+methodName;
                System.out.println("assignmentname : " + assignmentname);
            }

            if(additionalnote==null){
                additionalnote = "This is a note";
            }

            CurrentAssignments currentAssignments = PageFactory.initElements(Driver.driver, CurrentAssignments.class);
            new Navigator().NavigateTo("Assignments");

            Driver.driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();
            new UIElement().waitAndFindElement(By.cssSelector("div[class='tab active']"));
            if (Driver.driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources")) {//Opening All Resources tab if not opened after clicking on New Assignment button

                Driver.driver.findElement(By.cssSelector("span[title='All Resources']")).click();
            }
            currentAssignments.getUsePreCreatedAssignment_button().click();//click on Use Pre-created Assignment on Create New assignment pop-up
            new UIElement().waitAndFindElement(By.id("all-resource-search-textarea"));
            //Adding assignment to search
            Driver.driver.findElement(By.id("all-resource-search-textarea")).clear();
            System.out.println("Asssessment Searched : " + assignmentname +"_"+appendChar);
            Driver.driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname +"_"+appendChar+ "\"");
            Driver.driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='assign-this']"));
            Thread.sleep(5000);
            Driver.driver.findElement(By.cssSelector("span[class='assign-this']")).click();
            //new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, true);
            new UIElement().waitAndFindElement(By.id("due-time"));

            Driver.driver.findElement(By.id("due-time")).click();
            Driver.driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            new UIElement().waitAndFindElement(By.id("due-date"));

            Driver.driver.findElement(By.id("due-date")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
            Driver.driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            Driver.driver.findElement(By.linkText(duedate)).click();

            if(gradeable != null){
                if (gradeable.equals("true")) {
                    new UIElement().waitAndFindElement(By.className("ir-ls-assign-dialog-gradable-label-check"));
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
                }
            }else{
                if(testData!=null) {
                    if (Arrays.asList(testData).contains("gradable")) {
                        new UIElement().waitAndFindElement(By.className("ir-ls-assign-dialog-gradable-label-check"));
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
                    }
                }
            }


            if(gradeable != null){
                if (gradeable.equals("true") && assignmentpolicy != null) {
                    //click on  Choose your assignment policy dropdown
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                    new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                    Driver.driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
                }
            }else{
                if (Arrays.asList(testData).contains("gradable")&& assignmentpolicy == null) {
                    //click on  Choose your assignment policy dropdown
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                    new UIElement().waitAndFindElement(By.xpath("//div[@class='overview']//li//a[1]"));
                    Driver.driver.findElement(By.xpath("((//div[@class='viewport'])[8]//a)[3]")).click();//select a policy
                }
            }



            if (accessibleafter != null) {
                Driver.driver.findElement(By.id("accessible-date")).click();
                Driver.driver.findElement(By.xpath("//a[@title='Next']")).click();
                Driver.driver.findElement(By.linkText(accessibleafter)).click();
            }


            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            new UIElement().waitAndFindElement(By.id("additional-notes"));
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);

            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")));
            new UIElement().waitTillInvisibleElement(By.cssSelector("span[class='btn sty-green submit-assign']"));

        } catch (Exception e) {
            Assert.fail("Exception in  assignToStudent in AppHelper class Assignment", e);
        }
    }

    public void AssignAssessmentwithgradingPolicy(String dataIndex, boolean sharewithstudent) {
        try {
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", dataIndex);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", dataIndex);
            String asignmentReference = ReadTestData.readDataByTagName("", "asignmentReference", dataIndex);
            String policyname = ReadTestData.readDataByTagName("", "resourcesname", dataIndex);
            String navigateTo = ReadTestData.readDataByTagName("", "navigateTo", dataIndex);


            if(navigateTo!=null){
                if(navigateTo.equals("My Question Bank")){
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("My Question Bank");

                }
            }else{
                new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Question Banks");
                //Adding assignment to search
                Driver.driver.findElement(By.id("all-resource-search-textarea")).clear();
                Driver.driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                Driver.driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            }
            new UIElement().waitAndFindElement(By.className("assign-this"));
            Driver.driver.findElement(By.className("assign-this")).click();
            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            if (sharewithstudent == true) {
                new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, false);
            }
            new UIElement().waitAndFindElement(By.id("due-time"));
            Driver.driver.findElement(By.id("due-time")).click();
            List<WebElement> elements = Driver.driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals(duetime)) {
                    time.click();
                    break;
                }
            }
            Driver.driver.findElement(By.id("due-date")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
            Driver.driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            Driver.driver.findElement(By.linkText(duedate)).click();
            if (gradeable.equals("true")) {
                new UIElement().waitAndFindElement(By.className("ir-ls-assign-dialog-gradable-label-check"));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
            }
            if (gradeable.equals("true") && assignmentpolicy != null) {
                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Select an Assignment Policy")));
                new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                Driver.driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            }
            if (accessibleafter != null) {
                Driver.driver.findElement(By.id("accessible-date")).click();
                Driver.driver.findElement(By.linkText(accessibleafter)).click();
            }

            if(asignmentReference!=null){

                if (System.getProperty("UCHAR") == null) {
                    policyname = policyname + LoginUsingLTI.appendChar;
                } else {
                    policyname = policyname + System.getProperty("UCHAR");
                }
                Driver.driver.findElement(By.linkText("Choose your Assignment Reference")).click();
                new UIElement().waitAndFindElement(By.cssSelector("a[title='" + policyname + "']"));
                new Click().clickBycssselector("a[title='" + policyname + "']");
            }
            new UIElement().waitAndFindElement(By.id("additional-notes"));
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);
            Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();
            new UIElement().waitTillInvisibleElement(By.cssSelector("span[class='btn sty-green submit-assign']"));

        } catch (Exception e) {
            Assert.fail("Exception in  assignToStudent in AppHelper class Assignment", e);
        }
    }

    public void updateAssignment(String dataIndex, boolean addShareWith) {
        try {
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);

            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Summary");
            //Selecting third and fourth filter values
            Driver.driver.findElement(By.linkText("All Assignment Status")).click();
            new UIElement().waitAndFindElement(By.linkText("Available for Students"));
            Driver.driver.findElement(By.linkText("Available for Students")).click();
            new UIElement().waitAndFindElement(By.linkText("All Activity"));
            Driver.driver.findElement(By.linkText("All Activity")).click();
            if (gradeable == null)
                Driver.driver.findElement(By.linkText("Question Practice")).click();
            else if (gradeable.equals("false"))
                Driver.driver.findElement(By.linkText("Question Practice")).click();
            else
                Driver.driver.findElement(By.linkText("Question Assignment")).click();

            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));

            List<WebElement> assignmentNames = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            int index = 0;
            for (int i = 0; i < assignmentNames.size(); i++) {
                if (assignmentNames.get(i).getText().substring(7).equals(assignmentname)) {
                    break;
                }
                index++;
            }
            Driver.driver.findElements(By.cssSelector("span[class='assign-more']")).get(index).click(); //click on Update Assignment in Assignments page only
            new UIElement().waitAndFindElement(By.className("assignment-update-reassign"));
            new Click().clickByclassname("assignment-update-reassign");//click on Reassign
            if (addShareWith == true)
                new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, false);

            if (duedate != null) {
                Driver.driver.findElement(By.id("due-date")).click();
                new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
                Driver.driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
                new UIElement().waitAndFindElement(By.linkText(duedate));
                Driver.driver.findElement(By.linkText(duedate)).click();


            }
            new UIElement().waitAndFindElement(By.cssSelector("span[class='btn sty-green submit-assign']"));
            Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();

            //no update link in  My Resource page
		/*new Navigator().NavigateTo("Assignments");
		//Clicking on New Assignment Button
		Driver.driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();
		Thread.sleep(3000);
		if(Driver.driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources"))
		{//Opening All Resources tab if not opened after clicking on New Assignment button

			Driver.driver.findElement(By.cssSelector("span[title='All Resources']")).click();
		}
		//Adding assignment to search
		Driver.driver.findElement(By.id("all-resource-search-textarea")).clear();
	    Driver.driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\""+assignmentname+"\"");
	    Driver.driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
	    Thread.sleep(2000);
	    Driver.driver.findElement(By.cssSelector("span[class='assign-this update-assignment']")).click();

	    //Driver.driver.findElement(By.cssSelector("span[class='assign-this update-assignment']")).click();

	    if(addShareWith == true)
		new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,false);

	    Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();
	    */
        } catch (Exception e) {
            Assert.fail("Exception in app helper updateAssignment in class Assignment", e);
        }

    }

    public void submitAssignmentAsStudent(String dataIndex) {
        try {
            String submitassignment = ReadTestData.readDataByTagName("", "submitassignment", dataIndex);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String usehint = ReadTestData.readDataByTagName("", "usehint", dataIndex);
            String answerchoice = ReadTestData.readDataByTagName("", "answerchoice", dataIndex);
            new Navigator().NavigateTo("Course Stream");
            int helppage = Driver.driver.findElements(By.className("close-help-page")).size();
            if (helppage == 1)
                Driver.driver.findElement(By.className("close-help-page")).click();
            //new Click().clickbyxpath("//span[contains(@title,'"+assessmentname+"')]");
            new Click().clickBycssselector("span[assignmentname='" + assessmentname+"_"+appendChar+"']");//click on Assignment
            Thread.sleep(20000);
            /*int index = 0;
            List<WebElement> allAssignment = Driver.driver.findElements(By.cssSelector("span[class='ls-stream-assignment-title']"));
            for(WebElement assignment:allAssignment)
            {
                if (assignment.getText().substring(7).equals(assessmentname)) {
                    break;
                } else
                    index++;
            }
            new Click().clickbylistcssselector("span[class='ls-stream-assignment-title']", index);//click on Assignment*/
            boolean useHint = false;//by default useHint is set to false
            if (usehint != null)//read the tag from testdata
                useHint = true;//set useHint to true

            if (answerchoice == null)
                answerchoice = "correct";
            int timer = 1;
            while (timer != 0) {
             Thread.sleep(3000);
                //String questionText =Driver.driver.findElement(By.id("question-edit")).getText();
                String questionText = (new WebDriverWait(Driver.driver, 200)).until(ExpectedConditions.presenceOfElementLocated(By.id("question-edit"))).getText();
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

          /* if(questionText.contains("Match the Following"))
               new AttemptQuestion().matchTheFollowing(useHint, answerchoice, dataIndex);

           if(questionText.contains("Drag and Drop"))
               new AttemptQuestion().dragAndDrop(useHint, answerchoice, dataIndex);

           if(questionText.contains("Cloze Formula"))
               new AttemptQuestion().attemptClozeFormula(useHint, answerchoice, dataIndex);*/
                boolean lastQuestionNotToSubmitAssignment = submitAnswer(submitassignment, dataIndex);
                if (lastQuestionNotToSubmitAssignment == true)
                    timer = 0;
                else {
                    try {
                        Driver.driver.findElement(By.cssSelector("span[id='timer-label']"));
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
        try {
            int timer = 1;

            while (timer == 1) {
                timer = Driver.driver.findElements(By.id("assessmentTimer")).size();
                if (timer == 0)
                    break;
                int valueofoption = Driver.driver.findElements(By.className("qtn-label")).size();
                if (valueofoption >= 1)
                    Driver.driver.findElement(By.className("qtn-label")).click();

			/*	List<WebElement> textbox = Driver.driver.findElements(By.tagName("input"));
				forloop:
				for(WebElement box : textbox)
				{
					if(box.isDisplayed() == true)
					{
						box.sendKeys("abcd");
						break forloop;
					}
				}*/
                int submitbutton = Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).size();
                if (submitbutton >= 1)
                    Driver.driver.findElement(By.linkText("Submit")).click(); //submitting the assignment as student
                else
                    Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).click();//click on next button

            }


        } catch (Exception e) {
            Assert.fail("Exception in app helper attemptingQuestionAsStudent in class Assignment", e);
        }
    }

    public void statusValidate(String dataIndex, String expectedStatus) {
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
        String statuscolor = ReadTestData.readDataByTagName("", "statuscolor", dataIndex);
        new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignments");
        int index = 0;
        List<WebElement> assignments = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
        for (WebElement element : assignments) {
            if (element.getText().contains(assessmentname)) {
                break;
            }
            index++;
        }
        List<WebElement> status = Driver.driver.findElements(By.className("ls-assignment-status"));

        if (!status.get(index).getText().contains(expectedStatus))
            Assert.fail("Status of the asignment in Instructor Dashboard is " + status.get(index).getText() + " which is not equal to expected status: " + expectedStatus);
        if (statuscolor != null) {
            if (expectedStatus.contains("Status:  Grading in Progress")) {
                WebElement statuscolors = Driver.driver.findElement(By.cssSelector("span[title='Grading in Progress']"));
                if (!statuscolors.getCssValue("color").equals(statuscolor))
                    Assert.fail("Status color is " + statuscolors.getCssValue("color") + " which is not equal to expected color " + statuscolor);
            }
            if (expectedStatus.contains("Status:  Review in Progress")) {
                WebElement statuscolors = Driver.driver.findElement(By.cssSelector("span[title='Review in Progress']"));
                if (!statuscolors.getCssValue("color").equals(statuscolor))
                    Assert.fail("Status color is " + statuscolors.getCssValue("color") + " which is not equal to expected color " + statuscolor);
            }
            if (expectedStatus.contains("Status:  Graded") || expectedStatus.contains("Status:  Reviewed")) {
                WebElement statuscolors = Driver.driver.findElement(By.cssSelector("span[class='ls-assignment-status-grades-released']"));
                if (!statuscolors.getCssValue("color").equals(statuscolor))
                    Assert.fail("Status color is " + statuscolors.getCssValue("color") + " which is not equal to expected color " + statuscolor);
            }
        }
        //List<WebElement> firstblock =  Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-first not-started']"));
        //System.out.println(firstblock.get(index).getText());
    }


    public int statusBoxCount(String dataIndex, String boxName) {
        String[] cntarray = {""};
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
        new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignments");
        int index = 0;
        List<WebElement> assignments = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
        for (WebElement element : assignments) {
            if (element.getText().contains(assessmentname)) {
                break;
            }
            index++;
        }
        if (boxName.equals("Not Started")) {
            List<WebElement> notstartedbox = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-first not-started']"));

            String cnt = notstartedbox.get(index).getText();
            cntarray = cnt.split("\\n");
        }
        if (boxName.equals("In Progress")) {
            List<WebElement> inprogress = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-second in-progress']"));

            String cnt = inprogress.get(index).getText();
            cntarray = cnt.split("\\n");
        }
        if (boxName.equals("Submitted")) {
            List<WebElement> submitted = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-third submitted']"));

            String cnt = submitted.get(index).getText();
            cntarray = cnt.split("\\n");
        }
        if (boxName.equals("Graded")) {
            List<WebElement> graded = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));

            String cnt = graded.get(index).getText();
            cntarray = cnt.split("\\n");
        }
        if (boxName.equals("Reviewed")) {
            List<WebElement> graded = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));

            String cnt = graded.get(index).getText();
            cntarray = cnt.split("\\n");
        }
        return Integer.parseInt(cntarray[0]);

    }


    public void statusBoxCheckInInstructorDashBoard(String dataIndex) {
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String notStartedCount = ReadTestData.readDataByTagName("", "notstarted", dataIndex);
            String inProgressCount = ReadTestData.readDataByTagName("", "inprogress", dataIndex);
            String submittedCount = ReadTestData.readDataByTagName("", "submitted", dataIndex);
            String reviewedCount = ReadTestData.readDataByTagName("", "reviewed", dataIndex);
            String gradedCount = ReadTestData.readDataByTagName("", "graded", dataIndex);
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }

            List<WebElement> notstarted = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-first not-started']"));

            List<WebElement> inprogress = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-second in-progress']"));

            List<WebElement> submitted = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-third submitted']"));

            List<WebElement> reviewed = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));

            List<WebElement> graded = Driver.driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']"));

            if (!notstarted.get(index).getText().replaceAll("[\n\r]", "").equals(notStartedCount + "Not Started")) {
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

    public void releaseGrades(String dataIndex, String releaseAction) {
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> viewresponseslink = Driver.driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
            //viewresponseslink.get(2).click();

            Driver.driver.findElement(By.cssSelector("div[title='" + releaseAction + "']")).click();
        } catch (Exception e) {
            Assert.fail("Exception in app helper releaseGrades in class Assignment", e);
        }
    }

    public void gradesValidation(String dataIndex) {
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignments");
            new ComboBox().selectValue(3, "Graded");
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            int index = 0;
            List<WebElement> assignments = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> viewgradeslink = Driver.driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
            if (!viewgradeslink.get(index).isDisplayed())
                Assert.fail("View Grades link not displayed");
            if (!viewgradeslink.get(index).getText().equals("View Grades"))
                Assert.fail("View Grades link text is not as expected");

            List<WebElement> graphs = Driver.driver.findElements(By.className("ls-assignment-performance-graph")); //validating the grade graph by clicking it
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", graphs.get(index));

            List<WebElement> xaxis = Driver.driver.findElements(By.className("ls-assignment-performance-graph"));
            if (!xaxis.get(index).getText().contains("Number of Students"))
                Assert.fail("x-axis of grade graph not shown as 'Number of Students'");

            List<WebElement> yaxis = Driver.driver.findElements(By.className("ls-assignment-performance-graph"));
            if (!yaxis.get(index).getText().contains("Score Range"))
                Assert.fail("y-axis of grade graph not shown as 'Score Range'");
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", viewgradeslink.get(index));
            new UIElement().waitAndFindElement(By.className("idb-gradebook-header-div"));
            if (!Driver.driver.findElement(By.className("idb-gradebook-header-div")).getText().equals("Assignment Responses"))
                Assert.fail("The Assignment Responses tab not opened after clicking on the view responses link in the assignments page");
            //viewresponseslink.get(2).click();


        } catch (Exception e) {
            Assert.fail("Exception in app helper gradesValidation in class Assignment", e);
        }
    }

    public void assignmentValidate(String dataIndex) {
        try {
            String fullName = ReadTestData.readDataByTagName("", "fullName", dataIndex);
            String y[] = fullName.split(" ");
            fullName = y[1] + ", " + y[0];//reverse the name with comma in between
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            List<String> stringarray = new ArrayList<String>();
            List<WebElement> allInstructorName = Driver.driver.findElements(By.className("ls-assignment-item-author-name"));
            for (WebElement name : allInstructorName)
                stringarray.add(name.getText());

            if (!stringarray.contains(fullName))
                Assert.fail("Instructor Name is not present after posting");


            stringarray.clear();

            List<WebElement> allAssesmentName = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));

            for (WebElement name : allAssesmentName)
                stringarray.add(name.getText());
            String[] allNames = stringarray.toArray(new String[stringarray.size()]);
            boolean contains = false;
            for (int i = 0; i < allNames.length; i++) {
                String str = allNames[i];
                if (str.contains(assessmentname)) {
                    contains = true;
                    break;
                }
            }
            if (contains == false)
                Assert.fail("Assesment name is not present in Current Assignment page.");


        } catch (Exception e) {
            Assert.fail("Exception in  assignmentValidate in AppHelper class Assignment", e);
        }
    }

    public void provideGRadeToStudent(String dataIndex) {
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String studentname = ReadTestData.readDataByTagName("", "studentname", dataIndex);

            if(studentname ==null){
                studentname = "iPadIntegration Student";
            }

            String y[] = studentname.split(" ");
            studentname = y[1] + ", " + y[0];//reverse the name with comma in between

            //String gradeLinkIndex = ReadTestData.readDataByTagName("", "gradeLinkIndex", dataIndex);



            if(assessmentname==null){
                String packageName = new Exception().getStackTrace()[1].getClassName();
                String methodName = new Exception().getStackTrace()[1].getMethodName();

                packageName = packageName.substring(64);
                packageName = packageName.replaceAll("\\.", "_");

                if(assessmentname==null){
                    assessmentname  = dataIndex+ "_Assessment_"+packageName+"_"+methodName+"_"+appendChar;
                }
            }



            new Navigator().NavigateTo("Assignments");

            int index = 0;
            List<WebElement> assignments = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> viewresponseslink = Driver.driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
            List<WebElement> menuitem = Driver.driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            for (WebElement user : menuitem) {
                if (user.getText().equals(studentname)) {
                    try {
                        MouseHover.mouserhoverbywebelement(user);
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("idb-grade-now-link")));
                    } catch (Exception e) {
                        MouseHover.mouserhoverbywebelement(user);
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("idb-grade-now-link")));
                    }
                    /*Driver.driver.findElement(By.id("idb-grade-now-link")).click();*/
                    new UIElement().waitAndFindElement(By.cssSelector("input[class='idb-grade-points']"));
                    Driver.driver.findElement(By.cssSelector("input[class='idb-grade-points']")).clear();
                    new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@class, 'idb-grade-points')]"));
                    Driver.driver.findElement(By.xpath("//*[starts-with(@class, 'idb-grade-points')]")).sendKeys("0.6");
                    new UIElement().waitAndFindElement(By.cssSelector("input[class='idb-grade-points']"));
                    Driver.driver.findElement(By.cssSelector("input[class='idb-grade-points']")).sendKeys(Keys.TAB);
                    new UIElement().waitAndFindElement(By.id("ls-assignment-not-started-count"));
                    Driver.driver.findElement(By.id("ls-assignment-not-started-count")).click(); //clicking on not started box to save the marks
                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in apphelper provideGRadeToStudent in class Assignment", e);
        }
    }

    public void likeAssignment(String dataIndex) {
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> likelinks = Driver.driver.findElements(By.className("ls-post-like-link"));
            if (!likelinks.get(index).getText().equals("Like")) //Validating if assignment is liked to unlike it
                Assert.fail("The assignment is already liked so can not like it again.");
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", likelinks.get(index)); //Clicking on like

            List<WebElement> likecounts = Driver.driver.findElements(By.className("ls-post-like-count"));
            String likecount = likecounts.get(index).getText(); //Validating link count
            if (!likecount.equals("1"))
                Assert.fail("Like count of the assignment not equal to 1");

            List<WebElement> likelinksafterclicking = Driver.driver.findElements(By.className("ls-post-like-link"));
            String likelinkafterclicking = likelinksafterclicking.get(index).getText(); //Validating like changed to unlike
            if (!likelinkafterclicking.equals("Unlike"))
                Assert.fail("Like link not converted to unlike after clicking on it");

        } catch (Exception e) {
            Assert.fail("Exception in apphelper likeAssignment in class Assignment", e);
        }
    }

    public void unlikeAssignment(String dataIndex) {
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> likelinks = Driver.driver.findElements(By.className("ls-post-like-link"));
            if (!likelinks.get(index).getText().equals("Unlike")) //Validating if assignment is liked to unlike it
                Assert.fail("The assignment is not liked so can not unlike it.");
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", likelinks.get(index)); //Clicking on unlike

            List<WebElement> likecounts = Driver.driver.findElements(By.className("ls-post-like-count"));
            String likecount = likecounts.get(index).getText(); //Validating link count
            if (!likecount.equals("0"))
                Assert.fail("Like count of the assignment not equal to 1");

            List<WebElement> likelinksafterclicking = Driver.driver.findElements(By.className("ls-post-like-link"));
            String likelinkafterclicking = likelinksafterclicking.get(index).getText(); //Validating like changed to unlike
            if (!likelinkafterclicking.equals("Like"))
                Assert.fail("Unlike link not converted to Like after clicking on it");

        } catch (Exception e) {
            Assert.fail("Exception in apphelper likeAssignment in class Assignment", e);
        }
    }


    public void commentAssignment(String dataIndex) {
        try {
            String random = new RandomString().randomstring(5);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> allComments = Driver.driver.findElements(By.cssSelector("a[title='Comments']"));
            allComments.get(index).click();//click on Comment Link
            Driver.driver.switchTo().activeElement().sendKeys(random + Keys.RETURN);
            //Searching for comment posted
            boolean commentfound = false;
            List<WebElement> comments = Driver.driver.findElements(By.className("ls-stream-post__comment-text"));
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

    public int commentCount(String dataIndex) {
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
        new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignments");
        int index = 0;
        List<WebElement> assignments = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
        for (WebElement element : assignments) {
            if (element.getText().contains(assessmentname)) {
                break;
            }
            index++;
        }
        List<WebElement> commentscount = Driver.driver.findElements(By.className("ls-stream-post-comment-count"));
        return Integer.parseInt(commentscount.get(index).getText());
    }

    public void assignFormValidate(String dataIndex) {
        try {
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            Driver.driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();
            new UIElement().waitAndFindElement(By.cssSelector("div[class='tab active']"));
            if (Driver.driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources"))    //Opening All Resources tab if not opened after clicking on New Assignment button
                Driver.driver.findElement(By.className("tab")).click();

            //Adding assignment to search
            Driver.driver.findElement(By.id("all-resource-search-textarea")).clear();
            Driver.driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            Driver.driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[title='Assign This']"));
            Driver.driver.findElement(By.cssSelector("span[title='Assign This']")).click();
		/*List<WebElement> assign = Driver.driver.findElements(By.className("assign-this"));

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

    public String provideFeedback(String dataIndex) {
        String feedback = null;
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String studentname = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            feedback = ReadTestData.readDataByTagName("", "feedback", dataIndex);

            if(assessmentname==null){
                String packageName = new Exception().getStackTrace()[1].getClassName();
                String methodName = new Exception().getStackTrace()[1].getMethodName();

                packageName = packageName.substring(64);
                packageName = packageName.replaceAll("\\.", "_");

                if(assessmentname==null){
                    assessmentname  = dataIndex+ "_Assessment_"+packageName+"_"+methodName+"_"+appendChar;
                }
            }

            if(feedback==null){
                feedback = "This is a FeedbackText";
            }

            new Navigator().NavigateTo("Assignments");
            int index = 0;
            //finding the index of the current assessment
            List<WebElement> assignments = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> viewresponseslink = Driver.driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
            new UIElement().waitAndFindElement(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            //finding the index of the particular student
            int index1 = 0;
            List<WebElement> usernames = Driver.driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            for (WebElement element : usernames) {
                if (element.getText().equals(studentname)) {

                    break;
                }
                index1++;
            }

            List<WebElement> gradebook = Driver.driver.findElements(By.className("idb-question-score-wrapper"));

					 /*Locatable hoverItem = (Locatable) gradebook.get(index1);
					 Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());*/
            MouseHover.mouserhover("idb-gradebook-question-content");
            new UIElement().waitAndFindElement(By.className("ls-view-response-link"));

            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-view-response-link")));

            Driver.driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys(feedback);

            // Driver.driver.findElement(By.className("view-user-question-performance-save-btn")).click();
            Driver.driver.findElement(By.xpath("//span[text()='Save']")).click(); //click on save button

        } catch (Exception e) {
            Assert.fail("Exception in  provideFeedback in AppHelper class Assignment", e);
        }
        return feedback;
    }

    public void submitAssignmentAsStudentFromAssignmentsnavigation(String dataIndex) {
        try {
            String submitassignment = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignments");
            List<WebElement> allElements = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : allElements) {
                if (element.getText().trim().contains(submitassignment)) {
                    element.click();
                    int helppage = Driver.driver.findElements(By.className("close-help-page")).size();
                    if (helppage == 1)
                        Driver.driver.findElement(By.className("close-help-page")).click();
                    new UIElement().waitAndFindElement(By.className("qtn-label"));
                    Driver.driver.findElement(By.className("qtn-label")).click();
                    new UIElement().waitAndFindElement(By.linkText("Submit"));
                    Driver.driver.findElement(By.linkText("Submit")).click(); //submitting the assignment as student 1
                    break;
                }
            }
        } catch (Exception e) {
            Assert.fail("Exception in app helper submitAssignmentAsStudent in class Assignment", e);
        }
    }

    public void clickonAssignment(String assignmentName) {
        try {

            if (Driver.driver.getCurrentUrl().contains("lsStudentDashBoard")) {
                new Click().clickBycssselector("span[title='(shor)  " + assignmentName + "']");
            } else
                new Click().clickBycssselector("span[title='(shor) " + assignmentName + "']");


            /*List<WebElement> allelements=Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for(WebElement elements:allelements)
            {
                String assigntext=elements.getText();

                if(assigntext.contains(text))
                {

                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",elements);
                    //elements.click();
                    int helppage = Driver.driver.findElements(By.className("close-help-page")).size();
                    if(helppage!=0)
                        Driver.driver.findElement(By.className("close-help-page")).click();
                    break;
                }
            }*/
        } catch (Exception e) {
            Assert.fail("Exception in apphelper clickonAssignment in class Assignments", e);
        }
    }


    public void createAssignmentAtTopicLevel(String dataIndex) {
        try {
            String course = Config.course;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String questionset = ReadTestData.readDataByTagName("", "questionset", dataIndex);
            String questiontype = ReadTestData.readDataByTagName("", "questiontype", dataIndex);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", dataIndex);
            String topicname = ReadTestData.readDataByTagName("", "topicname", dataIndex);
            String reservforassignment = ReadTestData.readDataByTagName("", "reservforassignment", dataIndex);
            String practice_type = ReadTestData.readDataByTagName("", "practice_type", dataIndex);

            new DirectLogin().CMSLogin();
            String title = Driver.driver.getTitle();
            if (title.equals("Course Content Details")) {
                Driver.driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                int index = 0;
                //Find the chapter index
                List<WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
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
                List<WebElement> expansionSymbol = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'expand-chapter-tree expand')]"));
                expansionSymbol.get(index).click();
                new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@class, 'course-topic-label node')]"));
                //List the topic and click on a topic
                List<WebElement> allTopic = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-topic-label node')]"));
                if (topicname != null) {
                    for (WebElement topic : allTopic) {
                        if (topic.getText().equals(topicname)) {
                            topic.click();
                            break;
                        }

                    }
                } else
                    allTopic.get(0).click();
                Driver.driver.findElement(By.cssSelector("div.create-practice")).click();
                if (practice_type == null) {
                    new Click().clickByclassname("create-regular-assessment-popup-item"); //click on the create regular assessment
                } else {
                    new Click().clickByclassname("create-file-assessment-popup-item");//click on the create file assessment
                }

                new Click().clickbylinkText("Adaptive Component Diagnostic");
                new Click().clickbylinkText(questiontype);
                int popup = Driver.driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
                if (popup == 0) {
                    Driver.driver.findElement(By.id("assessmentName")).click();
                    Driver.driver.findElement(By.id("assessmentName")).clear();
                    Driver.driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
                    Driver.driver.findElement(By.id("questionSetName")).clear();
                    Driver.driver.findElement(By.id("questionSetName")).sendKeys(questionset);

                    if (reservforassignment == null) {
                        Driver.driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
                    }

                    new QuestionCreate().trueFalseQuestions(dataIndex);
                }

            }

        } catch (Exception e) {
            Assert.fail("Exception in  createAssignmentAtTopicLevel in AppHelper class Assignment", e);
        }
    }


    public void clickViewResponse(String assessmentname) {
        try {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            //click on 'View Responses link'
            List<WebElement> viewresponseslink = Driver.driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
        } catch (Exception e) {
            Assert.fail("Exception in  clickViewResponse in AppHelper class Assignment", e);
        }
    }

    public void viewResponseOfDW() {
        try {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignments");    //navigate to Assignments
            Driver.driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();    //click on View Student Responses
            new UIElement().waitAndFindElement(By.className("idb-gradebook-question-content"));
            Actions ac = new Actions(Driver.driver);
            try {
                ac.moveToElement(Driver.driver.findElement(By.className("idb-gradebook-question-content"))).build().perform();
                new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='ls-view-response-link']")));
                ac.moveToElement(Driver.driver.findElement(By.cssSelector("div[class='ls-view-response-link']"))).build().perform();
            } catch (Exception e) {
                try {
                    ac.moveToElement(Driver.driver.findElement(By.className("idb-gradebook-question-content"))).build().perform();
                    new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='ls-view-response-link']")));
                    ac.moveToElement(Driver.driver.findElement(By.cssSelector("div[class='ls-view-response-link']"))).build().perform();
                }
                catch (Exception e1) {
                    ac.moveToElement(Driver.driver.findElement(By.className("idb-gradebook-question-content"))).build().perform();
                    new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='ls-view-response-link']")));
                    ac.moveToElement(Driver.driver.findElement(By.cssSelector("div[class='ls-view-response-link']"))).build().perform();
                }
            }
            /*        MouseHover.mouserhover("idb-gradebook-question-content");
            MouseHover.mouserhover("ls-view-response-link");*/
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='ls-view-response-link']")));    //click on View Response link
        } catch (Exception e) {
            Assert.fail("Exception in  clicking view response link of Discussion widget", e);
        }
    }

    public void postCommentInAssessmentResponseTab() {
        try {
            List<WebElement> comments = Driver.driver.findElements(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']"));
            comments.get(1).click();
            String random = new RandomString().randomstring(5);
            //Post a comment
            List<WebElement> commentarea = Driver.driver.findElements(By.className("ls-textarea-focus"));
            commentarea.get(1).sendKeys(random + Keys.ENTER);
            //Searching for comment posted
            boolean commentfound = false;
            List<WebElement> postedcomments = Driver.driver.findElements(By.className("ls-stream-post__comment-text"));
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

    public void provideGradeToStudentForMultipleQuestions(String dataIndex) {
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String studentname = ReadTestData.readDataByTagName("", "studentname", dataIndex);
            String y[] = studentname.split(" ");
            studentname = y[1] + ", " + y[0];//reverse the name with comma in between

            String grade = ReadTestData.readDataByTagName("", "grade", dataIndex);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> viewresponseslink = Driver.driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
            List<WebElement> menuitem = Driver.driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            int gradeLinkIndex = 0;
            for (WebElement user : menuitem) {
                if (user.getText().equals(studentname)) {
                    /*Locatable hoverItem = (Locatable) user;
                    Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
                    mouse.mouseMove(hoverItem.getCoordinates());*/
                    new UIElement().waitAndFindElement(user);
                    MouseHover.mouserhoverbywebelement(user);
                    new UIElement().waitAndFindElement(By.id("idb-grade-now-link"));
                    List<WebElement> gradenowlinks = Driver.driver.findElements(By.id("idb-grade-now-link"));
                    new UIElement().waitAndFindElement(gradenowlinks.get(gradeLinkIndex));
                    //gradenowlinks.get(gradeLinkIndex).click();
                    new Click().clickByElement(gradenowlinks.get(gradeLinkIndex));
                    new UIElement().waitAndFindElement(By.cssSelector("input[class='idb-grade-points']"));
                    List<WebElement> allGradeBox = Driver.driver.findElements(By.cssSelector("input[class='idb-grade-points']"));
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
                    Driver.driver.findElement(By.xpath("/html/body")).click();
                }
                gradeLinkIndex++;
            }

        } catch (Exception e) {
            Assert.fail("Exception in apphelper provideGradeToStudentForMultipleQuestions in class Assignment", e);
        }
    }

    public void addQuestionsWithCustomizedQuestionText(String dataIndex, String questionType, String assignmentname, int noOfQuestions) {

        try {

            assignmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", dataIndex);
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", dataIndex);
            String course = Config.course;
            new DirectLogin().CMSLogin();
            String title = Driver.driver.getTitle();
            if (title.equals("Course Content Details")) {
                Driver.driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null)
                    new Click().clickBycssselector("div.course-chapter-label.node");

                else if (courselevel != null)
                    new Click().clickBycssselector("div[class='course-label node']");

                else {
                    new UIElement().waitAndFindElement(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                    if( Driver.driver.findElements(By.xpath("//*[contains(text(),'"+chapterName+"')]")).size()>1)
                    {
                        System.out.println("inside if");
                        new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.TopicOpen().openCMSLastChapter();
                    }
                    else {
                        System.out.println("inside else");
                        List<WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                        for (WebElement chapters : allChapters) {
                            if (chapters.getText().contains(chapterName)) {
                                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters);
                                break;
                            }

                        }
                    }

                }

                List<WebElement> elements = Driver.driver.findElements(By.xpath("//div[@title='" + assignmentname + "']"));
                try {
                    elements.get(elements.size() - 1).click();
                }
                catch (Exception e) {

                }
              /*  List<WebElement> elements = Driver.driver.findElements(By.className("collection-assessment-name"));
                for (WebElement content : elements) {
                    if (content.getText().trim().equals(assignmentname)) {

                        Thread.sleep(3000);
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", content);
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
                    else if (questionType.equals("audio") || questionType.equals("qtn-audio-type"))
                        new QuestionCreate().audio(dataIndex);
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
    public void createChapter(String dataIndex, int... tloid) {
        try {
            String newChapterName = ReadTestData.readDataByTagName("", "newChapterName", dataIndex);
            String course = Config.course;
            new DirectLogin().CMSLogin();
            Driver.driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
            //Driver.driver.findElement(By.id("manage-toc")).click();
            new UIElement().waitAndFindElement(By.id("manage-toc"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("manage-toc")));
            Thread.sleep(5000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div.add-new.add-new-chapter > span.f1")));
            Thread.sleep(3000);
            Driver.driver.switchTo().activeElement().sendKeys(newChapterName);
            Thread.sleep(3000);
            Driver.driver.findElement(By.cssSelector("div[id='body-content-wrapper']")).click();
            Thread.sleep(3000);
            List<WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label node course-chapter-label-unpublished')]"));
            for (WebElement chapters : allChapters) {
                if (chapters.getText().contains(newChapterName)) {
                    Locatable hoverItem = (Locatable) chapters;
                    Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
                    mouse.mouseMove(hoverItem.getCoordinates());
                }
            }
            //associate TLO
            if (tloid.length > 0) {
                Driver.driver.findElement(By.xpath("//span[@class='tree-node-associate-icon']")).click();//click on the tlo icon
                WebDriverWait wait = new WebDriverWait(Driver.driver, 30);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[title='Add Learning Objective']")));

                Driver.driver.findElement(By.cssSelector("span[title='Add Learning Objective']")).click();
                Thread.sleep(500);
                Driver.driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[3]/label")).click();
                new Click().clickBycssselector("span.add-collection-to-subtopic");//click on Associate
                Thread.sleep(3000);
            }

        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper createChapter in class AssignmentCreate", e);
        }
    }
    public void OpenAssignment(String assignmentname, String dataIndex) {
        try {
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", dataIndex);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);

            String course = Config.course;
            new DirectLogin().CMSLogin();
            String title = Driver.driver.getTitle();
            if (title.equals("Course Content Details")) {
                Driver.driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                } else {
                    List<WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                    for (WebElement chapters : allChapters) {
                        if (chapters.getText().contains(chapterName)) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters);
                            break;
                        }

                    }

                }
                //Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                /*List<WebElement> elements = Driver.driver.findElements(By.className("collection-assessment-name"));
                for (WebElement content : elements) {
                    if (content.getText().trim().equals(assignmentname)) {

                        Thread.sleep(3000);
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", content);
                        break;
                    }
                }*/
                List<WebElement> elements = Driver.driver.findElements(By.cssSelector("div[title = '"+assessmentName+"']"));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", elements.get(elements.size()-1));
            }
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper OpenAssignment in class AssignmentCreate", e);
        }
    }

    public void openAssignmentFromAssignmentPage(String dataIndex) {
        try {

            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignments");//navigate to Assignments
            Driver.driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();//click on assignment

            new UIElement().waitAndFindElement(By.className("question-label"));//Waiting for 180 seconds to let the assignment open
        }
        catch (Exception e) {

        }

    }


    public void openAssignmentFromQuestionBanksPageAsInstructor(String dataIndex) {
        try {

            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Question Banks");//navigate to Question Banks
            new Assignment().searchAssessmentInQuestionBanks(dataIndex);
            Driver.driver.findElement(By.className("ls-preview")).click();//click on preview button
            new UIElement().waitAndFindElement(By.className("question-label")); //Waiting for 180 seconds to let the assignment open

        }
        catch (Exception e) {

        }

    }

    public void duplicatQuestion() {
        try {
            new Click().clickByid("questionOptions");
            new Click().clickByid("copyQuestion");
            new ComboBox().selectValue(5, "Publish");
            Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper duplicatQuestion in class AssignmentCreate", e);
        }
    }

    //assign assignment from Assignemnt tab of eTextbook
    public void assignAssignmentFromEtextBook(String dataIndex, int assignThisIndex) {
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
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("e-Textbook");//go to e-Textbook
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.TOCShow().tocHide(); //close the TOC
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().navigateToTab("Assignments");   //go to Assignments tab
            List<WebElement> allAssignThisButton = Driver.driver.findElements(By.cssSelector("span[class='ls-assign-this-sprite-right-tab assign-resource-bg']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allAssignThisButton.get(assignThisIndex));//click on AssignThis button
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, true);
            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            new UIElement().waitAndFindElement(By.id("due-time"));

            Driver.driver.findElement(By.id("due-time")).click();//click on dur time
            new UIElement().waitAndFindElement(By.xpath("//li"));
            List<WebElement> elements = Driver.driver.findElements(By.xpath("//li"));//fetch all time section
            for (WebElement time : elements) {
                if (time.getText().equals(duetime)) {
                    time.click();
                    break;
                }
            }
            Driver.driver.findElement(By.id("due-date")).click();//click on due date
            new UIElement().waitAndFindElement(By.cssSelector("a[title='Next']"));
            Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            Driver.driver.findElement(By.linkText(duedate)).click();//click on specified date
            if (gradeable != null) {
                if (gradeable.equals("true")) {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
                }
            }
            new UIElement().waitAndFindElement(By.id("additional-notes"));
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            new Click().clickBycssselector("span[class='btn sty-green submit-assign']");
            new UIElement().waitTillInvisibleElement(By.cssSelector("span[class='btn sty-green submit-assign']"));
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper assignAssignmentFromEtextBook in class Assignment", e);
        }
    }

    //open assignment from Assignment tan from eTextBook
    public void openAssignmentFromAssignmentTab(int openLinkIndex) {
        try {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("e-Textbook");//go to e-Textbook
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.TOCShow().tocHide(); //close the TOC
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().navigateToTab("Assignments");   //go to Assignments tab
            List<WebElement> allOpenLink = Driver.driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-cycle-bg']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allOpenLink.get(openLinkIndex));//click on open link
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper openAssignmentFromAssignmentTab in class Assignment", e);
        }
    }

    public void assignAssignmentFromAssignmentTab(int assignLink,String assessmentName) {
        try {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("e-Textbook");//go to e-Textbook
            new TOCShow().tocHide(); //close the TOC
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().navigateToTab("Assignments");   //go to Assignments tab
            int pos = 0;
            List<WebElement> allOpenLink = Driver.driver.findElements(By.className("ls_assessment_link"));
            for(int a=0;a<allOpenLink.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allOpenLink.get(a));
                if((allOpenLink.get(a).getText().equals(assessmentName))){
                    System.out.println("assessmentName:"+assessmentName);
                    break;
                }
                pos++;

            }

            List<WebElement> rightArrowElementsList = driver.findElements(By.className("ls-assignment-show-assign-this-block"));
            new Click().clickByElement(rightArrowElementsList.get(pos));
            List<WebElement> assignThisELementsList = Driver.driver.findElements(By.cssSelector("span[class='ls-assign-this-sprite-right-tab assign-resource-bg']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", assignThisELementsList.get(pos));//click on open link
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper assignAssignmentFromAssignmentTab in class Assignment", e);
        }
    }

    //open the 1st assignment from Course stream
    public void openAssignmentFromCourseStream(String dataIndex) {
        try {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Course Stream");
            new WebDriverWait(Driver.driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='ls-stream-assignment-title']")));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span[class='ls-stream-assignment-title']")));//click on Assignment
            new UIElement().waitTillInvisibleElement(By.cssSelector("span[class='ls-stream-assignment-title']"));
            //new WebDriverWait(Driver.driver, 800).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper openAssignmentFromCourseStream in class Assignment", e);
        }
    }

    //Author Sumit On 13/08/2014
    //submit the assignment with immediate feedback enabled using Assignment Policy.
    public void submitAssignmentWithImmediateFeedBack(String dataIndex) {
        try {
            String submitassignment = ReadTestData.readDataByTagName("", "submitassignment", dataIndex);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Course Stream");
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-stream-assignment-title']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span[class='ls-stream-assignment-title']")));//click on Assignment
            new WebDriverWait(Driver.driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));

            int helppage = Driver.driver.findElements(By.className("close-help-page")).size();
            if (helppage == 1)
                Driver.driver.findElement(By.className("close-help-page")).click();

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
                    Driver.driver.findElement(By.linkText("Finish Assignment")).click();
                    System.out.println("clicked finish");
                    timer = 0;
                } else
                    timer = Driver.driver.findElements(By.id("assessmentTimer")).size();
            }
        } catch (Exception e) {
            Assert.fail("Exception in app helper submitAssignmentAsStudent in class Assignment", e);
        }
    }


    public void deleteAssignment() {
        try {
            new UIElement().waitAndFindElement(By.cssSelector("span[title='Un-assign Assignment']"));
            Driver.driver.findElement(By.cssSelector("span[title='Un-assign Assignment']")).click(); //clicking on delete link for the first assignment in summary page
            Driver.driver.findElement(By.cssSelector("div[class='as-modal-yes-btn delete-button']")).click(); //click on yes button in the alert which comes
        } catch (Exception e) {
            Assert.fail("Exception in app helper deleteAssignment in class Assignment", e);
        }
    }

    public boolean submitAnswer(String submitassignment, String dataIndex) {
        boolean lastQuestionNotToSubmitAssignment = true;
        boolean clicked = false;
        boolean nextClicked = false;
        try {
            if (submitassignment == null || submitassignment.equalsIgnoreCase("true")) {
                try {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")));
                    clicked = true;

                } catch (Exception e) {
                    //empty catch block
                }
                if (clicked == false) {
                    try {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")));
                        clicked = true;

                    } catch (Exception e) {
                        //empty catch block
                    }
                }
                if (clicked == false) {
                    try {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large next-or-submit-link']")));

                    } catch (Exception e) {
                        //empty catch block
                    }
                }

                /*if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).size() > 0) //Finish Assignment
                {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")));
                }
                else if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).size()>0)//click on Finish Assignment
                {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")));
                }
                else if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large next-or-submit-link']")).size()>0)//click on Finish Assignment
                {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large next-or-submit-link']")));
                }*///submitting the assignment as student 1
            }

            try {
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")));
                nextClicked = true;
                lastQuestionNotToSubmitAssignment = false;

            } catch (Exception e) {
                //empty catch block
            }
            if (nextClicked == false) {
                try {

                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")));
                    nextClicked = true;
                    lastQuestionNotToSubmitAssignment = false;

                } catch (Exception e) {
                    //empty catch block
                }
            }
            if (nextClicked == false) {
                try {

                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[id='next-practice-question-button']")));
                    lastQuestionNotToSubmitAssignment = false;
                } catch (Exception e) {
                    //empty catch block
                }
            }
            /*if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size() > 0) //next
            {
                lastQuestionNotToSubmitAssignment = false;
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")));
            }
            else if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).size() > 0) //next
            {
                lastQuestionNotToSubmitAssignment = false;
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")));
            }
            else if(Driver.driver.findElements(By.cssSelector("input[id='next-practice-question-button']")).size() > 0) //next
            {
                lastQuestionNotToSubmitAssignment = false;
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[id='next-practice-question-button']")));
            }*/
        } catch (Exception e) {
            Assert.fail("Exception while attempting assignment", e);
        }
        return lastQuestionNotToSubmitAssignment;

    }

    public void addQuestionLink() {
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
        try {
            if (Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size() > 0) //next
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")));

            else if (Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).size() > 0) //next
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")));
            else if (Driver.driver.findElements(By.cssSelector("div[class='ls-static-practice-test-next-button']")).size() > 0)//click on Next for static assessment
            {
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='ls-static-practice-test-next-button']")));
            } else
                Assert.fail("Next button not found in assignment");

        } catch (Exception e) {
            Assert.fail("Exception while clicking on next button while attempting an assignment", e);
        }
    }

    public void previousButtonInQuestionClick() {
        try {
            if (Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--previous long-text-button']")).size() > 0) //next
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--previous long-text-button']")));

            else if (Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--previous']")).size() > 0) //next
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--previous']")));

            else
                Assert.fail("Previous button not found in assignment");

        } catch (Exception e) {
            Assert.fail("Exception while clicking on next button while attempting an assignment", e);
        }
    }

    public void submitButtonInQuestionClick() {
        try {
            if (Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).size() > 0) //Finish Assignment
            {
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")));
            } else if (Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).size() > 0)//click on Finish Assignment
            {
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")));
            }
            else if (Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large next-or-submit-link']")).size() > 0)//click on Finish Assignment
            {
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large next-or-submit-link']")));
            }

            else if (Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size() > 0)//click on Submit for static assessment
            {
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")));
            }


            else if (Driver.driver.findElements(By.cssSelector("div[class='ls-static-practice-test-submit-button']")).size() > 0)//click on Submit for static assessment
            {
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='ls-static-practice-test-submit-button']")));
            }
           else
                Assert.fail("Submit button not found in assignment");
        } catch (Exception e) {
            Assert.fail("Exception while clicking on submit button while attempting an assignment", e);
        }

    }

    public void extendDueDate(String dataIndex) {
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            String newduedate = ReadTestData.readDataByTagName("", "newduedate", dataIndex);
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(Driver.driver, CurrentAssignments.class);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            if (assessmentname != null) {
                Driver.driver.findElement(By.linkText("All Assignment Status")).click();
                new UIElement().waitAndFindElement(By.linkText("Available for Students"));
                Driver.driver.findElement(By.linkText("Available for Students")).click();
                new UIElement().waitAndFindElement(By.linkText("All Activity"));

                Driver.driver.findElement(By.linkText("All Activity")).click();
                new UIElement().waitAndFindElement(By.linkText("Question Practice"));
                if (gradeable == null)
                    Driver.driver.findElement(By.linkText("Question Practice")).click();
                else if (gradeable.equals("false"))
                    Driver.driver.findElement(By.linkText("Question Practice")).click();
                else
                    Driver.driver.findElement(By.linkText("Question Assignment")).click();

                new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));

                List<WebElement> assignmentNames = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
                int index = 0;
                for (int i = 0; i < assignmentNames.size(); i++) {
                    if (assignmentNames.get(i).getText().substring(7).equals(assessmentname)) {
                        break;
                    }
                    index++;
                }
                Driver.driver.findElements(By.cssSelector("span[class='assign-more']")).get(index).click(); //click on Update Assignment in Assignments page only
                currentAssignments.getReassign_button().click();    //click on Re-assign button
            } else {
                currentAssignments.getUpdateAssignment_button().click();//click on Update Assignment at first index
            }
            currentAssignments.getExtendDueDateTab().click();   //go to extend due date tab
            new ShareWith().shareInCS(shareWithInitialString, shareName, shareWithClass, context_title, true);  //share with class/student
            Driver.driver.findElement(By.id("extended-due-time")).click();
            List<WebElement> elements = Driver.driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals(newduedate)) {
                    time.click();
                    break;
                }
            }
            Driver.driver.findElement(By.id("extended-due-date")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
            Driver.driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            new UIElement().waitAndFindElement(By.linkText(newduedate));
            Driver.driver.findElement(By.linkText(newduedate)).click();
            currentAssignments.getUpdateButtonInReassign_button().get(1).click();//click on Update Assignment link
        } catch (Exception e) {
            Assert.fail("Exception in app helper extendDueDate in class Assignment", e);
        }

    }
    /*
     * @Author Mukesh
     * Method will update grade after grade release
     */


    public void updateGradeAfterGradeRelease(String dataIndex) {
        try {
            String grade = ReadTestData.readDataByTagName("", "grade", dataIndex);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Current Assignments");// navigate to the assignment summary page
            Driver.driver.findElement(By.className("ls-grade-book-assessment")).click();////click on the view grade link
            new UIElement().waitAndFindElement(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            Actions action = new Actions(Driver.driver);
            List<WebElement> we = Driver.driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            action.moveToElement(we.get(0)).build().perform();
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("idb-grade-now-link")));	//click on Enter Grade
            new UIElement().waitAndFindElement(By.cssSelector("input[class='idb-grade-points']"));
            List<WebElement> provideGrade = Driver.driver.findElements(By.cssSelector("input[class='idb-grade-points']"));
            for (WebElement eachGrade : provideGrade) {
                eachGrade.clear();
                eachGrade.sendKeys(grade);
                eachGrade.sendKeys(Keys.TAB);

            }
            Driver.driver.findElement(By.xpath("/html/body")).click();
            driver.navigate().refresh();
            Driver.driver.findElement(By.className("ls-grade-book-assessment")).click();////click on the view grade link

        } catch (Exception e) {
            Assert.fail("Exception in apphelper provideGradeAfterReleasein class Assignment", e);
        }
    }
 /*
     * @Author Mukesh
     * Method will provide feedback for particular student
     */

    public void provideFeedBackForMultipleQuestion(String dataIndex, String sendKeys) {
        try {
            String studentname = ReadTestData.readDataByTagName("", "studentname", dataIndex);
            String y[] = studentname.split(" ");
            studentname = y[1] + ", " + y[0];//reverse the name with comma in between

            System.out.println("Student Name:" + studentname);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Current Assignments");// navigate to the assignment summary page
            new Click().clickByclassname("ls-grade-book-assessment");//click on the view student feedback
            List<WebElement> menuitem = Driver.driver.findElements(By.cssSelector("span[class='idb-gradebook-assignment-username']"));
            int feedBackLinkIndex = 0;
            for (WebElement user : menuitem) {
                if (user.getText().equals(studentname)) {
                    break;
                } else {
                    feedBackLinkIndex++;
                }

            }
            System.out.println("feedBackLinkIndex: " + feedBackLinkIndex);
            List<WebElement> provideFeedback = Driver.driver.findElements(By.cssSelector("div[class='idb-gradebook-content-coloumn']"));
            for (int i = feedBackLinkIndex; i < provideFeedback.size(); i++) {
                MouseHover.mouserHoverByClassList("idb-gradebook-question-content", feedBackLinkIndex);

            }
            new WebDriverWait(Driver.driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-view-response-link")));
            Driver.driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys(sendKeys);
            Driver.driver.findElement(By.xpath("//span[text()='Save']")).click(); //click on save button
            new TabClose().tabClose(2);//close the tab

        } catch (Exception e) {
            Assert.fail("Exception in method provideFeedBackForMultipleQuestion of class Assignment", e);
        }
    }

    /*
     * @Author Mukesh
     * Method will import question using WPQTI
     */
    public void importQuestionUsingWPQTI(String dataIndex) {
        try {
            String course = "";
            String course_type = ReadTestData.readDataByTagName("", "course_type", dataIndex);
            String course_name = ReadTestData.readDataByTagName("", "course_name", dataIndex);
            String practice_type = ReadTestData.readDataByTagName("", "practice_type", dataIndex);
            course = course_name;
            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String questionset = ReadTestData.readDataByTagName("", "questionset", dataIndex);
            String questiontype = ReadTestData.readDataByTagName("", "questiontype", dataIndex);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", dataIndex);
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", dataIndex);
            String reservforassignment = ReadTestData.readDataByTagName("", "reservforassignment", dataIndex);
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
            String title = Driver.driver.getTitle();
            if (title.equals("Course Content Details")) {
                Driver.driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    new Click().clickBycssselector("div.course-chapter-label.node");
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    List<WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                    for (WebElement chapters : allChapters) {
                        if (chapters.getText().contains(chapterName)) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters);
                            break;
                        }

                    }

                }
                Driver.driver.findElement(By.cssSelector("div.create-practice")).click();//click on the create practice link

                if (practice_type == null) {
                    new Click().clickByclassname("create-regular-assessment-popup-item"); //click on the create regular assessment
                } else {
                    new Click().clickByclassname("create-file-assessment-popup-item");//click on the create file assessment
                }

                new Click().clickbylinkText("Adaptive Component Diagnostic");
                new Click().clickbylinkText(questiontype);
                int popup = 1;
                try {
                    Driver.driver.findElement(By.cssSelector("div[class='cms-notification-message-body']"));
                } catch (Exception e) {
                    popup = 0;
                }
                //int popup = Driver.driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
                if (popup == 0) {
                    Driver.driver.findElement(By.id("assessmentName")).click();
                    Driver.driver.findElement(By.id("assessmentName")).clear();
                    Driver.driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
                    Driver.driver.findElement(By.id("questionSetName")).clear();
                    Driver.driver.findElement(By.id("questionSetName")).sendKeys(questionset);

                    if (reservforassignment == null) {
                        Driver.driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
                    }

                    new Click().clickByid("import-contents-img");//click on import image


                    try {
                        new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
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

    /*
     * @Author Mukesh
     * Method will deActivate question
     */
    public void deActivateQuestion(String dataIndex, String diaAssessment, int noOfQuestion) {
        try {
            String course = "";
            diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", dataIndex);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", dataIndex);
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", dataIndex);
            String course_type = ReadTestData.readDataByTagName("", "course_type", dataIndex);
            String course_name = ReadTestData.readDataByTagName("", "course_name", dataIndex);
            String deActivateQuestion = ReadTestData.readDataByTagName("", "deActivateQuestion", dataIndex);
            String test_type = ReadTestData.readDataByTagName("", "test_type", dataIndex);
            String subSection_level = ReadTestData.readDataByTagName("", "subSection_level", dataIndex);
            String chapterIndex = ReadTestData.readDataByTagName("", "chapterIndex", dataIndex);
            String topicIndex = ReadTestData.readDataByTagName("", "topicIndex", dataIndex);


            course = course_name;
            System.out.println("course: " + course);
            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }

            new DirectLogin().CMSLogin();

            String title = Driver.driver.getTitle();
            if (title.equals("Course Content Details")) {
                Driver.driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    List<WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                    for (WebElement chapters : allChapters) {
                        if (chapters.getText().contains(chapterName)) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters);
                            break;
                        }

                    }

                }
                if (subSection_level != null) {
                    new SelectCourse().expandChapterTreeByIndex(dataIndex, Integer.parseInt(chapterIndex));
                    new SelectCourse().selectTopicByIndex(dataIndex, Integer.parseInt(topicIndex));

                }
                if (test_type.equals("DS")) {
                    Driver.driver.findElement(By.xpath("//div[@title='" + diaAssessment + "']")).click(); //click on the diagnostic test
                }

                if (test_type.equals("PS")) {
                    Driver.driver.findElement(By.xpath("//div[@title='" + diaAssessment + "']")).click(); //click on the Practice test

                }

                if (test_type.equals("SS")) {
                    Driver.driver.findElement(By.xpath("//div[@title='" + diaAssessment + "']")).click(); //click on the Static test

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
                        WebElement element = Driver.driver.findElement(By.xpath("//label[@id='" + Integer.toString(i) + "']"));
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", element);
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

    /*
    * @Author Mukesh
    * Method will deActivate Particular Question
    */
    public void deActivateParticularQuestion(String dataIndex, String diaAssessment) {
        try {
            diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", dataIndex);
            String test_type = ReadTestData.readDataByTagName("", "test_type", dataIndex);
            String subSection_level = ReadTestData.readDataByTagName("", "subSection_level", dataIndex);
            String chapterIndex = ReadTestData.readDataByTagName("", "chapterIndex", dataIndex);
            String topicIndex = ReadTestData.readDataByTagName("", "topicIndex", dataIndex);
            String QuestionNo = ReadTestData.readDataByTagName("", "QuestionNo", dataIndex);
            String assessment = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            if (subSection_level == null) {
                Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();

            } else {
                new SelectCourse().expandChapterTreeByIndex(dataIndex, Integer.parseInt(chapterIndex));
                new SelectCourse().selectTopicByIndex(dataIndex, Integer.parseInt(topicIndex));
            }

            boolean assignmentExists = false;
            List<WebElement> elements = Driver.driver.findElements(By.className("collection-assessment-name"));
            for (WebElement content : elements) {
                if (content.getText().trim().equals(assessment)) {

                    new UIElement().waitAndFindElement(content);
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", content);
                    assignmentExists = true;
                    break;
                }
            }

            if (test_type.equals("DS")) {
                Driver.driver.findElement(By.xpath("//div[@title='" + diaAssessment + "']")).click(); //click on the diagnostic test
            }

            if (test_type.equals("PS")) {
                Driver.driver.findElement(By.xpath("//div[@title='" + diaAssessment + "']")).click(); //click on the Practice test

            }

            if (test_type.equals("SS")) {
                Driver.driver.findElement(By.xpath("//div[@title='" + diaAssessment + "']")).click(); //click on the Static test

            }
            new WebDriverWait(Driver.driver,180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Jump To Q#']")));
            Driver.driver.findElement(By.xpath("//a[@title='Jump To Q#']")).click();
            new UIElement().waitAndFindElement(By.linkText(QuestionNo));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);",Driver.driver.findElement(By.linkText(QuestionNo)));
            Driver.driver.findElement(By.linkText(QuestionNo)).click();
            new UIElement().waitAndFindElement(By.id("questionOptions"));
           // new CMSActions().navigateToQuestionNo(Integer.parseInt(QuestionNo));
            new Click().clickByid("questionOptions"); //click on question option
            new Click().clickByid("questionRevisions"); // click on revisions
            new Click().clickByid("cms-question-revision-deactivate-button");//click on the deactivate button


        } catch (Exception e) {

            Assert.fail("Exception in create in Apphelper deActivateParticularQuestion in class AssignmentCreate", e);
        }
    }




    /*
     * @Author Dharaneesha
     * Method will create the new version question
     */
    public void createNewQuestionVersion(String dataIndex, int questionPosition) {
        try {
            String course = "";
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentName", dataIndex);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", dataIndex);
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", dataIndex);
            String course_type = ReadTestData.readDataByTagName("", "course_type", dataIndex);
            String course_name = ReadTestData.readDataByTagName("", "course_name", dataIndex);
            String deActivateQuestion = ReadTestData.readDataByTagName("", "deActivateQuestion", dataIndex);
            String test_type = ReadTestData.readDataByTagName("", "test_type", dataIndex);
            String subSection_level = ReadTestData.readDataByTagName("", "subSection_level", dataIndex);
            String chapterIndex = ReadTestData.readDataByTagName("", "chapterIndex", dataIndex);
            String topicIndex = ReadTestData.readDataByTagName("", "topicIndex", dataIndex);
            Questions questions = PageFactory.initElements(Driver.driver, Questions.class);

            course = course_name;
            System.out.println("course: " + course);
            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }

            new DirectLogin().CMSLogin();

            String title = Driver.driver.getTitle();
            if (title.equals("Course Content Details")) {
                Driver.driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    List<WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                    for (WebElement chapters : allChapters) {
                        if (chapters.getText().contains(chapterName)) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters);
                            break;
                        }

                    }

                }
                if (subSection_level != null) {
                    new SelectCourse().expandChapterTreeByIndex(dataIndex, Integer.parseInt(chapterIndex));
                    new SelectCourse().selectTopicByIndex(dataIndex, Integer.parseInt(topicIndex));

                }
                Driver.driver.findElement(By.xpath("//div[@title='" + assessmentName + "']")).click(); //click on the test
                if(questionPosition !=1){
                    new WebDriverWait(Driver.driver,180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Jump To Q#']")));
                    Driver.driver.findElement(By.xpath("//a[@title='Jump To Q#']")).click();
                    new UIElement().waitAndFindElement(By.linkText(Integer.toString(questionPosition)));
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);",Driver.driver.findElement(By.linkText(Integer.toString(questionPosition))));
                    Driver.driver.findElement(By.linkText(Integer.toString(questionPosition))).click();

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
                System.out.println("QuestionText 1 : "+questionText);
            }
        } catch (Exception e) {

            Assert.fail("Exception in create in Apphelper deActivateQuestion in class AssignmentCreate", e);
        }
    }

    public void assignFileBasedAssignmentToStudent(String dataIndex) {
        try {
            CurrentAssignments currentAssignments=PageFactory.initElements(Driver.driver,CurrentAssignments.class);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            String gradable = ReadTestData.readDataByTagName("", "gradable", dataIndex);
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", dataIndex);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", dataIndex);
            String totalpoints = ReadTestData.readDataByTagName("", "totalpoints", dataIndex);
            String questionBank = ReadTestData.readDataByTagName("", "questionBank", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);

            if(questionBank!=null)
            {
                new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignments");
                Driver.driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();

                new UIElement().waitAndFindElement(By.cssSelector("div[class='tab active']"));
                if (Driver.driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources")) {//Opening All Resources tab if not opened after clicking on New Assignment button

                    Driver.driver.findElement(By.cssSelector("span[title='All Resources']")).click();
                }
                currentAssignments.getUsePreCreatedAssignment_button().click();//click on Use Pre-created Assignment on Create New assignment pop-up
                new UIElement().waitAndFindElement(By.id("all-resource-search-textarea"));
                //Adding assignment to search
                Driver.driver.findElement(By.id("all-resource-search-textarea")).clear();
                Driver.driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                Driver.driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            }

            String currentUrl=Driver.driver.getCurrentUrl();
            Thread.sleep(3000);
            if(currentUrl.contains("assignment")) {
                Driver.driver.findElement(By.id("ls-assign-now-assigment-btn")).click();
            }
            else {
                //List<WebElement> assignThis = Driver.driver.findElements(By.cssSelector("span[class='assign-this']"));
                //assignThis.get(0).click();
                Driver.driver.findElement(By.cssSelector("span[class='assign-this']")).click();
            }
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass,context_title,true);

            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            Thread.sleep(2000);
            new UIElement().waitAndFindElement(By.id("due-time"));
            Driver.driver.findElement(By.id("due-time")).click();
            Driver.driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            Driver.driver.findElement(By.id("due-date")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
            Driver.driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            Driver.driver.findElement(By.linkText(duedate)).click();

            if(gradable !=null) {
                if (gradable.equals("true")) {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));    //check gradable chekbox
                    Driver.driver.findElement(By.id("total-points")).sendKeys(totalpoints);  //enter Total Points
                }
            }
            if (gradable.equals("true") && assignmentpolicy != null) {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Select an assignment policy")));
                new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                Driver.driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            }


            if (accessibleafter != null) {
                Driver.driver.findElement(By.id("accessible-date")).click();
                Driver.driver.findElement(By.linkText(accessibleafter)).click();
            }


            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            new UIElement().waitAndFindElement(By.id("additional-notes"));
            Driver.driver.findElement(By.id("additional-notes")).clear();
            Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//span[text()='Assign']")));

        } catch (Exception e) {
            Assert.fail("Exception in  assignFileBasedAssignmentToStudent in AppHelper class Assignment", e);
        }
    }

    public ArrayList<String> createFileBasedAssessment(String dataIndex) {
        ArrayList<String> assessmentInfoList= new ArrayList<>();
        try {
            String course = "";
            String overrideDefaultLogin = ReadTestData.readDataByTagName("", "overrideDefaultLogin", dataIndex);
            String course_type = ReadTestData.readDataByTagName("", "course_type", dataIndex);
            String course_name = ReadTestData.readDataByTagName("", "course_name", dataIndex);
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
            course = course_name;
            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String promptdetails = ReadTestData.readDataByTagName("", "promptdetails", dataIndex);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", dataIndex);
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", dataIndex);

            String packageName = new Exception().getStackTrace()[1].getClassName();
            String methodName = new Exception().getStackTrace()[1].getMethodName();

            packageName = packageName.substring(64);
            packageName = packageName.replaceAll("\\.", "_");

            if(assessmentname==null){
                assessmentname  = dataIndex+ "_Assessment_"+packageName+"_"+methodName+"_"+appendChar;
                assessmentInfoList.add(assessmentname);
            }

            if(filename==null){
                filename  = "img.png";
            }

            if (overrideDefaultLogin == null)
                new DirectLogin().CMSLogin();
            else {
                String role = ReadTestData.readDataByTagName("", "role", dataIndex);
                new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect().Connect();
                ResultSet rs = com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect.st.executeQuery("select id,username from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = '" + role + "') limit 1,1;");
                String username = "";
                while (rs.next()) {
                    username = rs.getString("username");
                }
                new DirectLogin().directLoginWithCreditial(username, "snapwiz", "0");
                new Click().clickbyxpath("(//select[@name='product-drop-down']/following-sibling::div/a)[1]");
                new Click().clickbylinkText("Learning Space + Adaptive Component");
            }

            String title = Driver.driver.getTitle();
            if (title.equals("Course Content Details")) {
                Driver.driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    new Click().clickBycssselector("div.course-chapter-label.node");
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    List<WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                    for (WebElement chapters : allChapters) {
                        if (chapters.getText().contains(chapterName)) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters);

                            break;
                        }

                    }

                }
                new UIElement().waitAndFindElement(By.cssSelector("div.create-practice"));
                Driver.driver.findElement(By.cssSelector("div.create-practice")).click();
                new Click().clickByclassname("create-file-assessment-popup-item");//click on the create file assessment
                WebDriverWait wait = new WebDriverWait(Driver.driver, 2000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='File Based Assessment']")));
                new Click().clickbyxpath("//a[@id='uploadFile']");
                Thread.sleep(5000);
                /*new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
                Driver.driver.findElement(By.id("uploadFile")).sendKeys(Config.fileUploadPath + filename);*/

                StringSelection stringSelection = new StringSelection(Config.fileUploadPath + filename);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
                Robot robot = new Robot();



                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_META);
                robot.keyRelease(KeyEvent.VK_TAB);

                robot.delay(500);




                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress(KeyEvent.VK_G);

                robot.keyRelease(KeyEvent.VK_G);
                robot.keyRelease(KeyEvent.VK_SHIFT);
                robot.keyRelease(KeyEvent.VK_META);


                robot.delay(1000);



                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_META);
                robot.delay(2000);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                robot.delay(2000);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                Thread.sleep(5000);

                Driver.driver.findElement(By.xpath("//*[@id='cms-file-assessment-popup-title']")).sendKeys(assessmentname); // give assessment name

                Thread.sleep(2000);
                new UIElement().waitAndFindElement(By.id("question-prompt-raw-content"));
                new TextSend().textsendbyid(""+promptdetails+"","question-prompt-raw-content");

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ls-delete-file-icon']")));
                new Click().clickbyxpath("//span[@class='cms-file-assessment-popup-save']"); //click on save button

            }

        } catch (Exception e) {
            Assert.fail("Exception in method createFileBasedAssessment of appHelper Assignment",e);
        }
        return  assessmentInfoList;
    }

    /*
 * @Author Mukesh
 * Method will provide Grade From AssignmentResponsePage(Engagement Report)
 */
    public void provideGradeToStudentFromAssignmentResponsePage(String dataIndex) throws InterruptedException {
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String chapterIndex = ReadTestData.readDataByTagName("", "chapterIndex", dataIndex);
            String grade = ReadTestData.readDataByTagName("", "grade", dataIndex);


            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Engagement Report");//navigate to current Assignment
            new UIElement().waitAndFindElement(By.xpath("(//div[@class='row-non-assigned-status']/div[2]/span)[2]"));
            Driver.driver.findElement(By.xpath("(//div[@class='row-non-assigned-status']/div[2]/span)[2]")).click(); //click on the assignment count
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.TopicOpen().chapterOpen(Integer.parseInt(chapterIndex)); //select Chapter from testData
            new SelectCourse().selectInvisibleAssignment(assessmentname); //select assessment from testData
            int gradeLinkIndex = 0;
            List<WebElement> menuitem = Driver.driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            for (WebElement user : menuitem) {
                MouseHover.mouserhoverbywebelement(user);
                List<WebElement> gradenowlinks = Driver.driver.findElements(By.id("idb-grade-now-link"));
                new UIElement().waitAndFindElement(gradenowlinks.get(gradeLinkIndex));
                gradenowlinks.get(gradeLinkIndex).click();
                List<WebElement> allGradeBox = Driver.driver.findElements(By.cssSelector("input[class='idb-grade-points']"));
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
                Driver.driver.findElement(By.xpath("/html/body")).click();
            }
            gradeLinkIndex++;

        }
        catch (Exception e) {
            Assert.fail("Exception in apphelper provideGradeToStudentFromAssignmentResponsePage in class Assignment", e);
        }
    }

    public void assignFileBasedAssignmentFromMyQuestionBank(String dataIndex) {
        try {
            CurrentAssignments currentAssignments=PageFactory.initElements(Driver.driver, CurrentAssignments.class);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            String gradable = ReadTestData.readDataByTagName("", "gradable", dataIndex);
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", dataIndex);
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", dataIndex);
            String totalpoints = ReadTestData.readDataByTagName("", "totalpoints", dataIndex);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("My Question Bank");
            Driver.driver.findElement(By.cssSelector("span[class='assign-this']")).click();
            new UIElement().waitAndFindElement(By.id("due-time"));
            Driver.driver.findElement(By.id("due-time")).click();
            Driver.driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            Driver.driver.findElement(By.id("due-date")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            Driver.driver.findElement(By.linkText(duedate)).click();
            if(gradable !=null) {
                if (gradable.equals("true")) {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));    //check gradable chekbox
                    Driver.driver.findElement(By.id("total-points")).sendKeys(totalpoints);  //enter Total Points
                }
            }
            if (gradable.equals("true") && assignmentpolicy != null) {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Select an assignment policy")));
                new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                Driver.driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            }
            if (accessibleafter != null) {
                new UIElement().waitAndFindElement(By.id("accessible-date"));
                Driver.driver.findElement(By.id("accessible-date")).click();
                Driver.driver.findElement(By.linkText(accessibleafter)).click();
            }


            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            new UIElement().waitAndFindElement(By.id("additional-notes"));
            //Driver.driver.findElement(By.id("additional-notes")).clear();
            ((JavascriptExecutor)Driver.driver).executeScript("document.getElementById('additional-notes').value='additionalnote'");
           // Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//span[text()='Assign']")));
            new UIElement().waitTillInvisibleElement(By.xpath("//span[text()='Assign']"));

        } catch (Exception e) {
            Assert.fail("Exception in  assignFileBasedAssignmentFromMyQuestionBank in AppHelper class Assignment", e);
        }
    }


    public void assignCustomAssignmentFromMyQuestionBank(String dataIndex) {
        try {
            CurrentAssignments currentAssignments=PageFactory.initElements(Driver.driver, CurrentAssignments.class);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            String gradable = ReadTestData.readDataByTagName("", "gradable", dataIndex);
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", dataIndex);
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", dataIndex);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("My Question Bank");
            Driver.driver.findElement(By.cssSelector("span[class='assign-this']")).click();
            new UIElement().waitAndFindElement(By.id("due-time"));
            Driver.driver.findElement(By.id("due-time")).click();
            Driver.driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            Driver.driver.findElement(By.id("due-date")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            Driver.driver.findElement(By.linkText(duedate)).click();
            if(gradable !=null) {
                if (gradable.equals("true")) {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));    //check gradable chekbox
                }
            }
            if (gradable.equals("true") && assignmentpolicy != null) {
                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Select an assignment policy")));
                new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                Driver.driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            }
            if (accessibleafter != null) {
                new UIElement().waitAndFindElement(By.id("accessible-date"));
                Driver.driver.findElement(By.id("accessible-date")).click();
                Driver.driver.findElement(By.linkText(accessibleafter)).click();
            }
            Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            new UIElement().waitAndFindElement(By.id("additional-notes"));
            //Driver.driver.findElement(By.id("additional-notes")).clear();
            ((JavascriptExecutor)Driver.driver).executeScript("document.getElementById('additional-notes').value='additionalnote'");
            // Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//span[text()='Assign']")));
            new UIElement().waitTillInvisibleElement(By.xpath("//span[text()='Assign']"));
        } catch (Exception e) {
            Assert.fail("Exception in  assignFileBasedAssignmentFromMyQuestionBank in AppHelper class Assignment", e);
        }
    }


    public String createNewerVersionQuestion(String dataIndex, int questionPosition)
    {
        String questionId =null;
        Assignments assignments= PageFactory.initElements(Driver.driver, Assignments.class);
        NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(Driver.driver, NewQuestionDataEntry.class);
        String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
        String status = ReadTestData.readDataByTagName("", "status", dataIndex);

        try
        {
            OpenAssignment(assessmentName, dataIndex);
            new UIElement().waitAndFindElement(By.className("question-set-add-text"));
            if(!(questionPosition==1)){
                assignments.combBox_jumpToQ.click();
                new UIElement().waitAndFindElement(By.linkText(""+questionPosition));
                new Click().clickbylinkText(""+questionPosition);
                WebDriverWait wait = new WebDriverWait(driver,60);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = '"+questionPosition+"']")));
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
            if(status==null){

            }else{
                if(status.equalsIgnoreCase("Publish")){
                    assignments.combBox_publish.click();
                    assignments.link_publish.click();
                }
            }

            newQuestionDataEntry.save_button.click();
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in create in Apphelper createNewerVersionQuestion in class AssignmentCreate",e);
        }
        return questionId;
    }


    public void openRevisionSideBar(String dataIndex, int questionPosition)
    {
        Assignments assignments= PageFactory.initElements(Driver.driver, Assignments.class);
        NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(Driver.driver, NewQuestionDataEntry.class);
        String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
        try
        {
            OpenAssignment(assessmentName, dataIndex);
            new UIElement().waitAndFindElement(By.className("question-set-add-text"));
            assignments.new_Link.click();
            Thread.sleep(2000);
            assignments.option_Revisions.click();
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in create in Apphelper openRevisionSideBar in class Assignment",e);
        }
    }


    public void startStaticAssignmentFromeTextbook(String dataIndex, int questionPosition)
    {
        String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
        String chapterNameToSearch = ReadTestData.readDataByTagName("", "chapterNameToSearch", dataIndex);
        try
        {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("e-Textbook");

            List<WebElement> allChapters = Driver.driver.findElements(By.xpath("//div[starts-with(@class,'chapter-heading')]"));
            for (int a=0;a< allChapters.size(); a++) {
                if (allChapters.get(a).getText().trim().contains(chapterNameToSearch)){
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allChapters.get(a));
                    Thread.sleep(5000);
                    break;
                }
            }
            new UIElement().waitAndFindElement(By.xpath(".//*[@title='" + assessmentName + "']"));
            WebElement element= Driver.driver.findElement(By.xpath(".//*[@title='"+assessmentName+"']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath(".//*[@title='" + assessmentName + "']")));
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in create in Apphelper openRevisionSideBar in class AssignmentCreate",e);
        }
    }


    public void searchAssessmentInQuestionBanks(String dataIndex)
    {
        String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);



        String packageName = new Exception().getStackTrace()[1].getClassName();
        String methodName = new Exception().getStackTrace()[1].getMethodName();

        packageName = packageName.substring(64);
        packageName = packageName.replaceAll("\\.", "_");

        if(assessmentName==null){
            assessmentName  = dataIndex+ "_Assessment_"+packageName+"_"+methodName+"_"+appendChar;
            System.out.println("Assessment Name Created: " + assessmentName);
        }
        try
        {
            new Click().clickByid("all-resource-search-textarea");//Click on search text box
            new TextSend().textsendbyid("\"" + assessmentName + "\"", "all-resource-search-textarea");//Type as assessment name which we would like to search
            new UIElement().waitAndFindElement(By.id("all-resource-search-button"));
            new Click().clickByid("all-resource-search-button");//Click on Search icon
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in create in Apphelper searchAssessmentInQuestionBanks in class AssignmentCreate",e);
        }
    }


    public List<String> getMPQQuestionsStatus(String dataIndex)
    {
        List<String> statusList = new ArrayList<String>();
        Assignments assignments= PageFactory.initElements(Driver.driver, Assignments.class);
        try
        {
            String[] countArray = assignments.questionsCount.getText().split("f");
            statusList.add(assignments.label_status.getText());
            String count[] = countArray[1].split("[)]");
            for(int a=1;a<Integer.parseInt(count[0].trim());a++){
                Thread.sleep(1000);
                driver.findElement(By.className("next-question-part")).sendKeys(Keys.PAGE_UP);
                new UIElement().waitAndFindElement(assignments.questionsCount);
                assignments.questionsCount.click();
                new UIElement().waitAndFindElement(By.cssSelector("tr[qindex='"+(a+1)+"']"));
                driver.findElement(By.cssSelector("tr[qindex='"+(a+1)+"']")).click();
                if(!(a==2)) {
                    new UIElement().waitAndFindElement(assignments.label_status);
                    Thread.sleep(1000);
                    statusList.add(assignments.label_status.getText());
                }
            }
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in create in Apphelper getMPQQuestionStatus in class AssignmentCreate",e);
        }
        Collections.sort(statusList);
        return statusList;
    }


    public void switchToAssignmentResponsePage(String dataIndex) {
        try {
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            new Navigator().NavigateTo("Current Assignments");//navigate to Assignments
            /*//List<WebElement> assessmentsList = driver.findElements(By.cssSelector("span[assignmentname='" + assessmentName + "_" + appendChar + "']"));
            List<WebElement> timeStampList = driver.findElements(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']"));
            List<WebElement> assessmentsList = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));

            *//*ArrayList<Integer> list = new ArrayList<Integer>();

            for(int a=0;a<timeStampList.size();a++){
                String timeStamp = timeStampList.get(a).getText();
                list.add(Integer.parseInt(timeStamp));
            }
            int minIndex = list.indexOf(Collections.min(list));
            System.out.println("minIndex : " + minIndex);
            if(assessmentsList.get(minIndex).getAttribute("assignmentname").equals(assessmentName+"_"+appendChar))*//*
            //driver.findElements(By.className("ls-grade-book-assessment")).get(minIndex-1).click();


            if(pos>0){
                driver.findElements(By.className("ls-grade-book-assessment")).get(pos-1).click();
            }else{
                ArrayList<Integer> list = new ArrayList<Integer>();

                for(int a=0;a<timeStampList.size();a++){
                    String timeStamp = timeStampList.get(a).getText();
                    System.out.println("timeStamp : " + timeStamp.substring(0,2));
                    list.add(Integer.parseInt(timeStamp.substring(0,2)));
                }
                int minIndex = list.indexOf(Collections.min(list));
                System.out.println("minIndex : " + minIndex);
                if(assessmentsList.get(minIndex).getAttribute("assignmentname").equals(assessmentName+"_"+appendChar));
                driver.findElements(By.className("ls-grade-book-assessment")).get(minIndex-1).click();

            }*/


            new Navigator().NavigateTo("Current Assignments");//navigate to Assignments
            List<WebElement> assessmentsList = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            int pos = 0;
            for(int a=0;a<assessmentsList.size();a++){
                if(assessmentsList.get(a).getAttribute("title").equals("(shor) "+assessmentName+"_"+appendChar)){
                    pos++;
                    break;
                }
                pos++;
            }
            driver.findElements(By.className("ls-grade-book-assessment")).get(pos-1).click();

        }
        catch (Exception e) {
            Assert.fail("Exception in create in Apphelper getMPQQuestionStatus in class AssignmentCreate",e);
        }

    }

    public void provideGradeAndFeedbackToStudentForMPQ(String dataIndex) {
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String studentname = ReadTestData.readDataByTagName("", "studentname", dataIndex);
            String marks = ReadTestData.readDataByTagName("", "marks", dataIndex);
            String feedback = ReadTestData.readDataByTagName("", "feedback", dataIndex);
            String y[] = studentname.split(" ");
            studentname = y[1] + ", " + y[0];//reverse the name with comma in between
            String grade = ReadTestData.readDataByTagName("", "grade", dataIndex);
            AssignmentResponsesPage assignmentResponsesPage= PageFactory.initElements(Driver.driver, AssignmentResponsesPage.class);

            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element : assignments) {
                if (element.getText().contains(assessmentname)) {
                    break;
                }
                index++;
            }
            List<WebElement> viewresponseslink = Driver.driver.findElements(By.cssSelector("span[class='ls-grade-book-assessment']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", viewresponseslink.get(index));
            List<WebElement> menuitem = Driver.driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            for (WebElement user : menuitem) {
                if (user.getText().equals(studentname)) {
                    List<WebElement> mpqQuestionsDisabled = driver.findElements(By.cssSelector("span[class='idb-gradebook-question-content mpq-score-disabled']"));
                    for(int a=0;a<mpqQuestionsDisabled.size();a++){
                        new MouseHover().mouserhoverbywebelement(mpqQuestionsDisabled.get(a));
                        new UIElement().waitAndFindElement(By.className("ls-view-response-link"));
                        new Click().clickByclassname("ls-view-response-link");
                        Thread.sleep(3000);
                        if(marks!=null) {
                            List<WebElement> scoreElementsList = driver.findElements(By.className("multi-part-question-score"));
                            for (int b = 0; b < scoreElementsList.size(); b++) {
                                scoreElementsList.get(b).clear();
                                scoreElementsList.get(b).sendKeys(marks);
                            }
                            assignmentResponsesPage.getSaveButton().click();
                        }

                        for(int c=0;c<assignmentResponsesPage.getTextArea_FeedbackList().size();c++){
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


    public void selectInvisiblebottomAssignment(String assignmentname)
    {
        try
        {
            new WebDriverWait(Driver.driver,60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@title='" + assignmentname + "']")));
            List<WebElement> elementsList= Driver.driver.findElements(By.xpath(".//*[@title='"+assignmentname+"']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", elementsList.get(elementsList.size()-1));
            ((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",elementsList.get(elementsList.size()-1));
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method selectInvisiblebottomAssignment in class Assignment",e);
        }
    }

    public void openAssessmentInCMS(String dataIndex) {
        try {
            String course = "";
            String overrideDefaultLogin = ReadTestData.readDataByTagName("", "overrideDefaultLogin", dataIndex);
            String course_type = ReadTestData.readDataByTagName("", "course_type", dataIndex);
            String course_name = ReadTestData.readDataByTagName("", "course_name", dataIndex);


            course = course_name;
            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", dataIndex);
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", dataIndex);

            if (overrideDefaultLogin == null)
                new DirectLogin().CMSLogin();
            else {
                String role = ReadTestData.readDataByTagName("", "role", dataIndex);
                new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect().Connect();
                ResultSet rs = com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect.st.executeQuery("select id,username from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = '" + role + "') limit 1,1;");
                String username = "";
                while (rs.next()) {
                    username = rs.getString("username");
                }
                new DirectLogin().directLoginWithCreditial(username, "snapwiz", "0");
                new Click().clickbyxpath("(//select[@name='product-drop-down']/following-sibling::div/a)[1]");
                new Click().clickbylinkText("Learning Space + Adaptive Component");
                DBConnect.conn.close();
            }

            String title = Driver.driver.getTitle();
            if (title.equals("Course Content Details")) {
                Driver.driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                //Driver.driver.findElement(By.cssSelector("img[alt='"+course+"']")).click();

                if (chapterName == null) {
                    // Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                    new Click().clickBycssselector("div.course-chapter-label.node");
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    Thread.sleep(10000);
                    if( Driver.driver.findElements(By.xpath("//div[@title='"+chapterName+"']")).size()>1)
                    {
                        System.out.println("inside if:"+chapterName);
                        new TopicOpen().openCMSLastChapter();
                    }
                    else {
                        System.out.println("inside else:"+chapterName);
                        List<WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                        for (WebElement chapters : allChapters) {
                            if (chapters.getText().contains(chapterName)) {
                                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters);
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


    public void publishChapter(String dataIndex) {
        try {
            List<WebElement> editElementsList = driver.findElements(By.xpath("//img[starts-with(@id,'tree-node-edit-icon-')]"));
            for(int a=0;a<editElementsList.size();a++){
                if(editElementsList.get(a).isDisplayed()){
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
        try {
            MyQuestionBank myQuestionBank = PageFactory.initElements(Driver.driver, MyQuestionBank.class);
            myQuestionBank.shareThis.click();//click on share this
            myQuestionBank.shareWithTextBox.click();//click on share text box
            Thread.sleep(2000);
            String y[] = shareName.split(" ");
            shareName = y[1] + ", " + y[0];//reverse the name with comma in between
            System.out.println("shareName:" + shareName);
            myQuestionBank.shareTextBox.sendKeys(shareName);
            List<WebElement> suggestname = Driver.driver.findElements(By.xpath("//ul[@id='share-with_feed']/li"));
            for (WebElement name: suggestname)
            {
                System.out.println("Names "+name.getText());
                if(name.getText().trim().equals(shareName))
                {
                    Thread.sleep(2000);
                    //((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", name);
                    name.click();
                    break;
                }
            }

            myQuestionBank.shareButton.click();//click on share button

        } catch (Exception e) {
            Assert.fail("Exception in app helper shareCustomAssignment in class Assignment", e);
        }
    }


    public void submitAssignmentFromAssignmentsAsStudent(String dataIndex) {
        try {
            String submitassignment = ReadTestData.readDataByTagName("", "submitassignment", dataIndex);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String usehint = ReadTestData.readDataByTagName("", "usehint", dataIndex);
            String answerchoice = ReadTestData.readDataByTagName("", "answerchoice", dataIndex);
            new Navigator().NavigateTo("Assignments");
            int helppage = Driver.driver.findElements(By.className("close-help-page")).size();
            if (helppage == 1)
                Driver.driver.findElement(By.className("close-help-page")).click();
            new Click().clickBycssselector("span[title='(shor)  " + assessmentname + "']");//click on Assignment
            Thread.sleep(2000);
            boolean useHint = false;//by default useHint is set to false
            if (usehint != null)//read the tag from testdata
                useHint = true;//set useHint to true
            if (answerchoice == null)
                answerchoice = "correct";
            int timer = 1;
            while (timer != 0) {
                String questionText = (new WebDriverWait(Driver.driver, 50)).until(ExpectedConditions.presenceOfElementLocated(By.id("question-edit"))).getText();
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
                        Driver.driver.findElement(By.cssSelector("span[id='timer-label']"));
                    } catch (Exception e) {
                        timer = 0;
                    }
                }
            }
        } catch (Exception e) {
            Assert.fail("Exception in app helper submitAssignmentFromAssignmentsAsStudent in class Assignment", e);
        }
    }


    public void provideGradeToStudent(String dataIndex, String grade)
    {
        try
        {
            Actions action = new Actions(driver);
            List<WebElement> we = driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            action.moveToElement(we.get(Integer.parseInt(dataIndex))).build().perform();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("idb-grade-now-link")));	//click on Enter Grade
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("input[class='idb-grade-points']")).clear();
            Thread.sleep(2000);
            driver.switchTo().activeElement().sendKeys(grade);	//enter grade
            driver.findElement(By.cssSelector("body")).click();//click outside
            //Driver.driver.findElement(By.className("gradeBook-question-content-header")).click();	//click outside
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper provideGradeToStudent in class DiscussionWidget",e);
        }
    }

    public void releaseGradeForAll(){
        try{
            driver.findElement(By.cssSelector("div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-release']")).click(); //click on Release Grade for all
        }catch(Exception e){
            Assert.fail("Exception in the class 'Assignment' in the method 'releaseGradeForAll'",e);
        }
    }

}


