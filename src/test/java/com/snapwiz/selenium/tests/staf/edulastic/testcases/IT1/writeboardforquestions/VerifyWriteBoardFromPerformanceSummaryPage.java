
package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.writeboardforquestions;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


/*
 * Created by sumit on 17/12/14.
 */

public class VerifyWriteBoardFromPerformanceSummaryPage extends Driver{
    
    @Test(priority = 1, enabled = true)
    public void verifyWriteBoardFromPerformanceSummaryPage()
    {
        try 
        {
            String appendChar = "c2";
            int dataIndex = 84;
            new SignUp().teacher(appendChar, dataIndex);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//log in as teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, dataIndex);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//log in as teacher
            new Assignment().create(dataIndex, "all");//create an Assignment
            new Assignment().assignToStudent(dataIndex, appendChar);//assign to student1 as gradable assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, dataIndex);//log in as student
            new Assignment().openAssignment(dataIndex);//open assignment
            String str = new RandomString().randomstring(5);
            int timer = 1;
            while (timer != 0) {
                new WriteBoard().enterTextInWriteBoard(str, dataIndex);
                new Assignment().clickOnNextButton(dataIndex);
                try {
                    Thread.sleep(2000);
                    driver.findElement(By.cssSelector("span[id='timer-label']"));
                } catch (Exception e) {
                    timer = 0;
                }
            }
            new Click().clickByclassname("student-report-continue-button");//click on Continue button
            for(int i= 0; i<20; i++){
                String str1 = new TextFetch().textfetchbyid("show-your-work-label");
                Assert.assertEquals(str1, "View your work", "\"View your work\" link is not shown for Write board in "+i+"th type question.");
                boolean dataFound;
                dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
                Assert.assertEquals(dataFound, true, "Writeboard data is not saved for "+i+"th type question.");
                String str2 = new TextFetch().textfetchbyid("show-your-work-label");
                Assert.assertEquals(str2, "Hide your work", "\"Hide your work\" link is not shown for Write board in "+i+"th type question.");

                try {
                    driver.findElement(By.id("next-question-performance-view"));
                    new Click().clickByid("next-question-performance-view");//click on Arrow
                    Thread.sleep(2000);
                } catch (Exception e) {
                    //empty catch block
                }
            }


        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase verifyWriteBoardFromPerformanceSummaryPage in class VerifyWriteBoardFromPerformanceSummaryPage.", e);
        }
    }
}



