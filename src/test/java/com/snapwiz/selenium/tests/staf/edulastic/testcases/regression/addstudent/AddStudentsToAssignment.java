package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.addstudent;

import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AddStudentToAssignment;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assign;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Murthi on 12/29/2016.
 */
public class AddStudentsToAssignment extends Driver {

    private Assign assignPage;
    private SchoolPage schoolPage;
    StudentResponse studentResponsePage;
    AddStudentToAssignment addStudentToAssignmentPage;

    @BeforeMethod
    public void init() {
        WebDriver driver = Driver.getWebDriver();
        addStudentToAssignmentPage = PageFactory.initElements(driver, AddStudentToAssignment.class);
        schoolPage = PageFactory.initElements(driver, SchoolPage.class);
        assignPage = PageFactory.initElements(driver, Assign.class);
        studentResponsePage = PageFactory.initElements(driver, StudentResponse.class);
    }

    @Test(enabled = false)
    public void validateAddStudentToAssignmentUIElement() {

        ReportUtil.log("Description", "This test case validate the UI elements and it's functionality on the add student to assignment page", "Info");

        WebDriver driver = getWebDriver();
        int dataIndex = 1;

        String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
        String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

        System.out.println("appendChar1:" + appendChar1);
        System.out.println("appendChar2:" + appendChar2);

        String methodName = new Exception().getStackTrace()[0].getMethodName();
        System.out.println(methodName);

        String student1UserName = methodName + "st" + appendChar1;
        String student2UserName = methodName + "st" + appendChar2;

        new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor1
        new School().enterAndSelectSchool("654321369", dataIndex, false);
        schoolPage.getContinueButton().click(); //clicking on continue button
        String classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
        System.out.println("classCode :" + classCode);
        new Navigator().logout();//log out


        new SignUp().studentDirectRegister(appendChar1, classCode, dataIndex);//Sign up as a student1
        new Navigator().logout();//log out

        new SignUp().studentDirectRegister(appendChar2, classCode, dataIndex);//Sign up as a student2
        new Navigator().logout();//log out
        //1. Login as teacher
        new Login().loginAsInstructor(appendChar1, dataIndex);
        //2. Navigate to Assignments Page
        new Assignment().create(dataIndex, "truefalse");
        new Assignment().assignToStudent(dataIndex, appendChar1);//Assign to student 1
        //3. Click on view responses for any assignment
        assignPage.btnViewResponse.click();
        WebDriverUtil.waitForAjax(driver, 60);

        //validate add students button on student response button
        CustomAssert.assertEquals(studentResponsePage.btnAddStudents.isDisplayed(), true, "Validate 'Add Students' button exist", "The 'Add Students' button exists on the student response page", "The 'Add students' button doesn't exist on the student response page");

        //4. click on add students button
        studentResponsePage.btnAddStudents.click();
        WebDriverUtil.waitForAjax(driver, 60);

        //validate navigation to assign page after click on add students button
        CustomAssert.assertEquals(driver.getCurrentUrl().contains("addStudentToAssignment"), true, "Validate navigation to assign page", "Instructor able to navigate to assign page after click on add students button", "Instructor is not able to navigate to assign page after click on add students button");

        //'Assign This To' field should be blank
        CustomAssert.assertEquals(addStudentToAssignmentPage.isAssignThisToTextBoxEmpty(), true, "Validate Assign This To text box value", "'Assign This To' text box value is empty", "'Assign This To' text box value is not empty");

        //Due Date And Assessment close options should be editable
        CustomAssert.assertEquals(addStudentToAssignmentPage.isAssessmentDueDateEditable(), true, "Validate due date filed is editable", "Due date field is editable", "due date field is not editable");
        CustomAssert.assertEquals(addStudentToAssignmentPage.isAssessmentCloseListBoxEditable(), true, "Validate assessment close list box is editable", "Assessment close list box is editable", "Assessment close list box is not editable");

        //Assignment Name, Total points, Tag, Start date and Assessment open should be non-editable
        CustomAssert.assertEquals(addStudentToAssignmentPage.isAssessmentNameEditable(), false, "Validate Assessment name field is editable", "Assessment name is not editable as expected", "Assessment name shouldn't be editable on add students to assignment page");
        CustomAssert.assertEquals(addStudentToAssignmentPage.isTotalPointsEditable(), false, "Validate Total points field is editable", "Total points is not editable as expected", "Total points shouldn't be editable on add students to assignment page");
        CustomAssert.assertEquals(addStudentToAssignmentPage.isTagsEditable(), false, "Validate Tags field is editable", "Tags is not editable as expected", "Tags shouldn't be editable on add students to assignment page");
        CustomAssert.assertEquals(addStudentToAssignmentPage.isAssessmentStarDateEditable(), false, "Validate Start Date field is editable", "Start Date is not editable as expected", "Start Date shouldn't be editable on add students to assignment page");
        CustomAssert.assertEquals(addStudentToAssignmentPage.isAssessmentOpenListBoxEditable(), false, "Validate Start Date field is editable", "Start Date is not editable as expected", "Start Date shouldn't be editable on add students to assignment page");

        //5. Click on Assign button
        addStudentToAssignmentPage.clickOnAssignButton();

        //Validation should display as "Please enter the student(s) name"
        CustomAssert.assertEquals(addStudentToAssignmentPage.isErrorMessageDisplayedForAssignTo("Please enter the student(s) name"), true, "Verify error message", "Error message 'Please enter the student(s) name' is displayed after giving blank value in 'assign to' field", "Please enter the student(s) name' is not displayed after giving blank value in 'assign to' field");

        //6. Enter student name for whom assignment is not assigned
        addStudentToAssignmentPage.enterTextInAssignThisToTextBox(student2UserName);

        //Auto-suggest list should appear and should display the not assigned student names
        CustomAssert.assertEquals(addStudentToAssignmentPage.isNameExistInSuggestedList(student2UserName), true, "Verify auto suggested list appears with unassigned student", "Auto suggested list appears with unassigned user", "Auto suggested listed is not contains unassigned student " + student2UserName);

        //The List of student shown should NOT included the student already assigned
        CustomAssert.assertEquals(addStudentToAssignmentPage.isNameExistInSuggestedList(student1UserName), false, "Verify auto suggested list appears without assigned student", "Auto suggested list appears without assigned user", "Auto suggested listed is contains already assigned student " + student1UserName);

        //7. Select the student name and click on Assign button
        addStudentToAssignmentPage.selectNameFromAutoSuggestedList(student2UserName);
        addStudentToAssignmentPage.clickOnAssignButton();
        WebDriverUtil.waitForAjax(driver, 60);
        assignPage.btnViewResponse.click();
        WebDriverUtil.waitForAjax(driver, 60);

        boolean isStuCardDisplayed=false;

        for(WebElement studentCard:studentResponsePage.lstStudentCard){
            if(studentCard.getText().contains(student2UserName)){
                isStuCardDisplayed=true;
                break;
            }
        }

        CustomAssert.assertEquals(isStuCardDisplayed,true,"Verify response card for newly added student","response card is displayed for newly added student","response card is not displayed for new added student "+student2UserName);


    }

}
