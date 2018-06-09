package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1818;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 1/13/2015.
 */
public class InstructorShouldAbleToViewTheAssignment extends Driver {
    @Test(priority = 1, enabled = true)
    public void iShouldAbleToViewTheAssignmentWithTheRemovedQuestions() {
        try {
            //tc row no 42
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "42");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "42");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("42");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("42");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(42);//assign assignment
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIcon().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getAssign().click();//click on assign
            currentAssignments.yesLinkAfterAssign.click();//click on yes

            Thread.sleep(1000);
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            boolean elementFound = false;
            try{
                driver.findElement(By.linkText("Delete This"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"Delete option is available");
            Thread.sleep(1000);
            myQuestionBank.getPreviewButton().click();//click on preview of “Assessment Name - V1”
            int questionCount = myQuestionBank.getQuestionCount().size();
            if(questionCount > 1){
                Assert.fail("List of question preview except the deleted question is not displaying");
            }
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(1000);
            new AssignLesson().Assigncustomeassignemnt(421);//assign assignment
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getPreviewButtonOfOriginal().click();//click on preview of original custom assignment

            int questionCount1 = myQuestionBank.getQuestionCount().size();
            if(questionCount1 > 2){
                Assert.fail("Deleted question is not displaying");
            }
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            boolean elementFound1 = false;
            try{
                driver.findElement(By.xpath("(//span[@title='Delete This'])[1]"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1,true,"Delete option is not available");
            myQuestionBank.getDeleteButtonOfOriginal().click();//click on delete of original assignment
            myQuestionBank.getYesOnNotificationMessage().click();//click on yes of notification message
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            Thread.sleep(2000);
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
            currentAssignments.getYesOnUnAssignPopUp().click();//click on yes of unassign popup
            Thread.sleep(2000);
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
            currentAssignments.getYesOnUnAssignPopUp().click();//click on yes of unassign popup
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            Thread.sleep(2000);
            String delete=myQuestionBank.getDeleteButton().getAttribute("title");
            System.out.println("Delete:"+delete);
            Assert.assertEquals(delete, "Delete This", "Delete option is not available");
            myQuestionBank.getDeleteButton().click();
            myQuestionBank.getYesOnNotificationMessage().click();//click on yes of notification message


        } catch (Exception e) {
            Assert.fail("Exception in test case iShouldAbleToViewTheAssignmentWithTheRemovedQuestions in class InstructorShouldAbleToViewTheAssignment", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void anyStudentHasAccessedTheAssignmentBeforeDelete() {
        try {
            //tc row no 51
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "51");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "51");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new LoginUsingLTI().ltiLogin("51");//create instructor
            new LoginUsingLTI().ltiLogin("55");//create student
            new LoginUsingLTI().ltiLogin("51");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("51");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(6000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(51);//assign assignment
            Thread.sleep(2000);
            currentAssignments.getViewGrade_link().click();//click on view student responses
            String points=assignmentResponsesPage.getTotalPoints().getText();
            Assert.assertEquals(points,"Total Points: 4","Total points is not displaying");
            currentAssignments.getCurrentAssignmentTitle().click();//click on current assignment
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIcon().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getAssign().click();//click on assign
            currentAssignments.yesLinkAfterAssign.click();//click on yes
            Thread.sleep(1000);
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student responses
            boolean elementFound1 = false;
            try{
                driver.findElement(By.xpath("(//div[contains(@class,'idb-gradebook-content-coloumn idb-gradebook-question-coloumn')])[4]"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1,false,"Deleted question column is available");
            String points1=assignmentResponsesPage.getTotalPoints().getText();
            Assert.assertEquals(points1,"Total Points: 3","Total points is not updated after delete a question");
            Thread.sleep(2000);
            assignmentResponsesPage.getPreviewOfAssignment().click();
            int questionCount = myQuestionBank.getQuestionCount().size();
            if(questionCount > 3){
                Assert.fail("List of question preview except the deleted question is not displaying");
            }
            currentAssignments.getCurrentAssignmentTitle().click();//click on current assignment
            currentAssignments.getPreviewOfAssignmentOnCurrentAssignment().click();//click on preview of assignment
            int questionCount1 = myQuestionBank.getQuestionCount().size();
            if(questionCount1 > 3){
                Assert.fail("List of question preview except the deleted question is not displaying");
            }
            new LoginUsingLTI().ltiLogin("55");//login as student
            new Assignment().submitAssignmentAsStudent(51); //submit assignment
            new LoginUsingLTI().ltiLogin("51");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getViewGrade_link().click();//click on view student responses
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            currentAssignments.getNextArrow().click();
            currentAssignments.getNextArrow().click();
            currentAssignments.getCurrentAssignmentTitle().click();//click on current assignment
            currentAssignments.getViewGrade_link().click();//click on view student responses
            boolean elementFound2 = false;
            try{
                driver.findElement(By.xpath("(//div[contains(@class,'idb-gradebook-content-coloumn idb-gradebook-question-coloumn')])[4]"));
                elementFound2 = true;
            } catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound2,false,"View resposne for deleted question is displaying");
            new Assignment().provideGradeToStudentForMultipleQuestions(51);
            new Assignment().releaseGrades(51,"Release Grade for All");
            String grade=assignmentResponsesPage.getGrade().getText();
            Assert.assertEquals(grade,"2.4","Total grade is not displaying after grade released");


        } catch (Exception e) {
            Assert.fail("Exception in test case anyStudentHasAccessedTheAssignmentBeforeDelete in class InstructorShouldAbleToViewTheAssignment", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void  questionIsDeletedAfterSomeStudentsHaveAttempted() {
        try {
            //tc row no 65
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "65");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(65));
            new Assignment().create(65);
            new Assignment().addQuestions(65, "writeboard", "");
            new Assignment().addQuestions(65, "essay", "");
            new LoginUsingLTI().ltiLogin("65_1");//create student
            new LoginUsingLTI().ltiLogin("65_2");//create student
            new LoginUsingLTI().ltiLogin("65_3");//create student
            new LoginUsingLTI().ltiLogin("65_4");//create student
            new LoginUsingLTI().ltiLogin("65");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();

            List<WebElement> a=driver.findElements(By.cssSelector("span[title='Customize This']"));
            for(WebElement b:a){
                if(b.isDisplayed()){
                    b.click();
                }
            }
            //click on customize this link
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("65");//select three question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(65);//assign assignment
            new LoginUsingLTI().ltiLogin("65_2");//login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Thread.sleep(12000);
            new LoginUsingLTI().ltiLogin("65_3");//login as student3
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().essay(false,"incorrect",65);
            new Assignment().nextButtonInQuestionClick();
           // assignments.getNextQuestion().click();
            new AttemptQuestion().writeBoard(false,"incorrect",65);
            Thread.sleep(3000);
            new Assignment().nextButtonInQuestionClick();
           // assignments.getNextQuestion().click();
            new LoginUsingLTI().ltiLogin("65_4");//login as student4
            new Assignment().submitAssignmentAsStudent(65); //submit assignment

            new LoginUsingLTI().ltiLogin("65");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIconTwo().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getExtendDueDate().click();//click on extend due date
            driver.findElement(By.id("extended-due-time")).click();//enter time
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals("12:15 AM")) {
                    time.click();
                    break;
                }
            }
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)","");
            Thread.sleep(2000);
            currentAssignments.getUpdate().click();
            driver.findElement(By.className("extend-due-submit-yes")).click();
            Thread.sleep(3000);
            //tc row no 67
            currentAssignments.getViewGrade_link().click();//click on view response
            String totalPoint=assignmentResponsesPage.getTotalPoints().getText();
            System.out.println("totalPoint:"+totalPoint);
            Assert.assertEquals(totalPoint,"Total Points: 3","total point is not including the deleted question");
            String deletedQuestion=assignmentResponsesPage.getDeletedQuestion().getText();
            Assert.assertEquals(deletedQuestion,"Q3","Deleted Question column is not displaying");
            assignmentResponsesPage.getPreviewOfAssignment().click();
            int questionCount = myQuestionBank.getQuestionCount().size();
            if(questionCount > 2){
                Assert.fail("List of question preview except the deleted question is not displaying");
            }
            currentAssignments.getCurrentAssignmentTitle().click();//click on current assignment
            currentAssignments.getPreviewOfAssignmentOnCurrentAssignment().click();//click on preview of assignment
            int questionCount1 = myQuestionBank.getQuestionCount().size();
            if(questionCount1 > 2){
                Assert.fail("List of question preview except the deleted question is not displaying");
            }
            //tc row no 75
            new LoginUsingLTI().ltiLogin("65_1");//login as student1
            new Assignment().submitAssignmentAsStudent(65); //submit assignment
            new LoginUsingLTI().ltiLogin("65_2");//login as student2
            new Assignment().submitAssignmentAsStudent(65); //submit assignment
            new LoginUsingLTI().ltiLogin("65_3");//login as student3
            new Assignment().submitAssignmentAsStudent(65); //submit assignment
            new LoginUsingLTI().ltiLogin("65");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            String status=currentAssignments.status_reviewInProgress.getText();
            Assert.assertEquals(status,"Needs Grading","status is not showing review in progress");
            Thread.sleep(2000);
            String submitBoxCount=currentAssignments.getSubmittedBoxCount().getText();
            Assert.assertEquals(submitBoxCount,"4","submitbox is not displaying the total student count");
            //tc row no 77
            currentAssignments.getViewGrade_link().click();//click on view student responses
            String deletedQuestion1=assignmentResponsesPage.getDeletedQuestion().getText();
            Assert.assertEquals(deletedQuestion1,"Q3","Deleted Question column is not displaying");
            String crossIcon=assignmentResponsesPage.getCrossIconOnDeletedQuestion().getAttribute("title");
            Assert.assertEquals(crossIcon,"This question has been deleted by the instructor","deleted message is not displaying");
            assignmentResponsesPage.getCrossIcon().click();
            String crossIcon1=assignmentResponsesPage.getCrossIcon().getAttribute("title");
            Assert.assertEquals(crossIcon1,"Question not delivered","deleted message is not displaying");
            int questionSize=assignmentResponsesPage.getQuestionLabels().size();
            if(questionSize>3){
                Assert.fail("deleted question column is not available");
            }
            assignmentResponsesPage.getViewResponsesforDeletedquestion().click();
            String message=assignments.getNotificationMessage().getText();
            Assert.assertEquals(message,"This question is deleted and is no longer part of this assignment.","notification message for deleted question is not displaying");
            assignmentResponsesPage.getFeedbackBox().sendKeys("This is a feedback");
            assignmentResponsesPage.getSaveButton().click();//click on save
            currentAssignments.getCurrentAssignmentTitle().click();//click on current assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new Assignment().provideGradeToStudentForMultipleQuestions(65);
            new Assignment().releaseGrades(65,"Release Grade for All");
            String gradedBoxCount=assignmentResponsesPage.getGradeBoxCount().getText();
            Assert.assertEquals(gradedBoxCount,"4","gradebox count not displying total student count");

        } catch (Exception e) {
            Assert.fail("Exception in test case questionIsDeletedAfterSomeStudentsHaveAttempted in class InstructorShouldAbleToViewTheAssignment", e);
        }
    }
    @Test(priority = 4, enabled = true)
    public void  courseStreamEntryForDiscussionAddedToDeletedQuestion() {
        try {
            //tc row no 99
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "99");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "99");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            new LoginUsingLTI().ltiLogin("99_1");//create student1
            new LoginUsingLTI().ltiLogin("99_2");//create student2
            new LoginUsingLTI().ltiLogin("99");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("99");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(99);//assign assignment
            new LoginUsingLTI().ltiLogin("99_1");//login as student1
            new Navigator().NavigateTo("Assignments");
            new Assignment().submitAssignmentAsStudent(99); //submit assignment
            Thread.sleep(2000);
            new ClickOnquestionCard().clickonquestioncard(0);
            assignments.getDiscussionTab().click();
            assignments.getNewButton().click();
            assignments.getEditBox().sendKeys("aassdfdghh asdfgjj");
            assignments.getSubmit().click();

            new LoginUsingLTI().ltiLogin("99");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getThreeDots().click();//click on three dots
            currentAssignments.getExpendQuestion().click();//click on expand icon
            String text=currentAssignments.getQuestionLabel().getText();
            currentAssignments.getDeleteIcon().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getExtendDueDate().click();
            driver.findElement(By.id("extended-due-time")).click();
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals("12:15 AM")) {
                    time.click();
                    break;
                }
            }
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)","");
            Thread.sleep(2000);
            currentAssignments.getUpdate().click();
            currentAssignments.updateNotificationYesLink.click();//click on yes
            Thread.sleep(2000);
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();//click on jumpout
            new UIElement().waitAndFindElement(By.xpath("//div[@class='al-diag-test-chapter-label']"));
            String question=courseStreamPage.getQuestion().getText();
            if (!text.contains(question)){
                Assert.fail("Instructor should not get navigated to deleted question");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case courseStreamEntryForDiscussionAddedToDeletedQuestion in class InstructorShouldAbleToViewTheAssignment", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void  proficiencyReportForDeletedQuestion() {
        try {
            //tc row no 100
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "100");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "100");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            ProficiencyReport proficiencyReport;
            proficiencyReport= PageFactory.initElements(driver,ProficiencyReport.class);
            new LoginUsingLTI().ltiLogin("100_1");//create student1
            new LoginUsingLTI().ltiLogin("100_2");//create student2
            new LoginUsingLTI().ltiLogin("100");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("100");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(6000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(100);//assign assignment
            new LoginUsingLTI().ltiLogin("100_1");//login as student1
            new Assignment().submitAssignmentAsStudent(100); //submit assignment
            new LoginUsingLTI().ltiLogin("100");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIcon().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getExtendDueDate().click();
            driver.findElement(By.id("extended-due-time")).click();
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals("12:15 AM")) {
                    time.click();
                    break;
                }
            }
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)","");
            Thread.sleep(2000);
            currentAssignments.getUpdate().click();
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("100_2");//login as student2
            new Assignment().submitAssignmentAsStudent(100); //submit assignment
            new LoginUsingLTI().ltiLogin("100");//log in as instructor
            new Assignment().releaseGrades(100,"Release Grade for All");
            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("100");//log in as instructor
            proficiencyReport= PageFactory.initElements(driver,ProficiencyReport.class);
            new Navigator().NavigateTo("Proficiency Report");
            proficiencyReport.getXaxixLabel().click();
           /* proficiencyReport.getXaxixLabelByChapter().click();
            String width=proficiencyReport.getWidth().getAttribute("width");
            Assert.assertEquals(width,"35","Class performance by question should not showing the deleted question bar in color based on student response");*/
        } catch (Exception e) {
            Assert.fail("Exception in test case proficiencyReportForDeletedQuestion in class InstructorShouldAbleToViewTheAssignment", e);
        }
    }
    @Test(priority = 6, enabled = true)
    public void  assignmentWithPolicyHavingGradeReleaseOption1() {
        try {
            //tc row no 101
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "101");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "101");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "101");
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new Assignment().create(101);
            new Assignment().addQuestions(101, "truefalse", "");
            new Assignment().addQuestions(101, "truefalse", "");
            new LoginUsingLTI().ltiLogin("101_1");//create student1
            new LoginUsingLTI().ltiLogin("101_2");//create student2
            new LoginUsingLTI().ltiLogin("101");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("101");//select three question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy
            new Navigator().NavigateTo("My Question Bank");//navigate to Assignments
            new AssignLesson().Assigncustomeassignemnt(101);//assign assignment

            new LoginUsingLTI().ltiLogin("101_1");//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//open lesson assignment
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "incorrect", 101);
            new Assignment().nextButtonInQuestionClick();
            new AttemptQuestion().trueFalse(false, "incorrect", 101);
            new Assignment().nextButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("101");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIconTwo().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(5000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getExtendDueDate().click();
            driver.findElement(By.id("extended-due-time")).click();
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals("12:15 AM")) {
                    time.click();
                    break;
                }
            }
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)","");
            Thread.sleep(2000);
            currentAssignments.getUpdate().click();
            driver.findElement(By.className("extend-due-submit-yes")).click();
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("101_1");//login as student1
            new Assignment().submitAssignmentAsStudent(101); //submit assignment
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            Thread.sleep(2000);
            int question=assignments.getWidth().size();
            if(question > 3){
                Assert.fail("scoreover assignment page is not included deleted question");
            }
            new LoginUsingLTI().ltiLogin("101_2");//login as student2
            new Assignment().submitAssignmentAsStudent(101); //submit assignment
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            Thread.sleep(2000);
            int question1=assignments.getWidth().size();
            if(question1 > 2){
                Assert.fail("scoreover assignment page is not included deleted question");
            }
            new LoginUsingLTI().ltiLogin("101");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getViewGrade_link().click();//click on view student responses
            Thread.sleep(2000);
            String assigmentStatus=assignmentResponsesPage.review_Status.get(1).getText();
            Assert.assertEquals(assigmentStatus,"Graded","Assignment status not displaying graded");
        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentWithPolicyHavingGradeReleaseOption1 in class InstructorShouldAbleToViewTheAssignment", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void  assignmentWithPolicyHavingGradeReleaseOption3() {
        try {
            //tc row no 109
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "109");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "109");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "109");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(109));
            new Assignment().create(109);
            new Assignment().addQuestions(109, "writeboard", "");
            new Assignment().addQuestions(109, "essay", "");
            new LoginUsingLTI().ltiLogin("109_1");//create student1
            new LoginUsingLTI().ltiLogin("109_2");//create student2
            new LoginUsingLTI().ltiLogin("109_3");//create student3
            new LoginUsingLTI().ltiLogin("109");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();

            List<WebElement> a=driver.findElements(By.cssSelector("span[title='Customize This']"));
            for(WebElement b:a){
                if(b.isDisplayed()){
                    b.click();
                }
            }
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("109");//select three question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//till save policy*//**//*
            new Navigator().NavigateTo("My Question Bank");//navigate to Assignments
            new AssignLesson().Assigncustomeassignemnt(109);//assign assignment
            new LoginUsingLTI().ltiLogin("109_1");//login as student1
            new Assignment().submitAssignmentAsStudent(109); //submit assignment
            new LoginUsingLTI().ltiLogin("109_2");//login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//open lesson assignment
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().essay(false, "incorrect", 109);
            assignments.getSubmitAssignment().click();
            new AttemptQuestion().writeBoard(false,"incorrect",109);
            assignments.getSubmitAssignment().click();
            new LoginUsingLTI().ltiLogin("109");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIconTwo().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getExtendDueDate().click();//click on extend due date
            driver.findElement(By.id("extended-due-time")).click();//enter time
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals("12:15 AM")) {
                    time.click();
                    break;
                }
            }
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)","");
            Thread.sleep(2000);
            currentAssignments.getUpdate().click();
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("109_2");//login as student2
            new Assignment().submitAssignmentAsStudent(109); //submit assignment
            new LoginUsingLTI().ltiLogin("109_3");//login as student3
            new Assignment().submitAssignmentAsStudent(109); //submit assignment
            new LoginUsingLTI().ltiLogin("109");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            Thread.sleep(2000);
            new Assignment().provideGradeToStudentForMultipleQuestions(109);
            driver.navigate().refresh();
            currentAssignments.getViewGrade_link().click();//click on view response
            new LoginUsingLTI().ltiLogin("109_1");//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score6=assignments.getScore().getText();
            Assert.assertEquals(score6,"Score (2.4/6)","Score for assignment over assignment page is not included deleted question");
            new LoginUsingLTI().ltiLogin("109_2");//login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score4=assignments.getScore().getText();
            Assert.assertEquals(score4,"Score (2.4/6)","Score for assignment over assignment page is not included deleted question");
            new LoginUsingLTI().ltiLogin("109_3");//login as student3
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score5=assignments.getScore().getText();
            Assert.assertEquals(score5,"Score (1.6/4)","Score for assignment over assignment page is not excluded deleted question");

        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentWithPolicyHavingGradeReleaseOption3 in class InstructorShouldAbleToViewTheAssignment", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void  assignmentWithPolicyHavingGradeReleaseOption4() {
        try {
            //tc row no 113
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "113");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "113");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "113");
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new Assignment().create(113);
            new Assignment().addQuestions(113, "truefalse", "");
            new Assignment().addQuestions(113, "truefalse", "");
            new LoginUsingLTI().ltiLogin("113_1");//create student1
            new LoginUsingLTI().ltiLogin("113_2");//create student2
            new LoginUsingLTI().ltiLogin("113_3");//create student3
            new LoginUsingLTI().ltiLogin("113");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("113");//select three question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description21", "2", null, false, "1", "", "", "", "", "");//policy 4
            new Navigator().NavigateTo("My Question Bank");//navigate to Assignments
            new AssignLesson().Assigncustomeassignemnt(113);//assign assignment
            new LoginUsingLTI().ltiLogin("113_1");//login as student1
            new Assignment().submitAssignmentAsStudent(113); //submit assignment
            new LoginUsingLTI().ltiLogin("113_2");//login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//open lesson assignment
            new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false,"incorrect",113);
            assignments.getSubmitAssignment().click();
            new AttemptQuestion().trueFalse(false,"incorrect",113);
            assignments.getSubmitAssignment().click();
            new LoginUsingLTI().ltiLogin("113");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIcon().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getExtendDueDate().click();//click on extend due date
            driver.findElement(By.id("extended-due-time")).click();//enter time
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals("12:15 AM")) {
                    time.click();
                    break;
                }
            }
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)","");
            Thread.sleep(2000);
            currentAssignments.getUpdate().click();
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("113_2");//login as student2
            new Assignment().submitAssignmentAsStudent(113); //submit assignment
            new LoginUsingLTI().ltiLogin("113_3");//login as student3
            new Assignment().submitAssignmentAsStudent(113); //submit assignment
            new LoginUsingLTI().ltiLogin("113");//log in as instructor
            new Assignment().releaseGrades(113,"Release Grade for All");
            String status1=assignmentResponsesPage.getReviewStatus().getText();
            Assert.assertEquals(status1,"Graded","Graded status is not displaying after released grade for all");
            new LoginUsingLTI().ltiLogin("113_2");//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score6=assignments.getScore().getText();
            Assert.assertEquals(score6,"Score (2/6)","Score for assignment over assignment page is not included deleted question");
            new LoginUsingLTI().ltiLogin("113_2");//login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score4=assignments.getScore().getText();
            Assert.assertEquals(score4,"Score (2/6)","Score for assignment over assignment page is not included deleted question");
            new LoginUsingLTI().ltiLogin("113_3");//login as student3
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score5=assignments.getScore().getText();
            Assert.assertEquals(score5,"Score (4/4)","Score for assignment over assignment page is not excluded deleted question");

        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentWithPolicyHavingGradeReleaseOption4 in class InstructorShouldAbleToViewTheAssignment", e);
        }
    }
    @Test(priority = 9, enabled = true)
    public void  questionIsDeletedAfterSomeStudentsHaveAttemptedTheTtoBeDeletedQuestiont() {
        try {
            //tc row no 116
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "116");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "116");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            EngagementReport engagementReport = PageFactory.initElements(driver,EngagementReport.class);
            new Assignment().create(116);
            new Assignment().addQuestions(116, "truefalse", "");
            new Assignment().addQuestions(116, "truefalse", "");
            new LoginUsingLTI().ltiLogin("116_1");//create student1
            new LoginUsingLTI().ltiLogin("116_2");//create student2
            new LoginUsingLTI().ltiLogin("116_3");//create student3
            new LoginUsingLTI().ltiLogin("116");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("116");//select three question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(8000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(116);//assign assignment
            new LoginUsingLTI().ltiLogin("116_2");//login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            Thread.sleep(12000);
            new LoginUsingLTI().ltiLogin("116_3");//login as student3
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new AttemptQuestion().trueFalse(false,"incorrect",116);
            new Assignment().nextButtonInQuestionClick();
            new AttemptQuestion().trueFalse(false,"incorrect",116);
            new Assignment().nextButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("116");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIconTwo().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getExtendDueDate().click();//click on extend due date
            driver.findElement(By.id("extended-due-time")).click();//enter time
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals("12:15 AM")) {
                    time.click();
                    break;
                }
            }
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)","");
            Thread.sleep(2000);
            currentAssignments.getUpdate().click();
            driver.findElement(By.className("extend-due-submit-yes")).click();
            Thread.sleep(2000);
            new UIElement().waitAndFindElement(By.className("ls-grade-book-assessment"));//tc row no 118
            currentAssignments.getViewGrade_link().click();//click on view response
            String deletedQuestion=assignmentResponsesPage.getDeletedQuestion().getText();
            Assert.assertEquals(deletedQuestion,"Q3","Deleted Question column is not displaying");
            assignmentResponsesPage.getPreviewOfAssignment().click();
            int questionCount = myQuestionBank.getQuestionCount().size();
            if(questionCount > 2){
                Assert.fail("List of question preview except the deleted question is not displaying");
            }
            currentAssignments.getCurrentAssignmentTitle().click();//click on current assignment
            currentAssignments.getPreviewOfAssignmentOnCurrentAssignment().click();//click on preview of assignment
            int questionCount1 = myQuestionBank.getQuestionCount().size();
            if(questionCount1 > 2){
                Assert.fail("List of question preview except the deleted question is not displaying");
            }
            //tc row no 124
            new LoginUsingLTI().ltiLogin("116_1");//login as student1
            new Assignment().submitAssignmentAsStudent(116); //submit assignment
            new LoginUsingLTI().ltiLogin("116_2");//login as student2
            new Assignment().submitAssignmentAsStudent(116); //submit assignment
            new LoginUsingLTI().ltiLogin("116_3");//login as student3
            new Assignment().submitAssignmentAsStudent(116); //submit assignment
            new LoginUsingLTI().ltiLogin("116");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            String status=currentAssignments.status_reviewInProgress.getText();
            Assert.assertEquals(status,"Review in Progress","status is not showing review in progress");
            Thread.sleep(2000);
            String submitBoxCount=currentAssignments.getSubmittedBoxCount().getText();
            Assert.assertEquals(submitBoxCount,"3","submitbox is not displaying the total student count");
            //tc row no 126
            currentAssignments.getViewGrade_link().click();//click on view student responses
            String crossIcon=assignmentResponsesPage.getCrossIconOnDeletedQuestion().getAttribute("title");
            Assert.assertEquals(crossIcon,"This question has been deleted by the instructor","deleted message is not displaying");
            assignmentResponsesPage.getCrossIcon().click();
            assignmentResponsesPage.getSecondQuestionCrossIcon().click();
            String crossIcon1=assignmentResponsesPage.getCrossIcon().getAttribute("title");
            Assert.assertEquals(crossIcon1,"Question not delivered","deleted message is not displaying");
            int questionSize=assignmentResponsesPage.getQuestionLabels().size();
            if(questionSize>3){
                Assert.fail("deleted question column is not available");
            }
            assignmentResponsesPage.getViewResponse().click();
            String message=assignments.getNotificationMessage().getText();
            Assert.assertEquals(message,"This question is deleted and is no longer part of this assignment.","notification message for deleted question is not displaying");
            assignmentResponsesPage.getFeedbackBox().sendKeys("This is a feedback");
            assignmentResponsesPage.getSaveButton().click();//click on save
            currentAssignments.getCurrentAssignmentTitle().click();//click on current assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new Assignment().releaseGrades(116,"Release Feedback for All");
            String status1=assignmentResponsesPage.getReviewStatus().getText();
            Assert.assertEquals(status1,"Reviewed","Reviewed status is not displaying after released feedback for all");
            new Navigator().NavigateTo("Engagement Report");//navigate to engagement report
            String questionAttempt=engagementReport.getQuestionAttempt().getText().trim();
            Assert.assertEquals(questionAttempt,"3","deleted question count is not excluding");
            String questionAttempt1=engagementReport.getQuestionAttempted().getText().trim();
            Assert.assertEquals(questionAttempt1,"2","deleted question count is not including");

        } catch (Exception e) {
            Assert.fail("Exception in test case questionIsDeletedAfterSomeStudentsHaveAttemptedTheTtoBeDeletedQuestiont in class InstructorShouldAbleToViewTheAssignment", e);
        }
    }

}
