package com.snapwiz.selenium.tests.staf.datacreation.apphelper;

import com.mongodb.DBCollection;
import com.snapwiz.selenium.tests.staf.datacreation.uihelper.RandomNumber;
import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import com.snapwiz.selenium.tests.staf.datacreation.ReadTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.ResultSet;
import java.util.*;

public class PracticeTest extends Driver {

    public void startTest(int index) {
        try {
            Thread.sleep(1000);
            //Driver.driver.findElement(By.cssSelector("span[title='Practice']")).click();
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElements(By.xpath("//div[@class='al-content-header-row']/div[1]/span")).get(index));
            Thread.sleep(3000);
        } catch (Exception e) {

            Assert.fail("Exception in starting the practice test", e);
        }
    }

    public void startTLOLevelPracticeTest(int tloIndex) {
        try {
            List<String> tlonames = new PracticeTest().tloNames();
            System.out.println(tlonames.get(tloIndex));
            //List<WebElement> practicebuttons = Driver.driver.findElements(By.cssSelector("div[tloname='"+tlonames.get(tloIndex)+"']"));
            List<WebElement> tlolist = Driver.driver.findElements(By.className("al-terminal-objective-title"));
            for (WebElement tlo : tlolist) {
                if (tlo.getText().equals(tlonames.get(tloIndex))) {
                    Actions action = new Actions(Driver.driver);
                    action.moveToElement(tlo).build().perform();
                    break;
                }
            }
		  		
				/*WebElement we = Driver.driver.findElement(By.className(classname));
				
		  	    MouseHover.mouserhover("al-terminal-objective-title");*/
            Driver.driver.findElement(By.cssSelector("div[tloname='" + tlonames.get(tloIndex) + "']")).click();
            //Driver.driver.findElement(By.className("al-terminal-objective-title"))
            //((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",practicebuttons.get(practicebuttons.size()-1));
            Thread.sleep(3000);
            //((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",practicebuttons.get(practicebuttons.size()-1));

        } catch (Exception e) {
            Assert.fail("Exception in app helper method startTLOLevelPracticeTest in class SelectChapterForTest", e);
        }
    }

    public void quitTestAndGoToDashboard() {
        try {
            Driver.driver.findElement(By.className("al-quit-diag-test-icon")).click();
            Thread.sleep(1000);
            Driver.driver.findElement(By.className("ls-practice-test-view-report")).click();
            Thread.sleep(2000);
        } catch (Exception e) {

            Assert.fail("Exception in quitting the practice test", e);
        }
    }

    public void attemptInCorrect(int noOfQuestions)
    {
        try {
            int timer=1;
            int questionCount = 0;
            while(timer!=0) {
                boolean attempted = false;
                //True False and Single Select Question Type
                try {
                    driver.findElement(By.className("answer-choice-label-wrapper"));
                    String correctAnswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                    char correctAnswerOption=correctAnswer.charAt(17);
                    String correctChoice=Character.toString(correctAnswerOption);
                    String answerIndex="0";
                    if(correctChoice.equals("A")) {
                        answerIndex = "2";
                    }
                    else if (correctChoice.equals("B")) {
                        answerIndex = "1";
                    }
                    else if (correctChoice.equals("C")) {
                        answerIndex = "1";
                    }
                    else if (correctChoice.equals("D")) {
                        answerIndex = "1";
                    }
                    driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div["+answerIndex+"]/div[1]/label")).click();
                    attempted = true;

                }
                catch (Exception e) {

                }
                //Multiple Selection Question Type
                if(attempted ==false) {
                    try {
                        driver.findElement(By.className("multiple-selection-answer-choice-label-wrapper"));
                        String correctAnswer1 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctOption1;
                        if (correctAnswer1.charAt(17) == '(') {
                            correctOption1 = correctAnswer1.charAt(18);
                        } else {
                            correctOption1 = correctAnswer1.charAt(17);
                        }

                        String correctChoice1 = Character.toString(correctOption1); //Answer 1

                        String correctAnswer2 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctOption2 = correctAnswer2.charAt(22);

                        String correctChoice2 = Character.toString(correctOption2); //Answer 2

                        String answerIndex1 = "0",answerIndex2 = "0";

                        if((correctChoice1.equals("A") && correctChoice2.equals("B")) || (correctChoice1.equals("B") && correctChoice2.equals("A")) ) {
                            answerIndex1="3"; answerIndex2 = "4";
                        }
                        else if(correctChoice1.equals("A") && correctChoice2.equals("C") || (correctChoice1.equals("C") && correctChoice2.equals("A"))) {
                            answerIndex1="2"; answerIndex2 = "4";
                        }
                        else if(correctChoice1.equals("A") && correctChoice2.equals("D") || (correctChoice1.equals("D") && correctChoice2.equals("A"))) {
                            answerIndex1="2"; answerIndex2 = "3";
                        }
                        else if(correctChoice1.equals("A") && correctChoice2.equals("E") || (correctChoice1.equals("E") && correctChoice2.equals("A"))) {
                            answerIndex1="2"; answerIndex2 = "3";
                        }
                        else if(correctChoice1.equals("A") && correctChoice2.equals("F") || (correctChoice1.equals("F") && correctChoice2.equals("A"))) {
                            answerIndex1="2"; answerIndex2 = "3";
                        }


                        else if((correctChoice1.equals("B") && correctChoice2.equals("C")) || (correctChoice1.equals("C") && correctChoice2.equals("B")) ) {
                            answerIndex1="1"; answerIndex2 = "4";
                        }
                        else if(correctChoice1.equals("B") && correctChoice2.equals("D") || (correctChoice1.equals("D") && correctChoice2.equals("B"))) {
                            answerIndex1="1"; answerIndex2 = "3";
                        }
                        else if(correctChoice1.equals("B") && correctChoice2.equals("E") || (correctChoice1.equals("E") && correctChoice2.equals("B"))) {
                            answerIndex1="1"; answerIndex2 = "3";
                        }
                        else if(correctChoice1.equals("B") && correctChoice2.equals("F") || (correctChoice1.equals("F") && correctChoice2.equals("B"))) {
                            answerIndex1="1"; answerIndex2 = "3";
                        }

                        else if((correctChoice1.equals("C") && correctChoice2.equals("D")) || (correctChoice1.equals("D") && correctChoice2.equals("C")) ) {
                            answerIndex1="1"; answerIndex2 = "2";
                        }
                        else if(correctChoice1.equals("C") && correctChoice2.equals("E") || (correctChoice1.equals("E") && correctChoice2.equals("C"))) {
                            answerIndex1="1"; answerIndex2 = "2";
                        }
                        else if(correctChoice1.equals("C") && correctChoice2.equals("F") || (correctChoice1.equals("F") && correctChoice2.equals("C"))) {
                            answerIndex1="1"; answerIndex2 = "2";
                        }

                        else if((correctChoice1.equals("D") && correctChoice2.equals("E")) || (correctChoice1.equals("E") && correctChoice2.equals("D"))) {
                            answerIndex1="1"; answerIndex2 = "2";
                        }
                        else if(correctChoice1.equals("D") && correctChoice2.equals("F") || (correctChoice1.equals("F") && correctChoice2.equals("D"))) {
                            answerIndex1="1"; answerIndex2 = "2";
                        }

                        else if((correctChoice1.equals("E") && correctChoice2.equals("F")) || (correctChoice1.equals("F") && correctChoice2.equals("E")) ) {
                            answerIndex1="1"; answerIndex2 = "2";
                        }

                        driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div[" + answerIndex1 + "]/div[1]/label")).click();
                        driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div[" + answerIndex2 + "]/div[1]/label")).click();
                        attempted = true;

                    } catch (Exception e) {

                    }
                }
                //Drop Down Question Type
                if(attempted == false) {
                    try {
                        driver.findElement(By.className("sbHolder"));
                        String correctAnswerText=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        String correctAnswer=correctAnswerText.substring(16, correctAnswerText.length());
                        Driver.driver.findElement(By.className("sbToggle")).click();
                        Thread.sleep(1000);
                        /*Driver.driver.findElement(By.linkText(correctAnswer)).click();*/

                        String values = Driver.driver.findElement(By.cssSelector("ul[class='sbOptions']")).getText();
                        String [] val = values.split("\n");
                        for(String element:val)
                        {
                            if(!element.equals(correctAnswer))
                            {

                                Driver.driver.findElement(By.linkText(element)).click();
                                break;
                            }
                        }

                        attempted = true;
                    } catch (Exception e) {

                    }
                }
                //Text Entry Question Type
                if(attempted == false) {
                    try {
                        driver.findElement(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']"));
                        Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys("deliberately");
                        attempted = true;
                    }
                    catch (Exception e) {

                    }
                }
                /*(new WebDriverWait(Driver.driver, 2))
                        .until(ExpectedConditions.presenceOfElementLocated(By.className("al-diag-test-submit-button"))).click();*/

                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                try {
                    Driver.driver.findElement(By.className("al-notification-message-body"));

                    String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                    if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
                        Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                        Driver.driver.findElement(By.className("al-practice-test-submit-button")).click();
                        Thread.sleep(1000);
                    }


                }
                catch (Exception e) {

                }
                Thread.sleep(500);
                try {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                }
                catch (Exception e) {

                }

                Thread.sleep(1000);
                questionCount++;
                if(noOfQuestions == 0) {
                    try {
                        Driver.driver.findElement(By.cssSelector("span[id='timer-label']"));
                    }
                    catch (Exception e) {
                        timer = 0;
                    }
                }
                else {
                    if(noOfQuestions == questionCount) {
                        timer = 0;
                    }
                }


            }
        }
        catch (Exception e) {
            Assert.fail("Exception while attempting the diagnostic question as correct",e);
        }
    }


    public void attemptCorrect(int noOfQuestions)
    {
        try {
            int timer=1;
            int questionCount = 0;
            while(timer!=0) {
                boolean attempted = false;
                //True False and Single Select Question Type
                try {
                    driver.findElement(By.className("answer-choice-label-wrapper"));
                    String correctAnswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                    char correctAnswerOption=correctAnswer.charAt(17);
                    String correctChoice=Character.toString(correctAnswerOption);
                    String answerIndex="0";
                    if(correctChoice.equals("A")) {
                        answerIndex = "1";
                    }
                    else if (correctChoice.equals("B")) {
                        answerIndex = "2";
                    }
                    else if (correctChoice.equals("C")) {
                        answerIndex = "3";
                    }
                    else if (correctChoice.equals("D")) {
                        answerIndex = "4";
                    }
                    driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div["+answerIndex+"]/div[1]/label")).click();
                    attempted = true;

                }
                catch (Exception e) {

                }
                //Multiple Selection Question Type
                if(attempted ==false) {
                    try {
                        driver.findElement(By.className("multiple-selection-answer-choice-label-wrapper"));
                        String correctAnswer1 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctOption1;
                        if (correctAnswer1.charAt(17) == '(') {
                            correctOption1 = correctAnswer1.charAt(18);
                        } else {
                            correctOption1 = correctAnswer1.charAt(17);
                        }

                        String correctChoice1 = Character.toString(correctOption1); //Answer 1

                        String correctAnswer2 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctOption2 = correctAnswer2.charAt(22);

                        String correctChoice2 = Character.toString(correctOption2); //Answer 2

                        String answerIndex = "0";
                        if (correctChoice1.equals("A")) {
                            answerIndex = "1";
                        } else if (correctChoice1.equals("B")) {
                            answerIndex = "2";
                        } else if (correctChoice1.equals("C")) {
                            answerIndex = "3";
                        } else if (correctChoice1.equals("D")) {
                            answerIndex = "4";
                        } else if (correctChoice1.equals("E")) {
                            answerIndex = "5";
                        } else if (correctChoice1.equals("F")) {
                            answerIndex = "6";
                        }
                        driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div[" + answerIndex + "]/div[1]/label")).click();

                        answerIndex = "0";

                        if (correctChoice2.equals("A")) {
                            answerIndex = "1";
                        } else if (correctChoice2.equals("B")) {
                            answerIndex = "2";
                        } else if (correctChoice2.equals("C")) {
                            answerIndex = "3";
                        } else if (correctChoice2.equals("D")) {
                            answerIndex = "4";
                        } else if (correctChoice2.equals("E")) {
                            answerIndex = "5";
                        } else if (correctChoice2.equals("F")) {
                            answerIndex = "6";
                        }
                        driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div[" + answerIndex + "]/div[1]/label")).click();
                        attempted = true;

                    } catch (Exception e) {

                    }
                }
                //Drop Down Question Type
                if(attempted == false) {
                    try {
                        driver.findElement(By.className("sbHolder"));
                        String correctAnswerText=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        String correctAnswer=correctAnswerText.substring(16, correctAnswerText.length());
                        Driver.driver.findElement(By.className("sbToggle")).click();
                        Thread.sleep(1000);
                        Driver.driver.findElement(By.linkText(correctAnswer)).click();
                        attempted = true;
                    } catch (Exception e) {

                    }
                }
                //Text Entry Question Type
                if(attempted == false) {
                    try {
                        driver.findElement(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']"));
                        String correctAnswerText=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        String correctAnswer=correctAnswerText.substring(16, correctAnswerText.length());
                        Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys(correctAnswer);
                        attempted = true;
                    }
                    catch (Exception e) {

                    }
                }
                /*(new WebDriverWait(Driver.driver, 2))
                        .until(ExpectedConditions.presenceOfElementLocated(By.className("al-diag-test-submit-button"))).click();*/

                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                try {
                    Driver.driver.findElement(By.className("al-notification-message-body"));

                    String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                    if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
                        Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                        Driver.driver.findElement(By.className("al-practice-test-submit-button")).click();
                        Thread.sleep(1000);
                    }


                }
                catch (Exception e) {

                }
                Thread.sleep(500);
                try {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                }
                catch (Exception e) {

                }

                Thread.sleep(1000);
                if(noOfQuestions == 0) {
                    try {
                        Driver.driver.findElement(By.cssSelector("span[id='timer-label']"));
                    } catch (Exception e) {
                        timer = 0;
                    }
                }
                else {
                    if(noOfQuestions == questionCount) {
                        timer = 0;
                    }
                }
                questionCount++;


            }
        }
        catch (Exception e) {
            Assert.fail("Exception while attempting the diagnostic question as correct",e);
        }
    }



    public List<String> tloNames() {
        List<String> tlonames = new ArrayList<String>();
        try {
            String tlo1 = ReadTestData.readDataByTagName("tlos", "tlo1", "0");
            String tlo2 = ReadTestData.readDataByTagName("tlos", "tlo2", "0");
            String tlo3 = ReadTestData.readDataByTagName("tlos", "tlo3", "0");
            String tlo4 = ReadTestData.readDataByTagName("tlos", "tlo4", "0");
            String tlo5 = ReadTestData.readDataByTagName("tlos", "tlo5", "0");
            String tlo6 = ReadTestData.readDataByTagName("tlos", "tlo6", "0");
            String tlo7 = ReadTestData.readDataByTagName("tlos", "tlo7", "0");
            tlonames.add(tlo1);
            tlonames.add(tlo2);
            tlonames.add(tlo3);
            tlonames.add(tlo4);
            tlonames.add(tlo5);
            tlonames.add(tlo6);
            tlonames.add(tlo7);
        } catch (Exception e) {

            Assert.fail("Exception in fetching TLO names from test data", e);
        }
        return tlonames;
    }


    public List<String> tloIds() {
        List<String> tloids = new ArrayList<String>();
        try {
            String tlo1 = ReadTestData.readDataByTagName("tloids", "tlo1", "0");
            String tlo2 = ReadTestData.readDataByTagName("tloids", "tlo2", "0");
            String tlo3 = ReadTestData.readDataByTagName("tloids", "tlo3", "0");
            String tlo4 = ReadTestData.readDataByTagName("tloids", "tlo4", "0");
            String tlo5 = ReadTestData.readDataByTagName("tloids", "tlo5", "0");
            String tlo6 = ReadTestData.readDataByTagName("tloids", "tlo6", "0");
            String tlo7 = ReadTestData.readDataByTagName("tloids", "tlo7", "0");
            tloids.add(tlo1);
            tloids.add(tlo2);
            tloids.add(tlo3);
            tloids.add(tlo4);
            tloids.add(tlo5);
            tloids.add(tlo6);
            tloids.add(tlo7);
        } catch (Exception e) {

            Assert.fail("Exception in fetching TLO names from test data", e);
        }
        return tloids;
    }

    public void AttemptCorrectAnswer(int confidencelevel) {
        try {
            String corranswer = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
            char correctanswer = corranswer.charAt(17);
            String correctchoice = Character.toString(correctanswer);
            String corranswertext = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
            int lastindex = corranswertext.length();
            String correcttextanswer = corranswertext.substring(16, lastindex);

            if (Driver.driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size() > 0) {
                String corranswer1 = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                if (corranswer1.length() > 21) {
                    char correctanswer1 = corranswer1.charAt(22);
                    String correctchoice1 = Character.toString(correctanswer1);
                    List<WebElement> answer1 = Driver.driver.findElements(By.className("qtn-label"));
                    for (WebElement answerchoice1 : answer1) {

                        if (answerchoice1.getText().trim().equals(correctchoice1)) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice1);
                            break;
                        }
                    }
                }
                List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                for (WebElement answerchoice : answer) {

                    if (answerchoice.getText().trim().equals(correctchoice)) {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                        break;
                    }
                }


            } else if (Driver.driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0) //single select and true/false question type
            {
                List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                for (WebElement answerchoice : answer) {
                    if (answerchoice.getText().trim().equals(correctchoice)) {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                        break;
                    }
                }
            } else if (Driver.driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0) {
                Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys(correcttextanswer);
            } else if (Driver.driver.findElements(By.className("sbHolder")).size() > 0) {
                try {
                    Driver.driver.findElement(By.className("sbToggle")).click();
                    //Thread.sleep(2000);
                    Driver.driver.findElement(By.linkText(correcttextanswer)).click();
                    //Thread.sleep(5000);
                } catch (Exception e) {
                    Assert.fail("Exception in App helper attemptQuestion in class PracticeTest", e);
                }
            }
            if (confidencelevel != 0)
                Driver.driver.findElement(By.cssSelector("a[id='" + confidencelevel + "']")).click();//click on confidence level

            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));

            int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
            if (noticesize == 1)

            {
                String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
                    Thread.sleep(3000);
                    Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                    Driver.driver.findElement(By.className("al-practice-test-submit-button")).click();
                    Thread.sleep(2000);
                }

            }
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
            Thread.sleep(2000);

        } catch (Exception e) {

            Assert.fail("Exception in App helper AttemptCorrectAnswer in class PracticeTest", e);
        }
    }

    public int questionDifficulty() {
        String diff = null;
        try {
            List<WebElement> debugvalues = Driver.driver.findElements(By.id("show-debug-question-id-label"));
            diff = debugvalues.get(1).getText();
        } catch (Exception e) {

            Assert.fail("Exception in app helper questionDifficulty in class PracticeTest", e);
        }
        return Integer.parseInt(diff.substring(13));
    }

    public void questionPresentCheck(String tcIndex) {
        try {
            DBCollection collection = com.snapwiz.selenium.tests.staf.datacreation.apphelper.DBConnect.mongoConnect("UserQuestionHistory");
            int userid = 0, classid = 0;
            String username = ReadTestData.readDataByTagName("", "user_id", tcIndex);

            ResultSet userids = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.st.executeQuery("SELECT id as userid from t_user where username like '" + username + "';");
            while (userids.next()) {
                userid = userids.getInt("userid");
            }

            ResultSet classsections = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.st.executeQuery("SELECT id as classid FROM t_class_section  where name like 'studtitle';");
            while (classsections.next()) {
                classid = classsections.getInt("classid");
            }


            String tlonamefound = Driver.driver.findElement(By.className("al-diagtest-skills-evaluted-ul")).getText().substring(2);

            List<String> tlonames = new PracticeTest().tloNames();        //Get TLO names
            int index = 0; //Get index at which the TLO is present
            for (String tlo : tlonames) {
                if (tlo.equals(tlonamefound))
                    break;
                index++;
            }
            List<Double> profs = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.getProficienciesFromMongo(userid, classid); //Get the proficiency of the TLO found
            Double tloprof = profs.get(index);
            int proficiency = (int) (tloprof * 100);

            tlonamefound = tlonamefound.replaceAll("'", "''");

            List<Integer> quesids = new ArrayList<Integer>();
            //List<Integer> qidsgrcq = new ArrayList<Integer>();

            ResultSet rstidltcq = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.st.executeQuery("select tq.id as qids from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and" +
                    " tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and" +
                    " ta.id = 11052 and tms.name like '" + tlonamefound + "' and tq.status = 80 order by tq.computed_difficulty;");
            while (rstidltcq.next()) {
                quesids.add(rstidltcq.getInt("qids"));
            }

            List<Integer> notattemptedquestions = new ArrayList<Integer>();
            for (Integer qids : quesids) {
                int value = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.getAttemptedQuestions(qids, collection, userid, classid);
                if (value == 0) {
                    notattemptedquestions.add(qids);
                }
            }
            Map<Integer, Integer> qid_diff = new HashMap<Integer, Integer>();
            for (Integer qid : notattemptedquestions) {
                ResultSet comp_diff = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.st.executeQuery("select tq.computed_difficulty as comp_difficulty from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and" +
                        " tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and" +
                        " ta.id = 11052 and tms.name like '" + tlonamefound + "' and tq.status = 80 and tq.id = " + (qid) + ";");
                while (comp_diff.next()) {
                    qid_diff.put(qid, comp_diff.getInt("comp_difficulty"));
                }
            }
            Set set = qid_diff.entrySet();
            Iterator i = set.iterator();
            Iterator i2 = set.iterator();
            while (i2.hasNext()) {
                Map.Entry me2 = (Map.Entry) i2.next();
                System.out.print("QID: " + me2.getKey() + " ");
                System.out.print("CD :" + me2.getValue() + " ");
                System.out.println("Distance :" + Math.abs((proficiency - (Integer) me2.getValue())));
            }
            System.out.println();
            System.out.println();
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
                qid_diff.put((Integer) me.getKey(), Math.abs((proficiency - (Integer) me.getValue())));
            }
            Integer min = Collections.min(qid_diff.values());
            System.out.println("Minimum Value of Distance " + min);
            List<Integer> expectedqid = new ArrayList<Integer>();
            Iterator i_min = set.iterator();
            while (i_min.hasNext()) {

                Map.Entry me_min = (Map.Entry) i_min.next();
                if ((Integer) me_min.getValue() == min) {
                    expectedqid.add((Integer) me_min.getKey());
                }
            }
            for (Integer qid : expectedqid) System.out.println("Expected questions " + qid);
            List<Integer> expected_comp_diff = new ArrayList<Integer>();
            for (Integer qid : expectedqid) {
                ResultSet comp_diff = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.st.executeQuery("select tq.computed_difficulty as comp_difficulty from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and" +
                        " tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and" +
                        " ta.id = 11052 and tms.name like '" + tlonamefound + "' and tq.status = 80 and tq.id = " + (qid) + ";");
                while (comp_diff.next()) {
                    expected_comp_diff.add(comp_diff.getInt("comp_difficulty"));
                }
            }
            int rangeLower = 0, rangeUpper = 0;
            for (Integer compdiff : expected_comp_diff) {
                rangeLower = compdiff - 15;
                rangeUpper = compdiff + 15;
                System.out.println("Expected Computed Difficulty range " + (compdiff - 15) + "-" + (compdiff + 15));
            }

            System.out.println();
            int questiondifficulty = questionDifficulty();
            System.out.println("computed difficulty found " + questiondifficulty);
            boolean inRange = false;
            if (questiondifficulty >= rangeLower && questiondifficulty <= rangeUpper)
                inRange = true;
            if (inRange == false) {

                Assert.fail("Question not as Expected. " + "Difficulty Found " + questiondifficulty + ". Expected in Range " + rangeLower + "-" + rangeUpper + " from TLO " + tlonamefound + " with Proficiency " + proficiency);
            }

		/*int suggesstedidfromlowerside =0;
		int suggestedidfromupperside =0;
		if(qidsltcq.size() > 0)
		suggesstedidfromlowerside = qidsltcq.get(0);
		if(qidsgrcq.size()>0)
		suggestedidfromupperside =  qidsgrcq.get(0);

		int lowercomputeddifficulty=0;
		ResultSet computeddifffromlowerside = DBConnect.st.executeQuery("SELECT computed_difficulty as compdifflower from t_questions where id= '"+suggesstedidfromlowerside+"'");
		while(computeddifffromlowerside.next())
		{
			lowercomputeddifficulty = computeddifffromlowerside.getInt("compdifflower");
		}

		int uppercomputeddifficulty=0;
		ResultSet computeddifffromupperside = DBConnect.st.executeQuery("SELECT computed_difficulty as compdiffupper from t_questions where id= '"+suggestedidfromupperside+"'");
		while(computeddifffromupperside.next())
		{
			uppercomputeddifficulty = computeddifffromupperside.getInt("compdiffupper");
		}

		//String question_id = Driver.driver.findElement(By.id("show-debug-question-id-label")).getText().substring(13);
		//System.out.println("Qeustion ID found "+question_id);
		System.out.println("lower comp diff "+lowercomputeddifficulty);
		System.out.println("upper comp diff "+uppercomputeddifficulty);
		int rangelower = 0; int rangeupper = 0;
		if(lowercomputeddifficulty == 0 && uppercomputeddifficulty !=0)
		{
			rangelower = uppercomputeddifficulty-10;
			rangeupper = uppercomputeddifficulty+10;
		}
		else if (uppercomputeddifficulty == 0 && lowercomputeddifficulty !=0)
		{
			rangelower = lowercomputeddifficulty-10;
			rangeupper = lowercomputeddifficulty+10;
		}
		else if(uppercomputeddifficulty - cqtloprof < cqtloprof - lowercomputeddifficulty)
		{
			rangelower = uppercomputeddifficulty-10;
			rangeupper = uppercomputeddifficulty+10;
		}
		else if(uppercomputeddifficulty - cqtloprof > cqtloprof - lowercomputeddifficulty)
		{
			rangelower = lowercomputeddifficulty-10;
			rangeupper = lowercomputeddifficulty+10;
		}
		else
		{
			rangelower = lowercomputeddifficulty-10;
			rangeupper = uppercomputeddifficulty+10;
		}
		System.out.println("Lower Range "+rangelower);
		System.out.println("Upper Range "+rangeupper);


		String nextQuesTloName = Driver.driver.findElement(By.className("al-diagtest-skills-evaluted-ul")).getText().substring(2).replaceAll("'", "''");
		System.out.println("Next Ques TLO "+nextQuesTloName);
		boolean inRange = false;
		int questiondifficulty = new PracticeTest().questionDifficulty();
		System.out.println("Difficulty found "+questiondifficulty);
		if(questiondifficulty >=rangelower && questiondifficulty <=rangeupper )
			inRange = true;
		*/
		/*List<Integer> notatteptedltquest_notinrange = new ArrayList<Integer>();
		List<Integer> notatteptedgrquest_notinrange = new ArrayList<Integer>();
		List<Integer> qidsltcq_notinrange = new ArrayList<Integer>();
		List<Integer> qidsgrcq_notinrange = new ArrayList<Integer>();

		List<Integer> inrangeqids = new ArrayList<Integer>();
		List<Integer> notattemptedquestinrange = new ArrayList<Integer>();
		if(inRange == false) //checking if there are non-attempted questions in the TLO which are in Range
		{
			ResultSet inrangequestions = DBConnect.st.executeQuery("select tq.id as qids from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and"+
			        " tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and"+
			        " ta.id = 11052 and tms.name like '"+nextQuesTloName+"' and tq.status = 80 and tq.computed_difficulty >= "+(rangelower)+" and  tq.computed_difficulty <= "+(rangeupper)+";");
					while(inrangequestions.next())
					{
						inrangeqids.add(inrangequestions.getInt("qids"));
					}
			for(Integer qids : inrangeqids)
					{
					int value = DBConnect.getAttemptedQuestions(qids,collection,userid,classid);
					if(value == 0)
						{
						notattemptedquestinrange.add(qids);
						}
					}
			for(Integer ques : inrangeqids) System.out.println("In range questions "+ques);
			for(Integer ques : notattemptedquestinrange) System.out.println("Not attempted questions "+ques);
			if(notattemptedquestinrange.size() > 0)
				Assert.fail("There are questions in this TLO which has computed difficulty in the suggested range and are not attempted but are still not presented as the next question");
		}

		if(inRange == false)
		{
			//int currentquestdiff = questionDifficulty();
			ResultSet rstidltcq_notinrange = DBConnect.st.executeQuery("select tq.id as qids from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and"+
			        " tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and"+
			        " ta.id = 11052 and tms.name like '"+nextQuesTloName+"' and tq.status = 80 and tq.computed_difficulty < "+(rangelower)+" order by tq.computed_difficulty desc;");
					while(rstidltcq_notinrange.next())
					{
						qidsltcq_notinrange.add(rstidltcq_notinrange.getInt("qids"));
					}


		ResultSet rstidgrcq_notinrange = DBConnect.st.executeQuery("select tq.id as qids from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and"+
					" tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and"+
					" ta.id = 11052 and tms.name like '"+nextQuesTloName+"' and tq.status = 80 and tq.computed_difficulty > "+(rangeupper)+" order by tq.computed_difficulty;");
					while(rstidgrcq_notinrange.next())
					{
						qidsgrcq_notinrange.add(rstidgrcq_notinrange.getInt("qids"));

					}


					int suggesstedidfromlowerside_notinrange =0;
					int suggestedidfromupperside_notinrange =0;
		for(Integer qids : qidsltcq_notinrange)
				{
				int value = DBConnect.getAttemptedQuestions(qids,collection,userid,classid);
				if(value == 0)
				{
					notatteptedltquest_notinrange.add(qids);
					suggesstedidfromlowerside_notinrange = notatteptedltquest_notinrange.get(0);
					break;
				}
				}

		for(Integer qids : qidsgrcq_notinrange)
				{
					int value = DBConnect.getAttemptedQuestions(qids,collection,userid,classid);
					if(value == 0)
					{
							notatteptedgrquest_notinrange.add(qids);
							suggestedidfromupperside_notinrange =  notatteptedgrquest_notinrange.get(0);
							break;
					}
				}

		int lowercomputeddifficulty_notinrange=0;
		ResultSet computeddifffromlowerside_notinrange = DBConnect.st.executeQuery("SELECT computed_difficulty as compdifflower from t_questions where id= '"+suggesstedidfromlowerside_notinrange+"'");
		while(computeddifffromlowerside_notinrange.next())
		{
			lowercomputeddifficulty_notinrange = computeddifffromlowerside_notinrange.getInt("compdifflower");
		}

		int uppercomputeddifficulty_notinrange=0;
		ResultSet computeddifffromupperside_notinrange = DBConnect.st.executeQuery("SELECT computed_difficulty as compdiffupper from t_questions where id= '"+suggestedidfromupperside_notinrange+"'");
		while(computeddifffromupperside_notinrange.next())
		{
			uppercomputeddifficulty_notinrange = computeddifffromupperside_notinrange.getInt("compdiffupper");
		}
		System.out.println("suggested from lower side not in range "+lowercomputeddifficulty_notinrange);
		System.out.println("suggested from upper side no in range "+uppercomputeddifficulty_notinrange);
		//int questiondiff = questionDifficulty();
		//System.out.println("Question Difficulty found "+questiondiff);
		if(questiondifficulty == lowercomputeddifficulty_notinrange || questiondifficulty == uppercomputeddifficulty_notinrange)
			inRange=true;

		}
		if(inRange == false)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Next question difficulty not in range. Found Difficulty");
		}*/
            //int lastidofltcq = qidsltcq.get(qidsltcq.size()-1);

        } catch (Exception e) {

            Assert.fail("Exception in App helper questionPresentCheck in class PracticeTest", e);
        }
    }

    public void questionPresentCheckRepeatFlow(String tcIndex) {
        try {
            //String practiceassessmentname= ReadTestData.readDataByTagName("", "practiceassessmentname",tcIndex);
            int assessmentid = 205642;
			/*ResultSet rstassmtid = DBConnect.st.executeQuery("select id as assessment_id from t_assessment where name like '"+practiceassessmentname+"' and assessment_type like 'Adaptive Component Practice';");
			while(rstassmtid.next())
			{
				assessmentid = rstassmtid.getInt("assessment_id");
			}*/

            // System.out.println("Assessment ID "+assessmentid);
            DBCollection collection = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.mongoConnect("UserQuestionHistory");
            int userid = 0, classid = 0;
            String username = ReadTestData.readDataByTagName("", "user_id", tcIndex);

            ResultSet userids = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.st.executeQuery("SELECT id as userid from t_user where username like '" + username + "';");
            while (userids.next()) {
                userid = userids.getInt("userid");
            }

            ResultSet classsections = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.st.executeQuery("SELECT id as classid FROM t_class_section  where name like 'studrepeattitle';");
            while (classsections.next()) {
                classid = classsections.getInt("classid");
            }


            String tlonamefound = Driver.driver.findElement(By.className("al-diagtest-skills-evaluted-ul")).getText().substring(2);

            List<String> tlonames = new PracticeTest().tloNames();        //Get TLO names
            int index = 0; //Get index at which the TLO is present
            for (String tlo : tlonames) {
                if (tlo.equals(tlonamefound))
                    break;
                index++;
            }
            List<Double> profs = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.getProficienciesFromMongo(userid, classid); //Get the proficiency of the TLO found
            Double tloprof = profs.get(index);
            int proficiency = (int) (tloprof * 100);

            tlonamefound = tlonamefound.replaceAll("'", "''");

            List<Integer> quesids = new ArrayList<Integer>();
            //List<Integer> qidsgrcq = new ArrayList<Integer>();

            ResultSet rstidltcq = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.st.executeQuery("select tq.id as qids from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and" +
                    " tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and" +
                    " ta.id = " + assessmentid + " and tms.name like '" + tlonamefound + "' and tq.status = 80 order by tq.computed_difficulty;");
            while (rstidltcq.next()) {
                quesids.add(rstidltcq.getInt("qids"));
            }

            List<Integer> questions = new ArrayList<Integer>();
            for (Integer qids : quesids) {
                int value = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.getAttemptedQuestions(qids, collection, userid, classid);
                if (value == 0) {
                    questions.add(qids);
                    System.out.println("Unattempted question with id " + qids + " expected. TLO " + tlonamefound);
                }
            }
            System.out.println("Size initially " + questions.size());
            if (questions.size() == 0) //If all questions are attempted
            {
                for (Integer qids : quesids) {
                    int value = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.getQuestions(qids, collection, userid, classid, "skipped"); //checking for skipped questions
                    if (value != 0) {
                        questions.add(qids);
                        System.out.println("Skipped question with id " + qids + " expected. Questions Exhaused TLO " + tlonamefound);
                    }
                }

            }

            if (questions.size() == 0) //If there are no skipped questions, checking for incorrectly answered questions
            {
                for (Integer qids : quesids) {
                    int value = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.getQuestions(qids, collection, userid, classid, "incorrect"); //checking for incorrectly answered questions
                    if (value != 0) {
                        questions.add(qids);
                        System.out.println("Incorrect question with id " + qids + " expected. Questions Exhaused TLO " + tlonamefound);
                    }
                }

            }

            if (questions.size() == 0) //If there are no skipped and incorrect questions, checking for correctly answered questions
            {
                for (Integer qids : quesids) {
                    int value = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.getQuestions(qids, collection, userid, classid, "correct"); //checking for correctly answered questions
                    if (value != 0) {
                        questions.add(qids);
                        System.out.println("Correct question with id " + qids + " expected. Questions Exhausted TLO: " + tlonamefound);
                    }
                }

            }

            String question_id_found = Driver.driver.findElement(By.id("show-debug-question-id-label")).getText().substring(13);
            System.out.println("Question Found " + question_id_found);
            //if(!tlonamefound.equals("Outline the characteristics that make a company admired."))
            //{
            if (!questions.contains(Integer.parseInt(question_id_found))) {

                Assert.fail("Question Found " + question_id_found + ". Expected " + questions + " from TLO " + tlonamefound);
            }
            //}
            Map<Integer, Integer> qid_diff = new HashMap<Integer, Integer>();
            for (Integer qid : questions) {
                ResultSet comp_diff = com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect.st.executeQuery("select tq.computed_difficulty as comp_difficulty from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and" +
                        " tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and" +
                        " ta.id = " + assessmentid + " and tms.name like '" + tlonamefound + "' and tq.status = 80 and tq.id = " + (qid) + ";");
                while (comp_diff.next()) {
                    qid_diff.put(qid, comp_diff.getInt("comp_difficulty"));
                }
            }
            Set set = qid_diff.entrySet();
            Iterator i = set.iterator();
            Iterator i2 = set.iterator();
            while (i2.hasNext()) {
                Map.Entry me2 = (Map.Entry) i2.next();
                System.out.print("QID: " + me2.getKey() + " ");
                System.out.print("CD :" + me2.getValue() + " ");
                System.out.println("Distance :" + Math.abs((proficiency - (Integer) me2.getValue())));
            }
            System.out.println();
            System.out.println();
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
                qid_diff.put((Integer) me.getKey(), Math.abs((proficiency - (Integer) me.getValue())));
            }
            Integer min = Collections.min(qid_diff.values());
            System.out.println("Minimum Value of Distance " + min);
            List<Integer> expectedqid = new ArrayList<Integer>();
            Iterator i_min = set.iterator();
            while (i_min.hasNext()) {

                Map.Entry me_min = (Map.Entry) i_min.next();
                if ((Integer) me_min.getValue() == min) {
                    expectedqid.add((Integer) me_min.getKey());
                }
            }
            //for(Integer qid : expectedqid) System.out.println("Expected questions "+qid);
            List<Integer> expected_comp_diff = new ArrayList<Integer>();
            for (Integer qid : expectedqid) {
                ResultSet comp_diff = DBConnect.st.executeQuery("select tq.computed_difficulty as comp_difficulty from t_questions tq, t_assessment ta, t_question_set tqs, t_question_set_item tqsi,t_master_skill tms, t_question_skill_map tqsm where ta.id = tqs.assessment_id and" +
                        " tq.id = tqsi.question_id and tqs.id = tqsi.question_set_id and tms.id = tqsm.master_skill_id and tq.id = tqsm.question_id and" +
                        " ta.id = " + assessmentid + " and tms.name like '" + tlonamefound + "' and tq.status = 80 and tq.id = " + (qid) + ";");
                while (comp_diff.next()) {
                    expected_comp_diff.add(comp_diff.getInt("comp_difficulty"));
                }
            }
            int rangeLower = 0, rangeUpper = 0;
            for (Integer compdiff : expected_comp_diff) {
                rangeLower = compdiff - 15;
                rangeUpper = compdiff + 15;
                System.out.println("Expected Computed Difficulty range " + (compdiff - 15) + "-" + (compdiff + 15));
            }

            System.out.println();
            int questiondifficulty = questionDifficulty();
            System.out.println("computed difficulty found " + questiondifficulty);
            boolean inRange = false;
            if (questiondifficulty >= rangeLower && questiondifficulty <= rangeUpper)
                inRange = true;
            if (inRange == false) {

                Assert.fail("Question not as Expected. " + "Difficulty Found " + questiondifficulty + ". Expected in Range " + rangeLower + "-" + rangeUpper + " from TLO " + tlonamefound + " with Proficiency " + proficiency);
            }
        } catch (Exception e) {
            Assert.fail("", e);
        }
    }

    public void attemptRandomly(int noOfQuestions)
    {
        try
        {
            for(int i = 0 ; i<noOfQuestions;i++)
            {
                try {
                    String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                    char correctanswer=corranswer.charAt(17);
                    String correctchoice=Character.toString(correctanswer);
                    System.out.println("Correct Answer "+correctchoice);
                    if(correctchoice.equals("A") || correctchoice.equals("B") || correctchoice.equals("C") || correctchoice.equals("D")) {
                        int random = new RandomNumber().generateRandomNumber(0, 100);
                        if(random  > 70) {
                            if(correctchoice.equals("A") || correctchoice.equals("C") || correctchoice.equals("D")) {
                                correctchoice = "B";
                            }
                            else
                                correctchoice = "A";

                        }
                        List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice : answer) {
                            if (answerchoice.getText().trim().equals(correctchoice)) {
                                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                                break;
                            }
                        }
                    }
                    else {
                        Driver.driver.findElement(By.className("qtn-label")).click();
                    }

                }
                catch (Exception e)  {

                }
                int confidencelevel = new RandomNumber().generateRandomNumber(0, 5);
                if(confidencelevel!=0)
                {
                    Driver.driver.findElement(By.cssSelector("a[id='"+confidencelevel+"']")).click();//click on confidence level
                }
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                try {
                    Driver.driver.findElement(By.className("al-notification-message-body"));

                        String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                        if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            Driver.driver.findElement(By.className("al-practice-test-submit-button")).click();
                            Thread.sleep(1000);
                        }


                }
                catch (Exception e) {

                }
                Thread.sleep(500);
                try {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                }
                catch (Exception e) {

                }
                Thread.sleep(1000);
            }
        }
        catch(Exception e)
        {

            Assert.fail("Exception in app helper attemptRandomly in class DiagnosticTest",e);
        }
    }

    public void openLastPracticeTest() {
        try {
            List<WebElement> allPractice = Driver.driver.findElements(By.cssSelector("span[title='Practice']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allPractice.get(allPractice.size() - 1));//click on latest created practice test
            Thread.sleep(2000);
        } catch (Exception e) {

            Assert.fail("Exception in App helper openLastPracticeTest in class PracticeTest", e);
        }
    }


    public void attemptPracticeQuesFromEachTLO(int questionsFromEachTLO, String answerChoice, int confidence, boolean useHints, boolean useSolutionText) {
        try {
            List<String> tlonames = new PracticeTest().tloNames();        //Get TLO names
            //List<String> tlosattempted = new ArrayList<String>();
            int tlo1cnt = 0;
            int tlo2cnt = 0;
            int tlo3cnt = 0;
            int tlo4cnt = 0;
            int tlo5cnt = 0;
            int tlo6cnt = 0;
            int tlo7cnt = 0;

            boolean done = false;
            while (done == false) {
                String tlonamefound = Driver.driver.findElement(By.className("al-diagtest-skills-evaluted-ul")).getText().substring(2); //TLO from Question Page
                System.out.println(tlonamefound);
                System.out.println(tlonames.get(4));
                if (tlonamefound.equals(tlonames.get(0)))
                    tlo1cnt++;
                if (tlonamefound.equals(tlonames.get(1)))
                    tlo2cnt++;
                if (tlonamefound.equals(tlonames.get(2)))
                    tlo3cnt++;
                if (tlonamefound.equals(tlonames.get(3)))
                    tlo4cnt++;
                if (tlonamefound.equals(tlonames.get(4)))
                    tlo5cnt++;
                if (tlonamefound.equals(tlonames.get(5)))
                    tlo6cnt++;
                if (tlonamefound.equals(tlonames.get(6)))
                    tlo7cnt++;
                //for loop ends
                System.out.println(tlo1cnt + " " + tlo2cnt + " " + tlo3cnt + " " + tlo4cnt + " " + tlo5cnt + " " + tlo6cnt + " " + tlo7cnt);
                if (tlo1cnt >= questionsFromEachTLO && tlo2cnt >= questionsFromEachTLO && tlo3cnt >= questionsFromEachTLO && tlo4cnt >= questionsFromEachTLO && tlo5cnt >= questionsFromEachTLO && tlo6cnt >= questionsFromEachTLO && tlo7cnt >= questionsFromEachTLO)
                    done = true;
                attemptQuestion(answerChoice, confidence, useHints, useSolutionText);
            }  //while loop ends
        } catch (Exception e) {

            Assert.fail("Exception in App helper attemptPracticeQuesFromEachTLO in class PracticeTest", e);
        }
    }

    public void attemptQuestion(String answeroption, int confidencelevel, boolean useHints, boolean useSolutionText) {
        try {
            if (answeroption.equalsIgnoreCase("skip")) {

                if (useHints == true)
                    Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();

                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                Thread.sleep(2000);
                int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
                if (noticesize == 1)

                {
                    String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                    if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
                        Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                        Thread.sleep(2000);

                    }

                }
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                Thread.sleep(2000);

            } //logic for skipping questions ends

            else if (answeroption.equalsIgnoreCase("correct")) {
                String confidence = Integer.toString(confidencelevel);

                if (useHints == true)
                    Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();

                if (Driver.driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size() > 0) {
                    String corranswer = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                    char correctanswer;
                    if (corranswer.charAt(17) == '(')
                        correctanswer = corranswer.charAt(18);
                    else
                        correctanswer = corranswer.charAt(17);
                    String correctchoice = Character.toString(correctanswer);

                    String corranswer1 = corranswer;
                    char correctanswer1 = corranswer1.charAt(22);
                    String correctchoice1 = Character.toString(correctanswer1);
                    List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                    for (WebElement answerchoice : answer) {

                        if (answerchoice.getText().trim().equals(correctchoice)) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                            break;
                        }
                    }
                    List<WebElement> answer1 = Driver.driver.findElements(By.className("qtn-label"));
                    for (WebElement answerchoice1 : answer1) {

                        if (answerchoice1.getText().trim().equals(correctchoice1)) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice1);
                            break;
                        }
                    }

                } else if (Driver.driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0) {
                    String corranswer = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                    char correctanswer = corranswer.charAt(17);
                    String correctchoice = Character.toString(correctanswer);
                    List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                    for (WebElement answerchoice : answer) {

                        if (answerchoice.getText().trim().equals(correctchoice)) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                            break;
                        }
                    }
                } else if (Driver.driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0) {
                    String corranswertext = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                    int lastindex = corranswertext.length();
                    String corrcttextanswer = corranswertext.substring(16, lastindex);
                    Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer);
                } else if (Driver.driver.findElements(By.className("sbHolder")).size() > 0) {
                    String corranswertext = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                    int lastindex = corranswertext.length();
                    String corrcttextanswer = corranswertext.substring(16, lastindex);
                    Driver.driver.findElement(By.className("sbToggle")).click();
                    Thread.sleep(2000);
                    Driver.driver.findElement(By.linkText(corrcttextanswer)).click();
                    Thread.sleep(5000);
                }

                Thread.sleep(2000);
                if (confidencelevel != 0) {
                    Driver.driver.findElement(By.cssSelector("a[id='" + confidence + "']")).click();//click on confidence level
                    Thread.sleep(2000);
                }
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                Thread.sleep(2000);
                int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
                if (noticesize == 1)

                {
                    String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                    if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
                        Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                        Thread.sleep(2000);
                    }
                }
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                Thread.sleep(2000);


            } //attepting correct answer ends
            else {
                int recommendation = Driver.driver.findElements(By.className("al-notification-message-wrapper")).size();
                if (recommendation > 0) {
                    String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                    if (notice.contains("Looks like you might want to study this objective in WileyPLUS. Go to WileyPLUS for")) {
                        Thread.sleep(3000);
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span[id='exit-practice-test-block']")));
                        //Driver.driver.findElement(By.cssSelector("span[id='exit-practice-test-block']")).click();

                    }
                }
                if (useHints == true) {

                    Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
                    Thread.sleep(2000);
                    int hintpopup = Driver.driver.findElements(By.className("al-notification-message-header")).size();
                    if (hintpopup > 0) {
                        Driver.driver.findElement(By.xpath("/html/body")).click();
                        Thread.sleep(2000);
                    }
                }
                String confidence = Integer.toString(confidencelevel);

                String corranswertext = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                int lastindex = corranswertext.length();
                String corrcttextanswer = corranswertext.substring(16, lastindex);

                if (Driver.driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size() > 0) {
                    String corranswer = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                    char correctanswer;
                    if (corranswer.charAt(17) == '(')
                        correctanswer = corranswer.charAt(18);
                    else
                        correctanswer = corranswer.charAt(17);

                    String correctchoice = Character.toString(correctanswer); //first correct choice

                    char correctanswer1 = corranswer.charAt(22);
                    String correctchoice1 = Character.toString(correctanswer1); //second correct choice

                    if ((correctchoice.equals("A") && correctchoice1.equals("B")) || (correctchoice.equals("B") && correctchoice1.equals("A"))) {
                        correctchoice = "C";
                        correctchoice1 = "D";
                    } else if (correctchoice.equals("A") && correctchoice1.equals("C") || (correctchoice.equals("C") && correctchoice1.equals("A"))) {
                        correctchoice = "B";
                        correctchoice1 = "D";
                    } else if (correctchoice.equals("A") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("A"))) {
                        correctchoice = "B";
                        correctchoice1 = "C";
                    } else if (correctchoice.equals("A") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("A"))) {
                        correctchoice = "B";
                        correctchoice1 = "C";
                    } else if (correctchoice.equals("A") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("A"))) {
                        correctchoice = "B";
                        correctchoice1 = "C";
                    } else if ((correctchoice.equals("B") && correctchoice1.equals("C")) || (correctchoice.equals("C") && correctchoice1.equals("B"))) {
                        correctchoice = "A";
                        correctchoice1 = "D";
                    } else if (correctchoice.equals("B") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("B"))) {
                        correctchoice = "A";
                        correctchoice1 = "C";
                    } else if (correctchoice.equals("B") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("B"))) {
                        correctchoice = "A";
                        correctchoice1 = "C";
                    } else if (correctchoice.equals("B") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("B"))) {
                        correctchoice = "A";
                        correctchoice1 = "C";
                    } else if ((correctchoice.equals("C") && correctchoice1.equals("D")) || (correctchoice.equals("D") && correctchoice1.equals("C"))) {
                        correctchoice = "A";
                        correctchoice1 = "B";
                    } else if (correctchoice.equals("C") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("C"))) {
                        correctchoice = "A";
                        correctchoice1 = "B";
                    } else if (correctchoice.equals("C") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("C"))) {
                        correctchoice = "A";
                        correctchoice1 = "B";
                    } else if ((correctchoice.equals("D") && correctchoice1.equals("E")) || (correctchoice.equals("E") && correctchoice1.equals("D"))) {
                        correctchoice = "A";
                        correctchoice1 = "B";
                    } else if (correctchoice.equals("D") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("D"))) {
                        correctchoice = "A";
                        correctchoice1 = "B";
                    } else if ((correctchoice.equals("E") && correctchoice1.equals("F")) || (correctchoice.equals("F") && correctchoice1.equals("E"))) {
                        correctchoice = "A";
                        correctchoice1 = "B";
                    }
                    List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                    for (WebElement answerchoice : answer) {

                        if (answerchoice.getText().trim().equals(correctchoice)) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                            break;
                        }
                    }
                    List<WebElement> answer1 = Driver.driver.findElements(By.className("qtn-label"));
                    for (WebElement answerchoice1 : answer1) {

                        if (answerchoice1.getText().trim().equals(correctchoice1)) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice1);
                            break;
                        }
                    }

                } else if (Driver.driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0) {
                    String corranswer = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                    char correctanswer = corranswer.charAt(17);
                    String correctchoice = Character.toString(correctanswer);
                    if (correctchoice.equals("A"))
                        correctchoice = "B";
                    else if (correctchoice.equals("B"))
                        correctchoice = "A";
                    else if (correctchoice.equals("C"))
                        correctchoice = "D";
                    else if (correctchoice.equals("D"))
                        correctchoice = "C";
                    List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                    for (WebElement answerchoice : answer) {

                        if (answerchoice.getText().trim().equals(correctchoice)) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                            break;
                        }
                    }

                } else if (Driver.driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0) {

                    Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer + "bh");
                } else if (Driver.driver.findElements(By.className("sbHolder")).size() > 0) {
                    Driver.driver.findElement(By.className("sbToggle")).click();
                    Thread.sleep(2000);

                    String values = Driver.driver.findElement(By.cssSelector("ul[class='sbOptions']")).getText();
                    //values = values.replaceAll("\n", " ");
                    String[] val = values.split("\n");


                    for (String element : val) {
                        if (!element.equals(corrcttextanswer)) {
                            Driver.driver.findElement(By.linkText(element)).click();
                            break;
                        }
                    }
                }
                //	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/div/label")));
                //	Thread.sleep(2000);
                Thread.sleep(2000);

                if (confidencelevel != 0) {
                    Driver.driver.findElement(By.cssSelector("a[id='" + confidence + "']")).click();//click on confidence level
                    Thread.sleep(2000);
                }
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                Thread.sleep(2000);
                int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
                if (noticesize == 1)

                {
                    String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                    if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
                        Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                        Thread.sleep(2000);

                    }

                }
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                Thread.sleep(2000);


            }
        } catch (Exception e) {

            Assert.fail("Exception in App helper attemptQuestion in class PracticeTest", e);
        }
    }

    public void submitOnlyQuestion() {
        try {
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
            Thread.sleep(2000);
            int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
            if (noticesize == 1)

            {
                String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
                    Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                    Thread.sleep(2000);

                }

            }
        } catch (Exception e) {

            Assert.fail("Exception in App helper submitOnlyQuestion in class PracticeTest", e);
        }
    }

    public void practiceTestAttempt(int confidencelevel, int numberofquestionattempt, String answeroption, boolean useHints, boolean showSolutionText) {
        try {

            for (int i = 0; i < numberofquestionattempt; i++) {
                int random = new RandomNumber().generateRandomNumber(0, 100);

                if (random > 50) answeroption = "correct";
                else if (random > 20 && random < 50) answeroption = "incorrect";
                else answeroption = "skip";
                if (answeroption.equalsIgnoreCase("skip")) {

                    if (useHints == true)
                        Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();

                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                    Thread.sleep(2000);
                    int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
                    if (noticesize == 1)

                    {
                        String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                        if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                            Thread.sleep(2000);

                        }

                    }
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                    Thread.sleep(2000);
                }
                //logic for skipping questions ends

                else if (answeroption.equalsIgnoreCase("correct")) {

                    String confidence = Integer.toString(confidencelevel);

                    if (useHints == true)
                        Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();

                    if (Driver.driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size() > 0) {
                        String corranswer = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctanswer;
                        if (corranswer.charAt(17) == '(')
                            correctanswer = corranswer.charAt(18);
                        else
                            correctanswer = corranswer.charAt(17);
                        String correctchoice = Character.toString(correctanswer);

                        String corranswer1 = corranswer;
                        char correctanswer1 = corranswer1.charAt(22);
                        String correctchoice1 = Character.toString(correctanswer1);
                        List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice : answer) {

                            if (answerchoice.getText().trim().equals(correctchoice)) {
                                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                                break;
                            }
                        }
                        List<WebElement> answer1 = Driver.driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice1 : answer1) {

                            if (answerchoice1.getText().trim().equals(correctchoice1)) {
                                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice1);
                                break;
                            }
                        }

                    } else if (Driver.driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0) {
                        String corranswer = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctanswer = corranswer.charAt(17);
                        String correctchoice = Character.toString(correctanswer);
                        List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice : answer) {

                            if (answerchoice.getText().trim().equals(correctchoice)) {
                                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                                break;
                            }
                        }
                    } else if (Driver.driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0) {
                        String corranswertext = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        int lastindex = corranswertext.length();
                        String corrcttextanswer = corranswertext.substring(16, lastindex);
                        Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer);
                    } else if (Driver.driver.findElements(By.className("sbHolder")).size() > 0) {
                        String corranswertext = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        int lastindex = corranswertext.length();
                        String corrcttextanswer = corranswertext.substring(16, lastindex);
                        Driver.driver.findElement(By.className("sbToggle")).click();
                        Thread.sleep(2000);
                        Driver.driver.findElement(By.linkText(corrcttextanswer)).click();
                        Thread.sleep(5000);
                    }

                    Thread.sleep(2000);
                    if (confidencelevel != 0) {
                        Driver.driver.findElement(By.cssSelector("a[id='" + confidence + "']")).click();//click on confidence level
                        Thread.sleep(2000);
                    }
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                    Thread.sleep(2000);
                    int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
                    if (noticesize == 1)

                    {
                        String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                        if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                            Thread.sleep(2000);
                        }
                    }
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                    Thread.sleep(2000);


                }
                //attepting correct answer ends
                else {

                    if (useHints == true)
                        Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
                    Thread.sleep(2000);
                    int hintpopup = Driver.driver.findElements(By.className("al-notification-message-header")).size();
                    if (hintpopup > 0) {
                        Driver.driver.findElement(By.xpath("/html/body")).click();
                        Thread.sleep(2000);
                    }
                    String confidence = Integer.toString(confidencelevel);

                    String corranswertext = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                    int lastindex = corranswertext.length();
                    String corrcttextanswer = corranswertext.substring(16, lastindex);

                    if (Driver.driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size() > 0) {
                        String corranswer = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctanswer;
                        if (corranswer.charAt(17) == '(')
                            correctanswer = corranswer.charAt(18);
                        else
                            correctanswer = corranswer.charAt(17);

                        String correctchoice = Character.toString(correctanswer); //first correct choice

                        char correctanswer1 = corranswer.charAt(22);
                        String correctchoice1 = Character.toString(correctanswer1); //second correct choice
                        System.out.println("Correct answer 1 in attemptincorrect in muliple chocce " + correctchoice);
                        System.out.println("Correct answer 2 in attemptincorrect in muliple chocce " + correctchoice1);


                        if ((correctchoice.equals("A") && correctchoice1.equals("B")) || (correctchoice.equals("B") && correctchoice1.equals("A"))) {
                            correctchoice = "C";
                            correctchoice1 = "D";
                        } else if (correctchoice.equals("A") && correctchoice1.equals("C") || (correctchoice.equals("C") && correctchoice1.equals("A"))) {
                            correctchoice = "B";
                            correctchoice1 = "D";
                        } else if (correctchoice.equals("A") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("A"))) {
                            correctchoice = "B";
                            correctchoice1 = "C";
                        } else if (correctchoice.equals("A") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("A"))) {
                            correctchoice = "B";
                            correctchoice1 = "C";
                        } else if (correctchoice.equals("A") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("A"))) {
                            correctchoice = "B";
                            correctchoice1 = "C";
                        } else if ((correctchoice.equals("B") && correctchoice1.equals("C")) || (correctchoice.equals("C") && correctchoice1.equals("B"))) {
                            correctchoice = "A";
                            correctchoice1 = "D";
                        } else if (correctchoice.equals("B") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("B"))) {
                            correctchoice = "A";
                            correctchoice1 = "C";
                        } else if (correctchoice.equals("B") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("B"))) {
                            correctchoice = "A";
                            correctchoice1 = "C";
                        } else if (correctchoice.equals("B") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("B"))) {
                            correctchoice = "A";
                            correctchoice1 = "C";
                        } else if ((correctchoice.equals("C") && correctchoice1.equals("D")) || (correctchoice.equals("D") && correctchoice1.equals("C"))) {
                            correctchoice = "A";
                            correctchoice1 = "B";
                        } else if (correctchoice.equals("C") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("C"))) {
                            correctchoice = "A";
                            correctchoice1 = "B";
                        } else if (correctchoice.equals("C") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("C"))) {
                            correctchoice = "A";
                            correctchoice1 = "B";
                        } else if ((correctchoice.equals("D") && correctchoice1.equals("E")) || (correctchoice.equals("E") && correctchoice1.equals("D"))) {
                            correctchoice = "A";
                            correctchoice1 = "B";
                        } else if (correctchoice.equals("D") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("D"))) {
                            correctchoice = "A";
                            correctchoice1 = "B";
                        } else if ((correctchoice.equals("E") && correctchoice1.equals("F")) || (correctchoice.equals("F") && correctchoice1.equals("E"))) {
                            correctchoice = "A";
                            correctchoice1 = "B";
                        }
                        List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice : answer) {

                            if (answerchoice.getText().trim().equals(correctchoice)) {
                                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                                break;
                            }
                        }
                        List<WebElement> answer1 = Driver.driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice1 : answer1) {

                            if (answerchoice1.getText().trim().equals(correctchoice1)) {
                                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice1);
                                break;
                            }
                        }

                    } else if (Driver.driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0) {
                        String corranswer = Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctanswer = corranswer.charAt(17);
                        String correctchoice = Character.toString(correctanswer);
                        if (correctchoice.equals("A"))
                            correctchoice = "B";
                        else if (correctchoice.equals("B"))
                            correctchoice = "A";
                        else if (correctchoice.equals("C"))
                            correctchoice = "D";
                        else if (correctchoice.equals("D"))
                            correctchoice = "C";
                        List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice : answer) {

                            if (answerchoice.getText().trim().equals(correctchoice)) {
                                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                                break;
                            }
                        }

                    } else if (Driver.driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0) {

                        Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer + "bh");
                    } else if (Driver.driver.findElements(By.className("sbHolder")).size() > 0) {
                        Driver.driver.findElement(By.className("sbToggle")).click();
                        Thread.sleep(2000);

                        String values = Driver.driver.findElement(By.cssSelector("ul[class='sbOptions']")).getText();
                        //values = values.replaceAll("\n", " ");
                        String[] val = values.split("\n");


                        for (String element : val) {
                            if (!element.equals(corrcttextanswer)) {
                                Driver.driver.findElement(By.linkText(element)).click();
                                break;
                            }
                        }
                    }
                    //	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/div/label")));
                    //	Thread.sleep(2000);
                    Thread.sleep(2000);
                    if (confidencelevel != 0) {
                        Driver.driver.findElement(By.cssSelector("a[id='" + confidence + "']")).click();//click on confidence level
                        Thread.sleep(2000);
                    }
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                    Thread.sleep(2000);
                    int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
                    if (noticesize == 1)

                    {
                        String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                        if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                            Thread.sleep(2000);

                        }

                    }
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                    Thread.sleep(2000);


                }
            }
            Driver.driver.findElement(By.className("al-quit-diag-test-icon")).click();
            Thread.sleep(1000);
            Driver.driver.findElement(By.className("al-diag-test-continue-later")).click();
            Thread.sleep(2000);
        } catch (Exception e) {

            Assert.fail("Exception in App helper method  AdaptiveTestInBetween in class practicetest", e);
        }
    }

    //@author Sumit
    //open a TLO level practice test based on Index(just passed the index of TLO needs to be opened
    public void openTLOLevelPracticeTestBasedOnIndex(int tloIndex) {
        try {
            List<WebElement> tloList = Driver.driver.findElements(By.className("al-terminal-objective-title"));
            new Actions(Driver.driver).moveToElement(tloList.get(tloIndex)).build().perform();
            List<WebElement> allPractice = Driver.driver.findElements(By.cssSelector("div[title='Practice']"));
            for (WebElement practice : allPractice) {
                if (practice.isDisplayed() == true) {
                    practice.click();
                    Thread.sleep(2000);
                    break;
                }
            }
        } catch (Exception e) {
            Assert.fail("Exception in App helper method  openTLOLevelPracticeTestBasedOnIndex in class PracticeTest", e);
        }
    }

    public void startTestLS(int testNo) {
        try {
            //Driver.driver.findElement(By.cssSelector("span[title='Practice']")).click();
            if(testNo == 1) {
                (new WebDriverWait(Driver.driver, 12))
                        .until(ExpectedConditions.presenceOfElementLocated(By.linkText("Personalized Practice - Ch 3: Así es mi familia")));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Personalized Practice - Ch 3: Así es mi familia")));
            }
            else if(testNo == 2) {
                (new WebDriverWait(Driver.driver, 12))
                        .until(ExpectedConditions.presenceOfElementLocated(By.linkText("Personalized Practice - Ch 2: The Chemical Foundation of Life")));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Personalized Practice - Ch 2: The Chemical Foundation of Life")));
            }
            Thread.sleep(3000);
        } catch (Exception e) {

            Assert.fail("Exception in starting the practice test", e);
        }
    }

    //starting static practice test like the one for ls course type
    public void startStaticPracticeTestLS() {
        try {
            //Driver.driver.findElement(By.cssSelector("span[title='Practice']")).click();
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[data-type='static_assessment']")));
            Thread.sleep(3000);
        } catch (Exception e) {

            Assert.fail("Exception in starting the practice test", e);
        }
    }

    public void attemptCorrectAnswerLS(int numberofQuestions) {
        try {
            boolean skipOthers;
            int random = new RandomNumber().generateRandomNumber(0, 100);
            for(int i=0 ;i<numberofQuestions;i++) {
                System.out.println("Question No "+i);
                skipOthers = false;


                try {
                    if(random <= 60) {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.className("choice-value"))).click();
                    }
                    else {
                        Driver.driver.findElements(By.className("choice-value")).get(1).click();
                    }
                } catch (Exception e) {
                    skipOthers = true;
                }

            /*if (Driver.driver.findElements(By.className("preview-multiple-select-answer-choice")).size() > 0)
                new AttemptDiagnosticQuestion().attemptMultipleSelection("correct", false, false, new RandomNumber().generateRandomNumber(1, 4), "");

            else if (Driver.driver.findElements(By.className("preview-single-selection-answer-choices")).size() > 0)
                new AttemptDiagnosticQuestion().attemptMultipleChoice("correct", false, false, new RandomNumber().generateRandomNumber(1, 4), "");

            else if (Driver.driver.findElements(By.className("true-false-answer-choices")).size() > 0)
                new AttemptDiagnosticQuestion().attemptTrueFalse("correct", false, false, new RandomNumber().generateRandomNumber(1, 4), "");

            else if (Driver.driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptTextEntry("correct", false, false, new RandomNumber().generateRandomNumber(1, 4), "");

            else if (Driver.driver.findElements(By.cssSelector("div[class='text-entry-dropdown-wrapper visible_redactor_input_wrapper']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptTextDropDown("correct", false, false, new RandomNumber().generateRandomNumber(1, 4), "");

            else if (Driver.driver.findElements(By.className("numeric_entry_student_title")).size() > 0)
                new AttemptDiagnosticQuestion().attemptNumericEntryWithUnits("correct", false, false, new RandomNumber().generateRandomNumber(1, 4), "");

            else if (Driver.driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptAdvancedNumeric("correct", false, false, new RandomNumber().generateRandomNumber(1, 4), "");

            else if (Driver.driver.findElements(By.cssSelector("div[class='symb-notation-math-edit-icon-preview']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptExpressionEvaluator("correct", false, false, new RandomNumber().generateRandomNumber(1, 4), "");

            else if (Driver.driver.findElements(By.cssSelector("div[id='html-editor-non-draggable']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptEssayType("correct", false, false, new RandomNumber().generateRandomNumber(1, 4), "");
*/
                try {
                    (new WebDriverWait(Driver.driver, 2))
                            .until(ExpectedConditions.presenceOfElementLocated(By.id("submit-practice-question-button"))).click();
                }
                catch (Exception e) {
                }

               // ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("submit-practice-question-button")));
                try {
                        Driver.driver.findElement(By.className("al-notification-message-body"));
                        Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();

                        if (Driver.driver.findElement(By.id("next-practice-question-button")).isDisplayed() == true) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("next-practice-question-button")));

                        } else {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("submit-practice-question-button")));
                        }

                }
                catch (Exception e) {

                }
                try {
                    (new WebDriverWait(Driver.driver, 4))
                            .until(ExpectedConditions.presenceOfElementLocated(By.id("next-practice-question-button"))).click();
                }
                catch (Exception e) {

                }

            }
        }
        catch (Exception e) {

            Assert.fail("Exception in App helper AttemptCorrectAnswer in class PracticeTest", e);
        }

    }

    public void attemptStaticPractice(int numberofQuestions)
    {
        try {
            boolean skipOthers;
            int random = new RandomNumber().generateRandomNumber(0, 100);
            for (int i = 0; i < numberofQuestions; i++) {
                System.out.println("Question No " + i);
                skipOthers = false;
                try {
                    if (random <= 60) {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.className("choice-value"))).click();
                    } else {
                        Driver.driver.findElements(By.className("choice-value")).get(1).click();
                    }
                } catch (Exception e) {
                    try {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.className("true-false-student-answer-label"))).click();
                    }
                    catch (Exception e1) {

                    }
                }

                try {
                    (new WebDriverWait(Driver.driver, 2))
                            .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='Submit Answer']"))).click();
                }
                catch (Exception e) {
                }

                try {
                    Driver.driver.findElement(By.className("al-notification-message-body"));
                    Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();

                    if (Driver.driver.findElement(By.cssSelector("input[value='Next Question']")).isDisplayed() == true) {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[value='Next Question']")));

                    } else {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[value='Submit Answer']")));
                    }

                }
                catch (Exception e) {

                }


                try {
                    (new WebDriverWait(Driver.driver, 4))
                            .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='Next Question']"))).click();
                }
                catch (Exception e) {

                }

            }
        }
        catch (Exception e) {

        }

    }
}




