package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1915;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 2/24/2015.
 */
public class EnhancementToTheGradebookPageForAnyAssignment  extends Driver{
    @Test(priority = 1, enabled = true)
    public void enhancementToTheGradebookPageForAnyAssignment() {

        try {
            //tc row no 9
            Gradebook gradebook;
            gradebook = PageFactory.initElements(driver,Gradebook.class);
            AssignmentResponsesPage assignmentResponsesPage;
            assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new Assignment().create(10); //create assignment
            for(int i=0;i<11;i++)
                new Assignment().addQuestions(10,"truefalse","");
            new Assignment().create(11); //create assignment
            for(int i=0;i<2;i++)
                new Assignment().addQuestions(11,"truefalse","");
            new Assignment().create(12); //create assignment
            for(int i=0;i<3;i++)
                new Assignment().addQuestions(12,"truefalse","");
            new Assignment().create(13); //create assignment
            for(int i=0;i<4;i++)
                new Assignment().addQuestions(13,"truefalse","");
            new Assignment().create(14); //create assignment
            for(int i=0;i<5;i++)
                new Assignment().addQuestions(14,"truefalse","");
            new Assignment().create(15); //create assignment
            for(int i=0;i<6;i++)
                new Assignment().addQuestions(15,"truefalse","");
            new Assignment().create(16); //create assignment
            for(int i=0;i<7;i++)
                new Assignment().addQuestions(16,"truefalse","");
            new Assignment().create(17); //create assignment
            for(int i=0;i<8;i++)
                new Assignment().addQuestions(17,"truefalse","");
            new Assignment().create(18); //create assignment
            new Assignment().create(19); //create assignment
            for(int i=0;i<1;i++)
                new Assignment().addQuestions(19,"truefalse","");
            new Assignment().create(20); //create assignment
            for(int i=0;i<11;i++)
                new Assignment().addQuestions(20,"truefalse","");
            new Assignment().create(21); //create assignment
            for(int i=0;i<5;i++)
                new Assignment().addQuestions(21,"truefalse","");
            new LoginUsingLTI().ltiLogin("9_1");//login as student
            new LoginUsingLTI().ltiLogin("9_2");//login as student
            new LoginUsingLTI().ltiLogin("9_3");//login as student
            new LoginUsingLTI().ltiLogin("9_4");//login as student
            new LoginUsingLTI().ltiLogin("9_5");//login as student
            new LoginUsingLTI().ltiLogin("9_6");//login as student
            new LoginUsingLTI().ltiLogin("9_7");//login as student
            new LoginUsingLTI().ltiLogin("9_8");//login as student
            new LoginUsingLTI().ltiLogin("9_9");//login as student
            new LoginUsingLTI().ltiLogin("9_10");//login as student
            new LoginUsingLTI().ltiLogin("9_11");//login as student
            new LoginUsingLTI().ltiLogin("9_12");//login as student
            new LoginUsingLTI().ltiLogin("9_13");//login as student
            new LoginUsingLTI().ltiLogin("9_14");//login as student
            new LoginUsingLTI().ltiLogin("9_15");//login as student
            new LoginUsingLTI().ltiLogin("9_16");//login as student
            new LoginUsingLTI().ltiLogin("9_17");//login as student
            new LoginUsingLTI().ltiLogin("9_18");//login as student
            new LoginUsingLTI().ltiLogin("9_19");//login as student
            new LoginUsingLTI().ltiLogin("9_20");//login as student
            new LoginUsingLTI().ltiLogin("9"); //login as instructor
            gradebook = PageFactory.initElements(driver,Gradebook.class);
            assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new Assignment().assignToStudent(10); //assign to the student
            new Assignment().assignToStudent(11); //assign to the student
            new Assignment().assignToStudent(12); //assign to the student
            new Assignment().assignToStudent(13); //assign to the student
            new Assignment().assignToStudent(14); //assign to the student
            new Assignment().assignToStudent(15); //assign to the student
            new Assignment().assignToStudent(16); //assign to the student
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            String header=gradebook.getGradeBookHeader().getText();
            Assert.assertEquals(header,"Gradebook","Gradebook page is not displaying");
            String exportToCsv=gradebook.getExportToCSV().getAttribute("src");
            String nameHeader=gradebook.getNameHeader().getText();
            String postAmessage=gradebook.getPostAmessage().getText();
            String overAllScore=gradebook.getOverallScore().getText();
            WebElement scroll=driver.findElement(By.xpath("(//span[@class='ls-ins-gradebook-activity-midterm'])[138]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            boolean scrollDownText=gradebook.getScrollDown().isDisplayed();
            if(scrollDownText==false){
                Assert.fail("Instructor should not able to scroll down");
            }
            boolean leftContent=gradebook.getLeftContent().isDisplayed();
            if(leftContent==false){
                Assert.fail("Left content should not scroll along with right content");
            }
            String exportToCsv1=gradebook.getExportToCSV().getAttribute("src");
            if(!exportToCsv.equals(exportToCsv1)){
                Assert.fail("'Export to CV'is not remain as it is");
            }
            String postAmessage1=gradebook.getPostAmessage().getText();
            if(!postAmessage.equals(postAmessage1)){
                Assert.fail("'Post a message' is not remain as it is.");
            }

            String nameHeader1=gradebook.getNameHeader().getText();
            if(!nameHeader.contains(nameHeader1)){
                Assert.fail("Header with 'Names' is not displaying  below 'Export to CV' and 'Post a message'");
            }
            String overAllScore1=gradebook.getOverallScore().getText();
            if (!overAllScore.equals(overAllScore1)) {
                Assert.fail(" 'Overall score' is not displaying  below 'Export to CV' and 'Post a message'");
            }

            int studentCount=gradebook.getLeftContentCount().size();
            if(studentCount < 20){
                Assert.fail("All the student names is not displaying");
            }
            Thread.sleep(3000);
            WebElement scroll2=driver.findElement(By.xpath("(//span[@class='ls-ins-gradebook-activity-midterm'])[140]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll2);
            boolean scrollHorizontalText=gradebook.getScrollHorizontall().isDisplayed();
            if(scrollDownText==false){
                Assert.fail("Instructor should not able to scroll horizontally");
            }

            int assignmentCount=gradebook.getAllAssignmentName().size();
            if(assignmentCount < 7){
                Assert.fail("Instructor is not able to see all the assignments");
            }
            int studentCount1=gradebook.getLeftContentCount().size();
            if(studentCount1 < 20){
                Assert.fail("All the student names is not displaying");
            }
            gradebook.getAssignmentName().click();//click on assignment
            String respnsepageTitle=assignmentResponsesPage.getPageTitle().getText();
            Assert.assertEquals(respnsepageTitle,"Assignment Responses","Instructor is not navigated to Assignment Response page");
            assignmentResponsesPage.getBack_Button().click();//click on back button
            WebElement scroll1=driver.findElement(By.xpath("(//span[@class='ls-ins-gradebook-activity-midterm'])[140]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll1);
            new Navigator().NavigateTo("Dashboard");//navigate to dashboard
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            boolean scrollDownText1=gradebook.getAssignment().isDisplayed();
            if(scrollDownText1==false){
                Assert.fail("The Gradebook page is not get reset");
            }

        }

        catch (Exception e)
        {
            Assert.fail("Exception in test case enhancementToTheGradebookPageForAnyAssignment of class EnhancementToTheGradebookPageForAnyAssignment", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void enhancementToTheGradebookPageForAnyAssignmentAsMentor() {

        try {
            //tc row no 26
            Gradebook gradebook;
            gradebook = PageFactory.initElements(driver, Gradebook.class);
            AssignmentResponsesPage assignmentResponsesPage;
            assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            new LoginUsingLTI().ltiLogin("26"); //login as mentor
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            String header=gradebook.getGradeBookHeader().getText();
            Assert.assertEquals(header,"Gradebook","Gradebook page is not displaying");
            String exportToCsv=gradebook.getExportToCSV().getAttribute("src");
            String nameHeader=gradebook.getNameHeader().getText();
            String postAmessage=gradebook.getPostAmessage().getText();
            String overAllScore=gradebook.getOverallScore().getText();
            WebElement scroll=driver.findElement(By.xpath("(//span[@class='ls-ins-gradebook-activity-midterm'])[138]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            boolean scrollDownText=gradebook.getScrollDown().isDisplayed();
            if(scrollDownText==false){
                Assert.fail("Instructor should not able to scroll down");
            }
            boolean leftContent=gradebook.getLeftContent().isDisplayed();
            if(leftContent==false){
                Assert.fail("Left content should not scroll along with right content");
            }
            String exportToCsv1=gradebook.getExportToCSV().getAttribute("src");
            if(!exportToCsv.equals(exportToCsv1)){
                Assert.fail("'Export to CV'is not remain as it is");
            }
            String postAmessage1=gradebook.getPostAmessage().getText();
            if(!postAmessage.equals(postAmessage1)){
                Assert.fail("'Post a message' is not remain as it is.");
            }

            String nameHeader1=gradebook.getNameHeader().getText();
            if(!nameHeader.contains(nameHeader1)){
                Assert.fail("Header with 'Names' is not displaying  below 'Export to CV' and 'Post a message'");
            }
            String overAllScore1=gradebook.getOverallScore().getText();
            if (!overAllScore.equals(overAllScore1)) {
                Assert.fail(" 'Overall score' is not displaying  below 'Export to CV' and 'Post a message'");
            }

            int studentCount=gradebook.getLeftContentCount().size();
            if(studentCount < 20){
                Assert.fail("All the student names is not displaying");
            }
            Thread.sleep(3000);
            WebElement scroll2=driver.findElement(By.xpath("(//span[@class='ls-ins-gradebook-activity-midterm'])[140]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll2);
            boolean scrollHorizontalText=gradebook.getScrollHorizontall().isDisplayed();
            if(scrollDownText==false){
                Assert.fail("Instructor should not able to scroll horizontally");
            }

            int assignmentCount=gradebook.getAllAssignmentName().size();
            if(assignmentCount < 7){
                Assert.fail("Instructor is not able to see all the assignments");
            }
            int studentCount1=gradebook.getLeftContentCount().size();
            if(studentCount1 < 20){
                Assert.fail("All the student names is not displaying");
            }
            gradebook.getAssignmentName().click();//click on assignment
            String respnsepageTitle=assignmentResponsesPage.getPageTitle().getText();
            Assert.assertEquals(respnsepageTitle,"Assignment Responses","Instructor is not navigated to Assignment Response page");
            assignmentResponsesPage.getBack_Button().click();//click on back button
            WebElement scroll1=driver.findElement(By.xpath("(//span[@class='ls-ins-gradebook-activity-midterm'])[140]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll1);
            new Navigator().NavigateTo("Dashboard");//navigate to dashboard
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            boolean scrollDownText1=gradebook.getAssignment().isDisplayed();
            if(scrollDownText1==false){
                Assert.fail("The Gradebook page is not get reset");
            }
        }

        catch (Exception e)
            {
                Assert.fail("Exception in test case enhancementToTheGradebookPageForAnyAssignmentAsMentor of class EnhancementToTheGradebookPageForAnyAssignment", e);
            }
        }

}
