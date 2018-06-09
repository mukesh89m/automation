package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1824;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.DiagnosticTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Mukesh on 1/23/15.
 */
public class StudentSubmittedQs extends Driver{
    @Test(priority = 1,enabled = true)
    public  void studentSubmittedQs()
    {
        String expected="";
        try {
            String diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", "12");
            String diagTestName = ReadTestData.readDataByTagName("", "diagTestName", "12");
            DiagnosticTab diagnosticTab= PageFactory.initElements(driver,DiagnosticTab.class);
            ProficiencyReport proficiencyReport=PageFactory.initElements(driver,ProficiencyReport.class);


            new LoginUsingLTI().ltiLogin("12");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TopicOpen().chapterOpen(3); //open fourth chapter
            new DiagnosticTest().startTest(2);
            new DiagnosticTest().attemptAllCorrect(2,false,false);
            new Assignment().deActivateQuestion(12,diaAssessment,2);

            new LoginUsingLTI().ltiLogin("12");//login as student
            new Navigator().NavigateTo("My Activity"); //Navigate to my activity
            new Click().clickBycssselector("a[class='assessment-title QWERTY']");
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.id("al-peformance-title-id")));
            diagnosticTab.nextChart.click();//click on the next chart

            //Expected 1
            Thread.sleep(5000);
            String questionBar=diagnosticTab.questionPerformanceBarContent.getText().trim();
            System.out.println("Question Bar:"+questionBar);
            Assert.assertEquals(questionBar,"4.1","Deactivated question content bar is not displaying");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("g.highcharts-series.highcharts-tracker > rect", 4); //click on the deactivate question bar type
            new QuestionCard().clickOnInvisibleCard("12",19);

            //Expected 2
            String deActivatedQuestionMsg="This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(diagnosticTab.deActivatedMsg.getText().trim(),deActivatedQuestionMsg,"This question is deactivated and is no longer part of this assignment/practice is not showing");

            //Expected 3
            Thread.sleep(3000);
            boolean hidden=diagnosticTab.contentIssue_icon.isDisplayed();
            if(hidden==true)
                Assert.fail(" “Report Content Issue” icon is not hidden for a deactivated question.");

            //Tc row no 15
            new Navigator().navigateToTab("Discussion");
            String discussionText = new RandomString().randomstring(6);
            new Discussion().postDiscussion(discussionText);//post a discussion

