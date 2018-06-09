package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.testcases.IT21Mobile.R218E2E;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelperAppium.*;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium.LoginPage_appium;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium.RandomLoginPage_appium;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.dashboard.Dashboard;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.eTextbook.Discussion;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.UIElement;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelperAppium.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Snapwiz on 02/09/15.
 */
public class EndToEndFlow extends Driver {




    @Test(priority =1,enabled = true)
    public void performActionsForDiagnosticAssessment(){
        try{

            String dataIndex = "9";
            /*Login to the application as a student
            Click on the Main Navigator on top left hand corner of the screen
            Click on e - text book option
            Select a Chapter and Click on Orion Adaptive Practice tab
            Select the Confidence level and Click on Begin Diagnostic button
            Complete the Diagnostic Test*/

            WebDriverWait wait = new WebDriverWait(appiumDriver,20);
            new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Navigator_appium().navigateTo("etextbook");
            new SelectChapter_appium().selectChapter(1);
            wait.until(ExpectedConditions.visibilityOf(eTextbook.button_OrionAdaptivePractice));
            eTextbook.button_OrionAdaptivePractice.click();
            new Tap_appium().tapConfidenceLevel(1);
            wait.until(ExpectedConditions.visibilityOf(eTextbook.button_beginDiagnostic));
            eTextbook.button_beginDiagnostic.click();
            wait.until(ExpectedConditions.visibilityOf(eTextbook.tab_diagTest));
            Thread.sleep(5000);
            for(int a=0; a <=20; a++) {
                Thread.sleep(1000);
                appiumDriver.findElement(By.xpath("//UIAStaticText[@name='A']")).click();//Click on True button
                if(a==20){
                    eTextbook.button_finish.click();
                }else{
                    eTextbook.button_submit.click();
                }
            }


            wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Performance']"))));
            /*String performanceSummaryText = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Performance']")).getText()+appiumDriver.findElement(By.xpath("//UIAStaticText[@name = ' Summary']")).getText();
            System.out.println("performanceSummaryText : " + performanceSummaryText);
            Assert.assertEquals(performanceSummaryText, "Performance Summary", "Student should be able to finish the assignment and should get navigated to Performance summary page of the assignment");*/

            String performanceText = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Performance']")).getText();
            Assert.assertEquals(performanceText,"Performance","The diagnostic test should be completed and the user should be navigated to Performance summary page");


        }catch(Exception e){
            Assert.fail("Exception in the testscript 'performActionsForDiagnosticAssessment' in the class 'EndToEndFlow'",e);
        }
    }


    @Test(priority =2,enabled = true)
    public void performActionsForNonGradableAssignments(){
        try{
            Assignments assignment = PageFactory.initElements(driver, Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Discussion discussion = PageFactory.initElements(driver, Discussion.class);
            String dataIndex = "15";


            WebDriverWait wait = new WebDriverWait(appiumDriver,20);
            ArrayList<String> instructorInfoList  = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            System.out.println("instructorInfoList.get(0) : " + instructorInfoList.get(0));
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            System.out.println("studentInfoList.get(0) : " + studentInfoList.get(0));

            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), "snapwiz", dataIndex);
            new Navigator().NavigateTo("e-Textbook");//Click on 'e-Textbook'
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            Thread.sleep(5000);
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(dataIndex);


            new Navigator_appium().navigateTo("Dashboard");
            WebElement ele = appiumDriver.findElement(By.xpath("//UIALink[@name = 'Student, iPadIntegrationInstructor posted an assignment. - navigate to course stream page for details']"));
            System.out.println("ele 1 : " + ele.getText());
            new Navigator_appium().navigateTo("Course Stream");

            ele = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'D1 - What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?']"));
            Assert.assertEquals(ele.getText(), "D1 - What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?", "Assigned Widget should be displayed on the Course stream as card view");



