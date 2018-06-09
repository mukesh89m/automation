package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT22.R229;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.PerformanceSummaryReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

/*
 * Created by Dharaneesha on 7/1/15.
 */
public class EndToEndAssignments extends Driver {
    private final String  notificationMessage = "Your file upload request is being processed...";
    @Test(priority = 1, enabled = true)
    public void userToBeAbleToPerformActionsForNonGradableAssignments() {
        try {

            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Discussion discussion = PageFactory.initElements(driver, Discussion.class);

            int dataIndex = 132;
            String widgetText = "(shor) What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?";
            String feedback = null;

            //Row nO - 132 : Login to the application as a Instructor
            //Expected - The user should be successfully logged in to the application
            new LoginUsingLTI().ltiLogin("132");//Log in as an instructor
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            System.out.println("Url : " + driver.getCurrentUrl());
            if (!driver.getCurrentUrl().contains("secure/learningSpaceInstructorDashboard")) {
                Assert.fail("The user should be successfully logged in to the application");
            }


            //Row No - 133 : Assign Non Gradable Widget(Discussion/Audio/Video)
            //Expected  - The Non Gradable Widget should be assigned to the students on the class


            new Navigator().NavigateTo("e-Textbook");//Click on 'e-Textbook'
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            Thread.sleep(5000);
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(dataIndex);
            Assert.assertEquals(assignments.resourceLink.getText(),widgetText,"The Non Gradable Widget is not assigned to the students on the class");


            //Row N0 134 : Login to the application as a student
            //Expected -  The user should be successfully logged in to the application
            new LoginUsingLTI().ltiLogin("132_2");//Log in as a student
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            System.out.println("Url : " + driver.getCurrentUrl());
            if (!driver.getCurrentUrl().contains("/lsStudentDashBoard")) {
                Assert.fail("The user is not successfully logged in to the application");
            }


            //Row No - 135 : Verify the visibility of Assignment on Dashboard
            //Expected - Assigned Widget should be displayed on the dashboard
            Thread.sleep(2000);
            Assert.assertEquals(dashboard.postedAssignmentText.getText(), widgetText, "Assigned Widget is not displayed on the dashboard");

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


            //Row No - 138 :Verify the visibility of Assignment on Course Stream
            //Expected -Assigned Widget should be displayed on the Course stream as card view
            //Assert.assertEquals(courseStreamPage.assignmentLink.getText(),widgetText,"Assigned Widget should be displayed on the Course stream as card view");
            if (!courseStreamPage.assignmentLink.getText().contains(widgetText.substring(8))) {
                Assert.fail("Assigned Widget should be displayed on the Course stream as card view");
            }


            //Row No - 139 :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();



            //Row No - 140 : Click on Assignments option
            //Expected -Assignment page should be displayed to the user
            assignments.menu_Assignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("Assignment page should be displayed to the user");
            }


            //Row No - 141 :Verify the visibility of Assignment on Assignments page
            //Expected -Assigned Widget should be displayed on the Assignments page as card view
            if (!currentAssignments.getLessonAssignment().getText().contains(widgetText.substring(8))) {
                Assert.fail("Assigned Widget should be displayed on the Assignments page as card view");
            }


            //Row No - 142 :Click on the Assignment
            //Expected -The Assignment should be opened in a new tab
            currentAssignments.getLessonAssignment().click();
            new UIElement().waitAndFindElement(By.partialLinkText("Perspectives"));
            discussion.getPerspectives().click();
            discussion.getLink_enterSubmission().click();
            if (!discussion.getTab_discussion().isDisplayed()) {
                Assert.fail("The Assignment is not opened in a new tab");
            }


            //Row No - 143 :Finish the Assignment and Submit
            //Expected -The assignment should be finished and assignments page screen should be displayed
            String perspectiveText = new RandomString().randomstring(10);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspectiveText);
            Assert.assertEquals(currentAssignments.getDwComment().getText(), perspectiveText, "The assignment is not finished and assignments page screen is not displayed");


            //Row No - 144 :Navigate to the Instructor Login and click on the main navigation
            //Expected -The Navigation List should be displayed to the User
            new LoginUsingLTI().ltiLogin("132");//Log in as an instructor
            validateMainNavigator();

            //Row No - 145 : Click on Assignments option
            //Expected -The sub menus should be displayed
            assignments.menu_Assignments.click();
            if(assignments.subMenu.isDisplayed()){
                String menuOption = assignments.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }


            //Row No - 146 :Click on Current Assignments submenu
            //Expected -Current assignments page should be opened
            assignments.subMenu_currentAssignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Chapters"));
            if (!driver.getCurrentUrl().endsWith("/assignment")) {
                Assert.fail("Current assignments page should be opened");
            }


            //Row No - 147 :Click on View response link of the Assignment
            //Expected -View Response page should be opened
            currentAssignments.getViewGrade_link().click();
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            Thread.sleep(3000);
            System.out.println("currentAssignments.tab_Title.get(1).getText() : " + currentAssignments.tab_Title.get(1).getText());
            if(!currentAssignments.tab_Title.get(1).getText().contains("Response - "+widgetText)){
                Assert.fail("View Response page is not opened");
            }



            //Row No - 148 :Click on View response link of the Question Number
            //Expected -View Response page should be opened in a new tab
            new ResponsePage().openViewResponsePage();
            Thread.sleep(5000);
            if(!(currentAssignments.tab_Title.size()==3 && currentAssignments.tab_Title.get(2).getText().contains("Response - "+widgetText))){
                Assert.fail("View Response page is not opened in a new tab");
            }




            //Row No - 149 : Click on Teacher's feedback textbox
            //Expected - The entered text should be displayed in the textbox
            //Doubt



