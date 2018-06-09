package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.livegradebook;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.TakeAssignment;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pragya on 12-08-2015.
 */
public class LiveGradebookViewMastery extends Driver{

    @Test(priority = 1,enabled = true)
    public void viewLiveResult(){
        try{
            int dataIndex = 16;
            String appendChar = "a";

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            //signup with a student
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out

            //login as instructor
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            //create a questions
            new Assignment().create(dataIndex, "truefalse");//Create a true false question
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            instructorDashboard.viewResponseonDashboard().click();//Click on view response

            //Expected - Teacher should be able to view the Mastery of the class over the standards used in that particular assignment.
            Assert.assertEquals(studentResponse.mastery.isDisplayed(),true,"Mastery is not displayed");

            //Expected - This is represented using horizontal bar graph.
            //Expected - Maximum 3 values should be displayed for Y axis.
            Assert.assertEquals(studentResponse.labelsInChart.get(2).getText(),"1","Value is not displayed as expected");
            Assert.assertEquals(studentResponse.labelsInChart.get(3).getText(),"1.OA.A.1","Value is not displayed as expected");
            Assert.assertEquals(studentResponse.labelsInChart.get(4).getText(),"1.OA.A.2","Value is not displayed as expected");
            Assert.assertEquals(studentResponse.labelsInChart.get(5).getText(),"1.OA.B.3","Value is not displayed as expected");
            Assert.assertEquals(studentResponse.labelsInChart.get(6).getText(),"0","Value is not displayed as expected");

            //Expected - If more than 3 standards used in the assignment then arrow link to naviagate the user to other questions should be displayed.  Flow navigation should be tested.
            studentResponse.scroll_bottomMastery.click();//Click on scroll

            Assert.assertEquals(studentResponse.labelsInChart.get(2).getText(),"1","Value is not displayed as expected");
            Assert.assertEquals(studentResponse.labelsInChart.get(3).getText(),"1.OA.A.2","Value is not displayed as expected");
            Assert.assertEquals(studentResponse.labelsInChart.get(4).getText(),"1.OA.B.3","Value is not displayed as expected");
            Assert.assertEquals(studentResponse.labelsInChart.get(5).getText(),"1.OA.B.4","Value is not displayed as expected");
            Assert.assertEquals(studentResponse.labelsInChart.get(6).getText(),"0","Value is not displayed as expected");

            //Expected - X axis should represent the mastery of the particular standard in percentage. Hence the value range is between 0 to 100.
            Assert.assertEquals(studentResponse.labelsInChart.get(6).getText(),"0","Value range is not displayed as expected");
            Assert.assertEquals(studentResponse.labelsInChart.get(16).getText(),"100","Value range is not displayed as expected");


        }   catch (Exception e){
            Assert.fail("Exception in viewLiveResult method in class LiveGradebookViewMastery", e);

        }
    }


