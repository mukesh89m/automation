package com.snapwiz.selenium.tests.staf.edulastic.testcases.smoke.assignmentflow;

import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Login;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AddQuestion;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assessments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.QuestionTypesPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Stack;

import static com.snapwiz.selenium.tests.staf.edulastic.testcases.smoke.assignmentflow.AttempMathmlQuestion.noOfUser;

/**
 * Created by mukesh on 19/10/16.
 */
public class AttempQuestion extends Driver{


    AddQuestion addQuestion=null;
    Assessments assessments=null;
    SchoolPage schoolPage=null;
    QuestionTypesPage questionTypesPage=null;
    public static String appendChar=null;
    Stack stack=new Stack();
    @BeforeMethod
    public void init(){
        WebDriver driver=Driver.getWebDriver();
        addQuestion= PageFactory.initElements(driver,AddQuestion.class);
        assessments = PageFactory.initElements(driver, Assessments.class);
        schoolPage = PageFactory.initElements(driver, SchoolPage.class);
        questionTypesPage=PageFactory.initElements(driver,QuestionTypesPage.class);



    }

    @BeforeClass
    public void initCount(){
        for(int i=40;i<=50;i++){
            stack.push(new Integer(i));
        }
    }


    @Test(enabled = true,threadPoolSize = 10, invocationCount = 10)
    public void createQuestion() throws InterruptedException {

        appendChar="oi";
        System.out.println("appendchar "+appendChar);
        WebDriver driver= Driver.getWebDriver();
        int dataIndex = 2;
        String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

        System.out.println(Thread.currentThread().getName());
        // String no=Thread.currentThread().getName().split("-")[1];
        Integer no=(Integer)stack.pop();
        System.out.println("Student " + no + " is attempting ...");
        new Login().loginAsStudent(appendChar + no, dataIndex);//log in as student 1
        driver.get("http://10.0.0.68/#assignment/close");
        List<WebElement> assessmentss = driver.findElements(By.cssSelector("h4.as-title"));  //click on the assignment name
        for (WebElement ele:assessmentss){
            if (ele.getAttribute("title").trim().equals(assessmentName)){
                ele.click();
                break;
            }
        }
        WebDriverUtil.waitForAjax(driver, 120);
        //Attemp 1st question
//        new WebDriverWait(driver,60).until(ExpectedConditions.textToBePresentInElementLocated(By.className(".slider-viewport"), "Question 1 of 10"));
        WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("wrs_focusElement")),60);
        driver.findElement(By.xpath("//input[@class='wrs_focusElement'][@type='text']")).sendKeys("y=24x");
        System.out.println("Question 1 attempted");
        submitAnswer();

        //Attemp 2nd question
//        new WebDriverWait(driver,60).until(ExpectedConditions.textToBePresentInElementLocated(By.className(".slider-viewport"),"Question 2 of 10"));
        WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("wrs_focusElement")),60);
        driver.findElement(By.xpath("//input[@class='wrs_focusElement'][@type='text']")).sendKeys("y=2x");
        System.out.println("Question 2 attempted");
        submitAnswer();

        //Attemp 3rd question
//        new WebDriverWait(driver,60).until(ExpectedConditions.textToBePresentInElementLocated(By.className(".slider-viewport"),"Question 3 of 10"));
        WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("wrs_focusElement")),60);
        driver.findElement(By.xpath("//input[@class='wrs_focusElement'][@type='text']")).sendKeys("y=2x");
        submitAnswer();
        System.out.println("Question 3 attempted");

        //Attemp 4th question
//        new WebDriverWait(driver,60).until(ExpectedConditions.textToBePresentInElementLocated(By.className(".slider-viewport"),"Question 4 of 10"));
        WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("wrs_focusElement")),60);
        driver.findElement(By.xpath("//input[@class='wrs_focusElement'][@type='text']")).sendKeys("y=2x");
        submitAnswer();
        System.out.println("Question 4 attempted");

        //Attemp 5th question
//        new WebDriverWait(driver,60).until(ExpectedConditions.textToBePresentInElementLocated(By.className(".slider-viewport"),"Question 5 of 10"));
        WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("wrs_focusElement")),60);
        driver.findElement(By.xpath("//input[@class='wrs_focusElement'][@type='text']")).sendKeys("y=2x");
        submitAnswer();
        System.out.println("Question 5 attempted");

        //Attemp 6st question
//        new WebDriverWait(driver,60).until(ExpectedConditions.textToBePresentInElementLocated(By.className(".slider-viewport"),"Question 6 of 10"));
        WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("wrs_focusElement")),60);
        driver.findElement(By.xpath("//input[@class='wrs_focusElement'][@type='text']")).sendKeys("y=2x");
        submitAnswer();
        System.out.println("Question 6 attempted");

        //Attemp 7nd question
//        new WebDriverWait(driver,60).until(ExpectedConditions.textToBePresentInElementLocated(By.className(".slider-viewport"),"Question 7 of 10"));
        WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("wrs_focusElement")),60);
        driver.findElement(By.xpath("//input[@class='wrs_focusElement'][@type='text']")).sendKeys("y=2x");
        submitAnswer();
        System.out.println("Question 7 attempted");

        //Attemp 8rd question
//        new WebDriverWait(driver,60).until(ExpectedConditions.textToBePresentInElementLocated(By.className(".slider-viewport"),"Question 8 of 10"));
        WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("wrs_focusElement")),60);
        driver.findElement(By.xpath("//input[@class='wrs_focusElement'][@type='text']")).sendKeys("y=2x");
        submitAnswer();
        System.out.println("Question 8 attempted");

        //Attemp 9th question
//        new WebDriverWait(driver,60).until(ExpectedConditions.textToBePresentInElementLocated(By.className(".slider-viewport"),"Question 9 of 10"));
        WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("wrs_focusElement")),60);
        driver.findElement(By.xpath("//input[@class='wrs_focusElement'][@type='text']")).sendKeys("y=2x");
        submitAnswer();
        System.out.println("Question 9 attempted");

        //Attemp 10th question
//        new WebDriverWait(driver,60).until(ExpectedConditions.textToBePresentInElementLocated(By.className(".slider-viewport"),"Question 10 of 10"));
        WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("wrs_focusElement")),60);
        driver.findElement(By.xpath("//input[@class='wrs_focusElement'][@type='text']")).sendKeys("-0.2z");
        Thread.sleep(9000);
        System.out.println("Question 10 attempted");
        WebDriverUtil.waitUntilTextToBePresentInElement(driver.findElement(By.cssSelector("span[class^='m-l-sm save submit-button green-bg']>span")), "Submit", 60);
        WebDriverUtil.waitForAjax(driver, 60);
        driver.findElement(By.cssSelector("span[class^='m-l-sm save submit-button green-bg']>span")).click(); //click on the submit button
        submitAnswer();
        System.out.println("Student "+ no +" has completed the assignment.");

    }

    public void submitAnswer(){
        WebDriver driver= Driver.getWebDriver();
        try {
            Thread.sleep(4000);
            driver.findElement(By.id("as-take-next-question")).click();//click on next
            WebDriverUtil.waitForAjax(driver,60);
            Thread.sleep(5000);

        }
        catch (Exception e) {
            try {
                WebDriverUtil.waitForAjax(driver,60);
                new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'btn sty-blue submit')]")));
                driver.findElement(By.xpath("//span[contains(@class,'btn sty-blue submit')]")).click();//click on Submit

            }
            catch (Exception e1) {
                Assert.fail("Exception in clicking Finish button while attempting the assignment",e1);
            }
        }
    }
}
