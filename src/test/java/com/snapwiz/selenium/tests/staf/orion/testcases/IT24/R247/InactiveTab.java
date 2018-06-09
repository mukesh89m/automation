package com.snapwiz.selenium.tests.staf.orion.testcases.IT24.R247;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.Dashboard.ClassSectionDropDown;
import com.snapwiz.selenium.tests.staf.orion.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.orion.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by mukesh on 12/11/15.
 */
public class InactiveTab {
    @Test(priority = 1,enabled = true)
    public  void inActiveTab(){
        try {
            //TC row no 55
            Driver.startDriver();
            ClassSectionDropDown classSectionDropDown= PageFactory.initElements(Driver.driver, ClassSectionDropDown.class);
            new LoginUsingLTI().ltiLogin("55");//login as instructor
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown

            Assert.assertEquals(classSectionDropDown.classSectionTab.size(), 3, "Popup is not showing 3 tabs at bottom of the footer.");
            new UIElement().waitAndFindElement(classSectionDropDown.active_tab);
            Thread.sleep(4000);
            Assert.assertEquals(classSectionDropDown.classSectionTab.get(0).getText(),"Active");
            Assert.assertEquals(classSectionDropDown.classSectionTab.get(1).getText(),"Inactive");
            Assert.assertEquals(classSectionDropDown.classSectionTab.get(2).getText(),"Finished");
            WebElement active=classSectionDropDown.active_tab;
            WebElement inActive=classSectionDropDown.inactive_tab;
            WebElement finish=classSectionDropDown.finished_tab;

            Assert.assertEquals(classSectionDropDown.byDefaultActive.getText(),"Inactive","Active Tab is not visible.");
            if(classSectionDropDown.equals("Active")){
                Assert.fail("Inactive is not disable.");
            }

            if(classSectionDropDown.equals("Finished")){
                Assert.fail("Finished is  not disable.");
            }

            new LoginUsingLTI().ltiLogin("67");//login as instructor
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown

            List<WebElement> classSectionText=Driver.driver.findElements(By.className("js-sections-list-drop-down"));
            String classSectionTextcolor = verifyColor(classSectionText.get(1));

            String activeTextColor = null;
            activeTextColor= verifyColor(active);
            String activeBgColor =null;
            activeBgColor=verifyBgColor(active);

            String inactiveTextColor =null;
            inactiveTextColor=verifyColor(inActive);
            String inactiveBgColor =null;
            inactiveBgColor=verifyBgColor(inActive);

            String finishedTexColor =null;
            finishedTexColor=verifyColor(finish);
            String finishedBgColor = null;
            finishedBgColor=verifyBgColor(finish);

            Assert.assertEquals(activeTextColor, "#4287ab","Text Color of Active Tabs not in Blue.");//blue
            Assert.assertEquals(inactiveTextColor,"#4287ab","Text Color of Inactive Tabs not in Blue.");
            Assert.assertEquals(finishedTexColor,"#4287ab","Text Color of Finished Tabs not in Blue.");

            Assert.assertEquals(classSectionTextcolor,"#515151","7.Text Color of  Class section not in Black.");//gray color
            Assert.assertEquals(activeBgColor,"#000000","Background of Active Tab is in white color.");
            Assert.assertEquals(inactiveBgColor,"#ffffff","Background of InActive Tab is not in white color.");
            Assert.assertEquals(finishedBgColor,"#000000","Background of Finished Tab is in white color.");


            //TC row no 25
            classSectionText.get(1).click();//click on the firsr class section name
            new UIElement().waitAndFindElement(classSectionDropDown.defaultClassSectionName);
            for(int i=1;i<3;i++){
                try {
                    Assert.assertEquals(classSectionDropDown.defaultClassSectionName.getAttribute("title").trim(),classSectionText.get(1).getText(),"User is not able to navigate to that class section's Dashboard with Refreshing page and take to Dashboard.");
                    break;
                } catch (Exception e) {
                }
            }

            //Tc row no 27
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            finish.click(); //click on the inactive class section Tab.
            activeBgColor=verifyBgColor(active);
            inactiveBgColor=verifyBgColor(inActive);
            finishedBgColor=verifyBgColor(finish);

            Assert.assertEquals(inactiveBgColor, "#000000", "Background of InActive Tab is  in white color.");
            Assert.assertEquals(finishedBgColor,"#ffffff","Background of Finished Tab is not in white color.");
            Assert.assertEquals(activeBgColor,"#000000","Background of Active Tab is in white color.");

        } catch (Exception e) {
            Assert.fail("Exception in TC inActiveTAB of class InactiveTab",e);

        }
    }


