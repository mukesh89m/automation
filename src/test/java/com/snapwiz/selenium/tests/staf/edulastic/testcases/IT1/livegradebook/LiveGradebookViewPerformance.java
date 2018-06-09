package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.livegradebook;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.TakeAssignment;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 10-08-2015.
 */
public class LiveGradebookViewPerformance extends Driver{
   @Test(priority = 1,enabled = true)
   public void  liveGradebookView()
   {
       try
       {
           int dataIndex = 1;
           String appendChar = "a4";
           InstructorDashboard instructorDashboard= PageFactory.initElements(driver,InstructorDashboard.class);
           String assessmentName= ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
           StudentResponse studentResponse= PageFactory.initElements(driver,StudentResponse.class);
           new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
           new School().createWithOnlyName(appendChar,dataIndex);//Create school
           String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
           new Navigator().logout();

               new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1
           new Navigator().logout();//Log out
           new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher


           new Assignment().create(dataIndex, "truefalse");//Create a true false question
           new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class
           Thread.sleep(2000);
           //navigate to live result using live result link
           new Navigator().navigateTo("dashboard");
           instructorDashboard.getViewLiveResult().click();
           Thread.sleep(3000);
           Assert.assertTrue(studentResponse.performanceChart.isDisplayed(),"Performance chart not displaying");
           Assert.assertTrue(studentResponse.masteryChart.isDisplayed(),"Mastery chart not displaying");
           Assert.assertTrue(studentResponse.studentResponseCard.get(0).isDisplayed(),"Student card not displaying");
           Assert.assertTrue(studentResponse.assessmentName.getText().contains(assessmentName), "Not displaying assessment name");
           Assert.assertTrue(studentResponse.backButton.isDisplayed(),"Back button not displaying");
           Assert.assertTrue(studentResponse.assignmentPolicy.getText().contains("Non-Gradable"), "Not displaying assignment policy");

           //navigate to live result using view response link
           new Navigator().navigateTo("dashboard");
           instructorDashboard.viewResponseonDashboard().click();
           Thread.sleep(3000);
           Assert.assertTrue(studentResponse.performanceChart.isDisplayed(),"Performance chart not displaying");
           Assert.assertTrue(studentResponse.masteryChart.isDisplayed(),"Mastery chart not displaying");
           Assert.assertTrue(studentResponse.studentResponseCard.get(0).isDisplayed(),"Student card not displaying");
           Assert.assertTrue(studentResponse.assessmentName.getText().contains(assessmentName), "Not displaying assessment name");
           Assert.assertTrue(studentResponse.backButton.isDisplayed(),"Back button not displaying");
           Assert.assertTrue(studentResponse.assignmentPolicy.getText().contains("Non-Gradable"), "Not displaying assignment policy");

           //navigate to live result using view response link on assignment
           new Navigator().navigateTo("assignment");
           instructorDashboard.getButton_ViewResponses().click();
           Thread.sleep(3000);
           Assert.assertTrue(studentResponse.performanceChart.isDisplayed(),"Performance chart not displaying");
           Assert.assertTrue(studentResponse.masteryChart.isDisplayed(),"Mastery chart not displaying");
           Assert.assertTrue(studentResponse.studentResponseCard.get(0).isDisplayed(),"Student card not displaying");
           Assert.assertTrue(studentResponse.assessmentName.getText().contains(assessmentName), "Not displaying assessment name");
           Assert.assertTrue(studentResponse.backButton.isDisplayed(),"Back button not displaying");
           Assert.assertTrue(studentResponse.assignmentPolicy.getText().contains("Non-Gradable"), "Not displaying assignment policy");









       }
       catch (Exception e)
       {
           Assert.fail("Exception in liveGradebookView method in class LiveGradebookView",e);
       }
   }


