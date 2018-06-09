package com.snapwiz.selenium.tests.staf.edulastic.testcases.e9.questioncentricstudentview;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssignmentSummary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.TakeAssignment;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.StudentDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.detailedResponse.DetailedResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.profile.Profile;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.yourresponse.YourResponse;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Action;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.KeysSend;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Calendar;

/**
 * Created by pragya on 18-09-2015.
 */
public class QuestionCentricStudentView extends Driver {

    @Test(priority = 1,enabled = true)
    public void studentCentricView(){ 
        try{

            int dataIndex = 15;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);

            String appendChar1 = "a";
            String appendChar2 = "b";

            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Navigator().logout();//log out

            //Register student1
            new SignUp().studentDirectRegister(appendChar1, classCode, dataIndex);
            new Navigator().logout();//log out

            //Register student2
            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as an instructor

            //Create assignment having 5 questions
            new Assignment().create(dataIndex,"truefalse");
            for(int i=0; i<4; i++){
                new Assignment().addQuestion(dataIndex,"truefalse");
            }
            new Assignment().assignToStudent(dataIndex,appendChar1);//assign to student
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);//Login as a student1
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar2,dataIndex);//Login as a student2
            new Assignment().submitAssignment(64);//Submit assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            studentResponse.barTwoCorrectUnderPerformance.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            //Expected - Question Centric View for the Question that was selected should be opened
            Assert.assertEquals(detailedResponse.questionLabelAtDetailedResponse.get(0).getText().replaceAll("\n", " "),"Q1: True False "+questiontext+"","Expected question is not opened for student1");
            Assert.assertEquals(detailedResponse.questionLabelAtDetailedResponse.get(1).getText().replaceAll("\n", " "),"Q1: True False "+questiontext+"","Expected question is not opened for student2");

            //Expected - All the student's responses for the Selected question should be displayed
            Assert.assertEquals(detailedResponse.options_trueFalse.get(0).getAttribute("class"),"true-false-student-answer-select","Answer is not displayed as attempted by student2");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(1).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Answer is not displayed as attempted by student2");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(2).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Answer is not displayed as attempted by student1");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(3).getAttribute("class"),"true-false-student-answer-select","Answer is not displayed as attempted by student1");

            //Student should be displayed as per recency
            Assert.assertTrue(detailedResponse.list_studentname.get(0).getText().contains("studentCentricViewst"+appendChar2),"2nd student is not displayed above as per recency");
            Assert.assertTrue(detailedResponse.list_studentname.get(1).getText().contains("studentCentricViewst"+appendChar1),"1st student is not displayed below as per recency");

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int actualMonth = cal.get(Calendar.MONTH);
            int month = actualMonth+1;
            String monthDisplayed = new Calender().getCurrentMonthNameFull(month);

            //Expected - Page header should be displayed with the Following details
            //a. A back link
            //b. Assignment Image
            //c. Name of the Assignment
            //d. Due Date
            //e. Drop down with the List of all the questions that allows the Instructor to Switch Between Questions Any time
            Assert.assertEquals(detailedResponse.detailedResponseBackArrow.isDisplayed(), true, "Back arrow is not displayed");
            Assert.assertEquals(detailedResponse.assignmentImage.isDisplayed(), true, "Assignment image is not displayed");
            Assert.assertEquals(detailedResponse.detailedResponseHeader.getText(),assessmentname+" (Due Date : 28 "+monthDisplayed+","+year+" 12:00 AM"+")","Assignment name and due date is not displayed as expected");
            Assert.assertEquals(detailedResponse.dropdown_questionNumber.getText(),"1","Question number1 is not displayed as expected");

            detailedResponse.list_dropdown.get(1).click();//Click on questions dropdown
            detailedResponse.dropdown_questions.get(1).click();//Select question2

            //Question2 should be selected
            Assert.assertEquals(detailedResponse.dropdown_questionNumber.getText(),"2","Question2 is not selected");

            //Expected -  The question centric page for the respective Question should be opened up
            Assert.assertTrue(detailedResponse.performanceHeader.getText().contains("Q2"),"The question centric page for the respective Question is not opened");

            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");//Scroll to the end of page

            //Expected - The header should have Sticky behavior
            Assert.assertEquals(detailedResponse.detailedResponseHeader.isDisplayed(),true,"Header of the page is not displayed");

            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");//Scroll to the begin of page

            //Expected - Performance Summary should be displayed with the Following Details
            //a. Bar Chart to Display the responses of Every Student
            Assert.assertEquals(detailedResponse.bars_student.get(0).getAttribute("fill"),"url(#highcharts-pattern-0)","Student2 bar is not displaying the response as expected");
            Assert.assertEquals(detailedResponse.bars_student.get(1).getAttribute("fill"),"#73B966","Student1 bar is not displaying the response as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_student.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Scoring points: 0"),"Student2 response is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_student.get(1));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Scoring points: 1"),"Student1 response is not displayed as expected");

            //b. Line Graph to display the Amount of time spent By the Every Student for the Question
            new MouseHover().mouserhoverbywebelement(detailedResponse.lineGraph_students.get(0));//Hover over on student1 line bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time spent is not displayed for student1");
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(detailedResponse.lineGraph_students.get(1));//Hover over on student1 line bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time spent is not displayed for student2");

            //c. Student's' initials/Display picture should be shown under the Bar Graph Sorted by recency
            Assert.assertEquals(detailedResponse.studentsInitial_bar.get(0).getText(),"ST","Student2 initial is not displayed as expected");
            Assert.assertEquals(detailedResponse.studentsInitial_bar.get(1).getText(),"ST","Student1 initial is not displayed as expected");

            //Expected -  Bubble Navigator should be Displayed With Initials/Display picture of each Student in One bubble, Sorted according to recency
            Assert.assertEquals(detailedResponse.studentsInitial_bubble.get(0).getText(),"ST","Student2 initial is not displayed as expected");
            Assert.assertEquals(detailedResponse.studentsInitial_bubble.get(1).getText(),"ST","Student1 initial is not displayed as expected");

        }catch (Exception e){

            Assert.fail("Exception in test case 'studentCentricView' of class 'QuestionCentricStudentView'", e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void student21ResponseDrillDown(){
        try{
            int dataIndex = 158;
            String appendChar = "a";

            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);
            StudentDashboard studentDashboard = PageFactory.initElements(driver,StudentDashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);
            new Navigator().logout();//log out

            //Register 21 students
            new SignUp().studentDirectRegister(appendChar+1, classCode, dataIndex);
            studentDashboard.dropdown_userName.click();//Click on username dropdown
            profile.myProfile.click();//Click on my profile
            profile.uploadProfile.click();//Click on upload profile
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            new Click().clickByid("start_queue");//Click on upload now
            new WebDriverWait(driver,120).until(ExpectedConditions.invisibilityOfElementLocated(By.id("start_queue")));
            new Navigator().logout();//log out

            for(int i=2;i<22;i++){
                new SignUp().studentDirectRegister(appendChar+i, classCode, dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create assignment having 5 questions
            new Assignment().create(dataIndex,"truefalse");
            for(int i=0; i<4; i++){
                new Assignment().addQuestion(dataIndex,"truefalse");
            }
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment
            new Navigator().logout();//log out

            //21 students submitting assignment
            for(int i=1;i<22;i++){
                new Login().loginAsStudent(appendChar+i,dataIndex);
                new Assignment().submitAssignment(dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            studentResponse.barAllCorrectAnswer.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            //Expected - The Summary graph should have 15 Bars maximum and side scrollers to view further
            String noOfBars = String.valueOf(detailedResponse.studentBar_allCorrectAnswers.size());

            Assert.assertEquals(noOfBars,"15","15 bars are not appearing as expected");

            detailedResponse.nextArrowUnderPerformanceSummary.click();//Click on next arrow
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/*//*[@height='46']")));

            String noOfBarsAfterScroll = String.valueOf(detailedResponse.studentBar_allCorrectAnswers.size());

            Assert.assertEquals(noOfBarsAfterScroll,"6","6 bars are not appearing as expected");

            detailedResponse.previousArrowUnderPerformanceSummary.click();//Click on previous arrow
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/*//*[@fill='#73B966']")));

            //Expected - All student's responses should be Displayed one below the other in Card view
            for(int i=0; i<30; i=i+2) {
                if (!detailedResponse.options_trueFalse.get(i).getAttribute("class").equals("true-false-student-answer-select true-false-student-answer-clicked")) {
                        Assert.fail("All the student responses are not displayed correct as expected");
                }
            }

            detailedResponse.nextArrowUnderPerformanceSummary.click();//Click on next arrow
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/*//*[@fill='#73B966']")));

            for(int i=0; i<12; i=i+2) {
                if (!detailedResponse.options_trueFalse.get(i).getAttribute("class").equals("true-false-student-answer-select true-false-student-answer-clicked")) {
                    Assert.fail("All the student responses are not displayed correct as expected");
                }
            }

            //Expected -  The response card should have the Following Details
            //a - Question Body should be displayed
            Assert.assertTrue(detailedResponse.questionLabelAtDetailedResponse.get(0).getText().contains("True False question text_student21ResponseDrillDownn_158"), "Question body is not displayed in response card");

            //b - Correct answer/ Answer given by the Student should be displayed
            Assert.assertEquals(detailedResponse.options_trueFalse.get(0).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Answer is not displayed as given by instructor");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(1).getAttribute("class"),"true-false-student-answer-select","Answer is not displayed as given by instructor");

            //c - Feedback section should be displayed that allows the Instructor to Edit/ Enter the Feedback
            Assert.assertEquals(detailedResponse.label_TeacherFeedback.get(0).getText(),"Teacher Feedback","Teacher feedback label is not displayed as expected");

            detailedResponse.teacherFeedbackOnEachQuestion.get(0).clear();
            driver.switchTo().activeElement().sendKeys("Teacher Feedback");//Enter text in edit box

            //Expected -Student name with Display Picture should be displayed
            Assert.assertTrue(detailedResponse.bubble_studentWithImage.get(0).getAttribute("src").contains("img"),"Student1 image is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"6","6 student bubble is not displayed as expected");

            //Expected - Score section should be displayed
            Assert.assertEquals(String.valueOf(detailedResponse.scoreSection.size()),"6","6 Score section is not displayed as expected");

            detailedResponse.previousArrowUnderPerformanceSummary.click();//Click on previous arrow
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/*//*[@fill='#73B966']")));

            Assert.assertEquals(String.valueOf(detailedResponse.scoreSection.size()),"15","15 Score section is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"15","15 student bubble is not displayed as expected");

            //Expected -  All the Student's cards should be displayed
            Assert.assertEquals(String.valueOf(detailedResponse.cards_student.size()),"15","All the student's cards are not displaying ");

        }catch (Exception e){
            Assert.fail("Exception in test case 'student21ResponseDrillDown' of class 'QuestionCentricStudentView'", e);

        }
    }

    @Test(priority = 3,enabled = true)
    public void instructorUsingBubbleNavigator(){
        try{
            int dataIndex = 285;
            String appendChar = "a";

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);
            new Navigator().logout();//log out

            //Register 11 students
            for(int i=1;i<=11;i++){
                new SignUp().studentDirectRegister(appendChar+i, classCode, dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create assignment having 5 questions
            new Assignment().create(dataIndex,"truefalse");
            for(int i=0; i<4; i++){
                new Assignment().addQuestion(dataIndex,"truefalse");
            }
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment
            new Navigator().logout();//log out

            //11 students submitting assignment
            for(int i=1;i<=11;i++){
                new Login().loginAsStudent(appendChar+i,dataIndex);
                new Assignment().submitAssignment(dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            studentResponse.barAllCorrectAnswer_11Students.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            //Expected - Bubble navigator should be displayed under the Summary Graph
            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"11");

            for(int i=1; i<detailedResponse.bubble_student.size();i++){
                if(detailedResponse.bubble_student.get(i).isDisplayed()==false){
                    Assert.fail("bubble navigator is not displayed as expected");
                }
            }

            detailedResponse.bubble_student.get(1).click();//Click on 2nd bubble
            //Expected -  The bubble should be highlighted
            Assert.assertTrue(detailedResponse.bubble_student.get(1).getAttribute("class").equals("question-link-label correct selected"), "2nd bubble is not highlighted after selecting");

            Assert.assertTrue(detailedResponse.bubble_student.get(0).getAttribute("class").equals("question-link-label correct"),"1st bubble is highlighted even if it is not selected");

            for(int i=2;i<detailedResponse.bubble_student.size();i++){
                if(detailedResponse.bubble_student.get(i).getAttribute("class").equals("question-link-label correct selected")){
                  Assert.fail("bubble"+i+"is highlighted even if it is not selected");
                }
            }

        }catch (Exception e){
            Assert.fail("Exception in test case 'instructorUsingBubbleNavigator' of class 'QuestionCentricStudentView'", e);

        }
    }


    @Test(priority = 4,enabled = true)
    public void studentInitials(){
        try{
            int dataIndex = 355;
            String appendChar = "a1";

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);
            String firstName = "a";

            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);
            new Navigator().logout();//log out

            //Register 3 students
            for(int i=0; i<3; i++) {
                new SignUp().studentStaticSignUp(firstName+i,appendChar,firstName+appendChar+i+"@snapwiz.com", classCode, dataIndex);//Register student1
                new Navigator().logout();
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Assignment().create(dataIndex,"truefalse");//Create an assessment
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class
            new Navigator().logout();

            //Student attempting assignments
            for(int i=0; i<3; i++) {
               new Login().directLoginAsStudent(dataIndex,firstName+appendChar+i+"@snapwiz.com");
                new Assignment().submitAssignment(dataIndex);
                new Navigator().logout();
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            studentResponse.barOneCorrectUnderPerformance.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            //Expected - Bubble navigator should have the initials of the students Sorted in Alphabetical order
            Assert.assertEquals(detailedResponse.bubble_student.get(0).getText(),"A2","Students are not displaying in sorted order");
            Assert.assertEquals(detailedResponse.bubble_student.get(1).getText(),"A1","Students are not displaying in sorted order");
            Assert.assertEquals(detailedResponse.bubble_student.get(2).getText(),"A0","Students are not displaying in sorted order");

        }catch (Exception e){
            Assert.fail("Exception in test case 'studentInitials' of class 'QuestionCentricStudentView'", e);

        }
    }


    @Test(priority = 5,enabled = true)
    public void feedbackAssessmentGradingInProgress(){
        try{
            int dataIndex = 409;
            String appendChar = "a";

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);
            AssignmentSummary assignmentSummary = PageFactory.initElements(driver,AssignmentSummary.class);
            YourResponse yourResponse = PageFactory.initElements(driver,YourResponse.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);
            new Navigator().logout();//log out

            //Register 11 students
            for(int i=1;i<=11;i++){
                new SignUp().studentDirectRegister(appendChar+i, classCode, dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create assignment having 5 questions
            new Assignment().create(dataIndex,"truefalse");
            for(int i=0; i<4; i++){
                new Assignment().addQuestion(dataIndex,"truefalse");
            }
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment
            new Navigator().logout();//log out

            //11 students submitting assignment
            for(int i=1;i<=11;i++){
                new Login().loginAsStudent(appendChar+i,dataIndex);
                new Assignment().submitAssignment(dataIndex);
                new Navigator().logout();//log out
            }


            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response

            studentResponse.viewDetailedResponse.get(8).click();//Click on student11 view detail response
            detailedResponse.overallFeedback.clear();
            driver.switchTo().activeElement().sendKeys("Overall Feedback for Student11");//Enter overall feedback
            driver.findElement(By.xpath("//html/body")).click();

            Assert.assertEquals(detailedResponse.message_overallFeedback.getText(),"Feedback has been saved.","Message is not displayed as expected");

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            studentResponse.viewDetailedResponse.get(8).click();//Click on student11 view detail response

            //Expected - Feedback entered in the Question Centric View Should be Displayed
            Assert.assertEquals(detailedResponse.overallFeedback.getText(),"Overall Feedback for Student11","Overall feedback is not displayed as expected");

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            studentResponse.barAllCorrectAnswer_11Students.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.id("view-user-question-performance-score-box")));

            //Expected - Grades should be displayed
            for(int i=0;i<11;i++)
            {
                if(!detailedResponse.scorePerQuestion.get(i).getAttribute("value").equals("1")){
                    Assert.fail("Grades are not displayed as expected");
                }
            }

            detailedResponse.teacherFeedbackOnEachQuestion.get(0).clear();
            detailedResponse.teacherFeedbackOnEachQuestion.get(0).sendKeys("Teacher Feedback1");//Enter text into feedback edit box for student11(Q1)
            driver.findElement(By.xpath("//html/body")).click();//Click outside on body

            //Expected - Feedback should be saved and "Saved successfully" Message should be displayed
            Assert.assertEquals(detailedResponse.message_feedbackOrGrade.get(0).getText(),"Saved successfully.","Message is not appearing as expected for student11");

            detailedResponse.list_dropdown.get(1).click();//Click on questions dropdown
            detailedResponse.dropdown_questions.get(1).click();//Select question2
            Thread.sleep(2000);
            detailedResponse.teacherFeedbackOnEachQuestion.get(0).clear();
            detailedResponse.teacherFeedbackOnEachQuestion.get(0).sendKeys("Teacher Feedback2");//Enter text into feedback edit box for student11(Q2)
            driver.findElement(By.xpath("//html/body")).click();//Click outside on body

            //Expected - Feedback should be saved and "Saved successfully" Message should be displayed
            Assert.assertEquals(detailedResponse.message_feedbackOrGrade.get(0).getText(),"Saved successfully.","Message is not appearing as expected for student11");

            detailedResponse.list_dropdown.get(1).click();//Click on questions dropdown
            detailedResponse.dropdown_questions.get(0).click();//Select question1
            Thread.sleep(2000);

            //Expected - Feedback that was entered earlier for the Student Should be Displayed
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback1","Teacher feedback is not displayed for student11 as expected");

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow

            studentResponse.barAllCorrectAnswer_11Students.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.id("view-user-question-performance-score-box")));

            //Expected - Feedback entered by teacher should be displayed
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback1","Teacher feedback is not displayed for student11 as expected");

            detailedResponse.list_dropdown.get(1).click();//Click on questions dropdown
            detailedResponse.dropdown_questions.get(1).click();//Select question2
            Thread.sleep(2000);

            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback2","Teacher feedback is not displayed for student11 as expected");

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            studentResponse.releaseGrade.click();//Click on release grade
            new Navigator().logout();//log out

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String student11email = methodName+"st"+appendChar+"11"+"@snapwiz.com";

            new Login().directLoginAsStudent(dataIndex,student11email);//Login as student which is given feedback by instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Assignment().openAssignment(dataIndex);

            //Expected - Feedback that was entered earlier for the Student Should be Displayed
            Assert.assertEquals(assignmentSummary.editbox_teacherFeedback.getText(),"Overall Feedback for Student11","Overall feedback is not displayed as expected");

            assignmentSummary.bar_performanceByQuestions.get(0).click();//Click on 1st bar(Q1)
            //Expected - The Feedback that was Given by the Instructor should be visible to the Student
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback1","Teacher feedback is not displayed as expected");

            yourResponse.arrow_next.click();//Click on next arrow
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.id("view-user-question-performance-feedback-box")));

            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback2","Teacher feedback is not displayed as expected");

        }catch (Exception e){
            Assert.fail("Exception in test case 'feedbackAssessmentGradingInProgress' of class 'QuestionCentricStudentView'", e);

        }
    }


    @Test(priority = 6,enabled = true)
    public void feedbackAssessmentGraded(){
        try{
            int dataIndex = 547;
            String appendChar = "a";

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);
            AssignmentSummary assignmentSummary = PageFactory.initElements(driver,AssignmentSummary.class);
            YourResponse yourResponse = PageFactory.initElements(driver,YourResponse.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);
            new Navigator().logout();//log out

            //Register 11 students
            for(int i=1;i<=11;i++){
                new SignUp().studentDirectRegister(appendChar+i, classCode, dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create assignment having 5 questions
            new Assignment().create(dataIndex,"truefalse");
            for(int i=0; i<4; i++){
                new Assignment().addQuestion(dataIndex,"truefalse");
            }
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment
            new Navigator().logout();//log out

            //11 students submitting assignment
            for(int i=1;i<=11;i++){
                new Login().loginAsStudent(appendChar+i,dataIndex);
                new Assignment().submitAssignment(dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            studentResponse.viewDetailedResponse.get(8).click();//Click on student11 view detail response
            detailedResponse.overallFeedback.clear();
            driver.switchTo().activeElement().sendKeys("Overall Feedback for Student11");//Enter overall feedback
            driver.findElement(By.xpath("//html/body")).click();

            Assert.assertEquals(detailedResponse.message_overallFeedback.getText(),"Feedback has been saved.","Message is not displayed as expected");

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            studentResponse.viewDetailedResponse.get(8).click();//Click on student11 view detail response

            //Expected - Feedback entered in the Question Centric View Should be Displayed
            Assert.assertEquals(detailedResponse.overallFeedback.getText(),"Overall Feedback for Student11","Overall feedback is not displayed as expected");

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            studentResponse.barAllCorrectAnswer_11Students.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.id("view-user-question-performance-score-box")));

            detailedResponse.teacherFeedbackOnEachQuestion.get(0).clear();
            detailedResponse.teacherFeedbackOnEachQuestion.get(0).sendKeys("Teacher Feedback1");//Enter text into feedback edit box for student11(Q1)
            driver.findElement(By.xpath("//html/body")).click();//Click outside on body

            //Expected - Feedback should be saved and "Saved successfully" Message should be displayed
            Assert.assertEquals(detailedResponse.message_feedbackOrGrade.get(0).getText(),"Saved successfully.","Message is not appearing as expected for student11");

            detailedResponse.list_dropdown.get(1).click();//Click on questions dropdown
            detailedResponse.dropdown_questions.get(1).click();//Select question2
            Thread.sleep(2000);
            detailedResponse.teacherFeedbackOnEachQuestion.get(0).clear();
            detailedResponse.teacherFeedbackOnEachQuestion.get(0).sendKeys("Teacher Feedback2");//Enter text into feedback edit box for student11(Q2)
            driver.findElement(By.xpath("//html/body")).click();//Click outside on body
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[id='view-user-question-performance-save-success-message']")));

            //Expected - Feedback should be saved and "Saved successfully" Message should be displayed
            Assert.assertEquals(detailedResponse.message_feedbackOrGrade.get(0).getText(),"Saved successfully.","Message is not appearing as expected for student11");

            detailedResponse.list_dropdown.get(1).click();//Click on questions dropdown
            detailedResponse.dropdown_questions.get(0).click();//Select question1
            Thread.sleep(2000);

            //Expected - Feedback that was entered earlier for the Student Should be Displayed
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback1","Teacher feedback is not displayed for student11 as expected");

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow

            studentResponse.barAllCorrectAnswer_11Students.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.id("view-user-question-performance-score-box")));

            //Expected - Feedback entered by teacher should be displayed
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback1","Teacher feedback is not displayed for student11 as expected");

            detailedResponse.list_dropdown.get(1).click();//Click on questions dropdown
            detailedResponse.dropdown_questions.get(1).click();//Select question2
            Thread.sleep(2000);

            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback2","Teacher feedback is not displayed for student11 as expected");
            new Navigator().logout();//log out

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String student11email = methodName+"st"+appendChar+"11"+"@snapwiz.com";

            new Login().directLoginAsStudent(dataIndex,student11email);//Login as student which is given feedback by instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Assignment().openAssignment(dataIndex);

            //Expected - Feedback that was entered earlier for the Student Should be Displayed
            Assert.assertEquals(assignmentSummary.editbox_teacherFeedback.getText(),"Overall Feedback for Student11","Overall feedback is not displayed as expected");

            assignmentSummary.bar_performanceByQuestions.get(0).click();//Click on 1st bar(Q1)
            //Expected - The Feedback that was Given by the Instructor should be visible to the Student
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback1","Teacher feedback is not displayed as expected");

            yourResponse.arrow_next.click();//Click on next arrow
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.id("view-user-question-performance-feedback-box")));

            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback2","Teacher feedback is not displayed as expected");

        }catch (Exception e){
            Assert.fail("Exception in test case 'feedbackAssessmentGraded' of class 'QuestionCentricStudentView'", e);

        }
    }


    @Test(priority = 7,enabled = true)
    public void feedbackAssessmentReviewInProgress(){
        try{
            int dataIndex = 670;
            String appendChar = "a";

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);
            AssignmentSummary assignmentSummary = PageFactory.initElements(driver,AssignmentSummary.class);
            YourResponse yourResponse = PageFactory.initElements(driver,YourResponse.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);
            new Navigator().logout();//log out

            //Register 11 students
            for(int i=1;i<=11;i++){
                new SignUp().studentDirectRegister(appendChar+i, classCode, dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create assignment having 5 questions
            new Assignment().create(dataIndex,"truefalse");
            for(int i=0; i<4; i++){
                new Assignment().addQuestion(dataIndex,"truefalse");
            }
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment
            new Navigator().logout();//log out

            //11 students submitting assignment
            for(int i=1;i<=11;i++){
                new Login().loginAsStudent(appendChar+i,dataIndex);
                new Assignment().submitAssignment(dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            studentResponse.viewDetailedResponse.get(8).click();//Click on student11 view detail response
            detailedResponse.overallFeedback.clear();
            driver.switchTo().activeElement().sendKeys("Overall Feedback for Student11");//Enter overall feedback
            driver.findElement(By.xpath("//html/body")).click();
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.feedback-saved-message")));

            Assert.assertEquals(detailedResponse.message_overallFeedback.getText(),"Feedback has been saved.","Message is not displayed as expected");

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            studentResponse.viewDetailedResponse.get(8).click();//Click on student11 view detail response

            //Expected - Feedback entered in the Question Centric View Should be Displayed
            Assert.assertEquals(detailedResponse.overallFeedback.getText(),"Overall Feedback for Student11","Overall feedback is not displayed as expected");

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            studentResponse.barAllCorrectAnswer_11Students.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            detailedResponse.teacherFeedbackOnEachQuestion.get(0).clear();
            detailedResponse.teacherFeedbackOnEachQuestion.get(0).sendKeys("Teacher Feedback1");//Enter text into feedback edit box for student11(Q1)
            driver.findElement(By.xpath("//html/body")).click();//Click outside on body

            //Expected - Feedback should be saved and "Saved successfully" Message should be displayed
            Assert.assertEquals(detailedResponse.message_feedbackOrGrade.get(0).getText(),"Saved successfully.","Message is not appearing as expected for student11");

            detailedResponse.list_dropdown.get(1).click();//Click on questions dropdown
            detailedResponse.dropdown_questions.get(1).click();//Select question2
            Thread.sleep(2000);
            detailedResponse.teacherFeedbackOnEachQuestion.get(0).clear();
            detailedResponse.teacherFeedbackOnEachQuestion.get(0).sendKeys("Teacher Feedback2");//Enter text into feedback edit box for student11(Q2)
            driver.findElement(By.xpath("//html/body")).click();//Click outside on body

            //Expected - Feedback should be saved and "Saved successfully" Message should be displayed
            Assert.assertEquals(detailedResponse.message_feedbackOrGrade.get(0).getText(),"Saved successfully.","Message is not appearing as expected for student11");

            detailedResponse.list_dropdown.get(1).click();//Click on questions dropdown
            detailedResponse.dropdown_questions.get(0).click();//Select question1
            Thread.sleep(2000);

            //Expected - Feedback that was entered earlier for the Student Should be Displayed
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback1","Teacher feedback is not displayed for student11 as expected");

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@height='78']")));

            studentResponse.barAllCorrectAnswer_11Students.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            //Expected - Feedback entered by teacher should be displayed
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback1","Teacher feedback is not displayed for student11 as expected");

            detailedResponse.list_dropdown.get(1).click();//Click on questions dropdown
            detailedResponse.dropdown_questions.get(1).click();//Select question2
            Thread.sleep(2000);

            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback2","Teacher feedback is not displayed for student11 as expected");
            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            studentResponse.releaseGrade.click();//Click on release feedback
            new Navigator().logout();//log out

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String student11email = methodName+"st"+appendChar+"11"+"@snapwiz.com";

            new Login().directLoginAsStudent(dataIndex,student11email);//Login as student which is given feedback by instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Assignment().openAssignment(dataIndex);

            //Expected - Feedback that was entered earlier for the Student Should be Displayed
            Assert.assertEquals(assignmentSummary.editbox_teacherFeedback.getText(),"Overall Feedback for Student11","Overall feedback is not displayed as expected");

            assignmentSummary.bar_performanceByQuestions.get(0).click();//Click on 1st bar(Q1)
            //Expected - The Feedback that was Given by the Instructor should be visible to the Student
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback1","Teacher feedback is not displayed as expected");

            yourResponse.arrow_next.click();//Click on next arrow
            Thread.sleep(2000);
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback2","Teacher feedback is not displayed as expected");

        }catch (Exception e){
            Assert.fail("Exception in test case 'feedbackAssessmentReviewInProgress' of class 'QuestionCentricStudentView'", e);

        }
    }

    @Test(priority = 8,enabled = true)
    public void feedbackAssessementReviewed(){
        try{
            int dataIndex = 795;
            String appendChar = "a";

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);
            AssignmentSummary assignmentSummary = PageFactory.initElements(driver,AssignmentSummary.class);
            YourResponse yourResponse = PageFactory.initElements(driver,YourResponse.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);
            new Navigator().logout();//log out

            //Register 11 students
            for(int i=1;i<=11;i++){
                new SignUp().studentDirectRegister(appendChar+i, classCode, dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create assignment having 5 questions
            new Assignment().create(dataIndex,"truefalse");
            for(int i=0; i<4; i++){
                new Assignment().addQuestion(dataIndex,"truefalse");
            }
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment
            new Navigator().logout();//log out

            //11 students submitting assignment
            for(int i=1;i<=11;i++){
                new Login().loginAsStudent(appendChar+i,dataIndex);
                new Assignment().submitAssignment(dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            studentResponse.releaseGrade.click();//Click on release feedback
            studentResponse.viewDetailedResponse.get(8).click();//Click on student11 view detail response
            detailedResponse.overallFeedback.clear();
            driver.switchTo().activeElement().sendKeys("Overall Feedback for Student11");//Enter overall feedback
            driver.findElement(By.xpath("//html/body")).click();
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.feedback-saved-message")));

            Assert.assertEquals(detailedResponse.message_overallFeedback.getText(),"Feedback has been saved.","Message is not displayed as expected");

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            studentResponse.viewDetailedResponse.get(8).click();//Click on student11 view detail response

            //Expected - Feedback entered in the Question Centric View Should be Displayed
            Assert.assertEquals(detailedResponse.overallFeedback.getText(),"Overall Feedback for Student11","Overall feedback is not displayed as expected");

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            studentResponse.barAllCorrectAnswer_11Students.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            detailedResponse.teacherFeedbackOnEachQuestion.get(0).clear();
            detailedResponse.teacherFeedbackOnEachQuestion.get(0).sendKeys("Teacher Feedback1");//Enter text into feedback edit box for student11(Q1)
            driver.findElement(By.xpath("//html/body")).click();//Click outside on body

            //Expected - Feedback should be saved and "Saved successfully" Message should be displayed
            Assert.assertEquals(detailedResponse.message_feedbackOrGrade.get(0).getText(),"Saved successfully.","Message is not appearing as expected for student11");

            detailedResponse.list_dropdown.get(1).click();//Click on questions dropdown
            detailedResponse.dropdown_questions.get(1).click();//Select question2
            Thread.sleep(2000);
            detailedResponse.teacherFeedbackOnEachQuestion.get(0).clear();
            detailedResponse.teacherFeedbackOnEachQuestion.get(0).sendKeys("Teacher Feedback2");//Enter text into feedback edit box for student11(Q2)
            driver.findElement(By.xpath("//html/body")).click();//Click outside on body

            //Expected - Feedback should be saved and "Saved successfully" Message should be displayed
            Assert.assertEquals(detailedResponse.message_feedbackOrGrade.get(0).getText(),"Saved successfully.","Message is not appearing as expected for student11");

            detailedResponse.list_dropdown.get(1).click();//Click on questions dropdown
            detailedResponse.dropdown_questions.get(0).click();//Select question1
            Thread.sleep(2000);

            //Expected - Feedback that was entered earlier for the Student Should be Displayed
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback1","Teacher feedback is not displayed for student11 as expected");

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@height='78']")));

            studentResponse.barAllCorrectAnswer_11Students.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            //Expected - Feedback entered by teacher should be displayed
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback1","Teacher feedback is not displayed for student11 as expected");

            detailedResponse.list_dropdown.get(1).click();//Click on questions dropdown
            detailedResponse.dropdown_questions.get(1).click();//Select question2
            Thread.sleep(2000);

            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback2","Teacher feedback is not displayed for student11 as expected");
            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            new Navigator().logout();//log out

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String student11email = methodName+"st"+appendChar+"11"+"@snapwiz.com";

            new Login().directLoginAsStudent(dataIndex,student11email);//Login as student which is given feedback by instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Assignment().openAssignment(dataIndex);

            //Expected - Feedback that was entered earlier for the Student Should be Displayed
            Assert.assertEquals(assignmentSummary.editbox_teacherFeedback.getText(),"Overall Feedback for Student11","Overall feedback is not displayed as expected");

            assignmentSummary.bar_performanceByQuestions.get(0).click();//Click on 1st bar(Q1)
            //Expected - The Feedback that was Given by the Instructor should be visible to the Student
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback1","Teacher feedback is not displayed as expected");

            yourResponse.arrow_next.click();//Click on next arrow
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.id("view-user-question-performance-feedback-box")));

            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getText(),"Teacher Feedback2","Teacher feedback is not displayed as expected");

        }catch (Exception e){
            Assert.fail("Exception in test case 'feedbackAssessementReviewed' of class 'QuestionCentricStudentView'", e);

        }
    }

    @Test(priority = 9,enabled = true)
    public void instructorGivingGradesWithGradingInProgress(){
        try{
            int dataIndex = 920;
            String appendChar = "a";

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);
            AssignmentSummary assignmentSummary = PageFactory.initElements(driver,AssignmentSummary.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);
            new Navigator().logout();//log out

            //Register 11 students
            for(int i=1;i<=11;i++){
                new SignUp().studentDirectRegister(appendChar+i, classCode, dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create assignment having 5 questions
            new Assignment().create(dataIndex,"truefalse");
            for(int i=0; i<4; i++){
                new Assignment().addQuestion(dataIndex,"truefalse");
            }
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment
            new Navigator().logout();//log out

            //11 students submitting assignment
            for(int i=1;i<=11;i++){
                new Login().loginAsStudent(appendChar+i,dataIndex);
                new Assignment().submitAssignment(dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            studentResponse.barAllCorrectAnswer_11Students.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            detailedResponse.scorePerQuestion.get(0).clear();
            driver.switchTo().activeElement().sendKeys("0.5");//Change the score of student11(on the top)
            new Action().doubleClick(driver.findElement(By.xpath("//html/body")));
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[id='view-user-question-performance-save-success-message']")));

            //Expected - Grade should be saved and "Saved successfully" Message should be displayed
            Assert.assertEquals(detailedResponse.message_feedbackOrGrade.get(0).getText(),"Saved successfully.","Message is not appearing as expected for student11");

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow

            studentResponse.viewDetailedResponse.get(8).click();//Click on student11 view detailed response

            //Expected - Grade entered in the Question Centric View Should be Displayed
            Assert.assertEquals(detailedResponse.scorePerQuestion.get(0).getAttribute("value"),"0.5","student score is not displaying as expected for question1");

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow

            studentResponse.releaseGrade.click();//Click on release grade
            new Navigator().logout();//log out

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String student11email = methodName+"st"+appendChar+"11"+"@snapwiz.com";

            new Login().directLoginAsStudent(dataIndex,student11email);//Login as student which is given feedback by instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Assignment().openAssignment(dataIndex);

            assignmentSummary.bar_performanceByQuestions.get(0).click();//Click on 1st bar(Q1)
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[id='view-user-question-performance-score-box']")));

            //Expected - Grade Given By Instructor Should be Displayed
            Assert.assertEquals(detailedResponse.scorePerQuestion.get(0).getAttribute("value"),"0.5","Score is not displayed as given by instructor");

        }catch (Exception e){
            Assert.fail("Exception in test case 'instructorGivingGradesWithGradingInProgress' of class 'QuestionCentricStudentView'", e);

        }
    }

    @Test(priority = 10,enabled = true)
    public void instructorGivingGradesWithGraded(){
        try{
            int dataIndex = 971;
            String appendChar = "a";

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);
            new Navigator().logout();//log out

            //Register 11 students
            for(int i=1;i<=11;i++){
                new SignUp().studentDirectRegister(appendChar+i, classCode, dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create assignment having 5 questions
            new Assignment().create(dataIndex,"truefalse");
            for(int i=0; i<4; i++){
                new Assignment().addQuestion(dataIndex,"truefalse");
            }
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment
            new Navigator().logout();//log out

            //11 students submitting assignment
            for(int i=1;i<=11;i++){
                new Login().loginAsStudent(appendChar+i,dataIndex);
                new Assignment().submitAssignment(dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            studentResponse.barAllCorrectAnswer_11Students.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            //Expected - Instructor should be able to Edit the Grades
            for(int i=0;i<detailedResponse.scorePerQuestion.size();i++)
            {
                detailedResponse.scorePerQuestion.get(i).clear();
                detailedResponse.scorePerQuestion.get(i).sendKeys("0.5");//Edit the score for all the questions
            }
            driver.findElement(By.xpath("//html/body")).click();

            for(int i=0;i<detailedResponse.scorePerQuestion.size();i++)
            {
                if(!detailedResponse.scorePerQuestion.get(i).getAttribute("value").contains("0.5"))
                {
                    Assert.fail("Score is not edited");
                }
            }
        }catch (Exception e){
            Assert.fail("Exception in test case 'instructorGivingGradesWithGraded' of class 'QuestionCentricStudentView'", e);

        }
    }

    @Test(priority = 11,enabled = true)
    public void questionCentricViewStudentNotAttempted(){
        try{
            int dataIndex = 1060;
            String appendChar = "a";

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);
            new Navigator().logout();//log out

            //Register 3 students
            for(int i=1;i<=3;i++){
                new SignUp().studentDirectRegister(appendChar+i, classCode, dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create assignment having 5 questions
            new Assignment().create(dataIndex,"truefalse");
            for(int i=0; i<4; i++){
                new Assignment().addQuestion(dataIndex,"truefalse");
            }
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar+1,dataIndex);//Login as student1
            new Assignment().openAssignment(dataIndex);//Open assignment
            for(int i=1; i<=5;i++) {
                new Assignment().attemptQuestions("truefalse", dataIndex);//Attempt all the question correctly but don't submit
            }
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar+3,dataIndex);//Login as student3
            new Assignment().openAssignment(dataIndex);//Open assignment
            for(int i=1; i<=5;i++) {
                new Assignment().attemptQuestions("truefalse", 1061);//Attempt all the question incorrect but don't submit
            }
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            studentResponse.barForWrongUnderPerformance.get(0).click();//Click on Q1 bar

            //Expected - The question centric page for the respective Question should be opened
            Assert.assertTrue(detailedResponse.performanceHeader.getText().contains("Q1"),"The question centric page for the respective Question is not opened");

            //Expected -  It should Display the Responses of only Student A and C
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"2","More than 2 student's responses are displayed which is not expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"2","More than 2 students are displayed");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"questionCentricViewStudentNotAttemptedst"+appendChar+3,"Student3 is not displayed");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"questionCentricViewStudentNotAttemptedst"+appendChar+1,"Student1 is not displayed");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(0).getAttribute("class"),"true-false-student-answer-select","Student3 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(1).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student3 response is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(2).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student1 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(3).getAttribute("class"),"true-false-student-answer-select","Student1 response is not displayed as expected");

            //Expected -  Bar graph should Be displayed for 1st and 3rd only
            Assert.assertEquals(String.valueOf(detailedResponse.bars_student.size()),"2","More than 2 bars are displayed");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_student.get(0));//Hover over on student3 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewStudentNotAttemptedst"+appendChar+3),"Student3 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_student.get(1));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewStudentNotAttemptedst"+appendChar+1),"Student1 is not displayed as expected");

            //Expected - Bubble Navigator Should be Displayed for 1st and 3rd only
            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"2","2 bubbles are not displayed as expected for student1 and student3");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar+2,dataIndex);//Login as student2
            new Assignment().submitAssignment(dataIndex);//submit assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            studentResponse.barTwoCorrectUnderPerformance.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            //Expected - Now, the question Response for all three Students should be Displayed
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"3","3 student's responses are not displayed which is not expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"3","3 students are not displayed");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"questionCentricViewStudentNotAttemptedst"+appendChar+2,"Student2 is not displayed");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"questionCentricViewStudentNotAttemptedst"+appendChar+3,"Student3 is not displayed");
            Assert.assertEquals(detailedResponse.list_studentName.get(2).getText(),"questionCentricViewStudentNotAttemptedst"+appendChar+1,"Student1 is not displayed");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(0).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student2 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(1).getAttribute("class"),"true-false-student-answer-select","Student2 response is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(2).getAttribute("class"),"true-false-student-answer-select","Student3 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(3).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student3 response is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(4).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student1 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(5).getAttribute("class"),"true-false-student-answer-select","Student1 response is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bars_3students.size()),"3","3 students bar are not displayed");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewStudentNotAttemptedst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student3 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewStudentNotAttemptedst"+appendChar+3),"Student3 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(2));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewStudentNotAttemptedst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"3","3 bubbles are not displayed as expected");

        }catch (Exception e){
            Assert.fail("Exception in test case 'questionCentricViewStudentNotAttempted' of class 'QuestionCentricStudentView'", e);

        }
    }

   @Test(priority = 12,enabled = true)
   public void instructorNavigatingBackToLiveGradebook(){
       try{
           int dataIndex = 1142;
           String appendChar = "a";

           InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
           StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
           DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);

           new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
           new School().createWithOnlyName(appendChar, dataIndex);//Create school
           String classCode = new Classes().createClass(appendChar, dataIndex);
           new Navigator().logout();//log out

           //Register 2 students
           for(int i=1;i<=2;i++){
               new SignUp().studentDirectRegister(appendChar+i, classCode, dataIndex);
               new Navigator().logout();//log out
           }

           new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
           //Create assignment having 12 questions
           new Assignment().create(dataIndex,"truefalse");
           for(int i=0; i<11; i++){
               new Assignment().addQuestion(dataIndex,"truefalse");
           }
           new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment
           new Navigator().logout();//log out

           //2 students submitting assignment
           for(int i=2;i<=2;i++){
               new Login().loginAsStudent(appendChar+i,dataIndex);
               new Assignment().submitAssignment(dataIndex);
               new Navigator().logout();//log out
           }

           new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
           instructorDashboard.viewResponseonDashboard().click();//Click on view response
           studentResponse.nextArrow_performanceQuestions.click();//Click on next arrow
           studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(9).click();//Click on Q11 bar
           new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));
           detailedResponse.detailedResponseBackArrow.click();//Click on back arrow

           //Expected - The same question should be in the visible area of the graph for which the Question Centric page was Opened
           Assert.assertEquals(studentResponse.labelsInChart.get(9).getText(),"Q11","The same question(Q11)is not appearing in the visible area of the graph for which the Question Centric page was Opened");

       }catch (Exception e){
           Assert.fail("Exception in test case 'instructorNavigatingBackToLiveGradebook' of class 'QuestionCentricStudentView'", e);

       }
   }

    @Test(priority = 13,enabled = true)
    public void questionCentricViewReAttempt(){
        try{
            int dataIndex = 1200;
            String appendChar = "a";

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);
            new Navigator().logout();//log out

            //Register 3 students
            for(int i=1;i<=3;i++){
                new SignUp().studentDirectRegister(appendChar+i, classCode, dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create assignment having 5 questions
            new Assignment().create(dataIndex,"truefalse");
            for(int i=0; i<4; i++){
                new Assignment().addQuestion(dataIndex,"truefalse");
            }
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar+1,dataIndex);//Login as student1
            new Assignment().openAssignment(dataIndex);//Open assignment
            for(int i=1; i<=3;i++) {
                new Assignment().attemptQuestions("truefalse", dataIndex);//Attempt 3 questions and don't submit
            }
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response

            studentResponse.bar3QuestionsAttemptedOutOf5.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            //Expected -  Question Centric View should show Responses for Only Student A
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"1","only 1 student response is not displayed as expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"1","only 1 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"questionCentricViewReAttemptst"+appendChar+1,"only 1 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(0).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student1 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(1).getAttribute("class"),"true-false-student-answer-select","Student1 response is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bars_3students.size()),"1","only 1 student bar is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"1","1 bubbles is not displayed as expected");

            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown

            //Expected - Question Selection Dropdown should Have only 3 questions as The Student has attempted only 3 questions
            //Expected - Question 1,2 and 3 should be enabled
            Assert.assertEquals(detailedResponse.dropdown_questions.get(0).getAttribute("aria-selected"),"true","Question 1 is not selected");
            for(int i=1; i<3; i++){
                if(!detailedResponse.dropdown_questions.get(i).getAttribute("aria-selected").equals("false")){
                    Assert.fail("Question is not enabled");
                }
            }

            //Question 4 and 5 should be disabled
            for(int i=3;i<5;i++){
                if(!detailedResponse.dropdown_questions.get(i).getAttribute("aria-disabled").equals("true")){
                    Assert.fail("Question is not disabled");
                }
            }

            //created new instance for student for parallel execution  of student as well as instructor
            WebDriver firefoxWebdriver = new FirefoxDriver();
            firefoxWebdriver.close();
            firefoxWebdriver = new Driver().startDriver(firefoxWebdriver);
            TakeAssignment takeAssignment = PageFactory.initElements(firefoxWebdriver, TakeAssignment.class);

            new Login().loginAsStudent(appendChar+1, dataIndex, firefoxWebdriver);//login as a student1
            new Navigator().navigateTo("assignment", firefoxWebdriver);
            Thread.sleep(1000);
            //start assignment
            new Assignment().startAssignment(dataIndex, firefoxWebdriver);
            takeAssignment.trueFalse.get(0).click();//Attempt 4th question
            takeAssignment.button_next.click();

            //Expected - Instructor's view of Question centric view Should not be updated Immediately
            //Question 1,2 and 3 should be enabled
            Assert.assertEquals(detailedResponse.dropdown_questions.get(0).getAttribute("aria-selected"),"true","Question 1 is not selected");
            for(int i=1; i<3; i++){
                if(!detailedResponse.dropdown_questions.get(i).getAttribute("aria-selected").equals("false")){
                    Assert.fail("Question is not enabled");
                }
            }
            //Question 4 and 5 should be disabled
            for(int i=3;i<5;i++){
                if(!detailedResponse.dropdown_questions.get(i).getAttribute("aria-disabled").equals("true")){
                    Assert.fail("Question is not disabled");
                }
            }

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            studentResponse.bar3QuestionsAttemptedOutOf5.get(2).click();//Click on Q3 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            //Expected - Question Centric View should show Responses for Only Student A
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"1","only 1 student response is not displayed as expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"1","only 1 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"questionCentricViewReAttemptst"+appendChar+1,"only 1 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(0).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student1 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(1).getAttribute("class"),"true-false-student-answer-select","Student1 response is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bars_1studentAttempted.size()),"1","only 1 student bar is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_1studentAttempted.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"1","1 bubbles is not displayed as expected");

            //Expected - Question Selection Dropdown should now Have only 4 questions as The Student has attempted only 4 questions
            //Question 1,2,3,4 should be enabled
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            for(int i=0; i<2; i++){
                if(!detailedResponse.dropdown_questions.get(i).getAttribute("aria-selected").equals("false")){
                    Assert.fail("Question is not enabled");
                }
            }
            Assert.assertEquals(detailedResponse.dropdown_questions.get(2).getAttribute("aria-selected"),"true","Question 3 is not selected");
            Assert.assertEquals(detailedResponse.dropdown_questions.get(3).getAttribute("aria-selected"),"false","Question 4 is not enabled");

            //Question 5 should be disabled
            Assert.assertEquals(detailedResponse.dropdown_questions.get(4).getAttribute("aria-disabled"),"true","Question 5 is not displayed as disabled");

            takeAssignment.navigateToQuestion.get(2).click();//Click on 3rd question bubble
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("true-false-student-content-text")));
            takeAssignment.trueFalse.get(1).click();//Change the response to B
            takeAssignment.button_next.click();//Click on next button

            //Check instructor side
            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            studentResponse.bar3QuestionsAttemptedOutOf5.get(1).click();//Click on Q3 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            //Expected -  Question Centric View should show Responses for Only Student A
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"1","only 1 student response is not displayed as expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"1","only 1 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"questionCentricViewReAttemptst"+appendChar+1,"only 1 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(0).getAttribute("class"),"true-false-student-answer-select","Student1 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(1).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student1 response is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bars_1studentAttempted.size()),"1","only 1 student bar is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_1studentAttempted.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"1","1 bubbles is not displayed as expected");

            //Expected - Question Selection Dropdown should now Have only 4 questions as The Student has attempted only 4 questions
            //Question 1,2,3 and 4 should be enabled
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            for(int i=0; i<2; i++){
                if(!detailedResponse.dropdown_questions.get(i).getAttribute("aria-selected").equals("false")){
                    Assert.fail("Question is not enabled");
                }
            }
            Assert.assertEquals(detailedResponse.dropdown_questions.get(2).getAttribute("aria-selected"),"true","Question 3 is not selected");
            Assert.assertEquals(detailedResponse.dropdown_questions.get(3).getAttribute("aria-selected"),"false","Question 4 is not enabled");

            //Question 5 should be disabled
            Assert.assertEquals(detailedResponse.dropdown_questions.get(4).getAttribute("aria-disabled"),"true","Question 5 is not displayed as disabled");

            //Student1 attempting 5th question and submitting assignment
            takeAssignment.navigateToQuestion.get(4).click();//Click on 5th question
            takeAssignment.trueFalse.get(0).click();
            takeAssignment.button_next.click();//Click on submit button
            Thread.sleep(2000);
            if(!takeAssignment.submitButton.isDisplayed()) {
                takeAssignment.button_next.click();//Click on submit button
            }
            takeAssignment.submitButton.click();//Click on submit button

            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            studentResponse.bar3QuestionsAttemptedOutOf5.get(4).click();//Click on Q5 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));
            //Expected - Question Centric View should show Responses for Only Student A
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"1","only 1 student response is not displayed as expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"1","only 1 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"questionCentricViewReAttemptst"+appendChar+1,"only 1 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(0).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student1 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(1).getAttribute("class"),"true-false-student-answer-select","Student1 response is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bars_3students.size()),"1","only 1 student bar is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"1","1 bubbles is not displayed as expected");

            //Expected - Question Selection Dropdown should now have all the Questions Available
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown

            for(int i=0; i<4; i++){
                if(!detailedResponse.dropdown_questions.get(i).getAttribute("aria-selected").equals("false")){
                    Assert.fail("Question is not enabled");
                }
            }
            Assert.assertEquals(detailedResponse.dropdown_questions.get(4).getAttribute("aria-selected"),"true","Question 5 is not selected and enabled");
            new Navigator().logout(firefoxWebdriver);//Logout student

            //Login student 2 and 3 and submit assignment
            new Login().loginAsStudent(appendChar+2,dataIndex,firefoxWebdriver);//Login as student2
            new Assignment().startAssignment(dataIndex,firefoxWebdriver);
            for(int i=0;i<5;i++){
                takeAssignment.trueFalse.get(0).click();
                takeAssignment.button_next.click();//Click on next button
            }
            if(!takeAssignment.submitButton.isDisplayed()) {
                takeAssignment.button_next.click();//Click on submit button
            }
            takeAssignment.submitButton.click();//Click on submit button
            new Navigator().logout(firefoxWebdriver);//Logout student


            new Login().loginAsStudent(appendChar+3,dataIndex,firefoxWebdriver);//Login as student3
            new Assignment().startAssignment(dataIndex,firefoxWebdriver);
            for(int i=0;i<5;i++){
                takeAssignment.trueFalse.get(0).click();
                takeAssignment.button_next.click();//Click on next button
            }
            if(!takeAssignment.submitButton.isDisplayed()) {
                takeAssignment.button_next.click();//Click on submit button
            }
            takeAssignment.submitButton.click();//Click on submit button
            new Navigator().logout(firefoxWebdriver);//Logout student

            detailedResponse.detailedResponseBackArrow.click();//go back
            studentResponse.barOneCorrectUnderPerformance.get(0).click();//Click on Q1 bar

            //Expected - Question Centric View should Display all Student's response for every question.
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"3","3 student's responses are not displayed which is not expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"3","3 students are not displayed");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"questionCentricViewReAttemptst"+appendChar+3,"Student3 is not displayed");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"questionCentricViewReAttemptst"+appendChar+2,"Student2 is not displayed");
            Assert.assertEquals(detailedResponse.list_studentName.get(2).getText(),"questionCentricViewReAttemptst"+appendChar+1,"Student1 is not displayed");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(0).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student3 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(1).getAttribute("class"),"true-false-student-answer-select","Student3 response is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(2).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student3 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(3).getAttribute("class"),"true-false-student-answer-select","Student3 response is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(4).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student1 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(5).getAttribute("class"),"true-false-student-answer-select","Student1 response is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bars_3students.size()),"3","3 students bar are not displayed");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student3 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+3),"Student3 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(2));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"3","3 bubbles are not displayed as expected");

            //Verify Responses for question2
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            detailedResponse.dropdown_questions.get(1).click();//Select question 2
            Thread.sleep(2000);
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"3","3 student's responses are not displayed which is not expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"3","3 students are not displayed");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"questionCentricViewReAttemptst"+appendChar+3,"Student3 is not displayed");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"questionCentricViewReAttemptst"+appendChar+2,"Student2 is not displayed");
            Assert.assertEquals(detailedResponse.list_studentName.get(2).getText(),"questionCentricViewReAttemptst"+appendChar+1,"Student1 is not displayed");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(0).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student3 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(1).getAttribute("class"),"true-false-student-answer-select","Student3 response is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(2).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student3 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(3).getAttribute("class"),"true-false-student-answer-select","Student3 response is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(4).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student1 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(5).getAttribute("class"),"true-false-student-answer-select","Student1 response is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bars_1studentAttempted.size()),"3","3 students bar are not displayed");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_1studentAttempted.get(0));//Hover over on student3 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+3),"Student3 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_1studentAttempted.get(1));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_1studentAttempted.get(2));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"3","3 bubbles are not displayed as expected");

            //Verify Responses for question3
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            detailedResponse.dropdown_questions.get(2).click();//Select question 3
            Thread.sleep(2000);
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"3","3 student's responses are not displayed which is not expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"3","3 students are not displayed");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"questionCentricViewReAttemptst"+appendChar+3,"Student3 is not displayed");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"questionCentricViewReAttemptst"+appendChar+2,"Student2 is not displayed");
            Assert.assertEquals(detailedResponse.list_studentName.get(2).getText(),"questionCentricViewReAttemptst"+appendChar+1,"Student1 is not displayed");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(0).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student3 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(1).getAttribute("class"),"true-false-student-answer-select","Student3 response is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(2).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student3 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(3).getAttribute("class"),"true-false-student-answer-select","Student3 response is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(4).getAttribute("class"),"true-false-student-answer-select","Student1 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(5).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student1 response is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bars_1studentAttempted.size()),"3","3 students bar are not displayed");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_1studentAttempted.get(0));//Hover over on student3 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+3),"Student3 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_1studentAttempted.get(1));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_1studentAttempted.get(2));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"3","3 bubbles are not displayed as expected");

            //Verify Responses for question3
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            detailedResponse.dropdown_questions.get(3).click();//Select question 4
            Thread.sleep(2000);
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"3","3 student's responses are not displayed which is not expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"3","3 students are not displayed");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"questionCentricViewReAttemptst"+appendChar+3,"Student3 is not displayed");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"questionCentricViewReAttemptst"+appendChar+2,"Student2 is not displayed");
            Assert.assertEquals(detailedResponse.list_studentName.get(2).getText(),"questionCentricViewReAttemptst"+appendChar+1,"Student1 is not displayed");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(0).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student3 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(1).getAttribute("class"),"true-false-student-answer-select","Student3 response is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(2).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student3 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(3).getAttribute("class"),"true-false-student-answer-select","Student3 response is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(4).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student1 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(5).getAttribute("class"),"true-false-student-answer-select","Student1 response is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bars_3students.size()),"3","3 students bar are not displayed");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student3 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+3),"Student3 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(2));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"3","3 bubbles are not displayed as expected");

            //Verify Responses for question5
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            detailedResponse.dropdown_questions.get(4).click();//Select question 5
            Thread.sleep(2000);
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"3","3 student's responses are not displayed which is not expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"3","3 students are not displayed");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"questionCentricViewReAttemptst"+appendChar+3,"Student3 is not displayed");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"questionCentricViewReAttemptst"+appendChar+2,"Student2 is not displayed");
            Assert.assertEquals(detailedResponse.list_studentName.get(2).getText(),"questionCentricViewReAttemptst"+appendChar+1,"Student1 is not displayed");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(0).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student3 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(1).getAttribute("class"),"true-false-student-answer-select","Student3 response is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(2).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student3 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(3).getAttribute("class"),"true-false-student-answer-select","Student3 response is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(4).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student1 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(5).getAttribute("class"),"true-false-student-answer-select","Student1 response is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bars_3students.size()),"3","3 students bar are not displayed");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student3 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+3),"Student3 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(2));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("questionCentricViewReAttemptst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"3","3 bubbles are not displayed as expected");
            new Navigator().logout();
            firefoxWebdriver.quit();

        }catch (Exception e){
            Assert.fail("Exception in test case 'questionCentricViewReAttempt' of class 'questionCentricStudentView'", e);

        }
    }

    @Test(priority = 14,enabled = true)
    public void studentCentricViewStudentNotAttempted(){
        try{
            int dataIndex = 1297;
            String appendChar = "a";

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);
            new Navigator().logout();//log out

            //Register 3 students
            for(int i=1;i<=3;i++){
                new SignUp().studentDirectRegister(appendChar+i, classCode, dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create assignment having 5 questions
           new Assignment().create(dataIndex,"truefalse");
            for(int i=0; i<4; i++){
                new Assignment().addQuestion(dataIndex,"truefalse");
            }
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar+1,dataIndex);//Login as student1
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar+3,dataIndex);//Login as student3
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().logout();//log out

            Thread.sleep(360000);

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            studentResponse.barTwoCorrectUnderPerformance.get(0).click();//Click on Q1 bar

            //Expected - It should Display the Responses of all Students. Student B's response for any question should be Shown as though he has skipped it
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"3","3 student's responses are not displayed which is not expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"3","3 students are not displayed");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"studentCentricViewStudentNotAttemptedst"+appendChar+2,"Student2 is not displayed");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"studentCentricViewStudentNotAttemptedst"+appendChar+3,"Student3 is not displayed");
            Assert.assertEquals(detailedResponse.list_studentName.get(2).getText(),"studentCentricViewStudentNotAttemptedst"+appendChar+1,"Student1 is not displayed");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(0).getAttribute("class"),"true-false-student-answer-select","Student2 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(1).getAttribute("class"),"true-false-student-answer-select","Student2 response is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(2).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student3 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(3).getAttribute("class"),"true-false-student-answer-select","Student3 response is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_trueFalse.get(4).getAttribute("class"),"true-false-student-answer-select true-false-student-answer-clicked","Student1 response is not displayed as expected");
            Assert.assertEquals(detailedResponse.options_trueFalse.get(5).getAttribute("class"),"true-false-student-answer-select","Student1 response is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bars_3students.size()),"3","3 students bar are not displayed");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("studentCentricViewStudentNotAttemptedst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student3 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("studentCentricViewStudentNotAttemptedst"+appendChar+3),"Student3 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(2));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("studentCentricViewStudentNotAttemptedst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"3","3 bubbles are not displayed as expected");

        }catch (Exception e){
            Assert.fail("Exception in test case 'studentCentricViewStudentNotAttempted' of class 'QuestionCentricStudentView'", e);

        }
    }

    @Test(priority = 15,enabled = true)
    public void timeGraphForQuestionCentricView(){
        try{
            int dataIndex = 1504;
            String appendChar = "a";

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);
            TakeAssignment takeAssignment = PageFactory.initElements(driver, TakeAssignment.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);
            new Navigator().logout();//log out

            //Register 3 students
            for(int i=1;i<=3;i++){
                new SignUp().studentDirectRegister(appendChar+i, classCode, dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create assignment having 5 questions
            new Assignment().create(dataIndex,"truefalse");
            for(int i=0; i<4; i++){
                new Assignment().addQuestion(dataIndex,"truefalse");
            }
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar+1,dataIndex);//Login as student1
            new Assignment().openAssignment(dataIndex);//Open the assignment
            takeAssignment.trueFalse.get(0).click();//Attempt question1 correctly

            //Navigate to 2nd question after 10 seconds
            Thread.sleep(10000);
            takeAssignment.navigateToQuestion.get(1).click();//Click on 2nd question
            takeAssignment.trueFalse.get(0).click();//Attempt question2 correctly

            //Navigate to 3rd question after 10 seconds
            Thread.sleep(10000);
            takeAssignment.navigateToQuestion.get(2).click();//Click on 3rd question
            takeAssignment.trueFalse.get(0).click();//Attempt question3 correctly

            //Navigate to 4th question after 10 seconds
            Thread.sleep(10000);
            takeAssignment.navigateToQuestion.get(3).click();//Click on 4th question
            takeAssignment.trueFalse.get(0).click();//Attempt question4 correctly

            //Navigate to 5th question after 10 seconds
            Thread.sleep(10000);
            takeAssignment.navigateToQuestion.get(4).click();//Click on 5th question
            takeAssignment.trueFalse.get(0).click();//Attempt question5 correctly
            Thread.sleep(10000);

            takeAssignment.button_next.click();//Click on submit button
            Thread.sleep(2000);
            if(!takeAssignment.submitButton.isDisplayed()) {
                takeAssignment.button_next.click();//Click on submit button
            }
            takeAssignment.submitButton.click();//Click on submit button
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar+2,dataIndex);//Login as student2
            new Assignment().openAssignment(dataIndex);//Open the assignment
            takeAssignment.trueFalse.get(0).click();//Attempt question1 correctly

            //Navigate to 2nd question after 20 seconds
            Thread.sleep(20000);
            takeAssignment.navigateToQuestion.get(1).click();//Click on 2nd question
            takeAssignment.trueFalse.get(0).click();//Attempt question2 correctly

            //Navigate to 3rd question after 20 seconds
            Thread.sleep(20000);
            takeAssignment.navigateToQuestion.get(2).click();//Click on 3rd question
            takeAssignment.trueFalse.get(0).click();//Attempt question3 correctly

            //Navigate to 4th question after 20 seconds
            Thread.sleep(20000);
            takeAssignment.navigateToQuestion.get(3).click();//Click on 4th question
            takeAssignment.trueFalse.get(0).click();//Attempt question4 correctly

            //Navigate to 5th question after 20 seconds
            Thread.sleep(20000);
            takeAssignment.navigateToQuestion.get(4).click();//Click on 5th question
            takeAssignment.trueFalse.get(0).click();//Attempt question5 correctly
            Thread.sleep(20000);

            takeAssignment.button_next.click();//Click on submit button
            Thread.sleep(2000);
            if(!takeAssignment.submitButton.isDisplayed()) {
                takeAssignment.button_next.click();//Click on submit button
            }
            takeAssignment.submitButton.click();//Click on submit button
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar+3,dataIndex);//Login as student3
            new Assignment().openAssignment(dataIndex);//Open the assignment
            takeAssignment.trueFalse.get(0).click();//Attempt question1 correctly

            //Navigate to 2nd question after 30 seconds
            Thread.sleep(30000);
            takeAssignment.navigateToQuestion.get(1).click();//Click on 2nd question
            takeAssignment.trueFalse.get(0).click();//Attempt question2 correctly

            //Navigate to 3rd question after 30 seconds
            Thread.sleep(30000);
            takeAssignment.navigateToQuestion.get(2).click();//Click on 3rd question
            takeAssignment.trueFalse.get(0).click();//Attempt question3 correctly

            //Navigate to 4th question after 30 seconds
            Thread.sleep(30000);
            takeAssignment.navigateToQuestion.get(3).click();//Click on 4th question
            takeAssignment.trueFalse.get(0).click();//Attempt question4 correctly

            //Navigate to 5th question after 30 seconds
            Thread.sleep(30000);
            takeAssignment.navigateToQuestion.get(4).click();//Click on 5th question
            takeAssignment.trueFalse.get(0).click();//Attempt question5 correctly
            Thread.sleep(30000);

            takeAssignment.button_next.click();//Click on submit button
            Thread.sleep(2000);
            if(!takeAssignment.submitButton.isDisplayed()) {
                takeAssignment.button_next.click();//Click on submit button
            }
            takeAssignment.submitButton.click();//Click on submit button
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response

            studentResponse.barOneCorrectUnderPerformance.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            //Expected - The Summary Graph should display 10 Seconds for Student A, 20 Seconds for Student B and 30 Seconds for Student C for all the questions
            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student3 bar
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds): 32"),"Student3 response time is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student2 bar
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds): 22"),"Student2 response time is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(2));//Hover over on student1 bar
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds): 12"),"Student1 response time is not displayed as expected");

            detailedResponse.list_dropdown.get(1).click();//Click on question list
            detailedResponse.dropdown_questions.get(1).click();//Select question2
            Thread.sleep(2000);

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student3 bar
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds): 30"),"Student3 response time is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student2 bar
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds): 20"),"Student2 response time is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(2));//Hover over on student1 bar
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds): 10"),"Student1 response time is not displayed as expected");

            detailedResponse.list_dropdown.get(1).click();//Click on question list
            detailedResponse.dropdown_questions.get(2).click();//Select question3
            Thread.sleep(2000);

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student3 bar
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds): 30"),"Student3 response time is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student2 bar
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds): 20"),"Student2 response time is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(2));//Hover over on student1 bar
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds): 10"),"Student1 response time is not displayed as expected");

            detailedResponse.list_dropdown.get(1).click();//Click on question list
            detailedResponse.dropdown_questions.get(3).click();//Select question4
            Thread.sleep(2000);

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student3 bar
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds): 30"),"Student3 response time is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student2 bar
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds): 20"),"Student2 response time is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(2));//Hover over on student1 bar
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds): 10"),"Student1 response time is not displayed as expected");

            detailedResponse.list_dropdown.get(1).click();//Click on question list
            detailedResponse.dropdown_questions.get(4).click();//Select question5
            Thread.sleep(2000);

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student3 bar
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds): 30"),"Student3 response time is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student2 bar
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds): 20"),"Student2 response time is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(2));//Hover over on student1 bar
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds): 10"),"Student1 response time is not displayed as expected");

            new Navigator().logout();//log out

        }catch (Exception e){
            Assert.fail("Exception in test case 'timeGraphForQuestionCentricView' of class 'QuestionCentricStudentView'", e);

        }
    }

    @Test(priority = 16,enabled = true)
    public void summaryGraphManuallyGraded()
    {
        try
        {
            int dataIndex = 116;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);

            String appendChar1 = "a2";
            String appendChar2 = "b2";
            String appendChar3 = "c2";

            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Navigator().logout();//log out

            //Register student1
            new SignUp().studentDirectRegister(appendChar1, classCode, dataIndex);
            new Navigator().logout();//log out

            //Register student2
            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);
            new Navigator().logout();//log out


            //Register student3
            new SignUp().studentDirectRegister(appendChar3,classCode,dataIndex);
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as an instructor
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().addQuestion(dataIndex,"multiplechoice");
            new Assignment().addQuestion(dataIndex,"essay");
            new Assignment().assignToStudent(dataIndex,appendChar1);//assign to student
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);//Login as a student1
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar2,dataIndex);//Login as a student2
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar3,dataIndex);//Login as a student2
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login a san instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            Thread.sleep(2000);
            studentResponse.barOneCorrectUnderPerformance.get(0).click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(detailedResponse.bars_3students.get(0)));
            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("3"),"time is not displaying properly");
            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("3"),"time is not displaying properly");
            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(2));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("3"),"time is not displaying properly");

            detailedResponse.scorePerQuestion.get(0).clear();
            detailedResponse.scorePerQuestion.get(0).sendKeys("1");
            detailedResponse.scorePerQuestion.get(1).clear();
            detailedResponse.scorePerQuestion.get(1).sendKeys(".5");
            detailedResponse.scorePerQuestion.get(2).clear();
            detailedResponse.scorePerQuestion.get(2).sendKeys("0");
            Assert.assertEquals(detailedResponse.breadCrumb.getText(),"Dashboard/Student Responses/Detailed Responses (Question)");

            detailedResponse.detailedResponseBackArrow.click();
            Thread.sleep(2000);

            Assert.assertTrue(studentResponse.bar3QuestionsAttemptedOutOf5.get(0).getAttribute("fill").contains("#cc470f"),"Color is not displaying properly");
            Assert.assertTrue(studentResponse.bar3QuestionsAttemptedOutOf5.get(1).getAttribute("fill").contains("#6bb45f"),"Color is not displaying properly");
            Assert.assertTrue(studentResponse.bar3QuestionsAttemptedOutOf5.get(0).getAttribute("fill").contains("#EBBB3D"),"Color is not displaying properly");

            studentResponse.releaseGrade.click();
            studentResponse.bar3QuestionsAttemptedOutOf5.get(0).click();
            Thread.sleep(2000);
            Assert.assertTrue(detailedResponse.bars_3students.get(0).getAttribute("fill").contains("#73B966"),"Color is not displaying properly");
            Assert.assertTrue(detailedResponse.bars_3students.get(1).getAttribute("fill").contains("url(#highcharts-pattern"),"Color is not displaying properly");
            Assert.assertTrue(detailedResponse.bars_student.get(0).getAttribute("fill").contains("url(#highcharts-pattern"),"Color is not displaying properly");
            Assert.assertTrue(detailedResponse.scorePerQuestion.get(0).getAttribute("value").contains("1"),"point not displaying properly");
            Assert.assertTrue(detailedResponse.scorePerQuestion.get(1).getAttribute("value").contains(".5"),"point not displaying properly");
            Assert.assertTrue( detailedResponse.scorePerQuestion.get(2).getAttribute("value").contains("0"),"point not displaying properly");

            studentResponse.backButton.click();
            Thread.sleep(2000);
            Assert.assertTrue(driver.getCurrentUrl().contains("assignment"),"Current page is not assignment");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case 'summaryGraphManuallyGraded' of class 'QuestionCentricStudentView'", e);
        }
    }


    @Test(priority = 17,enabled = true)
    public void summaryGraph(){
        try{
            int dataIndex = 2063;
            String appendChar = "a";

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);
            TakeAssignment takeAssignment = PageFactory.initElements(driver, TakeAssignment.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);
            new Navigator().logout();//log out

            //Register 3 students
            for(int i=1;i<=3;i++){
                new SignUp().studentDirectRegister(appendChar+i, classCode, dataIndex);
                new Navigator().logout();//log out
            }

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create assignment having 5 multiple selection questions
            new Assignment().create(dataIndex,"multipleselection");
            for(int i=0; i<4; i++){
                new Assignment().addQuestion(dataIndex,"multipleselection");
            }
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment
            new Navigator().logout();//log out

            //Login as a student1,attempt all the questions correctly and submit
            new Login().loginAsStudent(appendChar+1,dataIndex);
            new Assignment().openAssignment(dataIndex);
            for(int i=1; i<=5; i++) {
                new AttemptQuestion().multipleSelection(false, "correct", dataIndex);
                takeAssignment.button_next.click();

            }
            Thread.sleep(2000);
            if(!takeAssignment.submitButton.isDisplayed()) {
                takeAssignment.button_next.click();//Click on submit button
            }
            takeAssignment.submitButton.click();//Click on submit button
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            studentResponse.bar3QuestionsAttemptedOutOf5.get(0).click();//Click on Q1 bar
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            //Expected 1 - Question Centric View should Display only Student A's response
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"1","only 1 student response is not displayed as expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"1","only 1 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"summaryGraphst"+appendChar+1,"only 1 student name is not displayed as expected");

            for(int i=0;i<=1;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick")){
                    Assert.fail("Student response is not displayed as given by him");
                }
            }

            for(int i=2;i<=3;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student response is not displayed as given by him");
                }
            }
            Assert.assertEquals(String.valueOf(detailedResponse.bars_1studentAttempted.size()),"1","only 1 student bar is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_1studentAttempted.get(0));//Hover over on student1 bar

            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"1","1 bubbles is not displayed as expected");

            //Expected -  Summary Graph should show a green bar for Student A for Getting it right
            Assert.assertEquals(detailedResponse.bars_1studentAttempted.get(0).getAttribute("fill"),"rgb(140,210,127)","Color is not displayed as green");

            //Expected - it should also Display the amount of Time the Student took to Respond For this Particular question
            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_1studentAttempted.get(0));//Hover over on student1 bar
            String studentDetails = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTime = Integer.parseInt(studentDetails.substring(studentDetails.length()-1));

            if(!(respondTime>=0)){
                Assert.fail("Respond time is not displayed");
            }

            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected");

            //Switch to Next question from Question Selection Dropdown one by one And Verify the Same
            //Verifying the above for Q2
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            detailedResponse.dropdown_questions.get(1).click();//Switch to question 2
            Thread.sleep(1000);

            Assert.assertEquals(detailedResponse.bars_3students.get(0).getAttribute("fill"),"#73B966","Color is not displayed as green");
            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student1 bar
            String studentDetailsQ2 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeQ2 = Integer.parseInt(studentDetails.substring(studentDetailsQ2.length()-1));

            if(!(respondTimeQ2>=0)){
                Assert.fail("Respond time is not displayed");
            }

            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected");

            //Verifying the above for Q3
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            detailedResponse.dropdown_questions.get(2).click();//Switch to question 3
            Thread.sleep(1000);

            Assert.assertEquals(detailedResponse.bars_3students.get(0).getAttribute("fill"),"#73B966","Color is not displayed as green");
            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student1 bar
            String studentDetailsQ3 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeQ3 = Integer.parseInt(studentDetails.substring(studentDetailsQ3.length()-1));

            if(!(respondTimeQ3>=0)){
                Assert.fail("Respond time is not displayed");
            }

            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected");

            //Verifying the above for Q4
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            detailedResponse.dropdown_questions.get(3).click();//Switch to question 4
            Thread.sleep(1000);

            Assert.assertEquals(detailedResponse.bars_3students.get(0).getAttribute("fill"),"#73B966","Color is not displayed as green");
            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student1 bar
            String studentDetailsQ4 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeQ4 = Integer.parseInt(studentDetails.substring(studentDetailsQ4.length()-1));

            if(!(respondTimeQ4>=0)){
                Assert.fail("Respond time is not displayed");
            }

            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected");

            //Verifying the above for Q5
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            detailedResponse.dropdown_questions.get(4).click();//Switch to question 4
            Thread.sleep(1000);

            Assert.assertEquals(detailedResponse.bars_3students.get(0).getAttribute("fill"),"#73B966","Color is not displayed as green");
            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student1 bar
            String studentDetailsQ5 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeQ5 = Integer.parseInt(studentDetails.substring(studentDetailsQ5.length()-1));

            if(!(respondTimeQ5>=0)){
                Assert.fail("Respond time is not displayed");
            }

            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected");

            new Navigator().logout();//log out

            // Login as Student B and Get everything Partially Correct
            new Login().loginAsStudent(appendChar+2,dataIndex);
            new Assignment().openAssignment(dataIndex);
            for(int i=1; i<=5; i++) {
                new AttemptQuestion().multipleSelection(false, "partialCorrect", dataIndex);
                takeAssignment.button_next.click();
            }
            Thread.sleep(2000);
            if(!takeAssignment.submitButton.isDisplayed()) {
                takeAssignment.button_next.click();//Click on submit button
            }
            takeAssignment.submitButton.click();//Click on submit button
            new Navigator().logout();//log out

            //From instructor's side, open Question Centric View for 1st question
            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            studentResponse.bar3QuestionsAttemptedOutOf5.get(0).click();//Click on Q1
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            //Expected - Question Centric View should Display Student A's and B's response
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"2","2 student response is not displayed as expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"2","2 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"summaryGraphst"+appendChar+2,"student2 name is not displayed as expected");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"summaryGraphst"+appendChar+1,"student1 name is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_multiSelection.get(0).getAttribute("class"),"multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick","Student2 response is not displayed as expected");
            for(int i=1;i<=3;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student2 response is not displayed as given by him");
                }
            }

            for(int i=4;i<=5;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            for(int i=6;i<=7;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            Assert.assertEquals(String.valueOf(detailedResponse.bars_studentPartiallyAttempted.size()),"1","1 bar is not displayed for student2 as partially attempted");
            Assert.assertEquals(String.valueOf(detailedResponse.bars_1studentAttempted.size()),"1","1 bar is not displayed for student1 as correctly attempted");


            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_1studentAttempted.get(0));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"2","2 bubbles are not displayed as expected");

            //Expected - Summary Graph should show a green bar for Student A for Getting it right and partially Filled Yellow Bar for Student B
            Assert.assertEquals(detailedResponse.bars_studentPartiallyAttempted.get(0).getAttribute("fill"),"url(#highcharts-pattern-2)","Student2 bar is not displayed as partially attempted");
            Assert.assertEquals(detailedResponse.bars_1studentAttempted.get(0).getAttribute("fill"),"rgb(140,210,127)","Student1 bar is not displayed as correctly attempted");

            //Expected - It should also Display the amount of Time the Corresponding Students took to respond for this particular question
            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted.get(0));//Hover over on student2 bar
            String studentDetailsSt2Q1 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeSt2Q1 = Integer.parseInt(studentDetailsSt2Q1.substring(studentDetailsSt2Q1.length()-1));
            if(!(respondTimeSt2Q1>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student2");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_1studentAttempted.get(0));//Hover over on student1 bar
            String studentDetailsSt1Q1 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeSt1Q1 = Integer.parseInt(studentDetailsSt1Q1.substring(studentDetailsSt1Q1.length()-1));
            if(!(respondTimeSt1Q1>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student1");

            //Switch to Next question from Question Selection Dropdown one by one And Verify the Same
            //Verifying above for Q2
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            detailedResponse.dropdown_questions.get(1).click();//Switch to question 2
            Thread.sleep(1000);

            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"2","2 student response is not displayed as expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"2","2 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"summaryGraphst"+appendChar+2,"student2 name is not displayed as expected");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"summaryGraphst"+appendChar+1,"student1 name is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_multiSelection.get(0).getAttribute("class"),"multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick","Student2 response is not displayed as expected");
            for(int i=1;i<=3;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student2 response is not displayed as given by him");
                }
            }


            for(int i=4;i<=5;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            for(int i=6;i<=7;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            Assert.assertEquals(String.valueOf(detailedResponse.bars_studentPartiallyAttempted1.size()),"1","1 bar is not displayed for student2 as partially attempted");
            Assert.assertEquals(String.valueOf(detailedResponse.bars_3students.size()),"1","1 bar is not displayed for student1 as correctly attempted");


            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted1.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"2","2 bubbles are not displayed as expected");

            Assert.assertEquals(detailedResponse.bars_studentPartiallyAttempted1.get(0).getAttribute("fill"),"url(#highcharts-pattern-2)","Student2 bar is not displayed as partially attempted");
            Assert.assertEquals(detailedResponse.bars_3students.get(0).getAttribute("fill"),"rgb(140,210,127)","Student1 bar is not displayed as correctly attempted");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted1.get(0));//Hover over on student2 bar
            String studentDetailsSt2Q2 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeSt2Q2 = Integer.parseInt(studentDetailsSt2Q2.substring(studentDetailsSt2Q2.length()-1));
            if(!(respondTimeSt2Q2>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student2");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student1 bar
            String studentDetailsSt1Q2 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeSt1Q2 = Integer.parseInt(studentDetailsSt1Q2.substring(studentDetailsSt1Q2.length()-1));
            if(!(respondTimeSt1Q2>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student1");

            //Verifying above for Q3
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            detailedResponse.dropdown_questions.get(2).click();//Switch to question 3
            Thread.sleep(1000);

            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"2","2 student response is not displayed as expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"2","2 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"summaryGraphst"+appendChar+2,"student2 name is not displayed as expected");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"summaryGraphst"+appendChar+1,"student1 name is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_multiSelection.get(0).getAttribute("class"),"multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick","Student2 response is not displayed as expected");
            for(int i=1;i<=3;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student2 response is not displayed as given by him");
                }
            }


            for(int i=4;i<=5;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            for(int i=6;i<=7;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            Assert.assertEquals(String.valueOf(detailedResponse.bars_studentPartiallyAttempted1.size()),"1","1 bar is not displayed for student2 as partially attempted");
            Assert.assertEquals(String.valueOf(detailedResponse.bars_3students.size()),"1","1 bar is not displayed for student1 as correctly attempted");


            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted1.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"2","2 bubbles are not displayed as expected");

            Assert.assertEquals(detailedResponse.bars_studentPartiallyAttempted1.get(0).getAttribute("fill"),"url(#highcharts-pattern-2)","Student2 bar is not displayed as partially attempted");
            Assert.assertEquals(detailedResponse.bars_3students.get(0).getAttribute("fill"),"rgb(140,210,127)","Student1 bar is not displayed as correctly attempted");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted1.get(0));//Hover over on student2 bar
            String studentDetailsSt2Q3 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeSt2Q3 = Integer.parseInt(studentDetailsSt2Q3.substring(studentDetailsSt2Q3.length()-1));
            if(!(respondTimeSt2Q3>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student2");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student1 bar
            String studentDetailsSt1Q3 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeSt1Q3 = Integer.parseInt(studentDetailsSt1Q3.substring(studentDetailsSt1Q3.length()-1));
            if(!(respondTimeSt1Q3>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student1");


            //Verifying above for Q4
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            detailedResponse.dropdown_questions.get(3).click();//Switch to question 4
            Thread.sleep(1000);

            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"2","2 student response is not displayed as expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"2","2 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"summaryGraphst"+appendChar+2,"student2 name is not displayed as expected");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"summaryGraphst"+appendChar+1,"student1 name is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_multiSelection.get(0).getAttribute("class"),"multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick","Student2 response is not displayed as expected");
            for(int i=1;i<=3;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student2 response is not displayed as given by him");
                }
            }


            for(int i=4;i<=5;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            for(int i=6;i<=7;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            Assert.assertEquals(String.valueOf(detailedResponse.bars_studentPartiallyAttempted1.size()),"1","1 bar is not displayed for student2 as partially attempted");
            Assert.assertEquals(String.valueOf(detailedResponse.bars_3students.size()),"1","1 bar is not displayed for student1 as correctly attempted");


            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted1.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"2","2 bubbles are not displayed as expected");

            Assert.assertEquals(detailedResponse.bars_studentPartiallyAttempted1.get(0).getAttribute("fill"),"url(#highcharts-pattern-2)","Student2 bar is not displayed as partially attempted");
            Assert.assertEquals(detailedResponse.bars_3students.get(0).getAttribute("fill"),"rgb(140,210,127)","Student1 bar is not displayed as correctly attempted");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted1.get(0));//Hover over on student2 bar
            String studentDetailsSt2Q4 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeSt2Q4 = Integer.parseInt(studentDetailsSt2Q4.substring(studentDetailsSt2Q4.length()-1));
            if(!(respondTimeSt2Q4>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student2");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student1 bar
            String studentDetailsSt1Q4 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeSt1Q4 = Integer.parseInt(studentDetailsSt1Q4.substring(studentDetailsSt1Q4.length()-1));
            if(!(respondTimeSt1Q4>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student1");

            //Verifying above for Q5
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            detailedResponse.dropdown_questions.get(4).click();//Switch to question 5
            Thread.sleep(1000);

            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"2","2 student response is not displayed as expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"2","2 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"summaryGraphst"+appendChar+2,"student2 name is not displayed as expected");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"summaryGraphst"+appendChar+1,"student1 name is not displayed as expected");

            Assert.assertEquals(detailedResponse.options_multiSelection.get(0).getAttribute("class"),"multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick","Student2 response is not displayed as expected");
            for(int i=1;i<=3;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student2 response is not displayed as given by him");
                }
            }


            for(int i=4;i<=5;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            for(int i=6;i<=7;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            Assert.assertEquals(String.valueOf(detailedResponse.bars_studentPartiallyAttempted1.size()),"1","1 bar is not displayed for student2 as partially attempted");
            Assert.assertEquals(String.valueOf(detailedResponse.bars_3students.size()),"1","1 bar is not displayed for student1 as correctly attempted");


            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted1.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"2","2 bubbles are not displayed as expected");

            Assert.assertEquals(detailedResponse.bars_studentPartiallyAttempted1.get(0).getAttribute("fill"),"url(#highcharts-pattern-2)","Student2 bar is not displayed as partially attempted");
            Assert.assertEquals(detailedResponse.bars_3students.get(0).getAttribute("fill"),"rgb(140,210,127)","Student1 bar is not displayed as correctly attempted");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted1.get(0));//Hover over on student2 bar
            String studentDetailsSt2Q5 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeSt2Q5 = Integer.parseInt(studentDetailsSt2Q5.substring(studentDetailsSt2Q5.length()-1));
            if(!(respondTimeSt2Q5>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student2");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student1 bar
            String studentDetailsSt1Q5 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeSt1Q5 = Integer.parseInt(studentDetailsSt1Q5.substring(studentDetailsSt1Q5.length()-1));
            if(!(respondTimeSt1Q5>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student1");
            new Navigator().logout();//log out

            // Login as Student C and get everything wrong
            new Login().loginAsStudent(appendChar+3,dataIndex);
            new Assignment().openAssignment(dataIndex);
            for(int i=1; i<=5; i++) {
                new AttemptQuestion().multipleSelection(false, "incorrect", dataIndex);
                takeAssignment.button_next.click();
            }
            Thread.sleep(2000);
            if(!takeAssignment.submitButton.isDisplayed()) {
                takeAssignment.button_next.click();//Click on submit button
            }
            takeAssignment.submitButton.click();//Click on submit button
            new Navigator().logout();//log out

            //From instructor's side, open Question Centric View for 1st question
            new Login().loginAsInstructor(appendChar,dataIndex);
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            studentResponse.bar3QuestionsAttemptedOutOf5.get(0).click();//Click on Q1
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.question-no")));

            //Expected - Question Centric View should Display all students response
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"3","3 student response is not displayed as expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"3","3 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"summaryGraphst"+appendChar+3,"student3 name is not displayed as expected");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"summaryGraphst"+appendChar+2,"student2 name is not displayed as expected");
            Assert.assertEquals(detailedResponse.list_studentName.get(2).getText(),"summaryGraphst"+appendChar+1,"student1 name is not displayed as expected");

            for(int i=0;i<=1;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student3 response is not displayed as given by him");
                }
            }

            for(int i=2;i<=3;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick")){
                    Assert.fail("Student3 response is not displayed as given by him");
                }
            }

            Assert.assertEquals(detailedResponse.options_multiSelection.get(4).getAttribute("class"),"multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick","Student2 response is not displayed as expected");
            for(int i=5;i<=7;i++){
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student2 response is not displayed as given by him");
                }
            }

            for(int i=8;i<=9;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            for(int i=10;i<=11;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            Assert.assertEquals(String.valueOf(detailedResponse.bars_1studentAttempted.size()),"2","2 bar is not displayed(1 for student1 and other for student3)");
            Assert.assertEquals(String.valueOf(detailedResponse.bars_studentPartiallyAttempted.size()),"1","1 bar is not displayed for student2 as partially attempted");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_1studentAttempted.get(0));//Hover over on student3 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+3),"Student3 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_1studentAttempted.get(1));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"3","3 bubbles are not displayed as expected");

            //Expected -  Summary Graph should show a green bar for Student A for Getting it right, partially Filled Yellow Bar for Student B and a red bar for student C fro getting everything wrong
            Assert.assertEquals(detailedResponse.bars_1studentAttempted.get(0).getAttribute("fill"),"url(#highcharts-pattern-4)","Color is not displayed as red for student3");
            Assert.assertEquals(detailedResponse.bars_studentPartiallyAttempted.get(0).getAttribute("fill"),"url(#highcharts-pattern-5)","Color is not displayed as yellow for student2");
            Assert.assertEquals(detailedResponse.bars_1studentAttempted.get(1).getAttribute("fill"),"rgb(140,210,127)","Color is not displayed as green for student1");

            //Expected - It should also Display the amount of Time the Corresponding Students took to respond for this particular question and Scoring points
            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_1studentAttempted.get(0));//Hover over on student3 bar
            String studentDetailsSt3Q1 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeSt3Q1 = Integer.parseInt(studentDetailsSt3Q1.substring(studentDetailsSt3Q1.length()-1));
            if(!(respondTimeSt3Q1>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student3");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted.get(0));//Hover over on student2 bar
            String studentDetails1St2Q1 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTime1St2Q1 = Integer.parseInt(studentDetails1St2Q1.substring(studentDetails1St2Q1.length()-1));
            if(!(respondTime1St2Q1>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student2");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_1studentAttempted.get(1));//Hover over on student1 bar
            String studentDetails1St1Q1 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTime1St1Q1 = Integer.parseInt(studentDetails1St1Q1.substring(studentDetails1St1Q1.length()-1));
            if(!(respondTime1St1Q1>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student2");

            //Switch to Next question from Question Selection Dropdown one by one And Verify the Same
            //Verifying same as above for Q2
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            detailedResponse.dropdown_questions.get(1).click();//Switch to question 2
            Thread.sleep(1000);

            //Expected - Question Centric View should Display all students response
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"3","3 student response is not displayed as expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"3","3 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"summaryGraphst"+appendChar+3,"student3 name is not displayed as expected");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"summaryGraphst"+appendChar+2,"student2 name is not displayed as expected");
            Assert.assertEquals(detailedResponse.list_studentName.get(2).getText(),"summaryGraphst"+appendChar+1,"student1 name is not displayed as expected");

            for(int i=0;i<=1;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student3 response is not displayed as given by him");
                }
            }

            for(int i=2;i<=3;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick")){
                    Assert.fail("Student3 response is not displayed as given by him");
                }
            }

            Assert.assertEquals(detailedResponse.options_multiSelection.get(4).getAttribute("class"),"multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick","Student2 response is not displayed as expected");
            for(int i=5;i<=7;i++){
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student2 response is not displayed as given by him");
                }
            }

            for(int i=8;i<=9;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            for(int i=10;i<=11;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            Assert.assertEquals(String.valueOf(detailedResponse.bars_3students.size()),"2","2 bar is not displayed(1 for student1 and other for student3)");
            Assert.assertEquals(String.valueOf(detailedResponse.bars_studentPartiallyAttempted1.size()),"1","1 bar is not displayed for student2 as partially attempted");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student3 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+3),"Student3 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted1.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"3","3 bubbles are not displayed as expected");

            //Expected -  Summary Graph should show a green bar for Student A for Getting it right, partially Filled Yellow Bar for Student B and a red bar for student C fro getting everything wrong
            Assert.assertEquals(detailedResponse.bars_3students.get(0).getAttribute("fill"),"url(#highcharts-pattern-4)","Color is not displayed as red for student3");
            Assert.assertEquals(detailedResponse.bars_studentPartiallyAttempted1.get(0).getAttribute("fill"),"url(#highcharts-pattern-5)","Color is not displayed as yellow for student2");
            Assert.assertEquals(detailedResponse.bars_3students.get(1).getAttribute("fill"),"rgb(140,210,127)","Color is not displayed as green for student1");

            //Expected - It should also Display the amount of Time the Corresponding Students took to respond for this particular question and Scoring points
            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student3 bar
            String studentDetailsSt3Q2 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeSt3Q2 = Integer.parseInt(studentDetailsSt3Q2.substring(studentDetailsSt3Q2.length()-1));
            if(!(respondTimeSt3Q2>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student3");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted1.get(0));//Hover over on student2 bar
            String studentDetails1St2Q2 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTime1St2Q2 = Integer.parseInt(studentDetails1St2Q2.substring(studentDetails1St2Q2.length()-1));
            if(!(respondTime1St2Q2>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student2");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student1 bar
            String studentDetails1St1Q2 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTime1St1Q2 = Integer.parseInt(studentDetails1St1Q2.substring(studentDetails1St1Q2.length()-1));
            if(!(respondTime1St1Q2>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student2");


            //Verifying same as above for Q3
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            detailedResponse.dropdown_questions.get(2).click();//Switch to question 3
            Thread.sleep(1000);

            //Expected - Question Centric View should Display all students response
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"3","3 student response is not displayed as expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"3","3 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"summaryGraphst"+appendChar+3,"student3 name is not displayed as expected");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"summaryGraphst"+appendChar+2,"student2 name is not displayed as expected");
            Assert.assertEquals(detailedResponse.list_studentName.get(2).getText(),"summaryGraphst"+appendChar+1,"student1 name is not displayed as expected");

            for(int i=0;i<=1;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student3 response is not displayed as given by him");
                }
            }

            for(int i=2;i<=3;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick")){
                    Assert.fail("Student3 response is not displayed as given by him");
                }
            }

            Assert.assertEquals(detailedResponse.options_multiSelection.get(4).getAttribute("class"),"multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick","Student2 response is not displayed as expected");
            for(int i=5;i<=7;i++){
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student2 response is not displayed as given by him");
                }
            }

            for(int i=8;i<=9;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            for(int i=10;i<=11;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            Assert.assertEquals(String.valueOf(detailedResponse.bars_3students.size()),"2","2 bar is not displayed(1 for student1 and other for student3)");
            Assert.assertEquals(String.valueOf(detailedResponse.bars_studentPartiallyAttempted1.size()),"1","1 bar is not displayed for student2 as partially attempted");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student3 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+3),"Student3 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted1.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"3","3 bubbles are not displayed as expected");

            //Expected -  Summary Graph should show a green bar for Student A for Getting it right, partially Filled Yellow Bar for Student B and a red bar for student C fro getting everything wrong
            Assert.assertEquals(detailedResponse.bars_3students.get(0).getAttribute("fill"),"url(#highcharts-pattern-4)","Color is not displayed as red for student3");
            Assert.assertEquals(detailedResponse.bars_studentPartiallyAttempted1.get(0).getAttribute("fill"),"url(#highcharts-pattern-5)","Color is not displayed as yellow for student2");
            Assert.assertEquals(detailedResponse.bars_3students.get(1).getAttribute("fill"),"rgb(140,210,127)","Color is not displayed as green for student1");

            //Expected - It should also Display the amount of Time the Corresponding Students took to respond for this particular question and Scoring points
            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student3 bar
            String studentDetailsSt3Q3 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeSt3Q3 = Integer.parseInt(studentDetailsSt3Q3.substring(studentDetailsSt3Q3.length()-1));
            if(!(respondTimeSt3Q3>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student3");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted1.get(0));//Hover over on student2 bar
            String studentDetails1St2Q3 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTime1St2Q3 = Integer.parseInt(studentDetails1St2Q3.substring(studentDetails1St2Q3.length()-1));
            if(!(respondTime1St2Q3>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student2");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student1 bar
            String studentDetails1St1Q3 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTime1St1Q3 = Integer.parseInt(studentDetails1St1Q3.substring(studentDetails1St1Q3.length()-1));
            if(!(respondTime1St1Q3>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student2");

            //Verifying same as above for Q4
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            detailedResponse.dropdown_questions.get(3).click();//Switch to question 4
            Thread.sleep(1000);

            //Expected - Question Centric View should Display all students response
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"3","3 student response is not displayed as expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"3","3 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"summaryGraphst"+appendChar+3,"student3 name is not displayed as expected");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"summaryGraphst"+appendChar+2,"student2 name is not displayed as expected");
            Assert.assertEquals(detailedResponse.list_studentName.get(2).getText(),"summaryGraphst"+appendChar+1,"student1 name is not displayed as expected");

            for(int i=0;i<=1;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student3 response is not displayed as given by him");
                }
            }

            for(int i=2;i<=3;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick")){
                    Assert.fail("Student3 response is not displayed as given by him");
                }
            }

            Assert.assertEquals(detailedResponse.options_multiSelection.get(4).getAttribute("class"),"multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick","Student2 response is not displayed as expected");
            for(int i=5;i<=7;i++){
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student2 response is not displayed as given by him");
                }
            }

            for(int i=8;i<=9;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            for(int i=10;i<=11;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            Assert.assertEquals(String.valueOf(detailedResponse.bars_3students.size()),"2","2 bar is not displayed(1 for student1 and other for student3)");
            Assert.assertEquals(String.valueOf(detailedResponse.bars_studentPartiallyAttempted1.size()),"1","1 bar is not displayed for student2 as partially attempted");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student3 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+3),"Student3 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted1.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"3","3 bubbles are not displayed as expected");

            //Expected -  Summary Graph should show a green bar for Student A for Getting it right, partially Filled Yellow Bar for Student B and a red bar for student C fro getting everything wrong
            Assert.assertEquals(detailedResponse.bars_3students.get(0).getAttribute("fill"),"url(#highcharts-pattern-4)","Color is not displayed as red for student3");
            Assert.assertEquals(detailedResponse.bars_studentPartiallyAttempted1.get(0).getAttribute("fill"),"url(#highcharts-pattern-5)","Color is not displayed as yellow for student2");
            Assert.assertEquals(detailedResponse.bars_3students.get(1).getAttribute("fill"),"rgb(140,210,127)","Color is not displayed as green for student1");

            //Expected - It should also Display the amount of Time the Corresponding Students took to respond for this particular question and Scoring points
            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student3 bar
            String studentDetailsSt3Q4 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeSt3Q4 = Integer.parseInt(studentDetailsSt3Q4.substring(studentDetailsSt3Q4.length()-1));
            if(!(respondTimeSt3Q4>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student3");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted1.get(0));//Hover over on student2 bar
            String studentDetails1St2Q4 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTime1St2Q4 = Integer.parseInt(studentDetails1St2Q4.substring(studentDetails1St2Q4.length()-1));
            if(!(respondTime1St2Q4>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student2");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student1 bar
            String studentDetails1St1Q4 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTime1St1Q4 = Integer.parseInt(studentDetails1St1Q4.substring(studentDetails1St1Q4.length()-1));
            if(!(respondTime1St1Q4>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student2");

            //Verifying same as above for Q5
            detailedResponse.list_dropdown.get(1).click();//Click on question dropdown
            detailedResponse.dropdown_questions.get(4).click();//Switch to question 5
            Thread.sleep(1000);

            //Expected - Question Centric View should Display all students response
            Assert.assertEquals(String.valueOf(detailedResponse.questionLabelAtDetailedResponse.size()),"3","3 student response is not displayed as expected");
            Assert.assertEquals(String.valueOf(detailedResponse.list_studentName.size()),"3","3 student name is not displayed as expected");

            Assert.assertEquals(detailedResponse.list_studentName.get(0).getText(),"summaryGraphst"+appendChar+3,"student3 name is not displayed as expected");
            Assert.assertEquals(detailedResponse.list_studentName.get(1).getText(),"summaryGraphst"+appendChar+2,"student2 name is not displayed as expected");
            Assert.assertEquals(detailedResponse.list_studentName.get(2).getText(),"summaryGraphst"+appendChar+1,"student1 name is not displayed as expected");

            for(int i=0;i<=1;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student3 response is not displayed as given by him");
                }
            }

            for(int i=2;i<=3;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick")){
                    Assert.fail("Student3 response is not displayed as given by him");
                }
            }

            Assert.assertEquals(detailedResponse.options_multiSelection.get(4).getAttribute("class"),"multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick","Student2 response is not displayed as expected");
            for(int i=5;i<=7;i++){
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student2 response is not displayed as given by him");
                }
            }

            for(int i=8;i<=9;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-select-preview removeOnClick")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            for(int i=10;i<=11;i++) {
                if(!detailedResponse.options_multiSelection.get(i).getAttribute("class").equals("multiple-select-choice-icon-preview multiple-select-choice-icon-deselect")){
                    Assert.fail("Student1 response is not displayed as given by him");
                }
            }

            Assert.assertEquals(String.valueOf(detailedResponse.bars_3students.size()),"2","2 bar is not displayed(1 for student1 and other for student3)");
            Assert.assertEquals(String.valueOf(detailedResponse.bars_studentPartiallyAttempted1.size()),"1","1 bar is not displayed for student2 as partially attempted");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student3 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+3),"Student3 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted1.get(0));//Hover over on student2 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+2),"Student2 is not displayed as expected");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student1 bar
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("summaryGraphst"+appendChar+1),"Student1 is not displayed as expected");

            Assert.assertEquals(String.valueOf(detailedResponse.bubble_student.size()),"3","3 bubbles are not displayed as expected");

            //Expected -  Summary Graph should show a green bar for Student A for Getting it right, partially Filled Yellow Bar for Student B and a red bar for student C fro getting everything wrong
            Assert.assertEquals(detailedResponse.bars_3students.get(0).getAttribute("fill"),"url(#highcharts-pattern-4)","Color is not displayed as red for student3");
            Assert.assertEquals(detailedResponse.bars_studentPartiallyAttempted1.get(0).getAttribute("fill"),"url(#highcharts-pattern-5)","Color is not displayed as yellow for student2");
            Assert.assertEquals(detailedResponse.bars_3students.get(1).getAttribute("fill"),"rgb(140,210,127)","Color is not displayed as green for student1");

            //Expected - It should also Display the amount of Time the Corresponding Students took to respond for this particular question and Scoring points
            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(0));//Hover over on student3 bar
            String studentDetailsSt3Q5 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTimeSt3Q5 = Integer.parseInt(studentDetailsSt3Q5.substring(studentDetailsSt3Q5.length()-1));
            if(!(respondTimeSt3Q5>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student3");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_studentPartiallyAttempted1.get(0));//Hover over on student2 bar
            String studentDetails1St2Q5 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTime1St2Q5 = Integer.parseInt(studentDetails1St2Q5.substring(studentDetails1St2Q5.length()-1));
            if(!(respondTime1St2Q5>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student2");

            new MouseHover().mouserhoverbywebelement(detailedResponse.bars_3students.get(1));//Hover over on student1 bar
            String studentDetails1St1Q5 = detailedResponse.hoverOverDetails_studentBar.getText();
            int respondTime1St1Q5 = Integer.parseInt(studentDetails1St1Q5.substring(studentDetails1St1Q5.length()-1));
            if(!(respondTime1St1Q5>=0)){
                Assert.fail("Respond time is not displayed");
            }
            Assert.assertTrue(detailedResponse.hoverOverDetails_studentBar.getText().contains("Time (seconds):"),"Time text is not displayed as expected for student2");
            new Navigator().logout();//log out

        }catch (Exception e){
            Assert.fail("Exception in test case 'summaryGraph' of class 'QuestionCentricStudentView'", e);

        }
    }


















}