package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelperAppium;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Config;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.UIElement;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelperAppium.TouchActions;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelperAppium.Wait;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Snapwiz on 14/08/15.
 */
public class LoginUsingRandomUser_appium extends Driver {
    public void loginAsRandomInstructor1(String dataIndex){
        try{

            String userId= ReadTestData.readDataByTagName("", "userId", dataIndex);
            String courseName= ReadTestData.readDataByTagName("", "courseName", dataIndex);
            String role= ReadTestData.readDataByTagName("", "role", dataIndex);
            String coureId= ReadTestData.readDataByTagName("", "coureId", dataIndex);
            String contextId= ReadTestData.readDataByTagName("", "contextId", dataIndex);
            String contextTitle= ReadTestData.readDataByTagName("", "contextTitle", dataIndex);
            String domainId= ReadTestData.readDataByTagName("", "domainId", dataIndex);

           /* System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            for(int a=0;a<new Exception().getStackTrace().length;a++){
                String classNames = new Exception().getStackTrace()[a].getClassName();
                System.out.println("Class Names in loginUsingRandomUser : " + classNames);
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            for(int a=0;a<new Exception().getStackTrace().length;a++){
                String MethodNames = new Exception().getStackTrace()[a].getMethodName();
                System.out.println("Method Names loginUsingRandomUser : " + MethodNames);
            }

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");*/




            //IT22_R225_instructor_10

            String packageName = new Exception().getStackTrace()[1].getClassName();
            String methodName = new Exception().getStackTrace()[1].getMethodName();

            packageName = packageName.substring(64);
            packageName = packageName.replaceAll("\\.", "_");
            System.out.println("packageName 1 : " +packageName);


            if(userId ==null){
                userId = "Ins_"+packageName+"_"+methodName+"_"+appendChar;
            }

            if(courseName==null){
                courseName = Config.course;
            }

            if(role==null){
                role = "instructor";
            }


            if(coureId==null){
                coureId = Config.courseid;
            }


            if(contextId==null){
                contextId = "cid_"+packageName+"_"+methodName+"_"+appendChar;
            }

            if(contextTitle==null){
                contextTitle = "ctitle_"+packageName+"_"+methodName+"_"+appendChar;
            }

            if(domainId==null){
                domainId = "dmId_"+packageName+"_"+methodName+"_"+appendChar;
            }


            System.out.println("Special UserId 2: " + userId);
            System.out.println("Course Id " + coureId);

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            randomLoginPage.textField_userId.sendKeys(userId);
            randomLoginPage.textField_courseName.sendKeys(courseName);
            randomLoginPage.textField_role.sendKeys(role);
            randomLoginPage.textField_courseId.sendKeys(coureId);
            randomLoginPage.textField_contextId.sendKeys(contextId);
            randomLoginPage.textField_contextTitle.sendKeys(contextTitle);
            randomLoginPage.textField_domainId.sendKeys(domainId);
            randomLoginPage.button_submit.click();
            homePage.link_biologyThroughRandomLogin.click();
        }catch(Exception e){
            Assert.fail("Exception in the apphelper 'loginAsRandomInstructor' and in the class 'LoginUsingRandomUser_appium'",e);
        }
    }



    public ArrayList<String> loginAsRandomInstructor(String dataIndex){
        ArrayList<String> userInfoList = new ArrayList<>();
        try{

            String userId= ReadTestData.readDataByTagName("", "userId", dataIndex);
            String courseName= ReadTestData.readDataByTagName("", "courseName", dataIndex);
            String role= ReadTestData.readDataByTagName("", "role", dataIndex);
            String coureId= ReadTestData.readDataByTagName("", "coureId", dataIndex);
            String contextId= ReadTestData.readDataByTagName("", "contextId", dataIndex);
            String contextTitle= ReadTestData.readDataByTagName("", "contextTitle", dataIndex);
            String domainId= ReadTestData.readDataByTagName("", "domainId", dataIndex);


            System.out.println("new Exception().getStackTrace()[1] : "+ new Exception().getStackTrace().toString());
            System.out.println("PACKAGE NAME : "+ new Exception().getStackTrace()[1].getClassName());
            System.out.println("methodName : "+ new Exception().getStackTrace()[1].getMethodName());


            String packageName = new Exception().getStackTrace()[1].getClassName();
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            packageName = packageName.substring(64);
            System.out.println("packageName:"+packageName);

            packageName = packageName.replaceAll("\\.", "_");
            if(userId ==null){
                //userId = "Ins_"+packageName+"_"+methodName+"_"+dataIndex+"_"+appendChar;
                userId = "Ins_"+methodName+"_"+dataIndex+"_"+appendChar;

                userInfoList.add(userId);
                System.out.println("userId : " + userId);
            }
            if(courseName==null){
                courseName = Config.course;
            }
            if(role==null){
                role = "instructor";
            }
            if(coureId==null){
                coureId = Config.courseid;
            }
            if(contextId==null){
                contextId = "cid_"+methodName+"_"+dataIndex+"_"+appendChar;
                userInfoList.add(contextId);
            }
            if(contextTitle==null){
                contextTitle = "ctitle_"+methodName+"_"+dataIndex+"_"+appendChar;
                userInfoList.add(contextTitle);
            }
            if(domainId==null){
                domainId = "dmId_"+methodName+"_"+dataIndex+"_"+appendChar;
                userInfoList.add(domainId);
            }
            Thread.sleep(5000);
            loginPage.link_RandomUser.click();
            randomLoginPage.textField_userId.sendKeys(userId);
            randomLoginPage.textField_courseName.sendKeys(courseName);
            randomLoginPage.textField_role.sendKeys(role);
            randomLoginPage.textField_courseId.sendKeys(coureId);
            randomLoginPage.textField_contextId.sendKeys(contextId);
            randomLoginPage.textField_contextTitle.sendKeys(contextTitle);
            randomLoginPage.textField_domainId.sendKeys(domainId);
            randomLoginPage.button_submit.click();
            Thread.sleep(15000);
            new Wait().visibilityOf(homePage.link_biologyThroughRandomLogin);
            homePage.link_biologyThroughRandomLogin.click();
            Thread.sleep(7000);
            new Wait().visibilityOf(dashBoard.icon_accountUser);
            if(!dashBoard.icon_accountUser.isDisplayed()){
                Assert.fail("Instructor is not able to login to the dashboard");
            }
        }catch(Exception e){
            Assert.fail("Exception in the apphelper 'loginAsRandomInstructor' and in the class 'LoginUsingRandomUser_appium'",e);
        }
        return  userInfoList;
    }


