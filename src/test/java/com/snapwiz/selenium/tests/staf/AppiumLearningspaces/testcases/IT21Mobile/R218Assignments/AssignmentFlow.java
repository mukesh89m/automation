package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.testcases.IT21Mobile.R218Assignments;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Config;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.AssignmentPolicy;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelperAppium.*;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium.Assignments_appium;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.Assignments.Policies;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.UIElement;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelperAppium.BooleanValue;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.apache.xpath.SourceTree;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Snapwiz on 01/09/15.
 */
public class AssignmentFlow extends Driver {

    @Test(priority = 1, enabled = true)
    public void gradableAssignment (){
        try{

            WebDriverWait  wait = new WebDriverWait(appiumDriver,10);
            String dataIndex = "9";

            //TC row no 9

          /*  ArrayList<String> assessmentInfoList = new ArrayList<>();
            ArrayList<String> instructorInfoList = new ArrayList<>();
            ArrayList<String> studentInfoList = new ArrayList<>();

            assessmentInfoList.add("15_Assessment_gradableAssignment_16010a");
            instructorInfoList.add("Ins_gradableAssignment_15_16010a");
            studentInfoList.add("stu_gradableAssignment_15_16010a");*/







            String contentIssue = "This is a content Issue";
            ArrayList<String> instructorInfoList = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            ArrayList<String> assessmentInfoList = new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");

            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), Config.cmsPassword, dataIndex);
            new Assignment().assignToStudent(dataIndex, "gradable","");

            new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Assignment_appium().startAssignmentbyStudent(dataIndex, assessmentInfoList.get(0));

            new UIElement().waitAndFindElement(assignments.button_about);
            Assert.assertEquals(assignments.button_about.getText().trim(), "About", "About tab should be displayed on right side ");
            System.out.println("button Available : " + assignments.button_about.isDisplayed());


            String pointsAvailableText = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Points']")).getText()+appiumDriver.findElement(By.xpath("//UIAStaticText[@name = ' Available : 1']")).getText();
            System.out.println("pointsAvailableText : " + pointsAvailableText);
            Assert.assertEquals(pointsAvailableText,"Points Available : 1","Points Avaialbe:<x>, x is the score of the question");

            Assert.assertEquals(assignments.label_markForReview.getText(), "Mark for Review", "Mark for review label is not displayed");
            Assert.assertEquals(assignments.label_confidence.getText(), "Confidence", "Confidence label is not displayed");


            try{
                assignments.icon_reportContentError.click();
            }catch(Exception e){
                new Tap_appium().tapReportContentErrorIcon();
                Thread.sleep(2000);
            }

            new UIElement().waitAndFindElement(assignments.textArea_enterContentIssue);
            Thread.sleep(5000);
            assignments.textArea_enterContentIssue.click();
            assignments.textArea_enterContentIssue.sendKeys(contentIssue);
            assignments.button_send.click();
            wait.until(ExpectedConditions.visibilityOf(assignments.label_ClickYesToReportThisIssue));
            assignments.label_ClickYesToReportThisIssue.click(); //click on yes



            //Row no - 13 : 4. Select the response and click on Next Question button
            /*Expected  - Student should be able to submit the response and should get navigated to next question
            "Bottom footer should display ""Previous question"" and ""Next Question"" buttons*/

            eTextbook.label_answerChoiceA.click();
            assignments.button_nextQuestion.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[@name = 'Q 2:']")));

            Assert.assertEquals(assignments.button_previousQuestion.isDisplayed(), true, "Bottom footer should display \"\"Previous question\"\" and \"\"Next Question\"\" buttons");
            Assert.assertEquals(assignments.button_nextQuestion.isDisplayed(), true, "Bottom footer should display \"\"Previous question\"\" and \"\"Next Question\"\" buttons");




            //15 - 5.Navigate to last question
            /*Expected  - "Finish Assignment" button should appear*/

            eTextbook.label_answerChoiceA.click();
            assignments.button_nextQuestion.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[@name = 'Q 3:']")));

            //16 -  6. Click on Finish Assignment button

            assignments.button_finishAssignment.click();

            wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Performance']"))));
            String performanceSummaryText = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Performance']")).getText().trim();
            String summary =appiumDriver.findElement(By.xpath("//UIAStaticText[contains(@name,'Summary')]")).getText().trim();

