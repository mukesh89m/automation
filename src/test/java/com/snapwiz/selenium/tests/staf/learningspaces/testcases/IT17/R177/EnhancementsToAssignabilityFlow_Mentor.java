package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R177;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by snapwiz on 17-Nov-14.
 */
public class EnhancementsToAssignabilityFlow_Mentor extends Driver {
    @Test(priority = 1,enabled = true)
    public void assignThisFromQuestionBankPage(){
      /*  Steps : "1. Login as instructor.
        2. Go to question bank.
        3. Click on assign this for an entry."*/
        try {
            String orderNo = "3";
            new Assignment().create(146);// Create an assignment with true false question creation
            new Assignment().addQuestions(146, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");// Navigate to 'Question Banks'
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(146));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();// Clcik on 'Assugn this' button
            populateAllActiveClassSections_Instructor("146",orderNo);
            removeClassSections_Instructor("147");
            reselectRemovedClassSections_Instructor("148",orderNo);
            displayAutoSuggestForAllStudentForAllSections_Instructor("149 or 150",orderNo);
            selectAllStudentsFromAutoSuggest("151 or 152",orderNo);
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisFromQuestionBankPage' in class 'EnhancementsToAssignabilityFlow_Mentor'", e);
        }

    }




    @Test(priority = 2,enabled = true)
    public void assignThisFromQuestionBankLibrary(){

        /*"1. Login as instructor.
        2. Go to question bank library
        3. Click on assign this for an entry."*/
        try {
            String orderNo = "3";
            //Driver.startDriver();//start browser
            new Assignment().create(153);// Create an assignment with true false question creation
            new Assignment().addQuestions(153, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();//Method call to create multiple class sections
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");//Navigate to Question Banks
            new Click().clickByid("all-resource-search-textarea");//Click on 'Search Question Banks...' Text Area
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(153));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type the text 'Search Question Banks...' Text Area
            new Click().clickByid("all-resource-search-button");//Click on 'Search' button
            new Click().clickbyxpath("//i[normalize-space(@class)='addThisToMyResources not-bookmarked ls-resource-bookmark-icon']");//Click on 'Add to My Question Bank' Bookmark for a first entry
            new Navigator().navigateToTab("My Question Bank");// Click on Tab 'My Question Bank'
            new Click().clickByid("my-resource-search-textarea");//Click on 'Search Question Banks...' Text Area
            new TextSend().textsendbyid("\"" + assessmentName + "\"","my-resource-search-textarea");//Type assessment name  'Search Question Banks...' Text Area
            new Click().clickByid("my-resource-search-button");//Click on 'Search' button
            new AssignmentsDetails().assignThis_ButtonClick();
            populateAllActiveClassSections_Instructor("153",orderNo);
            removeClassSections_Instructor("154");
            reselectRemovedClassSections_Instructor("155",orderNo);
            displayAutoSuggestForAllStudentForAllSections_Instructor("156 or 157",orderNo);
            selectAllStudentsFromAutoSuggest("158 or 159",orderNo);
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisFromQuestionBankLibrary' in class 'EnhancementsToAssignabilityFlow_Mentor'", e);
        }

    }


