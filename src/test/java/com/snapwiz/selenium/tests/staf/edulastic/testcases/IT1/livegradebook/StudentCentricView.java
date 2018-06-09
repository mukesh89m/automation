package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.livegradebook;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssignmentSummary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.TakeAssignment;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.detailedResponse.DetailedResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Calendar;

/**
 * Created by shashank on 17-08-2015.
 */
public class StudentCentricView extends Driver{

    @Test(priority = 1,enabled = true)
    public void studentCentricView()
    {
        try {
            int dataIndex = 88;
            String appendChar ="a1";
            String appendChar1="b1";
            String appendChar2="c1";
            String appendChar3="d1";
            String appendChar4="e1";
            InstructorDashboard instructorDashboard = PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver, StudentResponse.class);
            DetailedResponse detailedResponse= PageFactory.initElements(driver, DetailedResponse.class);
            String assessmentname= ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//Create class
            new Navigator().logout();
            //signup with first student
            new SignUp().studentDirectRegister(appendChar, classCode, dataIndex);//Sign up as a student1
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar1, classCode, dataIndex);//Sign up as a student1
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar2, classCode, dataIndex);//Sign up as a student1
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar3, classCode, dataIndex);//Sign up as a student1
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar4, classCode, dataIndex);//Sign up as a student1
            new Navigator().logout();
            //login as instructor
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            //create five questions
            new Assignment().create(dataIndex, "truefalse");//Create a true false question
            for (int i = 1; i < 5; i++) {
                new Assignment().addQuestion(dataIndex, "truefalse");//add 10 true false question
            }
            new Assignment().assignToStudent(dataIndex, appendChar);//Assign to class
            new Navigator().navigateTo("dashboard");
            instructorDashboard.getViewLiveResult().click();
            Thread.sleep(3000);
            //count number of response card
            Assert.assertEquals(studentResponse.studentResponseCard.size(),5,"Number of response card not same as number of student");
            Assert.assertTrue(studentResponse.checkCardDisabled.get(0).getAttribute("class").contains("disabled"),"Response card is not disabled");
            Assert.assertTrue(studentResponse.checkCardDisabled.get(1).getAttribute("class").contains("disabled"),"Response card is not disabled");
            Assert.assertTrue(studentResponse.checkCardDisabled.get(2).getAttribute("class").contains("disabled"),"Response card is not disabled");
            Assert.assertTrue(studentResponse.checkCardDisabled.get(3).getAttribute("class").contains("disabled"),"Response card is not disabled");
            Assert.assertTrue(studentResponse.checkCardDisabled.get(4).getAttribute("class").contains("disabled"),"Response card is not disabled");



