package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R236;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

/**
 * Created by mukesh on 27/8/15.
 */
public class BrowseSearchFunctionalityMethod extends Driver{

    public   void viewOptimizedQuestionFilter() {
        NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
        try {

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
            newAssignment.advancedFilter_link.click();//click on the -advanced filter link

            if(!newAssignment.advancedFilter_checkbox.get(0).getAttribute("class").equals("ls-ins-customize-checkbox-hint checked")){
                Assert.fail("Not showing same results untill the instructor navigates from the current assignment page. (Chech box should be checked for 'Show only questions with hints')");
            }
        } catch (Exception e) {
            Assert.fail("Exception in method viewOptimizedQuestionFilter of class BrowseSearchFunctionalityMethod" );

         }

    }

    public   void filterWithCheckboxAndColorVerification() {
        NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);

        try {

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
            Assert.assertFalse( new BrowseFunctionalityOfQuestionBank().checkBoxStatusUnchecked()); //check for unchecked

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
            if(newAssignment.searchQuestion_textbox.isDisplayed()) {
                newAssignment.searchQuestion_textbox.sendKeys("test");
            }
            newAssignment.go_button.click();//click on go button

            //TC row no 70
            Assert.assertEquals(newAssignment.searchText.isEmpty(), false, "Browse results is not displayed in 'Find Questions' tab.");
            //TC row no 71
            if(newAssignment.filter.isDisplayed() && newAssignment.browse_link.isDisplayed()) {
                newAssignment.filter.click(); //click on the filter
            }

            if(newAssignment.browse_link.isDisplayed()  && !newAssignment.filter.isDisplayed()) {
                newAssignment.browse_link.click(); //click on the browse
            }
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
            if(newAssignment.filter.isDisplayed() && newAssignment.browse_link.isDisplayed()) {
                newAssignment.filter.click(); //click on the filter
            }

            if(newAssignment.browse_link.isDisplayed()  && !newAssignment.filter.isDisplayed()) {
                newAssignment.browse_link.click(); //click on the browse
            }

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

        } catch (InterruptedException e) {
            Assert.fail("Exception in method filterWithCheckboxAndColorVerification of class BrowseSearchFunctionalityMethod");
        }

    }
    public   void filterWithCheckboxAndColorVerificationLS() {
        NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);

        try {

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
            new ScrollElement().scrollToViewOfElement(newAssignment.allQuestionType_Filter.get(16));
            newAssignment.allQuestionType_Filter.get(16).click(); //click on the advanced Numeric
            newAssignment.done_button.click(); //click on done button
            Thread.sleep(3000);
            Assert.assertEquals(newAssignment.allQuestionType_dropdown.getAttribute("title"), "True/False");

            if(newAssignment.searchQuestion_textbox.isDisplayed()) {
                newAssignment.searchQuestion_textbox.sendKeys("test");
            }
            newAssignment.go_button.click();//click on go button

            //TC row no 70
            Assert.assertEquals(newAssignment.searchText.isEmpty(), false, "Browse results is not displayed in 'Find Questions' tab.");
            //TC row no 71
            if(newAssignment.filter.isDisplayed() && newAssignment.browse_link.isDisplayed()) {
                newAssignment.filter.click(); //click on the filter
            }

            if(newAssignment.browse_link.isDisplayed()  && !newAssignment.filter.isDisplayed()) {
                newAssignment.browse_link.click(); //click on the browse
            }
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
            if(newAssignment.filter.isDisplayed() && newAssignment.browse_link.isDisplayed()) {
                newAssignment.filter.click(); //click on the filter
            }

            if(newAssignment.browse_link.isDisplayed()  && !newAssignment.filter.isDisplayed()) {
                newAssignment.browse_link.click(); //click on the browse
            }

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

        } catch (InterruptedException e) {
            Assert.fail("Exception in method filterWithCheckboxAndColorVerificationLS of class BrowseSearchFunctionalityMethod");
        }

    }

    public  void deSelectAnyOneQuestion()
    {
        NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);

            newAssignment.allQuestionType_dropdown.click();//click on the all question type
            newAssignment.allQuestionType_Filter.get(1).click(); //click on the all question type

            Assert.assertFalse(newAssignment.allQuestionType_Filter.get(0).isSelected());
            Assert.assertFalse(newAssignment.allQuestionType_Filter.get(1).isSelected());

            for(int i=2;i<newAssignment.allQuestionType_Filter.size();i++){
                Assert.assertTrue((boolean) ((JavascriptExecutor) driver).executeScript("return document.getElementById('ddcl-ls-ins-question-type-down-3-i" + i + "').checked"));//check for checked box for true
            }
            newAssignment.browse_dropdown.click();
            Assert.assertEquals(newAssignment.allQuestionType_dropdown.getAttribute("title"),"Multiple Question Types ...","The results of selected question types is not getting save and display in \"All Question types' field.");

        }


    public  void determineOrderOfQuestion()
    {
        NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
            Assert.assertEquals(newAssignment.searchText.isEmpty(), false, "Browse results is not displayed in 'Find Questions' tab.");

            List<WebElement> chapterNo = newAssignment.chapterNumber;
            for(int i=0;i<chapterNo.size()-1;i++) {
                new UIElement().waitAndFindElement(chapterNo.get(i));
                String str = chapterNo.get(i).getAttribute("title").substring(0, 4);
                String nextStr = chapterNo.get(i + 1).getAttribute("title").substring(0, 4);
                if (str.compareTo(nextStr) > 1) {
                    Assert.fail("The order of the questions is not in ascending order of chapter no");
                }
            }
        }


    public  void clickOnTheFilerForSearch()
    {
        try {
            NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
            newAssignment.searchButton.click(); //click on the search link
            newAssignment.searchQuestion_textbox.click(); //click on the search textbox
            Assert.assertTrue(newAssignment.filter.isDisplayed(), "filter button is not next to search label");
            newAssignment.filter.click(); //click on the filter
        } catch (Exception e) {
            Assert.fail("Exception in method clickOnTheFilerForSearch of class BrowseSearchFunctionalityMethod");
        }
    }

    public  void clickOnTheBrowse()
    {
        try {
            NewAssignment newAssignment= PageFactory.initElements(driver,NewAssignment.class);
            newAssignment.browse_link.click(); //click on the browse link

        } catch (Exception e) {
            Assert.fail("Exception in method clickOnTheBrowse of class BrowseSearchFunctionalityMethod");
        }
    }
}
