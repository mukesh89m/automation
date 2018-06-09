package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R169;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SearchQuestionInCustomCourseAssignemnt;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by @Brajesh Kumar on 16-Sep-14.
 * Testcase-IT16 R16.9
 * Started Date:-16/09/2014
 * End Date:-16/09/2014
 */
public class FunctionalityOfCustomCreateAssignment extends Driver {
    @Test
    public void functionalityOfCustomCreateAssignment() {
        try {
            String questionText = ReadTestData.readDataByTagName("", "questiontext", "26");
            new Assignment().create(26);//assignment create by author
            new LoginUsingLTI().ltiLogin("26");
            new Navigator().NavigateTo("Question Banks");//Navigate to resources
            new Click().clickByid("customAssignment");//click on create custom assignment

            //Row no-26
            //step:-1. Verify search & browse icons
            boolean searchIcon = new BooleanValue().booleanbyclass("ls-ins-search-icon");
            boolean browseIcon = new BooleanValue().booleanbyclass("ls-ins-browse-icon ");
            boolean separator = new BooleanValue().booleanbyclass("search-browse-separator");

            //Excepted:-Only Search and Browse options should appear along with line separator 'OR' in the middle
            Assert.assertEquals(true, searchIcon, "search icon not shown on create custom assignment page");
            Assert.assertEquals(true, browseIcon, "browse icon not shown on create custom assignment page");
            Assert.assertEquals(true, separator, "separator icon not shown on create custom assignment page");

            //Row no-27
            //Step:-2. Click on Search button
            //Excepted of row no -27:-Search option should expand showing Search text-box
            //Row no-28. Type any text and Initiate the search by clicking on the “Search” button
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(questionText);
            //Excepted:-28:-"Filter" tool should show up for search result
            boolean filterOption = new BooleanValue().booleanbyclass("ls-ins-view-filters");
            Assert.assertEquals(true, filterOption, "filter option not shown after search the question");

            //Row no-30:-5. Verify Expand All Questions/Collapse All Questions options
            String expandText = new TextFetch().textfetchbyclass("custom-expand-all-text");
            //Excepted:-By default it should be in close state and should show the label “Expand All Questions”
            Assert.assertEquals(true, expandText.contains("Expand all questions"), "By default expand/collapse option not be in close state and not show the label “Expand All Questions”");

            //Row no-31-6. step:-Click on Expand All Questions option
            new Click().clickByclassname("custom-expand-all-icon");//click on expand all question

            //Excepted:-Clicking on "Expand All Questions” should change the option to “Collapse all questions” and should open all the question cards .
            String collapseText = new TextFetch().textfetchbyclass("custom-expand-all-text");
            Assert.assertEquals(true, collapseText.contains("Collapse all questions"), "After click on expand all questions text not be changed to 'Collapse all questions'");

            //Row no-29 4. Verify "Shuffle answer choices” text displaying under question
            String shuffleAnswerChoiceText = new TextFetch().textfetchbylistclass("ls-ins-question-wrapper", 0);
            //Excepted :-“Shuffle answer choices” option should be removed
            Assert.assertEquals(false, shuffleAnswerChoiceText.contains("Shuffle answer choices"), "shuffle answer choice option not remove for  question");

            //Row no-32  7. Click on Collapse All Questions option
            new Click().clickByclassname("custom-collapse-all-icon");//click on collapse all question icon
            //Excepted:-Clicking on “Collapse All Questions” should change the option to "Expand all Questions" and should collapse all the question cards .
            String expandText1 = new TextFetch().textfetchbyclass("custom-expand-all-text");
            Assert.assertEquals(true, expandText1.contains("Expand all questions"), "After click on collapse all questions label not changed to  “Expand All Questions”");

            //row no-35
            //Step:-10. Verify "Selected Questions" tab for Manual grading
            new Navigator().navigateToTab("Selected Questions");
            //Excepted:-Manual Grading checkbox should have help text saying “Use this checkbox to grade the  assignment manually . If checkbox is not selected, the assignment would be auto graded “.
            new Click().clickByclassname("manual-grading-help-icon");
            String manualGradingHelpTextActual = new TextFetch().textfetchbyclass("manual-grading-help-tooltip-wrapper");
            Assert.assertEquals(true, manualGradingHelpTextActual.contains("Use this checkbox to grade the assignment manually."), "help text not shown for manual grading");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class FunctionalityOfCustomCreateAssignment in test method functionalityOfCustomCreateAssignment", e);
        }
    }

}

