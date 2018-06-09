package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.livegradebook;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.TakeAssignment;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by shashank on 07-09-2015.
 */
public class GradebookWithAllQuestionType extends Driver {


    /*This test case is for following questions
    Under Classic Question Types
    truefalse
    multiplechoice
    multipleselection
    textentry
    textdropdown*/
    @Test(priority = 1,enabled = true)
     public void livegradebookWithFirstFiveQuestion()
    {
        int dataIndex = 99;
        String appendChar = "a";
        List<String> questionList= ReadTestData.readDataByTagNameList("","questiontype",Integer.toString(dataIndex));
        // System.out.println(questionList.size());
        try {
            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            //signup with a student
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out
            //login as instructor
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            System.out.println("questionList.get(0)"+questionList.get(0));
            //create a questions
            new Assignment().create(dataIndex,questionList.get(0) );//Create a true false question
            for(int i=1;i<questionList.size();i++)
            {
                new Assignment().addQuestion(dataIndex,questionList.get(i));
            }
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);//login as student

            //start new driver instance and login with instructor with that instance
            //
            WebDriver firefoxWebdriver=new FirefoxDriver();

            firefoxWebdriver.close();
            firefoxWebdriver=new Driver().startDriver(firefoxWebdriver);
            InstructorDashboard instructorDashboard= PageFactory.initElements(firefoxWebdriver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(firefoxWebdriver,StudentResponse.class);


            new Login().loginAsInstructor(appendChar,dataIndex,firefoxWebdriver);//login as a teacher
            new Navigator().navigateTo("dashboard",firefoxWebdriver);
            instructorDashboard.viewResponseonDashboard().click();//Click on view response

            //start Assignemnt and attempt all correct
            new Assignment().startAssignment(dataIndex);
            for(String questiontype:questionList)
            {
                new Assignment().attemptQuestions(questiontype,dataIndex);
            }
            Thread.sleep(8000);
            //verify the colour os bar
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(0).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(1).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(2).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(3).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(4).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            TakeAssignment takeAssignment =PageFactory.initElements(driver,TakeAssignment.class);
            takeAssignment.navigateToQuestion.get(1).click();//click on first question bubble
            takeAssignment.navigateToQuestion.get(0).click();//click on first question bubble
            takeAssignment.navigateToQuestion.get(0).click();//click on first question bubble
            for(String questiontype:questionList)
            {
                new Assignment().attemptQuestions(questiontype,100);
            }
            Thread.sleep(8000);
            //verify the colour os bar
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(0).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(1).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(2).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(3).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(4).getAttribute("fill"),"#EBBB3D","Colour according to question attempt is wrong");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in allQuestiontype method in GradebookWithAllQuestionType class", e);
        }
    }

   /*This test case is for following questions
      numericentrywithunits
      advancednumeric
      expressionevaluator
      matchthefollowing(MAth Tech Enhanced)
      draganddrop(Math tech Enhanced)*/
    @Test(priority = 2,enabled = true)
    public void livegradebookWwithSecondFiveQuestion()
    {
        int dataIndex = 101;
        String appendChar = "a17";
        List<String> questionList= ReadTestData.readDataByTagNameList("","questiontype",Integer.toString(dataIndex));
        // System.out.println(questionList.size());
        try {
               new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            //signup with a student
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out
            //login as instructor
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            System.out.println("questionList.get(0)"+questionList.get(0));
            //create a questions
            new Assignment().create(dataIndex,questionList.get(0) );//Create a true false question
         for(int i=1;i<questionList.size();i++)
            {
                new Assignment().addQuestion(dataIndex,questionList.get(i));
            }
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Navigator().logout();
           new Login().loginAsStudent(appendChar,dataIndex);//login as student

            //start new driver instance and login with instructor with that instance
            //
            WebDriver firefoxWebdriver=new FirefoxDriver();

            firefoxWebdriver.close();
            firefoxWebdriver=new Driver().startDriver(firefoxWebdriver);
            InstructorDashboard instructorDashboard= PageFactory.initElements(firefoxWebdriver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(firefoxWebdriver,StudentResponse.class);


            new Login().loginAsInstructor(appendChar,dataIndex,firefoxWebdriver);//login as a teacher
            new Navigator().navigateTo("dashboard",firefoxWebdriver);
            instructorDashboard.viewResponseonDashboard().click();//Click on view response

            //start Assignemnt and attempt all correct
            new Assignment().startAssignment(dataIndex);
           for(String questiontype:questionList)
            {
                new Assignment().attemptQuestions(questiontype,dataIndex);
            }
            Thread.sleep(8000);
              //verify the colour os bar
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(0).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(1).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(2).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(3).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(4).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
           TakeAssignment takeAssignment =PageFactory.initElements(driver,TakeAssignment.class);
            takeAssignment.navigateToQuestion.get(1).click();//click on first question bubble
            takeAssignment.navigateToQuestion.get(0).click();//click on first question bubble
            takeAssignment.navigateToQuestion.get(0).click();//click on first question bubble
            for(String questiontype:questionList)
            {
                new Assignment().attemptQuestions(questiontype,102);
            }
            Thread.sleep(8000);
            //verify the colour os bar
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(0).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(1).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(2).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(3).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(4).getAttribute("fill"),"#EBBB3D","Colour according to question attempt is wrong");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in allQuestiontype method in GradebookWithAllQuestionType class", e);
        }
    }




    /*This test case is for following questions
       clozeformula
       graphplotter
       clozematrix
       resequence(Math Tech Enhanced)
       labelanimagetext*/
    @Test(priority =3,enabled = true)
    public void livegradebookWithThirdFiveQuestion()
    {
        int dataIndex = 103;
        String appendChar = "a10";
        List<String> questionList= ReadTestData.readDataByTagNameList("","questiontype",Integer.toString(dataIndex));
        try {
            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            //signup with a student
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out
            // login as instructor
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            System.out.println("questionList.get(0)"+questionList.get(3));
            //create a questions
            new Assignment().create(dataIndex,questionList.get(3) );//Create a true false question
           for(int i=1;i<questionList.size();i++)
            {
                new Assignment().addQuestion(dataIndex,questionList.get(i));
            }
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);//login as student

            //start new driver instance and login with instructor with that instance
            //
            WebDriver firefoxWebdriver=new FirefoxDriver();

            firefoxWebdriver.close();
            firefoxWebdriver=new Driver().startDriver(firefoxWebdriver);
            InstructorDashboard instructorDashboard= PageFactory.initElements(firefoxWebdriver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(firefoxWebdriver,StudentResponse.class);


            new Login().loginAsInstructor(appendChar,dataIndex,firefoxWebdriver);//login as a teacher
            new Navigator().navigateTo("dashboard",firefoxWebdriver);
            instructorDashboard.viewResponseonDashboard().click();//Click on view response

            //start Assignemnt and attempt all correct
            new Assignment().startAssignment(dataIndex);
            for(String questiontype:questionList)
            {
                new Assignment().attemptQuestions("resequence",dataIndex);
            }
            Thread.sleep(8000);
            //verify the colour os bar
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(0).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(1).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(2).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(3).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(4).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            TakeAssignment takeAssignment =PageFactory.initElements(driver,TakeAssignment.class);
            takeAssignment.navigateToQuestion.get(1).click();//click on first question bubble
            takeAssignment.navigateToQuestion.get(0).click();//click on first question bubble
            takeAssignment.navigateToQuestion.get(0).click();//click on first question bubble
            for(String questiontype:questionList)
            {
                new Assignment().attemptQuestions(questiontype,104);
            }
            Thread.sleep(8000);
            //verify the colour os bar
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(0).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(1).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(2).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(3).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(4).getAttribute("fill"),"#EBBB3D","Colour according to question attempt is wrong");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in allQuestiontype method in GradebookWithAllQuestionType class", e);
        }
    }



    /*This test case is for following questions
      labelanimagedropdown
      numberline
      classification(MAth tech Enhanced)
      sentenceresponse
      matchingtables
      passage(Math tech enahanced)*/
    @Test(priority =4,enabled = true)
    public void livegradebookWithFourthSixQuestion()
    {
        int dataIndex = 105;
        String appendChar = "a22";
        List<String> questionList= ReadTestData.readDataByTagNameList("","questiontype",Integer.toString(dataIndex));
        try {
           new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            //signup with a student
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1*//**//*
            new Navigator().logout();//Log out
            //login as instructor
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            //create a questions
            new Assignment().create(dataIndex,questionList.get(0) );//Create a true false question
            for(int i=1;i<questionList.size();i++)
            {
                new Assignment().addQuestion(dataIndex,questionList.get(i));
            }
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);//login as student

            //start new driver instance and login with instructor with that instance
            //
            WebDriver firefoxWebdriver=new FirefoxDriver();

            firefoxWebdriver.close();
            firefoxWebdriver=new Driver().startDriver(firefoxWebdriver);
            InstructorDashboard instructorDashboard= PageFactory.initElements(firefoxWebdriver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(firefoxWebdriver,StudentResponse.class);


            new Login().loginAsInstructor(appendChar,dataIndex,firefoxWebdriver);//login as a teacher
            new Navigator().navigateTo("dashboard",firefoxWebdriver);
            instructorDashboard.viewResponseonDashboard().click();//Click on view response

            //start Assignemnt and attempt all correct
            new Assignment().startAssignment(dataIndex);
            for(String questiontype:questionList)
            {
                new Assignment().attemptQuestions(questiontype,dataIndex);
            }
            Thread.sleep(8000);
            //verify the colour os bar
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(0).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(1).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(2).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(3).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(4).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            TakeAssignment takeAssignment =PageFactory.initElements(driver,TakeAssignment.class);
            takeAssignment.navigateToQuestion.get(1).click();//click on first question bubble
            takeAssignment.navigateToQuestion.get(0).click();//click on first question bubble
            takeAssignment.navigateToQuestion.get(0).click();//click on first question bubble
            for(String questiontype:questionList)
            {
                new Assignment().attemptQuestions(questiontype,106);
            }
            Thread.sleep(8000);
            //verify the colour os bar
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(0).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(1).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(2).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(3).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(4).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in allQuestiontype method in GradebookWithAllQuestionType class", e);
        }
    }

    /*This test case is for following questions
      resequence(ELA tech Enhanced)
      classification(ELA tech Enhanced)
      draganddrop(ELA tech Enhanced)
      matchingtables(ELA tech Enhanced)
      passage(ELA tech Enhanced)
      matchthefollowing(ELA tech Enhanced)*/
    @Test(priority =5,enabled = true)
    public void livegradebookWithFifthSixQuestion()
    {
        int dataIndex = 107;
        String appendChar = "a13";
        List<String> questionList= ReadTestData.readDataByTagNameList("","questiontype",Integer.toString(dataIndex));
        try {
          new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            //  new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            //signup with a student
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out
            //login as instructor
           new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            //create a questions
            new Assignment().create(dataIndex,questionList.get(0) );//Create a true false question
            for(int i=1;i<questionList.size();i++)
            {
                new Assignment().addQuestion(dataIndex,questionList.get(i));
            }
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);//login as student

            //start new driver instance and login with instructor with that instance
            //
           WebDriver firefoxWebdriver=new FirefoxDriver();

            firefoxWebdriver.close();
            firefoxWebdriver=new Driver().startDriver(firefoxWebdriver);
            InstructorDashboard instructorDashboard= PageFactory.initElements(firefoxWebdriver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(firefoxWebdriver,StudentResponse.class);


            new Login().loginAsInstructor(appendChar,dataIndex,firefoxWebdriver);//login as a teacher
            new Navigator().navigateTo("dashboard",firefoxWebdriver);
            instructorDashboard.viewResponseonDashboard().click();//Click on view response

            //start Assignemnt and attempt all correct
            new Assignment().startAssignment(dataIndex);
           for(String questiontype:questionList)
            {
                new Assignment().attemptQuestions(questiontype,dataIndex);
            }
            Thread.sleep(8000);
            //verify the colour os bar
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(0).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(1).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(2).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(3).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(4).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            TakeAssignment takeAssignment =PageFactory.initElements(driver,TakeAssignment.class);
            takeAssignment.navigateToQuestion.get(0).click();//click on first question bubble
            takeAssignment.navigateToQuestion.get(0).click();//click on first question bubble
            for(String questiontype:questionList)
            {
                new Assignment().attemptQuestions(questiontype,106);
            }
            Thread.sleep(8000);
            //verify the colour os bar
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(0).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(1).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(2).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(3).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(4).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in allQuestiontype method in GradebookWithAllQuestionType class", e);
        }
    }


    /*This test case is for following questions
      Lineplot
      rangeplotter
      fractioneditor
      graphing*/
    @Test(priority =5,enabled = true)
    public void livegradebookWithSixthsetQuestion()
    {
        int dataIndex = 109;
        String appendChar = "a16";
        List<String> questionList= ReadTestData.readDataByTagNameList("","questiontype",Integer.toString(dataIndex));
        try {
           new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            //  new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            //signup with a student
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out
            //login as instructor
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            //create a questions
            new Assignment().create(dataIndex,questionList.get(0) );//Create a true false question
            for(int i=1;i<questionList.size();i++)
            {
                new Assignment().addQuestion(dataIndex,questionList.get(i));
            }
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);//login as student

            //start new driver instance and login with instructor with that instance
            //
            WebDriver firefoxWebdriver=new FirefoxDriver();

            firefoxWebdriver.close();
            firefoxWebdriver=new Driver().startDriver(firefoxWebdriver);
            InstructorDashboard instructorDashboard= PageFactory.initElements(firefoxWebdriver, InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(firefoxWebdriver,StudentResponse.class);


            new Login().loginAsInstructor(appendChar,dataIndex,firefoxWebdriver);//login as a teacher
            new Navigator().navigateTo("dashboard",firefoxWebdriver);
            instructorDashboard.viewResponseonDashboard().click();//Click on view response

            //start Assignemnt and attempt all correct
            new Assignment().startAssignment(dataIndex);
            for(String questiontype:questionList)
            {
                new Assignment().attemptQuestions(questiontype,dataIndex);
            }
            Thread.sleep(8000);
            //verify the colour os bar
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(0).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(1).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(2).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(3).getAttribute("fill"),"#6bb45f","Colour according to question attempt is wrong");
            TakeAssignment takeAssignment =PageFactory.initElements(driver,TakeAssignment.class);
            takeAssignment.navigateToQuestion.get(0).click();//click on first question bubble
            takeAssignment.navigateToQuestion.get(0).click();//click on first question bubble
            for(String questiontype:questionList)
            {
                new Assignment().attemptQuestions(questiontype,110);
            }
            Thread.sleep(8000);
            //verify the colour os bar
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(0).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(1).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(2).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(3).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(4).getAttribute("fill"),"#cc470f","Colour according to question attempt is wrong");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in allQuestiontype method in GradebookWithAllQuestionType class", e);
        }
    }
}
