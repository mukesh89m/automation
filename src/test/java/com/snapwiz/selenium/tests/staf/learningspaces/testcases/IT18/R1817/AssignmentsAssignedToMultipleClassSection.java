package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1817;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.ClassSectionDropDown;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by sumit on 7/1/15.
 */
public class AssignmentsAssignedToMultipleClassSection extends Driver{

    @Test(priority = 1, enabled = true)
    public void assignmentsAssignedToMultipleClassSection()
    {
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            ClassSectionDropDown classSectionDropDown = PageFactory.initElements(driver, ClassSectionDropDown.class);

            String class1 = ReadTestData.readDataByTagName("", "context_title", "227");
            String class2 = ReadTestData.readDataByTagName("", "context_title", "228");
            new Assignment().create(227);
            new LoginUsingLTI().ltiLogin("227");		//login as instructor of cs1
            new LoginUsingLTI().ltiLogin("228");		//login as same instructor in cs2


            new LoginUsingLTI().ltiLogin("227_1");		//login as student1 of cs1
            new LoginUsingLTI().ltiLogin("227_2");    //login as student2 of cs1

            new LoginUsingLTI().ltiLogin("228_1");		//login as student1 of cs2
            new LoginUsingLTI().ltiLogin("228_2");    //login as student2 of cs2

            new LoginUsingLTI().ltiLogin("227");		//login a instructor
            new Assignment().assignToStudent(227);//assign to cs1
            classSectionDropDown.defaultClassSectionName.click();
            classSectionDropDown.classSectionName_list.get(3).click();
            new Assignment().assignToStudent(228);//assign to cs2
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-assign for cs2
            currentAssignments.getYesForUnAssign_button().click();//click on Yes link

            classSectionDropDown.defaultClassSectionName.click();
            classSectionDropDown.classSectionName_list.get(2).click();
            new Navigator().NavigateTo("Current Assignments");
            boolean unAssign1 = new BooleanValue().presenceOfElement(201, By.className("delete-assigned-task"));
            Assert.assertEquals(unAssign1, true, "In classsection2, \"Unassign Assignment\" option is not present for Assignment if it is deleted for classsection1.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class AssignmentsAssignedToMultipleClassSection in method assignmentsAssignedToMultipleClassSection.", e);
        }
    }

}
