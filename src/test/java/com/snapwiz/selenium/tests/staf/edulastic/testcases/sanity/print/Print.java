package com.snapwiz.selenium.tests.staf.edulastic.testcases.sanity.print;

import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview.PrintPreview;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by pragyas on 08-03-2016.
 */
public class Print extends Driver{

    InstructorDashboard instructorDashboard;
    StudentResponse studentResponse;
    PrintPreview printPreview;
    String assessmentname;


    @BeforeMethod
    public void init(){
        assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(1));
        WebDriver driver=Driver.getWebDriver();
        instructorDashboard = PageFactory.initElements(driver,InstructorDashboard.class);
        studentResponse = PageFactory.initElements(driver,StudentResponse.class);
        printPreview = PageFactory.initElements(driver,PrintPreview.class);

    }

    @Test(priority = 1,enabled = true)
    public void printAwaitingSubmissionStatusNonGradableAssignment(){
        WebDriver driver=Driver.getWebDriver();
        try{
            ReportUtil.log("Description", "This test case validates the print page for the awaiting submission status if the assignment is non gradable", "info");

            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            int dataIndex = 1;

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);

            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963", dataIndex, false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create a class
            new Assignment().create(dataIndex,"truefalse");//Create an assignment
            new Assignment().addQuestion(dataIndex,"multiplechoice");
            new Assignment().addQuestion(dataIndex,"multipleselection");
            new Assignment().addQuestion(dataIndex,"textentry");
            new Assignment().addQuestion(dataIndex,"textdropdown");
            new Assignment().assignToStudent(dataIndex, appendChar1);//Assign to class
            new Navigator().logout();//log out

        /*    //Assignment status 'Not Started'
            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            //Assignment status 'Turned In'
            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student2
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().navigateTo("dashboard");
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[class='btn gradebook-left-btn as-live-print-wrapper']")));
            studentResponse.checkboxInStudentCard.get(2).click();//Select student 2 checkbox
            studentResponse.printResponse.click();//Click on print

            String winHandleBefore = driver.getWindowHandle();
            switchWindow();
            WebDriverUtil.waitForAjax(driver,60);
            //Verify the student obtained marks(for all the 5 questions) should not appear
            for(int i=0;i<printPreview.marksObtained.size();i++) {
                if (printPreview.marksObtained.get(i).isDisplayed()==true) {
                    CustomAssert.fail("Verify the marks obtained by student for question number "+i +" should not be displayed","Marks is displayed as expected for question "+i);
                }
            }

            //Verify the student's total marks (for all the 5 questions) should not display
            for(int i=0;i<printPreview.totalPoint.size();i++) {
                if (printPreview.totalPoint.get(i).isDisplayed()==true) {
                    CustomAssert.fail("Verify the total point for question number"+i+" should not be displayed","Total point is displayed as expected for question "+i);
                }
            }

            //Verify the total number of questions
            CustomAssert.assertEquals(String.valueOf(printPreview.questionNames.size()),"5","Verify the total number of questions","Total number of questions are displayed as expected","Total number of questions are not displayed as expected");

            //Verify all the question names
            CustomAssert.assertEquals(printPreview.questionNames.get(0).getText().trim(),"True False "+questiontext,"Verify the question1 name","Question1 name is displayed as expected","Question1 name is not displayed as expected");
            CustomAssert.assertEquals(printPreview.questionNames.get(1).getText().trim(),"Multiple Choice "+questiontext,"Verify the question2 name","Question2 name is displayed as expected","Question2 name is not displayed as expected");
            CustomAssert.assertEquals(printPreview.questionNames.get(2).getText().trim(),"Multi Selection "+questiontext,"Verify the question3 name","Question3 name is displayed as expected","Question3 name is not displayed as expected");
            CustomAssert.assertEquals(printPreview.questionNames.get(3).getText().trim(),"Text Entry "+questiontext,"Verify the question4 name","Question4 name is displayed as expected","Question4 name is not displayed as expected");
            CustomAssert.assertTrue(printPreview.questionNames.get(4).getText().contains("Text Drop Down "+questiontext),"Verify the question5 name","Question5 name is displayed as expected","Question5 name is not displayed as expected");

            driver.close();
            driver.switchTo().window(winHandleBefore);

*/
        }catch (Exception e)
        {
            Assert.fail("Exception in testcase 'printAwaitingSubmissionStatusNonGradableAssignment' in class 'Print'", e);

        }
    }



    @Test(priority = 2,enabled = false)
    public void printGradedStatusNonGradableAssignment(){
        WebDriver driver=Driver.getWebDriver();
        try{
            ReportUtil.log("Description", "This test case validates the print page for the graded status if the assignment is non gradable", "info");

            InstructorDashboard instructorDashboard = PageFactory.initElements(driver,InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);

            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            int dataIndex = 2;

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963", dataIndex, false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create a class

            new Assignment().useExistingAssignment(dataIndex,appendChar1);//Use existing assessment create in above test case and assign to class
            new Navigator().logout();//log out

            //Assignment status 'Turned In'
            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Assignment().submitAssignmentWithMixResponse(dataIndex,2,5);//Submit assignment with mix response
            new Navigator().navigateTo("dashboard");
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[class='btn gradebook-left-btn as-live-print-wrapper']")));
            studentResponse.releaseGrade.click();//Click on release feedback
            studentResponse.checkboxInStudentCard.get(1).click();//Select student checkbox
            studentResponse.printResponse.click();//Click on print

            String winHandleBefore = driver.getWindowHandle();
            switchWindow();

            //Verify the student obtained marks(for all the 5 questions) should not appear
            for(int i=0;i<printPreview.marksObtained.size();i++) {
                if (printPreview.marksObtained.get(i).isDisplayed()==true) {
                    CustomAssert.fail("Verify the marks obtained by student for question number "+i +" should not be displayed","Marks is displayed as expected for question "+i);
                }
            }

            //Verify the student's total marks (for all the 5 questions) should not display
            for(int i=0;i<printPreview.totalPoint.size();i++) {
                if (printPreview.totalPoint.get(i).isDisplayed()==true) {
                    CustomAssert.fail("Verify the total point for question number"+i+" should not be displayed","Total point is displayed as expected for question "+i);
                }
            }

            //Verify the total number of questions
            CustomAssert.assertEquals(String.valueOf(printPreview.questionNames.size()),"5","Verify the total number of questions","Total number of questions are displayed as expected","Total number of questions are not displayed as expected");

            //Verify all the question names
            CustomAssert.assertEquals(printPreview.questionNames.get(0).getText().trim(),"True False "+questiontext,"Verify the question1 name","Question1 name is displayed as expected","Question1 name is not displayed as expected");
            CustomAssert.assertEquals(printPreview.questionNames.get(1).getText().trim(),"Multiple Choice "+questiontext,"Verify the question2 name","Question2 name is displayed as expected","Question2 name is not displayed as expected");
            CustomAssert.assertEquals(printPreview.questionNames.get(2).getText().trim(),"Multi Selection "+questiontext,"Verify the question3 name","Question3 name is displayed as expected","Question3 name is not displayed as expected");
            CustomAssert.assertEquals(printPreview.questionNames.get(3).getText().trim(),"Text Entry "+questiontext,"Verify the question4 name","Question4 name is displayed as expected","Question4 name is not displayed as expected");
            CustomAssert.assertTrue(printPreview.questionNames.get(4).getText().contains("Text Drop Down "+questiontext),"Verify the question5 name","Question5 name is displayed as expected","Question5 name is not displayed as expected");

            driver.close();
            driver.switchTo().window(winHandleBefore);

        }catch (Exception e ){
            Assert.fail("Exception in testcase 'printGradedStatusNonGradableAssignment' in class 'Print'", e);

        }
    }

    @Test(priority = 3,enabled = true)
    public void printGradedStatusGradableAssignment(){
        WebDriver driver=Driver.getWebDriver();
        try{
            ReportUtil.log("Description", "This test case validates the print page for the graded status if the assignment is gradable", "info");

            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);

            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            int dataIndex = 3;
            //String appendChar1 = "ajqd";

            String questiontextOnAssignmentSubmission = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String questiontextExplicitlyByTeacher = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(4));
            String assessmentNameOnAssignmentSubmission = ReadTestData.readDataByTagName("", "newassessmentname", Integer.toString(3));
            String assessmentNameExplicitlyByTeacher = ReadTestData.readDataByTagName("", "newassessmentname", Integer.toString(4));

            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963", dataIndex, false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create a class
            System.out.println(classCode);
            new Assignment().useExistingAssignment(dataIndex,appendChar1);//use existing assignment created in test case1 and assign to class

            new Assignment().useExistingAssignment(4,appendChar1);//use existing assignment created in test case1 and assign to class
            new Navigator().logout();//log out

            //Assignment status 'Turned In' for assignment2(Explicitly by teacher) and 'Graded' for assignment1(On assignment submission)
            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().navigateTo("assignment");//Navigate to assignment
            new Assignment().submitAssignmentWithMixResponse(4,0,5);//Submit assignment2 with all incorrect answers
            new Navigator().navigateTo("assignment");//Navigate to assignment
            new Assignment().submitAssignmentWithMixResponse(dataIndex,3,5);//Submit assignment1 with mix responses
            new Navigator().navigateTo("dashboard");
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page


            for(int i=0;i<assignments.getAssessmentNameList().size();i++)
            {
                if(assignments.getAssessmentNameList().get(i).getText().equals(assessmentNameOnAssignmentSubmission))
                {
                    assignments.viewResponse.get(i).click();//Click on assignment1(on assignment submission) view response
                    break;
                }
            }

            WebDriverUtil.waitTillVisibilityOfElement(studentResponse.printResponse,60);
            //Click on 'Release grades for all student's' should not appear
            boolean gradeReleaseOptionFound = new BooleanValue().presenceOfElementByWebElement(dataIndex,studentResponse.releaseGrade);
            CustomAssert.assertEquals(gradeReleaseOptionFound,false,"Verify the release option for assignment1(gradable on assignment submission)","Grade release option is not displayed as expected","Grade release option is displayed");

            studentResponse.checkboxInStudentCard.get(1).click();//Select student checkbox
            studentResponse.printResponse.click();//Click on print

            String winHandleBefore = driver.getWindowHandle();
            switchWindow();

            //Verify the student obtained marks (1 for starting 3 questions and 0 for remaining 2 questions)
            for(int i=0;i<printPreview.marksObtained.size();i++) {
                if (i > 2) {
                    if (!printPreview.marksObtained.get(i).getAttribute("value").equals("0")) {
                        CustomAssert.fail("Verify the marks obtained by student for question number " + i, "Marks is not displayed as expected for question " + i);
                    }
                } else if (!printPreview.marksObtained.get(i).getAttribute("value").equals("1")) {
                    CustomAssert.fail("Verify the marks obtained by student for question number " + i, "Marks is not displayed as expected for question " + i);
                }

                //Verify the total marks (for all the 5 questions)
                for (i = 0; i < printPreview.totalPoint.size(); i++) {
                    if (!printPreview.totalPoint.get(i).getText().equals("1")) {
                        CustomAssert.fail("Verify the total point for question number" + i, "Total point is not displayed as expected for question " + i);
                    }
                }
                //Verify the total number of questions
                CustomAssert.assertEquals(String.valueOf(printPreview.questionNames.size()), "5", "Verify the total number of questions", "Total number of questions are displayed as expected", "Total number of questions are not displayed as expected");

                //Verify all the question names
                CustomAssert.assertEquals(printPreview.questionNames.get(0).getText().trim(), "True False " + questiontextOnAssignmentSubmission, "Verify the question1 name", "Question1 name is displayed as expected", "Question1 name is not displayed as expected");
                CustomAssert.assertEquals(printPreview.questionNames.get(1).getText().trim(), "Multiple Choice " + questiontextOnAssignmentSubmission, "Verify the question2 name", "Question2 name is displayed as expected", "Question2 name is not displayed as expected");
                CustomAssert.assertEquals(printPreview.questionNames.get(2).getText().trim(), "Multi Selection " + questiontextOnAssignmentSubmission, "Verify the question3 name", "Question3 name is displayed as expected", "Question3 name is not displayed as expected");
                CustomAssert.assertEquals(printPreview.questionNames.get(3).getText().trim(), "Text Entry " + questiontextOnAssignmentSubmission, "Verify the question4 name", "Question4 name is displayed as expected", "Question4 name is not displayed as expected");
                CustomAssert.assertTrue(printPreview.questionNames.get(4).getText().contains("Text Drop Down " + questiontextOnAssignmentSubmission), "Verify the question5 name", "Question5 name is displayed as expected", "Question5 name is not displayed as expected");

                driver.close();
                driver.switchTo().window(winHandleBefore);

                new Navigator().navigateTo("assignment");//Navigate to assignment page

                for(int j=0;j<assignments.getAssessmentNameList().size();j++)
                {
                    if(assignments.getAssessmentNameList().get(j).getText().equals(assessmentNameExplicitlyByTeacher))
                    {
                        assignments.viewResponse.get(j).click();//Click on assignment2(Explicitly by teacher) view response
                        break;
                    }
                }

                studentResponse.releaseGrade.click();//Click on 'Release grades for all student's'
                Thread.sleep(3000);
                studentResponse.checkboxInStudentCard.get(1).click();//Select student checkbox
                studentResponse.printResponse.click();//Click on print

                switchWindow();

                //Verify the student obtained marks (0 for all the questions)
                for(i=0;i<printPreview.marksObtained.size();i++) {
                    if (!printPreview.marksObtained.get(i).getAttribute("value").equals("0")) {
                        CustomAssert.fail("Verify the marks obtained by student for question number "+i+1,"Marks is not displayed as expected for question "+i+1);
                    }
                }

                //Verify the total number of questions
                Thread.sleep(8000);
                int size = printPreview.questionNames.size() ;
                System.out.println(size);
                CustomAssert.assertEquals(size,5,"Verify the total number of questions", "Total number of questions are displayed as expected", "Total number of questions are not displayed as expected");

                //Verify all the question names
                CustomAssert.assertEquals(printPreview.questionNames.get(0).getText().trim(), "True False " + questiontextExplicitlyByTeacher, "Verify the question1 name", "Question1 name is displayed as expected", "Question1 name is not displayed as expected");
                CustomAssert.assertEquals(printPreview.questionNames.get(1).getText().trim(), "Multiple Choice " + questiontextExplicitlyByTeacher, "Verify the question2 name", "Question2 name is displayed as expected", "Question2 name is not displayed as expected");
                CustomAssert.assertEquals(printPreview.questionNames.get(2).getText().trim(), "Multi Selection " + questiontextExplicitlyByTeacher, "Verify the question3 name", "Question3 name is displayed as expected", "Question3 name is not displayed as expected");
                CustomAssert.assertEquals(printPreview.questionNames.get(3).getText().trim(), "Text Entry " + questiontextExplicitlyByTeacher, "Verify the question4 name", "Question4 name is displayed as expected", "Question4 name is not displayed as expected");
                CustomAssert.assertTrue(printPreview.questionNames.get(4).getText().contains("Text Drop Down " + questiontextExplicitlyByTeacher), "Verify the question5 name", "Question5 name is displayed as expected", "Question5 name is not displayed as expected");

            }

        }
        catch (Exception e ){
            Assert.fail("Exception in testcase 'printGradedStatusNonGradableAssignment' in class 'Print'", e);

        }
    }


    public void switchWindow(){
        WebDriver driver=Driver.getWebDriver();
        try{
            for(String winHandle : driver.getWindowHandles()){
                driver.switchTo().window(winHandle);
            }
            Thread.sleep(8000);
            //new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.id("view-user-question-performance-score-box")));


        }catch (Exception e){
            Assert.fail("Exception in testcase 'switchWindow' in class 'Print'", e);

        }
    }







}
