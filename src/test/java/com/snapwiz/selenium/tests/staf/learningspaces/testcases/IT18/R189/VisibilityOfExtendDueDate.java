package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R189;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by sumit on 31/12/14.
 */
public class VisibilityOfExtendDueDate extends Driver{

    @Test(priority = 1, enabled = true)
     public void visibilityOfExtendDueDateForInstructor()
    {
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);


            String newduedate = ReadTestData.readDataByTagName("", "newduedate", "233");
            String duedate = ReadTestData.readDataByTagName("", "duedate", "233");

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "233");
            String extendedDate;
            String originalDueDate;
            String toolTip;

            new Assignment().create(233);

            new LoginUsingLTI().ltiLogin("233_1");		//login as student1
            new LoginUsingLTI().ltiLogin("233_2");      //login as student2
            new LoginUsingLTI().ltiLogin("233");		//login a instructor
            new Assignment().assignToStudent(233);      //assign non gradable question assignment with class

            new LoginUsingLTI().ltiLogin("233_1");		//login as student1
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentname);//open the assignment
            new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            //TC row no. 233
            new LoginUsingLTI().ltiLogin("233");		//login a instructor
            new Assignment().extendDueDate("233");//extend due date
            extendedDate = currentAssignments.getExtendedDueDate().getText();
            System.out.println(extendedDate);
            if(!extendedDate.contains(newduedate)){
                Assert.fail("Newly extended due date is not reflected in Current Assignments page.");
            }
             originalDueDate = currentAssignments.getOriginalDueDate().getText();
            System.out.println(originalDueDate);
            if(!originalDueDate.contains(duedate)){
                Assert.fail("The original due date is no more reflected in Current Assignments page once the due date is extended.");
            }
            //TC row no. 234
            new LoginUsingLTI().ltiLogin("233_1");		//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            extendedDate = assignments.getExtendedDueDate().getText();
            System.out.println(extendedDate);
            if(!extendedDate.contains(newduedate)){
                Assert.fail("Newly extended due date is not reflected in Assignments page.");
            }
            originalDueDate = assignments.getOriginalDueDate().getText();
            System.out.println(originalDueDate);
            if(!originalDueDate.contains(duedate)){
                Assert.fail("The original due date is no more reflected in Assignments page once the due date is extended.");
            }

            //TC row no. 235
            new LoginUsingLTI().ltiLogin("233");		//login a instructor
            new Assignment().clickViewResponse(assessmentname);
            extendedDate = assignmentResponsesPage.getExtendedDueDate().get(0).getText();
            System.out.println(extendedDate);
            if(!extendedDate.contains(newduedate)){
                Assert.fail("Newly extended due date is not reflected in Assignments page.");
            }
            List<WebElement> l = assignmentResponsesPage.getOriginalDueDate();
            for(WebElement k: l){
                System.out.println("-->"+k.getText());
            }
            originalDueDate = assignmentResponsesPage.getOriginalDueDate().get(3).getText();
            System.out.println(originalDueDate);
            if(!originalDueDate.contains(duedate)){
                Assert.fail("The original due date is no more reflected in Assignments page once the due date is extended.");
            }

            //TC row no. 236
            boolean extendedDateFound = new BooleanValue().presenceOfElement(237, By.className("ls-extended-due-date-icon"));
            Assert.assertEquals(extendedDateFound, true, "extended due Date icon is Found in student response page.");

            //TC row no. 240, 241
            new LoginUsingLTI().ltiLogin("233_1");		//login as student1
            new Navigator().NavigateTo("Dashboard");  //navigate to Dashboard
            assignmentResponsesPage.getVisualIndicatorForDueDate().get(0).click();//click on visual indicator in class activity of dashboard
            toolTip = assignmentResponsesPage.getVisualIndicatorToolTip().getText();
            System.out.println("toolTip: "+toolTip);
            if(!toolTip.contains("Due date has been extended to")){
                Assert.fail("If due date is extended for whole class, a visual indicator is not displayed in student dashboard with tooltip as - \"Due date has been extended to <month> <date>, <Year> format\" .");
            }

            //TC row no. 240, 241
            new LoginUsingLTI().ltiLogin("233_2");      //login as student2
            new Navigator().NavigateTo("Dashboard");  //navigate to Dashboard
            assignmentResponsesPage.getVisualIndicatorForDueDate().get(0).click();//click on visual indicator in class activity of dashboard
            toolTip = assignmentResponsesPage.getVisualIndicatorToolTip().getText();
            System.out.println("toolTip: "+toolTip);
            if(!toolTip.contains("Due date has been extended to")){
                Assert.fail("If due date is extended for whole class, a visual indicator is not displayed in student dashboard with tooltip as - \"Due date has been extended to <month> <date>, <Year> format\" .");
            }

        }
        catch (Exception e){
            Assert.fail("Exception in class VisibilityOfExtendDueDate in method visibilityOfExtendDueDateForInstructor.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void extendDueDateForStudent()
    {
        try
        {
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);


            String newduedate = ReadTestData.readDataByTagName("", "newduedate", "237_2");
            String duedate = ReadTestData.readDataByTagName("", "duedate", "237");

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "237");
            String extendedDate;
            String originalDueDate;
            String toolTip;

             new Assignment().create(237);

            new LoginUsingLTI().ltiLogin("237_1");		//login as student1
            new LoginUsingLTI().ltiLogin("237_2");      //login as student2
            new LoginUsingLTI().ltiLogin("237");		//login a instructor
            new Assignment().assignToStudent(237);      //assign non gradable question assignment with class

            new LoginUsingLTI().ltiLogin("237_1");		//login as student1
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentname);//open the assignment

            new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title ")));
            Thread.sleep(9000);
            new LoginUsingLTI().ltiLogin("237");		//login a instructor
            new Assignment().extendDueDate("237_2"); //extend due date for student2

            //TC row no. 237
            new LoginUsingLTI().ltiLogin("237_2");		//login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            extendedDate = assignments.getExtendedDueDate().getText();
            System.out.println(extendedDate);
            if(!extendedDate.contains(newduedate)){
                Assert.fail("Newly extended due date is not reflected in Assignments page.");
            }
            originalDueDate = assignments.getOriginalDueDate().getText();
            System.out.println(originalDueDate);
            if(!originalDueDate.contains(duedate)){
                Assert.fail("The original due date is no more reflected in Assignments page once the due date is extended.");
            }

            new LoginUsingLTI().ltiLogin("237_1");		//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            boolean extendedDateFound = new BooleanValue().presenceOfElement(237, By.cssSelector("span[class='ls-assignment-due-date assignment-extended-due-date']"));
            Assert.assertEquals(extendedDateFound, false, "extended due Date is Found for students for whom the the due date was NOT extended.");

            //TC row no. 238
            new LoginUsingLTI().ltiLogin("237");		//login a instructor
            new Assignment().clickViewResponse(assessmentname);
            List<WebElement> l = assignmentResponsesPage.getVisualIndicatorForDueDate();
            for(WebElement k: l){
                System.out.println("-->"+k.isDisplayed());
            }
            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

            assignmentResponsesPage.getVisualIndicatorForDueDate().get(2).click();//click on visual indicator near student name
            toolTip = assignmentResponsesPage.getVisualIndicatorToolTip().getText();
            System.out.println("toolTip: "+toolTip);
            if(!toolTip.contains("Due date has been extended to")){
                Assert.fail("If due date is extended for individual students, a visual indicator is not against that student name with tooltip as - \"Due date has been extended to <month> <date>, <Year> format\" .");
            }
            html.sendKeys(Keys.chord(Keys.CONTROL, "0"));

            //TC row no. 239
            new Navigator().NavigateTo("Dashboard");  //navigate to Dashboard
            assignmentResponsesPage.getVisualIndicatorForDueDate().get(0).click();//click on visual indicator in class activity of dashboard
            toolTip = assignmentResponsesPage.getVisualIndicatorToolTip().getText();
            System.out.println("toolTip: "+toolTip);
            if(!toolTip.contains("Due date has been extended to")){
                Assert.fail("If due date is extended for individual students, a visual indicator is not displayed in instructor dashboard with tooltip as - \"Due date has been extended to <month> <date>, <Year> format\" .");
            }
            //TC row no. 242
            new LoginUsingLTI().ltiLogin("237_2");		//login as student2
            assignmentResponsesPage.getVisualIndicatorForDueDate().get(0).click();//click on visual indicator in class activity of dashboard
            toolTip = assignmentResponsesPage.getVisualIndicatorToolTip().getText();
            System.out.println("toolTip: "+toolTip);
            if(!toolTip.contains("Due date has been extended to")){
                Assert.fail("If due date is extended for individual students, a visual indicator is not displayed in student dashboard with tooltip as - \"Due date has been extended to <month> <date>, <Year> format\" .");
            }

            //TC row no. 243
            new LoginUsingLTI().ltiLogin("237_1");		//login as student1
            boolean visualIndicator = new BooleanValue().presenceOfElement(237, By.className("ls-extended-due-date-icon"));
            Assert.assertEquals(visualIndicator, false, "extended due Date icon is Found student dashboard for students for whom the the due date was NOT extended.");


        }
        catch (Exception e){
            Assert.fail("Exception in class VisibilityOfExtendDueDate in method extendDueDateForStudent.", e);
        }
    }
    @Test(priority = 3, enabled = true)
    public void visibilityOfExtendDueDateInAssignmentTab() {
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            String toolTip;

            new LoginUsingLTI().ltiLogin("245_1");		//login as student1
            new LoginUsingLTI().ltiLogin("245_2");      //login as student2
            new LoginUsingLTI().ltiLogin("245");		//login a instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TOCShow().tocHide();                    //hide the TOC
            new AssignLesson().assignLesson("245");//assign lesson to class

            new LoginUsingLTI().ltiLogin("245_1");    //login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getLessonAssignment().click();//open lesson assignment
            new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.id("sec-intro")));
            Thread.sleep(9000);
            //TC row no. 245
            new LoginUsingLTI().ltiLogin("245");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            new Assignment().extendDueDate("245");//extend due date
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments"); //navigate assignments tab
            toolTip = lessonPage.getExtendedDueDate().getText();//extended due date field in assignments tab of e-Textbook
            System.out.println("toolTip: "+toolTip);
            if(!toolTip.contains("Due date has been extended to")){
                Assert.fail("If due date is extended for lesson assignment, a visual indicator is not displayed in assignments tab of etextbook with text as - \"Due date has been extended to <month> <date>, <Year> format\" .");
            }

            //TC row no. 246
            new LoginUsingLTI().ltiLogin("245_2");      //login as student2
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments"); //navigate assignments tab
            toolTip = lessonPage.getExtendedDueDate().getText();//extended due date field in assignments tab of e-Textbook
            System.out.println("toolTip: "+toolTip);
            if(!toolTip.contains("Due date has been extended to")){
                Assert.fail("If due date is extended for lesson assignment, a visual indicator is not displayed in assignments tab of etextbook with text as - \"Due date has been extended to <month> <date>, <Year> format\" .");
            }
            //TC row no. 247
            new LoginUsingLTI().ltiLogin("245_1");      //login as student1
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments"); //navigate assignments tab
            Thread.sleep(2000);
            toolTip = lessonPage.getExtendedDueDate().getText();//extended due date field in assignments tab of e-Textbook
            System.out.println("toolTip: "+toolTip);
            if(!toolTip.contains("Due date has been extended to")){
                Assert.fail("If due date is extended for lesson assignment, a visual indicator is not displayed in assignments tab of etextbook with text as - \"Due date has been extended to <month> <date>, <Year> format\" .");
            }

        }
        catch (Exception e) {
            Assert.fail("Exception in class VisibilityOfExtendDueDate in method visibilityOfExtendDueDateInAssignmentTab.", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void visibilityOfExtendDueDateInAssignmentTabWhenExtendedForStudent() {
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            String toolTip;

            new LoginUsingLTI().ltiLogin("248_1");		//login as student1
            new LoginUsingLTI().ltiLogin("248_2");    //login as student2
            new LoginUsingLTI().ltiLogin("248");		//login a instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TOCShow().tocHide();                    //hide the TOC
            new AssignLesson().assignLesson("248");//assign lesson to class

            new LoginUsingLTI().ltiLogin("248_1");    //login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getLessonAssignment().click();//open lesson assignment
            new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.id("sec-intro")));
            Thread.sleep(9000);
            //TC row no. 248
            new LoginUsingLTI().ltiLogin("248");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            new Assignment().extendDueDate("248_2");//extend due date for student2
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TOCShow().tocHide(); //hide the TOC
            new Navigator().navigateToTab("Assignments"); //navigate assignments tab
            toolTip = lessonPage.getExtendedDueDate().getText();//extended due date field in assignments tab of e-Textbook
            System.out.println("toolTip: "+toolTip);
            if(!toolTip.contains("Due date has been extended to")){
                Assert.fail("If due date is extended for lesson assignment for a particular student, a visual indicator is not displayed in assignments tab of etextbook with text as - \"Due date has been extended to <month> <date>, <Year> format\" .");
            }

            //TC row no. 249
            new LoginUsingLTI().ltiLogin("248_2");      //login as student2
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TOCShow().tocHide(); //hide the TOC
            new Navigator().navigateToTab("Assignments"); //navigate assignments tab
            toolTip = lessonPage.getExtendedDueDate().getText();//extended due date field in assignments tab of e-Textbook
            System.out.println("toolTip: "+toolTip);
            if(!toolTip.contains("Due date has been extended to")){
                Assert.fail("If due date is extended for lesson assignment for a particular student, a visual indicator is not displayed in assignments tab of etextbook with text as - \"Due date has been extended to <month> <date>, <Year> format\" .");
            }
            //TC row no. 249
            new LoginUsingLTI().ltiLogin("248_1");      //login as student1
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments"); //navigate assignments tab
            Thread.sleep(2000);
            boolean elementFound = new BooleanValue().presenceOfElement(248, By.className("extended-due-date-title"));
            Assert.assertEquals(elementFound, false, "Visual indicator is found in Assignments tab for the stuent for whom the date was not extended.");

        }
        catch (Exception e) {
            Assert.fail("Exception in class VisibilityOfExtendDueDate in method visibilityOfExtendDueDateInAssignmentTabWhenExtendedForStudent.", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void visibilityOfExtendDueDateInCourseStream() {
        try
        {
            CourseStreamPage courseStream = PageFactory.initElements(driver, CourseStreamPage.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "250");

            String toolTip;
            new Assignment().create(250);
            new LoginUsingLTI().ltiLogin("250_1");		//login as student1
            new LoginUsingLTI().ltiLogin("250_2");    //login as student2

            new LoginUsingLTI().ltiLogin("250");		//login a instructor
            new Assignment().assignToStudent(250);      //assign non gradable question assignment with class

            new LoginUsingLTI().ltiLogin("250_2");    //login as student2
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentname);//open the assignment
            new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new LoginUsingLTI().ltiLogin("250");		//login a instructor
            new Assignment().extendDueDate("250");//extend due date for class

            //TC row no. 251
            new LoginUsingLTI().ltiLogin("250_1");		//login as student1
            new Navigator().NavigateTo("Course Stream");  //navigate to Course Stream
            toolTip = courseStream.getExtendedDueDate().getText();//extended due date field in CS
            System.out.println("toolTip: "+toolTip);
            if(!toolTip.contains("Due date has been extended to")){
                Assert.fail("If due date is extended for assignment for class, a visual indicator is not displayed in CS with text as - \"Due date has been extended to <month> <date>, <Year> format\" .");
            }
            //TC row no. 252
            new LoginUsingLTI().ltiLogin("250_2");		//login as student2
            new Navigator().NavigateTo("Course Stream");  //navigate to Course Stream
            toolTip = courseStream.getExtendedDueDate().getText();//extended due date field in CS
            System.out.println("toolTip: "+toolTip);
            if(!toolTip.contains("Due date has been extended to")){
                Assert.fail("If due date is extended for assignment for class, a visual indicator is not displayed in CS with text as - \"Due date has been extended to <month> <date>, <Year> format\" .");
            }
            new LoginUsingLTI().ltiLogin("250_1");    //login as student1
            new Assignment().submitAssignmentAsStudent(250);

            new LoginUsingLTI().ltiLogin("250_2");    //login as student1
            new Assignment().submitAssignmentAsStudent(250);

            //TC row no. 250
            new LoginUsingLTI().ltiLogin("250");		//login a instructor
            new Navigator().NavigateTo("Course Stream");  //navigate to Course Stream
            toolTip = courseStream.getExtendedDueDate().getText();//extended due date field in CS
            System.out.println("toolTip: "+toolTip);
            if(!toolTip.contains("Due date has been extended to")){
                Assert.fail("If due date is extended for assignment for class, a visual indicator is not displayed in CS with text as - \"Due date has been extended to <month> <date>, <Year> format\" .");
            }

        }
        catch (Exception e) {
            Assert.fail("Exception in class VisibilityOfExtendDueDate in method visibilityOfExtendDueDateInCourseStream.", e);
        }
    }
    @Test(priority = 6, enabled = true)
    public void visibilityOfExtendDueDateInCourseStreamWhenExtendedForStudent() {
        try
        {
            CourseStreamPage courseStream = PageFactory.initElements(driver, CourseStreamPage.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "253");

            String toolTip;
            new Assignment().create(253);
            new LoginUsingLTI().ltiLogin("253_1");		//login as student1
            new LoginUsingLTI().ltiLogin("253_2");    //login as student2

            new LoginUsingLTI().ltiLogin("253");		//login a instructor
            new Assignment().assignToStudent(253);      //assign non gradable question assignment with class

            new LoginUsingLTI().ltiLogin("253_1");    //login as student1
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentname);//open the assignment
            new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Thread.sleep(9000);
            new LoginUsingLTI().ltiLogin("253");		//login a instructor
            new Assignment().extendDueDate("253_2");//extend due date for student2

            //TC row no. 253
            new LoginUsingLTI().ltiLogin("253_2");		//login as student2
            new Navigator().NavigateTo("Course Stream");  //navigate to Course Stream
            toolTip = courseStream.getExtendedDueDate().getText();//extended due date field in CS
            System.out.println("toolTip: "+toolTip);
            if(!toolTip.contains("Due date has been extended to")){
                Assert.fail("If due date is extended for assignment for class, a visual indicator is not displayed in CS with text as - \"Due date has been extended to <month> <date>, <Year> format\" .");
            }
            //TC row no. 254
            new LoginUsingLTI().ltiLogin("253_1");		//login as student1
            new Navigator().NavigateTo("Course Stream");  //navigate to Course Stream
            boolean elementFound = new BooleanValue().presenceOfElement(253, By.className("course-stream-extended-due-date-wrapper"));
            Assert.assertEquals(elementFound, false, "Visual indicator is found in CS for the student for whom the due date was not extended.");

        }
        catch (Exception e) {
            Assert.fail("Exception in class VisibilityOfExtendDueDate in method visibilityOfExtendDueDateInCourseStreamWhenExtendedForStudent.", e);
        }
    }

}