    public ArrayList<String> loginAsRandomStudent(String dataIndex){
        ArrayList<String> userInfoList = new ArrayList<>();
        try{

            String userId= ReadTestData.readDataByTagName("", "userId", dataIndex);
            String courseName= ReadTestData.readDataByTagName("", "courseName", dataIndex);
            String role= ReadTestData.readDataByTagName("", "role", dataIndex);
            String coureId= ReadTestData.readDataByTagName("", "coureId", dataIndex);
            String contextId= ReadTestData.readDataByTagName("", "contextId", dataIndex);
            String contextTitle= ReadTestData.readDataByTagName("", "contextTitle", dataIndex);
            String domainId= ReadTestData.readDataByTagName("", "domainId", dataIndex);

            String packageName = new Exception().getStackTrace()[1].getClassName();
            String methodName = new Exception().getStackTrace()[1].getMethodName();

            packageName = packageName.substring(64);
            packageName = packageName.replaceAll("\\.", "_");
            if(userId ==null){
                userId = "stu_"+methodName+"_"+dataIndex+"_"+appendChar;
                userInfoList.add(userId);
                System.out.println("UserId : " + userId);
            }

            if(courseName==null){
                courseName = Config.course;
            }

            if(role==null){
                role = "student";
            }


            if(coureId==null){
                coureId = Config.courseid;
            }


            if(contextId==null){
                contextId = "cid_"+methodName+"_"+dataIndex+"_"+appendChar;
                userInfoList.add(contextId);
            }

            if(contextTitle==null){

                contextTitle = "ctitle_"+methodName+"_"+dataIndex+"_"+appendChar;
                userInfoList.add(contextTitle);
            }

            if(domainId==null){
                domainId = "dmId_"+methodName+"_"+dataIndex+"_"+appendChar;
                userInfoList.add(domainId);
            }

            loginPage.link_RandomUser.click();

            randomLoginPage.textField_userId.sendKeys(userId);

            try {
                randomLoginPage.textField_courseName.sendKeys(courseName);

            }
            catch (Exception ex){
                ex.printStackTrace();
            }


            randomLoginPage.textField_role.sendKeys(role);
            randomLoginPage.textField_courseId.sendKeys(coureId);
            randomLoginPage.textField_contextId.sendKeys(contextId);
            randomLoginPage.textField_contextTitle.sendKeys(contextTitle);
            randomLoginPage.textField_domainId.sendKeys(domainId);
            randomLoginPage.button_submit.click();
            Thread.sleep(9000);
            new UIElement().waitAndFindElement(homePage.link_biologyThroughRandomLogin);
            homePage.link_biologyThroughRandomLogin.click();
            Thread.sleep(5000);
            new Wait().visibilityOf(dashBoard.icon_accountUser);
            if(!dashBoard.icon_accountUser.isDisplayed()){
                Assert.fail("The Student should be successfully logged in to the application");
            }


        }catch(Exception e){
            Assert.fail("Exception in the apphelper 'loginAsRandomInstructor' and in the class 'LoginUsingRandomUser_appium'",e);
        }
        return  userInfoList;
    }

    public void logOutUser(){
        try{
            Thread.sleep(15000);
            dashBoard.icon_logOut.click();
            Thread.sleep(3000);
            dashBoard.option_logOut.click();
        }catch(Exception e){
            Assert.fail("Exception in Apphelper 'logOutUser' in the class 'logOutUser'",e);
        }
    }

}
