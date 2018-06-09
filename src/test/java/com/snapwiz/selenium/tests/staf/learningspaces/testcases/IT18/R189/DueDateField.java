package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R189;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


/*
 * Created by sumit on 29/12/14.
 */
public class DueDateField extends Driver
{
    @Test(priority = 1, enabled = true)
    public void assignLesson(){
        try
        {
            //TC row no. 9 - 14
            /*"The instructor should be able to add one/more students
            and change the “Due date” to extend the due date for that
            class-section/students."*/
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String duedate = ReadTestData.readDataByTagName("", "duedate", "9_2");

            new LoginUsingLTI().ltiLogin("9_1");		//login as student1
            new LoginUsingLTI().ltiLogin("9_2");    //login as student2
            new LoginUsingLTI().ltiLogin("9");		//login a instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TOCShow().tocHide();                    //hide the TOC
            new AssignLesson().assignLesson("9_1");//assign lesson to student1
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            Thread.sleep(2000);
            String assignmentName = new TextFetch().textfetchbyclass("ir-ls-assign-dialog-header");
            Assert.assertEquals(assignmentName, "Introduction", "The title of the popup is not like [\"(Short Label)\" Assignment name].");
            new AssignLesson().updateAssignment("9_2");//update assignment assign to student 2
            String dueDate = new TextFetch().textfetchbyclass("ls-assignment-due-date");
            if(!dueDate.contains(duedate))
                Assert.fail("Instructor is unable to update the due date from current assignment page for a lesson assignment.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class DueDateField in method assignLesson.", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void assignPicture(){
        try
        {
            //TC row no. 15 - 20
            /*"The instructor should be able to add one/more students
            and change the “Due date” to extend the due date for that
            class-section/students."*/
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            String duedate = ReadTestData.readDataByTagName("", "duedate", "152");
            new LoginUsingLTI().ltiLogin("151");		//login as student1
            new LoginUsingLTI().ltiLogin("152");   //login as student2
            new LoginUsingLTI().ltiLogin("15");		//login a instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TOCShow().tocHide();                    //hide the TOC
            new AddCart().widgetaddtocart();//add image widget to cart
            lessonPage.getAssignmentCart().click();//click on Cart
            new AssignLesson().assigncartwithclasssection(151);//assign image widget to student1
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            String assignmentName = new TextFetch().textfetchbyclass("ir-ls-assign-dialog-header");
            if(assignmentName.contains("shor")){
                Assert.fail("The title of the popup is not like [\"(Short Label)\" Assignment name] for a image widget.");
            }
            new AssignLesson().updateAssignment("152");//update assignment assign image widget to student2
            new UIElement().waitAndFindElement(By.className("ls-assignment-due-date"));
            String dueDate = new TextFetch().textfetchbyclass("ls-assignment-due-date");
            if(!dueDate.contains(duedate))
                Assert.fail("Instructor is unable to update the due date from current assignment page for a image widget assignment.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class DueDateField in method assignPicture.", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void assignNonGradableDW(){
        try
        {
            //TC row no. 39 - 44
            /*"The instructor should be able to add one/more students
            and change the “Due date” to extend the due date for that
            class-section/students."*/
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String duedate = ReadTestData.readDataByTagName("", "duedate", "39_2");
            new LoginUsingLTI().ltiLogin("39_1");		//login as student1
            new LoginUsingLTI().ltiLogin("39_2");    //login as student2
            new LoginUsingLTI().ltiLogin("39");		//login a instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidget("39_1");//assign DW with student1
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            String assignmentName = new TextFetch().textfetchbyclass("ir-ls-assign-dialog-header");
            if(assignmentName.contains("shor")){
                Assert.fail("The title of the popup is not like [\"(Short Label)\" Assignment name] for a non gradable DW.");
            }
            new AssignLesson().updateAssignment("39_2");//update DW assignment assign to student2
            String dueDate = new TextFetch().textfetchbyclass("ls-assignment-due-date");
            if(!dueDate.contains(duedate))
                Assert.fail("Instructor is unable to update the due date from current assignment page for a non gradable DW.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class DueDateField in method assignNonGradableDW.", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void assignGradableDW(){
        try
        {
            //TC row no. 45 - 50
            /*"The instructor should be able to add one/more students
            and change the “Due date” to extend the due date for that
            class-section/students."*/
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String duedate = ReadTestData.readDataByTagName("", "duedate", "45_2");
            new LoginUsingLTI().ltiLogin("45_1");		//login as student1
            new LoginUsingLTI().ltiLogin("45_2");    //login as student2
            new LoginUsingLTI().ltiLogin("45");		//login a instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidget("45_1");//assign DW with student1
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            String assignmentName = new TextFetch().textfetchbyclass("ir-ls-assign-dialog-header");
            if(assignmentName.contains("shor")){
                Assert.fail("The title of the popup is not like [\"(Short Label)\" Assignment name] for a gradable DW.");
            }
            new AssignLesson().updateAssignment("45_2");//update DW assignment assign to student2
            String dueDate = new TextFetch().textfetchbyclass("ls-assignment-due-date");
            if(!dueDate.contains(duedate))
                Assert.fail("Instructor is unable to update the due date from current assignment page for a gradable DW.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class DueDateField in method assignGradableDW.", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void nonGradableQuestionAssignment(){
        try
        {
            //TC row no. 51 - 56
            /*"The instructor should be able to add one/more students
            and change the “Due date” to extend the due date for that
            class-section/students."*/
            new Assignment().create(51);
            String duedate = ReadTestData.readDataByTagName("", "duedate", "512");
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "51");

            new LoginUsingLTI().ltiLogin("511");		//login as student1
            new LoginUsingLTI().ltiLogin("512");    //login as student2
            new LoginUsingLTI().ltiLogin("51");		//login a instructor
            new Assignment().assignToStudent(511);//assign to student1
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            String assignmentName = new TextFetch().textfetchbycssselector("span[class='ls-assignment-name instructor-assessment-review']");
            if(!assignmentName.equals("(shor) "+assignmentname)){
                Assert.fail("The title of the popup is not like [\"(Short Label)\" Assignment name] for a non Gradable Question Assignment.");
            }
            new Assignment().updateAssignment(512, true);//update  assignment assign to student2
            Thread.sleep(2000);
            String dueDate = new TextFetch().textfetchbyclass("ls-assignment-due-date");
            if(!dueDate.contains(duedate))
                Assert.fail("Instructor is unable to update the due date from current assignment page for a non Gradable Question Assignment.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class DueDateField in method nonGradableQuestionAssignment.", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void gradableQuestionAssignmentWithNoPolicy(){
        try
        {
            //TC row no. 57 - 62
            /*"The instructor should be able to add one/more students
            and change the “Due date” to extend the due date for that
            class-section/students."*/
            new Assignment().create(57);
            String duedate = ReadTestData.readDataByTagName("", "duedate", "572");
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "57");

            new LoginUsingLTI().ltiLogin("571");		//login as student1
            new LoginUsingLTI().ltiLogin("572");    //login as student2
            new LoginUsingLTI().ltiLogin("57");		//login a instructor
            new Assignment().assignToStudent(571);//assign to student1
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            String assignmentName = new TextFetch().textfetchbycssselector("span[class='ls-assignment-name instructor-assessment-review']");
            if(!assignmentName.equals("(shor) "+assignmentname)){
                Assert.fail("The title of the popup is not like [\"(Short Label)\" Assignment name] for a Gradable Question Assignment with no policy.");
            }
            new Assignment().updateAssignment(572, true);//update assignment assign to student2
            Thread.sleep(2000);
            String dueDate = new TextFetch().textfetchbyclass("ls-assignment-due-date");
            if(!dueDate.contains(duedate))
                Assert.fail("Instructor is unable to update the due date from current assignment page for a Gradable Question Assignment with no policy.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class DueDateField in method gradableQuestionAssignmentWithNoPolicy.", e);
        }
    }
    @Test(priority = 7, enabled = true)
    public void gradableQuestionAssignmentWithPolicy(){
        try
        {
            //TC row no. 63 - 68
            /*"The instructor should be able to add one/more students
            and change the “Due date” to extend the due date for that
            class-section/students."*/

            new Assignment().create(63);
            String duedate = ReadTestData.readDataByTagName("", "duedate", "632");
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "63");
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "631");

            new LoginUsingLTI().ltiLogin("631");		//login as student1
            new LoginUsingLTI().ltiLogin("632");    //login as student2
            new LoginUsingLTI().ltiLogin("63");		//login a instructor
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicy, "63 Policy description", "2", null, false, "1", "", null, "", "", "");
            new Assignment().assignToStudent(631);//assign to student1 with assignment policy
            String assignmentName = new TextFetch().textfetchbycssselector("span[class='ls-assignment-name instructor-assessment-review']");
            if(!assignmentName.equals("(shor) "+assignmentname)){
                Assert.fail("The title of the popup is not like [\"(Short Label)\" Assignment name] for a Gradable Question Assignment with policy.");
            }
            new Assignment().updateAssignment(632, true);//update assignment assign to student2
            Thread.sleep(2000);
            String dueDate = new TextFetch().textfetchbyclass("ls-assignment-due-date");
            if(!dueDate.contains(duedate))
                Assert.fail("Instructor is unable to update the due date from current assignment page for a Gradable Question Assignment with policy.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class DueDateField in method gradableQuestionAssignmentWithPolicy.", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void assignResource(){
        try
        {
            //TC row no. 27
            /*"The instructor should be able to add one/more students
            and change the “Due date” to extend the due date for that
            class-section/students."*/
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String duedate = ReadTestData.readDataByTagName("", "duedate", "27_2");
            String resourcesname = ReadTestData.readDataByTagName("", "resourcesname", "27");

            new LoginUsingLTI().ltiLogin("27_1");		//login as student1
            new LoginUsingLTI().ltiLogin("27_2");    //login as student2

            new LoginUsingLTI().ltiLogin("27");		//login a instructor
            new UploadResources().uploadResources("27", true, false, true);//upload resource
            new AssignLesson().assignResourceFromMyResources(27);
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            Thread.sleep(2000);
            String assignmentName = new TextFetch().textfetchbyclass("ir-ls-assign-dialog-header");
            if(System.getProperty("UCHAR") == null) {
                resourcesname = resourcesname + LoginUsingLTI.appendChar;
            }
            else {
                resourcesname = resourcesname + System.getProperty("UCHAR");
            }

            Assert.assertEquals(assignmentName,resourcesname, "The title of the popup is not like [\"(Short Label)\" Assignment name].");
            new AssignLesson().updateAssignment("27_2");//update assignment assign to student 2
            String dueDate = new TextFetch().textfetchbyclass("ls-assignment-due-date");
            if(!dueDate.contains(duedate))
                Assert.fail("Instructor is unable to update the due date from current assignment page for a resource assignment.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class DueDateField in method assignResource.", e);
        }
    }

}
