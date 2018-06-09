package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1816;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by root on 1/9/15.
 */
public class MentorReleaseGradeForAll extends Driver {
    @Test(priority = 1,enabled = true)
    public void releaseGradeForAllInARP()
    {

        //Tc row no 111
        //"1. Login as instructor 2. Navigate to Asignment Summary Page from Main navigator 3. Click on “View Grade” Button for Gradable Assignment"
        try {
            CurrentAssignments currentAssignments= PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("111_1");//login as student
            new LoginUsingLTI().ltiLogin("111");//login as instructor
            //new Assignment().create(111); //create assignment

            new LoginUsingLTI().ltiLogin("111");//login as instructor
            new Assignment().assignToStudent(111); //assign to the student

            new LoginUsingLTI().ltiLogin("111_1"); //login as student
            new Assignment().submitAssignmentAsStudent(111); //submit assignment

            new LoginUsingLTI().ltiLogin("111");//login as instructor
            new Navigator().NavigateTo("Current Assignments");// navigate to the assignment summary page
            currentAssignments.getViewGrade_link().click();//click on the view grade link

            //Expected 1 It should Navigate to Assignment Response page of that Assignment.
            Assert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", " Instructor has not Navigated to Assignment Response page of that Assignment");
            List<WebElement> assignmentName=driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            Assert.assertEquals(assignmentName.get(1).getText().trim(),"Assignment_IT18","Same assignment is not present in the Assignment Response page");

            //Tc row no 12 4. Click on "Enter Grade" for any student.
            WebElement menuitem = currentAssignments.getFamilyGivenName();
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(menuitem);
            currentAssignments.getEnterGrade_link().click();
            Thread.sleep(2000);

            //Expected 2 It should allow to enter marks for all the questions and click any where on the application it should get saved automitically
            currentAssignments.getEnterGrade_textBox().clear();
            Thread.sleep(2000);
            currentAssignments.clearGrade_textBox.sendKeys("0.8");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//html/body")).click();
            Thread.sleep(5000);
            Assert.assertEquals(currentAssignments.getTotalMark().getText().trim(),"0.8","marks are not saved after provide grade");
            //Tc row no 3 5. Click on "View Response" page on any question

            new MouseHover().mouserhoverbywebelement(currentAssignments.getGradeBookQuestionContent());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",currentAssignments.getViewResponseLink());


            //Expected 3 It should navigate to that particular question
            Thread.sleep(4000);
            if(!currentAssignments.getQuestionText().getText().trim().contains("True False Test Question for IT18"))
                Assert.fail("instructor is not navigated to the particular question");
            //Expected 4 It should display grades and feedback added previously by instructor if any.
            Assert.assertEquals(currentAssignments.getPerformanceScoreBox().getAttribute("value").trim(),"0.8","grades is not displayed  added previously by instructor");

            //Tc row no 15,16 &17 6. Edit marks or enter feedback in View response page
            currentAssignments.getPerformanceScoreBox().clear();
            currentAssignments.getPerformanceScoreBox().sendKeys("0.6");
            currentAssignments.getFeedBack_textBox().clear();
            currentAssignments.getFeedBack_textBox().sendKeys("This is a FeedbackText");
            currentAssignments.getSave_button().click();
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getSaveMessage().getText().trim(),"Saved successfully.","Grade and feedback is not save successfully");

            //Tc row no 18



        } catch (Exception e) {
            Assert.fail("Exception in test case releaseGradeForAllInARP of class ReleaseGradeForAll", e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void gradableAssignmentWithPolicyOne()
    {

        //Tc row no 19
        //"1. Login as instructor 2. Navigate to Assignment Summary Page from Main navigator 3. Click on “View Grade” Button for Gradable Assignment"
        try {
            CurrentAssignments currentAssignments= PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "12");
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", "12");
            System.out.println("assignmentpolicyname:"+assignmentpolicyname);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "12");
            new LoginUsingLTI().ltiLogin("12_1");//login as student
            new LoginUsingLTI().ltiLogin("12");//login as instructor
            new Assignment().create(12);
            new Assignment().addQuestions(12, "truefalse", "");
            new LoginUsingLTI().ltiLogin("12");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(12);//assign to student

            new LoginUsingLTI().ltiLogin("12_1");//login as student
            new Assignment().submitAssignmentAsStudent(12); //submit assignment

            new LoginUsingLTI().ltiLogin("12");//login as instructor
            new Navigator().NavigateTo("Current Assignments");// navigate to the assignment summary page
            currentAssignments.getViewGrade_link().click();//click on the view grade link
            //Expected 1 It should Navigate to Assignment Response page of that Assignment.
            Assert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(),"Assignment Responses"," Instructor has not Navigated to Assignment Response page of that Assignment");
            List<WebElement> assignmentName=driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            Assert.assertEquals(assignmentName.get(1).getText().trim(),"Assignment_IT18_12","Same assignment is not present in the Assignment Response page");
            //Tc row no 20 4. Click on "Enter Grade" for the student who submitted the assignment.
            WebElement menuitem = currentAssignments.getFamilyGivenName();
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(menuitem);
            currentAssignments.getEnterGrade_link().click();
            Thread.sleep(2000);

            //Expected 2 It should allow to enter marks for all the questions and click any where on the application it should get saved automitically
            currentAssignments.getEnterGrade_textBox().clear();
            Thread.sleep(2000);
            currentAssignments.clearGrade_textBox.sendKeys("0.8");
            Thread.sleep(2000);
            currentAssignments.getEnterGrade_textBox().sendKeys(Keys.TAB);
            Thread.sleep(2000);
            driver.switchTo().activeElement().sendKeys("0.8");
            driver.findElement(By.id("ls-assignment-not-started-count")).click(); //clicking on not started box to save the marks
            Thread.sleep(5000);
            Assert.assertEquals(currentAssignments.getTotalMark().getText().trim(),"1.6","marks are not saved after provide grade");
            //Tc row no 21 5. Click on "View Response" page on any question

            new MouseHover().mouserhoverbywebelement(currentAssignments.getGradeBookQuestionContent());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",currentAssignments.getViewResponseLink());


            //Expected 3 It should navigate to that particular question
            if(!currentAssignments.getQuestionText().getText().trim().contains(questiontext))
                Assert.fail("instructor is not navigated to the particular question");
            //Expected 4 It should display grades and feedback added previously by instructor if any.
            Assert.assertEquals(currentAssignments.getPerformanceScoreBox().getAttribute("value").trim(),"0.8","grades is not displayed  added previously by instructor");

            //Tc row no 22,23 &24 6. Edit marks or enter feedback in View response page
            currentAssignments.getPerformanceScoreBox().clear();
            currentAssignments.getPerformanceScoreBox().sendKeys("0.6");
            currentAssignments.getFeedBack_textBox().clear();
            currentAssignments.getFeedBack_textBox().sendKeys("This is a FeedbackText");
            currentAssignments.getSave_button().click();
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getSaveMessage().getText().trim(),"Saved successfully.","Grade and feedback is not save successfully");

