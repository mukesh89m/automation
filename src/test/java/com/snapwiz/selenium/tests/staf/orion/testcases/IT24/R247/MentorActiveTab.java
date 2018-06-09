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
 * Created by root on 12/3/15.
 */
public class MentorActiveTab extends Driver {
    @Test(priority = 1,enabled = true)
    public  void activeTab(){
        try {
            //TC row no 229
            com.snapwiz.selenium.tests.staf.orion.Driver.startDriver();
            ClassSectionDropDown classSectionDropDown= PageFactory.initElements(com.snapwiz.selenium.tests.staf.orion.Driver.driver, ClassSectionDropDown.class);

            new LoginUsingLTI().ltiLogin("11");//login as instructor
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown

            //Tc row no 12
            Assert.assertEquals(classSectionDropDown.classSectionTab.size(), 3, "Popup is not showing 3 tabs at bottom of the footer.");
            new UIElement().waitAndFindElement(classSectionDropDown.active_tab);
            Thread.sleep(4000);
            Assert.assertEquals(classSectionDropDown.classSectionTab.get(0).getText(),"Active");
            Assert.assertEquals(classSectionDropDown.classSectionTab.get(1).getText(),"Inactive");
            Assert.assertEquals(classSectionDropDown.classSectionTab.get(2).getText(),"Finished");
            WebElement active=classSectionDropDown.active_tab;
            WebElement inActive=classSectionDropDown.inactive_tab;
            WebElement finish=classSectionDropDown.finished_tab;

            Assert.assertEquals(classSectionDropDown.byDefaultActive.getText(),"Active","Active Tab is not visible.");
            if(classSectionDropDown.equals("Inactive")){
                Assert.fail("Inactive is not disable.");
            }

            if(classSectionDropDown.equals("Finished")){
                Assert.fail("Finished is  not disable.");
            }


            List<WebElement> classSectionText= com.snapwiz.selenium.tests.staf.orion.Driver.driver.findElements(By.className("js-sections-list-drop-down"));
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
            Assert.assertEquals(activeBgColor,"#ffffff","Background of Active Tab not in white color.");
            Assert.assertEquals(inactiveBgColor,"#000000","Background of InActive Tab is in white color.");
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
            inActive.click(); //click on the inactive class section Tab.
            activeBgColor=verifyBgColor(active);
            inactiveBgColor=verifyBgColor(inActive);
            finishedBgColor=verifyBgColor(finish);

            Assert.assertEquals(inactiveBgColor, "#ffffff", "Background of InActive Tab is not in white color.");
            Assert.assertEquals(finishedBgColor, "#000000", "Background of Finished Tab is in white color.");
            Assert.assertEquals(activeBgColor,"#000000","Background of Active Tab is in white color.");



        } catch (Exception e) {
            Assert.fail("Exception in TC activeTAB of class ActiveTab",e);

        }
    }

