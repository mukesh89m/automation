package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.classManagement;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentQuestionsListPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 03-03-2015.
 */
public class SearchStudentAfterDelete extends Driver{
    @Test(priority = 0)
    public void searchStudentAfterDelete()
    {
        try {
            String appendChar = "a8";
            String appendChar1 ="b8";
            int dataIndex = 185;
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            AssessmentQuestionsListPage assessmentQuestionsListPage=PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            ManageClass manageClass= PageFactory.initElements(driver,ManageClass.class);
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//logout

            new SignUp().studentDirectRegister(appendChar, classCode, 1119);//create student
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar1, classCode, 1119);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("manageclass");//navigate to manageclass
            Thread.sleep(2000);
            manageClass.getSettings().click();
            //click on edit
            manageClass.getEditClass().click();
            Thread.sleep(3000);
            //click on delete student from the class
            Actions action=new Actions(driver);
            WebDriverWait wait=new WebDriverWait(driver,30);
            action.moveToElement(manageClass.getStudentNames().get(0)).build().perform();
            action.moveToElement(manageClass.getDeleteStudent()).click().build().perform();
            wait.until(ExpectedConditions.visibilityOf(manageClass.getYesDelete()));
            //type delete in confirm screen
            manageClass.getDeleteConfirmText().sendKeys("DELETE");
            //click on yes delete option
            manageClass.getYesDelete().click();
            Thread.sleep(2000);
            manageClass.getSaveTheClass().click();
            Thread.sleep(3000);
            new Assignment().create(dataIndex,"truefalse");//create assessment with one question
            Thread.sleep(1000);
            new Click().clickBycssselector("div[class='lsm-createAssignment-done selected']");
            new Click().clickByid("assessments-use-button");//click on next button to assign
            Thread.sleep(2000);
            driver.findElement(By.className("maininput")).clear();
            String appendCharacterBuild=System.getProperty("UCHAR");

            driver.findElement(By.className("maininput")).sendKeys("searchStudentAfterDeletest"+appendChar1+appendCharacterBuild);








        }
        catch (Exception e)
        {
            Assert.fail("Exception in searchStudentAfterDelete Class in method searchStudentAfterDelete",e);
        }

    }
}
