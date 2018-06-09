package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.reports;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Mukesh on 11/27/14.
 */
public class NewInstructorReport extends Driver {
    @Test(priority = 1,enabled = true)
    public void newInstructorReportPage()
    {

        try {
            //Tc row no 2
            String appendChar = "aa";
            int index=2;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            System.out.println("Class code:"+classCode);
             new Navigator().navigateTo("myReports");//navigate to My report Page
            String reportPage=new TextFetch().textfetchbycssselector("div[class='center header-title']");
            System.out.println("Report Page:"+reportPage);
            Assert.assertEquals(reportPage, "MY REPORTS", "Instructor id not able to see the Report Page");
            String imagePath=driver.findElement(By.xpath("//span[@class='as-noData-icon']/img")).getAttribute("src");
            System.out.println("Default Image Icon:"+imagePath);
            if(!imagePath.contains("/webresources/images/as/robo.png"))
                Assert.fail("Default Image Icon is not visible");


        }
        catch (Exception e) {
            Assert.fail("Exception in TestCase newInstructorReport of class NewInstructorReport", e);
        }
    }
    @Test(priority = 2,enabled = true)
    public void existingInstructorReportPage()
    {

        try {
            //Tc row no 3
            String appendChar = "a";
            int index=3;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            System.out.println("Class code:"+classCode);
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Navigator().navigateTo("myReports");//navigate to My report Page
            String watchThisField=new TextFetch().textfetchbyclass("skillReportIns-heading-text");
            Assert.assertEquals(watchThisField,"Watch These Students","Watch These Students text is not visible");
            String struggling=new TextFetch().textfetchbylistcssselector("span[class='skillReportIns-right-col2 skillReportIns-watch-student-title']",0);
            System.out.println("Struggling:"+struggling);
            Assert.assertEquals(struggling,"Struggling","Struggling field is not available");
            String excelling=new TextFetch().textfetchbylistcssselector("span[class='skillReportIns-right-col2 skillReportIns-watch-student-title']",1);
            Assert.assertEquals(excelling,"Excelling","Excelling field is not available");
            String topMovers=new TextFetch().textfetchbylistcssselector("span[class='skillReportIns-right-col2 skillReportIns-watch-student-title']",2);
            Assert.assertEquals(topMovers,"Top Movers","Top Movers field is not available");

            String reviewSkill=new TextFetch().textfetchbylistclass("skillReportIns-heading-text",1);
            System.out.println("Review Skills:"+reviewSkill);
            Assert.assertEquals(reviewSkill,"Review These Skills","Review These Skills is not Available");
            String tlo=new TextFetch().textfetchbyclass("skillReportIns-col");
            if(tlo.equals("")||tlo==null)
                Assert.fail("Review these skills field is not displayed with relevant TLOs");
            String majorSkill=new TextFetch().textfetchbycssselector("span[class='skillReportIns-common-core-std-title skillReport-tlo-title']");
            Assert.assertEquals(majorSkill,"Major Skills","Major Skills is not visible in Report page");

            String minorSkill=new TextFetch().textfetchbylistclass("skillReportIns-common-core-std-title",1);
            System.out.println("Minor Sills:"+minorSkill);
            Assert.assertEquals(minorSkill,"Minor Skills","Minor Skills is not visible in report page");

            String course=new TextFetch().textfetchbycssselector("span[class='skillReport-course-title skillReportIns-common-core-std-grade skillReport-tlo-title skillReport-course-title']");
            Assert.assertEquals(course,"Course","Course Label is not visible on the page ");
            String overall=new TextFetch().textfetchbylistcssselector("span[class='skillReport-course-overall-title skillReport-overall-title skillReportIns-common-core-std-grade skillReport-tlo-title']",0);
            Assert.assertEquals(overall,"Overall","Overall label is not visible in the report page");
            String percentage=new TextFetch().textfetchbycssselector("span[class='skillReportIns-common-core-std-grade skillReportIns-redGrade']");
            if(percentage.equals("")||percentage==null)
                Assert.fail("Percentage grade is not visible is the report page");
            new Click().clickBycssselector("span[class='skillReportIns-table-sprite skillReportIns-expand-icon']");//click on the +icon(tlo)
            new Click().clickBycssselector("span[class='skillReportIns-table-sprite skillReportIns-expand-icon skillReport-tlo-icon-collapsed']");//click on the - icon(Elo)


        }
        catch (Exception e) {
            Assert.fail("Exception in TestCase newInstructorReport of class NewInstructorReport", e);
        }
    }
    @Test(priority = 3,enabled = true)
    public void twoClassWithNoAssignmentReport()
    {

        try {
            //Tc row no 5
            String appendChar = "a";
            String appendChar2 = "b";
            int index=5;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Classes().createNewClass(appendChar2, index);
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            Select select=new Select(driver.findElement(By.className("as-header-classes-selectbox")));//switch to class2
            select.selectByIndex(1);
            new Navigator().navigateTo("myReports");//navigate to My report Page
            String reportPage=new TextFetch().textfetchbycssselector("div[class='center header-title']");
            System.out.println("Report Page:"+reportPage);
            Assert.assertEquals(reportPage, "MY REPORTS", "Instructor id not able to see the Report Page");
            String imagePath=driver.findElement(By.xpath("//span[@class='as-noData-icon']/img")).getAttribute("src");
            System.out.println("Default Image Icon:"+imagePath);
            if(!imagePath.contains("/webresources/images/as/robo.png"))
                Assert.fail("Default Image Icon is not visible");

            Select selectFirst=new Select(driver.findElement(By.className("as-header-classes-selectbox")));//switch to class2
            selectFirst.selectByIndex(0);
            new Navigator().navigateTo("myReports");//navigate to My report Page
            String reportPage1=new TextFetch().textfetchbycssselector("div[class='center header-title']");
            System.out.println("Report Page:"+reportPage1);
            Assert.assertEquals(reportPage, "MY REPORTS", "Instructor id not able to see the Report Page");
            String imagePath1=driver.findElement(By.xpath("//span[@class='as-noData-icon']/img")).getAttribute("src");
            System.out.println("Default Image Icon:"+imagePath1);
            if(!imagePath1.contains("/webresources/images/as/robo.png"))
                Assert.fail("Default Image Icon is not visible");

        }
        catch (Exception e) {
            Assert.fail("Exception in TestCase twoClassWithNoAssignmentReport of class NewInstructorReport", e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void twoClassWithAssignment()
    {

        try {
            //Tc row no 6
            String appendChar = "mm";
            String appendChar2 = "nn";
            int index=6;
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
            new Navigator().navigateTo("myReports");//navigate to My report Page
            Select select1=new Select(driver.findElement(By.className("as-header-classes-selectbox")));//switch to class2
            select1.selectByIndex(1);
            new Navigator().navigateTo("myReports");//navigate to My report Page
            String reportPage1=new TextFetch().textfetchbycssselector("div[class='center header-title']");
            System.out.println("Report Page:"+reportPage1);
            Assert.assertEquals(reportPage1, "MY REPORTS", "Instructor id not able to see the Report Page");
            String imagePath1=driver.findElement(By.xpath("//span[@class='as-noData-icon']/img")).getAttribute("src");
            System.out.println("Default Image Icon:"+imagePath1);
            if(!imagePath1.contains("/webresources/images/as/robo.png"))
                Assert.fail("Default Image Icon is not visible");

            //Tc row no 7

            Select select2=new Select(driver.findElement(By.className("as-header-classes-selectbox")));//switch to class1
            select2.selectByIndex(0);
            new Navigator().navigateTo("myReports");//navigate to My report Page
            String watchThisField=new TextFetch().textfetchbyclass("skillReportIns-heading-text");
            Assert.assertEquals(watchThisField,"Watch These Students","Watch These Students text is not visible");
            String struggling=new TextFetch().textfetchbylistcssselector("span[class='skillReportIns-right-col2 skillReportIns-watch-student-title']",0);
            System.out.println("Struggling:"+struggling);
            Assert.assertEquals(struggling,"Struggling","Struggling field is not available");
            String excelling=new TextFetch().textfetchbylistcssselector("span[class='skillReportIns-right-col2 skillReportIns-watch-student-title']",1);
            Assert.assertEquals(excelling,"Excelling","Excelling field is not available");
            String topMovers=new TextFetch().textfetchbylistcssselector("span[class='skillReportIns-right-col2 skillReportIns-watch-student-title']",2);
            Assert.assertEquals(topMovers,"Top Movers","Top Movers field is not available");

            String reviewSkill=new TextFetch().textfetchbylistclass("skillReportIns-heading-text",1);
            System.out.println("Review Skills:"+reviewSkill);
            Assert.assertEquals(reviewSkill,"Review These Skills","Review These Skills is not Available");
            String tlo=new TextFetch().textfetchbyclass("skillReportIns-col");
            if(tlo.equals("")||tlo==null)
                Assert.fail("Review these skills field is not displayed with relevant TLOs");
            String majorSkill=new TextFetch().textfetchbycssselector("span[class='skillReportIns-common-core-std-title skillReport-tlo-title']");
            Assert.assertEquals(majorSkill,"Major Skills","Major Skills is not visible in Report page");

            String minorSkill=new TextFetch().textfetchbylistclass("skillReportIns-common-core-std-title",1);
            System.out.println("Minor Sills:"+minorSkill);
            Assert.assertEquals(minorSkill,"Minor Skills","Minor Skills is not visible in report page");


        }
        catch (Exception e) {
            Assert.fail("Exception in TestCase twoClassWithAssignment of class NewInstructorReport", e);
        }
    }
    @Test(priority = 5,enabled = true)
    public void reportOfGradedAssignment()
    {

        try {
            //Tc row no 8
            String appendChar = "e";
            int index=8;
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
            new Navigator().logout();//log out*

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Navigator().navigateTo("myReports");//navigate to My report Page

            if(driver.findElements(By.className("skillReportIns-students-info-wrapper")).size()!=0)
                Assert.fail("The reports for the Assignments whose grades are not released is displayed on the reports page");

            //Tc row no 9
            new Assignment().releaseGrades(index, " Release Grade");//click on  Release Grade for All
            new Navigator().navigateTo("myReports");//navigate to My report Page

            if(driver.findElements(By.className("skillReportIns-students-info-wrapper")).size()==0)
                Assert.fail("The reports for the Assignments whose grades are  released is not displayed on the reports page");
            String watchThisField=new TextFetch().textfetchbyclass("skillReportIns-heading-text");
            Assert.assertEquals(watchThisField,"Watch These Students","Watch These Students text is not visible");
            String struggling=new TextFetch().textfetchbylistcssselector("span[class='skillReportIns-right-col2 skillReportIns-watch-student-title']",0);
            System.out.println("Struggling:"+struggling);
            Assert.assertEquals(struggling,"Struggling","Struggling field is not available");
            String excelling=new TextFetch().textfetchbylistcssselector("span[class='skillReportIns-right-col2 skillReportIns-watch-student-title']",1);
            Assert.assertEquals(excelling,"Excelling","Excelling field is not available");
            String topMovers=new TextFetch().textfetchbylistcssselector("span[class='skillReportIns-right-col2 skillReportIns-watch-student-title']",2);
            Assert.assertEquals(topMovers,"Top Movers","Top Movers field is not available");

        }
        catch (Exception e) {
            Assert.fail("Exception in TestCase reportOfGradedAssignment of class NewInstructorReport", e);
        }
    }
    @Test(priority = 6,enabled = true)
    public void reportOfNonGradedAssignment()
    {

        try {
            //Tc row no 10
            String appendChar = "ab";
            int index=10;
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
            new Navigator().navigateTo("myReports");//navigate to My report Page

            if(driver.findElements(By.className("skillReportIns-students-info-wrapper")).size()==0)
                Assert.fail("The reports for the Assignments whose grades are  released is not displayed on the reports page");
            String watchThisField=new TextFetch().textfetchbyclass("skillReportIns-heading-text");
            Assert.assertEquals(watchThisField,"Watch These Students","Watch These Students text is not visible");
            String struggling=new TextFetch().textfetchbylistcssselector("span[class='skillReportIns-right-col2 skillReportIns-watch-student-title']",0);
            System.out.println("Struggling:"+struggling);
            Assert.assertEquals(struggling,"Struggling","Struggling field is not available");
            String excelling=new TextFetch().textfetchbylistcssselector("span[class='skillReportIns-right-col2 skillReportIns-watch-student-title']",1);
            Assert.assertEquals(excelling,"Excelling","Excelling field is not available");
            String topMovers=new TextFetch().textfetchbylistcssselector("span[class='skillReportIns-right-col2 skillReportIns-watch-student-title']",2);
            Assert.assertEquals(topMovers,"Top Movers","Top Movers field is not available");

        }
        catch (Exception e) {
            Assert.fail("Exception in TestCase reportOfNonGradedAssignment of class NewInstructorReport", e);
        }
    }
    @Test(priority = 7,enabled = true)
    public void skillReportForNewStudent()
    {

        try {
            //Tc row no 18
            String appendChar = "b";
            int index=18;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            System.out.println("Class code:"+classCode);
            new Navigator().logout();//logout

            new SignUp().studentDirectRegister(appendChar,classCode,index);//sign up student
            new Navigator().navigateTo("skillreport");//navigate to the skill report
            String reportPage=new TextFetch().textfetchbycssselector("div[class='center header-title']");
            System.out.println("Report Page:"+reportPage);
            Assert.assertEquals(reportPage, "SKILL REPORT", "student is not able to see the SKILL REPORT Page");
            String noData=new TextFetch().textfetchbyclass("as-noData-title");
            Assert.assertEquals(noData,"Skill Report Not Available"," \"Skill Report Not Available\" Message is not displayed");

        }
        catch (Exception e) {
            Assert.fail("Exception in TestCase skillReportForNewStudent of class NewInstructorReport", e);
        }
    }
    @Test(priority = 8,enabled = true)
    public void studentAbleToViewSkillReport()
    {

        try {
            //Tc row no 19
            String appendChar = "c"; //********************************************
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

            new Login().loginAsStudent(appendChar, index);//log in as student
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().navigateTo("skillreport");//navigate to the skill report

            if(driver.findElements(By.xpath("//span[contains(text(),'Operations & Algebraic Thinking')]//..//div[@title = '100']")).size()==0)
                Assert.fail("TLO wise proficiency report is not visible");
            if(driver.findElements(By.xpath("//h2[contains(text(),'Operations & Algebraic Thinking')]/../following-sibling::div[1]/span[text()='100%']")).size()==0)
                Assert.fail("ELos wise proficiency report is not visible");
           // Thread.sleep(5000);
            String eLosPercentage=new TextFetch().textFetchByXpath("//h2[contains(text(),'Operations & Algebraic Thinking')]/../../following-sibling::div[1]");
            Thread.sleep(9000);
            System.out.println("eLosPercentage:"+eLosPercentage);
            if(!eLosPercentage.contains("100%"))
                Assert.fail("ELO wise detailed reports with proficiency for each ELO is not displayed");


        }
        catch (Exception e) {
            Assert.fail("Exception in TestCase studentAbleToViewSkillReport of class NewInstructorReport", e);
        }
    }
    @Test(priority = 9,enabled = true)
    public void studentNotAbleToViewOtherStudentSkillReport()
    {

        try {
            //Tc row no 20
            String appendChar = "v";
            String appendChar2 = "m";
            int index=20;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar2, classCode, index);//create student2
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as student1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().navigateTo("skillreport");//navigate to the skill report

            if(driver.findElements(By.xpath("//span[contains(text(),'Operations & Algebraic Thinking')]//..//div[@title = '100']")).size()==0)
                Assert.fail("TLO wise proficiency report is not visible");
            if(driver.findElements(By.xpath("//h2[contains(text(),'Operations & Algebraic Thinking')]/../following-sibling::div[1]/span[text()='100%']")).size()==0)
                Assert.fail("ELos wise proficiency report is not visible");
            // Thread.sleep(5000);
            String eLosPercentage=new TextFetch().textFetchByXpath("//h2[contains(text(),'Operations & Algebraic Thinking')]/../../following-sibling::div[1]");
            Thread.sleep(9000);
            System.out.println("eLosPercentage:"+eLosPercentage);
            if(!eLosPercentage.contains("100%"))
                Assert.fail("ELO wise detailed reports with proficiency for each ELO is not displayed");
            new Navigator().logout();//log out

            //Tc row no 21
            new Login().loginAsStudent(appendChar2, index);//log in as student2
            new Navigator().navigateTo("skillreport");//navigate to the skill report
            String noData=new TextFetch().textfetchbyclass("as-noData-title");
            Assert.assertEquals(noData,"Skill Report Not Available"," \"Skill Report Not Available\" Message is not displayed");

        }
        catch (Exception e) {
            Assert.fail("Exception in TestCase studentNotAbleToViewOtherStudentSkillReport of class NewInstructorReport", e);
        }
    }

    @Test(priority = 10,enabled = true)
    public void studentSkillReportOfGradedAssignment()
    {

        try {
            //Tc row no 22 & 23
            String appendChar = "jk";
            int index=22;
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

            new Login().loginAsStudent(appendChar, index);//log in as student1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().navigateTo("skillreport");//navigate to the skill report
            String noData=new TextFetch().textfetchbyclass("as-noData-title");
            Assert.assertEquals(noData,"Skill Report Not Available"," \"Skill Report Not Available\" Message is not displayed");
            new Navigator().logout();//log out

            //Tc row no 24
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().releaseGrades(index, " Release Grade");//click on  Release Grade for All
            new Navigator().logout();


            new Login().loginAsStudent(appendChar, index);//log in as student1
            new Navigator().navigateTo("skillreport");//navigate to the skill report
            if(driver.findElements(By.xpath("//span[contains(text(),'Operations & Algebraic Thinking')]//..//div[@title = '100']")).size()==0)
                Assert.fail("TLO wise proficiency report is not visible");
            if(driver.findElements(By.xpath("//h2[contains(text(),'Operations & Algebraic Thinking')]/../following-sibling::div[1]/span[text()='100%']")).size()==0)
                Assert.fail("ELos wise proficiency report is not visible");
            // Thread.sleep(5000);
            String eLosPercentage=new TextFetch().textFetchByXpath("//h2[contains(text(),'Operations & Algebraic Thinking')]/../../following-sibling::div[1]");
            Thread.sleep(9000);
            System.out.println("eLosPercentage:"+eLosPercentage);
            if(!eLosPercentage.contains("100%"))
                Assert.fail("ELO wise detailed reports with proficiency for each ELO is not displayed");
        }
        catch (Exception e) {
            Assert.fail("Exception in TestCase studentSkillReportOfGradedAssignment of class NewInstructorReport", e);
        }
    }
}