            //Row No - 150 :Click on Save button
            //Expected -The entered text should be saved
            assignmentResponsesPage.getFeedbackTextArea().click();
            feedback = new RandomString().randomstring(10);
            assignmentResponsesPage.getFeedbackTextArea().sendKeys(feedback);
            currentAssignments.getDwSave_button().click();
            Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(),"Saved successfully.","The entered text is not saved");


            //Row No - 151 :Close the tab
            //Expected -The tab should be closed
            assignmentResponsesPage.closeIcon_dwResponsePage.click();
            if(!(currentAssignments.tab_Title.size()<=2 && currentAssignments.tab_Title.get(1).getText().contains("Response - "+widgetText))){
                Assert.fail("The tab should is not closed");
            }



            //Row No - 153 :Navigate to the Student Login and click on the main navigation
            //Expected -The Navigation List should be displayed to the User
            new LoginUsingLTI().ltiLogin("132_2");//Log in as a student
            validateMainNavigator();



            //Row No - 154 : Click on Assignments option
            //Expected -Assignment page should be displayed to the user
            assignments.menu_Assignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("Assignment page should be displayed to the user");
            }



            //Row No - 155 :Click on the Assignment for which the feedback was released
            //Expected -The Assignment should be opened in a new tab and performance subtab should be selected by default
            new DiscussionWidget().openAddedPerspectiveForDWAssignment(dataIndex);
            System.out.println("currentAssignments.tab_Title.size() : " + currentAssignments.tab_Title.size());
            System.out.println("currentAssignments.tab_Title.get(1).getText() : " + currentAssignments.tab_Title.get(1).getText());

            for(int a=0;a<currentAssignments.tab_Title.size();a++){
                System.out.println("a : " + a);
                String b = currentAssignments.tab_Title.get(a).getText();
                System.out.println("B : " + b);
            }
            if(!(currentAssignments.tab_Title.size()<=8 && currentAssignments.tab_Title.get(3).getText().contains("Discussion"))){
                Assert.fail("The Assignment is not opened in a new tab ");
            }


            //Row No - 156 :Verify the teacher feedback
            //Expected -The entered teacher feedback should be displayed below
            Assert.assertEquals(discussion.getInsFeedback().getText(),feedback,"The entered teacher feedback should be displayed below");


        } catch (Exception e) {
            Assert.fail("Exception in test script 'userToBeAbleToPerformActionsForNonGradableAssignments' in the class 'EndToEndAssignments'", e);
        }
    }




    @Test(priority = 2, enabled = true)
    public void userToBeAbleToPerformActionsForNonGradableCustomAssignments() {
        try {

            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            PerformanceSummaryReport performanceSummaryReport = PageFactory.initElements(driver, PerformanceSummaryReport.class);

            int dataIndex = 157;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(dataIndex));

            String widgetText = "(shor) What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?";
            String feedback = null;

            //Row No - 157 : Login to the application as a Instructor
            //Expected - The user should be successfully logged in to the application
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex,"multiplechoice","");
            new LoginUsingLTI().ltiLogin("157");//Log in as an instructor
            new LoginUsingLTI().ltiLogin("157_2");//Log in as a student


            new LoginUsingLTI().ltiLogin("157");//Log in as an instructor
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            if (!driver.getCurrentUrl().contains("secure/learningSpaceInstructorDashboard")) {
                Assert.fail("The user should be successfully logged in to the application");
            }


            //Row No - 158 : Assign a Custom Assignment
            //Expected  - The Non Gradable Custom Assignment should be assigned to the students on the class
            new CreateCustomAssignment().createCustomAssignment(questiontext,"157");
            new Assignment().assignFileBasedAssignmentFromMyQuestionBank(dataIndex);
            Assert.assertEquals(assignments.getAssignmentName().getText(),"(shor) "+customAssignmentName+"","The Non Gradable Widget is not assigned to the students on the class");


            //Row N0 159 : Login to the application as a student
            //Expected -  The user should be successfully logged in to the application
            new LoginUsingLTI().ltiLogin("157_2");//Log in as a student
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            if (!driver.getCurrentUrl().contains("/lsStudentDashBoard")) {
                Assert.fail("The user is not successfully logged in to the application");
            }


            //Row No - 160 : Verify the visibility of Assignment on Dashboard
            //Expected - Assigned Widget should be displayed on the dashboard
            new UIElement().waitAndFindElement(dashboard.postedAssignmentText);
            Assert.assertEquals(dashboard.postedAssignmentText.getText(), "(shor) "+customAssignmentName+"", "Assigned Widget is not displayed on the dashboard");

            //Row No - 161 :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();



            //Row No - 162 :Click on Course Stream option
            //Expected -Course stream page should be displayed to the user
            dashboard.menu_courseStream.click();
            new UIElement().waitAndFindElement(By.className("share-to-ls-label"));
            if (!driver.getCurrentUrl().endsWith("/coursestream")) {
                Assert.fail("Course stream page should be displayed to the user");
            }


            //Row No - 163 :Verify the visibility of Assignment on Course Stream
            //Expected -Assigned Widget should be displayed on the Course stream as card view
            Assert.assertEquals(courseStreamPage.assessmentNameLink.getText(),"(shor) "+customAssignmentName+"","Assigned Widget should be displayed on the Course stream as card view");

            //Row No - 164 :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();



            //Row No - 165 : Click on Assignments option
            //Expected -Assignment page should be displayed to the user
            assignments.menu_Assignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("Assignment page should be displayed to the user");
            }


            //Row No - 166 :Verify the visibility of Assignment on Assignments page
            //Expected -Assigned Widget should be displayed on the Assignments page as card view
            Assert.assertEquals(assignments.getAssignmentName().getText(),"(shor) "+customAssignmentName+"","Assigned Widget should be displayed on the Assignments page as card view");




            //Row No - 167 :Click on the Assignment
            //Expected -The Assignment should be opened in a new tab
            assignments.getAssignmentName().click();
            Thread.sleep(5000);
            new UIElement().waitAndFindElement(By.cssSelector("span[class='tab_title']"));
            if(!(currentAssignments.tab_Title.size()==5 && currentAssignments.tab_Title.get(0).getText().equals(customAssignmentName))){
                Assert.fail("The Assignment is not opened in a new tab");
            }


            //Row No - 168 :Finish the Assignment and Submit
            //Expected -The assignment should be finished and assignments page screen should be displayed
            new Assignment().submitAssignmentFromAssignmentsAsStudent(1571);
            Assert.assertEquals(performanceSummaryReport.chartTitle.getText().trim(),"Performance Summary","The assignment is not finished and assignments page screen is not displayed");



            //Row No - 169 :Navigate to the Instructor Login and click on the main navigation
            //Expected -The Navigation List should be displayed to the User
            new LoginUsingLTI().ltiLogin("157");//Log in as an instructor
            validateMainNavigator();

            //Row No - 170 : Click on Assgnments option
            //Expected -The sub menus should be displayed
            assignments.menu_Assignments.click();
            if(assignments.subMenu.isDisplayed()){
                String menuOption = assignments.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }


            //Row No - 171 :Click on Current Assignments submenu
            //Expected -Current assignments page should be opened
            assignments.subMenu_currentAssignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Chapters"));
            if (!driver.getCurrentUrl().endsWith("/assignment")) {
                Assert.fail("Current assignments page should be opened");
            }


            //Row No - 172 :Click on View response link of the Assignment
            //Expected -View Response page should be opened
            currentAssignments.getViewGrade_link().click();
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            Thread.sleep(3000);
            System.out.println("currentAssignments.tab_Title.get(1).getText() : " + currentAssignments.tab_Title.get(1).getText());
            if(!currentAssignments.tab_Title.get(1).getText().contains("Response - (shor) "+customAssignmentName)){
                Assert.fail("View Response page is not opened");
            }



            //Row No - 173 :Click on View response link of the Question Number
            //Expected -View Response page should be opened in a new tab
            new ResponsePage().openViewResponsePage();
            Thread.sleep(5000);
            if(!(currentAssignments.tab_Title.size()==3 && currentAssignments.tab_Title.get(2).getText().contains("Response - (shor) "+customAssignmentName))){
                Assert.fail("View Response page is not opened in a new tab");
            }




            //Row No - 174 : Click on Teacher's feedback textbox
            //Expected - The entered text should be displayed in the textbox
            //Doubt



            //Row No - 175 :Click on Save button
            //Expected -The entered text should be saved
            assignmentResponsesPage.getFeedbackTextArea().click();
            feedback = new RandomString().randomstring(10);
            assignmentResponsesPage.getFeedbackTextArea().sendKeys(feedback);
            currentAssignments.getSave_button().click();
            Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(),"Saved successfully.","The entered text is not saved");


            //Row No - 176 :Close the tab
            //Expected -The tab should be closeds
            assignmentResponsesPage.closeTab.click();

            if(!(currentAssignments.tab_Title.size()<=2 && currentAssignments.tab_Title.get(1).getText().equals("Response - (shor) "+customAssignmentName+""))){
                Assert.fail("The tab should is not closed");
            }




            //Row No - 177 : Click on Release Feedback for All button
            //Expected - The feedback should be released and the button should be disabled
            assignmentResponsesPage.getReleaseFeedbackForAll().click();
            new UIElement().waitAndFindElement(assignmentResponsesPage.feedback_Box);
            if(!assignmentResponsesPage.feedback_Box.getAttribute("title").equals("Feedback Released")){
                Assert.fail("The feedback is not released and the button is not disabled");
            }


            //Row No - 178 :Navigate to the Student Login and click on the main navigation
            //Expected -The Navigation List should be displayed to the User
            new LoginUsingLTI().ltiLogin("157_2");//Log in as a student
            validateMainNavigator();



            //Row No - 179 : Click on Assgnments option
            //Expected -Assignment page should be displayed to the user
            assignments.menu_Assignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("Assignment page should be displayed to the user");
            }



            //Row No - 180 :Click on the Assignment for which the feedback was released
            //Expected -The Assignment should be opened in a new tab and performance subtab should be selected by default
            assignments.getAssignmentName().click();
            Thread.sleep(3000);
            if(!(currentAssignments.tab_Title.size()<=5 && currentAssignments.tab_Title.get(0).getText().equals(customAssignmentName) &&currentAssignments.tab_Title.get(4).getText().equals("Performance"))){
                Assert.fail("The Assignment is not opened in a new tab ");
            }

            //Row No - 181 :Verify the teacher feedback
            //Expected -The entered teacher feedback should be displayed below
            //Doubt Ask Suraj to get the expected result
            Assert.assertEquals(currentAssignments.getDwComment().getText(),feedback,"The entered teacher feedback should be displayed below");

        } catch (Exception e) {
            Assert.fail("Exception in test script 'userToBeAbleToPerformActionsForNonGradableCustomAssignments' in the class 'EndToEndAssignments'", e);
        }
    }







    @Test(priority = 3, enabled = true)
    public void userToBeAbleToPerformActionsForGradableAssignments() {
        try {

            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Discussion discussion = PageFactory.initElements(driver, Discussion.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            int dataIndex = 182;
            String widgetText = "(shor) What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?";
            String feedback = null;

            //Row No - 182 : Login to the application as a Instructor
            //Expected - The user should be successfully logged in to the application
            new LoginUsingLTI().ltiLogin("182");//Log in as an instructor
            new LoginUsingLTI().ltiLogin("182_2");//Log in as a student

            new LoginUsingLTI().ltiLogin("182");//Log in as an instructor
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            System.out.println("Url : " + driver.getCurrentUrl());
            if (!driver.getCurrentUrl().contains("secure/learningSpaceInstructorDashboard")) {
                Assert.fail("The user should be successfully logged in to the application");
            }


            //Row No - 183 : Assign  Gradable Widget(Discussion/Audio/Video)
            //Expected  - The Non Gradable Widget should be assigned to the students on the class


            new Navigator().NavigateTo("e-Textbook");//Click on 'e-Textbook'
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            Thread.sleep(5000);
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(dataIndex);
            Assert.assertEquals(lessonPage.getAssignmentName().getText(),widgetText,"The Non Gradable Widget is not assigned to the students on the class");


            //Row N0 184 : Login to the application as a student
            //Expected -  The user should be successfully logged in to the application
            new LoginUsingLTI().ltiLogin("182_2");//Log in as a student
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            System.out.println("Url : " + driver.getCurrentUrl());
            if (!driver.getCurrentUrl().contains("/lsStudentDashBoard")) {
                Assert.fail("The user is not successfully logged in to the application");
            }


            //Row No - 185 : Verify the visibility of Assignment on Dashboard
            //Expected - Assigned Widget should be displayed on the dashboard
            new UIElement().waitAndFindElement(dashboard.postedAssignmentText);
            Assert.assertEquals(dashboard.postedAssignmentText.getText(), widgetText, "Assigned Widget is not displayed on the dashboard");

            //Row No - 186 :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();



            //Row No - 187 :Click on Course Stream option
            //Expected -Course stream page should be displayed to the user
            dashboard.menu_courseStream.click();
            new UIElement().waitAndFindElement(By.className("share-to-ls-label"));
            System.out.println("dxSX: " + driver.getCurrentUrl());
            if (!driver.getCurrentUrl().endsWith("/coursestream")) {
                Assert.fail("Course stream page should be displayed to the user");
            }


            //Row No - 188 :Verify the visibility of Assignment on Course Stream
            //Expected -Assigned Widget should be displayed on the Course stream as card view
            //Assert.assertEquals(courseStreamPage.assignmentLink.getText(),widgetText,"Assigned Widget should be displayed on the Course stream as card view");
            if (!courseStreamPage.assignmentLink.getText().contains(widgetText.substring(8))) {
                Assert.fail("Assigned Widget should be displayed on the Course stream as card view");
            }


            //Row No - 189 :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();



            //Row No - 190 : Click on Assignments option
            //Expected -Assignment page should be displayed to the user
            assignments.menu_Assignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("Assignment page should be displayed to the user");
            }


            //Row No - 191 :Verify the visibility of Assignment on Assignments page
            //Expected -Assigned Widget should be displayed on the Assignments page as card view
            if (!currentAssignments.getLessonAssignment().getText().contains(widgetText.substring(8))) {
                Assert.fail("Assigned Widget should be displayed on the Assignments page as card view");
            }


            //Row No - 192 :Click on the Assignment
            //Expected -The Assignment should be opened in a new tab
            currentAssignments.getLessonAssignment().click();
            new UIElement().waitAndFindElement(By.partialLinkText("Perspectives"));
            discussion.getPerspectives().click();
            discussion.getLink_enterSubmission().click();
            if (!discussion.getTab_discussion().isDisplayed()) {
                Assert.fail("The Assignment is not opened in a new tab");
            }


            //Row No - 193 :Finish the Assignment and Submit
            //Expected -The assignment should be finished and assignments page screen should be displayed
            String perspectiveText = new RandomString().randomstring(10);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspectiveText);
            Assert.assertEquals(currentAssignments.getDwComment().getText(), perspectiveText, "The assignment is not finished and assignments page screen is not displayed");


            //Row No - 194 :Navigate to the Instructor Login and click on the main navigation
            //Expected -The Navigation List should be displayed to the User
            new LoginUsingLTI().ltiLogin("182");//Log in as an instructor
            validateMainNavigator();

            //Row No - 195 : Click on Assgnments option
            //Expected -The sub menus should be displayed
            assignments.menu_Assignments.click();
            if(assignments.subMenu.isDisplayed()){
                String menuOption = assignments.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }


            //Row No - 196 :Click on Current Assignments submenu
            //Expected -Current assignments page should be opened
            assignments.subMenu_currentAssignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Chapters"));
            if (!driver.getCurrentUrl().endsWith("/assignment")) {
                Assert.fail("Current assignments page should be opened");
            }


            //Row No - 197 :Click on View response link of the Assignment
            //Expected -View Response page should be opened
            currentAssignments.getViewGrade_link().click();
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            Thread.sleep(3000);
            System.out.println("currentAssignments.tab_Title.get(1).getText() : " + currentAssignments.tab_Title.get(1).getText());
            if(!currentAssignments.tab_Title.get(1).getText().contains("Response - "+widgetText)){
                Assert.fail("View Response page is not opened");
            }



            //Row No - 198 :Click on View response link of the Question Number
            //Expected -View Response page should be opened in a new tab
            new ResponsePage().openViewResponsePage();
            if(!(currentAssignments.tab_Title.size()==2 && currentAssignments.tab_Title.get(1).getText().contains("Response - "+widgetText))){
                Assert.fail("View Response page is not opened in a new tab");
            }




            //Row No - 199 : Click on Teacher's feedback textbox
            //Expected - The entered text should be displayed in the textbox
            //Doubt



            //Row No - 200 :Click on Save button
            //Expected -The entered text should be saved
            assignmentResponsesPage.getFeedbackTextArea().click();
            feedback = new RandomString().randomstring(10);
            assignmentResponsesPage.getFeedbackTextArea().sendKeys(feedback);
            currentAssignments.getDwSave_button().click();
            Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(),"Saved successfully.","The entered text is not saved");


            //Row No - 201 :Close the tab
            //Expected -The tab should be closed
            assignmentResponsesPage.closeIcon_dwResponsePage.click();
            if(!(currentAssignments.tab_Title.size()<=2 && currentAssignments.tab_Title.get(1).getText().contains("Response - "+widgetText))){
                Assert.fail("The tab should is not closed");
            }

            //Row No - 202 : Click on Release Grade for All button
            //Expected - The Grade should be released and the button should be disabled
            Thread.sleep(3000);
            assignmentResponsesPage.getReleaseGradeForAll().click();
            if(!assignmentResponsesPage.grade_Box.getAttribute("title").equals("Grades Released")){
                Assert.fail("The Grade should is not released and the button is not disabled");
            }




            //Row No - 203 :Navigate to the Student Login and click on the main navigation
            //Expected -The Navigation List should be displayed to the User
            new LoginUsingLTI().ltiLogin("182_2");//Log in as a student
            validateMainNavigator();



            //Row No - 204 : Click on Assgnments option
            //Expected -Assignment page should be displayed to the user
            assignments.menu_Assignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("Assignment page should be displayed to the user");
            }



            //Row No - 205 : Verify the Overall Score and Total Points
            //Expected - Overall Score should be displayed as per the calculation and total points should be displayed in the Assignment card
            Assert.assertEquals(assignments.score.getText().trim(),"Score (0/0.6)","Overall Score is not displayed as per the calculation and total points is not displayed in the Assignment card");


            //Row No - 206 :Click on the Assignment for which the feedback was released
            //Expected -The Assignment should be opened in a new tab and performance subtab should be selected by default
            new DiscussionWidget().openAddedPerspectiveForDWAssignment(dataIndex);
            System.out.println("currentAssignments.tab_Title.size() : " + currentAssignments.tab_Title.size());
            System.out.println("currentAssignments.tab_Title.get(1).getText() : " + currentAssignments.tab_Title.get(1).getText());

            for(int a=0;a<currentAssignments.tab_Title.size();a++){
                System.out.println("a : " + a);
                String b = currentAssignments.tab_Title.get(a).getText();
                System.out.println("B : " + b);
            }
            if(!(currentAssignments.tab_Title.size()<=8 && currentAssignments.tab_Title.get(3).getText().contains("Discussion"))){
                Assert.fail("The Assignment is not opened in a new tab ");
            }


            //Row No - 207 :Verify the teacher feedback
            //Expected -The entered teacher feedback should be displayed below
            Thread.sleep(5000);
            Assert.assertEquals(currentAssignments.teacher_Feedback.getText(),feedback,"The entered teacher feedback should be displayed below");


        } catch (Exception e) {
            Assert.fail("Exception in test script 'userToBeAbleToPerformActionsForGradableAssignments' in the class 'EndToEndAssignments'", e);
        }
    }






    @Test(priority = 4, enabled = true)
    public void userToBeAbleToPerformActionsForGradableCustomAssignments() {
        try {

            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Discussion discussion = PageFactory.initElements(driver, Discussion.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            PerformanceSummaryReport performanceSummaryReport = PageFactory.initElements(driver, PerformanceSummaryReport.class);

            int dataIndex = 208;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(dataIndex));
            String feedback = null;

            //Row No - 208 : Login to the application as a Instructor
            //Expected - The user should be successfully logged in to the application
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex,"multiplechoice","");
            new LoginUsingLTI().ltiLogin("208");//Log in as an instructor
            new LoginUsingLTI().ltiLogin("208_2");//Log in as a student


            new LoginUsingLTI().ltiLogin("208");//Log in as an instructor
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            if (!driver.getCurrentUrl().contains("secure/learningSpaceInstructorDashboard")) {
                Assert.fail("The user should be successfully logged in to the application");
            }


            //Row No - 209 : Assign a Custom Assignment
            //Expected  - The  Gradable Custom Assignment should be assigned to the students on the class
            new CreateCustomAssignment().createCustomAssignment(questiontext,"208");
            new Assignment().assignCustomAssignmentFromMyQuestionBank(dataIndex);
            Assert.assertEquals(assignments.getAssignmentName().getText(),"(shor) "+customAssignmentName+"","The Non Gradable Widget is not assigned to the students on the class");


            //Row N0 210 : Login to the application as a student
            //Expected -  The user should be successfully logged in to the application
            new LoginUsingLTI().ltiLogin("208_2");//Log in as a student
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            if (!driver.getCurrentUrl().contains("/lsStudentDashBoard")) {
                Assert.fail("The user is not successfully logged in to the application");
            }


            //Row No - 211 : Verify the visibility of Assignment on Dashboard
            //Expected - Assigned Widget should be displayed on the dashboard
            new UIElement().waitAndFindElement(dashboard.postedAssignmentText);
            Assert.assertEquals(dashboard.postedAssignmentText.getText(), "(shor) "+customAssignmentName+"", "Assigned Widget is not displayed on the dashboard");

            //Row No - 212 :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();



            //Row No - 213 :Click on Course Stream option
            //Expected -Course stream page should be displayed to the user
            dashboard.menu_courseStream.click();
            new UIElement().waitAndFindElement(By.className("share-to-ls-label"));
            if (!driver.getCurrentUrl().endsWith("/coursestream")) {
                Assert.fail("Course stream page should be displayed to the user");
            }


            //Row No - 214 :Verify the visibility of Assignment on Course Stream
            //Expected -Assigned Widget should be displayed on the Course stream as card view
            Assert.assertEquals(courseStreamPage.assessmentNameLink.getText(),"(shor) "+customAssignmentName+"","Assigned Widget should be displayed on the Course stream as card view");

            //Row No - 215 :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();



            //Row No - 216 : Click on Assignments option
            //Expected -Assignment page should be displayed to the user
            assignments.menu_Assignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("Assignment page should be displayed to the user");
            }


            //Row No - 217 :Verify the visibility of Assignment on Assignments page
            //Expected -Assigned Widget should be displayed on the Assignments page as card view
            Assert.assertEquals(assignments.getAssignmentName().getText(),"(shor) "+customAssignmentName+"","Assigned Widget should be displayed on the Assignments page as card view");



            //Row No - 218 : Verify the Gradebook Category
            //Expected - Gradebook Category should be displayed with the percentage weightage






            //Row No - 219 :Click on the Assignment
            //Expected -The Assignment should be opened in a new tab
            assignments.getAssignmentName().click();
            Thread.sleep(5000);
            new UIElement().waitAndFindElement(By.cssSelector("span[class='tab_title']"));
            if(!(currentAssignments.tab_Title.size()==5 && currentAssignments.tab_Title.get(0).getText().equals(customAssignmentName))){
                Assert.fail("The Assignment is not opened in a new tab");
            }


            //Row No - 220 :Finish the Assignment and Submit
            //Expected -The assignment should be finished and assignments page screen should be displayed
            new Assignment().submitAssignmentFromAssignmentsAsStudent(2081);
            Assert.assertEquals(performanceSummaryReport.chartTitle.getText().trim(),"Performance Summary","The assignment is not finished and assignments page screen is not displayed");



            //Row No - 221 :Navigate to the Instructor Login and click on the main navigation
            //Expected -The Navigation List should be displayed to the User
            new LoginUsingLTI().ltiLogin("208");//Log in as an instructor
            validateMainNavigator();

            //Row No - 222 : Click on Assgnments option
            //Expected -The sub menus should be displayed
            assignments.menu_Assignments.click();
            if(assignments.subMenu.isDisplayed()){
                String menuOption = assignments.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }


            //Row No - 223 :Click on Current Assignments submenu
            //Expected -Current assignments page should be opened
            assignments.subMenu_currentAssignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Chapters"));
            if (!driver.getCurrentUrl().endsWith("/assignment")) {
                Assert.fail("Current assignments page should be opened");
            }


            //Row No - 224 :Click on View response link of the Assignment
            //Expected -View Response page should be opened
            currentAssignments.getViewGrade_link().click();
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            Thread.sleep(3000);
            System.out.println("currentAssignments.tab_Title.get(1).getText() : " + currentAssignments.tab_Title.get(1).getText());
            if(!currentAssignments.tab_Title.get(1).getText().contains("Response - (shor) "+customAssignmentName)){
                Assert.fail("View Response page is not opened");
            }



            //Row No - 225 :Click on View response link of the Question Number
            //Expected -View Response page should be opened in a new tab
            new ResponsePage().openViewResponsePage();
            Thread.sleep(5000);
            if(!(currentAssignments.tab_Title.size()==3 && currentAssignments.tab_Title.get(2).getText().contains("Response - (shor) "+customAssignmentName))){
                Assert.fail("View Response page is not opened in a new tab");
            }




            //Row No - 226 : Click on Teacher's feedback textbox
            //Expected - The entered text should be displayed in the textbox
            //Row No - 227 :Click on Save button
            //Expected -The entered text should be saved
            assignmentResponsesPage.getFeedbackTextArea().click();
            feedback = new RandomString().randomstring(10);
            assignmentResponsesPage.getFeedbackTextArea().sendKeys(feedback);
            currentAssignments.getSave_button().click();
            Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(),"Saved successfully.","The entered text is not saved");

            System.out.println("Inner HTML ; " + assignmentResponsesPage.getFeedbackTextArea().getAttribute("innerHTML"));




            //Row No - 228 :Close the tab
            //Expected -The tab should be closed
            assignmentResponsesPage.closeTab.click();

            if(!(currentAssignments.tab_Title.size()<=2 && currentAssignments.tab_Title.get(1).getText().equals("Response - (shor) "+customAssignmentName+""))){
                Assert.fail("The tab should is not closed");
            }




            //Row No - 229 : Click on Release Grade for All button
            //Expected - The grade should be released and the button should be disabled
            assignmentResponsesPage.getReleaseGradeForAll().click();
            Thread.sleep(2000);
            if(assignmentResponsesPage.releaseGrade.isDisplayed()==false){
                Assert.fail("The feedback is not released and the button is not disabled");
            }


            //Row No - 230 :Navigate to the Student Login and click on the main navigation
            //Expected -The Navigation List should be displayed to the User
            new LoginUsingLTI().ltiLogin("208_2");//Log in as a student
            validateMainNavigator();



            //Row No - 231 : Click on Assgnments option
            //Expected -Assignment page should be displayed to the user
            assignments.menu_Assignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("Assignment page should be displayed to the user");
            }


            //Row No - 233 : Verify the Overall Score and Total Points
            //Expected - Overall Score should be displayed as per the calculation and total points should be displayed in the Assignment card
            Assert.assertEquals(assignments.score.getText().trim(),"Score (2/2)","Overall Score is not displayed as per the calculation and total points is not displayed in the Assignment card");




            //Row No - 232 :Click on the Assignment for which the Grade was released
            //Expected -The Assignment should be opened in a new tab and performance subtab should be selected by default
            assignments.getAssignmentName().click();
            Thread.sleep(3000);
            if(!(currentAssignments.tab_Title.size()<=5 && currentAssignments.tab_Title.get(0).getText().equals(customAssignmentName) &&currentAssignments.tab_Title.get(4).getText().equals("Performance"))){
                Assert.fail("The Assignment is not opened in a new tab ");
            }






            //Row No - 234 :Verify the teacher feedback
            //Expected -The entered teacher feedback should be displayed below
            //Doubt Ask Suraj to get the expected result
            questions.question_card.get(0).click();//click on question card
            String feedback1=driver.findElement(By.className("feedback-block-header")).getText();
            Assert.assertEquals(feedback1,"Teacher Feedback","The entered teacher feedback should be displayed below");


        } catch (Exception e) {
            Assert.fail("Exception in test script 'userToBeAbleToPerformActionsForNonGradableAssignments' in the class 'EndToEndAssignments'", e);
        }
    }







    @Test(priority = 5, enabled = true)
    public void userToBeAbleToPerformActionsForGradableCustomAssignmentsWithPolicies() {
        try {

            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Discussion discussion = PageFactory.initElements(driver, Discussion.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            PerformanceSummaryReport performanceSummaryReport = PageFactory.initElements(driver, PerformanceSummaryReport.class);
            Policies policies = PageFactory.initElements(driver, Policies.class);

            int dataIndex = 235;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(dataIndex));
            String feedback = null;

            //Row No - 235 : Login to the application as a Instructor
            //Expected - The user should be successfully logged in to the application
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex,"multiplechoice","");
            new LoginUsingLTI().ltiLogin("235");//Log in as an instructor
            new LoginUsingLTI().ltiLogin("235_2");//Log in as a student


            new LoginUsingLTI().ltiLogin("235");//Log in as an instructor
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            if (!driver.getCurrentUrl().contains("secure/learningSpaceInstructorDashboard")) {
                Assert.fail("The user should be successfully logged in to the application");
            }


            //Row No - 236 : Assign a Custom Assignment(with Policy P1/P2/P3/P4)
            //Expected  - The Non Gradable Custom Assignment should be assigned to the students on the class
            new Navigator().NavigateTo("Policies");
            new AssignmentPolicy().createAssignmentPolicy("Policy Name", "Policy Description text", "2", null, false, "1", "", "Release explicitly by the instructor", "", "", "");
            new CreateCustomAssignment().createCustomAssignment(questiontext,""+dataIndex);
            new Assignment().AssignAssessmentwithgradingPolicy(dataIndex, false);
            Assert.assertEquals(assignments.getAssignmentName().getText(), "(shor) " + customAssignmentName + "", "The Non Gradable Widget is not assigned to the students on the class");


            //Row N0 237 : Login to the application as a student
            //Expected -  The user should be successfully logged in to the application
            new LoginUsingLTI().ltiLogin("235_2");//Log in as a student
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            if (!driver.getCurrentUrl().contains("/lsStudentDashBoard")) {
                Assert.fail("The user is not successfully logged in to the application");
            }


            //Row No - 238 : Verify the visibility of Assignment on Dashboard
            //Expected - Assigned Widget should be displayed on the dashboard
            new UIElement().waitAndFindElement(dashboard.postedAssignmentText);
            Assert.assertEquals(dashboard.postedAssignmentText.getText(), "(shor) "+customAssignmentName+"", "Assigned Widget is not displayed on the dashboard");

            //Row No - 239 :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();



            //Row No - 240 :Click on Course Stream option
            //Expected -Course stream page should be displayed to the user
            dashboard.menu_courseStream.click();
            new UIElement().waitAndFindElement(By.className("share-to-ls-label"));
            if (!driver.getCurrentUrl().endsWith("/coursestream")) {
                Assert.fail("Course stream page should be displayed to the user");
            }


            //Row No - 241 :Verify the visibility of Assignment on Course Stream
            //Expected -Assigned Widget should be displayed on the Course stream as card view
            Assert.assertEquals(courseStreamPage.assessmentNameLink.getText(),"(shor) "+customAssignmentName+"","Assigned Widget should be displayed on the Course stream as card view");

            //Row No - 242 :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();



            //Row No - 243 : Click on Assignments option
            //Expected -Assignment page should be displayed to the user
            assignments.menu_Assignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("Assignment page should be displayed to the user");
            }


            //Row No - 244 :Verify the visibility of Assignment on Assignments page
            //Expected -Assigned Widget should be displayed on the Assignments page as card view
            Assert.assertEquals(assignments.getAssignmentName().getText(),"(shor) "+customAssignmentName+"","Assigned Widget should be displayed on the Assignments page as card view");



            //Row No - 245 : Verify the Gradebook Category
            //Expected - Gradebook Category should be displayed with the percentage weightage
            Assert.assertEquals(assignments.percentageWeightage.getText().trim(), "Uncategorized: 100%", "The Gradebook Category is not displayed with the percentage weightage");




            //Row No - 246 :Click on the Assignment
            //Expected -The Assignment should be opened in a new tab
            assignments.getAssignmentName().click();
            new UIElement().waitAndFindElement(policies.continue_button);
            //policies.continue_button.click();
            Thread.sleep(5000);
            new UIElement().waitAndFindElement(By.cssSelector("span[class='tab_title']"));
            if(!(currentAssignments.tab_Title.size()==5 && currentAssignments.tab_Title.get(0).getText().equals(customAssignmentName))){
                Assert.fail("The Assignment is not opened in a new tab");
            }


            //Row No - 247 :Finish the Assignment and Submit
            //Expected -The assignment should be finished and assignments page screen should be displayed
            new Assignment().submitAssignmentFromAssignmentsAsStudent(2351);
            Thread.sleep(2000);
            /*currentAssignments.finish_button.click();
            Thread.sleep(2000);
            assignments.getGoToNextLinkOnPopUp().click();*/
            Assert.assertEquals(performanceSummaryReport.chartTitle.getText().trim(),"Performance Summary","The assignment is not finished and assignments page screen is not displayed");



            //Row No - 248 :Navigate to the Instructor Login and click on the main navigation
            //Expected -The Navigation List should be displayed to the User
            new LoginUsingLTI().ltiLogin("235");//Log in as an instructor
            validateMainNavigator();

            //Row No - 249 : Click on Assgnments option
            //Expected -The sub menus should be displayed
            assignments.menu_Assignments.click();
            if(assignments.subMenu.isDisplayed()){
                String menuOption = assignments.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }


            //Row No - 250 :Click on Current Assignments submenu
            //Expected -Current assignments page should be opened
            assignments.subMenu_currentAssignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Chapters"));
            if (!driver.getCurrentUrl().endsWith("/assignment")) {
                Assert.fail("Current assignments page should be opened");
            }


            //Row No - 251 :Click on View response link of the Assignment
            //Expected -View Response page should be opened
            currentAssignments.getViewGrade_link().click();
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            Thread.sleep(5000);
            System.out.println("currentAssignments.tab_Title.get(1).getText() : " + currentAssignments.tab_Title.get(1).getText());
            if(!currentAssignments.tab_Title.get(1).getText().contains("Response - (shor) "+customAssignmentName)){
                Assert.fail("View Response page is not opened");
            }



            //Row No - 252 :Click on View response link of the Question Number
            //Expected -View Response page should be opened in a new tab
            new ResponsePage().openViewResponsePage();
            Thread.sleep(5000);
            System.out.println("currentAssignments.tab_Title.size() : " + currentAssignments.tab_Title.size());
            if(!(currentAssignments.tab_Title.size()==3 && currentAssignments.tab_Title.get(2).getText().contains("Response - (shor) "+customAssignmentName))){
                Assert.fail("View Response page is not opened in a new tab");
            }




            //Row No - 253 : Click on Teacher's feedback textbox
            //Expected - The entered text should be displayed in the textbox
            //Row No - 254 :Click on Save button
            //Expected -The entered text should be saved
            assignmentResponsesPage.getFeedbackTextArea().click();
            feedback = new RandomString().randomstring(10);
            assignmentResponsesPage.getFeedbackTextArea().sendKeys(feedback);
            currentAssignments.getSave_button().click();
            new UIElement().waitAndFindElement(assignmentResponsesPage.getSavedSuccessfullyMessage());
            Thread.sleep(2000);
            Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(),"Saved successfully.","The entered text is not saved");

            System.out.println("Inner HTML ; " + assignmentResponsesPage.getFeedbackTextArea().getAttribute("innerHTML"));




            //Row No - 255 :Close the tab
            //Expected -The tab should be closed
            assignmentResponsesPage.closeTab.click();

            if(!(currentAssignments.tab_Title.size()<=2 && currentAssignments.tab_Title.get(1).getText().equals("Response - (shor) "+customAssignmentName+""))){
                Assert.fail("The tab should is not closed");
            }




            //Row No - 256 : Click on Release Grade for All button
            //Expected - The grade should be released and the button should be disabled
            assignmentResponsesPage.getReleaseGradeForAll().click();
            Assert.assertEquals(assignmentResponsesPage.grade_Box.getText(),"Grades Released","The Grade is not released and the button is not disabled");




            //Row No - 257 :Navigate to the Student Login and click on the main navigation
            //Expected -The Navigation List should be displayed to the User
            new LoginUsingLTI().ltiLogin("235_2");//Log in as a student
            validateMainNavigator();



            //Row No - 258 : Click on Assgnments option
            //Expected -Assignment page should be displayed to the user
            assignments.menu_Assignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("Assignment page should be displayed to the user");
            }



            //Row No - 260 : Verify the Overall Score and Total Points
            //Expected - Overall Score should be displayed as per the calculation and total points should be displayed in the Assignment card
            Assert.assertEquals(assignments.score.getText().trim(),"Score (2/2)","Overall Score is not displayed as per the calculation and total points is not displayed in the Assignment card");
            Assert.assertEquals(assignments.overallScorePercentage.getText().trim(),"100%","Overall Score is not displayed as per the calculation");


            //Row No - 259 :Click on the Assignment for which the Grade was released
            //Expected -The Assignment should be opened in a new tab and performance subtab should be selected by default
            assignments.getAssignmentName().click();
            Thread.sleep(3000);
            if(!(currentAssignments.tab_Title.size()<=5 && currentAssignments.tab_Title.get(0).getText().equals(customAssignmentName) &&currentAssignments.tab_Title.get(4).getText().equals("Performance"))){
                Assert.fail("The Assignment is not opened in a new tab ");
            }






            //Row No - 261 :Verify the teacher feedback
            //Expected -The entered teacher feedback should be displayed below
            //To be discussed with Suraj to get the expected result
            //Assert.assertEquals(currentAssignments.getDwComment().getText(),feedback,"The entered teacher feedback should be displayed below");

        } catch (Exception e) {
            Assert.fail("Exception in test script 'userToBeAbleToPerformActionsForNonGradableAssignments' in the class 'EndToEndAssignments'", e);
        }
    }




    @Test(priority = 6, enabled = true)
    public void userToBeAbleToPerformActionsForFileBasedNonGradableAssignments() {
        try {

            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Discussion discussion = PageFactory.initElements(driver, Discussion.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            int dataIndex = 262;
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            //Row No - 262 : Login to the application as a Instructor
            //Expected - The user should be successfully logged in to the application
            new Assignment().createFileBasedAssessment(dataIndex);
            new LoginUsingLTI().ltiLogin("262");//Log in as an instructor
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            System.out.println("Url : " + driver.getCurrentUrl());
            if (!driver.getCurrentUrl().contains("secure/learningSpaceInstructorDashboard")) {
                Assert.fail("The user should be successfully logged in to the application");
            }


            //Row No - 263 :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();


            //Row No - 264 : Click on the Assignment option and Verify the submenus displayed towards the right
            /*Expected  - "The user should be able to see 4 different submenus i.e.
            1. New Assignment
            2. Current Assignments
            3. My Question Bank
            4. Policies"*/
            assignments.menu_Assignments.click();
            if(assignments.subMenu.isDisplayed()){
                String menuOption = assignments.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }



            //Row No - 265 : Click on the New Assignment option
            /*Expected  - "Create New Assignment pop-up window should be opened with three different types of Assignments i.e.
            1. Custom Assignment
            2. Use Pre Created Assignment
            3. File Based Assignment"*/

            assignments.subMenu_currentAssignments.click();
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            if (!currentAssignments.close_Icon.isDisplayed()) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");




            //Row No - 266 : Click on Use Pre Created Assignment button on the pop -up
            //Expected - The user should be navigated to the Question Banks page
            currentAssignments.usePreCreatedAssignmentButton.click();//click on use pre create assignment
            Assert.assertEquals(currentAssignments.tab_Title.get(1).getAttribute("title"), "Question Banks", "The user is not navigated to the Question Banks page");



            //Row No - 267 : Verify the Created File Based Assignment
            //Expected - File Based Assessment created from the Author's side should be displayed
            new Assignment().searchAssessmentInQuestionBanks(dataIndex);
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "File Based Assessment created from the Author's side is not displayed");


            //Row No - 268 :Click on Assign this link under the Assignment Name
            //Expected - a dropdown should be displayed with the Assessment name
            new UIElement().waitAndFindElement(questionBank.getAssignThisButtton());
            questionBank.getAssignThisButtton().click();
            Thread.sleep(2000);
            Assert.assertEquals(newAssignment.popUP.getText(), assessmentName, "Form should be displayed with the Assessment name");



            //Row No - 269 : Verify the fields displayed in the pop-up
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


            //Row No - 270 : Verify for the Total Points textbox
            //Expected  - Total Points text box should not be displayed
            newAssignment.gradableCheckBox.click();//click on gradable check box
            if(driver.findElement(By.id("total-points")).isDisplayed()){
                Assert.fail("Total Points text box should not be displayed");
            }


            //Row No - 271 : Enter the Mandatory fields and Click on Assign button
            //Expected - The Assessment should be assigned to all the Assigned To members
            driver.findElement(By.xpath("//html/body")).click();
            questionBank.getAssignThisButtton().click();
            new UIElement().waitAndFindElement(By.id("due-date"));
            driver.findElement(By.id("due-date")).click();//click on due date
            driver.findElement(By.cssSelector("a[title='Next']")).click();
            driver.findElement(By.linkText(duedate)).click();
            driver.findElement(By.id("due-time")).click();//click on due time
            driver.findElement(By.xpath("(//ul[@class='ui-timepicker-list']/li)[5]")).click();
            newAssignment.button_assign.click();
            Thread.sleep(4000);
            Assert.assertEquals(currentAssignments.tab_Title.get(0).getText(), "Current Assignments", "The user is not redirected to Current Assignments page");
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "The Assessment is not Assigned To all the members");
            Assert.assertEquals(currentAssignments.getAssignmentName().getText().trim(), "(shor) "+assessmentName+"", "The Assignment name is not listed on the Assignment page");


            //Row No - 272 : Click on the Main navigation on top left hand corner of the screen
            //The user should get the main navigation dropdown displayed
            validateMainNavigator();



            //Row No - 273 :Click on Assignments option for the dropdown
            //Expected  - The submenus should be open
            assignments.menu_Assignments.click();
            if(assignments.subMenu.isDisplayed()){
                String menuOption = assignments.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }


            //Row No - 274 : Click on Current Assignments option
            //Expected - The File Based assignment should be displayed at the top with its mandatory details
            Assert.assertEquals(currentAssignments.tab_Title.get(0).getText(), "Current Assignments", "The user is not redirected to Current Assignments page");
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "The Assessment is not Assigned To all the members");
            Assert.assertEquals(currentAssignments.getAssignmentName().getText().trim(), "(shor) "+assessmentName+"", "The Assignment name is not listed on the Assignment page");



            //Row No - 275 : Verify for the details displayed
            //Expected - All the attached files should be displayed and should have a link
            //repeated


            //Row No - 276 : Click on the link displayed under each File Name
            //Expected - The browser pop up to download the attachment should be opened
            currentAssignments.uploadedResources.click();
            Thread.sleep(15000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);


            //Row No - 280 : Login to the application as a student
            //Expected - The user should be successfully logged in to the application
            new LoginUsingLTI().ltiLogin("262_2");//Log in as a student
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            if (!driver.getCurrentUrl().contains("/lsStudentDashBoard")) {
                Assert.fail("The user is not successfully logged in to the application");
            }



            //Row No - 281 : Click on the Main navigation on top left hand corner of the screen
            //Expected - The Navigation List should be displayed to the User
            validateMainNavigator();
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            if (!driver.getCurrentUrl().contains("/lsStudentDashBoard")) {
                Assert.fail("The user is not successfully logged in to the application");
            }



            //Row No - 282  - Click on Assignments option for the dropdown
            //Expected - The User should be navigated to the Assignments page
            assignments.menu_Assignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("The User is not navigated to the Assignments page");
            }
            Assert.assertEquals(assignments.getAssignmentName().getText(),"(shor) "+assessmentName+"","The Assessment is not displayed on the Assignments page as card view");


            //Row No - 283 : Click on the Assignment name
            //Expected  - The User should be navigated to the Assignments page with the Assignment details
            assignments.getAssignmentName().click();
            Thread.sleep(5000);
            new UIElement().waitAndFindElement(By.cssSelector("span[class='tab_title']"));
            if(!(currentAssignments.tab_Title.size()==5 && currentAssignments.tab_Title.get(0).getText().equals(assessmentName))){
                Assert.fail("The User is not navigated to the Assignments page with the Assignment details");
            }


            //Row No - 284 : Click on Upload File(s) link
            //Expected - The browser pop-up for uploading files should be opened
            //Row No  -285 : Select a file and click on Open button on the browser pop-up
            //Expected - The file should be uploaded and the browser popup should be closed
            fileUploadNotificationMessageValidate(""+dataIndex, notificationMessage);
            Thread.sleep(9000);

            //Row No - 286 : Verify the uploaded file
            //Expected  - The uploaded file should be displayed in Your Response section.
            new UIElement().waitAndFindElement(assignmentResponsesPage.getUploadFileList().get(0));
            if(!(assignmentResponsesPage.getUploadFileList().get(0).isDisplayed()&& assignmentResponsesPage.getUploadFileList().get(0).getText().trim().equals("img.png"))){
                Assert.fail("The uploaded file is not displayed in Your Response section.");
            }

            //Row No - 287 :  Click on Upload File(s) link
            //Expected - The browser pop-up for uploading files should be opened
            //Row No - 288 : Select a file and click on Open button on the browser pop-up
            //Expected - The file should be uploaded and the browser popup should be closed
            fileUploadNotificationMessageValidate("262_2", notificationMessage);


            //Row No - 289 : Verify the newly uploaded file
            //Expected  - The newly added file should come above the previously uploaded file
            //Row No - 290 : Verify the Icon and the file name for the uploaded files
            //Expected  - The icons and the file names should be proper with its extensions
            new UIElement().waitAndFindElement(newAssignment.fieUpload.get(1));
            Thread.sleep(5000);
            List<WebElement> imageLIst = driver.findElements(By.className("ls-uploaded-file"));
            System.out.println("imageLIst size : " + imageLIst.size());
            System.out.println("imageLIst.get(2).getText() : " + imageLIst.get(2).getText());
            if(!(imageLIst.get(2).isDisplayed()&& imageLIst.get(2).getText().trim().equals("img.jpg"))){
                Assert.fail("The uploaded file is not displayed in Your Response section.");
                //To be uncommented
            }

            if(newAssignment.imageIconList.size()!=3){
                Assert.fail("The icons are not proper with its extensions");
            }





            //Row No - 291 : Click on Finish Assignment button
            //Expected -The assignment should be submitted and the user should be navigated to the Assignments page
            assignmentTab.finishButton.click();
            lessonPage.continueButton.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("Assignment page should be displayed to the user");
            }
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("The User is not navigated to the Assignments page");
            }
            Assert.assertEquals(assignments.getAssignmentName().getText(),"(shor) "+assessmentName+"","The Assessment is not displayed on the Assignments page as card view");




            //Row No - 292 : Login to the application as a Instructor/ Mentor
            //Expected - The user should be successfully logged in to the application
            new LoginUsingLTI().ltiLogin("262");//Log in as an instructor            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            System.out.println("Url : " + driver.getCurrentUrl());
            if (!driver.getCurrentUrl().contains("secure/learningSpaceInstructorDashboard")) {
                Assert.fail("The user should be successfully logged in to the application");
            }


            //Row No - 293 : Click on the Main navigation on top left hand corner of the screen
            //Expected - The Navigation List should be displayed to the User
            validateMainNavigator();



            //Row No - 294 : Click on Assignments option
            //Expected -  The sub menu should be displayed to the user
            assignments.menu_Assignments.click();
            if(assignments.subMenu.isDisplayed()){
                String menuOption = assignments.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }


            //Row No - 295 : Click on Current Assignments option
            //Expected - Current Assignment page should be displayed to the user
            assignments.subMenu_currentAssignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Chapters"));
            if (!driver.getCurrentUrl().endsWith("/assignment")) {
                Assert.fail("Current assignments page should be opened");
            }
            Assert.assertEquals(currentAssignments.tab_Title.get(0).getText(), "Current Assignments", "The user is not redirected to Current Assignments page");





            //Row No - 296 : Verify for the File Based Assessment Name
            //Expected - File Based Assessment Should be displayed
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "File Based Assessment created from the Author's side is not displayed");
            Assert.assertEquals(currentAssignments.getAssignmentName().getText().trim(), "(shor) "+assessmentName+"", "The Assignment name is not listed on the Assignment page");




            //Row No - 297 : Click on View Student Responses link under Actions
            //Expected - The Assignment Response page should be opened as a new tab next to current assignment tab
            //Expected -View Response page should be opened in a new tab
            currentAssignments.getViewGrade_link().click();
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            Thread.sleep(5000);
            System.out.println("currentAssignments.tab_Title.size() : " + currentAssignments.tab_Title.size());
            System.out.println("currentAssignments.tab_Title.get(1).getText() : " + currentAssignments.tab_Title.get(1).getText());

            if(!(currentAssignments.tab_Title.size()==2 && currentAssignments.tab_Title.get(1).getText().contains("Response - (shor) "+assessmentName))){
                Assert.fail("View Response page is not opened in a new tab");
            }


            //Row No - 298 : Verify the % Complete column
            //Expected - The % complete column should show the percentage based on the shouldn't response either 0% or 100%
            Assert.assertEquals(currentAssignments.getGradeMark().getText().trim(),"100%","The % complete column is not showing the percentage based on the response either 0% or 100%");

            //Row No - 299 : Verify for the Score field
            //Expected - The Score field should not be present
            try{
                if(assignmentResponsesPage.icon_tickMark.isDisplayed()){
                    Assert.fail("The Score field should not be present");
                }
            }catch(Exception e){
                System.out.println("The Score field is not present");
            }


            //Row No - 300 : Hover the mouse on the % complete for a particular student
            //Expected - a link for View Response should be displayed based on hovering the mouse
            new MouseHover().mouserhover("idb-gradebook-question-content");
            new UIElement().waitAndFindElement(By.className("ls-view-response-link"));
            if(!assignmentResponsesPage.getViewResponseLink().isDisplayed()){
                Assert.fail("View Response Link should be displayed based on hovering the mouse");
            }



            //Row No - 301 - Click on View Response link
            //Expected - The user should be navigated to the Student response page in a new tab
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            Thread.sleep(9000);
            if(!(currentAssignments.tab_Title.size()==3 && currentAssignments.tab_Title.get(2).getText().contains("Response - (shor) "+assessmentName))){
                Assert.fail("The user is not navigated to the Student response page in a new tab");
            }




            //Row No - 302 : Click on Teacher feedback text box and type the feedback
            //Expected - The Feedback should be displayed in the textbox
            //Row No - 303 : Click on save button
            //Expected - The typed text should be saved and Saved Successfully message should be displayed below the textbox
            String feedback = new RandomString().randomstring(10);
            assignmentResponsesPage.getFeedbackTextArea().click();
            assignmentResponsesPage.getFeedbackTextArea().sendKeys(feedback);
            assignmentResponsesPage.getSaveButton().click();
            Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(),"Saved successfully.","The entered text is not saved");




            //Row No - 304 : Navigate to View response page and click on Release Feedback for All button
            /*Expected - "The button label should be changed to Feedback Released and the button should be greyed out.
            The Class Status on the page should be changed to Reviewed from Review in Progress"*/
            assignmentTab.cancelMark.click();
            Thread.sleep(1000);
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            Thread.sleep(1000);
            assignmentResponsesPage.getReleaseFeedbackForAll().click();
            new UIElement().waitAndFindElement(assignmentResponsesPage.feedback_Box);
            //new WebDriverWait(driver,60).until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div[class='idb-gradeBook-feedback-section-release idb-gradeBook-release-feedback-section idb-gradeBook-feedback-released']"),"Feedback Released"));
            if(!assignmentResponsesPage.feedback_Box.getAttribute("title").equals("Feedback Released")){
                Assert.fail("The feedback is not released and the button is not disabled");
            }
            Assert.assertEquals(assignmentResponsesPage.getReviewStatus().getText(),"Reviewed","The Class Status on the page is not changed to Reviewed from Review in Progress");




            //Row No - 305 : Login to the application as a student
            //Expected - The user should be successfully logged in to the application
            new LoginUsingLTI().ltiLogin("262_2");//Log in as a student
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            if (!driver.getCurrentUrl().contains("/lsStudentDashBoard")) {
                Assert.fail("The user is not successfully logged in to the application");
            }




            //Row No - 306 : Click on the Main navigation on top left hand corner of the screen
            //Expected - The Navigation List should be displayed to the User
            validateMainNavigator();
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            if (!driver.getCurrentUrl().contains("/lsStudentDashBoard")) {
                Assert.fail("The user is not successfully logged in to the application");
            }


            //Row No - 307 : Click on Assignments option for the dropdown
            //Expected - The User should be navigated to the Assignments page
            assignments.menu_Assignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("The User is not navigated to the Assignments page");
            }
            Assert.assertEquals(assignments.getAssignmentName().getText(),"(shor) "+assessmentName+"","The Assessment is not displayed on the Assignments page as card view");
            Assert.assertEquals(assignments.status_submitted.getText(),"Reviewed","The Class Status on the page is not changed to Reviewed from Review in Progress");



            //Row No - 308 : Click on the Assignment name
            /*Expected - "The User should be navigated to the performance tab with the Assignment details and Teacher's Feedbacks
            Status should be ""Reviewed"""*/
            assignments.getAssignmentName().click();
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(By.cssSelector("span[class='tab_title']"));
            System.out.println("currentAssignments.tab_Title.size() : " + currentAssignments.tab_Title.size());
            System.out.println("currentAssignments.tab_Title.get(0).getText() : " + currentAssignments.tab_Title.get(0).getText());
            if(!(currentAssignments.tab_Title.size()==4 && currentAssignments.tab_Title.get(0).getText().equals(assessmentName))){
                Assert.fail("The User is not navigated to the Assignments page with the Assignment details");
            }

            //Row No - 309 : Click on Student's Response area
            //Expected - Student's Response area should not be editable
            try {
                driver.findElement(By.className("ls-uploaded-files-wrapper")).clear();
                Assert.fail("Student's Response area should not be editable");
            }catch(Exception e){
                System.out.println("Student's Response area is not editable");
            }



            //Row No - 310 : Click on Teacher's Feedback area
            //Expected - Teacher's Feedback area should not be editable
            try {
                driver.findElement(By.className("feedback-content")).clear();
                Assert.fail("Teacher's Feedback should not be editable");
            }catch(Exception e){
                System.out.println("Teacher's Feedback is not editable");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test script 'userToBeAbleToPerformActionsForNonGradableAssignments' in the class 'userToBeAbleToPerformActionsForFileBasedNonGradableAssignments'", e);
        }
    }


    @Test(priority = 7, enabled = true)
    public void userToBeAbleToPerformActionsForFileBasedGradableAssignments() {
        try {

            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Discussion discussion = PageFactory.initElements(driver, Discussion.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            int dataIndex = 311;
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            //Row No - 311 : Login to the application as a Instructor
            //Expected - The user should be successfully logged in to the application
            new Assignment().createFileBasedAssessment(dataIndex);
            new LoginUsingLTI().ltiLogin("311");//Log in as an instructor
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            System.out.println("Url : " + driver.getCurrentUrl());
            if (!driver.getCurrentUrl().contains("secure/learningSpaceInstructorDashboard")) {
                Assert.fail("The user should be successfully logged in to the application");
            }


            //Row No - 312 :Click on the Main navigation on top left hand corner of the screen
            //Expected -The Navigation List should be displayed to the User
            validateMainNavigator();


            //Row No - 313 : Click on the Assignment option and Verify the submenus displayed towards the right
            /*Expected  - "The user should be able to see 4 different submenus i.e.
            1. New Assignment
            2. Current Assignments
            3. My Question Bank
            4. Policies"*/
            assignments.menu_Assignments.click();
            if(assignments.subMenu.isDisplayed()){
                String menuOption = assignments.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }



            //Row No - 314 : Click on the New Assignment option
            /*Expected  - "Create New Assignment pop-up window should be opened with three different types of Assignments i.e.
            1. Custom Assignment
            2. Use Pre Created Assignment
            3. File Based Assignment"*/

            assignments.subMenu_currentAssignments.click();
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            if (!currentAssignments.close_Icon.isDisplayed()) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");




            //Row No - 315 : Click on Use Pre Created Assignment button on the pop -up
            //Expected - The user should be navigated to the Question Banks page
            currentAssignments.usePreCreatedAssignmentButton.click();//click on use pre create assignment
            Assert.assertEquals(currentAssignments.tab_Title.get(1).getAttribute("title"), "Question Banks", "The user is not navigated to the Question Banks page");



            //Row No - 316 : Verify the Created File Based Assignment
            //Expected - File Based Assessment created from the Author's side should be displayed
            new Assignment().searchAssessmentInQuestionBanks(dataIndex);
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "File Based Assessment created from the Author's side is not displayed");


            //Row No - 317 :Click on Assign this link under the Assignment Name
            //Expected - a dropdown should be displayed with the Assessment name
            new UIElement().waitAndFindElement(questionBank.getAssignThisButtton());
            questionBank.getAssignThisButtton().click();
            Thread.sleep(2000);
            Assert.assertEquals(newAssignment.popUP.getText(), assessmentName, "Form should be displayed with the Assessment name");



            //Row No - 318 : Verify the fields displayed in the pop-up
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


            //Row No - 319 : Verify for the Total Points textbox
            //Expected  - Total Points text box should not be displayed
            newAssignment.gradableCheckBox.click();//click on gradable check box
            if(driver.findElement(By.id("total-points")).isDisplayed()){
                Assert.fail("Total Points text box should not be displayed");
            }



            //Row No - 320 : Click on Gradable Checkbox
            //Expected - Gradable checkbox should be checked and the Total Points text box should appear
            newAssignment.gradableCheckBox.click();//click on gradable check box
            Assert.assertEquals(newAssignment.assignField.get(2).getText(), "Total Points: *", "Total points field is not available");
            driver.findElement(By.id("total-points")).sendKeys("2");







            //Row No - 321 : Enter the Mandatory fields and Click on Assign button
            //Expected - The Assessment should be assigned to all the Assigned To members
            driver.findElement(By.xpath("//html/body")).click();
            questionBank.getAssignThisButtton().click();
            new UIElement().waitAndFindElement(By.id("due-date"));
            newAssignment.gradableCheckBox.click();//click on gradable check box
            driver.findElement(By.id("total-points")).sendKeys("2");
            driver.findElement(By.id("due-date")).click();//click on due date
            driver.findElement(By.cssSelector("a[title='Next']")).click();
            driver.findElement(By.linkText(duedate)).click();
            driver.findElement(By.id("due-time")).click();//click on due time
            driver.findElement(By.xpath("(//ul[@class='ui-timepicker-list']/li)[5]")).click();
            newAssignment.button_assign.click();
            Thread.sleep(4000);
            Assert.assertEquals(currentAssignments.tab_Title.get(0).getText(), "Current Assignments", "The user is not redirected to Current Assignments page");
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "The Assessment is not Assigned To all the members");
            Assert.assertEquals(currentAssignments.getAssignmentName().getText().trim(), "(shor) "+assessmentName+"", "The Assignment name is not listed on the Assignment page");




            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //Row No - 322 : Click on the Main navigation on top left hand corner of the screen
            //The user should get the main navigation dropdown displayed
            validateMainNavigator();




            //Row No - 323 :Click on Assignments option for the dropdown
            //Expected  - The submenus should be open
            assignments.menu_Assignments.click();
            if(assignments.subMenu.isDisplayed()){
                String menuOption = assignments.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }





            //Row No - 324 : Click on Current Assignments option
            //Expected - The File Based assignment should be displayed at the top with its mandatory details
            Assert.assertEquals(currentAssignments.tab_Title.get(0).getText(), "Current Assignments", "The user is not redirected to Current Assignments page");
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "The Assessment is not Assigned To all the members");
            Assert.assertEquals(currentAssignments.getAssignmentName().getText().trim(), "(shor) "+assessmentName+"", "The Assignment name is not listed on the Assignment page");



            //Row No - 325 : Verify for the details displayed
            //Expected - All the attached files should be displayed and should have a link
            //repeated


            //Row No - 326 : Click on the link displayed under each File Name
            //Expected - The browser pop up to download the attachment should be opened
            currentAssignments.uploadedResources.click();
            Thread.sleep(15000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);


            //Row No - 330 : Login to the application as a student
            //Expected - The user should be successfully logged in to the application
            new LoginUsingLTI().ltiLogin("311_2");//Log in as a student
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            if (!driver.getCurrentUrl().contains("/lsStudentDashBoard")) {
                Assert.fail("The user is not successfully logged in to the application");
            }



            //Row No - 331 : Click on the Main navigation on top left hand corner of the screen
            //Expected - The Navigation List should be displayed to the User
            validateMainNavigator();
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            if (!driver.getCurrentUrl().contains("/lsStudentDashBoard")) {
                Assert.fail("The user is not successfully logged in to the application");
            }





            //Row No - 332  - Click on Assignments option for the dropdown
            //Expected - The User should be navigated to the Assignments page
            assignments.menu_Assignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("The User is not navigated to the Assignments page");
            }
            Assert.assertEquals(assignments.getAssignmentName().getText(),"(shor) "+assessmentName+"","The Assessment is not displayed on the Assignments page as card view");



            //Row No - 333 : Click on the Assignment name
            //Expected  - The User should be navigated to the Assignments page with the Assignment details
            assignments.getAssignmentName().click();
            Thread.sleep(5000);
            new UIElement().waitAndFindElement(By.cssSelector("span[class='tab_title']"));
            if(!(currentAssignments.tab_Title.size()==5 && currentAssignments.tab_Title.get(0).getText().equals(assessmentName))){
                Assert.fail("The User is not navigated to the Assignments page with the Assignment details");
            }



            //Row No - 334 : Click on Upload File(s) link
            //Expected - The browser pop-up for uploading files should be opened
            //Row No  -335 : Select a file and click on Open button on the browser pop-up
            //Expected - The file should be uploaded and the browser popup should be closed
            fileUploadNotificationMessageValidate(""+dataIndex, notificationMessage);
            Thread.sleep(9000);

            //Row No - 336 : Verify the uploaded file
            //Expected  - The uploaded file should be displayed in Your Response section.
            new UIElement().waitAndFindElement(assignmentResponsesPage.getUploadFileList().get(0));
            if(!(assignmentResponsesPage.getUploadFileList().get(0).isDisplayed()&& assignmentResponsesPage.getUploadFileList().get(0).getText().trim().equals("img.png"))){
                Assert.fail("The uploaded file is not displayed in Your Response section.");
            }




            //Row No - 337 :  Click on Upload File(s) link
            //Expected - The browser pop-up for uploading files should be opened
            //Row No - 338 : Select a file and click on Open button on the browser pop-up
            //Expected - The file should be uploaded and the browser popup should be closed
            fileUploadNotificationMessageValidate("311_2", notificationMessage);


            //Row No - 339 : Verify the newly uploaded file
            //Expected  - The newly added file should come above the previously uploaded file
            //Row No - 340 : Verify the Icon and the file name for the uploaded files
            //Expected  - The icons and the file names should be proper with its extensions
            new UIElement().waitAndFindElement(newAssignment.fieUpload.get(1));
            Thread.sleep(5000);
            List<WebElement> imageLIst = driver.findElements(By.className("ls-uploaded-file"));
            if(!(imageLIst.get(2).isDisplayed()&& imageLIst.get(2).getText().trim().equals("img.jpg"))){
                Assert.fail("The uploaded file is not displayed in Your Response section.");
                //To be uncommented
            }
            Thread.sleep(2000);
            System.out.println("newAssignment.imageIconList.size() : " + newAssignment.imageIconList.size());
            if(newAssignment.imageIconList.size()!=3){
                Assert.fail("The icons are not proper with its extensions");
            }



            //Row No - 341 : Click on Finish Assignment button
            //Expected -The assignment should be submitted and the user should be navigated to the Assignments page
            assignmentTab.finishButton.click();
            lessonPage.continueButton.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("Assignment page should be displayed to the user");
            }
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("The User is not navigated to the Assignments page");
            }
            Assert.assertEquals(assignments.getAssignmentName().getText(),"(shor) "+assessmentName+"","The Assessment is not displayed on the Assignments page as card view");




            //Row No - 342 : Login to the application as a Instructor/ Mentor
            //Expected - The user should be successfully logged in to the application
            new LoginUsingLTI().ltiLogin("311");//Log in as an instructor            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            System.out.println("Url : " + driver.getCurrentUrl());
            if (!driver.getCurrentUrl().contains("secure/learningSpaceInstructorDashboard")) {
                Assert.fail("The user should be successfully logged in to the application");
            }


            //Row No - 343 : Click on the Main navigation on top left hand corner of the screen
            //Expected - The Navigation List should be displayed to the User
            validateMainNavigator();



            //Row No - 344 : Click on Assignments option
            //Expected -  The sub menu should be displayed to the user
            assignments.menu_Assignments.click();
            if(assignments.subMenu.isDisplayed()){
                String menuOption = assignments.subMenu.getText();
                if(!(menuOption.contains("New Assignment") && menuOption.contains("Current Assignments") && menuOption.contains("My Question Bank")&&menuOption.contains("Policies"))){
                    Assert.fail("The sub menu Options are not displayed");
                }
            }else{
                Assert.fail("The sub menu is not displayed");

            }


            //Row No - 345 : Click on Current Assignments option
            //Expected - Current Assignment page should be displayed to the user
            assignments.subMenu_currentAssignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Chapters"));
            if (!driver.getCurrentUrl().endsWith("/assignment")) {
                Assert.fail("Current assignments page should be opened");
            }
            Assert.assertEquals(currentAssignments.tab_Title.get(0).getText(), "Current Assignments", "The user is not redirected to Current Assignments page");





            //Row No - 346 : Verify for the File Based Assessment Name
            //Expected - File Based Assessment Should be displayed
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "File Based Assessment created from the Author's side is not displayed");
            Assert.assertEquals(currentAssignments.getAssignmentName().getText().trim(), "(shor) "+assessmentName+"", "The Assignment name is not listed on the Assignment page");




            //Row No - 347 : Click on View Student Responses link under Actions
            //Expected - The Assignment Response page should be opened as a new tab next to current assignment tab
            //Expected -View Response page should be opened in a new tab
            currentAssignments.getViewGrade_link().click();
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            Thread.sleep(5000);
            System.out.println("currentAssignments.tab_Title.size() : " + currentAssignments.tab_Title.size());
            System.out.println("currentAssignments.tab_Title.get(1).getText() : " + currentAssignments.tab_Title.get(1).getText());

            if(!(currentAssignments.tab_Title.size()==2 && currentAssignments.tab_Title.get(1).getText().contains("Response - (shor) "+assessmentName))){
                Assert.fail("View Response page is not opened in a new tab");
            }





            //Row No - 348 : Verify the Overall Score column
            //Expected - The Overall Score should be a '-' icon
            Assert.assertEquals(assignmentResponsesPage.icon_tickMark.isDisplayed(),true,"The Overall Score should be a '-' icon");




            //Row No - 349 : Hover the mouse on the '-' for a particular student
            //Expected - a link for View Response should be displayed based on hovering the mouse
            new MouseHover().mouserhover("idb-gradebook-question-content");
            new UIElement().waitAndFindElement(By.className("ls-view-response-link"));
            if(!assignmentResponsesPage.getViewResponseLink().isDisplayed()){
                Assert.fail("View Response Link should be displayed based on hovering the mouse");
            }



            //Row No - 350 - Click on View Response link
            //Expected - The user should be navigated to the Student response page in a new tab
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            Thread.sleep(3000);
            if(!(currentAssignments.tab_Title.size()==3 && currentAssignments.tab_Title.get(2).getText().trim().contains("Response - (shor) "+assessmentName+""))){
                Assert.fail("The user is not navigated to the Student response page in a new tab");
            }




            //Row No - 351 : Click on Teacher feedback text box and type the feedback
            //Expected - The Feedback should be displayed in the textbox
            //Row No - 354 : Click on save button
            //Expected - The typed text should be saved and Saved Successfully message should be displayed below the textbox
            String feedback = new RandomString().randomstring(10);
            assignmentResponsesPage.getFeedbackTextArea().click();
            assignmentResponsesPage.getFeedbackTextArea().sendKeys(feedback);


            //Row No  - 352 : Click on the Uploaded Files Link
            //Expected - a browser pop up should open to save the file
            //Row No - 353 : Give the Grade in Score text box
            //Expected - The numeric value should be displayed
            currentAssignments.getPerformanceScoreBox().click();
            currentAssignments.getPerformanceScoreBox().sendKeys("0.6");
            assignmentResponsesPage.getSaveButton().click();
            Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(),"Saved successfully.","The entered text is not saved");




            //Row No - 355 : Navigate to View response page and click on Release Feedback for All button
            /*Expected - "The button label should be changed to Feedback Released and the button should be greyed out.
            The Class Status on the page should be changed to Reviewed from Review in Progress"*/
            assignmentTab.cancelMark.click();
            new UIElement().waitAndFindElement(assignmentTab.cancelMark);
            assignmentResponsesPage.getReleaseGradeForAll().click();
            new UIElement().waitAndFindElement(assignmentResponsesPage.gradeBook_Box);
            Thread.sleep(3000);
            if(!assignmentResponsesPage.gradeBook_Box.getAttribute("title").equals("Grades Released")){
                Assert.fail("The Grade is not released and the button is not disabled");
            }
            Assert.assertEquals(assignmentResponsesPage.getReviewStatus().getText().trim(),"Graded","The Class Status on the page is not changed to Reviewed from Review in Progress");




            //Row No - 356 : Login to the application as a student
            //Expected - The user should be successfully logged in to the application
            new LoginUsingLTI().ltiLogin("311_2");//Log in as a student
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            if (!driver.getCurrentUrl().contains("/lsStudentDashBoard")) {
                Assert.fail("The user is not successfully logged in to the application");
            }




            //Row No - 357 : Click on the Main navigation on top left hand corner of the screen
            //Expected - The Navigation List should be displayed to the User
            validateMainNavigator();
            new UIElement().waitAndFindElement(By.className("ls-user-nav__username"));
            if (!driver.getCurrentUrl().contains("/lsStudentDashBoard")) {
                Assert.fail("The user is not successfully logged in to the application");
            }


            //Row No - 358 : Click on Assignments option for the dropdown
            //Expected - The User should be navigated to the Assignments page
            assignments.menu_Assignments.click();
            new UIElement().waitAndFindElement(By.linkText("All Assignments"));
            if (!driver.getCurrentUrl().endsWith("/studentAssignment")) {
                Assert.fail("The User is not navigated to the Assignments page");
            }
            Assert.assertEquals(assignments.getAssignmentName().getText(),"(shor) "+assessmentName+"","The Assessment is not displayed on the Assignments page as card view");



            //Row No - 359 : Click on the Assignment name
            /*Expected - "The User should be navigated to the performance tab with the Assignment details and Teacher's Feedbacks
            Status should be ""Reviewed"""*/
            assignments.getAssignmentName().click();
            Thread.sleep(1000);
            new UIElement().waitAndFindElement(By.cssSelector("span[class='tab_title']"));
            System.out.println("currentAssignments.tab_Title.size() : " + currentAssignments.tab_Title.size());
            System.out.println("currentAssignments.tab_Title.get(0).getText() : " + currentAssignments.tab_Title.get(0).getText());
            if(!(currentAssignments.tab_Title.size()==4 && currentAssignments.tab_Title.get(0).getText().equals(assessmentName))){
                Assert.fail("The User is not navigated to the Assignments page with the Assignment details");
            }



            //Row No - 360 : Click on Student's Response area
            //Expected - Student's Response area should not be editable
            try {
                driver.findElement(By.className("ls-uploaded-files-wrapper")).clear();
                Assert.fail("Student's Response area should not be editable");
            }catch(Exception e){
                System.out.println("Student's Response area is not editable");
            }



            //Row No - 361 : Click on Teacher's Feedback area
            //Expected - Teacher's Feedback area should not be editable
            try {
                driver.findElement(By.className("feedback-content")).clear();
                Assert.fail("Teacher's Feedback should not be editable");
            }catch(Exception e){
                System.out.println("Teacher's Feedback is not editable");
            }

            //Row No - 362 : Verify the grade displayed for the student as Score
            //Expected - The grade(Score) should be displayed on the right hand side bottom corner
            Assert.assertEquals(assignmentTab.score.getText().trim(),"Score 0.6/2.0","The grade(Score) is not displayed on the right hand side bottom corner");

        } catch (Exception e) {
            Assert.fail("Exception in test script 'userToBeAbleToPerformActionsForFileBasedGradableAssignments' in the class 'userToBeAbleToPerformActionsForFileBasedNonGradableAssignments'", e);
        }
    }






















    private void validateMainNavigator() {
        AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
        Preview preview = PageFactory.initElements(driver, Preview.class);
        Questions questions = PageFactory.initElements(driver, Questions.class);
        CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
        CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
        AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
        Assignments assignments = PageFactory.initElements(driver, Assignments.class);
        Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
        Discussion discussion = PageFactory.initElements(driver, Discussion.class);
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


    private  void fileUploadNotificationMessageValidate(String dataIndex, String notificationMessage) {
        boolean uploaded = true;
        try {
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
            driver.findElement(By.id("uploadFile")).click();
            Thread.sleep(3000);
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            //Thread.sleep(2000);
            new UIElement().waitAndFindElement(By.className("al-notification-message-body"));
            int errmsg = driver.findElements(By.className("al-notification-message-body")).size();
            if (errmsg > 0) {
                String notificationtext = driver.findElement(By.className("al-notification-message-body")).getText();
                System.out.println("notificationtext "+notificationtext);
                if (!notificationtext.equals(notificationMessage))
                    uploaded = false;
            } else {
                Assert.fail("Notification Message during file upload did not appear.");
            }
            System.out.println("uploaded : " + uploaded);
        } catch (Exception e) {
            Assert.fail("Exception in app helper fileUploadValidate in class FileUpload", e);
        }
    }
}
