package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.reportchanges;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.reports.MyReports;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.ViewResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Created by pragya on 28-07-2015.
 */
public class ReportChanges extends Driver{

    @Test(priority = 1)
    public void MyReportsViewAfterCreatingAssessmentGradable(){

        try{
            int dataIndex = 15;
            String appendChar1 = "a";
            String appendChar2 = "b";

            MyReports MyReports = PageFactory.initElements(driver,MyReports.class);

            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Navigator().navigateTo("myReports");//Navigate to report

            //Expected -  Report page should be opened
            Assert.assertEquals(MyReports.titleName.getText(),"My Reports","Report page is not opened");

            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"truefalse");//Create a true false question

            boolean draftStatusOnAssignment = new BooleanValue().presenceOfElement(dataIndex, By.cssSelector("span.as-assignment-draftTxt"));
            //View Draft Status should not appear under assignment page
            Assert.assertEquals(draftStatusOnAssignment,false,"'view draft status is displayed under assignment page'");

            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class
            new Navigator().navigateTo("myReports");//Navigate to report


            //Expected - Summary details should show Number of standards Mastered and Number of standards Assessed ( 0 in this case)
            //Expected - Number of standars in the Summary view should be the Total number of ELOs that are associated to particular Combination of Grade and Subject
            Assert.assertEquals(MyReports.standards.get(0).getText(),"0 out of 21","Standard mastered is not displayed as 0");
            Assert.assertEquals(MyReports.standards.get(1).getText(),"STANDARDS MASTERED","'STANDARDS MASTERED' text is not displayed");
            Assert.assertEquals(MyReports.standards.get(2).getText(),"0 out of 21","Standard assessed is not displayed as 0");
            Assert.assertEquals(MyReports.standards.get(3).getText(),"STANDARDS ASSESSED","'STANDARDS ASSESSED' text is not displayed");

            //Expected - Tabular details should have 3 Different views, 3 Radio buttons should be displayed with the Following label
           //a. Mastery
           //b. Score
           //c. Percent Score
           Assert.assertEquals(MyReports.labels_viewBy.get(0).getText(),"Mastery","Mastery radio button is not displayed");
           Assert.assertEquals(MyReports.labels_viewBy.get(1).getText(),"Score","Score radio button is not displayed");
           Assert.assertEquals(MyReports.labels_viewBy.get(2).getText(),"Percent","Percent radio button is not displayed");

           MyReports.viewByMastery.click();//Click on mastery radio button

           //Expected - Tabular details should show the Mastery details per standard and per student wise (Default values in this case)
            int tabularSizeMaster = MyReports.tabularValue_master.size();
            for(int i=1;i<26;i++){
                if(MyReports.tabularValue_master.get(i).isDisplayed()) {
                    if (!MyReports.tabularValue_master.get(i).getText().contains("0 out of 2")) {
                        Assert.fail("Tabular value is not displayed as expected");
                    }
                }
            }

            for(int i=28;i<tabularSizeMaster-1;i++){
                if(MyReports.tabularValue_master.get(i).isDisplayed()) {
                    if (!MyReports.tabularValue_master.get(i).getText().contains("Not Assessed")) {
                        Assert.fail("Tabular value is not displayed as expected");
                    }
                }
            }

            MyReports.viewByScore.click();//Click on score radio button

            Assert.assertEquals(MyReports.tabularValue_score.get(0).getText(),"0","0 is not displayed in 1st row and 1st column as expected under view by score");

            int tabularSizeScore = MyReports.tabularValue_score.size();
            for(int i=1;i<tabularSizeScore-1;i++){
                if(MyReports.tabularValue_score.get(i).isDisplayed()) {
                    if (!MyReports.tabularValue_score.get(i).getText().equals("-")) {
                        Assert.fail("Tabular value is not displayed as expected");
                    }
                }
            }

            MyReports.viewByPercent.click();//Click on percent radio button

            int tabularSizePercent = MyReports.tabularValue_score.size();
            for(int i=1;i<tabularSizePercent-1;i++){
                if(MyReports.tabularValue_percent.get(i).isDisplayed()) {
                    if (!MyReports.tabularValue_percent.get(i).getText().equals("-")) {
                        Assert.fail("Tabular value is not displayed as expected");
                    }
                }
            }

        }catch (Exception e){
            Assert.fail("Exception in 'ReportChanges' in method 'MyReportsViewAfterCreatingAssessmentGradable'", e);

    }

    }


    @Test(priority = 2)
    public void MyReportsViewAfterCreatingAssessmentNonGradable(){
        try{
            int dataIndex = 86;
            String appendChar1 = "a";
            String appendChar2 = "b";

            MyReports MyReports = PageFactory.initElements(driver,MyReports.class);

            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"truefalse");//Create a true false question
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class*/
            new Navigator().navigateTo("myReports");//Navigate to report

            //Expected - Reports page should be shown only with default Values as none on the students have started the assignment
            int tabularSizeMaster = MyReports.tabularValue_master.size();
            for(int i=1;i<26;i++){
                if(MyReports.tabularValue_master.get(i).isDisplayed()) {
                    if (!MyReports.tabularValue_master.get(i).getText().contains("0 out of 2")) {
                        Assert.fail("Tabular value is not displayed as expected");
                    }
                }
            }

            for(int i=28;i<tabularSizeMaster-1;i++){
                if(MyReports.tabularValue_master.get(i).isDisplayed()) {
                    if (!MyReports.tabularValue_master.get(i).getText().contains("Not Assessed")) {
                        Assert.fail("Tabular value is not displayed as expected");
                    }
                }
            }

            MyReports.viewByScore.click();//Click on score radio button

            Assert.assertEquals(MyReports.tabularValue_score.get(0).getText(),"0","0 is not displayed in 1st row and 1st column as expected under view by score");

            int tabularSizeScore = MyReports.tabularValue_score.size();
            for(int i=1;i<tabularSizeScore-1;i++){
                if(MyReports.tabularValue_score.get(i).isDisplayed()) {
                    if (!MyReports.tabularValue_score.get(i).getText().equals("-")) {
                        Assert.fail("Tabular value is not displayed as expected");
                    }
                }
            }

            MyReports.viewByPercent.click();//Click on percent radio button

            int tabularSizePercent = MyReports.tabularValue_score.size();
            for(int i=1;i<tabularSizePercent-1;i++){
                if(MyReports.tabularValue_percent.get(i).isDisplayed()) {
                    if (!MyReports.tabularValue_percent.get(i).getText().equals("-")) {
                        Assert.fail("Tabular value is not displayed as expected");
                    }
                }
            }

        }catch (Exception e){
            Assert.fail("Exception in 'ReportChanges' in method 'MyReportsViewAfterCreatingAssessmentNonGradable'", e);

        }
    }



    @Test(priority = 3)
    public void MyReportsViewAfterStudentAttemptNonGradable(){
        try{
            int dataIndex = 188;
            String appendChar1 = "a";
            String appendChar2 = "b";

            MyReports MyReports = PageFactory.initElements(driver,MyReports.class);

            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"truefalse");//Create a true false question
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);//login as a student1
            driver.navigate().refresh();
            new Assignment().submitAssignment(dataIndex);//submit assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Navigator().navigateTo("myReports");//Navigate to report

            //Expected - By default mastery radio button should be selected
            Assert.assertEquals(MyReports.viewByMastery.getAttribute("class"),"iradio_square-green checked","By default percent radio button is not selected");

            //Expected - The Summary view of "Standards assessed" should be updated based on the standards the Assignment covers
            Assert.assertEquals(MyReports.standards.get(2).getText(),"0 out of 21","'Standards assessed' is not updated as expected");

            MyReports.viewByMastery.click();//Click on master radio  button

            MyReports.expandIcon.get(3).click();//Click on 1.OA expand icon

            //Expected - The Mastery View Should show if the Student has mastered a Particular Standard Based on the Score he procured
            Assert.assertEquals(MyReports.masteryByEachStudent.get(0).getText(),"1 out of 2","Class average is not displayed as expected under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(1).getText(),"Mastered","Student1 value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(2).getText(),"Not Assessed","Student2 value is not displayed as Not Assessed under 1.OA");

            MyReports.viewByScore.click();//Click on score radio button

            //Expected - Points scored by the student in a particular standard out of total assigned points for that standard rounded off to nearest second decimal should be displayed
            Assert.assertEquals(MyReports.scoreByEachStudent.get(0).getText(),"1.00","Score is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(1).getText(),"1/1","Score is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(2).getText(),"-","Score is not displayed for student 2 as expected under 1.O.A");

            MyReports.viewByPercent.click();//Click on percent radio button

            //Expected - Raw score that the student has scored should be displayed in Terms of Percentage
            Assert.assertEquals(MyReports.percentByEachStudent.get(0).getText(),"100%","Percentage is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(1).getText(),"100%","Percentage is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(2).getText(),"-","Percentage is not displayed for student 2 as expected under 1.O.A");

        }catch (Exception e){
            Assert.fail("Exception in 'ReportChanges' in method 'MyReportsViewAfterStudentAttemptNonGradable'", e);

        }
    }

    @Test(priority = 4)
    public void MyReportsViewAfterStudentAttemptGradable(){
        try{
            int dataIndex = 254;
            String appendChar1 = "a";
            String appendChar2 = "b";

            MyReports MyReports = PageFactory.initElements(driver,MyReports.class);

            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"truefalse");//Create a true false question
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);//login as a student1
            new Assignment().submitAssignment(dataIndex);//submit assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Navigator().navigateTo("myReports");//Navigate to report

            //Expected - By default mastery radio button should be selected
            Assert.assertEquals(MyReports.viewByMastery.getAttribute("class"),"iradio_square-green checked","By default percent radio button is not selected");

            //Expected - The Summary view of "Standards assessed" should be updated based on the standards the Assignment covers
            Assert.assertEquals(MyReports.standards.get(2).getText(),"0 out of 21","'Standards assessed' is not updated as expected");

            MyReports.viewByMastery.click();//Click on master radio  button

            MyReports.expandIcon.get(3).click();//Expand 1.OA
            //Expected - The Mastery View Should show if the Student has mastered a Particular Standard Based on the Score he procured
            Assert.assertEquals(MyReports.masteryByEachStudent.get(0).getText(),"1 out of 2","Class average is not displayed as expected under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(1).getText(),"Mastered","Student1 value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(2).getText(),"Not Assessed","Student2 value is not displayed as Not Assessed under 1.OA");

            MyReports.viewByScore.click();//Click on score radio button

            //Expected - Points scored by the student in a particular standard out of total assigned points for that standard rounded off to nearest second decimal should be displayed
            Assert.assertEquals(MyReports.scoreByEachStudent.get(0).getText(),"1.00","Score is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(1).getText(),"1/1","Score is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(2).getText(),"-","Score is not displayed for student 2 as expected under 1.O.A");

            MyReports.viewByPercent.click();//Click on percent radio button

            //Expected - Raw score that the student has scored should be displayed in Terms of Percentage
            Assert.assertEquals(MyReports.percentByEachStudent.get(0).getText(),"100%","Percentage is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(1).getText(),"100%","Percentage is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(2).getText(),"-","Percentage is not displayed for student 2 as expected under 1.O.A");


        }catch (Exception e){
            Assert.fail("Exception in 'ReportChanges' in method 'MyReportsViewAfterStudentAttemptGradable'", e);

        }

    }


    @Test(priority = 21,enabled = true)
    public void MyReportsViewAfterStudentAttemptGradableExplicitlyByTeacher(){
        try{
            int dataIndex = 349;
            String appendChar1 = "a1";
            String appendChar2 = "b1";

            MyReports MyReports = PageFactory.initElements(driver,MyReports.class);
            InstructorDashboard instructorDashboard = PageFactory.initElements(driver,InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);

            new SignUp().teacher(appendChar1, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"truefalse");//Create a true false question
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);//login as a student1
            new Assignment().submitAssignment(dataIndex);//submit assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar2,dataIndex);//login as a student2
            new Assignment().submitAssignment(350);//submit assignment with incorrect answer
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Navigator().navigateTo("myReports");//Navigate to report

            //Expected - By default mastery radio button should be selected
            Assert.assertEquals(MyReports.viewByMastery.getAttribute("class"),"iradio_square-green checked","By default percent radio button is not selected");

            //Expected - The Summary view of "Standards assessed" should be updated based on the standards the Assignment covers
            Assert.assertEquals(MyReports.standards.get(2).getText(),"0 out of 21","'Standards assessed' is not updated as expected");

            MyReports.viewByMastery.click();//Click on master radio  button

            MyReports.expandIcon.get(3).click();//Expand 1.OA
            //Expected - The Mastery View Should show if the Student has mastered a Particular Standard Based on the Score he procured
            Assert.assertEquals(MyReports.masteryByEachStudent.get(0).getText(),"0 out of 2","Class average is not displayed as expected under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(1).getText(),"Not Assessed","Student1 value is not displayed as expected under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(2).getText(),"Not Assessed","Student2 value is not displayed as Not Assessed under 1.OA");

            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            instructorDashboard.viewResponseonDashboard().click();//Click on view response
            studentResponse.releaseGrade.click();//Click on release grade
            new Navigator().navigateTo("myReports");//Navigate to report
            MyReports.expandIcon.get(3).click();//Expand 1.OA

            //Expected - The Mastery View Should show if the Student has mastered a Particular Standard Based on the Score he procured
            Assert.assertEquals(MyReports.masteryByEachStudent.get(0).getText(),"1 out of 2","Class average is not displayed as expected under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(1).getText(),"Mastered","Student1 value is not displayed as expected under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(2).getText(),"Not Mastered","Student2 value is not displayed as Not Assessed under 1.OA");

            MyReports.viewByScore.click();//Click on score radio button

            //Expected - Points scored by the student in a particular standard out of total assigned points for that standard rounded off to nearest second decimal should be displayed
            Assert.assertEquals(MyReports.scoreByEachStudent.get(0).getText(),"0.50","Score is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(1).getText(),"1/1","Score is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(2).getText(),"0/1","Score is not displayed for student 2 as expected under 1.O.A");

            MyReports.viewByPercent.click();//Click on percent radio button

            //Expected - Raw score that the student has scored should be displayed in Terms of Percentage
            Assert.assertEquals(MyReports.percentByEachStudent.get(0).getText(),"50%","Percentage is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(1).getText(),"100%","Percentage is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(2).getText(),"0%","Percentage is not displayed for student 2 as expected under 1.O.A");


        }catch (Exception e){
            Assert.fail("Exception in 'ReportChanges' in method 'MyReportsViewAfterStudentAttemptGradableExplicitlyByTeacher'", e);

        }

    }



    @Test(priority = 5)
    public void MyReportsViewAfterStudentAttemptMoreThan75NonGradable(){
        try{
            int dataIndex = 323;
            String appendChar1 = "a";
            String appendChar2 = "b";

            MyReports MyReports = PageFactory.initElements(driver,MyReports.class);

            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex, "truefalse");//Create a true false question
            for(int i=1;i<10;i++) {
                new Assignment().addQuestion(dataIndex, "truefalse");//add 10 true false question
            }
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);//login as a student1
            new Assignment().submitAssignmentWithMixResponse(dataIndex,8,10);//Submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Navigator().navigateTo("myReports");//Navigate to report

            //Expected -  The Summary view of "Standards assessed" should be updated based on the standards the Assignment covers
            Assert.assertEquals(MyReports.standards.get(2).getText(),"2 out of 21","'Standards assessed' is not updated as expected");
            Assert.assertEquals(MyReports.standards.get(0).getText(),"0 out of 21","'Standards mastered' is updated");

            //Expected - Tabular details should be updated for the Student Who has submitted the Assignment
            Assert.assertEquals(MyReports.percentByEachStudent.get(0).getText(),"80%","Percentage is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(1).getText(),"80%","Percentage is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(2).getText(),"-","Percentage is not displayed for student 2 as expected under 1.O.A");

            MyReports.viewByMastery.click();//Click on mastery radio button

            Assert.assertEquals(MyReports.masteryByEachStudent.get(0).getText(),"Mastered","Class average is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(1).getText(),"Mastered","Student1 value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(2).getText(),"Not Assessed","Student2 value is not displayed as Not Assessed under 1.OA");

            MyReports.viewByScore.click();//Click on score radio button

            Assert.assertEquals(MyReports.scoreByEachStudent.get(0).getText(),"8.00","Score is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(1).getText(),"8/10","Score is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(2).getText(),"-","Score is not displayed for student 2 as expected under 1.O.A");

            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar2,dataIndex);//login as a student2
            new Assignment().submitAssignmentWithMixResponse(dataIndex,8,10);//Submit the assignment
            new Navigator().logout();//logout

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Navigator().navigateTo("myReports");//Navigate to report

            //Expected - The Summary view should be updated ; "Standards mastered" count should be incremented taking the Average score of the class for That particular standard (ELO)
            Assert.assertEquals(MyReports.standards.get(0).getText(),"2 out of 21","'Standards mastered' is not updated");

            //Expected - "Standards assessed" should be incremented if the assessment has been created for any New Standard(ELO)
            Assert.assertEquals(MyReports.standards.get(2).getText(),"2 out of 21","'Standards assessed' is not displayed as expected");


            MyReports.viewByMastery.click();//Click on mastery radio button

            //Expected - The Tabular details for 'Mastery' should be updated for both the students for corresponding standard with Green Color background and a Tick Mark
            Assert.assertEquals(MyReports.masteryByEachStudent.get(0).getText(),"Mastered","Class average value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(1).getText(),"Mastered","Student1 value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(2).getText(),"Mastered","Student2 value is not displayed as Not Assessed under 1.OA");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery color on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery color on Student1");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery color on Student2");

            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student1");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student2");

            MyReports.viewByScore.click();//click on score radio button

            //Expected - Raw score should be displayed for Both the students with points scored out of total assigned points
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("8.00"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("8/10"),"Not displaying proper score on Student1");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("8/10"),"Not displaying proper score on Student2");

            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score color on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score color on Student1");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score color on Student2");

            //click on Percent
            MyReports.viewByPercent.click();
            //check percentage
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("80%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("80%"),"Not displaying proper percent on Student1");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("80%"),"Not displaying proper percent on Student2");

            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student1");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student2");

        }catch (Exception e){
            Assert.fail("Exception in 'ReportChanges' in method 'MyReportsViewAfterStudentAttemptMoreThan75NonGradable'", e);

        }
    }



    @Test(priority = 6)
    public void MyReportsViewAfterStudentAttemptMoreThan75Gradable(){
        try{
            int dataIndex = 440;
            String appendChar1 = "a";
            String appendChar2 = "b";

            MyReports MyReports = PageFactory.initElements(driver,MyReports.class);

            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex, "truefalse");//Create a true false question
            for(int i=1;i<10;i++) {
                new Assignment().addQuestion(dataIndex, "truefalse");//add 10 true false question
            }
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);//login as a student1
            new Assignment().submitAssignmentWithMixResponse(dataIndex,8,10);//Submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Navigator().navigateTo("myReports");//Navigate to report

            //Expected -  The Summary view of "Standards assessed" should be updated based on the standards the Assignment covers
            Assert.assertEquals(MyReports.standards.get(2).getText(),"0 out of 21","'Standards assessed' is not updated as expected");
            Assert.assertEquals(MyReports.standards.get(0).getText(),"0 out of 21","'Standards mastered' is updated");

            //Expected - Tabular details should be updated for the Student Who has submitted the Assignment
            Assert.assertEquals(MyReports.percentByEachStudent.get(0).getText(),"-","Percentage is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(1).getText(),"-","Percentage is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(2).getText(),"-","Percentage is not displayed for student 2 as expected under 1.O.A");

            MyReports.viewByMastery.click();//Click on mastery radio button

            Assert.assertEquals(MyReports.masteryByEachStudent.get(0).getText(),"Not Assessed","Class average is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(1).getText(),"Not Assessed","Student1 value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(2).getText(),"Not Assessed","Student2 value is not displayed as Not Assessed under 1.OA");

            MyReports.viewByScore.click();//Click on score radio button

            Assert.assertEquals(MyReports.scoreByEachStudent.get(0).getText(),"-","Score is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(1).getText(),"-","Score is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(2).getText(),"-","Score is not displayed for student 2 as expected under 1.O.A");

            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar2,dataIndex);//login as a student2
            new Assignment().submitAssignmentWithMixResponse(dataIndex,8,10);//Submit the assignment with 8 correct answer
            new Navigator().logout();//logout

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Navigator().navigateTo("myReports");//Navigate to report

            //Expected - The Summary view should be updated ; "Standards mastered" count should be incremented taking the Average score of the class for That particular standard (ELO)
            Assert.assertEquals(MyReports.standards.get(0).getText(),"2 out of 21","'Standards mastered' is not updated");

            //Expected - "Standards assessed" should be incremented if the assessment has been created for any New Standard(ELO)
            Assert.assertEquals(MyReports.standards.get(2).getText(),"2 out of 21","'Standards assessed' is not displayed as expected");

            MyReports.viewByMastery.click();//Click on mastery radio button

            //Expected - The Tabular details for 'Mastery' should be updated for both the students for corresponding standard with Green Color background and a Tick Mark
            Assert.assertEquals(MyReports.masteryByEachStudent.get(0).getText(),"Mastered","Class average value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(1).getText(),"Mastered","Student1 value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(2).getText(),"Mastered","Student2 value is not displayed as Not Assessed under 1.OA");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student1");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student2");

            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student1");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student2");

            MyReports.viewByScore.click();//click on score radio button

            //Expected - Raw score should be displayed for Both the students with points scored out of total assigned points
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("8.00"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("8/10"),"Not displaying proper score on Student1");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("8/10"),"Not displaying proper score on Student2");

            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score color on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score color on Student1");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score color on Student2");

            //click on Percent
            MyReports.viewByPercent.click();
            //check percentage
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("80%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("80%"),"Not displaying proper percent on Student1");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("80%"),"Not displaying proper percent on Student2");

            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent color on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent color on Student1");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent color on Student2");

        }catch (Exception e){
            Assert.fail("Exception in 'ReportChanges' in method 'MyReportsViewAfterStudentAttemptMoreThan75NonGradable'", e);

        }
    }


    @Test(priority = 7)
    public void MyReportsViewAfterStudentAttemptbetween60To75NonGradable(){
        try{
            int dataIndex = 550;
            String appendChar1 = "a";
            String appendChar2 = "b";

            MyReports MyReports = PageFactory.initElements(driver,MyReports.class);

            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex, "truefalse");//Create a true false question
            for(int i=1;i<10;i++) {
                new Assignment().addQuestion(dataIndex, "truefalse");//add 10 true false question
            }
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);//login as a student1
            new Assignment().submitAssignmentWithMixResponse(dataIndex,6,10);//Submit the assignment with 6 correct answer
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Navigator().navigateTo("myReports");//Navigate to report

           //Expected - "Standards assessed" should be incremented if the assessment has been created for any New Standard(ELO)
            Assert.assertEquals(MyReports.standards.get(2).getText(),"2 out of 21","'Standards assessed' is not updated as expected");

            //Expected - Tabular details should be updated for the Student Who has submitted the Assignment
            Assert.assertEquals(MyReports.percentByEachStudent.get(0).getText(),"60%","Percentage is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(1).getText(),"60%","Percentage is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(2).getText(),"-","Percentage is not displayed for student 2 as expected under 1.O.A");

            MyReports.viewByMastery.click();//Click on mastery radio button

            Assert.assertEquals(MyReports.masteryByEachStudent.get(0).getText(),"Nearly Mastered","Class average is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(1).getText(),"Nearly Mastered","Student1 value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(2).getText(),"Not Assessed","Student2 value is not displayed as Not Assessed under 1.OA");

            //Expected -  Mastery for that student should be shown with 'x' mark and Yellow background indicating that the student has not mastered the Standard
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery color on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery color on Student1");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("greyGrade"),"Not displaying proper mastery color on Student2");

            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student1");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-minus"),"Not displaying proper Check mark on Student2");

            MyReports.viewByScore.click();//Click on score radio button

            Assert.assertEquals(MyReports.scoreByEachStudent.get(0).getText(),"6.00","Score is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(1).getText(),"6/10","Score is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(2).getText(),"-","Score is not displayed for student 2 as expected under 1.O.A");

            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar2,dataIndex);//login as a student1
            new Assignment().submitAssignmentWithMixResponse(dataIndex,7,10);//Submit the assignment with 6 correct answer
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Navigator().navigateTo("myReports");//Navigate to report

            //Expected -  Standards mastered should not be incremented as the Students average for this Standard Does not cross 75%
            Assert.assertEquals(MyReports.standards.get(0).getText(),"0 out of 21","'Standards mastered' is incremented");

            MyReports.viewByMastery.click();//Click on mastery radio button

            //Expected - The Tabular details for 'Mastery' should be updated for both the students for corresponding standard with Green Color background and a Tick Mark
            Assert.assertEquals(MyReports.masteryByEachStudent.get(0).getText(),"Nearly Mastered","Class average value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(1).getText(),"Nearly Mastered","Student1 value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(2).getText(),"Nearly Mastered","Student2 value is not displayed as Not Assessed under 1.OA");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student1");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student2");

            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student1");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student2");

            MyReports.viewByScore.click();//click on score radio button

            //Expected - Raw score should be displayed for Both the students with points scored out of total assigned points
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("6.50"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("6/10"),"Not displaying proper score on Student1");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("7/10"),"Not displaying proper score on Student2");

            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score color on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score color on Student1");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score color on Student2");

            //click on Percent
            MyReports.viewByPercent.click();
            //check percentage
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("65%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("60%"),"Not displaying proper percent on Student1");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("70%"),"Not displaying proper percent on Student2");

            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent color on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent color on Student1");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent color on Student2");


        }catch (Exception e){
            Assert.fail("Exception in 'ReportChanges' in method 'MyReportsViewAfterStudentAttemptbetween60To75NonGradable'", e);

        }
    }


    @Test(priority = 8)
    public void MyReportsViewAfterStudentAttemptbetween60To75Gradable(){
        try{
            int dataIndex = 677;
            String appendChar1 = "a";
            String appendChar2 = "b";

            MyReports MyReports = PageFactory.initElements(driver,MyReports.class);

            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex, "truefalse");//Create a true false question
            for(int i=1;i<10;i++) {
                new Assignment().addQuestion(dataIndex, "truefalse");//add 10 true false question
            }
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);//login as a student1
            new Assignment().submitAssignmentWithMixResponse(dataIndex,6,10);//Submit the assignment with 6 correct answer
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Navigator().navigateTo("myReports");//Navigate to report

            //Expected - "Standards assessed" should be incremented if the assessment has been created for any New Standard(ELO)
            Assert.assertEquals(MyReports.standards.get(2).getText(),"0 out of 21","'Standards assessed' is not updated as expected");

            //Expected - Tabular details should be updated for the Student Who has submitted the Assignment
            Assert.assertEquals(MyReports.percentByEachStudent.get(0).getText(),"-","Percentage is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(1).getText(),"-","Percentage is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(2).getText(),"-","Percentage is not displayed for student 2 as expected under 1.O.A");

            MyReports.viewByMastery.click();//Click on mastery radio button

            Assert.assertEquals(MyReports.masteryByEachStudent.get(0).getText(),"Not Assessed","Class average is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(1).getText(),"Not Assessed","Student1 value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(2).getText(),"Not Assessed","Student2 value is not displayed as Not Assessed under 1.OA");

            //Expected -  Mastery for that student should be shown with 'x' mark and Yellow background indicating that the student has not mastered the Standard
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("greyGrade"),"Not displaying proper mastery color on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("greyGrade"),"Not displaying proper mastery color on Student1");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("greyGrade"),"Not displaying proper mastery color on Student2");

            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-minus"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-minus"),"Not displaying proper Check mark on Student1");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-minus"),"Not displaying proper Check mark on Student2");

            MyReports.viewByScore.click();//Click on score radio button

            Assert.assertEquals(MyReports.scoreByEachStudent.get(0).getText(),"-","Score is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(1).getText(),"-","Score is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(2).getText(),"-","Score is not displayed for student 2 as expected under 1.O.A");

            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar2,dataIndex);//login as a student1
            new Assignment().submitAssignmentWithMixResponse(dataIndex,7,10);//Submit the assignment with 6 correct answer
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Navigator().navigateTo("myReports");//Navigate to report

            //Expected -  Standards mastered should not be incremented as the Students average for this Standard Does not cross 75%
            Assert.assertEquals(MyReports.standards.get(0).getText(),"0 out of 21","'Standards mastered' is incremented");

            MyReports.viewByMastery.click();//Click on mastery radio button

            //Expected - The Tabular details for 'Mastery' should be updated for both the students for corresponding standard with Green Color background and a Tick Mark
            Assert.assertEquals(MyReports.masteryByEachStudent.get(0).getText(),"Nearly Mastered","Class average value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(1).getText(),"Nearly Mastered","Student1 value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(2).getText(),"Nearly Mastered","Student2 value is not displayed as Not Assessed under 1.OA");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student1");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student2");

            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student1");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student2");

            MyReports.viewByScore.click();//click on score radio button

            //Expected - Raw score should be displayed for Both the students with points scored out of total assigned points
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("6.50"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("6/10"),"Not displaying proper score on Student1");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("7/10"),"Not displaying proper score on Student2");

            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score color on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score color on Student1");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score color on Student2");

            //click on Percent
            MyReports.viewByPercent.click();
            //check percentage
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("65%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("60%"),"Not displaying proper percent on Student1");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("70%"),"Not displaying proper percent on Student2");

            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent color on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent color on Student1");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent color on Student2");


        }catch (Exception e){
            Assert.fail("Exception in 'ReportChanges' in method 'MyReportsViewAfterStudentAttemptbetween60To75Gradable'", e);

        }
    }


    @Test(priority = 9)
    public void MyReportsViewAfterStudentAttemptbelow60NonGradable(){
        try{
            int dataIndex = 820;
            String appendChar1 = "a3";
            String appendChar2 = "b3";

            MyReports MyReports = PageFactory.initElements(driver,MyReports.class);


            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//Log out


           new Login().loginAsInstructor(appendChar1, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex, "truefalse");//Create a true false question
            for(int i=1;i<10;i++) {
                new Assignment().addQuestion(dataIndex, "truefalse");//add 10 true false question
            }
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);//login as a student1
            new Assignment().submitAssignmentWithMixResponse(dataIndex,6,10);//Submit the assignment with 6 correct answer
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Navigator().navigateTo("myReports");//Navigate to report

            //Expected - "Standards assessed" should be incremented if the assessment has been created for any New Standard(ELO)
            Assert.assertEquals(MyReports.standards.get(2).getText(),"2 out of 21","'Standards assessed' is not updated as expected");

            //Expected - Tabular details should be updated for the Student Who has submitted the Assignment
            Assert.assertEquals(MyReports.percentByEachStudent.get(0).getText(),"60%","Percentage is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(1).getText(),"60%","Percentage is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(2).getText(),"-","Percentage is not displayed for student 2 as expected under 1.O.A");

            MyReports.viewByMastery.click();//Click on mastery radio button

            Assert.assertEquals(MyReports.masteryByEachStudent.get(0).getText(),"Nearly Mastered","Class average is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(1).getText(),"Nearly Mastered","Student1 value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(2).getText(),"Not Assessed","Student2 value is not displayed as Not Assessed under 1.OA");

           //Expected -  3. Mastery for that student should be shown with '!' mark and Red background indicating that the student needs remidiation
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery color on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery color on Student1");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("greyGrade"),"Not displaying proper mastery color on Student2");

            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student1");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-minus"),"Not displaying proper Check mark on Student2");

            MyReports.viewByScore.click();//Click on score radio button

            Assert.assertEquals(MyReports.scoreByEachStudent.get(0).getText(),"6.00","Score is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(1).getText(),"6/10","Score is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(2).getText(),"-","Score is not displayed for student 2 as expected under 1.O.A");

            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar2,dataIndex);//login as a student1
            new Assignment().submitAssignmentWithMixResponse(dataIndex,7,10);//Submit the assignment with 6 correct answer
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Navigator().navigateTo("myReports");//Navigate to report

            //Expected -  Standards mastered should not be incremented as the Students average for this Standard Does not cross 75%
            Assert.assertEquals(MyReports.standards.get(0).getText(),"0 out of 21","'Standards mastered' is incremented");

            MyReports.viewByMastery.click();//Click on mastery radio button

            //Expected - The Tabular details for 'Mastery' should be updated for both the students for corresponding standard with Green Color background and a Tick Mark
            Assert.assertEquals(MyReports.masteryByEachStudent.get(0).getText(),"Nearly Mastered","Class average value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(1).getText(),"Nearly Mastered","Student1 value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(2).getText(),"Nearly Mastered","Student2 value is not displayed as Not Assessed under 1.OA");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student1");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student2");

            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student1");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student2");

            MyReports.viewByScore.click();//click on score radio button

            //Expected - Raw score should be displayed for Both the students with points scored out of total assigned points
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("6.50"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("6/10"),"Not displaying proper score on Student1");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("7/10"),"Not displaying proper score on Student2");

            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score color on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score color on Student1");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score color on Student2");

            //click on Percent
            MyReports.viewByPercent.click();
            //check percentage
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("65%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("60%"),"Not displaying proper percent on Student1");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("70%"),"Not displaying proper percent on Student2");

            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent color on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent color on Student1");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent color on Student2");

        }catch (Exception e){
            Assert.fail("Exception in 'ReportChanges' in method 'MyReportsViewAfterStudentAttemptbelow60NonGradable'", e);

        }
    }

    @Test(priority = 10)
    public void MyReportsViewAfterStudentAttemptbelow60Gradable(){
        try{
            int dataIndex = 821;
            String appendChar1 = "a3";
            String appendChar2 = "b3";

            MyReports MyReports = PageFactory.initElements(driver,MyReports.class);


            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create school
            String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//Log out


            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex, "truefalse");//Create a true false question
            for(int i=1;i<10;i++) {
                new Assignment().addQuestion(dataIndex, "truefalse");//add 10 true false question
            }
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);//login as a student1
            new Assignment().submitAssignmentWithMixResponse(dataIndex,6,10);//Submit the assignment with 6 correct answer
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Navigator().navigateTo("myReports");//Navigate to report

            //Expected - "Standards assessed" should be incremented if the assessment has been created for any New Standard(ELO)
            Assert.assertEquals(MyReports.standards.get(2).getText(),"2 out of 21","'Standards assessed' is not updated as expected");

            //Expected - Tabular details should be updated for the Student Who has submitted the Assignment
            Assert.assertEquals(MyReports.percentByEachStudent.get(0).getText(),"60%","Percentage is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(1).getText(),"60%","Percentage is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.percentByEachStudent.get(2).getText(),"-","Percentage is not displayed for student 2 as expected under 1.O.A");

            MyReports.viewByMastery.click();//Click on mastery radio button

            Assert.assertEquals(MyReports.masteryByEachStudent.get(0).getText(),"Nearly Mastered","Class average is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(1).getText(),"Nearly Mastered","Student1 value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(2).getText(),"Not Assessed","Student2 value is not displayed as Not Assessed under 1.OA");

            //Expected -  3. Mastery for that student should be shown with '!' mark and Red background indicating that the student needs remidiation
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery color on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery color on Student1");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("greyGrade"),"Not displaying proper mastery color on Student2");

            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student1");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-minus"),"Not displaying proper Check mark on Student2");

            MyReports.viewByScore.click();//Click on score radio button

            Assert.assertEquals(MyReports.scoreByEachStudent.get(0).getText(),"6.00","Score is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(1).getText(),"6/10","Score is not displayed for student 1 as expected under 1.O.A");
            Assert.assertEquals(MyReports.scoreByEachStudent.get(2).getText(),"-","Score is not displayed for student 2 as expected under 1.O.A");

            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar2,dataIndex);//login as a student1
            new Assignment().submitAssignmentWithMixResponse(dataIndex,7,10);//Submit the assignment with 6 correct answer
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as a teacher
            new Navigator().navigateTo("myReports");//Navigate to report

            //Expected -  Standards mastered should not be incremented as the Students average for this Standard Does not cross 75%
            Assert.assertEquals(MyReports.standards.get(0).getText(),"0 out of 21","'Standards mastered' is incremented");

            MyReports.viewByMastery.click();//Click on mastery radio button

            //Expected - The Tabular details for 'Mastery' should be updated for both the students for corresponding standard with Green Color background and a Tick Mark
            Assert.assertEquals(MyReports.masteryByEachStudent.get(0).getText(),"Nearly Mastered","Class average value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(1).getText(),"Nearly Mastered","Student1 value is not displayed as mastered under 1.OA");
            Assert.assertEquals(MyReports.masteryByEachStudent.get(2).getText(),"Nearly Mastered","Student2 value is not displayed as Not Assessed under 1.OA");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student1");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student2");

            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student1");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student2");

            MyReports.viewByScore.click();//click on score radio button

            //Expected - Raw score should be displayed for Both the students with points scored out of total assigned points
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("6.50"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("6/10"),"Not displaying proper score on Student1");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("7/10"),"Not displaying proper score on Student2");

            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score color on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score color on Student1");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score color on Student2");

            //click on Percent
            MyReports.viewByPercent.click();
            //check percentage
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("65%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("60%"),"Not displaying proper percent on Student1");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("70%"),"Not displaying proper percent on Student2");

            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent color on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent color on Student1");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent color on Student2");

        }catch (Exception e){
            Assert.fail("Exception in 'ReportChanges' in method 'MyReportsViewAfterStudentAttemptbelow60NonGradable'", e);

        }
    }

    @Test(priority = 11,enabled = true)
    public void reportInCaseOfNewStandardMastered()
    {
        try
        {
            String appendChar = "aa11";
            String appendChar1 = "ab11";
            String appendChar2 = "ac11";
            int index=51;
            MyReports MyReports= PageFactory.initElements(driver,MyReports.class);
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            ResultSet rs = DBConnect.st.executeQuery("select count(subject) from t_master_skill where grade like '%Grade 1' and taxonomy_id=212 and subject like '%Math%' and parent_id is not null and type='ELO';");
            // System.out.println(rs.);
            rs.next();
            int count=rs.getInt(1);
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar1, classCode, index);//create student2
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar2, classCode, index);//create student3
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index,"truefalse");
            for (int i=0;i<9;i++)
            {
                new Assignment().addQuestion(index,"truefalse");
            }
            new Assignment().assignToStudent(index, appendChar);
            new Navigator().navigateTo("myReports");
            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n", " ").contains("0 out of " + count + " STANDARDS ASSESSED"), "Standard assessed not displaying properly");

            new Navigator().logout();
            //login with first student and attempt 7 of 10 correctly
            new Login().loginAsStudent(appendChar, index);
            new Assignment().submitAssignmentWithMixResponse(index, 7, 10);
            new Navigator().logout();
            //login with Second student and attempt 8 of 10 correctly
            new Login().loginAsStudent(appendChar1, index);
            new Assignment().submitAssignmentWithMixResponse(index, 8, 10);
            new Navigator().logout();
            //login with Second student and attempt 8 of 10 correctly
            new Login().loginAsStudent(appendChar2, index);
            new Assignment().submitAssignmentWithMixResponse(index, 8, 10);
            new Navigator().logout();

            ArrayList subject=new ArrayList();
            while(rs.next()){
                //Retrieve by column name
                subject.add(rs.getInt("subject"));
            }

            System.out.println("count "+subject.size());

            new Login().loginAsInstructor(appendChar,index);
            new Navigator().navigateTo("myReports");
            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("2 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("2 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");


            //select mastery as view by
            MyReports.viewByMastery.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getText().trim().contains("Mastered"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getText().trim().contains("Nearly Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getText().trim().contains("Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(3).getText().trim().contains("Mastered"),"Not displaying proper mastery on Student");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(3).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");
            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(3).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");

            //click on score
            MyReports.viewByScore.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("7.67"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("7/10"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("8/10"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(3).getText().trim().contains("8/10"),"Not displaying proper score on Student");

            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(3).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");

            //click on Percent
            MyReports.viewByPercent.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("77%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("70%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("80%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(3).getText().trim().contains("80%"),"Not displaying proper percent on Student");

            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(3).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in ReportChanges in method reportInCaseOfNewStandardMastered", e);
        }




    }


    @Test(priority =12,enabled = true)
    public void reportInCaseOfNewStandardNotMastered()
    {
        try
        {
            String appendChar = "aa12";
            String appendChar1 = "ab12";
            String appendChar2 = "ac12";
            int index=51;

            MyReports MyReports= PageFactory.initElements(driver, MyReports.class);
            ResultSet rs = DBConnect.st.executeQuery("select count(subject) from t_master_skill where grade like '%Grade 1' and taxonomy_id=212 and subject like '%Math%' and parent_id is not null and type='ELO';");
            rs.next();
            int count = rs.getInt(1);
            new SignUp().teacher(appendChar, index);//Sign up as teacher

            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar1, classCode, index);//create student2
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar2, classCode, index);//create student3
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index,"truefalse");
            for (int i=0;i<9;i++)
            {
                new Assignment().addQuestion(index,"truefalse");
            }
            new Assignment().assignToStudent(index,appendChar);
            new Navigator().navigateTo("myReports");
            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n", " ").contains("0 out of " + count + " STANDARDS MASTERED"), "Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");

            new Navigator().logout();
            //login with first student and attempt 7 of 10 correctly
            new Login().loginAsStudent(appendChar,index);
            new Assignment().submitAssignmentWithMixResponse(index, 6, 10);
            new Navigator().logout();
            //login with Second student and attempt 8 of 10 correctly
            new Login().loginAsStudent(appendChar1,index);
            new Assignment().submitAssignmentWithMixResponse(index, 7, 10);
            new Navigator().logout();
            //login with Second student and attempt 8 of 10 correctly
            new Login().loginAsStudent(appendChar2,index);
            new Assignment().submitAssignmentWithMixResponse(index, 8, 10);
            new Navigator().logout();//count(subject

            // System.out.println(rs.);




            new Login().loginAsInstructor(appendChar,index);
            new Navigator().navigateTo("myReports");

            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("2 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");


            //select mastery as view by
            MyReports.viewByMastery.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getText().trim().contains("Mastered"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getText().trim().contains("Nearly Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getText().trim().contains("Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(3).getText().trim().contains("Mastered"),"Not displaying proper mastery on Student");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(3).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");
            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(3).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");

            //click on score
            MyReports.viewByScore.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("7.00"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("6/10"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("7/10"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(3).getText().trim().contains("8/10"),"Not displaying proper score on Student");

            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(3).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");

            //click on Percent
            MyReports.viewByPercent.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("70%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("60%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("70%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(3).getText().trim().contains("80%"),"Not displaying proper percent on Student");

            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(3).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in ReportChanges in method reportInCaseOfNewStandardNotMastered", e);
        }
    }


    @Test(priority = 13,enabled = true)
    public void reportInCaseOfNewStandardNeedsRemediation()
    {
        try
        {
            String appendChar = "aa13";
            String appendChar1 = "ab13";
            String appendChar2 = "ac13";
            int index=51;
            ResultSet rs = DBConnect.st.executeQuery("select count(subject) from t_master_skill where grade like '%Grade 1' and taxonomy_id=212 and subject like '%Math%' and parent_id is not null and type='ELO';");
            rs.next();
            int count = rs.getInt(1);
            MyReports MyReports= PageFactory.initElements(driver,MyReports.class);
            new SignUp().teacher(appendChar, index);//Sign up as teacher

            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar1, classCode, index);//create student2
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar2, classCode, index);//create student3
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index,"truefalse");
            for (int i=0;i<9;i++)
            {
                new Assignment().addQuestion(index,"truefalse");
            }
            new Assignment().assignToStudent(index,appendChar);
            new Navigator().navigateTo("myReports");
            System.out.println(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," "));
            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of " + count + " STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");

            new Navigator().logout();
            //login with first student and attempt 7 of 10 correctly
            new Login().loginAsStudent(appendChar,index);
            new Assignment().submitAssignmentWithMixResponse(index,5,10);
            new Navigator().logout();
            //login with Second student and attempt 8 of 10 correctly
            new Login().loginAsStudent(appendChar1,index);
            new Assignment().submitAssignmentWithMixResponse(index,5,10);
            new Navigator().logout();
            //login with Second student and attempt 8 of 10 correctly
            new Login().loginAsStudent(appendChar2,index);
            new Assignment().submitAssignmentWithMixResponse(index,6,10);
            new Navigator().logout();//count(subject
            new Login().loginAsInstructor(appendChar,index);
            new Navigator().navigateTo("myReports");

            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("2 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");


            //select mastery as view by
            MyReports.viewByMastery.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getText().trim().equals("Not Mastered"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getText().trim().equals("Not Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getText().trim().equals("Not Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(3).getText().trim().equals("Nearly Mastered"),"Not displaying proper mastery on Student");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("redGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("redGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("redGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(3).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student");
            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-exclamation"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-exclamation"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-exclamation"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(3).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student");

            //click on score
            MyReports.viewByScore.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("5.33"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("5/10"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("5/10"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(3).getText().trim().contains("6/10"),"Not displaying proper score on Student");

            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("redGrade"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("redGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("redGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(3).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score on Student");

            //click on Percent
            MyReports.viewByPercent.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("53%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("50%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("50%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(3).getText().trim().contains("60%"),"Not displaying proper percent on Student");

            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("redGrade"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("redGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("redGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(3).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent on Student");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in ReportChanges in method reportInCaseOfNewStandardNotMastered", e);
        }
    }

    @Test(priority =14,enabled = true)
    public void reportInCaseOfMasteredNotMasteredBefore()
    {
        try
        {
            String appendChar = "aa1";
            String appendChar1 = "ab1";
            String appendChar2 = "ac1";
            int index=51;

            MyReports MyReports= PageFactory.initElements(driver,MyReports.class);
            ResultSet rs = DBConnect.st.executeQuery("select count(subject) from t_master_skill where grade like '%Grade 1' and taxonomy_id=212 and subject like '%Math%' and parent_id is not null and type='ELO';");
            rs.next();
            int count = rs.getInt(1);
            new SignUp().teacher(appendChar, index);//Sign up as teacher

            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar1, classCode, index);//create student2
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar2, classCode, index);//create student3
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index,"truefalse");
            for (int i=0;i<9;i++)
            {
                new Assignment().addQuestion(index,"truefalse");
            }
            new Assignment().assignToStudent(index,appendChar);
            new Navigator().navigateTo("myReports");
            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");

            new Navigator().logout();
            //login with first student and attempt 7 of 10 correctly
            new Login().loginAsStudent(appendChar,index);
            new Assignment().submitAssignmentWithMixResponse(index,6,10);
            new Navigator().logout();
            //login with Second student and attempt 8 of 10 correctly
            new Login().loginAsStudent(appendChar1,index);
            new Assignment().submitAssignmentWithMixResponse(index,7,10);
            new Navigator().logout();
            //login with Second student and attempt 8 of 10 correctly
            new Login().loginAsStudent(appendChar2,index);
            new Assignment().submitAssignmentWithMixResponse(index,8,10);
            new Navigator().logout();//count(subject

            // System.out.println(rs.);




            new Login().loginAsInstructor(appendChar,index);
            new Navigator().navigateTo("myReports");

            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("2 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");


            //select mastery as view by
            MyReports.viewByMastery.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getText().trim().contains("Mastered"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getText().trim().contains("Nearly Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getText().trim().contains("Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(3).getText().trim().contains("Mastered"),"Not displaying proper mastery on Student");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(3).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");
            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(3).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");

            //click on score
            MyReports.viewByScore.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("7.00"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("6/10"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("7/10"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(3).getText().trim().contains("8/10"),"Not displaying proper score on Student");

            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(3).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");

            //click on Percent
            MyReports.viewByPercent.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("70%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("60%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("70%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(3).getText().trim().contains("80%"),"Not displaying proper percent on Student");

            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(3).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");







            new Assignment().create(index,"truefalse");
            new Assignment().assignToStudent(52,appendChar);
            new Navigator().navigateTo("myReports");
            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("2 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");

            new Navigator().logout();
            //login with first student and attempt 7 of 10 correctly
            new Login().loginAsStudent(appendChar,index);
            new Assignment().submitAssignmentWithMixResponse(index,1,1);
            new Navigator().logout();
            //login with Second student and attempt 8 of 10 correctly
            new Login().loginAsStudent(appendChar1,index);
            new Assignment().submitAssignmentWithMixResponse(index,1,1);
            new Navigator().logout();
            //login with Second student and attempt 8 of 10 correctly
            new Login().loginAsStudent(appendChar2,index);
            new Assignment().submitAssignmentWithMixResponse(index,1,1);
            new Navigator().logout();//count(subject

            // System.out.println(rs.);




            new Login().loginAsInstructor(appendChar,index);
            new Navigator().navigateTo("myReports");

            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("2 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");


            //select mastery as view by
            MyReports.viewByMastery.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getText().trim().contains("Nearly Mastered"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getText().trim().contains("Nearly Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getText().trim().contains("Nearly Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(3).getText().trim().contains("Mastered"),"Not displaying proper mastery on Student");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(3).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");
            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(3).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");

            //click on score
            MyReports.viewByScore.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("8.00"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("7/11"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("8/11"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(3).getText().trim().contains("9/11"),"Not displaying proper score on Student");

            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(3).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");

            //click on Percent
            MyReports.viewByPercent.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("73%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("64%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("73%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(3).getText().trim().contains("82%"),"Not displaying proper percent on Student");

            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(3).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");


















        }
        catch (Exception e)
        {
            Assert.fail("Exception in ReportChanges in method reportInCaseOfNewStandardNotMastered", e);
        }
    }
     @Test(priority = 15,enabled = true)
    public void reportViewForCourseOverallField()
    {
        try
        {
            String appendChar = "aa3";
            String appendChar1 = "ab3";
            String appendChar2 = "ac3";
            int index=103;
            ResultSet rs = DBConnect.st.executeQuery("select count(subject) from t_master_skill where grade like '%Grade 1' and taxonomy_id=212 and subject like '%Math%' and parent_id is not null and type='ELO';");
            rs.next();
            int count = rs.getInt(1);
            MyReports MyReports= PageFactory.initElements(driver,MyReports.class);
           new SignUp().teacher(appendChar, index);//Sign up as teacher

            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar1, classCode, index);//create student2
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar2, classCode, index);//create student3
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(103,"truefalse");

            for (int i=0;i<9;i++)
            {
                new Assignment().addQuestion(103,"truefalse");
            }

            new Assignment().create(104,"truefalse");

            for (int i=0;i<9;i++)
            {
                new Assignment().addQuestion(104,"truefalse");
            }

            new Assignment().create(105,"truefalse");

            for (int i=0;i<9;i++)
            {
                new Assignment().addQuestion(105,"truefalse");
            }
            new Assignment().assignToStudent(index,appendChar);
            new Assignment().assignToStudent(104,appendChar);
            new Assignment().assignToStudent(105,appendChar);
            new Navigator().navigateTo("myReports");
            System.out.println(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," "));
            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of " + count + " STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");

            new Navigator().logout();
            //login with first student and attempt 7 of 10 correctly
            new Login().loginAsStudent(appendChar,index);
            new Assignment().submitAssignmentWithMixResponse(index,7,10);
            new Assignment().submitAssignmentWithMixResponse(104,7,10);
            new Assignment().submitAssignmentWithMixResponse(105,9,10);
            new Navigator().logout();
            //login with Second student and attempt 8 of 10 correctly
            new Login().loginAsStudent(appendChar1,index);
            new Assignment().submitAssignmentWithMixResponse(index,7,10);
            new Assignment().submitAssignmentWithMixResponse(104,7,10);
            new Assignment().submitAssignmentWithMixResponse(105,9,10);
            new Navigator().logout();
            //login with Second student and attempt 8 of 10 correctly
            new Login().loginAsStudent(appendChar2,index);
            new Assignment().submitAssignmentWithMixResponse(index,7,10);
            new Assignment().submitAssignmentWithMixResponse(104,7,10);
            new Assignment().submitAssignmentWithMixResponse(105,9,10);
            new Navigator().logout();//count(subject
            new Login().loginAsInstructor(appendChar,index);
            new Navigator().navigateTo("myReports");

            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("1 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("3 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");


            //select mastery as view by
            MyReports.viewByMastery.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getText().trim().equals("Mastered"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getText().trim().equals("Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getText().trim().equals("Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(3).getText().trim().equals("Mastered"),"Not displaying proper mastery on Student");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(3).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");
            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(3).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");

            //click on score
            MyReports.viewByScore.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("23.00"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("23/30"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("23/30"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(3).getText().trim().contains("23/30"),"Not displaying proper score on Student");

            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(3).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");

            //click on Percent
            MyReports.viewByPercent.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("77%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("77%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("77%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(3).getText().trim().contains("77%"),"Not displaying proper percent on Student");

            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(3).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in ReportChanges in method reportInCaseOfNewStandardNotMastered", e);
        }
    }














    @Test(priority =16,enabled = true)
    public void reportViewForSameStandardWithMultiple()
    {
        try
        {
            String appendChar = "aa";
            int index=106;
            ResultSet rs = DBConnect.st.executeQuery("select count(subject) from t_master_skill where grade like '%Grade 1' and taxonomy_id=212 and subject like '%Math%' and parent_id is not null and type='ELO';");
            rs.next();
            int count = rs.getInt(1);
            MyReports MyReports= PageFactory.initElements(driver,MyReports.class);
            new SignUp().teacher(appendChar, index);//Sign up as teacher

            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index,"truefalse");

            for (int i=0;i<9;i++)
            {
                new Assignment().addQuestion(index,"truefalse");
            }

            new Assignment().create(107,"truefalse");

            for (int i=0;i<9;i++)
            {
                new Assignment().addQuestion(107,"truefalse");
            }

            new Assignment().create(108,"truefalse");

            for (int i=0;i<9;i++)
            {
                new Assignment().addQuestion(108,"truefalse");
            }
            new Assignment().assignToStudent(index,appendChar);
            new Assignment().assignToStudent(107,appendChar);
            new Assignment().assignToStudent(108,appendChar);
            new Navigator().navigateTo("myReports");
            System.out.println(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," "));
            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of " + count + " STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");

            new Navigator().logout();
            //login with first student and attempt 7 of 10 correctly
            new Login().loginAsStudent(appendChar,index);
            new Assignment().submitAssignmentWithMixResponse(index,7,10);
            new Assignment().submitAssignmentWithMixResponse(107,7,10);
            new Assignment().submitAssignmentWithMixResponse(108,9,10);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,index);
            new Navigator().navigateTo("myReports");

            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("1 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("1 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");


            //select mastery as view by
            MyReports.viewByMastery.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getText().trim().equals("Mastered"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getText().trim().equals("Mastered"),"Not displaying proper mastery on Student");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");
            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");

            //click on score
            MyReports.viewByScore.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("23.00"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("23/30"),"Not displaying proper score on Student");

            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");

            //click on Percent
            MyReports.viewByPercent.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("77%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("77%"),"Not displaying proper percent on Student");

            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in ReportChanges in method reportInCaseOfNewStandardNotMastered", e);
        }
    }












    @Test(priority = 17,enabled = true)
    public void reportViewForAssessmentFromDifferentClass()
    {
        try
        {

            String appendChar = "aa1";
            String appendChar1 = "ab1";
            int index=110;
            ResultSet rs = DBConnect.st.executeQuery("select count(subject) from t_master_skill where grade like '%Grade 1' and taxonomy_id=212 and subject like '%Math%' and parent_id is not null and type='ELO';");
            rs.next();
            int count = rs.getInt(1);
            MyReports MyReports= PageFactory.initElements(driver,MyReports.class);
            new SignUp().teacher(appendChar, 111);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 111);//create school
            new Classes().createClass(appendChar, 111, "Grade 2", "Mathematics", "Math - Common Core");//create class
            new Assignment().create(111, "truefalse");
            new Assignment().assignToStudent(111, appendChar);
            new Navigator().logout();//log out
            new SignUp().teacher(appendChar1, index);//Sign up as teacher
            new School().createWithOnlyName(appendChar1, index);//create school
            String classCode = new Classes().createClass(appendChar1, index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar1, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar1, index);//log in as teacher
            new Assignment().useExistingAssignment(index, appendChar1);
            new Navigator().navigateTo("myReports");
            System.out.println(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," "));
            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of " + count + " STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");

            new Navigator().logout();
            //login with first student and attempt 7 of 10 correctly

            new Login().loginAsStudent(appendChar1,index);
            new Assignment().submitAssignment(index);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar1,index);
            new Navigator().navigateTo("myReports");
            MyReports.selectionBox.get(1).click();
            MyReports.grade2.click();
            Thread.sleep(10000);


            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");


            //select mastery as view by
            MyReports.viewByMastery.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.masteryByEachStudentGrade2.get(0).getText().trim().equals("Mastered"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudentGrade2.get(1).getText().trim().equals("Mastered"),"Not displaying proper mastery on Student");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudentGrade2.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudentGrade2.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");
            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMasteryGrade2.get(0).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMasteryGrade2.get(1).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");

            //click on score
            MyReports.viewByScore.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.scoreByEachStudentGrade2.get(0).getText().trim().contains("1.00"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudentGrade2.get(1).getText().trim().contains("1/1"),"Not displaying proper score on Student");

            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudentGrade2.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudentGrade2.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");

            //click on Percent
            MyReports.viewByPercent.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.percentByEachStudentGrade2.get(0).getText().trim().contains("100%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudentGrade2.get(1).getText().trim().contains("100%"),"Not displaying proper percent on Student");

            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudentGrade2.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudentGrade2.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in ReportChanges in method reportInCaseOfNewStandardNotMastered", e);
        }
    }











    @Test(priority = 18,enabled = true)
    public void reportViewForReassignCase()
    {
        try
        {


            String appendChar = "aa";
            String appendChar1 = "ab";
            String appendCharacterBuild=System.getProperty("UCHAR");
            if (appendCharacterBuild!=null)
                appendChar=appendChar+appendCharacterBuild;
            int index=113;
            ViewResponse viewResponse=PageFactory.initElements(driver,ViewResponse.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            ResultSet rs = DBConnect.st.executeQuery("select count(subject) from t_master_skill where grade like '%Grade 1' and taxonomy_id=212 and subject like '%Math%' and parent_id is not null and type='ELO';");
            rs.next();
            int count = rs.getInt(1);
            MyReports MyReports= PageFactory.initElements(driver,MyReports.class);
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar1, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index,"truefalse");

            for (int i=0;i<9;i++)
            {
                new Assignment().addQuestion(index,"truefalse");
            }
            new Assignment().assignToStudent(index,appendChar);
            new Navigator().navigateTo("myReports");
            System.out.println(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," "));
            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of " + count + " STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");

            new Navigator().logout();
            //login with first student and attempt 7 of 10 correctly
            new Login().loginAsStudent(appendChar,index);
            new Assignment().submitAssignmentWithMixResponse(index,6,10);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar1,index);
            new Assignment().submitAssignmentWithMixResponse(index,8,10);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,index);
            new Navigator().navigateTo("myReports");
            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("1 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");
            //select mastery as view by
            MyReports.viewByMastery.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getText().trim().equals("Nearly Mastered"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getText().trim().equals("Nearly Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getText().trim().equals("Mastered"),"Not displaying proper mastery on Student");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");

            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");

            //click on score
            MyReports.viewByScore.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("7.00"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("6/10"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("8/10"),"Not displaying proper score on Student");


            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");


            //click on Percent
            MyReports.viewByPercent.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("70%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("60%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("80%"),"Not displaying proper percent on Student");


            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");


           //navigate to assignments
            new Navigator().navigateTo("assignment");
            assignments.viewResponse.get(0).click();

            Iterator itr=viewResponse.fetchStudentEmailId.iterator();
            int counter=0;
            while(itr.hasNext())
            {

                if(itr.next().equals("reportViewForReassignCasest"+appendChar+"@snapwiz.com"))
                {
                    break;
                }
                counter++;
            }
            viewResponse.checkStudentCard.get(counter).click();
            viewResponse.reassign.click();

            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).clear();//short label clear
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).sendKeys("shor");//short label name

            new Click().clickByid("lsm_assignment_due_date");//click on Due Date field
            new Click().clickbylinkText("27");//select due date
            new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
            driver.findElement(By.id("as-description")).click();
            driver.findElement(By.id("as-description")).clear();
            driver.findElement(By.id("as-description")).sendKeys("Reassign Assignment");//add description
            driver.findElement(By.id("as-tag")).click();
            driver.findElement(By.id("as-tag")).sendKeys("tag tag");//add tags
            new Click().clickByid("assign-button");//click on Assign button
            new Navigator().logout();

            //login with first student and attempt 7 of 10 correctly
            new Login().loginAsStudent(appendChar,index);
            new Assignment().submitAssignmentWithMixResponse(index,9,10);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,index);
            new Navigator().navigateTo("myReports");

            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("1 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("1 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");


            //select mastery as view by
            MyReports.viewByMastery.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getText().trim().equals("Mastered"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getText().trim().equals("Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getText().trim().equals("Mastered"),"Not displaying proper mastery on Student");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");

            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");

            //click on score
            MyReports.viewByScore.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("12.67"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("15/20"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("8/10"),"Not displaying proper score on Student");


            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");


            //click on Percent
            MyReports.viewByPercent.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("78%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("75%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("80%"),"Not displaying proper percent on Student");


            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in ReportChanges in method reportInCaseOfNewStandardNotMastered", e);
        }
    }




    @Test(priority = 19,enabled = true)
    public void reportViewForDeleteAssessment()
    {
        try
        {


            String appendChar = "aa4";
            String appendChar1 = "ab4";
            String appendCharacterBuild=System.getProperty("UCHAR");
            if (appendCharacterBuild!=null)
                appendChar=appendChar+appendCharacterBuild;
            int index=120;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(121));
            ViewResponse viewResponse=PageFactory.initElements(driver,ViewResponse.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            ResultSet rs = DBConnect.st.executeQuery("select count(subject) from t_master_skill where grade like '%Grade 1' and taxonomy_id=212 and subject like '%Math%' and parent_id is not null and type='ELO';");
            rs.next();
            int count = rs.getInt(1);
            MyReports MyReports= PageFactory.initElements(driver,MyReports.class);
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar1, classCode, index);//create student2
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index,"truefalse");

            for (int i=0;i<9;i++)
            {
                new Assignment().addQuestion(index,"truefalse");
            }

            new Assignment().create(121,"truefalse");

            for (int i=0;i<9;i++)
            {
                new Assignment().addQuestion(121,"truefalse");
            }
            new Assignment().assignToStudent(index,appendChar);
            new Assignment().assignToStudent(121,appendChar);
            new Navigator().navigateTo("myReports");
            System.out.println(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," "));
            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of " + count + " STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");

            new Navigator().logout();
            //login with first student and attempt 7 of 10 correctly
            new Login().loginAsStudent(appendChar,index);
            new Assignment().submitAssignmentWithMixResponse(index,7,10);
            Thread.sleep(3000);
            new Assignment().submitAssignmentWithMixResponse(121,8,10);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar1,index);
            new Assignment().submitAssignmentWithMixResponse(index,7,10);
            Thread.sleep(3000);
            new Assignment().submitAssignmentWithMixResponse(121,8,10);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,index);
            new Navigator().navigateTo("myReports");
            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("1 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("1 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");
            //select mastery as view by
            MyReports.viewByMastery.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getText().trim().equals("Mastered"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getText().trim().equals("Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getText().trim().equals("Mastered"),"Not displaying proper mastery on Student");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");

            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");

            //click on score
            MyReports.viewByScore.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("15.00"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("15/20"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("15/20"),"Not displaying proper score on Student");


            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");


            //click on Percent
            MyReports.viewByPercent.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("75%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("75%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("75%"),"Not displaying proper percent on Student");


            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");


            //navigate to assignments
            new Navigator().navigateTo("assignment");

            List<WebElement> assessments=assignments.getAssessmentNameList();
            int counter =0;
            for(WebElement assessment: assessments)
            {
                 if(assessment.getText().substring(7).equals(assessmentname))
                {
                    break;
                }
                counter++;
            }
            assignments.deleteAssessment.get(counter).click();
            driver.findElement(By.cssSelector("input#delete-confm-txt")).sendKeys("DELETE");
            driver.findElement(By.cssSelector("div[class='as-modal-yes-btn delete-assignment']")).click();
            Thread.sleep(1000);


            new Navigator().navigateTo("myReports");

            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("1 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");


            //select mastery as view by
            MyReports.viewByMastery.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getText().trim().equals("Nearly Mastered"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getText().trim().equals("Nearly Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getText().trim().equals("Nearly Mastered"),"Not displaying proper mastery on Student");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper mastery on Student");

            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-times"),"Not displaying proper Check mark on Student");

            //click on score
            MyReports.viewByScore.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("7.00"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("7/10"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("7/10"),"Not displaying proper score on Student");


            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper score on Student");


            //click on Percent
            MyReports.viewByPercent.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("70%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("70%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("70%"),"Not displaying proper percent on Student");


            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("orangeGrade"),"Not displaying proper percent on Student");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in ReportChanges in method reportInCaseOfNewStandardNotMastered", e);
        }
    }


    @Test(priority = 20,enabled = true)
    public void reportViewForDeleteStudent()
    {
        try
        {


            String appendChar = "aa9";
            String appendChar1= "ab9";
            String appendCharacterBuild=System.getProperty("UCHAR");
            if (appendCharacterBuild!=null)
                appendChar=appendChar+appendCharacterBuild;
            int index=128;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(129));
            ViewResponse viewResponse=PageFactory.initElements(driver,ViewResponse.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            ResultSet rs = DBConnect.st.executeQuery("select count(subject) from t_master_skill where grade like '%Grade 1' and taxonomy_id=212 and subject like '%Math%' and parent_id is not null and type='ELO';");
            rs.next();
            int count = rs.getInt(1);
            MyReports MyReports= PageFactory.initElements(driver,MyReports.class);
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar1, classCode, index);//create student2
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index,"truefalse");

            for (int i=0;i<9;i++)
            {
                new Assignment().addQuestion(index,"truefalse");
            }

            new Assignment().create(129,"truefalse");

            for (int i=0;i<9;i++)
            {
                new Assignment().addQuestion(129,"truefalse");
            }
            new Assignment().assignToStudent(index,appendChar);
            new Assignment().assignToStudent(129,appendChar);
            new Navigator().navigateTo("myReports");
            System.out.println(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," "));
            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("0 out of " + count + " STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("0 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");

            new Navigator().logout();
            //login with first student and attempt 7 of 10 correctly
            new Login().loginAsStudent(appendChar,index);
            new Assignment().submitAssignmentWithMixResponse(index,7,10);
            Thread.sleep(3000);
            new Assignment().submitAssignmentWithMixResponse(129,8,10);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar1,index);
            new Assignment().submitAssignmentWithMixResponse(index,7,10);
            Thread.sleep(3000);
            new Assignment().submitAssignmentWithMixResponse(129,8,10);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,index);
            new Navigator().navigateTo("myReports");
            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("1 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("1 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");
            //select mastery as view by
            MyReports.viewByMastery.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getText().trim().equals("Mastered"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getText().trim().equals("Mastered"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getText().trim().equals("Mastered"),"Not displaying proper mastery on Student");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");

            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(2).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");

            //click on score
            MyReports.viewByScore.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("15.00"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("15/20"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getText().trim().contains("15/20"),"Not displaying proper score on Student");


            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");


            //click on Percent
            MyReports.viewByPercent.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("75%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("75%"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getText().trim().contains("75%"),"Not displaying proper percent on Student");


            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");
            Assert.assertTrue(MyReports.percentByEachStudent.get(2).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");


            //navigate to assignments
            new Navigator().navigateTo("manageclass");
            ManageClass manageClass=PageFactory.initElements(driver,ManageClass.class);
            List<WebElement> itr=manageClass.checkBoxSelectStudent;
            int counter=0;
            int numberOfRow=driver.findElements(By.xpath("//tbody/tr")).size();
            int i=0;
            for(i=0;i<numberOfRow;i++)
            {
                if(driver.findElement(By.xpath("//tbody/tr["+(i+1)+"]/td[2]")).getText().trim().equals("reportViewForDeleteStudentst"+appendChar))
                {
                    break;
                }

            }
            manageClass.checkBoxSelectStudent.get(i+1).click();
            manageClass.deleteStudents.click();
            driver.findElement(By.cssSelector("input#delete-confm-txt")).sendKeys("DELETE");
            driver.findElement(By.cssSelector("span[class='delete-draft-assessment delete-class-student']")).click();
            Thread.sleep(1000);


            new Navigator().navigateTo("myReports");


            Assert.assertTrue(MyReports.standardAssessced.get(0).getText().trim().replaceAll("\n"," ").contains("1 out of "+count+" STANDARDS MASTERED"),"Standard mastered not displaying properly");
            Assert.assertTrue(MyReports.standardAssessced.get(1).getText().trim().replaceAll("\n"," ").contains("1 out of "+count+" STANDARDS ASSESSED"),"Standard assessed not displaying properly");


            //select mastery as view by
            MyReports.viewByMastery.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getText().trim().equals("Mastered"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getText().trim().equals("Mastered"),"Not displaying proper mastery on Student");

            //check color of each student
            Assert.assertTrue(MyReports.masteryByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on class average");
            Assert.assertTrue(MyReports.masteryByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper mastery on Student");

            //check tick mark at each student and class average
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(0).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on class average");
            Assert.assertTrue(MyReports.checkMarkOnEachStudentMastery.get(1).getAttribute("class").trim().contains("fa fa-check"),"Not displaying proper Check mark on Student");

            //click on score
            MyReports.viewByScore.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getText().trim().contains("15.00"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getText().trim().contains("15/20"),"Not displaying proper score on Student");


            //check color of each student
            Assert.assertTrue(MyReports.scoreByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on class average");
            Assert.assertTrue(MyReports.scoreByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper score on Student");


            //click on Percent
            MyReports.viewByPercent.click();
            //check mastery for class average
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getText().trim().contains("75%"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getText().trim().contains("75%"),"Not displaying proper percent on Student");


            //check color of each student
            Assert.assertTrue(MyReports.percentByEachStudent.get(0).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on class average");
            Assert.assertTrue(MyReports.percentByEachStudent.get(1).getAttribute("class").trim().contains("greenGrade"),"Not displaying proper percent on Student");



        }
        catch (Exception e)
        {
            Assert.fail("Exception in ReportChanges in method reportInCaseOfNewStandardNotMastered", e);
        }
    }

}
