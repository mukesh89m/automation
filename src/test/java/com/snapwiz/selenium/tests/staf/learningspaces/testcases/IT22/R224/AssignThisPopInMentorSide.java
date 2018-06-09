package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT22.R224;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Resources.MyResources;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Mukesh on 7/8/15.
 */
public class AssignThisPopInMentorSide extends Driver{
    @Test(priority = 1,enabled = true)
    public void assignThisPopOfCustomAssignment()
    {
        try {
            MyQuestionBank myQuestionBank= PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment=PageFactory.initElements(driver,NewAssignment.class);
            int dataIndex=12;
            String assignmentType = ReadTestData.readDataByTagName("", "assignmentType", Integer.toString(dataIndex));
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(dataIndex));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(dataIndex));

            new LoginUsingLTI().ltiLogin("12"); //Login as instructor
            goToParticularAssignmentType(assignmentType,dataIndex); //go to custom assignment

            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("12");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);

            new UIElement().waitAndFindElement(newAssignment.assignNowButton);
            newAssignment.assignNowButton.click(); //click on the assign now button
            new UIElement().waitAndFindElement(newAssignment.assignThis_popUp);
            //Tc row no 12
            Assert.assertEquals(newAssignment.assignThis_popUp.isDisplayed(), true, "\"Assign This\" pop up is not  displayed.");
            //Tc row no 13
            Assert.assertEquals(newAssignment.assignmentReference_label.getText().trim(),"Assignment Reference:","“Grading Policy” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(),true,"After Assignment reference text a drop down is not displayed.");

            //Tc row no 15&16
            if(!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Select your Assignment Reference") && newAssignment.assignmentReference_dropDown.getText().trim()!=null)){
                Assert.fail("Default value in dropdown has not  changed to “Select your Assignment Reference.”");
            }

            //Tc row no 17
            if(newAssignment.assignmentReference_description.getText().trim().equals("Assignment Reference Description:")){
                Assert.fail("\"Grading Policy description\" is not changed to “Assignment Reference Description” in below the Assignment Reference row.");
            }

            //Tc row no 18
            newAssignment.gradableCheckBox.click(); //click on the gradable check box
            newAssignment.assignmentPolicy_dropdown.click(); //click on the assignment policy text
            //Tc row no 19
            newAssignment.createAssignmentPolicy.click(); //click on the create assignment policy text


        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopOfCustomAssignment of class AssignThisPopInMentorSide", e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void assignThisPopOfUsePreCreatedAssignment()
    {
        try {
            //Tc row no 21
            QuestionBank questionBank=PageFactory.initElements(driver,QuestionBank.class);
            NewAssignment newAssignment=PageFactory.initElements(driver,NewAssignment.class);
            int dataIndex=21;
            String assignmentType = ReadTestData.readDataByTagName("", "assignmentType", Integer.toString(dataIndex));

            new LoginUsingLTI().ltiLogin("21"); //Login as instructor
            goToParticularAssignmentType(assignmentType,dataIndex); //go to custom assignment

            questionBank.getAssignThisButtton().click(); //click on the assign this
            //Tc row no 12
            Assert.assertEquals(newAssignment.assignThis_popUp.isDisplayed(),true,"\"Assign This\" pop up is not  displayed.");
            //Tc row no 13
            Assert.assertEquals(newAssignment.assignmentReference_label.getText().trim(),"Assignment Reference:","“Grading Policy” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(),true,"After Assignment reference text a drop down is not displayed.");

            //Tc row no 15&16
            if(!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Select your Assignment Reference") && newAssignment.assignmentReference_dropDown.getText().trim()!=null)){
                Assert.fail("Default value in dropdown has not  changed to “Select your Assignment Reference.”");
            }

            //Tc row no 17
            if(newAssignment.assignmentReference_description.getText().trim().equals("Assignment Reference Description:")){
                Assert.fail("\"Grading Policy description\" is not changed to “Assignment Reference Description” in below the Assignment Reference row.");
            }

            //Tc row no 18
            newAssignment.gradableCheckBox.click(); //click on the gradable check box
            newAssignment.assignmentPolicy_dropdown.click(); //click on the assignment policy text
            //Tc row no 19
            newAssignment.createAssignmentPolicy.click(); //click on the create assignment policy text


        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopOfUsePreCreatedAssignment of class AssignThisPopInMentorSide", e);
        }
    }
    @Test(priority = 3,enabled = true)
    public void assignThisPopOfFileBasedAssignment()
    {
        try {
            //Tc row no 30
            MyQuestionBank myQuestionBank= PageFactory.initElements(driver,MyQuestionBank.class);
            NewAssignment newAssignment=PageFactory.initElements(driver,NewAssignment.class);
            int dataIndex=30;
            String fileBasedAssignmentName = ReadTestData.readDataByTagName("", "fileBasedAssignmentName", Integer.toString(dataIndex));
            String assignmentType = ReadTestData.readDataByTagName("", "assignmentType", Integer.toString(dataIndex));

            new LoginUsingLTI().ltiLogin("30"); //Login as instructor
            goToParticularAssignmentType(assignmentType,dataIndex); //go to custom assignment

            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(fileBasedAssignmentName);

            new UIElement().waitAndFindElement(newAssignment.assignNowButton);
            newAssignment.assignNowButton.click(); //click on the assign now button
            // Tc row no 12
            Assert.assertEquals(newAssignment.assignThis_popUp.isDisplayed(),true,"\"Assign This\" pop up is not  displayed.");
            //Tc row no 13
            Assert.assertEquals(newAssignment.assignmentReference_label.getText().trim(),"Assignment Reference:","“Grading Policy” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(),true,"After Assignment reference text a drop down is not displayed.");

            //Tc row no 15&16
            if(!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Select your Assignment Reference") && newAssignment.assignmentReference_dropDown.getText().trim()!=null)){
                Assert.fail("Default value in dropdown has not  changed to “Select your Assignment Reference.”");
            }

            //Tc row no 17
            if(newAssignment.assignmentReference_description.getText().trim().equals("Assignment Reference Description:")){
                Assert.fail("\"Grading Policy description\" is not changed to “Assignment Reference Description” in below the Assignment Reference row.");
            }


        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopOfFileBasedAssignment of class AssignThisPopInMentorSide", e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void assignThisPopOfStaticAssessment() {
        try {
            //Tc row no 37
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            new LoginUsingLTI().ltiLogin("37"); //Login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook

            new UIElement().waitAndFindElement(lessonPage.getStaticPracticeIcon());
            lessonPage.getStaticPracticeIcon().click(); //click on the static assessment icon

            new TopicOpen().clickOnAssignThisIcon(); //click on the assign this icon
            //Tc row no 12
            Assert.assertEquals(newAssignment.assignThis_popUp.isDisplayed(), true, "\"Assign This\" pop up is not  displayed.");
            //Tc row no 13
            Assert.assertEquals(newAssignment.assignmentReference_label3.getText().trim(), "Assignment Reference:", "“Grading Policy” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(), true, "After Assignment reference text a drop down is not displayed.");

            //Tc row no 15&16
            if (!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Select your Assignment Reference") && newAssignment.assignmentReference_dropDown.getText().trim() != null)) {
                Assert.fail("Default value in dropdown has not  changed to “Select your Assignment Reference.”");
            }

            //Tc row no 17
            if (newAssignment.assignmentReference_description3.getText().trim().equals("Assignment Reference Description:")) {
                Assert.fail("\"Grading Policy description\" is not changed to “Assignment Reference Description” in below the Assignment Reference row.");
            }

            //Tc row no 18
            newAssignment.gradableCheckBox.click(); //click on the gradable check box
            newAssignment.assignmentPolicy_dropdown.click(); //click on the assignment policy text
            //Tc row no 19
            newAssignment.createAssignmentPolicy.click(); //click on the create assignment policy text


        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopOfStaticAssessment of class AssignThisPopInMentorSide", e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void assignThisPopOfAdaptivePractice()
    {
        try {
            //Tc row no 46
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);

            new LoginUsingLTI().ltiLogin("46"); //Login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon(); //click on the assign this icon
            //Tc row no 12
            Assert.assertEquals(newAssignment.assignThis_popUp.isDisplayed(), true, "\"Assign This\" pop up is not  displayed.");
            //Tc row no 13
            Assert.assertEquals(newAssignment.assignmentReference_label4.getText().trim(), "Assignment Reference:", "“Grading Policy” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(), true, "After Assignment reference text a drop down is not displayed.");

            //Tc row no 15&16
            if (!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Select your Assignment Reference") && newAssignment.assignmentReference_dropDown.getText().trim() != null)) {
                Assert.fail("Default value in dropdown has not  changed to “Select your Assignment Reference.”");
            }

            //Tc row no 17
            if (newAssignment.assignmentReference_description4.getText().trim().equals("Assignment Reference Description:")) {
                Assert.fail("\"Grading Policy description\" is not changed to “Assignment Reference Description” in below the Assignment Reference row.");
            }


        }
        catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopOfAdaptivePractice of class AssignThisPopInMentorSide", e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void assignThisPopOfDiscussionWidget()
    {
        try {
            //Tc row no 53
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiscussionWidget.DiscussionWidget discussionWidget = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiscussionWidget.DiscussionWidget.class);

            new LoginUsingLTI().ltiLogin("53"); //Login as instructor
            new Navigator().NavigateTo("eTextbook");
            new TopicOpen().openLessonWithDiscussionWidget();
            new Click().clickByclassname("assign-this-text");
            Assert.assertEquals(newAssignment.assignThis_popUp.isDisplayed(), true, "\"Assign This\" pop up is not  displayed.");

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", discussionWidget.gradable_checkbox);    //check gradable chekbox

            //Tc row no 13
            Assert.assertEquals(newAssignment.assignmentReference_label1.getText().trim(), "Assignment Reference:", "“Grading Policy” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(), true, "After Assignment reference text a drop down is not displayed.");

            //Tc row no 15&16
            if (!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Select your Assignment Reference") && newAssignment.assignmentReference_dropDown.getText().trim() != null)) {
                Assert.fail("Default value in dropdown has not  changed to “Select your Assignment Reference.”");
            }

            //Tc row no 17
            if (newAssignment.assignmentReference_description1.getText().trim().equals("Assignment Reference Description:")) {
                Assert.fail("\"Grading Policy description\" is not changed to “Assignment Reference Description” in below the Assignment Reference row.");
            }


        }
        catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopOfDiscussionWidget of class AssignThisPopInMentorSide", e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void assignThisPopForAssignmentTab() {
        try {
            //Tc row no 60
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank= PageFactory.initElements(driver,MyQuestionBank.class);
            int dataIndex = 60;

            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(dataIndex));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(dataIndex));

            new Assignment().create(60);
            new Assignment().addQuestions(60,"truefalse","");

            new LoginUsingLTI().ltiLogin("60"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");
            new Navigator().navigateToTab("Question Banks");
            myQuestionBank.customAssignmentButton_list.get(1).click();

            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("60");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click(); //click on save for later


            new Assignment().assignAssignmentFromAssignmentTab(60,customAssignmentName);

            //Tc row no 12
            Assert.assertEquals(newAssignment.assignThis_popUp.isDisplayed(), true, "\"Assign This\" pop up is not  displayed.");
            //Tc row no 13
            Assert.assertEquals(newAssignment.assignmentReference_label.getText().trim(), "Assignment Reference:", "“Grading Policy” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(), true, "After Assignment reference text a drop down is not displayed.");

            //Tc row no 15&16
            if (!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Select your Assignment Reference") && newAssignment.assignmentReference_dropDown.getText().trim() != null)) {
                Assert.fail("Default value in dropdown has not  changed to “Select your Assignment Reference.”");
            }

            //Tc row no 17
            if (newAssignment.assignmentReference_description.getText().trim().equals("Assignment Reference Description:")) {
                Assert.fail("\"Grading Policy description\" is not changed to “Assignment Reference Description” in below the Assignment Reference row.");
            }

            //Tc row no 18
            newAssignment.gradableCheckBox.click(); //click on the gradable check box
            newAssignment.assignmentPolicy_dropdown.click(); //click on the assignment policy text
            //Tc row no 19
            newAssignment.createAssignmentPolicy.click(); //click on the create assignment policy text


        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopForAssignmentTab of class AssignThisPopInMentorSide", e);
        }
    }

    @Test(priority = 8,enabled = true)
    public void assignThisPopForMyQuestionBank() {
        try {
            //Tc row no 70
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            QuestionBank questionBank=PageFactory.initElements(driver,QuestionBank.class);

            new LoginUsingLTI().ltiLogin("60"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");

            questionBank.getAssignThisButtton().click(); //click on the assign this

            //Tc row no 12
            Assert.assertEquals(newAssignment.assignThis_popUp.isDisplayed(), true, "\"Assign This\" pop up is not  displayed.");
            //Tc row no 13
            Assert.assertEquals(newAssignment.assignmentReference_label.getText().trim(), "Assignment Reference:", "“Grading Policy” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(), true, "After Assignment reference text a drop down is not displayed.");

            //Tc row no 15&16
            if (!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Select your Assignment Reference") && newAssignment.assignmentReference_dropDown.getText().trim() != null)) {
                Assert.fail("Default value in dropdown has not  changed to “Select your Assignment Reference.”");
            }

            //Tc row no 17
            if (newAssignment.assignmentReference_description.getText().trim().equals("Assignment Reference Description:")) {
                Assert.fail("\"Grading Policy description\" is not changed to “Assignment Reference Description” in below the Assignment Reference row.");
            }

            //Tc row no 18
            newAssignment.gradableCheckBox.click(); //click on the gradable check box
            newAssignment.assignmentPolicy_dropdown.click(); //click on the assignment policy text
            //Tc row no 19
            newAssignment.createAssignmentPolicy.click(); //click on the create assignment policy text


        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopForMyQuestionBank of class AssignThisPopInMentorSide", e);
        }
    }


    @Test(priority = 9,enabled = true)
    public void assignThisPopForEditThisFromMyQuestionBank() {
        try {
            //Tc row no 80
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank= PageFactory.initElements(driver,MyQuestionBank.class);

            new LoginUsingLTI().ltiLogin("60"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");
            myQuestionBank.editThis.click();

            new UIElement().waitAndFindElement(newAssignment.assignNowButton);
            newAssignment.assignNowButton.click(); //click on the assign now button
            //Tc row no 12
            Assert.assertEquals(newAssignment.assignThis_popUp.isDisplayed(), true, "\"Assign This\" pop up is not  displayed.");
            //Tc row no 13
            Assert.assertEquals(newAssignment.assignmentReference_label.getText().trim(), "Assignment Reference:", "“Grading Policy” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(), true, "After Assignment reference text a drop down is not displayed.");

            //Tc row no 15&16
            if (!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Select your Assignment Reference") && newAssignment.assignmentReference_dropDown.getText().trim() != null)) {
                Assert.fail("Default value in dropdown has not  changed to “Select your Assignment Reference.”");
            }

            //Tc row no 17
            if (newAssignment.assignmentReference_description.getText().trim().equals("Assignment Reference Description:")) {
                Assert.fail("\"Grading Policy description\" is not changed to “Assignment Reference Description” in below the Assignment Reference row.");
            }

            //Tc row no 18
            newAssignment.gradableCheckBox.click(); //click on the gradable check box
            newAssignment.assignmentPolicy_dropdown.click(); //click on the assignment policy text
            //Tc row no 19
            newAssignment.createAssignmentPolicy.click(); //click on the create assignment policy text


        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopForEditThisFromMyQuestionBank of class AssignThisPopInMentorSide", e);
        }
    }

    @Test(priority = 10,enabled = true)
    public void assignThisPopForUpdateCustomAssignment() {
        try {
            //Tc row no 90
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank= PageFactory.initElements(driver,MyQuestionBank.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            int dataIndex=90;
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(dataIndex));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(dataIndex));

            new LoginUsingLTI().ltiLogin("90_1"); //Login as student
            new LoginUsingLTI().ltiLogin("90"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");
            new Navigator().navigateToTab("Question Banks");
            myQuestionBank.customAssignmentButton_list.get(1).click();

            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("90");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);
            myQuestionBank.getSaveForLater().click(); //click on save for later

            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(90);

            new Navigator().NavigateTo("Current Assignments"); //navigate to Current Assignments
            currentAssignments.updateAssignment_link.get(0).click();
            currentAssignments.reAssign_link.click();

            //Assert.assertEquals(newAssignment.assignThis_popUp.isDisplayed(), true, "\"Assign This\" pop up is not  displayed.");
            //Tc row no 13
            Assert.assertEquals(newAssignment.assignmentReference_label5.getText().trim(), "Assignment Reference:", "“Grading Policy” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(), true, "After Assignment reference text a drop down is not displayed.");

            //Tc row no 15&16
            if (!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Select your Assignment Reference") && newAssignment.assignmentReference_dropDown.getText().trim() != null)) {
                Assert.fail("Default value in dropdown has not  changed to “Select your Assignment Reference.”");
            }

            //Tc row no 17
            System.out.println(newAssignment.assignmentReference_description4.getText());
            if (newAssignment.assignmentReference_description5.getText().trim().equals("Assignment Reference Description:")) {
                Assert.fail("\"Grading Policy description\" is not changed to “Assignment Reference Description” in below the Assignment Reference row.");
            }

            //Tc row no 18
            newAssignment.gradableCheckBox.click(); //click on the gradable check box
            newAssignment.assignmentPolicydropdown.click(); //click on the assignment policy text
            //Tc row no 19
            newAssignment.createAssignmentPolicy.click(); //click on the create assignment policy text


        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopForUpdateCustomAssignment of class AssignThisPopInMentorSide", e);
        }
    }

    @Test(priority = 11,enabled = true)
    public void assignThisPopForUpdateFileBasedAssignment() {
        try {
            //Tc row no 100
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);

            new Assignment().createFileBasedAssessment(100);

            new LoginUsingLTI().ltiLogin("100_1"); //Login as student
            new LoginUsingLTI().ltiLogin("100"); //Login as instructor

            new Assignment().assignFileBasedAssignmentToStudent(100);

            new Navigator().NavigateTo("Current Assignments"); //navigate to Current Assignments
            currentAssignments.updateAssignment_link.get(0).click();

            //Assert.assertEquals(newAssignment.assignThis_popUp.isDisplayed(), true, "\"Assign This\" pop up is not  displayed.");
            //Tc row no 13
            Assert.assertEquals(newAssignment.assignmentReference_label2.getText().trim(), "Assignment Reference:", "“Grading Policy” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(), true, "After Assignment reference text a drop down is not displayed.");

            //Tc row no 15&16
            if (!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Select your Assignment Reference") && newAssignment.assignmentReference_dropDown.getText().trim() != null)) {
                Assert.fail("Default value in dropdown has not  changed to “Select your Assignment Reference.”");
            }

            //Tc row no 17
            if (newAssignment.assignmentReference_description2.getText().trim().equals("Assignment Reference Description:")) {
                Assert.fail("\"Grading Policy description\" is not changed to “Assignment Reference Description” in below the Assignment Reference row.");
            }



        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopForUpdateFileBasedAssignment of class AssignThisPopInMentorSide", e);
        }
    }
    @Test(priority = 12,enabled = true)
    public void currentAssignmentForNonGradableAssignment() {
        try {
            //Tc row no 110
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            MyQuestionBank myQuestionBank= PageFactory.initElements(driver,MyQuestionBank.class);
            int dataIndex=110;
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(dataIndex));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(dataIndex));


            new LoginUsingLTI().ltiLogin("110_1");    //login as student2
            new LoginUsingLTI().ltiLogin("110");		//login a instructor
            new UploadResources().uploadResources("110", false, true, true);//upload resource*

            new LoginUsingLTI().ltiLogin("110"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");
            new Navigator().navigateToTab("Question Banks");
            myQuestionBank.customAssignmentButton_list.get(1).click();

            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("110");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);

            new UIElement().waitAndFindElement(newAssignment.assignNowButton);
            newAssignment.assignNowButton.click(); //click on the assign now button
            new AssignLesson().assignTOCWithDefaultClassSection(110);
            //Tc row no 110*/
            new Navigator().NavigateTo("Current Assignments"); //navigate to Current Assignments
            String assignmentname=currentAssignments.getAssessmentName().getAttribute("assignmentname").trim();
            if(!assignmentname.equals(customAssignmentName)){
                Assert.fail("Assignment is not displayed in current assignment page.");

            }

            Assert.assertEquals(currentAssignments.assignmentReference.get(1).getText().trim(),"Assignment Reference:","“Grading Policy” is not  changed to “Assignment Reference” label.");

            currentAssignments.getViewGrade_link().click(); //click on the viewStudentResponse link
            new UIElement().waitAndFindElement(By.id("idb-gradeBook-title"));
            for(WebElement ele:currentAssignments.assignmentReference) {
                if (!ele.getText().trim().equals("Assignment Reference:") && !ele.getText().trim().equals(" ")&& ele.getText().trim().equals("Grading Policy")) {
                    Assert.fail("“Grading Policy” is not  changed to “Assignment Reference” label.");

                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in testcase currentAssignmentForNonGradableAssignment of class AssignThisPopInMentorSide", e);
        }
    }
    @Test(priority = 13,enabled = true)
    public void currentAssignmentForGradableAssignment() {
        try {
            //Tc row no 114
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            MyQuestionBank myQuestionBank= PageFactory.initElements(driver,MyQuestionBank.class);
            int dataIndex=114;
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(dataIndex));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(dataIndex));


            new LoginUsingLTI().ltiLogin("114_1");    //login as student2
            new LoginUsingLTI().ltiLogin("114");		//login a instructor
            new UploadResources().uploadResources("114", false, true, true);//upload resource

            new LoginUsingLTI().ltiLogin("114"); //Login as instructor
            new Navigator().NavigateTo("My Question Bank");
            new Navigator().navigateToTab("Question Banks");
            myQuestionBank.customAssignmentButton_list.get(1).click();

            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("114");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);

            new UIElement().waitAndFindElement(newAssignment.assignNowButton);
            newAssignment.assignNowButton.click(); //click on the assign now button
            new AssignLesson().assignTOCWithDefaultClassSection(114);
            //Tc row no 114*/
            new LoginUsingLTI().ltiLogin("114"); //Login as instructor

            new Navigator().NavigateTo("Current Assignments"); //navigate to Current Assignments
            String assignmentname=currentAssignments.getAssessmentName().getAttribute("assignmentname").trim();
            if(!assignmentname.equals(customAssignmentName)){
                Assert.fail("Assignment is not displayed in current assignment page.");

            }

            Assert.assertEquals(currentAssignments.assignmentReference.get(1).getText().trim(),"Assignment Reference:","“Grading Policy” is not  changed to “Assignment Reference” label.");

            currentAssignments.getViewGrade_link().click(); //click on the viewStudentResponse link
            new UIElement().waitAndFindElement(By.className("grading-policy-media-file-for-instructor"));

            for(WebElement ele:currentAssignments.assignmentReference) {
                if (!ele.getText().trim().equals("Assignment Reference:") && !ele.getText().trim().equals(" ")&& ele.getText().trim().equals("Grading Policy")) {
                    Assert.fail("“Grading Policy” is not  changed to “Assignment Reference” label.");

                }
            }
           // Assert.assertEquals(currentAssignments.assignmentReference.get(3).getText().trim(),"Assignment Reference:","“Grading Policy” is not  changed to “Assignment Reference” label.");


        } catch (Exception e) {
            Assert.fail("Exception in testcase currentAssignmentForGradableAssignment of class AssignThisPopInMentorSide", e);
        }
    }


    @Test(priority = 14,enabled = true)
    public void changedLabelGradingPolicyInMyResource() {
        try {
            //Tc row no 128
            MyResources myResources=PageFactory.initElements(driver,MyResources.class);
            String filename = ReadTestData.readDataByTagName("", "filename", "128");

            new LoginUsingLTI().ltiLogin("128");		//login a instructor
            new Navigator().NavigateTo("My Resources"); //Navigate to my resource
            String myResourceTitle = myResources.getMyResourceTitle().getAttribute("title");
            Assert.assertEquals(myResourceTitle, "My Resources", "My Resources page is not opened");

            myResources.uploadResource_link.click(); //click on the upload file
            Assert.assertEquals(myResources.uploadFile_popup.isDisplayed(),true,"upload file popup is not displayed");
            String assignmentReference="Reserved for assignment reference";
            if(!assignmentReference.contains(myResources.assignmentReference_text.getText().trim())){
                Assert.fail("Reserved for grading policy” label is not changed to “Reserved for assignment reference");
            }

            myResources.assignmentReference_help.click(); //click on the help

            String help_text="If you select this option, the assignment reference resource will be available for the instructor in the \"My Resources\" page under Resources. This assignment reference should be used while creating gradable assignments for students.";
            Assert.assertEquals(myResources.help_text.getText().trim(),help_text,"help text is not same");


            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",myResources.uploadFile_link);
            Thread.sleep(5000);
            new KeysSend().sendKeyBoardKeys("$");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(10000);
            driver.findElement(By.className("ins-uploadResource-input")).clear();
            driver.findElement(By.className("ins-uploadResource-input")).click();
            Thread.sleep(2000);
            driver.findElement(By.className("ins-uploadResource-input")).sendKeys("resourcesname");//give resources name
            myResources.assignmentReference_checkbok.click();//checked for reserve for graded policy

            new Click().clickbylistcssselector("div[class='course-chapter-label node']", 0);//select 1st chapter to associate
            myResources.save_button.click();
            if(!myResources.notification_msg.getText().trim().equals("The Assignment Reference has been successfully created.")){
                Assert.fail("The Assignment Reference has been successfully created is not showing");
            }

            Assert.assertEquals(myResources.assignmentReference_icon.getText().trim(),"Assignment Reference","Assignment Reference icon is not present");
            Assert.assertEquals(myResources.assignmentReference_position.isDisplayed(),true,"assignmentReference_icon is not after resources name");

            new Click().clickbylinkText("Type"); //click on the Type
            new Click().clickbylinkText("Assignment Reference"); //click on the Assignment Reference


        } catch (Exception e) {
            Assert.fail("Exception in testcase changedLabelGradingPolicyInMyResource of class AssignThisPopInMentorSide", e);
        }
    }

    @Test(priority = 15,enabled = true)
    public void changedLabelGradingPolicyInMyResourceForExistingInstructor() {
        try {
            //Tc row no 142
            MyResources myResources=PageFactory.initElements(driver,MyResources.class);
            new LoginUsingLTI().ltiLogin("128");		//login a instructor
            new Navigator().NavigateTo("My Resources"); //Navigate to my resource

            Assert.assertEquals(myResources.assignmentReference_icon.getText().trim(),"Assignment Reference","Assignment Reference icon is not present");
            Assert.assertEquals(myResources.assignmentReference_position.isDisplayed(),true,"assignmentReference_icon is not after resources name");

            new Click().clickbylinkText("Type"); //click on the Type
            new Click().clickbylinkText("Assignment Reference"); //click on the Assignment Reference


        } catch (Exception e) {
            Assert.fail("Exception in testcase changedLabelGradingPolicyInMyResourceForExistingInstructor of class AssignThisPopInMentorSide", e);
        }
    }

    @Test(priority = 16,enabled = true)
    public void changedLabelGradingPolicyInAssignmentPage() {
        try {
            //Tc row no 148
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("110_1");    //login as student1
            new Navigator().NavigateTo("Assignments"); //Navigate to my resource
            Assert.assertEquals(currentAssignments.assignmentReference.get(1).getText().trim(),"Assignment Reference:","“Grading Policy” is not  changed to “Assignment Reference” label.");



        } catch (Exception e) {
            Assert.fail("Exception in testcase changedLabelGradingPolicyInAssignmentPage of class AssignThisPopInMentorSide", e);
        }
    }

    @Test(priority = 17,enabled = true)
    public void changedLabelNonGradingPolicyInAssignmentPage() {
        try {
            //Tc row no 150
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("114_1");    //login as student
            new Navigator().NavigateTo("Assignments"); //Navigate to my resource
            Assert.assertEquals(currentAssignments.assignmentReference.get(1).getText().trim(),"Assignment Reference:","“Grading Policy” is not  changed to “Assignment Reference” label.");



        } catch (Exception e) {
            Assert.fail("Exception in testcase changedLabelNonGradingPolicyInAssignmentPage of class AssignThisPopInMentorSide", e);
        }
    }
    public  void goToParticularAssignmentType(String assignmentType,int dataIndex )
    {
        new Navigator().NavigateTo("New Assignment");//navigate to New Assignment
        driver.findElement(By.xpath("//div[text()='"+assignmentType+"']")).click(); //click on the assignment type
    }
}

