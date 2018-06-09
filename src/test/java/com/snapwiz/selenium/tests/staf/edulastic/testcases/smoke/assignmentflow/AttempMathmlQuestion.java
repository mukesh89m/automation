package com.snapwiz.selenium.tests.staf.edulastic.testcases.smoke.assignmentflow;

import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AddQuestion;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assessments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssignmentSummary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.QuestionTypesPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Stack;

/**
 * Created by mukesh on 19/10/16.
 */
public class AttempMathmlQuestion extends Driver {

    AddQuestion addQuestion=null;
    Assessments assessments=null;
    SchoolPage schoolPage=null;
    QuestionTypesPage questionTypesPage=null;
    public static String appendChar=null;
    Stack stack=new Stack();
    public static int noOfUser=50;



//    @BeforeClass
//    public void initCount(){
//        for(int i=0;i<10;i++){
//            stack.push(new Integer(i));
//        }


    @BeforeMethod
    public void init(){
        WebDriver driver=Driver.getWebDriver();
        addQuestion=PageFactory.initElements(driver,AddQuestion.class);
        assessments = PageFactory.initElements(driver, Assessments.class);
        schoolPage = PageFactory.initElements(driver, SchoolPage.class);
        questionTypesPage=PageFactory.initElements(driver,QuestionTypesPage.class);


    }


    @Test(enabled = true)
    public void createQuestion(){
        WebDriver driver= Driver.getWebDriver();
        try{
            int dataIndex = 1;
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            appendChar = StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
            //String appendChar="Bg";
            System.out.println("appendchar "+appendChar);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("654321369",dataIndex,false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out
            ReportUtil.log("Create School","create school with zip code 654321369","info");

            for (int i = 0; i <noOfUser; i++) {
                new SignUp().studentDirectRegister(appendChar+i,classCode,dataIndex);//Sign up as a student
                System.out.println("student "+(i+1)+" registered");
                WebDriverUtil.waitForAjax(driver,5);
                new Navigator().logout();//log out

            }
            ReportUtil.log("Register student","Student registered","info");
            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            WebDriverUtil.waitForAjax(driver,60);

            assessments.button_createCommonAssessment.click();//click on create new assignment

            driver.findElement(By.id("new-assessment-name")).clear();
            driver.findElement(By.id("new-assessment-name")).sendKeys(assessmentName);//give the assignment title
            new Click().clickByid("new-assessment-name");
            new Click().clickByXpath("//html/body");
            new Click().clickByid("create-assessment-with-val");//click on Create
            WebDriverUtil.waitForAjax(driver,60);
            Thread.sleep(9000);
            try {
                driver.findElement(By.className("select2-selection__choice__remove")).click();
            } catch (Exception e) {
                driver.findElement(By.cssSelector("input[placeholder='All Grades']")).click();
            }

            List<WebElement> selectgrade =  driver.findElements(By.cssSelector("#select2-grade-drop-down-results>li")); //select grade7
            for (WebElement ele:selectgrade){
                if (ele.getText().trim().equals("Grade 7")){
                    ele.click();
                    break;
                }
            }
            driver.findElement(By.cssSelector("input[ placeholder='All Types']")).click(); //click on the all types
            driver.findElements(By.cssSelector("#select2-qtype-drop-down-results>li")).get(2).click(); //select expression evaluater
            WebDriverUtil.waitForAjax(driver,60);
            List<WebElement> questionCheckbox = driver.findElements(By.cssSelector(".as-grey-checkbox"));
            for (int i = 2; i <12 ; i++) {
                WebDriverUtil.scrollIntoView(questionCheckbox.get(i),false);
                questionCheckbox.get(i).click(); //select first five question
                Thread.sleep(1000);
                System.out.println("The question "+ (i+1) +" is created");
            }

            Thread.sleep(5000);
            driver.findElement(By.cssSelector("div[class^='createQuestion-review-button']>button")).click(); //click on the review
            Thread.sleep(4000);
            driver.findElement(By.cssSelector("#assessments-use-button")).click(); //click on the publish

//            new Assignment().assignToStudent(dataIndex,appendChar);  // assign to student
            driver.findElement(By.cssSelector("#assign-button>span")).click();
            new Navigator().logout();//log out

        }catch (Exception e ){
            Assert.fail("Exception in TC createQuestion in class 'AttempMathmlQuestion'", e);
        }
    }



}
