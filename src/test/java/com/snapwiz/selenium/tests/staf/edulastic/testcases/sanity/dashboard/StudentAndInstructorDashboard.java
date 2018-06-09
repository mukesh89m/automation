package com.snapwiz.selenium.tests.staf.edulastic.testcases.sanity.dashboard;

import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssignmentDetails;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.TakeAssignment;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Calendar;

/**
 * Created by pragyas on 09-02-2016.
 */
public class StudentAndInstructorDashboard extends Driver {

    @Test(priority = 1,enabled = true)
    public void studentAndInstructorDashboardVerification() {
        WebDriver driver=Driver.getWebDriver();
        try {

            ReportUtil.log("Description","This test case validates the student dashboard(status and order of assignment  etc) and instructor dashboard information(assignment details page, standard mastered and assessed count),","info");
            int dataIndex = 17;
            String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            //String appendChar = "ansr";

            String appendCharacterBuild="";
            if (System.getProperty("UCHAR")!=null)
                appendCharacterBuild=System.getProperty("UCHAR");

            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(20));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(20));

            String class1Assignment1 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(17));
            String class1Assignment2 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(18));
            String class1Assignment3 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(19));
            String class2Assignment = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(20));
            String class3Assignment = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(21));

            ManageClass manageClass = PageFactory.initElements(driver, ManageClass.class);
            com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.StudentDashboard studentDashboard = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.StudentDashboard.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            TakeAssignment takeAssignment = PageFactory.initElements(driver,TakeAssignment.class);
            AssignmentDetails assignmentDetails = PageFactory.initElements(driver,AssignmentDetails.class);
            InstructorDashboard instructorDashboard = PageFactory.initElements(driver,InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String studentName = methodName+"st"+appendChar+appendCharacterBuild;
            String instructorName = methodName+"ins"+appendChar+appendCharacterBuild;

            String  className1 = methodName+"class"+appendChar+appendCharacterBuild;
            String  className2 = methodName+"class"+"class2"+appendCharacterBuild;
            String  className3 = methodName+"class"+"class3"+appendCharacterBuild;

            new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode = new Classes().createClass(appendChar, dataIndex);//Create class1
            System.out.println("classCode :"+classCode);
            // new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor

            new Assignment().create(dataIndex, "truefalse");//Create an assignment1
            new Assignment().assignToStudent(dataIndex, appendChar);//Assign the 1st assignment to class1

            new Assignment().create(18,"truefalse");//Create an assignment2
            new Assignment().assignToStudent(18,appendChar);//Assign assignment2 to class1

            new Assignment().create(19,"truefalse");//Create an assignment3
            new Assignment().assignToStudent(19,appendChar);//Assign assignment3 to class1

            String classCode2 = new Classes().createNewClass("class2",dataIndex,"Grade 2","Mathematics","Math - Common Core");//Create a grade2 class
            new Assignment().create(20, "truefalse");//Create an assignment in grade2
            new Assignment().assignToStudent(20,"class2");//Assign to class2
            System.out.println("classCode2 :"+classCode2);

            String classCode3 = new Classes().createNewClass("class3",dataIndex,"Grade 3","Mathematics","Math - Common Core");//Create a grade3 class
            System.out.println("classCode3 :"+classCode3);

            new Assignment().create(21, "truefalse");//Create an assignment in grade3
            new Assignment().assignToStudent(21,"class3");//Assign to class3
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student in class1

            // Join a class button is removed in RR release on dashboard
            // studentDashboard.button_joinAClass.click();//Click on join button

            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.getNewClass().click();//Click on new class

            //Student should be navigated to manage class page after clicking on join a class button
            CustomAssert.assertTrue(manageClass.label_manageClass.getText().contains("Manage Class"),"Verify manage class label","Manage class label is displayed as expected","Manage class label is not displayed as expected");
            manageClass.enterClassCodeWebElement().sendKeys(classCode2);//Join class2 (Grade2)
            manageClass.getSaveButton().click();//Click on join class

            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.getNewClass().click();//Click on new class
            manageClass.enterClassCodeWebElement().sendKeys(classCode3);//Join class3 (Grade3)
            manageClass.buttonJoinClass().click();//Click on join class

            new Navigator().navigateTo("dashboard");//Navigate to dashboard

            //Verify header as Dashboard,subheading as My Classes and buttons Join a class and 'View All Assignments' button
            CustomAssert.assertEquals(studentDashboard.heading_dashboard.getText(), "Dashboard","Verify Dashboard heading on dashboard page","Heading is displayed as expected","Heading is not displayed as expected");
            CustomAssert.assertEquals(studentDashboard.subHeading_MyClasses.getText(),"My Classes","Verify subheading My Classes on dashboard page","Sub heading is displayed as expected","Sub heading is not displayed as expected");
            CustomAssert.assertEquals(studentDashboard.button_launchClass.get(0).getText(),"View All Assignments","Verify 'View All Assignments' button","'View All Assignments' button is displayed","Launch class button is not displayed");

            // Join a class button is removed in RR release on dashboard
            // CustomAssert.assertEquals(studentDashboard.button_joinAClass.getText(),"Join a Class","Verify Join class button","Join a class button is displayed","Join a class button is not displayed");

            //Verify the student name
            CustomAssert.assertEquals(studentDashboard.userName.getText(),studentName,"Verify student name","Student name is displayed as expected","Student name is not displayed as expected");

            //Verify the order of the class name
            CustomAssert.assertEquals(studentDashboard.name_classes.get(0).getText(),className2,"Verify the order of class","Classname 2  is displayed on the top as expected","Classname 2  is not displayed on the top as expected");
            CustomAssert.assertEquals(studentDashboard.name_classes.get(1).getText(),className3,"Verify the order of class","Classname 3 is displayed in the middle as expected","Classname 3 is not displayed in the middle as expected");
            CustomAssert.assertEquals(studentDashboard.name_classes.get(2).getText(),className1,"Verify the order of class","Classname 1 is displayed on the bottom as expected","Classname 1 is not displayed on the bottom as expected");

            //Verify the order of the Class Code
            CustomAssert.assertTrue(studentDashboard.list_classCodes.get(0).getText().contains(classCode2),"Verify the order of class code","Classcode 2 is displayed on the top as expected","Classcode 2 is not displayed on the top as expected");
            CustomAssert.assertTrue(studentDashboard.list_classCodes.get(1).getText().contains(classCode3), "Verify the order of class code","Classcode 3 is displayed in the middle as expected","Classcode 3 is not displayed in the middle as expected");
            CustomAssert.assertTrue(studentDashboard.list_classCodes.get(2).getText().contains(classCode), "Verify the order of class code","Classcode 1 is displayed on the bottom as expected","Classcode 1 is not displayed on the bottom as expected");

            //Verify the order of assignments
            CustomAssert.assertEquals(studentDashboard.list_assignmentsName.get(0).getText(),class2Assignment,"Verify the order of assignment","Class 2 assignment is displayed on the top as expected","Class 2 assignment is not displayed on the top as expected");
            CustomAssert.assertEquals(studentDashboard.list_assignmentsName.get(1).getText(),class3Assignment, "Verify the order of assignment","Class 3 assignment is displayed in the middle as expected","Class 3 assignment is not displayed in the middle as expected");
            CustomAssert.assertEquals(studentDashboard.list_assignmentsName.get(2).getText(),class1Assignment1,"Verify the order of assignment", "Class 1 assignment1 is displayed on the bottom as expected","Class 1 assignment1 is not displayed on the bottom as expected");

            //Verify instructor name and Active Assignment
            CustomAssert.assertTrue(studentDashboard.list_instructorName.get(0).getText().contains(instructorName),"Verify instructor name","Instructor name is displayed as expected","Instructor name is not displayed as expected");
            CustomAssert.assertEquals(studentDashboard.activeAssignment.get(0).getText(),"Active Assignment:","Verify Active Assignment text","Active Assignment is displayed as expected","Active Assignment is not displayed as expected");

            //Verify that Class dropdown should not be displayed in student dashboard
            boolean classDropDownFound = new BooleanValue().presenceOfElementByWebElement(dataIndex,assignments.dropdown_defaultClassName);

            CustomAssert.assertEquals(classDropDownFound,false,"Verify class dropdown in dashboard page","Class drop down is not displayed as expected","Class drop down is displayed");

            Calendar calendar = Calendar.getInstance();
            int currentMonth = calendar.get(Calendar.MONTH);
            int DueDateYear = calendar.get(Calendar.YEAR)+1;
            String dueDateMonth = new Calender().getCurrentMonthName(currentMonth+1);

            //Verify the due date of the class2Assignment on the top
            CustomAssert.assertEquals(studentDashboard.dueDate_assignment.get(0).getText(),"Due on"+" "+dueDateMonth+" "+duedate+","+" "+DueDateYear+"  "+"at"+" "+duetime,"Verify due date of class2Assignment","Due date is displayed as expected","Due date is not displayed as expected");

            studentDashboard.button_launchClass.get(2).click();//Click on class1 launch class
            Thread.sleep(2000);
            //Verify the order of assignments in a class1 and number of assignment
            CustomAssert.assertTrue(studentDashboard.list_assignmentsName.get(0).getText().contains(class1Assignment1),"Verify the order of assignment in class1","Assignment1 in class1 is displayed on the top as expected", "Assignment1 in class1 is not displayed on the top as expected");
            CustomAssert.assertTrue(studentDashboard.list_assignmentsName.get(1).getText().contains(class1Assignment3),"Verify the order of assignment in class3","Assignment3 in class1 is displayed in the middle as expected", "Assignment3 in class1 is not displayed in the middle as expected");
            CustomAssert.assertTrue(studentDashboard.list_assignmentsName.get(2).getText().contains(class1Assignment2),"Verify the order of assignment in class2", "Assignment2 in class1 is displayed on the bottom as expected","Assignment2 in class1 is not displayed on the bottom as expected");

            CustomAssert.assertEquals((String.valueOf(studentDashboard.list_assignmentsName.size())),"3","Verify the number of assignment in class3","No of assignments are same as created by instructor","No of assignments are not same as created by instructor");

            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            new Navigator().navigateTo("assignment");//Navigate to assignment page

            //Verify the default class name in dropdown
            CustomAssert.assertEquals(assignments.dropdown_defaultClassName.getText(),className2,"Verify default class name in dropdown","By default class2 name is displayed if it is present on the top in dashboard","By default class2 name is not displayed even if it is present on the top in dashboard");

            assignments.dropdown_defaultClassName.click();//Click on class drop down

            assignments.dropdown_classNames.get(0).click();//Click on class3

            Thread.sleep(1000);
            //Class3 assignment should be displayed
            CustomAssert.assertTrue(assignments.getAssessmentName().getText().contains(class3Assignment),"Verify class3 assignment","Class3 assignment is displayed after selecting class3 in dropdown","Class3 assignment is not displayed after selecting class3 in dropdown");

            new Navigator().navigateTo("dashboard");//Navigate to dashboard

            //Verify the order of class i.e. should be same as previous (should not be impacted)
            CustomAssert.assertEquals(studentDashboard.name_classes.get(0).getText(),className2,"Verify the order of class after changing the class in dropdown in assignment page","Classname 2  is displayed on the top as expected","Classname 2  is not displayed on the top as expected");
            CustomAssert.assertEquals(studentDashboard.name_classes.get(1).getText(),className3,"Verify the order of class after changing the class in dropdown in assignment page","Classname 3 is displayed in the middle as expected","Classname 3 is not displayed in the middle as expected");
            CustomAssert.assertEquals(studentDashboard.name_classes.get(2).getText(),className1,"Verify the order of class after changing the class in dropdown in assignment page","Classname 1 is displayed on the bottom as expected","Classname 1 is not displayed on the bottom as expected");

            new Navigator().navigateTo("manageclass");//Navigate to manage class

            //All classes present in Dashboard must be present in Manage Classes
            CustomAssert.assertEquals(manageClass.getClassNameStudent().get(0).getText(),className3,"Verify all the classes under manage class","Class3 is displaying as expected","Class3 is not displaying as expected");
            CustomAssert.assertEquals(manageClass.getClassNameStudent().get(1).getText(),className2,"Verify all the classes under manage class","Class3 is displaying as expected","Class2 is not displaying as expected");
            CustomAssert.assertEquals(manageClass.getClassNameStudent().get(2).getText(),className1,"Verify all the classes under manage class","Class3 is displaying as expected","Class1 is not displaying as expected");

            new Navigator().navigateTo("dashboard");//Navigate to dashboard

            //Verify the status of class2assignment on the top
            CustomAssert.assertEquals(studentDashboard.status_assignments.get(0).getText(),"Not Started","Verify the status of assignment2 on the top","Assignment 2 status is displayed as 'Not Started' as expected","Status is not displayed as expected");

            studentDashboard.list_assignmentsName.get(0).click();//Open class2Assignment on the top


            new Navigator().navigateTo("dashboard");//Navigate to dashboard

            //Verify the status of class2assignment at bottom
            CustomAssert.assertEquals(studentDashboard.status_assignments.get(2).getText(),"In Progress","Verify the status of class2assignment at the bottom","Classs2Assignment status is displayed as 'In Progress' as expected","class3Assignment Status is not displayed as expected");
            CustomAssert.assertEquals(studentDashboard.list_assignmentsName.get(2).getText(),class2Assignment,"Verify the class2assignment at the bottom","class2assignment is displayed at the bottom as expected","class2assignment is not displayed at the bottom as expected");

            //Open class3Assignment(non gradable) on the top and submit it
            studentDashboard.list_assignmentsName.get(0).click();
            takeAssignment.trueFalseOptions.get(0).click();//Select option A
            takeAssignment.button_next.click();//Click on submit button
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",takeAssignment.submitButton);//Again click on submit button

            new Navigator().navigateTo("dashboard");//Navigate to dashboard

            //Verify the status of class3Assignment at the bottom
            CustomAssert.assertEquals(studentDashboard.status_assignments.get(2).getText(),"Grade Released","Verify the status of class3Assignment at the bottom","class3Assignment status is displayed as 'In Progress' as expected","class3Assignment Status is not displayed as expected");
            CustomAssert.assertEquals(studentDashboard.list_assignmentsName.get(2).getText(),class3Assignment,"Verify the class3Assignment at the bottom","class3assignment is displayed at the bottom as expected","class3Assignment is not displayed at the bottom as expected");

            //Open class1Assignment1 on the top and submit it
            studentDashboard.list_assignmentsName.get(0).click();
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.true-false-student-answer-select")));
            takeAssignment.trueFalseOptions.get(0).click();//Select option A
            takeAssignment.button_next.click();//Click on submit button
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",takeAssignment.submitButton);//Again click on submit button

            new Navigator().navigateTo("assignment");//Navigate to assignment page

            //Verify the status of class1Assignment1(gradable on assignment submission) and position
            CustomAssert.assertEquals(studentDashboard.status_assignments.get(2).getText(),"Grade Released","Verify the status of class1Assignment1","class1Assignment1 status is displayed as 'Graded' expected","class1Assignment1 status is not displayed as expected");
            CustomAssert.assertTrue(studentDashboard.list_assignmentsName.get(2).getText().contains(class1Assignment1),"Verify the position of class1Assignment1","Class1Assignment1 is displayed at the bottom as expected","Class2Assignment1 is not displayed at the bottom as expected");

            new Navigator().navigateTo("dashboard");//Navigate to dashboard

            //Submit class2Assignment and verify the status
            studentDashboard.list_assignmentsName.get(1).click();//Click on class2Assignment
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.true-false-student-answer-select")));
            takeAssignment.trueFalseOptions.get(1).click();//Select option B
            takeAssignment.button_next.click();//Click on submit button
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",takeAssignment.submitButton);//Again click on submit button

            new Navigator().navigateTo("dashboard");//Navigate to dashboard

            //Verify the status of class2Assignment after submission(assigned as explicitly by teacher) and position
            CustomAssert.assertEquals(studentDashboard.status_assignments.get(2).getText(),"Submitted","Verify the status of class2Assignment","Class2Assignment status is displayed as 'Submitted' as expected","Class2Assignment status is not displayed as 'Submitted' as expected");
            CustomAssert.assertEquals(studentDashboard.list_assignmentsName.get(2).getText(),class2Assignment,"Verify the position of class2Assignment","class2Assignment is displayed in the middle as expected","class2Assignment is not displayed in the middle as expected");

            new Navigator().logout();//Log out student
            //Instructor Dashboard verification
            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");// Navigate to assignment page
            Thread.sleep(2000);
            assignments.dropdown_defaultClassName.click();//Click on dropdown arrow

            for(int i=0;i<assignments.dropdown_classNames.size();i++)
            {
                if(assignments.dropdown_classNames.get(i).getText().equals(className1))
                {
                    assignments.dropdown_classNames.get(i).click();//Click on class1
                }
            }

            //Class1Assignment3 should be displayed based on status
            CustomAssert.assertTrue(studentDashboard.list_assignmentsName.get(0).getText().contains(class1Assignment3),"Verify class1Assignment3 assignment","Class1Assignment3 assignment is displayed as expected","Class1Assignment3 assignment is not displayed as expected");

            new Navigator().navigateTo("assignment");//Navigate to assignment page

            assignments.dropdown_defaultClassName.click();//Click on class drop down

            for(int i=0;i<assignments.dropdown_classNames.size();i++)
            {
                if(assignments.dropdown_classNames.get(i).getText().equals(className2))
                {
                    assignments.dropdown_classNames.get(i).click();//Click on class2
                }
            }

            //Class2Assignment should be displayed
            CustomAssert.assertTrue(assignments.getAssessmentName().getText().contains(class2Assignment),"Verify class2 assignment","Class2 assignment is displayed after selecting class2 in dropdown","Class2 assignment is not displayed after selecting class2 in dropdown");

            assignments.getAssessmentName().click();//Click on class2 assignment
            Thread.sleep(2000);
            //Assignment Details page should appear for class2
            CustomAssert.assertEquals(assignmentDetails.labels_pageHeader.get(1).getText(),"Student Responses","Verify Assignment Details page","Assignment details page is appearing after selecting the assignment","Assignment details page is not appearing after selecting the assignment");
            CustomAssert.assertEquals(assignmentDetails.name_assignment.getText(),class2Assignment,"Verify class2 assignment name on assignment details page","Class2 assignment name is appearing on assignment details page as expected","Class2 assignment name is not appearing on assignment details page");

            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            studentResponse.releaseGrade.click();//Click on release grade for all student(s) button

            new Navigator().navigateTo("assignment");//Navigate to assignment
            assignments.dropdown_defaultClassName.click();//Click on class drop down
            Thread.sleep(2000);
            for(int i=0;i<assignments.dropdown_classNames.size();i++)
            {
                if(assignments.dropdown_classNames.get(i).getText().equals(className3))
                {
                    System.out.println("i"+i+assignments.dropdown_classNames.get(i));
                    System.out.println(i+"className3");
                    assignments.dropdown_classNames.get(i).click();//Click on class3
                }
            }
            assignments.viewResponse.get(0).click();//Click on view response

            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student

            //Verify the status of class2Assignment and class3assignment
            CustomAssert.assertEquals(studentDashboard.status_assignments.get(1).getText(),"Grade Released","Verify the status and order of class2Assignment ","Class2Assignment status is displayed as 'Graded' on 2nd number after releasing grade as expected","Class2Assignment status is not displayed as 'Graded' after releasing grade");
            CustomAssert.assertEquals(studentDashboard.status_assignments.get(2).getText(),"Grade Released","Verify the status and order of class3Assignment ","Class3Assignment status is displayed as 'Reviewed' on 3rd number after releasing feedback as expected","Class3Assignment status is not displayed as 'Graded' after releasing feedback");
            new Navigator().logout();//log out student

            new Login().loginAsInstructor(appendChar,dataIndex);

            new Navigator().navigateTo("assignment");//Navigate to assignment
            Thread.sleep(2000);
            assignments.dropdown_defaultClassName.click();//Click on class drop down

            for(int i=0;i<assignments.dropdown_classNames.size();i++)
            {
                if(assignments.dropdown_classNames.get(i).getText().equals(className2))
                {
                    assignments.dropdown_classNames.get(i).click();//Click on class2
                }
            }

        }catch(Exception e){
            Assert.fail("Exception in 'studentAndInstructorDashboardVerification' Class in method 'studentDashboardVerification'", e);
        }

    }


    @Test(priority = 2,enabled = true)
    public void classOrderVerificationWithoutAssignment(){
        WebDriver driver=Driver.getWebDriver();
        try{
            ReportUtil.log("Description","This test case validates the order of classes (no assignments) on student dashboard","info");


            int dataIndex = 250;
            String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            //String appendChar = "a";

            String appendCharacterBuild="";
            if (System.getProperty("UCHAR")!=null)
                appendCharacterBuild=System.getProperty("UCHAR");

            ManageClass manageClass = PageFactory.initElements(driver, ManageClass.class);
            com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.StudentDashboard studentDashboard = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.StudentDashboard.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);

            String methodName = new Exception().getStackTrace()[0].getMethodName();

            String  className1 = methodName+"class"+appendChar+appendCharacterBuild;
            String  className2 = methodName+"class"+"class2"+appendCharacterBuild;
            String  className3 = methodName+"class"+"class3"+appendCharacterBuild;

            new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode = new Classes().createClass(appendChar, dataIndex);//Create class1

            //Create class2
            String classCode2 = new Classes().createNewClass("class2",dataIndex,"Grade 2","Mathematics","Math - Common Core");//Create a grade2 class

            //Create class3
            String classCode3 = new Classes().createNewClass("class3",dataIndex,"Grade 3","Mathematics","Math - Common Core");//Create a grade3 class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode2,dataIndex);//Sign up as a student in class2(Grade2)

            //Join class1
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.getNewClass().click();//Click on new class
            // studentDashboard.button_joinAClass.click();//Click on join button
            manageClass.enterClassCodeWebElement().sendKeys(classCode);//Join class1 (Grade1)
            manageClass.getSaveButton().click();//Click on join class

            //Join class3
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.getNewClass().click();//Click on new class
            manageClass.enterClassCodeWebElement().sendKeys(classCode3);//Join class3 (Grade3)
            manageClass.buttonJoinClass().click();//Click on join class

            new Navigator().navigateTo("dashboard");//Navigate to dashboard

            //Verify the order of the class name
            CustomAssert.assertEquals(studentDashboard.name_classes.get(0).getText(),className3,"Verify the order of class","Classname 3  is displayed on the top as expected","Classname 3  is not displayed on the top as expected");
            CustomAssert.assertEquals(studentDashboard.name_classes.get(1).getText(),className1,"Verify the order of class","Classname 1 is displayed in the middle as expected","Classname 1 is not displayed in the middle as expected");
            CustomAssert.assertEquals(studentDashboard.name_classes.get(2).getText(),className2,"Verify the order of class","Classname 2 is displayed on the bottom as expected","Classname 2 is not displayed on the bottom as expected");

            //Verify the order of the Class Code
            CustomAssert.assertTrue(studentDashboard.list_classCodes.get(0).getText().contains(classCode3),"Verify the order of class code","Classcode 3 is displayed on the top as expected","Classcode 3 is not displayed on the top as expected");
            CustomAssert.assertTrue(studentDashboard.list_classCodes.get(1).getText().contains(classCode), "Verify the order of class code","Classcode 1 is displayed in the middle as expected","Classcode 1 is not displayed in the middle as expected");
            CustomAssert.assertTrue(studentDashboard.list_classCodes.get(2).getText().contains(classCode2), "Verify the order of class code","Classcode 2 is displayed on the bottom as expected","Classcode 2 is not displayed on the bottom as expected");

        }catch (Exception e){
            Assert.fail("Exception in 'classOrderVerificationWithoutAssignment' Class in method 'studentDashboardVerification'", e);
        }
    }


}