            new Navigator().NavigateTo("Course Stream");//navigate to course stream
            Thread.sleep(3000);
            diagnosticTab.jumpOut_icon.click();//click on the jump out icon
            WebDriverWait wait=new WebDriverWait(driver,120);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'Q 4.1:')]")));
            verifyMessage(15);

            //Tc row no 18
            new Navigator().NavigateTo("My Activity"); //Navigate to my activity
            diagnosticTab.discussion_icon.get(0).click(); //click on the discussion icon
            diagnosticTab.discussion_icon.get(0).click(); //click on the discussion icon
            expected="The smallest unit of a living thing is a";
            Assert.assertEquals(diagnosticTab.questionName.getText().trim(),expected,"Student is not navigated to the deactivated question");
            verifyMessage(21);

            //Tc row no 21
            new Navigator().NavigateTo("Proficiency Report"); //navigate to Proficiency Report
            deActivateQuestion(21);
            //Tc row no
            proficiencyReport.getBarChart().click();// click on the chapter number
            Thread.sleep(2000);
            deActivateQuestion(22);

            new Click().clickbylistcssselector("g.highcharts-series.highcharts-tracker > rect", 1); //click on the tlo question
            Thread.sleep(2000);
            deActivateQuestion(23);

            //Tc row no 24
            new Click().clickbylistcssselector("g.highcharts-series.highcharts-tracker > rect", 2); //click on the deactivate question bar type

            //Expected 2
            String deActivatedQuestionMsg1="This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(diagnosticTab.deActivatedMsg.getText().trim(),deActivatedQuestionMsg1,"This question is deactivated and is no longer part of this assignment/practice is not showing");

            //Expected 3
            Thread.sleep(3000);
            boolean hidden1=diagnosticTab.contentIssue_icon.isDisplayed();
            if(hidden1==true)
                Assert.fail(" “Report Content Issue” icon is not hidden for a deactivated question.");


        } catch (Exception e) {
            Assert.fail("Exception in testcase studentSubmittedQs of class StudentSubmittedQs", e);
        }

    }
    @Test(priority = 2,enabled = true)
    public  void studentNotSubmittedQs()
    {
        String expected="";
        try {
           String diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", "27");
            String diagTestName = ReadTestData.readDataByTagName("", "diagTestName", "27");
            DiagnosticTab diagnosticTab= PageFactory.initElements(driver,DiagnosticTab.class);
            ProficiencyReport proficiencyReport=PageFactory.initElements(driver,ProficiencyReport.class);

            new Assignment().deActivateQuestion(27,diaAssessment,0); //deactivate one question

            new LoginUsingLTI().ltiLogin("27");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TopicOpen().chapterOpen(4); //open fifth chapter
            new DiagnosticTest().startTest(2);
            new DiagnosticTest().attemptAllCorrect(2,false,false);

            //Expected 1
            new LoginUsingLTI().ltiLogin("27");//login as student
            new Navigator().NavigateTo("My Activity"); //Navigate to my activity
            diagnosticTab.practiceQuestionTab.get(0).click();
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.id("al-peformance-title-id")));
            String questionCount=diagnosticTab.questionCount.getText().trim();
            int countValue=Integer.parseInt(questionCount);
            if(countValue>=20)
                Assert.fail("Number of Questions count is not less than the original one");



        } catch (Exception e) {
            Assert.fail("Exception in testcase studentNotSubmittedQs of class StudentSubmittedQs", e);
        }

    }

    @Test(priority = 3,enabled = true)
    public  void studentSubmittedPracticeTestQs()
    {
        try {

            DiagnosticTab diagnosticTab= PageFactory.initElements(driver,DiagnosticTab.class);
            ProficiencyReport proficiencyReport=PageFactory.initElements(driver,ProficiencyReport.class);
            new LoginUsingLTI().ltiLogin("32");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TopicOpen().chapterOpen(3); //open fourth chapter
            new DiagnosticTest().startTest(2);
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true); //quit bcs of true

            new Navigator().NavigateTo("e-Textbook"); //navigate to eTextbook
            new TopicOpen().chapterOpen(3); //open fourth chapter
            new PracticeTest().startTest();//start practice test
             for(int i = 0; i < 12; i++)
            {
                new PracticeTest().AttemptCorrectAnswer(0,"32");
            }
            new PracticeTest().quitThePracticeTest();

            new Navigator().NavigateTo("My Activity");//navigate to My Activity
            diagnosticTab.practiceQuestionTab.get(0).click();
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.id("al-peformance-title-id")));
            String questionName=diagnosticTab.questionCard.getText().trim();
            String actualQuestion=questionName.substring(0,30);
            System.out.println("Question Name:"+actualQuestion);
            new OpenSearchPage().openSearchPage(); //open search page  through cms
            new OpenSearchPage().searchquestion(actualQuestion);
            Thread.sleep(5000);
            deActivateStaticAssessment(32); //deActivate question*/

            new LoginUsingLTI().ltiLogin("32");//login as student
            //Expected 1
            new Navigator().NavigateTo("My Activity");//navigate to My Activity
            diagnosticTab.practiceQuestionTab.get(0).click(); //click on the practice assessment link
            new Click().clickbylistcssselector("g[class='highcharts-series highcharts-tracker'] > rect", 0); //click on the deactivate question bar type
            new QuestionCard().clickOnCard("32",0);

            //Expected 2
            String deActivatedQuestionMsg="This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(diagnosticTab.deActivatedMsg.getText().trim(),deActivatedQuestionMsg,"This question is deactivated and is no longer part of this assignment/practice is not showing");

            //Expected 3
            Thread.sleep(3000);
            boolean hidden=diagnosticTab.contentIssue_icon.isDisplayed();
            if(hidden==true)
                Assert.fail(" “Report Content Issue” icon is not hidden for a deactivated question.");

            //Tc row no 35
            new Navigator().navigateToTab("Discussion");
            String discussionText = new RandomString().randomstring(6);
            new Discussion().postDiscussion(discussionText);//post a discussion

            new Navigator().NavigateTo("Course Stream");//navigate to course stream
            Thread.sleep(3000);
            diagnosticTab.jumpOut_icon.click();//click on the jump out icon
            Assert.assertEquals(diagnosticTab.questionName.getText().trim(),questionName,"Student is not navigated to the deactivated question");
            verifyMessage(15);

            //Tc row no 38
            new Navigator().NavigateTo("My Activity"); //Navigate to my activity
            new Click().clickBycssselector("a[class='assessment-title QWERTY']");
           /* diagnosticTab.discussion_icon.get(0).click(); //click on the discussion icon
            diagnosticTab.discussion_icon.get(0).click(); //click on the discussion icon*/
            new QuestionCard().clickOnCard("32",0);
            Assert.assertEquals(diagnosticTab.questionName.getText().trim(),questionName,"Student is not navigated to the deactivated question");
            String deActivatedQuestionMsg1="This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(diagnosticTab.deActivatedMsg.getText().trim(),deActivatedQuestionMsg1,"This question is deactivated and is no longer part of this assignment/practice is not showing");

            //Expected 3
            Thread.sleep(3000);
            boolean hidden1=diagnosticTab.contentIssue_icon.isDisplayed();
            if(hidden1==true)
                Assert.fail(" “Report Content Issue” icon is not hidden for a deactivated question.");

            //Tc row no 41
            new LoginUsingLTI().ltiLogin("32");//login as student

            new Navigator().NavigateTo("Proficiency Report"); //navigate to Proficiency Report
            deActivatedStaticQuestionNumber(41);
            //Tc row no 42
            proficiencyReport.getBarChart().click();// click on the chapter number
            Thread.sleep(2000);
            deActivatedStaticQuestionNumber(42);
            //Tc row no 43
            new Click().clickbylistcssselector("g.highcharts-series.highcharts-tracker > rect", 2); //click on the tlo question

            //new Click().clickbylistcssselector("g.highcharts-series.highcharts-tracker > rect", 0); //click on the tlo question
            Thread.sleep(2000);
            deActivatedStaticQuestionNumber(43);

            //Tc row no 44
            new Click().clickbylistcssselector("g.highcharts-series.highcharts-tracker > rect", 0); //click on the deactivate question bar type

            //Expected 2
            String deActivatedQuestionMsg12="This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(diagnosticTab.deActivatedMsg.getText().trim(),deActivatedQuestionMsg12,"This question is deactivated and is no longer part of this assignment/practice is not showing");

            //Expected 3
            Thread.sleep(3000);
            boolean hidden12=diagnosticTab.contentIssue_icon.isDisplayed();
            if(hidden12==true)
                Assert.fail(" “Report Content Issue” icon is not hidden for a deactivated question.");


        } catch (Exception e) {
            Assert.fail("Exception in testcase studentSubmittedPracticeTestQs of class StudentSubmittedQs", e);
        }

    }

    @Test(priority = 4,enabled = true)
    public  void studentNotSubmittedPracticeTestQs()
    {
        String expected="";
        try {
            String diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", "47");
            String diagTestName = ReadTestData.readDataByTagName("", "diagTestName", "47");
            String staticTestName = ReadTestData.readDataByTagName("", "staticTestName", "47");

            DiagnosticTab diagnosticTab= PageFactory.initElements(driver,DiagnosticTab.class);
            ProficiencyReport proficiencyReport=PageFactory.initElements(driver,ProficiencyReport.class);

            new Assignment().deActivateQuestion(47,diaAssessment,1); //deactivate question of static assessment

            new LoginUsingLTI().ltiLogin("47");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TopicOpen().chapterOpen(4); //open fifth chapter
            new DiagnosticTest().startTest(2);
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true); //quit bcs of true

            new Navigator().NavigateTo("e-Textbook"); //navigate to eTextbook
            new TopicOpen().chapterOpen(4); //open fifth chapter
            new PracticeTest().startTest();//start practice test
            int count = 0;
            for(int i = 0; i <5; i++)
            {
                new PracticeTest().AttemptCorrectAnswer(0,"47");
                count++;
            }
            new PracticeTest().quitThePracticeTest();


        } catch (Exception e) {
            Assert.fail("Exception in testcase studentNotSubmittedPracticeTestQs of class StudentSubmittedQs", e);
        }

    }

    @Test(priority = 5,enabled = true)
    public  void studentSubmittedStaticTestQs()
    {
        try {
            DiagnosticTab diagnosticTab= PageFactory.initElements(driver,DiagnosticTab.class);
            ProficiencyReport proficiencyReport=PageFactory.initElements(driver,ProficiencyReport.class);

            String diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", "49");
            for(int i=0;i<8;i++) {
                new Assignment().addQuestions(49, "truefalse", "4.1 Concept Check"); //add question in chapter 4 static test
             }
             new LoginUsingLTI().ltiLogin("49");//login as student
             new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
             new TopicOpen().chapterOpen(3);
             new SelectCourse().selectInvisibleAssignment("4.1 Concept Check");
             new AttemptTest().StaticTest();// start static test
             new Assignment().deActivateQuestion(49,diaAssessment,1);
            //Expected 1
            new LoginUsingLTI().ltiLogin("49");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TopicOpen().chapterOpen(3);
            new SelectCourse().selectInvisibleAssignment("4.1 Concept Check"); //open static assessments of fourth chapter

            diagnosticTab.nextChart.click();//click on the next chart

            //Expected 1
            Thread.sleep(3000);
            new Click().clickbylistcssselector("g.highcharts-series.highcharts-tracker > rect", 0); //click on the deactivate question bar type
            new QuestionCard().clickOnInvisibleCard("49",10); //click on the deActivated question card

            //Expected 2
            String deActivatedQuestionMsg="This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(diagnosticTab.deActivatedMsg.getText().trim(),deActivatedQuestionMsg,"This question is deactivated and is no longer part of this assignment/practice is not showing");

            //Expected 3
            Thread.sleep(5000);
            boolean hidden=diagnosticTab.contentIssue_icon.isDisplayed();
            if(hidden==true)
                Assert.fail(" “Report Content Issue” icon is not hidden for a deactivated question.");

            //Tc row no 52
            new Navigator().navigateToTab("Discussion");
            String discussionText = new RandomString().randomstring(6);
            new Discussion().postDiscussion(discussionText);//post a discussion

            new Navigator().NavigateTo("Course Stream");//navigate to course stream
            Thread.sleep(3000);
            diagnosticTab.jumpOut_icon.click();//click on the jump out icon
            verifyMessage(52);

           //Tc row no 55
            new Navigator().NavigateTo("My Activity"); //Navigate to my activity
            new Click().clickBycssselector("a[class='assessment-title QWERTY']");
            new QuestionCard().clickOnInvisibleCard("52",10);
            String deActivatedQuestionMsg1="This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(diagnosticTab.deActivatedMsg.getText().trim(),deActivatedQuestionMsg1,"This question is deactivated and is no longer part of this assignment/practice is not showing");

            //Expected 3
            Thread.sleep(3000);
            boolean hidden1=diagnosticTab.contentIssue_icon.isDisplayed();
            if(hidden1==true)
                Assert.fail(" “Report Content Issue” icon is not hidden for a deactivated question.");

            //Tc row no 58
            new Navigator().NavigateTo("Proficiency Report"); //navigate to Proficiency Report
            deActivatedStaticAssessmentQuestionNumber(58);
            //Tc row no 59
            proficiencyReport.getBarChart().click();// click on the chapter number
            Thread.sleep(2000);
            deActivatedStaticAssessmentQuestionNumber(59);
            //Tc row no 60
            new Click().clickbylistcssselector("g.highcharts-series.highcharts-tracker > rect", 0); //click on the tlo question
            Thread.sleep(2000);
            deActivatedStaticAssessmentQuestionNumber(60);

            //Tc row no 62
            new Click().clickbylistcssselector("g.highcharts-series.highcharts-tracker > rect", 10); //click on the deactivate question bar type

            //Expected 2
            String deActivatedQuestionMsg12="This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(diagnosticTab.deActivatedMsg.getText().trim(),deActivatedQuestionMsg12,"This question is deactivated and is no longer part of this assignment/practice is not showing");

            //Expected 3
            Thread.sleep(3000);
            boolean hidden12=diagnosticTab.contentIssue_icon.isDisplayed();
            if(hidden12==true)
                Assert.fail(" “Report Content Issue” icon is not hidden for a deactivated question.");

            String correct=diagnosticTab.studentGotCorrectPer.getText().trim();
            if(!correct.contains("Students got it correct"))
                Assert.fail("Students got it correct messgae is not displayed");


        } catch (Exception e) {
            Assert.fail("Exception in testcase studentSubmittedStaticTestQs of class StudentSubmittedQs", e);
        }

    }
    @Test(priority = 6,enabled = true)
    public  void studentNotSubmittedStaticTestQs() {
        String expected = "";
        try {
            String diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", "64");
            DiagnosticTab diagnosticTab = PageFactory.initElements(driver, DiagnosticTab.class);
            new Assignment().deActivateQuestion(64,diaAssessment,1);
            new LoginUsingLTI().ltiLogin("64");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TopicOpen().chapterOpen(4);
            new SelectCourse().selectInvisibleAssignment("5.1 Concept Check"); //open static assessments of fourth chapter
            new AttemptTest().StaticTest();// start static test

            //Expected 1
            new LoginUsingLTI().ltiLogin("64");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TopicOpen().chapterOpen(4);
            new SelectCourse().selectInvisibleAssignment("5.1 Concept Check"); //open static assessments of fourth chapter

            String questionCount = diagnosticTab.questionCount.getText().trim();
            int countValue = Integer.parseInt(questionCount);
            if (countValue > 2)
                Assert.fail("Number of Questions count is not less than the original one");


        } catch (Exception e) {
            Assert.fail("Exception in testcase studentNotSubmittedStaticTestQs of class StudentSubmittedQs", e);
        }

    }

    @Test(priority = 7,enabled = true)
    public  void gradableAssignmentForDeactivatedQuestion() {

        try {
            //Tc row no 69
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "69");
            String diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", "69");

            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            DiagnosticTab diagnosticTab = PageFactory.initElements(driver, DiagnosticTab.class);
            ProficiencyReport proficiencyReport=PageFactory.initElements(driver, ProficiencyReport.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            Dashboard dashboard=PageFactory.initElements(driver,Dashboard.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);

            new LoginUsingLTI().ltiLogin("69_1");//login as student1
            new LoginUsingLTI().ltiLogin("69_2");//login as student2
            new LoginUsingLTI().ltiLogin("69_3");//login as student3
            new LoginUsingLTI().ltiLogin("69_4");//login as student4

            new LoginUsingLTI().ltiLogin("69"); //login as instructor
            new Assignment().create(69); //create assignment
            new Assignment().addQuestions(69,"truefalse","");
            new Assignment().addQuestions(69,"truefalse","");
            new Assignment().addQuestions(69,"multiplechoice","");
            new Assignment().addQuestions(69,"truefalse","");
            new Assignment().addQuestions(69,"truefalse","");
            new LoginUsingLTI().ltiLogin("69");//login as instructor
            new Assignment().assignToStudent(69); //assign to the student

            new LoginUsingLTI().ltiLogin("69_2");//login as student2
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name
            new AttemptQuestion().trueFalse(false,"incorrect",69);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false,"correct",69);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false,"correct",69);


            new LoginUsingLTI().ltiLogin("69_3");//login as student3
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name
            new AttemptQuestion().trueFalse(false,"correct",69);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false,"correct",69);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false,"correct",69);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().multipleChoice(false, "correct", 136);//skip the question
            new Assignment().nextButtonInQuestionClick(); //click on the next button


            new LoginUsingLTI().ltiLogin("69_4");//login as student4
            new Assignment().submitAssignmentAsStudent(69);//submit the Assignment

            new Assignment().deActivateParticularQuestion(69,diaAssessment); //deactivate fourth position question

            new LoginUsingLTI().ltiLogin("69_1");//login as student1
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name

            //Expected:1
            assignments.getArrowDropDown().click();
            int questionCount = assignments.getQuestionCount().size();
            if(questionCount >5) {
                Assert.fail("summary dropdown is showing the deactivated question");
            }

            //Expected:3
            List<WebElement> questionLabel=assignments.questionLabel;
            boolean found=false;
            for(WebElement str:questionLabel)
            {
                if(str.getText().contains("Q6")) {
                    found = true;
                }
            }
            if(found == true)
                Assert.fail("Question label is not updated post question deactivation ");

            //Expected 4
            driver.findElement(By.xpath("/html/body")).click();
            new Assignment().submitAssignmentAsStudent(69);//submit the Assignment

            //Expected 5
            expectedForGradableAssignment(70);

            new LoginUsingLTI().ltiLogin("69_2");//login as student2
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name
            verifyExpectedForGradableAssignment(75);

            new AttemptQuestion().trueFalse(false,"correct",69);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false,"correct",69);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false,"correct",69);
            new Assignment().submitButtonInQuestionClick();

            expectedForGradableAssignment(75);

            new LoginUsingLTI().ltiLogin("69_3");//login as student3
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name
            verifyExpectedForGradableAssignment(81);

            new AttemptQuestion().trueFalse(false,"correct",69);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false,"correct",69);
            new Assignment().submitButtonInQuestionClick();

            boolean found1=false;
            List<String> deActivatedQuestionNo=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-content']");
            for(String str1:deActivatedQuestionNo)
            {
                if(str1.contains("Multiple Choice QuestionTextIT1869")) {
                    found1 = true;
                }
            }
            if(found1 == false)
                Assert.fail("Question card is displayed for deactivated question ");

            new Click().clickbylistcssselector("g.highcharts-series.highcharts-tracker > rect", 3); //click on the deactivate question bar type
            String deActivatedQuestionMsg="This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(diagnosticTab.deActivatedMsg.getText().trim(),deActivatedQuestionMsg,"This question is deactivated and is no longer part of this assignment/practice is not showing");

            new LoginUsingLTI().ltiLogin("69_4");//login as student4
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name
            boolean found11=false;
            Thread.sleep(5000);
            List<String> deActivatedQuestionNo1=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-content']");
            for(String str11:deActivatedQuestionNo1)
            {
                if(str11.contains("Multiple Choice QuestionTextIT1869")) {
                    found11 = true;
                }
            }
            if(found11 == false)
                Assert.fail("Question card is displayed for deactivated question ");

            new Click().clickbylistcssselector("g.highcharts-series.highcharts-tracker > rect", 3); //click on the deactivate question bar type
            String deActivatedQuestionMsg1="This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(diagnosticTab.deActivatedMsg.getText().trim(),deActivatedQuestionMsg1,"This question is deactivated and is no longer part of this assignment/practice is not showing");


            new LoginUsingLTI().ltiLogin("69"); //login as instructor
            new Assignment().provideGradeToStudentForMultipleQuestions(69);
            new Assignment().releaseGrades(69,"Release Grade for All");

            new LoginUsingLTI().ltiLogin("69_1");//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            verifyMessageAfterReleaseGrade();
            new LoginUsingLTI().ltiLogin("69_2");//login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            verifyMessageAfterReleaseGrade();

            new LoginUsingLTI().ltiLogin("69_3");//login as student3
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score1=assignments.getScore().getText();
            Assert.assertEquals(score1,"Score (3.6/6)","Score for assignment over assignment page is included deactivated question");


            currentAssignments.getAssessmentName().click(); //click on the Assignment name
            if(!assignmentTab.questionCount.getText().trim().contains("6"))
            {
                Assert.fail("Ddeactivated is displayed in performance summary");
            }
            new Navigator().navigateToTab("Assignments");
            String score2=assignments.getMarks().getText();
            Assert.assertEquals(score2,"Score (3.6/6)","Score for assignment in assignment tab at right is included deactivated question");
            Thread.sleep(2000);
            //Expected 2
            new Navigator().NavigateTo("Dashboard");
            String questionAttempt=dashboard.getQuestionAttempted().getText();
            Assert.assertEquals(questionAttempt,"6","Questions attempted count over dashboard is included deactivated question");

            //Expected 3
            dashboard.questionPerformance.click(); //click on the question performance
            multipleChoiceQuestionPresent();

            new Navigator().NavigateTo("Proficiency Report");
            multipleChoiceQuestionPresent();
            new QuestionCard().clickOnCard("106",2);
            Thread.sleep(4000);
            String deActivatedQuestionMsg2="This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(diagnosticTab.deActivatedMsg.getText().trim(),deActivatedQuestionMsg2,"This question is deactivated and is no longer part of this assignment/practice is not showing");

            new LoginUsingLTI().ltiLogin("69_4");//login as student4
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score6=assignments.getScore().getText();
            Assert.assertEquals(score6,"Score (3.6/6)","Score for assignment over assignment page is included deactivated question");


            currentAssignments.getAssessmentName().click(); //click on the Assignment name
            if(!assignmentTab.questionCount.getText().trim().contains("6"))
            {
                Assert.fail("Ddeactivated is displayed in performance summary");
            }
            new Navigator().navigateToTab("Assignments");
            String score7=assignments.getMarks().getText();
            Assert.assertEquals(score7,"Score (3.6/6)","Score for assignment in assignment tab at right is included deactivated question");
            Thread.sleep(2000);
            //Expected 2
            new Navigator().NavigateTo("Dashboard");
            String questionAttempt6=dashboard.getQuestionAttempted().getText();
            Assert.assertEquals(questionAttempt6,"6","Questions attempted count over dashboard is included deactivated question");

            //Expected 3
            dashboard.questionPerformance.click(); //click on the question performance
            multipleChoiceQuestionPresent();

            new Navigator().NavigateTo("Proficiency Report");
            multipleChoiceQuestionPresent();
            new QuestionCard().clickOnCard("106",2);
            Thread.sleep(4000);
            String deActivatedQuestionMsg3="This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(diagnosticTab.deActivatedMsg.getText().trim(),deActivatedQuestionMsg3,"This question is deactivated and is no longer part of this assignment/practice is not showing");



        } catch (Exception e) {
            Assert.fail("Exception in testcase gradableAssignmentForDeactivatedQuestion of class StudentSubmittedQs", e);
        }

    }

    @Test(priority = 8,enabled = true)
    public  void nonGradableAssignmentForDeactivatedQuestion() {

        try {
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "115");
            String diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", "115");

            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            DiagnosticTab diagnosticTab = PageFactory.initElements(driver, DiagnosticTab.class);
            Dashboard dashboard=PageFactory.initElements(driver,Dashboard.class);

            new LoginUsingLTI().ltiLogin("115_1");//login as student1
            new LoginUsingLTI().ltiLogin("115_2");//login as student2
            new LoginUsingLTI().ltiLogin("115_3");//login as student3

            new LoginUsingLTI().ltiLogin("115"); //login as instructor
            new Assignment().create(115); //create assignment
            new Assignment().addQuestions(115,"truefalse","");
            new Assignment().addQuestions(115,"truefalse","");
            new Assignment().addQuestions(115,"multiplechoice","");
            new Assignment().addQuestions(115,"truefalse","");
            new Assignment().addQuestions(115,"truefalse","");

            new LoginUsingLTI().ltiLogin("115");//login as instructor
            new Assignment().assignToStudent(115); //assign to the student

            new LoginUsingLTI().ltiLogin("115_2");//login as student2
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name
            new AttemptQuestion().trueFalse(false,"incorrect",115);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false,"correct",115);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false,"correct",115);
            new Assignment().nextButtonInQuestionClick(); //click on the next button


            new LoginUsingLTI().ltiLogin("115_3");//login as student3
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name
            new AttemptQuestion().trueFalse(false,"correct",115);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false,"correct",115);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false,"correct",115);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().multipleChoice(false, "correct", 136);//skip the question
            new Assignment().nextButtonInQuestionClick(); //click on the next button

            new Assignment().deActivateParticularQuestion(115,diaAssessment); //deactivate fourth position question


            new LoginUsingLTI().ltiLogin("115_1");//login as student1
            verifyNonGradableAssignment(115); //verify all 10 expected for student 1
            new LoginUsingLTI().ltiLogin("115_2");//login as student2
            verifyNonGradableAssignment(125);//verify all 10 expected for student 2

            new LoginUsingLTI().ltiLogin("115_3");//login as student3
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name

            assignments.getArrowDropDown().click();
            int questionCount = assignments.getQuestionCount().size();
            if(questionCount >5) {
                Assert.fail("summary dropdown is showing the deactivated question");
            }

            //Expected:3
            List<WebElement> questionLabel=assignments.questionLabel;
            boolean found=false;
            for(WebElement str:questionLabel)
            {
                if(str.getText().contains("Q6")) {
                    found = true;
                }
            }
            if(found == true)
                Assert.fail("Question label is not updated post question deactivation ");

            //Expected 4
            driver.findElement(By.xpath("/html/body")).click();
            new Assignment().submitAssignmentAsStudent(115);//submit the Assignment

            //Expected 5
            multipleChoiceQuestionPresentForNonGradable();

            //Expected 7
            new QuestionCard().clickOnCard("106",3);
            Thread.sleep(4000);
            String deActivatedQuestionMsg2="This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(diagnosticTab.deActivatedMsg.getText().trim(),deActivatedQuestionMsg2,"This question is deactivated and is no longer part of this assignment/practice is not showing");

            new Navigator().NavigateTo("Dashboard");
            String questionAttempt=dashboard.getQuestionAttempted().getText();
            Assert.assertEquals(questionAttempt,"6","Questions attempted count over dashboard is included deactivated question");

            //Expected 7
            dashboard.questionPerformance.click(); //click on the question performance
            multipleChoiceQuestionPresentForNonGradable();
            new Navigator().NavigateTo("Proficiency Report");
            multipleChoiceQuestionPresentForNonGradable();

            new QuestionCard().clickOnCard("106",2);
            Thread.sleep(4000);

            String deActivatedQuestionMsg="This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(diagnosticTab.deActivatedMsg.getText().trim(),deActivatedQuestionMsg,"This question is deactivated and is no longer part of this assignment/practice is not showing");

            //Expected 3
            Thread.sleep(3000);
            boolean hidden=diagnosticTab.contentIssue_icon.isDisplayed();
            if(hidden==true)
                Assert.fail(" “Report Content Issue” icon is not hidden for a deactivated question.");



        } catch (Exception e) {
            Assert.fail("Exception in testcase gradableAssignmentForDeactivatedQuestion of class StudentSubmittedQs", e);
        }

    }

    @Test(priority = 9,enabled = false)
    public  void courseStreamEntryForDeActivatedQuestion() {

        try {
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            new LoginUsingLTI().ltiLogin("149_1");//login as student1
            new LoginUsingLTI().ltiLogin("149_2");//login as student2

            new LoginUsingLTI().ltiLogin("149"); //login as instructor
            new Assignment().create(149); //create assignment
            new Assignment().addQuestions(149,"truefalse","");
            new Assignment().addQuestions(149,"truefalse","");
            new Assignment().addQuestions(149,"multiplechoice","");
            new LoginUsingLTI().ltiLogin("149"); //login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank


            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(149);//assign assignment


        } catch (Exception e) {
            Assert.fail("Exception in testcase courseStreamEntryForDeActivatedQuestion of class StudentSubmittedQs", e);
        }

    }


    @Test(priority = 10,enabled = true)
    public  void gradeAssignmentHavingDeactivatedQuestion() {

        try {
            String diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", "157");
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", "157");

            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            Dashboard dashboard=PageFactory.initElements(driver,Dashboard.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Gradebook gradebook= PageFactory.initElements(driver,Gradebook.class);
            EngagementReport engagementReport= PageFactory.initElements(driver,EngagementReport.class);

            new LoginUsingLTI().ltiLogin("157_1");//login as student1

            new LoginUsingLTI().ltiLogin("157"); //login as instructor
            new Assignment().create(157); //create assignment
            new Assignment().addQuestions(157,"truefalse","");
            new Assignment().addQuestions(157,"truefalse","");
            new Assignment().addQuestions(157,"multiplechoice","");

            new LoginUsingLTI().ltiLogin("157"); //login as instructor
            new Assignment().assignToStudent(157);

            new Assignment().deActivateParticularQuestion(157,diaAssessment); //deactivate multiple choice question

            new LoginUsingLTI().ltiLogin("157"); //login as instructor
            //Tc row no 157
            new Navigator().NavigateTo("Current Assignments"); //navigate to current assignment
            currentAssignments.getViewGrade_link().click(); //click on the view student response
            int columnSize=assignmentResponsesPage.getQuestionLabels().size();
            Assert.assertEquals(columnSize, 3, "Column for deactivated question label is available");

            //Tc row no 157
            Assert.assertEquals(assignmentResponsesPage.getTotalPoints().getText().trim(),"Total Points: 3","Total points displayed is not excluding deactivated question");

           //Tc row no 158
            assignmentResponsesPage.assessmentName_AssignmentResponse.get(1).click();//click on the assessment name
           Thread.sleep(3000);
           String QuestionName="Multiple Choice"+questiontext;
           System.out.println("QuestionName:"+QuestionName);
           deActivateQuestionInAssignmentReview(158,QuestionName);

            //Tc row no 159
            new Navigator().NavigateTo("Current Assignments"); //navigate to current assignment
            assignmentResponsesPage.assessmentName_AssignmentResponse.get(0).click();//click on the assessment name
            Thread.sleep(3000);
            deActivateQuestionInAssignmentReview(159,QuestionName);

            //Tc row no 160
           new LoginUsingLTI().ltiLogin("157_1");//login as student1
            new Assignment().submitAssignmentAsStudent(157);

            new LoginUsingLTI().ltiLogin("157"); //login as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to current assignment
            currentAssignments.getViewGrade_link().click(); //click on the view student response

            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link

            //Tc row no 161
            deActivateQuestionInAssignmentReview(159,QuestionName);
            currentAssignments.getNextArrow().click();

            deActivateQuestionInAssignmentReview(159,QuestionName);
            Thread.sleep(1000);
            currentAssignments.getNextArrow().click();
            deActivateQuestionInAssignmentReview(159,QuestionName);

            //Tc row no 162

            new Assignment().provideGradeToStudentForMultipleQuestions(157);
            new Assignment().releaseGrades(157,"Release Grade for All");

            //Tc row no 165
            new LoginUsingLTI().ltiLogin("157"); //login as instructor

            new Navigator().NavigateTo("Gradebook"); //navigate to gradeBook page
            Assert.assertEquals(gradebook.questionAttempt_Count.getText().trim(),"1.8/3","Score displayed over gradebook page has not excluded deactivated question");

           //Tc row no 166
            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("157"); //login as instructor
            dashboard=PageFactory.initElements(driver,Dashboard.class);
            engagementReport= PageFactory.initElements(driver,EngagementReport.class);


            new Navigator().NavigateTo("Dashboard");
            Assert.assertEquals(dashboard.getQuestionAttempted().getText().trim(),"3" ,"Question attempted count over dashboard tile has not excluded deactivated question");

            //Tc row no 167
            new Navigator().NavigateTo("Engagement Report");
            Assert.assertEquals(engagementReport.questionPerformance_Count.getText().trim(),"3" ,"Question attempted count over engagement report has not excluded deactivated question");





        } catch (Exception e) {
            Assert.fail("Exception in testcase gradeAssignmentHavingDeactivatedQuestion of class StudentSubmittedQs", e);
        }

    }
    @Test(priority = 11,enabled = true)
    public  void studentAttemptDeActivatedQuestionOfGradableAssignment() {

        try {
            //Tc row no 171
            String diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", "171");
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", "171");
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);


            new LoginUsingLTI().ltiLogin("171_1");//login as student1
            new LoginUsingLTI().ltiLogin("171_2");//login as student2
            new LoginUsingLTI().ltiLogin("171_3");//login as student3
            new LoginUsingLTI().ltiLogin("171_4");//login as student4

            new LoginUsingLTI().ltiLogin("171"); //login as instructor
            new Assignment().create(171); //create assignment
            new Assignment().addQuestions(171, "essay", "");
            new Assignment().addQuestions(171, "essay", "");
            new Assignment().addQuestions(171, "multiplechoice", "");
            new Assignment().addQuestions(171, "truefalse", "");
            new Assignment().addQuestions(171, "truefalse", "");
            new LoginUsingLTI().ltiLogin("171");//login as instructor
            new Assignment().assignToStudent(171); //assign to the student

            new LoginUsingLTI().ltiLogin("171_2");//login as student2
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name
            new WebDriverWait(driver, 300).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 171);
            new Assignment().nextButtonInQuestionClick(); //click on the next button*//*
            Thread.sleep(2000);
            new AttemptQuestion().essay(false, "incorrect", 171);//attempt incorrect
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().essay(false, "incorrect", 171);//attempt incorrect
            new Assignment().nextButtonInQuestionClick(); //click on the next button


            new LoginUsingLTI().ltiLogin("171_3");//login as student3
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name
            new WebDriverWait(driver, 300).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));

            new AttemptQuestion().trueFalse(false, "correct", 171);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().essay(false, "incorrect", 171);//attempt incorrect
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().essay(false, "incorrect", 171);//attempt incorrect
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);

            new AttemptQuestion().multipleChoice(false, "correct", 171);//skip the question
            new Assignment().nextButtonInQuestionClick(); //click on the next button


            new LoginUsingLTI().ltiLogin("171_4");//login as student4
            new Assignment().submitAssignmentAsStudent(171);//submit the Assignment

            new Assignment().deActivateParticularQuestion(171, diaAssessment); //deactivate fourth position question
            new LoginUsingLTI().ltiLogin("171"); //login as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to current assignment
            currentAssignments.getViewGrade_link().click(); //click on the view student response

            //Tc row no 171
            boolean lastQuestionLabel = assignmentResponsesPage.deActivateQuestionPosition.isDisplayed();
            Assert.assertEquals(lastQuestionLabel, true, "Questions column for deactivated question not appear at the end");

            //Tc row no 173
            String deActivatedQuestionMsg = assignmentResponsesPage.deActivateQuestionPosition.getAttribute("title").trim();
            Assert.assertEquals(deActivatedQuestionMsg, "This question has been deactivated by the author", "“D” icon is not displayed for deactivated questionwith the tool tip");

            //Tc row no 174
            String marksForStudent = assignmentResponsesPage.deActivatedQuestion_Text.getText().trim();
            if (!marksForStudent.contains("1.0"))
                Assert.fail("Marks based on student is not displayed under deactivated question column for student 3 and 4");

            //Tc row no 175
            String tickMark = assignmentResponsesPage.tickMark_assayQuestion.getCssValue("background-image").trim();
            System.out.println("TickMArk:" + tickMark);
            if (!tickMark.contains("/images/ls/ls_x_icon_gradebook.png"))
                Assert.fail("Tick mark icon is not displayed under deactivated question column if deactivated question is manually graded");

            //Tc row no 177
            Assert.assertEquals(assignmentResponsesPage.getTotalPoints().getText().trim(), "Total Points: 6", "Total points displayed is not excluding deactivated question");

            //Tc row no 178

            assignmentResponsesPage.assessmentName_AssignmentResponse.get(1).click();//click on the assessment name
            Thread.sleep(3000);
            String QuestionName = "Multiple Choice" + questiontext;
            System.out.println("QuestionName:" + QuestionName);
            deActivateQuestionInAssignmentReview(178, QuestionName);

            //Tc row no 179
            new Navigator().NavigateTo("Current Assignments"); //navigate to current assignment
            assignmentResponsesPage.assessmentName_AssignmentResponse.get(0).click();//click on the assessment name
            Thread.sleep(3000);
            deActivateQuestionInAssignmentReview(179, QuestionName);

            new LoginUsingLTI().ltiLogin("171_1");//login as student1
            new Assignment().submitAssignmentAsStudent(171);
            new LoginUsingLTI().ltiLogin("171_2");//login as student2
            new Assignment().submitAssignmentAsStudent(171);
            new LoginUsingLTI().ltiLogin("171_3");//login as student3
            new Assignment().submitAssignmentAsStudent(171);

            new LoginUsingLTI().ltiLogin("171");//login as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to current assignment

            //Tc row no 180
            String status = currentAssignments.status_reviewInProgress.getText();
            Assert.assertEquals(status, "Needs Grading", "status is not showing review in progress");

            //Tc row no 181
            String submitBoxCount = currentAssignments.getSubmittedBoxCount().getText();
            Assert.assertEquals(submitBoxCount, "4", "submitbox is not displaying the total student count");

            //Tc row no 182
            currentAssignments.getViewGrade_link().click();//click on view student responses
            String deActivatedQuestionMsg1 = assignmentResponsesPage.deActivateQuestionPosition.getAttribute("title").trim();
            Assert.assertEquals(deActivatedQuestionMsg1, "This question has been deactivated by the author", "Deactivated question column is not available");

            //Tc row no 183 and 184
            String xIconForStudent = assignmentResponsesPage.xIcon_deActivatedQuestion.getAttribute("title").trim();
            if (!xIconForStudent.contains("Question not delivered"))
                Assert.fail("Marks based on student is not displayed under deactivated question column for student 3 and 4");

            //Tc row no 185
            String marksForStudent1 = assignmentResponsesPage.deActivatedQuestion_Text.getText().trim();
            if (!marksForStudent1.contains("1.0"))
                Assert.fail("Marks based on student is not displayed under deactivated question column for student 3 and 4");

            //Tc row no 187 & 188

            new Assignment().provideGradeToStudentForMultipleQuestions(171);

            new LoginUsingLTI().ltiLogin("171");//login as instructor
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            currentAssignments.getViewGrade_link().click(); //click on the view student response*/
            enterGradeOnParticularQuestion(2, 5, "0.7");
            Thread.sleep(5000);
            enterGradeOnParticularQuestion(3, 5, "0.7");

            String marksForStudent3 = assignmentResponsesPage.totalMark.getText().trim();
            if (!marksForStudent3.contains("3.7"))
                Assert.fail("Total mark for student 3 and 4 has not consider deactivated question");

            //Tc row no 189
            int size = driver.findElements(By.xpath("//img[@title='Graded']")).size();
            if (size < 4)
                Assert.fail("Tick mark icon is not displayed for all students");

            //Tc row no 190

            String gradedBoxCount = assignmentResponsesPage.graded_box.get(2).getText().trim();
            Assert.assertEquals(gradedBoxCount, "4", "gradedBoxCount is not displaying the total student count");

            //Tc row no 191
            new Assignment().releaseGrades(171, "Release Grade for All");

            //Tc row no 193
            String xIconForStudent2 = assignmentResponsesPage.xIcon_deActivatedQuestion.getAttribute("title").trim();
            if (!xIconForStudent2.contains("Question not delivered"))
                Assert.fail("View resposnes for deactivated question is not appear for student 1 and 2");

            //Tc row no 194
            WebElement thirdQuestion = driver.findElement(By.xpath("(//span[@class='idb-gradebook-assignment-username'])[last()]/ancestor::div[2]/div[@class='idb-question-score-wrapper']/div[last()]/span"));
            Thread.sleep(5000);
            new MouseHover().mouserhoverbywebelement(thirdQuestion);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));

            //Tc row no 198

            new Navigator().NavigateTo("Engagement Report");
            String attemptedQuestion1 = assignmentResponsesPage.attemptedQuestion.get(0).getText().trim();
            if (!attemptedQuestion1.equals("5"))
                Assert.fail("Question attempted count over engagement report should exclude deactivated question for student 1,2");

            String attemptedQuestion2 = assignmentResponsesPage.attemptedQuestion.get(1).getText().trim();
            if (!attemptedQuestion2.equals("5"))
                Assert.fail("Question attempted count over engagement report should exclude deactivated question for student 1,2");

            //Tc row no 199
            String attemptedQuestion3 = assignmentResponsesPage.attemptedQuestion1.get(0).getText().trim();
            if (!attemptedQuestion3.equals("6"))
                Assert.fail("Question attempted count over engagement report should exclude deactivated question for student 3,4");

            String attemptedQuestion4 = assignmentResponsesPage.attemptedQuestion1.get(1).getText().trim();
            if (!attemptedQuestion4.equals("6"))
                Assert.fail("Question attempted count over engagement report should exclude deactivated question for student 3,4");


        } catch (Exception e) {
            Assert.fail("Exception in testcase studentAttemptDeActivatedQuestionOfGradableAssignment of class StudentSubmittedQs", e);
        }
    }
        @Test(priority = 12, enabled = true)
        public void  courseStreamEntryForDiscussionAddedToDeActivatedQuestion() {
            try {
                 //Tc row np 202
                String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "202");
                String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "202");
                MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
                CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                Assignments assignments = PageFactory.initElements(driver,Assignments.class);
                new Assignment().create(203);
                new Assignment().addQuestions(203,"multiplechoice","");
                new LoginUsingLTI().ltiLogin("202_1");//create student1
                new LoginUsingLTI().ltiLogin("202_2");//create student2
                new LoginUsingLTI().ltiLogin("202");//log in as instructor
                new Navigator().NavigateTo("My Question Bank");//click on my question bank
                myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
                Thread.sleep(2000);
                new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
                String questionName=driver.findElement(By.className("ls-search-question-text")).getText().trim();
                System.out.println("questionName:"+questionName);

                new AssignLesson().selectQuestionForCustomAssignment("202");//select two question
                myQuestionBank.getAssignmentNameField().click();//click on name field
                myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
                myQuestionBank.getSaveForLater().click();//click on save for later
                Thread.sleep(5000);
                myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
                new AssignLesson().Assigncustomeassignemnt(202);//assign assignment
                new LoginUsingLTI().ltiLogin("202_1");//login as student1
                new Navigator().NavigateTo("Assignments");
                new Assignment().submitAssignmentAsStudent(202); //submit assignment
                Thread.sleep(2000);
                new QuestionCard().clickOnCard("202",0);
                assignments.getDiscussionTab().click();
                assignments.getNewButton().click();
                assignments.getEditBox().sendKeys("This is new discussion");
                assignments.getSubmit().click();

                new LoginUsingLTI().ltiLogin("202");//log in as instructor*//*

                new OpenSearchPage().openSearchPage(); //open search page  through cms
                new OpenSearchPage().searchquestion(questionName);
                Thread.sleep(5000);
                deActivateStaticAssessment(202); //deActivate questionn

                new LoginUsingLTI().ltiLogin("202");//log in as instructor
                new Navigator().NavigateTo("Course Stream");
                courseStreamPage.getJumpOut().click();//click on jumpOut icon
                Thread.sleep(4000);
                verifyMessage(202);


            } catch (Exception e) {
                Assert.fail("Exception in test case courseStreamEntryForDiscussionAddedToDeActivatedQuestion in class StudentSubmittedQs", e);
            }
        }
    @Test(priority = 13, enabled = true)
    public void  proficiencyReportForDeActivatedQuestion() {
        try {
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "204");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "204");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

            new Assignment().create(205);
            new Assignment().addQuestions(205,"multiplechoice","");
            new Assignment().addQuestions(205,"multipleselection","");
            new Assignment().addQuestions(205,"textentry","");
            new Assignment().addQuestions(205,"textdropdown","");
            new Assignment().addQuestions(205,"multiplechoice","");
            new Assignment().addQuestions(205,"multiplechoice","");
            new Assignment().addQuestions(205,"multiplechoice","");
            new Assignment().addQuestions(205,"multiplechoice","");
            new Assignment().addQuestions(205,"multiplechoice","");
            new Assignment().addQuestions(205,"multipleselection","");
            new Assignment().addQuestions(205,"multipleselection","");
            new LoginUsingLTI().ltiLogin("204_1");//create student1
            new LoginUsingLTI().ltiLogin("204_2");//create student2
            new LoginUsingLTI().ltiLogin("204");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            String questionName=driver.findElement(By.className("ls-search-question-text")).getText().trim();
            System.out.println("questionName:"+questionName);

            new AssignLesson().selectQuestionForCustomAssignment("204");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(204);//assign assignment
            new LoginUsingLTI().ltiLogin("204_1");//login as student1
            new Navigator().NavigateTo("Assignments");
            new Assignment().submitAssignmentAsStudent(204); //submit assignment by student1

            new OpenSearchPage().openSearchPage(); //open search page  through cms
            new OpenSearchPage().searchquestion(questionName);
            Thread.sleep(5000);
            deActivateStaticAssessment(204); //deActivate question

            new LoginUsingLTI().ltiLogin("204_2");//login as student2
            new Navigator().NavigateTo("Assignments");
            new Assignment().submitAssignmentAsStudent(204); //submit assignment by student2

            new LoginUsingLTI().ltiLogin("204");//log in as instructor
            new Assignment().releaseGrades(204,"Release Grade for All");


            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("204"); //login as instructor


        } catch (Exception e) {
            Assert.fail("Exception in test case proficiencyReportForDeActivatedQuestion in class StudentSubmittedQs", e);
        }
    }

    @Test(priority = 14, enabled = true)
    public void  assignmentWithPolicy1ForDeActivatedQuestion() {
        try {
             //Tc row no 206
            String diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", "206");
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "206");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "206");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "206");
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);

            new LoginUsingLTI().ltiLogin("206");//log in as instructor
            new LoginUsingLTI().ltiLogin("206_1");//log in as student1
            new LoginUsingLTI().ltiLogin("206_2");//log in as student2

            new Assignment().create(206); //create assignment
            new Assignment().addQuestions(206,"truefalse","");
            new Assignment().addQuestions(206,"truefalse","");
            new Assignment().addQuestions(206,"multiplechoice","");
            new Assignment().addQuestions(206,"multipleselection","");

            new LoginUsingLTI().ltiLogin("206");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//policy1


            new LoginUsingLTI().ltiLogin("206");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);

            new AssignLesson().selectQuestionForCustomAssignment("206");
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later

            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(206);//assign assignment

            new LoginUsingLTI().ltiLogin("206_1");//login as student1
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name
            Thread.sleep(3000);
            new AttemptQuestion().multipleSelection(false,"correct",206);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false,"correct",206);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false,"correct",206);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);


            new Assignment().deActivateParticularQuestion(206,diaAssessment); //deactivate fourth position question


            new LoginUsingLTI().ltiLogin("206_1");//log in as student1
            new Assignment().submitAssignmentAsStudent(206);

            new LoginUsingLTI().ltiLogin("206_2");//log in as student2
            new Assignment().submitAssignmentAsStudent(206);

            new LoginUsingLTI().ltiLogin("206_1");//log in as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            Thread.sleep(2000);
            int question1=assignments.getWidth().size();
            if(question1< 5){
                Assert.fail("scoreover assignment page is not included deleted question");
            }

            new LoginUsingLTI().ltiLogin("206_2");//log in as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            Thread.sleep(2000);
            int question2=assignments.getWidth().size();
            if(question2>5){
                Assert.fail("scoreover assignment page is not included deleted question");
            }
            new LoginUsingLTI().ltiLogin("206");//login as instructor
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            currentAssignments.getViewGrade_link().click(); //click on the view student response

            String deActivatedQuestionMarks1=new TextFetch().textfetchbyxpath("(//span[@class='idb-gradebook-assignment-username'])[position()<2]/ancestor::div[2]/div[@class='idb-question-score-wrapper']/div[last()]/span");
            Assert.assertEquals(deActivatedQuestionMarks1,"2.0","Deactivated question column has not contained value based on student response for student 1");

            //Tc row no 193
            String xIconForStudent2 = driver.findElement(By.xpath("(//span[@class='idb-gradebook-assignment-username'])[position()>1]/ancestor::div[2]/div[@class='idb-question-score-wrapper']/div[last()]/span")).getAttribute("title").trim();
            if (!xIconForStudent2.contains("Question not delivered"))
                Assert.fail("x icon is not displaying for student 2");



        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentWithPolicy1ForDeActivatedQuestion in class StudentSubmittedQs", e);
        }
    }
    @Test(priority = 15, enabled = true)
    public void  assignmentWithPolicy3ForDeActivatedQuestion() {
        try {
            //Tc row no 212
            String diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", "212");
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "212");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "212");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "212");
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);

            new LoginUsingLTI().ltiLogin("212");//log in as instructor
            new LoginUsingLTI().ltiLogin("212_1");//log in as student1
            new LoginUsingLTI().ltiLogin("212_2");//log in as student2
            new LoginUsingLTI().ltiLogin("212_3");//log in as student2

            new Assignment().create(212); //create assignment
            new Assignment().addQuestions(212,"truefalse","");
            new Assignment().addQuestions(212,"essay","");
            new Assignment().addQuestions(212,"multiplechoice","");
            new Assignment().addQuestions(212,"writeboard","");


            new LoginUsingLTI().ltiLogin("212");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//policy3


            new LoginUsingLTI().ltiLogin("212");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);

            new AssignLesson().selectQuestionForCustomAssignment("212");
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later

            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(212);//assign assignment

            new LoginUsingLTI().ltiLogin("212_1");//login as student1
            new Assignment().submitAssignmentAsStudent(212);

            new LoginUsingLTI().ltiLogin("212_2");//log in as student2
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name
            new AttemptQuestion().writeBoard(false,"correct",212);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().essay(false,"correct",212);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);


            new Assignment().deActivateParticularQuestion(212,diaAssessment); //deactivate fourth position question


            new LoginUsingLTI().ltiLogin("212_2");//log in as student2
            new Assignment().submitAssignmentAsStudent(212);
            new LoginUsingLTI().ltiLogin("212_3");//log in as student3
            new Assignment().submitAssignmentAsStudent(212);



        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentWithPolicy3ForDeActivatedQuestion in class StudentSubmittedQs", e);
        }
    }
    @Test(priority = 16,enabled = true)
    public  void studentAttemptDeActivatedQuestionOfNonGradableAssignment() {

        try {
            //Tc row no 209
            String diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", "219");
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", "219");
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);


            new LoginUsingLTI().ltiLogin("219_1");//login as student1
            new LoginUsingLTI().ltiLogin("219_2");//login as student2
            new LoginUsingLTI().ltiLogin("219_3");//login as student3
            new LoginUsingLTI().ltiLogin("219_4");//login as student4

            new LoginUsingLTI().ltiLogin("219"); //login as instructor
            new Assignment().create(219); //create assignment
            new Assignment().addQuestions(219, "truefalse", "");
            new Assignment().addQuestions(219, "truefalse", "");
            new Assignment().addQuestions(219, "multiplechoice", "");
            new Assignment().addQuestions(219, "truefalse", "");
            new Assignment().addQuestions(219, "truefalse", "");

            new LoginUsingLTI().ltiLogin("219");//login as instructor
            new Assignment().assignToStudent(219); //assign to the student

            new LoginUsingLTI().ltiLogin("219_2");//login as student2
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name
            new WebDriverWait(driver, 300).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 219);
            new Assignment().nextButtonInQuestionClick(); //click on the next button*//**//**//**//*
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false, "incorrect", 219);//attempt incorrect
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false, "incorrect", 219);//attempt incorrect
            new Assignment().nextButtonInQuestionClick(); //click on the next button


            new LoginUsingLTI().ltiLogin("219_3");//login as student3
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name
            new WebDriverWait(driver, 300).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));

            new AttemptQuestion().trueFalse(false, "correct", 219);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false, "incorrect", 219);//attempt incorrect
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false, "incorrect", 219);//attempt incorrect
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);

            new AttemptQuestion().multipleChoice(false, "correct", 219);//skip the question
            new Assignment().nextButtonInQuestionClick(); //click on the next button


            new LoginUsingLTI().ltiLogin("219_4");//login as student4
            new Assignment().submitAssignmentAsStudent(219);//submit the Assignment

            new Assignment().deActivateParticularQuestion(219, diaAssessment); //deactivate fourth position question
            new LoginUsingLTI().ltiLogin("219"); //login as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to current assignment
            currentAssignments.getViewGrade_link().click(); //click on the view student response

            //Tc row no 219
            boolean lastQuestionLabel = assignmentResponsesPage.deActivateQuestionPosition.isDisplayed();
            Assert.assertEquals(lastQuestionLabel, true, "Questions column for deactivated question not appear at the end");

            //Tc row no 221
            String deActivatedQuestionMsg = assignmentResponsesPage.deActivateQuestionPosition.getAttribute("title").trim();
            Assert.assertEquals(deActivatedQuestionMsg, "This question has been deactivated by the author", "“D” icon is not displayed for deactivated questionwith the tool tip");

            //Tc row no 222
            String tickMark = assignmentResponsesPage.tickMark_assayQuestion.getCssValue("background-image").trim();
            System.out.println("TickMArk:" + tickMark);
            if (!tickMark.contains("/images/ls/ls_x_icon_gradebook.png"))
                Assert.fail("Tick mark icon is not displayed under deactivated question column if deactivated question is manually graded");


            //Tc row no 224

            assignmentResponsesPage.assessmentName_AssignmentResponse.get(1).click();//click on the assessment name
            Thread.sleep(3000);
            String QuestionName = "Multiple Choice" + questiontext;
            System.out.println("QuestionName:" + QuestionName);
            deActivateQuestionInAssignmentReview(178, QuestionName);

            //Tc row no 225
            new Navigator().NavigateTo("Current Assignments"); //navigate to current assignment
            assignmentResponsesPage.assessmentName_AssignmentResponse.get(0).click();//click on the assessment name
            Thread.sleep(3000);
            deActivateQuestionInAssignmentReview(179, QuestionName);

            new LoginUsingLTI().ltiLogin("219_1");//login as student1
            new Assignment().submitAssignmentAsStudent(219);
            new LoginUsingLTI().ltiLogin("219_2");//login as student2
            new Assignment().submitAssignmentAsStudent(219);
            new LoginUsingLTI().ltiLogin("219_3");//login as student3
            new Assignment().submitAssignmentAsStudent(219);

            new LoginUsingLTI().ltiLogin("219");//login as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to current assignment

            //Tc row no 226
            String status = currentAssignments.status_reviewInProgress.getText();
            Assert.assertEquals(status, "Review in Progress", "status is not showing review in progress");

            //Tc row no 227
            String submitBoxCount = currentAssignments.getSubmittedBoxCount().getText();
            Assert.assertEquals(submitBoxCount, "4", "submitbox is not displaying the total student count");

            //Tc row no 228
            currentAssignments.getViewGrade_link().click();//click on view student responses
            String deActivatedQuestionMsg1 = assignmentResponsesPage.deActivateQuestionPosition.getAttribute("title").trim();
            Assert.assertEquals(deActivatedQuestionMsg1, "This question has been deactivated by the author", "Deactivated question column is not available");

            //Tc row no 183 and 233
            String xIconForStudent = assignmentResponsesPage.xIcon_deActivatedQuestion.getAttribute("title").trim();
            if (!xIconForStudent.contains("Question not delivered"))
                Assert.fail("Marks based on student is not displayed under deactivated question column for student 3 and 4");

            //Tc row no 234
            WebElement fourthQuestion = driver.findElement(By.xpath("(//span[@class='idb-gradebook-assignment-username'])[last()]/ancestor::div[2]/div[@class='idb-question-score-wrapper']/div[last()]/span"));
            Thread.sleep(5000);
            new MouseHover().mouserhoverbywebelement(fourthQuestion);
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));
            verifyMessage(234);

            //TC row no 235
            currentAssignments.getFeedBack_textBox().sendKeys("This is a FeedbackText");
            currentAssignments.getSave_button().click();
            //Tc row no 187 & 188


            new LoginUsingLTI().ltiLogin("219");//login as instructor
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            currentAssignments.getViewGrade_link().click(); //click on the view student response

            new Assignment().releaseGrades(219,"Release Feedback for All");

            //Tc row no 237
            String status1=assignmentResponsesPage.getReviewStatus().getText();
            Assert.assertEquals(status1,"Reviewed","Reviewed status is not displaying after released feedback for all");

            //
            new Navigator().NavigateTo("Engagement Report");
            String attemptedQuestion1 = assignmentResponsesPage.attemptedQuestion.get(0).getText().trim();
            if (!attemptedQuestion1.equals("5"))
                Assert.fail("Question attempted count over engagement report should exclude deactivated question for student 1,2");

            String attemptedQuestion2 = assignmentResponsesPage.attemptedQuestion.get(1).getText().trim();
            if (!attemptedQuestion2.equals("5"))
                Assert.fail("Question attempted count over engagement report should exclude deactivated question for student 1,2");

            //Tc row no 199
            String attemptedQuestion3 = assignmentResponsesPage.attemptedQuestion1.get(0).getText().trim();
            if (!attemptedQuestion3.equals("6"))
                Assert.fail("Question attempted count over engagement report should exclude deactivated question for student 3,4");

            String attemptedQuestion4 = assignmentResponsesPage.attemptedQuestion1.get(1).getText().trim();
            if (!attemptedQuestion4.equals("6"))
                Assert.fail("Question attempted count over engagement report should exclude deactivated question for student 3,4");



        } catch (Exception e) {
            Assert.fail("Exception in testcase studentAttemptDeActivatedQuestionOfNonGradableAssignment of class StudentSubmittedQs", e);
        }
    }

    @Test(priority = 17, enabled = true)
    public void  courseStreamEntryForDeActivationQuestion() {
        try {
            //Tc row no 149
            DiagnosticTab diagnosticTab = PageFactory.initElements(driver, DiagnosticTab.class);
            String diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", "150");
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "150");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "150");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("150");//log in as instructor
            new LoginUsingLTI().ltiLogin("150_1");//log in as student1
            new LoginUsingLTI().ltiLogin("150_2");//log in as student2

            new Assignment().create(150); //create assignment
            new Assignment().addQuestions(150,"truefalse","");
            new Assignment().addQuestions(150,"essay","");

            new LoginUsingLTI().ltiLogin("150");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);

            new AssignLesson().selectQuestionForCustomAssignment("150");
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later

            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(150);//assign assignment

            new LoginUsingLTI().ltiLogin("150_1");//login as student1
            new Assignment().submitAssignmentAsStudent(150);

            new LoginUsingLTI().ltiLogin("150_1");//login as student1
            new Navigator().NavigateTo("Assignments");
            currentAssignments.getAssessmentName().click(); //click on the assignment name

            Thread.sleep(5000);
            new QuestionCard().clickOnCard("150",1);

            new Navigator().navigateToTab("Discussion");
            String discussionText = new RandomString().randomstring(6);
            new Discussion().postDiscussion(discussionText);//post a discussion


            new Assignment().deActivateParticularQuestion(150,diaAssessment);
            //Tc row no 38
            new LoginUsingLTI().ltiLogin("150_1");//login as student1

            new Navigator().NavigateTo("My Activity"); //Navigate to my activity
            diagnosticTab.discussion_icon.get(0).click(); //click on the discussion icon
            diagnosticTab.discussion_icon.get(0).click(); //click on the discussion icon
            //Assert.assertEquals(diagnosticTab.questionName.getText().trim(),questionName,"Student is not navigated to the deactivated question");
            verifyMessage(21);



        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentWithPolicy3ForDeActivatedQuestion in class StudentSubmittedQs", e);
        }
    }



    public void verifyMessage(int dataIndex)
    {
        DiagnosticTab diagnosticTab= PageFactory.initElements(driver,DiagnosticTab.class);
        try {

            String deActivatedQuestionMsg="This question is deactivated and is no longer part of this assignment/practice.";
            Assert.assertEquals(diagnosticTab.deActivatedMsg.getText().trim(),deActivatedQuestionMsg,"This question is deactivated and is no longer part of this assignment/practice is not showing");

            //Expected 3
            Thread.sleep(3000);
            boolean hidden=new BooleanValue().presenceOfElement(17,By.cssSelector("div[class='add-content-error show-content-issues-dialog']"));
            if(hidden==true)
                Assert.fail(" “Report Content Issue” icon is not hidden for a deactivated question.");
        } catch (InterruptedException e) {
            Assert.fail("Exception in method verifyMessage of class StudentSubmittedQs", e);
        }

    }
    public void deActivateQuestion(int dataIndex)
    {
        List<String> questionNumber=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-no']");
        boolean found=false;
        for(String str:questionNumber)
        {
            if(str.equals("4.1")) {
                found = true;
            }
        }
        if(found == false)
            Assert.fail("Course proficiency summary page is not displaying the deactivated question on the right card ");

    }

    public void deActivateQuestionInAssignmentReview(int dataIndex,String questionName)
    {
        AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);

        List<WebElement> questionNames=assignmentResponsesPage.questionText_assignmentReviewPage;
        boolean found=false;
        for(WebElement str:questionNames)
        {
            if(str.getText().contains("questionName")) {
                found = true;
            }
        }
        if(found == true)
            Assert.fail("Preview opened from current/assignment response page has contained deactivated question ");

    }

    public void deActivateStaticAssessment(int dataIndex)
    {
        try {
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = driver.findElements(By.xpath("//div[@id = 'question-check-box-div']//label"));
            action2.moveToElement(we2.get(0)).build().perform();// Mouse Over on Edit icon on first question
            Thread.sleep(2000);
            action2.moveToElement( driver.findElement(By.xpath("//div[@class='edit-question-content']"))).click().build().perform();
            Thread.sleep(2000);
            new Click().clickByid("questionOptions"); //click on question option
            Thread.sleep(2000);
            new Click().clickByid("questionRevisions"); // click on revisions
            Thread.sleep(2000);
            new Click().clickByid("cms-question-revision-deactivate-button");//click on the deactivate button


        } catch (InterruptedException e) {
            Assert.fail("Exception in method deActivateStaticAssessment of class StudentSubmittedQs",e);
        }
    }
    public void deActivatedStaticQuestionNumber(int dataIndex)
    {
        List<String> questionNumber=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-no']");
        boolean found=false;
        for(String str:questionNumber)
        {
            if(str.equals("4.14")) {
                found = true;
            }
        }
        if(found == false)
            Assert.fail("Course proficiency summary page is not displaying the deactivated question on the right card ");

    }
    public void deActivatedStaticAssessmentQuestionNumber(int dataIndex)
    {
        List<String> questionNumber=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-no']");
        boolean found=false;
        for(String str:questionNumber)
        {
            if(str.equals("Q1")) {
                found = true;
            }
        }
        if(found == false)
            Assert.fail("Course proficiency summary page is not displaying the deactivated question on the right card ");

    }

    public void verifyExpectedForGradableAssignment(int dataIndex)
    {
        //Expected 1
        Assignments assignments=PageFactory.initElements(driver,Assignments.class);
        assignments.getArrowDropDown().click();
        int questionCount = assignments.getQuestionCount().size();
        if(questionCount >5) {
            Assert.fail("summary dropdown is showing the deactivated question");
        }

        //Expected:3
        List<WebElement> questionLabel=assignments.questionLabel;
        boolean found=false;
        for(WebElement str:questionLabel)
        {
            if(str.getText().contains("Q6")) {
                found = true;
            }
        }
        if(found == true)
            Assert.fail("Question label is not updated post question deactivation ");

    }
    public void expectedForGradableAssignment(int dataIndex)
    {
        boolean found1=false;
        List<String> deActivatedQuestionNo=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-content']");
        for(String str1:deActivatedQuestionNo)
        {
            if(str1.contains("Multiple Choice QuestionTextIT1869")) {
                found1 = true;
            }
        }
        if(found1 == true)
            Assert.fail("Question card is displayed for deactivated question ");

        //Expected 6

        List<String> questionNumber=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-no']");
        if(questionNumber.size()>5)
            Assert.fail("Updated question has not displayed for bar and card for questions remaining post question deactivation");
        boolean found2=false;
        for(String str3:questionNumber)
        {
            if(str3.contains("Q6")) {
                found2 = true;
            }
        }
        if(found2 == true)
            Assert.fail("Updated question has not displayed for bar and card for questions remaining post question deactivation");

    }

    public void verifyMessageAfterReleaseGrade()
    {
        try {
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            DiagnosticTab diagnosticTab = PageFactory.initElements(driver, DiagnosticTab.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            ProficiencyReport proficiencyReport=PageFactory.initElements(driver, ProficiencyReport.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            Dashboard dashboard=PageFactory.initElements(driver,Dashboard.class);

            String score1=assignments.getScore().getText();
            Assert.assertEquals(score1,"Score (3/5)","Score for assignment over assignment page is included deactivated question");


             currentAssignments.getAssessmentName().click(); //click on the Assignment name
             if(!assignmentTab.questionCount.getText().trim().contains("5"))
             {
                 Assert.fail("Ddeactivated is displayed in performance summary");
             }
            new Navigator().navigateToTab("Assignments");
            String score2=assignments.getMarks().getText();
            Assert.assertEquals(score2,"Score (3/5)","Score for assignment in assignment tab at right is included deactivated question");
            Thread.sleep(2000);
            //Expected 2
            new Navigator().NavigateTo("Dashboard");
            String questionAttempt=dashboard.getQuestionAttempted().getText();
            Assert.assertEquals(questionAttempt,"5","Questions attempted count over dashboard is included deactivated question");

            //Expected 3
            dashboard.questionPerformance.click(); //click on the question performance
            expectedForGradableAssignment(94);

            new Navigator().NavigateTo("Proficiency Report");
            expectedForGradableAssignment(94);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void multipleChoiceQuestionPresent()
    {
        boolean found13=false;
        List<String> deActivatedQuestionNo13=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-content']");
        for(String str13:deActivatedQuestionNo13)
        {
            if(str13.contains("Multiple Choice QuestionTextIT1869")) {
                found13 = true;
            }
        }
        if(found13 == false)
            Assert.fail("Question card is not displayed for deactivated question ");

    }
    public void multipleChoiceQuestionPresentForNonGradable()
    {
        boolean found13=false;
        List<String> deActivatedQuestionNo13=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-content']");
        for(String str13:deActivatedQuestionNo13)
        {
            if(str13.contains("Multiple Choice QuestionTextIT18115")) {
                found13 = true;
            }
        }
        if(found13 == false)
            Assert.fail("Question card is not displayed for deactivated question ");

    }

   public void verifyNonGradableAssignment(int dataIndex)
   {
       Assignments assignments=PageFactory.initElements(driver,Assignments.class);
       Dashboard dashboard=PageFactory.initElements(driver,Dashboard.class);
       new Navigator().NavigateTo("Assignments"); //navigate to assignment
       assignments.getAssignmentName().click();//click on the assignment name

       assignments.getArrowDropDown().click();
       int questionCount = assignments.getQuestionCount().size();
       if(questionCount >5) {
           Assert.fail("summary dropdown is showing the deactivated question");
       }

       //Expected:3
       List<WebElement> questionLabel=assignments.questionLabel;
       boolean found=false;
       for(WebElement str:questionLabel)
       {
           if(str.getText().contains("Q6")) {
               found = true;
           }
       }
       if(found == true)
           Assert.fail("Question label is not updated post question deactivation ");

       //Expected 4
       driver.findElement(By.xpath("/html/body")).click();
       new Assignment().submitAssignmentAsStudent(115);//submit the Assignment

       //Expected 5
       expectedForGradableAssignment(119);

       //Expected 6

       new Navigator().NavigateTo("Dashboard");
       String questionAttempt=dashboard.getQuestionAttempted().getText();
       Assert.assertEquals(questionAttempt,"5","Questions attempted count over dashboard is included deactivated question");

       //Expected 7
       dashboard.questionPerformance.click(); //click on the question performance
       expectedForGradableAssignment(122);

       new Navigator().NavigateTo("Proficiency Report");
       expectedForGradableAssignment(123);

   }
    public  void enterGradeOnParticularQuestion(int studentIndex,int questionIndex,String sendKey)
    {
        try {
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            List<WebElement> menuitem =driver.findElements(By.xpath("//span[@class='idb-gradebook-assignment-username']"));
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(menuitem.get(studentIndex));
            Thread.sleep(2000);
            List<WebElement> enterGradeLink = driver.findElements(By.id("idb-grade-now-link"));
            Thread.sleep(2000);
            enterGradeLink.get(studentIndex).click();
            List<WebElement> gradeBox = driver.findElements(By.xpath("//input[@class='idb-grade-points']"));
            Thread.sleep(2000);
            gradeBox.get(questionIndex).clear();
            currentAssignments.clearGrade_textBox.sendKeys(sendKey);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//html/body")).click();
        } catch (InterruptedException e) {
           Assert.fail("Exception in method enterGradeOnParticularQuestion");
        }

    }

}
