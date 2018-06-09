package com.snapwiz.selenium.tests.staf.learnon.testcases.IT22.R227;
import au.com.bytecode.opencsv.CSVReader;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.AssignLesson;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.ExportToCSVGradebookPage;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.SearchQuestionInCustomCourseAssignemnt;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learnon.pageFactory.assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.ReadFile;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.UIElement;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.*;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Navigator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by root on 8/12/15.
 */
public class TextShouldBeChangedToMarksNotReleased {
    @Test(priority = 1, enabled = true)
    public void textShouldBeChangedToMarksNotReleased()
    {
        try {
            //tc row no 183
            Driver.startDriver();
            MyQuestionBank myQuestionBank= PageFactory.initElements(Driver.driver, MyQuestionBank.class);
            String searchQuestion =ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(183));
            String customAssignmentName =ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(183));
            new Assignment().create(183); // Create an assignment
            new LoginUsingLTI().ltiLogin("183_1"); //Login as student
            new LoginUsingLTI().ltiLogin("183_2"); //Login as student
            new LoginUsingLTI().ltiLogin("183_3"); //Login as student
            new LoginUsingLTI().ltiLogin("183"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");
            new Navigator().navigateToTab("Question Banks");
            myQuestionBank.customAssignmentButton_list.get(1).click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("183");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click(); //click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my que
            new AssignLesson().Assigncustomeassignemnt(183);
            new UIElement().waitAndFindElement(By.xpath("//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath("//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            Driver.driver.findElement(By.linkText("Markbook")).click();
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = Driver.driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = Driver.driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> gradesNotReleasedText = Driver.driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            int count =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Marks Not Released"))
                {
                    Assert.fail("Marks Not Released text is not present");
                }
                count++;
            }
            if(Driver.driver.getPageSource().contains("Not Marked"))
            {
                Assert.fail("Not Marked text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("183");
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
                        Assert.assertEquals(assignmentStatus1.trim(),"Marks Not Released","Marks Not Released is not present");
                    }
                }
                pos++;
            }

        }
            catch (Exception e)
            {
                Assert.fail("Exception in TC textShouldBeChangedToMarksNotReleased in class TextShouldBeChangedToMarksNotReleased", e);
            }
        }


    @Test(priority = 2,enabled = true)
    public void customAssignmentAssignedToSpecificStudent()
    {
        try
        {
            //tc row no 189
            Driver.startDriver();
            MyQuestionBank myQuestionBank= PageFactory.initElements(Driver.driver, MyQuestionBank.class);
            String searchQuestion =ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(189));
            String customAssignmentName =ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(189));
            new LoginUsingLTI().ltiLogin("189_1"); //Login as student
            new LoginUsingLTI().ltiLogin("189_2"); //Login as student
            new LoginUsingLTI().ltiLogin("189_3"); //Login as student
            new LoginUsingLTI().ltiLogin("189"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");
            new Navigator().navigateToTab("Question Banks");
            myQuestionBank.customAssignmentButton_list.get(1).click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("189");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click(); //click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(189);
            new UIElement().waitAndFindElement(By.xpath("//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath("//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            Driver.driver.findElement(By.linkText("Markbook")).click();
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = Driver.driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = Driver.driver.findElements(By.xpath(".//div[@class='gradebook-assignment-header-cell']")).size();
            System.out.println("noOfAssignments:"+noOfAssignments);
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> studentGrade = Driver.driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if(!(studentGrade.get(0).getText().trim().equals("Marks Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned")))
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
            CSVReader path = new ReadFile().readCSVFilePath("189");
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
                            Assert.assertEquals(assignmentStatus.trim(),"Marks Not Released","Marks Not Released is not present");
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
            Assert.fail("Exception in TC customAssignmentAssignedToSpecificStudent in class TextShouldBeChangedToMarksNotReleased", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void staticAssessmentGradesNotReleased(){
        try
        {
            //tc row no 198
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("198_1");
            new LoginUsingLTI().ltiLogin("198_2");
            new LoginUsingLTI().ltiLogin("198_3");
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(198));
             new Assignment().create(198);
            new LoginUsingLTI().ltiLogin("198");
            new UIElement().waitAndFindElement(By.xpath("//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath("//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            Driver.driver.findElement(By.linkText("Course")).click();
            WebDriverWait wait = new WebDriverWait(Driver.driver, 10);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className("chapter-heading-label")));
            List<WebElement> chapterSelect = Driver.driver.findElements(By.className("chapter-heading-label"));
            chapterSelect.get(0).click();
            Thread.sleep(2000);
            List<WebElement> staticAssessments = Driver.driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
            int count = 0;
            for(WebElement ele : staticAssessments)
            {
                if(ele.getAttribute("title").trim().equals(assessmentName))
                {
                    List<WebElement> assignLink = Driver.driver.findElements(By.xpath("//*[@class='assessment-card']/li/div/div[@class='ls-inner-arw']"));
                    assignLink.get(count+count).click();
                    Thread.sleep(1000);
                    List<WebElement> assignThis = Driver.driver.findElements(By.xpath("//*[@title='Assign This']"));
                    System.out.println("assignThis:"+assignThis);
                    assignThis.get(count).click();
                    Thread.sleep(1000);
                }
                count++;
            }

            Thread.sleep(1000);
            new AssignLesson().assignTOCWithDefaultClassSection(198);
            new UIElement().waitAndFindElement(By.xpath("//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath("//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            Driver.driver.findElement(By.linkText("Markbook")).click();
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = Driver.driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = Driver.driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> gradesNotReleasedText = Driver.driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            int found =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Marks Not Released"))
                {
                    Assert.fail("Marks Not Released text is not present");
                }
                found++;
            }
            if(Driver.driver.getPageSource().contains("Not Marked"))
            {
                Assert.fail("Not Marked text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("198");
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
                        Assert.assertEquals(assignmentStatus1.trim(),"Marks Not Released","Grades Not Released is not present");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC staticAssessmentGradesNotReleased in class TextShouldBeChangedToMarksNotReleased", e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void staticAssessmentAssignedToSpecificStudent(){
        try
        {
            //tc row no 204
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("204_1");
            new LoginUsingLTI().ltiLogin("204_2");
            new LoginUsingLTI().ltiLogin("204_3");
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(204));
            System.out.println("assessmentName: "+assessmentName);
            new Assignment().create(204);
            new LoginUsingLTI().ltiLogin("204");
            new UIElement().waitAndFindElement(By.xpath("//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath("//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            Driver.driver.findElement(By.linkText("Course")).click();
            WebDriverWait wait = new WebDriverWait(Driver.driver, 10);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className("chapter-heading-label")));
            List<WebElement> chapterSelect = Driver.driver.findElements(By.className("chapter-heading-label"));
            chapterSelect.get(0).click();
            Thread.sleep(2000);
            List<WebElement> staticAssessments = Driver.driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
            int count = 0;
            for(WebElement ele : staticAssessments)
            {
                if(ele.getAttribute("title").trim().equals(assessmentName))
                {
                    List<WebElement> assignLink = Driver.driver.findElements(By.xpath("//*[@class='assessment-card']/li/div/div[@class='ls-inner-arw']"));
                    assignLink.get(count+count).click();
                    Thread.sleep(1000);
                    List<WebElement> assignThis = Driver.driver.findElements(By.xpath("//*[@title='Assign This']"));
                    System.out.println("assignThis:"+assignThis);
                    assignThis.get(count).click();
                    Thread.sleep(1000);
                }
                count++;
            }
            Thread.sleep(1000);
            new AssignLesson().assignTOCToSpecificStudent(204);
            new UIElement().waitAndFindElement(By.xpath(".//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            Driver.driver.findElement(By.linkText("Markbook")).click();
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = Driver.driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            System.out.println("noOfStudents:"+noOfStudents);
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = Driver.driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> studentGrade = Driver.driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if(!(studentGrade.get(0).getText().trim().equals("Marks Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned")))
            {
                Assert.fail("Grades for students(to whom assignment is not assigned) cell is not contains text NOT ASSIGNED instead of NA");
            }
            int found = 0;
            for(WebElement ele: studentGrade)
            {
                if(ele.getText().trim().equals("NA"))
                {
                    Assert.fail("NA text is present on Gradebook page");
                }
                found++;
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("204");
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
                            Assert.assertEquals(assignmentStatus.trim(),"Marks Not Released","Marks Not Released is not present");
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
            Assert.fail("Exception in TC staticAssessmentAssignedToSpecificStudent in class TextShouldBeChangedToMarksNotReleased", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void fileBasedAssignment() {

        try {
            //tc row no 212
            Driver.startDriver();
            new Assignment().createFileBasedAssessment(212);
            new LoginUsingLTI().ltiLogin("212_1");//login as student1
            new LoginUsingLTI().ltiLogin("212_2");//login as student2
            new LoginUsingLTI().ltiLogin("212_3");//login as student3

            new LoginUsingLTI().ltiLogin("212");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(212);
            new Navigator().NavigateTo("Markbook");//navigate to gradebook
            int studentCount = 3;
            int noOfStudents = Driver.driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = Driver.driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }

            List<WebElement> gradesNotReleasedText = Driver.driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            int count =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Marks Not Released"))
                {
                    org.junit.Assert.fail("Grades Not Released text is not present");
                }
                count++;
            }
            if(Driver.driver.getPageSource().contains("Not Marked"))
            {
                org.junit.Assert.fail("Not Marked text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("212");
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
                        Assert.assertEquals(assignmentStatus1.trim(),"Marks Not Released","Marks Not Released is not present");
                    }
                }
                pos++;
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC fileBasedAssignment in class TextShouldBeChangedToMarksNotReleased", e);
        }
    }



    @Test(priority = 6,enabled = true)
    public void fileBasedAssignmentAssignedToSpecificStudent()
    {
        try
        {
            //tc row no 218
            Driver.startDriver();
            new Assignment().createFileBasedAssessment(218);
            new LoginUsingLTI().ltiLogin("218_1");//login as student1
            new LoginUsingLTI().ltiLogin("218_2");//login as student2
            new LoginUsingLTI().ltiLogin("218_3");//login as student3

            new LoginUsingLTI().ltiLogin("218");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(218);
            new Navigator().NavigateTo("Markbook");//navigate to gradebook
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = Driver.driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments =Driver.driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            System.out.println("noOfAssignments:"+noOfAssignments);
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> studentGrade = Driver.driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if(!(studentGrade.get(0).getText().trim().equals("Marks Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned")))
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
            CSVReader path = new ReadFile().readCSVFilePath("218");
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
                            Assert.assertEquals(assignmentStatus.trim(),"Marks Not Released","Grades Not Released is not present");
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
            Assert.fail("Exception in test case fileBasedAssignmentAssignedToSpecificStudent of class TextShouldBeChangedToMarksNotReleased", e);
        }
    }



    @Test(priority = 7,enabled = true)
    public void editThisLinkInMyQuestionBank()
    {
        try {
            //tc row no 239
            Driver.startDriver();
            NewAssignment newAssignment = PageFactory.initElements(Driver.driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(Driver.driver,MyQuestionBank.class);
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(239));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(239));
            new LoginUsingLTI().ltiLogin("239_1"); //Login as student
            new LoginUsingLTI().ltiLogin("239_2"); //Login as student
            new LoginUsingLTI().ltiLogin("239_3"); //Login as student
            new LoginUsingLTI().ltiLogin("239"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("239");//select one question
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
            new AssignLesson().assignTOCWithDefaultClassSection(239);//assign assignment
            new Navigator().NavigateTo("Markbook");//navigate to gradebook
            int studentCount = 3;
            int noOfStudents = Driver.driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = Driver.driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }

            List<WebElement> gradesNotReleasedText = Driver.driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            int count =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Marks Not Released"))
                {
                    org.junit.Assert.fail("Marks Not Released text is not present");
                }
                count++;
            }
            if(Driver.driver.getPageSource().contains("Not Marked"))
            {
                org.junit.Assert.fail("Not Marked text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path =new ReadFile().readCSVFilePath("239");
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
                        Assert.assertEquals(assignmentStatus1.trim(),"Marks Not Released","Marks Not Released is not present");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case editThisLinkInMyQuestionBank of class TextShouldBeChangedToMarksNotReleased", e);
        }
    }

    @Test(priority = 8,enabled = true)
    public void editThisLinkInMyQuestionBankForSpecificStudent()
    {
        try {
            //tc row no 245
            Driver.startDriver();
            NewAssignment newAssignment = PageFactory.initElements(Driver.driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(Driver.driver,MyQuestionBank.class);
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(245));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(245));
            new LoginUsingLTI().ltiLogin("245_1"); //Login as student
            new LoginUsingLTI().ltiLogin("245_2"); //Login as student
            new LoginUsingLTI().ltiLogin("245_3"); //Login as student
            new LoginUsingLTI().ltiLogin("245"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("245");//select one question
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
            new AssignLesson().assignCustomAssignmentFromCustomAssignmentPage(245);//assign assignment
            new Navigator().NavigateTo("Markbook");//navigate to gradebook
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = Driver.driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            if(studentCount!=noOfStudents)
            {
                Assert.fail("All students for the respective class is not displayed");
            }
            int AssignmentCount = 1;
            int noOfAssignments = Driver.driver.findElements(By.xpath("//div[@class='gradebook-assignment-header-cell']")).size();
            System.out.println("noOfAssignments:"+noOfAssignments);
            if(AssignmentCount!=noOfAssignments)
            {
                Assert.fail("All gradable assignments assigned by the instructor are not displayed");
            }
            List<WebElement> studentGrade = Driver.driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if(!(studentGrade.get(0).getText().trim().equals("Marks Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned")))
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
            CSVReader path = new ReadFile().readCSVFilePath("245");
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
                            Assert.assertEquals(assignmentStatus.trim(),"Marks Not Released","Grades Not Released is not present");
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
            Assert.fail("Exception in test case editThisLinkInMyQuestionBankForSpecificStudent of class TextShouldBeChangedToMarksNotReleased", e);
        }
    }

}
