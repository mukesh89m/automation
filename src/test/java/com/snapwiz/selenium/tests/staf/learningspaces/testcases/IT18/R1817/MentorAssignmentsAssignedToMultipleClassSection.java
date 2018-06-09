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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by sumit on 8/1/15.
 */
public class MentorAssignmentsAssignedToMultipleClassSection extends Driver{
    @Test(priority = 1, enabled = true)
    public void mentorAssignmentsAssignedToMultipleClassSection()
    {
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            ClassSectionDropDown classSectionDropDown = PageFactory.initElements(driver, ClassSectionDropDown.class);

            String class1 = ReadTestData.readDataByTagName("", "context_title", "229");
            String class2 = ReadTestData.readDataByTagName("", "context_title", "230");
            new Assignment().create(229);
            new LoginUsingLTI().ltiLogin("229");		//login as mentor of cs1
            new LoginUsingLTI().ltiLogin("230");		//login as same mentor in cs2


            new LoginUsingLTI().ltiLogin("229_1");		//login as student1 of cs1
            new LoginUsingLTI().ltiLogin("229_2");    //login as student2 of cs1

            new LoginUsingLTI().ltiLogin("230_1");		//login as student1 of cs2
            new LoginUsingLTI().ltiLogin("230_2");    //login as student2 of cs2

            new LoginUsingLTI().ltiLogin("229");		//login a mentor
            new Assignment().assignToStudent(229);//assign to cs1
            classSectionDropDown.defaultClassSection.click();
            WebElement element=driver.findElement(By.xpath("//div[@title='"+class2+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
            new Assignment().assignToStudent(230);//assign to cs2
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-assign for cs2
            currentAssignments.getYesForUnAssign_button().click();//click on Yes link
            classSectionDropDown.defaultClassSection.click();
            WebElement element1=driver.findElement(By.xpath("//div[@title='"+class1+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
            element1.click();
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