    @Test(priority = 2,enabled = true)
    public void  liveGradebookSummaryViewThreeStudent()
    {
        try
        {
            int dataIndex = 34;
            String appendChar = "a10";
            String appendChar1 ="b10";
            String appendChar2 ="c10";
            InstructorDashboard instructorDashboard=PageFactory.initElements(driver,InstructorDashboard.class);
            StudentResponse studentResponse=PageFactory.initElements(driver,StudentResponse.class);
           new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();
            //signup with first student
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out
            //signup with second student
            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out
            //signup with third student
            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out
            //login as instructor
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher

            //create five questions
            new Assignment().create(dataIndex, "truefalse");//Create a true false question
            for(int i=1;i<5;i++) {
                new Assignment().addQuestion(dataIndex, "truefalse");//add 10 true false question
            }

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class
            Thread.sleep(2000);
            //navigate to dashboard
            new Navigator().navigateTo("dashboard");
            instructorDashboard.getViewLiveResult().click();
            Thread.sleep(3000);
            //verify labels in performance chart
            Assert.assertEquals(studentResponse.labelsInChart.get(0).getText(), "Q1", "Question label is not displaying");
            Assert.assertEquals(studentResponse.labelsInChart.get(1).getText(),"Q2","Question label is not displaying");
            Assert.assertEquals(studentResponse.labelsInChart.get(2).getText(),"Q3","Question label is not displaying");
            Assert.assertEquals(studentResponse.labelsInChart.get(3).getText(),"Q4","Question label is not displaying");
            Assert.assertEquals(studentResponse.labelsInChart.get(4).getText(),"Q5","Question label is not displaying");
            Assert.assertEquals(studentResponse.labelsInChart.get(5).getText(),"0","Question label is not displaying");
            Assert.assertEquals(studentResponse.labelsInChart.get(6).getText(),"2","Question label is not displaying");
            Assert.assertEquals(studentResponse.labelsInChart.get(7).getText(),"4","Question label is not displaying");




            //created new instance for student for parallel execution  of student as well as instructor
            WebDriver firefoxWebdriver=new FirefoxDriver();

            firefoxWebdriver.close();
            firefoxWebdriver=new Driver().startDriver(firefoxWebdriver);
            TakeAssignment takeAssignment =PageFactory.initElements(firefoxWebdriver,TakeAssignment.class);

           new Login().loginAsStudent(appendChar,dataIndex,firefoxWebdriver);//login as a student1
            new Navigator().navigateTo("assignment",firefoxWebdriver);
            Thread.sleep(1000);
            //start assignment
            new Assignment().startAssignment(34,firefoxWebdriver);
            takeAssignment.trueFalse.get(0).click();
            takeAssignment.navigateToQuestion.get(1).click();
            Thread.sleep(8000);
            //verify on instructor view live response
            //number of bars under performance should be 1
            Assert.assertEquals(studentResponse.barUnderPerformance.size(),1,"Number of bars under performance chart should be one");
            Assert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(),"Performance: 100 %","Not displaying percentage properly");

            //attempt remaining true false question correctly
            for (int i=1;i<5;i++) {
                takeAssignment.trueFalse.get(0).click();
                try {
                    takeAssignment.button_next.click();
                }
                catch (Exception e)
                {
                    takeAssignment.finishButton.click();
                }
            }

            takeAssignment.submitButton.click();
            Thread.sleep(5000);
            Assert.assertEquals(studentResponse.barUnderPerformance.size(),5,"Number of bars under performance chart should be one");
            Assert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(),"Performance: 100 %","Not displaying percentage properly");
            //logout first student
            new Navigator().logout(firefoxWebdriver);
            //login using second user
            new Login().loginAsStudent(appendChar1,dataIndex,firefoxWebdriver);//login as a student1
            new Navigator().navigateTo("assignment",firefoxWebdriver);
            Thread.sleep(1000);
            //start assignment
            new Assignment().startAssignment(34,firefoxWebdriver);
            takeAssignment.trueFalse.get(1).click();//attempt false
            takeAssignment.button_next.click();
            Thread.sleep(3000);