/*------------------------------------------------------------------------------------------------*/




            //created new instance for student for parallel execution  of student as well as instructor
            WebDriver firefoxWebdriver = new FirefoxDriver();
            firefoxWebdriver.close();
            firefoxWebdriver = new Driver().startDriver(firefoxWebdriver);
            TakeAssignment takeAssignment = PageFactory.initElements(firefoxWebdriver, TakeAssignment.class);

            new Login().loginAsStudent(appendChar, dataIndex, firefoxWebdriver);//login as a student1
            new Navigator().navigateTo("assignment", firefoxWebdriver);
            Thread.sleep(1000);
            //start assignment
            new Assignment().startAssignment(34, firefoxWebdriver);

            //attempt remaining true false question correctly
            for (int i=0;i<5;i++) {
                if(i%2==0) {
                    takeAssignment.trueFalse.get(0).click();
                }
                else
                {
                    takeAssignment.trueFalse.get(1).click();
                }
                try {
                    takeAssignment.button_next.click();
                }
                catch (Exception e)
                {
                    takeAssignment.finishButton.click();
                }
            }
            takeAssignment.submitButton.click();
            Thread.sleep(1000);
            //instructor
            //click on view detailed response
            studentResponse.viewDetailedResponse.get(0).click();
            //click on question bubble
            studentResponse.questionListAtDetailedViewResponse.get(3).click();
            studentResponse.questionListAtDetailedViewResponse.get(3).click();
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.questionLabelAtDetailedResponse.get(3).isDisplayed(),"Question Bubble is not working as expected");
            Calendar calendar=Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            //verify assessment name with due date
            Assert.assertEquals(detailedResponse.detailedResponseHeader.getText(),assessmentname+" (Due Date : 28 "+Calender.getCurrentMonthNameFull(calendar.get(Calendar.MONTH) + 1) +","+year+" 12:00 AM)");
            //navigate to
            detailedResponse.navigateToTop.click();
            Thread.sleep(1000);
            detailedResponse.overallFeedback.clear();
            detailedResponse.overallFeedback.sendKeys("Attempted");


            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(0).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");
            Assert.assertTrue(studentResponse.barGraphInDetailedResponse.get(1).getAttribute("fill").contains("url(#highcharts-pattern"),"color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(2).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");
            Assert.assertTrue(studentResponse.barGraphInDetailedResponse.get(3).getAttribute("fill").contains("url(#highcharts-pattern"),"color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(4).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");



            studentResponse.barGraphInDetailedResponse.get(2).click();
            Assert.assertTrue(detailedResponse.questionLabelAtDetailedResponse.get(2).isDisplayed(),"Question Bubble is not working as expected");

            detailedResponse.detailedResponseBackArrow.click();
            Thread.sleep(2000);

            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(0).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(1).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(2).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(3).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(4).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );

            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(0).getText().replaceAll("\n"," "),"PERFORMANCE 0/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(1).getText().replaceAll("\n"," "),"PERFORMANCE 0/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(2).getText().replaceAll("\n"," "),"PERFORMANCE 0/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(3).getText().replaceAll("\n"," "),"PERFORMANCE 0/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(4).getText().replaceAll("\n"," "),"PERFORMANCE 3/5","Performance is not displaying as expeccted" );


            //click on response under student card
            studentResponse.responseListAtStudentCard.get(23).click();
            Thread.sleep(3000);
           // Assert.assertTrue(studentResponse.questionLabelAtDetailedResponse.get(3).isDisplayed(),"Question Bubble is not working as expected");


/*------------------------------------------------------------------------------------------------*/

              new Navigator().logout(firefoxWebdriver);
             //login with second student
            new Login().loginAsStudent(appendChar1, dataIndex, firefoxWebdriver);//login as a student1
            new Navigator().navigateTo("assignment", firefoxWebdriver);
            Thread.sleep(1000);
            //start assignment
            new Assignment().startAssignment(34, firefoxWebdriver);

            //attempt remaining true false question correctly
            for (int i=0;i<5;i++) {
                if(i%2!=0) {
                    takeAssignment.trueFalse.get(0).click();
                }
                else
                {
                    takeAssignment.trueFalse.get(1).click();
                }
                try {
                    takeAssignment.button_next.click();
                }
                catch (Exception e)
                {
                    takeAssignment.finishButton.click();
                }
            }

            takeAssignment.submitButton.click();

            detailedResponse.detailedResponseBackArrow.click();
            Thread.sleep(2000);

            studentResponse.viewDetailedResponse.get(0).click();
            studentResponse.questionListAtDetailedViewResponse.get(3).click();
            studentResponse.questionListAtDetailedViewResponse.get(3).click();
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.questionLabelAtDetailedResponse.get(3).isDisplayed(),"Question Bubble is not working as expected");

            detailedResponse.navigateToTop.click();
            Thread.sleep(1000);

            detailedResponse.overallFeedback.clear();
            detailedResponse.overallFeedback.sendKeys("Attempted");

            Assert.assertTrue(studentResponse.barGraphInDetailedResponse.get(0).getAttribute("fill").contains("url(#highcharts-pattern"),"color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(1).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");
            Assert.assertTrue(studentResponse.barGraphInDetailedResponse.get(2).getAttribute("fill").contains("url(#highcharts-pattern"),"color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(3).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");
            Assert.assertTrue(studentResponse.barGraphInDetailedResponse.get(4).getAttribute("fill").contains("url(#highcharts-pattern"),"color of bar is not displaying as expected");




            studentResponse.barGraphInDetailedResponse.get(2).click();
            Assert.assertTrue(detailedResponse.questionLabelAtDetailedResponse.get(2).isDisplayed(),"Question Bubble is not working as expected");

            detailedResponse.detailedResponseBackArrow.click();
            Thread.sleep(2000);

            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(0).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(1).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(2).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(3).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(4).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );

            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(0).getText().replaceAll("\n"," "),"PERFORMANCE 0/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(1).getText().replaceAll("\n"," "),"PERFORMANCE 0/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(2).getText().replaceAll("\n"," "),"PERFORMANCE 0/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(3).getText().replaceAll("\n"," "),"PERFORMANCE 2/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(4).getText().replaceAll("\n"," "),"PERFORMANCE 3/5","Performance is not displaying as expeccted" );


            //click on response under student card
            studentResponse.responseListAtStudentCard.get(19).click();
            Thread.sleep(3000);
            Assert.assertTrue(detailedResponse.questionLabelAtDetailedResponse.get(4).isDisplayed(),"Question Bubble is not working as expected");
            detailedResponse.detailedResponseBackArrow.click();

