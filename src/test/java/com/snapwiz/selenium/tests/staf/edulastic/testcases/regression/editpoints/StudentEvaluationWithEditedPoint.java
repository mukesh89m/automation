package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.editpoints;

import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.detailedResponse.DetailedResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview.PrintPreview;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.yourresponse.YourResponse;
import com.snapwiz.selenium.tests.staf.edulastic.testcases.sanity.print.Print;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.KeysSend;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * Created by pragyas on 29-07-2016.
 */
public class StudentEvaluationWithEditedPoint extends Driver{


    @Test(priority = 1,enabled = true)
    public void studentEvaluationWithEditedPoint(){
        try{
            ReportUtil.log("Description", "This test case validates the student report with edited points", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 1;
            String appendChar= "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            TrueFalseQuestionCreation questionCreate = PageFactory.initElements(driver,TrueFalseQuestionCreation.class);
            Performance performance = PageFactory.initElements(driver,Performance.class);
            YourResponse yourResponse = PageFactory.initElements(driver,YourResponse.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver,DetailedResponse.class);
            PrintPreview printPreview = PageFactory.initElements(driver,PrintPreview.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classcode = new Classes().createClass(appendChar, dataIndex);//Create class

            new Assignment().create(dataIndex,"truefalse");//Create an assignment with true false question type
            new Assignment().addQuestion(dataIndex,"multiplechoice");//Add a question

            questionCreate.getPoint_editor().click();
            //Remove the point
//            new KeysSend().sendKeyBoardKeys("$");
//            new KeysSend().sendKeyBoardKeys("<");
//            Thread.sleep(1000);
            questionCreate.getPoint_textbox().clear();

            questionCreate.getPoint_textbox().sendKeys("2");//Enter the point
            questionCreate.button_savePoint.click();//Click on save button

            new Assignment().assignToStudent(dataIndex,appendChar);
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classcode,dataIndex);//Sign up as a student
            new Assignment().submitAssignmentWithMixResponse(dataIndex,1,2);//submit assignment

            WebDriverUtil.waitTillVisibilityOfElement(performance.getPerformancePercentage(),30);
            //Verify the percentage
            CustomAssert.assertEquals(performance.getPerformancePercentage().getText(),"33","Verify the perfomance percentage","Performance percentage is displayed as expected","Performance percentage is not displayed as expected");

            WebDriverUtil.waitTillVisibilityOfElement(performance.questionPoint.get(0),30);
            //Student report view

            // In the performance summary view, the points for each question should be displayed as assigned by the instructor
            CustomAssert.assertEquals(performance.questionPoint.get(0).getText(),"1","Verify the point for question1","Point is displayed as expected for question1","Point is not displayed as expected for question1");
            CustomAssert.assertEquals(performance.questionPoint.get(1).getText(),"2","Verify the point for question2","Point is displayed as expected for question2","Point is not displayed as expected for question2");

            //Verify the total points assigned
            CustomAssert.assertEquals(performance.totalScore.getText(),"3","Verify the total score assigned","Total score is displayed as expected","Total score is not displayed as expected");

            performance.getButton_Continue().click();//Click on continue button
            WebDriverUtil.waitForAjax(driver,60);
            WebDriverUtil.waitTillVisibilityOfElement(yourResponse.arrow_next,30);
            //For each question, the grading should take the points assigned by the instructor and the default points
            CustomAssert.assertEquals(yourResponse.totalScorePerQuestion.getText(),"1","Verify the default point for Q1","Default point is displayed for Q1 as expected","Default point is not displayed for Q1 as expected");
            WebDriverUtil.scrollIntoView(yourResponse.arrow_next,false);
            yourResponse.arrow_next.click();//Click on next arrow

            WebDriverUtil.waitTillVisibilityOfElement(yourResponse.arrow_previous,30);
            CustomAssert.assertEquals(yourResponse.totalScorePerQuestion.getText(),"2","Verify the assigned point for Q2","Assigned point is displayed for Q2 as expected","Assigned point is not displayed for Q2 as expected");

            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page

            System.out.println();
            assignments.viewResponse.get(0).click();//click on view response

            //The evaluation should be for the points set by the instructor and not the default points
            CustomAssert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(),"33%","Verify the performance percentage","Performance percentage is displayed as expected","Performance percentage is not displayed as expected");

            //The marks in the view response page should show the Points assigned by the instructor and not the default points
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(0).getText(),"1/3","Verify the score on student card","Score is displayed as expected","Score is not displayed as expected");

            studentResponse.viewDetailedResponse.get(0).click();//Click on view detailed response

            // For each question the grading should take the Points assigned by the instructor and not the default points
            CustomAssert.assertEquals(detailedResponse.totalScorePerQuestion.get(0).getText(),"1","Verify the default score for Q1 at instructor side","Default score for Q1 is displayed as expected","Default score for Q1 is not displayed as expected");
            CustomAssert.assertEquals(detailedResponse.totalScorePerQuestion.get(1).getText(),"2","Verify the assigned score for Q2 at instructor side","Assigned score for Q2 is displayed as expected","Assigned score for Q2 is not displayed as expected");

            detailedResponse.detailedResponseBackArrow.click();//click on back arrow

            WebDriverUtil.waitTillVisibilityOfElement(studentResponse.printResponse,30);
            studentResponse.checkboxInStudentCard.get(1).click();//click on student check box

            studentResponse.printResponse.click();//Click on print

            WebDriverUtil.waitForAjax(driver,60);
            new Print().switchWindow();


            //Print page
            // The assessment print page should print the grades based on the points assigned by the instructor
            CustomAssert.assertEquals(printPreview.totalScore.getText(),"1.00/3","Verify the marks obtained/Total marks","Total score is displayed as expected","Total score is not displayed as expected");

            //Verify the default point for Q1 and assigned point for Q2
            CustomAssert.assertEquals(printPreview.totalPoint.get(0).getText(),"1","Verify the default point for Q1","Default point for Q1 is displayed as expected","Default point for Q1 is not displayed as expected");
            CustomAssert.assertEquals(printPreview.totalPoint.get(1).getText(),"2","Verify the default point for Q2","Default point for Q2 is displayed as expected","Default point for Q2 is not displayed as expected");

            //Verify the marks obtained for Q1 and Q2
            CustomAssert.assertEquals(printPreview.marksObtained.get(0).getAttribute("value"),"1","Verify the marks obtained for Q1","Marks obtained for Q1 is displayed as expected","Marks obtained for Q1 is not displayed as expected");
            CustomAssert.assertEquals(printPreview.marksObtained.get(1).getAttribute("value"),"0","Verify the marks obtained for Q2","Marks obtained for Q2 is displayed as expected","Marks obtained for Q2 is not displayed as expected");

        }catch (Exception e)
        {
            Assert.fail("Exception in testcase 'studentEvaluationWithEditedPoint' in class 'EditPoints'", e);

        }
    }


}
