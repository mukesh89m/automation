package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT22.R227;

import au.com.bytecode.opencsv.CSVReader;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ReadFile;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 7/8/2015.
 */
public class FileBasedAssignmentForLsAdaptive extends Driver {

    @Test(priority = 1, enabled = true)
    public void fileBasedAssignmentForLsAdaptive() {

        try {
            //tc row no 53
            new Assignment().createFileBasedAssessment(53);
            new LoginUsingLTI().ltiLogin("53_1");//login as student1
            new LoginUsingLTI().ltiLogin("53_2");//login as student2
            new LoginUsingLTI().ltiLogin("53_3");//login as student3

            new LoginUsingLTI().ltiLogin("53");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(53);
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }

            List<WebElement> gradesNotReleasedText = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            int count =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Grades Not Released"))
                {
                    org.junit.Assert.fail("Grades Not Released text is not present");
                }
                count++;
            }
            if(driver.getPageSource().contains("Not Graded"))
            {
                org.junit.Assert.fail("Not Graded text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("53");
            String [] nextLine;
            int pos=0;
            while((nextLine=path.readNext())!=null)
            {
                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        String assignmentStatus1 = nextLine[6];
                        System.out.println("assignmentstatus1 :  " + assignmentStatus1);
                        Assert.assertEquals(assignmentStatus1.trim(),"Grades Not Released","Grades Not Released is not present");
                    }
                }
                pos++;
            }


        } catch (Exception e) {
            Assert.fail("Exception in test case fileBasedAssignmentForLsAdaptive of class FileBasedAssignmentForLsAdaptive", e);
        }
    }


    @Test(priority = 2,enabled = true)
    public void fileBasedAssignmentAssignedToSpecificStudent()
    {
        try
        {
            //tc row no 59
            new Assignment().createFileBasedAssessment(59);
            new LoginUsingLTI().ltiLogin("59_1");//login as student1
            new LoginUsingLTI().ltiLogin("59_2");//login as student2
            new LoginUsingLTI().ltiLogin("59_3");//login as student3

            new LoginUsingLTI().ltiLogin("59");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(59);
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            System.out.println("noOfAssignments:"+noOfAssignments);
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> studentGrade = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if(!(studentGrade.get(0).getText().trim().equals("Grades Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned")))
            {
                Assert.fail("Grades for students(to whom assignment is not assigned) cell is not contains text NOT ASSIGNED instead of NA");
            }
            int count = 0;
            for(WebElement ele: studentGrade)
            {
                System.out.println("ele"+ele.getText());
                if(ele.getText().trim().equals("NA"))
                {
                    Assert.fail("NA text is present on Gradebook page");
                }
                count++;
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("59");
            String [] nextLine;
            int pos=0;
            int lineNo = 0;
            while((nextLine=path.readNext())!=null)
            {

                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        lineNo++;
                        String assignmentStatus = nextLine[6];
                        System.out.println("assignmentstatus :  " + assignmentStatus);
                        if(lineNo == 1)
                            Assert.assertEquals(assignmentStatus.trim(),"Grades Not Released","Grades Not Released is not present");
                        else if(lineNo == 2)
                            Assert.assertEquals(assignmentStatus.trim(),"Not Assigned","Not Assigned is not present for student2");
                        else if(lineNo == 3)
                            Assert.assertEquals(assignmentStatus.trim(),"Not Assigned","Not Assigned is not present for student3");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case fileBasedAssignmentAssignedToSpecificStudent of class FileBasedAssignmentForLsAdaptive", e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void editThisLinkInMyQuestionBank()
    {
        try {
            //tc row no 67
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(67));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(67));
            new LoginUsingLTI().ltiLogin("67_1"); //Login as student
            new LoginUsingLTI().ltiLogin("67_2"); //Login as student
            new LoginUsingLTI().ltiLogin("67_3"); //Login as student
            new LoginUsingLTI().ltiLogin("67"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("67");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click(); //click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            myQuestionBank.closeTab.click();
            Thread.sleep(2000);
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.assignNowButton.click();//click on assign now
            new AssignLesson().assignTOCWithDefaultClassSection(67);//assign assignment
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }

            List<WebElement> gradesNotReleasedText = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            int count =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Grades Not Released"))
                {
                    org.junit.Assert.fail("Grades Not Released text is not present");
                }
                count++;
            }
            if(driver.getPageSource().contains("Not Graded"))
            {
                org.junit.Assert.fail("Not Graded text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("67");
            String [] nextLine;
            int pos=0;
            while((nextLine=path.readNext())!=null)
            {
                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        String assignmentStatus1 = nextLine[6];
                        System.out.println("assignmentstatus1 :  " + assignmentStatus1);
                        Assert.assertEquals(assignmentStatus1.trim(),"Grades Not Released","Grades Not Released is not present");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case editThisLinkInMyQuestionBank of class FileBasedAssignmentForLsAdaptive", e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void editThisLinkInMyQuestionBankForSpecificStudent()
    {
        try {
            //tc row no 73
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(73));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(73));
            new LoginUsingLTI().ltiLogin("73_1"); //Login as student
            new LoginUsingLTI().ltiLogin("73_2"); //Login as student
            new LoginUsingLTI().ltiLogin("73_3"); //Login as student
            new LoginUsingLTI().ltiLogin("73"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("73");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click(); //click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            myQuestionBank.closeTab.click();
            Thread.sleep(2000);
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.assignNowButton.click();//click on assign now
            new AssignLesson().assignCustomAssignmentFromCustomAssignmentPage(73);//assign assignment
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            System.out.println("noOfAssignments:"+noOfAssignments);
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> studentGrade = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if(!(studentGrade.get(0).getText().trim().equals("Grades Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned")))
            {
                Assert.fail("Grades for students(to whom assignment is not assigned) cell is not contains text NOT ASSIGNED instead of NA");
            }
            int count = 0;
            for(WebElement ele: studentGrade)
            {
                System.out.println("ele"+ele.getText());
                if(ele.getText().trim().equals("NA"))
                {
                    Assert.fail("NA text is present on Gradebook page");
                }
                count++;
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("73");
            String [] nextLine;
            int pos=0;
            int lineNo = 0;
            while((nextLine=path.readNext())!=null)
            {

                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        lineNo++;
                        String assignmentStatus = nextLine[6];
                        System.out.println("assignmentstatus :  " + assignmentStatus);
                        if(lineNo == 1)
                            Assert.assertEquals(assignmentStatus.trim(),"Grades Not Released","Grades Not Released is not present");
                        else if(lineNo == 2)
                            Assert.assertEquals(assignmentStatus.trim(),"Not Assigned","Not Assigned is not present for student2");
                        else if(lineNo == 3)
                            Assert.assertEquals(assignmentStatus.trim(),"Not Assigned","Not Assigned is not present for student3");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case editThisLinkInMyQuestionBankForSpecificStudent of class FileBasedAssignmentForLsAdaptive", e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void discussionWidgetAssignment()
    {
        try {
            //tc row no 80
            new LoginUsingLTI().ltiLogin("80_1"); //Login as student
            new LoginUsingLTI().ltiLogin("80_2"); //Login as student
            new LoginUsingLTI().ltiLogin("80_3"); //Login as student
            new LoginUsingLTI().ltiLogin("80"); //Login as instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidget("80");
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }

            List<WebElement> gradesNotReleasedText = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            int count =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Grades Not Released"))
                {
                    org.junit.Assert.fail("Grades Not Released text is not present");
                }
                count++;
            }
            if(driver.getPageSource().contains("Not Graded"))
            {
                org.junit.Assert.fail("Not Graded text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("80");
            String [] nextLine;
            int pos=0;
            while((nextLine=path.readNext())!=null)
            {
                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        String assignmentStatus1 = nextLine[6];
                        System.out.println("assignmentstatus1 :  " + assignmentStatus1);
                        Assert.assertEquals(assignmentStatus1.trim(),"Grades Not Released","Grades Not Released is not present");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case discussionWidgetAssignment of class FileBasedAssignmentForLsAdaptive", e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void discussionWidgetAssignmentAssignToSpecificStudent()
    {
        try {
            //tc row no 86
            new LoginUsingLTI().ltiLogin("86_1"); //Login as student
            new LoginUsingLTI().ltiLogin("86_2"); //Login as student
            new LoginUsingLTI().ltiLogin("86_3"); //Login as student
            new LoginUsingLTI().ltiLogin("86"); //Login as instructor
            new Navigator().NavigateTo("eTextbook");    //navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidget("86");
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if (studentCount != noOfStudents) {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            System.out.println("noOfAssignments:" + noOfAssignments);
            if (AssignmentCount != noOfAssignments) {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> studentGrade = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if (!(studentGrade.get(0).getText().trim().equals("Grades Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned"))) {
                Assert.fail("Grades for students(to whom assignment is not assigned) cell is not contains text NOT ASSIGNED instead of NA");
            }
            int count = 0;
            for (WebElement ele : studentGrade) {
                System.out.println("ele" + ele.getText());
                if (ele.getText().trim().equals("NA")) {
                    Assert.fail("NA text is present on Gradebook page");
                }
                count++;
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("86");
            String[] nextLine;
            int pos = 0;
            int lineNo = 0;
            while ((nextLine = path.readNext()) != null) {

                if (pos != 0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        lineNo++;
                        String assignmentStatus = nextLine[6];
                        System.out.println("assignmentstatus :  " + assignmentStatus);
                        if (lineNo == 1)
                            Assert.assertEquals(assignmentStatus.trim(), "Grades Not Released", "Grades Not Released is not present");
                        else if (lineNo == 2)
                            Assert.assertEquals(assignmentStatus.trim(), "Not Assigned", "Not Assigned is not present for student2");
                        else if (lineNo == 3)
                            Assert.assertEquals(assignmentStatus.trim(), "Not Assigned", "Not Assigned is not present for student3");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case discussionWidgetAssignmentAssignToSpecificStudent of class FileBasedAssignmentForLsAdaptive", e);
        }
    }


    @Test(priority = 7, enabled = true)
    public void fileBasedAssignmentForLsAdaptiveInMentorSide() {

        try {
            //tc row no 53
            new Assignment().createFileBasedAssessment(531);
            new LoginUsingLTI().ltiLogin("531_1");//login as student1
            new LoginUsingLTI().ltiLogin("531_2");//login as student2
            new LoginUsingLTI().ltiLogin("531_3");//login as student3

            new LoginUsingLTI().ltiLogin("531");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(531);
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }

            List<WebElement> gradesNotReleasedText = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            int count =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Grades Not Released"))
                {
                    org.junit.Assert.fail("Grades Not Released text is not present");
                }
                count++;
            }
            if(driver.getPageSource().contains("Not Graded"))
            {
                org.junit.Assert.fail("Not Graded text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("531");
            String [] nextLine;
            int pos=0;
            while((nextLine=path.readNext())!=null)
            {
                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        String assignmentStatus1 = nextLine[6];
                        System.out.println("assignmentstatus1 :  " + assignmentStatus1);
                        Assert.assertEquals(assignmentStatus1.trim(),"Grades Not Released","Grades Not Released is not present");
                    }
                }
                pos++;
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case fileBasedAssignmentForLsAdaptive of class FileBasedAssignmentForLsAdaptive", e);
        }
    }


    @Test(priority = 8,enabled = true)
    public void fileBasedAssignmentAssignedToSpecificStudentInMentorSide()
    {
        try
        {
            //tc row no 59
            new Assignment().createFileBasedAssessment(591);
            new LoginUsingLTI().ltiLogin("591_1");//login as student1
            new LoginUsingLTI().ltiLogin("591_2");//login as student2
            new LoginUsingLTI().ltiLogin("591_3");//login as student3

            new LoginUsingLTI().ltiLogin("591");//login as mentor
            new Assignment().assignFileBasedAssignmentToStudent(591);
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            System.out.println("noOfAssignments:"+noOfAssignments);
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> studentGrade = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if(!(studentGrade.get(0).getText().trim().equals("Grades Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned")))
            {
                Assert.fail("Grades for students(to whom assignment is not assigned) cell is not contains text NOT ASSIGNED instead of NA");
            }
            int count = 0;
            for(WebElement ele: studentGrade)
            {
                System.out.println("ele"+ele.getText());
                if(ele.getText().trim().equals("NA"))
                {
                    Assert.fail("NA text is present on Gradebook page");
                }
                count++;
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("591");
            String [] nextLine;
            int pos=0;
            int lineNo = 0;
            while((nextLine=path.readNext())!=null) {

                if (pos != 0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        lineNo++;
                        String assignmentStatus = nextLine[6];
                        System.out.println("assignmentstatus :  " + assignmentStatus);
                        if (lineNo == 1)
                            Assert.assertEquals(assignmentStatus.trim(), "Grades Not Released", "Grades Not Released is not present");
                        else if (lineNo == 2)
                            Assert.assertEquals(assignmentStatus.trim(), "Not Assigned", "Not Assigned is not present for student2");
                        else if (lineNo == 3)
                            Assert.assertEquals(assignmentStatus.trim(), "Not Assigned", "Not Assigned is not present for student3");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case fileBasedAssignmentAssignedToSpecificStudent of class FileBasedAssignmentForLsAdaptive", e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void editThisLinkInMyQuestionBankInMentorSide()
    {
        try {
            //tc row no 67
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(671));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(671));
            new LoginUsingLTI().ltiLogin("671_1"); //Login as student
            new LoginUsingLTI().ltiLogin("671_2"); //Login as student
            new LoginUsingLTI().ltiLogin("671_3"); //Login as student
            new LoginUsingLTI().ltiLogin("671"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("671");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click(); //click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            myQuestionBank.closeTab.click();
            Thread.sleep(2000);
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.assignNowButton.click();//click on assign now
            new AssignLesson().assignTOCWithDefaultClassSection(671);//assign assignment
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }

            List<WebElement> gradesNotReleasedText = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            int count =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Grades Not Released"))
                {
                    org.junit.Assert.fail("Grades Not Released text is not present");
                }
                count++;
            }
            if(driver.getPageSource().contains("Not Graded"))
            {
                org.junit.Assert.fail("Not Graded text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("671");
            String [] nextLine;
            int pos=0;
            while((nextLine=path.readNext())!=null)
            {
                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        String assignmentStatus1 = nextLine[6];
                        System.out.println("assignmentstatus1 :  " + assignmentStatus1);
                        Assert.assertEquals(assignmentStatus1.trim(),"Grades Not Released","Grades Not Released is not present");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case editThisLinkInMyQuestionBank of class FileBasedAssignmentForLsAdaptive", e);
        }
    }

    @Test(priority = 10,enabled = true)
    public void editThisLinkInMyQuestionBankForSpecificStudentInMentorSide()
    {
        try {
            //tc row no 73
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(731));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(731));
            new LoginUsingLTI().ltiLogin("731_1"); //Login as student
            new LoginUsingLTI().ltiLogin("731_2"); //Login as student
            new LoginUsingLTI().ltiLogin("731_3"); //Login as student
            new LoginUsingLTI().ltiLogin("731"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("731");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click(); //click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            myQuestionBank.closeTab.click();
            Thread.sleep(2000);
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.assignNowButton.click();//click on assign now
            new AssignLesson().assignCustomAssignmentFromCustomAssignmentPage(731);//assign assignment
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            System.out.println("noOfAssignments:"+noOfAssignments);
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> studentGrade = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if(!(studentGrade.get(0).getText().trim().equals("Grades Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned")))
            {
                Assert.fail("Grades for students(to whom assignment is not assigned) cell is not contains text NOT ASSIGNED instead of NA");
            }
            int count = 0;
            for(WebElement ele: studentGrade)
            {
                System.out.println("ele"+ele.getText());
                if(ele.getText().trim().equals("NA"))
                {
                    Assert.fail("NA text is present on Gradebook page");
                }
                count++;
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("731");
            String [] nextLine;
            int pos=0;
            int lineNo = 0;
            while((nextLine=path.readNext())!=null) {

                if (pos != 0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        lineNo++;
                        String assignmentStatus = nextLine[6];
                        System.out.println("assignmentstatus :  " + assignmentStatus);
                        if (lineNo == 1)
                            Assert.assertEquals(assignmentStatus.trim(), "Grades Not Released", "Grades Not Released is not present");
                        else if (lineNo == 2)
                            Assert.assertEquals(assignmentStatus.trim(), "Not Assigned", "Not Assigned is not present for student2");
                        else if (lineNo == 3)
                            Assert.assertEquals(assignmentStatus.trim(), "Not Assigned", "Not Assigned is not present for student3");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case editThisLinkInMyQuestionBankForSpecificStudent of class FileBasedAssignmentForLsAdaptive", e);
        }
    }

    @Test(priority = 11,enabled = true)
    public void discussionWidgetAssignmentInMentorSide()
    {
        try {
            //tc row no 80
            new LoginUsingLTI().ltiLogin("801_1"); //Login as student
            new LoginUsingLTI().ltiLogin("801_2"); //Login as student
            new LoginUsingLTI().ltiLogin("801_3"); //Login as student
            new LoginUsingLTI().ltiLogin("801"); //Login as instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidget("801");
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }

            List<WebElement> gradesNotReleasedText = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            int count =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Grades Not Released"))
                {
                    org.junit.Assert.fail("Grades Not Released text is not present");
                }
                count++;
            }
            if(driver.getPageSource().contains("Not Graded"))
            {
                org.junit.Assert.fail("Not Graded text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("801");
            String [] nextLine;
            int pos=0;
            while((nextLine=path.readNext())!=null)
            {
                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        String assignmentStatus1 = nextLine[6];
                        System.out.println("assignmentstatus1 :  " + assignmentStatus1);
                        Assert.assertEquals(assignmentStatus1.trim(),"Grades Not Released","Grades Not Released is not present");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case discussionWidgetAssignment of class FileBasedAssignmentForLsAdaptive", e);
        }
    }

    @Test(priority = 12,enabled = true)
    public void discussionWidgetAssignmentAssignToSpecificStudentInMentorSide()
    {
        try {
            //tc row no 86
            new LoginUsingLTI().ltiLogin("861_1"); //Login as student
            new LoginUsingLTI().ltiLogin("861_2"); //Login as student
            new LoginUsingLTI().ltiLogin("861_3"); //Login as student
            new LoginUsingLTI().ltiLogin("861"); //Login as instructor
            new Navigator().NavigateTo("eTextbook");    //navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidget("861");
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if (studentCount != noOfStudents) {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            System.out.println("noOfAssignments:" + noOfAssignments);
            if (AssignmentCount != noOfAssignments) {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> studentGrade = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if (!(studentGrade.get(0).getText().trim().equals("Grades Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned"))) {
                Assert.fail("Grades for students(to whom assignment is not assigned) cell is not contains text NOT ASSIGNED instead of NA");
            }
            int count = 0;
            for (WebElement ele : studentGrade) {
                System.out.println("ele" + ele.getText());
                if (ele.getText().trim().equals("NA")) {
                    Assert.fail("NA text is present on Gradebook page");
                }
                count++;
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("861");
            String[] nextLine;
            int pos = 0;
            int lineNo = 0;
            while ((nextLine = path.readNext()) != null) {

                if (pos != 0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        lineNo++;
                        String assignmentStatus = nextLine[6];
                        System.out.println("assignmentstatus :  " + assignmentStatus);
                        if (lineNo == 1)
                            Assert.assertEquals(assignmentStatus.trim(), "Grades Not Released", "Grades Not Released is not present");
                        else if (lineNo == 2)
                            Assert.assertEquals(assignmentStatus.trim(), "Not Assigned", "Not Assigned is not present for student2");
                        else if (lineNo == 3)
                            Assert.assertEquals(assignmentStatus.trim(), "Not Assigned", "Not Assigned is not present for student3");
                    }
                }
                pos++;
            }
        }

        catch (Exception e)
        {
            Assert.fail("Exception in test case discussionWidgetAssignmentAssignToSpecificStudent of class FileBasedAssignmentForLsAdaptive", e);
        }
    }


    @Test(priority = 13, enabled = true)
    public void fileBasedAssignmentForLs() {

        try {
            //tc row no 132
            new Assignment().createFileBasedAssessment(132);
            new LoginUsingLTI().ltiLogin("132_1");//login as student1
            new LoginUsingLTI().ltiLogin("132_2");//login as student2
            new LoginUsingLTI().ltiLogin("132_3");//login as student3

            new LoginUsingLTI().ltiLogin("132");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(132);
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }

            List<WebElement> gradesNotReleasedText = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            int count =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Grades Not Released"))
                {
                    org.junit.Assert.fail("Grades Not Released text is not present");
                }
                count++;
            }
            if(driver.getPageSource().contains("Not Graded"))
            {
                org.junit.Assert.fail("Not Graded text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("132");
            String [] nextLine;
            int pos=0;
            while((nextLine=path.readNext())!=null)
            {
                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        String assignmentStatus1 = nextLine[6];
                        System.out.println("assignmentstatus1 :  " + assignmentStatus1);
                        Assert.assertEquals(assignmentStatus1.trim(),"Grades Not Released","Grades Not Released is not present");
                    }
                }
                pos++;
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case fileBasedAssignmentForLsAdaptive of class FileBasedAssignmentForLsAdaptive", e);
        }
    }


    @Test(priority = 14,enabled = true)
    public void fileBasedAssignmentAssignedToSpecificStudentForLs()
    {
        try {
            //tc row no 138
            new Assignment().createFileBasedAssessment(138);
            new LoginUsingLTI().ltiLogin("138_1");//login as student1
            new LoginUsingLTI().ltiLogin("138_2");//login as student2
            new LoginUsingLTI().ltiLogin("138_3");//login as student3

            new LoginUsingLTI().ltiLogin("138");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(138);
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if (studentCount != noOfStudents) {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            System.out.println("noOfAssignments:" + noOfAssignments);
            if (AssignmentCount != noOfAssignments) {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> studentGrade = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if (!(studentGrade.get(0).getText().trim().equals("Grades Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned"))) {
                Assert.fail("Grades for students(to whom assignment is not assigned) cell is not contains text NOT ASSIGNED instead of NA");
            }
            int count = 0;
            for (WebElement ele : studentGrade) {
                System.out.println("ele" + ele.getText());
                if (ele.getText().trim().equals("NA")) {
                    Assert.fail("NA text is present on Gradebook page");
                }
                count++;
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("138");
            String[] nextLine;
            int pos = 0;
            int lineNo = 0;
            while ((nextLine = path.readNext()) != null) {

                if (pos != 0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        lineNo++;
                        String assignmentStatus = nextLine[6];
                        System.out.println("assignmentstatus :  " + assignmentStatus);
                        if (lineNo == 1)
                            Assert.assertEquals(assignmentStatus.trim(), "Grades Not Released", "Grades Not Released is not present");
                        else if (lineNo == 2)
                            Assert.assertEquals(assignmentStatus.trim(), "Not Assigned", "Not Assigned is not present for student2");
                        else if (lineNo == 3)
                            Assert.assertEquals(assignmentStatus.trim(), "Not Assigned", "Not Assigned is not present for student3");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case fileBasedAssignmentAssignedToSpecificStudent of class FileBasedAssignmentForLsAdaptive", e);
        }
    }


    @Test(priority = 15,enabled = true)
    public void discussionWidgetAssignmentForLs()
    {
        try {
            //tc row no 146
            new LoginUsingLTI().ltiLogin("146_1"); //Login as student
            new LoginUsingLTI().ltiLogin("146_2"); //Login as student
            new LoginUsingLTI().ltiLogin("146_3"); //Login as student
            new LoginUsingLTI().ltiLogin("146"); //Login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(15);
            new SelectCourse().selectInvisibleAssignment("8A.3: South Asia’s Physiography");
            WebElement scroll=driver.findElement(By.cssSelector("span.assign-this-text"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            new DiscussionWidget().assignDiscussionWidget("146");
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }

            List<WebElement> gradesNotReleasedText = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            int count =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Grades Not Released"))
                {
                    org.junit.Assert.fail("Grades Not Released text is not present");
                }
                count++;
            }
            if(driver.getPageSource().contains("Not Graded"))
            {
                org.junit.Assert.fail("Not Graded text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("146");
            String [] nextLine;
            int pos=0;
            while((nextLine=path.readNext())!=null)
            {
                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        String assignmentStatus1 = nextLine[6];
                        System.out.println("assignmentstatus1 :  " + assignmentStatus1);
                        Assert.assertEquals(assignmentStatus1.trim(),"Grades Not Released","Grades Not Released is not present");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case discussionWidgetAssignment of class FileBasedAssignmentForLsAdaptive", e);
        }
    }

    @Test(priority = 16,enabled = true)
    public void discussionWidgetAssignmentAssignToSpecificStudentForLs()
    {
        try {
            //tc row no 152
            new LoginUsingLTI().ltiLogin("152_1"); //Login as student
            new LoginUsingLTI().ltiLogin("152_2"); //Login as student
            new LoginUsingLTI().ltiLogin("152_3"); //Login as student
            new LoginUsingLTI().ltiLogin("152"); //Login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(15);
            new SelectCourse().selectInvisibleAssignment("8A.3: South Asia’s Physiography");
            new DiscussionWidget().assignDiscussionWidget("152");
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if (studentCount != noOfStudents) {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            System.out.println("noOfAssignments:" + noOfAssignments);
            if (AssignmentCount != noOfAssignments) {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> studentGrade = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if (!(studentGrade.get(0).getText().trim().equals("Grades Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned"))) {
                Assert.fail("Grades for students(to whom assignment is not assigned) cell is not contains text NOT ASSIGNED instead of NA");
            }
            int count = 0;
            for (WebElement ele : studentGrade) {
                System.out.println("ele" + ele.getText());
                if (ele.getText().trim().equals("NA")) {
                    Assert.fail("NA text is present on Gradebook page");
                }
                count++;
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("152");
            String[] nextLine;
            int pos = 0;
            int lineNo = 0;
            while ((nextLine = path.readNext()) != null) {

                if (pos != 0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        lineNo++;
                        String assignmentStatus = nextLine[6];
                        System.out.println("assignmentstatus :  " + assignmentStatus);
                        if (lineNo == 1)
                            Assert.assertEquals(assignmentStatus.trim(), "Grades Not Released", "Grades Not Released is not present");
                        else if (lineNo == 2)
                            Assert.assertEquals(assignmentStatus.trim(), "Not Assigned", "Not Assigned is not present for student2");
                        else if (lineNo == 3)
                            Assert.assertEquals(assignmentStatus.trim(), "Not Assigned", "Not Assigned is not present for student3");
                    }
                }
                pos++;
            }
        }

        catch (Exception e)
        {
            Assert.fail("Exception in test case discussionWidgetAssignmentAssignToSpecificStudent of class FileBasedAssignmentForLsAdaptive", e);
        }
    }



    @Test(priority = 17,enabled = true)
    public void editThisLinkInMyQuestionBankDForLs()
    {
        try {
            //tc row no 159
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(159));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(159));
            new LoginUsingLTI().ltiLogin("159_1"); //Login as student
            new LoginUsingLTI().ltiLogin("159_2"); //Login as student
            new LoginUsingLTI().ltiLogin("159_3"); //Login as student
            new LoginUsingLTI().ltiLogin("159"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("159");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click(); //click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            myQuestionBank.closeTab.click();
            Thread.sleep(2000);
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.assignNowButton.click();//click on assign now
            new AssignLesson().assignTOCWithDefaultClassSection(159);//assign assignment
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }

            List<WebElement> gradesNotReleasedText = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            int count =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Grades Not Released"))
                {
                    org.junit.Assert.fail("Grades Not Released text is not present");
                }
                count++;
            }
            if(driver.getPageSource().contains("Not Graded"))
            {
                org.junit.Assert.fail("Not Graded text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("159");
            String [] nextLine;
            int pos=0;
            while((nextLine=path.readNext())!=null)
            {
                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        String assignmentStatus1 = nextLine[6];
                        System.out.println("assignmentstatus1 :  " + assignmentStatus1);
                        Assert.assertEquals(assignmentStatus1.trim(),"Grades Not Released","Grades Not Released is not present");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case editThisLinkInMyQuestionBank of class FileBasedAssignmentForLsAdaptive", e);
        }
    }

    @Test(priority = 18,enabled = true)
    public void editThisLinkInMyQuestionBankForSpecificStudentForLs()
    {
        try {
            //tc row no 165
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(165));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(165));
            new LoginUsingLTI().ltiLogin("165_1"); //Login as student
            new LoginUsingLTI().ltiLogin("165_2"); //Login as student
            new LoginUsingLTI().ltiLogin("165_3"); //Login as student
            new LoginUsingLTI().ltiLogin("165"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("165");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click(); //click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            myQuestionBank.closeTab.click();
            Thread.sleep(2000);
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.assignNowButton.click();//click on assign now
            new AssignLesson().assignCustomAssignmentFromCustomAssignmentPage(165);//assign assignment
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            System.out.println("noOfAssignments:"+noOfAssignments);
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> studentGrade = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if(!(studentGrade.get(0).getText().trim().equals("Grades Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned")))
            {
                Assert.fail("Grades for students(to whom assignment is not assigned) cell is not contains text NOT ASSIGNED instead of NA");
            }
            int count = 0;
            for(WebElement ele: studentGrade)
            {
                System.out.println("ele"+ele.getText());
                if(ele.getText().trim().equals("NA"))
                {
                    Assert.fail("NA text is present on Gradebook page");
                }
                count++;
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("165");
            String [] nextLine;
            int pos=0;
            int lineNo = 0;
            while((nextLine=path.readNext())!=null) {

                if (pos != 0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        lineNo++;
                        String assignmentStatus = nextLine[6];
                        System.out.println("assignmentstatus :  " + assignmentStatus);
                        if (lineNo == 1)
                            Assert.assertEquals(assignmentStatus.trim(), "Grades Not Released", "Grades Not Released is not present");
                        else if (lineNo == 2)
                            Assert.assertEquals(assignmentStatus.trim(), "Not Assigned", "Not Assigned is not present for student2");
                        else if (lineNo == 3)
                            Assert.assertEquals(assignmentStatus.trim(), "Not Assigned", "Not Assigned is not present for student3");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case editThisLinkInMyQuestionBankForSpecificStudent of class FileBasedAssignmentForLsAdaptive", e);
        }
    }

    @Test(priority = 19, enabled = true)
    public void fileBasedAssignmentForLsMentorSide() {

        try {
            //tc row no 132
            new Assignment().createFileBasedAssessment(1321);
            new LoginUsingLTI().ltiLogin("1321_1");//login as student1
            new LoginUsingLTI().ltiLogin("1321_2");//login as student2
            new LoginUsingLTI().ltiLogin("1321_3");//login as student3

            new LoginUsingLTI().ltiLogin("1321");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(1321);
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }

            List<WebElement> gradesNotReleasedText = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            int count =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Grades Not Released"))
                {
                    org.junit.Assert.fail("Grades Not Released text is not present");
                }
                count++;
            }
            if(driver.getPageSource().contains("Not Graded"))
            {
                org.junit.Assert.fail("Not Graded text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("1321");
            String [] nextLine;
            int pos=0;
            while((nextLine=path.readNext())!=null)
            {
                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        String assignmentStatus1 = nextLine[6];
                        System.out.println("assignmentstatus1 :  " + assignmentStatus1);
                        Assert.assertEquals(assignmentStatus1.trim(),"Grades Not Released","Grades Not Released is not present");
                    }
                }
                pos++;
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case fileBasedAssignmentForLsAdaptive of class FileBasedAssignmentForLsAdaptive", e);
        }
    }


    @Test(priority = 20,enabled = true)
    public void fileBasedAssignmentAssignedToSpecificStudentForLsMentorSide()
    {
        try
        {
            //tc row no 138
            new Assignment().createFileBasedAssessment(1381);
            new LoginUsingLTI().ltiLogin("1381_1");//login as student1
            new LoginUsingLTI().ltiLogin("1381_2");//login as student2
            new LoginUsingLTI().ltiLogin("1381_3");//login as student3

            new LoginUsingLTI().ltiLogin("1381");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(1381);
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            System.out.println("noOfAssignments:"+noOfAssignments);
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> studentGrade = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if(!(studentGrade.get(0).getText().trim().equals("Grades Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned")))
            {
                Assert.fail("Grades for students(to whom assignment is not assigned) cell is not contains text NOT ASSIGNED instead of NA");
            }
            int count = 0;
            for(WebElement ele: studentGrade)
            {
                System.out.println("ele"+ele.getText());
                if(ele.getText().trim().equals("NA"))
                {
                    Assert.fail("NA text is present on Gradebook page");
                }
                count++;
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("1381");
            String [] nextLine;
            int pos=0;
            int lineNo = 0;
            while((nextLine=path.readNext())!=null)
            {

                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        lineNo++;
                        String assignmentStatus = nextLine[6];
                        System.out.println("assignmentstatus :  " + assignmentStatus);
                        if(lineNo == 1)
                            Assert.assertEquals(assignmentStatus.trim(),"Grades Not Released","Grades Not Released is not present");
                        else if(lineNo == 2)
                            Assert.assertEquals(assignmentStatus.trim(),"Not Assigned","Not Assigned is not present for student2");
                        else if(lineNo == 3)
                            Assert.assertEquals(assignmentStatus.trim(),"Not Assigned","Not Assigned is not present for student3");
                    }
                }
                pos++;}
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case fileBasedAssignmentAssignedToSpecificStudent of class FileBasedAssignmentForLsAdaptive", e);
        }
    }


    @Test(priority = 21,enabled = true)
    public void discussionWidgetAssignmentForLsMentorSide()
    {
        try {
            //tc row no 146
            new LoginUsingLTI().ltiLogin("1461_1"); //Login as student
            new LoginUsingLTI().ltiLogin("1461_2"); //Login as student
            new LoginUsingLTI().ltiLogin("1461_3"); //Login as student
            new LoginUsingLTI().ltiLogin("1461"); //Login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(15);
            new SelectCourse().selectInvisibleAssignment("8A.3: South Asia’s Physiography");
            WebElement scroll=driver.findElement(By.cssSelector("span.assign-this-text"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            new DiscussionWidget().assignDiscussionWidget("1461");
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }

            List<WebElement> gradesNotReleasedText = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            int count =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Grades Not Released"))
                {
                    org.junit.Assert.fail("Grades Not Released text is not present");
                }
                count++;
            }
            if(driver.getPageSource().contains("Not Graded"))
            {
                org.junit.Assert.fail("Not Graded text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("1461");
            String [] nextLine;
            int pos=0;
            while((nextLine=path.readNext())!=null)
            {
                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        String assignmentStatus1 = nextLine[6];
                        System.out.println("assignmentstatus1 :  " + assignmentStatus1);
                        Assert.assertEquals(assignmentStatus1.trim(),"Grades Not Released","Grades Not Released is not present");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case discussionWidgetAssignment of class FileBasedAssignmentForLsAdaptive", e);
        }
    }

    @Test(priority = 22,enabled = true)
    public void discussionWidgetAssignmentAssignToSpecificStudentForLsMentorSide()
    {
        try {
            //tc row no 152
            new LoginUsingLTI().ltiLogin("1521_1"); //Login as student
            new LoginUsingLTI().ltiLogin("1521_2"); //Login as student
            new LoginUsingLTI().ltiLogin("1521_3"); //Login as student
            new LoginUsingLTI().ltiLogin("1521"); //Login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(15);
            new SelectCourse().selectInvisibleAssignment("8A.3: South Asia’s Physiography");
            new DiscussionWidget().assignDiscussionWidget("1521");
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            System.out.println("noOfAssignments:"+noOfAssignments);
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> studentGrade = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if(!(studentGrade.get(0).getText().trim().equals("Grades Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned")))
            {
                Assert.fail("Grades for students(to whom assignment is not assigned) cell is not contains text NOT ASSIGNED instead of NA");
            }
            int count = 0;
            for(WebElement ele: studentGrade)
            {
                System.out.println("ele"+ele.getText());
                if(ele.getText().trim().equals("NA"))
                {
                    Assert.fail("NA text is present on Gradebook page");
                }
                count++;
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("1521");
            String [] nextLine;
            int pos=0;
            int lineNo = 0;
            while((nextLine=path.readNext())!=null) {

                if (pos != 0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        lineNo++;
                        String assignmentStatus = nextLine[6];
                        System.out.println("assignmentstatus :  " + assignmentStatus);
                        if (lineNo == 1)
                            Assert.assertEquals(assignmentStatus.trim(), "Grades Not Released", "Grades Not Released is not present");
                        else if (lineNo == 2)
                            Assert.assertEquals(assignmentStatus.trim(), "Not Assigned", "Not Assigned is not present for student2");
                        else if (lineNo == 3)
                            Assert.assertEquals(assignmentStatus.trim(), "Not Assigned", "Not Assigned is not present for student3");
                    }
                }
                pos++;
            }
        }

        catch (Exception e)
        {
            Assert.fail("Exception in test case discussionWidgetAssignmentAssignToSpecificStudent of class FileBasedAssignmentForLsAdaptive", e);
        }
    }



    @Test(priority = 23,enabled = true)
    public void editThisLinkInMyQuestionBankDForLsMentorSide()
    {
        try {
            //tc row no 159
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(1591));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(1591));
            new LoginUsingLTI().ltiLogin("1591_1"); //Login as student
            new LoginUsingLTI().ltiLogin("1591_2"); //Login as student
            new LoginUsingLTI().ltiLogin("1591_3"); //Login as student
            new LoginUsingLTI().ltiLogin("1591"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("1591");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click(); //click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            myQuestionBank.closeTab.click();
            Thread.sleep(2000);
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.assignNowButton.click();//click on assign now
            new AssignLesson().assignTOCWithDefaultClassSection(1591);//assign assignment
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }

            List<WebElement> gradesNotReleasedText = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            int count =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Grades Not Released"))
                {
                    org.junit.Assert.fail("Grades Not Released text is not present");
                }
                count++;
            }
            if(driver.getPageSource().contains("Not Graded"))
            {
                org.junit.Assert.fail("Not Graded text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("1591");
            String [] nextLine;
            int pos=0;
            while((nextLine=path.readNext())!=null)
            {
                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        String assignmentStatus1 = nextLine[6];
                        System.out.println("assignmentstatus1 :  " + assignmentStatus1);
                        Assert.assertEquals(assignmentStatus1.trim(),"Grades Not Released","Grades Not Released is not present");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case editThisLinkInMyQuestionBank of class FileBasedAssignmentForLsAdaptive", e);
        }
    }

    @Test(priority = 24,enabled = true)
    public void editThisLinkInMyQuestionBankForSpecificStudentForLsMentorSide()
    {
        try {
            //tc row no 165
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(1651));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(1651));
            new LoginUsingLTI().ltiLogin("1651_1"); //Login as student
            new LoginUsingLTI().ltiLogin("1651_2"); //Login as student
            new LoginUsingLTI().ltiLogin("1651_3"); //Login as student
            new LoginUsingLTI().ltiLogin("1651"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("1651");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click(); //click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            myQuestionBank.closeTab.click();
            Thread.sleep(2000);
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.assignNowButton.click();//click on assign now
            new AssignLesson().assignCustomAssignmentFromCustomAssignmentPage(1651);//assign assignment
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            System.out.println("noOfAssignments:"+noOfAssignments);
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> studentGrade = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if(!(studentGrade.get(0).getText().trim().equals("Grades Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned")))
            {
                Assert.fail("Grades for students(to whom assignment is not assigned) cell is not contains text NOT ASSIGNED instead of NA");
            }
            int count = 0;
            for(WebElement ele: studentGrade)
            {
                System.out.println("ele"+ele.getText());
                if(ele.getText().trim().equals("NA"))
                {
                    Assert.fail("NA text is present on Gradebook page");
                }
                count++;
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("1651");
            String [] nextLine;
            int pos=0;
            int lineNo = 0;
            while((nextLine=path.readNext())!=null)
            {

                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        lineNo++;
                        String assignmentStatus = nextLine[6];
                        System.out.println("assignmentstatus :  " + assignmentStatus);
                        if(lineNo == 1)
                            Assert.assertEquals(assignmentStatus.trim(),"Grades Not Released","Grades Not Released is not present");
                        else if(lineNo == 2)
                            Assert.assertEquals(assignmentStatus.trim(),"Not Assigned","Not Assigned is not present for student2");
                        else if(lineNo == 3)
                            Assert.assertEquals(assignmentStatus.trim(),"Not Assigned","Not Assigned is not present for student3");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case editThisLinkInMyQuestionBankForSpecificStudent of class FileBasedAssignmentForLsAdaptive", e);
        }
    }
}