/*------------------------------------------------------------------------------------------------*/

            new Navigator().logout(firefoxWebdriver);
            //login with second student
            new Login().loginAsStudent(appendChar2, dataIndex, firefoxWebdriver);//login as a student1
            new Navigator().navigateTo("assignment", firefoxWebdriver);
            Thread.sleep(1000);
            //start assignment
            new Assignment().startAssignment(34, firefoxWebdriver);

            //attempt remaining true false question correctly
            for (int i=0;i<5;i++) {
                takeAssignment.trueFalse.get(0).click();
                try {
                    takeAssignment.button_next.click();
                }
                catch (Exception e)
                {
                    takeAssignment.finishButton.click();
                }
            }



            //  studentResponse.detailedResponseBackArrow.click();
            Thread.sleep(2000);
            studentResponse.viewDetailedResponse.get(0).click();
            studentResponse.questionListAtDetailedViewResponse.get(3).click();
            studentResponse.questionListAtDetailedViewResponse.get(3).click();
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.questionLabelAtDetailedResponse.get(3).isDisplayed(),"Question Bubble is not working as expected");

            detailedResponse.navigateToTop.click();
            Thread.sleep(1000);

            //verify overall feedback should be disabled
            Assert.assertEquals(detailedResponse.overallFeedback.getAttribute("disabled"),"true","overall feedback is not disabled");
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(0).getAttribute("disabled"),"true","overall feedback is not disabled");
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(1).getAttribute("disabled"),"true","teacher feedback is not disabled");
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(2).getAttribute("disabled"),"true","teacher feedback is not disabled");
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(3).getAttribute("disabled"),"true","teacher feedback is not disabled");
            Assert.assertEquals(detailedResponse.teacherFeedbackOnEachQuestion.get(4).getAttribute("disabled"),"true","teacher feedback is not disabled");




            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(0).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(1).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(2).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(3).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(4).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");



            studentResponse.barGraphInDetailedResponse.get(2).click();
            Assert.assertTrue(detailedResponse.questionLabelAtDetailedResponse.get(2).isDisplayed(),"Question Bubble is not working as expected");

            detailedResponse.detailedResponseBackArrow.click();
            Thread.sleep(2000);

            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(0).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(1).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(2).getText().replaceAll("\n"," "),"MASTERY 2/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(3).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(4).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );

            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(0).getText().replaceAll("\n"," "),"PERFORMANCE 0/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(1).getText().replaceAll("\n"," "),"PERFORMANCE 0/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(2).getText().replaceAll("\n"," "),"PERFORMANCE 5/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(3).getText().replaceAll("\n"," "),"PERFORMANCE 2/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(4).getText().replaceAll("\n"," "),"PERFORMANCE 3/5","Performance is not displaying as expeccted" );


            //click on response under student card
            studentResponse.responseListAtStudentCard.get(19).click();
            Thread.sleep(3000);
            Assert.assertTrue(detailedResponse.questionLabelAtDetailedResponse.get(4).isDisplayed(),"Question Bubble is not working as expected");
            detailedResponse.detailedResponseBackArrow.click();