            Assert.assertEquals(studentResponse.barUnderPerformance.size(),5,"Number of bars under performance chart should be one");
            Assert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(),"Performance: 83 %","Not displaying percentage properly");

            for (int i=1;i<5;i++) {
                takeAssignment.trueFalse.get(0).click();
                try {
                    takeAssignment.button_next.click();
                }
                catch (Exception e)
                {
                    takeAssignment.finishButton.click();
                }
            }

            takeAssignment.submitButton.click();
            Thread.sleep(5000);

            Assert.assertEquals(studentResponse.barUnderPerformance.size(),1,"Number of bars under performance chart should be one");
            Assert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(),"Performance: 90 %","Not displaying percentage properly");
            Assert.assertEquals(studentResponse.barForWrongUnderPerformance.size(),5,"Number of bars under performance chart should be one");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformance.size(),4,"Number of bars under performance chart should be one");
            firefoxWebdriver.close();
         }
        catch (Exception e)
        {
            Assert.fail("Exception in liveGradebookView method in class LiveGradebookView",e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void  liveGradebookSummaryViewThreeStudentAllCorrect()
    {
        try
        {
            int dataIndex = 34;
            String appendChar = "a17";
            String appendChar1 ="b17";
            String appendChar2 ="c017";

            InstructorDashboard instructorDashboard=PageFactory.initElements(driver,InstructorDashboard.class);
            StudentResponse studentResponse=PageFactory.initElements(driver,StudentResponse.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();
            //signup with first student
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out
            //signup with second student
            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//Log out
            //signup with third student
            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student3
            new Navigator().logout();//Log out
            //login as instructor
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher

            //create five questions
            new Assignment().create(dataIndex, "truefalse");//Create a true false question
            for(int i=1;i<5;i++) {
                new Assignment().addQuestion(dataIndex, "truefalse");//add 5 true false question
            }

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//click on view response            Thread.sleep(2000);

            //created new instance for student for parallel execution  of student as well as instructor
            WebDriver firefoxWebdriver=new FirefoxDriver();

            firefoxWebdriver.close();
            firefoxWebdriver=new Driver().startDriver(firefoxWebdriver);
            TakeAssignment takeAssignment =PageFactory.initElements(firefoxWebdriver,TakeAssignment.class);

            new Login().loginAsStudent(appendChar,dataIndex,firefoxWebdriver);//login as a student1
            new Navigator().navigateTo("assignment",firefoxWebdriver);
            Thread.sleep(1000);
            //start assignment
            new Assignment().startAssignment(34,firefoxWebdriver);

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

            takeAssignment.submitButton.click();
            Thread.sleep(5000);
            //logout first student
            new Navigator().logout(firefoxWebdriver);
            //login using second user
            new Login().loginAsStudent(appendChar1,dataIndex,firefoxWebdriver);//login as a student1
            new Navigator().navigateTo("assignment",firefoxWebdriver);
            Thread.sleep(1000);
            //start assignment
            new Assignment().startAssignment(34,firefoxWebdriver);
            //attempt first question correctly
            takeAssignment.trueFalse.get(0).click();
            takeAssignment.button_next.click();
            Thread.sleep(3000);

            Assert.assertEquals(studentResponse.barUnderPerformance.size(),4,"Number of bars under performance chart should be one");
            Assert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(),"Performance: 100 %","Not displaying percentage properly");

             //attempt second question correctly
            takeAssignment.trueFalse.get(0).click();
            takeAssignment.button_next.click();
            Thread.sleep(3000);

            Assert.assertEquals(studentResponse.barUnderPerformance.size(),4,"Number of bars under performance chart should be one");
            Assert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(),"Performance: 100 %","Not displaying percentage properly");
            //click on second questionusing bubble
            takeAssignment.navigateToQuestion.get(1).click();
            //select false option
            takeAssignment.trueFalse.get(1).click();
            takeAssignment.button_next.click();
            Thread.sleep(3000);
            Assert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(),"Performance: 75 %","Not displaying percentage properly");
            firefoxWebdriver.close();



        }
        catch (Exception e)
        {
            Assert.fail("Exception in liveGradebookView method in class LiveGradebookView",e);
        }
    }


    @Test(priority = 4,enabled = true)
    public void  liveGradebookSummaryViewMoreThan10Question()
    {
        try
        {
            int dataIndex = 44;
            String appendChar = "a11";
            InstructorDashboard instructorDashboard=PageFactory.initElements(driver,InstructorDashboard.class);
            StudentResponse studentResponse=PageFactory.initElements(driver,StudentResponse.class);
            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();
            //signup with first student
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();
            //login as instructor
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher

            //create five questions
            new Assignment().create(dataIndex, "truefalse");//Create a true false question
            for(int i=1;i<12;i++) {
                new Assignment().addQuestion(dataIndex, "truefalse");//add 10 true false question
            }

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");
            instructorDashboard.getViewLiveResult().click();
            Thread.sleep(3000);
            Assert.assertEquals(studentResponse.labelsInChart.get(0).getText(),"Q1", "Question label is not displaying");
            Assert.assertEquals(studentResponse.labelsInChart.get(9).getText(),"Q10","Question label is not displaying");
            studentResponse.scroll_RightPerformance.click();
            Thread.sleep(1000);
            Assert.assertEquals(studentResponse.labelsInChart.get(0).getText(),"Q2", "Question label is not displaying");
            Assert.assertEquals(studentResponse.labelsInChart.get(9).getText(),"Q11","Question label is not displaying");
            studentResponse.scroll_RightPerformance.click();
            Thread.sleep(1000);
            Assert.assertEquals(studentResponse.labelsInChart.get(0).getText(),"Q3", "Question label is not displaying");
            Assert.assertEquals(studentResponse.labelsInChart.get(9).getText(),"Q12","Question label is not displaying");


            studentResponse.scroll_LeftPerformance.click();
            Thread.sleep(1000);
            Assert.assertEquals(studentResponse.labelsInChart.get(0).getText(),"Q2", "Question label is not displaying");
            Assert.assertEquals(studentResponse.labelsInChart.get(9).getText(),"Q11","Question label is not displaying");
            studentResponse.scroll_LeftPerformance.click();
            Thread.sleep(1000);
            Assert.assertEquals(studentResponse.labelsInChart.get(0).getText(),"Q1", "Question label is not displaying");
            Assert.assertEquals(studentResponse.labelsInChart.get(9).getText(),"Q10","Question label is not displaying");





        }
        catch (Exception e)
        {
            Assert.fail("Exception in liveGradebookView method in class LiveGradebookView",e);
        }
    }

}