    @Test(priority = 2,enabled = true)
    public  void activeTabForMoreThanTenClassSection(){
        try {
            //TC row no 28
            com.snapwiz.selenium.tests.staf.orion.Driver.startDriver();
            ClassSectionDropDown classSectionDropDown= PageFactory.initElements(com.snapwiz.selenium.tests.staf.orion.Driver.driver,ClassSectionDropDown.class);
            new LoginUsingLTI().ltiLogin("28");//login as instructor
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            Assert.assertEquals(classSectionDropDown.scrollBarEnable.get(0).isDisplayed(), true, "Scroll bar is not display for  class section dropdown list in Active tab.");
            classSectionDropDown.inactive_tab.click(); //click on the inactive class section Tab
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg.get(0).getText(),"There are no classes in this tab.","\"Default message \"There are no class sections in this tab\" not displayed.");

            classSectionDropDown.finished_tab.click(); //click on the inactive class section Tab
            Assert.assertEquals(classSectionDropDown.emptyClassSectionMsg.get(1).getText(),"There are no classes in this tab.","\"Default message \"There are no class sections in this tab\" not displayed.");

        } catch (Exception e) {
            Assert.fail("Exception in TC activeTabForMoreThanTenClassSection of class ActiveTab",e);

        }
    }

    @Test(priority = 3,enabled = true)
    public  void defaultTabHighLightedState(){
        try {
            //TC row no 33
            com.snapwiz.selenium.tests.staf.orion.Driver.startDriver();
            ClassSectionDropDown classSectionDropDown= PageFactory.initElements(com.snapwiz.selenium.tests.staf.orion.Driver.driver,ClassSectionDropDown.class);
            new LoginUsingLTI().ltiLogin("33");//login as instructor active
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            Thread.sleep(4000);
            Assert.assertEquals(classSectionDropDown.byDefaultActive.getText(), "Active", "Default selected tab is not “Active”");

            new LoginUsingLTI().ltiLogin("35");//login as instructor inactive
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            Thread.sleep(4000);
            Assert.assertEquals(classSectionDropDown.byDefaultActive.getText(), "Inactive", "Default selected tab is not “Inactive”");

            new LoginUsingLTI().ltiLogin("37");//login as instructor finishe
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            Thread.sleep(4000);
            Assert.assertEquals(classSectionDropDown.byDefaultActive.getText(), "Finished", "Default selected tab is not “Finished”");


        } catch (Exception e) {
            Assert.fail("Exception in TC defaultTabHighLightedState of class ActiveTab",e);

        }
    }

    @Test(priority = 4,enabled = true)
    public  void ellipsesForAllThreeTab(){
        try {
            //TC row no 39
            com.snapwiz.selenium.tests.staf.orion.Driver.startDriver();
            ClassSectionDropDown classSectionDropDown= PageFactory.initElements(com.snapwiz.selenium.tests.staf.orion.Driver.driver,ClassSectionDropDown.class);
            String context_title = ReadTestData.readDataByTagName("", "context_title", "39");
            String inActiveContext_title = ReadTestData.readDataByTagName("", "context_title", "42");
            String finishedContext_title = ReadTestData.readDataByTagName("", "context_title", "45");
            new LoginUsingLTI().ltiLogin("39");//login as instructor active
            new MouseHover().mouserhoverbyid("drop-down-display-label"); //mouse hover on class section
            Assert.assertEquals(classSectionDropDown.defaultClassSection.getAttribute("title"),context_title,"Complete class section is not displayed as a \"Tooltip\".");

            new LoginUsingLTI().ltiLogin("42");//login as instructor Inactive
            new MouseHover().mouserhoverbyid("drop-down-display-label"); //mouse hover on class section
            Assert.assertEquals(classSectionDropDown.defaultClassSection.getAttribute("title"),inActiveContext_title,"Complete class section is not displayed as a \"Tooltip\".");

            new LoginUsingLTI().ltiLogin("45");//login as instructor Finished
            new MouseHover().mouserhoverbyid("drop-down-display-label"); //mouse hover on class section
            Assert.assertEquals(classSectionDropDown.defaultClassSection.getAttribute("title"),finishedContext_title,"Complete class section is not displayed as a \"Tooltip\".");

        } catch (Exception e) {
            Assert.fail("Exception in TC ellipsesForAllThreeTab of class ActiveTab",e);

        }
    }

    @Test(priority = 5,enabled = true)
    public  void tooltipForAllThreeTab(){
        try {
            //TC row no 48
            com.snapwiz.selenium.tests.staf.orion.Driver.startDriver();
            ClassSectionDropDown classSectionDropDown= PageFactory.initElements(com.snapwiz.selenium.tests.staf.orion.Driver.driver,ClassSectionDropDown.class);
            String context_title = ReadTestData.readDataByTagName("", "context_title", "39");
            String inActiveContext_title = ReadTestData.readDataByTagName("", "context_title2", "42");
            String finishedContext_title = ReadTestData.readDataByTagName("", "context_title2", "45");

            new LoginUsingLTI().ltiLogin("39");//login as instructor active
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            Thread.sleep(2000);
            new MouseHover().mouserhover("js-sections-list-drop-down"); //mouse hover on class section
            Assert.assertEquals(classSectionDropDown.classSectionName_list.get(1).getAttribute("title"),context_title,"Complete class section is not displayed as a \"Tooltip\".");


            new LoginUsingLTI().ltiLogin("42");//login as instructor Inactive
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            classSectionDropDown.inactive_tab.click();
            Thread.sleep(4000);
            new MouseHover().mouserHoverByClassList("js-sections-list-drop-down",2); //mouse hover on class section
            Assert.assertEquals(classSectionDropDown.classSectionName_list.get(2).getAttribute("title"),inActiveContext_title,"Complete class section is not displayed as a \"Tooltip\".");

            new LoginUsingLTI().ltiLogin("45");//login as instructor Finished
            classSectionDropDown.classSection_DropDown.click(); //click on the class section dropdown
            classSectionDropDown.finished_tab.click();
            Thread.sleep(2000);
            new MouseHover().mouserHoverByClassList("js-sections-list-drop-down", 4); //mouse hover on class section
            Assert.assertEquals(classSectionDropDown.classSectionName_list.get(4).getAttribute("title"),finishedContext_title,"Complete class section is not displayed as a \"Tooltip\".");

        } catch (Exception e) {
            Assert.fail("Exception in TC ellipsesForAllThreeTab of class ActiveTab",e);

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