/*------------------------------------------------------------------------------------------------*/


            new Navigator().logout(firefoxWebdriver);
            //login with second student
            new Login().loginAsStudent(appendChar3, dataIndex, firefoxWebdriver);//login as a student1
            new Navigator().navigateTo("assignment", firefoxWebdriver);
            Thread.sleep(1000);
            //start assignment
            new Assignment().startAssignment(34, firefoxWebdriver);

            //attempt remaining true false question correctly
            for (int i=0;i<4;i++) {

                takeAssignment.trueFalse.get(0).click();
                try {
                    takeAssignment.button_next.click();
                }
                catch (Exception e)
                {
                    takeAssignment.finishButton.click();
                }
            }

            try {
                takeAssignment.button_next.click();
            }
            catch (Exception e)
            {
                takeAssignment.finishButton.click();
            }
            takeAssignment.submitButton.click();

            Thread.sleep(2000);
            studentResponse.viewDetailedResponse.get(0).click();
            studentResponse.questionListAtDetailedViewResponse.get(3).click();
            studentResponse.questionListAtDetailedViewResponse.get(3).click();
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.questionLabelAtDetailedResponse.get(3).isDisplayed(),"Question Bubble is not working as expected");

            detailedResponse.navigateToTop.click();
            Thread.sleep(1000);

            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(0).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(1).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(2).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(3).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(4).getAttribute("fill"),"#767676","color of bar is not displaying as expected");



            studentResponse.barGraphInDetailedResponse.get(2).click();
            Assert.assertTrue(detailedResponse.questionLabelAtDetailedResponse.get(2).isDisplayed(),"Question Bubble is not working as expected");

            detailedResponse.detailedResponseBackArrow.click();
            Thread.sleep(2000);

            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(0).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(1).getText().replaceAll("\n"," "),"MASTERY 2/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(2).getText().replaceAll("\n"," "),"MASTERY 2/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(3).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(4).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );

            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(0).getText().replaceAll("\n"," "),"PERFORMANCE 0/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(1).getText().replaceAll("\n"," "),"PERFORMANCE 4/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(2).getText().replaceAll("\n"," "),"PERFORMANCE 5/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(3).getText().replaceAll("\n"," "),"PERFORMANCE 2/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(4).getText().replaceAll("\n"," "),"PERFORMANCE 3/5","Performance is not displaying as expeccted" );


            //click on response under student card
            studentResponse.responseListAtStudentCard.get(19).click();
            Thread.sleep(3000);
            Assert.assertTrue(detailedResponse.questionLabelAtDetailedResponse.get(4).isDisplayed(),"Question Bubble is not working as expected");
            detailedResponse.detailedResponseBackArrow.click();


            new Navigator().logout(firefoxWebdriver);
            //login with second student
            new Login().loginAsStudent(appendChar3, dataIndex, firefoxWebdriver);//login as a student1
            new Navigator().navigateTo("assignment", firefoxWebdriver);
            Thread.sleep(1000);
            //start assignment
            new Assignment().startAssignment(34, firefoxWebdriver);

            //attempt remaining true false question correctly
            for (int i=0;i<4;i++) {

                takeAssignment.trueFalse.get(0).click();
                try {
                    takeAssignment.button_next.click();
                }
                catch (Exception e)
                {
                    takeAssignment.finishButton.click();
                }
            }

            try {
                takeAssignment.button_next.click();
            }
            catch (Exception e)
            {
                takeAssignment.finishButton.click();
            }
            takeAssignment.submitButton.click();


            Thread.sleep(2000);
            studentResponse.viewDetailedResponse.get(0).click();
            studentResponse.questionListAtDetailedViewResponse.get(3).click();
            studentResponse.questionListAtDetailedViewResponse.get(3).click();
            Thread.sleep(1000);
            Assert.assertTrue(detailedResponse.questionLabelAtDetailedResponse.get(3).isDisplayed(),"Question Bubble is not working as expected");

            detailedResponse.navigateToTop.click();
            Thread.sleep(1000);

            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(0).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(1).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(2).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(3).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(4).getAttribute("fill"),"#73B966","color of bar is not displaying as expected");



            studentResponse.barGraphInDetailedResponse.get(2).click();
            Assert.assertTrue(detailedResponse.questionLabelAtDetailedResponse.get(2).isDisplayed(),"Question Bubble is not working as expected");

            detailedResponse.detailedResponseBackArrow.click();
            Thread.sleep(2000);

            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(0).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(1).getText().replaceAll("\n"," "),"MASTERY 2/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(2).getText().replaceAll("\n"," "),"MASTERY 2/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(3).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(4).getText().replaceAll("\n"," "),"MASTERY 0/2","Mastery is not displaying as expeccted" );

            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(0).getText().replaceAll("\n"," "),"PERFORMANCE 0/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(1).getText().replaceAll("\n"," "),"PERFORMANCE 4/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(2).getText().replaceAll("\n"," "),"PERFORMANCE 5/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(3).getText().replaceAll("\n"," "),"PERFORMANCE 2/5","Performance is not displaying as expeccted" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(4).getText().replaceAll("\n"," "),"PERFORMANCE 3/5","Performance is not displaying as expeccted" );


            //click on response under student card
            studentResponse.responseListAtStudentCard.get(19).click();
            Thread.sleep(3000);
            Assert.assertTrue(detailedResponse.questionLabelAtDetailedResponse.get(4).isDisplayed(),"Question Bubble is not working as expected");
            detailedResponse.detailedResponseBackArrow.click();
            firefoxWebdriver.close();

        }
        catch (Exception e)
        {
            Assert.fail("Exception in studentCentricview in StudentCentricView",e);
        }

    }

    @Test(priority = 2,enabled = true)
    public void studentCentricViewWith2Student() {
        try {
            int dataIndex = 115;
            String appendChar= "a4";
            String appendChar1="b4";

            InstructorDashboard instructorDashboard = PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver, StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver, DetailedResponse.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//Create class
            new Navigator().logout();
            //signup with first student
            new SignUp().studentDirectRegister(appendChar, classCode, dataIndex);//Sign up as a student1
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar1, classCode, dataIndex);//Sign up as a student2
            new Navigator().logout();
            //login as instructor
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            //create five questions
            new Assignment().create(dataIndex, "truefalse");//Create a true false question
            for (int i = 1; i < 5; i++) {
                new Assignment().addQuestion(dataIndex, "truefalse");//add 10 true false question
            }
            new Assignment().assignToStudent(dataIndex, appendChar);//Assign to class
            new Navigator().navigateTo("dashboard");
            instructorDashboard.getViewLiveResult().click();
            Thread.sleep(3000);




/*------------------------------------------------------------------------------------------------*/


            //created new instance for student for parallel execution  of student as well as instructor
            WebDriver firefoxWebdriver = new FirefoxDriver();
            firefoxWebdriver.close();
            firefoxWebdriver = new Driver().startDriver(firefoxWebdriver);
            TakeAssignment takeAssignment = PageFactory.initElements(firefoxWebdriver, TakeAssignment.class);

            new Login().loginAsStudent(appendChar, dataIndex, firefoxWebdriver);//login as a student1
            new Navigator().navigateTo("assignment", firefoxWebdriver);
            Thread.sleep(1000);
            //start assignment
            new Assignment().startAssignment(34, firefoxWebdriver);

            //attempt remaining true false question correctly
            for (int i = 0; i < 5; i++) {
                if (i % 2 == 0) {
                    takeAssignment.trueFalse.get(0).click();
                } else {
                    takeAssignment.trueFalse.get(1).click();
                }
                try {
                    takeAssignment.button_next.click();
                } catch (Exception e) {
                    takeAssignment.finishButton.click();
                }
            }
            takeAssignment.submitButton.click();
            Thread.sleep(1000);
            //instructor
            //click on view detailed response
            studentResponse.viewDetailedResponse.get(0).click();
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(0).getAttribute("fill"), "#73B966", "color of bar is not displaying as expected");
            Assert.assertTrue(studentResponse.barGraphInDetailedResponse.get(1).getAttribute("fill").contains("url(#highcharts-pattern"), "color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(2).getAttribute("fill"), "#73B966", "color of bar is not displaying as expected");
            Assert.assertTrue(studentResponse.barGraphInDetailedResponse.get(3).getAttribute("fill").contains("url(#highcharts-pattern"), "color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(4).getAttribute("fill"), "#73B966", "color of bar is not displaying as expected");


            studentResponse.barGraphInDetailedResponse.get(2).click();
            Assert.assertTrue(detailedResponse.questionLabelAtDetailedResponse.get(2).isDisplayed(), "Question Bubble is not working as expected");
            //verify total score before editing
            Assert.assertEquals(detailedResponse.totalScoreNumerator.getText().trim(),"3","Numerator value dose not match");
            Assert.assertEquals(detailedResponse.totalScoreDenominator.getText().trim(),"5","Denominator value dose not match");
            detailedResponse.detailedResponseBackArrow.click();
            Thread.sleep(2000);

            Assert.assertEquals(studentResponse.masteryAtStudentCard.get(0).getText().replaceAll("\n", " "), "MASTERY 0/2", "Mastery is not displaying as expeccted");
            Assert.assertEquals(studentResponse.masteryAtStudentCard.get(1).getText().replaceAll("\n", " "), "MASTERY 0/2", "Mastery is not displaying as expeccted");

            Assert.assertEquals(studentResponse.performanceAtStudentCard.get(0).getText().replaceAll("\n", " "), "PERFORMANCE 0/5", "Performance is not displaying as expeccted");
            Assert.assertEquals(studentResponse.performanceAtStudentCard.get(1).getText().replaceAll("\n", " "), "PERFORMANCE 3/5", "Performance is not displaying as expeccted");
            studentResponse.viewDetailedResponse.get(0).click();
            detailedResponse.scorePerQuestion.get(0).clear();
            detailedResponse.scorePerQuestion.get(0).sendKeys("0");
            detailedResponse.navigateToTop.click();
            Thread.sleep(1000);
            Assert.assertEquals(detailedResponse.totalScoreNumerator.getText().trim(),"3","Numerator value dose not match");
            Assert.assertEquals(detailedResponse.totalScoreDenominator.getText().trim(),"5","Denominator value dose not match");

            new Navigator().logout(firefoxWebdriver);
            //login with second student
            new Login().loginAsStudent(appendChar1, dataIndex, firefoxWebdriver);//login as a student2
            new Navigator().navigateTo("assignment", firefoxWebdriver);
            Thread.sleep(1000);
            //start assignment
            new Assignment().startAssignment(34, firefoxWebdriver);

            //attempt remaining true false question correctly
            for (int i = 0; i < 5; i++) {

                    takeAssignment.trueFalse.get(0).click();

                try {
                    takeAssignment.button_next.click();
                } catch (Exception e) {
                    takeAssignment.finishButton.click();
                }
            }

            takeAssignment.submitButton.click();

            //Instructor
            detailedResponse.detailedResponseBackArrow.click();
            Thread.sleep(2000);

            studentResponse.viewDetailedResponse.get(0).click();


            detailedResponse.overallFeedback.clear();
            detailedResponse.overallFeedback.sendKeys("Attempted");
            Thread.sleep(1000);

            detailedResponse.teacherFeedbackOnEachQuestion.get(0).clear();
            detailedResponse.teacherFeedbackOnEachQuestion.get(0).sendKeys("1 Attempted");
            Thread.sleep(1000);

            detailedResponse.teacherFeedbackOnEachQuestion.get(1).clear();
            detailedResponse.teacherFeedbackOnEachQuestion.get(1).sendKeys("2 Attempted");
            Thread.sleep(1000);


           detailedResponse.teacherFeedbackOnEachQuestion.get(2).clear();
            detailedResponse.teacherFeedbackOnEachQuestion.get(2).sendKeys("3 Attempted");
            Thread.sleep(1000);




            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(0).getAttribute("fill"), "#73B966", "color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(1).getAttribute("fill"), "#73B966", "color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(2).getAttribute("fill"), "#73B966", "color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(3).getAttribute("fill"), "#73B966", "color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(4).getAttribute("fill"), "#73B966", "color of bar is not displaying as expected");



            //change student from student list dropdown
            detailedResponse.studentListDropDown.click();
            detailedResponse.studentNameListDropDown.get(1).click();

            Assert.assertTrue(studentResponse.barGraphInDetailedResponse.get(0).getAttribute("fill").contains("#73B966"), "color of bar is not displaying as expected");
            Assert.assertTrue(studentResponse.barGraphInDetailedResponse.get(1).getAttribute("fill").contains("url(#highcharts-pattern"), "color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(2).getAttribute("fill"), "#73B966", "color of bar is not displaying as expected");
            Assert.assertTrue(studentResponse.barGraphInDetailedResponse.get(3).getAttribute("fill").contains("url(#highcharts-pattern"), "color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(4).getAttribute("fill"), "#73B966", "color of bar is not displaying as expected");


            detailedResponse.detailedResponseBackArrow.click();
            Thread.sleep(2000);

            Assert.assertEquals(studentResponse.masteryAtStudentCard.get(0).getText().replaceAll("\n", " "), "MASTERY 2/2", "Mastery is not displaying as expeccted");
            Assert.assertEquals(studentResponse.masteryAtStudentCard.get(1).getText().replaceAll("\n", " "), "MASTERY 0/2", "Mastery is not displaying as expeccted");

            Assert.assertEquals(studentResponse.performanceAtStudentCard.get(0).getText().replaceAll("\n", " "), "PERFORMANCE 5/5", "Performance is not displaying as expeccted");
            Assert.assertEquals(studentResponse.performanceAtStudentCard.get(1).getText().replaceAll("\n", " "), "PERFORMANCE 3/5", "Performance is not displaying as expeccted");

            studentResponse.releaseGrade.click();
            AssignmentSummary assignmentSummary=PageFactory.initElements(firefoxWebdriver,AssignmentSummary.class);
            assignmentSummary.barOnAssignmentSummary.get(0).click();
            Assert.assertEquals(assignmentSummary.teacherFeedbackStudentSide.getText(),"1 Attempted","Feedback Not updated properly");
            assignmentSummary.nextArrowInPerformance.click();
            Thread.sleep(2000);

            Assert.assertEquals(assignmentSummary.teacherFeedbackStudentSide.getText(),"2 Attempted","Feedback Not updated properly");
            assignmentSummary.nextArrowInPerformance.click();
            Thread.sleep(2000);
            Assert.assertEquals(assignmentSummary.teacherFeedbackStudentSide.getText(),"3 Attempted","Feedback Not updated properly");
            assignmentSummary.nextArrowInPerformance.click();
            Thread.sleep(2000);
            Assert.assertEquals(assignmentSummary.teacherFeedbackStudentSide.getText(),"No feedback provided by teacher.","Feedback Not updated properly");
            firefoxWebdriver.close();



        } catch (Exception e) {
            Assert.fail("Exception in StudentCentricView in method studentCentricViewWith2Student",e);
        }
    }


    @Test(priority = 3,enabled = true)
    public void viewResponseWithMultipleChoiceEssayType() {
        try {
            int dataIndex = 147;
            String appendChar= "a";
            String appendChar1="b";

            InstructorDashboard instructorDashboard = PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver, StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver, DetailedResponse.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//Create class
            new Navigator().logout();
            //signup with first student
            new SignUp().studentDirectRegister(appendChar, classCode, dataIndex);//Sign up as a student1
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar1, classCode, dataIndex);//Sign up as a student2
            new Navigator().logout();
            //login as instructor
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            //create five questions
            new Assignment().create(dataIndex, "multipleselection");//Create a true false question
            new Assignment().addQuestion(dataIndex, "essay");//add essay type question

            new Assignment().assignToStudent(dataIndex, appendChar);//Assign to class
            new Navigator().navigateTo("dashboard");
            instructorDashboard.getViewLiveResult().click();
            Thread.sleep(3000);




/*------------------------------------------------------------------------------------------------*/


            //created new instance for student for parallel execution  of student as well as instructor
            WebDriver firefoxWebdriver = new FirefoxDriver();
            firefoxWebdriver.close();
            firefoxWebdriver = new Driver().startDriver(firefoxWebdriver);
            TakeAssignment takeAssignment = PageFactory.initElements(firefoxWebdriver, TakeAssignment.class);


            new Login().loginAsStudent(appendChar, dataIndex, firefoxWebdriver);//login as a student1
            new Navigator().navigateTo("assignment", firefoxWebdriver);
            Thread.sleep(1000);
            //start assignment
            new Assignment().startAssignment(34, firefoxWebdriver);

            takeAssignment.multipleSelectionAnswerList.get(0).click();
            takeAssignment.button_next.click();
            String randomString=new RandomString().randomstring(5);

            takeAssignment.answerEssayText.sendKeys(randomString);
            takeAssignment.button_next.click();
            takeAssignment.submitButton.click();

            Thread.sleep(8000);


            new Navigator().logout(firefoxWebdriver);
            //login with second student
            new Login().loginAsStudent(appendChar1, dataIndex, firefoxWebdriver);//login as a student2
            new Navigator().navigateTo("assignment", firefoxWebdriver);
            Thread.sleep(1000);
            //start assignment
            new Assignment().startAssignment(34, firefoxWebdriver);

            takeAssignment.multipleSelectionAnswerList.get(0).click();
            takeAssignment.multipleSelectionAnswerList.get(1).click();
            takeAssignment.button_next.click();
            takeAssignment.answerEssayText.clear();

            randomString=new RandomString().randomstring(5);

            takeAssignment.answerEssayText.sendKeys(randomString);
            takeAssignment.button_next.click();
            takeAssignment.submitButton.click();


            Thread.sleep(8000);
           //Instructor
            studentResponse.viewDetailedResponse.get(0).click();
            detailedResponse.overallFeedback.clear();
            detailedResponse.overallFeedback.sendKeys("Attempted");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(0).getAttribute("fill"), "#73B966", "color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(1).getAttribute("fill"), "#3896BE", "color of bar is not displaying as expected");


            detailedResponse.scorePerQuestion.get(1).clear();
            detailedResponse.scorePerQuestion.get(1).sendKeys(".2");

            detailedResponse.detailedResponseBackArrow.click();
            Thread.sleep(2000);
            Assert.assertEquals(studentResponse.masteryAtStudentCard.get(0).getText().replaceAll("\n", " "), "MASTERY 0/2", "Mastery is not displaying as expeccted");
            Assert.assertEquals(studentResponse.masteryAtStudentCard.get(1).getText().replaceAll("\n", " "), "MASTERY 0/2", "Mastery is not displaying as expeccted");

            Assert.assertEquals(studentResponse.performanceAtStudentCard.get(0).getText().replaceAll("\n", " "), "PERFORMANCE 1/2", "Mastery is not displaying as expeccted");
            Assert.assertEquals(studentResponse.performanceAtStudentCard.get(1).getText().replaceAll("\n", " "), "PERFORMANCE 0.5/2", "Mastery is not displaying as expeccted");




            studentResponse.viewDetailedResponse.get(1).click();
            Assert.assertTrue(studentResponse.barGraphInDetailedResponse.get(0).getAttribute("fill").contains("url(#highcharts-pattern"), "color of bar is not displaying as expected");
            Assert.assertEquals(studentResponse.barGraphInDetailedResponse.get(1).getAttribute("fill"), "#3896BE", "color of bar is not displaying as expected");


            detailedResponse.scorePerQuestion.get(1).clear();
            detailedResponse.scorePerQuestion.get(1).sendKeys("1");

            detailedResponse.detailedResponseBackArrow.click();
            Thread.sleep(2000);
            Assert.assertEquals(studentResponse.masteryAtStudentCard.get(0).getText().replaceAll("\n", " "), "MASTERY 0/2", "Mastery is not displaying as expeccted");
            Assert.assertEquals(studentResponse.masteryAtStudentCard.get(1).getText().replaceAll("\n", " "), "MASTERY 0/2", "Mastery is not displaying as expeccted");

            Assert.assertEquals(studentResponse.performanceAtStudentCard.get(0).getText().replaceAll("\n", " "), "PERFORMANCE 1/2", "Mastery is not displaying as expeccted");
            Assert.assertEquals(studentResponse.performanceAtStudentCard.get(1).getText().replaceAll("\n", " "), "PERFORMANCE 0.5/2", "Mastery is not displaying as expeccted");


            //verify release feedback,Print,Reassign,Download Grade
            Assert.assertTrue(studentResponse.releaseGrade.isDisplayed(),"release is not present on screen");
            Assert.assertTrue(studentResponse.printResponse.isDisplayed(),"Print is not present on screen");
            Assert.assertTrue(studentResponse.button_redirect.isDisplayed(),"Reassign assignment is not present on screen");
            Assert.assertTrue(studentResponse.downloadGrade.isDisplayed(),"Download grade is not present on screen");
            firefoxWebdriver.close();
        } catch (Exception e) {
            Assert.fail("Exception in StudentCentricView in method studentCentricViewWith2Student",e);
        }
    }
    }



