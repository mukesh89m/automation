package com.snapwiz.selenium.tests.staf.learnon.testcases.IT22.R224;

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.*;
import com.snapwiz.selenium.tests.staf.learnon.pageFactory.assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learnon.pageFactory.assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learnon.pageFactory.assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learnon.pageFactory.assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learnon.pageFactory.etextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Mukesh on 8/12/15.
 */
public class ChangedAssignmentPolicyText extends Driver {
    @Test(priority = 1,enabled = true)
    public void changedAssignmentPolicyText()
    {
        try
        {
            MyQuestionBank myQuestionBank= PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment=PageFactory.initElements(driver,NewAssignment.class);
            int dataIndex=298;
            String assignmentType = ReadTestData.readDataByTagName("", "assignmentType", Integer.toString(dataIndex));
            String searchQuestion = ReadTestData.readDataByTagName("", "searchquestion", Integer.toString(dataIndex));
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", Integer.toString(dataIndex));

            new LoginUsingLTI().ltiLogin("298"); //log in as instructor
            goToParticularAssignmentType(assignmentType,dataIndex); //go to custom assignment

            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchQuestion);
            new AssignLesson().selectQuestionForCustomAssignment("298");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customAssignmentName);

            new UIElement().waitAndFindElement(newAssignment.assignNowButton);
            newAssignment.assignNowButton.click(); //click on the assign now button
            new UIElement().waitAndFindElement(newAssignment.assignThis_popUp);
            //Tc row no 298

            Assert.assertEquals(newAssignment.assignThis_popUp.isDisplayed(), true, "\"Assign This\" pop up is not  displayed.");
            //Tc row no 299
            newAssignment.gradableCheckBox.click(); //click on the gradable check box
            newAssignment.assignmentPolicy_dropdown.click(); //click on the assignment policy text
            //Tc row no 300
            newAssignment.createAssignmentPolicy.click(); //click on the create assignment policy text

        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC changedAssignmentPolicyText in class ChangedAssignmentPolicyText.", e);
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
            String assignmentType =ReadTestData.readDataByTagName("", "assignmentType", Integer.toString(dataIndex));
            new LoginUsingLTI().ltiLogin("21"); //Login as instructor

            goToParticularAssignmentType(assignmentType,dataIndex); //go to custom assignment

