package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R231;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentPolicy;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Lesson;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.LsLms.BlackBoard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.LsLms.Canvas;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.LsLms.ErrorScreen;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Resources.AllResources;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by rashmi on 04-02-2016.
 */
public class CanvasContentDiscovery extends Driver{
    Canvas canvas;
    LessonPage lessonpage;
    Dashboard dashboard;
    Gradebook gradebook;
    CourseStreamPage courseStreamPage;
    ProficiencyReport proficiencyReport;
    ErrorScreen errorScreen;
    BlackBoard blackBoard;
    CurrentAssignments currentAssignments;
    AllResources allResources;

    @BeforeMethod
    public void pageFactory(){
        WebDriver driver=Driver.getWebDriver();
        canvas= PageFactory.initElements(driver,Canvas.class);
        lessonpage=PageFactory.initElements(driver,LessonPage.class);
        dashboard= PageFactory.initElements(driver,Dashboard.class);
        gradebook=PageFactory.initElements(driver, Gradebook.class);
        courseStreamPage=PageFactory.initElements(driver,CourseStreamPage.class);
        proficiencyReport=PageFactory.initElements(driver,ProficiencyReport.class);
        errorScreen=PageFactory.initElements(driver,ErrorScreen.class);
        blackBoard=PageFactory.initElements(driver,BlackBoard.class);
        currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
        allResources=PageFactory.initElements(driver,AllResources.class);


    }
    @Test(priority = 1,enabled = true)
    public void PreviewForToolsTab(){
        try{


            WebDriver driver=Driver.getWebDriver();
            new LoginUsingLTI().ltiLogin("37");// login as user instructor with mandate fields for Content Discovery screen
            driver.switchTo().frame(driver.findElement(By.id("ls-lti-iframe")));// switching to iframe
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();

            Assert.assertEquals(canvas.ToolsLink.isDisplayed(), true, "Content discovery screen not loaded");//To verify whether content discoveryscreen is displayed
            Assert.assertEquals(canvas.AssignmentsLink.isDisplayed(), true, "Content discovery screen not loaded");//To verify whether content discoveryscreen is displayed
            Assert.assertEquals(canvas.ReadingsLink.isDisplayed(), true, "Content discovery screen not loaded");//To verify whether content discoveryscreen is displayed
            Assert.assertEquals(canvas.InstructorTools.isDisplayed(), true, "Content discovery screen not loaded");//To verify whether content discoveryscreen is displayed

            new MouseHover().mouserhoverbywebelement(canvas.HomepageTile);//Hover on Homepage tile
            Assert.assertEquals(canvas.HomepageTile.getText(), "WileyPLUS" + "\n" +
                    "Learning Space" + "\n" +
                    "Homepage", "Homepage tile text is not valid");// Verification of Tile text.
            //verification of the description
            Assert.assertEquals(canvas.DescHomepageTile.getText(), "View the Learning Space dashboard for your WileyPLUS Learning Space Class Section.", "Description not matching");
            Assert.assertEquals(canvas.AddButtonAnyTile.isDisplayed(),true,"Add button not displayed");// verification whether add button is displayed
            new MouseHover().mouserhoverbywebelement(canvas.ToolsLink);// To hover elsewhere

            new MouseHover().mouserhoverbywebelement(canvas.eTextbookTile);//Hover on eTextbook tile
            Assert.assertEquals(canvas.eTextbookTile.getText(), "WileyPLUS" + "\n" +
                    "Learning Space" + "\n" +
                    "e-Textbook", "e-Textbook tile text is not valid");// Verification of Tile text.
            //verification of the description
            Assert.assertEquals(canvas.DesceTextbookTile.getText(), "View the digital textbook in your WileyPLUS class section.", "Description not matching");
            Assert.assertEquals(canvas.AddButtonAnyTile.isDisplayed(),true,"Add button not displayed");// verification whether add button is displayed

            new MouseHover().mouserhoverbywebelement(canvas.ToolsLink);// To hover elsewhere

            new MouseHover().mouserhoverbywebelement(canvas.GradebookTile.get(0));//Hover on gradebook tile
            Assert.assertEquals(canvas.GradebookTile.get(0).getText(), "WileyPLUS" + "\n" +
                    "Learning Space" + "\n" +
                    "Gradebook", "Gradebook tile text is not valid");// Verification of Tile text.
            //verification of the description
            Assert.assertEquals(canvas.DescGradeBook.getText(), "View all scores and attempt details for assignments in your WileyPLUS class section.", "Description not matching");
            Assert.assertEquals(canvas.AddButtonAnyTile.isDisplayed(),true,"Add button not displayed");// verification whether add button is displayed

            new MouseHover().mouserhoverbywebelement(canvas.ToolsLink);// To hover elsewhere

            new MouseHover().mouserhoverbywebelement(canvas.CourseStreamTile.get(1));//Hover on CourseStreamTile tile
            Assert.assertEquals(canvas.CourseStreamTile.get(1).getText(), "WileyPLUS" + "\n" +
                    "Learning Space" + "\n" +
                    "Course Stream", "Course Stream tile text is not valid");// Verification of Tile text.
            //verification of the description
            Assert.assertEquals(canvas.DescCourseStream.getText(),"View all the social collaboration available in your WileyPLUS class section.","Description not matching");
            Assert.assertEquals(canvas.AddButtonAnyTile.isDisplayed(),true,"Add button not displayed");// verification whether add button is displayed

            new MouseHover().mouserhoverbywebelement(canvas.ToolsLink);// To hover elsewhere

            new MouseHover().mouserhoverbywebelement(canvas.ReportsTile);//Hover on Reports tile
            Assert.assertEquals(canvas.ReportsTile.getText(), "WileyPLUS" + "\n" +
                    "Learning Space" + "\n" +
                    "Reports", "Reports tile text is not valid");// Verification of Tile text.
            //verification of the description
            Assert.assertEquals(canvas.DescReports.getText(),"View all the reports available in your WileyPLUS class section.","Description not matching");
            Assert.assertEquals(canvas.AddButtonAnyTile.isDisplayed(),true,"Add button not displayed");// verification whether add button is displayed

            new MouseHover().mouserhoverbywebelement(canvas.ToolsLink);// To hover elsewhere

            new MouseHover().mouserhoverbywebelement(canvas.GradeRefresh);//Hover on Grade Refresh tile
            Assert.assertEquals(canvas.GradeRefresh.getText(), "WileyPLUS" + "\n" +
                    "Learning Space" + "\n" +
                    "Grade Refresh", "Grade Refresh tile text is not valid");// Verification of Tile text.
            //verification of the description
            Assert.assertEquals(canvas.DescGradeRefresh.getText(),"View all scores and attempt details for assignments in your WileyPLUS class section.The same would be commu...","Description not matching");
            Assert.assertEquals(canvas.AddButtonAnyTile.isDisplayed(),true,"Add button not displayed");// verification whether add button is displayed

            new MouseHover().mouserhoverbywebelement(canvas.ToolsLink);// To hover elsewhere

            new MouseHover().mouserhoverbywebelement(canvas.NotesTile);//Hover on NotesTile tile
            Assert.assertEquals(canvas.NotesTile.getText(), "WileyPLUS" + "\n" +
                    "Learning Space" + "\n" +
                    "Notes", "Notes tile text is not valid");// Verification of Tile text.
            //verification of the description
            Assert.assertEquals(canvas.DescNotes.getText(),"View the notes area in your WileyPLUS class section for students only.","Description not matching");
            Assert.assertEquals(canvas.AddButtonAnyTile.isDisplayed(),true,"Add button not displayed");// verification whether add button is displayed


            new MouseHover().mouserhoverbywebelement(canvas.ToolsLink);// To hover elsewhere
            new MouseHover().mouserhoverbywebelement(canvas.HomepageTile);//Hover on Homepage tile
            canvas.HomepagePreview.click();// click on preview button of Homepage tile
            String parentWindow=driver.getWindowHandle();
            for(String handle : driver.getWindowHandles()) {

                driver.switchTo().window(handle);
            }

            Assert.assertEquals(dashboard.getNewAssignmentButton().isDisplayed(), true, "Homepage preview failed");//verification of the homepage preview from tile


            driver.switchTo().window(parentWindow);//switching back to content discovery screen
            driver.switchTo().frame(driver.findElement(By.id("ls-lti-iframe")));// switching to iframe
            new MouseHover().mouserhoverbywebelement(canvas.ToolsLink);// To hover elsewhere
            new MouseHover().mouserhoverbywebelement(canvas.eTextbookTile);//Hover on eTextbook tile
            canvas.HomepagePreview.click();// click on preview button of eTextbook tile
            for(String handle : driver.getWindowHandles()) {

                driver.switchTo().window(handle);
            }
            Assert.assertEquals(lessonpage.searchETextBook.isDisplayed(), true, "e-Textbook preview failed");//verification of the eTextbook preview from tile


            driver.switchTo().window(parentWindow);//switching back to content discovery screen
            driver.switchTo().frame(driver.findElement(By.id("ls-lti-iframe")));// switching to iframe
            new MouseHover().mouserhoverbywebelement(canvas.ToolsLink);// To hover elsewhere
            new MouseHover().mouserhoverbywebelement(canvas.GradebookTile.get(0));//Hover on gradebook tile
            canvas.HomepagePreview.click();// click on preview button of gradebook tile
            for(String handle : driver.getWindowHandles()) {

                driver.switchTo().window(handle);
            }
            Assert.assertEquals(gradebook.getGradebookWeighting().isDisplayed(),true,"gradebook preview failed");//verification of the gradebook preview from tile


            driver.switchTo().window(parentWindow);//switching back to content discovery screen
            driver.switchTo().frame(driver.findElement(By.id("ls-lti-iframe")));// switching to iframe
            new MouseHover().mouserhoverbywebelement(canvas.ToolsLink);// To hover elsewhere
            new MouseHover().mouserhoverbywebelement(canvas.CourseStreamTile.get(1));//Hover on course stream tile
            canvas.HomepagePreview.click();// click on preview button of coursestream tile
            for(String handle : driver.getWindowHandles()) {

                driver.switchTo().window(handle);
            }
            Assert.assertEquals(courseStreamPage.postTextBox.isDisplayed(),true,"course stream preview failed");//verification of the course stream preview from tile



            driver.switchTo().window(parentWindow);//switching back to content discovery screen
            driver.switchTo().frame(driver.findElement(By.id("ls-lti-iframe")));// switching to iframe
            new MouseHover().mouserhoverbywebelement(canvas.ToolsLink);// To hover elsewhere
            new MouseHover().mouserhoverbywebelement(canvas.ReportsTile);//Hover on reports tile
            canvas.HomepagePreview.click();// click on preview button of reports tile
            for(String handle : driver.getWindowHandles()) {

                driver.switchTo().window(handle);
            }
            Assert.assertEquals(proficiencyReport.getProficiencyReportTitle().isDisplayed(),true,"course stream preview failed");//verification of the course stream preview from tile


            driver.switchTo().window(parentWindow);//switching back to content discovery screen
            driver.switchTo().frame(driver.findElement(By.id("ls-lti-iframe")));// switching to iframe
            new MouseHover().mouserhoverbywebelement(canvas.ToolsLink);// To hover elsewhere
            new MouseHover().mouserhoverbywebelement(canvas.GradeRefresh);//Hover on graderefresh tile
            canvas.HomepagePreview.click();// click on preview button of graderefresh tile
            for(String handle : driver.getWindowHandles()) {

                driver.switchTo().window(handle);
            }
            Assert.assertEquals(gradebook.getGradebookWeighting().isDisplayed(),true,"graderefresh preview failed");//verification of the graderefresh preview from tile



            driver.switchTo().window(parentWindow);//switching back to content discovery screen
            driver.switchTo().frame(driver.findElement(By.id("ls-lti-iframe")));// switching to iframe
            new MouseHover().mouserhoverbywebelement(canvas.ToolsLink);// To hover elsewhere
            new MouseHover().mouserhoverbywebelement(canvas.NotesTile);//Hover on notes tile
            canvas.HomepagePreview.click();// click on preview button of Notes tile
            for(String handle : driver.getWindowHandles()) {

                driver.switchTo().window(handle);
            }
            Assert.assertEquals(errorScreen.myNotesInstructorErrorScreen().isDisplayed(),true,"Notes preview failed");//verification of the notes preview from tile





        }catch (Exception e){
            Assert.fail("Exception in TC PreviewForToolsTab of class CanvasContentDiscovery", e);
        }

    }

