package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R236;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by mukesh on 27/8/15.
 */
public class CreateCustomAssignmentFromDifferentPlaces extends Driver {

    @Test(priority = 1, enabled = true)
    public void SearchFilterFromCAP() {

        try {
            //TC row no 456
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("456"); //login as instructor
            new Navigator().NavigateTo("Current Assignments"); //Navigate to Current Assignments
            currentAssignments.newAssignment_Button.click(); //click on the new Assignment
            currentAssignments.createCustomAssignmentButton.click(); //click on the create custom assignment

            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            new BrowseSearchFunctionalityMethod().viewOptimizedQuestionFilter();
            goToCreateCustomAssignmentFromCAP();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            new BrowseSearchFunctionalityMethod().filterWithCheckboxAndColorVerification();
            goToCreateCustomAssignmentFromCAP();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            new BrowseSearchFunctionalityMethod().deSelectAnyOneQuestion();
            goToCreateCustomAssignmentFromCAP();
           new  BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            newAssignment.searchQuestion_textbox.sendKeys("test");
            newAssignment.go_button.click();//click on go button
            new BrowseSearchFunctionalityMethod().determineOrderOfQuestion();


        } catch (Exception e) {
            Assert.fail("Exception in TC SearchFilterFromCAP of class CreateCustomAssignmentFromDifferentPlaces", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void BrowseFilterFromCAP() {

        try {
            //TC row no 457
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("456"); //login as instructor
            new Navigator().NavigateTo("Current Assignments"); //Navigate to Current Assignments
            currentAssignments.newAssignment_Button.click(); //click on the new Assignment
            currentAssignments.createCustomAssignmentButton.click(); //click on the create custom assignment
            new BrowseSearchFunctionalityMethod().clickOnTheBrowse();
            new BrowseSearchFunctionalityMethod().viewOptimizedQuestionFilter();
            goToCreateCustomAssignmentFromCAP();
            new BrowseSearchFunctionalityMethod().clickOnTheBrowse();
            new BrowseSearchFunctionalityMethod().filterWithCheckboxAndColorVerification();
            goToCreateCustomAssignmentFromCAP();
            new BrowseSearchFunctionalityMethod().clickOnTheBrowse();
            new BrowseSearchFunctionalityMethod().deSelectAnyOneQuestion();
            goToCreateCustomAssignmentFromCAP();
            new BrowseSearchFunctionalityMethod().clickOnTheBrowse();
            newAssignment.go_button.click();//click on go button
            new BrowseSearchFunctionalityMethod().determineOrderOfQuestion();


        } catch (Exception e) {
            Assert.fail("Exception in TC BrowseFilterFromCAP of class CreateCustomAssignmentFromDifferentPlaces", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void SearchFilterFromCustomizedThis() {

        try {
            //TC row no 460
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new LoginUsingLTI().ltiLogin("456"); //login as instructor
            clickOnTheCustomizedThis();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            new BrowseSearchFunctionalityMethod().viewOptimizedQuestionFilter();

            clickOnTheCustomizedThis();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            new BrowseSearchFunctionalityMethod().filterWithCheckboxAndColorVerification();

            clickOnTheCustomizedThis();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            new BrowseSearchFunctionalityMethod().deSelectAnyOneQuestion();

            clickOnTheCustomizedThis();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            newAssignment.searchQuestion_textbox.sendKeys("test");
            newAssignment.go_button.click();//click on go button
            new BrowseSearchFunctionalityMethod().determineOrderOfQuestion();


        } catch (Exception e) {
            Assert.fail("Exception in TC SearchFilterFromCustomizedThis of class CreateCustomAssignmentFromDifferentPlaces", e);
        }
    }


    @Test(priority = 4, enabled = true)
    public void browseFilterFromCustomizedThis() {

        try {
            //TC row no 464
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new LoginUsingLTI().ltiLogin("456"); //login as instructor
            clickOnTheCustomizedThis();
            new BrowseSearchFunctionalityMethod().clickOnTheBrowse();
            new BrowseSearchFunctionalityMethod().viewOptimizedQuestionFilter();

            clickOnTheCustomizedThis();
            new BrowseSearchFunctionalityMethod().clickOnTheBrowse();
            new BrowseSearchFunctionalityMethod().filterWithCheckboxAndColorVerification();

            clickOnTheCustomizedThis();
            new BrowseSearchFunctionalityMethod().clickOnTheBrowse();
           new BrowseSearchFunctionalityMethod().deSelectAnyOneQuestion();

            clickOnTheCustomizedThis();
            new BrowseSearchFunctionalityMethod().clickOnTheBrowse();
            newAssignment.go_button.click();//click on go button
            new BrowseSearchFunctionalityMethod().determineOrderOfQuestion();


        } catch (Exception e) {
            Assert.fail("Exception in TC browseFilterFromCustomizedThis of class CreateCustomAssignmentFromDifferentPlaces", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void searchFilterForSaveForLater(){

        try {
            //TC row no 468
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "468");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "468");
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            new LoginUsingLTI().ltiLogin("468");
            new Navigator().NavigateTo("New Assignment");
            //click on Custom assignment
            newAssignment.createCustomAssignment.click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);//search question
            new AssignLesson().selectQuestionForCustomAssignment("468");//select question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);//Type name

            newAssignment.saveForLater_Button.click(); //click on the save for later
            Thread.sleep(3000);
            newAssignment.close_Icon.click(); //click on the x icon to close assignmnet
            new Navigator().navigateToTab("My Question Bank"); //navigate to My Question Bank
            Thread.sleep(3000);
            myQuestionBank.editThis.click();
            new Navigator().navigateToTab("Find Questions"); //navigate to Find Questions
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            new BrowseSearchFunctionalityMethod().viewOptimizedQuestionFilter();

            clickOnTheEditThis();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            new BrowseSearchFunctionalityMethod().filterWithCheckboxAndColorVerification();

            clickOnTheEditThis();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            new BrowseSearchFunctionalityMethod().deSelectAnyOneQuestion();

            clickOnTheEditThis();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            newAssignment.searchQuestion_textbox.sendKeys("test");
            newAssignment.go_button.click();//click on go button
            new BrowseSearchFunctionalityMethod().determineOrderOfQuestion();




        } catch (Exception e) {
            Assert.fail("Exception in TC searchFilterForSaveForLater of class CreateCustomAssignmentFromDifferentPlaces", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void browseFilterForEditThisLink(){

        try {
            //TC row no 468
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "468");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "468");
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            new LoginUsingLTI().ltiLogin("468");
            new Navigator().NavigateTo("New Assignment");
            //click on Custom assignment
            newAssignment.createCustomAssignment.click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);//search question
            new AssignLesson().selectQuestionForCustomAssignment("468");//select question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);//Type name

            newAssignment.saveForLater_Button.click(); //click on the save for later
            Thread.sleep(3000);
            newAssignment.close_Icon.click(); //click on the x icon to close assignmnet
            new Navigator().navigateToTab("My Question Bank"); //navigate to My Question Bank
            myQuestionBank.editThis.click();
            new Navigator().navigateToTab("Find Questions"); //navigate to Find Questions

            new BrowseSearchFunctionalityMethod().clickOnTheBrowse();
            new BrowseSearchFunctionalityMethod().viewOptimizedQuestionFilter();

            clickOnTheEditThis();
            new BrowseSearchFunctionalityMethod().clickOnTheBrowse();
            new BrowseSearchFunctionalityMethod().filterWithCheckboxAndColorVerification();

            clickOnTheEditThis();
            new BrowseSearchFunctionalityMethod().clickOnTheBrowse();
            new BrowseSearchFunctionalityMethod().deSelectAnyOneQuestion();

            clickOnTheEditThis();
            new BrowseSearchFunctionalityMethod().clickOnTheBrowse();
            newAssignment.go_button.click();//click on go button
            new BrowseSearchFunctionalityMethod().determineOrderOfQuestion();


        } catch (Exception e) {
            Assert.fail("Exception in TC browseFilterForEditThisLink of class CreateCustomAssignmentFromDifferentPlaces", e);
        }
    }


    @Test(priority = 7, enabled = true)
    public void searchFilterForUpdateAssignment(){

        try {
            //TC row no 472
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "472");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "472");
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            new LoginUsingLTI().ltiLogin("472");
            new Navigator().NavigateTo("New Assignment");
            //click on Custom assignment
            newAssignment.createCustomAssignment.click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);//search question
            new AssignLesson().selectQuestionForCustomAssignment("472");//select question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);//Type name
            Thread.sleep(5000);
            myQuestionBank.getSaveForLater().click(); //click on the save for later
            new UIElement().waitAndFindElement(myQuestionBank.getMyQuestionBankTitle());
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(472);
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIcon().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getAssign().click();//click on assign
            Thread.sleep(1000);
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            new UIElement().waitAndFindElement(myQuestionBank.getAssessment());
            Assert.assertEquals(myQuestionBank.getAssessment().getAttribute("title"),"customAssigment_IT23_R236_472 - V1","New version of assignment  \"#name of assignment -V1\" is not creating ");

            clickOnTheCustomizedThisForQuestionVersion();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            new BrowseSearchFunctionalityMethod().viewOptimizedQuestionFilter();

            clickOnTheCustomizedThisForQuestionVersion();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            new BrowseSearchFunctionalityMethod().filterWithCheckboxAndColorVerification();

            clickOnTheCustomizedThisForQuestionVersion();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            new BrowseSearchFunctionalityMethod().deSelectAnyOneQuestion();

            clickOnTheCustomizedThisForQuestionVersion();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            newAssignment.searchQuestion_textbox.sendKeys("test");
            newAssignment.go_button.click();//click on go button
            new BrowseSearchFunctionalityMethod().determineOrderOfQuestion();


        } catch (Exception e) {
            Assert.fail("Exception in TC searchFilterForUpdateAssignment of class CreateCustomAssignmentFromDifferentPlaces", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void browseFilterForUpdateAssignment(){

        try {
            //TC row no 472
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "472");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "472");
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            new LoginUsingLTI().ltiLogin("472");
            new Navigator().NavigateTo("New Assignment");
            //click on Custom assignment
            newAssignment.createCustomAssignment.click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);//search question
            new AssignLesson().selectQuestionForCustomAssignment("472");//select question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);//Type name
            Thread.sleep(5000);
            myQuestionBank.getSaveForLater().click(); //click on the save for later
            new UIElement().waitAndFindElement(myQuestionBank.getMyQuestionBankTitle());
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(472);
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIcon().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getAssign().click();//click on assign
            Thread.sleep(1000);
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            new UIElement().waitAndFindElement(myQuestionBank.getAssessment());
            Assert.assertEquals(myQuestionBank.getAssessment().getAttribute("title"),"customAssigment_IT23_R236_472 - V1","New version of assignment  \"#name of assignment -V1\" is not creating ");

            new BrowseSearchFunctionalityMethod().clickOnTheBrowse();
            new BrowseSearchFunctionalityMethod().viewOptimizedQuestionFilter();

            clickOnTheCustomizedThisForQuestionVersion();
            new BrowseSearchFunctionalityMethod().clickOnTheBrowse();
            new BrowseSearchFunctionalityMethod().filterWithCheckboxAndColorVerification();

            clickOnTheCustomizedThisForQuestionVersion();
            new BrowseSearchFunctionalityMethod().clickOnTheBrowse();
            new BrowseSearchFunctionalityMethod().deSelectAnyOneQuestion();

            clickOnTheCustomizedThisForQuestionVersion();
            new BrowseSearchFunctionalityMethod().clickOnTheBrowse();
            newAssignment.go_button.click();//click on go button
            new BrowseSearchFunctionalityMethod().determineOrderOfQuestion();



        } catch (Exception e) {
            Assert.fail("Exception in TC browseFilterForUpdateAssignment of class CreateCustomAssignmentFromDifferentPlaces", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void searchFilterForShareWithOtherInstructor(){

        try {
            //TC row no 478
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(479));
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "478");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "478");
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            new LoginUsingLTI().ltiLogin("479"); //login as instructor2
            new LoginUsingLTI().ltiLogin("478"); //login as instructor1

            new Navigator().NavigateTo("New Assignment");
            //click on Custom assignment
            newAssignment.createCustomAssignment.click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);//search question
            new AssignLesson().selectQuestionForCustomAssignment("478");//select question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);//Type name
            Thread.sleep(5000);
            myQuestionBank.getSaveForLater().click(); //click on the save for later
            new UIElement().waitAndFindElement(myQuestionBank.getMyQuestionBankTitle());
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank

            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            new Assignment().shareCustomAssignment(shareName);
            myQuestionBank.yesOnShareMessage.click();//click on yes*/

            new LoginUsingLTI().ltiLogin("479"); //login as instructor2
            clickOnTheCustomizedThisForQuestionVersion();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            new BrowseSearchFunctionalityMethod().viewOptimizedQuestionFilter();

            clickOnTheCustomizedThisForQuestionVersion();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            new BrowseSearchFunctionalityMethod().filterWithCheckboxAndColorVerification();

            clickOnTheCustomizedThisForQuestionVersion();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            new BrowseSearchFunctionalityMethod().deSelectAnyOneQuestion();

            clickOnTheCustomizedThisForQuestionVersion();
            new BrowseSearchFunctionalityMethod().clickOnTheFilerForSearch();
            newAssignment.searchQuestion_textbox.sendKeys("test");
            newAssignment.go_button.click();//click on go button
            new BrowseSearchFunctionalityMethod().determineOrderOfQuestion();


        } catch (Exception e) {
            Assert.fail("Exception in TC searchFilterForShareWithOtherInstructor of class CreateCustomAssignmentFromDifferentPlaces", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void toggleBetweenSearchAndBrowse(){

        try {
            //TC row no 482
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            new LoginUsingLTI().ltiLogin("472"); //login as instructor

            new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
            myQuestionBank.getCustomAssignmentButton().click(); //click on the custom assignment name
            Assert.assertEquals(new BooleanValue().presenceOfElement(12,By.className("ls-ins-browse-icon ")),true,"instructor not navigated to custom assignmnet page");
            //TC row no 13
            newAssignment.browse_link.click(); //click on the browse link
            myQuestionBank.getAllChapters().click(); //click on the all chapter
            Thread.sleep(4000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",newAssignment.chapterName.get(2)); //select second chapter

            newAssignment.go_button.click(); //click on the go button

            Assert.assertEquals(newAssignment.searchText.isEmpty(), false, "Browse results is not displayed in 'Find Questions' tab.");

            Assert.assertEquals(newAssignment.chapterNumber.get(0).getText().trim(),"Ch 2: The Chemical Foundation of Life");

            newAssignment.searchButton.click(); //click on the search button

            newAssignment.searchQuestion_textbox.sendKeys("expression");

            newAssignment.searchButton.click(); //click on the go button

            Assert.assertEquals(newAssignment.searchText.isEmpty(), false, "Browse results is not displayed in 'Find Questions' tab.");
            new UIElement().waitAndFindElement(newAssignment.chapterNumber.get(0));
            Assert.assertEquals(newAssignment.chapterNumber.get(0).getText().trim(),"Ch 1: The Study of Life");

            newAssignment.browse_link.click(); // click on the browse link

            for(WebElement each:newAssignment.secondChapterName)
                if(each.isDisplayed()){
                    each.click();
                }



        } catch (Exception e) {
            Assert.fail("Exception in TC toggleBetweenSearchAndBrowse of class CreateCustomAssignmentFromDifferentPlaces", e);
        }
    }
    @Test(priority = 10, enabled = true)
    public void cmsCreatedAdvancedQuestionFilterLink(){

        try {
            //TC row no 488
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(488));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "488");
            String questiontext490 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(490));
            String assessmentname490 = ReadTestData.readDataByTagName("", "assessmentname", "490");

            new Assignment().create(488);
            new LoginUsingLTI().ltiLogin("488"); //login as instructor

            new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
            myQuestionBank.getCustomAssignmentButton().click(); //click on the custom assignment name
            newAssignment.browse_link.click(); //click on the browse link
            newAssignment.advancedFilter_link.click(); //click on advanced filter link

            newAssignment.advancedFilter_checkbox.get(0).click(); // click on the first checkbox
            newAssignment.checkboxForInstructorAssignment.click();
            newAssignment.go_button.click();
            new UIElement().waitAndFindElement(newAssignment.searchText.get(0));
            Assert.assertEquals(newAssignment.searchText.get(0).getText().trim(), "True False " + questiontext);
            new Assignment().deActivateQuestion(488,assessmentname,0);

            new LoginUsingLTI().ltiLogin("488"); //login as instructor

            new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
            myQuestionBank.getCustomAssignmentButton().click(); //click on the custom assignment name
            newAssignment.browse_link.click(); //click on the browse link
            newAssignment.advancedFilter_link.click(); //click on advanced filter link

            newAssignment.advancedFilter_checkbox.get(0).click(); // click on the first checkbox
            newAssignment.checkboxForInstructorAssignment.click();
            newAssignment.go_button.click();
            new UIElement().waitAndFindElement(newAssignment.searchText.get(0));
            Assert.assertNotEquals(newAssignment.searchText.get(0).getText().trim(), "True False " + questiontext);

            //TC row no 490

            new Assignment().create(490);
            new LoginUsingLTI().ltiLogin("490"); //login as instructor

            new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
            myQuestionBank.getCustomAssignmentButton().click(); //click on the custom assignment name
            newAssignment.browse_link.click(); //click on the browse link
            newAssignment.advancedFilter_link.click(); //click on advanced filter link

            newAssignment.advancedFilter_checkbox.get(0).click(); // click on the first checkbox
            newAssignment.advancedFilter_checkbox.get(1).click(); // click on the second checkbox

            newAssignment.checkboxForInstructorAssignment.click();
            newAssignment.go_button.click();
            new UIElement().waitAndFindElement(newAssignment.searchText.get(0));
            Assert.assertEquals(newAssignment.searchText.get(0).getText().trim(), "True False " + questiontext490);
            new Assignment().deActivateQuestion(490,assessmentname490,0);

            new LoginUsingLTI().ltiLogin("490"); //login as instructor
            driver.navigate().refresh();
            driver.navigate().refresh();
            driver.navigate().refresh();
            Thread.sleep(10000);
            new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
            myQuestionBank.getCustomAssignmentButton().click(); //click on the custom assignment name
            newAssignment.browse_link.click(); //click on the browse link
            newAssignment.advancedFilter_link.click(); //click on advanced filter link

            newAssignment.advancedFilter_checkbox.get(0).click(); // click on the first checkbox
            newAssignment.advancedFilter_checkbox.get(1).click(); // click on the second checkbox

            newAssignment.checkboxForInstructorAssignment.click();
            newAssignment.go_button.click();
            new UIElement().waitAndFindElement(newAssignment.searchText.get(0));

            Assert.assertNotEquals(newAssignment.searchText.get(0).getText().trim(), "True False " + questiontext490);

        } catch (Exception e) {
            Assert.fail("Exception in TC cmsCreatedAdvancedQuestionFilterLink of class CreateCustomAssignmentFromDifferentPlaces", e);
        }
    }


    @Test(priority = 10, enabled = true)
    public void algoDataTypeFilterLink(){

        try {
            //TC row no 492
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(492));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "492");

            new Assignment().create(492);

        } catch (Exception e) {
            Assert.fail("Exception in TC algoDataTypeFilterLink of class CreateCustomAssignmentFromDifferentPlaces", e);
        }
    }


    public  void goToCreateCustomAssignmentFromCAP() {
        CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
        new Navigator().NavigateTo("Current Assignments"); //Navigate to Current Assignments
        currentAssignments.newAssignment_Button.click(); //click on the new Assignment
        currentAssignments.createCustomAssignmentButton.click(); //click on the create custom assignment
    }
    public  void clickOnTheCustomizedThis() {
        MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
        new Navigator().NavigateTo("My Question Bank"); //navigate to my Question bank
        new Navigator().navigateToTab("Question Banks"); //navigate to Question Banks
        myQuestionBank.customizeThis.click(); //click on the customized this link
    }
    public  void clickOnTheCustomizedThisForQuestionVersion() {
        MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
        new Navigator().NavigateTo("My Question Bank"); //navigate to my Question bank
        myQuestionBank.customizeThis.click(); //click on the customized this link
    }


    public  void clickOnTheEditThis() throws InterruptedException {
        NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
        MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
        newAssignment.close_Icon.click(); //click on the x icon to close assignmnet
        new Navigator().navigateToTab("My Question Bank"); //navigate to My Question Bank
        Thread.sleep(3000);
        myQuestionBank.editThis.click();
        new Navigator().navigateToTab("Find Questions"); //navigate to Find Questions
    }

}