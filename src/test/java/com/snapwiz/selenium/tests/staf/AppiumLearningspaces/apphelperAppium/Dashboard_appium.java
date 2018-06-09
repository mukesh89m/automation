package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelperAppium;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium.LoginPage_appium;
import org.testng.Assert;

/**
 * Created by Dharaneesha on 04/08/15.
 */
public class Dashboard_appium extends Driver{

    public void navigateToInstructorDashboard(){
        try{
            new DirectLogin_appium().loginAsInstructor();
            new SelectCourse_appium().selectCourse();
        }catch(Exception e){
            Assert.fail("Exception in the apphelper 'navigateToInstructorDashboard' in the class 'Dashboard_appium'",e);
        }
    }


    public void navigateToMentorDashboard(){
        try{
            new DirectLogin_appium().loginAsMentor();
            new SelectCourse_appium().selectCourse();

        }catch(Exception e){
            Assert.fail("Exception in the apphelper 'navigateToMentorDashboard' in the class 'Dashboard_appium'",e);
        }
    }

    public void navigateToStudent1Dashboard(){
        try{
            new DirectLogin_appium().loginAsStudent1();
            Thread.sleep(3000);
            new SelectCourse_appium().selectCourse();
        }catch(Exception e){
            Assert.fail("Exception in the apphelper 'navigateToStudent1Dashboard' in the class 'Dashboard_appium'",e);
        }
    }


    public void navigateToStudent2Dashboard(){
        try{
            new DirectLogin_appium().loginAsStudent2();
            new SelectCourse_appium().selectCourse();
        }catch(Exception e){
            Assert.fail("Exception in the apphelper 'navigateToStudent2Dashboard' in the class 'Dashboard_appium'",e);
        }
    }

}
