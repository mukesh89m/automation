package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.quickassessmentview;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pragya on 18-02-2015.
 */
public class QuickAssessmentView extends Driver {

    @Test(priority =1 ,enabled = true)
     public void quickAssessmentView(){
        try{
            MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver,AssignmentReview.class);
            String appendChar = "a";
            int dataIndex = 1;
            int dataIndex1=2;
            int dataIndex2=3;
            int dataIndex3=4;
            int dataIndex4=5;
            int dataIndex5=6;
            int dataIndex6=7;
            int dataIndex7=8;
            int dataIndex8=9;
            int dataIndex9=10;
            int dataIndex10=11;
            int dataIndex11=12;
            int dataIndex12=13;
            int dataIndex13=14;
            int dataIndex14=15;
            int dataIndex15=16;
            int dataIndex16=17;
            int dataIndex17=18;

            String assessmentname1 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String assessmentname2 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex1));
            String assessmentname3 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex2));
            String assessmentname4 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex3));
            String assessmentname5 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex4));
            String assessmentname6 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex5));
            String assessmentname7 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex6));
            String assessmentname8 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex7));
            String assessmentname9 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex8));
            String assessmentname10 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex9));
            String assessmentname11 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex10));
            String assessmentname12 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex11));
            String assessmentname13 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex12));
            String assessmentname14 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex13));
            String assessmentname15 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex14));
            String assessmentname16 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex15));
            String assessmentname17 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex16));
            String assessmentname18 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex17));

            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//logout

            new SignUp().studentDirectRegister(appendChar, classCode, dataIndex);//create a student
            new Navigator().logout();//logout

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
           
            new Assignment().create(dataIndex,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);
            new Assignment().create(dataIndex1,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex1,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);

            new Assignment().create(dataIndex2,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex2,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);

            new Assignment().create(dataIndex3,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex3,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);

            new Assignment().create(dataIndex4,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex4,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);

            new Assignment().create(dataIndex5,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex5,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);

            new Assignment().create(dataIndex6,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex6,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);

            new Assignment().create(dataIndex7,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex7,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);

            new Assignment().create(dataIndex8,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex8,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);

            new Assignment().create(dataIndex9,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex9,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);

            new Assignment().create(dataIndex10,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex10,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);

            new Assignment().create(dataIndex11,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex11,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);

            new Assignment().create(dataIndex12,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex12,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);

            new Assignment().create(dataIndex13,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex13,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);

            new Assignment().create(dataIndex14,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex14,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);

            new Assignment().create(dataIndex15,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex15,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);

            new Assignment().create(dataIndex16,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex16,appendChar);//Assign to class
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            Thread.sleep(2000);

            new Assignment().create(dataIndex17,"truefalse");//Create true false question
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex17,appendChar);//Assign to class
            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar, dataIndex);//Login as a student
            Thread.sleep(2000);
            new Assignment().submitAssignment(30);//Submit assignment
            assessments.getButton_continue().click();//Click on Continue button
            new Navigator().navigateTo("assignment");//Navigate to assignment

            new Assignment().submitAssignment(31);//Submit assignment
            assessments.getButton_continue().click();//Click on Continue button
            new Navigator().navigateTo("assignment");//Navigate to assignment

            new Assignment().submitAssignment(32);//Submit assignment
            assessments.getButton_continue().click();//Click on Continue button
            new Navigator().navigateTo("assignment");//Navigate to assignment

            new Assignment().submitAssignment(33);//Submit assignment
            assessments.getButton_continue().click();//Click on Continue button
            new Navigator().navigateTo("assignment");//Navigate to assignment

            new Assignment().submitAssignment(34);//Submit assignment
            assessments.getButton_continue().click();//Click on Continue button
            new Navigator().navigateTo("assignment");//Navigate to assignment

            new Assignment().submitAssignment(35);//Submit assignment
            assessments.getButton_continue().click();//Click on Continue button
            new Navigator().navigateTo("assignment");//Navigate to assignment

            new Assignment().submitAssignment(36);//Submit assignment
            assessments.getButton_continue().click();//Click on Continue button
            new Navigator().navigateTo("assignment");//Navigate to assignment

            new Assignment().submitAssignment(37);//Submit assignment
            assessments.getButton_continue().click();//Click on Continue button
            new Navigator().navigateTo("assignment");//Navigate to assignment

            new Assignment().submitAssignment(38);//Submit assignment
            assessments.getButton_continue().click();//Click on Continue button
            new Navigator().navigateTo("assignment");//Navigate to assignment

            new Assignment().submitAssignment(39);//Submit assignment
            assessments.getButton_continue().click();//Click on Continue button
            new Navigator().navigateTo("assignment");//Navigate to assignment

            new Assignment().submitAssignment(40);//Submit assignment
            assessments.getButton_continue().click();//Click on Continue button
            new Navigator().navigateTo("assignment");//Navigate to assignment

            new Assignment().submitAssignment(41);//Submit assignment
            assessments.getButton_continue().click();//Click on Continue button
            new Navigator().navigateTo("assignment");//Navigate to assignment

            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor

            new Navigator().navigateTo("assignment");//Navigate to assignment
            new Assignment().releaseGrades(39, " Release Feedback for All");//Click on Release feedback to all

            new Navigator().navigateTo("assignment");//Navigate to assignment
            new Assignment().releaseGrades(40, " Release Feedback for All");//Click on Relesae feedback to all

            new Navigator().navigateTo("assignment");//Navigate to assignment
            new Assignment().releaseGrades(41, " Release Feedback for All");//Click on Relesae feedback to all

            new Navigator().navigateTo("assignment");//Navigate to assignment

            //Verify the ordering of all the assessments based on due date and status in instructor's side

            //Verify ordering of assessment
            Assert.assertEquals(assignments.getList_assessmentName().get(0).getText().substring(6).trim(),assessmentname4,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(1).getText().substring(6).trim(),assessmentname7,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(2).getText().substring(6).trim(),assessmentname5,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(3).getText().substring(6).trim(),assessmentname8,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(4).getText().substring(6).trim(),assessmentname6,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(5).getText().substring(6).trim(),assessmentname9,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(6).getText().substring(6).trim(),assessmentname1,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(7).getText().substring(6).trim(),assessmentname2,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(8).getText().substring(6).trim(),assessmentname3,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(9).getText().substring(6).trim(),assessmentname16,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(10).getText().substring(6).trim(),assessmentname17,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(11).getText().substring(6).trim(),assessmentname18,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(12).getText().substring(6).trim(),assessmentname10,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(13).getText().substring(6).trim(),assessmentname13,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(14).getText().substring(6).trim(),assessmentname11,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(15).getText().substring(6).trim(),assessmentname14,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(16).getText().substring(6).trim(),assessmentname12,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(17).getText().substring(6).trim(),assessmentname15,"Assessment ordering is not correct");

            //Verify ordering of status
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(0).getText().substring(8).trim(),"Grading in Progress","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(1).getText().substring(8).trim(),"Review in Progress","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(2).getText().substring(8).trim(),"Grading in Progress","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(3).getText().substring(8).trim(),"Review in Progress","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(4).getText().substring(8).trim(),"Grading in Progress","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(5).getText().substring(8).trim(),"Review in Progress","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(6).getText().substring(8).trim(),"Awaiting Submission","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(7).getText().substring(8).trim(),"Awaiting Submission","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(8).getText().substring(8).trim(),"Awaiting Submission","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(9).getText().substring(8).trim(),"Scheduled","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(10).getText().substring(8).trim(),"Scheduled","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(11).getText().substring(8).trim(),"Scheduled","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(12).getText().substring(8).trim(),"Graded","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(13).getText().substring(8).trim(),"Reviewed","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(14).getText().substring(8).trim(),"Graded","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(15).getText().substring(8).trim(),"Reviewed","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(16).getText().substring(8).trim(),"Graded","status is not correct");
            Assert.assertEquals(assessmentReview.getList_assessmentstatus().get(17).getText().substring(8).trim(),"Reviewed","status is not correct");

            //Verifying ordering of Due date
            Assert.assertEquals(assessmentReview.getList_duedate().get(0).getText(),"March 26, 12:05 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(1).getText(),"March 26, 12:05 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(2).getText(),"March 26, 12:10 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(3).getText(),"March 26, 12:10 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(4).getText(),"March 28, 12:15 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(5).getText(),"March 28, 12:15 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(6).getText(),"March 26, 12:05 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(7).getText(),"March 26, 12:10 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(8).getText(),"March 28, 12:15 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(9).getText(),"March 26, 12:05 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(10).getText(),"March 26, 12:10 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(11).getText(),"March 28, 12:15 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(12).getText(),"March 26, 12:05 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(13).getText(),"March 26, 12:05 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(14).getText(),"March 26, 12:10 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(15).getText(),"March 26, 12:10 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(16).getText(),"March 28, 12:15 AM","Due date is not correct");
            Assert.assertEquals(assessmentReview.getList_duedate().get(17).getText(),"March 28, 12:15 AM","Due date is not correct");

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to assessment page

            assignments.getList_assessmentName().get(0).click();//Select assignment 2 on the top
            assessments.getButton_exit().click();//Click on exit button

            //Verify the ordering of all the assessments based on due date and status in student's side

            //Verify ordering of assessments
         /*   Assert.assertEquals(assignments.getList_assessmentName().get(0).getText().substring(6).trim(),assessmentname4,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(1).getText().substring(6).trim(),assessmentname7,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(2).getText().substring(6).trim(),assessmentname5,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(3).getText().substring(6).trim(),assessmentname8,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(4).getText().substring(6).trim(),assessmentname6,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(5).getText().substring(6).trim(),assessmentname9,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(6).getText().substring(6).trim(),assessmentname1,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(7).getText().substring(6).trim(),assessmentname2,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(8).getText().substring(6).trim(),assessmentname3,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(9).getText().substring(6).trim(),assessmentname16,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(10).getText().substring(6).trim(),assessmentname17,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(11).getText().substring(6).trim(),assessmentname18,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(12).getText().substring(6).trim(),assessmentname10,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(13).getText().substring(6).trim(),assessmentname13,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(14).getText().substring(6).trim(),assessmentname11,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(15).getText().substring(6).trim(),assessmentname14,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(16).getText().substring(6).trim(),assessmentname12,"Assessment ordering is not correct");
            Assert.assertEquals(assignments.getList_assessmentName().get(17).getText().substring(6).trim(),assessmentname15,"Assessment ordering is not correct");
*/



        }catch (Exception e)
    {
        Assert.fail("Exception in testcase 'quickAssessmentView' in class 'QuickAssessmentView'", e);

    }

    }
}
