package com.snapwiz.selenium.tests.staf.edulastic.testcases.sanity.assignmentflowwithreport;

import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssignmentSummary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.detailedResponse.DetailedResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.reports.DetailedMasteryreport;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.reports.MyReports;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.reports.MySkillReport;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.yourresponse.YourResponse;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 16-02-2016.
 */
public class AssignmentFlowWithReport extends Driver {


    @Test(priority = 1 , enabled = true)
    public void gradableAssignmentFlowByInstructor()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            ReportUtil.log("Description", "This test case validates the creation of gradable assessment and assign to class,submitted by students and report verification", "info");
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            AssignmentSummary assignmentSummary = PageFactory.initElements(driver, AssignmentSummary.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            YourResponse yourResponse=PageFactory.initElements(driver,YourResponse.class);
            MyReports MyReports = PageFactory.initElements(driver,MyReports.class);
            DetailedResponse detailedResponse=PageFactory.initElements(driver,DetailedResponse.class);
            DetailedMasteryreport detailedMasteryreport=PageFactory.initElements(driver,DetailedMasteryreport.class);
            MySkillReport mySkillReport=PageFactory.initElements(driver,MySkillReport.class);

            int dataIndex = 1;
            String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
//            String appendChar="aYdn";
            System.out.println("appendChar:"+appendChar);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create an assessment with 10 different types of questions


            new Assignment().create(dataIndex, "passage");
            new Assignment().addQuestion(dataIndex, "multipart");
            new Assignment().addQuestion(dataIndex,"graphplotter");
            new Assignment().addQuestion(dataIndex,"matchthefollowing");
            new Assignment().addQuestion(dataIndex, "multipleselection");
            new Assignment().addQuestion(dataIndex,"expressionevaluator");
            new Assignment().addQuestion(dataIndex,"labelanimagedropdown");
            new Assignment().addQuestion(dataIndex,"graphing");
            new Assignment().addQuestion(dataIndex,"classification");
            new Assignment().addQuestion(dataIndex,"clozematrix");
            new Assignment().addQuestion(dataIndex,"fractioneditor");
            new Assignment().addQuestion(dataIndex,"clozeformula");
            new Assignment().addQuestion(dataIndex,"sentencecorrection");


            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class
            new Navigator().logout();//log out


            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Assignment().submitAssignmentWithMixResponse(dataIndex,13,20);
            //Verify the percentage of student Should not display
            if(!assignmentSummary.percentage.get(0).getText().equals("Questions"))
            {
                CustomAssert.fail("Percentage should not display","Percentage is displaying ");
            }


            //Verify the percentage of student

            //click on continue
            assignmentSummary.button_continue.click();//Click on continue button
            Thread.sleep(3000);
            CustomAssert.assertEquals(yourResponse.scoreByQuestion.size(), 0, "Score should not display for every question", "Score is not displaying", "Score is displaying");


            new Navigator().logout();//log out


            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(4000);
            //Verify performance score at instructor side
            CustomAssert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText().replaceAll("[\\t\\n\\r]"," "), "52%", "Verify performance score at instructor side", "Performance percentage is displayed as expected", "Performance percentage is not displayed as expected");
            //Verify score and percentage on student card
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(0).getText(),"11/21","Verify score of student at instructor side","Score is displayed as expected","Score is not displayed as expected");
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(1).getText().substring(0,2),"52","Verify percentage of student at instructor side","Percentage is displayed as expected","Percentage is not displayed as expected");
            //assign grade to essay type question

            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                WebElement BarQ2 = driver.findElements(By.cssSelector("rect[width='30']")).get(4); //Click on 4th essay bar
                new Actions(driver).moveToElement(BarQ2).click(BarQ2).perform();
            }
            //Manually assign grade for essay type question(Q2)
            driver.findElement(By.xpath("(//div[@id='view-user-question-performance-score-box'])[1]")).click();
            driver.findElement(By.xpath("//input[@data-id='score']")).clear();
            driver.findElement(By.xpath("//input[@data-id='score']")).sendKeys("1");
            driver.findElement(By.cssSelector("li[class^='question-link-label ']")).click();
            Thread.sleep(5000);
            ((JavascriptExecutor)driver).executeScript("document.getElementsByClassName('btn btn-rounded btn-blue save-points save-score-feedback')[0].click()");
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//*[text()='Saved']")), 60);
            Thread.sleep(5000);
            detailedResponse.backIcon.click();
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.icheckbox_square-green")));
            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                WebElement BarQ2 = driver.findElements(By.cssSelector("rect[width='30']")).get(8); //Click on 8th essay bar
                new Actions(driver).moveToElement(BarQ2).click(BarQ2).perform();
            }
            //Manually assign grade for essay type question(Q2)
            driver.findElement(By.xpath("(//div[@id='view-user-question-performance-score-box'])[1]")).click();
            driver.findElement(By.xpath("//input[@data-id='score']")).clear();
            driver.findElement(By.xpath("//input[@data-id='score']")).sendKeys("0");
            driver.findElement(By.cssSelector("li[class^='question-link-label ']")).click();
            Thread.sleep(5000);
            ((JavascriptExecutor)driver).executeScript("document.getElementsByClassName('btn btn-rounded btn-blue save-points save-score-feedback')[0].click()");
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//*[text()='Saved']")), 60);
            Thread.sleep(5000);
            detailedResponse.backIcon.click();

            //verify percentage after giving score to essay type question
            //Verify performance score at instructor side
            CustomAssert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText().replaceAll("[\\t\\n\\r]"," ").trim(), "57%", "Verify performance score at instructor side", "Performance percentage is displayed as expected", "Performance percentage is not displayed as expected");
            //Verify score and percentage on student card
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(0).getText(),"12/21","Verify score of student at instructor side","Score is displayed as expected","Score is not displayed as expected");
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(1).getText(),"57.14%","Verify percentage of student at instructor side","Percentage is displayed as expected","Percentage is not displayed as expected");

            studentResponse.checkboxInStudentCard.get(0).click();
            studentResponse.releaseGrade.click();
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);
            new Navigator().navigateTo("assignment");
            //verify score on assignment page
            CustomAssert.assertEquals(assignments.studentScore.getText().trim(),"12.00","Verify score of student","Score is displayed as expected","Score is not displayed as expected");
            CustomAssert.assertEquals(assignments.totalScore.getText(),"21","Verify total score at student side","Total Score is displayed as expected","Total Score is not displayed as expected");
            //click on score to navigate assignemnt summary
            assignments.totalScore.click();
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.tagName("tspan")));
            //verify percentage at assignment summary

            CustomAssert.assertEquals(assignmentSummary.percentage.get(0).getText(),"57","Verify percentage on assignment summary","Percentage is displayed as expected","Percentage is not displayed as expected");
            //verify score on assignment summary page
            CustomAssert.assertEquals(assignmentSummary.studentScore.getText().trim(),"12","Verify score of student","Score is displayed as expected","Score is not displayed as expected");
            CustomAssert.assertEquals(assignmentSummary.totalScore.getText(),"21","Verify total score at student side","Total Score is displayed as expected","Total Score is not displayed as expected");

            //click on 1 bar on assignment summary page

            if( Properties.getPropertyValue("Browser").equals("FIREFOX")) {
                assignmentSummary.correctAnswer.get(0).click();
            }
            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                WebElement BarQ2 = driver.findElements(By.cssSelector("rect[width='34']")).get(4); //Click on 1st essay bar
                new Actions(driver).moveToElement(BarQ2).click(BarQ2).perform();
            }
            //wait for question to appear
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(yourResponse.totalScorePerQuestion));
            //verify score of question
            CustomAssert.assertEquals(yourResponse.scoreByQuestion.get(0).getAttribute("value").trim(),"1","Verify score question","Score of particular is displayed as expected","Score of particular question is not displayed as expected");
            CustomAssert.assertEquals(yourResponse.totalScorePerQuestion.getText(),"1","Verify maximum score of particular question at student side","Total Score of particular question is displayed as expected","Total Score of particular question is not displayed as expected");

            yourResponse.backArrowYourResponse.click();
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(assignmentSummary.studentScore));

            //click on essay type question bar and verify score
            if( Properties.getPropertyValue("Browser").equals("FIREFOX")) {
                assignmentSummary.correctAnswer.get(0).click();
            }
            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                WebElement BarQ2 = driver.findElements(By.cssSelector("rect[width='34']")).get(8); //Click on 1st essay bar
                new Actions(driver).moveToElement(BarQ2).click(BarQ2).perform();
            }
            //wait for question to appear
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(yourResponse.totalScorePerQuestion));
            //verify score of question
            CustomAssert.assertEquals(yourResponse.scoreByQuestion.get(0).getAttribute("value").trim(),"0","Verify score question","Score of particular is displayed as expected","Score of particular question is not displayed as expected");
            CustomAssert.assertEquals(yourResponse.totalScorePerQuestion.getText(),"2","Verify maximum score of particular question at student side","Total Score of particular question is displayed as expected","Total Score of particular question is not displayed as expected");

            yourResponse.backArrowYourResponse.click();
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(assignmentSummary.studentScore));

            new Navigator().navigateTo("skillreport");
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(mySkillReport.percentUnderElo.get(0)));
            //verify percent on My skill report
            CustomAssert.assertEquals(mySkillReport.percentUnderElo.get(5).getText(),"57","Verify percentage at student side on skill report page","Percentage of particular ELO is displayed as expected","Percentage of particular ELO is not displayed as expected");

            //click on 57
            mySkillReport.percentUnderElo.get(5).click();
            Thread.sleep(2000);
            CustomAssert.assertTrue(mySkillReport.percentScorePerElo.get(0).isDisplayed(),"Verify percentage at student side on skill report page","Percentage of particular ELO is displayed as expected","Percentage of particular ELO is not displayed as expected");

            new Navigator().navigateTo("dashboard");
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,dataIndex);
            //navigate to report

            new Navigator().navigateTo("myReports");
            //verify standard mastered and standard assessed
            Assert.assertEquals(MyReports.standards.get(0).getText(),"0%","'Standards mastered' is incremented");
            Assert.assertEquals(MyReports.standards.get(2).getText(),"5%","'Standards assessed' is incremented");

            MyReports.viewByMastery.click();//Click on mastery radio button
            Thread.sleep(3000);
            //verify insufficient information for unassessed elo's
            CustomAssert.assertEquals(MyReports.eloInsufficientInfo.get(1).getText(),"Insufficient Information","Verify ELO Info for non assessed","Insufficient Information is displaying for not assessed elo","Insufficient Information is not displaying for not assessed elo");
            CustomAssert.assertEquals(MyReports.eloInsufficientInfo.get(3).getText(),"Insufficient Information","Verify ELO Info for non assessed","Insufficient Information is displaying for not assessed elo","Insufficient Information is not displaying for not assessed elo");

            //verify not mastered for Elo for which student has attempted assessment

            CustomAssert.assertEquals(MyReports.eloNotMastered.get(2).getText(),"Not Mastered","Verify ELO Info for assessed","Not mastered is displaying for attempted ELO","Not mastered is not displaying for attempted ELO");

            MyReports.viewByScore.click();//Click on score radio button
            CustomAssert.assertEquals(MyReports.tabularValue_score.get(0).getText().trim(),"12.00","Verify score on My reports page","Score of assessment is displaying properly","Score of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(driver.findElements(By.xpath("//span[@data-proficiency='raw' and @identifier='1.OA']")).get(0).getText(),"12.00","Verify score on My reports page under 1.O.A","Score of assessment under 1.O.A is displaying properly","Score of assessment under 1.O.A is not displaying properly as expected");
            CustomAssert.assertEquals(driver.findElements(By.xpath("//span[@data-proficiency='raw' and @identifier='1.OA']")).get(1).getText(),"12/21","Verify score on My reports page under 1.O.A","Score of assessment under 1.O.A is displaying properly","Score of assessment under 1.O.A is not displaying properly as expected");


            MyReports.viewByPercent.click();//Click on percent radio button

            //Expected - Raw score that the student has scored should be displayed in Terms of Percentage
            CustomAssert.assertEquals(driver.findElements(By.xpath("//span[@data-proficiency='percent' and @identifier='1.OA']")).get(0).getText(),"57%","Verify Percentage on My reports page under 1.O.A","Percentage of assessment is displaying properly","Percentage of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(driver.findElements(By.xpath("//span[@data-proficiency='percent' and @identifier='1.OA']")).get(1).getText(),"57%","Verify Percentage on My reports page under 1.O.A","Percentage of assessment under 1.O.A is displaying properly","Percentage of assessment under 1.O.A is not displaying properly as expected");

            MyReports.viewByMastery.click();//Click on mastery radio button
            Thread.sleep(1000);
            //click on mastered elo
            MyReports.eloNotMastered.get(2).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(detailedMasteryreport.detailedEloNotMastered.get(1)));
            //verify score an percentage on detailed Mastery report
            CustomAssert.assertEquals(detailedMasteryreport.detailedEloNotMastered.get(0).getText(),"Not Mastered","Verify Mastery on Detailed mastery report page under 1.O.A","Mastery of assessment is displaying properly","Mastery of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(detailedMasteryreport.detailedEloNotMastered.get(1).getText(),"12/21","Verify Score on Detailed mastery report page under 1.O.A","Score of assessment is displaying properly","Score of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(detailedMasteryreport.detailedEloNotMastered.get(2).getText(),"57%","Verify Percentage on Detailed mastery report page under 1.O.A","Percentage of assessment is displaying properly","Percentage of assessment is not displaying properly as expected");


            CustomAssert.assertEquals(detailedMasteryreport.detailedEloNotMastered.get(3).getText(),"Not Mastered","Verify Mastery on Detailed mastery report page under 1.O.A.A.1","Mastery of assessment is displaying properly","Mastery of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(detailedMasteryreport.detailedEloNotMastered.get(4).getText(),"12/21","Verify score on Detailed mastery report page under 1.O.A","score of assessment is displaying properly","score of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(detailedMasteryreport.detailedEloNotMastered.get(5).getText(),"57%","Verify Percentage on Detailed mastery report page under 1.O.A","Percentage of assessment is displaying properly","Percentage of assessment is not displaying properly as expected");



        }catch (Exception e ){
            Assert.fail("Exception in testcase 'gradableAssignmentFlowByInstructor' in class 'AssignmentFlow'", e);
        }
    }


    @Test(priority = 2 , enabled = true)
    public void gradableOnAssignmentSubmission()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            ReportUtil.log("Description", "This test case validates the creation of gradable assessment and assign to class,submitted by students and report verification", "info");
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            AssignmentSummary assignmentSummary = PageFactory.initElements(driver, AssignmentSummary.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            YourResponse yourResponse=PageFactory.initElements(driver,YourResponse.class);
            MyReports MyReports = PageFactory.initElements(driver,MyReports.class);
            DetailedMasteryreport detailedMasteryreport=PageFactory.initElements(driver,DetailedMasteryreport.class);
            MySkillReport mySkillReport=PageFactory.initElements(driver,MySkillReport.class);

            int dataIndex = 2;
            String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
//            String appendChar="aaVw";
            System.out.println("appendChar:"+appendChar);
            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create an assessment with 10 different types of questions
            new Assignment().assignWhileRemovingEssayType(dataIndex,appendChar);
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, dataIndex);//Login as a student
            new Assignment().submitAssignmentWithMixResponse(dataIndex,12,18);
            //Verify the percentage of student Should display
            CustomAssert.assertEquals(assignmentSummary.percentage.get(0).getText(),"67","Verify percentage of student ","percentage is displayed as expected","percentage  is not displayed as expected");

            //click on continue
            assignmentSummary.button_continue.click();//Click on continue button
            Thread.sleep(3000);
            CustomAssert.assertEquals(yourResponse.scoreByQuestion.size(), 1, "Score should not display for every question", "Score is not displaying", "Score is displaying");

            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(4000);
            //Verify performance score at instructor side
            CustomAssert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText().replaceAll("[\\t\\n\\r]"," ").trim(), "67%", "Verify performance score at instructor side", "Performance percentage is displayed as expected", "Performance percentage is not displayed as expected");
            //Verify score and percentage on student card
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(0).getText(),"12/18","Verify score of student at instructor side","Score is displayed as expected","Score is not displayed as expected");
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(1).getText().replaceAll("[\\t\\n\\r]"," ").trim(),"66.67%","Verify percentage of student at instructor side","Percentage is displayed as expected","Percentage is not displayed as expected");

            studentResponse.checkboxInStudentCard.get(0).click();
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);
            new Navigator().navigateTo("assignment");
            //verify score on assignment page
            CustomAssert.assertEquals(assignments.studentScore.getText().trim(),"12.00","Verify score of student","Score is displayed as expected","Score is not displayed as expected");
            CustomAssert.assertEquals(assignments.totalScore.getText(),"18","Verify total score at student side","Total Score is displayed as expected","Total Score is not displayed as expected");
            //click on score to navigate assignemnt summary
            assignments.totalScore.click();
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.tagName("tspan")));
            //verify percentage at assignment summary

            CustomAssert.assertEquals(assignmentSummary.percentage.get(0).getText(),"67","Verify percentage on assignment summary","Percentage is displayed as expected","Percentage is not displayed as expected");
            //verify score on assignment summary page
            CustomAssert.assertEquals(assignmentSummary.studentScore.getText().trim(),"12","Verify score of student","Score is displayed as expected","Score is not displayed as expected");
            CustomAssert.assertEquals(assignmentSummary.totalScore.getText(),"18","Verify total score at student side","Total Score is displayed as expected","Total Score is not displayed as expected");


            //click on 1 bar on assignment summary page
            if( Properties.getPropertyValue("Browser").equals("FIREFOX")) {
                assignmentSummary.correctAnswer.get(0).click();
            }
            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                WebElement BarQ2 = driver.findElements(By.cssSelector("rect[width='34']")).get(0); //Click on 1st essay bar
                new Actions(driver).moveToElement(BarQ2).click(BarQ2).perform();
            }
            //wait for question to appear
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(yourResponse.totalScorePerQuestion));
            //verify score of question
            CustomAssert.assertEquals(yourResponse.scoreByQuestion.get(0).getAttribute("value").trim(),"1","Verify score question","Score of particular is displayed as expected","Score of particular question is not displayed as expected");
            CustomAssert.assertEquals(yourResponse.totalScorePerQuestion.getText(),"1","Verify maximum score of particular question at student side","Total Score of particular question is displayed as expected","Total Score of particular question is not displayed as expected");

            yourResponse.backArrowYourResponse.click();
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(assignmentSummary.studentScore));
            assignmentSummary.nextArrowInPerformanceBar.click();
            Thread.sleep(5000);
            String height = assignmentSummary.bar_question.get(0).getAttribute("height");

            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                WebElement q = driver.findElements(By.cssSelector("rect[height='"+height+"']")).get(0); //Click on 1st essay bar
                new Actions(driver).moveToElement(q).click(q).perform();
            }
            //wait for question to appear
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(yourResponse.totalScorePerQuestion));

            //verify score of question
            CustomAssert.assertEquals(yourResponse.scoreByQuestion.get(0).getAttribute("value").trim(),"0","Verify score question","Score of particular is displayed as expected","Score of particular question is not displayed as expected");
            CustomAssert.assertEquals(yourResponse.totalScorePerQuestion.getText(),"1","Verify maximum score of particular question at student side","Total Score of particular question is displayed as expected","Total Score of particular question is not displayed as expected");
            Thread.sleep(2000);
            WebDriverUtil.clickOnElementUsingJavascript(yourResponse.arrow_previous);
            //verify skill report
            new Navigator().navigateTo("skillreport");
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(mySkillReport.percentUnderElo.get(0)));
            //verify percent on My skill report
            CustomAssert.assertEquals(mySkillReport.percentUnderElo.get(5).getText(),"67","Verify percentage at student side on skill report page","Percentage of particular ELO is displayed as expected","Percentage of particular ELO is not displayed as expected");

            //click on 57
            mySkillReport.percentUnderElo.get(5).click();
            Thread.sleep(2000);
            CustomAssert.assertTrue(mySkillReport.percentScorePerEloGreenColor.get(0).isDisplayed(),"Verify percentage at student side on skill report page","Percentage of particular ELO is displayed as expected","Percentage of particular ELO is not displayed as expected");

            new Navigator().navigateTo("dashboard");
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,dataIndex);
            //navigate to report
            new Navigator().navigateTo("premiumReports");
            //verify standard mastered and standard assessed
            Assert.assertEquals(MyReports.standards.get(0).getText(),"0%","'Standards mastered' is incremented");
            Assert.assertEquals(MyReports.standards.get(2).getText(),"5%","'Standards assessed' is incremented");

            MyReports.viewByMastery.click();//Click on mastery radio button
            Thread.sleep(3000);
            //verify insufficient information for unassessed elo's
            CustomAssert.assertEquals(MyReports.eloInsufficientInfo.get(1).getText(),"Insufficient Information","Verify ELO Info for non assessed","Insufficient Information is displaying for not assessed elo","Insufficient Information is not displaying for not assessed elo");
            CustomAssert.assertEquals(MyReports.eloInsufficientInfo.get(3).getText(),"Insufficient Information","Verify ELO Info for non assessed","Insufficient Information is displaying for not assessed elo","Insufficient Information is not displaying for not assessed elo");

            //verify Nearly Mastered for Elo for which student has attempted assessment

            CustomAssert.assertEquals(MyReports.eloNearlyMastered.get(1).getText(),"Nearly Mastered","Verify ELO Info for assessed","Not mastered is displaying for attempted ELO","Not mastered is not displaying for attempted ELO");

            MyReports.viewByScore.click();//Click on score radio button
            CustomAssert.assertEquals(MyReports.tabularValue_score.get(0).getText().trim(),"12.00","Verify score on My reports page","Score of assessment is displaying properly","Score of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(driver.findElements(By.xpath("//span[@data-proficiency='raw' and @identifier='1.OA']")).get(0).getText(),"12.00","Verify score on My reports page under 1.O.A","Score of assessment under 1.O.A is displaying properly","Score of assessment under 1.O.A is not displaying properly as expected");
            CustomAssert.assertEquals(driver.findElements(By.xpath("//span[@data-proficiency='raw' and @identifier='1.OA']")).get(1).getText(),"12/18","Verify score on My reports page under 1.O.A","Score of assessment under 1.O.A is displaying properly","Score of assessment under 1.O.A is not displaying properly as expected");

            MyReports.viewByPercent.click();//Click on percent radio button

            //Expected - Raw score that the student has scored should be displayed in Terms of Percentage
            CustomAssert.assertEquals(driver.findElements(By.xpath("//span[@data-proficiency='percent' and @identifier='1.OA']")).get(0).getText(),"67%","Verify Percentage on My reports page under 1.O.A","Percentage of assessment is displaying properly","Percentage of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(driver.findElements(By.xpath("//span[@data-proficiency='percent' and @identifier='1.OA']")).get(1).getText(),"67%","Verify Percentage on My reports page under 1.O.A","Percentage of assessment under 1.O.A is displaying properly","Percentage of assessment under 1.O.A is not displaying properly as expected");

            MyReports.viewByMastery.click();//Click on mastery radio button
            Thread.sleep(1000);
            //click on mastered elo
            MyReports.eloNearlyMastered.get(1).click();
            //verify score an percentage on detailed Mastery report
            CustomAssert.assertEquals(detailedMasteryreport.detailedElo.get(0).getText(),"Nearly Mastered","Verify Mastery on Detailed mastery report page under 1.O.A","Mastery of assessment is displaying properly","Mastery of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(detailedMasteryreport.detailedElo.get(1).getText(),"12/18","Verify Score on Detailed mastery report page under 1.O.A","Score of assessment is displaying properly","Score of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(detailedMasteryreport.detailedElo.get(2).getText(),"67%","Verify Percentage on Detailed mastery report page under 1.O.A","Percentage of assessment is displaying properly","Percentage of assessment is not displaying properly as expected");


            CustomAssert.assertEquals(detailedMasteryreport.detailedElo.get(3).getText(),"Nearly Mastered","Verify Mastery on Detailed mastery report page under 1.O.A.A.1","Mastery of assessment is displaying properly","Mastery of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(detailedMasteryreport.detailedElo.get(4).getText(),"12/18","Verify score on Detailed mastery report page under 1.O.A","score of assessment is displaying properly","score of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(detailedMasteryreport.detailedElo.get(5).getText(),"67%","Verify Percentage on Detailed mastery report page under 1.O.A","Percentage of assessment is displaying properly","Percentage of assessment is not displaying properly as expected");



        }catch (Exception e ){
            Assert.fail("Exception in testcase 'gradableAssignmentFlowByInstructor' in class 'AssignmentFlow'", e);
        }
    }


    @Test(priority = 4 , enabled = false)
    public void nonGradableAssignmentFlow()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {

            ReportUtil.log("Description", "This test case validates the creation of gradable assessment and assign to class,submitted by students and report verification", "info");
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            AssignmentSummary assignmentSummary = PageFactory.initElements(driver, AssignmentSummary.class);
            InstructorDashboard instructorDashboard = PageFactory.initElements(driver,InstructorDashboard.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            YourResponse yourResponse=PageFactory.initElements(driver,YourResponse.class);
            MyReports MyReports = PageFactory.initElements(driver,MyReports.class);
            DetailedResponse detailedResponse=PageFactory.initElements(driver,DetailedResponse.class);
            DetailedMasteryreport detailedMasteryreport=PageFactory.initElements(driver,DetailedMasteryreport.class);
            MySkillReport mySkillReport=PageFactory.initElements(driver,MySkillReport.class);

            int dataIndex = 3;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963",dataIndex,false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            //Create an assessment with 10 different types of questions
            new Assignment().assignWhileRemovingEssayType(dataIndex,appendChar);
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Assignment().submitAssignmentWithMixResponse(dataIndex,11,19);
            //Verify the percentage of student Should display
            CustomAssert.assertEquals(assignmentSummary.percentage.get(0).getText(),"58","Verify percentage of student ","percentage is displayed as expected","percentage  is not displayed as expected");

            //Verify the percentage of student

            //click on continue
            assignmentSummary.button_continue.click();//Click on continue button
            Thread.sleep(3000);
            CustomAssert.assertEquals(yourResponse.scoreByQuestion.size(), 0, "Score should not display for every question", "Score is not displaying", "Score is displaying");

            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(4000);
            //Verify performance score at instructor side
            CustomAssert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(), "58%", "Verify performance score at instructor side", "Performance percentage is displayed as expected", "Performance percentage is not displayed as expected");
            //Verify score and percentage on student card
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(0).getText(),"11/19","Verify score of student at instructor side","Score is displayed as expected","Score is not displayed as expected");
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(1).getText(),"57.89%","Verify percentage of student at instructor side","Percentage is displayed as expected","Percentage is not displayed as expected");

            studentResponse.checkboxInStudentCard.get(0).click();
            studentResponse.releaseGrade.click();
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);
            new Navigator().navigateTo("assignment");
            //verify score on assignment page
            CustomAssert.assertEquals(assignments.studentScoreList.size(),0,"Verify score of student","Score is not displayed as expected","Score is displayed as not expected");
            CustomAssert.assertEquals(assignments.totalScoreList.size(),0,"Verify total score at student side","Total Score is not displayed as expected","Total Score is displayed as not expected");

            assignments.assignment.click();
            Thread.sleep(1000);
            //verify percentage at assignment summary

            CustomAssert.assertEquals(assignmentSummary.percentage.get(0).getText(),"58","Verify percentage on assignment summary","Percentage is displayed as expected","Percentage is not displayed as expected");
            //verify score on assignment summary page


            //click on 1 bar on assignment summary page
            assignmentSummary.correctAnswer.get(0).click();
            //wait for question to appear
            Thread.sleep(1000);
            //verify score of question
            CustomAssert.assertEquals(yourResponse.scoreByQuestion.size(), 0, "Score should not display for every question", "Score is not displaying", "Score is displaying");

            yourResponse.backArrowYourResponse.click();

            //verify skill report
            new Navigator().navigateTo("skillreport");
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(mySkillReport.percentUnderElo.get(0)));
            //verify percent on My skill report
            CustomAssert.assertEquals(mySkillReport.percentUnderElo.get(5).getText(),"58","Verify percentage at student side on skill report page","Percentage of particular ELO is displayed as expected","Percentage of particular ELO is not displayed as expected");

            //click on 57
            mySkillReport.percentUnderElo.get(5).click();
            Thread.sleep(2000);
            CustomAssert.assertTrue(mySkillReport.percentScorePerElo.get(0).isDisplayed(),"Verify percentage at student side on skill report page","Percentage of particular ELO is displayed as expected","Percentage of particular ELO is not displayed as expected");

            new Navigator().navigateTo("dashboard");
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,dataIndex);
            //navigate to report
            new Navigator().navigateTo("myReports");
            //verify standard mastered and standard assessed
            Assert.assertEquals(MyReports.standards.get(0).getText(),"0%","'Standards mastered' is incremented");
            Assert.assertEquals(MyReports.standards.get(2).getText(),"5%","'Standards assessed' is incremented");

            MyReports.viewByMastery.click();//Click on mastery radio button
            Thread.sleep(3000);
            //verify insufficient information for unassessed elo's
            CustomAssert.assertEquals(MyReports.eloInsufficientInfo.get(1).getText(),"Insufficient Information","Verify ELO Info for non assessed","Insufficient Information is displaying for not assessed elo","Insufficient Information is not displaying for not assessed elo");
            CustomAssert.assertEquals(MyReports.eloInsufficientInfo.get(3).getText(),"Insufficient Information","Verify ELO Info for non assessed","Insufficient Information is displaying for not assessed elo","Insufficient Information is not displaying for not assessed elo");
            CustomAssert.assertEquals(MyReports.eloInsufficientInfo.get(5).getText(),"Insufficient Information","Verify ELO Info for non assessed","Insufficient Information is displaying for not assessed elo","Insufficient Information is not displaying for not assessed elo");

            //verfy not mastered for Elo for which student has attempted assessment

            CustomAssert.assertEquals(MyReports.eloNotMastered.get(1).getText(),"Not Mastered","Verify ELO Info for assessed","Not mastered is displaying for attempted ELO","Not mastered is not displaying for attempted ELO");

            MyReports.viewByScore.click();//Click on score radio button
            CustomAssert.assertEquals(MyReports.tabularValue_score.get(0).getText().trim(),"11.00","Verify score on My reports page","Score of assessment is displaying properly","Score of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(driver.findElements(By.xpath("//span[@data-proficiency='raw' and @identifier='1.OA']")).get(0).getText(),"11.00","Verify score on My reports page under 1.O.A","Score of assessment under 1.O.A is displaying properly","Score of assessment under 1.O.A is not displaying properly as expected");
            CustomAssert.assertEquals(driver.findElements(By.xpath("//span[@data-proficiency='raw' and @identifier='1.OA']")).get(1).getText(),"11/19","Verify score on My reports page under 1.O.A","Score of assessment under 1.O.A is displaying properly","Score of assessment under 1.O.A is not displaying properly as expected");


            MyReports.viewByPercent.click();//Click on percent radio button

            //Expected - Raw score that the student has scored should be displayed in Terms of Percentage
            CustomAssert.assertEquals(driver.findElements(By.xpath("//span[@data-proficiency='percent' and @identifier='1.OA']")).get(0).getText(),"58%","Verify Percentage on My reports page under 1.O.A","Percentage of assessment is displaying properly","Percentage of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(driver.findElements(By.xpath("//span[@data-proficiency='percent' and @identifier='1.OA']")).get(1).getText(),"58%","Verify Percentage on My reports page under 1.O.A","Percentage of assessment under 1.O.A is displaying properly","Percentage of assessment under 1.O.A is not displaying properly as expected");

            MyReports.viewByMastery.click();//Click on mastery radio button
            Thread.sleep(1000);
            //click on mastered elo
            MyReports.eloNotMastered.get(1).click();
            Thread.sleep(1000);
            //verify score an percentage on detailed Mastery report
            CustomAssert.assertEquals(detailedMasteryreport.detailedEloNotMastered.get(0).getText(),"Not Mastered","Verify Mastery on Detailed mastery report page under 1.O.A","Mastery of assessment is displaying properly","Mastery of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(detailedMasteryreport.detailedEloNotMastered.get(1).getText(),"11/19","Verify Score on Detailed mastery report page under 1.O.A","Score of assessment is displaying properly","Score of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(detailedMasteryreport.detailedEloNotMastered.get(2).getText(),"58%","Verify Percentage on Detailed mastery report page under 1.O.A","Percentage of assessment is displaying properly","Percentage of assessment is not displaying properly as expected");


            CustomAssert.assertEquals(detailedMasteryreport.detailedEloNotMastered.get(3).getText(),"Not Mastered","Verify Mastery on Detailed mastery report page under 1.O.A.A.1","Mastery of assessment is displaying properly","Mastery of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(detailedMasteryreport.detailedEloNotMastered.get(4).getText(),"11/19","Verify score on Detailed mastery report page under 1.O.A","score of assessment is displaying properly","score of assessment is not displaying properly as expected");
            CustomAssert.assertEquals(detailedMasteryreport.detailedEloNotMastered.get(5).getText(),"58%","Verify Percentage on Detailed mastery report page under 1.O.A","Percentage of assessment is displaying properly","Percentage of assessment is not displaying properly as expected");


            //click on 'not mastered'
            detailedMasteryreport.detailedEloNotMastered.get(0).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(detailedMasteryreport.scorePerQuestion.get(1)));

            CustomAssert.assertTrue(detailedMasteryreport.scorePerQuestion.get(0).isDisplayed(),"Verify score should display","Score is displaying","Score is not displaying");
            //click on 9th bubble
            detailedMasteryreport.questionBubble.get(8).click();
            Thread.sleep(3000);
            //click on 9th bubble and verify 9th question should display
            CustomAssert.assertTrue(detailedMasteryreport.scorePerQuestion.get(8).isDisplayed(),"Verify score should display","Score is displaying","Score is not displaying");

        }catch (Exception e ){
            Assert.fail("Exception in testcase 'gradableAssignmentFlowByInstructor' in class 'AssignmentFlow'", e);
        }
    }
}