    @Test(priority = 2,enabled = true)
    public void PreviewForAssignmentsTab(){
        try{
            WebDriver driver=Driver.getWebDriver();
            String parentWindow=driver.getWindowHandle();// store main window in parentWindow object
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "37");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "37");
            //new Assignment().create(37);
            new LoginUsingLTI().ltiLogin("37");// login as user instructor with mandate fields for Content Discovery screen
            driver.switchTo().frame(driver.findElement(By.id("ls-lti-iframe")));// switching to iframe
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();

            canvas.AssignmentsLink.click();//Click on Assignments tab
           /*canvas.AssignWPLSButton.click();// Click on Assign WPLS button
            for(String handle : driver.getWindowHandles()) {

                driver.switchTo().window(handle);// switch to assign a WPLS assignment window
            }
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();

            blackBoard.getUsePrecreatedAssignmentbutton().click();// Click on 'Use pre-created assignment button'
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release explicitly by the instructor", "", "", "");//till save policy

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(37);//assign to student
            driver.switchTo().window(parentWindow);//switching back to content discovery screen
            driver.switchTo().frame(driver.findElement(By.id("ls-lti-iframe")));// switching to iframe*/
            canvas.RefreshAssignmentsButton.click();// click on refresh assignments button in assignments tab
            new MouseHover().mouserhoverbywebelement(canvas.AssignmentTile.get(0));//Hover on assignment tile
            Assert.assertEquals(canvas.AssignmentDescription.getText(), "View this assignment in your WileyPLUS class section.", "text does not match");
            Assert.assertEquals(canvas.AssignmentTitleText.getText(),assignmentname,"Assignment name is not displayed");//verification of assignment name
            canvas.HomepagePreview.click();// click on preview button of Assigned assignment tile
            for(String handle : driver.getWindowHandles()) {

                driver.switchTo().window(handle);
            }
            Assert.assertEquals(canvas.AssignmentPreviewScreen.isDisplayed(), true, "Assignment preview failed");//verification of the assignment preview from tile
            driver.switchTo().window(parentWindow);//switching back to content discovery screen
            driver.switchTo().frame(driver.findElement(By.id("ls-lti-iframe")));// switching to iframe
            canvas.ReadingsLink.click();// Click on readings link
            new MouseHover().mouserhoverbywebelement(canvas.ChapterTile.get(0));//Hover on chapter tile
            Assert.assertEquals(canvas.ChapterDescription.getText(), "Full digital textbook and helpful media resources.", "Chapter tile's description not correct");
            canvas.HomepagePreview.click();// click on preview button of first chapter tile
            for(String handle : driver.getWindowHandles()) {

                driver.switchTo().window(handle);
            }
            Assert.assertEquals(lessonpage.assign_lesson_icon.isDisplayed(), true, "Chapter preview failed");//verification of the chapter preview from tile
            driver.switchTo().window(parentWindow);//switching back to content discovery screen
            driver.switchTo().frame(driver.findElement(By.id("ls-lti-iframe")));// switching to iframe

