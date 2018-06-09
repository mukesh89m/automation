package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.R7;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.ExplicitWait;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by root on 10/9/14.
 */
public class QuestionTypes extends Driver {

    @Test(priority = 1)
    public void supportVariousQuestionNonGradableAssignment() {
        try {
            String appendChar = "a";
            new SignUp().teacher(appendChar, 0);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 0);//log in as teacher
            new School().createWithOnlyName(appendChar, 0);//create class
            String classCode = new Classes().createClass(appendChar, 42, "Grade 1", "Other Subjects", "Art");
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 42);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new Assignment().create(1, "all");//create an Assignment

            new Assignment().assignToStudent(1, appendChar);//assign to student1;
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, 1);//log in as student 1
            new Assignment().submitAssignment(1);//submit the assignment
            String percentage = new TextFetch().textfetchbyclass("highcharts-container");
            System.out.println("percentage: "+percentage);
            if(!percentage.contains("%")){
                Assert.fail("In performance summary the percentage is not displayed.");
            }
            new Navigator().navigateTo("assignment");
            new Assignment().openAssignment(1);
            String percentage1 = new TextFetch().textfetchbyclass("highcharts-container");
            System.out.println("percentage1: "+percentage1);
            if(!percentage1.contains("%")){
                Assert.fail("In performance summary the percentage is not displayed if we open assignment.");
            }


        } catch (Exception e) {
            Assert.fail("Exception in testcase supportVariousQuestionNonGradableAssignment in class QuestionTypes", e);

        }
    }

    @Test(priority = 2)
    public void supportVariousQuestionGradableAssignment() {
        try {
            String appendChar = "c";
            new SignUp().teacher(appendChar, 0);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 2);//log in as teacher
            new School().createWithOnlyName(appendChar, 0);//create class
            String classCode = new Classes().createClass(appendChar, 0, "Grade 1", "Other Subjects", "Art");
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 0);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 2);//log in as teacher
            new Assignment().create(2, "all");//create an Assignment

            new Assignment().assignToStudent(2, appendChar);//assign to student1;
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, 2);//log in as student 1
            new Assignment().submitAssignment(2);//submit the assignment
            new Navigator().navigateTo("skillreport");
            Assert.assertEquals("Skill Report Not Available", new TextFetch().textfetchbyclass("as-noData-title"));
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 0);//log in as teacher
            Assert.assertEquals("Performance Summary Not Available", new TextFetch().textfetchbyclass("as-noData-title"));
            new Navigator().navigateTo("assignment");
            Assert.assertEquals("Grading in Progress", new Assignment().assignmentStatus(2).trim());
            new Assignment().releaseGrades(2, " Release Grade for All");
            Assert.assertEquals("Graded", new Assignment().assignmentStatus(2).trim());

            new Navigator().navigateTo("dashboard");
            String graph = new TextFetch().textfetchbyid("mobile-dashboard-overall-performance-chart");
            System.out.println("Graph:"+graph);
            if(!graph.contains("Correct"))
                Assert.fail("Graph does not have the percentage for Correct");
            if(!graph.contains("Incorrect"))
                Assert.fail("Graph does not have the percentage for Incorrect.");
            if(!graph.contains("Skipped"))
                Assert.fail("Graph does not have the percentage for Skipped.");

            new Navigator().navigateTo("myReports");
            Assert.assertEquals("0", new TextFetch().textfetchbyid("SUserIds-count"));
            Assert.assertEquals("0", new TextFetch().textfetchbyid("EUserIds-count"));
            Assert.assertEquals("1", new TextFetch().textfetchbyid("TUserIds-count"));

        }
        catch (Exception e) {
            Assert.fail("Exception in testcase supportVariousQuestionGradableAssignment in class QuestionTypes", e);

        }
    }

    @Test(priority = 3)
    public void supportVariousQuestionNonGradableExistingAssignment() {
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(3));
            String appendChar = "z";
            new SignUp().teacher(appendChar, 0);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 3);//log in as teacher
            new School().createWithOnlyName(appendChar, 0);//create class
            String classCode = new Classes().createClass(appendChar, 0, "Grade 1", "Other Subjects", "Art");
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 0);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 3);//log in as teacher
            new Assignment().create(3, "all");//create an Assignment
            new Assignment().assignToStudent(3,appendChar);
            new Assignment().useExistingAssignment(3,appendChar);
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, 3);//log in as student 1
            new Assignment().submitAssignment(3);//submit the assignment
            String percentage = new TextFetch().textfetchbyclass("highcharts-container");
            System.out.println("percentage: "+percentage);
            if(!percentage.contains("%")){
                Assert.fail("In performance summary the percentage is not displayed.");
            }
            new Navigator().navigateTo("assignment");

            int index = 1;
            new ExplicitWait().explicitWaitByCssSelector("div.as-label.ellipsis",10);
            List<WebElement> allAssignment = driver.findElements(By.cssSelector("div.as-label.ellipsis"));
            for(WebElement assignment:allAssignment)
            {
                if (assignment.getText().substring(6).trim().equals(assessmentname)) {
                    break;
                } else
                    index++;
            }
            new Click().clickbylistcssselector("div.as-label.ellipsis", index);//click on Assignment

           // new Assignment().openAssignment(3);
            String percentage1 = new TextFetch().textfetchbyclass("highcharts-container");
            System.out.println("percentage1: "+percentage1);
            if(!percentage1.contains("%")){
                Assert.fail("In performance summary the percentage is not displayed.");
            }            new Navigator().navigateTo("skillreport");
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 3);//log in as teacher



            new Navigator().navigateTo("myReports");
            Assert.assertEquals("0", new TextFetch().textfetchbyid("SUserIds-count"));
            Assert.assertEquals("0", new TextFetch().textfetchbyid("EUserIds-count"));
            Assert.assertEquals("1", new TextFetch().textfetchbyid("TUserIds-count"));

        } catch (Exception e) {
            Assert.fail("Exception in testcase supportVariousQuestionNonGradableExistingAssignment in class QuestionTypes", e);
        }
    }

    @Test(priority = 4)
    public void supportVariousQuestionGradableExistingAssignment() {
        try {
            String appendChar = "a";
            new SignUp().teacher(appendChar, 0);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 4);//log in as teacher
            new School().createWithOnlyName(appendChar, 0);//create class
            String classCode = new Classes().createClass(appendChar, 0, "Grade 1", "Other Subjects", "Art");
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 0);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 4);//log in as teacher
            new Assignment().create(4, "all");//create an Assignment
            new Assignment().assignToStudent(4,appendChar);
            new Assignment().useExistingAssignment(4, appendChar);
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, 4);//log in as student 1
            new Assignment().submitAssignment(4);//submit the assignment
            new Navigator().navigateTo("skillreport");
            Assert.assertEquals("Skill Report Not Available", new TextFetch().textfetchbyclass("as-noData-title"));
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 0);//log in as teacher
            Assert.assertEquals("Performance Summary Not Available", new TextFetch().textfetchbyclass("as-noData-title"));
            new Navigator().navigateTo("assignment");
            Assert.assertEquals("Grading in Progress", new Assignment().assignmentStatus(4).trim());
            new Assignment().releaseGrades(4, " Release Grade for All");
            Assert.assertEquals("Graded", driver.findElement(By.xpath("//div[@class='as-status-label']/span")).getText());

            new Navigator().navigateTo("myReports");
            Assert.assertEquals("0", new TextFetch().textfetchbyid("SUserIds-count"));
            Assert.assertEquals("0", new TextFetch().textfetchbyid("EUserIds-count"));
            Assert.assertEquals("1", new TextFetch().textfetchbyid("TUserIds-count"));

            new Navigator().navigateTo("dashboard");
            Assert.assertNotNull(driver.findElement(By.id("mobile-dashboard-recently-graded-assignment-chart")));

        } catch (Exception e) {
            Assert.fail("Exception in testcase supportVariousQuestionGradableExistingAssignment in class QuestionTypes", e);
        }
    }
}
