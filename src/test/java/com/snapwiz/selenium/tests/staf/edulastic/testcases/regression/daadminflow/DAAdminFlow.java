package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.daadminflow;

import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssignmentSummary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Performance;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.commonassessments.CommonAssessments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.detailedResponse.DetailedResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.districtassessmentresponse.DistrictAssessmentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by pragyas on 11-08-2016.
 */
public class DAAdminFlow extends Driver {

    public  static String totalScore=null;
    @Test(priority = 1, enabled = true)
    public void dAAdminFlowForAllClasses() {
        WebDriver driver = Driver.getWebDriver();
        try {
            ReportUtil.log("Description", "This test case verifies the DAAdmin flow assigned to all classes in the district", "info");

//            String appendChar = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
                String appendChar = "abS" ;

            System.out.println("appendChar:" + appendChar);
            int dataIndex = 1;
            String email = "ryanda@snapwiz.com";

            AssignmentSummary assignmentSummary = PageFactory.initElements(driver, AssignmentSummary.class);
            StudentResponse studentResponse = PageFactory.initElements(driver, StudentResponse.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            CommonAssessments commonAssessments = PageFactory.initElements(driver, CommonAssessments.class);
            DistrictAssessmentResponse districtAssessmentResponse = PageFactory.initElements(driver, DistrictAssessmentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver, DetailedResponse.class);
            Performance performance = PageFactory.initElements(driver, Performance.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            System.out.println("assessmentname:"+assessmentname);

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String insemail = methodName + "ins" + appendChar + "@snapwiz.com";

            new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode = new Classes().createClass(appendChar, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, dataIndex);//Sign up as a student
            new Navigator().logout();//log out*/

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Assignment().createByDA(dataIndex, "passage");// includes true false and essay
            new Assignment().addQuestion(dataIndex, "multipart");
            new Assignment().addQuestion(dataIndex, "graphplotter");
            new Assignment().addQuestion(dataIndex, "multipleselection");
            new Assignment().addQuestion(dataIndex, "matchthefollowing");
            new Assignment().addQuestion(dataIndex, "expressionevaluator");


            new Assignment().assignByDA(dataIndex, null);
            new Navigator().navigateTo("dashboard");// Navigate to dashboard
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            Thread.sleep(5000);
            assignments.open.get(0).click();//Click on open
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, dataIndex);//Login as a student
            new Assignment().submitAssignmentWithMixResponse(dataIndex, 4, 7);//Submit assignment
//            assignmentSummary.button_continue.click();//Click on continue button
            detailedResponse.detailedResponseBackArrow.click();
            Thread.sleep(1000);
            new Navigator().logout();


            new Login().loginAsInstructor(appendChar, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(1000);

            //Release grade by teacher should not be displayed
            boolean releaseGradeByTeacher = new BooleanValue().presenceOfElementByWebElement(dataIndex, studentResponse.releaseGrade);
            CustomAssert.assertEquals(releaseGradeByTeacher, false, "Verify the release grade option for instructor", "Release grade option is not displayed as expected", "Release grade option is displayed");

            //Explicitly by DAAdmin option should be displayed
            CustomAssert.assertEquals(studentResponse.explicitlyByDistrictAdmin.getText(), "Gradable - Explicitly by District Admin", "Verify the Explicitly by District Admin text for instructor", "Explicitly by District Admin  is displayed as expected", "Explicitly by District Admin is not displayed");

            WebDriverUtil.clickOnElementUsingJavascript(studentResponse.viewDetailedResponse.get(0));//Click on view detailed responses
            Thread.sleep(5000);
            WebDriverUtil.waitForAjax(driver,60);
            if( Properties.getPropertyValue("Browser").equals("FIREFOX")){
                driver.findElements(By.cssSelector("rect[width='40']")).get(1).click();
            }
            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                WebElement BarQ2 = driver.findElements(By.cssSelector("rect[width='40']")).get(1);
                new Actions(driver).moveToElement(BarQ2).click(BarQ2).perform();
            }
            Thread.sleep(2000);
            //Manually assign grade for essay type question(Q2)
            driver.findElement(By.xpath("(//div[@id='view-user-question-performance-score-box'])[2]")).click();
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("1");
            driver.findElement(By.cssSelector("body")).click();
            Thread.sleep(5000);
//            ((JavascriptExecutor)driver).executeScript("document.getElementsByClassName('btn btn-rounded btn-blue save-points save-score-feedback')[2].click()");
            driver.findElement(By.cssSelector("[class='btn btn-rounded btn-blue save-points save-score-feedback']")).click();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//*[text()='Saved']")), 60);
            Thread.sleep(5000);
            totalScore=studentResponse.total_score.getText().trim();
            new Navigator().logout();//log out

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            driver.findElement(By.cssSelector(".orange-count.awaiting-grades")).click(); //click on the awaiting grades
            int viewResponse=0;
            for (WebElement ca : commonAssessments.assessmentName) {
                if (ca.getText().equals(assessmentname)) {
                    break;
                }
                viewResponse++;
            }
            WebDriverUtil.clickOnElementUsingJavascript(commonAssessments.response_link.get(viewResponse)); //click on the view response link


            int grade=0;
            for (WebElement ele:commonAssessments.grades_status){
                if(ele.getText().trim().equals("Awaiting Grades")){
                    break;
                }
                grade++;
            }
            WebDriverUtil.clickOnElementUsingJavascript(commonAssessments.assignment_checkbox.get(grade+1));


            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("scroll(250, 0)"); //x value '250' can be altered
            WebDriverUtil.clickOnElementUsingJavascript(commonAssessments.releaseGrade);//Click on release grade button

            //Check the notification message
            CustomAssert.assertTrue(districtAssessmentResponse.notificationMsg.getText().replaceAll("[\\t\\n\\r]", " ").contains("There are questions that need to be manually graded before you release grades for this assessment. If you release grades now, the corresponding responses will be marked 0."), "Verify the notification message", "Notification message is displayed as expected", "Notification message is not displayed as expected");
            districtAssessmentResponse.button_yes.click();//Click on yes
            WebDriverUtil.waitTillVisibilityOfElement(districtAssessmentResponse.notificationMsg_gradeReleased, 20);
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(1000);

            //Check the score and percentage
            System.out.println("totalScore:"+totalScore);
            if(totalScore.equals("3.4")) {
                CustomAssert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(), "43%", "Verify the percentage", "Percentage is displayed as expected", "Percentage is not displayed as expected");
                CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(0).getText(), "3.4/8", "Verify the score on student card", "Score is displayed as expected", "Score is not displayed as expected");
            }
            else {
                CustomAssert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(), "38%", "Verify the percentage", "Percentage is displayed as expected", "Percentage is not displayed as expected");
                CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(0).getText(), "3/8", "Verify the score on student card", "Score is displayed as expected", "Score is not displayed as expected");
            }

            studentResponse.viewDetailedResponse.get(0).click();//Click on view detailed response
            Thread.sleep(5000);

            //Verify the manually graded question(Q2 as 1(assigned by instructor) and Q3(no marks assigned) as 0)
            CustomAssert.assertEquals(detailedResponse.scorePerQuestion.get(1).getAttribute("value"), "1", "Verify the score for Q2", "Score is displayed as expected for Q2", "Score is not displayed as expected");

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", detailedResponse.scorePerQuestion.get(2));
            CustomAssert.assertEquals(detailedResponse.scorePerQuestion.get(2).getAttribute("value"), "0", "Verify the score for Q3", "Score is displayed as expected for Q3", "Score is not displayed as expected");

            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");
            assignments.assignment.click();//Click on assignment

            //Verify score and percentage
            if(totalScore.equals("3.4")) {
                CustomAssert.assertEquals(performance.getPerformancePercentage().getText(),"43","Verify percentage","Percentage is displayed as expected","Percentage is not displayed as expected");
                CustomAssert.assertEquals(performance.getQuestionPerformanceScore().getText(),"3.4","Verify score obtained","Obtained score is displayed as expected","Obtained score is not displayed as expected");
            }
            else {
                CustomAssert.assertEquals(performance.getPerformancePercentage().getText(),"38","Verify percentage","Percentage is displayed as expected","Percentage is not displayed as expected");
                CustomAssert.assertEquals(performance.getQuestionPerformanceScore().getText(),"3","Verify score obtained","Obtained score is displayed as expected","Obtained score is not displayed as expected");
            }
            CustomAssert.assertEquals(performance.totalScore.getText(),"8","Verify total score","Total score is displayed as expected","Total score is not displayed as expected");

        } catch (Exception e) {
            Assert.fail("Exception in 'DAAdminFlow' in 'dAAdminFlowForAllClasses' method", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void dAAdminFlowForParticularClass() {
        WebDriver driver = Driver.getWebDriver();
        try {
            ReportUtil.log("Description", "This test case verifies the DAAdmin flow assigned to particular class in the district", "info");



            String appendChar1 = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);

//            String appendChar1="aZy";
//             String appendChar2="bZo";

            System.out.println("appendChar1:"+appendChar1);
            System.out.println("appendChar2:"+appendChar2);

            int dataIndex = 2;
            String email = "ryanda@snapwiz.com";

//            AssignmentSummary assignmentSummary = PageFactory.initElements(driver, AssignmentSummary.class);
            StudentResponse studentResponse = PageFactory.initElements(driver, StudentResponse.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            CommonAssessments commonAssessments = PageFactory.initElements(driver, CommonAssessments.class);
            DistrictAssessmentResponse districtAssessmentResponse = PageFactory.initElements(driver, DistrictAssessmentResponse.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver, DetailedResponse.class);
            Performance performance = PageFactory.initElements(driver, Performance.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            String methodName = new Exception().getStackTrace()[0].getMethodName();
//            String insemail = methodName + "ins" + appendChar1 + "@snapwiz.com";


            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
            System.out.println("classCode :" + classCode);
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1, classCode, dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new SignUp().teacher(appendChar2, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            new Classes().createClass(appendChar2, dataIndex);//Create class
            new Navigator().logout();//log out

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Assignment().createByDA(dataIndex, "passage");// includes true false and essay
            new Assignment().addQuestion(dataIndex, "multipart");
            new Assignment().addQuestion(dataIndex, "graphplotter");
            new Assignment().addQuestion(dataIndex, "multipleselection");
            new Assignment().addQuestion(dataIndex, "expressionevaluator");
            new Assignment().assignByDA(dataIndex, classCode);
            new Navigator().navigateTo("dashboard");// Navigate to dashboard
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.open.get(0).click();//Click on open
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1, dataIndex);//Login as a student
            new Assignment().submitAssignmentWithMixResponse(dataIndex, 4, 6);//Submit assignment
//            assignmentSummary.button_continue.click();//Click on continue button
            new Common().waitForToastBlockerToClose(60);
            detailedResponse.detailedResponseBackArrow.click();
            WebDriverUtil.waitForAjax(driver, 60);
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(1000);

            //Release grade by teacher should not be displayed
            boolean releaseGradeByTeacher = new BooleanValue().presenceOfElementByWebElement(dataIndex, studentResponse.releaseGrade);
            CustomAssert.assertEquals(releaseGradeByTeacher, false, "Verify the release grade option for instructor", "Release grade option is not displayed as expected", "Release grade option is displayed");

            //Explicitly by DAAdmin option should be displayed
            CustomAssert.assertEquals(studentResponse.explicitlyByDistrictAdmin.getText(), "Gradable - Explicitly by District Admin", "Verify the Explicitly by District Admin text for instructor", "Explicitly by District Admin  is displayed as expected", "Explicitly by District Admin is not displayed");

            new Navigator().logout();


            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(5000);
            studentResponse.viewDetailedResponse.get(0).click();//Click on view detailed responses
            Thread.sleep(5000);
            if( Properties.getPropertyValue("Browser").equals("FIREFOX")){
                driver.findElements(By.cssSelector("rect[width='40']")).get(1).click();
            }
            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                WebElement BarQ2 = driver.findElements(By.cssSelector("rect[width='40']")).get(1);
                new Actions(driver).moveToElement(BarQ2).click(BarQ2).perform();
            }
            Thread.sleep(2000);
            //Manually assign grade for essay type question(Q2
            driver.findElement(By.xpath("(//div[@id='view-user-question-performance-score-box'])[2]")).click();
            driver.findElement(By.xpath("//input[@data-id='score']")).clear();
            driver.findElement(By.xpath("//input[@data-id='score']")).sendKeys("1");

            driver.findElement(By.cssSelector("body")).click();
            Thread.sleep(5000);
            ((JavascriptExecutor)driver).executeScript("document.getElementsByClassName('btn btn-rounded btn-blue save-points save-score-feedback')[2].click()");
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//*[text()='Saved']")),60);
            Thread.sleep(5000);

            totalScore=studentResponse.total_score.getText().trim();
            new Navigator().logout();//log out*//*

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            driver.findElement(By.cssSelector(".orange-count.awaiting-grades")).click(); //click on the awaiting grades
            int viewResponse=0;
            for (WebElement ca : commonAssessments.assessmentName) {
                if (ca.getText().equals(assessmentname)) {
                    break;
                }
                viewResponse++;
            }
            WebDriverUtil.clickOnElementUsingJavascript(commonAssessments.response_link.get(viewResponse)); //click on the view response link

            //click on checkbox to release grade
            WebDriverUtil.clickOnElementUsingJavascript(commonAssessments.assignment_checkbox.get(1));

            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("scroll(250, 0)"); //x value '250' can be altered
            WebDriverUtil.clickOnElementUsingJavascript(commonAssessments.releaseGrade);//Click on release grade button

            //Check the notification message
            CustomAssert.assertTrue(districtAssessmentResponse.notificationMsg.getText().replaceAll("[\\t\\n\\r]", " ").contains("There are questions that need to be manually graded before you release grades for this assessment. If you release grades now, the corresponding responses will be marked 0."), "Verify the notification message", "Notification message is displayed as expected", "Notification message is not displayed as expected");
            districtAssessmentResponse.button_yes.click();//Click on yes
            WebDriverUtil.waitTillVisibilityOfElement(districtAssessmentResponse.notificationMsg_gradeReleased, 20);
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(1000);

            //Check the score and percentage
            System.out.println("totalScore:"+totalScore);
            if(totalScore.equals("3")) {
                CustomAssert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(), "43%", "Verify the percentage", "Percentage is displayed as expected", "Percentage is not displayed as expected");
                CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(0).getText(), "3/7", "Verify the score on student card", "Score is displayed as expected", "Score is not displayed as expected");
            }
            else {
                CustomAssert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(), "29%", "Verify the percentage", "Percentage is displayed as expected", "Percentage is not displayed as expected");
                CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(0).getText(), "2/7", "Verify the score on student card", "Score is displayed as expected", "Score is not displayed as expected");
            }

            studentResponse.viewDetailedResponse.get(0).click();//Click on view detailed response
            Thread.sleep(1000);

            //Verify the manually graded question(Q2 as 1(assigned by instructor) and Q3(no marks assigned) as 0)
            CustomAssert.assertEquals(detailedResponse.scorePerQuestion.get(1).getAttribute("value"), "0", "Verify the score for Q2", "Score is displayed as expected for Q2", "Score is not displayed as expected");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", detailedResponse.scorePerQuestion.get(3));
            CustomAssert.assertEquals(detailedResponse.scorePerQuestion.get(2).getAttribute("value"), "0", "Verify the score for Q3", "Score is displayed as expected for Q3", "Score is not displayed as expected");

            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1, dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");
            assignments.assignment.click();//Click on assignment

            //Verify score and percentage
            if(totalScore.equals("3")) {
                CustomAssert.assertEquals(performance.getPerformancePercentage().getText(),"43","Verify percentage","Percentage is displayed as expected","Percentage is not displayed as expected");
                CustomAssert.assertEquals(performance.getQuestionPerformanceScore().getText(),"3","Verify score obtained","Obtained score is displayed as expected","Obtained score is not displayed as expected");
            }
            else {
                CustomAssert.assertEquals(performance.getPerformancePercentage().getText(),"29","Verify percentage","Percentage is displayed as expected","Percentage is not displayed as expected");
                CustomAssert.assertEquals(performance.getQuestionPerformanceScore().getText(),"2","Verify score obtained","Obtained score is displayed as expected","Obtained score is not displayed as expected");
            }
            CustomAssert.assertEquals(performance.totalScore.getText(),"7","Verify total score","Total score is displayed as expected","Total score is not displayed as expected");

            new Navigator().navigateTo("dashboard");
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar2,dataIndex);//Login as an instructor2
            new Navigator().navigateTo("assignment");//Navigate to assignment

            //It should not show this assignment in assignments page
            CustomAssert.assertTrue(assignments.message_noAssignment.getText().contains("No Assignments Available For Your Class"),"Verify the assignment for instructor2","Assignmetn is not displayed","Assignment is displayed");


        } catch (Exception e) {
            Assert.fail("Exception in 'DAAdminFlow' in 'dAAdminFlowForParticularClass' method", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void dAAdminFlowAssignedByInstructor() {
        WebDriver driver = Driver.getWebDriver();
        try {
            ReportUtil.log("Description", "This test case verifies the DAAdmin flow assigned to particular class in the district by instructor", "info");

            String appendChar = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
//            String appendChar="aGI";

            System.out.println("appendChar:"+appendChar);

            int dataIndex = 3;

            AssignmentSummary assignmentSummary = PageFactory.initElements(driver, AssignmentSummary.class);
            StudentResponse studentResponse = PageFactory.initElements(driver, StudentResponse.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            DetailedResponse detailedResponse = PageFactory.initElements(driver, DetailedResponse.class);
            Performance performance = PageFactory.initElements(driver, Performance.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode = new Classes().createClass(appendChar, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as an instructor
            new Assignment().useExistingAssignment(dataIndex, appendChar);//Assign assessment to class created by DAAdmin i above test case
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, dataIndex);//Login as a student
            new Assignment().submitAssignmentWithMixResponse(dataIndex, 4, 6);//Submit assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignmentSummary.button_continue);//Click on continue button
            Thread.sleep(1000);
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assignments.viewResponse.get(0).click();//Click on view response
            studentResponse.viewDetailedResponse.get(0).click();//Click on view detailed responses
            Thread.sleep(9000);
            //Manually assign grade for essay type question(Q2)
            WebDriverUtil.waitUntilTextToBePresentInElement(driver.findElement(By.xpath("//div[text()='Performance by Questions']")),"Performance by Questions",60);

            if( Properties.getPropertyValue("Browser").equals("FIREFOX")){
                driver.findElements(By.cssSelector("rect[width='40']")).get(1).click();
            }
            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                WebElement BarQ2 = driver.findElements(By.cssSelector("rect[width='40']")).get(1);
                new Actions(driver).moveToElement(BarQ2).click(BarQ2).perform();
            }
            Thread.sleep(2000);            //Manually assign grade for essay type question(Q2
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("#qId-1+div [data-id='score']")));
            driver.findElement(By.xpath("//input[@data-id='score']")).clear();
            driver.findElement(By.xpath("//input[@data-id='score']")).sendKeys("1");
            driver.findElement(By.cssSelector("#qId-1+div .question-score")).click();
            Thread.sleep(2000);
            ((JavascriptExecutor)driver).executeScript("document.getElementsByClassName('btn btn-rounded btn-blue save-points save-score-feedback')[2].click()");
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//*[text()='Saved']")),60);
            Thread.sleep(2000);
            detailedResponse.backIcon.click();//Click on back icon
            WebDriverUtil.waitTillVisibilityOfElement(studentResponse.releaseGrade, 30);
            WebDriverUtil.clickOnElementUsingJavascript(studentResponse.releaseGrade);

            //Verify notification message
            CustomAssert.assertEquals(studentResponse.notificationMsg_redirect.getText(), "You can't release grades until you assign marks for manually graded questions.", "Verify the notification message", "Notification is displayed as expected", "Notification is not displayed as expected");

            studentResponse.viewDetailedResponse.get(0).click();//Click on view detailed responses
            //Manually assign grade for essay type question(Q3)
            Thread.sleep(9000);
            //driver.findElements(By.cssSelector("rect[width='40']")).get(2).click();

            if( Properties.getPropertyValue("Browser").equals("FIREFOX")){
                driver.findElements(By.cssSelector("rect[width='40']")).get(2).click();
            }
            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                WebElement BarQ2 = driver.findElements(By.cssSelector("rect[width='40']")).get(2);
                new Actions(driver).moveToElement(BarQ2).click(BarQ2).perform();
            }
            Thread.sleep(2000);
            //Manually assign grade for essay type question(Q2
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("#qId-2+div [data-id='score']")));
            driver.findElement(By.xpath("//input[@data-id='score']")).clear();
            driver.findElement(By.xpath("//input[@data-id='score']")).sendKeys("1");
            driver.findElement(By.cssSelector("#qId-2+div .question-score")).click();
            Thread.sleep(5000);
            ((JavascriptExecutor)driver).executeScript("document.getElementsByClassName('btn btn-rounded btn-blue save-points save-score-feedback')[4].click()");
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//*[text()='Saved']")), 60);

            totalScore=studentResponse.total_score.getText().trim();
            System.out.println("totalScore:"+totalScore);

            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("scroll(250, 0)"); //x value '250' can be altered

            detailedResponse.backIcon.click();//Click on back icon
            WebDriverUtil.waitForAjax(driver, 60);
            WebDriverUtil.waitUntilTextToBePresentInElement(driver.findElement(By.xpath("//button[text()=' Release Grades for all Student(s) ']")), "Release Grades for all Student(s)",60);
            driver.findElement(By.xpath("//button[text()=' Release Grades for all Student(s) ']")).click();

            //Check the score and percentage
            if(totalScore.equals("4.4")) {
                CustomAssert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(), "55%", "Verify the percentage", "Percentage is displayed as expected", "Percentage is not displayed as expected");
                CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(0).getText(), "4.4/8", "Verify the score on student card", "Score is displayed as expected", "Score is not displayed as expected");
            }
            else {
                CustomAssert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(), "57%", "Verify the percentage", "Percentage is displayed as expected", "Percentage is not displayed as expected");
                CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(0).getText(), "4/7", "Verify the score on student card", "Score is displayed as expected", "Score is not displayed as expected");

            }
            studentResponse.viewDetailedResponse.get(0).click();//Click on view detailed response
            WebDriverUtil.waitForAjax(driver, 60);

            //Verify the manually graded question(Q2 and Q3 as 1 and 1 respectively)
            CustomAssert.assertEquals(detailedResponse.scorePerQuestion.get(1).getAttribute("value"), "1", "Verify the score for Q2", "Score is displayed as expected for Q2", "Score is not displayed as expected");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", detailedResponse.scorePerQuestion.get(3));
            CustomAssert.assertEquals(detailedResponse.scorePerQuestion.get(2).getAttribute("value"), "1", "Verify the score for Q3", "Score is displayed as expected for Q3", "Score is not displayed as expected");

            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");
            assignments.assignment.click();//Click on assignment

            //Verify score and percentage
            if(totalScore.equals("4.4")) {
                CustomAssert.assertEquals(performance.getPerformancePercentage().getText(),"55","Verify percentage","Percentage is displayed as expected","Percentage is not displayed as expected");
                CustomAssert.assertEquals(performance.getQuestionPerformanceScore().getText(),"4.4","Verify score obtained","Obtained score is displayed as expected","Obtained score is not displayed as expected");
            }
            else {
                CustomAssert.assertEquals(performance.getPerformancePercentage().getText(),"57","Verify percentage","Percentage is displayed as expected","Percentage is not displayed as expected");
                CustomAssert.assertEquals(performance.getQuestionPerformanceScore().getText(),"4","Verify score obtained","Obtained score is displayed as expected","Obtained score is not displayed as expected");
            }
            CustomAssert.assertEquals(performance.totalScore.getText(),"7","Verify total score","Total score is displayed as expected","Total score is not displayed as expected");

        } catch (Exception e) {
            Assert.fail("Exception in 'DAAdminFlow' in 'dAAdminFlowAssignedByInstructor' method", e);
        }
    }
}
