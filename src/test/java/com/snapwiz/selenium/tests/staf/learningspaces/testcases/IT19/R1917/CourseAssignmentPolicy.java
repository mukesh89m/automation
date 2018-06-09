package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1917;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CreateCourse;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Mukesh on 3/10/15.
 */
public class CourseAssignmentPolicy extends Driver {

    @Test(priority = 1, enabled = true)
    public void courseAssignmentPolicy() {
        try {
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            new DirectLogin().CMSLogin(); //CMS login as author
            new SelectCourse().selectcourse();
            courseOutline.courseDetails.click(); //click on the courseDetails icon

            //Tc row no 11
            //It should show "Course Details" option and an additional option as “Course Assignment Policy"
            Assert.assertEquals(courseOutline.courseDetails.getText().trim(), "Course Details", "Course Details is not displaying");
            Assert.assertEquals(courseOutline.courseAssignmentPolicyLink.getText().trim(), "Course Settings", "Course Assignment Policy is not displaying");

            //Tc row no 12
            //Click on "Course Assignment Policy option"
            courseOutline.courseAssignmentPolicyLink.click();//Click on "Course Assignment Policy option"
            Thread.sleep(3000);
            //Expected:Dropdown should get closed and "a popup should open".
            List<WebElement> list = driver.findElements(By.xpath("//*[text()='Course Details']"));
            Assert.assertTrue(list.size() > 0, "Dropdown is displayed after clicking on course Assignment Policy link");
            Assert.assertEquals(courseOutline.courseAssignmentPolicyHeader.isDisplayed(), true, " popup is not getting open");

            //Tc row no 13
            //Expected :"It should show the title as ""Course Assignment Policy"" in Green color.
            Thread.sleep(5000);
            String textColor = courseOutline.courseAssignmentPolicyHeader.getCssValue("color");
            System.out.println("textColor:" + textColor);
            if (!textColor.trim().equals("rgba(26, 137, 2, 1)"))
                Assert.fail("Course Assignment Policy in Green color");
            Assert.assertEquals(courseOutline.courseAssignmentPolicyHeader.getText().trim(), "Course Settings", "Course Assignment Policy title is not displaying");

            //Tc row no 14 """Help icon"" should be there after ""Title"".
            Assert.assertEquals(courseOutline.helpIcon_position.isDisplayed(), true, "Help icon is not after Title");

            //Tc row no 15
            //Click on help icon next to "Title".
            courseOutline.getHelpIcon().click();//click on the help pop up
            String popText = "Use this setting to enable or disable course level options which are applicable to specific courses only.";
            Assert.assertEquals(courseOutline.helpText.getText().trim(), popText, "Use this policy to enable or disable special policy setting which are applicable to specific courses only. is not showing");

            //Tc row no 16
            //Expected:Language grading policy label should be available
            Assert.assertEquals(courseOutline.gradingPolicy_Text.get(0).getText().trim(), "Language grading policy", "Language grading policy is not available");
            //Tc row no 17
            //Expected:"Help icon" should also be there after "Language grading policy".
            Assert.assertEquals(courseOutline.helpIcon_afterPolicy.isDisplayed(), true, "Help icon is not after Language grading policy");

            //Tc row no 18
            //7.Click on help icon next to “Language grading policy
            courseOutline.helpIcon_afterPolicy.click(); //click on the help icon after Language grading policy
            //Expected:
            String popText1 = "Disable option will hide the “Language Options” field in the create assignment policy page. Enable this to appear in Spanish or other language courses where accents are to be evaluated.";
            Assert.assertEquals(courseOutline.helpText_afterPolicy.getText().trim(), popText1, "Help message is not displaying");

            //Tc row no 19
            //Expected:Two values with its radio button"Enable" & "Disable" should be there.
            Assert.assertEquals(courseOutline.enable_radioButton_Text.isDisplayed(), true, "Enable radio button is not displayed");
            Assert.assertEquals(courseOutline.disable_radioButton_Text.get(0).isDisplayed(), true, "disable radio button is not displayed");

            //Tc row no 20
            //Cancel link should be there.
            Assert.assertEquals(courseOutline.cancel_button.getText().trim(), "Cancel", "Cancel link is not available");
            //Tc row no 21
            Assert.assertEquals(courseOutline.save_button.getText().trim(), "Save", "Save button is not available");

            //Tc row no 22
            //The default value for “Language grading policy” should be “Disable”.
            Assert.assertEquals(courseOutline.disable_radioButton_Text.get(0).isEnabled(), true, "Default value of Language grading policy is not disable");

            //Tc row no 23
            //"8. Select value ""Enable"". 9.Click on ""Cancel Link"". "
            courseOutline.enable_radioButton.click();//click on the enable radio button
            courseOutline.cancel_button.click();//click on the cancel button

            Thread.sleep(3000);
            //Expected:" The popup should be closed & the changes should not be saved."
            List<WebElement> list1 = driver.findElements(By.className("course-assignment-policy-body-content"));
            Assert.assertTrue(list1.size() == 0, "Dropdown is displayed after clicking on course Assignment Policy link");
            //Tc row no 25
            goToCourseAssignmentPolicy(25);
            Thread.sleep(2000);
            courseOutline.enable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();
            goToCourseAssignmentPolicy(25);

            Assert.assertEquals(courseOutline.enable_radioButton.isSelected(), true, "Default value of Language grading policy is not disable");

            goToCourseAssignmentPolicy(26);
            courseOutline.disable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();
            goToCourseAssignmentPolicy(26);

            Assert.assertEquals(courseOutline.disable_radioButton.isSelected(), true, "Default value of Language grading policy is not disable");


        } catch (Exception e) {
            Assert.fail("Exception in test case courseAssignmentPolicy of class CourseAssignmentPolicy:", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void courseAssignmentPolicyWhileCourseCreation() {
        try {
            //Tc row no 27
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            CreateCourse createCourse = PageFactory.initElements(driver, CreateCourse.class);
            new DirectLogin().CMSLogin(); //CMS login as author
            createCourse.createCourse_link.click(); //create course link
            Thread.sleep(5000);
            //Expected :It should navigate to "Create New course" page.
            Assert.assertEquals(createCourse.createCourse_title.getText().trim(), "Create New course", "Author is not navigated to \"Create New course\" page.");

            // Tc row no 28
            //3.Fill all the required fields & click on "Step 2: Course Outline" button
            createCourse.course_name.sendKeys("Biology second edition");
            createCourse.author_name.sendKeys("Mukesh");
            createCourse.course_description.sendKeys("Description");
            createCourse.courseType_dropdown.click(); //click on the courseDropDown link
            createCourse.courseType_lsadp.click();
            createCourse.publisherr.sendKeys("wileyplus");
            createCourse.CourseOutline_link.click(); //click on the step 2

            //Expected :It should navigate to "Course Outline" page.
            Assert.assertEquals(createCourse.courseOutline_title.getText().trim(), "Step 2: Course Outline", "Author is not navigated \"Course Outline\" page");
            //Expected:"Course Details" icon on Header should be there.
            courseOutline.courseDetails.click(); //click on the courseDetails icon

            //Tc row no 30
            courseOutline.courseAssignmentPolicyLink_LS.click();//Click on "Course Assignment Policy option"
            Thread.sleep(3000);
            //Expected:"Course Assignment policy" popup should be displayed
            Assert.assertEquals(courseOutline.courseAssignmentPolicyHeader.isDisplayed(), true, " popup is not getting open");

            //Tc row no 31
            //6.Click on "Add Content And Learning Objectives" button
            createCourse.addContent_img.click();//create add content img
            Assert.assertEquals(createCourse.addContent_link.getAttribute("title").trim(), "Step 3: Add Content & Learning Objectives", "Author is not navigated to Step 3: Add Content & Learning Objectives page");

            goToCourseAssignmentPolicy(32);
            Thread.sleep(2000);
            courseOutline.enable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();

            goToCourseAssignmentPolicy(25);
            Assert.assertEquals(courseOutline.enable_radioButton.isSelected(), true, "Default value of Language grading policy is not disable");

            //Tc row no 33
            createCourse.addContent_link.click(); //click on the Step 3: Add Content & Learning Objectives
            createCourse.finish_button.click();//click on the finish button

            Assert.assertEquals(createCourse.manageContent_Tab.getText().trim(), "Manage Content", "Author is not navigated to the Manage Content");
            goToCourseAssignmentPolicy(26);
            Assert.assertEquals(courseOutline.enable_radioButton.isSelected(), true, "Default value of Language grading policy is not disable");


        } catch (Exception e) {
            Assert.fail("Exception in test case courseAssignmentPolicyWhileCourseCreation of class CourseAssignmentPolicy:", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void policyConfigurationByAuthor() {
        try {
            //Tc row no 36
            Policies policies = PageFactory.initElements(driver, Policies.class);
            MyQuestionBank myQuestionBank=PageFactory.initElements(driver,MyQuestionBank.class);
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(36));
            String policyDescription = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(36));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(36));

            new AssignmentPolicy().CMSDisablePolicy(36);

            new LoginUsingLTI().ltiLogin("36"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the +new Assignment policy link
            // Expected:"New Policy" tab should be displayed.
            Assert.assertEquals(driver.findElement(By.xpath("//span[text()='New Policy']")).isDisplayed(), true, "New Policy tab is displayed");
            //Expected2: The “Language accent” option should not be shown there on the assignment policy page.
            isTextVisible("Language accent"); //check for text Language accent is visible or not in the page

            new AssignmentPolicy().policyCreation(assignmentPolicyName, policyDescription, 36); //create a policy
            //Expected 3:Copy policy link should be available for newly created policy
            new Navigator().navigateToTab("Assignment Policies"); //navigate to Assignment Policies tab
            Assert.assertEquals(policies.copyPolicy_link.isDisplayed(),true,"Copy policy link is not available for newly created policy");
            //Expected 4:Language accent option should not be available over copy policy page
            policies.copyPolicy_link.click(); //click on the copy policy link
            isTextVisible("Language accent"); //check for text Language accent is visible or not in the page

            //Expected 5:Language accent option should not be available over update policy page

            new Navigator().navigateToTab("Assignment Policies"); //navigate to Assignment Policies tab
            policies.updatePolicy_link.click(); //click on the update policy link
            isTextVisible("Language accent"); //check for text Language accent is visible or not in the page

            new Assignment().create(36);
            new Assignment().addQuestions(36,"truefalse","");

            new LoginUsingLTI().ltiLogin("36");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(36);//assign to student

            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.viewPolicy_link.click();
            isTextVisible("Language accent"); //check for text Language accent is visible or not in the page
            new Navigator().navigateToTab("Assignment Policies"); //navigate to Assignment Policies tab
            isTextVisible("Language accent"); //check for text Language accent is visible or not in the page

            new Navigator().NavigateTo("My Question Bank"); //Navigate to question bank
            new Navigator().navigateToTab("Question Banks"); //Navigate to Question Banks

            myQuestionBank.allResourse_textarea.clear();
            myQuestionBank.allResourse_textarea.sendKeys("\"" + assessmentName + "\"");

            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(3000);
            myQuestionBank.getAssignThis().click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myQuestionBank.gradable_checkBox);
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",myQuestionBank.selectPolicy);
            Thread.sleep(2000);
            driver.findElement(By.linkText(assignmentPolicyName)).click();//select a policy

            //expected:Language accent option should not be available over view policy page opened from eye icon
            myQuestionBank.eyeIcon.click(); //click on the eye icon
            isTextVisible("Language accent"); //check for text Language accent is visible or not in the page*/


        } catch (Exception e) {
            Assert.fail("Exception in test case policyConfigurationByAuthor of class CourseAssignmentPolicy:", e);
        }
    }
    @Test(priority = 4, enabled = true)
    public void policyWithAccentOption() {
        try {
            //Tc row no 46
            Policies policies = PageFactory.initElements(driver, Policies.class);
            MyQuestionBank myQuestionBank=PageFactory.initElements(driver,MyQuestionBank.class);
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(46));
            String policyDescription = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(46));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(46));

            new AssignmentPolicy().CMSEnablePolicy(46);

            new LoginUsingLTI().ltiLogin("46"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the new Assignment policy link

            new AssignmentPolicy().policyCreation(assignmentPolicyName,policyDescription,46);
            new AssignmentPolicy().CMSDisablePolicy(46);

            new LoginUsingLTI().ltiLogin("46"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            //Expected 1:"Copy Policy" link should not be there for that policy.
            List<WebElement> list = driver.findElements(By.xpath("//a[@class='copy-policy']"));
            System.out.println("list size:"+list.size());
            System.out.println("list size:"+list.size());
            System.out.println("list size:"+list.size());

            if(list.size()>0)
            {
                Assert.fail("Copy Policy link is displayed that policy");
            }

            //Tc row no 47
            //6.Click on "Update Policy" link.
            policies.updatePolicy_link.click(); //click on the update policy link
            //Expected :"Policy" page should open in new tab.
            if(driver.findElements(By.className("tab_title")).size()<1)
                Assert.fail("Policy\" page in not opened new tab");

            //Tc row no 48
            //"""Language accent"" option dropdown should not be displayed.
            isTextVisible("Language accent"); //check for text Language accent is visible or not in the page*//*

            //Tc row no 49
            //8.Associate this "Policy" with an assignment.

            new Assignment().create(46);

            new LoginUsingLTI().ltiLogin("46"); //login as instructor
            new Navigator().NavigateTo("My Question Bank"); //Navigate to question bank
            new Navigator().navigateToTab("Question Banks"); //Navigate to Question Banks

            myQuestionBank.allResourse_textarea.clear();
            myQuestionBank.allResourse_textarea.sendKeys("\"" + assessmentName + "\"");

            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(3000);
            myQuestionBank.getAssignThis().click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myQuestionBank.gradable_checkBox);
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",myQuestionBank.selectPolicy);
            Thread.sleep(2000);
            driver.findElement(By.linkText(assignmentPolicyName)).click();//select a policy
            //Expected:
            String policyMessage="The language grading policy has been disabled by your publisher for this course.";
            Assert.assertEquals(policies.policy_Message.getText().trim(),policyMessage,"The language grading policy has been disabled by your publisher for this course message is not displaying");

            //Tc row no 50
            new AssignmentPolicy().CMSEnablePolicy(46);
            new LoginUsingLTI().ltiLogin("46"); //login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(46);//assign to student
            new AssignmentPolicy().CMSDisablePolicy(46);

            new LoginUsingLTI().ltiLogin("46"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.viewPolicy_link.click();
            isTextVisible("Language accent"); //check for text Language accent is visible or not in the page


        } catch (Exception e) {
            Assert.fail("Exception in test case policyWithAccentOption of class CourseAssignmentPolicy:", e);
        }
    }
    @Test(priority = 5, enabled = true)
    public void policyWithAccentOptionNone() {
        try {
            //Tc row no 156
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            Policies policies = PageFactory.initElements(driver, Policies.class);
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(50));
            String policyDescription = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(50));


            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            goToCourseAssignmentPolicy(50);
            Thread.sleep(2000);
            courseOutline.enable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();

            new LoginUsingLTI().ltiLogin("50"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the new Assignment policy link

            new AssignmentPolicy().policyCreation(assignmentPolicyName, policyDescription, 50);

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            goToCourseAssignmentPolicy(50);

            courseOutline.disable_radioButton.click();//click on the disable radio button
            courseOutline.save_button.click();


            new LoginUsingLTI().ltiLogin("50"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy

            Assert.assertEquals(policies.copyPolicy_link.isDisplayed(),true,"Copy policy link is not available for newly created policy");
            //6.Click on "Update Policy" link.
            policies.updatePolicy_link.click(); //click on the update policy link
            //Expected :"Policy" page should open in new tab.
            if(driver.findElements(By.className("tab_title")).size()<1)
                Assert.fail("Policy\" page in not opened new tab");
            //"Language accent"" option dropdown should not be displayed.
            isTextVisible("Language accent"); //check for text Language accent is visible or not in the page

            //8.Associate this "Policy" with an assignment.

            new Assignment().create(50);
            new LoginUsingLTI().ltiLogin("50");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(50);//assign to student
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.viewPolicy_link.click();
            isTextVisible("Language accent"); //check for text Language accent is visible or not in the page


        } catch (Exception e) {
            Assert.fail("Exception in test case policyWithAccentOptionNone of class CourseAssignmentPolicy:", e);
        }
    }
    @Test(priority = 6, enabled = true)
    public void policyWithReassign() {
        try {
            //Tc row no 46
            Policies policies = PageFactory.initElements(driver, Policies.class);
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);

            String assignmentPolicyName1 = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(56));
            String policyDescription1 = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(56));

            String assignmentPolicyName2 = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(57));
            String policyDescription2 = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(57));

            String assignmentPolicyName3 = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(58));
            String policyDescription3 = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(58));

            String assignmentPolicyName4 = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(59));
            String policyDescription4 = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(59));

            String assignmentPolicyName5 = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(60));
            String policyDescription5 = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(60));

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            goToCourseAssignmentPolicy(50);
            Thread.sleep(2000);
            courseOutline.enable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();

            new Assignment().create(56); //A1
            new Assignment().create(57);//A2
            new Assignment().create(58);//A3
            new Assignment().create(59);//A4
            new LoginUsingLTI().ltiLogin("56"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the new Assignment policy link
            new AssignmentPolicy().policyCreation(assignmentPolicyName1, policyDescription1, 56);
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the new Assignment policy link
            new AssignmentPolicy().policyCreation(assignmentPolicyName2, policyDescription2, 57);
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the new Assignment policy link
            new AssignmentPolicy().policyCreation(assignmentPolicyName3, policyDescription3, 58);
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the new Assignment policy link
            new AssignmentPolicy().policyCreation(assignmentPolicyName4, policyDescription4, 59);
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the new Assignment policy link


            new LoginUsingLTI().ltiLogin("56");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(56);//assign to student

            new LoginUsingLTI().ltiLogin("57");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(57);//assign to student

            new LoginUsingLTI().ltiLogin("58");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(58);//assign to student

            new LoginUsingLTI().ltiLogin("59");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(59);//assign to student


            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            goToCourseAssignmentPolicy(46);
            courseOutline.disable_radioButton.click();//click on the disable radio button
            courseOutline.save_button.click();

            new LoginUsingLTI().ltiLogin("60"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the new Assignment policy link
            new AssignmentPolicy().policyCreation(assignmentPolicyName5, policyDescription5, 60);


            new LoginUsingLTI().ltiLogin("56");//login as instructor

            //Expected 1:Instructor should be able to reassign A1 with P3
            selectPolicy(0, 56, assignmentPolicyName3);
            //Expected 2:Instructor should be able to reassign A1 with P4
            selectPolicy(0,56,assignmentPolicyName4);
            //Expected 3:Instructor should be able to reassign A1 with P2/P5
            selectPolicy(0,56,assignmentPolicyName2);
            selectPolicy(0,56,assignmentPolicyName5);

            //Expected 4:Instructor should be not able to reassign A2 with P1/P3/P4
            selectPolicyForNotification(0,56,assignmentPolicyName1);
            selectPolicyForNotification(0,56,assignmentPolicyName3);
            selectPolicyForNotification(0,56,assignmentPolicyName4);

            //Expected 5:Instructor should be able to reassign A2 with P5
            selectPolicy(1,56,assignmentPolicyName5);
            //Expected 6:Instructor should be able to reassign A3 with P1
            selectPolicy(2,56,assignmentPolicyName1);
            //Expected 7:Instructor should be able to reassign A3 with P4
            selectPolicy(2,56,assignmentPolicyName4);
            //Expected 8:Instructor should be able to reassign A3 with P2/P5
            selectPolicy(2,56,assignmentPolicyName2);

            selectPolicy(2,56,assignmentPolicyName5);

            //Expected 9:Instructor should not be able to reassign A3 with P1/P3/P4
            selectPolicyForNotification(2,56,assignmentPolicyName1);
            selectPolicyForNotification(2,56,assignmentPolicyName3);
            selectPolicyForNotification(2,56,assignmentPolicyName4);

            //Expected 10:Instructor should be able to reassign A4 with P1/P3
            selectPolicy(3,56,assignmentPolicyName1);
            selectPolicy(3,56,assignmentPolicyName3);

            //Expected 11:Instructor should be able to reassign A4 with P2/P5
            selectPolicy(3,56,assignmentPolicyName2);
            selectPolicy(3,56,assignmentPolicyName5);
            //Expected 12 Instructor should not be able to reassign A4 with P1/P3/P4
            selectPolicyForNotification(3,56,assignmentPolicyName1);
            selectPolicyForNotification(3,56,assignmentPolicyName3);
            selectPolicyForNotification(3,56,assignmentPolicyName4);

            //Tc row no 69 1.Login as author and set the policy as enabled

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            goToCourseAssignmentPolicy(46);
            courseOutline.enable_radioButton.click();//click on the disable radio button
            courseOutline.save_button.click();

            new LoginUsingLTI().ltiLogin("56");//login as instructor
            //Expected 12 Instructor should be able to reassign A1 with any policy
            selectPolicy(0,56,assignmentPolicyName3);

            //Expected 13 Instructor should be able to reassign A2 with any policy
            selectPolicy(1,56,assignmentPolicyName4);

            //Expected 14 Instructor should be able to reassign A3 with any policy
            selectPolicy(2,56,assignmentPolicyName1);

            //Expected 15 Instructor should be able to reassign A3 with any policy
            selectPolicy(3,56,assignmentPolicyName2);



        } catch (Exception e) {
            Assert.fail("Exception in test case policyWithReassign of class CourseAssignmentPolicy:", e);
        }
    }
    @Test(priority = 7, enabled = true)
    public void assignmentWithPolicyAccentOptionSelected() {
        try {
            //Tc row no 73
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            Policies policies = PageFactory.initElements(driver, Policies.class);
            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(73));
            String policyDescription = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(73));
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            CurrentAssignments currentAssignment=PageFactory.initElements(driver,CurrentAssignments.class);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            goToCourseAssignmentPolicy(73);
            courseOutline.enable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();

            new LoginUsingLTI().ltiLogin("73"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the new Assignment policy link

            new AssignmentPolicy().policyCreation(assignmentPolicyName,policyDescription,73);

            new Assignment().create(73);

            new LoginUsingLTI().ltiLogin("73");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(73);//assign to student

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            goToCourseAssignmentPolicy(73);
            courseOutline.disable_radioButton.click();//click on the disable radio button
            courseOutline.save_button.click();

            new LoginUsingLTI().ltiLogin("73_1"); //login as student
            new Navigator().NavigateTo("Assignments");
            assignments.getAssignmentName().click(); //click on the assignment name
            new AttemptQuestion().trueFalse(false,"correct",73);
            new Assignment().submitButtonInQuestionClick();

            Thread.sleep(3000);
            assignments.getGoToNextLinkOnPopUp().click(); //click on the finish assignment

            new LoginUsingLTI().ltiLogin("73"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignment.getViewGrade_link().click();//click on view student responses
            String assigmentStatus=driver.findElements(By.className("ls-assignment-status-grades-released")).get(1).getText();
            Assert.assertEquals(assigmentStatus,"Graded","Assignment status not displaying graded");


        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentWithPolicyAccentOptionSelected of class CourseAssignmentPolicy:", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void reAssignedAssignmentWithPolicyFiftyPer() {
        try {
            //Tc row no 77
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            Policies policies = PageFactory.initElements(driver, Policies.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            CurrentAssignments currentAssignment=PageFactory.initElements(driver,CurrentAssignments.class);

            String assignmentPolicyName1 = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(77));
            String policyDescription1 = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(77));

            String assignmentPolicyName2 = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(78));
            String policyDescription2 = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(78));



            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            goToCourseAssignmentPolicy(77);
            courseOutline.enable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();

            new LoginUsingLTI().ltiLogin("77"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the new Assignment policy link
            new AssignmentPolicy().policyCreation(assignmentPolicyName1,policyDescription1,77);

            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the new Assignment policy link
            new AssignmentPolicy().policyCreation(assignmentPolicyName2,policyDescription2,78);


            new Assignment().create(77);
            new Assignment().addQuestions(77,"textentry","");

            new LoginUsingLTI().ltiLogin("77");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(77);//assign to student

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            goToCourseAssignmentPolicy(77);
            courseOutline.disable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();

            new LoginUsingLTI().ltiLogin("77");//login as instructor

            selectPolicy(0,78,assignmentPolicyName2);

            new LoginUsingLTI().ltiLogin("77_1");//login as instructor
            new Navigator().NavigateTo("Assignments");
            assignments.getAssignmentName().click(); //click on the assignment name
            new AttemptQuestion().trueFalse(false,"correct",77);
            assignments.getSubmitAssignment().click();
            assignments.getGoToNextLinkOnPopUp().click(); //click on the finish assignment

            Thread.sleep(3000);
            new AttemptQuestion().textEntryWithSpanish(false,"correct",77);
            new Assignment().submitButtonInQuestionClick();
            Thread.sleep(3000);
            assignments.getGoToNextLinkOnPopUp().click(); //click on the finish assignment

            new LoginUsingLTI().ltiLogin("77"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignment.getViewGrade_link().click();//click on view student responses
            new UIElement().waitAndFindElement(By.className("ls-assignment-status-grades-released"));
            String assigmentStatus=driver.findElements(By.className("ls-assignment-status-grades-released")).get(1).getText();
            Assert.assertEquals(assigmentStatus,"Graded","Assignment status not displaying graded");
            new MouseHover().mouserhoverbywebelement(driver.findElement(By.xpath("(//div[@class='idb-gradebook-content-coloumn'])[2]"))); //mouse over view response link
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);

            Assert.assertEquals(currentAssignment.getPerformanceScoreBox().getAttribute("value").trim(),"0.5","50% Language accent policy should get considered and instructor not able to release the grades");



        } catch (Exception e) {
            Assert.fail("Exception in test case reAssignedAssignmentWithPolicyFiftyPer of class CourseAssignmentPolicy:", e);
        }
    }
    @Test(priority = 9, enabled = true)
    public void policySwitchedFromExactToNone() {
        try {
            //Tc row no 79
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            Policies policies = PageFactory.initElements(driver, Policies.class);

            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            CurrentAssignments currentAssignment=PageFactory.initElements(driver,CurrentAssignments.class);

            String assignmentPolicyName1 = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(79));
            String policyDescription1 = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(79));

            String assignmentPolicyName2 = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(80));
            String policyDescription2 = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(80));


            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            goToCourseAssignmentPolicy(79);
            courseOutline.enable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();

            new LoginUsingLTI().ltiLogin("79"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the new Assignment policy link
            new AssignmentPolicy().policyCreation(assignmentPolicyName1,policyDescription1,79);

            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the new Assignment policy link
            new AssignmentPolicy().policyCreation(assignmentPolicyName2,policyDescription2,80);

            new Assignment().create(79);
            new Assignment().addQuestions(79,"textentry","");

            new LoginUsingLTI().ltiLogin("79");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(79);//assign to student

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            goToCourseAssignmentPolicy(79);
            courseOutline.disable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();

            new LoginUsingLTI().ltiLogin("79");//login as instructor

            selectPolicy(0,80,assignmentPolicyName2); //reassign policy

            new LoginUsingLTI().ltiLogin("79_1");//login as student
            new Navigator().NavigateTo("Assignments");
            assignments.getAssignmentName().click(); //click on the assignment name
            new AttemptQuestion().trueFalse(false, "correct", 79);
            assignments.getSubmitAssignment().click();
            Thread.sleep(2000);
            assignments.getGoToNextLinkOnPopUp().click(); //click on the go to next question

            Thread.sleep(3000);
            new AttemptQuestion().textEntryWithSpanish(false,"correct",79);
            new Assignment().submitButtonInQuestionClick();
            Thread.sleep(3000);
            assignments.getGoToNextLinkOnPopUp().click(); //click on the finish assignment


            new LoginUsingLTI().ltiLogin("79"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignment.getViewGrade_link().click();//click on view student responses
            Thread.sleep(4000);

            String assigmentStatus1=driver.findElements(By.className("ls-assignment-status-grades-released")).get(1).getText();
            Assert.assertEquals(assigmentStatus1,"Graded","Assignment status not displaying graded");

            new MouseHover().mouserhoverbywebelement(driver.findElement(By.xpath("(//div[@class='idb-gradebook-content-coloumn'])[2]"))); //mouse over view response link
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);

            Assert.assertEquals(currentAssignment.getPerformanceScoreBox().getAttribute("value").trim(),"0.0","No language accent policy should get considered and instructor not  able to release the grades");




        } catch (Exception e) {
            Assert.fail("Exception in test case policySwitchedFromExactToNone of class CourseAssignmentPolicy:", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void flowEnableDisableEnable() {
        try {
            //Tc row no 82
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            Policies policies = PageFactory.initElements(driver, Policies.class);

            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            CurrentAssignments currentAssignment=PageFactory.initElements(driver,CurrentAssignments.class);

            String assignmentPolicyName1 = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(82));
            String policyDescription1 = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(82));

            String assignmentPolicyName2 = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(83));
            String policyDescription2 = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(83));


            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            goToCourseAssignmentPolicy(82);
            courseOutline.enable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();

            new LoginUsingLTI().ltiLogin("82"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the new Assignment policy link
            new AssignmentPolicy().policyCreation(assignmentPolicyName1,policyDescription1,82);

            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the new Assignment policy link
            new AssignmentPolicy().policyCreation(assignmentPolicyName2,policyDescription2,83);

            new Assignment().create(82);
            new Assignment().addQuestions(82,"textentry","");

            new LoginUsingLTI().ltiLogin("82");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(82);//assign to student

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            goToCourseAssignmentPolicy(82);
            courseOutline.disable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();

            new LoginUsingLTI().ltiLogin("82");//login as instructor

            selectPolicy(0,83,assignmentPolicyName2); //reassign policy

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            goToCourseAssignmentPolicy(82);
            courseOutline.enable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();

            new LoginUsingLTI().ltiLogin("82");//login as instructor

            selectPolicy(0,82,assignmentPolicyName1); //reassign policy


           new LoginUsingLTI().ltiLogin("82_1");//login as student
            new Navigator().NavigateTo("Assignments");
            assignments.getAssignmentName().click(); //click on the assignment name
            new AttemptQuestion().trueFalse(false,"correct",82);
            assignments.getSubmitAssignment().click();
            Thread.sleep(2000);
            assignments.getGoToNextLinkOnPopUp().click(); //click on the go to next question

            Thread.sleep(3000);
            new AttemptQuestion().textEntryExactMatch(false, "correct", 82);
            new Assignment().submitButtonInQuestionClick();
            Thread.sleep(3000);
            assignments.getGoToNextLinkOnPopUp().click(); //click on the finish assignment


            new LoginUsingLTI().ltiLogin("82"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignment.getViewGrade_link().click();//click on view student responses
            Thread.sleep(4000);

            String assigmentStatus1=driver.findElements(By.className("ls-assignment-status-grades-released")).get(1).getText();
            Assert.assertEquals(assigmentStatus1,"Graded","Assignment status not displaying graded");

            new MouseHover().mouserhoverbywebelement(driver.findElement(By.xpath("(//div[@class='idb-gradebook-content-coloumn'])[2]"))); //mouse over view response link
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);

            Assert.assertEquals(currentAssignment.getPerformanceScoreBox().getAttribute("value").trim(),"1.0","No language accent policy should get considered and instructor not  able to release the grades");



        } catch (Exception e) {
            Assert.fail("Exception in test case flowEnableDisableEnable of class CourseAssignmentPolicy:", e);
        }
    }

    @Test(priority = 11, enabled = true)
    public void assignmentPolicyWithDisable() {
        try {
            //Tc row no 85
            Policies policies = PageFactory.initElements(driver, Policies.class);

            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            CurrentAssignments currentAssignment=PageFactory.initElements(driver,CurrentAssignments.class);

            String assignmentPolicyName1 = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(85));
            String policyDescription2 = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(85));



             new LoginUsingLTI().ltiLogin("85"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the new Assignment policy link
            new AssignmentPolicy().policyCreation(assignmentPolicyName1,policyDescription2,85);

            new Assignment().create(85);
            new Assignment().addQuestions(85,"textentry","");

            new LoginUsingLTI().ltiLogin("85");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(85);//assign to student*//*


            new LoginUsingLTI().ltiLogin("85_1");//login as student
            new Navigator().NavigateTo("Assignments");
            assignments.getAssignmentName().click(); //click on the assignment name
            new AttemptQuestion().trueFalse(false,"correct",85);
            assignments.getSubmitAssignment().click();
            Thread.sleep(2000);
            assignments.getGoToNextLinkOnPopUp().click(); //click on the go to next question


            Thread.sleep(3000);
            new AttemptQuestion().textEntryWithSpanish(false,"correct",85);
            new Assignment().submitButtonInQuestionClick();
            Thread.sleep(3000);
            assignments.getGoToNextLinkOnPopUp().click(); //click on the finish assignment

            new LoginUsingLTI().ltiLogin("85"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignment.getViewGrade_link().click();//click on view student responses
            Thread.sleep(4000);

            String assigmentStatus1=driver.findElements(By.className("ls-assignment-status-grades-released")).get(1).getText();
            Assert.assertEquals(assigmentStatus1,"Graded","Assignment status not displaying graded");

            new MouseHover().mouserhoverbywebelement(driver.findElement(By.xpath("(//div[@class='idb-gradebook-content-coloumn'])[2]"))); //mouse over view response link
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);

            Assert.assertEquals(currentAssignment.getPerformanceScoreBox().getAttribute("value").trim(),"0.0","No language accent policy should get considered and instructor not  able to release the grades");



        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentPolicyWithDisable of class CourseAssignmentPolicy:", e);
        }
    }
    public  void goToCourseAssignmentPolicy(int dataIndex) throws InterruptedException {
        CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
        courseOutline.courseDetails.click(); //click on the courseDetails icon
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@rel='3']")).click();

    }


    public  void isTextVisible(String text) throws InterruptedException {
        boolean found= driver.findElement(By.xpath("//div[text()='Language accent']")).isDisplayed();
        Assert.assertEquals(found,false,"Language accent” option is displayed");


    }

    public  void selectPolicy(int index,int dataIndex,String assignmentPolicyName ) throws InterruptedException {

        CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
        MyQuestionBank myQuestionBank=PageFactory.initElements(driver,MyQuestionBank.class);

        new Navigator().NavigateTo("Assignments");//navigate to Assignments
        currentAssignments.updateAssignment_link.get(index).click(); //click on the update assignment
        currentAssignments.getReassign_button().click(); //click on the reassign button

        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",myQuestionBank.selectPolicy_dropdown);
        Thread.sleep(2000);
        driver.findElement(By.linkText(assignmentPolicyName)).click();//select a policy
        myQuestionBank.getAssignOnPopUp().click(); //click on the assign this
        driver.findElement(By.cssSelector("span[class='confirm-submit-yes submit-assign']")).click();


    }
    public  void selectPolicyForNotification(int index,int dataIndex,String assignmentPolicyName ) throws InterruptedException {
        Policies policies = PageFactory.initElements(driver, Policies.class);

        CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
        MyQuestionBank myQuestionBank=PageFactory.initElements(driver,MyQuestionBank.class);

        new Navigator().NavigateTo("Assignments");//navigate to Assignments
        currentAssignments.updateAssignment_link.get(index).click(); //click on the update assignment
        currentAssignments.getReassign_button().click(); //click on the reassign button

        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",myQuestionBank.selectPolicy_dropdown);
        Thread.sleep(2000);
        driver.findElement(By.linkText(assignmentPolicyName)).click();//select a policy

        String policyMessage="The language grading policy has been disabled by your publisher for this course.";
        Assert.assertEquals(policies.policy_Message.getText().trim(),policyMessage,"The language grading policy has been disabled by your publisher for this course message is not displaying");

    }

}
