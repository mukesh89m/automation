package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT22.R227;

import au.com.bytecode.opencsv.CSVReader;
import com.google.common.base.Function;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ReadFile;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by durgapathi on 7/7/2015.
 */
public class AssignmentGradesNotReleased extends Driver{

    @Test(priority = 1,enabled = true)
    public void customAssignmentGradesNotReleased()
    {
        try
        {
            int dataIndex = 10;
            MyQuestionBank myQuestionBank= PageFactory.initElements(driver, MyQuestionBank.class);
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(dataIndex));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(dataIndex));
            new Assignment().create(10); // Create an assignment
            new LoginUsingLTI().ltiLogin("10_1"); //Login as student
            new LoginUsingLTI().ltiLogin("10_2"); //Login as student
            new LoginUsingLTI().ltiLogin("10_3"); //Login as student
            new LoginUsingLTI().ltiLogin("10"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");
            new Navigator().navigateToTab("Question Banks");
            myQuestionBank.customAssignmentButton_list.get(1).click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("10");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click(); //click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my que
            new AssignLesson().Assigncustomeassignemnt(10);
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
            CSVReader path = new ReadFile().readCSVFilePath("10");
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
    @Test(priority = 2,enabled = true)
    public void customAssignmentAssignedToSpecificStudent()
    {
        try
        {
            MyQuestionBank myQuestionBank= PageFactory.initElements(driver, MyQuestionBank.class);
            int dataIndex = 11;
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(dataIndex));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(dataIndex));
            new Assignment().create(11); // Create an assignment
            new LoginUsingLTI().ltiLogin("11_1"); //Login as student
            new LoginUsingLTI().ltiLogin("11_2"); //Login as student
            new LoginUsingLTI().ltiLogin("11_3"); //Login as student
            new LoginUsingLTI().ltiLogin("11"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");
            new Navigator().navigateToTab("Question Banks");
            myQuestionBank.customAssignmentButton_list.get(1).click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("10");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click(); //click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(11);
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
            int noOfAssignments = driver.findElements(By.xpath(".//div[@class='gradebook-assignment-header-cell']")).size();
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
            CSVReader path = new ReadFile().readCSVFilePath("11");
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
    @Test(priority = 3, enabled = true)
    public void staticAssessmentGradesNotReleased(){
        try
        {
            new LoginUsingLTI().ltiLogin("2");
            new LoginUsingLTI().ltiLogin("3");
            new LoginUsingLTI().ltiLogin("4");
            int dataIndex = 1;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            System.out.println("assessmentName: "+assessmentName);
            new Assignment().create(1);
            new LoginUsingLTI().ltiLogin("1");
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
            new AssignLesson().assignTOCWithDefaultClassSection(1);
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
            CSVReader path = new ReadFile().readCSVFilePath("1");
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
            Assert.fail("assignmentGradesNotReleased fail" + e);
        }
    }
    @Test(priority = 4,enabled = true)
    public void staticAssessmentAssignedToSpecificStudent(){
        try
        {
            new LoginUsingLTI().ltiLogin("7");
            new LoginUsingLTI().ltiLogin("8");
            new LoginUsingLTI().ltiLogin("9");
            int dataIndex = 6;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            System.out.println("assessmentName: "+assessmentName);
            new Assignment().create(6);
            new LoginUsingLTI().ltiLogin("6");
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
            new AssignLesson().assignTOCToSpecificStudent(6);
            new UIElement().waitAndFindElement(By.xpath(".//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
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
                CSVReader path = new ReadFile().readCSVFilePath("6");
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
            Assert.fail("assignmentAssignedToSpecificStudent fail" + e);
        }
    }
    @Test(priority = 5,enabled = true)
    public void adaptivePracticeGradesNotReleased()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("12_1");
            new LoginUsingLTI().ltiLogin("12_2");
            new LoginUsingLTI().ltiLogin("12_3");
            int dataIndex = 12;
            new LoginUsingLTI().ltiLogin("12");
            new UIElement().waitAndFindElement(By.xpath("//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath("//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            driver.findElement(By.linkText("e-Textbook")).click();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            List<WebElement> chapterSelect = driver.findElements(By.className("chapter-heading-label"));
            chapterSelect.get(0).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("span[title='ORION Adaptive Practice']")).click();
            Thread.sleep(1000);
            List<WebElement> rightArrowButtons =  driver.findElements(By.className("ls-inner-arw"));
            int pos =0;
            for(int a=0;a<rightArrowButtons.size();a++){
                if(rightArrowButtons.get(a).isDisplayed()){
                    pos++;
                    new Click().clickByElement(rightArrowButtons.get(a));
                    if(pos==2) {
                        break;
                    }
                }
            }
            Thread.sleep(1000);
            new Click().clickByCSSSelectorsIfDisplayed("div[class = 'toc-assign-this open-assign-window']");
            Thread.sleep(1000);
            new AssignLesson().assignTOCWithDefaultClassSection(12);
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
            CSVReader path = new ReadFile().readCSVFilePath("12");
            String [] nextLine;
            pos=0;
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
    @Test(priority = 6, enabled = true)
    public void adaptivePracticeAssignedToSpecificStudent()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("13_1");
            new LoginUsingLTI().ltiLogin("13_2");
            new LoginUsingLTI().ltiLogin("13_3");
            int dataIndex = 13;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new LoginUsingLTI().ltiLogin("13");
            new UIElement().waitAndFindElement(By.xpath("//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath("//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            driver.findElement(By.linkText("e-Textbook")).click();
            List<WebElement> chapterSelect = driver.findElements(By.className("chapter-heading-label"));
            chapterSelect.get(0).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("span[title='ORION Adaptive Practice']")).click();
            Thread.sleep(1000);
            List<WebElement> rightArrowButtons =  driver.findElements(By.className("ls-inner-arw"));
            int pos =0;
            for(int a=0;a<rightArrowButtons.size();a++){
                if(rightArrowButtons.get(a).isDisplayed()){
                    pos++;
                    new Click().clickByElement(rightArrowButtons.get(a));
                    if(pos==2) {
                        break;
                    }
                }
            }
            Thread.sleep(1000);
            new Click().clickByCSSSelectorsIfDisplayed("div[class = 'toc-assign-this open-assign-window']");
            Thread.sleep(1000);
            new AssignLesson().assignTOCToSpecificStudent(13);
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
            CSVReader path = new ReadFile().readCSVFilePath("13");
            String [] nextLine;
            pos=0;
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
    @Test(priority = 7,enabled = true)
    public void questionBankGradesNotReleased()
    {
        try
        {
            new Assignment().create(18); // Create an assignment
            new LoginUsingLTI().ltiLogin("18_1"); //Login as student
            new LoginUsingLTI().ltiLogin("18_2"); //Login as student
            new LoginUsingLTI().ltiLogin("18_3"); //Login as student
            new LoginUsingLTI().ltiLogin("18"); //Login as instructor
            new Assignment().assignToStudent(18);
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
            CSVReader path = new ReadFile().readCSVFilePath("18");
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
    @Test(priority = 8,enabled = true)
    public void questionBankAssignedToSpecificStudent()
    {
        try
        {
            new Assignment().create(19); // Create an assignment
            new LoginUsingLTI().ltiLogin("19_1"); //Login as student
            new LoginUsingLTI().ltiLogin("19_2"); //Login as student
            new LoginUsingLTI().ltiLogin("19_3"); //Login as student
            new LoginUsingLTI().ltiLogin("19"); //Login as instructor
            new Assignment().assignToStudent(19);
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
            CSVReader path = new ReadFile().readCSVFilePath("19");
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
