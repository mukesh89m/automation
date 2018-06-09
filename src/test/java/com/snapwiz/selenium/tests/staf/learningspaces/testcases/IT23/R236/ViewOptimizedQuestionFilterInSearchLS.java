package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R236;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by mukesh on 2/9/15.
 */
public class ViewOptimizedQuestionFilterInSearchLS extends Driver {
    @Test(priority = 1, enabled = true)
    public void viewOptimizedQuestionFilterInSearch() {
        try {
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
            int dataIndex=12;
            new LoginUsingLTI().ltiLogin("160"); //login as instructor
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",dashboard.getNewAssignments());   //click on the new Assignments button
            newAssignment.createCustomAssignment.click(); //click on the create custom assignment

            //TC row no 160
            Assert.assertEquals(new BooleanValue().presenceOfElement(12, By.className("ls-ins-browse-icon ")), true, "instructor not navigated to custom assignmnet page");

            //TC row no 161
            newAssignment.browse_link.click(); //click on the browse link

            String bgColor=newAssignment.browse_link.getCssValue("background-color");
            Assert.assertEquals(bgColor,"rgba(255, 255, 255, 1)","2. Browse view is not focused bydefault");
            //TC row no 162
            Assert.assertEquals(new BooleanValue().presenceOfElement(12,By.className("view-browse-options-drop-down")),false,"“View browse options” button icon is not removed.");

            //TC row  no 163
            Assert.assertEquals(new BooleanValue().presenceOfElement(12,By.id("ls-ins-view-browse-options-dropdown")),true,"browse filter dropdown is not displaying");

            //TC row no 164
            Assert.assertEquals(new BooleanValue().presenceOfElement(12,By.xpath("//a[@title='All Sub-sections']")),false,"All Sub-sections is dispalying in dropdown");

            //TC row no 165
            String position= newAssignment.advancedFilter_link.getCssValue("float");
            Assert.assertEquals(position,"left","'+Advanced filters' link is not displayed to the left of GO button.");
            String advancedFilterTextColor= newAssignment.advancedFilter_link.getCssValue("color");
            //TC row no 166 rgba(43, 133, 192, 1) --blue color
            Assert.assertEquals(advancedFilterTextColor,"rgba(43, 133, 192, 1)","+Advanced filters' link is not displayed in blue color.");

            //TC row no 167
            newAssignment.advancedFilter_link.click(); //click on the advanced filter link
            new UIElement().waitAndFindElement(By.cssSelector("span[for='.ls-ins-customize-checkbox-hint']"));
            Thread.sleep(2000);
            Assert.assertEquals(newAssignment.advancedFilter_list.get(0).getText().trim(), "Show only questions with hints", "'Not Showing only questions with Hints' check-box.");
            Assert.assertEquals(newAssignment.advancedFilter_list.get(1).getText().trim(),"Show only questions with solutions" ,"Not Showing questions with Solution' checkbox.");
            Assert.assertEquals(newAssignment.advancedFilter_list.get(2).getText().trim(),"Show only algorithmic questions" ,"'Not Showing only algorithmic questions' check-box.");

            boolean checked=false;
            //TC row no 171
            List<WebElement> advancedFilterCheckbox = newAssignment.advancedFilter_checkbox;
            for(WebElement checkbox:advancedFilterCheckbox)
            {
                if(checkbox.isSelected()==true){
                    checked=true;
                }
            }
            if(checked==true){
                Assert.fail("All  three checkboxes is not unchecked by default.");
            }


            //TC row no 172
            Assert.assertEquals(newAssignment.advancedFilter_link.getText().trim(),"- Advanced Filters"," \"+Advanced filters\" link is not to changed \"-Advanced filters\"");

            //TC row no 172
            for(int i=0;i<=3;i++){
                try {
                    newAssignment.advancedFilter_checkbox.get(0).click(); // click on the first checkbox
                    break;
                } catch (Exception e) {
                }

            }
            newAssignment.advancedFilter_link.click();//click on the -advanced filter link
            Thread.sleep(3000);
            newAssignment.advancedFilter_link.click();//click on the -advanced filter link

            if(!newAssignment.advancedFilter_checkbox.get(0).getAttribute("class").equals("ls-ins-customize-checkbox-hint checked")){
                Assert.fail("Not showing same results untill the instructor navigates from the current assignment page. (Chech box should be checked for 'Show only questions with hints')");
            }



        } catch (Exception e) {
            Assert.fail("Exception in TC viewOptimizedQuestionFilterInSearch of class ViewOptimizedQuestionFilterInSearch", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void allQuestionTypeFilterForBrowse() {
        try {
            Dashboard dashboard= PageFactory.initElements(driver,Dashboard.class);
            NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
            int dataIndex=203;
            new LoginUsingLTI().ltiLogin("203"); //login as instructor
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", dashboard.getNewAssignments());   //click on the new Assignments button
            newAssignment.createCustomAssignment.click(); //click on the create custom assignment
            //TC row no 203
            Assert.assertEquals(new BooleanValue().presenceOfElement(12,By.className("ls-ins-browse-icon ")),true,"instructor not navigated to custom assignmnet page");


            newAssignment.browse_link.click(); //click on the browse link
            Thread.sleep(2000);
            newAssignment.allQuestionType_dropdown.click();//click on the all question type

            int allQuestionTypeSize=newAssignment.allQuestionType_Filter.size();
            Assert.assertEquals(allQuestionTypeSize,18,"Not displaying 'All Question types' option followed by all the question types as supported by the product in (ascending order of question type name).");

            //Tc row no 204All Question types
            List<WebElement> checkBoxType=newAssignment.allQuestionType_checkbox;
            for(WebElement questionType:checkBoxType) {
                if(!questionType.getAttribute("type").equals("checkbox"))
                    Assert.fail("Not displaying check box infront of 'All the Question types' and for all the question types supported by the product.");
            }

            //TC row no 205
            boolean isChecked=false;
            List<WebElement> checked=newAssignment.allQuestionType_checkbox;
            for(WebElement questionType:checked) {
                if(questionType.isSelected()==false)
                {
                    isChecked=true;
                }
            }
            if(isChecked==true){
                Assert.fail("By default the check-boxes is not checked for all the question types including  'All Question types' option dropdown.");

            }

            //Tc row no 206
            Assert.assertTrue(newAssignment.done_button.isDisplayed());

            //TC row no 207
            String doneButtonColor=newAssignment.done_button.getCssValue("color");
            Color color=null;
            String hex=null;
            color=Color.fromString(doneButtonColor);
            hex= color.asHex();
            Assert.assertEquals(hex,"#2b85c0","Done is not in blue color");

            //TC row no 208
            newAssignment.allQuestionType_Filter.get(0).click(); //click on the all question type
            Thread.sleep(3000);
            boolean unChecked=false;
            List<WebElement> notChecked=newAssignment.allQuestionType_checkbox;
            for(WebElement questionType:notChecked) {
                if(questionType.isSelected()==true)
                {
                    unChecked=true;
                }
            }
            if(unChecked==true){
                Assert.fail("check box is not un-checked for 'All Question types' option and for all the questions types entries in drop-down.");

            }

            //TC row no 209
            Assert.assertTrue(newAssignment.done_deactivate.isDisplayed());
            String doneButtonColor1=newAssignment.done_deactivate.getCssValue("color");
            color=Color.fromString(doneButtonColor1);
            hex= color.asHex();
            Assert.assertEquals(hex,"#afb0b1","Done is not in gray color");


            newAssignment.browse_dropdown.click(); //click on the body of browse dropdown

            Assert.assertFalse(newAssignment.allQuestionType_Filter.get(0).isDisplayed());
            //TC row no 63
            newAssignment.allQuestionType_dropdown.click();//click on the all question type
            Assert.assertFalse(new BrowseFunctionalityOfQuestionBank().checkBoxStatusChecked()); //check status for checked

            //TC row no 64
            String blue1=new BrowseFunctionalityOfQuestionBank().getColorOfParticularElement(newAssignment.done_button);
            Assert.assertEquals(blue1,"#2b85c0","Done is not in blue color");

            //TC row no 65
            newAssignment.allQuestionType_Filter.get(0).click(); //click on the all question type
            Assert.assertFalse(new BrowseFunctionalityOfQuestionBank().checkBoxStatusUnchecked()); //check for unchecked

            //TC row no 66 &
            String gray=new BrowseFunctionalityOfQuestionBank().getColorOfParticularElement(newAssignment.done_deactivate);
            Assert.assertEquals(gray,"#afb0b1","Done is not in gray color");

            //TC row no 68
            new ScrollElement().scrollToViewOfElement(newAssignment.allQuestionType_Filter.get(16));
            newAssignment.allQuestionType_Filter.get(16).click(); //click on the advanced Numeric
            newAssignment.done_button.click(); //click on done button
            Thread.sleep(3000);
            Assert.assertEquals(newAssignment.allQuestionType_dropdown.getAttribute("title"), "True/False");

            newAssignment.go_button.click();//click on go button

            //TC row no 70
            Assert.assertEquals(newAssignment.searchText.isEmpty(), false, "Browse results is not displayed in 'Find Questions' tab.");
            //TC row no 71

            newAssignment.browse_link.click(); //click on the browse
            newAssignment.allQuestionType_dropdown.click(); //click on the
            for(int i=2;i<=5;i++){
                new ScrollElement().scrollToViewOfElement(newAssignment.allQuestionType_Filter.get(i));
                newAssignment.allQuestionType_Filter.get(i).click(); //select 3 question
                Thread.sleep(1000);

            }
            newAssignment.done_button.click();
            //TC row no 72
            Assert.assertTrue(newAssignment.multipleQuestions_elipses.isDisplayed());

            //TC row no 73
            newAssignment.multipleQuestions_elipses.click(); //click on the ellipses;
            Assert.assertEquals(newAssignment.question_Container.size(),5,"Not displaying the complete list of selcted question types as notification window.");

            //TC row no 74
            newAssignment.browse_dropdown.click(); //click on the body of browse dropdown
            Assert.assertFalse(new BooleanValue().presenceOfElement(74, By.className("help-data-container")));

            //TC row no 75
            newAssignment.go_button.click();
            Assert.assertEquals(newAssignment.searchText.isEmpty(), false, "Browse results is not displayed in 'Find Questions' tab.");

            //TC row no 76
            newAssignment.browse_link.click(); //click on the browse
            newAssignment.allQuestionType_dropdown.click(); //click on the
            int found=0;
            List<WebElement> checked1=driver.findElements(By.xpath("//input[@type='checkbox']"));
            for(WebElement questionType:checked1) {
                Thread.sleep(1500);
                if (questionType.isSelected()==true)
                {

                    found++;
                }
            }
            Assert.assertEquals(found,5,"selected question types not  preserved until the instructor navigates away from the “create custom assignment” page");


        } catch (Exception e) {
            Assert.fail("Exception in TC allQuestionTypeFilterForBrowse of class viewOptimizedQuestionFilterInSearch", e);
        }
    }
    @Test(priority = 3, enabled = true)
    public void allQuestionTypeFilterFunctionality() {
        try {
            //TC row no 225
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            Dashboard dashboard= PageFactory.initElements(driver,Dashboard.class);
            NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
            new LoginUsingLTI().ltiLogin("203"); //login as instructor
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", dashboard.getNewAssignments());   //click on the new Assignments button
            newAssignment.createCustomAssignment.click(); //click on the create custom assignment
            newAssignment.browse_link.click(); //click on the browse link
            Thread.sleep(2000);
            newAssignment.allQuestionType_dropdown.click();//click on the all question type

            newAssignment.allQuestionType_Filter.get(1).click(); //click on the all question type

            Assert.assertFalse(newAssignment.allQuestionType_Filter.get(0).isSelected());
            Assert.assertFalse(newAssignment.allQuestionType_Filter.get(1).isSelected());

            for(int i=2;i<newAssignment.allQuestionType_Filter.size();i++){
                Assert.assertTrue((boolean) ((JavascriptExecutor) driver).executeScript("return document.getElementById('ddcl-ls-ins-question-type-down-1-i" + i + "').checked"));//check for checked box for true
            }

            //TC row no 226
            newAssignment.browse_dropdown.click();

            Assert.assertEquals(newAssignment.allQuestionType_dropdown.getAttribute("title"),"Multiple Question Types ...","The results of selected question types is not getting save and display in \"All Question types' field.");

        } catch (Exception e) {
            Assert.fail("Exception in TC allQuestionTypeFilterForBrowse of class viewOptimizedQuestionFilterInSearch", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void allQuestionTypeFilterFunctionalityMentor() {
        try {
            //TC row no 227
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            Dashboard dashboard= PageFactory.initElements(driver,Dashboard.class);
            NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
            new LoginUsingLTI().ltiLogin("227"); //login as mentor
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", dashboard.getNewAssignments());   //click on the new Assignments button
            newAssignment.createCustomAssignment.click(); //click on the create custom assignment
            newAssignment.browse_link.click(); //click on the browse link
            Thread.sleep(2000);
            newAssignment.allQuestionType_dropdown.click();//click on the all question type

            newAssignment.allQuestionType_Filter.get(1).click(); //click on the all question type

            Assert.assertFalse(newAssignment.allQuestionType_Filter.get(0).isSelected());
            Assert.assertFalse(newAssignment.allQuestionType_Filter.get(1).isSelected());

            for(int i=2;i<newAssignment.allQuestionType_Filter.size();i++){
                Assert.assertTrue((boolean) ((JavascriptExecutor) driver).executeScript("return document.getElementById('ddcl-ls-ins-question-type-down-1-i" + i + "').checked"));//check for checked box for true
            }

            //TC row no 226
            newAssignment.browse_dropdown.click();

            Assert.assertEquals(newAssignment.allQuestionType_dropdown.getAttribute("title"),"Multiple Question Types ...","The results of selected question types is not getting save and display in \"All Question types' field.");

        } catch (Exception e) {
            Assert.fail("Exception in TC allQuestionTypeFilterFunctionalityMentor of class viewOptimizedQuestionFilterInSearch", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void newOrderQuestionInBrowser() {
        try {
            //TC row no 228
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            Dashboard dashboard= PageFactory.initElements(driver,Dashboard.class);
            NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
            new LoginUsingLTI().ltiLogin("203"); //login as instructor
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", dashboard.getNewAssignments());   //click on the new Assignments button
            newAssignment.createCustomAssignment.click(); //click on the create custom assignment
            newAssignment.browse_link.click(); //click on the browse link
            newAssignment.go_button.click();//click on go button

            Assert.assertEquals(newAssignment.searchText.isEmpty(), false, "Browse results is not displayed in 'Find Questions' tab.");

            List<WebElement> chapterNo = newAssignment.chapterNumber;
            for(int i=0;i<chapterNo.size()-1;i++) {

                //TC row no 229
                String str = chapterNo.get(i).getAttribute("title").substring(0, 4);
                String nextstr = chapterNo.get(i + 1).getAttribute("title").substring(0, 4);
                if (str.compareTo(nextstr) > 1) {
                    Assert.fail("The order of the questions is not in ascending order of chapter no");
                }
            }

        } catch (Exception e) {
            Assert.fail("Exception in TC newOrderQuestionInBrowser of class viewOptimizedQuestionFilterInSearch", e);
        }
    }


    /*Search Functionality*/


    @Test(priority = 7, enabled = true)
    public void searchFunctionalityFromDashboard() {

        try {
            MyQuestionBank myQuestionBank= PageFactory.initElements(driver, MyQuestionBank.class);
            Dashboard dashboard= PageFactory.initElements(driver,Dashboard.class);
            NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
            //TC row no 234
            new LoginUsingLTI().ltiLogin("203"); //login as instructor
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", dashboard.getNewAssignments());   //click on the new Assignments button
            newAssignment.createCustomAssignment.click(); //click on the create custom assignment

            Assert.assertEquals(new BooleanValue().presenceOfElement(12, By.className("ls-ins-browse-icon ")), true, "instructor not navigated to custom assignmnet page");

            newAssignment.searchButton.click(); //click on the search link
            newAssignment.searchQuestion_textbox.click(); //click on the search textbox

            //TC row no 87
            Assert.assertTrue(newAssignment.filter.isDisplayed(),"filter button is not next to search label");
            newAssignment.filter.click(); //click on the filter

            Assert.assertEquals(new BooleanValue().presenceOfElement(12,By.xpath("//a[@title='All Sub-sections']")),false,"All Sub-sections is dispalying in dropdown");

            String position= newAssignment.advancedFilter_link.getCssValue("float");
            Assert.assertEquals(position,"left","'+Advanced filters' link is not displayed to the left of GO button.");
            String advancedFilterTextColor= newAssignment.advancedFilter_link.getCssValue("color");
            //TC row no 166 rgba(43, 133, 192, 1) --blue color
            Assert.assertEquals(advancedFilterTextColor,"rgba(43, 133, 192, 1)","+Advanced filters' link is not displayed in blue color.");

            newAssignment.advancedFilter_link.click(); //click on the advanced filter link
            new UIElement().waitAndFindElement(By.cssSelector("span[for='.ls-ins-customize-checkbox-hint']"));
            Thread.sleep(2000);
            Assert.assertEquals(newAssignment.advancedFilter_list.get(0).getText().trim(), "Show only questions with hints", "'Not Showing only questions with Hints' check-box.");
            Assert.assertEquals(newAssignment.advancedFilter_list.get(1).getText().trim(),"Show only questions with solutions" ,"Not Showing questions with Solution' checkbox.");
            Assert.assertEquals(newAssignment.advancedFilter_list.get(2).getText().trim(),"Show only algorithmic questions" ,"'Not Showing only algorithmic questions' check-box.");

            boolean checked=false;
            List<WebElement> advancedFilterCheckbox = newAssignment.advancedFilter_checkbox;
            for(WebElement checkbox:advancedFilterCheckbox)
            {
                if(checkbox.isSelected()==true){
                    checked=true;
                }
            }
            if(checked==true){
                Assert.fail("All  three checkboxes is not unchecked by default.");
            }


            Assert.assertEquals(newAssignment.advancedFilter_link.getText().trim(),"- Advanced Filters"," \"+Advanced filters\" link is not to changed \"-Advanced filters\"");

            for(int i=0;i<=3;i++){
                try {
                    newAssignment.advancedFilter_checkbox.get(0).click(); // click on the first checkbox
                    break;
                } catch (Exception e) {
                }

            }
            newAssignment.advancedFilter_link.click();//click on the -advanced filter link
            Thread.sleep(3000);
            newAssignment.advancedFilter_link.click();//click on the -advanced filter link

            if(!newAssignment.advancedFilter_checkbox.get(0).getAttribute("class").equals("ls-ins-customize-checkbox-hint checked")){
                Assert.fail("Not showing same results untill the instructor navigates from the current assignment page. (Chech box should be checked for 'Show only questions with hints')");
            }



        } catch (Exception e) {
            Assert.fail("Exception in TC searchFunctionalityFromDashboard of class viewOptimizedQuestionFilterInSearch", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void searchFunctionalityFromDashboardForMentor() {

        try {
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            //TC row no 246
            new LoginUsingLTI().ltiLogin("227"); //login as mentor
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", dashboard.getNewAssignments());   //click on the new Assignments button
            newAssignment.createCustomAssignment.click(); //click on the create custom assignment

            Assert.assertEquals(new BooleanValue().presenceOfElement(12, By.className("ls-ins-browse-icon ")), true, "instructor not navigated to custom assignmnet page");

            newAssignment.searchButton.click(); //click on the search link
            newAssignment.searchQuestion_textbox.click(); //click on the search textbox

            Assert.assertTrue(newAssignment.filter.isDisplayed(), "filter button is not next to search label");
            newAssignment.filter.click(); //click on the filter

            Assert.assertEquals(new BooleanValue().presenceOfElement(12,By.xpath("//a[@title='All Sub-sections']")),false,"All Sub-sections is dispalying in dropdown");

            String position = newAssignment.advancedFilter_link.getCssValue("float");
            Assert.assertEquals(position, "left", "'+Advanced filters' link is not displayed to the left of GO button.");
            String advancedFilterTextColor = newAssignment.advancedFilter_link.getCssValue("color");
            Assert.assertEquals(advancedFilterTextColor, "rgba(43, 133, 192, 1)", "+Advanced filters' link is not displayed in blue color.");

            newAssignment.advancedFilter_link.click(); //click on the advanced filter link
            new UIElement().waitAndFindElement(By.cssSelector("span[for='.ls-ins-customize-checkbox-hint']"));
            Thread.sleep(2000);
            Assert.assertEquals(newAssignment.advancedFilter_list.get(0).getText().trim(), "Show only questions with hints", "'Not Showing only questions with Hints' check-box.");
            Assert.assertEquals(newAssignment.advancedFilter_list.get(1).getText().trim(), "Show only questions with solutions", "Not Showing questions with Solution' checkbox.");
            Assert.assertEquals(newAssignment.advancedFilter_list.get(2).getText().trim(), "Show only algorithmic questions", "'Not Showing only algorithmic questions' check-box.");

            boolean checked = false;
            List<WebElement> advancedFilterCheckbox = newAssignment.advancedFilter_checkbox;
            for (WebElement checkbox : advancedFilterCheckbox) {
                if (checkbox.isSelected() == true) {
                    checked = true;
                }
            }
            if (checked == true) {
                Assert.fail("All  three checkboxes is not unchecked by default.");
            }


            Assert.assertEquals(newAssignment.advancedFilter_link.getText().trim(), "- Advanced Filters", " \"+Advanced filters\" link is not to changed \"-Advanced filters\"");

            for (int i = 0; i <= 3; i++) {
                try {
                    newAssignment.advancedFilter_checkbox.get(0).click(); // click on the first checkbox
                    break;
                } catch (Exception e) {
                }

            }
            newAssignment.advancedFilter_link.click();//click on the -advanced filter link
            Thread.sleep(3000);
            newAssignment.advancedFilter_link.click();//click on the -advanced filter link

            if (!newAssignment.advancedFilter_checkbox.get(0).getAttribute("class").equals("ls-ins-customize-checkbox-hint checked")) {
                Assert.fail("Not showing same results untill the instructor navigates from the current assignment page. (Chech box should be checked for 'Show only questions with hints')");
            }

        } catch (Exception e) {
            Assert.fail("Exception in TC searchFunctionalityFromDashboardForMentor of class viewOptimizedQuestionFilterInSearch", e);
        }

    }
    @Test(priority = 9, enabled = true)
    public void allQuestionTypeFilterForSearchFromDashboard() {
        try {

            //TC row no 127
            Dashboard dashboard= PageFactory.initElements(driver,Dashboard.class);
            NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
            new LoginUsingLTI().ltiLogin("203"); //login as instructor
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", dashboard.getNewAssignments());   //click on the new Assignments button
            newAssignment.createCustomAssignment.click(); //click on the create custom assignment

            newAssignment.searchButton.click(); //click on the search link
            newAssignment.searchQuestion_textbox.click(); //click on the search textbox
            newAssignment.filter.click(); //click on the filter
            newAssignment.allQuestionType_dropdown.click();//click on the all question type

            int allQuestionTypeSize=newAssignment.allQuestionType_Filter.size();
            Assert.assertEquals(allQuestionTypeSize,18,"Not displaying 'All Question types' option followed by all the question types as supported by the product in (ascending order of question type name).");

            //Tc row no 204All Question types
            List<WebElement> checkBoxType=newAssignment.allQuestionType_checkbox;
            for(WebElement questionType:checkBoxType) {
                if(!questionType.getAttribute("type").equals("checkbox"))
                    Assert.fail("Not displaying check box infront of 'All the Question types' and for all the question types supported by the product.");
            }

            //TC row no 205
            Assert.assertFalse(new BrowseFunctionalityOfQuestionBank().checkBoxStatusChecked()); //check status for checked

            //Tc row no 206
            Assert.assertTrue(newAssignment.done_button.isDisplayed());

            //TC row no 207
            String doneButtonColor=newAssignment.done_button.getCssValue("color");
            Color color=null;
            String hex=null;
            color=Color.fromString(doneButtonColor);
            hex= color.asHex();
            Assert.assertEquals(hex,"#2b85c0","Done is not in blue color");

            //TC row no 208
            newAssignment.allQuestionType_Filter.get(0).click(); //click on the all question type
            Thread.sleep(3000);
            Assert.assertFalse(new BrowseFunctionalityOfQuestionBank().checkBoxStatusUnchecked()); //check for unchecked

            //TC row no 61
            Assert.assertTrue(newAssignment.done_deactivate.isDisplayed());
            String doneButtonColor1=newAssignment.done_deactivate.getCssValue("color");
            color=Color.fromString(doneButtonColor1);
            hex= color.asHex();
            Assert.assertEquals(hex,"#afb0b1","Done is not in gray color");

            //TC row no 62
            newAssignment.browse_dropdown.click(); //click on the body of browse dropdown

            Assert.assertFalse(newAssignment.allQuestionType_Filter.get(0).isDisplayed());
            //TC row no 63
            newAssignment.allQuestionType_dropdown.click();//click on the all question type
            Assert.assertFalse(new BrowseFunctionalityOfQuestionBank().checkBoxStatusChecked()); //check status for checked

            //TC row no 64
            String blue1=new BrowseFunctionalityOfQuestionBank().getColorOfParticularElement(newAssignment.done_button);
            Assert.assertEquals(blue1,"#2b85c0","Done is not in blue color");

            //TC row no 65
            newAssignment.allQuestionType_Filter.get(0).click(); //click on the all question type
            Assert.assertFalse(new BrowseFunctionalityOfQuestionBank().checkBoxStatusUnchecked()); //check for unchecked

            //TC row no 66 &
            String gray=new BrowseFunctionalityOfQuestionBank().getColorOfParticularElement(newAssignment.done_deactivate);
            Assert.assertEquals(gray,"#afb0b1","Done is not in gray color");

            //TC row no 68
            newAssignment.allQuestionType_Filter.get(1).click(); //click on the advanced Numeric
            newAssignment.done_button.click(); //click on done button
            Thread.sleep(3000);
            Assert.assertEquals(newAssignment.allQuestionType_dropdown.getAttribute("title"), "Advanced Numeric");

            newAssignment.searchQuestion_textbox.sendKeys("test");
            newAssignment.go_button.click();//click on go button

            //TC row no 70
            Assert.assertEquals(newAssignment.searchText.isEmpty(), false, "Browse results is not displayed in 'Find Questions' tab.");
            //TC row no 71

            newAssignment.filter.click(); //click on the filter
            newAssignment.allQuestionType_dropdown.click(); //click on the
            for(int i=2;i<=5;i++){
                new ScrollElement().scrollToViewOfElement(newAssignment.allQuestionType_Filter.get(i));
                newAssignment.allQuestionType_Filter.get(i).click(); //select 3 question type
                Thread.sleep(1000);

            }
            newAssignment.done_button.click();
            //TC row no 72
            Assert.assertTrue(newAssignment.multipleQuestions_elipses.isDisplayed());

            //TC row no 73
            newAssignment.multipleQuestions_elipses.click(); //click on the ellipses;
            Assert.assertEquals(newAssignment.question_Container.size(),5,"Not displaying the complete list of selcted question types as notification window.");

            //TC row no 74
            newAssignment.browse_dropdown.click(); //click on the body of browse dropdown
            Assert.assertFalse(new BooleanValue().presenceOfElement(74, By.className("help-data-container")));

            //TC row no 75
            newAssignment.go_button.click();
            Assert.assertEquals(newAssignment.searchText.isEmpty(), false, "Browse results is not displayed in 'Find Questions' tab.");

            //TC row no 76
            newAssignment.filter.click(); //click on the browse
            newAssignment.allQuestionType_dropdown.click(); //click on the
            int found=0;
            List<WebElement> checked=driver.findElements(By.xpath("//input[@type='checkbox']"));
            for(WebElement questionType:checked) {
                Thread.sleep(1500);
                if (questionType.isSelected()==true)
                {

                    found++;
                }
            }
            Assert.assertEquals(found,5,"selected question types not  preserved until the instructor navigates away from the “create custom assignment” page");


        } catch (Exception e) {
            Assert.fail("Exception in TC allQuestionTypeFilterForSearchFromDashboard of class viewOptimizedQuestionFilterInSearch", e);
        }
    }


    @Test(priority = 10, enabled = true)
    public void allQuestionTypeSearchFilterFunctionalityFromDashboard() {
        try {
            //TC row no 297
            Dashboard dashboard= PageFactory.initElements(driver,Dashboard.class);
            NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
            new LoginUsingLTI().ltiLogin("203"); //login as instructor
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", dashboard.getNewAssignments());   //click on the new Assignments button
            newAssignment.createCustomAssignment.click(); //click on the create custom assignment

            newAssignment.searchButton.click(); //click on the search link
            newAssignment.searchQuestion_textbox.click(); //click on the search textbox
            newAssignment.filter.click(); //click on the filter
            Thread.sleep(2000);
            newAssignment.allQuestionType_dropdown.click();//click on the all question type

            newAssignment.allQuestionType_Filter.get(1).click(); //click on the all question type

            Assert.assertFalse(newAssignment.allQuestionType_Filter.get(0).isSelected());
            Assert.assertFalse(newAssignment.allQuestionType_Filter.get(1).isSelected());

            for(int i=2;i<newAssignment.allQuestionType_Filter.size();i++){
                Assert.assertTrue((boolean) ((JavascriptExecutor) driver).executeScript("return document.getElementById('ddcl-ls-ins-question-type-down-1-i" + i + "').checked"));//check for checked box for true
            }

            newAssignment.browse_dropdown.click();
            Assert.assertEquals(newAssignment.allQuestionType_dropdown.getAttribute("title"),"Multiple Question Types ...","The results of selected question types is not getting save and display in \"All Question types' field.");



        } catch (Exception e) {
            Assert.fail("Exception in TC allQuestionTypeSearchFilterFunctionalityFromDashboard of class viewOptimizedQuestionFilterInSearch", e);
        }
    }
    @Test(priority = 11, enabled = true)
    public void newOrderQuestionInSearch() {
        try {
            //TC row no 300
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            Dashboard dashboard= PageFactory.initElements(driver,Dashboard.class);
            NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
            new LoginUsingLTI().ltiLogin("203"); //login as instructor
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", dashboard.getNewAssignments());   //click on the new Assignments button
            newAssignment.createCustomAssignment.click(); //click on the create custom assignment
            newAssignment.searchButton.click(); //click on the search link
            newAssignment.searchQuestion_textbox.click(); //click on the search textbox
            newAssignment.filter.click(); //click on the browse link
            newAssignment.searchQuestion_textbox.sendKeys("test");
            newAssignment.go_button.click();//click on go button
            List<WebElement> chapterNo = newAssignment.chapterNumber;
            for(int i=0;i<chapterNo.size()-1;i++) {

                String str = chapterNo.get(i).getAttribute("title").substring(0, 4);
                String nextstr = chapterNo.get(i + 1).getAttribute("title").substring(0, 4);
                if (str.compareTo(nextstr) > 1) {
                    Assert.fail("The order of the questions is not in ascending order of chapter no");
                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC newOrderQuestionInSearch of class viewOptimizedQuestionFilterInSearch", e);
        }
    }

}
