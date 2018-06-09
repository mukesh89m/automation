package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.unAssignAssignment;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Dharaneeesha on 3/2/16.
 */
public class unAssignAssignment extends Driver {

    @Test
    public void unAssignAssignment() {
        try {
            ReportUtil.log("Unassigning the assignment", "This Checks the unassigned assignment is reverted in all the modules in both Student & instructor side", "info");
            WebDriver driver=Driver.getWebDriver();
            int dataIndex = 2;
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", ""+dataIndex);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            String appendChar = StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA);
            System.setProperty("UCHAR", appendChar);
            System.out.println("appendChar : " + appendChar);

            /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
            //Create Assignment
            new Assignment().create(dataIndex);
            for (int i = 0; i < 4; i++)
                new Assignment().addQuestions(dataIndex, "truefalse", "");

            for (int i = 5; i <= 6; i++)
                new Assignment().addQuestions(dataIndex, "multiplechoice", "");

            for (int i = 7; i <= 9; i++)
                new Assignment().addQuestions(dataIndex, "multipleselection", "");

            for (int i = 10; i < 12; i++)
                new Assignment().addQuestions(dataIndex, "essay", "");
            /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

            //Create 3 Students & an instructor in a Class Section
            ReportUtil.log("Create Question", "Created 12 questions of different type", "Pass");

            new LoginUsingLTI().ltiLogin(dataIndex+"_1");//login as student1
            ReportUtil.log("Student1 Login", "Student1 logged in successfully", "Pass");

            new LoginUsingLTI().ltiLogin(dataIndex+"_2");//login as student2
            ReportUtil.log("Student2 Login", "Student2 logged in successfully", "Pass");

            new LoginUsingLTI().ltiLogin(dataIndex+"_3");//login as student3
            ReportUtil.log("Student3 Login", "Student3 logged in successfully", "Pass");

            //Login as an instructor & assign an assignment to Class Section on Policy 1
            new LoginUsingLTI().ltiLogin(""+dataIndex);//login as instructor
            ReportUtil.log("Instructor Login", "Instructor logged in successfully", "Pass");

            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "", "" + dataIndex);//till save policy
            ReportUtil.log("Navigated to policy", "Created policy with release option 1", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(dataIndex);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date", "Pass");
            /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

            //Let Student1 be completed status
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");//login as student1
            new Assignment().submitAssignmentAsStudent(dataIndex); //submit assignment
            ReportUtil.log("Student1 Login", "Student1 submitted the assignment", "Pass");
            /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

            //Let Student 2 be in progress state
            new LoginUsingLTI().ltiLogin(dataIndex+"_2");//login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            for (int i = 0; i <= 2; i++) {
                new AttemptQuestion().trueFalse(false, "correct", 2);
                Thread.sleep(1000);
                new Assignment().nextButtonInQuestionClick();
                Thread.sleep(1000);
            }
            /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

            //Let instructor unassigns the assignment
            new LoginUsingLTI().ltiLogin(""+dataIndex);//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            new Assignment().deleteAssignment();
            /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
            new RunScheduledJobs().runScheduledJob("ClassSectionAssignmentPercentileJob");
            driver=new ReInitDriver().startDriver("firefox");
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            lessonPage = PageFactory.initElements(driver, LessonPage.class);
            courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            //Instructor Validations
            new LoginUsingLTI().ltiLogin(""+dataIndex);//login as instructor
            new Navigator().NavigateTo("Dashboard");
            //1.'Average Performance' tile should be reverted//highcharts-title
            if(driver.findElements(By.className("highcharts-title")).size()!=0)
                CustomAssert.fail("Average Performance Tile","'Average Performance' tile is not reverted");

            //2.'Recently Graded' tile should be reverted
            if(driver.findElement(By.className("grade-book")).getText().contains("Recently Graded"))
                CustomAssert.fail("'Recently Graded' tile","'Recently Graded' tile is not reverted");

            //3. Assignment entry should be reverted in 'Recent Activities' Tile
            String courseDetailsContainer = dashboard.courseDetailsContainer.getText();
            if(courseDetailsContainer.contains(assessmentName)){
                CustomAssert.fail("'Recent Activities' Tile","Assignment entry is not reverted in 'Recent Activities' Tile");
            }

            //4. Assignment entry should be reverted in 'Classs Activity' Tile
            boolean isAssignmentPresent = true;
            try{
                dashboard.classActivityContentStream.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("'Class Activity' Tile","Assignment entry is not reverted in 'Classs Activity' Tile");
            }



            //Current Assignments
            //Un Assigned assignment should be reverted
            new Navigator().NavigateTo("Current Assignments");
            isAssignmentPresent = true;
            try{
                currentAssignments.assignment.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in Current Assignment Page","Un Assigned assignment is not reverted in Current Assignment Page");
            }


            //Gradebook
            //Unassigned assignment entry should be reverted back
            new Navigator().NavigateTo("Gradebook");
            isAssignmentPresent = true;
            try{
                driver.findElement(By.cssSelector("span[title = '"+assessmentName+"']")).click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in Gradebook Page","Unassigned assignment entry is  not reverted back in 'Gradebook' Page");
            }

            //Course Stream
            //Unassigned assignment entry should be reverted back
            new Navigator().NavigateTo("Course Stream");
            isAssignmentPresent = true;
            try{
                courseStreamPage.assessmentName.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in Course Stream Page","Unassigned assignment is not reverted back in 'Course Stream' Page");
            }


            //Student 1 Side DashBoard
            //1.'Overall Score' tile should be reverted
            new LoginUsingLTI().ltiLogin(dataIndex + "_1");//login as student1
            new Navigator().NavigateTo("Dashboard");
            if(driver.findElement(By.className("grade-book")).getText().contains("Overall Score"))
                CustomAssert.fail("Overall Score Tile","'Overall Score' tile is not reverted in dashboard");

            //2.'Recently Graded' tile should be reverted
            if(driver.findElement(By.className("grade-book")).getText().contains("Recently Graded"))
                CustomAssert.fail("'Recently Graded' tile ","'Recently Graded' tile is not reverted in dashboard");

            //3. Assignment entry should be reverted in Recent Activities Tile
            courseDetailsContainer = dashboard.courseDetailsContainer.getText();
            if(courseDetailsContainer.contains(assessmentName)){
                CustomAssert.fail("'Recent Activities' Tile","Assignment entry is not reverted in 'Recent Activities' Tile in dashboard");
            }

            //4. Assignment entry should be reverted in Course Stream Tile
            isAssignmentPresent = true;
            try{
                dashboard.courseStreamContentStream.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("'Course Stream' tile ","Assignment entry is not reverted in 'Course Stream' Tile in dashboard");
            }

            //e-textbook
            //1. Assignment entry should be reverted in 'Assignments' in e-textbook Right Tab
            new Navigator().NavigateTo("eTextbook");
            new TOCShow().tocHide();
            isAssignmentPresent = true;
            try{
                lessonPage.assignmentTab.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in Assignments Tab","Assignment entry is not reverted in 'Assignments' in e-textbook Right Tab");
            }

            //2. Assignment entry should be reverted in TOC
            isAssignmentPresent = true;
            try{
                lessonPage.TOCassignmentName.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in TOC Page","Un Assigned assignment is not reverted in Current Assignment Page");
            }

            //Assignments
            //Un Assigned assignment should be reverted
            new Navigator().NavigateTo("Assignments");
            isAssignmentPresent = true;
            try{
                currentAssignments.assignment.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in Current Assignment Page","Un Assigned assignment is reverted in Assignment Page");
            }


            //Course Stream
            //Unassigned assignment entry should be reverted back
            new Navigator().NavigateTo("Course Stream");
            isAssignmentPresent = true;
            try{
                courseStreamPage.assessmentName.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in Course Stream Page","Unassigned assignment is not reverted back in 'Course Stream' Page");
            }

            //My Activity
            //Assignment entry should be reverted
            new Navigator().NavigateTo("My Activity");
            isAssignmentPresent = true;
            try{
                driver.findElement(By.partialLinkText(assessmentName)).click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in My Activity Page","Unassigned assignment is not reverted back in 'My Activity' Page");
            }



            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..

            //Student 2 Side DashBoard
            new LoginUsingLTI().ltiLogin(dataIndex + "_2");//login as student2
            isAssignmentPresent = true;
            try{
                driver.findElement(By.cssSelector("div[title = '"+assessmentName+"']")).click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("'Upcoming' Tile","Assignment entry is not reverted in 'Upcomimg' Tile in dashboard");
            }

            new Navigator().NavigateTo("Dashboard");
            //1. Assignment entry should be reverted in Recent Activities Tile
            courseDetailsContainer = dashboard.courseDetailsContainer.getText();
            if(courseDetailsContainer.contains(assessmentName)){
                CustomAssert.fail("'Recent Activities' Tile","Assignment entry is not reverted in 'Recent Activities' Tile in dashboard");
            }

            //2. Assignment entry should be reverted in Course Stream Tile
            isAssignmentPresent = true;
            try{
                dashboard.courseStreamContentStream.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("'Course Stream' tile ","Assignment entry is not reverted in 'Course Stream' Tile in dashboard");
            }

            //e-textbook
            //1. Assignment entry should be reverted in 'Assignments' in e-textbook Right Tab
            new Navigator().NavigateTo("eTextbook");
            new TOCShow().tocHide();
            isAssignmentPresent = true;
            try{
                lessonPage.assignmentTab.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in Assignments Tab","Assignment entry is not reverted in 'Assignments' in e-textbook Right Tab");
            }

            //2. Assignment entry should be reverted in TOC
            isAssignmentPresent = true;
            try{
                lessonPage.TOCassignmentName.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in TOC Page","Un Assigned assignment is not reverted in Current Assignment Page");
            }

            //Assignments
            //Un Assigned assignment should be reverted
            new Navigator().NavigateTo("Assignments");
            isAssignmentPresent = true;
            try{
                currentAssignments.assignment.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in Current Assignment Page","Un Assigned assignment is reverted in Assignment Page");
            }


            //Course Stream
            //Unassigned assignment entry should be reverted back
            new Navigator().NavigateTo("Course Stream");
            isAssignmentPresent = true;
            try{
                courseStreamPage.assessmentName.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in Course Stream Page","Unassigned assignment is not reverted back in 'Course Stream' Page");
            }

            //My Activity
            //Assignment entry should be reverted
            new Navigator().NavigateTo("My Activity");
            isAssignmentPresent = true;
            try{
                driver.findElement(By.partialLinkText(assessmentName)).click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in My Activity Page","Unassigned assignment is not reverted back in 'My Activity' Page");
            }




//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..

            //Student 3 Side DashBoard

            new LoginUsingLTI().ltiLogin(dataIndex + "_3");//login as student3
            //1. Assignment entry should be reverted in Recent Activities Tile
            isAssignmentPresent = true;
            try{
                driver.findElement(By.cssSelector("div[title = '"+assessmentName+"']")).click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("'Upcoming' Tile","Assignment entry is not reverted in 'Upcomimg' Tile in dashboard");
            }


            //2. Assignment entry should be reverted in Course Stream Tile
            new Navigator().NavigateTo("Dashboard");
            isAssignmentPresent = true;
            try{
                dashboard.courseStreamContentStream.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("'Course Stream' tile ","Assignment entry is not reverted in 'Course Stream' Tile in dashboard");
            }

            //e-textbook
            //1. Assignment entry should be reverted in 'Assignments' in e-textbook Right Tab
            new Navigator().NavigateTo("eTextbook");
            new TOCShow().tocHide();
            isAssignmentPresent = true;
            try{
                lessonPage.assignmentTab.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in Assignments Tab","Assignment entry is not reverted in 'Assignments' in e-textbook Right Tab");
            }

            //2. Assignment entry should be reverted in TOC
            isAssignmentPresent = true;
            try{
                lessonPage.TOCassignmentName.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in TOC Page","Un Assigned assignment is not reverted in Current Assignment Page");
            }

            //Assignments
            //Un Assigned assignment should be reverted
            new Navigator().NavigateTo("Assignments");
            isAssignmentPresent = true;
            try{
                currentAssignments.assignment.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in Current Assignment Page","Un Assigned assignment is reverted in Assignment Page");
            }


            //Course Stream
            //Unassigned assignment entry should be reverted back
            new Navigator().NavigateTo("Course Stream");
            isAssignmentPresent = true;
            try{
                courseStreamPage.assessmentName.click();
                isAssignmentPresent = false;
            }catch(Exception e){

            }
            if(isAssignmentPresent ==false){
                CustomAssert.fail("Assignment Entry in Course Stream Page","Unassigned assignment is not reverted back in 'Course Stream' Page");
            }

        } catch (Exception e) {
            Assert.fail("Exception in TC PolicyOne of class PolicyOne", e);
        }
    }
}
