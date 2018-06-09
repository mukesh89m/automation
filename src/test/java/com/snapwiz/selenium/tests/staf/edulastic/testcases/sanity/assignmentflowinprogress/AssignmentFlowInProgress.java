package com.snapwiz.selenium.tests.staf.edulastic.testcases.sanity.assignmentflowinprogress;

import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;

import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.TakeAssignment;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by pragyas on 19-05-2016.
 */
public class AssignmentFlowInProgress extends Driver {

    String appendChar = "d";
    TakeAssignment takeAssignment;
    WebDriver driver;

    @BeforeMethod
    public void init(){
        driver=Driver.getWebDriver();
        takeAssignment = PageFactory.initElements(driver,TakeAssignment.class);
    }

    @Test(priority = 1,enabled = true)
    public void assignmentFlowInProgress(){

        try{

            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);

            int dataIndex = 1;

            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as an instructor
            new Assignment().create(dataIndex,"truefalse");//Create an assignment
            new Assignment().addQuestion(dataIndex, "multiplechoice");
            new Assignment().addQuestion(dataIndex,"multipleselection");
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as an instructor
            new Assignment().openAssignment(dataIndex);
            takeAssignment.closeIcon.click();//click on close icon
            new Navigator().logout();

        }catch (Exception e){
            Assert.fail("Exception in testcase 'assignmentFlowInProgress' in class 'AssignmentFlowInProgress'", e);

        }
    }



    //Use above test case for student email id
    @Test(priority = 2,enabled = true)
    public void studentAttemptingInProgressAssignment(){

        try{
            ReportUtil.log("Description", "This test case validates the student attempt flow while the assignment is in 'in progress' status", "info");

            String studentMailId = "assignmentFlowInProgress"+"st"+appendChar+"@snapwiz.com";
            int dataIndex = 1;
            new Login().directLoginAsStudent(dataIndex, studentMailId);//Login as an instructor
            new Assignment().submitAssignment(dataIndex);//Submit assignment (In Progress)
            //Verify the performance summary page
            CustomAssert.assertEquals(takeAssignment.labels_performance.get(0).getText(),"Performance Summary","Verify the performance summary page","Performance summary page is displayed as expected","Performance summary page is not displayed");

        }catch (Exception e)
        {
            Assert.fail("Exception in testcase 'studentAttemptingInProgressAssignment' in class 'AssignmentFlowInProgress'", e);

        }
    }

}