    @Test(priority = 3,enabled = true)
    public void assignThisFromResourcePage(){
        /* Steps : "1. Login as instructor.
        2. Go to resource page.
        3. Click on assign this for an entry."*/
        try {
            String orderNo = "3";
            //Driver.startDriver();
            new ResourseCreate().resourseCreate(160,0);// method call to Create a resource of Type 'image'
            new LoginUsingLTI().ltiLogin("3_1");// Login as an 'mentor' with CS1 sections
            new LoginUsingLTI().ltiLogin("3_2");// Login as an 'mentor' with CS2 sections
            new LoginUsingLTI().ltiLogin("3_3");// Login as an 'mentor' with CS3 sections
            new LoginUsingLTI().ltiLogin("3_4");// Login as an 'Student_A1' belonging to CS1 sections
            new LoginUsingLTI().ltiLogin("3_5");// Login as an 'Student_A2'  belonging to CS1 sections
            new LoginUsingLTI().ltiLogin("3_6");// Login as an 'Student_B1'  belonging to CS2 sections
            new LoginUsingLTI().ltiLogin("3_7");// Login as an 'Student_B2'  belonging to CS2 sections
            new LoginUsingLTI().ltiLogin("3_8");// Login as an 'Student_C1'  belonging to CS3 sections
            new LoginUsingLTI().ltiLogin("3_9");// Login as an 'Student_C2'  belonging to CS3 sections
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            new Navigator().NavigateTo("All Resources");//Click on Resources Page
            new Click().clickByid("all-resource-search-textarea");//Click on 'Search Question Banks...' Text Area
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(160));
            new TextSend().textsendbyid("\"" + resoursename + "\"","all-resource-search-textarea");//Type 'Search Question Banks...' Text Area
            new Click().clickByid("all-resource-search-button");//Clcik on search button
            new Click().clickbylist("assign-this",1);
            populateAllActiveClassSections_Instructor("160",orderNo);
            removeClassSections_Instructor("161");
            reselectRemovedClassSections_Instructor("162",orderNo);
            displayAutoSuggestForAllStudentForAllSections_Instructor("163 or 164",orderNo);
            selectAllStudentsFromAutoSuggest("165 or 166",orderNo);
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisFromResourcePage' in class 'EnhancementsToAssignabilityFlow_Mentor'", e);
        }

    }




    @Test(priority = 4,enabled = true)
    public void assignThisFromResourceLibrary(){

      /*  "1. Login as instructor.
        2. Go to resource library.
        3. Click on assign this for an entry."*/


        try {
            String orderNo = "3";
            //Driver.startDriver();
            new ResourseCreate().resourseCreate(167,0);// method call to Create a resource of Type 'image'
            createMultipleClassSections();
            //new LoginUsingLTI().ltiLogin("R17_7_11");// Login as an 'Instructor' with CS1 sections
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            new Navigator().NavigateTo("All Resources");//Click on Resources ------>All Resources
            new Click().clickByid("all-resource-search-textarea");//Click on 'Search Question Banks...' Text Area
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(167));
            new TextSend().textsendbyid("\"" + resoursename + "\"","all-resource-search-textarea");//Type 'Search Question Banks...' Text Area
            new Click().clickByid("all-resource-search-button");//Clcik on search button
            new Click().clickbyxpath("//span[@title = 'Add to My Resources']");//Click on 'Add  to My Resources' Bookmark for a first entry
            new Navigator().navigateToTab("My Resources");//Click on 'My Library' Tab
            new AssignmentsDetails().assignThis_ButtonClick();
            populateAllActiveClassSections_Instructor("167",orderNo);
            removeClassSections_Instructor("168");
            reselectRemovedClassSections_Instructor("169",orderNo);
            displayAutoSuggestForAllStudentForAllSections_Instructor("170 or 171",orderNo);
            selectAllStudentsFromAutoSuggest("172 or 173",orderNo);

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisFromResourceLibrary' in class 'EnhancementsToAssignabilityFlow_Mentor'", e);
        }

    }




    @Test(priority = 5,enabled = true)
    public void assignThisFromAssignmentTab(){
        /*Steps : "1. Login as instructor.
        2. Go to assignment tab beside a lesson
        3. Click on assign this for an entry."*/

        try {
            String orderNo = "3";
            //Driver.startDriver();
            createMultipleClassSections();
            //new LoginUsingLTI().ltiLogin("R17_7_11");// Login as an 'Instructor' with CS1 sections
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            new Navigator().NavigateTo("e-Textbook");//Click on 'e-Textbook'
            new TOCShow().tocHide();//Click on x icon to close the Panel
            new Navigator().navigateToTab("Assignments");//Click on 'Assignments' Tab
            new Click().clickbyxpath("(//a[@class='ls-assignment-show-assign-this-block'])[1]");// Click on 'RightHand arrow symbol'
            // new AssignmentsDetails().assignThis_ButtonClick();www
            new Click().clickbyxpath("//span[@class='ls-assign-this-sprite-right-tab assign-resource-bg']");
            populateAllActiveClassSections_Instructor("174",orderNo);
            removeClassSections_Instructor("175");
            reselectRemovedClassSections_Instructor("176",orderNo);
            displayAutoSuggestForAllStudentForAllSections_Instructor("177 or 178",orderNo);
            selectAllStudentsFromAutoSuggest("179 or 180",orderNo);

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisFromAssignmentTab' in class 'EnhancementsToAssignabilityFlow_Mentor'", e);
        }

    }




    @Test(priority = 6,enabled = true)
    public void assignThisFromResourcesTab(){
        /*"1. Login as instructor.
        2. Go to resource tab beside a lesson.
        3. Click on assign this for an entry."*/

        try {
            String orderNo = "3";
            //Driver.startDriver();
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            Thread.sleep(9000);
            new Navigator().NavigateTo("e-Textbook");//Click on 'e-Textbook'
            new TOCShow().tocHide();//Click on x icon to close the Panel
            new Navigator().navigateToTab("Resources");//Click on 'Resource' Tab
            new Click().clickbyxpath("(//a[@class='ls-assignment-show-assign-this-block'])[1]");// Click on 'RightHand arrow symbol'
            new Click().clickbyxpath("//span[@class='ls-assign-this-sprite-right-tab assign-resource-bg']");
            populateAllActiveClassSections_Instructor("181",orderNo);
            removeClassSections_Instructor("182");
            reselectRemovedClassSections_Instructor("183",orderNo);
            displayAutoSuggestForAllStudentForAllSections_Instructor("184 or 185",orderNo);
            selectAllStudentsFromAutoSuggest("186 or 187",orderNo);

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisFromResourcesTab' in class 'EnhancementsToAssignabilityFlow_Mentor'", e);
        }

    }



    @Test(priority = 7,enabled = true)
    public void assignThisForWidget(){
        /*Steps : "1. Login as instructor.
        2. Open a lesson
        3. Click on assign this for a widget."*/

        try {
            String orderNo = "3";
            //Driver.startDriver();
            createMultipleClassSections();
            //new LoginUsingLTI().ltiLogin("R17_7_11");// Login as an 'Instructor' with CS1 sections
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            new Navigator().NavigateTo("e-Textbook");//Click on 'e-Textbook'
            new Click().clickbyxpath("//i[normalize-space(@class) = 'close-study-plan-icon close-study-plan']");//Click on x icon to close the Panel
            new Click().clickByclassname("assign-options");
            //new AssignmentsDetails().assignThis_ButtonClick();
            new Click().clickbyxpath("//span[@class='ls-assign-this-sprite-right-tab assign-resource-bg']");
            populateAllActiveClassSections_Instructor("188",orderNo);
            removeClassSections_Instructor("189");
            reselectRemovedClassSections_Instructor("190",orderNo);
            displayAutoSuggestForAllStudentForAllSections_Instructor("191 or 192",orderNo);
            selectAllStudentsFromAutoSuggest("193 or 194",orderNo);

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisForWidget' in class 'EnhancementsToAssignabilityFlow_Mentor'", e);
        }

    }






    @Test(priority = 8,enabled = true)
    public void assignThisForLesson(){
        /*Steps : "1. Login as instructor.
        2. Go to a lesson.
        3. Click on assign this for lesson."*/

        try {
            String orderNo = "3";
            //Driver.startDriver();
            createMultipleClassSections();
            //new LoginUsingLTI().ltiLogin("R17_7_11");// Login as an 'Instructor' with CS1 sections
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            new Navigator().NavigateTo("e-Textbook");//Click on 'e-Textbook'
            new TOCShow().tocHide();
            //new AssignmentsDetails().assignThis_ButtonClick();
            new Click().clickbyxpath("//span[@class='ls-assign-this-sprite-right-tab assign-resource-bg']");
            populateAllActiveClassSections_Instructor("195",orderNo);
            removeClassSections_Instructor("196");
            reselectRemovedClassSections_Instructor("197",orderNo);
            displayAutoSuggestForAllStudentForAllSections_Instructor("198 or 199",orderNo);
            selectAllStudentsFromAutoSuggest("200 or 201",orderNo);
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisForLesson' in class 'EnhancementsToAssignabilityFlow_Mentor'", e);
        }

    }



    @Test(priority = 9,enabled = true)
    public void assignThisForCart(){
        /*Steps : "1. Login as instructor.
        2. Go to a lesson.
        3. Add 2 widgets to a cart.
        4. Click on cart icon.
        5. Click on assign button for cart."*/

        try {
            String orderNo = "3";
            //Driver.startDriver();
            createMultipleClassSections();
            //new LoginUsingLTI().ltiLogin("R17_7_11");// Login as an 'Instructor' with CS1 sections
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            new Navigator().NavigateTo("e-Textbook");//Click on 'e-Textbook'
            new TOCShow().tocHide();
            new Click().clickByclassname("assign-options");
            new Click().clickByclassname("add-assignment-cart-text");
            new Click().clickbyxpath("//a[@title ='Close']");
            new Click().clickByclassname("assignment-cart-notifications");
            new Click().clickbyxpath("//span[normalize-space(@class) ='btn sty-green submit-assign']");
            populateAllActiveClassSections_Instructor("202",orderNo);
            removeClassSections_Instructor("203");
            reselectRemovedClassSections_Instructor("204",orderNo);
            displayAutoSuggestForAllStudentForAllSections_Instructor("205 or 206",orderNo);
            selectAllStudentsFromAutoSuggest("207 or 208",orderNo);
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisForCart' in class 'EnhancementsToAssignabilityFlow_Mentor'", e);
        }

    }



    @Test(priority = 10,enabled = true)
    public void assignThisForCustomAssignment(){
       /* Steps : "1. Login as instructor.
        2. Go to question bank
        3. Save a custom assignment
        4. Click on assign this for custom assignment from library"*/
        try {
            String orderNo = "3";
            //Driver.startDriver();
            new Assignment().create(209);
            new Assignment().addQuestions(209, "multipleselection", "");
            createMultipleClassSections();
            //new LoginUsingLTI().ltiLogin("R17_7_11");// Login as an 'Instructor' with CS1 sections
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");// Navigates to 'Question Banks'
            new Click().clickbyxpath("(//div[@id = 'customAssignment'])[2]");// Click on 'Create Custom Assignment' Button
            new Click().clickByclassname("ls-ins-search-text");//Click on 'Search' Icon
            String searchKey = ReadTestData.readDataByTagName("", "searchKey", Integer.toString(209));
            new TextSend().textSendByXpath(searchKey  ,"//input[@placeholder = 'Search question statements...']");//Type a Question
            new Click().clickbyxpath("(//div[@class='ls-ins-left']//i)[2]");//Click on 'Search' Icon
            List<WebElement> quesionsCheckBoxList = driver.findElements(By.xpath("//div[@class='ls-ins-customize-checkbox-small']//label"));// Store the list of Question Checkbox's elements
            WebElement we = null;
            for(int a=0;a<quesionsCheckBoxList.size();a++){
                we= quesionsCheckBoxList.get(a);
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
            }
            new Click().clickbyxpath("//div[@class='tab']/span[contains(@title,'Selected Questions')]");//Click on 'Selected Questions' Tab
            ((JavascriptExecutor) driver).executeScript("scroll(250,0);");// Scroll up to 0 from 250
            new Click().clickByid("ls-ins-assignment-name");// Click on ''Click to enter assignment name...'
            String assignmentName = ReadTestData.readDataByTagName("", "assignmentName", Integer.toString(209));
            new TextSend().textsendbyid(assignmentName, "ls-ins-edit-assignment");// type the assignment name in 'Click to enter assignment name...' textfield
            driver.findElement(By.xpath("//span[text() = 'SAVE FOR LATER']")).click();//Click on 'Save for Later' button
            new Click().clickBycssselector("div[data-id='my-resources']");//Click on 'My Library' Tab
            new Click().clickByid("my-resource-search-textarea");
            new TextSend().textsendbyid("\"" + assignmentName + "\"","my-resource-search-textarea");
            new Click().clickByid("my-resource-search-button");
            new AssignmentsDetails().assignThis_ButtonClick();
            populateAllActiveClassSections_Instructor("209",orderNo);
            removeClassSections_Instructor("210");
            reselectRemovedClassSections_Instructor("211",orderNo);
            displayAutoSuggestForAllStudentForAllSections_Instructor("212 or 213",orderNo);
            selectAllStudentsFromAutoSuggest("214 or 215",orderNo);

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisForCustomAssignment' in class 'EnhancementsToAssignabilityFlow_Mentor'", e);
        }

    }




    @Test(priority = 11,enabled = true)
    public void assignThisForDiscussionWidget(){
        /*Steps : "1. Login as instructor.
        2. Go to a lesson with DW
        3. Click on assign this for DW."*/

        try {
            String orderNo = "3";
            //Driver.startDriver();
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            Thread.sleep(9000);
            new Navigator().NavigateTo("e-Textbook");//Click on 'e-Textbook'
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new Click().clickByclassname("assign-this-widget");
            populateAllActiveClassSections_Instructor("216",orderNo);
            removeClassSections_Instructor("217");
            reselectRemovedClassSections_Instructor("218",orderNo);
            displayAutoSuggestForAllStudentForAllSections_Instructor("219 or 220",orderNo);
            selectAllStudentsFromAutoSuggest("221 or 222",orderNo);
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisForDiscussionWidget' in class 'EnhancementsToAssignabilityFlow_Mentor'", e);
        }

    }

    public void populateAllActiveClassSections_Instructor(String rowNumber,String orderNumber){

            /* Steps : "1. Login as instructor.  2. Go to question bank.
            3. Click on assign this for an entry."
            Expected : Assign to field should be populated by all active class section of instructor to the course
            */

        //String priority = "3";
        try {
            List<WebElement> classSectionsElementsList = driver.findElements(By.xpath("//ul[@class='holder']//li[@class='bit-box']"));
            if(!(classSectionsElementsList.get(0).getText().equals("Computer_Science_"+orderNumber+"_1")&&classSectionsElementsList.get(1).getText().equals("Computer_Science_"+orderNumber+"_2")&&classSectionsElementsList.get(2).getText().equals("Computer_Science_"+orderNumber+"_3"))){
                Assert.fail("'Assign To: *' field is not populating by all active class section of instructor to the course in the testcase Row Number : " +rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'populateAllActiveClassSections_Instructor'in class 'EnhancementsToAssignabilityFlow_Mentor'",e);

        }

    }



    public void removeClassSections_Instructor(String rowNumber){
        //Expected 2 - Instructor should be able to remove the class sections

        try {
            List<WebElement> elementsList = driver.findElements(By.xpath("//ul[@class= 'holder']//a[@class = 'closebutton']"));
            for(int a=0;a<elementsList.size();a++){
                elementsList.get(a).click();
            }
            elementsList = driver.findElements(By.xpath("//ul[@class= 'holder']//a[@class = 'closebutton']"));
            if(elementsList.size()!=0){
                Assert.fail("'Instructor is not able to remove the class sections in the testcase Row Number : " +rowNumber);
            }


        }catch(Exception e){
            Assert.fail("Exception in method 'removeClassSections_Instructor'in class 'EnhancementsToAssignabilityFlow_Mentor'",e);

        }
    }


    public void reselectRemovedClassSections_Instructor(String rowNumber,String orderNumber){
        //Expected 3- Instructor should be able to reselect the removed class sections
        try {
            new TextSend().textsendbyclass("Com", "maininput");
            List<WebElement> sectionsElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));
            for (int a = sectionsElementsList.size(); a > 0; a--) {
                sectionsElementsList.get(0).click();
                if (a != 1) {
                    new TextSend().textsendbyclass("Com", "maininput");
                    sectionsElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));
                }
            }
            List<WebElement> classSectionsElementsList = driver.findElements(By.xpath("//ul[@class='holder']//li[@class='bit-box']"));
            if(!(classSectionsElementsList.get(0).getText().equals("Computer_Science_"+orderNumber+"_1")&&classSectionsElementsList.get(1).getText().equals("Computer_Science_"+orderNumber+"_2")&&classSectionsElementsList.get(2).getText().equals("Computer_Science_"+orderNumber+"_3"))){
                Assert.fail("'Instructor is not able to reselect the removed class sections in the testcase Row Number : " +rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'reselectRemovedClassSections_Instructor'in class 'EnhancementsToAssignabilityFlow_Mentor'",e);
        }
    }



    public void displayAutoSuggestForAllStudentForAllSections_Instructor(String rowNumber,String orderNumber){
        //Steps : 4. Fill in text in assign to field searching a student
        //Expected 1 - Autosuggest should appear considering student from all active class section
        //Expected 2 - Format of autosuggestion should be “<student name> - <class section name>”
        try {
            List<WebElement> elementsList = driver.findElements(By.xpath("//ul[@class= 'holder']//a[@class = 'closebutton']"));
            for (int a = 0; a < elementsList.size(); a++) {
                elementsList.get(a).click();
            }
            new TextSend().textsendbyclass("stu", "maininput");
            if (!(driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li")).get(0).getText().equals("family, studMent_"+orderNumber+"_4 - Computer_Science_"+orderNumber+"_1")&&
                    driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li")).get(1).getText().equals("family, studMent_"+orderNumber+"_5 - Computer_Science_"+orderNumber+"_1")&&
                    driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li")).get(2).getText().equals("family, studMent_"+orderNumber+"_6 - Computer_Science_"+orderNumber+"_2")&&
                    driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li")).get(3).getText().equals("family, studMent_"+orderNumber+"_7 - Computer_Science_"+orderNumber+"_2") &&
                    driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li")).get(4).getText().equals("family, studMent_"+orderNumber+"_8 - Computer_Science_"+orderNumber+"_3") &&
                    driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li")).get(5).getText().equals("family, studMent_"+orderNumber+"_9 - Computer_Science_"+orderNumber+"_3"))) {
                Assert.fail("Auto suggest is not appearing considering student from all active class section or Format of autosuggestion should be “<student name> - <class section name>” in the testcase Row Number : " +rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'displayAutoSuggestForAllStudentForAllSections_Instructor'in class 'EnhancementsToAssignabilityFlow_Mentor'",e);
        }

    }



    public void selectAllStudentsFromAutoSuggest(String rowNumber,String orderNumber){
        try {
            //Expected - 3 : Instructor should be able to select a student from logged in class section
            //Expected 4 : Instructor should be able to select a student from class section other than logged in
            List<WebElement> sections_Students_ElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));
            for (int a = sections_Students_ElementsList.size(); a > 0; a--) {
                if(sections_Students_ElementsList.get(0).getText().equals("family, Mentor")){
                    driver.findElement(By.className("maininput")).clear();
                    break;
                }


                sections_Students_ElementsList.get(0).click();

                if (a != 1) {
                    new TextSend().textsendbyclass("stu", "maininput");
                    sections_Students_ElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));

                }

            }
            List<WebElement> classSectionsElementsList = driver.findElements(By.xpath("//ul[@class='holder']//li[@class='bit-box']"));
            if(!(classSectionsElementsList.get(0).getText().equals("family, studMent_"+orderNumber+"_4 - Computer_Science_"+orderNumber+"_1")&&classSectionsElementsList.get(1).getText().equals("family, studMent_"+orderNumber+"_5 - Computer_Science_"+orderNumber+"_1")&&classSectionsElementsList.get(2).getText().equals("family, studMent_"+orderNumber+"_6 - Computer_Science_"+orderNumber+"_2")&&classSectionsElementsList.get(3).getText().equals("family, studMent_"+orderNumber+"_7 - Computer_Science_"+orderNumber+"_2")&&classSectionsElementsList.get(4).getText().equals("family, studMent_"+orderNumber+"_8 - Computer_Science_"+orderNumber+"_3")&&classSectionsElementsList.get(5).getText().equals("family, studMent_"+orderNumber+"_9 - Computer_Science_"+orderNumber+"_3"))){
                Assert.fail("'Instructor is not able to select a student from logged in class section or Instructor is not able to select a student from class section other than logged in in the testcase Row Number : " + rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'selectAllStudentsFromAutoSuggest'in class 'EnhancementsToAssignabilityFlow_Mentor'",e);

        }
    }



    public void createMultipleClassSections(){
        try{
            new LoginUsingLTI().ltiLogin("3_1");// Login as an 'mentor' with CS1 sections
            new LoginUsingLTI().ltiLogin("3_2");// Login as an 'mentor' with CS2 sections
            new LoginUsingLTI().ltiLogin("3_3");// Login as an 'mentor' with CS3 sections
            new LoginUsingLTI().ltiLogin("3_4");// Login as an 'Student_A1' belonging to CS1 sections
            new LoginUsingLTI().ltiLogin("3_5");// Login as an 'Student_A2'  belonging to CS1 sections
            new LoginUsingLTI().ltiLogin("3_6");// Login as an 'Student_B1'  belonging to CS2 sections
            new LoginUsingLTI().ltiLogin("3_7");// Login as an 'Student_B2'  belonging to CS2 sections
            new LoginUsingLTI().ltiLogin("3_8");// Login as an 'Student_C1'  belonging to CS3 sections
            new LoginUsingLTI().ltiLogin("3_9");// Login as an 'Student_C2'  belonging to CS3 sections
        }catch(Exception e){
            Assert.fail("Exception in Method 'createMultipleClassSections' in Class 'EnhancementsToAssignabilityFlow_Mentor'");
        }
    }







    @Test(priority = 12,enabled = true)
    public void assigningToMultipleClassSections(){
        try{

           /* Steps : "1. Login as mentor requesting cs1
            2. Go to question bank and click assign this for an element.
            3. Leave all active class section selected in assign to field
            4. Fill in other details
            5. Click on assign."  */
            //Expected 1- Assignment entry should get added to assignment page
            //Expected 2 - Assignment entry should get added to assignment tab at relevant level



            String orderNo = "3";
            new Assignment().create(224);// Create an assignment with true false question creation
            new Assignment().addQuestions(224, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(224));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();
            fillOtherDetailsInAssignThisForm();
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@assignmentname)= '"+assessmentName+"']")){
                Assert.fail("'Assignment entry is not added to assignment page in row Number 83");
            }
            Thread.sleep(5000);
            checkAssignmentEntryInAssignmentPage(assessmentName,224);
            Thread.sleep(5000);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,225);
            //checkAssignmentEntryInAssignmentTabAtRelavantLevel_updated(assessmentName,225);

            Thread.sleep(5000);


            //Steps : 6. Switch to cs2/cs3
            //Expected 1 - Assignment entry should be available at assignment page
            //Expected 2 - Assignment entry should be available at assignment tab at relevant level
            new Click().clickByclassname("sbSelector");
            new Click().clickbyxpath("//a[@title='Computer_Science_"+orderNo+"_2']");
            checkAssignmentEntryInAssignmentPage(assessmentName,226);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel_updated(assessmentName,227);


            //Steps : 7. Login as student of cs1/cs2/cs3
            //Expected 1 - Assignment entry should be available at assignment page
            //Expected 2 - Assignment entry should be available at assignment tab of relevant level
            new LoginUsingLTI().ltiLogin(orderNo+"_4");// Login as an 'Student_A1' belonging to CS1 sections
            checkAssignmentEntryInAssignmentPageInStudentLogin(assessmentName,228);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel_updated(assessmentName,229);


         /*   Steps : "8. Go to question bank.
            9. Click on create custom assignment.
            10. Select some questions.
            11. Fill in assignment name.
            12.Click on assign now"*/
            //Expected - All class sections should be displayed by default in assign to field
            new Assignment().create(230);
            new Assignment().addQuestions(230, "multipleselection", "");
            new LoginUsingLTI().ltiLogin(orderNo+"_1");// Login as an 'mentor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");// Navigates to 'Question Banks'
            new Click().clickbyxpath("(//div[@id = 'customAssignment'])[2]");// Click on 'Create Custom Assignment' Button
            new Click().clickByclassname("ls-ins-search-text");//Click on 'Search' Icon
            String searchKey = ReadTestData.readDataByTagName("", "searchKey", Integer.toString(230));
            new TextSend().textSendByXpath(searchKey  ,"//input[@placeholder = 'Search question statements...']");//Type a Question
            new Click().clickbyxpath("(//div[@class='ls-ins-left']//i)[2]");//Click on 'Search' Icon
            List<WebElement> quesionsCheckBoxList = driver.findElements(By.xpath("//div[@class='ls-ins-customize-checkbox-small']//label"));// Store the list of Question Checkbox's elements
            WebElement we = null;
            for(int a=0;a<quesionsCheckBoxList.size();a++){
                we= quesionsCheckBoxList.get(a);
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
            }
            new Click().clickbyxpath("//div[@class='tab']/span[contains(@title,'Selected Questions')]");//Click on 'Selected Questions' Tab
            ((JavascriptExecutor) driver).executeScript("scroll(250,0);");// Scroll up to 0 from 250
            new Click().clickByid("ls-ins-assignment-name");// Click on ''Click to enter assignment name...'
            String assignmentName = ReadTestData.readDataByTagName("", "assignmentName", Integer.toString(230));
            new TextSend().textsendbyid(assignmentName, "ls-ins-edit-assignment");// type the assignment name in 'Click to enter assignment name...' textfield
            Thread.sleep(3000);
            driver.findElement(By.id("ls-assign-now-assigment-btn")).click();
            populateAllActiveClassSections_Instructor("89",orderNo);

            //Step : 13. Fill in all fields and click on assign
            //Expected - Assignment should get assigned to all class sections
            fillOtherDetailsInAssignThisForm();
            new LoginUsingLTI().ltiLogin(orderNo+"_4");// Login as an 'Student_A1' belonging to CS1 sections
            checkAssignmentEntryInAssignmentPageInStudentLogin(assignmentName,231);
            new LoginUsingLTI().ltiLogin(orderNo+"_6");// Login as an 'Student_B1'  belonging to CS2 sections
            checkAssignmentEntryInAssignmentPageInStudentLogin(assignmentName,231);
            new LoginUsingLTI().ltiLogin(orderNo+"_8");// Login as an 'Student_C1'  belonging to CS3 sections
            checkAssignmentEntryInAssignmentPageInStudentLogin(assignmentName,231);

        }catch(Exception e){
            Assert.fail("Exception in method 'assigningToMultipleClassSections' in class 'EnhancementsToAssignabilityFlow_Mentor'",e);
        }
    }




    @Test(priority = 13,enabled = true)
    public void assigningToStudentOfVariousClassSections(){
        try{
           /* Steps : "1. Login as mentor requesting cs1
            2. Go to question bank.
            3. Click on assign this for an element.
            4. Remove class sections from assign to field
            5. Select students a1,b1 and c1.
            6. Fill in other details and click on assign"*/

            //Expected 1- Assignment entry should get added to assignment page
            //Expected 2 - Assignment entry should get added to assignment tab at relevant level
            String orderNo = "3";
            //Driver.startDriver();
            new Assignment().create(232);// Create an assignment with true false question creation
            new Assignment().addQuestions(232, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();
            //new LoginUsingLTI().ltiLogin("R17_7_11");// Login as an 'Instructor' with CS1 sections
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(232));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();
            removeClassSections_Instructor("232");
            displayAutoSuggestForAllStudentForAllSections_Instructor("232",orderNo);
            List<WebElement> sections_Students_ElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));
            for (int a = sections_Students_ElementsList.size(),b=0; a > 0; a--,b++) {
                try {
                    if (sections_Students_ElementsList.get(b).getText().equals("givenname family")) {
                        driver.findElement(By.className("maininput")).clear();
                        break;
                    }
                    sections_Students_ElementsList.get(b).click();

                    if (a != 1) {
                        new TextSend().textsendbyclass("stu", "maininput");
                        sections_Students_ElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));

                    }
                }catch(Exception e){
                }

            }



            fillOtherDetailsInAssignThisForm();
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@assignmentname)= '"+assessmentName+"']")){
                Assert.fail("'Assignment entry is not added to assignment page in row Number 232");
            }
            checkAssignmentEntryInAssignmentPage(assessmentName,232);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,233);


            //Steps : 6. Switch to cs2/cs3
            //Expected 1 - Assignment entry should be available at assignment page
            //Expected 2 - Assignment entry should be available at assignment tab at relevant level
            new Click().clickByclassname("sbSelector");
            new Click().clickbyxpath("//a[@title='Computer_Science_"+orderNo+"_2']");
            checkAssignmentEntryInAssignmentPage(assessmentName,234);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,235);


            //Steps : 8. 8. Login as a1/b1/c1
            //Expected 1 - Assignment entry should be available at assignment page
            //Expected 2 - Assignment entry should be available at assignment tab of relevant level
            new LoginUsingLTI().ltiLogin(orderNo+"_4");// Login as an 'Student_A1' belonging to CS1 sections
            checkAssignmentEntryInAssignmentPageInStudentLogin(assessmentName,236);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,237);




            //Steps : 8. 9. Login as a2/b2/c2
            //Expected - Assignment entry should not be available at assignment page/tab
            new LoginUsingLTI().ltiLogin(orderNo+"_5");// Login as an 'Student_A2' belonging to CS1 sections
            checkAssignmentEntryNotToBePresentInAssignmentPageInStudentLogin(assessmentName,238);
            checkAssignmentEntryNotToBePresentInAssignmentTabAtRelavantLevel(assessmentName,238);



        }catch(Exception e){
            Assert.fail("Exception in testcase 'assigningToStudentOfVariousClassSections' in class 'EnhancementsToAssignabilityFlow_Mentor'",e);
        }
    }





    @Test(priority = 14,enabled = true)
    public void assigningToOneClassSectionAndAStudentFromOtherClassSection(){
        try{
            /* Steps : "1. Login as mentor requesting class section cs1
            2. Go to question bank.
            3. Click on assign this.
            4. Remove all class sections except current from assign to field.
            5. Add b1 in assign to field
            6. Fill in other assignment details.
            7. Click on assign."*/

            //Expected 1- Assignment entry should get added to assignment page
            //Expected 2 - Assignment entry should get added to assignment tab at relevant level
            String orderNo = "3";
            //Driver.startDriver();
            new Assignment().create(239);// Create an assignment with true false question creation
            new Assignment().addQuestions(239, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(239));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();

            List<WebElement> classSectionsList = driver.findElements(By.xpath("//ul[@class='holder']//li"));
            for(int a=0;a<classSectionsList.size()-1;a++){
                if(!(classSectionsList.get(a).findElement(By.tagName("span")).getText().equals("Computer_Science_"+orderNo+"_1"))){
                    classSectionsList.get(a).findElement(By.tagName("a")).click();
                }
            }




            new TextSend().textsendbyclass("stu", "maininput");
            List<WebElement> sections_Students_ElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));
            sections_Students_ElementsList.get(2).click();
            fillOtherDetailsInAssignThisForm();
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@assignmentname)= '"+assessmentName+"']")){
                Assert.fail("'Assignment entry is not added to assignment page in row Number 83");
            }
            checkAssignmentEntryInAssignmentPage(assessmentName,239);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,240);


            //Steps : 8. Switch to class section cs2
            //Expected 1 - Assignment entry should be available at assignment page
            //Expected 2 - Assignment entry should be available at assignment tab at relevant level
            new Click().clickByclassname("sbSelector");
            new Click().clickbyxpath("//a[@title='Computer_Science_"+orderNo+"_2']");
            checkAssignmentEntryInAssignmentPage(assessmentName,241);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,242);



            //Steps : 9. Switch to class section cs3
            //Expected - Assignment entry should not be available
            new Click().clickByclassname("sbSelector");
            new Click().clickbyxpath("//a[@title='Computer_Science_"+orderNo+"_3']");
            checkAssignmentEntryNotToBePresentInAssignmentPage(assessmentName,243);
            checkAssignmentEntryNotToBePresentInAssignmentTabAtRelavantLevel(assessmentName,243);



            //Steps : 10. Login as a1/b1
            //Expected 1 - Assignment entry should be available at assignment page
            //Expected 2 - Assignment entry should be available at assignment tab of relevant level
            new LoginUsingLTI().ltiLogin(orderNo+"_4");// Login as an 'Student_A1' belonging to CS1 sections
            checkAssignmentEntryInAssignmentPageInStudentLogin(assessmentName,244);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,245);




            //Steps : 11. Login as c1
            //Expected - Assignment entry should not be available at assignment page/tab
            new LoginUsingLTI().ltiLogin(orderNo+"_8");// Login as an 'Student_C1'  belonging to CS3 sections
            checkAssignmentEntryNotToBePresentInAssignmentPageInStudentLogin(assessmentName,246);
            checkAssignmentEntryNotToBePresentInAssignmentTabAtRelavantLevel(assessmentName,246);

        }catch(Exception e){
            Assert.fail("Exception in method 'assigningToOneClassSectionAndAStudentFromOtherClassSection' in class 'EnhancementsToAssignabilityFlow_Mentor'",e);
        }
    }



    @Test(priority = 15,enabled = true)
    public void assignmentAssignedByUserWithBothInstructorAndMentorRoleAndStudents(){
        try{
            /*Steps : "1. Login as user requesting instructor role
            2. Login as same user requesting mentor role
            3. Login as same user requesting instructor role
            4. Assign an assignment to all active class section
            5. Go to class section cs1 view"*/
            //Expected - Assignment entry should be available at assignment page/tab
            String orderNo = "3";
            //Driver.startDriver();
            new Assignment().create(106);// Create an assignment with true false question creation
            new Assignment().addQuestions(106, "multipleselection", "");// Create Multiple Selection Question
            new LoginUsingLTI().ltiLogin("R17_7_111");// Login as an 'mentor' with CS1 sections
            new LoginUsingLTI().ltiLogin("R17_7_211");// Login as an 'mentor' with CS2 sections
            new LoginUsingLTI().ltiLogin("R17_7_311");// Login as an 'mentor' with CS3 sections
            new LoginUsingLTI().ltiLogin("R17_7_711");// Login as an 'Instructor' with CS1 sections
            new LoginUsingLTI().ltiLogin("R17_7_811");// Login as an 'Instructor' with CS2 sections
            new LoginUsingLTI().ltiLogin("R17_7_911");// Login as an 'Instructor' with CS3 sections
            new LoginUsingLTI().ltiLogin("R17_7_711");// Login as an 'instructor' with CS1 sections
            new LoginUsingLTI().ltiLogin("R17_7_111");// Login as an 'mentor' with CS1 sections
            new LoginUsingLTI().ltiLogin("R17_7_711");// Login as an 'instructor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");// Navigate to 'Question Banks'
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(106));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();// Clcik on 'Assign this' button
            fillOtherDetailsInAssignThisForm();
            checkAssignmentEntryInAssignmentPage(assessmentName,106);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,106);


            //Steps : 7. Login as student to cs1
            //Expected - Assignment entry should be available at assignment page/tab
            new LoginUsingLTI().ltiLogin("R17_7_411");// Login as an 'Student_A1' belonging to CS1 sections
            checkAssignmentEntryInAssignmentPageInStudentLogin(assessmentName,108);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,108);


            //Steps : 8. Login as student to cs2
            //Expected - Assignment entry should be available at assignment page/tab
            new LoginUsingLTI().ltiLogin("R17_7_511");// Login as an 'Student_B1'  belonging to CS2 sections
            checkAssignmentEntryInAssignmentPageInStudentLogin(assessmentName,109);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,109);



            //Steps : 9. Login as instructor requesting cs1
            //Expected - Assignment entry should display not started count as 1
            new LoginUsingLTI().ltiLogin("R17_7_711");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("Current Assignments");
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[contains(@class , 'ls-instructor-activity-cards-count') and text() = '1']")){
                Assert.fail("Assignment entry is not displaying 'Not Started' count as 1 in the row number 110");
            }


            //Step : 10 - Switch to cs2 view
            //Expected - Assignment entry should display not started count as 1
            new Click().clickByclassname("sbSelector");
            new Click().clickbyxpath("//a[@title='Maths_Science_2']");
            new Navigator().NavigateTo("Current Assignments");
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[contains(@class , 'ls-instructor-activity-cards-count') and text() = '1']")){
                Assert.fail("Assignment entry is not displaying 'Not Started' count as 1 in the row number 111");
            }
        }catch(Exception e){
            Assert.fail("Exception in testcase 'assignmentAssignedByUserWithBothInstructorAndMentorRoleAndStudents' in class 'EnhancementsToAssignabilityFlow_Mentor'");
        }
    }




    @Test(priority = 16,enabled = true)
    public void assignmentAssignedToMulitpleClassSection(){
        try{
            String orderNo = "3";
            //Driver.startDriver();//Start Browser
            new Assignment().create(254);// Create an assignment with true false question creation
            new Assignment().addQuestions(254, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();//Method call to create multiple class sections
            //new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");// Navigate to 'Question Banks'
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(254));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();// Clcik on 'Assugn this' button
            fillOtherDetailsInAssignThisForm();
            new Navigator().NavigateTo("Current Assignments");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'assignmentAssignedToMulitpleClassSection' in class 'EnhancementsToAssignabilityFlow_Mentor'",e);
        }
    }




    @Test(priority = 17,enabled = true)
    public void assigningAssignmentAlreadyAssignedToClassSection(){
        try{
            String orderNo = "3";
            //Driver.startDriver();//Start Browser
            new Assignment().create(273);// Create an assignment with true false question creation
            new Assignment().addQuestions(273, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();//Method call to create multiple class sections*/
            //new LoginUsingLTI().ltiLogin("R17_7_11");// Login as a mentor ' with CS1 sections
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");// Navigate to 'Question Banks'
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(273));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();// Clci   k on 'Assugn this' button
            removeClassSections_Instructor("132");
            new TextSend().textsendbyclass("Com", "maininput");
            List<WebElement> sectionsElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));
            for (int a = sectionsElementsList.size(); a > 0; a--) {
                if(sectionsElementsList.get(0).getText().contains("puter_Science_"+orderNo+"_1")){
                    sectionsElementsList.get(0).click();
                    break;
                }

            }

            Thread.sleep(5000);
            fillOtherDetailsInAssignThisForm();
            new Navigator().NavigateTo("Question Banks");
            new AssignmentsDetails().assignThis_ButtonClick();// Clcik on 'Assign this' button
            populateAllActiveClassSections_Instructor("273",orderNo);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'assigningAssignmentAlreadyAssignedToClassSection' in class 'EnhancementsToAssignabilityFlow_Mentor'",e);
        }
    }






    @Test(priority = 18,enabled = true)
    public void assignmentAssignedToMultipleClassSection(){
        try{
            String orderNo = "3";
           /*Row No - 275 :  "1. Login as mentor requesting cs1
            2. Assign an assignment to all class sections.
            3. Login as a1 and submit the assignment
            4. Login as a2 and submit the assignment
            5. Login back as mentor requesting cs1"*/
            //Expected - Assignment status should be needs grading
             new Assignment().create(275);// Create an assignment with true false question creation
            new Assignment().addQuestions(275, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();//Method call to create multiple class sections
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sectio
            new Navigator().NavigateTo("Question Banks");// Navigate to 'Question Banks'
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(275));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();// Clcik on 'Assugn this' button

            Thread.sleep(5000);
            fillOtherDetailsInAssignThisForm();
            new LoginUsingLTI().ltiLogin(orderNo+"_4");// Login as an 'student a1' with CS1 sections
            new Assignment().submitAssignmentAsStudent(275);
            new LoginUsingLTI().ltiLogin(orderNo+"_5");// Login as an 'student a2' with CS1 sections
            new Assignment().submitAssignmentAsStudent(275);
            new LoginUsingLTI().ltiLogin(orderNo+"_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("Current Assignments");
            if(driver.findElements(By.xpath("//div[@class = 'ls-assignment-item-detail-section']//span[normalize-space(@title) = '(Shor) "+assessmentName+"']//..//span[text() = 'Gradable']")).size()==0){
                Assert.fail("Assignment status should Gradable");
            }



            //Row No -276 :  6. Click on view student response for the assignment
            //Expected - Details of a1,a2 only should be displayed
            new Click().clickbyxpath("//div[@class = 'ls-assignment-item-detail-section']//span[normalize-space(@title) = '(Shor) "+assessmentName+"']//..//..//span[text() = 'View Student Responses']");
            String gradeUsers = new TextFetch().textfetchbyid("idb-gradeBook-breakdown-content-section");
            System.out.println("gradeUsers : " + gradeUsers);

            if(!(gradeUsers.contains("family, studMent_"+orderNo+"_4")&& gradeUsers.contains("family, studMent_"+orderNo+"_5"))){
                Assert.fail("Details of a1,a2 should be displayed");

            }


            if(gradeUsers.contains("family, studMent_"+orderNo+"_6")||gradeUsers.contains("family, studMent_"+orderNo+"_7")||gradeUsers.contains("family, studMent_"+orderNo+"_8")||gradeUsers.contains("family, studMent_"+orderNo+"_9")){
                Assert.fail("Details of a1,a2 only should be displayed");
            }


            /*if(!(gradeUsers.contains("family, studMent_4")&& gradeUsers.contains("family, studMent_5"))){
                Assert.fail("Details of a1,a2 should be displayed");

            }


            if(gradeUsers.contains("family, studMent_6")||gradeUsers.contains("family, studMent_7")||gradeUsers.contains("family, studMent_8")||gradeUsers.contains("family, studMent_9")){
                Assert.fail("Details of a1,a2 only should be displayed");
            }
*/

            //Row No - 278 : 8. Switch to cs2/cs3
            //Expected - Assignment status should be available for students
            new Click().clickByclassname("sbSelector");
            new Click().clickbyxpath("//a[@title='Computer_Science_"+orderNo+"_2']");
            new Navigator().NavigateTo("Current Assignments");
            List<String> asignmenNamesList = new TextFetch().textfetchbylistxpathwithoutindex("//span[normalize-space(@class) ='ls-assignment-name instructor-assessment-review']");
            List<String> classStatusNamesList = new TextFetch().textfetchbylistclasswithoutindex("ls-assignment-status");
            for(int f=0;f<asignmenNamesList.size();f++){
                System.out.println("asignmenNamesList : " + asignmenNamesList.get(f));
                System.out.println("classStatusNamesList : " + classStatusNamesList.get(f));
                if(asignmenNamesList.get(f).contains(assessmentName)){
                    if(!(classStatusNamesList.get(f).contains("Class Status:  Available for Students"))){
                        Assert.fail("Class Status for Assignment should be 'Available for Students'");
                    }
                }
            }


            //Row No - 279 : 9. Login as b1/c1
            //Expected - Assignment status should be not started
            new LoginUsingLTI().ltiLogin(orderNo+"_6");// Login as an 'student b1' with CS1 sections
            new Navigator().NavigateTo("Assignments");
            asignmenNamesList = new TextFetch().textfetchbylistxpathwithoutindex("//span[normalize-space(@class) ='ls-assignment-name instructor-assessment-review']");
            classStatusNamesList = new TextFetch().textfetchbylistclasswithoutindex("ls-assignment-status");
            for(int f=0;f<asignmenNamesList.size();f++){
                System.out.println("asignmenNamesList : " + asignmenNamesList.get(f));
                System.out.println("classStatusNamesList : " + classStatusNamesList.get(f));
                if(asignmenNamesList.get(f).contains(assessmentName)){
                    if(!(classStatusNamesList.get(f).contains("Your Status: Not Started"))){
                        Assert.fail("Class Status for Assignment should be 'Available for Students'");
                    }
                }
            }

        }catch(Exception e){
            Assert.fail("Exception in testcase 'assignmentAssignedToMultipleClassSection' in class 'EnhancementsToAssignabilityFlow_Mentor'",e);
        }
    }






    @Test(priority = 19,enabled = true)
    public void assignmentAssignedToStudentsFromMultipleClassSections(){
        try{

            /*Row No - 280 : "1. Login as mentor requesting cs1.
            2. Assign an assignment to a1,b1 and c1.
            3. Login as a1 and submit the assignment
            4. Login back as instructor requesting cs1"*/
            //Expected - Assignment status should be needs grading

            String orderNo = "3";
            //Driver.startDriver();
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(280));
            new Assignment().create(280);// Create an assignment with true false question creation
            new Assignment().addQuestions(280, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("3_1");// Login as a 'Mentor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();
            removeClassSections_Instructor("280");
            displayAutoSuggestForAllStudentForAllSections_Instructor("280",orderNo);





            List<WebElement> sections_Students_ElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));
            for (int a = sections_Students_ElementsList.size(),b=0; a > 0; a--,b++) {
                try {
                    if (sections_Students_ElementsList.get(b).getText().equals("givenname family")) {
                        driver.findElement(By.className("maininput")).clear();
                        break;
                    }
                    sections_Students_ElementsList.get(b).click();

                    if (a != 1) {
                        new TextSend().textsendbyclass("stu", "maininput");
                        sections_Students_ElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));

                    }
                }catch(Exception e){
                }

            }

            fillOtherDetailsInAssignThisForm();
            new LoginUsingLTI().ltiLogin(orderNo+"_4");// Login as an 'student a1' with CS1 sections
            new Assignment().submitAssignmentAsStudent(280);
            new LoginUsingLTI().ltiLogin(orderNo+"_1");// Login as an 'Mentor' with CS1 sections
            new Navigator().NavigateTo("Current Assignments");
            if(driver.findElements(By.xpath("//div[@class = 'ls-assignment-item-detail-section']//span[normalize-space(@title) = '(Shor) IT17_R177_Custom Assessment_280']//..//span[text() = 'Gradable']")).size()==0){
                Assert.fail("Assignment status should Gradable");
            }



            //Row No -281 :  6. Click on view student response for the assignment
            //Expected - Details of a1 only should be displayed
            new Click().clickbyxpath("//div[@class = 'ls-assignment-item-detail-section']//span[normalize-space(@title) = '(Shor) IT17_R177_Custom Assessment_280']//..//..//span[text() = 'View Student Responses']");
            String gradeUsers = new TextFetch().textfetchbyid("idb-gradeBook-breakdown-content-section");
            System.out.println("gradeUsers : " + gradeUsers);

            /*if(!(gradeUsers.contains("givenname_A_1 family"))){
                Assert.fail("Details of a1,a2 should be displayed");

            }
            if(gradeUsers.contains("givenname_B_1 family")||gradeUsers.contains("givenname_A_2 family")||gradeUsers.contains("givenname_B_2 family")||gradeUsers.contains("givenname_C_1 family")||gradeUsers.contains("givenname_C_2 family")){
                Assert.fail("Details of a1,a2 only should be displayed");
            }*/


            if(!(gradeUsers.contains("family, studMent_"+orderNo+"_4"))){
                Assert.fail("Details of a1,a2 should be displayed");

            }
            if(gradeUsers.contains("family, studMent_"+orderNo+"_5")||gradeUsers.contains("family, studMent_"+orderNo+"_6")||gradeUsers.contains("family, studMent_"+orderNo+"_7")||gradeUsers.contains("family, studMent_"+orderNo+"_8")||gradeUsers.contains("family, studMent_"+orderNo+"_9")){
                Assert.fail("Details of a1,a2 only should be displayed");
            }



            //Row No - 283 : 8. Switch to cs2/cs3
            //Expected - Assignment status should be available for students
            new Click().clickByclassname("sbSelector");
            new Click().clickbyxpath("//a[@title='Computer_Science_"+orderNo+"_2']");
            new Navigator().NavigateTo("Current Assignments");
            List<String> asignmenNamesList = new TextFetch().textfetchbylistxpathwithoutindex("//span[normalize-space(@class) ='ls-assignment-name instructor-assessment-review']");
            List<String> classStatusNamesList = new TextFetch().textfetchbylistclasswithoutindex("ls-assignment-status");
            for(int f=0;f<asignmenNamesList.size();f++){
                System.out.println("asignmenNamesList : " + asignmenNamesList.get(f));
                System.out.println("classStatusNamesList : " + classStatusNamesList.get(f));
                if(asignmenNamesList.get(f).contains(assessmentName)){
                    if(!(classStatusNamesList.get(f).contains("Class Status:  Available for Students"))){
                        Assert.fail("Class Status for Assignment should be 'Available for Students'");
                    }
                }
            }



            //Row No - 284 : 9. Login as b1/c1
            //Expected - Assignment status should be not started
            new LoginUsingLTI().ltiLogin(orderNo+"_6");// Login as an 'student b1' with CS1 sections
            new Navigator().NavigateTo("Assignments");
            asignmenNamesList = new TextFetch().textfetchbylistxpathwithoutindex("//span[normalize-space(@class) ='ls-assignment-name instructor-assessment-review']");
            classStatusNamesList = new TextFetch().textfetchbylistclasswithoutindex("ls-assignment-status");
            for(int f=0;f<asignmenNamesList.size();f++){
                System.out.println("asignmenNamesList : " + asignmenNamesList.get(f));
                System.out.println("classStatusNamesList : " + classStatusNamesList.get(f));
                if(asignmenNamesList.get(f).contains(assessmentName)){
                    if(!(classStatusNamesList.get(f).contains("Your Status: Not Started"))){
                        Assert.fail("Class Status for Assignment should be 'Available for Students'");
                    }
                }
            }

        }catch(Exception e){
            Assert.fail("Exception in testcase 'assignmentAssignedToStudentsFromMultipleClassSections' in class 'EnhancementsToAssignabilityFlow_Mentor'",e);
        }
    }



    public void fillOtherDetailsInAssignThisForm(){
        try{
            new TextSend().textsendbyid("Description","additional-notes");
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(224));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(224));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(224));
            String  assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(224));
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(224));

            driver.findElement(By.id("due-time")).click();
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for(WebElement time : elements)
            {
                if(time.getText().equals(duetime))
                {
                    time.click();
                    break;
                }
            }
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText(duedate)).click();
            Thread.sleep(2000);
            if(gradeable.equals("true"))
            {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
                Thread.sleep(2000);
            }
            if(gradeable.equals("true") && assignmentpolicy!= null)
            {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("Select an assignment policy")));
                Thread.sleep(2000);
                driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
                Thread.sleep(2000);
            }



            //((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[starts-with(normalize-space(@class),'btn sty-green submit-assign')]")));
            Thread.sleep(5000);
        }catch(Exception e){
            Assert.fail("Exception in method 'fillOtherDetailsInAssignThisForm' in class 'EnhancementsToAssignabilityFlow_Mentor'",e);
        }
    }

    public void checkAssignmentEntryInAssignmentPage(String assessmentName,int rowNumber){
        try{
            new Navigator().NavigateTo("Current Assignments");
            WebDriverWait wait = new WebDriverWait(driver, 300);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@assignmentname) = '" + assessmentName + "']")));
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@assignmentname) = '"+assessmentName+"']")){
                Assert.fail("Assignment entry is not added to assignment page in the " +rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'checkAssignmentEntryInAssignmentPage' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }
    public void checkAssignmentEntryNotToBePresentInAssignmentPageInStudentLogin(String assessmentName,int rowNumber){
        try{
            new Navigator().NavigateTo("Assignments");
            if(!(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@title) = '(Shor) "+assessmentName+"']"))){
                Assert.fail("Assignment entry is not added to assignment page in the " +rowNumber);
            }

        }catch(Exception e){
            Assert.fail("Exception in method 'checkAssignmentEntryInAssignmentPage' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }


    public void checkAssignmentEntryInAssignmentPageInStudentLogin(String assessmentName,int rowNumber){
        try{
            new Navigator().NavigateTo("Assignments");
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@title) = '(Shor) "+assessmentName+"']")){
                Assert.fail("Assignment entry is not added to assignment page in the " +rowNumber);
            }

        }catch(Exception e){
            Assert.fail("Exception in method 'checkAssignmentEntryInAssignmentPage' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }

    public void checkAssignmentEntryNotToBePresentInAssignmentTabAtRelavantLevel(String assessmentName,int rowNumber){
        try{
            new Navigator().NavigateTo("e-Textbook");
            Thread.sleep(2000);
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");
            if(!(new BooleanValue().verifyNotElementPresentByXpath("//a[@class='ls_assessment_link' and normalize-space(@title) = '(Shor) "+assessmentName+"']"))){
                Assert.fail("Assignment entry is not added to Assignment Tab at relevant level in the " +rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'checkAssignmentEntryInAssignmentTabAtRelavantLevel' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }


    public void checkAssignmentEntryNotToBePresentInAssignmentPage(String assessmentName,int rowNumber){
        try{
            new Navigator().NavigateTo("Current Assignments");
            if(!(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@assignmentname) = '"+assessmentName+"']"))){
                Assert.fail("Assignment entry is not added to assignment page in the " +rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'checkAssignmentEntryInAssignmentPage' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }

    public void checkAssignmentEntryInAssignmentTabAtRelavantLevel(String assessmentName,int rowNumber){
        try{
            new Navigator().NavigateTo("e-Textbook");
            Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//h3[text()='The Study of Life']")));
            new UIElement().waitAndFindElement(By.partialLinkText("The Science of Biology"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("The Science of Biology")));
            new Navigator().navigateToTab("Assignments");
            WebDriverWait wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='ls_assessment_link' and normalize-space(@title) = '(Shor) "+assessmentName+"']")));
            if(new BooleanValue().verifyNotElementPresentByXpath("//a[@class='ls_assessment_link' and normalize-space(@title) = '(Shor) "+assessmentName+"']")){
                Assert.fail("Assignment entry is not added to Assignment Tab at relevant level in the " +rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'checkAssignmentEntryInAssignmentTabAtRelavantLevel' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }


    public void checkAssignmentEntryInAssignmentTabAtRelavantLevel_updated(String assessmentName,int rowNumber){
        try{
            new Navigator().NavigateTo("e-Textbook");
            Thread.sleep(5000);
            new TopicOpen().lessonOpen(0,0);
            new Navigator().navigateToTab("Assignments");
            if(new BooleanValue().verifyNotElementPresentByXpath("//a[@class='ls_assessment_link' and normalize-space(@title) = '(Shor) "+assessmentName+"']")){
                Assert.fail("Assignment entry is not added to Assignment Tab at relevant level in the " +rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'checkAssignmentEntryInAssignmentTabAtRelavantLevel_updated' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }
}