            Assert.assertEquals(performanceSummaryText + " " + summary, "Performance Summary  ", "Student should be able to finish the assignment and should get navigated to Performance summary page of the assignment");

            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '3']")).getText(), "3", "Pie chart : showing the number of questions attempted");
            //Tc row no 19
            Assert.assertEquals(assignments.button_continue.isDisplayed(), true, "\"Continue\" button is not  displaying on the footer");

            //Tc row no 18




            new Tap_appium().tapOnQuestionCard(); //tap on 1ST question carD
            Thread.sleep(4000);
            new UIElement().waitAndFindElement(eTextbook.tab_discussion);
            eTextbook.tab_discussion.click();// click on the discussion tab

           assignments.newDiscussion_link.click(); //click on the new discussion link
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(assignments.newDiscussion_textArea);
             assignments.newDiscussion_textArea.sendKeys("This is new post"); //bug id =16673;
            Thread.sleep(3000);
            assignments.newDiscussion_submitButton.click(); //click on the submit link

            eTextbook.tab_bookMark.click(); //click on the bookmark tab

            assignments.addNote_link.click(); //click on the add
            new UIElement().waitAndFindElement(assignments.addNote_textArea);
            assignments.addNote_textArea.sendKeys("New Note");
            assignments.submitNote.click(); //click on the submit link
            Assert.assertTrue(assignments.postedNote.getText().contains("Posted New Note"), "Note has not posted by student");

            new Navigator_appium().navigateTo("Assignments"); //navigate to assignment
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[@name='Submitted'][1]")));
            Assert.assertEquals(assignments.submitted_status.getText().trim(), "Submitted", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.submitted_statusValue.getText().trim(), "1", "\"Submitted count in not increment to 1 in the filter box");



            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), Config.cmsPassword, dataIndex); //login as instructor
            new Assignment().switchToAssignmentResponsePage(dataIndex);
            new Assignment().provideGradeToStudent("0", "0.6");
            new Assignment().releaseGradeForAll();

            new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex); //login as student
            new Navigator_appium().navigateTo("Assignments"); //navigate to assignment

            Assert.assertEquals(assignments.assignemnt_status.get(5).getText().trim(), "1", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.assignemnt_status.get(6).getText().trim(), "Graded", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.assignemnt_status.get(8).getText().trim(), "Reviewed", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.assignemnt_status.get(13).getText().trim(), "67%", "Overall Score is not updated in the top header");
            Assert.assertEquals(assignments.assignemnt_status.get(25).getText().trim(), "Score", "Score Label is not displayed correctly");
            Assert.assertEquals(assignments.assignemnt_status.get(26).getText().trim(), "(2/3)", "Score is not be displayed correctly");

            new Assignment_appium().startAssignmentbyStudent(dataIndex, assessmentInfoList.get(0)); //click on the assignment name
            appiumDriver.findElement(By.xpath("//UIAStaticText[@name='Q3']")).click();



        }catch(Exception e){
            Assert.fail("Exception in the testscript 'gradableAssignment' in the class 'AssignmentFlow.java'",e);
        }

    }



    @Test(priority = 1, enabled = true)
    public void gradableAssignment1 (){
        try{

            String dataIndex = "9";
            //new Assignment().addQuestions(dataIndex, "truefalse", "");
            Driver.driver.get(Config.baseURL);
            new UIElement().waitAndFindElement(By.id("username"));
            Driver.driver.findElement(By.id("username")).sendKeys(Config.cmsAuthorName);
            Driver.driver.findElement(By.id("password")).sendKeys(Config.cmsPassword);
            Driver.driver.findElement(By.id("loginSubmitBtn")).click();
            Driver.driver.findElement(By.cssSelector("img[alt=\"" + Config.course + "\"]")).click();


            Driver.driver.findElement(By.cssSelector("div[title = 'Ch 1: The Study of Life']")).click();
            Driver.driver.findElement(By.className("create-practice")).click();
            Driver.driver.findElement(By.className("create-regular-assessment-popup-item")).click();
            Driver.driver.findElement(By.linkText("Adaptive Component Diagnostic")).click();
            Driver.driver.findElement(By.linkText("Static Practice")).click();

            Driver.driver.findElement(By.id("assessmentName")).click();
            Driver.driver.findElement(By.id("assessmentName")).clear();
            Driver.driver.findElement(By.id("assessmentName")).sendKeys("assessmentame");
            Driver.driver.findElement(By.id("questionSetName")).clear();
            Driver.driver.findElement(By.id("questionSetName")).sendKeys("questionset");
            Driver.driver.findElement(By.id("qtn-type-true-false-img")).click();

            Driver.driver.findElement(By.id("question-raw-content")).click();

            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("My True False Question");

            Driver.driver.findElement(By.className("true-false-answer-select")).click();
            Driver.driver.findElement(By.linkText("Draft")).click();
            Driver.driver.findElement(By.linkText("Publish")).click();
            Driver.driver.findElement(By.id("saveQuestionDetails1")).click();

            System.out.println("driver.getWindowHandle():1 " + driver.getWindowHandle());

            Driver.driver.findElement(By.id("preview-the-image-quiz")).click();

            System.out.println("driver.getWindowHandle(): 2" + driver.getWindowHandle());


           /* for(String winHandle: driver.getWindowHandles()){
                System.out.println("Special : " + winHandle.toString());
                Driver.driver.switchTo().window(winHandle);

            }

            */

            Set<String> winHandle = driver.getWindowHandles();
            System.out.println("winHandle size :" + winHandle.size());
            Iterator iterator = winHandle.iterator();
            int count = 0;
            while(iterator.hasNext()){
               /*System.out.println("iterator.next() : " + iterator.next().toString());
                String win = iterator.next().toString();
                count++;
                System.out.println(count);
                if(count==2){
                    Driver.driver.switchTo().window(win);
                    break;
                }*/
                System.out.println("Count : " + count);
                if(count==1) {
                    Driver.driver.switchTo().window(iterator.next().toString());
                }
                count++;

            }


            Thread.sleep(3000);

            Driver.driver.findElement(By.className("true-false-student-answer-label")).click();



        }catch(Exception e){
            Assert.fail("Exception in the testscript 'gradableAssignment' in the class 'AssignmentFlow.java'",e);
        }

    }

    @Test(priority = 2, enabled = true)
    public void gradableAssignmentWithPolicy1 (){
        try{

            WebDriverWait  wait = new WebDriverWait(appiumDriver,180);
            String dataIndex = "33";
            String appendChar=generateAppendChar();

            //TC row no 33

          /*  ArrayList<String> assessmentInfoList = new ArrayList<>();
            ArrayList<String> instructorInfoList = new ArrayList<>();
            ArrayList<String> studentInfoList = new ArrayList<>();

            assessmentInfoList.add("33_Assessment_gradableAssignmentWithPolicy1_19010a");
            instructorInfoList.add("Ins_gradableAssignmentWithPolicy1_33_19010a");
            studentInfoList.add("stu_gradableAssignmentWithPolicy1_33_19010a");*/




            String contentIssue = "Content issue"+appendChar;
            String policyName="GradeReleaseOptionOne"+appendChar;
            ArrayList<String> instructorInfoList = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            ArrayList<String> assessmentInfoList = new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");

            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), Config.cmsPassword, dataIndex);
            new Navigator().NavigateTo("Policies");
            new AssignmentPolicy().createAssignmentPolicy(policyName, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//policy 1
            new Assignment().assignToStudent(dataIndex, "gradable", policyName);

            new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Assignment_appium().startAssignmentbyStudent(dataIndex, assessmentInfoList.get(0));
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOf(assignments.button_about));
            Assert.assertEquals(assignments.button_about.getText().trim(), "About", "About tab should be displayed on right side ");
            System.out.println("button Available : " + assignments.button_about.isDisplayed());


            String pointsAvailableText = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Points']")).getText()+appiumDriver.findElement(By.xpath("//UIAStaticText[@name = ' Available : 2']")).getText();
            System.out.println("pointsAvailableText : " + pointsAvailableText);
            Assert.assertEquals(pointsAvailableText, "Points Available : 2", "Points Avaialbe:<x>, x is the score of the question");

            Assert.assertEquals(assignments.label_markForReview.getText(), "Mark for Review", "Mark for review label is not displayed");
            Assert.assertEquals(assignments.label_confidence.getText(), "Confidence", "Confidence label is not displayed");


            try{
                assignments.icon_reportContentError.click();
            }catch(Exception e){
                new Tap_appium().tapReportContentErrorIcon();
                Thread.sleep(2000);
            }

            new UIElement().waitAndFindElement(assignments.textArea_enterContentIssue);
            Thread.sleep(5000);
            assignments.textArea_enterContentIssue.click();
            assignments.textArea_enterContentIssue.sendKeys(contentIssue);
            assignments.button_send.click();
            wait.until(ExpectedConditions.visibilityOf(assignments.label_ClickYesToReportThisIssue));
            assignments.label_ClickYesToReportThisIssue.click(); //click on yes


             eTextbook.label_answerChoiceA.click(); //click on the Answer choice A
            assignments.submitAnswer_link.click(); //click on the submit answer
            assignments.button_nextQuestion.click(); //click on the next button

             wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[@name = 'Q 2:']")));



            //15 - 5.Navigate to last question
            /*Expected  - "Finish Assignment" button should appear*/

            eTextbook.label_answerChoiceA.click();  //click on the Answer choice A
            Assert.assertEquals(assignments.submitAnswer_link.isDisplayed(), true, "Bottom footer is mot displaying Submit Answer button");

            eTextbook.button_submit.click();
            assignments.button_nextQuestion.click(); //click on the next button
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[@name = 'Q 3:']")));

            //16 -  6. Click on Finish Assignment button

            assignments.button_finishAssignment.click(); //click on the finish assignment*/


            wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Performance']"))));
            String performanceSummaryText = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Performance']")).getText().trim();
            String summary =appiumDriver.findElement(By.xpath("//UIAStaticText[contains(@name,'Summary')]")).getText().trim();

            Assert.assertEquals(performanceSummaryText + " " + summary, "Performance Summary  ", "Student should be able to finish the assignment and should get navigated to Performance summary page of the assignment");

            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '3']")).getText(), "3", "Pie chart : showing the number of questions attempted");
            //Tc row no 19
            Assert.assertEquals(assignments.button_continue.isDisplayed(), true, "\"Continue\" button is not  displaying on the footer");

            //Tc row no 18


            new Tap_appium().tapOnQuestionCard(); //tap on 1ST question carD
            Thread.sleep(4000);
            new UIElement().waitAndFindElement(eTextbook.tab_discussion);
            eTextbook.tab_discussion.click();// click on the discussion tab

           /* assignments.newDiscussion_link.click(); //click on the new discussion link
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(assignments.newDiscussion_textArea);
             assignments.newDiscussion_textArea.sendKeys("This is new post"); //bug id =16673;
            Thread.sleep(3000);
            assignments.newDiscussion_submitButton.click(); //click on the submit link
     */
            eTextbook.tab_bookMark.click(); //click on the bookmark tab

            assignments.addNote_link.click(); //click on the add
            new UIElement().waitAndFindElement(assignments.addNote_textArea);
            assignments.addNote_textArea.sendKeys("New Note");
            assignments.submitNote.click(); //click on the submit link
            Assert.assertTrue(assignments.postedNote.getText().contains("Posted New Note"), "Note has not posted by student");


            new Navigator_appium().navigateTo("Assignments"); //navigate to assignment
            Assert.assertEquals(assignments.assignemnt_status.get(5).getText().trim(), "1", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.assignemnt_status.get(6).getText().trim(), "Graded", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.assignemnt_status.get(8).getText().trim(), "Reviewed", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.assignemnt_status.get(13).getText().trim(), "100%", "Overall Score is not updated in the top header");
            Assert.assertEquals(assignments.assignemnt_status.get(27).getText().trim(), "Score", "Score Label is not displayed correctly");
            Assert.assertEquals(assignments.assignemnt_status.get(28).getText().trim(), "(6/6)", "Score is not be displayed correctly");

            new Assignment_appium().startAssignmentbyStudent(dataIndex, assessmentInfoList.get(0)); //click on the assignment name
            appiumDriver.findElement(By.xpath("//UIAStaticText[@name='Q3']")).click();

            new Navigator_appium().navigateTo("Dashboard");
            Assert.assertTrue(assignments.overallScore.getText().contains("Overall Score"), "Overall Score label is not showing in dashboard");
            Assert.assertTrue(appiumDriver.findElement(By.xpath("//*[@name='100']")).getText().contains("100"), "Recently Graded graph is not updated");



        }catch(Exception e){
            Assert.fail("Exception in the TC 'gradableAssignmentWithPolicy1' in the class 'AssignmentFlow'",e);
        }

    }


    @Test(priority = 3, enabled = true)
    public void gradableAssignmentWithPolicy3 (){
        try{

            WebDriverWait  wait = new WebDriverWait(appiumDriver,180);
            String dataIndex = "52";
            String appendChar=generateAppendChar();

            //TC row no 52

          /*  ArrayList<String> instructorInfoList = new ArrayList<>();
            ArrayList<String> studentInfoList = new ArrayList<>();

            instructorInfoList.add("Ins_gradableAssignmentWithPolicy3_52_20010b");
            studentInfoList.add("stu_gradableAssignmentWithPolicy3_52_20010b");
*/

            String contentIssue = "Content issue"+appendChar;
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "52");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "52");
            assessmentname = assessmentname+"_"+appendChar;
            System.out.println("assignmentpolicyname:"+assignmentpolicyname);

            ArrayList<String> instructorInfoList = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "essay", "");

            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), Config.cmsPassword, dataIndex);
            new Navigator().NavigateTo("Policies");
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//policy 3*//**//*
            new Assignment().assignToStudent(dataIndex);

            new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Assignment_appium().startAssignmentbyStudent(dataIndex,assessmentname);
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOf(assignments.button_about));
            Assert.assertEquals(assignments.button_about.getText().trim(), "About", "About tab should be displayed on right side ");
            System.out.println("button Available : " + assignments.button_about.isDisplayed());


            String pointsAvailableText = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Points']")).getText()+appiumDriver.findElement(By.xpath("//UIAStaticText[@name = ' Available : 2']")).getText();
            System.out.println("pointsAvailableText : " + pointsAvailableText);
            Assert.assertEquals(pointsAvailableText, "Points Available : 2", "Points Avaialbe:<x>, x is the score of the question");

            Assert.assertEquals(assignments.label_markForReview.getText(), "Mark for Review", "Mark for review label is not displayed");
            Assert.assertEquals(assignments.label_confidence.getText(), "Confidence", "Confidence label is not displayed");


            try{
                assignments.icon_reportContentError.click();
            }catch(Exception e){
                new Tap_appium().tapReportContentErrorIcon();
                Thread.sleep(2000);
            }

            new UIElement().waitAndFindElement(assignments.textArea_enterContentIssue);
            Thread.sleep(5000);
            assignments.textArea_enterContentIssue.click();
            assignments.textArea_enterContentIssue.sendKeys(contentIssue);
            assignments.button_send.click();
            wait.until(ExpectedConditions.visibilityOf(assignments.label_ClickYesToReportThisIssue));
            assignments.label_ClickYesToReportThisIssue.click(); //click on yes


            eTextbook.label_answerChoiceA.click(); //click on the Answer choice A
            Assert.assertEquals(assignments.submitAnswer_link.isDisplayed(), true, "Bottom footer is mot displaying Submit Answer button");
            assignments.submitAnswer_link.click(); //click on the submit answer
            assignments.button_nextQuestion.click(); //click on the next button
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[@name = 'Q 2:']")));

            //15 - 5.Navigate to last question
            /*Expected  - "Finish Assignment" button should appear*/

            assignments.button_finishAssignment.click(); //click on the finish assignment


            wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Performance']"))));
            String performanceSummaryText = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Performance']")).getText().trim();
            String summary =appiumDriver.findElement(By.xpath("//UIAStaticText[contains(@name,'Summary')]")).getText().trim();

            Assert.assertEquals(performanceSummaryText + " " + summary, "Performance Summary  ", "Student should be able to finish the assignment and should get navigated to Performance summary page of the assignment");

            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '2']")).getText(), "2", "Pie chart : showing the number of questions attempted");
            //Tc row no 19
            Assert.assertEquals(assignments.button_continue.isDisplayed(), true, "\"Continue\" button is not  displaying on the footer");

            //Tc row no 18


            new Tap_appium().tapOnQuestionCard(); //tap on 1ST question carD
            Thread.sleep(4000);
            new UIElement().waitAndFindElement(eTextbook.tab_discussion);
            eTextbook.tab_discussion.click();// click on the discussion tab

            assignments.newDiscussion_link.click(); //click on the new discussion link
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(assignments.newDiscussion_textArea);
             assignments.newDiscussion_textArea.sendKeys("This is new post"); //bug id =16673;
            Thread.sleep(3000);
            assignments.newDiscussion_submitButton.click(); //click on the submit link

            eTextbook.tab_bookMark.click(); //click on the bookmark tab

            assignments.addNote_link.click(); //click on the add
            new UIElement().waitAndFindElement(assignments.addNote_textArea);
            assignments.addNote_textArea.sendKeys("New Note");
            assignments.submitNote.click(); //click on the submit link
            Assert.assertTrue(assignments.postedNote.getText().contains("Posted New Note"), "Note has not posted by student");

            new Navigator_appium().navigateTo("Assignments"); //navigate to assignment
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[@name='Submitted'][1]")));
            Assert.assertEquals(assignments.submitted_status.getText().trim(), "Submitted", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.submitted_statusValue.getText().trim(), "1", "\"Submitted count in not increment to 1 in the filter box");



            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), Config.cmsPassword, dataIndex); //login as instructor
            new Assignment().provideGRadeToStudent("52");


            new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Navigator_appium().navigateTo("Assignments"); //navigate to assignment
            Assert.assertEquals(assignments.assignemnt_status.get(5).getText().trim(), "1", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.assignemnt_status.get(6).getText().trim(), "Graded", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.assignemnt_status.get(8).getText().trim(), "Reviewed", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.assignemnt_status.get(13).getText().trim(), "30%", "Overall Score is not updated in the top header");
            Assert.assertEquals(assignments.assignemnt_status.get(30).getText().trim(), "Score", "Score Label is not displayed correctly");
            Assert.assertEquals(assignments.assignemnt_status.get(31).getText().trim(), "(1.2/4)", "Score is not be displayed correctly");

            new Assignment_appium().startAssignmentbyStudent(dataIndex, assessmentname); //click on the assignment name
            appiumDriver.findElement(By.xpath("//UIAStaticText[@name='Q2']")).click();


        }catch(Exception e){
            Assert.fail("Exception in the TC 'gradableAssignmentWithPolicy1' in the class 'AssignmentFlow'",e);
        }

    }
    @Test(priority = 4, enabled = true)
    public void gradableAssignmentWithPolicy4 (){
        try{

            WebDriverWait  wait = new WebDriverWait(appiumDriver,180);
            String dataIndex = "90";
            String appendChar=generateAppendChar();

            //TC row no 90

          /*  ArrayList<String> instructorInfoList = new ArrayList<>();
            ArrayList<String> studentInfoList = new ArrayList<>();

            instructorInfoList.add("Ins_gradableAssignmentWithPolicy4_90_21010b");
            studentInfoList.add("stu_gradableAssignmentWithPolicy4_90_21010b");*/


            String contentIssue = "Content issue"+appendChar;
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "90");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "90");
            assessmentname = assessmentname+"_"+appendChar;
            System.out.println("assignmentpolicyname:"+assignmentpolicyname);

            ArrayList<String> instructorInfoList = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");


            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), Config.cmsPassword, dataIndex);
            new Navigator().NavigateTo("Policies");

            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description90", "2", null, false, "1", "", "Release explicitly by the instructor", "", "", "");//Policy 4
            new Assignment().assignToStudent(dataIndex);

            new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
           new Assignment_appium().startAssignmentbyStudent(dataIndex, assessmentname);
           Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOf(assignments.button_about));
            Assert.assertEquals(assignments.button_about.getText().trim(), "About", "About tab should be displayed on right side ");
            System.out.println("button Available : " + assignments.button_about.isDisplayed());


            String pointsAvailableText = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Points']")).getText()+appiumDriver.findElement(By.xpath("//UIAStaticText[@name = ' Available : 2']")).getText();
            System.out.println("pointsAvailableText : " + pointsAvailableText);
            Assert.assertEquals(pointsAvailableText, "Points Available : 2", "Points Avaialbe:<x>, x is the score of the question");

            Assert.assertEquals(assignments.label_markForReview.getText(), "Mark for Review", "Mark for review label is not displayed");
            Assert.assertEquals(assignments.label_confidence.getText(), "Confidence", "Confidence label is not displayed");


            try{
                assignments.icon_reportContentError.click();
            }catch(Exception e){
                new Tap_appium().tapReportContentErrorIcon();
                Thread.sleep(2000);
            }

            new UIElement().waitAndFindElement(assignments.textArea_enterContentIssue);
            Thread.sleep(5000);
            assignments.textArea_enterContentIssue.click();
            assignments.textArea_enterContentIssue.sendKeys(contentIssue);
            assignments.button_send.click();
            wait.until(ExpectedConditions.visibilityOf(assignments.label_ClickYesToReportThisIssue));
            assignments.label_ClickYesToReportThisIssue.click(); //click on yes


            eTextbook.label_answerChoiceA.click(); //click on the Answer choice A
            assignments.submitAnswer_link.click(); //click on the submit answer
            assignments.button_nextQuestion.click(); //click on the next button

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[@name = 'Q 2:']")));



            //15 - 5.Navigate to last question
            /*Expected  - "Finish Assignment" button should appear*/

            eTextbook.label_answerChoiceA.click();  //click on the Answer choice A
            Assert.assertEquals(assignments.submitAnswer_link.isDisplayed(), true, "Bottom footer is mot displaying Submit Answer button");

            eTextbook.button_submit.click();
            assignments.button_nextQuestion.click(); //click on the next button
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[@name = 'Q 3:']")));

            //16 -  6. Click on Finish Assignment button

            assignments.button_finishAssignment.click(); //click on the finish assignment


            wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Performance']"))));
            String performanceSummaryText = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Performance']")).getText().trim();
            String summary =appiumDriver.findElement(By.xpath("//UIAStaticText[contains(@name,'Summary')]")).getText().trim();

            Assert.assertEquals(performanceSummaryText + " " + summary, "Performance Summary  ", "Student should be able to finish the assignment and should get navigated to Performance summary page of the assignment");

            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '3']")).getText(), "3", "Pie chart : showing the number of questions attempted");
            //Tc row no 19
            Assert.assertEquals(assignments.button_continue.isDisplayed(), true, "\"Continue\" button is not  displaying on the footer");

            //Tc row no 18


            new Tap_appium().tapOnQuestionCard(); //tap on 1ST question carD
            Thread.sleep(4000);
            new UIElement().waitAndFindElement(eTextbook.tab_discussion);
            eTextbook.tab_discussion.click();// click on the discussion tab

          /*  assignments.newDiscussion_link.click(); //click on the new discussion link
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(assignments.newDiscussion_textArea);
             assignments.newDiscussion_textArea.sendKeys("This is new post"); //bug id =16673;
            Thread.sleep(3000);
            assignments.newDiscussion_submitButton.click(); //click on the submit link*/

            eTextbook.tab_bookMark.click(); //click on the bookmark tab

            assignments.addNote_link.click(); //click on the add
            new UIElement().waitAndFindElement(assignments.addNote_textArea);
            assignments.addNote_textArea.sendKeys("New Note");
            assignments.submitNote.click(); //click on the submit link
            Assert.assertTrue(assignments.postedNote.getText().contains("Posted New Note"), "Note has not posted by student");


            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), Config.cmsPassword, dataIndex); //login as instructor
            new Assignment().switchToAssignmentResponsePage(dataIndex);
            new Assignment().releaseGradeForAll();

            new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Navigator_appium().navigateTo("Assignments"); //navigate to assignment
            Assert.assertEquals(assignments.assignemnt_status.get(5).getText().trim(), "1", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.assignemnt_status.get(6).getText().trim(), "Graded", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.assignemnt_status.get(8).getText().trim(), "Reviewed", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.assignemnt_status.get(13).getText().trim(), "0%", "Overall Score is not updated in the top header");
            Assert.assertEquals(assignments.assignemnt_status.get(27).getText().trim(), "Score", "Score Label is not displayed correctly");
            Assert.assertEquals(assignments.assignemnt_status.get(28).getText().trim(), "(0/6)", "Score is not be displayed correctly");

            new Assignment_appium().startAssignmentbyStudent(dataIndex, assessmentname); //click on the assignment name
            appiumDriver.findElement(By.xpath("//UIAStaticText[@name='Q3']")).click();

            new Navigator_appium().navigateTo("Dashboard");
            Assert.assertTrue(assignments.overallScore.getText().contains("Overall Score"), "Overall Score label is not showing in dashboard");
            Assert.assertTrue(appiumDriver.findElement(By.xpath("//*[@name='0']")).getText().contains("0"), "Recently Graded graph is not updated");


        }catch(Exception e){
            Assert.fail("Exception in the TC 'gradableAssignmentWithPolicy4' in the class 'AssignmentFlow'",e);
        }

    }

    @Test(priority = 5, enabled = true)
    public void gradableAssignmentEnabledFeedbackPolicy1(){
        try{

            WebDriverWait  wait = new WebDriverWait(appiumDriver,180);
            String dataIndex = "110";
            String appendChar=generateAppendChar();

            //TC row no 110

           /* ArrayList<String> instructorInfoList = new ArrayList<>();
            ArrayList<String> studentInfoList = new ArrayList<>();

            instructorInfoList.add("Ins_aa_110_21010c");
            studentInfoList.add("stu_aa_110_21010c");*/



            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "110");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "110");
            assessmentname = assessmentname+"_"+appendChar;
            System.out.println("assignmentpolicyname:" + assignmentpolicyname);
            System.out.println("assessmentname:" + assessmentname);


            ArrayList<String> instructorInfoList = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");


            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), Config.cmsPassword, dataIndex);
            new Navigator().NavigateTo("Policies");
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "2", "", "Auto-release on assignment submission", "", "", "");//policy 1
            new Assignment().assignToStudent(dataIndex);

            new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Assignment_appium().startAssignmentbyStudent(dataIndex, assessmentname);
            eTextbook.label_answerChoiceA.click(); //click on the Answer choice A
            assignments.submitAnswer_link.click(); //click on the submit answer
            Thread.sleep(4000);
            Assert.assertTrue(assignments.goToNextQuestionLink.isDisplayed(), "Go to next question' option is not  displayed on the robo pop up.");
            Assert.assertFalse(new BooleanValue().presenceOfElement(110, By.xpath("//UIAStaticText[contains(@name,'You got it correct')]")));

            assignments.goToNextQuestionLink.click(); //click on the nextQuestion
            eTextbook.label_answerChoiceB.click(); //click on the Answer choice B
            assignments.submitAnswer_link.click(); //click on the submit answer

            Assert.assertTrue(assignments.goToNextQuestionLink.isDisplayed(), "Go to next question' option is not  displayed on the robo pop up.");
            Assert.assertTrue(assignments.Reattempt_roboLink.isDisplayed(), "Reattempt' option is not  displayed on the robo pop up.");
            Assert.assertFalse(new BooleanValue().presenceOfElement(110, By.xpath("//UIAStaticText[contains(@name,'You got it incorrect')]")));

            assignments.goToNextQuestionLink.click(); //click on the nextQuestion*/
            wait.until(ExpectedConditions.visibilityOf(assignments.finishAssignment_button));
            assignments.finishAssignment_button.click(); //click on the submit answer
            Thread.sleep(5000);
            Assert.assertTrue(assignments.finishAssignment_roboLink.isDisplayed(), "FinishAssignment' option is not  displayed on the robo pop up.");




        }catch(Exception e){
            Assert.fail("Exception in the TC 'gradableAssignmentEnabledFeedbackPolicy1' in the class 'AssignmentFlow'",e);
        }

    }

    @Test(priority = 6, enabled = true)
    public void gradableAssignmentEnabledFeedbackPolicy1ForManuallyGradedQuestion(){
        try{

            //gradableAssignmentEnabledFeedbackPolicy1ForManuallyGradedQuestion
            WebDriverWait  wait = new WebDriverWait(appiumDriver,180);
            String dataIndex = "122";
            String appendChar=generateAppendChar();

            //TC row no 122

           /* ArrayList<String> instructorInfoList = new ArrayList<>();
            ArrayList<String> studentInfoList = new ArrayList<>();

            instructorInfoList.add("Ins_b_122_22010c");
            studentInfoList.add("stu_b_122_22010c");*/



            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "122");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "122");
            assessmentname = assessmentname+"_"+appendChar;
            System.out.println("assignmentpolicyname:" + assignmentpolicyname);
            System.out.println("assessmentname:" + assessmentname);


            ArrayList<String> instructorInfoList = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "textentry", "");
            new Assignment().addQuestions(dataIndex, "textdropdown", "");
            new Assignment().addQuestions(dataIndex, "advancednumeric", "");
            new Assignment().addQuestions(dataIndex, "essay", "");


            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), Config.cmsPassword, dataIndex);
            new Navigator().NavigateTo("Policies");
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "2", "1", "Auto-release on assignment submission", "", "", "");//policy 1*//*
            new Assignment().assignToStudent(dataIndex);

            new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Assignment_appium().startAssignmentbyStudent(dataIndex, assessmentname);
            wait.until(ExpectedConditions.visibilityOf(eTextbook.label_answerChoiceA));
            eTextbook.label_answerChoiceA.click(); //click on the Answer choice A
            assignments.submitAnswer_link.click(); //click on the submit answer
            Thread.sleep(4000);
            Assert.assertTrue(assignments.goToNextQuestionLink.isDisplayed(), "Go to next question' option is not  displayed on the robo pop up.");
            Assert.assertTrue(assignments.gotItCorrect.get(1).isDisplayed(), "You got it correct' message is not  be displayed on robo pop up");

            assignments.goToNextQuestionLink.click(); //click on the nextQuestion
            assignments.textEntry_textbox.get(1).sendKeys("Incorrect");
            assignments.submitAnswer_link.click(); //click on the submit answer
            Thread.sleep(3000);
            Assert.assertTrue(assignments.goToNextQuestionLink.isDisplayed(), "Go to next question' option is not  displayed on the robo pop up.");
            Assert.assertTrue(assignments.gotItInCorrect.get(1).isDisplayed(), "You got it incorrect' message should be displayed on robo pop up");
            Assert.assertTrue(assignments.Reattempt_roboLink.isDisplayed(), "Reattempt' option is not  displayed on the robo pop up.");

            assignments.Reattempt_roboLink.click(); //click on the reAttempt
            Thread.sleep(2000);
            assignments.submitAnswer_link.click(); //click on the submit answer
            Assert.assertTrue(assignments.gotItInCorrect.get(1).isDisplayed(), "You got it incorrect' message should be displayed on robo pop up");
            Assert.assertTrue(assignments.goToNextQuestionLink.isDisplayed(), "Go to next question' option is not  displayed on the robo pop up.");

            assignments.goToNextQuestionLink.click(); //click on the nextQuestion
            wait.until(ExpectedConditions.visibilityOf(assignments.select_textDropdown));
             assignments.select_textDropdown.click(); //click on the select
            List<WebElement> options=appiumDriver.findElements(By.className("UIATableCell"));
            for(WebElement element:options){
                System.out.println(element.getAttribute("name"));
            }
            options.get(2).click(); //click on the Answer2
            assignments.submitAnswer_link.click(); //click on the submit answer

            Assert.assertTrue(assignments.goToNextQuestionLink.isDisplayed(), "Go to next question' option is not  displayed on the robo pop up.");
            Assert.assertTrue(assignments.gotItInCorrect.get(1).isDisplayed(), "You got it incorrect' message should be displayed on robo pop up");
            Assert.assertTrue(assignments.Reattempt_roboLink.isDisplayed(), "Reattempt' option is not  displayed on the robo pop up.");
            assignments.goToNextQuestionLink.click(); //click on the nextQuestion
            Thread.sleep(5000);
            appiumDriver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]").sendKeys("Numeric");
            assignments.submitAnswer_link.click(); //click on the submit answer
            Thread.sleep(4000);
            Assert.assertTrue(assignments.goToNextQuestionLink.isDisplayed(), "Go to next question' option is not  displayed on the robo pop up.");
            Assert.assertTrue(assignments.gotItInCorrect.get(1).isDisplayed(), "You got it incorrect' message should be displayed on robo pop up");
            Assert.assertTrue(assignments.Reattempt_roboLink.isDisplayed(), "Reattempt' option is not  displayed on the robo pop up.");

            assignments.goToNextQuestionLink.click(); //click on the nextQuestion*/

            String pageSource=appiumDriver.getPageSource();
            System.out.println("pageSource:" + pageSource);
           //appiumDriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]")).sendKeys("essay");
            wait.until(ExpectedConditions.visibilityOf(assignments.finishAssignment_button));
            assignments.finishAssignment_button.click(); //click on the submit answer
            Thread.sleep(5000);
            Assert.assertTrue(assignments.finishAssignment_roboLink.isDisplayed(), "FinishAssignment' option is not  displayed on the robo pop up.");
            Assert.assertTrue(assignments.ins_feedback.get(1).isDisplayed(), "Your instructor should provide feedback' message is not displayed");



        }catch(Exception e){
            Assert.fail("Exception in the TC 'gradableAssignmentEnabledFeedbackPolicy1ForManuallyGradedQuestion' in the class 'AssignmentFlow'",e);
        }

    }

    @Test(priority = 7, enabled = true)
    public void nonGradableAssignmnet(){
        try{

            //gradableAssignmentEnabledFeedbackPolicy1ForManuallyGradedQuestion
            WebDriverWait  wait = new WebDriverWait(appiumDriver,180);
            String dataIndex = "145";
            String appendChar=generateAppendChar();

            //TC row no 145

          /*  ArrayList<String> instructorInfoList = new ArrayList<>();
            ArrayList<String> studentInfoList = new ArrayList<>();

            instructorInfoList.add("Ins_c_145_26010c");
            studentInfoList.add("stu_c_145_26010c");*/

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "145");
            assessmentname = assessmentname+"_"+appendChar;
            System.out.println("assessmentname:" + assessmentname);

            String contentIssue = "This is a content Issue";
            ArrayList<String> instructorInfoList = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");

            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), Config.cmsPassword, dataIndex);
            new Assignment().assignToStudent(dataIndex);


            new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Assignment_appium().startAssignmentbyStudent(dataIndex, assessmentname);


             new UIElement().waitAndFindElement(assignments.button_about);
            Assert.assertEquals(assignments.button_about.getText().trim(), "About", "About tab should be displayed on right side ");
            System.out.println("button Available : " + assignments.button_about.isDisplayed());


            String pointsAvailableText = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Points']")).getText()+appiumDriver.findElement(By.xpath("//UIAStaticText[@name = ' Available : 1']")).getText();
            System.out.println("pointsAvailableText : " + pointsAvailableText);
            Assert.assertEquals(pointsAvailableText,"Points Available : 1","Points Avaialbe:<x>, x is the score of the question");

            Assert.assertEquals(assignments.label_markForReview.getText(), "Mark for Review", "Mark for review label is not displayed");
            Assert.assertEquals(assignments.label_confidence.getText(), "Confidence", "Confidence label is not displayed");


            try{
                assignments.icon_reportContentError.click();
            }catch(Exception e){
                new Tap_appium().tapReportContentErrorIcon();
                Thread.sleep(2000);
            }

            new UIElement().waitAndFindElement(assignments.textArea_enterContentIssue);
            Thread.sleep(5000);
            assignments.textArea_enterContentIssue.click();
            assignments.textArea_enterContentIssue.sendKeys(contentIssue);
            assignments.button_send.click();
            wait.until(ExpectedConditions.visibilityOf(assignments.label_ClickYesToReportThisIssue));
            assignments.label_ClickYesToReportThisIssue.click(); //click on yes



            //Row no - 13 : 4. Select the response and click on Next Question button

            /*Expected  - Student should be able to submit the response and should get navigated to next question
            "Bottom footer should display ""Previous question"" and ""Next Question"" buttons*/



            eTextbook.label_answerChoiceA.click();
            assignments.button_nextQuestion.click();


            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[@name = 'Q 2:']")));

            Assert.assertEquals(assignments.button_previousQuestion.isDisplayed(), true, "Bottom footer should display \"\"Previous question\"\" and \"\"Next Question\"\" buttons");
            Assert.assertEquals(assignments.button_nextQuestion.isDisplayed(), true, "Bottom footer should display \"\"Previous question\"\" and \"\"Next Question\"\" buttons");




            //15 - 5.Navigate to last question
            /*Expected  - "Finish Assignment" button should appear*/


            eTextbook.label_answerChoiceA.click();
            assignments.button_nextQuestion.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[@name = 'Q 3:']")));

            //16 -  6. Click on Finish Assignment button

            assignments.button_finishAssignment.click();

            wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Performance']"))));
            String performanceSummaryText = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Performance']")).getText().trim();
            String summary =appiumDriver.findElement(By.xpath("//UIAStaticText[contains(@name,'Summary')]")).getText().trim();

            Assert.assertEquals(performanceSummaryText + " " + summary, "Performance Summary  ", "Student should be able to finish the assignment and should get navigated to Performance summary page of the assignment");

            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '3']")).getText(), "3", "Pie chart : showing the number of questions attempted");
            //Tc row no 19
            Assert.assertEquals(assignments.button_continue.isDisplayed(), true, "\"Continue\" button is not  displaying on the footer");

            //Tc row no 18




            new Tap_appium().tapOnQuestionCard(); //tap on 1ST question carD
            Thread.sleep(4000);
            new UIElement().waitAndFindElement(eTextbook.tab_discussion);
            eTextbook.tab_discussion.click();// click on the discussion tab


           /*  assignments.newDiscussion_link.click(); //click on the new discussion link
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(assignments.newDiscussion_textArea);
             assignments.newDiscussion_textArea.sendKeys("This is new post"); //bug id =16673;
            Thread.sleep(3000);
            assignments.newDiscussion_submitButton.click(); //click on the submit link*/


            eTextbook.tab_bookMark.click(); //click on the bookmark tab

            assignments.addNote_link.click(); //click on the add
            new UIElement().waitAndFindElement(assignments.addNote_textArea);
            assignments.addNote_textArea.sendKeys("New Note");
            assignments.submitNote.click(); //click on the submit link
            Assert.assertTrue(assignments.postedNote.getText().contains("Posted New Note"), "Note has not posted by student");

            new Navigator_appium().navigateTo("Assignments"); //navigate to assignment
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[@name='Submitted'][1]")));
            Assert.assertEquals(assignments.submitted_status.getText().trim(), "Submitted", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.submitted_statusValue.getText().trim(), "1", "\"Submitted count in not increment to 1 in the filter box");



            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), Config.cmsPassword, dataIndex); //login as instructor
            new Assignment().provideFeedback("145");
            new Assignment().releaseGrades("145", "Release Feedback for All");



            new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex); //login as student
            new Navigator_appium().navigateTo("Assignments"); //navigate to assignment

            Assert.assertEquals(assignments.assignemnt_status.get(6).getText().trim(), "Graded", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.assignemnt_status.get(7).getText().trim(), "1", "Status of the assignment is not \"Submitted\"");
            Assert.assertEquals(assignments.assignemnt_status.get(8).getText().trim(), "Reviewed", "Status of the assignment is not \"Submitted\"");


            new Assignment_appium().startAssignmentbyStudent(dataIndex, assessmentname); //click on the assignment name
            appiumDriver.findElement(By.xpath("//UIAStaticText[@name='Q1']")).click(); //click on the first question card

            Assert.assertTrue(assignments.stu_feedback.isDisplayed(), "Instructor Feedback is not displayed below the question");
            Assert.assertTrue(assignments.ins_feedbackLabel.isDisplayed(), "Instructor Feedback Label is not displayed below the question");



        }catch(Exception e){
            Assert.fail("Exception in the TC 'nonGradableAssignmnet' in the class 'AssignmentFlow'",e);
        }

    }

    @Test(priority = 8, enabled = true)
    public void d(){
        try{

            WebDriverWait  wait = new WebDriverWait(appiumDriver,180);
            String dataIndex = "163";
            String appendChar=generateAppendChar();

            //TC row no 163

          /*  ArrayList<String> instructorInfoList = new ArrayList<>();
            ArrayList<String> studentInfoList = new ArrayList<>();

            instructorInfoList.add("Ins_d_163_26010c");
            studentInfoList.add("stu_d_163_26010c");*/

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "163");
            assessmentname = assessmentname+"_"+appendChar;
            System.out.println("assessmentname:" + assessmentname);

            ArrayList<String> instructorInfoList = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);


        }catch(Exception e){
            Assert.fail("Exception in the TC 'nonGradableAssignmnet' in the class 'AssignmentFlow'",e);
        }

    }

    @Test(priority = 8, enabled = true)
    public void myNotePage(){
        try{

            //myNotePage
            WebDriverWait  wait = new WebDriverWait(appiumDriver,180);
            String dataIndex = "178";
            String appendChar=generateAppendChar();

            //TC row no 178

          /*  ArrayList<String> instructorInfoList = new ArrayList<>();
            ArrayList<String> studentInfoList = new ArrayList<>();

            instructorInfoList.add("Ins_d_163_26010c");
            studentInfoList.add("stu_d_163_26010c");*/

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "178");
            assessmentname = assessmentname+"_"+appendChar;
            System.out.println("assessmentname:" + assessmentname);

            ArrayList<String> instructorInfoList = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Navigator_appium().navigateTo("MyNotes");
            boolean found=false;
            List<WebElement> myNoteEntry=appiumDriver.findElements(By.className("UIAStaticText"));
            for(WebElement ele:myNoteEntry){
                if(ele.getAttribute("name").contains("Note")){
                    found=true;
                }
                if(ele.getAttribute("name").contains("+")){
                    found=true;
                }
            }
            Assert.assertTrue(found, "Note and + button is not displaying");

            myNoteEntry.get(5).click(); //click on my note

            Assert.assertTrue(assignments.createNewNote_label.isDisplayed(), "Create new note header with icon is not displayed below the question");
            Assert.assertTrue(assignments.Notes_label.isDisplayed(), "Notes label is not displayed below the question");
            Assert.assertTrue(assignments.associateNoteTo_label.isDisplayed(), "Associate Notes to label is not displayed below the question");
            Assert.assertTrue(assignments.addNote_textbox.isDisplayed(), "Box to add note is not displayed below the question");
            Assert.assertTrue(assignments.upload_link.isDisplayed(), "Upload link is not displayed below the question");
            Assert.assertTrue(assignments.cancel_button.isDisplayed(), "cancel_button is not displayed below the question");
            Assert.assertTrue(assignments.save_button.isDisplayed(), "save_button is not displayed below the question");

            assignments.save_button.click(); //click on the save button
            Assert.assertTrue(assignments.emptyNote_message.isDisplayed(), "The validation message should be “Please provide a note");

            appiumDriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]")).sendKeys("New Note");

            assignments.upload_link.click(); //click on the upload link
            assignments.choose_Exiting.click(); //click on the choose Existing
            assignments.cameraRoll.click();
            assignments.photos.get(1).click(); //click on the image
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[@name='deleteicon']")));
            String pageSource=appiumDriver.getPageSource();
            System.out.println(pageSource);
            Assert.assertTrue(new BooleanValue().presenceOfElement(189, By.xpath("//UIAStaticText[@name='image.jpg']")), "image not uploaded succussfully");
            assignments.deleteIcon.click(); //click on the delete icon
            Assert.assertFalse(new BooleanValue().presenceOfElement(189, By.xpath("//UIAStaticText[@name='image.jpg']")), "After deleting file is still showing ");
            Assert.assertTrue(assignments.upload_link.isDisplayed(), "Upload link is not displayed below the question");

            new Tap_appium().tapOnHelpIcon();
            Thread.sleep(3000);
            String helpMessgae="You should choose the course or a chapter/ section/ sub section level where this note should be associated.";
            Assert.assertEquals(assignments.help_msg.getAttribute("name").trim(), helpMessgae, "After clicking on help icon “You can choose a specific course/chapter/section or subsection where this note should be associated. is not displaying");
            new Tap_appium().tapOnplusIcon();
            Assert.assertEquals(assignments.expanded_course.get(12).getAttribute("name").trim(), "Biology", "Course name is not displaying");
            Assert.assertEquals(assignments.expanded_course.get(13).getAttribute("name").trim(), "Ch 1: The Study of Life", "Chapter name is not displaying");
            Assert.assertEquals(assignments.expanded_course.get(14).getAttribute("name").trim(), "Introduction", "Section name is not displaying");

            new Tap_appium().tapOnplusIcon();
            Assert.assertFalse(new BooleanValue().presenceOfElement(199, By.xpath("//UIAStaticText[@name='Introduction']")), "after clicking - icon Each node is not getting collapsed ");
            new Tap_appium().tapOnplusIcon();
            Thread.sleep(2000);
            Assert.assertEquals(assignments.youHvSelected_label.getAttribute("name").trim(), "You have selected Ch 1: The Study of Life", "“You have selected <Node Name as displayed on the screen>” is not displaying");

            assignments.cancel_button.click(); //click on the cancel button

            Assert.assertFalse(new BooleanValue().presenceOfElement(189, By.xpath("//UIAStaticText[@name='Create new note']")), "Pop up is not getting closed");
            Assert.assertTrue(assignments.noActivityMsg.isDisplayed(), "Note is created");

            assignments.save_button.click(); //click on the save button
            Assert.assertTrue(assignments.emptyNote_message.isDisplayed(), "The validation message should be “Please provide a note");



             myNoteEntry.get(5).click(); //click on my note
            appiumDriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]")).sendKeys("New Note");
            assignments.upload_link.click(); //click on the upload link
            assignments.choose_Exiting.click(); //click on the choose Existing
            assignments.cameraRoll.click();
            assignments.photos.get(1).click(); //click on the image
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[@name='deleteicon']")));

            try {
                assignments.save_button.click(); //click on the save button
            } catch (Exception e) {
                appiumDriver.tap(1, 892, 521, 1);

            }
            String notificationMsg="You have to choose a node to which this note will be associated before you save.";
            Assert.assertEquals(assignments.notificationMsg.getAttribute("name").trim(), notificationMsg, "You have to choose a node to which this note will be associated before you save is not displaying");
            Thread.sleep(3000);
            assignments.expanded_course.get(13).click(); //select first Chapter
            try {
                assignments.save_button.click(); //click on the save button

            } catch (Exception e) {
                appiumDriver.tap(1, 892, 521, 1);

            }


            Assert.assertEquals(assignments.imageLink.getAttribute("name").trim(), "image.jpg", "The uploaded file is not displaying  at the bottom as a link");

            appiumDriver.tap(1, 774, 210, 1); // click on the deleteicon

            Assert.assertEquals(assignments.noteEntry.get(19).getAttribute("name").trim(), "Are you sure you want to delete the note?", "Are you sure you want to delete the note? is not displaying");
            Assert.assertEquals(assignments.noteEntry.get(20).getAttribute("name").trim(), "Yes", "Yes messagae is not displaying");
            Assert.assertEquals(assignments.noteEntry.get(21).getAttribute("name").trim(), "No", "No message is not displaying");

            assignments.noteEntry.get(21).click(); //click on NO option

            Assert.assertEquals(assignments.imageLink.getAttribute("name").trim(), "image.jpg", "The uploaded file is not displaying  at the bottom as a link");

            appiumDriver.tap(1, 774, 210, 1); // click on the deleteicon
            assignments.noteEntry.get(20).click(); //click on Yes option
            Assert.assertTrue(assignments.emptyNote_message.isDisplayed(), "The validation message should be “Please provide a note");


            myNoteEntry.get(5).click(); //click on my note
            appiumDriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]")).sendKeys("New Note");

            assignments.expanded_course.get(13).click(); //select Chapter one
            Thread.sleep(2000);
            assignments.expanded_course.get(14).click(); //select introduction
            try {
                assignments.save_button.click(); //click on the save button

            } catch (Exception e) {
                appiumDriver.tap(1, 892, 521, 1);

            }


            assignments.note_link.click(); //click on my note
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[2]")));
            appiumDriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[2]")).sendKeys("New Note");

            assignments.expanded_course.get(23).click(); //select Chapter one
            try {
                assignments.save_button.click(); //click on the save button

            } catch (Exception e) {
                appiumDriver.tap(1, 892, 521, 1);

            }


            assignments.addTag.get(1).click(); //click on the second addtag
              assignments.addTag_textbox.get(0).sendKeys("custom add tag");
              for(WebElement ele:assignments.done_button){
                if(ele.isDisplayed()){
                    ele.click();
                    break;
                }
            }
           assignments.addTag.get(0).click(); //click on the second addtag
            assignments.addTag_textbox.get(0).sendKeys("cus");
            appiumDriver.findElement(By.xpath("//UIAStaticText[@name='cus']")).click();
           for(WebElement ele:assignments.done_button){
                if(ele.isDisplayed()){
                    ele.click();
                    break;
                }
            }
            assignments.editTags.get(0).click(); //click on the edit tag.
            Assert.assertTrue(assignments.customAddTag.isDisplayed(), "Tag text box is appearing with existing tags");
            assignments.addTag_textbox.get(0).sendKeys("edit tag");
            for(WebElement ele:assignments.done_button){
                if(ele.isDisplayed()){
                    ele.click();
                    break;
                }
            }


            assignments.editTags.get(0).click(); //click on the edit tag.
            assignments.closeNotes.get(3).click();
            Thread.sleep(2000);
            assignments.closeNotes.get(3).click();
            for(WebElement ele:assignments.done_button){
                if(ele.isDisplayed()){
                    ele.click(); //click on the done button
                    break;
                }
            }

            Assert.assertTrue(assignments.customAddTag.isDisplayed(), "Tag text box is appearing with existing tags");
            Assert.assertEquals(assignments.customAddTags.size(), 1, "Selected tag is still displaying");

            assignments.addTag.get(0).click(); //click on the edit tag.
            assignments.addTag_textbox.get(0).sendKeys("cus");
            appiumDriver.findElement(By.xpath("//UIAStaticText[@name='cus']")).click();
            assignments.closeNotes.get(3).click();
            for(WebElement ele:assignments.done_button){
                if(ele.isDisplayed()){
                    ele.click(); //click on the done button
                    break;
                }
            }

            assignments.addTag.get(0).click(); //click on the edit tag.
            myNoteEntry.get(5).click(); //click on my note
            appiumDriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]")).sendKeys("New Note");

            assignments.expanded_course.get(13).click(); //select Chapter one
            Thread.sleep(2000);
            try {
                assignments.save_button.click(); //click on the save button

            } catch (Exception e) {
                appiumDriver.tap(1, 892, 521, 1);

            }

            Assert.assertTrue(assignments.search_textbox.isDisplayed(), "“Search” box is not  available");
            assignments.search_textbox.sendKeys("cus");
            Assert.assertEquals(new BooleanValue().presenceOfElement(260, By.xpath("//UIATextField[contains(@value,'Search tag')]")), false);
            appiumDriver.findElement(By.xpath("//UIAStaticText[@name='Cus']")).click();

            Assert.assertEquals(new BooleanValue().presenceOfElement(260, By.xpath("//UIAStaticText[@name='Filters Applied']")), true);
            Assert.assertEquals(new BooleanValue().presenceOfElement(260, By.xpath("//UIAStaticText[@name='Custom add tag']")), true);

            assignments.closeNotes.get(3).click(); //close suggestion
            appiumDriver.findElement(By.xpath("//UIAStaticText[@name='All Chapters']")).click();
            appiumDriver.findElement(By.xpath("//UIAStaticText[contains(@name,'Ch 2')]")).click();

            String str="No data is available for selected filters. To see your \"My Notes\" entries, please change your filters.";
            String noData=appiumDriver.findElement(By.xpath("//UIAStaticText[contains(@name,'No data is available for selected filters')]")).getAttribute("name").trim();
            Assert.assertEquals(noData,str,"No data is available for selected filters. To see your \"My Notes\" entries, please change your filters. messgae is not displaying");


        }catch(Exception e){
            Assert.fail("Exception in the TC 'myNotePage' in the class 'AssignmentFlow'",e);
        }

    }

    @Test(priority = 8, enabled = true)
    public void postDiscussion(){
        try{

            WebDriverWait  wait = new WebDriverWait(appiumDriver,180);
            String dataIndex = "277";
            String appendChar=generateAppendChar();

            //TC row no 277

            /*  ArrayList<String> instructorInfoList = new ArrayList<>();
            ArrayList<String> studentInfoList = new ArrayList<>();

            instructorInfoList.add("Ins_f_277_03011c");
            studentInfoList.add("stu_f_277_03011c");*/

            ArrayList<String> instructorInfoList = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Navigator_appium().navigateTo("Course Stream"); //naviagate to course stream
            courseStreamAppium.post_discussion.click();
            Thread.sleep(9000);

            appiumDriver.tap(1, 305, 250, 1); //tab on 'T'
            Thread.sleep(3000);
            Assert.assertEquals(courseStreamAppium.courseSream_options.get(4).getAttribute("name").trim(), "Insert Equation");
            Assert.assertEquals(courseStreamAppium.courseSream_options.get(5).getAttribute("name").trim(), "Language");
            Assert.assertEquals(courseStreamAppium.courseSream_options.get(6).getAttribute("name").trim(), "Bold");
            Assert.assertEquals(courseStreamAppium.courseSream_options.get(7).getAttribute("name").trim(), "Italic");
            Assert.assertEquals(courseStreamAppium.courseSream_options.get(8).getAttribute("name").trim(), "Underline");
            Assert.assertEquals(courseStreamAppium.courseSream_options.get(9).getAttribute("name").trim(), "Subscript");
            Assert.assertEquals(courseStreamAppium.courseSream_options.get(10).getAttribute("name").trim(), "Superscript");
            Assert.assertEquals(courseStreamAppium.courseSream_options.get(11).getAttribute("name").trim(), "Text Color");

            String contextTitle=studentInfoList.get(2);
            Assert.assertTrue(courseStreamAppium.shareWith.isDisplayed(), "\"Share with\" option is not present with a text box.");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[contains(@name,'ctitle')]")).getAttribute("name").trim(), contextTitle);

            appiumDriver.findElement(By.xpath("//UIALink[5]/UIATextField[1]")).sendKeys("ipad");
            appiumDriver.findElement(By.xpath("//UIAStaticText[@name='Student,']")).click(); //click on the suggestion
            appiumDriver.getKeyboard().sendKeys("new post");
            appiumDriver.findElement(By.xpath("//UIALink[5]/UIATextField[1]")).sendKeys("ipad");

            Assert.assertEquals(new BooleanValue().presenceOfElement(295, By.xpath("//UIAStaticText[@name='Student,']")), false);


            appiumDriver.tap(1, 319, 110, 1);
            Thread.sleep(9000);
            appiumDriver.getKeyboard().sendKeys("new post");
            Thread.sleep(5000);
            appiumDriver.findElement(By.xpath("//UIAButton[@name='Submit']")).click(); //click on the suggestion*//*


            new Navigator_appium().navigateTo("Course Stream"); //naviagate to course stream
            Assert.assertEquals(courseStreamAppium.discussionPosted.getAttribute("name").trim(), "posted a discussion");
            String postedDiscussion=appiumDriver.findElement(By.xpath("//UIAScrollView[1]/UIAWebView[1]/UIAStaticText[5]")).getAttribute("name");
            System.out.println("postedDiscussion:"+postedDiscussion);

            new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            new Navigator_appium().navigateTo("Course Stream"); //naviagate to course stream
            Assert.assertEquals(courseStreamAppium.discussionPosted.getAttribute("name").trim(), "posted a discussion");
            String postedDiscussion1=appiumDriver.findElement(By.xpath("//UIAScrollView[1]/UIAWebView[1]/UIAStaticText[5]")).getAttribute("name");
            System.out.println("postedDiscussion1:" + postedDiscussion1);

        }catch(Exception e){
            Assert.fail("Exception in the TC 'postDiscussion' in the class 'AssignmentFlow'",e);
        }

    }

    @Test(priority = 9, enabled = true)
    public void uploadLink(){
        try{

            WebDriverWait  wait = new WebDriverWait(appiumDriver,180);
            String dataIndex = "299";
            String appendChar=generateAppendChar();

            //TC row no 299

            /*  ArrayList<String> instructorInfoList = new ArrayList<>();
            ArrayList<String> studentInfoList = new ArrayList<>();

            instructorInfoList.add("Ins_f_277_03011c");
            studentInfoList.add("stu_f_277_03011c");*/

            ArrayList<String> instructorInfoList = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Navigator_appium().navigateTo("Course Stream"); //naviagate to course stream
            courseStreamAppium.post_link.click();

            appiumDriver.tap(1, 319, 110, 1);
            Thread.sleep(9000);
            appiumDriver.getKeyboard().sendKeys("www.google.co.in");
            Thread.sleep(5000);
            appiumDriver.findElement(By.xpath("//UIAButton[@name='Submit']")).click(); //click on the suggestion*//*

            Assert.assertEquals(courseStreamAppium.sharedLink.getAttribute("name").trim(), "shared a link", "Posting with title \"Shared a Link\" is not added in course stream.");
            Assert.assertEquals(courseStreamAppium.google_link.getAttribute("name").trim(), "www.google.co.in", "Posting with title \"Shared a Link\" is not added in course stream.");
            new LoginUsingRandomUser_appium().logOutUser();


            new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            new Navigator_appium().navigateTo("Course Stream"); //naviagate to course stream
            Assert.assertEquals(courseStreamAppium.sharedLink.getAttribute("name").trim(), "shared a link", "Posting with title \"Shared a Link\" is not added in course stream.");
            Assert.assertEquals(courseStreamAppium.google_link.getAttribute("name").trim(), "www.google.co.in", "Posting with title \"Shared a Link\" is not added in course stream.");
            new LoginUsingRandomUser_appium().logOutUser();

            new LoginUsingRandomUser_appium().loginAsRandomInstructor("302");
            new Navigator_appium().navigateTo("Course Stream"); //naviagate to course stream
            Assert.assertEquals(new BooleanValue().presenceOfElement(302,By.xpath("//UIAStaticText[@name='shared a link']")),false,"Posting with title \"Shared a Link\" is available in course stream for other instructor");


        }catch(Exception e){
            Assert.fail("Exception in the TC 'uploadLink' in the class 'AssignmentFlow'",e);
        }

    }

}
