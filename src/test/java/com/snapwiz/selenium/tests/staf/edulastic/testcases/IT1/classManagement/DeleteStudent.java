package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.classManagement;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 02-03-2015.
 */
public class DeleteStudent extends Driver{

    @Test(priority = 1)
    public void deleteStudent()
    {
        try {
            String appendChar = "a7";
            String appendChar1 ="b7";
            String appendChar2 ="c7";
            int dataIndex = 14;
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
            new SignUp().studentDirectRegister(appendChar2, classCode, 1119);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("manageclass");
            Thread.sleep(5000);
            manageClass.getSettings().click();
            manageClass.getEditClass().click();
            WebDriverWait wait=new WebDriverWait(driver,30);
            Thread.sleep(4000);
            Actions action=new Actions(driver);
            Assert.assertTrue(manageClass.getStudentNames().size() == 3, "Exception in ClassManagement in method classManagement");
            action.moveToElement(manageClass.getStudentNames().get(0)).build().perform();
            action.moveToElement(manageClass.getDeleteStudent()).click().build().perform();
            wait.until(ExpectedConditions.visibilityOf(manageClass.getYesDelete()));
            manageClass.getDeleteConfirmText().sendKeys("DELETE");
            manageClass.getYesDelete().click();
            Thread.sleep(2000);
            manageClass.getSaveTheClass().click();
            Thread.sleep(3000);
            manageClass.getSettings().click();
            manageClass.getEditClass().click();
            Thread.sleep(4000);
            Assert.assertTrue(manageClass.getStudentNames().size() == 2, "Exception in ClassManagement in method classManagement");
            action.moveToElement(manageClass.getStudentNames().get(0)).build().perform();
            action.moveToElement(manageClass.getDeleteStudent()).click().build().perform();
            wait.until(ExpectedConditions.visibilityOf(manageClass.getYesDelete()));
            manageClass.getDeleteConfirmText().sendKeys("DELETE");
            manageClass.getYesDelete().click();
            Thread.sleep(2000);
            manageClass.getSaveTheClass().click();
            Thread.sleep(3000);
            manageClass.getSettings().click();
            manageClass.getEditClass().click();
            action.moveToElement(manageClass.getStudentNames().get(0)).build().perform();
            action.moveToElement(manageClass.getDeleteStudent()).click().build().perform();
            wait.until(ExpectedConditions.visibilityOf(manageClass.getYesDelete()));
            manageClass.getDeleteConfirmText().sendKeys("DELETE");
            manageClass.getYesDelete().click();
            Thread.sleep(2000);
            manageClass.getSaveTheClass().click();
            Thread.sleep(3000);
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("manageclass");
            Thread.sleep(5000);
            Assert.assertTrue(manageClass.getStudentNames().size() == 0, "Exception in ClassManagement in method classManagement");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in DeleteStudent Class in method deleteStudent",e);
        }

    }
}
