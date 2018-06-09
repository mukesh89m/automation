package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.sanity;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentLibrary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.detailedResponse.DetailedResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.soap.Detail;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by priyanka on 1index/3/indexindex14.
 */
public class AssignmentFlow extends Driver {

    @Test(priority = 1, enabled = true)
    public void teacherCreatesAssignmentGradeable() {
        try {
            String appendChar = "a";
            int index=1;

            InstructorDashboard instructorDashboard = PageFactory.initElements(driver,InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver, DetailedResponse.class);

            new SignUp().teacher(appendChar, index); //signup as a teacher
            new School().createWithOnlyName(appendChar, index); //createWithOnlyName school
            String classCode = new Classes().createClass(appendChar,index);//createWithOnlyName class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//createWithOnlyName student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index); //login as instructor
            new Assignment().create(index,"all");
            new Assignment().assignToStudent(index,appendChar);
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index); //login as instructor
            new Navigator().navigateTo("assignment");
            instructorDashboard.getButton_ViewResponses().click();//Click on view response
            studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(0).click();//Click on Q6(manually graded question)

            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/*//*[@height='46']")));
            detailedResponse.scorePerQuestion.get(0).sendKeys("1");//Enter score as 1
            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            Thread.sleep(2000);

            studentResponse.nextArrow_performanceQuestions.click();//Click on next arrow
            studentResponse.nextArrow_performanceQuestions.click();//Again click on next arrow
            studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.get(0).click();//Click on Q28(manually graded question)
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/*//*[@height='68']")));
            detailedResponse.scorePerQuestion.get(0).sendKeys("1");//Enter score as 1
            detailedResponse.detailedResponseBackArrow.click();//Click on back arrow
            Thread.sleep(2000);

            new Assignment().releaseGrades(index," Release Grade");
            List<Integer> status = new Assignment().allStatusBoxCount(index);
            //VALIDATING THE STATUS BOX COUNTS
            if (status.get(0) != 1 || status.get(1) != 1 || status.get(2) != 1) {
                Assert.fail("Status box counts not equals 1,1,1");
            }

            String statusname = new Assignment().assignmentStatus(index);
            Thread.sleep(2000);
            Assert.assertEquals(statusname.trim(),"Graded","Graded is not appearing as a status");
            Thread.sleep(5000);

        } catch (Exception e) {
            Assert.fail("Exception in testcase teacherCreatesAssignment in class AssignmentFlow",e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void teacherCreatesAssignmentNonGradeAble() {
        try {
            String appendChar = "i";
            int index=2;
            new SignUp().teacher(appendChar, index); //signup as a teacher
            new School().createWithOnlyName(appendChar, index); //createWithOnlyName school
            String classCode = new Classes().createClass(appendChar,index);//createWithOnlyName class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//createWithOnlyName student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index); //login as instructor
            new Assignment().create(index,"expressionevaluator");
            new Assignment().assignToStudent(index,appendChar);
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, index);//log in as student
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar, index); //login as instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment
            String statusname3 = new Assignment().assignmentStatus(index);
           //Status should display as 'Grading in Progress' as per e10 changes (Discussed with Ashish)
            Assert.assertEquals(statusname3.trim(),"Grading in Progress","Grading in Progress  is not appearing as a status");

            new Assignment().releaseGrades(index, "Release Feedback");
            String statusName4 = new Assignment().assignmentStatus(index);
            Assert.assertEquals(statusName4, "Graded", "Graded is not appearing as a status");
            List<Integer> statusbox = new Assignment().allStatusBoxCount(index);
            if (statusbox.get(0) !=1 || statusbox.get(1)!=1 || statusbox.get(index)!= 1){
                Assert.fail("Statusbox count is not equal to 1,1,1");
            }
        }
        catch (Exception e) {
            Assert.fail("Exception in testcase teacherCreatesAssignment in class AssignmentFlow",e);
        }
    }

    @Test(priority =3 ,enabled = true)
    public void existingAssignmentFlowForGradable(){

        try {
            String appendChar = "i";
            int index=4;
            new SignUp().teacher(appendChar, index); //signup as a teacher
            new School().createWithOnlyName(appendChar, index); //createWithOnlyName school
            String classCode = new Classes().createClass(appendChar,index);//createWithOnlyName class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//createWithOnlyName student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index); //login as instructor
            new Assignment().selectExistingAssignment();
            new Assignment().assignExistingAssignmentToStudent(index,appendChar);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar, index);//log in as student
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar, index); //login as instructor
            String statusBeforeReleasingGrade = new Assignment().assignmentStatus(index);
            Thread.sleep(2000);
            Assert.assertEquals(statusBeforeReleasingGrade.trim(),"Grading in Progress","Grading in Progress  is not appearing as a status");
            new Assignment().releaseGrades(index, "Release Grade");

            String statusAfterReleasingGrade = new Assignment().assignmentStatus(index);
            Thread.sleep(5000);
            Assert.assertEquals(statusAfterReleasingGrade.trim(),"Graded","Graded is not appearing as a status");


        } catch (Exception e) {
            Assert.fail(" Exception in testcase existingAssignmentFlowForGradable in class AssignmentFlow");
        }
    }

    @Test(priority =4 ,enabled = true)
    public void existingAssignmentFlowNonGradeAble() {
        try {
            String appendChar = "i";
            int index = 5;
            new SignUp().teacher(appendChar, index); //signup as a teacher
            new School().createWithOnlyName(appendChar, index); //createWithOnlyName school
            String classCode = new Classes().createClass(appendChar, index);//createWithOnlyName class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//createWithOnlyName student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index); //login as instructor
            new Assignment().selectExistingAssignment();
            new Assignment().assignExistingAssignmentToStudent(index, appendChar);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar, index); //login as instructor
            String statusBeforeReleasingGradeNonGradeAble = new Assignment().assignmentStatus(index);
            Thread.sleep(2000);
            Assert.assertEquals(statusBeforeReleasingGradeNonGradeAble.trim(), "Grading in Progress", "Review in Progress  is not appearing as a status");
            new Assignment().releaseGrades(index, "Release Feedback");

            String statusAfterReleasingGradeNonGradeAble = new Assignment().assignmentStatus(index);
            Thread.sleep(5000);
            Assert.assertEquals(statusAfterReleasingGradeNonGradeAble.trim(), "Graded", "Reviewed is not appearing as a status");


        } catch (Exception e) {
            Assert.fail(" Exception in testcase existingAssignmentFlowNonGradeAble in class AssignmentFlow");

        }
    }
     @Test(priority =5 ,enabled = true)
    public void existingUserGradeableAssignment() {
        try {
            String instructorEmail = "atuomation.test@at.com";
            int index=6;

            new Login().directLoginAsInstructor(index,instructorEmail); //login as instructor
            new Assignment().create(index,"all");
            new Assignment().assignToStudent(index);
            new Navigator().logout();//log out

            //Attempt assessment by all the students
            for(int i=1;i<16; i++) {
                String studentEmail = "at.student"+i+"@at.com";
                new Login().directLoginAsStudent(index, studentEmail);//log in as student
                new Assignment().submitAssignment(32);//submit the assignment
                new Navigator().logout();//log out
            }

            String studentEmail1 = "sa11";
            new Login().directLoginAsStudent(index,studentEmail1);//log in as student
            new Assignment().submitAssignment(32);//submit the assignment
            new Navigator().logout();//log out

            new Login().directLoginAsInstructor(index,instructorEmail); //login as instructor
            new Navigator().navigateTo("assignment");
            List<Integer> status = new Assignment().allStatusBoxCount(index);
            //VALIDATING THE STATUS BOX COUNTS
            if (status.get(0) != 16 || status.get(1) != 16 || status.get(2) != 16) {
                Assert.fail("Status box counts not equals 16,16,16");
            }

            String statusname = new Assignment().assignmentStatus(index);
            Thread.sleep(2000);

            Assert.assertEquals(statusname.trim(),"Grading in Progress","Grading in Progress  is not appearing as a status");
            new Assignment().releaseGrades(index,"Release Grade");
            String statusname1 = new Assignment().assignmentStatus(index);
            Thread.sleep(5000);
            Assert.assertEquals(statusname1.trim(),"Graded","Graded is not appearing as a status");

        } catch (Exception e) {
            Assert.fail("Exception in testcase teacherCreatesAssignment in class AssignmentFlow");
        }


    }

    @Test(priority = 6, enabled = true)
    public void existingUserNonGradeAbleAssignment() {
        try {
            int index=7;

            String instructorEmail = "atuomation.test@at.com";
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));

            new Login().directLoginAsInstructor(index,instructorEmail); //login as instructor
            new Assignment().create(index,"all");
            new Assignment().assignToStudent(index);
            new Navigator().logout();//log out

            //Attempt assessment by all the students
            for(int i=1;i<16; i++) {
                String studentEmail = "at.student"+i+"@at.com";
                System.out.println("i "+i);
                new Login().directLoginAsStudent(index, studentEmail);//log in as student
                new Assignment().submitAssignment(32);//submit the assignment
                new Navigator().logout();//log out
            }

            String studentEmail1 = "sa11";
            new Login().directLoginAsStudent(index,studentEmail1);//log in as student
            new Assignment().submitAssignment(32);//submit the assignment
            new Navigator().logout();//log out

            new Login().directLoginAsInstructor(index,instructorEmail); //login as instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment
            String statusname3 = new Assignment().assignmentStatus(index);

            Assert.assertEquals(statusname3.trim(),"Review in Progress","Review in Progress  is not appearing as a status");

            new Assignment().releaseGrades(index, " Release Feedback");
            new Navigator().navigateTo("assignment");//Navigate to assignment
            String statusName4 = new Assignment().assignmentStatus(index);
            Assert.assertEquals(statusName4, "Reviewed", "Reviewed is not appearing as a status");
            new Navigator().navigateTo("assignment");//Navigate to assignment
            List<Integer> statusbox = new Assignment().allStatusBoxCount(index);
            if (statusbox.get(0) != 16 || statusbox.get(1)!=16 || statusbox.get(2)!= 16){
                Assert.fail("Statusbox count is not equal to 16,16,16");
            }
        }
        catch (Exception e) {
            Assert.fail("Exception in testcase teacherCreatesAssignment in class AssignmentFlow",e);
        }

    }

    @Test(priority =7 ,enabled = true)
    public void existingUserExistingAssignmentFlowForGradable(){

        try {
            int index=8;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);
            
            String instructorEmail = "atuomation.test@at.com";

            new Login().directLoginAsInstructor(index,instructorEmail); //login as instructor
            new Assignment().selectExistingAssignment();
            assessmentLibrary.getSelectbox_grade().click();//Click on grade box
            assessmentLibrary.list_grades.get(1).click();//Select grade1

            new Assignment().assignExistingAssignmentToStudent(index);
            String assignmentName = new TextFetch().textfetchbyclass("edu-green");
            new Navigator().logout();//log out

            //Attempt the assessment by all the students
            for(int i =1; i<16; i++)
            {
                String studentEmail = "at.student"+i+"@at.com";
                new Login().directLoginAsStudent(index,studentEmail);//log in as student
                new Assignment().submitAssignment(32);//submit the assignment
                new Navigator().logout();//log out
            }

            String studentEmail1 = "sa11";
            new Login().directLoginAsStudent(index,studentEmail1);//log in as student
            new Assignment().submitAssignment(32);//submit the assignment
            new Navigator().logout();//log out

            new Login().directLoginAsInstructor(index,instructorEmail); //login as instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment
            List<WebElement> allAssignment = driver.findElements(By.cssSelector("h4.as-title"));
            List<WebElement> allDueDate = driver.findElements(By.cssSelector("div[class='col-xs-12 col-sm-5']"));
            int index1 = 0;
            for(WebElement assignment : allAssignment)
            {
                int lastLocOfSpace=0;
                String actualAssignmentname;

                if(assignment.getText().contains("GRADABLE"))
                {
                    lastLocOfSpace = assignment.getText().lastIndexOf(" ");
                    actualAssignmentname=assignment.getText().substring(6,lastLocOfSpace).trim();
                }
                else
                {
                    actualAssignmentname=assignment.getText().substring(6).trim();
                }

                if (actualAssignmentname.equals(assignmentName) && allDueDate.get(index1).getText().contains("12:20 PM"))
                {
                    break;
                } else
                {
                    index1++;
                }
            }
            new Navigator().navigateTo("assignment");//Navigate to assignment
            String statusBeforeReleasingGrade = new TextFetch().textfetchbylistclass("status-label", index1);//get the status
            Thread.sleep(2000);
            Assert.assertEquals(statusBeforeReleasingGrade.trim(),"Grading in Progress","Grading in Progress  is not appearing as a status");

            new Assignment().releaseGrades(index1, "Release Grade");

            List<WebElement> allAssignmentAfterGradeRelease = driver.findElements(By.cssSelector("h4.as-title"));
            List<WebElement> allDueDateAfterGradeRelease = driver.findElements(By.cssSelector("div[class='col-xs-12 col-sm-5']"));
            int index2 = 0;
            for(WebElement assignmentAfterGradeRelease : allAssignmentAfterGradeRelease)
            {
                int lastLocOfSpaceAfterGradeRelease=0;
                String actualAssignmentnameAfterGradeRelease;

                if(assignmentAfterGradeRelease.getText().contains("GRADABLE"))
                {
                    lastLocOfSpaceAfterGradeRelease = assignmentAfterGradeRelease.getText().lastIndexOf(" ");
                    actualAssignmentnameAfterGradeRelease=assignmentAfterGradeRelease.getText().substring(6,lastLocOfSpaceAfterGradeRelease).trim();
                }
                else
                {
                    actualAssignmentnameAfterGradeRelease=assignmentAfterGradeRelease.getText().substring(6).trim();
                }

                if (actualAssignmentnameAfterGradeRelease.equals(assignmentName) && allDueDateAfterGradeRelease.get(index2).getText().contains("12:20 PM"))
                {
                    break;
                } else
                {
                    index2++;
                }
            }
            new Navigator().navigateTo("assignment");//Navigate to assignment
            String statusAfterReleasingGrade = new TextFetch().textfetchbylistclass("status-label", index2);//get the status
            Thread.sleep(5000);

            Assert.assertEquals(statusAfterReleasingGrade.trim(),"Graded","Graded is not appearing as a status");

        } catch (Exception e) {
            Assert.fail(" Exception in testcase existingAssignmentFlowForGradable in class AssignmentFlow");
        }
    }

    @Test(priority =8 ,enabled = true)
    public void existingUserExistingAssignmentFlowNonGradeAble() {
        try {
            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);

            int index = 9;
            String instructorEmail = "atuomation.test@at.com";
            new Login().directLoginAsInstructor(index,instructorEmail); //login as instructor
            new Assignment().selectExistingAssignment();
            assessmentLibrary.getSelectbox_grade().click();//Click on grade box
            assessmentLibrary.list_grades.get(1).click();//Select grade1
            new Assignment().assignExistingAssignmentToStudent(index);
            String assignmentName = new TextFetch().textfetchbyclass("edu-green");
            new Navigator().logout();//log out

            //Attempt the assessment by all the students
            for(int i =1; i<16; i++)
            {
                String studentEmail = "at.student"+i+"@at.com";
                new Login().directLoginAsStudent(index,studentEmail);//log in as student
                new Assignment().submitAssignment(index);//submit the assignment
                new Navigator().logout();//log out
            }

            String studentEmail1 = "sa11";
            new Login().directLoginAsStudent(index,studentEmail1);//log in as student
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();//log out
            new Login().directLoginAsInstructor(index,instructorEmail); //login as instructor
            List<WebElement> allAssignment = driver.findElements(By.cssSelector("h4.as-title"));
            List<WebElement> allDueDate = driver.findElements(By.cssSelector("div[class='col-xs-12 col-sm-5']"));
            int index1 = 0;
            for(WebElement assignment : allAssignment)
            {
                int lastLocOfSpace=0;
                String actualAssignmentname;

                if(assignment.getText().contains("GRADABLE"))
                {
                    lastLocOfSpace = assignment.getText().lastIndexOf(" ");
                    actualAssignmentname=assignment.getText().substring(6,lastLocOfSpace).trim();
                }
                else
                {
                    actualAssignmentname=assignment.getText().substring(6).trim();
                }

                if (actualAssignmentname.equals(assignmentName) && allDueDate.get(index1).getText().contains("12:15 PM"))
                {
                    break;
                } else
                {
                    index1++;
                }
            }
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            String statusBeforeReleasingGradeNonGradeAble = new TextFetch().textfetchbylistclass("status-label", index1);//get the status;
            Thread.sleep(2000);
            Assert.assertEquals(statusBeforeReleasingGradeNonGradeAble.trim(), "Grading in Progress", "Grading in Progress  is not appearing as a status");
            new Assignment().releaseGrades(index1, "Release Feedback");

            List<WebElement> allAssignmentAfterGradeRelease = driver.findElements(By.cssSelector("h4.as-title"));
            List<WebElement> allDueDateAfterGradeRelease = driver.findElements(By.cssSelector("div[class='col-xs-12 col-sm-5']"));
            int index2 = 0;
            for(WebElement assignmentAfterGradeRelease : allAssignmentAfterGradeRelease)
            {
                int lastLocOfSpaceAfterGradeRelease=0;
                String actualAssignmentnameAfterGradeRelease;

                if(assignmentAfterGradeRelease.getText().contains("GRADABLE"))
                {
                    lastLocOfSpaceAfterGradeRelease = assignmentAfterGradeRelease.getText().lastIndexOf(" ");
                    actualAssignmentnameAfterGradeRelease=assignmentAfterGradeRelease.getText().substring(6,lastLocOfSpaceAfterGradeRelease).trim();
                }
                else
                {
                    actualAssignmentnameAfterGradeRelease=assignmentAfterGradeRelease.getText().substring(6).trim();
                }

                if (actualAssignmentnameAfterGradeRelease.equals(assignmentName) && allDueDateAfterGradeRelease.get(index2).getText().contains("12:15 PM"))
                {
                    break;
                } else
                {
                    index2++;
                }
            }
            new Navigator().navigateTo("assignment");//Navigate to assignment
            String statusAfterReleasingGradeNonGradeAble = new TextFetch().textfetchbylistclass("status-label", index2);//get the status
            Thread.sleep(5000);
            Assert.assertEquals(statusAfterReleasingGradeNonGradeAble.trim(), "Graded", "Graded is not appearing as a status");

        } catch (Exception e) {
            Assert.fail(" Exception in testcase existingAssignmentFlowNonGradeAble in class AssignmentFlow");

        }
    }

    @Test(priority =9 ,enabled = true)
    public void statusDueDateExceedNonGradableNotAttemptedByAllStudents(){
       try {
           String appendchar = "a";
           String appendchar1 = "b";
           int index = 10;

           new SignUp().teacher(appendchar, index);//Sign up as a teacher
           new School().createWithOnlyName(appendchar, index); //createWithOnlyName school
           String classCode = new Classes().createClass(appendchar, index);//create class
           new Navigator().logout();//log out

           new SignUp().studentDirectRegister(appendchar, classCode, index);//create student1
           new Navigator().logout();//log out

           new SignUp().studentDirectRegister(appendchar1, classCode, index);//create student2
           new Navigator().logout();//log out

           new Login().loginAsInstructor(appendchar, index);//Login as an instructor
           new Assignment().create(index, "truefalse");//Create an assignment
           new Assignment().assignToStudent(index, appendchar);//Assign to class
           new Navigator().logout();//Log out

           new Login().loginAsStudent(appendchar, index);//Login as student1
           new Assignment().submitAssignment(index);//Submit assignment
           new Navigator().logout();//Log out

           Thread.sleep(360000);

           new Login().loginAsInstructor(appendchar,index);//Login as an instructor
           String status = new Assignment().assignmentStatus(index);

           //Status should be 'Review in Progress'(As per e10 status should be 'Grading in Progress')
           Assert.assertEquals(status,"Grading in Progress","Status is not displayed as expected");

       }catch (Exception e){
           Assert.fail(" Exception in testcase 'statusDueDateExceedNonGradableNotAttemptedByAllStudents' in class 'AssignmentFlow'");

       }
    }


    @Test(priority =10 ,enabled = true)
    public void statusDueDateExceedNonGradableNotAttemptedByAnyStudents() {
        try{
            String appendchar = "a3";
            String appendchar1 = "b3";

            int index = 11;

            new SignUp().teacher(appendchar, index);//Sign up as a teacher
            new School().createWithOnlyName(appendchar, index); //createWithOnlyName school
            String classCode = new Classes().createClass(appendchar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendchar, classCode, index);//create student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendchar1, classCode, index);//create student2
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendchar, index);//Login as an instructor
            new Assignment().create(index, "truefalse");//Create an assignment
            new Assignment().assignToStudent(index, appendchar);//Assign to class
            new Navigator().logout();//Log out

            Thread.sleep(360000);

            new Login().loginAsInstructor(appendchar,index);//Login as an instructor
            String status = new Assignment().assignmentStatus(index);

            //Status should be 'Review in Progress'(As per e10 status should be 'Grading in Progress')
            Assert.assertEquals(status,"Grading in Progress","Status is not displayed as expected");

        }catch (Exception e){
            Assert.fail(" Exception in testcase 'statusDueDateExceedNonGradableNotAttemptedByAnyStudents' in class 'AssignmentFlow'");

        }
    }

}
