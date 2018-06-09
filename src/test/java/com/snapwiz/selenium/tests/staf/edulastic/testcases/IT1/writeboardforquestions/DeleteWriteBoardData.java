package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.writeboardforquestions;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Created by sumit on 18/12/14.
 */
public class DeleteWriteBoardData extends Driver{

    @Test(priority = 1, enabled = true)
    public void deleteWriteBoardData()
    {
        try{
            String appendChar = "a";
            int dataIndex = 109;

            new SignUp().teacher(appendChar, dataIndex);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//log in as teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, dataIndex);//create student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//log in as teacher
            new Assignment().create(dataIndex, "truefalse");
            new Assignment().addQuestion(dataIndex, "truefalse");

            new Assignment().assignToStudent(dataIndex, appendChar);//assign to student
            new Navigator().logout();//log out


            new Login().loginAsStudent(appendChar, dataIndex);
            new Assignment().openAssignment(dataIndex);//open assignment
            String str = new RandomString().randomstring(5);
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);

            new Assignment().clickOnNextButton(dataIndex);
            new Assignment().clickOnQuestion(0, dataIndex);//come back to the question
            new WriteBoard().deleteWriteBoardData();//delete all Data from write board
            new Assignment().clickOnNextButton(dataIndex);
            new Assignment().clickOnQuestion(0, dataIndex);//come back to the question
            boolean dataFound;
            dataFound = new WriteBoard().verifyWriteBoardDataIsDeleted(str);
            Assert.assertEquals(dataFound, false, "Writeboard data is not deleted.");

            new Assignment().clickOnNextButton(dataIndex);
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);
            new Assignment().clickOnNextButton(dataIndex);
            new Click().clickByclassname("student-report-continue-button");//click on Continue button
            new Click().clickByid("next-question-performance-view");//click on Arrow
            new Click().clickByid("show-your-work-label");//Click on "Show your work"

            String str1 = new TextFetch().textfetchbyid("show-your-work-label");
            Assert.assertEquals(str1, "Hide your work", "\"Hide your work\" link is not shown for question.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase deleteWriteBoardData in class DeleteWriteBoardData.", e);
        }
    }
}