    @Test(priority = 2,enabled = true)
    public void masteryView(){
        try{
            int dataIndex = 77;
            String appendChar = "a";
            String appendChar1 = "b";
            String appendChar2 = "c";

            InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            //signup with student1
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out
            //signup with student2
            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//Log out
            //signup with student3
            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student3
            new Navigator().logout();//Log out

            //login as instructor
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            //create an assignment with 5 questions(with 3 elos)
            new Assignment().create(dataIndex, "truefalse");//Create a true false question
            for(int i =0; i<4; i++){
                new Assignment().addQuestion(dataIndex,"truefalse");
            }
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            instructorDashboard.viewResponseonDashboard().click();//Click on view response

            //Expected - Performance bar graph should have 3 Y values.
            if(studentResponse.bars_mastery.size()!=3){
                Assert.fail("Bar graph does not have 3Y values");
            }

            //Expected - X axis percentage should be 0
            Assert.assertEquals(studentResponse.labelsInChart.get(11).getText(),"0","Value is not displayed as expected");
            Assert.assertEquals(studentResponse.mastery.getText(),"Mastery : 0%","Value is not displayed as expected");

            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//click on view response

            //Expected - Performance bar graph should have 3 Y values.
            if(studentResponse.bars_mastery.size()!=3){
                Assert.fail("Bar graph does not have 3Y values");
            }

            //Expected - X axis percentage should be 0
            Assert.assertEquals(studentResponse.labelsInChart.get(11).getText(),"0","Value is not displayed as expected");
            Assert.assertEquals(studentResponse.mastery.getText(),"Mastery : 0%","Value is not displayed as expected");

            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            instructorDashboard.getViewLiveResult().click();//Click on view live result

            //Expected - Performance bar graph should have 3 Y values.
            if(studentResponse.bars_mastery.size()!=3){
                Assert.fail("Bar graph does not have 3Y values");
            }

            //Expected - X axis percentage should be 0
            Assert.assertEquals(studentResponse.labelsInChart.get(11).getText(),"0","Value is not displayed as expected");
            Assert.assertEquals(studentResponse.mastery.getText(),"Mastery : 0%","Value is not displayed as expected");

            String bar1ColorBeforeAttempt = studentResponse.barsMastery_greyColor.get(0).getAttribute("height");
            String bar2ColorBeforeAttempt = studentResponse.barsMastery_greyColor.get(1).getAttribute("height");
            String bar3ColorBeforeAttempt = studentResponse.barsMastery_greyColor.get(2).getAttribute("height");

            new Navigator().logout();//log out

            //created new instance for student for parallel execution  of student as well as instructor
            WebDriver firefoxWebdriver=new FirefoxDriver();

            firefoxWebdriver.close();
            firefoxWebdriver=new Driver().startDriver(firefoxWebdriver);
            TakeAssignment takeAssignment =PageFactory.initElements(firefoxWebdriver,TakeAssignment.class);

            new Login().loginAsStudent(appendChar,dataIndex,firefoxWebdriver);//login as a student1
            new Navigator().navigateTo("assignment",firefoxWebdriver);
            Thread.sleep(1000);
            //start assignment
            new Assignment().startAssignment(77,firefoxWebdriver);
            takeAssignment.trueFalse.get(0).click();//Attempt 1st question
            takeAssignment.button_next.click();//Click on next button
            takeAssignment.trueFalse.get(0).click();//Attempt 2nd question
            takeAssignment.button_next.click();//Click on next button
            takeAssignment.button_next.click();//Click on next button(skip question 3)
            Thread.sleep(5000);

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            instructorDashboard.getViewLiveResult().click();//Click on view live result
            //verify on instructor view live response
            //Expected - The results should be updated in real time.
           if(studentResponse.barsMastery_greyColor.get(0).getAttribute("height").equals(bar1ColorBeforeAttempt)){
                Assert.fail("Result is not updated");

            }

            if(studentResponse.barsMastery_greyColor.get(1).getAttribute("height").equals(bar2ColorBeforeAttempt)){
                Assert.fail("Result is not updated");

            }

            if(studentResponse.barsMastery_greyColor.get(2).getAttribute("height").equals(bar3ColorBeforeAttempt)){
                Assert.fail("Result is not updated");

            }

            Assert.assertEquals(studentResponse.mastery.getText(),"Mastery : 67%","Result is not updated as expected");

            //Check student performance card
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(0).getText().replaceAll("\n"," "),"PERFORMANCE 0/5","Performance is not displaying as expected for student3" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(1).getText().replaceAll("\n"," "),"PERFORMANCE 0/5","Performance is not displaying as expected student2" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(2).getText().replaceAll("\n"," "),"PERFORMANCE 2/5","Performance is not displaying as expected student1" );

            //Check student mastery card
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(0).getText().replaceAll("\n"," "),"MASTERY 0/3","Mastery is not displaying as expected for student1" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(1).getText().replaceAll("\n"," "),"MASTERY 0/3","Mastery is not displaying as expected student2" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(2).getText().replaceAll("\n"," "),"MASTERY 0/3","Mastery is not displaying as expected student3" );

            //Response list of student1
            Assert.assertEquals(studentResponse.responseListAtStudentCard.get(10).getAttribute("class").trim(),"response-unit correct-response","Response is not displayed as expected");
            Assert.assertEquals(studentResponse.responseListAtStudentCard.get(11).getAttribute("class").trim(),"response-unit correct-response","Response is not displayed as expected");
            Assert.assertEquals(studentResponse.responseListAtStudentCard.get(12).getAttribute("class").trim(),"response-unit intent-skipped-answer","Response is ot displayed as expected");
            Assert.assertEquals(studentResponse.responseListAtStudentCard.get(13).getAttribute("class").trim(),"response-unit unattempted-answer disabled","Response is ot displayed as expected");
            Assert.assertEquals(studentResponse.responseListAtStudentCard.get(14).getAttribute("class").trim(),"response-unit unattempted-answer disabled","Response is ot displayed as expected");

            Assert.assertEquals(studentResponse.mastery.getText(),"Mastery : 67%","Result is not updated as expected");


            //attempt remaining true false question correctly
           for (int i=0;i<2;i++) {
                takeAssignment.trueFalse.get(0).click();
                try {
                    takeAssignment.button_next.click();
                }
                catch (Exception e)
                {
                    takeAssignment.finishButton.click();
                }
            }

            Thread.sleep(5000);

