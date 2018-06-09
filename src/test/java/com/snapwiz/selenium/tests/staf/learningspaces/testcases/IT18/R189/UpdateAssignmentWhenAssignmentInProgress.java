package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R189;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


/*
 * Created by sumit on 29/12/14.
 */
public class UpdateAssignmentWhenAssignmentInProgress extends Driver{

    @Test(priority = 1, enabled = true)
    public void updateLessonAssignmentWhenAssignmentInProgress()
    {
        try
        {
            //TC row no. 74 - 83
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String duedate = ReadTestData.readDataByTagName("", "duedate", "74_1");
            String shareName = ReadTestData.readDataByTagName("", "shareName", "74_1");

            new LoginUsingLTI().ltiLogin("74_1");		//login as student1
            new LoginUsingLTI().ltiLogin("74_2");    //login as student2
            new LoginUsingLTI().ltiLogin("74");		//login a instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TOCShow().tocHide();                    //hide the TOC
            new AssignLesson().assignLesson("74_1");//assign lesson to class

            //TC row no 74
            new LoginUsingLTI().ltiLogin("74_1");		//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getLessonAssignment().click();//open lesson assignment

            new LoginUsingLTI().ltiLogin("74");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            //TC row no 78
            String firstDueDate = currentAssignments.getDueDateOnAssignPopUp().getAttribute("value");
            if(!firstDueDate.contains(duedate)){
                Assert.fail("Due Date field does not contain the last due date for the lesson assignment, irrespective of the CLASS or STUDENT.");
            }
            //TC row no 79, 81
            String extendDueDateLabel = currentAssignments.getExtendDueDateTab().getText();
            Assert.assertEquals(extendDueDateLabel, "Extend Due Date", "\"Extend Due Date\" is not visible besides \"Assignment Details\" tab for a lesson assignment.");

            currentAssignments.getExtendDueDateTab().click();//click on Extend Due Date Tab
            //TC row no 82
            Assert.assertEquals(currentAssignments.getAssignmentNameOnAssignPopUpList().get(1).getText(), "Introduction", "The lesson assignment name is not shown on the top of extend due date tab.");

            //TC row no 83
            Assert.assertEquals(currentAssignments.getExtendDueDateForField().get(1).getText(), shareName, "The \"Extend due date for\" is not populated with the class section/ list of students for a lesson assignment.");
            String firstDueDate1 = currentAssignments.getNewDueDateField().getAttribute("value");
            if(!firstDueDate1.contains(duedate)){
                Assert.fail("\"New Due date\" is not populated with the first \"Due Date\" for a lesson assignment.");
            }

        }
        catch (Exception e){
            Assert.fail("Exception in class UpdateAssignmentWhenAssignmentInProgress in method updateLessonAssignmentWhenAssignmentInProgress.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void updatePictureAssignmentWhenAssignmentInProgress()
    {
        try
        {
            //TC row no. 84 - 93
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            String duedate = ReadTestData.readDataByTagName("", "duedate", "84_1");
            String shareName = ReadTestData.readDataByTagName("", "shareName", "84_1");

            new LoginUsingLTI().ltiLogin("84_1");		//login as student1
            new LoginUsingLTI().ltiLogin("84_2");    //login as student2
            new LoginUsingLTI().ltiLogin("84");		//login a instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TOCShow().tocHide();                    //hide the TOC
            new AssignLesson().assignImageWidget("84_1");//assign image to class

            //TC row no 84
            new LoginUsingLTI().ltiLogin("84_1");		//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getLessonAssignment().click();//open image assignment

            new LoginUsingLTI().ltiLogin("84");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            //TC row no 88
            String firstDueDate = currentAssignments.getDueDateOnAssignPopUp().getAttribute("value");
            if(!firstDueDate.contains(duedate)){
                Assert.fail("Due Date field does not contain the last due date for the image assignment, irrespective of the CLASS or STUDENT.");
            }
            //TC row no 89, 91
            String extendDueDateLabel = currentAssignments.getExtendDueDateTab().getText();
            Assert.assertEquals(extendDueDateLabel, "Extend Due Date", "\"Extend Due Date\" is not visible besides \"Assignment Details\" tab for a image assignment.");

            currentAssignments.getExtendDueDateTab().click();//click on Extend Due Date Tab
            //TC row no 92
            String assignmentName = currentAssignments.getAssignmentNameOnAssignPopUpList().get(1).getText();
            if(!assignmentName.contains("New Assignment")){
                Assert.fail("The image assignment name is not shown on the top of extend due date tab.");
            }

            //TC row no 93
            Assert.assertEquals(currentAssignments.getExtendDueDateForField().get(1).getText(), shareName, "The \"Extend due date for\" is not populated with the class section/ list of students for a image assignment.");
            String firstDueDate1 = currentAssignments.getNewDueDateField().getAttribute("value");
            if(!firstDueDate1.contains(duedate)){
                Assert.fail("\"New Due date\" is not populated with the first \"Due Date\" for a image assignment.");
            }

        }
        catch (Exception e){
            Assert.fail("Exception in class UpdateAssignmentWhenAssignmentInProgress in method updatePictureAssignmentWhenAssignmentInProgress.", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void updateCartAssignmentWhenAssignmentInProgress()
    {
        try
        {
            //TC row no. 114 - 123
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            String duedate = ReadTestData.readDataByTagName("", "duedate", "114");
            String shareName = ReadTestData.readDataByTagName("", "shareName", "114");

            new LoginUsingLTI().ltiLogin("114_1");		//login as student1
            new LoginUsingLTI().ltiLogin("114_2");    //login as student2
            new LoginUsingLTI().ltiLogin("114");		//login a instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TOCShow().tocHide();                    //hide the TOC
            new AddCart().widgetaddtocart();//add image widget to cart
            lessonPage.getAssignmentCart().click();//click on Cart
            new AssignLesson().assigncartwithclasssection(114);//assign cart to class

            //TC row no 114
            new LoginUsingLTI().ltiLogin("114_1");		//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getLessonAssignment().click();//open cart assignment

            new LoginUsingLTI().ltiLogin("114");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            //TC row no 118
            String firstDueDate = currentAssignments.getDueDateOnAssignPopUp().getAttribute("value");
            if(!firstDueDate.contains(duedate)){
                Assert.fail("Due Date field does not contain the last due date for the cart assignment, irrespective of the CLASS or STUDENT.");
            }
            //TC row no 119, 121
            String extendDueDateLabel = currentAssignments.getExtendDueDateTab().getText();
            Assert.assertEquals(extendDueDateLabel, "Extend Due Date", "\"Extend Due Date\" is not visible besides \"Assignment Details\" tab for a cart assignment.");

            currentAssignments.getExtendDueDateTab().click();//click on Extend Due Date Tab
            //TC row no 122
            String assignmentName = currentAssignments.getAssignmentNameOnAssignPopUpList().get(1).getText();
            if(!assignmentName.contains("New Assignment")){
                Assert.fail("The cart assignment name is not shown on the top of extend due date tab.");
            }

            //TC row no 123
            Assert.assertEquals(currentAssignments.getExtendDueDateForField().get(1).getText(), shareName, "The \"Extend due date for\" is not populated with the class section/ list of students for a cart assignment.");
            String firstDueDate1 = currentAssignments.getNewDueDateField().getAttribute("value");
            if(!firstDueDate1.contains(duedate)){
                Assert.fail("\"New Due date\" is not populated with the first \"Due Date\" for a cart assignment.");
            }

        }
        catch (Exception e){
            Assert.fail("Exception in class UpdateAssignmentWhenAssignmentInProgress in method updateCartAssignmentWhenAssignmentInProgress.", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void updateNonGradableDWAssignmentWhenAssignmentInProgress()
    {
        try
        {
            //TC row no. 124 - 133
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            String duedate = ReadTestData.readDataByTagName("", "duedate", "124");
            String shareName = ReadTestData.readDataByTagName("", "shareName", "124");

            new LoginUsingLTI().ltiLogin("124_1");		//login as student1
            new LoginUsingLTI().ltiLogin("124_2");    //login as student2
            new LoginUsingLTI().ltiLogin("124");		//login a instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidget("124");//assign non gradable DW with class

            //TC row no 124
            new LoginUsingLTI().ltiLogin("124_1");		//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getLessonAssignment().click();//open cart assignment
            String perspective = new RandomString().randomstring(20);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective);

            new LoginUsingLTI().ltiLogin("124");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            String assignmentName = currentAssignments.getDWassignmentName().getText();
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            //TC row no 128
            String firstDueDate = currentAssignments.getDueDateOnAssignPopUp().getAttribute("value");
            if(!firstDueDate.contains(duedate)){
                Assert.fail("Due Date field does not contain the last due date for the Non Gradable DW Assignment, irrespective of the CLASS or STUDENT.");
            }
            //TC row no 129, 131
            String extendDueDateLabel = currentAssignments.getExtendDueDateTab().getText();
            Assert.assertEquals(extendDueDateLabel, "Extend Due Date", "\"Extend Due Date\" is not visible besides \"Assignment Details\" tab for a Non Gradable DW assignment.");


            currentAssignments.getExtendDueDateTab().click();//click on Extend Due Date Tab
            //TC row no 132
            String assignmentName1 = currentAssignments.getAssignmentNameOnAssignPopUpList().get(1).getText();
            if(!assignmentName.contains(assignmentName1)){
                Assert.fail("The Non Gradable DW assignment name is not shown on the top of extend due date tab.");
            }

            //TC row no 133
            Assert.assertEquals(currentAssignments.getExtendDueDateForField().get(1).getText(), shareName, "The \"Extend due date for\" is not populated with the class section/ list of students for a Non Gradable DW assignment.");
            String firstDueDate1 = currentAssignments.getNewDueDateField().getAttribute("value");
            if(!firstDueDate1.contains(duedate)){
                Assert.fail("\"New Due date\" is not populated with the first \"Due Date\" for a Non Gradable DW assignment.");
            }

        }
        catch (Exception e){
            Assert.fail("Exception in class UpdateAssignmentWhenAssignmentInProgress in method updateNonGradableDWAssignmentWhenAssignmentInProgress.", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void updateGradableDWAssignmentWhenAssignmentInProgress()
    {
        try
        {
            //TC row no. 134 - 143
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            String duedate = ReadTestData.readDataByTagName("", "duedate", "134");
            String shareName = ReadTestData.readDataByTagName("", "shareName", "134");

            new LoginUsingLTI().ltiLogin("134_1");		//login as student1
            new LoginUsingLTI().ltiLogin("134_2");    //login as student2
            new LoginUsingLTI().ltiLogin("134");		//login a instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidget("134");//assign non gradable DW with class

            //TC row no 134
            new LoginUsingLTI().ltiLogin("134_1");		//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getLessonAssignment().click();//open cart assignment
            String perspective = new RandomString().randomstring(20);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective);

            new LoginUsingLTI().ltiLogin("134");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            String assignmentName = currentAssignments.getDWassignmentName().getText();
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            //TC row no 138
            String firstDueDate = currentAssignments.getDueDateOnAssignPopUp().getAttribute("value");
            if(!firstDueDate.contains(duedate)){
                Assert.fail("Due Date field does not contain the last due date for the Gradable DW assignment, irrespective of the CLASS or STUDENT.");
            }
            //TC row no 139, 141
            String extendDueDateLabel = currentAssignments.getExtendDueDateTab().getText();
            Assert.assertEquals(extendDueDateLabel, "Extend Due Date", "\"Extend Due Date\" is not visible besides \"Assignment Details\" tab for a Gradable DW assignment.");


            currentAssignments.getExtendDueDateTab().click();//click on Extend Due Date Tab
            //TC row no 142
            String assignmentName1 = currentAssignments.getAssignmentNameOnAssignPopUpList().get(1).getText();
            if(!assignmentName.contains(assignmentName1)){
                Assert.fail("The Gradable DW assignment name is not shown on the top of extend due date tab.");
            }

            //TC row no 143
            Assert.assertEquals(currentAssignments.getExtendDueDateForField().get(1).getText(), shareName, "The \"Extend due date for\" is not populated with the class section/ list of students for a Gradable DW assignment.");
            String firstDueDate1 = currentAssignments.getNewDueDateField().getAttribute("value");
            if(!firstDueDate1.contains(duedate)){
                Assert.fail("\"New Due date\" is not populated with the first \"Due Date\" for a Gradable DW assignment.");
            }

        }
        catch (Exception e){
            Assert.fail("Exception in class UpdateAssignmentWhenAssignmentInProgress in method updateGradableDWAssignmentWhenAssignmentInProgress.", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void updateNonGradableQuestionAssignmentWhenAssignmentInProgress()
    {
        try
        {
            //TC row no. 144 - 153
            new Assignment().create(144);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            String duedate = ReadTestData.readDataByTagName("", "duedate", "144");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "144");
            String shareName = ReadTestData.readDataByTagName("", "shareName", "144");

            new LoginUsingLTI().ltiLogin("144_1");		//login as student1
            new LoginUsingLTI().ltiLogin("144_2");    //login as student2
            new LoginUsingLTI().ltiLogin("144");		//login a instructor
            new Assignment().assignToStudent(144);//assign non gradable question assignment with class

            //TC row no 144
            new LoginUsingLTI().ltiLogin("144_1");		//login as student1
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentname);//open the assignment
            new WebDriverWait(driver,3000).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));

            new LoginUsingLTI().ltiLogin("144");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            currentAssignments.getReassign_button().click();    //click on Re-assign button
            //TC row no 148
            String firstDueDate = currentAssignments.getDueDateOnAssignPopUp().getAttribute("value");
            if(!firstDueDate.contains(duedate)){
                Assert.fail("Due Date field does not contain the last due date for the Non Gradable Question assignment, irrespective of the CLASS or STUDENT.");
            }
            //TC row no 149, 151
            String extendDueDateLabel = currentAssignments.getExtendDueDateTab().getText();
            Assert.assertEquals(extendDueDateLabel, "Extend Due Date", "\"Extend Due Date\" is not visible besides \"Assignment Details\" tab for a Non Gradable Question assignment.");


            currentAssignments.getExtendDueDateTab().click();//click on Extend Due Date Tab
            //TC row no 152
            String assignmentName1 = currentAssignments.getAssignmentNameOnAssignPopUpList().get(1).getText();
            if(!assignmentName1.contains(assessmentname)){
                Assert.fail("The Non Gradable Question assignment name is not shown on the top of extend due date tab.");
            }

            //TC row no 153
            Assert.assertEquals(currentAssignments.getExtendDueDateForField().get(1).getText(), shareName, "The \"Extend due date for\" is not populated with the class section/ list of students for a Non Gradable Question assignment.");
            String firstDueDate1 = currentAssignments.getNewDueDateField().getAttribute("value");
            if(!firstDueDate1.contains(duedate)){
                Assert.fail("\"New Due date\" is not populated with the first \"Due Date\" for a Non Gradable Question assignment.");
            }

        }
        catch (Exception e){
            Assert.fail("Exception in class UpdateAssignmentWhenAssignmentInProgress in method updateNonGradableQuestionAssignmentWhenAssignmentInProgress.", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void updateGradableQuestionAssignmentWithNoPolicyWhenAssignmentInProgress()
    {
        try
        {
            //TC row no. 154 - 163
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            String duedate = ReadTestData.readDataByTagName("", "duedate", "154");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "154");
            String shareName = ReadTestData.readDataByTagName("", "shareName", "154");
           new Assignment().create(154);

            new LoginUsingLTI().ltiLogin("154_1");		//login as student1
            new LoginUsingLTI().ltiLogin("154_2");    //login as student2
            new LoginUsingLTI().ltiLogin("154");		//login a instructor
            new Assignment().assignToStudent(154);//assign non gradable question assignment with class

            //TC row no 154
            new LoginUsingLTI().ltiLogin("154_1");		//login as student1
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentname);//open the assignment
            new WebDriverWait(driver,3000).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Thread.sleep(9000);
            new LoginUsingLTI().ltiLogin("154");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            currentAssignments.getReassign_button().click();    //click on Re-assign button
            //TC row no 158
            String firstDueDate = currentAssignments.getDueDateOnAssignPopUp().getAttribute("value");
            if(!firstDueDate.contains(duedate)){
                Assert.fail("Due Date field does not contain the last due date for the Gradable Question assignment, irrespective of the CLASS or STUDENT.");
            }
            //TC row no 159, 161
            String extendDueDateLabel = currentAssignments.getExtendDueDateTab().getText();
            Assert.assertEquals(extendDueDateLabel, "Extend Due Date", "\"Extend Due Date\" is not visible besides \"Assignment Details\" tab for a Gradable Question assignment.");


            currentAssignments.getExtendDueDateTab().click();//click on Extend Due Date Tab
            //TC row no 162
            String assignmentName1 = currentAssignments.getAssignmentNameOnAssignPopUpList().get(1).getText();
            if(!assignmentName1.contains(assessmentname)){
                Assert.fail("The Gradable Question assignment name is not shown on the top of extend due date tab.");
            }

            //TC row no 163
            Assert.assertEquals(currentAssignments.getExtendDueDateForField().get(1).getText(), shareName, "The \"Extend due date for\" is not populated with the class section/ list of students for a Gradable Question assignment.");
            String firstDueDate1 = currentAssignments.getNewDueDateField().getAttribute("value");
            System.out.println("firstDueDate1: "+firstDueDate1);
            if(!firstDueDate1.contains(duedate)){
                Assert.fail("\"New Due date\" is not populated with the first \"Due Date\" for a Gradable Question assignment.");
            }

        }
        catch (Exception e){
            Assert.fail("Exception in class UpdateAssignmentWhenAssignmentInProgress in method updateGradableQuestionAssignmentWithNoPolicyWhenAssignmentInProgress.", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void updateGradableQuestionAssignmentWithPolicyWhenAssignmentInProgress()
    {
        try
        {
            //TC row no. 164 - 174
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            String duedate = ReadTestData.readDataByTagName("", "duedate", "164");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "164");
            String shareName = ReadTestData.readDataByTagName("", "shareName", "164");
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "164");

            new Assignment().create(164);

            new LoginUsingLTI().ltiLogin("164_1");		//login as student1
            new LoginUsingLTI().ltiLogin("164_2");    //login as student2
            new LoginUsingLTI().ltiLogin("164");		//login a instructor
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicy, "164 Policy description", "2", null, false, "1", "", null, "", "", "");
            new Assignment().assignToStudent(164);//assign non gradable question assignment with class

            //TC row no 164
            new LoginUsingLTI().ltiLogin("164_1");		//login as student1
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentname);//open the assignment
            new WebDriverWait(driver,3000).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Thread.sleep(9000);
            new LoginUsingLTI().ltiLogin("164");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            currentAssignments.getReassign_button().click();    //click on Re-assign button
            //TC row no 174
            new Click().clickByid("assignment-policy-icons");   //click on eye icon
            Assert.assertEquals(new TextFetch().textfetchbyclass("policy-dialog-header-text"), "Policy name", "Clicking on eye icon does not open \"View Assignment Policy\" popup.");
            new Click().clickByid("dialog-close");//close the pop up
            //TC row no 168
            String firstDueDate = currentAssignments.getDueDateOnAssignPopUp().getAttribute("value");
            if(!firstDueDate.contains(duedate)){
                Assert.fail("Due Date field does not contain the last due date for the Gradable Question assignment, irrespective of the CLASS or STUDENT.");
            }
            //TC row no 169, 171
            String extendDueDateLabel = currentAssignments.getExtendDueDateTab().getText();
            Assert.assertEquals(extendDueDateLabel, "Extend Due Date", "\"Extend Due Date\" is not visible besides \"Assignment Details\" tab for a Gradable Question assignment.");


            currentAssignments.getExtendDueDateTab().click();//click on Extend Due Date Tab
            //TC row no 172
            String assignmentName1 = currentAssignments.getAssignmentNameOnAssignPopUpList().get(1).getText();
            if(!assignmentName1.contains(assessmentname)){
                Assert.fail("The Gradable Question assignment name is not shown on the top of extend due date tab.");
            }

            //TC row no 173
            Assert.assertEquals(currentAssignments.getExtendDueDateForField().get(1).getText(), shareName, "The \"Extend due date for\" is not populated with the class section/ list of students for a Gradable Question assignment.");
            String firstDueDate1 = currentAssignments.getNewDueDateField().getAttribute("value");
            if(!firstDueDate1.contains(duedate)){
                Assert.fail("\"New Due date\" is not populated with the first \"Due Date\" for a Gradable Question assignment.");
            }

        }
        catch (Exception e){
            Assert.fail("Exception in class UpdateAssignmentWhenAssignmentInProgress in method updateGradableQuestionAssignmentWithPolicyWhenAssignmentInProgress.", e);
        }
    }

}
