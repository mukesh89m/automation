package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1917;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CreateCourse;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by Mukesh on 3/18/15.
 */
public class OrionPolicyConfiguration extends Driver {
    @Test(priority = 1, enabled = true)
    public void productDesignerPolicyConfiguration() {

        try {
            CreateCourse createCourse= PageFactory.initElements(driver,CreateCourse.class);
            CourseOutline courseOutline=PageFactory.initElements(driver,CourseOutline.class);
            new DirectLogin().loginWithDifferentRole(211,"ROLE_PRODUCT_DESIGNER",228);
            new SelectCourse().navigateTODifferentCourse("Learning Space"); //navigate to ls course
            Thread.sleep(3000);
            createCourse.lsCourse.click();
            courseOutline.courseDetails.click(); //click on the courseDetails icon

            //Tc row no 227
            Assert.assertEquals(courseOutline.courseAssignmentPolicyLink.getText().trim(), "Course Settings", "Course Settings is not available with LS course");
            createCourse.changeCourse_link.click();
            Thread.sleep(2000);
            new SelectCourse().navigateTODifferentCourse("Learning Space + Adaptive Component"); //navigate to ls adp course
            createCourse.orionCourse.click(); //select lsAdpCourse
            courseOutline.courseDetails.click(); //click on the courseDetails icon
            //Tc row no 228
            Assert.assertEquals(courseOutline.courseAssignmentPolicyLink.getText().trim(), "Course Settings", "Course assignment policy is not available with LS + Adaptive course");

            createCourse.changeCourse_link.click();
            Thread.sleep(2000);
            new SelectCourse().navigateTODifferentCourse("Adaptive Component"); //navigate to orion course
            createCourse.adaptiveCourse.click();
            courseOutline.courseDetails.click(); //click on the courseDetails icon
            //Tc row no 228
            boolean found=new BooleanValue().presenceOfElement(228, By.linkText("Course Assignment Policy"));
            Assert.assertEquals(found,false,"Course assignment policy is available with orion course");



        } catch (Exception e) {
            Assert.fail("Exception in test case productDesignerPolicyConfiguration of class OrionPolicyConfiguration",e);


        }
    }

    @Test(priority = 2, enabled = true)
    public void SMEConfigurationPolicy() {

        try {
            //Tc row no 230
            CreateCourse createCourse= PageFactory.initElements(driver,CreateCourse.class);
            CourseOutline courseOutline=PageFactory.initElements(driver,CourseOutline.class);
            new DirectLogin().loginWithDifferentRole(211,"ROLE_SUBJECT_MATTER_EXPERT",228);
            new SelectCourse().navigateTODifferentCourse("Learning Space"); //navigate to ls course
            Thread.sleep(3000);
            createCourse.orionCourse.click();
            courseOutline.courseDetails.click(); //click on the courseDetails icon

            //Tc row no 227
            Assert.assertEquals(courseOutline.courseAssignmentPolicyLink.getText().trim(), "Course Settings", "Course Settings is not available with LS course");
            createCourse.changeCourse_link.click();
            Thread.sleep(2000);
            new SelectCourse().navigateTODifferentCourse("Learning Space + Adaptive Component"); //navigate to ls adp course
            createCourse.orionCourse.click(); //select lsAdpCourse
            courseOutline.courseDetails.click(); //click on the courseDetails icon
            //Tc row no 228
            Assert.assertEquals(courseOutline.courseAssignmentPolicyLink.getText().trim(), "Course Settings", "Course Settings is not available with LS + Adaptive course");

            createCourse.changeCourse_link.click();
            Thread.sleep(2000);
            new SelectCourse().navigateTODifferentCourse("Adaptive Component"); //navigate to orion course
            createCourse.orionCourse.click();
            courseOutline.courseDetails.click(); //click on the courseDetails icon
            //Tc row no 228
            boolean found=new BooleanValue().presenceOfElement(228, By.linkText("Course Settings"));
            Assert.assertEquals(found,false,"Course Settings is available with orion course");



        } catch (Exception e) {
            Assert.fail("Exception in test case SMEConfigurationPolicy of class OrionPolicyConfiguration",e);


        }
    }

    @Test(priority = 3, enabled = true)
    public void superAdminConfigurationPolicy() {

        try {
            //Tc row no 224
            CreateCourse createCourse= PageFactory.initElements(driver,CreateCourse.class);
            CourseOutline courseOutline=PageFactory.initElements(driver,CourseOutline.class);
            new DirectLogin().directLoginWithCreditial("wiley1","snapwiz",224);
            new SelectCourse().navigateTODifferentCourse("Learning Space"); //navigate to ls course
            Thread.sleep(3000);
            createCourse.orionCourse.click();
            courseOutline.courseDetails.click(); //click on the courseDetails icon

            //Tc row no 227
            Assert.assertEquals(courseOutline.courseAssignmentPolicyLink.getText().trim(), "Course Settings", "Course Settings is not available with LS course");
            createCourse.changeCourse_link.click();
            Thread.sleep(2000);
            new SelectCourse().navigateTODifferentCourse("Learning Space + Adaptive Component"); //navigate to ls adp course
            createCourse.orionCourse.click(); //select lsAdpCourse
            courseOutline.courseDetails.click(); //click on the courseDetails icon
            //Tc row no 228
            Assert.assertEquals(courseOutline.courseAssignmentPolicyLink.getText().trim(), "Course Settings", "Course Settings is not available with LS + Adaptive course");

            createCourse.changeCourse_link.click();
            Thread.sleep(2000);
            new SelectCourse().navigateTODifferentCourse("Adaptive Component"); //navigate to orion course
            createCourse.orionCourse.click();
            courseOutline.courseDetails.click(); //click on the courseDetails icon
            //Tc row no 228
            boolean found=new BooleanValue().presenceOfElement(228, By.linkText("Course Settings"));
            Assert.assertEquals(found,false,"Course Settings is available with orion course");



        } catch (Exception e) {
            Assert.fail("Exception in test case superAdminConfigurationPolicy of class OrionPolicyConfiguration",e);


        }
    }
}