            //Check student performance card
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(0).getText().replaceAll("\n"," "),"PERFORMANCE 0/5","Performance is not displaying as expected for student3" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(1).getText().replaceAll("\n"," "),"PERFORMANCE 0/5","Performance is not displaying as expected student2" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(2).getText().replaceAll("\n"," "),"PERFORMANCE 4/5","Performance is not displaying as expected student1" );

            //Check student mastery card
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(0).getText().replaceAll("\n"," "),"MASTERY 0/3","Mastery is not displaying as expected for student3" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(1).getText().replaceAll("\n"," "),"MASTERY 0/3","Mastery is not displaying as expected student2" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(2).getText().replaceAll("\n"," "),"MASTERY 3/3","Mastery is not displaying as expected student1" );

            //Response list of student3
            for(int i=0;i<5;i++){
                if(!studentResponse.responseListAtStudentCard.get(i).getAttribute("class").equals("response-unit unattempted-answer disabled")){
                    Assert.fail("Response list is not displayed for student1 as expected");
                }
            }

            //Response list of student2
            for(int i=5;i<10;i++){
                if(!studentResponse.responseListAtStudentCard.get(i).getAttribute("class").equals("response-unit unattempted-answer disabled")){
                    Assert.fail("Response list is not displayed for student2 as expected");
                }
            }

            //verify checkbox in student card
            Assert.assertEquals(studentResponse.checkboxInStudentCard.size(),4,"Number of checkbox are not equal to number of students");

            //Expected - Every student response should display as a card.(number of card is verified)
            Assert.assertEquals(studentResponse.studentResponseCard.size(),Integer.parseInt("3"),"3 cards are not displayed as expected");

            //logout first student
            new Navigator().logout(firefoxWebdriver);

            new Login().loginAsStudent(appendChar1,dataIndex,firefoxWebdriver);//login as a student2
            new Navigator().navigateTo("assignment",firefoxWebdriver);
            Thread.sleep(1000);
            //start assignment
            new Assignment().startAssignment(77,firefoxWebdriver);
            takeAssignment.trueFalse.get(0).click();//Attempt 1st question
            takeAssignment.button_next.click();//Click on next button
            takeAssignment.trueFalse.get(0).click();//Attempt 2nd question
            takeAssignment.button_next.click();//Click on next button
            Thread.sleep(5000);

            Assert.assertEquals(studentResponse.mastery.getText(),"Mastery : 90%","Result is not updated as expected");

            //Check student performance card
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(0).getText().replaceAll("\n"," "),"PERFORMANCE 0/5","Performance is not displaying as expected for student3" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(1).getText().replaceAll("\n"," "),"PERFORMANCE 2/5","Performance is not displaying as expected student2" );
            Assert.assertEquals( studentResponse.performanceAtStudentCard.get(2).getText().replaceAll("\n"," "),"PERFORMANCE 4/5","Performance is not displaying as expected student1" );

            //Check student mastery card
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(0).getText().replaceAll("\n"," "),"MASTERY 0/3","Mastery is not displaying as expected for student3" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(1).getText().replaceAll("\n"," "),"MASTERY 3/3","Mastery is not displaying as expected student2" );
            Assert.assertEquals( studentResponse.masteryAtStudentCard.get(2).getText().replaceAll("\n"," "),"MASTERY 3/3","Mastery is not displaying as expected student1" );

            //Response list of student3
            for(int i=0;i<5;i++){
                if(!studentResponse.responseListAtStudentCard.get(i).getAttribute("class").equals("response-unit unattempted-answer disabled")){
                    Assert.fail("Response list is not displayed for student1 as expected");
                }
            }

            //Response list of student2
            Assert.assertEquals(studentResponse.responseListAtStudentCard.get(5).getAttribute("class").trim(),"response-unit correct-response","Response is not displayed as expected");
            Assert.assertEquals(studentResponse.responseListAtStudentCard.get(6).getAttribute("class").trim(),"response-unit correct-response","Response is not displayed as expected");
            for(int i=7;i<10;i++){
                if(!studentResponse.responseListAtStudentCard.get(i).getAttribute("class").trim().equals("response-unit unattempted-answer disabled")){
                    Assert.fail("Response list is not displayed for student2 as expected");
                }
            }

            //Response list of student1
            Assert.assertEquals(studentResponse.responseListAtStudentCard.get(10).getAttribute("class").trim(),"response-unit correct-response","Response is not displayed as expected");
            Assert.assertEquals(studentResponse.responseListAtStudentCard.get(11).getAttribute("class").trim(),"response-unit correct-response","Response is not displayed as expected");
            Assert.assertEquals(studentResponse.responseListAtStudentCard.get(12).getAttribute("class").trim(),"response-unit intent-skipped-answer","Response is ot displayed as expected");
            Assert.assertEquals(studentResponse.responseListAtStudentCard.get(13).getAttribute("class").trim(),"response-unit correct-response","Response is ot displayed as expected");
            Assert.assertEquals(studentResponse.responseListAtStudentCard.get(14).getAttribute("class").trim(),"response-unit correct-response","Response is ot displayed as expected");

            firefoxWebdriver.close();
        }catch (Exception e){
            Assert.fail("Exception in masteryView method in class LiveGradebookViewMastery", e);

        }
    }
}
