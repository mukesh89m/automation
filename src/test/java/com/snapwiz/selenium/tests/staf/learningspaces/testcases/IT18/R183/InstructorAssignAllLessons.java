package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R183;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.ClassSectionDropDown;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by Mukesh on 8/1/15.
 */
public class InstructorAssignAllLessons extends Driver {

    @Test(priority = 1, enabled = true)
    public void instructorAssignAllLessons()
    {
        try
        {
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            CourseStreamPage courseStreamPage=PageFactory.initElements(driver,CourseStreamPage.class);
            String context_title = ReadTestData.readDataByTagName("", "context_title", "9");


            new LoginUsingLTI().ltiLogin("9_1");		//login as student1
            new LoginUsingLTI().ltiLogin("9_2");    //login as student2*/

            new LoginUsingLTI().ltiLogin("9");		//login as instructor
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            //TC row no. 9
            String icon = lessonPage.getAllLessonAssign().getCssValue("background-image");
            System.out.println("icon: "+icon);
            if(!icon.contains("toc-assignment-cart.png")){
                Assert.fail("In TOC, \"bulk assign\" icon in the right top before \"close\" icon is not shown.");
            }
            //TC row no. 12
            new MouseHover().mouserhoverbywebelement(lessonPage.getAllLessonAssign());//mouse hover the all lesson icon
            String tooltip = lessonPage.getAllLessonAssign().getAttribute("title");
            System.out.println("tooltip: "+tooltip);
            Assert.assertEquals(tooltip, "Assign all lessons in this chapter", "Mouse hover on the \"bulk assign\" icon it doesn't show tooltip as \"Assign all lessons in this chapter\"");
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon
            //TC row no. 13
            Assert.assertEquals(lessonPage.getAllLessonNames().size(), 4, "All lessons related to that particular chapter is not shown.");
            //TC row no. 14
            Assert.assertEquals(lessonPage.getDeleteIcon().size(), 4, "No delete icon before all lessons while assigning all lessons.");

            //Tc row no 15 #3. In the pop_up header the name should be editable
            Thread.sleep(5000);
            lessonPage.chapterName_edit.click();//click on the edit link
            lessonPage.chapterName_textBox.clear();
            lessonPage.chapterName_textBox.sendKeys("Ch 1:The Study of Lifes");

            //Tc row no 16
            lessonPage.assign_button.click(); //click on the assign button
            Assert.assertEquals(lessonPage.assignTo_label.get(0).getText().trim(), "Assign To: *", "Assign To: field is not displayed");
            Assert.assertEquals(lessonPage.assignTo_label.get(2).getText().trim(), "Accessible After: *", "Accessible After: field is not displayed");
            Assert.assertEquals(lessonPage.assignTo_label.get(3).getText().trim(),"Due Date: *","Due Date: field is not displayed");
            Assert.assertEquals(lessonPage.assignTo_label.get(4).getText().trim(),"Description:","Description: field is not displayed");


            //Tc row no 17
            Assert.assertEquals(lessonPage.assignTo_content.get(0).getText().trim(),context_title,"Assign to field having no instructor");
            //Tc row no 18
            assignAssignmentFromLessonPage("9");

            new Navigator().NavigateTo("Current Assignments"); //navigate to the current assignment
            currentAssignments.allActivity_link.click();//click on the allActivity
            currentAssignments.learningActivity_link.click();//click on the learning activity
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.status.getText().trim(),"Available for Students","Status of class is not a Available for Students");

            //Tc row no 19
            List<WebElement> lsIcons=currentAssignments.lsIcon;
            for(WebElement eachAssessment:lsIcons) {
                if(!eachAssessment.getCssValue("background-image").contains("/ls/doctypes.png"))
                    Assert.fail("All Assessments is not appear with ls icon");
            }

            //Tc row no 20
            currentAssignments.lesson.get(0).click();//click on the lesson
            Thread.sleep(5000);
            new Navigator().navigateToTab("Assignments"); //navigate tp assignment tab
            Thread.sleep(5000);
            Assert.assertEquals(currentAssignments.classStatus_assignmentTab.getText().trim(),"Available for Students","Status of class is not a Available for Students");

           //Tc row no 21
            new Navigator().NavigateTo("Course Stream");//navigate to course stream
            String instructorName=courseStreamPage.instructorIcon.getText().trim();
            System.out.println("Instructor Icon with Message:"+instructorName);
            if(!instructorName.contains("Instructor posted an assignment"))
                Assert.fail("Message posted by instructor is not displayed");




        }
        catch (Exception e)
        {
            Assert.fail("Exception in class InstructorAssignAllLessons in method instructorAssignAllLessons.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void mentorAssignAllLessons()
    {
        try
        {
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            CourseStreamPage courseStreamPage=PageFactory.initElements(driver,CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("22_1");		//login as student1
            new LoginUsingLTI().ltiLogin("22");		//login as mentor
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            //TC row no. 9
            String icon = lessonPage.getAllLessonAssign().getCssValue("background-image");
            System.out.println("icon: "+icon);
            if(!icon.contains("toc-assignment-cart.png")){
                Assert.fail("In TOC, \"bulk assign\" icon in the right top before \"close\" icon is not shown.");
            }
            //TC row no. 12
            new MouseHover().mouserhoverbywebelement(lessonPage.getAllLessonAssign());//mouse hover the all lesson icon
            String tooltip = lessonPage.getAllLessonAssign().getAttribute("title");
            System.out.println("tooltip: "+tooltip);
            Assert.assertEquals(tooltip, "Assign all lessons in this chapter", "Mouse hover on the \"bulk assign\" icon it doesn't show tooltip as \"Assign all lessons in this chapter\"");
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon
            //TC row no. 13
            Assert.assertEquals(lessonPage.getAllLessonNames().size(), 4, "All lessons related to that particular chapter is not shown.");
            //TC row no. 14
            Assert.assertEquals(lessonPage.getDeleteIcon().size(), 4, "No delete icon before all lessons while assigning all lessons.");

            //Tc row no 15 #3. In the pop_up header the name should be editable
            Thread.sleep(5000);
            lessonPage.chapterName_edit.click();//click on the edit link
            lessonPage.chapterName_textBox.clear();
            lessonPage.chapterName_textBox.sendKeys("Ch 1:The Study of Lifes");

            //Tc row no 16
            lessonPage.assign_button.click(); //click on the assign button

           int allClassSection = driver.findElements(By.cssSelector("span[class='item-text']")).size();
           for(int i=0;i<allClassSection-2;i++)
            {
                driver.findElement(By.className("closebutton")).click();//click on close symbol
            }
           Assert.assertEquals(lessonPage.assignTo_label.get(0).getText().trim(), "Assign To: *", "Assign To: field is not displayed");
            Assert.assertEquals(lessonPage.assignTo_label.get(2).getText().trim(), "Accessible After: *", "Accessible After: field is not displayed");
            Assert.assertEquals(lessonPage.assignTo_label.get(3).getText().trim(),"Due Date: *","Due Date: field is not displayed");
            Assert.assertEquals(lessonPage.assignTo_label.get(4).getText().trim(),"Description:","Description: field is not displayed");


            //Tc row no 17
            Assert.assertNotEquals(lessonPage.assignTo_content.get(0).getText().trim(), " ", "Assign to field having no instructor");
            //Tc row no 18
            assignAssignmentFromLessonPage("22");

            new Navigator().NavigateTo("Current Assignments"); //navigate to the current assignment
            currentAssignments.allActivity_link.click();//click on the allActivity
            currentAssignments.learningActivity_link.click();//click on the learning activity
            Assert.assertEquals(currentAssignments.status.getText().trim(),"Available for Students","Status of class is not a Available for Students");

            //Tc row no 122
            List<WebElement> lsIcons=currentAssignments.lsIcon;
            for(WebElement eachAssessment:lsIcons) {
                if(!eachAssessment.getCssValue("background-image").contains("/ls/doctypes.png"))
                    Assert.fail("All Assessments is not appear with ls icon");
            }

            //Tc row no 20
            currentAssignments.lesson.get(0).click();//click on the lesson
            Thread.sleep(4000);
            new Navigator().navigateToTab("Assignments"); //navigate tp assignment tab
            Thread.sleep(1000);
            Assert.assertEquals(currentAssignments.classStatus_assignmentTab.getText().trim(),"Available for Students","Status of class is not a Available for Students");

            //Tc row no 21
            new Navigator().NavigateTo("Course Stream");//navigate to course stream
            String instructorName=courseStreamPage.instructorIcon.getText().trim();
            System.out.println("Instructor Icon with Message:"+instructorName);
            if(!instructorName.contains("Instructor posted an assignment"))
                Assert.fail("Message posted by instructor is not displayed");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class mentorAssignAllLessons in method instructorAssignAllLessons.", e);
        }
    }
    @Test(priority = 3, enabled = true)
    public void instructorAssignRequiredLesson()
    {
        try
        {
            //Tc row no 34
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver, CurrentAssignments.class);


            new LoginUsingLTI().ltiLogin("34_1");		//login as student1
            new LoginUsingLTI().ltiLogin("34");		//login as instructor
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon

            lessonPage.getDeleteIcon().get(2).click();// click on the delete lesson icon
            lessonPage.deleteLesson.click(); //click on the yes link
            Thread.sleep(3000);
            lessonPage.getDeleteIcon().get(2).click();// click on the delete lesson icon
            lessonPage.deleteLesson.click(); //click on the yes link

            lessonPage.assign_button.click(); //click on the assign button

            //Tc row no 34 Expected
            Assert.assertEquals(lessonPage.assignTo_label.get(0).getText().trim(), "Assign To: *", "Assign To: field is not displayed");
            Assert.assertEquals(lessonPage.assignTo_label.get(2).getText().trim(), "Accessible After: *", "Accessible After: field is not displayed");
            Assert.assertEquals(lessonPage.assignTo_label.get(3).getText().trim(),"Due Date: *","Due Date: field is not displayed");
            Assert.assertEquals(lessonPage.assignTo_label.get(4).getText().trim(),"Description:","Description: field is not displayed");

            //Tc row no 35
            assignAssignmentFromLessonPage("34");
            new Navigator().NavigateTo("Current Assignments"); //navigate to the current assignment
            Assert.assertEquals(currentAssignments.lesson.size(), 2, "All lessons related to that particular chapter is not shown.");

            //Tc row no 36
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon
            Assert.assertEquals(lessonPage.getAllLessonNames().size(), 4, "All lessons related to that particular chapter is not shown.");




        }
        catch (Exception e)
        {
            Assert.fail("Exception in class instructorAssignRequiredLesson in method instructorAssignAllLessons.", e);
        }
    }
    @Test(priority = 4, enabled = true)
    public void mentorAssignRequiredLesson()
    {
        try
        {
            //Tc row no 37
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver, CurrentAssignments.class);


            new LoginUsingLTI().ltiLogin("37_1");		//login as student1
            new LoginUsingLTI().ltiLogin("37");		//login as mentor
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon

            lessonPage.getDeleteIcon().get(2).click();// click on the delete lesson icon
            lessonPage.deleteLesson.click(); //click on the yes link
            Thread.sleep(3000);
            lessonPage.getDeleteIcon().get(2).click();// click on the delete lesson icon
            lessonPage.deleteLesson.click(); //click on the yes link

            lessonPage.assign_button.click(); //click on the assign button

            //Tc row no 37 Expected
            Assert.assertEquals(lessonPage.assignTo_label.get(0).getText().trim(), "Assign To: *", "Assign To: field is not displayed");

            //Tc row no 38
            assignAssignmentFromLessonPage("37");
            new Navigator().NavigateTo("Current Assignments"); //navigate to the current assignment
            Assert.assertEquals(currentAssignments.lesson.size(), 2, "All lessons related to that particular chapter is not shown.");

            //Tc row no 39
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon
            Assert.assertEquals(lessonPage.getAllLessonNames().size(), 4, "All lessons related to that particular chapter is not shown.");



        }
        catch (Exception e)
        {
            Assert.fail("Exception in class mentorAssignRequiredLesson in method instructorAssignAllLessons.", e);
        }
    }
    @Test(priority = 5, enabled = true)
    public void instructorAccessMultipleClassSection()
    {
        try
        {
            //Tc row no 40
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            ClassSectionDropDown classSectionDropDown = PageFactory.initElements(driver, ClassSectionDropDown.class);

            new LoginUsingLTI().ltiLogin("40_1");		//login as student1
            new LoginUsingLTI().ltiLogin("40");		//login as instructor1
            new LoginUsingLTI().ltiLogin("41");		//login as instructor2
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("i.close-study-plan-icon.close-study-plan")));

            lessonPage.getAllLessonAssign().click();//click on bulk assign icon
            lessonPage.assign_button.click(); //click on the assign button
            //Expected 1
            int classSectionSize=lessonPage.assignTo_content.size();
            System.out.println("classSectionSize:"+classSectionSize);
            if(classSectionSize<3)
                Assert.fail("list of all class_sections in the \"Assign to\" Text block is not displaying");

            //Tc row no 41
            new AssignLesson().assignLessonWithDefaultClassSection("40");

            new Navigator().NavigateTo("Current Assignments"); //navigate to the current assignment
            currentAssignments.allActivity_link.click();//click on the allActivity
            currentAssignments.learningActivity_link.click();//click on the learning activity
            Assert.assertEquals(currentAssignments.status.getText().trim(),"Available for Students","Status of class is not a Available for Students");

            //Tc row no 43
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("i.close-study-plan-icon.close-study-plan")));
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon

            lessonPage.getDeleteIcon().get(2).click();// click on the delete lesson icon
            lessonPage.deleteLesson.click(); //click on the yes link
            Thread.sleep(3000);
            lessonPage.getDeleteIcon().get(2).click();// click on the delete lesson icon
            lessonPage.deleteLesson.click(); //click on the yes link

            lessonPage.assign_button.click(); //click on the assign button

            assignAssignmentFromLessonPage("41");

            new Navigator().NavigateTo("Current Assignments"); //navigate to the current assignment
            Assert.assertEquals(currentAssignments.lessonName_particularClass.size(), 2, "All lessons related to that particular class section is not shown.");
            Assert.assertEquals(currentAssignments.status.getText().trim(),"Available for Students","Status of class is not a Available for Students");

            //Tc row no 44
            classSectionDropDown.defaultClassSection.click();
            classSectionDropDown.classSectionName_list.get(2).click();
           /* currentAssignments.classSection_toggle.click();//click on the class section toggle
            currentAssignments.selectClassSection.click(); //select class section*/
            new Navigator().NavigateTo("Current Assignments"); //navigate to the current assignment
            Assert.assertEquals(currentAssignments.lesson.size(), 4, "All lessons related to that particular chapter is not shown.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class mentorAssignRequiredLesson in method instructorAssignAllLessons.", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void mentorAccessMultipleClassSection()
    {
        try
        {
            //Tc row no 45
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            String context_title = ReadTestData.readDataByTagName("", "context_title", "45");
            String context_title1 = ReadTestData.readDataByTagName("", "context_title", "46");
            ClassSectionDropDown classSectionDropDown = PageFactory.initElements(driver, ClassSectionDropDown.class);

            new LoginUsingLTI().ltiLogin("45_1");		//login as student1
            new LoginUsingLTI().ltiLogin("45");		//login as mentor1
            new LoginUsingLTI().ltiLogin("46");		//login as mentor2
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon
            lessonPage.assign_button.click(); //click on the assign button
            //Expected 1
           int classSectionSize=lessonPage.assignTo_content.size();
            System.out.println("classSectionSize:"+classSectionSize);
            if(classSectionSize<3)
                Assert.fail("list of all class_sections in the \"Assign to\" Text block is not displaying");


            int allClassSection =lessonPage.assignTo_content.size();
            for(int i=0;i<allClassSection;i++)
            {
                driver.findElement(By.className("closebutton")).click();//click on close symbol
            }

            lessonPage.assignTo_textBox.sendKeys(context_title);
            lessonPage.suggestion1.click();//click on the suggestion
            Thread.sleep(4000);
            lessonPage.assignTo_textBox.sendKeys(context_title1);
            lessonPage.suggestion.click(); //click on the second suggestion
            new AssignLesson().assignLessonWithDefaultClassSection("46");

            new Navigator().NavigateTo("Current Assignments"); //navigate to the current assignment
            currentAssignments.allActivity_link.click();//click on the allActivity
            currentAssignments.learningActivity_link.click();//click on the learning activity
            Assert.assertEquals(currentAssignments.status.getText().trim(),"Available for Students","Status of class is not a Available for Students");

            //Tc row no 43
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon

            lessonPage.getDeleteIcon().get(2).click();// click on the delete lesson icon
            lessonPage.deleteLesson.click(); //click on the yes link
            Thread.sleep(3000);
            lessonPage.getDeleteIcon().get(2).click();// click on the delete lesson icon
            lessonPage.deleteLesson.click(); //click on the yes link

            lessonPage.assign_button.click(); //click on the assign button

            assignAssignmentFromLessonPage("46");

            new Navigator().NavigateTo("Current Assignments"); //navigate to the current assignment
            Assert.assertEquals(currentAssignments.lessonName_particularClass.size(), 2, "All lessons related to that particular class section is not shown.");
            Assert.assertEquals(currentAssignments.status.getText().trim(),"Available for Students","Status of class is not a Available for Students");

            //Tc row no 44
            classSectionDropDown.defaultClassSection.click();
            WebElement element=driver.findElement(By.xpath("//div[@title='"+context_title+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
           /* driver.findElement(By.className("sbToggle")).click();
            WebElement element=driver.findElement(By.xpath("//a[text()='"+context_title+"']")); //select the other class section
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(500);
            driver.findElement(By.xpath("//a[text()='"+context_title+"']")).click(); //select the other class section
*/
            new Navigator().NavigateTo("Current Assignments"); //navigate to the current assignment
            Assert.assertEquals(currentAssignments.lesson.size(), 4, "All lessons related to that particular chapter is not shown.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class mentorAccessMultipleClassSection in method instructorAssignAllLessons.", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void deleteLessonAndClickOnCancel()
    {
        try
        {
            //Tc row no 52
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("50");		//login as instructor
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            Thread.sleep(7000);
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon
            lessonPage.getDeleteIcon().get(2).click();// click on the delete lesson icon
            lessonPage.deleteLesson.click(); //click on the yes link
            Thread.sleep(3000);
            lessonPage.getDeleteIcon().get(2).click();// click on the delete lesson icon
            lessonPage.deleteLesson.click(); //click on the yes link
            lessonPage.cancel_button.click(); //click on the cancel button
            //Expected
            boolean popUp=new BooleanValue().presenceOfElement(50,By.id("ir-ls-assign-dialog"));
            System.out.println("Pop up:"+popUp);
            if(popUp==true)
                Assert.fail("pop_up block is not closed");

            //Tc row no 53
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon
            Assert.assertEquals(lessonPage.getAllLessonNames().size(), 4, "All lessons related to that particular chapter is not shown.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class deleteLessonAndClickOnCancel in method instructorAssignAllLessons.", e);
        }
    }
    @Test(priority = 8, enabled = true)
    public void deleteLessonAndClickOnCancelInAssignPopUP()
    {
        try
        {
            //Tc row no 52
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("52");		//login as instructor
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            Thread.sleep(7000);
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon
            lessonPage.getDeleteIcon().get(2).click();// click on the delete lesson icon
            lessonPage.deleteLesson.click(); //click on the yes link
            Thread.sleep(3000);
            lessonPage.getDeleteIcon().get(2).click();// click on the delete lesson icon
            lessonPage.deleteLesson.click(); //click on the yes link
            lessonPage.assign_button.click(); //click on the assign button
            lessonPage.cancel_button.click(); //click on the cancel button
            //Expected
            boolean popUp=new BooleanValue().presenceOfElement(52,By.id("ir-ls-assign-dialog"));
            System.out.println("Pop up:"+popUp);
            if(popUp==true)
                Assert.fail("pop_up block is not closed");

            //Tc row no 53
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon
            Assert.assertEquals(lessonPage.getAllLessonNames().size(), 4, "All lessons related to that particular chapter is not shown.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class deleteLessonAndClickOnCancelInAssignPopUP in method instructorAssignAllLessons.", e);
        }
    }
    @Test(priority = 9, enabled = true)
    public void navigateToOtherChapter()
    {
        try
        {
            //Tc row no 54
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("54");		//login as instructor
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            Thread.sleep(7000);
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon
            lessonPage.getDeleteIcon().get(2).click();// click on the delete lesson icon
            lessonPage.deleteLesson.click(); //click on the yes link
            Thread.sleep(3000);
            lessonPage.getDeleteIcon().get(2).click();// click on the delete lesson icon
            lessonPage.deleteLesson.click(); //click on the yes link
            Thread.sleep(3000);
            lessonPage.selectChapter.get(1).click();//select second chapter
            Thread.sleep(5000);
            //Expected
            new UIElement().waitAndFindElement(lessonPage.getAllLessonAssign());
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon
            Thread.sleep(5000);
            Assert.assertEquals(lessonPage.getAllLessonNames().size(), 5, "All lessons related to that particular chapter is not shown.");


            //Tc row no 55
            lessonPage.selectChapter.get(0).click();//select first chapter
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon
            Thread.sleep(3000);
            Assert.assertEquals(lessonPage.getAllLessonNames().size(), 4, "All lessons related to that particular chapter is not shown.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class navigateToOtherChapter in method instructorAssignAllLessons.", e);
        }
    }
    @Test(priority = 10, enabled = true)
    public void assignToParticularStudent()
    {
        try
        {
            //Tc row no 56
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);

           new LoginUsingLTI().ltiLogin("56_1");		//login as student1
           new LoginUsingLTI().ltiLogin("56_2");		//login as student2
           new LoginUsingLTI().ltiLogin("56");		//login as instructor

           new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            Thread.sleep(7000);
           new AssignLesson().assignAllLesson("56");
            //Tc row no 57
            new Navigator().NavigateTo("Current Assignments"); //navigate to the current assignment
            Assert.assertEquals(currentAssignments.notStarted_box.get(0).getText().trim(), "1", "Not Started count is not displayed");
            Assert.assertEquals(currentAssignments.notStarted_box.get(1).getText().trim(), "Not Started", "Not Started label is not displayed");

            new LoginUsingLTI().ltiLogin("56_1");//login as student1
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            currentAssignments.studentLessonAssignment.get(0).click();//click first lesson

            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            currentAssignments.studentLessonAssignment.get(1).click();//click second lesson

            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            currentAssignments.studentLessonAssignment.get(2).click();//click third lesson

            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            currentAssignments.studentLessonAssignment.get(3).click();//click first lesson

            new LoginUsingLTI().ltiLogin("56");		//login as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to the current assignment
            Assert.assertEquals(currentAssignments.status_reviewInProgress.getText().trim(),"Review in Progress","Based on the student activity the status is not  shown in Assignments page");



        }
        catch (Exception e)
        {
            Assert.fail("Exception in class assignToParticularStudent in method instructorAssignAllLessons.", e);
        }
    }
    @Test(priority = 11, enabled = true)
    public void attemptAllLessonAsStudent()
    {
        try
        {
            //Tc row no 63
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            AssignmentTab assignmentTab=PageFactory.initElements(driver,AssignmentTab.class);
            new LoginUsingLTI().ltiLogin("63_1");		//login as student
            new LoginUsingLTI().ltiLogin("63");		//login as instructor
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            Thread.sleep(7000);
            new AssignLesson().assignAllLesson("63");
            new LoginUsingLTI().ltiLogin("63_1");		//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e_textBook
            new SelectCourse().selectInvisibleAssignment("Introduction");
            Thread.sleep(3000);
            new Navigator().navigateToTab("Assignments"); //navigate to assignment tab
            String userName=assignmentTab.userName.getText().trim();
            System.out.println("Learning Activity:"+userName);
            if(!userName.contains("family, givennameInstructor"))
                Assert.fail("Not showing the assigned \"Learning activity\"");

            //Tc row no 64
            assignmentTab.rightArrow.click();//click on the right arrow
            Thread.sleep(2000);
            assignmentTab.open_button.click();//click on the open button
            Assert.assertEquals(assignmentTab.lessonName_AssignmentTab.size(), 4, "Not opened the learning activity in a new tab with the list of all lessons");
            assignmentTab.lessonName_AssignmentTab.get(0).click();//click on the first lesson name
            Thread.sleep(3000);


            //Tc row no 66
            new Navigator().navigateToTab("Assignments"); //navigate to assignment tab
            Assert.assertEquals(assignmentTab.status_AssignmentTab.getText().trim(),"In Progress","The status of the activity is not \"Inprogress\"");

            //Tc row no 67
            for(int i=1;i<=3;i++) {
                assignmentTab.rightArrow.click();//click on the right arrow
                assignmentTab.open_button.click();//click on the open button
                assignmentTab.lessonName_AssignmentTab.get(i).click();//click on the first lesson name
                Thread.sleep(3000);
                new Navigator().navigateToTab("Assignments"); //navigate to assignment tab

            }

            Assert.assertEquals(assignmentTab.status_AssignmentTab.getText().trim(),"Submitted","The status of the activity is not \"Submitted\"");

            //Tc row no 68

            new LoginUsingLTI().ltiLogin("63_1");		//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TopicOpen().chapterOpen(2);
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Introduction']")));
            List<WebElement> introductionLesson = driver.findElements(By.xpath("//a[@title='Introduction']"));
            for(WebElement ele:introductionLesson)
            if(ele.isDisplayed())
            {
                ele.click();
            }

            new TOCShow().chaptertree();
            new TopicOpen().lessonOpen(1,4);
            new Navigator().navigateToTab("Assignments"); //navigate to assignment tab
            boolean popUp=new BooleanValue().presenceOfElement(66,By.className("ls-right-user-head"));
            System.out.println("Pop up:"+popUp);
            if(popUp==true)
                Assert.fail("showing learning activity");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in class attemptAllLessonAsStudent in method instructorAssignAllLessons.", e);
        }
    }
    @Test(priority = 12, enabled = true)
    public void attemptAllLessonFromAssignmentPage()
    {
        try
        {
            //Tc row no 69
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            AssignmentTab assignmentTab=PageFactory.initElements(driver,AssignmentTab.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);

            new LoginUsingLTI().ltiLogin("69_1");		//login as student
            new LoginUsingLTI().ltiLogin("69");		//login as instructor
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            Thread.sleep(7000);
            new AssignLesson().assignAllLesson("69");
            new LoginUsingLTI().ltiLogin("69_1");		//login as student
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments

            currentAssignments.studentLessonAssignment.get(0).click();//click on the first lesson name
            boolean tab=new BooleanValue().presenceOfElement(69, By.id("sec-intro"));
            if(tab==false)
                Assert.fail("lesson is not opened in new tab by replacing the existing lesson tab");

            //Tc row no 70

            new Navigator().NavigateTo("Assignments"); //navigate to Assignments
            Assert.assertEquals(assignments.status_inProgress.getText().trim(),"In Progress","The status of the activity is not \"Inprogress\"");
            //Tc row 71
            Assert.assertEquals(assignments.green_tickMark.isDisplayed(),true,"For Access lesson green tick mark is not showing");

            //Tc row no 72
            for(int i=1;i<=3;i++) {
                currentAssignments.studentLessonAssignment.get(i).click();//click on the first lesson name
                Thread.sleep(3000);
                new Navigator().NavigateTo("Assignments"); //navigate to Assignments

            }

            Assert.assertEquals(assignments.status_submitted.getText().trim(),"Submitted","The status of the activity is not \"Submitted\"");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in class attemptAllLessonFromAssignmentPage in method instructorAssignAllLessons.", e);
        }
    }
    @Test(priority = 13, enabled = true)
    public void attemptAllLessonFromCourseStream()
    {
        try
        {
            //Tc row no 73
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            AssignmentTab assignmentTab=PageFactory.initElements(driver,AssignmentTab.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            CourseStreamPage courseStreamPage=PageFactory.initElements(driver,CourseStreamPage.class);
            new LoginUsingLTI().ltiLogin("73_1");		//login as student
            new LoginUsingLTI().ltiLogin("73");		//login as instructor
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            Thread.sleep(7000);
            new AssignLesson().assignAllLesson("73");
            new LoginUsingLTI().ltiLogin("73_1");		//login as student
            new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream

            //Tc row no 74
            String currentUrl=driver.getCurrentUrl();
            if(!currentUrl.contains("/coursestream"))
                Assert.fail("Student is not navigate to  \"Course stream\" page");
            courseStreamPage.lessonName.get(0).click();//click on the first lesson name
            boolean tab=new BooleanValue().presenceOfElement(73, By.id("sec-intro"));
            if(tab==false)
                Assert.fail("lesson is not opened in new tab by replacing the existing lesson tab");

            //Tc row no 75

            new Navigator().NavigateTo("Assignments"); //navigate to Assignments
            Assert.assertEquals(assignments.status_inProgress.getText().trim(),"In Progress","The status of the activity is not \"Inprogress\"");
            //Tc row 76
            Assert.assertEquals(assignments.green_tickMark.isDisplayed(),true,"For Access lesson green tick mark is not showing");

            //Tc row no 77
            new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
            for(int i=1;i<=3;i++) {
                courseStreamPage.lessonName.get(i).click();//click on the second lesson name
                Thread.sleep(3000);
                new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream

            }
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments

            //Tc row no 77
            Assert.assertEquals(assignments.status_submitted.getText().trim(),"Submitted","The status of the activity is not \"Submitted\"");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in class attemptAllLessonFromCourseStream in method instructorAssignAllLessons.", e);
        }
    }
    @Test(priority = 14, enabled = true)
    public void learningActivityNotShownForDeletedLesson()
    {
        try
        {
            //Tc row no 73
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            AssignmentTab assignmentTab=PageFactory.initElements(driver,AssignmentTab.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            CourseStreamPage courseStreamPage=PageFactory.initElements(driver,CourseStreamPage.class);
            new LoginUsingLTI().ltiLogin("78_1");		//login as student
            new LoginUsingLTI().ltiLogin("78");		//login as instructor
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            Thread.sleep(7000);
            lessonPage.getAllLessonAssign().click();//click on bulk assign icon
            lessonPage.getDeleteIcon().get(2).click();// click on the delete lesson icon
            lessonPage.deleteLesson.click(); //click on the yes link
            Thread.sleep(3000);
            lessonPage.getDeleteIcon().get(2).click();// click on the delete lesson icon
            lessonPage.deleteLesson.click(); //click on the yes link
            Thread.sleep(3000);
            new AssignLesson().assignAllLesson("78");
            new LoginUsingLTI().ltiLogin("78_1");		//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e_textBook
            new SelectCourse().selectInvisibleAssignment("Introduction");
            Thread.sleep(3000);
            new Navigator().navigateToTab("Assignments"); //navigate to assignment tab
            String userName=assignmentTab.userName.getText().trim();
            System.out.println("Learning Activity:"+userName);
            if(!userName.contains("family, givennameInstructor"))
                Assert.fail("Not showing the assigned \"Learning activity\"");



        }
        catch (Exception e)
        {
            Assert.fail("Exception in class learningActivityNotShownForDeletedLesson in method instructorAssignAllLessons.", e);
        }
    }



    public void assignAssignmentFromLessonPage(String dataIndex) {
        try {
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", dataIndex);
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, true);
            Thread.sleep(2000);

            driver.findElement(By.id("due-time")).click();//click on dur time
            Thread.sleep(2000);
            driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();

            driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='"+duedate+"']")));
            Thread.sleep(2000);
            if (gradeable != null) {
                if (gradeable.equals("true")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
                    Thread.sleep(2000);
                }
            }
            driver.findElement(By.id("additional-notes")).clear();
            driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
            new Click().clickBycssselector("span[class='btn sty-green submit-assign toc-assignment-cart']");
            Thread.sleep(5000);
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper assignAssignmentFromLessonPage in class Assignment", e);
        }
    }

}
