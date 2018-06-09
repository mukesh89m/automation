package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.dashboard;

import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by pragyas on 21-01-2016.
 */
public class StudentDashboard extends Driver{

    @Test(priority = 1,enabled = true)
    //Verifying the order of assignments and class based on due date of the assignments
    public void studentDashboardVerification() {
        try {

            int dataIndex = 17;
            String appendChar = "a1";
            WebDriver driver=Driver.getWebDriver();
            String class1Assignment1 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(17));
            String class1Assignment2 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(18));
            String class1Assignment3 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(19));
            String class2Assignment = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(20));
            String class3Assignment = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(21));

            ManageClass manageClass = PageFactory.initElements(driver,ManageClass.class);
            com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.StudentDashboard studentDashboard = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.StudentDashboard.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String studentName = methodName+"st"+appendChar;
            String instructorName = methodName+"ins"+appendChar;

            String  className1 = methodName+"class"+"class1";
            String  className2 = methodName+"class"+"class2";
            String  className3 = methodName+"class"+"class3";

            String appendCharacterBuild = System.getProperty("UCHAR");
            if (appendCharacterBuild != null) {
                appendChar = appendChar + appendCharacterBuild;
            }

                new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor
                new School().createWithOnlyName(appendChar, dataIndex);//Create a school
                String classCode = new Classes().createClass("class1", dataIndex);//Create class1
                System.out.println("class code" +classCode);
                new Assignment().create(dataIndex, "passage");//Create an assignment1
                new Assignment().assignToStudent(dataIndex, "class1");//Assign the 1st assignment to class1

                new Assignment().create(18,"truefalse");//Create an assignment2
                new Assignment().assignToStudent(18,"class1");//Assign assignment2 to class1

                new Assignment().create(19,"truefalse");//Create an assignment3
                new Assignment().assignToStudent(19,"class1");//Assign assignment3 to class1

                String classCode2 = new Classes().createNewClass("class2",dataIndex,"Grade 2","Mathematics","Math - Common Core");//Create a grade2 class
                System.out.println("class code2" +classCode2);
                new Assignment().create(20, "truefalse");//Create an assignment in grad2
                new Assignment().assignToStudent(20,"class2");//Assign to class2

                String classCode3 = new Classes().createNewClass("class3",dataIndex,"Grade 3","Mathematics","Math - Common Core");//Create a grade3 class
                System.out.println("class code3" +classCode3);
                new Assignment().create(21, "truefalse");//Create an assignment in grade3
                new Assignment().assignToStudent(21,"class3");//Assign to class3
                new Navigator().logout();//log out

                new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student in class1
                new Navigator().navigateTo("manageclass");//Navigate to dashboard
                manageClass.getNewClass().click();//Click on new class
                manageClass.enterClassCodeWebElement().sendKeys("18E2D");//Join class2 (Grade2)
                manageClass.getSaveButton().click();//Click on join class

                new Navigator().navigateTo("manageclass");//Navigate to dashboard
                manageClass.getNewClass().click();//Click on new class
                manageClass.enterClassCodeWebElement().sendKeys("73BDE");//Join class3 (Grade3)
                manageClass.buttonJoinClass().click();//Click on join class

                new Navigator().navigateTo("dashboard");//Navigate to dashboard

                //Verify header as Dashboard,subheading as My Classes and buttons Join a class and Launch Class
                Assert.assertEquals(studentDashboard.heading_dashboard.getText(),"Dashboard","Heading is not displayed as expected");
                Assert.assertEquals(studentDashboard.subHeading_MyClasses.getText(),"My Classes","Sub heading is not displayed as expected");
                Assert.assertEquals(studentDashboard.button_launchClass.get(0).getText(),"Launch Class","Launch class button is not displayed");
                Assert.assertEquals(studentDashboard.button_joinAClass.getText(),"Join a Class","Join a class button is not displayed");

                //Verify the student name
                Assert.assertEquals(studentDashboard.userName.getText(),studentName,"Student mail is not displayed as expected");

                //Verify the order of the class name
                Assert.assertEquals(studentDashboard.name_classes.get(0).getText(),className2,"Classname 2  is not displayed on the top as expected");
                Assert.assertEquals(studentDashboard.name_classes.get(1).getText(),className3,"Classname 3 is not displayed in the middle as expected");
                Assert.assertEquals(studentDashboard.name_classes.get(2).getText(),className1,"Classname 1 is not displayed on the bottom as expected");

                //Verify the order of the Class Code
                Assert.assertTrue(studentDashboard.list_classCodes.get(0).getText().contains(classCode2), "Classcode 2 is not displayed on the top as expected");
                Assert.assertTrue(studentDashboard.list_classCodes.get(1).getText().contains(classCode3), "Classcode 3 is not displayed in the middle as expected");
                Assert.assertTrue(studentDashboard.list_classCodes.get(2).getText().contains(classCode), "Classcode 1 is not displayed on the bottom as expected");

                //Verify the order of assignments
                Assert.assertEquals(studentDashboard.list_assignmentsName.get(0).getText(),class2Assignment, "Class 2 assignment is not displayed on the top as expected");
                Assert.assertEquals(studentDashboard.list_assignmentsName.get(1).getText(),class3Assignment, "Class 3 assignment is not displayed in the middle as expected");
                Assert.assertEquals(studentDashboard.list_assignmentsName.get(2).getText(),class1Assignment1, "Class 1 assignment1 is not displayed on the bottom as expected");

                //Verify instructor name and Active Assignment
                Assert.assertTrue(studentDashboard.list_instructorName.get(0).getText().contains(instructorName),"Instructor name is not displayed as expected");
                Assert.assertEquals(studentDashboard.activeAssignment.get(0).getText(),"Active Assignment:","Active Assignment is not displayed as expected");

                studentDashboard.button_launchClass.get(2).click();//Click on class1 launch class
                Thread.sleep(2000);
                //Verify the order of assignments in a class1
                Assert.assertTrue(studentDashboard.list_assignmentsName.get(0).getText().contains(class1Assignment1), "Assignment1 in class1 is not displayed on the top as expected");
                Assert.assertTrue(studentDashboard.list_assignmentsName.get(1).getText().contains(class1Assignment3), "Assignment3 in class1 is not displayed in the middle as expected");
                Assert.assertTrue(studentDashboard.list_assignmentsName.get(2).getText().contains(class1Assignment2), "Assignment2 in class1 is not displayed on the bottom as expected");

                new Navigator().navigateTo("dashboard");//Navigate to dashboard
                new Navigator().navigateTo("assignment");//Navigate to assignment page

                //Verify the default class name in dropdown
                Assert.assertEquals(assignments.dropdown_defaultClassName.getText(),className2,"By default class2 nam is not displayed even if it is present on the top in dashboard");

                assignments.dropdown_defaultClassName.click();//Click on class drop down

                assignments.dropdown_classNames.get(0).click();//Click on class3

                Thread.sleep(1000);
                //Class3 assignment should be displayed
                Assert.assertTrue(assignments.getAssessmentName().getText().contains(class3Assignment),"Class3 assignment is not displayed after selecting class3 in dropdown");

                new Navigator().navigateTo("dashboard");//Navigate to dashboard

                //Verify the order of class i.e. should be same as previous (should not be impacted)
                Assert.assertEquals(studentDashboard.name_classes.get(0).getText(),className2,"Classname 2  is not displayed on the top as expected");
                Assert.assertEquals(studentDashboard.name_classes.get(1).getText(),className3,"Classname 3 is not displayed in the middle as expected");
                Assert.assertEquals(studentDashboard.name_classes.get(2).getText(),className1,"Classname 1 is not displayed on the bottom as expected");

                new Navigator().navigateTo("manageclass");//Navigate to manage class

                //All classes present in Dashboard must be present in Manage Classes
                Assert.assertEquals(manageClass.getClassNameStudent().get(0).getText(),className3,"Class3 is not displaying as expected");
                Assert.assertEquals(manageClass.getClassNameStudent().get(1).getText(),className2,"Class2 is not displaying as expected");
                Assert.assertEquals(manageClass.getClassNameStudent().get(2).getText(),className1,"Class1 is not displaying as expected");



        }catch(Exception e){
               Assert.fail("Exception in 'studentDashboardVerification' Class in method 'studentDashboardVerification'", e);        }

            }


    }


