package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelperAppium;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Config;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium.Dashboard_appiumPF;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium.HomePage_appium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by Dharaneesha on 04/08/15.
 */
public class SelectCourse_appium extends Driver{
    /*This method selects the course based on the couse name given in config.xml*/
    //HomePage_appium homePage = PageFactory.initElements(appiumDriver,HomePage_appium.class);
    //Dashboard_appiumPF dashBoard = PageFactory.initElements(appiumDriver,Dashboard_appiumPF.class);

    public void selectCourse(){
        try{
            if(Config.course!=null){
                if(Config.course.equalsIgnoreCase("Visualizing Human Geography: At Home in a Diverse World")){
                    homePage.link_visualizingHumanGeography.click();//Click on specified Course
                    if(!dashBoard.icon_accountUser.isEnabled()){
                        Assert.fail("Visualizing Geograpy Course is not opened");
                    }
                }else if(Config.course.equalsIgnoreCase("Geography: Realms, Regions, and Concepts - Sixteenth Edition")){
                    homePage.link_geography.click();//Click on specified Course
                    if(!dashBoard.icon_accountUser.isEnabled()){
                        Assert.fail("Geograpy Course is not opened");
                    }
                }else if(Config.course.equalsIgnoreCase("Biology")){
                    homePage.link_biology.click();//Click on specified Course
                    if(!dashBoard.icon_accountUser.isEnabled()){
                        Assert.fail("Biology Course is not opened");
                    }
                }else if(Config.course.equalsIgnoreCase("Human Resources Management in the Hospitality Industry, 2nd Edition")){
                    homePage.link_hrManagement.click();//Click on specified Course
                    if(!dashBoard.icon_accountUser.isEnabled()){
                        Assert.fail("Human Resources Management Course is not opened");
                    }
                }
            }else{
                Assert.fail("Course Name is not provided in Config file");
            }
        }catch(Exception e){
            Assert.fail("Exception in apphelper 'selectCourse' in the class 'SelectCourse_Appium'",e);
        }
    }
}
