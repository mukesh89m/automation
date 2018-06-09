package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R2314;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.SearchPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Created by Snapwiz on 13/10/15.
 */
public class CopyQuestions extends Driver {
    @Test
    public void copyQuestionsFromOneCourseToAnother(){
        WebDriver driver=Driver.getWebDriver();
        try{
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
            new UIElement().waitAndFindElement(searchPage.icon_search);
            searchPage.icon_search.click();
            Assert.assertEquals(searchPage.tab_search.getText().trim(), "SEARCH", "It should display search screens.");
            Assert.assertEquals(searchPage.tab_browse.getText().trim(), "BROWSE", "It should display browse screens.");
            

            new UIElement().waitAndFindElement(searchPage.link_bulkOperations);
            searchPage.link_bulkOperations.click();
            new UIElement().waitAndFindElement(searchPage.link_moveAssessments);
            Thread.sleep(3000);
            searchPage.link_moveAssessments.click();
            searchPage.icon_closeBulkOPeration.click();
            searchPage.link_bulkOperations.click();
            searchPage.link_copyAssessments.click();
            Assert.assertEquals(searchPage.label_bulkoperationCopy.getText().trim(), "Bulk Operation : Copy", "1.It should open a pop-up window.");
            List<WebElement> listElements = driver.findElements(By.xpath("//div[@class='cms-dialog-form-content']//a[@href = 'Select your option here']"));


            listElements.get(2).click();
            new UIElement().waitAndFindElement(By.partialLinkText("Ch"));
            new Click().clickbypartiallinkText("Ch");
            new Click().clickbyxpath("//div[@class='cms-assessment-checkbox-row']//input");
            listElements.get(4).click();
            new UIElement().waitAndFindElement(By.partialLinkText("Physical Geology: The Science of Earth"));

            new Click().clickbypartiallinkText("Physical Geology: The Science of Earth");
            Thread.sleep(2000);
            new UIElement().waitAndFindElement(By.xpath("//div[@class ='cms-copy-assessments-to']//..//a[@title = 'Select an option']"));

            new Click().clickbyxpath("//div[@class ='cms-copy-assessments-to']//..//a[@title = 'Select an option']");
            new UIElement().waitAndFindElement(By.partialLinkText("Ch 1: An Introduction to Geology"));
            new Click().clickbypartiallinkText("Ch 1: An Introduction to Geology");
            new UIElement().waitAndFindElement(By.xpath("//div[@class='cms-assessment-radiobtn-row']//input"));
            new Click().clickbyxpath("//div[@class='cms-assessment-radiobtn-row']//input");
            if(searchPage.chechBox_useLOMapping.isSelected()==true){
                Assert.fail("Use LO Mapping should not be checked by default");
            }
            new Click().clickByElement(searchPage.chechBox_useLOMapping);
            Thread.sleep(3000);
            if(searchPage.chechBox_useLOMapping.isSelected()==false){
                Assert.fail("Use LO Mapping should be unchecked by default");
            }

            List<WebElement> elementsList = driver.findElements(By.xpath("//div[@class='cms-assessment-dialog-footer']//i"));

            if(!elementsList.get(0).getText().equals("Use LO mapping")&&elementsList.get(1).getText().equals("Manage Mapping") && elementsList.get(2).getText().equals("Copy as reference only")){
               Assert.fail("The new option 'Use LO mapping' should display above the 'Copy as reference only option");
            }
            searchPage.link_manageMapping.click();
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "# It should display the Header as 'LO Mapping Table'");
            searchPage.icon_close.click();
            searchPage.link_manageMapping.click();
            Assert.assertEquals(searchPage.label_sourceCourse.getText(), "Source Course", "#The left part of heading should indicate 'Source Course'");
            Assert.assertEquals(searchPage.label_destinationCourse.getText(), "Destination Course", "#The rigth part of heading should indicate 'Destination Course'");
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective1.getText(),"Select an Learning Objective","# In third line it should display \"Select a Learning Objective\" as a default text in drop-down and the drop-down should be in  both source and destination places.");
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective2.getText(), "Select an Learning Objective", "# In third line it should display \"Select a Learning Objective\" as a default text in drop-down and the drop-down should be in  both source and destination places.");

           /*if(driver.findElements(By.xpath("(//div[@class='cms-lo-mapping-dialog-wrapper']//select)[3]/option")).size()!=889){
                Assert.fail("It should display all the TLO and ELO of that course in order as supported by product in source places.");
            }

            if(driver.findElements(By.xpath("(//div[@class='cms-lo-mapping-dialog-wrapper']//select)[4]/option")).size()!=202){
                Assert.fail("It should display all the TLO and ELO of that course in order as supported by product in destination places.");
            }*/
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective1.getText(),"Select an Learning Objective","# In third line it should display \"Select a Learning Objective\" as a default text in drop-down and the drop-down should be in  both source and destination places.");
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective1.getText(), "Select an Learning Objective", "# In third line it should display \"Select a Learning Objective\" as a default text in drop-down and the drop-down should be in  both source and destination places.");
            Assert.assertEquals(searchPage.link_addRow.getText(),"+ Add row","It should display '+Add Row' button below to that .");
            Assert.assertEquals(searchPage.button_done.getText(),"Done","It should display \"Done\" button in the footer part.");
            Assert.assertEquals(searchPage.button_cancel.getText(),"Cancel","It should display \"Cancel\"  before to 'Done' option in the footer part.");
            Assert.assertEquals(searchPage.link_clearAll.getText(),"Clear all","It should display \"Clear all\"  option left side of the footer part.");
            searchPage.link_addRow.click();
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective3.getText(), "Select an Learning Objective", "It should add a new line same as of second line.");
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective4.getText(), "Select an Learning Objective", "It should add a new line same as of second line.");
            Assert.assertEquals(searchPage.link_remove.getText(),"- Remove","# It should display “-Remove” link at the end for all the rows apart from second line..");

            for(int a=0;a<5;a++){
                searchPage.link_addRow.click();
            }

            for(int a=1;a<13;a++){
                Assert.assertEquals(driver.findElement(By.xpath("(//select[@class='select-box-mapping'])["+a+"]//option")).getText(), "Select an Learning Objective", "It should add a new line same as of second line.");
            }

            if(driver.findElements(By.linkText("- Remove")).size()!=6){
                Assert.fail("# It should display “-Remove” link at the end for all the rows apart from second line..");
            }
            searchPage.link_remove.click();
            if(driver.findElements(By.linkText("- Remove")).size()!=5){
                Assert.fail("1.The the row should get deleted.");
            }

            for(int a=0;a<5;a++){
                searchPage.link_remove.click();
            }
            Thread.sleep(5000);

            selectTLOs();
            /*List<WebElement> selectList = driver.findElements(By.xpath("//select"));
            for(int a=0;a<selectList.size();a++){
                try{
                    System.out.println("a : " + a);
                    Thread.sleep(3000);
                    new Select(selectList.get(a)).selectByIndex(1);
                }catch (Exception e){
                    System.out.println("Yes");
                }
            }*/




            /*new Select(searchPage.comboBox_selectAnLearningObjective1).selectByIndex(1);
            new Select(searchPage.comboBox_selectAnLearningObjective2).selectByIndex(1);*/
            searchPage.icon_close.click();
            searchPage.link_manageMapping.click();
            Thread.sleep(9000);
            /*new Select(searchPage.comboBox_selectAnLearningObjective1).selectByIndex(1);
            new Select(searchPage.comboBox_selectAnLearningObjective2).selectByIndex(1);*/


            selectTLOs();
            /*selectList = driver.findElements(By.xpath("//select"));
            for(int a=0;a<selectList.size();a++){
                try{
                    System.out.println("a : " + a);
                    Thread.sleep(3000);
                    new Select(selectList.get(a)).selectByIndex(1);
                }catch (Exception e){
                    System.out.println("Yes");
                }
            }*/
            searchPage.button_done.click();

            new UIElement().waitAndFindElement(searchPage.label_bulkoperationCopy);

            Assert.assertEquals(searchPage.successMessage.getText(), "The LO mapping operation has been successfully completed", "# It should display a message 'The LO mapping operation has been successfully completed' next to 'Use LO mapping'");
            Assert.assertEquals(searchPage.label_bulkoperationCopy.getText(), "Bulk Operation : Copy", "It should take back to \"Bulk Operation : Copy\" page in pop-up.");


            searchPage.link_manageMapping.click();
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "It should display 'Lo mapping table' in same pop-up window.");



            /*new Select(searchPage.comboBox_selectAnLearningObjective1).selectByIndex(0);
            new Select(searchPage.comboBox_selectAnLearningObjective2).selectByIndex(0);*/

            /*selectList = driver.findElements(By.xpath("//select"));
            for(int a=0;a<selectList.size();a++){
                try{
                    System.out.println("a : " + a);
                    Thread.sleep(3000);
                    new Select(selectList.get(a)).selectByIndex(0);
                }catch (Exception e){
                    System.out.println("Yes");
                }
            }*/
            unSelectTLOs();


            String border = searchPage.label_LoMappingTable.getCssValue("border-bottom-color");
            System.out.println("Border : " + border);
            Assert.assertEquals(border, "rgba(255, 255, 255, 1)", "# It should display red border validation for all the TLO/ELO filters for which no value are selected.");

            searchPage.icon_close.click();
            searchPage.link_manageMapping.click();
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "It should display 'Lo mapping table' in same pop-up window.");


            /*new Select(searchPage.comboBox_selectAnLearningObjective1).selectByIndex(0);
            new Select(searchPage.comboBox_selectAnLearningObjective2).selectByIndex(0);*/
            /*selectList = driver.findElements(By.xpath("//select"));
            for(int a=0;a<selectList.size();a++){
                try{
                    System.out.println("a : " + a);
                    Thread.sleep(3000);
                    new Select(selectList.get(a)).selectByIndex(0);
                }catch (Exception e){
                    System.out.println("Yes");
                }
            }*/
            unSelectTLOs();
            searchPage.icon_close.click();
            new UIElement().waitAndFindElement(searchPage.label_bulkoperationCopy);
            Assert.assertEquals(searchPage.label_bulkoperationCopy.getText(), "Bulk Operation : Copy", "It should take back to \"Bulk Operation : Copy\" page in pop-up.");


            searchPage.link_manageMapping.click();
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "It should display 'Lo mapping table' in same pop-up window.");
            /*new Select(searchPage.comboBox_selectAnLearningObjective1).selectByIndex(1);
            new Select(searchPage.comboBox_selectAnLearningObjective2).selectByIndex(1);*/



            /*selectList = driver.findElements(By.xpath("//select"));
            for(int a=0;a<selectList.size();a++){
                try{
                    System.out.println("a : " + a);
                    Thread.sleep(3000);
                    new Select(selectList.get(a)).selectByIndex(1);
                }catch (Exception e){
                    System.out.println("Yes");
                }
            }*/

            selectTLOs();
            searchPage.button_done.click();
            new UIElement().waitAndFindElement(searchPage.successMessage);
            Assert.assertEquals(searchPage.successMessage.getText(), "The LO mapping operation has been successfully completed", "# It should display a message 'The LO mapping operation has been successfully completed' next to 'Use LO mapping'");
            Assert.assertEquals(searchPage.label_bulkoperationCopy.getText(), "Bulk Operation : Copy", "It should take back to \"Bulk Operation : Copy\" page in pop-up.");




            //Row No - 66

            searchPage.link_manageMapping.click();
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "It should display 'Lo mapping table' in same pop-up window.");



            /*new Select(searchPage.comboBox_selectAnLearningObjective1).selectByIndex(1);
            new Select(searchPage.comboBox_selectAnLearningObjective2).selectByIndex(1);
            searchPage.button_done.click();
            Assert.assertEquals(searchPage.successMessage.getText(), "The LO mapping operation has been successfully completed", "# It should display a message 'The LO mapping operation has been successfully completed' next to 'Use LO mapping'");
            Assert.assertEquals(searchPage.label_bulkoperationCopy.getText(), "Bulk Operation : Copy", "It should take back to \"Bulk Operation : Copy\" page in pop-up.");

*/



        }catch(Exception e){
            Assert.fail("Exception in the method 'copyQuestionsFromOneCourseToAnother' in the class 'CopyQuestions'",e);
        }

    }




    //@Test(priority = 1,dependsOnMethods = { "copyQuestionsFromOneCourseToAnother" },enabled = true)
    @Test
    public void viewLOMapping(){
        try{
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            WebDriver driver=Driver.getWebDriver();
            SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
            new UIElement().waitAndFindElement(searchPage.icon_search);
            searchPage.icon_search.click();
            new UIElement().waitAndFindElement(searchPage.link_bulkOperations);
            searchPage.link_bulkOperations.click();
            new UIElement().waitAndFindElement(searchPage.link_moveAssessments);
            Thread.sleep(3000);
            searchPage.link_copyAssessments.click();
            List<WebElement> listElements = driver.findElements(By.xpath("//div[@class='cms-dialog-form-content']//a[@href = 'Select your option here']"));
            listElements.get(2).click();
            new UIElement().waitAndFindElement(By.partialLinkText("Ch"));
            new Click().clickbypartiallinkText("Ch");
            new Click().clickbyxpath("//div[@class='cms-assessment-checkbox-row']//input");
            listElements.get(4).click();
            new UIElement().waitAndFindElement(By.partialLinkText("Physical Geology: The Science of Earth"));

            new Click().clickbypartiallinkText("Physical Geology: The Science of Earth");
            Thread.sleep(2000);
            new UIElement().waitAndFindElement(By.xpath("//div[@class ='cms-copy-assessments-to']//..//a[@title = 'Select an option']"));

            new Click().clickbyxpath("//div[@class ='cms-copy-assessments-to']//..//a[@title = 'Select an option']");
            new UIElement().waitAndFindElement(By.partialLinkText("Ch 1: An Introduction to Geology"));
            new Click().clickbypartiallinkText("Ch 1: An Introduction to Geology");
            new Click().clickbyxpath("//div[@class='cms-assessment-radiobtn-row']//input");

            new Click().clickByElement(searchPage.chechBox_useLOMapping);
            Thread.sleep(3000);
            searchPage.link_manageMapping.click();
            Thread.sleep(9000);
            selectTLOs();
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "It should display 'Lo mapping table' in same pop-up window.");
            Assert.assertEquals(new Select(searchPage.tloOption1).getFirstSelectedOption().getText(), "1.0 Analyze the process of science and the ...", "# It should dispay all the mapped TLO/ELO in the pop-up which are saved already");
            Assert.assertEquals(new Select(searchPage.tloOption1).getFirstSelectedOption().getText(),"1.0 Analyze the process of science and the ...","# It should dispay all the mapped TLO/ELO in the pop-up which are saved already");

            for(int a=0;a<6;a++){
                searchPage.link_addRow.click();
            }

            for(int a=0;a<6;a++){
                searchPage.link_remove.click();
            }

            searchPage.link_clearAll.click();
            Assert.assertEquals(searchPage.clearAllMessage.getText(), "All the LO mapping would be erased. Do you want to continue? Yes | No", "\"1.It should display a validation message \"\"All the LO mapping would be erased. Do you want to continue? Yes|No");


            searchPage.link_no.click();
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "It should display 'Lo mapping table' in same pop-up window.");

            searchPage.link_clearAll.click();
            searchPage.link_yes.click();
            Assert.assertEquals(searchPage.label_bulkoperationCopy.getText().trim(), "Bulk Operation : Copy", "1.It should open a pop-up window.");

            new Click().clickByElement(searchPage.chechBox_useLOMapping);
            Thread.sleep(3000);
            searchPage.link_manageMapping.click();
            Thread.sleep(3000);
            selectTLOs();
            searchPage.button_done.click();
            Assert.assertEquals(searchPage.label_bulkoperationCopy.getText().trim(), "Bulk Operation : Copy", "1.It should open a pop-up window.");
            //searchPage.link_manageMapping.click();
        }catch(Exception e){
            Assert.fail("Exception in the method 'viewLOMapping' in the class 'CopyQuestions'",e);
        }

    }



    @Test
    public void copyQuestionsFromOneCourseToAnotherAndAddDestinationLOs(){
        try{
            WebDriver driver=Driver.getWebDriver();
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();

            SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
            new UIElement().waitAndFindElement(searchPage.icon_search);
            searchPage.icon_search.click();
            Assert.assertEquals(searchPage.tab_search.getText().trim(), "SEARCH", "It should display search screens.");
            Assert.assertEquals(searchPage.tab_browse.getText().trim(), "BROWSE", "It should display browse screens.");

            new Assignment().create(78);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(78));
            System.out.println("questiontext : " + questiontext);

            searchPage.icon_search.click();
            searchPage.searchField.click();

            searchPage.searchField.sendKeys(questiontext);
            //searchPage.searchField.sendKeys("true");

            searchPage.button_go.click();
            searchPage.checkBox.click();
            searchPage.button_copy.click();

            Assert.assertEquals(searchPage.label_copySelectedQuestiosTo.getText().trim(), "Copy Selected Questions To", "1. It should open \"Copy Selected Questions\" pop-up.");

            searchPage.link_selectACourse.click();
            new UIElement().waitAndFindElement(searchPage.link_geography);
            Thread.sleep(2000);
            searchPage.link_geography.click();

            Assert.assertEquals(searchPage.label_useLoMapping.getText().trim(), "Use LO Mapping", "Then it should display new option “Use LO mapping\" along with check-box in pop-up window. ");

            if(searchPage.chechBox_useLOMapping2.isSelected()==true){
                Assert.fail("It should be unchecked by default");
            }


            List<WebElement> eleList = driver.findElements(By.xpath(".//*[@id='contentSearchSelectChapterSlider']//span"));
            if(!eleList.get(0).getText().equals("Copy as reference only")&&eleList.get(1).getText().equals("Use LO Mapping ")){
                Assert.fail("The new option 'Use LO mapping' should display above the 'Copy as reference only option.");
            }


            //Row No - 86 : Execute T.C of 23.14.1.1 and 23.14.2.


            new Click().clickByElement(searchPage.chechBox_useLOMapping2);
            Thread.sleep(3000);

            if(!searchPage.chechBox_useLOMapping2.getAttribute("class").equals("checked")){
                Assert.fail("Use LO Mapping should be unchecked by default");
            }
            eleList = driver.findElements(By.xpath(".//*[@id='contentSearchSelectChapterSlider']//span"));
            if(!eleList.get(0).getText().equals("Copy as reference only")&&eleList.get(1).getText().equals("Use LO Mapping ")&&eleList.get(2).getText().trim().equals("(Manage Mapping)")){
                Assert.fail("The new option 'Use LO mapping' should display above the 'Copy as reference only option.");
            }

            searchPage.link_manageMapping.click();
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "# It should display the Header as 'LO Mapping Table'");
            searchPage.icon_close.click();

            searchPage.chechBox_useLOMapping2.click();
            searchPage.link_manageMapping.click();
            Assert.assertEquals(searchPage.label_sourceCourse.getText(), "Source Course", "#The left part of heading should indicate 'Source Course'");
            Assert.assertEquals(searchPage.label_destinationCourse.getText(), "Destination Course", "#The rigth part of heading should indicate 'Destination Course'");
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective1.getText(),"Select an Learning Objective","# In third line it should display \"Select a Learning Objective\" as a default text in drop-down and the drop-down should be in  both source and destination places.");
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective2.getText(), "Select an Learning Objective", "# In third line it should display \"Select a Learning Objective\" as a default text in drop-down and the drop-down should be in  both source and destination places.");

           /*if(driver.findElements(By.xpath("(//div[@class='cms-lo-mapping-dialog-wrapper']//select)[3]/option")).size()!=889){
                Assert.fail("It should display all the TLO and ELO of that course in order as supported by product in source places.");
            }

            if(driver.findElements(By.xpath("(//div[@class='cms-lo-mapping-dialog-wrapper']//select)[4]/option")).size()!=202){
                Assert.fail("It should display all the TLO and ELO of that course in order as supported by product in destination places.");
            }*/

            Assert.assertEquals(searchPage.label_SelectAnLearningObjective1.getText(),"Select an Learning Objective","# In third line it should display \"Select a Learning Objective\" as a default text in drop-down and the drop-down should be in  both source and destination places.");
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective1.getText(), "Select an Learning Objective", "# In third line it should display \"Select a Learning Objective\" as a default text in drop-down and the drop-down should be in  both source and destination places.");
            Assert.assertEquals(searchPage.link_addRow.getText(),"+ Add row","It should display '+Add Row' button below to that .");
            Assert.assertEquals(searchPage.button_done.getText(),"Done","It should display \"Done\" button in the footer part.");
            Assert.assertEquals(searchPage.button_cancel.getText(),"Cancel","It should display \"Cancel\"  before to 'Done' option in the footer part.");
            Assert.assertEquals(searchPage.link_clearAll.getText(),"Clear all","It should display \"Clear all\"  option left side of the footer part.");
            searchPage.link_addRow.click();
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective3.getText(), "Select an Learning Objective", "It should add a new line same as of second line.");
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective4.getText(), "Select an Learning Objective", "It should add a new line same as of second line.");
            Assert.assertEquals(searchPage.link_remove.getText(),"- Remove","# It should display “-Remove” link at the end for all the rows apart from second line..");

            for(int a=0;a<5;a++){
                searchPage.link_addRow.click();
            }

            for(int a=1;a<13;a++){
                Assert.assertEquals(driver.findElement(By.xpath("(//select[@class='select-box-mapping'])["+a+"]//option")).getText(), "Select an Learning Objective", "It should add a new line same as of second line.");
            }

            if(driver.findElements(By.linkText("- Remove")).size()!=6){
                Assert.fail("# It should display “-Remove” link at the end for all the rows apart from second line..");
            }
            searchPage.link_remove.click();
            if(driver.findElements(By.linkText("- Remove")).size()!=5){
                Assert.fail("1.The the row should get deleted.");
            }

            for(int a=0;a<5;a++){
                searchPage.link_remove.click();
            }

            searchPage.icon_close.click();
            Thread.sleep(2000);
            //searchPage.link_manageMapping.click();


            searchPage.chechBox_useLOMapping2.click();
            new Click().clickByElement(searchPage.link_manageMapping1);



            Thread.sleep(9000);
            selectTLOs();

            searchPage.button_done.click();

            new UIElement().waitAndFindElement(searchPage.label_bulkoperationCopy);


            Assert.assertEquals(searchPage.successMessage.getText(), "The LO mapping operation has been successfully completed", "# It should display a message 'The LO mapping operation has been successfully completed' next to 'Use LO mapping'");
            Assert.assertEquals(searchPage.label_bulkoperationCopy.getText(), "Bulk Operation : Copy", "It should take back to \"Bulk Operation : Copy\" page in pop-up.");


            searchPage.link_manageMapping.click();
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "It should display 'Lo mapping table' in same pop-up window.");

            unSelectTLOs();

            /*new Select(searchPage.comboBox_selectAnLearningObjective1).selectByIndex(0);
            new Select(searchPage.comboBox_selectAnLearningObjective2).selectByIndex(0);*/


            String border = searchPage.label_LoMappingTable.getCssValue("border-bottom-color");
            System.out.println("Border : " + border);
            Assert.assertEquals(border, "rgba(255, 255, 255, 1)", "# It should display red border validation for all the TLO/ELO filters for which no value are selected.");

            searchPage.icon_close.click();
            searchPage.link_manageMapping.click();
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "It should display 'Lo mapping table' in same pop-up window.");
            unSelectTLOs();
            /*new Select(searchPage.comboBox_selectAnLearningObjective1).selectByIndex(0);
            new Select(searchPage.comboBox_selectAnLearningObjective2).selectByIndex(0);*/
            searchPage.icon_close.click();
            new UIElement().waitAndFindElement(searchPage.label_bulkoperationCopy);
            Assert.assertEquals(searchPage.label_bulkoperationCopy.getText(), "Bulk Operation : Copy", "It should take back to \"Bulk Operation : Copy\" page in pop-up.");


            searchPage.link_manageMapping.click();
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "It should display 'Lo mapping table' in same pop-up window.");
            selectTLOs();

            /*new Select(searchPage.comboBox_selectAnLearningObjective1).selectByIndex(1);
            new Select(searchPage.comboBox_selectAnLearningObjective2).selectByIndex(1);*/
            searchPage.button_done.click();
            Assert.assertEquals(searchPage.successMessage.getText(), "The LO mapping operation has been successfully completed", "# It should display a message 'The LO mapping operation has been successfully completed' next to 'Use LO mapping'");
            Assert.assertEquals(searchPage.label_bulkoperationCopy.getText(), "Bulk Operation : Copy", "It should take back to \"Bulk Operation : Copy\" page in pop-up.");

            searchPage.link_manageMapping.click();
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "It should display 'Lo mapping table' in same pop-up window.");

        }catch(Exception e){
            Assert.fail("Exception in the method 'copyQuestionsFromOneCourseToAnotherAndAddDestinationLOs' in the class 'CopyQuestions'",e);
        }

    }




    public void selectTLOs(){
        try{
            WebDriver driver=Driver.getWebDriver();
            Actions action = new Actions(driver);
            action.moveToElement(driver.findElement(By.xpath(".//*[@id='child-view']/div/div[1]/select"))).click().build().perform();
            Thread.sleep(5000);
            driver.findElement(By.xpath(".//*[@id='child-view']/div/div[1]/select")).sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            driver.findElement(By.xpath(".//*[@id='child-view']/div/div[1]/select")).sendKeys(Keys.ENTER);

            Actions action1 = new Actions(driver);
            action1.moveToElement(driver.findElement(By.xpath(".//*[@id='child-view']/div/div[2]/select"))).click().build().perform();
            Thread.sleep(5000);
            driver.findElement(By.xpath(".//*[@id='child-view']/div/div[2]/select")).sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            driver.findElement(By.xpath(".//*[@id='child-view']/div/div[2]/select")).sendKeys(Keys.ENTER);

        }catch(Exception e){
            Assert.fail("Exception in the method 'selectTLOs' in the class 'copyQuestions'",e);
        }
    }

    public void unSelectTLOs(){
        try{
            WebDriver driver=Driver.getWebDriver();
            Actions action = new Actions(driver);
            action.moveToElement(driver.findElement(By.xpath(".//*[@id='child-view']/div/div[1]/select"))).click().build().perform();
            Thread.sleep(5000);
            driver.findElement(By.xpath(".//*[@id='child-view']/div/div[1]/select")).sendKeys(Keys.ARROW_UP);
            Thread.sleep(1000);
            driver.findElement(By.xpath(".//*[@id='child-view']/div/div[1]/select")).sendKeys(Keys.ENTER);
            Thread.sleep(5000);
            Actions action1 = new Actions(driver);
            action1.moveToElement(driver.findElement(By.xpath(".//*[@id='child-view']/div/div[2]/select"))).click().build().perform();
            Thread.sleep(5000);
            driver.findElement(By.xpath(".//*[@id='child-view']/div/div[2]/select")).sendKeys(Keys.ARROW_UP);
            Thread.sleep(1000);
            driver.findElement(By.xpath(".//*[@id='child-view']/div/div[2]/select")).sendKeys(Keys.ENTER);

        }catch(Exception e){
            Assert.fail("Exception in the method 'unSelectTLOs' in the class 'copyQuestions'",e);
        }
    }

}