    @Test(priority = 2,enabled = true)
    public  void inactiveTabForMoreThanTenClassSection(){
        try {
            //TC row no 68
            Driver.startDriver();
            ClassSectionDropDown classSectionDropDown= PageFactory.initElements(Driver.driver, ClassSectionDropDown.class);
            new LoginUsingLTI().ltiLogin("67");//login as instructor
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            Thread.sleep(3000);
            new MouseHover().mouserHoverByClassList("js-sections-list-drop-down", 2); //mouse hover on class section
            Assert.assertEquals(classSectionDropDown.scrollBarEnable.get(1).isDisplayed(), true, "Scroll bar is not display for  class section dropdown list in InActive tab.");

            classSectionDropDown.active_tab.click(); //click on the inactive class section Tab
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg.get(0).getText(), "There are no classes in this tab.", "\"Default message \"There are no class sections in this tab\" not displayed.");
            Assert.assertEquals(classSectionDropDown.scrollBarEnable.get(0).isDisplayed(), false, "Scroll bar is not display for  class section dropdown list in Active tab.");

            classSectionDropDown.finished_tab.click(); //click on the inactive class section Tab
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg.get(1).getText(),"There are no classes in this tab.","\"Default message \"There are no class sections in this tab\" not displayed.");

        } catch (Exception e) {
            Assert.fail("Exception in TC inactiveTabForMoreThanTenClassSection of class InactiveTab",e);

        }
    }



    /*//Finished Tab*/

    @Test(priority = 3,enabled = true)
    public  void finished(){
        try {
            //TC row no 72
            Driver.startDriver();
            ClassSectionDropDown classSectionDropDown= PageFactory.initElements(Driver.driver, ClassSectionDropDown.class);
            new LoginUsingLTI().ltiLogin("72");//login as instructor
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown

            Assert.assertEquals(classSectionDropDown.classSectionTab.size(), 3, "Popup is not showing 3 tabs at bottom of the footer.");
            new UIElement().waitAndFindElement(classSectionDropDown.active_tab);
            Thread.sleep(4000);
            Assert.assertEquals(classSectionDropDown.classSectionTab.get(0).getText(),"Active");
            Assert.assertEquals(classSectionDropDown.classSectionTab.get(1).getText(),"Inactive");
            Assert.assertEquals(classSectionDropDown.classSectionTab.get(2).getText(),"Finished");
            WebElement active=classSectionDropDown.active_tab;
            WebElement inActive=classSectionDropDown.inactive_tab;
            WebElement finish=classSectionDropDown.finished_tab;

            Assert.assertEquals(classSectionDropDown.byDefaultActive.getText(),"Finished","Active Tab is not visible.");
            if(classSectionDropDown.equals("Active")){
                Assert.fail("Inactive is not disable.");
            }

            if(classSectionDropDown.equals("Inactive")){
                Assert.fail("Finished is  not disable.");
            }

            new LoginUsingLTI().ltiLogin("84");//login as instructor
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown

            List<WebElement> classSectionText=Driver.driver.findElements(By.className("js-sections-list-drop-down"));
            String classSectionTextcolor = verifyColor(classSectionText.get(1));

            String activeTextColor = null;
            activeTextColor= verifyColor(active);
            String activeBgColor =null;
            activeBgColor=verifyBgColor(active);

            String inactiveTextColor =null;
            inactiveTextColor=verifyColor(inActive);
            String inactiveBgColor =null;
            inactiveBgColor=verifyBgColor(inActive);

            String finishedTexColor =null;
            finishedTexColor=verifyColor(finish);
            String finishedBgColor = null;
            finishedBgColor=verifyBgColor(finish);

            Assert.assertEquals(activeTextColor, "#4287ab","Text Color of Active Tabs not in Blue.");//blue
            Assert.assertEquals(inactiveTextColor,"#4287ab","Text Color of Inactive Tabs not in Blue.");
            Assert.assertEquals(finishedTexColor,"#4287ab","Text Color of Finished Tabs not in Blue.");

            Assert.assertEquals(classSectionTextcolor,"#515151","7.Text Color of  Class section not in Black.");//gray color
            Assert.assertEquals(activeBgColor,"#000000","Background of Active Tab is in white color.");
            Assert.assertEquals(inactiveBgColor,"#000000","Background of InActive Tab is  in white color.");
            Assert.assertEquals(finishedBgColor,"#ffffff","Background of Finished Tab is not in white color.");


            //TC row no 25
            classSectionText.get(1).click();//click on the firsr class section name
            new UIElement().waitAndFindElement(classSectionDropDown.defaultClassSectionName);
            for(int i=1;i<3;i++){
                try {
                    Assert.assertEquals(classSectionDropDown.defaultClassSectionName.getAttribute("title").trim(),classSectionText.get(1).getText(),"User is not able to navigate to that class section's Dashboard with Refreshing page and take to Dashboard.");
                    break;
                } catch (Exception e) {
                }
            }

            //Tc row no 27
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            inActive.click(); //click on the inactive class section Tab.
            activeBgColor=verifyBgColor(active);
            inactiveBgColor=verifyBgColor(inActive);
            finishedBgColor=verifyBgColor(finish);

            Assert.assertEquals(inactiveBgColor, "#ffffff", "Background of InActive Tab is not in white color.");
            Assert.assertEquals(finishedBgColor,"#000000","Background of Finished Tab is in white color.");
            Assert.assertEquals(activeBgColor,"#000000","Background of Active Tab is in white color.");

        } catch (Exception e) {
            Assert.fail("Exception in TC finished of class InactiveTab",e);

        }
    }

    @Test(priority = 4,enabled = true)
    public  void finishedTabForMoreThanTenClassSection(){
        try {
            //TC row no 84
            Driver.startDriver();
            ClassSectionDropDown classSectionDropDown= PageFactory.initElements(Driver.driver, ClassSectionDropDown.class);
            new LoginUsingLTI().ltiLogin("84");//login as instructor
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            Thread.sleep(3000);
            new MouseHover().mouserHoverByClassList("js-sections-list-drop-down", 2); //mouse hover on class section
            Assert.assertEquals(classSectionDropDown.scrollBarEnable.get(2).isDisplayed(), true, "Scroll bar is not display for  class section dropdown list in Finished tab.");

            classSectionDropDown.active_tab.click(); //click on the inactive class section Tab
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg.get(0).getText(), "There are no classes in this tab.", "\"Default message \"There are no class sections in this tab\" not displayed.");
            Assert.assertEquals(classSectionDropDown.scrollBarEnable.get(0).isDisplayed(), false, "Scroll bar is not display for  class section dropdown list in Active tab.");

            classSectionDropDown.inactive_tab.click(); //click on the inactive class section Tab
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg.get(1).getText(),"There are no classes in this tab.","\"Default message \"There are no class sections in this tab\" not displayed.");

        } catch (Exception e) {
            Assert.fail("Exception in TC finishedTabForMoreThanTenClassSection of class InactiveTab",e);

        }
    }

    @Test(priority = 5,enabled = true)
    public  void activeToInactiveAndFinished(){
        try {
            //TC row no 94
            Driver.startDriver();
            ClassSectionDropDown classSectionDropDown= PageFactory.initElements(Driver.driver, ClassSectionDropDown.class);
            String context_title = ReadTestData.readDataByTagName("", "context_title1", "94");

            new LoginUsingLTI().ltiLogin("94");//login as instructor
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            Assert.assertEquals(classSectionDropDown.classSectionName_list.get(1).getAttribute("title").trim(), context_title, "Classsection which was Active previously should be seen here.");

            classSectionDropDown.inactive_tab.click(); //click on the inactive class section Tab
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg.get(0).getText(),"There are no classes in this tab.","\"Default message \"There are no class sections in this tab\" not displayed.");

            classSectionDropDown.finished_tab.click(); //click on the inactive class section Tab
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg.get(1).getText(),"There are no classes in this tab.","\"Default message \"There are no class sections in this tab\" not displayed.");

            new LoginUsingLTI().ltiLogin("95");//login as instructor(Inactive)
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg1.getText(), "You are currently viewing the only class in this status.", "\"Default message \"There are no class sections in this tab\" not displayed.");
            classSectionDropDown.inactive_tab.click(); //click on the inactive class section Tab
            Assert.assertEquals(classSectionDropDown.classSectionName_list.get(1).getAttribute("title").trim(), context_title, "Classsection which was Active previously should be seen here.");
            classSectionDropDown.finished_tab.click(); //click on the inactive class section Tab
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg.get(0).getText(),"There are no classes in this tab.","\"Default message \"There are no class sections in this tab\" not displayed.");


            new LoginUsingLTI().ltiLogin("96");//login as instructor(Finished)
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg1.getText(), "You are currently viewing the only class in this status.", "Classsection which was Active is not  Removed here.");
            classSectionDropDown.inactive_tab.click(); //click on the inactive class section Tab
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg.get(0).getText(), "There are no classes in this tab.", "\"Default message \"There are no class sections in this tab\" not displayed.");
            classSectionDropDown.finished_tab.click(); //click on the inactive class section Tab
            Assert.assertEquals(classSectionDropDown.classSectionName_list.get(1).getAttribute("title").trim(), context_title, "Classsection which was Active previously should be seen here.");

        } catch (Exception e) {
            Assert.fail("Exception in TC activeToInactiveAndFinished of class InactiveTab",e);

        }
    }

    @Test(priority = 6,enabled = true)
    public  void inactiveToActiveAndFinished(){
        try {
            //TC row no 107
            Driver.startDriver();
            ClassSectionDropDown classSectionDropDown= PageFactory.initElements(Driver.driver, ClassSectionDropDown.class);
            String context_title = ReadTestData.readDataByTagName("", "context_title1", "107");

            new LoginUsingLTI().ltiLogin("107");//login as instructor(Inactive)
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            Assert.assertEquals(classSectionDropDown.classSectionName_list.get(1).getAttribute("title").trim(), context_title, "Class section name is not displaying  in InActive Tab");
            Thread.sleep(2000);
            classSectionDropDown.active_tab.click();
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg.get(0).getText(), "There are no classes in this tab.", "\"Default message \"There are no class sections in this tab\" not displayed.");
            Thread.sleep(2000);
            classSectionDropDown.finished_tab.click(); //click on the Finished class section Tab
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg.get(1).getText(),"There are no classes in this tab.","\"Default message \"There are no class sections in this tab\" not displayed.");

            new LoginUsingLTI().ltiLogin("111");//login as instructor(active)
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg1.getText(), "You are currently viewing the only class in this status.", "Classsection which was InActive previously should not be seen here.");
            classSectionDropDown.active_tab.click(); //click on the active class section Tab
            Thread.sleep(2000);
            Assert.assertEquals(classSectionDropDown.classSectionName_list.get(0).getAttribute("title").trim(), context_title, "Classsection which was in InActive should be seen in Active tab.");
            classSectionDropDown.finished_tab.click(); //click on the inactive class section Tab
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg.get(0).getText(),"There are no classes in this tab.","\"Default message \"There are no class sections in this tab\" not displayed.");


            new LoginUsingLTI().ltiLogin("115");//login as instructor(Finished)
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg1.getText(), "You are currently viewing the only class in this status.", "Classsection which was Active is not  Removed here.");
            classSectionDropDown.active_tab.click(); //click on the inactive class section Tab
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg.get(0).getText(), "There are no classes in this tab.", "\"Default message \"There are no class sections in this tab\" not displayed.");
            classSectionDropDown.finished_tab.click(); //click on the inactive class section Tab
            Assert.assertEquals(classSectionDropDown.classSectionName_list.get(1).getAttribute("title").trim(), context_title, "Classsection which was Active previously should be seen here.");

        } catch (Exception e) {
            Assert.fail("Exception in TC inactiveToActiveAndFinished of class InactiveTab", e);

        }
    }


    public static String verifyColor(WebElement element){
        String color=element.getCssValue("color");
        Color col= Color.fromString(color);
        String hexValue=col.asHex();
        System.out.println(col.asHex());
        return hexValue;
    }


    public static String verifyBgColor(WebElement element){
        String color=element.getCssValue("background-color");
        Color col= Color.fromString(color);
        String hexValue=col.asHex();
        System.out.println(col.asHex());
        return hexValue;
    }
}
