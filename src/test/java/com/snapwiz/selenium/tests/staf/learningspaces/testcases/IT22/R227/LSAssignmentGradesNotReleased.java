package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT22.R227;

import au.com.bytecode.opencsv.CSVReader;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ReadFile;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by durgapathi on 7/10/2015.
 */
public class LSAssignmentGradesNotReleased extends Driver{
    @Test(priority = 1,enabled = true)
    public void customAssignmentGradesNotReleased()
    {
        try
        {
            int dataIndex = 14;
            MyQuestionBank myQuestionBank= PageFactory.initElements(driver, MyQuestionBank.class);
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(dataIndex));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(dataIndex));
            new Assignment().create(14); // Create an assignment
            new LoginUsingLTI().ltiLogin("14_1"); //Login as student
            new LoginUsingLTI().ltiLogin("14_2"); //Login as student
            new LoginUsingLTI().ltiLogin("14_3"); //Login as student
            new LoginUsingLTI().ltiLogin("14"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");
            new Navigator().navigateToTab("Question Banks");
            myQuestionBank.customAssignmentButton_list.get(1).click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("14");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click(); //click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my que
            new AssignLesson().Assigncustomeassignemnt(14);
            new UIElement().waitAndFindElement(By.xpath("//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath("//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            driver.findElement(By.linkText("Gradebook")).click();
            Thread.sleep(1000);
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
                    Assert.fail("Grades Not Released text is not present");
                }
                count++;
            }
            if(driver.getPageSource().contains("Not Graded"))
            {
                Assert.fail("Not Graded text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("14");
            String [] nextLine;
            int pos=0;
            while((nextLine=path.readNext())!=null)
            {
                if(pos!=0) {
                    if (nextLine.length <= 6) {
                        break;
                    } else {
                        String assignmentStatus = nextLine[6];
                        System.out.println("assignmentStatus :  " + assignmentStatus);
                        Assert.assertEquals(assignmentStatus.trim(),"Grades Not Released","Grades Not Released is not present");
                    }
                }
                pos++;
            }
        }
        catch (Exception e)
        {
            Assert.fail("customAssignmentGradesNotReleased fail in LSAssignmentGradesNotReleased"+e);
        }

    }
    @Test(priority = 2,enabled = true)
    public void customAssignmentAssignedToSpecificStudent()
    {
        try
        {
            MyQuestionBank myQuestionBank= PageFactory.initElements(driver, MyQuestionBank.class);
            int dataIndex = 15;
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(dataIndex));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(dataIndex));
            new Assignment().create(15); // Create an assignment
            new LoginUsingLTI().ltiLogin("15_1"); //Login as student
            new LoginUsingLTI().ltiLogin("15_2"); //Login as student
            new LoginUsingLTI().ltiLogin("15_3"); //Login as student
            new LoginUsingLTI().ltiLogin("15"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");
            new Navigator().navigateToTab("Question Banks");
            myQuestionBank.customAssignmentButton_list.get(1).click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("15");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click(); //click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(15);
            new UIElement().waitAndFindElement(By.xpath("//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath("//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            driver.findElement(By.linkText("Gradebook")).click();
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
            CSVReader path = new ReadFile().readCSVFilePath("15");
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
            Assert.fail("customAssignmentAssignedToSpecificStudent fail in LSAssignmentGradesNotReleased" +e);
        }
    }
    @Test(priority = 3,enabled = true)
    public void staticAssessmentGradesNotReleased()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("16_1");
            new LoginUsingLTI().ltiLogin("16_2");
            new LoginUsingLTI().ltiLogin("16_3");
            int dataIndex = 16;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Assignment().create(16);
            new LoginUsingLTI().ltiLogin("16");
            new UIElement().waitAndFindElement(By.xpath("//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath("//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            driver.findElement(By.linkText("e-Textbook")).click();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className("chapter-heading-label")));
            List<WebElement> chapterSelect = driver.findElements(By.className("chapter-heading-label"));
            chapterSelect.get(0).click();
            Thread.sleep(2000);
            List<WebElement> staticAssessments = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
            int count = 0;
            for(WebElement ele : staticAssessments)
            {
                if(ele.getAttribute("title").trim().equals(assessmentName))
                {
                    List<WebElement> assignLink = driver.findElements(By.xpath("//*[@class='assessment-card']/li/div/div[@class='ls-inner-arw']"));
                    assignLink.get(count+count).click();
                    Thread.sleep(1000);
                    List<WebElement> assignThis = driver.findElements(By.xpath("//*[@title='Assign This']"));
                    System.out.println("assignThis:"+assignThis);
                    assignThis.get(count).click();
                    Thread.sleep(1000);
                }
                count++;
            }
            Thread.sleep(1000);
            new AssignLesson().assignTOCWithDefaultClassSection(16);
            new UIElement().waitAndFindElement(By.xpath("//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath("//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            driver.findElement(By.linkText("Gradebook")).click();
            Thread.sleep(1000);
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
            int found =0;
            for(WebElement ele:gradesNotReleasedText)
            {
                if(!ele.getText().trim().equals("Grades Not Released"))
                {
                    Assert.fail("Grades Not Released text is not present");
                }
                found++;
            }
            if(driver.getPageSource().contains("Not Graded"))
            {
                Assert.fail("Not Graded text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("16");
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
            Assert.fail("staticAssessmentGradesNotReleased fail in LSAssignmentGradesNotReleased" +e);
        }
    }
    @Test(priority = 4,enabled = true)
    public void staticAssessmentAssignedToSpecificStudent()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("17_1");
            new LoginUsingLTI().ltiLogin("17_2");
            new LoginUsingLTI().ltiLogin("17_3");
            int dataIndex = 17;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            System.out.println("assessmentName: "+assessmentName);
            new Assignment().create(17);
            new LoginUsingLTI().ltiLogin("17");
            new UIElement().waitAndFindElement(By.xpath("//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath("//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            driver.findElement(By.linkText("e-Textbook")).click();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className("chapter-heading-label")));
            List<WebElement> chapterSelect = driver.findElements(By.className("chapter-heading-label"));
            chapterSelect.get(0).click();
            Thread.sleep(2000);
            List<WebElement> staticAssessments = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
            int count = 0;
            for(WebElement ele : staticAssessments)
            {
                if(ele.getAttribute("title").trim().equals(assessmentName))
                {
                    List<WebElement> assignLink = driver.findElements(By.xpath("//*[@class='assessment-card']/li/div/div[@class='ls-inner-arw']"));
                    assignLink.get(count+count).click();
                    Thread.sleep(1000);
                    List<WebElement> assignThis = driver.findElements(By.xpath("//*[@title='Assign This']"));
                    System.out.println("assignThis:"+assignThis);
                    assignThis.get(count).click();
                    Thread.sleep(1000);
                }
                count++;
            }
            Thread.sleep(1000);
            new AssignLesson().assignTOCToSpecificStudent(17);
            new UIElement().waitAndFindElement(By.xpath("//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath("//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            driver.findElement(By.linkText("Gradebook")).click();
            Thread.sleep(1000);
            int studentCount = 3;
            int noOfStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student")).size();
            System.out.println("noOfStudents:"+noOfStudents);
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
            List<WebElement> studentGrade = driver.findElements(By.xpath("//*[@class='student-assignment-score-cell']/span"));
            if(!(studentGrade.get(0).getText().trim().equals("Grades Not Released") && studentGrade.get(1).getText().trim().equals("Not Assigned") && studentGrade.get(2).getText().trim().equals("Not Assigned")))
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
            CSVReader path = new ReadFile().readCSVFilePath("17");
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
            Assert.fail("staticAssessmentAssignedToSpecificStudent fail in LSAssignmentGradesNotReleased",e);
        }
    }
    @Test(priority = 5,enabled = true)
    public void questionBankGradesNotReleased()
    {
        try
        {
            int dataIndex = 20;
            new Assignment().create(20); // Create an assignment
            new LoginUsingLTI().ltiLogin("20_1"); //Login as student
            new LoginUsingLTI().ltiLogin("20_2"); //Login as student
            new LoginUsingLTI().ltiLogin("20_3"); //Login as student
            new LoginUsingLTI().ltiLogin("20"); //Login as instructor
            new Assignment().assignToStudent(20);
            new UIElement().waitAndFindElement(By.xpath("//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath("//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            driver.findElement(By.linkText("Gradebook")).click();
            Thread.sleep(1000);
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
                    Assert.fail("Grades Not Released text is not present");
                }
                count++;
            }
            if(driver.getPageSource().contains("Not Graded"))
            {
                Assert.fail("Not Graded text is present on Gradebook page");
            }
            Thread.sleep(1000);
            new ExportToCSVGradebookPage().exportCSVFile();
            Thread.sleep(4000);
            CSVReader path = new ReadFile().readCSVFilePath("20");
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
            Assert.fail("customAssignmentGradesNotReleased fail",e);
        }
    }
    @Test(priority = 6,enabled = true)
    public void questionBankAssignedToSpecificStudent()
    {
        try
        {
            new Assignment().create(21); // Create an assignment
            new LoginUsingLTI().ltiLogin("21_1"); //Login as student
            new LoginUsingLTI().ltiLogin("21_2"); //Login as student
            new LoginUsingLTI().ltiLogin("21_3"); //Login as student
            new LoginUsingLTI().ltiLogin("21"); //Login as instructor
            new Assignment().assignToStudent(21);
            new UIElement().waitAndFindElement(By.xpath("//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath("//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            driver.findElement(By.linkText("Gradebook")).click();
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
            CSVReader path = new ReadFile().readCSVFilePath("21");
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
            Assert.fail("customAssignmentGradesNotReleased fail"+e);
        }
    }
}
