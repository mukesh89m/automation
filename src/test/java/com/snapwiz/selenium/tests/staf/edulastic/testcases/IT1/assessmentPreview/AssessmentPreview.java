package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.assessmentPreview;


import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 04-03-2015.
 */
public class AssessmentPreview extends Driver{
    @Test(priority = 1)
    public void assessmentPreview()
    {
        try
        {
            String appendChar = "a221";
            String appendChar1 ="b221";
            int dataIndex = 186;
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            ManageClass manageClass= PageFactory.initElements(driver, ManageClass.class);
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar, classCode, 1119);//create student
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar1, classCode, 1119);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"all");
            Thread.sleep(2000);
            new Navigator().navigateTo("assignment");
            driver.findElement(By.xpath("//span[@class='as-assignment-draftTxt']")).click();
            Thread.sleep(1000);
            new Click().clickBycssselector("span.as-edit");
            new Click().clickBycssselector("span.as-questionDetails-clickArrow");
            Assert.assertTrue(driver.findElement(By.xpath("//a[@class='as-question-preview-edit-button']")).isDisplayed(),"Exception in AssessmentPreview in method assessmentPreview");
            Assert.assertTrue(driver.findElement(By.xpath("//a[@class='as-question-preview-duplicate-button']")).isDisplayed(),"Exception in AssessmentPreview in method assessmentPreview");
            Assert.assertTrue(driver.findElement(By.xpath("//a[@class='as-question-preview-delete-button']")).isDisplayed(),"Exception in AssessmentPreview in method assessmentPreview");
            new Navigator().navigateTo("assignment");
            driver.findElement(By.xpath("//span[@class='as-assignment-draftTxt']")).click();
            Thread.sleep(1000);
            new Click().clickBycssselector("span.ed-icon.icon-preview");

            Thread.sleep(17000);
            driver.switchTo().frame(0);
            new Click().clickByXpath("//span[@class='true-false-student-answer-label']");
           // new Click().clickBycssselector("#as-take-next-question");
            driver.findElement(By.xpath("//span[@id='as-take-next-question']")).click();
            Thread.sleep(1000);
            try {
                while (driver.findElement(By.xpath("//span[@id='as-take-next-question']")).isDisplayed()) {
                    driver.findElement(By.xpath("//span[@id='as-take-next-question']")).click();
                    Thread.sleep(2000);

                }
            }
            catch (Exception e)
            {}

            driver.findElement(By.cssSelector("span[class='btn sty-blue submit-button']")).click();
            Thread.sleep(3000);
            Assert.assertTrue(driver.findElement(By.cssSelector("div#performance-bar-graph-wrapper-display-block")).isDisplayed(),"Exception in AssessmentPreview in method assessmentPreview");

            driver.switchTo().defaultContent();
            Thread.sleep(1000);
            new Click().clickBycssselector("span[class='as-modal-sprite-img as-close-modal']");
            Thread.sleep(2000);
            new Click().clickBycssselector("span.as-edit");
            new Click().clickBycssselector("span.as-questionDetails-clickArrow");
            Assert.assertTrue(driver.findElement(By.xpath("//a[@class='as-question-preview-edit-button']")).isDisplayed(),"Exception in AssessmentPreview in method assessmentPreview");
            Assert.assertTrue(driver.findElement(By.xpath("//a[@class='as-question-preview-duplicate-button']")).isDisplayed(),"Exception in AssessmentPreview in method assessmentPreview");
            Assert.assertTrue(driver.findElement(By.xpath("//a[@class='as-question-preview-delete-button']")).isDisplayed(),"Exception in AssessmentPreview in method assessmentPreview");
            new Assignment().assignToStudent(dataIndex,appendChar);//assign assessment to class
            Thread.sleep(2000);
            new Navigator().navigateTo("assignment");
            Thread.sleep(4000);
            new Click().clickBycssselector("span.ed-icon.icon-preview");
            Thread.sleep(17000);
            driver.switchTo().frame(0);
            new Click().clickByXpath("//span[@class='true-false-student-answer-label']");
            // new Click().clickBycssselector("#as-take-next-question");
            driver.findElement(By.xpath("//span[@id='as-take-next-question']")).click();
            Thread.sleep(1000);
            try {
                while (driver.findElement(By.xpath("//span[@id='as-take-next-question']")).isDisplayed()) {
                    System.out.println("sdsdfdsdfsd dsd");
                    driver.findElement(By.xpath("//span[@id='as-take-next-question']")).click();
                    Thread.sleep(2000);

                }
            }
            catch (Exception e)
            {}
            Assert.assertTrue(driver.findElement(By.cssSelector("div#performance-bar-graph-wrapper-display-block")).isDisplayed(),"Exception in AssessmentPreview in method assessmentPreview");
            driver.switchTo().defaultContent();
            Thread.sleep(1000);
            new Click().clickBycssselector("span[class='as-modal-sprite-img as-close-modal']");
            Thread.sleep(2000);


        }
        catch (Exception e)
        {
        Assert.fail("Exception in searchStudentAfterDelete Class in method searchStudentAfterDelete", e);        }
    }
}
