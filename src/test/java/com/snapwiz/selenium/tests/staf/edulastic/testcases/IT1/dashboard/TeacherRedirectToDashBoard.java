package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.dashboard;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assessments;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


/**
 * Created by Mukesh on 11/26/14.
 */
public class TeacherRedirectToDashBoard extends Driver {
    @Test(priority = 1,enabled = true)
    public void teacherRedirectToDashBoard()
    {
        try {
             //Tc row no 2
            String appendChar = "b";
            int index=2;
            new SignUp().teacher(appendChar, index); //signup as a teacher
            new Login().loginAsInstructor(appendChar, index); //login as instructor
            new School().createWithOnlyName(appendChar, index); //create school
            new Classes().createClass(appendChar,index);//create class
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            String introductionMsg=new TextFetch().textFetchByXpath("(//div[@class='slideImgSection']/h2)[1]");
            String actualIntroductionMsg="Make homework an essential part of your formative assessment process!";
            if(!introductionMsg.equals(actualIntroductionMsg))
                Assert.fail("Introduction to edulastic's assessment message is not displayed");
            String takeTour=new TextFetch().textFetchByXpath("//span[@class='takeTourButton']/a");
            Assert.assertEquals(takeTour,"Take Tour","Take Tour field is not available");
            String createAssessment=new TextFetch().textFetchByXpath("(//span[@class='createAssessment']/a)[1]");
            Assert.assertEquals(createAssessment,"Create your first Assessment","Create your first Assessment field is not available");
            new Click().clickByclassname("nav-menu-toggle");//click on the nav toggle
            Thread.sleep(2000);
            new Click().clickByclassname("nav-menu-toggle");//click on the nav toggle
            new Navigator().logout();//logout

            //Tc row no 3
                new Login().loginAsInstructor(appendChar, index); //login as instructor
                new Click().clickByXpath("//span[@class='takeTourButton']/a");//click on take a tour

                new Click().clickByclassname("jssora05r");//click on the right Arrow
                String createAssessment1 = new TextFetch().textFetchByXpath("(//span[@class='createAssessment']/a)[1]");
                Assert.assertEquals(createAssessment1, "Create your first Assessment", "Create your first Assessment field is not available");
                Thread.sleep(2000);

                new Click().clickByclassname("jssora05r");//click on the right Arrow
                System.out.println("pass");
                String assignTrackGrade=new TextFetch().textFetchByXpath("(//div[@class='slideImgSection']/h2)[3]");
                Assert.assertEquals(assignTrackGrade,"Assign, track, & grade effortlessly","Assign, track, & grade effortlessly is not visible");
                String createAssessment2 = new TextFetch().textFetchByXpath("(//span[@class='createAssessment']/a)[3]");
                Assert.assertEquals(createAssessment2, "Create your first Assessment", "Create your first Assessment2 field is not available");
                Thread.sleep(2000);

                new Click().clickByclassname("jssora05r");//click on the right Arrow
                String proficiency=new TextFetch().textFetchByXpath("(//div[@class='slideImgSection']/h2)[4]");
                Assert.assertEquals(proficiency,"Get real-time insights into student proficiency.","Get real-time insights into student proficiency. is not visible");
                String createAssessment3 = new TextFetch().textFetchByXpath("(//span[@class='createAssessment']/a)[4]");
                Assert.assertEquals(createAssessment3, "Create your first Assessment", "Create your first Assessment3 field is not available");


        } catch (Exception e) {
            Assert.fail("Exception in test case teacherRedirectToDashBoard of class TeacherRedirectToDashBoard ",e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void clickOnTheCreateAssignment()
    {

        try {
            //Tc row no 9
            String appendChar = "a";
            int index=9;
            new SignUp().teacher(appendChar, index); //signup as a teacher
            new Login().loginAsInstructor(appendChar, index); //login as instructor
            new School().createWithOnlyName(appendChar, index); //create school
            new Classes().createClass(appendChar,index);//create class
            new Click().clickByXpath("(//span[@class='createAssessment']/a)[1]");//click on the create your first assignment

            String useExistingAssignment=new TextFetch().textfetchbyclass("as-assignment-flow-link-title");
            if(!useExistingAssignment.equals("Use Existing Assessment"))
                Assert.fail("Use Existing Assessment is not visible");
            String createNewAssignment=new TextFetch().textfetchbylistclass("as-assignment-flow-link-title",1);
            if(!createNewAssignment.equals("Create New Assessment"))
                Assert.fail("Create new Assessment is not visible in the assignment page");
            String backButton=new TextFetch().textfetchbyid("cancel-assignment");
            Assert.assertEquals(backButton,"Back","Back button is not visible");


        } catch (Exception e) {
            Assert.fail("Exception in test case clickOnTheCreateAssignment of class TeacherRedirectToDashBoard ",e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void dashboardSummaryTitlesUpdate()
    {

        try {
            //Tc row no 10
            String appendChar = "a";
            int index=12;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().releaseGrades(index, " Release Grade");//click on  Release Grade for All

             new Navigator().navigateTo("dashboard");//Navigate to dashBoard
             new Click().clickByid("performance-chart-label-id");//click on the performance chart
             new Navigator().navigateTo("dashboard");//Navigate to dashBoard
             new Click().clickByclassname("highcharts-container");//click on the bar chart
             new Navigator().navigateTo("dashboard");//Navigate to dashBoard
             new Click().clickByclassname("btn-transition");//click on the see all

        } catch (Exception e) {
            Assert.fail("Exception in test case dashboardSummaryTitlesUpdate of class TeacherRedirectToDashBoard ",e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void clickOnPieAndBarChart()
    {
        try {
             //Tc row no 12
            String appendChar = "ac";
            int index=13;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().releaseGrades(index, " Release Grade");//click on  Release Grade for All
            new Navigator().logout();//logout

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            new Click().clickByid("performance-chart-label-id");//click on the performance (pie)chart
            String reportPage=new TextFetch().textfetchbycssselector("div[class='center header-title']");
            Thread.sleep(2000);
            Assert.assertEquals(reportPage,"MY REPORTS","Instructor is not able to navigate to report page");
            String skillReport=new TextFetch().textfetchbyclass("skillReportIns-content");
            if(skillReport.equals("")||skillReport==null)
                Assert.fail("Relevant content is not displayed");

            //Tc row no 13
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            driver.navigate().refresh();
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("mobile-dashboard-recently-graded-assignment-chart")));
            new Click().clickByid("mobile-dashboard-recently-graded-assignment-chart");//click on the bar chart
            //new Click().clickByXpath("//img[@src='/webresources/images/as/recently-graded-no-data-chart.png']");//click on the bar chart

            String assignmentHomeTitle=new TextFetch().textfetchbycssselector("div[class='center header-title']");
            Assert.assertEquals("ASSIGNMENTS",assignmentHomeTitle,"Instructor is not able to navigate to the Assignment page");
            String newAssignmentTab=new TextFetch().textfetchbyclass("instructor-assignment-new-txt");
            Assert.assertEquals("New Assignment",newAssignmentTab,"New assignment tab is not present");
            if(driver.findElements(By.className("student-score-range-assignment-graph")).size()==0)
                Assert.fail("Report is not visible after clicking on bar chart");
             new Click().clickBycssselector("div[class='as-label ellipsis']");//click on the assignment Name

            //Tc row no 14
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            new Click().clickByclassname("btn-transition");//click on the see all button
            String assignmentHomeTitle1=new TextFetch().textfetchbycssselector("div[class='center header-title']");
            Assert.assertEquals("ASSIGNMENTS",assignmentHomeTitle1,"Instructor is not able to navigate to the Assignment page");
            String newAssignmentTab1=new TextFetch().textfetchbyclass("instructor-assignment-new-txt");
            Assert.assertEquals("New Assignment",newAssignmentTab1,"New assignment tab is not present");
            if(driver.findElements(By.className("student-score-range-assignment-graph")).size()==0)
                Assert.fail("Report is not visible after clicking on bar chart");

            //Tc row no 15
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
             new Click().clickBycssselector("div[class='as-label ellipsis']");//click on the assignment Name
             String assignmentNamePage=new TextFetch().textfetchbycssselector("div[class='lsm-assignment-header-title ellipsis']");
             Assert.assertEquals(assignmentNamePage,"IT1_assignmentFlow_Assessment_13","Instructor is not redirect to the Assignment Name Page after clicking on the Assignment Name");
             String questionName=new TextFetch().textfetchbyclass("lsm-createAssignment-Question");
             if(questionName.equals("")||questionName==null)
                 Assert.fail("Question is not displayed");
            List<WebElement> noOfQuestion=driver.findElements(By.className("lsm-createAssignment-Question"));
            int questionCount=noOfQuestion.size();
            System.out.println("noOfQuestion::"+questionCount);
            if(!(questionCount>=2))
                Assert.fail("Question count is not displayed according to the teacher's create question");


        } catch (Exception e) {
            Assert.fail("Exception in test case clickOnPieAndBarChart of class TeacherRedirectToDashBoard ",e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void switchBetweenClass()
    {

        try {
            //Tc row no 16
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            String appendChar = "a";
            String appendChar2 = "b";
            int index=16;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Classes().createNewClass(appendChar2,index);
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar2, classCode, index);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            Select select=new Select(driver.findElement(By.className("as-header-classes-selectbox")));//switch to class1
            select.selectByIndex(0);
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar2, index);//log in as student 1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().releaseGrades(index, " Release Grade");//click on  Release Grade for All
            new Navigator().logout();//logout

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            Select select1=new Select(driver.findElement(By.className("as-header-classes-selectbox")));//switch to class2
            select1.selectByIndex(1);
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            String defaultDashboardMessage=new TextFetch().textFetchByXpath("//div[@class='slideImgSection']/h2");
            String actual="Make homework an essential part of your formative assessment process!";
            Assert.assertEquals(defaultDashboardMessage,actual,"Dashboard is not changing according to the class");

            Select select2=new Select(driver.findElement(By.className("as-header-classes-selectbox")));//switch to class1
            select2.selectByIndex(0);
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            new Click().clickByid("performance-chart-label-id");//click on the performance chart
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            new Click().clickByclassname("highcharts-container");//click on the bar chart
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            new Click().clickByclassname("btn-transition");//click on the see all

            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            new Click().clickBycssselector("div[class='as-label ellipsis']");//click on the assignment Name
            new Click().clickByclassname("assessments-back-link");//click on the back button
            new Navigator().logout();//logout

            //Tc row no 17

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            new Navigator().navigateTo("manageclass");//navigate to manage class
            new Click().clickByListClassName("as-manage-class-visit",0); //switch to the class 1
            new Click().clickByid("performance-chart-label-id");//click on the performance chart
            new Navigator().navigateTo("assignment");
            driver.navigate().refresh();
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("rect[fill='#22A7F0']")));
            new Click().clickBycssselector("rect[fill='#22A7F0']");//click on the bar chart
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            String assignmentName=new TextFetch().textfetchbycssselector("div[class='as-label ellipsis']");
            System.out.println("Assignment name:"+assignmentName);
            if(!assignmentName.contains("IT1_assignmentFlow_Assessment_16"))
                Assert.fail("Assignment name is not displayed correctly");

            new Navigator().navigateTo("manageclass");//navigate to manage class
            new Click().clickByListClassName("as-manage-class-visit",1); //switch to the class 1
            String defaultDashboardMessage1=new TextFetch().textFetchByXpath("//div[@class='slideImgSection']/h2");
            String actual1="Make homework an essential part of your formative assessment process!";
            Assert.assertEquals(defaultDashboardMessage1,actual1,"Dashboard is not changing according to the class");


        } catch (Exception e) {
            Assert.fail("Exception in test case switchBetweenClass of class TeacherRedirectToDashBoard ",e);
        }
    }
    @Test(priority = 6,enabled = true)
    public void studentSignUp()
    {

        try {
            //Tc row no 18
            String appendChar = "aa";
            int index=18;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().navigateTo("assignment");
            String summaryNotAvailable=new TextFetch().textfetchbyclass("as-noData-title");
            Assert.assertEquals(summaryNotAvailable,"Performance Summary Not Available","Performance Summary message is not Available");


        } catch (Exception e) {
            Assert.fail("Exception in test case studentSignUp of class TeacherRedirectToDashBoard ",e);
        }
    }
    @Test(priority = 7,enabled = true)
    public void studentDashBoardAfterTeacherAssignTheAssignment()
    {

        try {
            //Tc row no 19
            String appendChar = "aa";
            int index=19;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            String assignmentName=new TextFetch().textfetchbycssselector("div[class='as-label ellipsis']");
            System.out.println("Assignment name:"+assignmentName);
            if(!assignmentName.contains("IT1_assignmentFlow_Assessment_19"))
                Assert.fail("Assignment name is not displayed correctly");
            //TC row no 20
            new Click().clickBycssselector("div[class='as-label ellipsis']");//click on the assignment Name
            new Click().clickByclassname("true-false-student-answer-select");//click on answer

        } catch (Exception e) {
            Assert.fail("Exception in test case studentDashBoardAfterTeacherAssignTheAssignment of class TeacherRedirectToDashBoard ",e);
        }
    }
    @Test(priority = 8,enabled = true)
    public void existingStudentDashBoardTilesUpdate()
    {

        try {
            //Tc row no 21
            String appendChar = "zz1";
            int index=21;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().releaseGrades(index, " Release Grade");//click on  Release Grade for All
            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            String questionCount=new TextFetch().textfetchbyclass("chart-label-title");
            System.out.println("Question Count:"+questionCount);
            Assert.assertEquals(questionCount,"2","Assignment Summary with question count is not Displayed");

            //TC row no 22
            String recentlyGradedAssignment=new TextFetch().textfetchbylistclass("ta-centre",1);
            Assert.assertEquals(recentlyGradedAssignment,"Recently Graded Assignments","Recently Graded Assessments is not displayed");

            //Tc row no 23

            new Click().clickByclassname("btn-transition");//click on the see all
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard

            String assignmentName=new TextFetch().textfetchbycssselector("div[class='as-label ellipsis']");
            System.out.println("Assignment name:"+assignmentName);
            if(!assignmentName.contains("IT1_assignmentFlow_Assessment_21"))
                Assert.fail("Assignment name is not displayed correctly");

            //Tc row no 24
            new Click().clickByid("performance-chart-label-id");//click on the  pie chart
            String skillReport=new TextFetch().textfetchbycssselector("div[class='center header-title']");
            Assert.assertEquals(skillReport,"SKILL REPORT","Instructor is not able to navigate to the Skill Report Page");
            String proficiencyLabel=new TextFetch().textfetchbyclass("lsm-skillReport-title");
            Assert.assertEquals(proficiencyLabel,"Proficiency : Common Core Standards: Grade 1","Proficiency Common Core Standards Grade label is Not Displayed");
            if(driver.findElements(By.className("skillreport-details-sections")).size()==0)
                Assert.fail("Proficiency Report is not generated");

            //Tc row no 25
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            new Click().clickByXpath("(//div[@class='highcharts-container'])[2]");//click on the bar chart
            String assignmentHomeTitle=new TextFetch().textfetchbycssselector("div[class='center header-title']");
            Assert.assertEquals("ASSIGNMENTS",assignmentHomeTitle,"Instructor is not able to navigate to the Assignment page");

            new Navigator().logout();//logout

            //Tc row no 26
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            new Click().clickByclassname("btn-transition");//click on the see all button
            String assignmentHomeTitle1=new TextFetch().textfetchbycssselector("div[class='center header-title']");
            Assert.assertEquals("ASSIGNMENTS",assignmentHomeTitle1,"Instructor is not able to navigate to the Assignment page");
            if(driver.findElements(By.className("student-score-range-assignment-graph")).size()==0)
                Assert.fail("Report is not visible after clicking on bar chart");

        } catch (Exception e) {
            Assert.fail("Exception in test case existingStudentDashBoardTilesUpdate of class TeacherRedirectToDashBoard ",e);
        }
    }
    @Test(priority = 9,enabled = true)
    public void assignmentProficiencyReport()
    {

        try {
            //Tc row no 27
            String appendChar = "kk";
            int index=27;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            new Click().clickBycssselector("div[class='as-label ellipsis']");//click on the assignment Name
            String performanceReportLabel=new TextFetch().textFetchByXpath("//h3[contains(text(),'Performance by Questions')]");
            System.out.println("performanceReportLabel:"+performanceReportLabel);
            if(!performanceReportLabel.contains("Performance by Questions"))
                Assert.fail("Performance by Questions");
             new Click().clickBycssselector("rect[width='40']");//click on the bar chart

        } catch (Exception e) {
            Assert.fail("Exception in test case existingStudentDashBoardTilesUpdate of class TeacherRedirectToDashBoard ",e);
        }
    }
    @Test(priority = 10,enabled = true)
    public void autoGradedAssignmentReport()
    {

        try {
            //Tc row no 11
            String appendChar = "c";
            int index=11;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            new Click().clickByid("performance-chart-label-id");//click on the performance chart
            new Navigator().navigateTo("assignment");//Navigate to dashBoard
            driver.navigate().refresh();
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            Thread.sleep(2000);
            new Click().clickByXpath("(//div[@class='highcharts-container'])[2]");//click on the bar chart
            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            new Click().clickByclassname("btn-transition");//click on the see all

            new Navigator().navigateTo("dashboard");//Navigate to dashBoard
            new Click().clickBycssselector("div[class='as-label ellipsis']");//click on the assignment Name


        } catch (Exception e) {
            Assert.fail("Exception in test case autoGradedAssignmentReport of class TeacherRedirectToDashBoard ",e);
        }
    }
}