            canvas.InstructorTools.click();// click on Instructor tools link
            new MouseHover().mouserhoverbywebelement(canvas.ChapterTile.get(1));//Hover on assignment tile
            Assert.assertEquals(canvas.CurrentAssignmentsTileText.getText(), "WileyPLUS" + "\n"+
                    "Learning Space" + "\n" +
                    "Assignments");
            canvas.HomepagePreview.click();// click on preview button of first chapter tile
            for(String handle : driver.getWindowHandles()) {

                driver.switchTo().window(handle);
            }
            System.out.println("adsfghj");
            Assert.assertEquals(currentAssignments.newAssignment_Button.isDisplayed(), true, "Assignments preview failed");//verification of the Current assignments preview from tile
            driver.switchTo().window(parentWindow);//switching back to content discovery screen
            driver.switchTo().frame(driver.findElement(By.id("ls-lti-iframe")));// switching to iframe
            new MouseHover().mouserhoverbywebelement(canvas.ToolsLink);// To hover elsewhere
            new MouseHover().mouserhoverbywebelement(canvas.ChapterTile.get(2));//Hover on assignment tile
            Assert.assertEquals(canvas.ResourcesTileText.getText(), "WileyPLUS" + "\n" +
                    "Learning Space" + "\n" +
                    "Instructor Resources");
            canvas.HomepagePreview.click();// click on preview button of first chapter tile
            for(String handle : driver.getWindowHandles()) {

                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);
            Assert.assertEquals(allResources.getAllResources().isDisplayed(), true, "resources preview failed");//verification of the resources preview from tile

           }catch (Exception e){
            Assert.fail("Exception in TC PreviewForAssignmentsTab of class CanvasContentDiscovery", e);
        }

    }
}