            //Tc row no 25 Navigate to next/previous question from view reponse page
            currentAssignments.getNextArrow().click();
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getPerformanceScoreBox().getAttribute("value").trim(),"0.8","grades is not displayed  added previously by instructor");

            Thread.sleep(2000);
            currentAssignments.getPrevArrow().click();
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getPerformanceScoreBox().getAttribute("value").trim(), "0.6", "grades is not displayed properly");
            Assert.assertEquals(currentAssignments.getFeedBack_textBox().getText().trim(), "This is a FeedbackText", "feedback is not displayed properly");


        } catch (Exception e) {
            Assert.fail("Exception in test case gradableAssignmentWithPolicyOne of class ReleaseGradeForAll", e);
        }
    }
    @Test(priority = 3,enabled = true)
    public void gradableAssignmentWithPolicyThree()
    {

        //Tc row no 33
        //"1. Login as instructor 2. Navigate to Assignment Summary Page from Main navigator 3. Click on “View Grade” Button for Gradable Assignment"
        try {
            CurrentAssignments currentAssignments= PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "13");
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", "13");
            System.out.println("assignmentpolicyname:"+assignmentpolicyname);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "13");
            new LoginUsingLTI().ltiLogin("13_1");//login as student
            new LoginUsingLTI().ltiLogin("13");//login as instructor
          /*  new Assignment().create(13); //create assignment
            new Assignment().addQuestions(13, "essay", "");*/

            new LoginUsingLTI().ltiLogin("13");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//till save policy

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(13);//assign to student

            new LoginUsingLTI().ltiLogin("13_1");//login as student
            new Assignment().submitAssignmentAsStudent(13); //submit assignment

            new LoginUsingLTI().ltiLogin("13");//login as instructor
            new Navigator().NavigateTo("Current Assignments");// navigate to the assignment summary page
            currentAssignments.getViewGrade_link().click();//click on the view grade link
            //Expected 1 It should Navigate to Assignment Response page of that Assignment.
            Assert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(),"Assignment Responses"," Instructor has not Navigated to Assignment Response page of that Assignment");
            List<WebElement> assignmentName=driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            Assert.assertEquals(assignmentName.get(1).getText().trim(),"Assignment_IT18_13","Same assignment is not present in the Assignment Response page");
            //Tc row no 34 4. Click on "Enter Grade" for the student who submitted the assignment.
            WebElement menuitem = currentAssignments.getFamilyGivenName();
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(menuitem);
            currentAssignments.getEnterGrade_link().click();
            Thread.sleep(2000);

            //Expected 2 It should allow to enter marks for all the questions and click any where on the application it should get saved automitically
            currentAssignments.getEnterGrade_textBox().clear();
            Thread.sleep(2000);
            currentAssignments.clearGrade_textBox.sendKeys("0.7");
            Thread.sleep(2000);
            currentAssignments.getEnterGrade_textBox().sendKeys(Keys.TAB);
            Thread.sleep(2000);
            driver.switchTo().activeElement().sendKeys("0.7");
            driver.findElement(By.id("ls-assignment-not-started-count")).click(); //clicking on not started box to save the marks
            Thread.sleep(5000);
            Assert.assertEquals(currentAssignments.getTotalMark().getText().trim(),"1.4","marks are not saved after provide grade");
            //Tc row no 35 5. Click on "View Response" page on any question

            new MouseHover().mouserhoverbywebelement(currentAssignments.getGradeBookQuestionContent());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",currentAssignments.getViewResponseLink());


            //Expected 3 It should navigate to that particular question
            if(!currentAssignments.getQuestionText().getText().trim().contains(questiontext))
                Assert.fail("instructor is not navigated to the particular question");
            //Expected 4 It should display grades and feedback added previously by instructor if any.
            Assert.assertEquals(currentAssignments.getPerformanceScoreBox().getAttribute("value").trim(),"0.7","grades is not displayed  added previously by instructor");

            //Tc row no 36,37 &38 6. Edit marks or enter feedback in View response page
            currentAssignments.getPerformanceScoreBox().clear();
            currentAssignments.getPerformanceScoreBox().sendKeys("0.6");
            currentAssignments.getFeedBack_textBox().clear();
            currentAssignments.getFeedBack_textBox().sendKeys("This is a FeedbackText");
            currentAssignments.getSave_button().click();
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getSaveMessage().getText().trim(),"Saved successfully.","Grade and feedback is not save successfully");

            //Tc row no 39 Navigate to next/previous question from view reponse page
            currentAssignments.getNextArrow().click();
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getPerformanceScoreBox().getAttribute("value").trim(),"0.7","grades is not displayed  added previously by instructor");

            Thread.sleep(2000);
            currentAssignments.getPrevArrow().click();
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getPerformanceScoreBox().getAttribute("value").trim(), "0.6", "grades is not displayed properly");
            Assert.assertEquals(currentAssignments.getFeedBack_textBox().getText().trim(), "This is a FeedbackText", "feedback is not displayed properly");


        } catch (Exception e) {
            Assert.fail("Exception in test case gradableAssignmentWithPolicyThree of class ReleaseGradeForAll", e);
        }
    }
    @Test(priority = 4,enabled = true)
    public void gradableAssignmentWithPolicyFour()
    {

        //Tc row no 40
        //"1. Login as instructor 2. Navigate to Assignment Summary Page from Main navigator 3. Click on “View Grade” Button for Gradable Assignment"
        try {
            CurrentAssignments currentAssignments= PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "14");
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", "14");
            new LoginUsingLTI().ltiLogin("14_1");//login as student
            new LoginUsingLTI().ltiLogin("14");//login as instructor
            new Assignment().create(14); //create assignment
            new Assignment().addQuestions(14, "truefalse", "");

            new LoginUsingLTI().ltiLogin("14");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description21", "2", null, false, "1", "", "", "", "", "");//till save policy

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(14);//assign to student

            new LoginUsingLTI().ltiLogin("14_1");//login as student
            new Assignment().submitAssignmentAsStudent(14); //submit assignment

            new LoginUsingLTI().ltiLogin("14");//login as instructor
            new Assignment().releaseGrades(14, "Release Grade for All");
            new Navigator().NavigateTo("Current Assignments");// navigate to the assignment summary page
            currentAssignments.getViewGrade_link().click();//click on the view grade link
            //Expected 1 It should Navigate to Assignment Response page of that Assignment.
            Assert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(),"Assignment Responses"," Instructor has not Navigated to Assignment Response page of that Assignment");
            List<WebElement> assignmentName=driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            Assert.assertEquals(assignmentName.get(1).getText().trim(),"Assignment_IT18_14","Same assignment is not present in the Assignment Response page");
            //Tc row no 41 4. Click on "Enter Grade" for the student who submitted the assignment.
            WebElement menuitem = currentAssignments.getFamilyGivenName();
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(menuitem);
            currentAssignments.getEnterGrade_link().click();
            Thread.sleep(2000);

            //Expected 2 It should allow to enter marks for all the questions and click any where on the application it should get saved automitically
            currentAssignments.getEnterGrade_textBox().clear();
            Thread.sleep(2000);
            currentAssignments.clearGrade_textBox.sendKeys("0.7");
            Thread.sleep(2000);
            currentAssignments.getEnterGrade_textBox().sendKeys(Keys.TAB);
            Thread.sleep(2000);
            driver.switchTo().activeElement().sendKeys("0.7");
            driver.findElement(By.id("ls-assignment-not-started-count")).click(); //clicking on not started box to save the marks
            Assert.assertEquals(currentAssignments.getTotalMark().getText().trim(),"1.4","marks are not saved after provide grade");
            //Tc row no 42 5. Click on "View Response" page on any question

            new MouseHover().mouserhoverbywebelement(currentAssignments.getGradeBookQuestionContent());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",currentAssignments.getViewResponseLink());


            //Expected 3 It should navigate to that particular question
            if(!currentAssignments.getQuestionText().getText().trim().contains(questiontext))
                Assert.fail("instructor is not navigated to the particular question");
            //Expected 4 It should display grades and feedback added previously by instructor if any.
            Assert.assertEquals(currentAssignments.getPerformanceScoreBox().getAttribute("value").trim(),"0.7","grades is not displayed  added previously by instructor");

            //Tc row no 43,44 &45 6. Edit marks or enter feedback in View response page
            currentAssignments.getPerformanceScoreBox().clear();
            currentAssignments.getPerformanceScoreBox().sendKeys("0.6");
            currentAssignments.getFeedBack_textBox().clear();
            currentAssignments.getFeedBack_textBox().sendKeys("This is a FeedbackText");
            currentAssignments.getSave_button().click();
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getSaveMessage().getText().trim(),"Saved successfully.","Grade and feedback is not save successfully");

            //Tc row no 46 Navigate to next/previous question from view reponse page
            currentAssignments.getNextArrow().click();
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getPerformanceScoreBox().getAttribute("value").trim(),"0.7","grades is not displayed  added previously by instructor");

            Thread.sleep(2000);
            currentAssignments.getPrevArrow().click();
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getPerformanceScoreBox().getAttribute("value").trim(),"0.6","grades is not displayed properly");
            Assert.assertEquals(currentAssignments.getFeedBack_textBox().getText().trim(),"This is a FeedbackText","feedback is not displayed properly");

        } catch (Exception e) {
            Assert.fail("Exception in test case gradableAssignmentWithPolicyThree of class ReleaseGradeForAll", e);
        }
    }
    @Test(priority = 5,enabled = true)
    public void nonGradableAssignmentQuestionType()
    {

        //Tc row no 47
        //"1. Login as instructor 2. Navigate to Assignment Summary Page from Main navigator 3. Click on “View Grade” Button for Gradable Assignment"
        try {
            CurrentAssignments currentAssignments= PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "15");
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", "15");
            new LoginUsingLTI().ltiLogin("15_1");//login as student

            new Assignment().create(15); //create assignment
            new Assignment().addQuestions(15,"truefalse","");
            new LoginUsingLTI().ltiLogin("15");//login as instructor
            new Assignment().assignToStudent(15); //assign to the student

            new LoginUsingLTI().ltiLogin("15_1"); //login as student
            new Assignment().submitAssignmentAsStudent(15); //submit assignment

            new LoginUsingLTI().ltiLogin("15");//login as instructor

            new Navigator().NavigateTo("Current Assignments");// navigate to the assignment summary page
            new Click().clickByclassname("ls-grade-book-assessment");//click on the view feedback
            int index = 0;
            List<WebElement> provideFeedback=driver.findElements(By.cssSelector("div[class='idb-gradebook-content-coloumn']"));
            for(WebElement eachFeedback:provideFeedback)
            {
                for(int i = index; i < provideFeedback.size(); i++){
                    new MouseHover().mouserHoverByClassList("idb-gradebook-question-content", index);
                    Thread.sleep(3000);
                    index++;
                    System.out.println("hovered "+i);
                    break;
                }
                new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));
                }
                driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys("This is a FeedbackText");
                driver.findElement(By.className("view-user-question-performance-save-btn")).click();
                new Click().clickByid("close-view-responses");
            }
            new Assignment().releaseGrades(15, "Release Feedback for All");//click on the release feedback
            new Navigator().NavigateTo("Current Assignments");// navigate to the assignment summary page
            currentAssignments.getViewGrade_link().click();//click on the view feedback link
            //Expected 1 It should Navigate to Assignment Response page of that Assignment.
            Assert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(),"Assignment Responses"," Instructor has not Navigated to Assignment Response page of that Assignment");
            List<WebElement> assignmentName=driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            Assert.assertEquals(assignmentName.get(1).getText().trim(),"Assignment_IT18_15","Same assignment is not present in the Assignment Response page");
            //Tc row no 48 5. Click on "View Response" page on any question
            new MouseHover().mouserhoverbywebelement(currentAssignments.getGradeBookQuestionContent());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",currentAssignments.getViewResponseLink());


            //Expected 3 It should navigate to that particular question
            if(!currentAssignments.getQuestionText().getText().trim().contains(questiontext))
                Assert.fail("instructor is not navigated to the particular question");
            //Expected 4 It should display and feedback added previously by instructor if any.
            Assert.assertEquals(currentAssignments.getFeedBack_textBox().getText().trim(),"This is a FeedbackText","feedback is not displayed properly");

            //Tc row no 50  6. 5. Edit or enter feedback in View response page
            currentAssignments.getFeedBack_textBox().clear();
            currentAssignments.getFeedBack_textBox().sendKeys("This is a new FeedbackText");
            currentAssignments.getSave_button().click();
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getSaveMessage().getText().trim(),"Saved successfully.","Grade and feedback is not save successfully");

            //Tc row no 52 Navigate to next/previous question from view reponse page
            currentAssignments.getNextArrow().click();
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getFeedBack_textBox().getText().trim(),"This is a FeedbackText","feedback is not displayed properly");

            Thread.sleep(2000);
            currentAssignments.getPrevArrow().click();
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getFeedBack_textBox().getText().trim(),"This is a new FeedbackText","feedback is not displayed properly");

        } catch (Exception e) {
            Assert.fail("Exception in test case nonGradableAssignmentQuestionType of class ReleaseGradeForAll", e);
        }
    }
    @Test(priority = 6,enabled = true)
    public void gradableDwAssignment()
    {

        //Tc row no 53
        //"1. Login as instructor 2. Navigate to Assignment Summary Page from Main navigator 3. Click on “View Grade” Button for Gradable Assignment"
        try {
            CurrentAssignments currentAssignments= PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("16_1");//login as student
            new LoginUsingLTI().ltiLogin("16"); //login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(16);

            new LoginUsingLTI().ltiLogin("16_1");//login a student
            new Navigator().NavigateTo("Assignments");	//navigate to Assignment
            new Click().clickByclassname("learning-activity-title"); //click on DW
            String perspective = new RandomString().randomstring(10);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignment

            new LoginUsingLTI().ltiLogin("16"); //login as instructor
            new Assignment().releaseGrades(16, "Release Grade for All");

            new Navigator().NavigateTo("Current Assignments");// navigate to the assignment summary page
            String beforeClickOnTheViewGradeDwAssignmentName=currentAssignments.getDwAssignmentName().getText().trim();
            currentAssignments.getViewGrade_link().click();//click on the view feedback link
            //Expected 1 It should Navigate to Assignment Response page of that Assignment.
            Assert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", " Instructor has not Navigated to Assignment Response page of that Assignment");
            Thread.sleep(4000);
            List<WebElement> dw=driver.findElements(By.cssSelector("span[class='ls-leaning-activity instructor-assessment-review']"));
            String afterClickOnTheViewGradeDwAssignmentName=dw.get(1).getText().trim();
            Assert.assertEquals(beforeClickOnTheViewGradeDwAssignmentName,afterClickOnTheViewGradeDwAssignmentName,"Dw assignment is not same after click on view grade");

            //Tc row no 54 4. Click on 'Enter Grade' for any student.



            Actions actions=new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END);
            Actions action = new Actions(driver);
            List<WebElement> we =currentAssignments.enterGrade_mouseOver;
            action.moveToElement(we.get(0)).build().perform();

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", currentAssignments.getEnterGrade_link());	//click on Enter Grade
            Thread.sleep(2000);
            currentAssignments.getEnterGrade_textBox().clear();
            Thread.sleep(2000);
            currentAssignments.clearGrade_textBox.sendKeys("0.7");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//html/body")).click();
            Thread.sleep(5000);
            //Expected It should allow to enter marks for that DW Assignment and click any where on the application it should get saved automitically.
            Assert.assertEquals(currentAssignments.getGradeMark().getText().trim(),"0.7","marks are not saved after provide grade");

            //Tc row no 55 5. Click on "View Response" page on any question
            new MouseHover().mouserhoverbywebelement(currentAssignments.getGradeBookQuestionContent());
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",currentAssignments.getViewResponseLink());


            //Expected 3 It should navigate to the response page of DW for a particular student
            if(!currentAssignments.getDwComment().getText().trim().contains(perspective))
                Assert.fail("instructor is not navigate to the response page of DW for a particular student");
            //Expected 4 It should display grades and feedback added previously by instructor if any.
            Assert.assertEquals(currentAssignments.getPerformanceScoreBox().getAttribute("value").trim(),"0.7","grades is not displayed  added previously by instructor");
            //Tc row no 57 6. Edit marks or enter feedback in View response page

            currentAssignments.getPerformanceScoreBox().clear();
            currentAssignments.getPerformanceScoreBox().sendKeys("0.6");
            currentAssignments.getFeedBack_textBox().clear();
            currentAssignments.getFeedBack_textBox().sendKeys("This is a FeedbackText");
            currentAssignments.getDwSave_button().click();
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.id("view-user-question-performance-save-success-message")));
            Assert.assertEquals(currentAssignments.getSaveMessage().getText().trim(),"Saved successfully.","Grade and feedback is not save successfully");
            new Navigator().NavigateTo("Current Assignments");// navigate to the assignment summary page
            currentAssignments.getViewGrade_link().click();//click on the view feedback link
            Assert.assertEquals(currentAssignments.getGradeMark().getText().trim(),"0.6","marks are not saved after provide grade");

        } catch (Exception e) {
            Assert.fail("Exception in test case gradableDwAssignment of class ReleaseGradeForAll", e);
        }
    }


    @Test(priority = 7,enabled = true)
    public void nonGradableDwAssignment()
    {

        //Tc row no 17
        //"1. Login as instructor 2. Navigate to Assignment Summary Page from Main navigator 3. Click on “View Grade” Button for Gradable Assignment"
        try {
            CurrentAssignments currentAssignments= PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new LoginUsingLTI().ltiLogin("17_1");//login as student
            new LoginUsingLTI().ltiLogin("17"); //login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(17);

            new LoginUsingLTI().ltiLogin("17_1");//login a student
            new Navigator().NavigateTo("Assignments");	//navigate to Assignment
            new Click().clickByclassname("learning-activity-title"); //click on DW
            String perspective = new RandomString().randomstring(10);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignment

            new LoginUsingLTI().ltiLogin("17"); //login as instructor
            new Assignment().provideFeedBackForMultipleQuestion(17, "This is a New FeedbackText");

            new Navigator().NavigateTo("Current Assignments");// navigate to the assignment summary page
            currentAssignments.getViewGrade_link().click();//click on the student response link
            Thread.sleep(2000);

            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link

            //Tc row no 61
            String expected="What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?";
            Assert.assertEquals(new TextFetch().textfetchbyxpath("(//div[@id='idb-gradeBook-title'])[1]"),expected," Instructor has not Navigated to Assignment Response page of that Assignment");

            //Tc row no 62 Previously added feedback should be displayed
            Assert.assertEquals(currentAssignments.getFeedBack_textBox().getText().trim(),"This is a New FeedbackText","Previously added feedback has not displayed");
            //Tc row no 63 5.Click on 'Instructor Feedback' box
            //Expected Instructor Feedback' box should be Editable
            currentAssignments.getFeedBack_textBox().click();//click on the feedback box
            //Tc row no 64 6.Enter or Change Feedback in the 'Instructor Feedback' box

            currentAssignments.getFeedBack_textBox().clear();
            currentAssignments.getFeedBack_textBox().sendKeys("This is a new FeedbackText");
            currentAssignments.getDwSave_button().click();
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getSaveMessage().getText().trim(),"Saved successfully.","Grade and feedback is not save successfully");



        } catch (Exception e) {
            Assert.fail("Exception in test case nonGradableDwAssignment of class ReleaseGradeForAll", e);
        }
    }

}