            //new Tap_appium().tapMainNavigator();
            new Navigator_appium().navigateTo("Assignments");
            ele = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'D1 - What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?']"));
            ele.click();















            new DirectLogin().directLoginWithCreditial(studentInfoList.get(0), "snapwiz", dataIndex);
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));

            //Row No - 136 :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();


            //Row No - 137 :Click on Course Stream option
            //Expected -Course stream page should be displayed to the user
            dashboard.menu_courseStream.click();
            new UIElement().waitAndFindElement(By.className("share-to-ls-label"));
            System.out.println("dxSX: " + driver.getCurrentUrl());
            if (!driver.getCurrentUrl().endsWith("/coursestream")) {
                Assert.fail("Course stream page should be displayed to the user");
            }



            //Row No - 139 :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();



            //Row No - 140 : Click on Assignments option
            //Expected -Assignment page should be displayed to the user
            assignment.menu_Assignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("Assignment page should be displayed to the user");
            }



            //Row No - 142 :Click on the Assignment
            //Expected -The Assignment should be opened in a new tab
            currentAssignments.getLessonAssignment().click();
            //Row No - 143 :Finish the Assignment and Submit
            //Expected -The assignment should be finished and assignments page screen should be displayed
            String perspectiveText = new RandomString().randomstring(10);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspectiveText);
            Assert.assertEquals(currentAssignments.getDwComment().getText(), perspectiveText, "The assignment is not finished and assignments page screen is not displayed");




























            //Row No - 144 :Navigate to the Instructor Login and click on the main navigation
            //Expected -The Navigation List should be displayed to the User
            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), "snapwiz", dataIndex);
            validateMainNavigator();

            //Row No - 145 : Click on Assignments option
            //Expected -The sub menus should be displayed
            assignment.menu_Assignments.click();



            //Row No - 146 :Click on Current Assignments submenu
            //Expected -Current assignments page should be opened
            assignment.subMenu_currentAssignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Chapters"));


            //Row No - 147 :Click on View response link of the Assignment
            //Expected -View Response page should be opened
            currentAssignments.getViewGrade_link().click();
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            Thread.sleep(3000);




            //Row No - 148 :Click on View response link of the Question Number
            //Expected -View Response page should be opened in a new tab
            new ResponsePage().openViewResponsePage();
            Thread.sleep(5000);

            //Row No - 150 :Click on Save button
            //Expected -The entered text should be saved
            assignmentResponsesPage.getFeedbackTextArea().click();
            String feedback = new RandomString().randomstring(10);
            assignmentResponsesPage.getFeedbackTextArea().sendKeys(feedback);
            currentAssignments.getDwSave_button().click();
            Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(),"Saved successfully.","The entered text is not saved");


            //Row No - 151 :Close the tab
            //Expected -The tab should be closed
            assignmentResponsesPage.closeIcon_dwResponsePage.click();




            new Navigator_appium().navigateTo("Dashboard");
            new Navigator_appium().navigateTo("etextbook");
            new Navigator_appium().navigateTo("Course Stream");
            new Navigator_appium().navigateTo("My Notes");
            new Navigator_appium().navigateTo("Assignments");
            new Wait().visibilityOf(assignments.label_allAssignemnts);
            assignments.label_allAssignemnts.click();
            assignments.label_questionPractice.click();

            new Navigator_appium().navigateTo("Assignments");
            WebElement element = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'D1 - What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?']"));
            element.click();

        }catch(Exception e){
            Assert.fail("Exception in the testscript 'performActionsForNonGradableAssignments' in the class 'EndToEndFlow'",e);
        }
    }






    @Test(priority =3,enabled = true)
    public void performActionsForGradableAssignments(){
        try{
            Assignments assignment = PageFactory.initElements(driver, Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Discussion discussion = PageFactory.initElements(driver, Discussion.class);
            String dataIndex = "65";


            WebDriverWait wait = new WebDriverWait(appiumDriver,20);
            ArrayList<String> instructorInfoList  = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            System.out.println("instructorInfoList.get(0) : " + instructorInfoList.get(0));
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            System.out.println("studentInfoList.get(0) : " + studentInfoList.get(0));

            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), "snapwiz", dataIndex);
            new Navigator().NavigateTo("e-Textbook");//Click on 'e-Textbook'
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            Thread.sleep(5000);
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(dataIndex,"gradable");


            new Navigator_appium().navigateTo("Dashboard");
            WebElement ele = appiumDriver.findElement(By.xpath("//UIALink[@name = 'Student, iPadIntegrationInstructor posted an assignment. - navigate to course stream page for details']"));
            System.out.println("ele 1 : " + ele.getText());
            new Navigator_appium().navigateTo("Course Stream");

            ele = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'D1 - What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?']"));
            Assert.assertEquals(ele.getText(), "D1 - What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?", "Assigned Widget should be displayed on the Course stream as card view");



            //new Tap_appium().tapMainNavigator();
            new Navigator_appium().navigateTo("Assignments");
            ele = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'D1 - What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?']"));
            ele.click();















            new DirectLogin().directLoginWithCreditial(studentInfoList.get(0), "snapwiz", dataIndex);
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));

            //Row No - 136 :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();


            //Row No - 137 :Click on Course Stream option
            //Expected -Course stream page should be displayed to the user
            dashboard.menu_courseStream.click();
            new UIElement().waitAndFindElement(By.className("share-to-ls-label"));
            System.out.println("dxSX: " + driver.getCurrentUrl());
            if (!driver.getCurrentUrl().endsWith("/coursestream")) {
                Assert.fail("Course stream page should be displayed to the user");
            }



            //Row No - 139 :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();



            //Row No - 140 : Click on Assignments option
            //Expected -Assignment page should be displayed to the user
            assignment.menu_Assignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("Assignment page should be displayed to the user");
            }



            //Row No - 142 :Click on the Assignment
            //Expected -The Assignment should be opened in a new tab
            currentAssignments.getLessonAssignment().click();
            //Row No - 143 :Finish the Assignment and Submit
            //Expected -The assignment should be finished and assignments page screen should be displayed
            String perspectiveText = new RandomString().randomstring(10);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspectiveText);
            Assert.assertEquals(currentAssignments.getDwComment().getText(), perspectiveText, "The assignment is not finished and assignments page screen is not displayed");




























            //Row No - 144 :Navigate to the Instructor Login and click on the main navigation
            //Expected -The Navigation List should be displayed to the User
            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), "snapwiz", dataIndex);
            validateMainNavigator();

            //Row No - 145 : Click on Assignments option
            //Expected -The sub menus should be displayed
            assignment.menu_Assignments.click();



            //Row No - 146 :Click on Current Assignments submenu
            //Expected -Current assignments page should be opened
            assignment.subMenu_currentAssignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Chapters"));


            //Row No - 147 :Click on View response link of the Assignment
            //Expected -View Response page should be opened
            currentAssignments.getViewGrade_link().click();
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            Thread.sleep(3000);




            //Row No - 148 :Click on View response link of the Question Number
            //Expected -View Response page should be opened in a new tab
            new ResponsePage().openViewResponsePage();
            Thread.sleep(5000);

            //Row No - 150 :Click on Save button
            //Expected -The entered text should be saved
            assignmentResponsesPage.getFeedbackTextArea().click();
            String feedback = new RandomString().randomstring(10);
            assignmentResponsesPage.getFeedbackTextArea().sendKeys(feedback);
            currentAssignments.getDwSave_button().click();
            Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(),"Saved successfully.","The entered text is not saved");


            //Row No - 151 :Close the tab
            //Expected -The tab should be closed
            assignmentResponsesPage.closeIcon_dwResponsePage.click();

            assignmentResponsesPage.getReleaseGradeForAll().click();



            new Navigator_appium().navigateTo("Dashboard");
            new Navigator_appium().navigateTo("etextbook");
            new Navigator_appium().navigateTo("Course Stream");
            new Navigator_appium().navigateTo("My Notes");
            new Navigator_appium().navigateTo("Assignments");
            new Wait().visibilityOf(assignments.label_allAssignemnts);
            assignments.label_allAssignemnts.click();
            assignments.label_questionPractice.click();

            new Navigator_appium().navigateTo("Assignments");
            WebElement element = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'D1 - What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?']"));
            element.click();

        }catch(Exception e){
            Assert.fail("Exception in the testscript 'performActionsForNonGradableAssignments' in the class 'EndToEndFlow'",e);
        }
    }



    @Test(priority =4,enabled = true)
    public void performActionsForNonGradableCustomAssignments(){
        try{
            Assignments assignment = PageFactory.initElements(driver, Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Discussion discussion = PageFactory.initElements(driver, Discussion.class);

            String dataIndex = "40";


            WebDriverWait wait = new WebDriverWait(appiumDriver,20);
            ArrayList<String> instructorInfoList  = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            System.out.println("instructorInfoList.get(0) : " + instructorInfoList.get(0));
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            System.out.println("studentInfoList.get(0) : " + studentInfoList.get(0));


            ArrayList<String> assessmentInfoList  = new Assignment().create(dataIndex);

            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), "snapwiz", dataIndex);
            new Assignment().assignToStudent(dataIndex);



            new Navigator_appium().navigateTo("Dashboard");
            WebElement ele = appiumDriver.findElement(By.xpath("//UIALink[@name = 'Student, iPadIntegrationInstructor posted an assignment. - navigate to course stream page for details']"));
            Assert.assertEquals(ele.getText(), "Student, iPadIntegrationInstructor posted an assignment. - navigate to course stream page for details", "Assignment should be displayed on the dashboard ");

            new Navigator_appium().navigateTo("Course Stream");
            new Wait().presenceOfElementLocated(By.xpath("//UIAStaticText[@name = '(shor) " + assessmentInfoList.get(0) + "']"));
            ele = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(shor) "+assessmentInfoList.get(0)+"']"));
            ele.click();



            new Navigator_appium().navigateTo("Assignments");
            Thread.sleep(9000);
            ele = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(shor) "+assessmentInfoList.get(0)+"']"));
            ele.click();


            boolean tabDisplayed = appiumDriver.findElement(By.xpath("//UIAButton[@name = '"+assessmentInfoList.get(0)+"']")).isDisplayed();
            Assert.assertEquals(tabDisplayed, true, "The Assignment should be opened in a new tab");



            appiumDriver.findElement(By.xpath("//UIAStaticText[@name='A']")).click();//Click on True button
            eTextbook.button_finishAssignment.click();
            new Wait().visibilityOf(eTextbook.label_performance);
            Assert.assertEquals(eTextbook.label_performance.getText(),"Performance","The assignment should be finished and Performane Summary page should be displayed");



            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), "snapwiz", dataIndex);
            String feedback = new Assignment().provideFeedback(dataIndex);
            new Click().clickBycssselector("span[id='close-view-responses']");
            assignmentResponsesPage.getReleaseFeedbackForAll().click();



            //ArrayList<String> studentInfoLIst =new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Navigator_appium().navigateTo("Assignments");



            ele = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(shor) "+assessmentInfoList.get(0)+"']"));
            ele.click();
            Assert.assertEquals(eTextbook.label_performance.isDisplayed(), true, "The Assignment should be opened in a new tab and performance subtab should be selected by default");
            appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '"+assessmentInfoList.get(2)+"']")).click();

            String feedbackText = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '"+feedback+"']")).getText();

            Assert.assertEquals(feedbackText,feedback,"The entered teacher feedback should be displayed below");

        }catch(Exception e){
            Assert.fail("Exception in the testscript 'performActionsForNonGradableAssignments' in the class 'EndToEndFlow'",e);
        }
    }







    @Test(priority =5,enabled = true)
    public void performActionsForGradableCustomAssignments(){
        try{
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            String dataIndex = "91";
            ArrayList<String> instructorInfoList  = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            System.out.println("instructorInfoList.get(0) : " + instructorInfoList.get(0));
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            System.out.println("studentInfoList.get(0) : " + studentInfoList.get(0));


            ArrayList<String> assessmentInfoList  = new Assignment().create(dataIndex);

            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), "snapwiz", dataIndex);
            new Assignment().assignToStudent(dataIndex, "gradable");



            new Navigator_appium().navigateTo("Dashboard");
            WebElement ele = appiumDriver.findElement(By.xpath("//UIALink[@name = 'Student, iPadIntegrationInstructor posted an assignment. - navigate to course stream page for details']"));
            Assert.assertEquals(ele.getText(), "Student, iPadIntegrationInstructor posted an assignment. - navigate to course stream page for details", "Assignment should be displayed on the dashboard ");

            new Navigator_appium().navigateTo("Course Stream");
            new Wait().presenceOfElementLocated(By.xpath("//UIAStaticText[@name = '(shor) " + assessmentInfoList.get(0) + "']"));
            ele = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(shor) "+assessmentInfoList.get(0)+"']"));
            ele.click();



            new Navigator_appium().navigateTo("Assignments");
            Thread.sleep(9000);
            ele = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(shor) "+assessmentInfoList.get(0)+"']"));
            ele.click();


            boolean tabDisplayed = appiumDriver.findElement(By.xpath("//UIAButton[@name = '"+assessmentInfoList.get(0)+"']")).isDisplayed();
            Assert.assertEquals(tabDisplayed, true, "The Assignment should be opened in a new tab");



            appiumDriver.findElement(By.xpath("//UIAStaticText[@name='A']")).click();//Click on True button
            eTextbook.button_finishAssignment.click();
            new Wait().visibilityOf(eTextbook.label_performance);
            Assert.assertEquals(eTextbook.label_performance.getText(), "Performance", "The assignment should be finished and Performane Summary page should be displayed");



            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), "snapwiz", dataIndex);
            String feedback = new Assignment().provideFeedback(dataIndex);
            new Assignment().provideGRadeToStudent(dataIndex);
            //new Click().clickBycssselector("span[id='close-view-responses']");
            assignmentResponsesPage.getReleaseGradeForAll().click();



            //ArrayList<String> studentInfoLIst =new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Navigator_appium().navigateTo("Assignments");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '60%']")).getText().trim(), "60%");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(0.6/1)']")).getText().trim(), "(0.6/1)");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Uncategorized: 100%']")).getText().trim(), "Uncategorized: 100%");
            ele = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(shor) "+assessmentInfoList.get(0)+"']"));
            ele.click();
            Assert.assertEquals(eTextbook.label_performance.isDisplayed(), true, "The Assignment should be opened in a new tab and performance subtab should be selected by default");
            appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '"+assessmentInfoList.get(2)+"']")).click();
            new Wait().visibilityOf(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '" + feedback + "']")));
            String feedbackText = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '"+feedback+"']")).getText();
            Assert.assertEquals(feedbackText, feedback, "The entered teacher feedback should be displayed below");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(0.6/1.0)']")).getText().trim(), "(0.6/1.0)");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIALink[@name = 'Points, : 1']")).getText().trim(), "Points, : 1");

        }catch(Exception e){
            Assert.fail("Exception in the testscript 'performActionsForGradableCustomAssignments' in the class 'EndToEndFlow'",e);
        }

    }






    @Test(priority =6,enabled = true)
    public void performActionsForGradableCustomAssignmentsWithPolicies(){
        try{
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            String dataIndex = "118";
            String policyName = "policyName";
            ArrayList<String> instructorInfoList  = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            System.out.println("instructorInfoList.get(0) : " + instructorInfoList.get(0));
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            System.out.println("studentInfoList.get(0) : " + studentInfoList.get(0));


            ArrayList<String> assessmentInfoList  = new Assignment().create(dataIndex);

            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), "snapwiz", dataIndex);
            new Navigator().NavigateTo("Policies");

            new AssignmentPolicy().createAssignmentPolicy(policyName, "Policy Description text", "1", null, false, "1", "", null, "", "", "");
            new Assignment().assignToStudent(dataIndex, "gradable");

            new Navigator_appium().navigateTo("Dashboard");
            WebElement ele = appiumDriver.findElement(By.xpath("//UIALink[@name = 'Student, iPadIntegrationInstructor posted an assignment. - navigate to course stream page for details']"));
            Assert.assertEquals(ele.getText(), "Student, iPadIntegrationInstructor posted an assignment. - navigate to course stream page for details", "Assignment should be displayed on the dashboard ");

            new Navigator_appium().navigateTo("Course Stream");
            new Wait().presenceOfElementLocated(By.xpath("//UIAStaticText[@name = '(shor) " + assessmentInfoList.get(0) + "']"));
            ele = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(shor) "+assessmentInfoList.get(0)+"']"));
            ele.click();



            new Navigator_appium().navigateTo("Assignments");
            Thread.sleep(9000);
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Uncategorized: 100%']")).getText().trim(), "Uncategorized: 100%");

            ele = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(shor) "+assessmentInfoList.get(0)+"']"));
            ele.click();

            boolean tabDisplayed = appiumDriver.findElement(By.xpath("//UIAButton[@name = '"+assessmentInfoList.get(0)+"']")).isDisplayed();
            Assert.assertEquals(tabDisplayed, true, "The Assignment should be opened in a new tab");



            appiumDriver.findElement(By.xpath("//UIAStaticText[@name='A']")).click();//Click on True button
            eTextbook.button_finishAssignment.click();
            new Wait().visibilityOf(eTextbook.label_performance);
            Assert.assertEquals(eTextbook.label_performance.getText(), "Performance", "The assignment should be finished and Performane Summary page should be displayed");



            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), "snapwiz", dataIndex);
            String feedback = new Assignment().provideFeedback(dataIndex);
            new Assignment().provideGRadeToStudent(dataIndex);
            assignmentResponsesPage.getReleaseGradeForAll().click();



            //ArrayList<String> studentInfoLIst =new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            new Navigator_appium().navigateTo("Assignments");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '60%']")).getText().trim(), "60%");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(0.6/1)']")).getText().trim(), "(0.6/1)");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Uncategorized: 100%']")).getText().trim(), "Uncategorized: 100%");
            ele = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(shor) "+assessmentInfoList.get(0)+"']"));
            ele.click();
            Assert.assertEquals(eTextbook.label_performance.isDisplayed(), true, "The Assignment should be opened in a new tab and performance subtab should be selected by default");
            appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '"+assessmentInfoList.get(2)+"']")).click();
            new Wait().visibilityOf(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '" + feedback + "']")));
            String feedbackText = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '"+feedback+"']")).getText();
            Assert.assertEquals(feedbackText, feedback, "The entered teacher feedback should be displayed below");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(0.6/1.0)']")).getText().trim(), "(0.6/1.0)");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIALink[@name = 'Points, : 1']")).getText().trim(), "Points, : 1");

        }catch(Exception e){
            Assert.fail("Exception in the testscript 'performActionsForGradableCustomAssignments' in the class 'EndToEndFlow'",e);
        }

    }







    @Test(priority =7,enabled = true)
    public void performActionsForFileBasedNonGradableAssignments(){
        try{
            String dataIndex = "145";
            ArrayList<String> instructorInfoList  = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            System.out.println("instructorInfoList.get(0) : " + instructorInfoList.get(0));
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            System.out.println("studentInfoList.get(0) : " + studentInfoList.get(0));


            Assignments assignment = PageFactory.initElements(driver, Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(Driver.driver, Preview.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Discussion discussion = PageFactory.initElements(driver, Discussion.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);




            //Row No - 145 : Login to the application as a Instructor
            //Expected - The user should be successfully logged in to the application
            ArrayList<String> assessmentInfoList = new Assignment().createFileBasedAssessment(dataIndex);
            System.out.println("assessmentInfoList.get(0) : " + assessmentInfoList.get(0));




            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0),"snapwiz",dataIndex);


            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            System.out.println("Url : " + driver.getCurrentUrl());
            if (!driver.getCurrentUrl().contains("secure/learningSpaceInstructorDashboard")) {
                Assert.fail("The user should be successfully logged in to the application");
            }


            // :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();


            // Click on the Assignment option and Verify the submenus displayed towards the right
            /*Expected  - "The user should be able to see 4 different submenus i.e.
            1. New Assignment
            2. Current Assignments
            3. My Question Bank
            4. Policies"*/
            assignment.menu_Assignments.click();
            if(assignment.subMenu.isDisplayed()){
                String menuOption = assignment.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }



            // Click on the New Assignment option
            /*Expected  - "Create New Assignment pop-up window should be opened with three different types of Assignments i.e.
            1. Custom Assignment
            2. Use Pre Created Assignment
            3. File Based Assignment"*/

            assignment.subMenu_currentAssignments.click();
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            if (!currentAssignments.close_Icon.isDisplayed()) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");




            //: Click on Use Pre Created Assignment button on the pop -up
            //Expected - The user should be navigated to the Question Banks page
            currentAssignments.usePreCreatedAssignmentButton.click();//click on use pre create assignment
            Assert.assertEquals(currentAssignments.tab_Title.get(1).getAttribute("title"), "Question Banks", "The user is not navigated to the Question Banks page");



            // Verify the Created File Based Assignment
            //Expected - File Based Assessment created from the Author's side should be displayed
            new Assignment().searchAssessmentInQuestionBanks(dataIndex);
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "File Based Assessment created from the Author's side is not displayed");


            //Click on Assign this link under the Assignment Name
            //Expected - a dropdown should be displayed with the Assessment name
            new UIElement().waitAndFindElement(questionBank.getAssignThisButtton());
            questionBank.getAssignThisButtton().click();
            Thread.sleep(2000);
            Assert.assertEquals(newAssignment.popUP.getText(), assessmentInfoList.get(0), "Form should be displayed with the Assessment name");



            // Verify the fields displayed in the pop-up
            /*Expected - "All the fields i.e.
            Assignment Name
            Assign To
            Gradable checkbox
            Grading Policy
            Grading Policy Description
            Accessible After
            Due Date
            Description
            Assign Button
            Cancel Link"*/
            Assert.assertEquals(newAssignment.assignField.get(0).getText(), "Assign To: *", "Assign To field is not available");
            Assert.assertEquals(newAssignment.assignField.get(2).getText(), "Assignment Reference:", "Grading Policy field is not available");
            Assert.assertEquals(newAssignment.assignField.get(3).getText(), "Assignment Reference Description:", "Grading Policy Description field is not available");
            Assert.assertEquals(newAssignment.assignField.get(4).getText(), "Accessible After: *", "Accessible After field is not available");
            Assert.assertEquals(newAssignment.assignField.get(5).getText(), "Due Date: *", "Due Date Description field is not available");
            Assert.assertEquals(newAssignment.assignField.get(6).getText(), "Description:", "Description field is not available");
            Assert.assertEquals(newAssignment.button_assign.getText(), "Assign", "Assign field is not available");
            Assert.assertEquals(newAssignment.cancelPopUp.getText(), "Cancel", "Cancel field is not available");
            newAssignment.gradableCheckBox.click();//click on gradable check box
            Assert.assertEquals(newAssignment.assignField.get(2).getText(), "Total Points: *", "Total points field is not available");
            driver.findElement(By.id("total-points")).sendKeys("2");
            newAssignment.helpIconOFPoints.click();//click on help of total points
            Assert.assertEquals(newAssignment.helpMessage.getText(), "Enter the points available for the file type assignment.", "It is not displaying Enter the points available for the file type assignment.");


            //Verify for the Total Points textbox
            //Expected  - Total Points text box should not be displayed
            newAssignment.gradableCheckBox.click();//click on gradable check box
            if(driver.findElement(By.id("total-points")).isDisplayed()){
                Assert.fail("Total Points text box should not be displayed");
            }


            //Enter the Mandatory fields and Click on Assign button
            //Expected - The Assessment should be assigned to all the Assigned To members
            Driver.driver.findElement(By.xpath("//html/body")).click();
            questionBank.getAssignThisButtton().click();
            new UIElement().waitAndFindElement(By.id("due-date"));
            driver.findElement(By.id("due-date")).click();//click on due date
            driver.findElement(By.linkText("28")).click();
            driver.findElement(By.id("due-time")).click();//click on due time
            driver.findElement(By.xpath("(//ul[@class='ui-timepicker-list']/li)[5]")).click();
            driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            newAssignment.button_assign.click();
            Thread.sleep(4000);
            Assert.assertEquals(currentAssignments.tab_Title.get(0).getText(), "Current Assignments", "The user is not redirected to Current Assignments page");
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "The Assessment is not Assigned To all the members");
            Assert.assertEquals(currentAssignments.getAssignmentName().getText().trim(), "(shor) "+assessmentInfoList.get(0)+"", "The Assignment name is not listed on the Assignment page");


            //Click on the Main navigation on top left hand corner of the screen
            //The user should get the main navigation dropdown displayed
            validateMainNavigator();



            //Click on Assignments option for the dropdown
            //Expected  - The submenus should be open
            assignment.menu_Assignments.click();
            if(assignment.subMenu.isDisplayed()){
                String menuOption = assignment.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }


            // Click on Current Assignments option
            //Expected - The File Based assignment should be displayed at the top with its mandatory details
            Assert.assertEquals(currentAssignments.tab_Title.get(0).getText(), "Current Assignments", "The user is not redirected to Current Assignments page");
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "The Assessment is not Assigned To all the members");
            Assert.assertEquals(currentAssignments.getAssignmentName().getText().trim(), "(shor) "+assessmentInfoList.get(0)+"", "The Assignment name is not listed on the Assignment page");



            // Verify for the details displayed
            //Expected - All the attached files should be displayed and should have a link
            //repeated


            // Click on the link displayed under each File Name
            //Expected - The browser pop up to download the attachment should be opened
            currentAssignments.uploadedResources.click();
            Thread.sleep(15000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);



            //Row No - 163 : Login to the application as a student
            //Expected - The user should be successfully logged in to the application
            new Navigator_appium().navigateTo("Assignments");
            WebElement element = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(shor) " + assessmentInfoList.get(0) + "']"));
            element.click();


            new Wait().visibilityOf(assignments.link_uploadFiles);
            //assignment.link_uploadFiles.click();
            String studentResponse = "This is Student Response";
            assignments.label_tapToEnterResponse.click();
            Thread.sleep(2000);
            new Wait().visibilityOf(assignments.textField_edit);
            assignments.textField_edit.sendKeys(studentResponse);
            appiumDriver.hideKeyboard();
            eTextbook.button_finishAssignment.click();
            Thread.sleep(2000);
            new Wait().visibilityOf(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '"+studentResponse+"']")));
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '"+studentResponse+"']")).getText(),studentResponse,"The Student response should be displayed in Your Response section.");




            //Row No - 342 : Login to the application as a Instructor/ Mentor
            //Expected - The user should be successfully logged in to the application


            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0),"snapwiz",dataIndex);

            if (!driver.getCurrentUrl().contains("secure/learningSpaceInstructorDashboard")) {
                Assert.fail("The user should be successfully logged in to the application");
            }


            //Row No - 343 : Click on the Main navigation on top left hand corner of the screen
            //Expected - The Navigation List should be displayed to the User
            validateMainNavigator();



            //Row No - 344 : Click on Assignments option
            //Expected -  The sub menu should be displayed to the user
            assignment.menu_Assignments.click();
            if(assignment.subMenu.isDisplayed()){
                String menuOption = assignment.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }


            //Row No - 345 : Click on Current Assignments option
            //Expected - Current Assignment page should be displayed to the user
            assignment.subMenu_currentAssignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Chapters"));
            if (!driver.getCurrentUrl().endsWith("/assignment")) {
                Assert.fail("Current assignments page should be opened");
            }
            Assert.assertEquals(currentAssignments.tab_Title.get(0).getText(), "Current Assignments", "The user is not redirected to Current Assignments page");





            //Row No - 346 : Verify for the File Based Assessment Name
            //Expected - File Based Assessment Should be displayed
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "File Based Assessment created from the Author's side is not displayed");
            Assert.assertEquals(currentAssignments.getAssignmentName().getText().trim(), "(shor) "+assessmentInfoList.get(0)+"", "The Assignment name is not listed on the Assignment page");




            //Row No - 347 : Click on View Student Responses link under Actions
            //Expected - The Assignment Response page should be opened as a new tab next to current assignment tab
            //Expected -View Response page should be opened in a new tab
            currentAssignments.getViewGrade_link().click();
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            Thread.sleep(5000);
            System.out.println("currentAssignments.tab_Title.size() : " + currentAssignments.tab_Title.size());
            System.out.println("currentAssignments.tab_Title.get(1).getText() : " + currentAssignments.tab_Title.get(1).getText());

            if(!(currentAssignments.tab_Title.size()==2 && currentAssignments.tab_Title.get(1).getText().contains("Response - (shor) "+assessmentInfoList.get(0)))){
                Assert.fail("View Response page is not opened in a new tab");
            }






            //Row No - 349 : Hover the mouse on the '-' for a particular student
            //Expected - a link for View Response should be displayed based on hovering the mouse
            MouseHover.mouserhover("idb-gradebook-question-content");
            new UIElement().waitAndFindElement(By.className("ls-view-response-link"));
            if(!assignmentResponsesPage.getViewResponseLink().isDisplayed()){
                Assert.fail("View Response Link should be displayed based on hovering the mouse");
            }



            //Row No - 350 - Click on View Response link
            //Expected - The user should be navigated to the Student response page in a new tab
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-view-response-link")));
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            Thread.sleep(3000);
            if(!(currentAssignments.tab_Title.size()==3 && currentAssignments.tab_Title.get(2).getText().trim().contains("Response - (shor) "+assessmentInfoList.get(0)+""))){
                Assert.fail("The user is not navigated to the Student response page in a new tab");
            }




            //Row No - 351 : Click on Teacher feedback text box and type the feedback
            //Expected - The Feedback should be displayed in the textbox
            //Row No - 354 : Click on save button
            //Expected - The typed text should be saved and Saved Successfully message should be displayed below the textbox
            String feedback = new RandomString().randomstring(10);
            assignmentResponsesPage.getFeedbackTextArea().clear();
            assignmentResponsesPage.getFeedbackTextArea().click();
            assignmentResponsesPage.getFeedbackTextArea().sendKeys(feedback);
            Thread.sleep(2000);
            assignmentResponsesPage.getSaveButton().click();
            Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(),"Saved successfully.","The entered text is not saved");




            //Row No - 355 : Navigate to View response page and click on Release Feedback for All button
            /*Expected - "The button label should be changed to Feedback Released and the button should be greyed out.
            The Class Status on the page should be changed to Reviewed from Review in Progress"*/
            assignmentTab.cancelMark.click();
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            assignmentResponsesPage.getReleaseFeedbackForAll().click();
            new UIElement().waitAndFindElement(assignmentResponsesPage.feedback_Box);
            Thread.sleep(3000);
            if(!assignmentResponsesPage.feedback_Box.getAttribute("title").equals("Feedback Released")){
                Assert.fail("The Grade is not released and the button is not disabled");
            }
            Assert.assertEquals(assignmentResponsesPage.getReviewStatus().getText().trim(),"Reviewed","The Class Status on the page is not changed to Reviewed from Review in Progress");







            /*Login to the application as a student
            Click on the Main Navigator on top left hand corner of the screen
            Click on Assignments option for the dropdown
            Click on the Assignment name
            Click on Student's Response area
            Click on Teacher's Feedback area*/


            /*EXPECTED- The user should be successfully logged in to the application
            The Navigation List should be displayed to the User
            The User should be navigated to the Assignments page
            "The User should be navigated to the performance tab with the Assignment details and Teacher's Feedbacks
            Status should be ""Reviewed"""
            Student's Response area should not be editable
            Teacher's Feedback area should not be editable*/


            new Navigator_appium().navigateTo("Assignments");
            element = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(shor) "+assessmentInfoList.get(0)+"']"));
            element.click();
            Thread.sleep(3000);

            //Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '"+assessmentInfoList.get(0)+"']")).getText(),assessmentInfoList.get(0),"The User should be navigated to the performance tab with the Assignment details and Teacher's Feedbacks .Status should be Reviewed");


            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Assignments']")).isDisplayed(),true,"The User should be navigated to the Assignemnt tab with the Assignment details and Teacher's Feedbacks Status should be Reviewed");

            // assignments.label_reviewedThisAssignemnt.

            Assert.assertEquals(assignments.label_reviewedThisAssignment.getText(), "Reviewed this Assignment.", "\"The User should be navigated to the performance tab with the Assignment details and Teacher's Feedbacks\n" +
                    "Status should be \"\"Reviewed\"\"\"");

            try{
                assignments.label_thisIsStudentResponse.clear();
                Assert.fail("Student's Response area should not be editable");
            }catch(Exception e){

            }

            try{
                appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '"+feedback+"")).clear();
                Assert.fail("Teacher's Feedback area should not be editable");
            }catch(Exception e){

            }

        }catch(Exception e){
            Assert.fail("Exception in the testscript 'performActionsForFileBasedNonGradableAssignments' in the class 'EndToEndFlow'",e);
        }

    }






    @Test(priority =8,enabled = true)
    public void performActionsForFileBasedGradableAssignments(){
        try{
            String dataIndex = "194";
            ArrayList<String> instructorInfoList  = new LoginUsingRandomUser_appium().loginAsRandomInstructor(dataIndex);
            System.out.println("instructorInfoList.get(0) : " + instructorInfoList.get(0));
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent(dataIndex);
            System.out.println("studentInfoList.get(0) : " + studentInfoList.get(0));


            Assignments assignment = PageFactory.initElements(driver, Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(Driver.driver, Preview.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Discussion discussion = PageFactory.initElements(driver, Discussion.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);




            //Row No - 145 : Login to the application as a Instructor
            //Expected - The user should be successfully logged in to the application
            ArrayList<String> assessmentInfoList = new Assignment().createFileBasedAssessment(dataIndex);
            System.out.println("assessmentInfoList.get(0) : " + assessmentInfoList.get(0));




            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0),"snapwiz",dataIndex);


            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            System.out.println("Url : " + driver.getCurrentUrl());
            if (!driver.getCurrentUrl().contains("secure/learningSpaceInstructorDashboard")) {
                Assert.fail("The user should be successfully logged in to the application");
            }


            // :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();


            // Click on the Assignment option and Verify the submenus displayed towards the right
            /*Expected  - "The user should be able to see 4 different submenus i.e.
            1. New Assignment
            2. Current Assignments
            3. My Question Bank
            4. Policies"*/
            assignment.menu_Assignments.click();
            if(assignment.subMenu.isDisplayed()){
                String menuOption = assignment.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }



            // Click on the New Assignment option
            /*Expected  - "Create New Assignment pop-up window should be opened with three different types of Assignments i.e.
            1. Custom Assignment
            2. Use Pre Created Assignment
            3. File Based Assignment"*/

            assignment.subMenu_currentAssignments.click();
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            if (!currentAssignments.close_Icon.isDisplayed()) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");




            //: Click on Use Pre Created Assignment button on the pop -up
            //Expected - The user should be navigated to the Question Banks page
            currentAssignments.usePreCreatedAssignmentButton.click();//click on use pre create assignment
            Assert.assertEquals(currentAssignments.tab_Title.get(1).getAttribute("title"), "Question Banks", "The user is not navigated to the Question Banks page");



            // Verify the Created File Based Assignment
            //Expected - File Based Assessment created from the Author's side should be displayed
            new Assignment().searchAssessmentInQuestionBanks(dataIndex);
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "File Based Assessment created from the Author's side is not displayed");


            //Click on Assign this link under the Assignment Name
            //Expected - a dropdown should be displayed with the Assessment name
            new UIElement().waitAndFindElement(questionBank.getAssignThisButtton());
            questionBank.getAssignThisButtton().click();
            Thread.sleep(2000);
            Assert.assertEquals(newAssignment.popUP.getText(), assessmentInfoList.get(0), "Form should be displayed with the Assessment name");



            // Verify the fields displayed in the pop-up
            /*Expected - "All the fields i.e.
            Assignment Name
            Assign To
            Gradable checkbox
            Grading Policy
            Grading Policy Description
            Accessible After
            Due Date
            Description
            Assign Button
            Cancel Link"*/
            Assert.assertEquals(newAssignment.assignField.get(0).getText(), "Assign To: *", "Assign To field is not available");
            Assert.assertEquals(newAssignment.assignField.get(2).getText(), "Assignment Reference:", "Grading Policy field is not available");
            Assert.assertEquals(newAssignment.assignField.get(3).getText(), "Assignment Reference Description:", "Grading Policy Description field is not available");
            Assert.assertEquals(newAssignment.assignField.get(4).getText(), "Accessible After: *", "Accessible After field is not available");
            Assert.assertEquals(newAssignment.assignField.get(5).getText(), "Due Date: *", "Due Date Description field is not available");
            Assert.assertEquals(newAssignment.assignField.get(6).getText(), "Description:", "Description field is not available");
            Assert.assertEquals(newAssignment.button_assign.getText(), "Assign", "Assign field is not available");
            Assert.assertEquals(newAssignment.cancelPopUp.getText(), "Cancel", "Cancel field is not available");
            newAssignment.gradableCheckBox.click();//click on gradable check box
            Assert.assertEquals(newAssignment.assignField.get(2).getText(), "Total Points: *", "Total points field is not available");
            driver.findElement(By.id("total-points")).sendKeys("2");
            newAssignment.helpIconOFPoints.click();//click on help of total points
            Assert.assertEquals(newAssignment.helpMessage.getText(), "Enter the points available for the file type assignment.", "It is not displaying Enter the points available for the file type assignment.");





            //Enter the Mandatory fields and Click on Assign button
            //Expected - The Assessment should be assigned to all the Assigned To members
            Driver.driver.findElement(By.xpath("//html/body")).click();
            questionBank.getAssignThisButtton().click();
            new UIElement().waitAndFindElement(By.id("due-date"));
            driver.findElement(By.id("due-date")).click();//click on due date
            driver.findElement(By.linkText("28")).click();
            driver.findElement(By.id("due-time")).click();//click on due time
            driver.findElement(By.xpath("(//ul[@class='ui-timepicker-list']/li)[5]")).click();
            driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
            newAssignment.button_assign.click();
            Thread.sleep(4000);
            Assert.assertEquals(currentAssignments.tab_Title.get(0).getText(), "Current Assignments", "The user is not redirected to Current Assignments page");
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "The Assessment is not Assigned To all the members");
            Assert.assertEquals(currentAssignments.getAssignmentName().getText().trim(), "(shor) "+assessmentInfoList.get(0)+"", "The Assignment name is not listed on the Assignment page");


            //Click on the Main navigation on top left hand corner of the screen
            //The user should get the main navigation dropdown displayed
            validateMainNavigator();



            //Click on Assignments option for the dropdown
            //Expected  - The submenus should be open
            assignment.menu_Assignments.click();
            if(assignment.subMenu.isDisplayed()){
                String menuOption = assignment.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }


            // Click on Current Assignments option
            //Expected - The File Based assignment should be displayed at the top with its mandatory details
            Assert.assertEquals(currentAssignments.tab_Title.get(0).getText(), "Current Assignments", "The user is not redirected to Current Assignments page");
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "The Assessment is not Assigned To all the members");
            Assert.assertEquals(currentAssignments.getAssignmentName().getText().trim(), "(shor) "+assessmentInfoList.get(0)+"", "The Assignment name is not listed on the Assignment page");



            // Verify for the details displayed
            //Expected - All the attached files should be displayed and should have a link
            //repeated


            // Click on the link displayed under each File Name
            //Expected - The browser pop up to download the attachment should be opened
            currentAssignments.uploadedResources.click();
            Thread.sleep(15000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);



            //Row No - 163 : Login to the application as a student
            //Expected - The user should be successfully logged in to the application
            new Navigator_appium().navigateTo("Assignments");
            WebElement element = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(shor) " + assessmentInfoList.get(0) + "']"));
            element.click();


            new Wait().visibilityOf(assignments.link_uploadFiles);
            //assignment.link_uploadFiles.click();
            String studentResponse = "This is Student Response";
            assignments.label_tapToEnterResponse.click();
            Thread.sleep(2000);
            new Wait().visibilityOf(assignments.textField_edit);
            assignments.textField_edit.sendKeys(studentResponse);
            appiumDriver.hideKeyboard();
            eTextbook.button_finishAssignment.click();
            Thread.sleep(2000);
            new Wait().visibilityOf(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '"+studentResponse+"']")));
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '"+studentResponse+"']")).getText(),studentResponse,"The Student response should be displayed in Your Response section.");




            //Row No - 342 : Login to the application as a Instructor/ Mentor
            //Expected - The user should be successfully logged in to the application


            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0),"snapwiz",dataIndex);

            if (!driver.getCurrentUrl().contains("secure/learningSpaceInstructorDashboard")) {
                Assert.fail("The user should be successfully logged in to the application");
            }


            //Row No - 343 : Click on the Main navigation on top left hand corner of the screen
            //Expected - The Navigation List should be displayed to the User
            validateMainNavigator();



            //Row No - 344 : Click on Assignments option
            //Expected -  The sub menu should be displayed to the user
            assignment.menu_Assignments.click();
            if(assignment.subMenu.isDisplayed()){
                String menuOption = assignment.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }


            //Row No - 345 : Click on Current Assignments option
            //Expected - Current Assignment page should be displayed to the user
            assignment.subMenu_currentAssignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Chapters"));
            if (!driver.getCurrentUrl().endsWith("/assignment")) {
                Assert.fail("Current assignments page should be opened");
            }
            Assert.assertEquals(currentAssignments.tab_Title.get(0).getText(), "Current Assignments", "The user is not redirected to Current Assignments page");





            //Row No - 346 : Verify for the File Based Assessment Name
            //Expected - File Based Assessment Should be displayed
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "File Based Assessment created from the Author's side is not displayed");
            Assert.assertEquals(currentAssignments.getAssignmentName().getText().trim(), "(shor) "+assessmentInfoList.get(0)+"", "The Assignment name is not listed on the Assignment page");




            //Row No - 347 : Click on View Student Responses link under Actions
            //Expected - The Assignment Response page should be opened as a new tab next to current assignment tab
            //Expected -View Response page should be opened in a new tab
            currentAssignments.getViewGrade_link().click();
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            Thread.sleep(5000);
            System.out.println("currentAssignments.tab_Title.size() : " + currentAssignments.tab_Title.size());
            System.out.println("currentAssignments.tab_Title.get(1).getText() : " + currentAssignments.tab_Title.get(1).getText());

            if(!(currentAssignments.tab_Title.size()==2 && currentAssignments.tab_Title.get(1).getText().contains("Response - (shor) "+assessmentInfoList.get(0)))){
                Assert.fail("View Response page is not opened in a new tab");
            }






            //Row No - 349 : Hover the mouse on the '-' for a particular student
            //Expected - a link for View Response should be displayed based on hovering the mouse
            MouseHover.mouserhover("idb-gradebook-question-content");
            new UIElement().waitAndFindElement(By.className("ls-view-response-link"));
            if(!assignmentResponsesPage.getViewResponseLink().isDisplayed()){
                Assert.fail("View Response Link should be displayed based on hovering the mouse");
            }



            //Row No - 350 - Click on View Response link
            //Expected - The user should be navigated to the Student response page in a new tab
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-view-response-link")));
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            Thread.sleep(3000);
            if(!(currentAssignments.tab_Title.size()==3 && currentAssignments.tab_Title.get(2).getText().trim().contains("Response - (shor) "+assessmentInfoList.get(0)+""))){
                Assert.fail("The user is not navigated to the Student response page in a new tab");
            }



            //Row No - 351 : Click on Teacher feedback text box and type the feedback
            //Expected - The Feedback should be displayed in the textbox
            //Row No - 354 : Click on save button
            //Expected - The typed text should be saved and Saved Successfully message should be displayed below the textbox
            String feedback = new RandomString().randomstring(10);
            assignmentResponsesPage.getFeedbackTextArea().clear();
            assignmentResponsesPage.getFeedbackTextArea().click();
            assignmentResponsesPage.getFeedbackTextArea().sendKeys(feedback);
            Thread.sleep(2000);
            assignmentResponsesPage.getSaveButton().click();
            Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(),"Saved successfully.","The entered text is not saved");




            //Row No - 355 : Navigate to View response page and click on Release Feedback for All button
            /*Expected - "The button label should be changed to Feedback Released and the button should be greyed out.
            The Class Status on the page should be changed to Reviewed from Review in Progress"*/
            assignmentTab.cancelMark.click();
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            assignmentResponsesPage.getReleaseFeedbackForAll().click();
            new UIElement().waitAndFindElement(assignmentResponsesPage.feedback_Box);
            Thread.sleep(3000);
            if(!assignmentResponsesPage.feedback_Box.getAttribute("title").equals("Feedback Released")){
                Assert.fail("The Grade is not released and the button is not disabled");
            }
            Assert.assertEquals(assignmentResponsesPage.getReviewStatus().getText().trim(),"Reviewed","The Class Status on the page is not changed to Reviewed from Review in Progress");







            /*Login to the application as a student
            Click on the Main Navigator on top left hand corner of the screen
            Click on Assignments option for the dropdown
            Click on the Assignment name
            Click on Student's Response area
            Click on Teacher's Feedback area*/


            /*EXPECTED- The user should be successfully logged in to the application
            The Navigation List should be displayed to the User
            The User should be navigated to the Assignments page
            "The User should be navigated to the performance tab with the Assignment details and Teacher's Feedbacks
            Status should be ""Reviewed"""
            Student's Response area should not be editable
            Teacher's Feedback area should not be editable*/


            new Navigator_appium().navigateTo("Assignments");
            element = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(shor) "+assessmentInfoList.get(0)+"']"));
            element.click();
            Thread.sleep(3000);

            //Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '"+assessmentInfoList.get(0)+"']")).getText(),assessmentInfoList.get(0),"The User should be navigated to the performance tab with the Assignment details and Teacher's Feedbacks .Status should be Reviewed");


            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Assignments']")).isDisplayed(),true,"The User should be navigated to the Assignemnt tab with the Assignment details and Teacher's Feedbacks Status should be Reviewed");

            // assignments.label_reviewedThisAssignemnt.

            Assert.assertEquals(assignments.label_reviewedThisAssignment.getText(), "Reviewed this Assignment.", "\"The User should be navigated to the performance tab with the Assignment details and Teacher's Feedbacks\n" +
                    "Status should be \"\"Reviewed\"\"\"");

            try{
                assignments.label_thisIsStudentResponse.clear();
                Assert.fail("Student's Response area should not be editable");
            }catch(Exception e){
                System.out.println("e.getMessage()1;" + e.getMessage());

            }

            try{
                appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '"+feedback+"")).clear();
                Assert.fail("Teacher's Feedback area should not be editable");
            }catch(Exception e){
                System.out.println("e.getMessage ()2;" + e.getMessage());

            }

        }catch(Exception e){
            Assert.fail("Exception in the testscript 'performActionsForFileBasedNonGradableAssignments' in the class 'EndToEndFlow'",e);
        }

    }




    private void validateMainNavigator() {
        Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
        try {
            dashboard.getMainNavigator().click();
            new UIElement().waitAndFindElement(By.className("ls--dashboard"));
            if (!dashboard.mainNavigatorList.isDisplayed()) {
                Assert.fail("The Navigation List is not displayed to the User");
            }
        } catch (Exception e) {
            Assert.fail("Exception in the method 'validateMainNavigator' in the class 'EndToEndAssignments'",e);
        }

    }

    public void navigateTo(String navigateTo){
        try{
            Thread.sleep(15000);
            new Tap().tapMainNavigator();
            Thread.sleep(2000);

            if(!dashBoard.link_dashBoard.isDisplayed()&&dashBoard.link_eTextBook.isDisplayed()&&dashBoard.link_assignments.isDisplayed()&&dashBoard.link_courseStream.isDisplayed()&&dashBoard.link_myNotes.isDisplayed()){
                Assert.fail("The Navigation List should be displayed to the User");
            }

            if(navigateTo.equalsIgnoreCase("Dashboard")){
                dashBoard.link_dashBoard.click();
                new Wait().visibilityOf(dashBoard.link_study);
                if(!dashBoard.link_study.isDisplayed()){
                    Assert.fail("Failure in Dashboard Navigation");
                }
            }else if(navigateTo.equalsIgnoreCase("e-TextBook")||navigateTo.equalsIgnoreCase("e_TextBook")||navigateTo.equalsIgnoreCase("eTextBook")){
                Thread.sleep(15000);
                dashBoard.link_eTextBook.click();
                Thread.sleep(2000);
                new Wait().visibilityOf(dashBoard.textField_searchETextBook);

                if(!dashBoard.textField_searchETextBook.isDisplayed()){
                    Assert.fail("Failure in e-TextBook Navigation");
                }
            } else if(navigateTo.equalsIgnoreCase("Assignments")){
                dashBoard.link_assignments.click();
                new Wait().visibilityOf(dashBoard.link_allAsignments);
                if(!dashBoard.link_allAsignments.isDisplayed()){
                    Assert.fail("Assignment page should be displayed to the user");
                }
            }else if(navigateTo.equalsIgnoreCase("Course Stream")||navigateTo.equalsIgnoreCase("CourseStream")){
                dashBoard.link_courseStream.click();
                Thread.sleep(5000);
                /*if(!dashBoard.link_ShareANew.isDisplayed()){
                    Assert.fail("Failure in Course Stream Navigation");
                }*/
            }else if(navigateTo.equalsIgnoreCase("My Notes")||navigateTo.equalsIgnoreCase("MyNotes")){
                dashBoard.link_myNotes.click();
                new Wait().visibilityOf(dashBoard.text_Note);
                if(!dashBoard.text_Note.isDisplayed()){
                    Assert.fail("Failure in My Notes Navigation");
                }
            }
            Thread.sleep(1000);
        }catch(Exception e){
            Assert.fail("Exception in the method 'navigateTo' in the class 'Navigator_appium'",e);
        }
    }
}
