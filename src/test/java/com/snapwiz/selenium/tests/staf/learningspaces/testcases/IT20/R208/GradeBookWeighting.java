package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT20.R208;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SearchQuestionInCustomCourseAssignemnt;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by shashank on 06-05-2015.
 */
public class GradeBookWeighting extends Driver {

    @Test(priority = 1,enabled = true)
    public void gradeBookWeighting() {
        try {
            int dataIndex=11;
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "11");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "11");
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentTab assignmentTab= PageFactory.initElements(driver, AssignmentTab.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            Gradebook gradebook=PageFactory.initElements(driver,Gradebook.class);

            new LoginUsingLTI().ltiLogin("12");//login as student
            new LoginUsingLTI().ltiLogin("11");//login as instructor
            new Navigator().NavigateTo("Gradebook"); //navigate to Gradebook

            //validate default elements at gradebook page
            Assert.assertTrue(gradebook.getGradebookWeighting().isDisplayed(),"Gradebook weighting is not displaying on screen");
            Assert.assertTrue(gradebook.getExportToCsv().getAttribute("style").contains("hidden"), "Export to csv is displaying on screen");
            Assert.assertTrue(gradebook.getPostAMessage().getAttribute("style").contains("hidden"),"Post a message is displaying on screen");
            Assert.assertTrue(gradebook.getGradebookWeighting().isDisplayed(),"Gradebook weighting is not displaying on screen");
            gradebook.getGradebookWeighting().click();//click on Gradebook Weighting button
            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(gradebook.getgradebookWeightingSaveButton()));
            //verify the lenght of each
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(0).getAttribute("maxlength"),"3","Lenght is not equal to 3");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(1).getAttribute("maxlength"),"3","Lenght is not equal to 3");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(2).getAttribute("maxlength"),"3","Lenght is not equal to 3");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(3).getAttribute("maxlength"),"3","Lenght is not equal to 3");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(4).getAttribute("maxlength"),"3","Lenght is not equal to 3");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(5).getAttribute("maxlength"),"3","Lenght is not equal to 3");

            //verify the value of each field
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(0).getAttribute("value"),"0","default value is not equal to 0");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(1).getAttribute("value"), "0", "default value is not equal to 0");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(2).getAttribute("value"),"0","default value is not equal to 0");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(3).getAttribute("value"),"0","default value is not equal to 0");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(4).getAttribute("value"),"0","default value is not equal to 0");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(5).getAttribute("value"),"100","default value is not equal to 0");
            Assert.assertEquals(gradebook.getTotalBoxGradebookWeghting().getText(),"100","default value is not equal to 100");

            //try to type more than 3digits in every field and check it should allow more than 3 digits

            gradebook.getEnterGradebookWeighting().get(0).clear();//clear practice textField
            gradebook.getEnterGradebookWeighting().get(0).sendKeys("12345");//type in practice textField
            gradebook.getEnterGradebookWeighting().get(1).clear();//clear Homework
            gradebook.getEnterGradebookWeighting().get(1).sendKeys("12345");//Type in Homework
            gradebook.getEnterGradebookWeighting().get(2).clear();//clear Quiz
            gradebook.getEnterGradebookWeighting().get(2).sendKeys("12345");//Type in Quiz
            gradebook.getEnterGradebookWeighting().get(3).clear();//clear Test
            gradebook.getEnterGradebookWeighting().get(3).sendKeys("12345");//Type in Test
            gradebook.getEnterGradebookWeighting().get(4).clear();//clear other
            gradebook.getEnterGradebookWeighting().get(4).sendKeys("12345");//Type in other
            gradebook.getEnterGradebookWeighting().get(5).clear();//clear uncategorized
            gradebook.getEnterGradebookWeighting().get(5).sendKeys("12345");//type in uncategorized
            gradebook.getgradebookWeightingSaveButton().click();//click on save button


            //verify the value of each field
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(0).getAttribute("value").length(),3,"Length is not equal to 3");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(1).getAttribute("value").length(),3,"Length is not equal to 3");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(2).getAttribute("value").length(),3,"Length is not equal to 3");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(3).getAttribute("value").length(),3,"Length is not equal to 3");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(4).getAttribute("value").length(),3,"Length is not equal to 3");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(5).getAttribute("value").length(),3,"Length is not equal to 3");

            //click on save button to check total box should change to red
            gradebook.getgradebookWeightingSaveButton().click();
            Thread.sleep(1000);

            Assert.assertTrue(gradebook.getTotalBoxGradebookWeghting().getAttribute("style").contains("solid red"),"Total box outline dose not change to red");

            //verify when user clear the  field and do tab out field get default value of 0
            gradebook.getEnterGradebookWeighting().get(0).clear();//clear practice textField
            driver.findElement(By.xpath("//span[text()='Practice']")).click();

            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(0).getAttribute("value"),"0","default value is not equal to 0");
            //verify cancel button
            gradebook.getCancelGradebookWeighting().click();
            Thread.sleep(2000);
            gradebook.getGradebookWeighting().click();
            gradebook.getEnterGradebookWeighting().get(0).clear();//clear practice textField
            gradebook.getEnterGradebookWeighting().get(0).sendKeys("10");//type in practice textField
            gradebook.getEnterGradebookWeighting().get(1).clear();//clear Homework
            gradebook.getEnterGradebookWeighting().get(1).sendKeys("10");//Type in Homework
            gradebook.getEnterGradebookWeighting().get(2).clear();//clear Quiz
            gradebook.getEnterGradebookWeighting().get(2).sendKeys("10");//Type in Quiz
            gradebook.getEnterGradebookWeighting().get(3).clear();//clear Test
            gradebook.getEnterGradebookWeighting().get(3).sendKeys("10");//Type in Test
            gradebook.getEnterGradebookWeighting().get(4).clear();//clear other
            gradebook.getEnterGradebookWeighting().get(4).sendKeys("10");//Type in other
            gradebook.getEnterGradebookWeighting().get(5).clear();//clear uncategorized
            gradebook.getEnterGradebookWeighting().get(5).sendKeys("50");//type in uncategorized
            gradebook.getgradebookWeightingSaveButton().click();//click on save button
            Assert.assertEquals(gradebook.getTopNotificationMessage().getText(),"It may take a few minutes for the changes to take effect. Please refresh to view the changes.","Notification message is not displaying");

            driver.navigate().refresh();//page refresh
            new Navigator().NavigateTo("Question Banks"); //navigate to Question Banks
            assignmentTab.addToMyQuestionBank.click();
            new Navigator().NavigateTo("My Question Bank"); //navigate to My Question Banks
            gradebook.getAssignThis().get(0).click();
            assignmentTab.gradable.click();//click on checkbox gradable
            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(assignmentTab.AssignButton));
            //check By default value should be No assignment category
            Assert.assertTrue(gradebook.getUncategorized().size() == 2, "By default No assignment category is not selected");
            //select different option
            gradebook.getUncategorized().get(0).click();
            gradebook.getPractice().get(0).click();//click on practice
            //validate selected value should be practice
            Assert.assertTrue(gradebook.getPractice().size() == 2, "Practice is not selected is not selected");
            assignmentTab.gradable.click();//click on checkbox gradable
            Thread.sleep(1000);
            //validate graphing weighting option should not present on the screen

            Assert.assertTrue(!gradebook.getGradebookWeightingLabel().isDisplayed(),"Gradebook Weighting option is still present on screen");
            assignmentTab.gradable.click();//click on checkbox gradable
            Thread.sleep(1000);
            Assert.assertTrue(gradebook.getGradebookWeightingLabel().isDisplayed(), "Gradebook weighting option not present on the screen");
           //bug logged Assert.assertTrue(gradebook.getNoAssignmentCategory().size()==2,"By default No assignment category is not selected");
            new Navigator().NavigateTo("Question Banks");
            Thread.sleep(1000);
            List<WebElement> assign=driver.findElements(By.cssSelector("span.assign-this"));
            for(int i=0;i<assign.size();i++)
            {
                if(assign.get(1).isDisplayed()){
                    assign.get(1).click();
                }
            }

            //gradebook.getAssignThis().get(1).click();
            assignmentTab.gradable.click();//click on checkbox gradable
            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(assignmentTab.AssignButton));
            //check By default value should be No assignment category
            Assert.assertTrue(gradebook.getUncategorized().size()==2,"By default No assignment category is not selected");
            //navigate to new assignment
            new Navigator().NavigateTo("New Assignment");
            //click on file base assessment
            currentAssignments.createFileBasedAssignmentButton.click();//click on file based assignment
            //type assessment name
            newAssignment.assessmentNameTextBox.click();//click on the text box
            Thread.sleep(2000);
            newAssignment.assessmentName_TextBox.sendKeys("Assessment");
            Thread.sleep(2000);
            //click on assign now button
            gradebook.getAssignNow().click();
            assignmentTab.gradable.click();//click on checkbox gradable
            Thread.sleep(1000);
            Assert.assertTrue(gradebook.getGradebookWeightingLabel().isDisplayed(), "Gradebook weighting option not present on the screen");

            //check gradable weighting in custom assignment
            new Navigator().NavigateTo("New Assignment");//click on my question bank
            gradebook.getCreateCustomAssignment().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("11");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            gradebook.getAssignNow().click();
            assignmentTab.gradable.click();//click on checkbox gradable
            Thread.sleep(1000);
            Assert.assertTrue(gradebook.getGradebookWeightingLabel().isDisplayed(), "Gradebook weighting option not present on the screen");

            //check gradable weighting in E-Textbook
            new Navigator().NavigateTo("e-Textbook");//click on e-textbook
            gradebook.getArrowOnAssessment().get(0).click();
            gradebook.getAssignThisFromETextbook().get(0).click();
            assignmentTab.gradable.click();//click on checkbox gradable
            Thread.sleep(1000);
            Assert.assertTrue(gradebook.getGradebookWeightingLabel().isDisplayed(), "Gradebook weighting option not present on the screen");

            //Gradebook category with zero percentage should not display in the in the grade weighting option
            new Navigator().NavigateTo("Gradebook"); //navigate to Gradebook
            gradebook.getGradebookWeighting().click();//click on Gradebook Weighting button
            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(gradebook.getgradebookWeightingSaveButton()));
            //type zero in practice
            gradebook.getEnterGradebookWeighting().get(0).clear();//clear practice textField
            gradebook.getEnterGradebookWeighting().get(0).sendKeys("0");//type in practice textField
            gradebook.getEnterGradebookWeighting().get(1).clear();//clear Homework
            gradebook.getEnterGradebookWeighting().get(1).sendKeys("20");//Type in Homework
            gradebook.getgradebookWeightingSaveButton().click();//click on save button
            driver.navigate().refresh();//page refresh
            new Navigator().NavigateTo("Question Banks");//navigate to question bank
            Thread.sleep(1000);
            gradebook.getAssignThis().get(2).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(assignmentTab.AssignButton));
            //check Practice option should not present in the dropdown
            assignmentTab.gradable.click();//click on checkbox gradable
            Thread.sleep(1000);
            Assert.assertTrue(gradebook.getPractice().size()==0,"Practice option is still present in dropdown");

            //double click on Gradable check box so that by default value should be selected as 'No Assignment category'
            assignmentTab.gradable.click();//click on checkbox gradable
            assignmentTab.gradable.click();//click on checkbox gradable
            Thread.sleep(2000);
            //assign this assessment with gradebook weighting as 'No Assignment Category'
            driver.findElement(By.id("due-time")).click();
            driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();

            driver.findElement(By.id("due-date")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText(duedate)).click();
            Thread.sleep(2000);
            assignmentTab.AssignButton.click();
            Thread.sleep(3000);
            //navigate to gradebook
            new Navigator().NavigateTo("Gradebook");
            //there should be no assignment weightage at assignment
            Assert.assertTrue(gradebook.getAssignmentWeightage().size()==1,"Assignment weightage is dispalying for assignment");
            //again assign assessment with grading weighting as "Quiz"
            new Navigator().NavigateTo("Question Banks");//navigate to question bank
            Thread.sleep(1000);
            gradebook.getAssignThis().get(1).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(assignmentTab.AssignButton));
            //Select gardebook weighting as "quiz"
            assignmentTab.gradable.click();//click on checkbox gradable
            Thread.sleep(1000);
            gradebook.getUncategorized().get(0).click();
            gradebook.getQuiz().get(0).click();//click on Quiz
            Thread.sleep(2000);
            //assign this assessment with gradebook weighting as 'No Assignment Category'
            driver.findElement(By.id("due-time")).click();
            driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();

            driver.findElement(By.id("due-date")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText(duedate)).click();
            Thread.sleep(2000);
            assignmentTab.AssignButton.click();
            Thread.sleep(3000);
            //navigate to gradebook
            new Navigator().NavigateTo("Gradebook");
            //validate default elements at gradebook page
            Assert.assertTrue(gradebook.getGradebookWeighting().isDisplayed(),"Gradebook weighting is not displaying on screen");
            Assert.assertTrue(gradebook.getExportToCsv().isDisplayed(),"Export to csv is not displaying on screen");
            Assert.assertTrue(gradebook.getPostAMessage().isDisplayed(),"Post a message is not displaying on screen");
            //no assignment weightage at assignment should present
            Assert.assertEquals(gradebook.getAssignmentWeightage().get(0).getAttribute("data-title"),"Quiz: 10% weightage","Assignment weightage not displaying on screen");

            //update current assignment and select gradebook weighting as 'No Assignment Category'
            new Navigator().NavigateTo("Current Assignments");
            gradebook.getUpdateAssignment().get(1).click();
            gradebook.getReassignAssessment().click();
            gradebook.getQuiz().get(0).click();
            gradebook.getNoAssignmentCategory().get(0).click();
            driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();
            Thread.sleep(3000);
            new Navigator().NavigateTo("Gradebook");
            //no assignment weightage at assignment should present
            Assert.assertTrue(gradebook.getAssignmentWeightage().size()==1,"Assignment weightage not displaying on screen");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class GradeBookWeighting in method gradeBookWeighting",e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void editGradeBookWeighing() {
        try {
            int dataIndex=11;
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "12");
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(13));
            AssignmentTab assignmentTab= PageFactory.initElements(driver, AssignmentTab.class);
            Gradebook gradebook=PageFactory.initElements(driver,Gradebook.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new Assignment().create(13);
            for(int i=0;i<9;i++){
                new Assignment().addQuestions(13,"truefalse","");
            }

            new Assignment().create(12);
            for(int i=0;i<9;i++){
                new Assignment().addQuestions(12,"truefalse","");
            }
            new LoginUsingLTI().ltiLogin("14");//login as student

            new LoginUsingLTI().ltiLogin("13");//login as instructor

            new Navigator().NavigateTo("Gradebook");
            gradebook.getGradebookWeighting().click();


            gradebook.getEnterGradebookWeighting().get(0).clear();//clear practice textField
            gradebook.getEnterGradebookWeighting().get(0).sendKeys("60");//type in practice textField
            gradebook.getEnterGradebookWeighting().get(5).clear();//clear uncategorized
            gradebook.getEnterGradebookWeighting().get(5).sendKeys("40");//type in uncategorized
            gradebook.getgradebookWeightingSaveButton().click();//click on save button
            driver.navigate().refresh();//page refresh

            //Click on Gradebook Weightage button

            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(13);
            new Navigator().NavigateTo("Question Banks"); //navigate to Question Banks
            Thread.sleep(1000);
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(3000);
            gradebook.getAssignThis().get(0).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(assignmentTab.AssignButton));
            //Select gardebook weighting as "quiz"
            assignmentTab.gradable.click();//click on checkbox gradable
            Thread.sleep(1000);
            gradebook.getUncategorized().get(0).click();
            gradebook.getNoAssignmentCategory().get(0).click();//click on Quiz
            Thread.sleep(2000);
            //assign this assessment with gradebook weighting as 'No Assignment Category'
            driver.findElement(By.id("due-time")).click();
            driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText(duedate)).click();
            Thread.sleep(2000);
            assignmentTab.AssignButton.click();
            Thread.sleep(3000);


            new Navigator().NavigateTo("Question Banks"); //navigate to Question Banks
            Thread.sleep(1000);
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assessmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(3000);
            gradebook.getAssignThis().get(0).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(assignmentTab.AssignButton));
            //Select gardebook weighting as "quiz"
            assignmentTab.gradable.click();//click on checkbox gradable
            Thread.sleep(1000);
            gradebook.getUncategorized().get(0).click();
            gradebook.getNoAssignmentCategory().get(0).click();//click on Quiz
            Thread.sleep(2000);
            //assign this assessment with gradebook weighting as 'No Assignment Category'
            driver.findElement(By.id("due-time")).click();
            driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText(duedate)).click();
            Thread.sleep(2000);
            assignmentTab.AssignButton.click();
            Thread.sleep(3000);


            new LoginUsingLTI().ltiLogin("14");//login as student
            new Assignment().submitAssignmentAsStudent(13);

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().clickonAssignment(assessmentname);//open the assignment to

            for(int i=0;i<5;i++)
            {
                new AttemptQuestion().trueFalse(false,"incorrect",192);
                assignments.getNextQuestion().click();
            }

            for(int i=0;i<4;i++)
            {
                new AttemptQuestion().trueFalse(false,"correct",192);
                assignments.getNextQuestion().click();
            }
            new AttemptQuestion().trueFalse(false,"correct",192);
            assignments.getFinishAssignment().click();
            Thread.sleep(2000);
            //add perspective for DW
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            new Click().clickByclassname("learning-activity-title"); //ip 31
            String perspective = new RandomString().randomstring(2);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignemnt



            new LoginUsingLTI().ltiLogin("13");//login as instructor
            new Assignment().releaseGrades(13,"Release Grade for All");
            new Assignment().releaseGrades(12,"Release Grade for All");

            //Navigate to Assignments
            new Navigator().NavigateTo("Current Assignments");
            driver.findElement(By.cssSelector("span.ls-grade-book-assessment")).click();
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            Thread.sleep(1000);
            new WebDriverWait(driver, 300).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));	//click on View Response
            Thread.sleep(5000);

            //Enter grade for DW Assignment
            driver.findElement(By.cssSelector("input#view-user-question-performance-score-box")).clear();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("input#view-user-question-performance-score-box")).sendKeys("0.6");
            //click on save button
            driver.findElement(By.cssSelector("span.view-user-discussion-performance-save-btn")).click();
            new Navigator().NavigateTo("Current Assignments");
            driver.findElement(By.cssSelector("span.ls-grade-book-assessment")).click();

            driver.findElement(By.cssSelector("div[title='Release Grade for All']")).click();




            new LoginUsingLTI().ltiLogin("14");//login as student
            new Navigator().NavigateTo("Dashboard");
            Assert.assertTrue(driver.findElement(By.xpath("//*[text()='71']")).isDisplayed(),"Percentage isnot displaying properly");



            new Navigator().NavigateTo("Assignments");
            Assert.assertEquals(gradebook.getOverallScoreStudentSide().getText(),"61","Percentage is not displaying properly");
            //select filter as 'Question Assignment'
            gradebook.getAllAssignments().click();
            gradebook.getQuestionAssignment().click();
            Assert.assertEquals(gradebook.getOverallScoreStudentSide().getText(),"71","Percentage is not displaying properly");
            gradebook.getQuestionAssignment().click();
            gradebook.getDiscussionAssignment().click();
            Assert.assertEquals(gradebook.getOverallScoreStudentSide().getText(),"40","Percentage is not displaying properly");



            new LoginUsingLTI().ltiLogin("13");//login as student
            //navigate to gradebook
            new Navigator().NavigateTo("Gradebook");
            Assert.assertEquals(gradebook.getOverallScore().getText(),"61","Percentage is not displaying properly");
            Assert.assertEquals(gradebook.getAssignmentWeightage().get(0).getAttribute("data-title"),"Uncategorized: 40% weightage","Assignment weightage not displaying on screen");

            //Click on Gradebook Weightage button
            gradebook.getAssignmentWeightage().get(0).click();
            Assert.assertEquals(gradebook.getHelpNotification().getText(),"Uncategorized: 40% weightage");
            gradebook.getGradebookWeighting().click();
            gradebook.getEnterGradebookWeighting().get(0).clear();//clear practice textField
            gradebook.getEnterGradebookWeighting().get(0).sendKeys("10");//type in practice textField
            gradebook.getEnterGradebookWeighting().get(5).clear();//clear uncategorized
            gradebook.getEnterGradebookWeighting().get(5).sendKeys("90");//type in uncategorized
            gradebook.getgradebookWeightingSaveButton().click();//click on save button
            driver.navigate().refresh();//page refresh
            Assert.assertEquals(gradebook.getAssignmentWeightage().get(0).getAttribute("data-title"),"Uncategorized: 90% weightage","Assignment weightage not displaying on screen");
            //Click on Gradebook Weightage button
            gradebook.getAssignmentWeightage().get(0).click();
            Assert.assertEquals(gradebook.getHelpNotification().getText(),"Uncategorized: 90% weightage");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class GradeBookWeighting in method editGradeBookWeighing",e);
        }
    }



    @Test(priority = 3,enabled = true)
    public void changeGradebookWeightageCheckInDifferentClassSection() {
        try {

            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            Gradebook gradebook=PageFactory.initElements(driver,Gradebook.class);

            new LoginUsingLTI().ltiLogin("15");//login as instructor
            new LoginUsingLTI().ltiLogin("16");//login as instructor
            new Navigator().NavigateTo("Gradebook");
            gradebook.getGradebookWeighting().click();


            gradebook.getEnterGradebookWeighting().get(0).clear();//clear practice textField
            gradebook.getEnterGradebookWeighting().get(0).sendKeys("60");//type in practice textField
            gradebook.getEnterGradebookWeighting().get(5).clear();//clear uncategorized
            gradebook.getEnterGradebookWeighting().get(5).sendKeys("40");//type in uncategorized
            gradebook.getgradebookWeightingSaveButton().click();//click on save button
            driver.navigate().refresh();//page refresh
            //change class section
            lessonPage.getClassSectionDropDown().click();
            lessonPage.getClassName().click();
            new Navigator().NavigateTo("Gradebook");
            gradebook.getGradebookWeighting().click();
            //verify the value of each field
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(0).getAttribute("value"),"0","default value is not equal to 0");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(1).getAttribute("value"), "0", "default value is not equal to 0");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(2).getAttribute("value"),"0","default value is not equal to 0");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(3).getAttribute("value"),"0","default value is not equal to 0");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(4).getAttribute("value"),"0","default value is not equal to 0");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(5).getAttribute("value"),"100","default value is not equal to 0");
            Assert.assertEquals(gradebook.getTotalBoxGradebookWeghting().getText(),"100","default value is not equal to 100");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class GradeBookWeighting in method changeGradebookWeightageCheckInDifferentClassSection",e);
        }
    }



    @Test(priority = 4,enabled = true)
    public void changeGradebookWeightageCheckInSameClassSection() {
        try {

            Gradebook gradebook=PageFactory.initElements(driver,Gradebook.class);
            new LoginUsingLTI().ltiLogin("17");//login as instructor
            new LoginUsingLTI().ltiLogin("18");//login as instructor
            new Navigator().NavigateTo("Gradebook");
            gradebook.getGradebookWeighting().click();
            gradebook.getEnterGradebookWeighting().get(0).clear();//clear practice textField
            gradebook.getEnterGradebookWeighting().get(0).sendKeys("60");//type in practice textField
            gradebook.getEnterGradebookWeighting().get(5).clear();//clear uncategorized
            gradebook.getEnterGradebookWeighting().get(5).sendKeys("40");//type in uncategorized
            gradebook.getgradebookWeightingSaveButton().click();//click on save button
            driver.navigate().refresh();//page refresh
            //change class section
            new LoginUsingLTI().ltiLogin("17");//login as instructor
            new Navigator().NavigateTo("Gradebook");
            gradebook.getGradebookWeighting().click();
            //verify the value of each field
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(0).getAttribute("value"),"60","default value is not equal to 0");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(1).getAttribute("value"), "0", "default value is not equal to 0");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(2).getAttribute("value"),"0","default value is not equal to 0");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(3).getAttribute("value"),"0","default value is not equal to 0");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(4).getAttribute("value"),"0","default value is not equal to 0");
            Assert.assertEquals(gradebook.getEnterGradebookWeighting().get(5).getAttribute("value"),"40","default value is not equal to 0");
            Assert.assertEquals(gradebook.getTotalBoxGradebookWeghting().getText(),"100","default value is not equal to 100");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class GradeBookWeighting in method changeGradebookWeightageCheckInSameClassSection",e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void viewOverallScoreWithPolicy() {
        try {
              String assignmentpolicynamePolicyFirst=ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(19));
            String assignmentpolicynamePolicySecond=ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(21));
            String assignmentpolicynamePolicyThird=ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(23));
            Gradebook gradebook=PageFactory.initElements(driver,Gradebook.class);
            new Assignment().create(19);
           new LoginUsingLTI().ltiLogin("20");//login as Student
            new LoginUsingLTI().ltiLogin("19");//login as instructor

            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            //create policy as "Auto-release on assignment submission"
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicynamePolicyFirst, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy
            new Assignment().assignToStudent(19);//login as instructor
            new LoginUsingLTI().ltiLogin("20");//login as student
            new Assignment().submitAssignmentAsStudent(19);
            new Navigator().NavigateTo("Assignments");
            Assert.assertEquals(gradebook.getOverallScoreStudentSide().getText(),"100%","Percentage is not displaying properly");
            Assert.assertEquals(gradebook.getAssignmentCategoryStudentSide().getText(),"Uncategorized: 100%","Assignment Category is not displaying ");


            new LoginUsingLTI().ltiLogin("22");//login as Student
            new LoginUsingLTI().ltiLogin("21");//login as instructor

            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            //create policy as "Auto-release on assignment submission"
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicynamePolicySecond, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy*//*
            new Assignment().assignToStudent(21);//login as instructor
            new LoginUsingLTI().ltiLogin("22");//login as student
            new Assignment().submitAssignmentAsStudent(21);
            new Navigator().NavigateTo("Assignments");
            Assert.assertEquals(gradebook.getOverallScoreStudentSide().getText(),"100%","Percentage is not displaying properly");
            Assert.assertEquals(gradebook.getAssignmentCategoryStudentSide().getText(),"Uncategorized: 100%","Assignment Category is not displaying ");


            new LoginUsingLTI().ltiLogin("24");//login as Student
            new LoginUsingLTI().ltiLogin("23");//login as instructor

            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            //create policy as "Auto-release on assignment submission"
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicynamePolicyThird, "Policy description21", "2", null, false, "1", "", "", "", "", "");//policy 4
            new Assignment().assignToStudent(23);//login as instructor
            new LoginUsingLTI().ltiLogin("24");//login as student
            new Assignment().submitAssignmentAsStudent(23);
            new LoginUsingLTI().ltiLogin("23");
            new Assignment().releaseGrades(23,"Release Grade for All");
            new LoginUsingLTI().ltiLogin("24");//login as student
            new Navigator().NavigateTo("Assignments");
            Assert.assertEquals(gradebook.getOverallScoreStudentSide().getText(),"100%","Percentage is not displaying properly");
            Assert.assertEquals(gradebook.getAssignmentCategoryStudentSide().getText(),"Uncategorized: 100%","Assignment Category is not displaying ");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class GradeBookWeighting in method viewOverallScoreWithPolicy",e);
        }
    }

}
