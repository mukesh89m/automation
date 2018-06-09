package com.snapwiz.selenium.tests.staf.learningspaces.testcases.regression.assignment;

import com.snapwiz.selenium.tests.staf.learningspaces.CustomAssert;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by mukesh on 14/1/16.
 */
public class NewAssignmentPage extends Driver {

    @Test(priority = 1,enabled = true)
    public void createNewAssignmentPopup(){

        try {
            NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
            new LoginUsingLTI().ltiLogin("121");//login as instructor
            new Navigator().NavigateTo("New Assignment"); //navigate to New Assignment
            CustomAssert.assertEquals(newAssignment.assignment_title.getText().trim(), "Create New Assignment", "Verify Assignment Title", "Create New Assignment label is  displaying","Create New Assignment label is not displaying");
            CustomAssert.assertEquals(newAssignment.assignment_header.getText().trim(), "How would you like to create your assignment?","Verify Assignment Header","How would you like to create your assignment? text is displaying","How would you like to create your assignment? text is not displaying");
            CustomAssert.assertEquals(newAssignment.assignment_mode.get(0).getText().trim(), "Custom Assignment","Verify Custom Assignment ","Custom Assignment text is  displaying","Custom Assignment text is not displaying");
            CustomAssert.assertEquals(newAssignment.assignment_mode.get(1).getText().trim(), "Use Pre Created Assignment","Verify Pre Created Assignment ","Use Pre Created Assignment text is  displaying","Use Pre Created Assignment text is not displaying");
            CustomAssert.assertEquals(newAssignment.assignment_mode.get(2).getText().trim(), "File based Assignment","Verify File Based Assignment","File based Assignment text is  displaying","File based Assignment text is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC createNewAssignmentPopup of class NewAssignmentPage",e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void createCustomAssignmentPage(){

        try {
            NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
            new LoginUsingLTI().ltiLogin("121");//login as instructor
            new Navigator().NavigateTo("New Assignment"); //navigate to New Assignment
            newAssignment.createCustomAssignment.click(); //click on the custom assignment

            String tab = newAssignment.activeTab.getText().trim();
            if(!tab.equals("New Assignment"))
                CustomAssert.fail("Instructor navigate to New Assignment tab.","Instructor does not navigate to New Assignment tab.");

            // Verify save for later button...........
            String saveButton =newAssignment.saveForLater_Button.getText().trim();
            if(!saveButton.contains("SAVE FOR LATER"))
                CustomAssert.fail("\"SAVE FOR LATER\" button is Present in New Assignment tab.","\"SAVE FOR LATER\" button is absent in New Assignment tab.");

            //Verify Assign now button...........
            String assignButton = newAssignment.assignNowButton.getText().trim();
            if(!assignButton.contains("ASSIGN NOW"))
            CustomAssert.fail("\"ASSIGN NOW\" button is Present in New Assignment tab.","\"ASSIGN NOW\" button is absent in New Assignment tab.");


        } catch (Exception e) {
            Assert.fail("Exception in TC createCustomAssignmentPage of class NewAssignmentPage",e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void usePreCreatedAssignmentPage(){

        try {
            NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
            QuestionBank questionBank= PageFactory.initElements(driver,QuestionBank.class);

            new LoginUsingLTI().ltiLogin("121");//login as instructor
            new Navigator().NavigateTo("New Assignment"); //navigate to New Assignment
            newAssignment.getUsePreCreatedAssignment().click(); //click on the usePreCreated assignment

            String tab = newAssignment.activeTab.getText().trim();
            if(!tab.equals("Question Banks"))
                CustomAssert.fail("Instructor navigate to Question Banks tab.","Instructor does not navigate to Question Banks tab.");

            // Verify save for later button...........
            String saveButton =questionBank.createCustomAssignment_link.getText().trim();
            if(!saveButton.equals("Create Custom Assignment"))
                CustomAssert.fail("\"Create Custom Assignment\" button is Present in New Assignment tab.","\"Create Custom Assignment\" button is absent in New Assignment tab.");




        } catch (Exception e) {
            Assert.fail("Exception in TC usePreCreatedAssignmentPage of class NewAssignmentPage",e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void createFileBasedAssignment(){

        try {
            NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
            QuestionBank questionBank= PageFactory.initElements(driver,QuestionBank.class);

            new LoginUsingLTI().ltiLogin("121");//login as instructor
            new Navigator().NavigateTo("New Assignment"); //navigate to New Assignment
            newAssignment.createFileBasedAssignment.click(); //click on the fileBased assignment
            String tab = newAssignment.activeTab.getText().trim();
            if(!tab.equals("New Assignment"))
                CustomAssert.fail("Instructor navigate to New Assignment tab.","Instructor does not navigate to New Assignment tab.");

            // Verify save for later button...........
            String saveButton =newAssignment.saveForLater_Button.getText().trim();
            if(!saveButton.contains("SAVE FOR LATER"))
                CustomAssert.fail("\"SAVE FOR LATER\" button is Present in New Assignment tab.","\"SAVE FOR LATER\" button is absent in New Assignment tab.");

            //Verify Assign now button...........
            String assignButton = newAssignment.assignNowButton.getText().trim();
            if(!assignButton.contains("ASSIGN NOW"))
                CustomAssert.fail("\"ASSIGN NOW\" button is Present in New Assignment tab.","\"ASSIGN NOW\" button is absent in New Assignment tab.");

            newAssignment.uploadFileButton.click();//click on the upload file link;


        } catch (Exception e) {
            Assert.fail("Exception in TC createFileBasedAssignment of class NewAssignmentPage",e);
        }
    }
}