            questionBank.getAssignThisButtton().click(); //click on the assign this
            //Tc row no 12
            Assert.assertEquals(newAssignment.assignThis_popUp.isDisplayed(),true,"\"Assign This\" pop up is not  displayed.");
            //Tc row no 13
            Assert.assertEquals(newAssignment.assignmentReference_label.getText().trim(),"Marking Policy:","“Marking Policy:” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(),true,"After Marking Policy: text a drop down is not displayed.");

            //Tc row no 15&16
            if(!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Choose your Marking Policy") && newAssignment.assignmentReference_dropDown.getText().trim()!=null)){
                Assert.fail("Default value in dropdown has not  changed to “Choose your Assignment Reference.”");
            }

            //Tc row no 17
            if(newAssignment.assignmentReference_description.getText().trim().equals("Marking Policy Description:")){
                Assert.fail("\"Grading Policy description\" is not changed to “Assignment Reference Description” in below the Assignment Reference row.");
            }

            //Tc row no 18
            newAssignment.gradableCheckBox.click(); //click on the gradable check box
            newAssignment.assignmentPolicy_dropdown.click(); //click on the assignment policy text
            //Tc row no 19
            newAssignment.createAssignmentPolicy.click(); //click on the create assignment policy text


        } catch (Exception e) {
            Assert.fail("Exception in TC assignThisPopOfUsePreCreatedAssignment of class ChangedAssignmentPolicyText", e);
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
            Assert.assertEquals(newAssignment.assignmentReference_label.getText().trim(),"Marking Policy:","“Marking Policy:” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(),true,"After Marking Policy: text a drop down is not displayed.");

            if(!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Choose your Marking Policy") && newAssignment.assignmentReference_dropDown.getText().trim()!=null)){
                Assert.fail("Default value in dropdown has not  changed to “Choose your Marking Policy”");
            }

            //Tc row no 17
            if(newAssignment.assignmentReference_description.getText().trim().equals("Marking Policy Description:")){
                Assert.fail("\"Marking Policy Description:\" is not changed to “Marking Policy Description:” in below the Marking Policy: row.");
            }


        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopOfFileBasedAssignment of class ChangedAssignmentPolicyText", e);
        }
    }
    @Test(priority = 4,enabled = true)
    public void assignThisPopOfStaticAssessment() {
        try {
            //Tc row no 37
            NewAssignment newAssignment = PageFactory.initElements(driver,NewAssignment.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            new LoginUsingLTI().ltiLogin("37"); //Login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook

            new TopicOpen().clickOnStaticAssessmentArrow(37); //click on the static assessment arrow

            new TopicOpen().clickOnAssignThisIcon(); //click on the assign this icon
            //Tc row no 12
            Assert.assertEquals(newAssignment.assignThis_popUp.isDisplayed(), true, "\"Assign This\" pop up is not  displayed.");
            //Tc row no 13
            Assert.assertEquals(newAssignment.assignmentReference_label3.getText().trim(), "Marking Policy:", "“Marking Policy:” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(), true, "After Marking Policy: text a drop down is not displayed.");

            //Tc row no 15&16
            if (!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Choose your Marking Policy") && newAssignment.assignmentReference_dropDown.getText().trim() != null)) {
                Assert.fail("Default value in dropdown has not  changed to “Choose your Marking Policy”");
            }

            //Tc row no 17
            if (newAssignment.assignmentReference_description3.getText().trim().equals("Marking Policy Description:")) {
                Assert.fail("\"Marking Policy Description:\" is not changed to “Marking Policy Description:” in below the Assignment Reference row.");
            }

            //Tc row no 18
            newAssignment.gradableCheckBox.click(); //click on the gradable check box
            newAssignment.assignmentPolicy_dropdown.click(); //click on the assignment policy text
            //Tc row no 19
            newAssignment.createAssignmentPolicy.click(); //click on the create assignment policy text


        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopOfStaticAssessment of class ChangedAssignmentPolicyText", e);
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
            Assert.assertEquals(newAssignment.assignmentReference_label3.getText().trim(), "Marking Policy:", "“Marking Policy:” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(), true, "After Marking Policy: text a drop down is not displayed.");

            //Tc row no 15&16
            if (!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Choose your Marking Policy") && newAssignment.assignmentReference_dropDown.getText().trim() != null)) {
                Assert.fail("Default value in dropdown has not  changed to “Choose your Marking Policy”");
            }

            //Tc row no 17
            if (newAssignment.assignmentReference_description3.getText().trim().equals("Marking Policy Description:")) {
                Assert.fail("\"Marking Policy Description:\" is not changed to “Marking Policy Description:” in below the Assignment Reference row.");
            }

            //Tc row no 18
            newAssignment.gradableCheckBox.click(); //click on the gradable check box
            newAssignment.assignmentPolicy_dropdown.click(); //click on the assignment policy text
            //Tc row no 19
            newAssignment.createAssignmentPolicy.click(); //click on the create assignment policy text



        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopForAssignmentTab of class ChangedAssignmentPolicyText", e);
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
            Assert.assertEquals(newAssignment.assignmentReference_label3.getText().trim(), "Marking Policy:", "“Marking Policy:” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(), true, "After Marking Policy: text a drop down is not displayed.");

            //Tc row no 15&16
            if (!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Choose your Marking Policy") && newAssignment.assignmentReference_dropDown.getText().trim() != null)) {
                Assert.fail("Default value in dropdown has not  changed to “Choose your Marking Policy”");
            }

            //Tc row no 17
            if (newAssignment.assignmentReference_description3.getText().trim().equals("Marking Policy Description:")) {
                Assert.fail("\"Marking Policy Description:\" is not changed to “Marking Policy Description:” in below the Assignment Reference row.");
            }

            //Tc row no 18
            newAssignment.gradableCheckBox.click(); //click on the gradable check box
            newAssignment.assignmentPolicy_dropdown.click(); //click on the assignment policy text
            //Tc row no 19
            newAssignment.createAssignmentPolicy.click(); //click on the create assignment policy text


        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopForMyQuestionBank of class ChangedAssignmentPolicyText", e);
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
            Assert.assertEquals(newAssignment.assignmentReference_label3.getText().trim(), "Marking Policy:", "“Marking Policy:” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(), true, "After Marking Policy: text a drop down is not displayed.");

            //Tc row no 15&16
            if (!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Choose your Marking Policy") && newAssignment.assignmentReference_dropDown.getText().trim() != null)) {
                Assert.fail("Default value in dropdown has not  changed to “Choose your Marking Policy”");
            }

            //Tc row no 17
            if (newAssignment.assignmentReference_description3.getText().trim().equals("Marking Policy Description:")) {
                Assert.fail("\"Marking Policy Description:\" is not changed to “Marking Policy Description:” in below the Assignment Reference row.");
            }

            //Tc row no 18
            newAssignment.gradableCheckBox.click(); //click on the gradable check box
            newAssignment.assignmentPolicy_dropdown.click(); //click on the assignment policy text
            //Tc row no 19
            newAssignment.createAssignmentPolicy.click(); //click on the create assignment policy text


        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopForEditThisFromMyQuestionBank of class ChangedAssignmentPolicyText", e);
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

            Assert.assertEquals(newAssignment.assignThis_popUp.isDisplayed(), true, "\"Assign This\" pop up is not  displayed.");
            //Tc row no 13
            Assert.assertEquals(newAssignment.assignmentReference_label5.getText().trim(), "Marking Policy:", "“Marking Policy:” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(), true, "After Marking Policy: text a drop down is not displayed.");

            //Tc row no 15&16
            if (!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Choose your Marking Policy") && newAssignment.assignmentReference_dropDown.getText().trim() != null)) {
                Assert.fail("Default value in dropdown has not  changed to “Choose your Marking Policy”");
            }

            //Tc row no 17
            if (newAssignment.assignmentReference_description5.getText().trim().equals("Marking Policy Description:")) {
                Assert.fail("\"Marking Policy Description:\" is not changed to “Marking Policy Description:” in below the Assignment Reference row.");
            }

            //Tc row no 18
            newAssignment.gradableCheckBox.click(); //click on the gradable check box
            newAssignment.assignmentPolicy_dropdown.click(); //click on the assignment policy text
            //Tc row no 19
            newAssignment.createAssignmentPolicy.click(); //click on the create assignment policy text


        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopForUpdateCustomAssignment of class ChangedAssignmentPolicyText", e);
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

            Assert.assertEquals(newAssignment.assignThis_popUp.isDisplayed(), true, "\"Assign This\" pop up is not  displayed.");
            //Tc row no 13
            Assert.assertEquals(newAssignment.assignmentReference_label2.getText().trim(), "Marking Policy:", "“Marking Policy:” text is not hanged to “Assignment Reference” text in fourth row.");
            //Tc row no 14
            Assert.assertEquals(newAssignment.assignmentReference_dropDown.isDisplayed(), true, "After Marking Policy: text a drop down is not displayed.");

            //Tc row no 15&16
            if (!(newAssignment.assignmentReference_dropDown.getText().trim().equals("Choose your Marking Policy") && newAssignment.assignmentReference_dropDown.getText().trim() != null)) {
                Assert.fail("Default value in dropdown has not  changed to “Choose your Marking Policy”");
            }

            //Tc row no 17
            if (newAssignment.assignmentReference_description2.getText().trim().equals("Marking Policy Description:")) {
                Assert.fail("\"Marking Policy Description:\" is not changed to “Marking Policy Description:” in below the Assignment Reference row.");
            }


        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopForUpdateFileBasedAssignment of class ChangedAssignmentPolicyText", e);
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

            Assert.assertEquals(currentAssignments.assignmentReference.get(1).getText().trim(),"Marking Policy:","“Marking Policy:” is not  changed to “Assignment Reference” label.");

            currentAssignments.getViewGrade_link().click(); //click on the viewStudentResponse link
            new UIElement().waitAndFindElement(By.id("idb-gradeBook-title"));
            Assert.assertEquals(currentAssignments.assignmentReference.get(3).getText().trim(),"Marking Policy:","“Marking Policy:” is not  changed to “Assignment Reference” label.");



        } catch (Exception e) {
            Assert.fail("Exception in testcase currentAssignmentForNonGradableAssignment of class ChangedAssignmentPolicyText", e);
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
            new Navigator().NavigateTo("Current Assignments"); //navigate to Current Assignments
            String assignmentname=currentAssignments.getAssessmentName().getAttribute("assignmentname").trim();
            if(!assignmentname.equals(customAssignmentName)){
                Assert.fail("Assignment is not displayed in current assignment page.");

            }

            Assert.assertEquals(currentAssignments.assignmentReference.get(1).getText().trim(),"Marking Policy:","“Marking Policy:” is not  changed to “Assignment Reference” label.");

            currentAssignments.getViewGrade_link().click(); //click on the viewStudentResponse link
            new UIElement().waitAndFindElement(By.id("idb-gradeBook-title"));
            Assert.assertEquals(currentAssignments.assignmentReference.get(3).getText().trim(),"Marking Policy:","“Marking Policy:” is not  changed to “Assignment Reference” label.");

        } catch (Exception e) {
            Assert.fail("Exception in testcase currentAssignmentForGradableAssignment of class ChangedAssignmentPolicyText", e);
        }
    }



    public  void goToParticularAssignmentType(String assignmentType,int dataIndex )
    {
        new Navigator().NavigateTo("New Assignment");//navigate to New Assignment
        driver.findElement(By.xpath("//div[text()='" + assignmentType + "']")).click(); //click on the assignment type
    }
}
