package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.bulkOperations;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
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

import java.util.List;

/**
 * Created by root on 4/5/16.
 */
public class BulkOperations extends Driver{

    @Test(priority=1,enabled = true)
    public void copyQuestionsFromOneCourseToAnother(){
        try{
            ReportUtil.log("Copy Questions from one Course to another Course","Validations while copying Questions from Biology to Geology", "info");
            WebDriver driver=Driver.getWebDriver();
            /*Row No - 5 : "1.Login as CMS/SWAT user.
            1.1.Select any course and click on Search icon in the header."*/
            //Expected - 1.It should display search and browse screens.

            new DirectLogin().CMSLogin();// CMS Login
            new SelectCourse().selectcourse();// Select Biology Course
            SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.icon_search,60);
            searchPage.icon_search.click();// Click on 'Search' icon
            CustomAssert.assertEquals(searchPage.tab_search.getText().trim(), "SEARCH", "Copy Questions from one Course to another Course", "It displayed search screens", "It doesn't display 'search' screens");
            CustomAssert.assertEquals(searchPage.tab_browse.getText().trim(), "BROWSE", "Copy Questions from one Course to another Course","It displayed Browse screens","It doesn't display 'browse' screens");

            //Row No - 6 : 1.2.Click on 'Bulk Operations' drop-down.
            //Expected - 1.It should display 'Copy Assessments' and 'Move Assessments' as a drop-down values.
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.link_bulkOperations,60);
            searchPage.link_bulkOperations.click();//Click on 'Bulk Operations'
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.link_moveAssessments,60);
            searchPage.link_moveAssessments.click();//Click on 'Move Assessments'
            searchPage.icon_closeBulkOPeration.click();//Close 'Bulk Operation' window

            //Row No - 7 - .3.Select 'Copy Assessments' option in the drop-down.
            //Expected - 1.It should open a pop-up window.
            searchPage.link_bulkOperations.click();//Click on 'Bulk Operations'
            searchPage.link_copyAssessments.click();//Click on 'Copy Assessments'
            CustomAssert.assertEquals(searchPage.label_bulkoperationCopy.getText().trim(), "Bulk Operation : Copy","Copy Questions from one Course to another Course","pop-up window is opened","pop-up window is not opened");

            //Row No - 8 : 1.4. Select a Course(Ex: Bio) and any assessment in that, under 'Copy Assessments From' section and select  a different course (Ex:Geo) and any assessment in that, under 'Copy Assessments To' section
            //Expected - 1. Then it should display new option “Use LO mapping" along with check-box in the footer part of pop-up window.
            List<WebElement> listElements = driver.findElements(By.xpath("//div[@class='cms-dialog-form-content']//a[@href = 'Select your option here']"));
            listElements.get(2).click();//Click on 'Select an option'
            new WebDriverUtil().waitTillVisibilityOfElement(driver.findElement(By.partialLinkText("Ch")),60);
            new Click().clickbypartiallinkText("Ch");//Click on 'Chapter'
            new Click().clickbyxpath("//div[@class='cms-assessment-checkbox-row']//input");//Click on checkbox 'Pre-lecture - The Study of Life'
            listElements.get(4).click();//Click on 'Select a course'
            new UIElement().waitAndFindElement(By.partialLinkText("Physical Geology: The Science of Earth"));
            new Click().clickbypartiallinkText("Physical Geology: The Science of Earth");//Select 'Physical Geology: The Science of Earth 2e'
            new WebDriverUtil().waitTillVisibilityOfElement(driver.findElement(By.xpath("//div[@class ='cms-copy-assessments-to']//..//a[@title = 'Select an option']")),60);
            new Click().clickbyxpath("//div[@class ='cms-copy-assessments-to']//..//a[@title = 'Select an option']");//Click on 'Select an option'
            new WebDriverUtil().waitTillVisibilityOfElement(driver.findElement(By.partialLinkText("Ch 1: An Introduction to Geology")),60);
            new Click().clickbypartiallinkText("Ch 1: An Introduction to Geology");//Select on 'Ch 1: An Introduction to Geology'
            Thread.sleep(2000);
            new Click().clickbyxpath("//div[@class='cms-assessment-radiobtn-row']//input");//Click on radio button 'Ch.1 Assessing Your Knowledge'

            //Expected - 1. Then it should display new option “Use LO mapping" along with check-box in the footer part of pop-up window.
            if(searchPage.chechBox_useLOMapping.isSelected()==true){
                Assert.fail("Use LO Mapping should not be checked by default");
            }

            //Expected - 2 "# It should be unchecked by default.
            new Click().clickByElement(searchPage.chechBox_useLOMapping);
            Thread.sleep(3000);
            if(searchPage.chechBox_useLOMapping.isSelected()==false){
                Assert.fail("Use LO Mapping should be unchecked by default");
            }

            //Expected - 3: "# The new option 'Use LO mapping' should display above the 'Copy as reference only' option."
            List<WebElement> elementsList = driver.findElements(By.xpath("//div[@class='cms-assessment-dialog-footer']//i"));
            if(!elementsList.get(0).getText().equals("Use LO mapping")&&elementsList.get(1).getText().equals("Manage Mapping") && elementsList.get(2).getText().equals("Copy as reference only")){
                Assert.fail("The new option 'Use LO mapping' should display above the 'Copy as reference only option");
            }


            //1.6  Click on 'Manage Mapping'.
            searchPage.link_manageMapping.click();

            //Expected - 1 : #It should display 'LO Mapping Table' in same pop-up window.
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "# It should display the Header as 'LO Mapping Table'");

            //Expected - 2 : # It should display close(X)-symbol at the top right corner of header with appropriate tool tip.
            searchPage.icon_close.click();
            Thread.sleep(2000);
            searchPage.label_useLOMapping.click();
            Thread.sleep(2000);
            searchPage.link_manageMapping.click();


            //Expected - 3 : #The left part of heading should indicate 'Source Course'
            Assert.assertEquals(searchPage.label_sourceCourse.getText(), "Source Course", "#The left part of heading should indicate 'Source Course'");

            //Expected - 4 : #The right part of heading should indicate 'Destination Course'
            Assert.assertEquals(searchPage.label_destinationCourse.getText(), "Destination Course", "#The rigth part of heading should indicate 'Destination Course'");

            //Expected - 5 : # In second line it should display "Select a Learning Objective" as a default text in drop-down and the drop-down should be in both source and destination places.
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective1.getText(),"Select an Learning Objective","# In third line it should display \"Select a Learning Objective\" as a default text in drop-down and the drop-down should be in  both source and destination places.");
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective2.getText(), "Select an Learning Objective", "# In third line it should display \"Select a Learning Objective\" as a default text in drop-down and the drop-down should be in  both source and destination places.");

            //Expected - 6 : # In third line it should display "Select a Learning Objective" as a default text in drop-down and the drop-down should be in  both source and destination places.
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective1.getText(),"Select an Learning Objective","# In third line it should display \"Select a Learning Objective\" as a default text in drop-down and the drop-down should be in  both source and destination places.");
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective1.getText(), "Select an Learning Objective", "# In third line it should display \"Select a Learning Objective\" as a default text in drop-down and the drop-down should be in  both source and destination places.");


            //# It should display '+Add Row' button below to that .
            Assert.assertEquals(searchPage.link_addRow.getText(),"+ Add row","It should display '+Add Row' button below to that .");

           //# It should display "Done" button in the footer part.
            Assert.assertEquals(searchPage.button_done.getText(),"Done","It should display \"Done\" button in the footer part.");

            //# It should display "Cancel"  before to 'Done' option in the footer part.
            Assert.assertEquals(searchPage.button_cancel.getText(),"Cancel","It should display \"Cancel\"  before to 'Done' option in the footer part.");

            //# It should display "Clear all"  option left side of the footer part.
            Assert.assertEquals(searchPage.link_clearAll.getText(),"Clear all","It should display \"Clear all\"  option left side of the footer part.");

            //1.7. Click on '+Add Row'.
            //Expected - 1 : # It should add a new line same as of second line.
            //Expected - 2 : # It should display “-Remove” link at the end for all the rows apart from second line..
            searchPage.link_addRow.click();
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective3.getText(), "Select an Learning Objective", "It should add a new line same as of second line.");
            Assert.assertEquals(searchPage.label_SelectAnLearningObjective4.getText(), "Select an Learning Objective", "It should add a new line same as of second line.");
            Assert.assertEquals(searchPage.link_remove.getText(),"- Remove","# It should display “-Remove” link at the end for all the rows apart from second line..");


            //1.8. Click on '+Add Row' multiple times (Ex: 5 times)
            //Expected - # It should add a new line same as of second line.
            for(int a=0;a<5;a++){
                searchPage.link_addRow.click();
            }

            for(int a=1;a<13;a++){
                Assert.assertEquals(driver.findElement(By.xpath("(//select[@class='select-box-mapping'])["+a+"]//option")).getText(), "Select an Learning Objective", "It should add a new line same as of second line.");
            }

            if(driver.findElements(By.linkText("- Remove")).size()!=6){
                Assert.fail("# It should display “-Remove” link at the end for all the rows apart from second line..");
            }


            //1.9. Click on “-Remove” link of any row.
            //Expected - 1.The the row should get deleted.
            searchPage.link_remove.click();
            if(driver.findElements(By.linkText("- Remove")).size()!=5){
                Assert.fail("1.The the row should get deleted.");
            }

            /*"1.10. Click on ""Select a Learning Objective"" drop-down and select the som TLO/ELO in both source and destination courses.
            Click on done."*/
            //Expected - 1.It should save the results.
            for(int a=0;a<5;a++){
                searchPage.link_remove.click();
            }
            Thread.sleep(5000);
            selectTLOs();
            searchPage.icon_close.click();
            Thread.sleep(2000);
            searchPage.label_useLOMapping.click();
            Thread.sleep(2000);
            searchPage.link_manageMapping.click();
            Thread.sleep(9000);
            selectTLOs();
            searchPage.button_done.click();
            new UIElement().waitAndFindElement(searchPage.label_bulkoperationCopy);

            //Expected - 2 : # It should take back to "Bulk Operation : Copy" page in pop-up.
            Assert.assertEquals(searchPage.label_bulkoperationCopy.getText(), "Bulk Operation : Copy", "It should take back to \"Bulk Operation : Copy\" page in pop-up.");

            //Expected - 3 : # It should display a message 'The LO mapping operation has been successfully completed' next to 'Use LO mapping'
            Assert.assertEquals(searchPage.successMessage.getText(), "The LO mapping operation has been successfully completed", "# It should display a message 'The LO mapping operation has been successfully completed' next to 'Use LO mapping'");


            //Row No - 42 : 1.11.  Click on 'Manage Mapping' again.
            //Expected - 1 : 1. It should allow user to add or delete the TLO/ELO.
            Thread.sleep(2000);
            searchPage.label_useLOMapping.click();
            Thread.sleep(2000);
            searchPage.link_manageMapping.click();
            unSelectTLOs();


            /*Row No - 46 : "2. Click on Bulk copy and select different source and destination cources.
            2.1. Select check box of ""Use LO mapping"".
            2.2. Click on 'Manage Mapping' link."*/
            //Expected - 1.It should display 'Lo mapping table' in same pop-up window.
            searchPage.icon_close.click();
            Thread.sleep(2000);
            searchPage.label_useLOMapping.click();
            Thread.sleep(2000);
            searchPage.link_manageMapping.click();
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "It should display 'Lo mapping table' in same pop-up window.");



            //Row No - 49 : 3.3. With out selecting any sources and destination TLO/ELO click on Close(x) or Cancel.
            //Expected - 1 : The pop-up should get closed and should navigate to “Bulk Operation: Copy” page in that pop-up.
            unSelectTLOs();
            searchPage.icon_close.click();
            new UIElement().waitAndFindElement(searchPage.label_bulkoperationCopy);
            Assert.assertEquals(searchPage.label_bulkoperationCopy.getText(), "Bulk Operation : Copy", "It should take back to \"Bulk Operation : Copy\" page in pop-up.");


            //Expected - 2 : "Use LO Mapping" check box should get un checked.
            Thread.sleep(2000);
            searchPage.label_useLOMapping.click();
            Thread.sleep(2000);

            /*"4. Click on Bulk copy and select different source(Ex:Bio) and destination(Ex:Geo) cources.
            4.1. Select check box of ""Use LO mapping"".
            4.2. Click on 'Manage Mapping' link."*/
            //Expected - 1 : 1.It should display 'Lo mapping table' in same pop-up window.
            searchPage.link_manageMapping.click();
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "It should display 'Lo mapping table' in same pop-up window.");

            //Row No - 52 : 4.3. Select and map some sources and destination TLO/ELO click on Done.
            //Expected - # It should navigate back to "Bulk Operation: Copy" and the changes should get saved successfully.
            selectTLOs();
            searchPage.button_done.click();
            new UIElement().waitAndFindElement(searchPage.successMessage);
            Assert.assertEquals(searchPage.label_bulkoperationCopy.getText(), "Bulk Operation : Copy", "It should take back to \"Bulk Operation : Copy\" page in pop-up.");




            //Row No - 54 : 4.5. Select the Courses which are mapped already.
            //Expected - # Then it should display "Manage Mapping" link(If the courses are already mapped)
            Thread.sleep(2000);
            searchPage.label_useLOMapping.click();
            Thread.sleep(2000);
            searchPage.link_manageMapping.click();
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "It should display 'Lo mapping table' in same pop-up window.");
            searchPage.link_clearAll.click();
            searchPage.link_yes.click();
        }catch(Exception e){
            Assert.fail("Exception in the method 'copyQuestionsFromOneCourseToAnother' in the class 'CopyQuestions'",e);
        }

    }




    @Test(priority=2,enabled = true)
    public void viewLOMapping(){
        try{
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("View LO Mapping","As an author, I should be able to view/edit a LO mapping for a course combination for bulk copy", "info");

            /*"1. Click On Bulk copy and select the courses in sources and destination which are mapped already.
            1.2. Click on ""Manage Mapping"" link."*/
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.icon_search,60);
            searchPage.icon_search.click();
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.link_bulkOperations,60);
            searchPage.link_bulkOperations.click();
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.link_moveAssessments,60);
            Thread.sleep(3000);
            searchPage.link_copyAssessments.click();
            List<WebElement> listElements = driver.findElements(By.xpath("//div[@class='cms-dialog-form-content']//a[@href = 'Select your option here']"));
            listElements.get(2).click();
            new WebDriverUtil().waitTillVisibilityOfElement(driver.findElement(By.partialLinkText("Ch")),60);
            new Click().clickbypartiallinkText("Ch");
            new Click().clickbyxpath("//div[@class='cms-assessment-checkbox-row']//input");
            listElements.get(4).click();
            new WebDriverUtil().waitTillVisibilityOfElement(driver.findElement(By.partialLinkText("Physical Geology: The Science of Earth")),60);
            new Click().clickbypartiallinkText("Physical Geology: The Science of Earth");
            Thread.sleep(2000);
            new WebDriverUtil().waitTillVisibilityOfElement(driver.findElement(By.xpath("//div[@class ='cms-copy-assessments-to']//..//a[@title = 'Select an option']")),60);
            new Click().clickbyxpath("//div[@class ='cms-copy-assessments-to']//..//a[@title = 'Select an option']");
            new WebDriverUtil().waitTillVisibilityOfElement(driver.findElement(By.partialLinkText("Ch 1: An Introduction to Geology")),60);
            new Click().clickbypartiallinkText("Ch 1: An Introduction to Geology");
            new Click().clickbyxpath("//div[@class='cms-assessment-radiobtn-row']//input");
            new Click().clickByElement(searchPage.chechBox_useLOMapping);
            Thread.sleep(3000);
            searchPage.link_manageMapping.click();
            Thread.sleep(9000);
            selectTLOs();

            //Expected -  1.It should display 'Lo mapping table' in same pop-up window.
            CustomAssert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table","View LO Mapping","It displayed 'Lo mapping table' in same pop-up window","It is not displayed 'Lo mapping table' in same pop-up window.");

            //# Expected - 2: It should dispay all the mapped TLO/ELO in the pop-up which are saved already
            CustomAssert.assertEquals(new Select(searchPage.tloOption1).getFirstSelectedOption().getText(), "1.0 Analyze the process of science and the ...","View LO Mapping","It displayed all the mapped TLO/ELO in the pop-up which are saved already","It is not displayed all the mapped TLO/ELO in the pop-up which are saved already");

            //"Expected - 3 : It should be able to remove / add new row entries except the second row.
            CustomAssert.assertEquals(new Select(searchPage.tloOption1).getFirstSelectedOption().getText(),"1.0 Analyze the process of science and the ...","View LO Mapping","It displayed all the mapped TLO/ELO in the pop-up which are saved already","It is not displayed all the mapped TLO/ELO in the pop-up which are saved already");
            for(int a=0;a<6;a++){
                searchPage.link_addRow.click();
            }
            for(int a=0;a<6;a++){
                searchPage.link_remove.click();
            }

            //Row No - 62 - 1.3. Click on "Clear all" option in the footer.
            //Expected - "1.It should display a validation message ""All the LO mapping would be erased. Do you want to continue? Yes|No"".
            searchPage.link_clearAll.click();
            CustomAssert.assertEquals(searchPage.clearAllMessage.getText(),"All the LO mapping would be erased. Do you want to continue? Yes | No","View LO Mapping","It displayed a validation message 'All the LO mapping would be erased. Do you want to continue? Yes|No'", "It is not displayed a validation message 'All the LO mapping would be erased. Do you want to continue? Yes|No''");

            //1.3.1. Click on "No"
            //Expected - .It should stay in the same page.
            searchPage.link_no.click();
            CustomAssert.assertEquals(searchPage.label_LoMappingTable.getText(),"LO Mapping Table","View LO Mapping","It displayed 'Lo mapping table' in same pop-up window.","It is not displayed 'Lo mapping table' in same pop-up window.");

            //1.3.1. Click on "Yes"
            //Expected- "1.  The  LO mapping for that course combination should be erased and It should be navigated back to “Bulk Operation: Copy” popup and  “Use LO mapping"" for destination course” checkbox should get unchecked.
            searchPage.link_clearAll.click();
            searchPage.link_yes.click();
            CustomAssert.assertEquals(searchPage.label_bulkoperationCopy.getText().trim(), "Bulk Operation : Copy","View LO Mapping","It opened a pop-up window.","It is not opened a pop-up window.");

            //Row No - 69 : 1.6. Click on 'Done'
            //Expected - "1.It should save new LO mapping and should navigate back to “Bulk Operation: Copy” popup.
            new Click().clickByElement(searchPage.chechBox_useLOMapping);
            Thread.sleep(3000);
            searchPage.link_manageMapping.click();
            Thread.sleep(3000);
            selectTLOs();
            searchPage.button_done.click();
            CustomAssert.assertEquals(searchPage.label_bulkoperationCopy.getText().trim(), "Bulk Operation : Copy","View LO Mapping","It opened a pop-up window.", "It is not opened a pop-up window.");
        }catch(Exception e){
            Assert.fail("Exception in the method 'viewLOMapping' in the class 'CopyQuestions'",e);
        }

    }



    @Test(priority=3,enabled = true)
    public void copyQuestionsFromOneCourseToAnotherAndAddDestinationLOs(){
        try{
            ReportUtil.log("Copy Questions from one Course to another and add destination LOs","Validations while copying Questions from one Course to another and add destination LOs", "info");
            WebDriver driver=Driver.getWebDriver();
            //Row No - 71 - 1.Login as CMS, Select any course and click on search icon.
            //Expected - 1.It should display search and browse screens.
            new DirectLogin().CMSLogin();//CMS Login
            new SelectCourse().selectcourse();// Select 'Biology' Course
            SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.icon_search,60);
            searchPage.icon_search.click();//Click on 'Search' icon
            CustomAssert.assertEquals(searchPage.tab_search.getText().trim(), "SEARCH","Copy Questions from one Course to another and add destination LOs","It displayed search screens.","search screens is not displayed");
            CustomAssert.assertEquals(searchPage.tab_browse.getText().trim(), "BROWSE","Copy Questions from one Course to another and add destination LOs","It displayed browse screens.","browse screens is not displayed");
            //1.2. Search some question using search/ browse filters.
            //Expected - 1. It should display all the question in the search/ browse results.
            new Assignment().create(78);//Create Custom Assignment
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(78));
            searchPage.icon_search.click();//Click on search icon
            searchPage.searchField.click();//Click on 'Search' Field
            searchPage.searchField.sendKeys(questiontext);//Type Question text
            searchPage.button_go.click();//Click on button 'Go'
            searchPage.checkBox.click();//Click on 'Check' box
            searchPage.button_copy.click();//Click on 'Copy' button
            CustomAssert.assertEquals(searchPage.label_copySelectedQuestiosTo.getText().trim(), "Copy Selected Questions To","Copy Questions from one Course to another and add destination LOs","'Copy Selected Questions' pop-up is opened","'Copy Selected Questions' pop-up is not opened");

            //1.4. Select a Different course to Copy questions
            searchPage.link_selectACourse.click();//Click on 'Select a Course' option
            new UIElement().waitAndFindElement(searchPage.link_geography);
            Thread.sleep(2000);
            searchPage.link_geography.click();//Click on link 'Geography'

            //Expected -1 : Then it should display new option “Use LO mapping" along with check-box in pop-up window.
            CustomAssert.assertEquals(searchPage.label_useLoMapping.getText().trim(), "Use LO Mapping","Copy Questions from one Course to another and add destination LOs","It is displayed new option “Use LO mapping\" along with check-box in pop-up window","It is not displayed new option “Use LO mapping\" along with check-box in pop-up window");


            //Expected - 2 : "# It should be unchecked by default.
            if(searchPage.chechBox_useLOMapping2.isSelected()==true){
                CustomAssert.fail("Copy Questions from one Course to another and add destination LOs","It is not unchecked by default");
            }

            //Expected - 3 :"# The new option 'Use LO mapping' should display above the 'Copy as reference only' option."
            List<WebElement> eleList = driver.findElements(By.xpath(".//*[@id='contentSearchSelectChapterSlider']//span"));
            if(!eleList.get(0).getText().equals("Copy as reference only")&&eleList.get(1).getText().equals("Use LO Mapping ")){
                CustomAssert.fail("Copy Questions from one Course to another and add destination LOs","The new option 'Use LO mapping' is not displayed above the 'Copy as reference only option.");
            }
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
