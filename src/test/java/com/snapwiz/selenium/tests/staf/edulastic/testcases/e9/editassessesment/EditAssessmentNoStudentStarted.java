package com.snapwiz.selenium.tests.staf.edulastic.testcases.e9.editassessesment;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.reports.MyReports;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by shashank on 24-09-2015.
 */
public class EditAssessmentNoStudentStarted extends Driver{
    @Test(priority = 1,enabled = true)
    public void editAssessmentDeselectResequenceAssignmentSharingPublic()

    {
        int dataIndex = 45;
        String appendChar = "a267";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String updatedAssessmentName=ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(46));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver, AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assignments assignments=PageFactory.initElements(driver,Assignments.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        StudentResponse studentResponse=PageFactory.initElements(driver,StudentResponse.class);
        InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
        MyReports instructorReport = PageFactory.initElements(driver,MyReports.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"truefalse");//create assessment
            new Assignment().addQuestion(dataIndex,"multiplechoice");//add question
            new Assignment().addQuestion(dataIndex,"multipleselection");//add question
            new Assignment().assignToStudent(dataIndex,appendChar);//assign to student
            new Assignment().editPublishedAssignment(dataIndex,appendChar);//edit published assessment
            //vaerify question details
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(2).getText().contains("Q3:  Multi Selection " + questiontext), "Question dosen't match");
            //deselect first question
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
            Thread.sleep(2000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multi Selection " + questiontext), "Question dosen't match");
            //change assessment name
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name.form-control")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name.form-control")).sendKeys(updatedAssessmentName);
            //resequence the quesntions
            Actions action=new Actions(driver);
            action.clickAndHold(assessmentReview.dragIcon.get(0)).moveToElement(assessmentReview.dragIcon.get(1),0,100).release().build().perform();
            Thread.sleep(3000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multiple Choice " + questiontext), "Question dosen't match");
            assessmentReview.saveInDrafts.click();
            new Assignment().assignToStudent(46,appendChar);//assign to student
            new Navigator().navigateTo("myAssessments");
            int index=0;
            List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='as-label ellipsis']"));//as per E8
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(updatedAssessmentName))
                    break;
                else
                    index++;

            }
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", index);//click on Assignment
            Thread.sleep(2000);
            //verify assessment details
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");

            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);
            new Assignment().submitAssignment(46);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar, dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to dashboard
            // instructorDashboard.getViewLiveResult().click();//Click on view live result
            index=0;
            List<WebElement> allAssignment = assignments.getAssessmentNameList();//as per E8
            for (WebElement assessment : allAssignment)
            {
                if (assessment.getText().contains(updatedAssessmentName))
                    break;
                else
                    index++;

            }
            new Click().clickbylistcssselector("span.as-response", index);//click on Assignment
            Thread.sleep(2000);
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.size(),2,"Number of question not matched");
            Thread.sleep(240000);
            //navigate to reports
            new Navigator().navigateTo("myReports");

            //Expected - The Summary view of "Standards assessed" should be updated based on the standards the Assignment covers
            Assert.assertEquals(instructorReport.standards.get(2).getText(),"1 out of 21","'Standards assessed' is not updated as expected");

            instructorReport.viewByMastery.click();//Click on master radio  button
            instructorReport.expandIcon.get(3).click();
            Thread.sleep(200);

            //Expected - The Mastery View Should show if the Student has mastered a Particular Standard Based on the Score he procured
            Assert.assertEquals(instructorReport.masteryByEachStudent.get(0).getText(),"1 out of 1","Class average is not displayed as mastered under 1.OA");
            Assert.assertEquals(instructorReport.masteryByEachStudent.get(1).getText(),"Mastered","Student1 value is not displayed as mastered under 1.OA");

            instructorReport.viewByScore.click();//Click on score radio button

            //Expected - Points scored by the student in a particular standard out of total assigned points for that standard rounded off to nearest second decimal should be displayed
            Assert.assertEquals(instructorReport.scoreByEachStudent.get(0).getText(),"2.00","Score is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(instructorReport.scoreByEachStudent.get(1).getText(),"2/2","Score is not displayed for student 1 as expected under 1.O.A");

            instructorReport.viewByPercent.click();//Click on percent radio button

            //Expected - Raw score that the student has scored should be displayed in Terms of Percentage
            Assert.assertEquals(instructorReport.percentByEachStudent.get(0).getText(),"100%","Percentage is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(instructorReport.percentByEachStudent.get(1).getText(),"100%","Percentage is not displayed for student 1 as expected under 1.O.A");
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar, dataIndex);
           new Navigator().navigateTo("assignment");//Navigate to dashboard
            index=0;
           allAssignment = assignments.getAssessmentNameList();//as per E8
            for (WebElement assessment : allAssignment)
            {
                if (assessment.getText().contains(assessmentname))
                    break;
                else
                    index++;

            }
            new Click().clickbylistcssselector("span.as-response", index);//click on Assignment
            Thread.sleep(2000);
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.size(),3,"Number of question not matched");
            Thread.sleep(240000);//reports is not updating at the time student submit assignment
            //navigate to reports
            new Navigator().navigateTo("myReports");

            //Expected - The Summary view of "Standards assessed" should be updated based on the standards the Assignment covers
            Assert.assertEquals(instructorReport.standards.get(2).getText(),"1 out of 21","'Standards assessed' is not updated as expected");

            instructorReport.viewByMastery.click();//Click on master radio  button
            instructorReport.expandIcon.get(3).click();//click on '+' sign to expand elo
            Thread.sleep(200);
            //Expected - The Mastery View Should show if the Student has mastered a Particular Standard Based on the Score he procured
            Assert.assertEquals(instructorReport.masteryByEachStudent.get(0).getText(),"1 out of 1","Class average is not displayed as mastered under 1.OA");
            Assert.assertEquals(instructorReport.masteryByEachStudent.get(1).getText(),"Mastered","Student1 value is not displayed as mastered under 1.OA");

            instructorReport.viewByScore.click();//Click on score radio button

            //Expected - Points scored by the student in a particular standard out of total assigned points for that standard rounded off to nearest second decimal should be displayed
            Assert.assertEquals(instructorReport.scoreByEachStudent.get(0).getText(),"5.00","Score is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(instructorReport.scoreByEachStudent.get(1).getText(),"5/5","Score is not displayed for student 1 as expected under 1.O.A");

            instructorReport.viewByPercent.click();//Click on percent radio button

            //Expected - Raw score that the student has scored should be displayed in Terms of Percentage
            Assert.assertEquals(instructorReport.percentByEachStudent.get(0).getText(),"100%","Percentage is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(instructorReport.percentByEachStudent.get(1).getText(),"100%","Percentage is not displayed for student 1 as expected under 1.O.A");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment",e);
        }
    }
    @Test(priority = 2,enabled = true)
    public void editAssessmentDeselectResequenceAssessmentSharingPublic()
    {
        int dataIndex = 47;
        String appendChar = "a259";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String updatedAssessmentName=ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(48));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver, AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assignments assignments=PageFactory.initElements(driver,Assignments.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        StudentResponse studentResponse=PageFactory.initElements(driver,StudentResponse.class);
        InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
        MyReports instructorReport = PageFactory.initElements(driver,MyReports.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"truefalse");//create assessment
            new Assignment().addQuestion(dataIndex,"multiplechoice");//add question
            new Assignment().addQuestion(dataIndex,"multipleselection");//add question
            new Assignment().assignToStudent(dataIndex,appendChar);//assign to student
            new Assignment().editPublishedAssessment(dataIndex,appendChar);//edit published assessment
            //vaerify question details
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(2).getText().contains("Q3:  Multi Selection " + questiontext), "Question dosen't match");
            //deselect first question
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
            Thread.sleep(2000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multi Selection " + questiontext), "Question dosen't match");
            //change assessment name
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name.form-control")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name.form-control")).sendKeys(updatedAssessmentName);
            //resequence the quesntions
            Actions action=new Actions(driver);
            action.clickAndHold(assessmentReview.dragIcon.get(0)).moveToElement(assessmentReview.dragIcon.get(1),0,100).release().build().perform();
            Thread.sleep(3000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multiple Choice " + questiontext), "Question dosen't match");
            assessmentReview.saveInDrafts.click();
            new Assignment().assignToStudent(48,appendChar);//assign to student
            new Navigator().navigateTo("myAssessments");
            int index=0;
            List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='as-label ellipsis']"));//as per E8
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(updatedAssessmentName))
                    break;
                else
                    index++;

            }
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", index);//click on Assignment
            Thread.sleep(2000);
            //verify assessment details
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");

            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);
            new Assignment().submitAssignment(48);
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar, dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to dashboard
            // instructorDashboard.getViewLiveResult().click();//Click on view live result
            index=0;
            List<WebElement> allAssignment = assignments.getAssessmentNameList();//as per E8
            for (WebElement assessment : allAssignment)
            {
                if (assessment.getText().contains(updatedAssessmentName))
                    break;
                else
                    index++;

            }
            new Click().clickbylistcssselector("span.as-response", index);//click on Assignment
            Thread.sleep(2000);
            String height=studentResponse.greenBarUnderPerformance.get(0).getAttribute("height");
            System.out.println(height);
            Assert.assertEquals(driver.findElements(By.cssSelector("rect[height='"+height+"']")).size(),2,"Number of question not matched");
            //Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.size(),2,"Number of question not matched");
            //navigate to reports
            Thread.sleep(24000);
            new Navigator().navigateTo("myReports");

            //Expected - The Summary view of "Standards assessed" should be updated based on the standards the Assignment covers
            Assert.assertEquals(instructorReport.standards.get(2).getText(),"1 out of 21","'Standards assessed' is not updated as expected");

            instructorReport.viewByMastery.click();//Click on master radio  button
            instructorReport.expandIcon.get(3).click();//click on '+' sign to expand elo
            Thread.sleep(200);
            //Expected - The Mastery View Should show if the Student has mastered a Particular Standard Based on the Score he procured
            Assert.assertEquals(instructorReport.masteryByEachStudent.get(0).getText(),"1 out of 1","Class average is not displayed as mastered under 1.OA");
            Assert.assertEquals(instructorReport.masteryByEachStudent.get(1).getText(),"Mastered","Student1 value is not displayed as mastered under 1.OA");

            instructorReport.viewByScore.click();//Click on score radio button

            //Expected - Points scored by the student in a particular standard out of total assigned points for that standard rounded off to nearest second decimal should be displayed
            Assert.assertEquals(instructorReport.scoreByEachStudent.get(0).getText(),"2.00","Score is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(instructorReport.scoreByEachStudent.get(1).getText(),"2/2","Score is not displayed for student 1 as expected under 1.O.A");

            instructorReport.viewByPercent.click();//Click on percent radio button

            //Expected - Raw score that the student has scored should be displayed in Terms of Percentage
            Assert.assertEquals(instructorReport.percentByEachStudent.get(0).getText(),"100%","Percentage is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(instructorReport.percentByEachStudent.get(1).getText(),"100%","Percentage is not displayed for student 1 as expected under 1.O.A");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment",e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void editAssignmentDeselectResequenceAssignmentAfterSubmit()
    {
        int dataIndex = 51;
        String appendChar = "a4";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String updatedAssessmentName=ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(52));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver, AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assignments assignments=PageFactory.initElements(driver,Assignments.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        StudentResponse studentResponse=PageFactory.initElements(driver,StudentResponse.class);
        InstructorDashboard instructorDashboard= PageFactory.initElements(driver, InstructorDashboard.class);
        MyReports instructorReport = PageFactory.initElements(driver,MyReports.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"truefalse");//create assessment
            new Assignment().addQuestion(dataIndex,"multiplechoice");//add question
            new Assignment().addQuestion(dataIndex,"multipleselection");//add question
            new Assignment().assignToStudent(dataIndex,appendChar);//assign to student
            new Assignment().editPublishedAssignment(dataIndex,appendChar);//edit published assessment
            //vaerify question details
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(2).getText().contains("Q3:  Multi Selection " + questiontext), "Question dosen't match");
            //deselect first question
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
            Thread.sleep(2000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multi Selection " + questiontext), "Question dosen't match");
            //change assessment name
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name.form-control")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name.form-control")).sendKeys(updatedAssessmentName);
            //resequence the quesntions
            Actions action=new Actions(driver);
            action.clickAndHold(assessmentReview.dragIcon.get(0)).moveToElement(assessmentReview.dragIcon.get(1),0,100).release().build().perform();
            Thread.sleep(3000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multiple Choice " + questiontext), "Question dosen't match");
            assessmentReview.nextButton.click();
            Thread.sleep(2000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);//login as a student1
            new Assignment().submitAssignmentWithMixResponse(dataIndex,3,3);//Submit the assignment with 6 correct answer
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar,dataIndex);





            new Navigator().navigateTo("assignment");//Navigate to dashboard
            // instructorDashboard.getViewLiveResult().click();//Click on view live result
            int index=0;
            List<WebElement> allAssignment = assignments.getAssessmentNameList();//as per E8
            for (WebElement assessment : allAssignment)
            {
                if (assessment.getText().contains(assessmentname))
                    break;
                else
                    index++;

            }
            new Click().clickbylistcssselector("span.as-response", index);//click on Assignment
            Thread.sleep(2000);
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.size(),3,"Number of question not matched");
            Thread.sleep(24000);
            //navigate to reports
            new Navigator().navigateTo("myReports");

            //Expected - The Summary view of "Standards assessed" should be updated based on the standards the Assignment covers
            Assert.assertEquals(instructorReport.standards.get(2).getText(),"1 out of 21","'Standards assessed' is not updated as expected");

            instructorReport.viewByMastery.click();//Click on master radio  button
            instructorReport.expandIcon.get(3).click();
            Thread.sleep(200);
            //Expected - The Mastery View Should show if the Student has mastered a Particular Standard Based on the Score he procured
            Assert.assertEquals(instructorReport.masteryByEachStudent.get(0).getText(),"1 out of 1","Class average is not displayed as mastered under 1.OA");
            Assert.assertEquals(instructorReport.masteryByEachStudent.get(1).getText(),"Mastered","Student1 value is not displayed as mastered under 1.OA");

            instructorReport.viewByScore.click();//Click on score radio button

            //Expected - Points scored by the student in a particular standard out of total assigned points for that standard rounded off to nearest second decimal should be displayed
            Assert.assertEquals(instructorReport.scoreByEachStudent.get(0).getText(),"3.00","Score is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(instructorReport.scoreByEachStudent.get(1).getText(),"3/3","Score is not displayed for student 1 as expected under 1.O.A");

            instructorReport.viewByPercent.click();//Click on percent radio button

            //Expected - Raw score that the student has scored should be displayed in Terms of Percentage
            Assert.assertEquals(instructorReport.percentByEachStudent.get(0).getText(),"100%","Percentage is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(instructorReport.percentByEachStudent.get(1).getText(),"100%","Percentage is not displayed for student 1 as expected under 1.O.A");

            new Assignment().assignToStudent(52,appendChar);//assign to student
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);//login as a student1
            new Assignment().submitAssignmentWithMixResponse(52,2,2);//Submit the assignment with 6 correct answer
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar,dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to dashboard
            index=0;
            allAssignment = assignments.getAssessmentNameList();//as per E8
            for (WebElement assessment : allAssignment)
            {
                if (assessment.getText().contains(updatedAssessmentName))
                    break;
                else
                    index++;

            }
            new Click().clickbylistcssselector("span.as-response", index);//click on Assignment
            Thread.sleep(2000);
            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.size(),2,"Number of question not matched");
            Thread.sleep(24000);
            //navigate to reports
            new Navigator().navigateTo("myReports");

            //Expected - The Summary view of "Standards assessed" should be updated based on the standards the Assignment covers
            Assert.assertEquals(instructorReport.standards.get(2).getText(),"1 out of 21","'Standards assessed' is not updated as expected");

            instructorReport.viewByMastery.click();//Click on master radio  button
            instructorReport.expandIcon.get(3).click();
            Thread.sleep(200);
            //Expected - The Mastery View Should show if the Student has mastered a Particular Standard Based on the Score he procured
            Assert.assertEquals(instructorReport.masteryByEachStudent.get(0).getText(),"1 out of 1","Class average is not displayed as mastered under 1.OA");
            Assert.assertEquals(instructorReport.masteryByEachStudent.get(1).getText(),"Mastered","Student1 value is not displayed as mastered under 1.OA");

            instructorReport.viewByScore.click();//Click on score radio button

            //Expected - Points scored by the student in a particular standard out of total assigned points for that standard rounded off to nearest second decimal should be displayed
            Assert.assertEquals(instructorReport.scoreByEachStudent.get(0).getText(),"5.00","Score is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(instructorReport.scoreByEachStudent.get(1).getText(),"5/5","Score is not displayed for student 1 as expected under 1.O.A");

            instructorReport.viewByPercent.click();//Click on percent radio button

            //Expected - Raw score that the student has scored should be displayed in Terms of Percentage
            Assert.assertEquals(instructorReport.percentByEachStudent.get(0).getText(),"100%","Percentage is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(instructorReport.percentByEachStudent.get(1).getText(),"100%","Percentage is not displayed for student 1 as expected under 1.O.A");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment",e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void editAssignmentDeselectResequenceAssignmentWith3Student()
    {
        int dataIndex = 56;
        String appendChar = "a10";
        String appendChar1 = "b10";
        String appendChar2 = "c10";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String updatedAssessmentName=ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(52));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver, AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        Assignments assignments=PageFactory.initElements(driver,Assignments.class);
        StudentResponse studentResponse=PageFactory.initElements(driver,StudentResponse.class);
        MyReports instructorReport = PageFactory.initElements(driver,MyReports.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"truefalse");//create assessment
            new Assignment().addQuestion(dataIndex,"multiplechoice");//add question
            new Assignment().addQuestion(dataIndex,"multipleselection");//add question
            new Assignment().assignToStudent(dataIndex,appendChar);//assign to student
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar1,dataIndex);
            new Assignment().startAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,dataIndex);
            Thread.sleep(24000);
            new Navigator().navigateTo("myReports");

            //Expected - The Summary view of "Standards assessed" should be updated based on the standards the Assignment covers
            Assert.assertEquals(instructorReport.standards.get(2).getText(),"0 out of 21","'Standards assessed' is not updated as expected");

            instructorReport.viewByMastery.click();//Click on master radio  button
            instructorReport.expandIcon.get(3).click();
            Thread.sleep(200);
            //Expected - The Mastery View Should show if the Student has mastered a Particular Standard Based on the Score he procured
            Assert.assertEquals(instructorReport.masteryByEachStudent.get(0).getText(),"1 out of 3","Class average is not displayed as mastered under 1.OA");
            Assert.assertEquals(instructorReport.masteryByEachStudent.get(1).getText(),"Mastered","Student1 value is not displayed as mastered under 1.OA");

            instructorReport.viewByScore.click();//Click on score radio button

            //Expected - Points scored by the student in a particular standard out of total assigned points for that standard rounded off to nearest second decimal should be displayed
            Assert.assertEquals(instructorReport.scoreByEachStudent.get(0).getText(),"3.00","Score is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(instructorReport.scoreByEachStudent.get(1).getText(),"3/3","Score is not displayed for student 1 as expected under 1.O.A");

            instructorReport.viewByPercent.click();//Click on percent radio button

            //Expected - Raw score that the student has scored should be displayed in Terms of Percentage
            Assert.assertEquals(instructorReport.percentByEachStudent.get(0).getText(),"100%","Percentage is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(instructorReport.percentByEachStudent.get(1).getText(),"100%","Percentage is not displayed for student 1 as expected under 1.O.A");

            new Assignment().editPublishedAssignment(dataIndex,appendChar);//edit published assessment
            //vaerify question details
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(2).getText().contains("Q3:  Multi Selection " + questiontext), "Question dosen't match");
            //deselect first question
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
            Thread.sleep(2000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multi Selection " + questiontext), "Question dosen't match");
            //change assessment name
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name.form-control")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name.form-control")).sendKeys(updatedAssessmentName);
            //resequence the quesntions
            Actions action=new Actions(driver);
            action.clickAndHold(assessmentReview.dragIcon.get(0)).moveToElement(assessmentReview.dragIcon.get(1),0,100).release().build().perform();
            Thread.sleep(3000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multiple Choice " + questiontext), "Question dosen't match");
            assessmentReview.nextButton.click();
            Thread.sleep(2000);
            Thread.sleep(24000);
            new Navigator().navigateTo("myReports");

            //Expected - The Summary view of "Standards assessed" should be updated based on the standards the Assignment covers
            Assert.assertEquals(instructorReport.standards.get(2).getText(),"0 out of 21","'Standards assessed' is not updated as expected");

            instructorReport.viewByMastery.click();//Click on master radio  button
            instructorReport.expandIcon.get(3).click();
            Thread.sleep(200);
            //Expected - The Mastery View Should show if the Student has mastered a Particular Standard Based on the Score he procured
            Assert.assertEquals(instructorReport.masteryByEachStudent.get(0).getText(),"1 out of 3","Class average is not displayed as mastered under 1.OA");
            Assert.assertEquals(instructorReport.masteryByEachStudent.get(1).getText(),"Mastered","Student1 value is not displayed as mastered under 1.OA");

            instructorReport.viewByScore.click();//Click on score radio button

            //Expected - Points scored by the student in a particular standard out of total assigned points for that standard rounded off to nearest second decimal should be displayed
            Assert.assertEquals(instructorReport.scoreByEachStudent.get(0).getText(),"3.00","Score is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(instructorReport.scoreByEachStudent.get(1).getText(),"3/3","Score is not displayed for student 1 as expected under 1.O.A");

            instructorReport.viewByPercent.click();//Click on percent radio button

            //Expected - Raw score that the student has scored should be displayed in Terms of Percentage
            Assert.assertEquals(instructorReport.percentByEachStudent.get(0).getText(),"100%","Percentage is not displayed for class average as expected under 1.O.A");
            Assert.assertEquals(instructorReport.percentByEachStudent.get(1).getText(),"100%","Percentage is not displayed for student 1 as expected under 1.O.A");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment",e);
        }
    }
    @Test(priority = 5,enabled = true)
    public void editAssignmentSharedPublic()
    {
        int dataIndex = 61;
        String appendChar = "a24";
        String appendChar1= "b24";
        String appendChar2= "c25";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String updatedAssessmentName=ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver, AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assignments assignments=PageFactory.initElements(driver,Assignments.class);
        StudentResponse studentResponse=PageFactory.initElements(driver,StudentResponse.class);
        MyReports instructorReport = PageFactory.initElements(driver,MyReports.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        try {


            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");//create assessment
            new Assignment().addQuestion(dataIndex,"multiplechoice");//add question
            new Assignment().addQuestion(dataIndex,"multipleselection");//add question
            new Assignment().assignToStudent(60,appendChar);//assign as assessmnet
            new Navigator().logout();//logout
            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a 2nd teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//signup as student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar1,dataIndex);//login as 2nd instructor
            new Assignment().useExistingAssignment(dataIndex,appendChar1);//use assessment of 1st instructor
            String assessmentPublicURl=assign.getAssessmentShareLink().getText();//fetch url from UI
            System.out.println(assessmentPublicURl);
            new Navigator().logout();
            //logion as 1st instructor
            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().editPublishedAssignment(dataIndex,appendChar);//edit published assessment
            //vaerify question details
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(2).getText().contains("Q3:  Multi Selection " + questiontext), "Question dosen't match");
            //deselect first question
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
            Thread.sleep(2000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multi Selection " + questiontext), "Question dosen't match");
            //change assessment name
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name.form-control")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name.form-control")).sendKeys(updatedAssessmentName);
            //resequence the questions
            Actions action=new Actions(driver);
            action.clickAndHold(assessmentReview.dragIcon.get(0)).moveToElement(assessmentReview.dragIcon.get(1),0,100).release().build().perform();
            Thread.sleep(3000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multiple Choice " + questiontext), "Question dosen't match");
            assessmentReview.saveInDrafts.click();
            //assign as assessment and sharing as private
            new Assignment().assignToStudent(62,appendChar);
            String assessmentPrivateURl=assign.getAssessmentShareLink().getText();//fetch url from UI
            System.out.println(assessmentPrivateURl);
            new Navigator().logout();

            //login as student and attempt the assignment
            new Login().loginAsStudent(appendChar,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            driver.navigate().to(assessmentPublicURl);//try to access url which was shared by instructor 2
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar, dataIndex);//login as teacher 1 and try to access url shared by instructor 2
            driver.navigate().to(assessmentPublicURl);
            Thread.sleep(3000);
            //number of question should be 2
            Assert.assertEquals(assessmentDetailsPage.numberOfQuestion.size(),2,"number of question are not same as expected");
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar1,dataIndex);
            driver.navigate().to(assessmentPrivateURl);//try to access private url of instructor 1
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("toast-message")));
            Assert.assertEquals(assessmentLibrary.errorMessage.getText(),"You are not authorized to view this assessment.","error message not matches");


            //signup with instructor and try to access deeplink url shared by instructor 2
            new SignUp().teacher(appendChar2,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar2,dataIndex);//Create a school
            new Classes().createClass(appendChar2,dataIndex);//Create class
            driver.navigate().to(assessmentPublicURl);
            Assert.assertEquals(assessmentLibrary.errorMessage.getText(),"You are not authorized to view this assessment.","error message not matches");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment",e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void reAssignEditedAssessment()
    {
        int dataIndex = 65;
        String appendChar = "a26";
        String appendChar1= "b26";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String updatedAssessmentName=ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver, AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assignments assignments=PageFactory.initElements(driver,Assignments.class);
        StudentResponse studentResponse=PageFactory.initElements(driver,StudentResponse.class);
        MyReports instructorReport = PageFactory.initElements(driver,MyReports.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        try {
            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");//create assessment
            new Assignment().addQuestion(dataIndex,"multiplechoice");//add question
            new Assignment().addQuestion(dataIndex,"multipleselection");//add question
            new Assignment().assignToStudent(dataIndex,appendChar);//assign as assessmnet
            new Navigator().logout();//logout
            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a 2nd teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create a school
            String classCode=new Classes().createClass(appendChar1,dataIndex);//Create class
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//signup as student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar1,dataIndex);//login as 2nd instructor
            new Assignment().useExistingAssignment(66,appendChar1);//use assessment of 1st instructor
            new Navigator().logout();
            //login as student and attempt the assignment
            new Login().loginAsStudent(appendChar,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();

            //logion as 1st instructor
            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().editPublishedAssignment(dataIndex,appendChar);//edit published assessment
            //vaerify question details
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(2).getText().contains("Q3:  Multi Selection " + questiontext), "Question dosen't match");
            //deselect first question
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
            Thread.sleep(2000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multi Selection " + questiontext), "Question dosen't match");
            //change assessment name
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name.form-control")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name.form-control")).sendKeys(updatedAssessmentName);
            //resequence the questions
            Actions action=new Actions(driver);
            action.clickAndHold(assessmentReview.dragIcon.get(0)).moveToElement(assessmentReview.dragIcon.get(1),0,100).release().build().perform();
            Thread.sleep(3000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multiple Choice " + questiontext), "Question dosen't match");
            assessmentReview.saveInDrafts.click();
            //assign as assessment and sharing as private
            new Assignment().assignToStudent(65,appendChar);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar1,dataIndex);
            new Navigator().navigateTo("assignment");
            assignments.viewResponse.get(0).click();
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("rect[height='108']")));

            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.size(),3,"Number of bars are not displaying correctly");

            studentResponse.checkboxInStudentCard.get(0).click();
            studentResponse.button_redirect.click();
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("toast-message")));
            Assert.assertEquals(assessmentLibrary.errorMessage.getText(),"Latest copy of the assessment will be assigned.","error message not match");
            new Click().clickByid("assign-button");//click on Assign button
            Thread.sleep(3000);
            new Navigator().logout();
            //login as student and attempt the assignment
            new Login().loginAsStudent(appendChar,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar1,dataIndex);
            new Navigator().navigateTo("assignment");
            assignments.viewResponse.get(0).click();
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("rect[height='108']")));

            Assert.assertEquals(studentResponse.barTwoCorrectUnderPerformanceWithOneStudent.size(),2,"Number of bars are not displaying correctly");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment",e);
        }
    }
}
