package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R177;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;

/**
 * Created by Dharaneesh TGowda on 10-Nov-14.
 */
public class EnhancementsToAssignabilityFlow_Instructor extends Driver {

    @Test(priority = 1,enabled = true)
    public void assignThisFromQuestionBankPage(){
      /*  Steps : "1. Login as instructor.
        2. Go to question bank.
        3. Click on assign this for an entry."*/
        try {
            new Assignment().create(5);// Create an assignment with true false question creation
            new Assignment().addQuestions(5, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();//Method call to create multiple class sections
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("My Question Bank");// Navigate to 'my Question Banks'
            driver.findElement(By.cssSelector("span[title='Question Banks']")).click();//click on question bank
            Thread.sleep(2000);
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(5));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();// Clcik on 'Assugn this' button
            populateAllActiveClassSections_Instructor("5");
            removeClassSections_Instructor("6");
            reselectRemovedClassSections_Instructor("7");
            displayAutoSuggestForAllStudentForAllSections_Instructor("8 or 9");
            selectAllStudentsFromAutoSuggest("10 or 11");
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisFromQuestionBankPage' in class 'EnhancementsToAssignabilityFlow_Instructor'", e);
        }

    }




    @Test(priority = 2,enabled = true)
    public void assignThisFromQuestionBankLibrary(){

        /*
        "1. Login as instructor.
        2. Go to question bank library
        3. Click on assign this for an entry."*/

        try {
            //Driver.startDriver();//start broser
            new Assignment().create(12);// Create an assignment with true false question creation
            new Assignment().addQuestions(12, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();//Method call to create multiple class sections*/
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");// Navigate to 'my Question Banks'
            Thread.sleep(1000);
            new Click().clickByid("all-resource-search-textarea");//Click on 'Search Question Banks...' Text Area
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(12));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type the text 'Search Question Banks...' Text Area
            new Click().clickByid("all-resource-search-button");//Click on 'Search' button
            new Click().clickbyxpath("//i[normalize-space(@class)='addThisToMyResources not-bookmarked ls-resource-bookmark-icon']");//Click on 'Add to My Question Bank' Bookmark for a first entry
            new Navigator().navigateToTab("My Question Bank");// Click on Tab 'My Question Bank'
            new Click().clickByid("my-resource-search-textarea");//Click on 'Search Question Banks...' Text Area
            new TextSend().textsendbyid("\"" + assessmentName + "\"","my-resource-search-textarea");//Type assessment name  'Search Question Banks...' Text Area
            new Click().clickByid("my-resource-search-button");//Click on 'Search' button
            new AssignmentsDetails().assignThis_ButtonClick();
            populateAllActiveClassSections_Instructor("12");
            removeClassSections_Instructor("13");
            reselectRemovedClassSections_Instructor("14");
            displayAutoSuggestForAllStudentForAllSections_Instructor("15 or 16");
            selectAllStudentsFromAutoSuggest("17 or 18");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisFromQuestionBankLibrary' in class 'EnhancementsToAssignabilityFlow_Instructor'", e);
        }

    }




    @Test(priority = 3,enabled = true)
    public void assignThisFromResourcePage(){

        /* Steps : "1. Login as instructor.
        2. Go to resource page.
        3. Click on assign this for an entry."*/


        try {
            //Driver.startDriver();
            new ResourseCreate().resourseCreate(19,0);// method call to Create a resource of Type 'image'
            createMultipleClassSections();;
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("All Resources");//Click on Resources Page
            new Click().clickByid("all-resource-search-textarea");//Click on 'Search Question Banks...' Text Area
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(19));
            new TextSend().textsendbyid("\"" + resoursename + "\"","all-resource-search-textarea");//Type 'Search Question Banks...' Text Area
            new Click().clickByid("all-resource-search-button");//Clcik on search button
            new AssignmentsDetails().assignThis_ButtonClick();//Click on 'Assign this' button
            populateAllActiveClassSections_Instructor("19");
            removeClassSections_Instructor("20");
            reselectRemovedClassSections_Instructor("21");
            displayAutoSuggestForAllStudentForAllSections_Instructor("22 or 23");
            selectAllStudentsFromAutoSuggest("24 or 25");
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisFromResourcePage' in class 'EnhancementsToAssignabilityFlow_Instructor'", e);
        }

    }





    @Test(priority = 4,enabled = true)
    public void assignThisFromResourceLibrary(){

      /*  "1. Login as instructor.
        2. Go to resource library.
        3. Click on assign this for an entry."*/


        try {
            //Driver.startDriver();
            new ResourseCreate().resourseCreate(26,0);// method call to Create a resource of Type 'image'
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("All Resources");//Click on Resources ------>All Resources
            new Click().clickByid("all-resource-search-textarea");//Click on 'Search Question Banks...' Text Area
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(26));
            new TextSend().textsendbyid("\"" + resoursename + "\"","all-resource-search-textarea");//Type 'Search Question Banks...' Text Area
            new Click().clickByid("all-resource-search-button");//Clcik on search button
            new Click().clickbyxpath("//span[@title = 'Add to My Resources']");//Click on 'Add  to My Resources' Bookmark for a first entry
            new Navigator().navigateToTab("My Resources");//Click on 'My Library' Tab
            new AssignmentsDetails().assignThis_ButtonClick();
            populateAllActiveClassSections_Instructor("26");
            removeClassSections_Instructor("27");
            reselectRemovedClassSections_Instructor("28");
            displayAutoSuggestForAllStudentForAllSections_Instructor("29 or 30");
            selectAllStudentsFromAutoSuggest("31 or 32");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisFromResourceLibrary' in class 'EnhancementsToAssignabilityFlow_Instructor'", e);
        }

    }





    @Test(priority = 5,enabled = true)
    public void assignThisFromAssignmentTab(){
        /*Steps : "1. Login as instructor.
        2. Go to assignment tab beside a lesson
        3. Click on assign this for an entry."*/


        try {
            //Driver.startDriver();
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("e-Textbook");//Click on 'e-Textbook'
            new TOCShow().tocHide();//Click on x icon to close the Panel
            new Navigator().navigateToTab("Assignments");//Click on 'Assignments' Tab
            new Click().clickbyxpath("(//a[@class='ls-assignment-show-assign-this-block'])[1]");// Click on 'RightHand arrow symbol'
            //new AssignmentsDetails().assignThis_ButtonClick();
            new Click().clickbyxpath("//span[@class='ls-assign-this-sprite-right-tab assign-resource-bg']");
            populateAllActiveClassSections_Instructor("33");
            removeClassSections_Instructor("34");
            reselectRemovedClassSections_Instructor("35");
            displayAutoSuggestForAllStudentForAllSections_Instructor("36 or 37");
            selectAllStudentsFromAutoSuggest("38 or 39");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisFromAssignmentTab' in class 'EnhancementsToAssignabilityFlow_Instructor'", e);
        }

    }




    @Test(priority = 6,enabled = true)
    public void assignThisFromResourcesTab(){
        /*"1. Login as instructor.
        2. Go to resource tab beside a lesson.
        3. Click on assign this for an entry."*/

        try {
            //Driver.startDriver();
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("e-Textbook");//Click on 'e-Textbook'
            new TOCShow().tocHide();//Click on x icon to close the Panel
            new Navigator().navigateToTab("Resources");//Click on 'Resource' Tab
            new Click().clickbyxpath("(//a[@class='ls-assignment-show-assign-this-block'])[1]");// Click on 'RightHand arrow symbol'
            new Click().clickbyxpath("//span[@class='ls-assign-this-sprite-right-tab assign-resource-bg']");
            //new AssignmentsDetails().assignThis_ButtonClick();
            populateAllActiveClassSections_Instructor("40");
            removeClassSections_Instructor("41");
            reselectRemovedClassSections_Instructor("42");
            displayAutoSuggestForAllStudentForAllSections_Instructor("43 or 44");
            selectAllStudentsFromAutoSuggest("45 or 46");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisFromResourcesTab' in class 'EnhancementsToAssignabilityFlow_Instructor'", e);
        }

    }



    @Test(priority = 7,enabled = true)
    public void assignThisForWidget(){
        /*Steps : "1. Login as instructor.
        2. Open a lesson
        3. Click on assign this for a widget."*/

        try {
            //Driver.startDriver();
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("e-Textbook");//Click on 'e-Textbook'
            WebDriverWait wait = new WebDriverWait(driver, 120);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[normalize-space(@class) = 'close-study-plan-icon close-study-plan']")));
            new Click().clickbyxpath("//i[normalize-space(@class) = 'close-study-plan-icon close-study-plan']");//Click on x icon to close the Panel
            new Click().clickByclassname("assign-options");
            new Click().clickbyxpath("//span[@class='ls-assign-this-sprite-right-tab assign-resource-bg']");
            //new AssignmentsDetails().assignThis_ButtonClick();
            populateAllActiveClassSections_Instructor("47");
            removeClassSections_Instructor("48");
            reselectRemovedClassSections_Instructor("49");
            displayAutoSuggestForAllStudentForAllSections_Instructor("50 or 51");
            selectAllStudentsFromAutoSuggest("52 or 53");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisForWidget' in class 'EnhancementsToAssignabilityFlow_Instructor'", e);
        }

    }






    @Test(priority = 8,enabled = true)
    public void assignThisForLesson(){
        /*Steps : "1. Login as instructor.
        2. Go to a lesson.
        3. Click on assign this for lesson."*/

        try {
            //Driver.startDriver();
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("e-Textbook");//Click on 'e-Textbook'
            new TOCShow().tocHide();
            new Click().clickbyxpath("//span[@class='ls-assign-this-sprite-right-tab assign-resource-bg']");
            //new AssignmentsDetails().assignThis_ButtonClick();
            populateAllActiveClassSections_Instructor("54");
            removeClassSections_Instructor("55");
            reselectRemovedClassSections_Instructor("56");
            displayAutoSuggestForAllStudentForAllSections_Instructor("57 or 58");
            selectAllStudentsFromAutoSuggest("59 or 60");
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisForLesson' in class 'EnhancementsToAssignabilityFlow_Instructor'", e);
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
            //Driver.startDriver();
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("e-Textbook");//Click on 'e-Textbook'
            new TOCShow().tocHide();
            new Click().clickByclassname("assign-options");
            new Click().clickByclassname("add-assignment-cart-text");
            new Click().clickbyxpath("//a[@title ='Close']");
            new Click().clickByclassname("assignment-cart-notifications");
            new Click().clickbyxpath("//span[normalize-space(@class) ='btn sty-green submit-assign']");
            populateAllActiveClassSections_Instructor("61");
            removeClassSections_Instructor("62");
            reselectRemovedClassSections_Instructor("63");
            displayAutoSuggestForAllStudentForAllSections_Instructor("64 or 65");
            selectAllStudentsFromAutoSuggest("66 or 67");
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisForCart' in class 'EnhancementsToAssignabilityFlow_Instructor'", e);
        }

    }



    @Test(priority = 10,enabled = true)
    public void assignThisForCustomAssignment(){
       /* Steps : "1. Login as instructor.
        2. Go to question bank
        3. Save a custom assignment
        4. Click on assign this for custom assignment from library"*/

        try {

            //Driver.startDriver();
            new Assignment().create(68);
            new Assignment().addQuestions(68, "multipleselection", "");
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("My Question Bank");// Navigates to 'Question Banks'
            new Click().clickbyxpath("(//div[@id = 'customAssignment'])[2]");// Click on 'Create Custom Assignment' Button
            new Click().clickByclassname("ls-ins-search-text");//Click on 'Search' Icon
            String searchKey = ReadTestData.readDataByTagName("", "searchKey", Integer.toString(68));
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
            String assignmentName = ReadTestData.readDataByTagName("", "assignmentName", Integer.toString(68));
            new TextSend().textsendbyid(assignmentName, "ls-ins-edit-assignment");// type the assignment name in 'Click to enter assignment name...' textfield
            driver.findElement(By.xpath("//span[text() = 'SAVE FOR LATER']")).click();//Click on 'Save for Later' button
            new Click().clickBycssselector("div[data-id='my-resources']");//Click on 'My Library' Tab
            new Click().clickByid("my-resource-search-textarea");
            new TextSend().textsendbyid("\"" + assignmentName + "\"","my-resource-search-textarea");
            new Click().clickByid("my-resource-search-button");
            new AssignmentsDetails().assignThis_ButtonClick();
            populateAllActiveClassSections_Instructor("68");
            removeClassSections_Instructor("69");
            reselectRemovedClassSections_Instructor("70");
            displayAutoSuggestForAllStudentForAllSections_Instructor("71 or 72");
            selectAllStudentsFromAutoSuggest("73 or 74");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisForCustomAssignment' in class 'EnhancementsToAssignabilityFlow_Instructor'", e);
        }

    }




    @Test(priority = 11,enabled = true)
    public void assignThisForDiscussionWidget(){
        /*Steps : "1. Login as instructor.
        2. Go to a lesson with DW
        3. Click on assign this for DW."*/

        try {
            //Driver.startDriver();
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            Thread.sleep(9000);
            new Navigator().NavigateTo("e-Textbook");//Click on 'e-Textbook'
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new Click().clickByclassname("assign-this-widget");
            populateAllActiveClassSections_Instructor("75");
            removeClassSections_Instructor("76");
            reselectRemovedClassSections_Instructor("77");
            displayAutoSuggestForAllStudentForAllSections_Instructor("78 or 79");
            selectAllStudentsFromAutoSuggest("80 or 81");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'assignThisForDiscussionWidget' in class 'EnhancementsToAssignabilityFlow_Instructor'", e);
        }

    }



    public void populateAllActiveClassSections_Instructor(String rowNumber){

            /* Steps : "1. Login as instructor.  2. Go to question bank.
            3. Click on assign this for an entry."
            Expected : Assign to field should be populated by all active class section of instructor to the course
            */
        try {
            List<WebElement> classSectionsElementsList = driver.findElements(By.xpath("//ul[@class='holder']//li[@class='bit-box']"));
            if(!(classSectionsElementsList.get(0).getText().equals("Mathematics_1")&&classSectionsElementsList.get(1).getText().equals("Biology's 101")&&classSectionsElementsList.get(2).getText().equals("Biology's 101")&&classSectionsElementsList.get(3).getText().equals("Mathematics_2")&&classSectionsElementsList.get(4).getText().equals("Mathematics_3"))){
                Assert.fail("'Assign To: *' field is not populating by all active class section of instructor to the course in the testcase Row Number : " +rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'populateAllActiveClassSections_Instructor'in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
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
            Assert.fail("Exception in method 'removeClassSections_Instructor'in class 'EnhancementsToAssignabilityFlow_Instructor'",e);

        }
    }

    public void reselectRemovedClassSections_Instructor(String rowNumber){
        //Expected 3- Instructor should be able to reselect the removed class sections
        try {
            Thread.sleep(1000);
            new TextSend().textsendbyclass("mat", "maininput");
            Thread.sleep(2000);
            List<WebElement> sectionsElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));
            for (int a = sectionsElementsList.size(); a > 0; a--) {
                Thread.sleep(2000);
                sectionsElementsList.get(0).click();
                if (a != 1) {
                    new TextSend().textsendbyclass("mat", "maininput");
                    sectionsElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));
                }
            }
            List<WebElement> classSectionsElementsList = driver.findElements(By.xpath("//ul[@class='holder']//li[@class='bit-box']"));
            if(!(classSectionsElementsList.get(0).getText().equals("Mathematics_1")&&classSectionsElementsList.get(1).getText().equals("Mathematics_2")&&classSectionsElementsList.get(2).getText().equals("Mathematics_3"))){
                Assert.fail("'Instructor is not able to reselect the removed class sections in the testcase Row Number : " +rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'reselectRemovedClassSections_Instructor'in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }


    }


    public void displayAutoSuggestForAllStudentForAllSections_Instructor(String rowNumber){
        //Steps : 4. Fill in text in assign to field searching a student
        //Expected 1 - Auto suggest should appear considering student from all active class section
        //Expected 2 - Format of autosuggestion should be “<student name> - <class section name>”
        try {
            List<WebElement> elementsList = driver.findElements(By.xpath("//ul[@class= 'holder']//a[@class = 'closebutton']"));
            for (int a = 0; a < elementsList.size(); a++) {
                elementsList.get(a).click();
            }
            Thread.sleep(2000);
            new TextSend().textsendbyclass("stu", "maininput");
            if (!(driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li")).get(0).getText().equals("family, student_1 - Mathematics_1")&&
                    driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li")).get(1).getText().equals("family, student_2 - Mathematics_1")&&
                    driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li")).get(2).getText().equals("family, student_3 - Mathematics_2")&&
                    driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li")).get(3).getText().equals("family, student_4 - Mathematics_2") &&
                    driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li")).get(4).getText().equals("family, student_5 - Mathematics_3") &&
                    driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li")).get(5).getText().equals("family, student_6 - Mathematics_3"))) {
                Assert.fail("Auto suggest is not appearing considering student from all active class section or Format of autosuggestion should be “<student name> - <class section name>” in the testcase Row Number : " +rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'displayAutoSuggestForAllStudentForAllSections_Instructor'in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }

    }


    public void selectAllStudentsFromAutoSuggest(String rowNumber){
        try {
            //Expected - 3 : Instructor should be able to select a student from logged in class section
            //Expected 4 : Instructor should be able to select a student from class section other than logged in
            List<WebElement> sections_Students_ElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));
            for (int a = sections_Students_ElementsList.size(); a > 0; a--) {
                Thread.sleep(2000);
                if(sections_Students_ElementsList.get(0).getText().equals("family, student")){
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
            if(!(classSectionsElementsList.get(0).getText().equals("family, student_1 - Mathematics_1")&&classSectionsElementsList.get(1).getText().equals("family, student_2 - Mathematics_1")&&classSectionsElementsList.get(2).getText().equals("family, student_3 - Mathematics_2") &&classSectionsElementsList.get(3).getText().equals("family, student_4 - Mathematics_2")&&classSectionsElementsList.get(4).getText().equals("family, student_5 - Mathematics_3")&&classSectionsElementsList.get(5).getText().equals("family, student_6 - Mathematics_3"))){
                Assert.fail("'Instructor is not able to select a student from logged in class section or Instructor is not able to select a student from class section other than logged in in the testcase Row Number : " + rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'selectAllStudentsFromAutoSuggest'in class 'EnhancementsToAssignabilityFlow_Instructor'",e);

        }
    }

    public void createMultipleClassSections(){
        try{
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new LoginUsingLTI().ltiLogin("R17_7_2");// Login as an 'Instructor' with CS2 sections
            new LoginUsingLTI().ltiLogin("R17_7_3");// Login as an 'Instructor' with CS3 sections
            new LoginUsingLTI().ltiLogin("R17_7_4");// Login as an 'Student_A1' belonging to CS1 sections
            new LoginUsingLTI().ltiLogin("R17_7_5");// Login as an 'Student_A2'  belonging to CS1 sections
            new LoginUsingLTI().ltiLogin("R17_7_6");// Login as an 'Student_B1'  belonging to CS2 sections
            new LoginUsingLTI().ltiLogin("R17_7_7");// Login as an 'Student_B2'  belonging to CS2 sections
            new LoginUsingLTI().ltiLogin("R17_7_8");// Login as an 'Student_C1'  belonging to CS3 sections
            new LoginUsingLTI().ltiLogin("R17_7_9");// Login as an 'Student_C2'  belonging to CS3 sections
        }catch(Exception e){
            Assert.fail("Exception in Method 'createMultipleClassSections' in Class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }


    @Test(priority = 12,enabled = true)
    public void assigningToMultipleClassSections(){
        try{

           /* Steps : "1. Login as instructor requesting cs1
            2. Go to question bank and click assign this for an element.
            3. Leave all active class section selected in assign to field
            4. Fill in other details
            5. Click on assign."  */
            //Expected 1- Assignment entry should get added to assignment page
            //Expected 2 - Assignment entry should get added to assignment tab at relevant level

            new Assignment().create(83);// Create an assignment with true false question creation
            new Assignment().addQuestions(83, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            Thread.sleep(9000);
            new Navigator().NavigateTo("My Question Bank");
            driver.findElement(By.cssSelector("span[title='Question Banks']")).click();//click on question bank
            Thread.sleep(1000);
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(83));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();
            fillOtherDetailsInAssignThisForm();
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@assignmentname)= '"+assessmentName+"']")){
                Assert.fail("'Assignment entry is not added to assignment page in row Number 83");
            }
            Thread.sleep(5000);
            checkAssignmentEntryInAssignmentPage(assessmentName, 83);
            Thread.sleep(20000);
            //checkAssignmentEntryInAssignmentTabAtRelavantLevel_ScrollView(assessmentName, 84);*/
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName, 84);
            Thread.sleep(5000);

            new Navigator().NavigateTo("e-Textbook");
            Thread.sleep(3000);
            new TopicOpen().lessonOpen(0,0);
            Thread.sleep(3000);
            //new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");
            if(new BooleanValue().verifyNotElementPresentByXpath("//a[@class='ls_assessment_link' and normalize-space(@title) = '(Shor) "+assessmentName+"']")){
                Assert.fail("Assignment entry is not added to Assignment Tab at relevant level in the ");
            }






            //Steps : 6. Switch to cs2/cs3
            //Expected 1 - Assignment entry should be available at assignment page
            //Expected 2 - Assignment entry should be available at assignment tab at relevant level
            new Click().clickByclassname("sbSelector");
            new Click().clickbyxpath("//a[@title='Mathematics_2']");
            checkAssignmentEntryInAssignmentPage(assessmentName,85);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel_ScrollView(assessmentName, 86);


            //Steps : 7. Login as student of cs1/cs2/cs3
            //Expected 1 - Assignment entry should be available at assignment page
            //Expected 2 - Assignment entry should be available at assignment tab of relevant level

            new LoginUsingLTI().ltiLogin("R17_7_4");// Login as an 'Student_A1' belonging to CS1 sections
            checkAssignmentEntryInAssignmentPageInStudentLogin(assessmentName,87);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel_ScrollView(assessmentName, 88);


            /*Steps : "8. Go to question bank.
            9. Click on create custom assignment.
            10. Select some questions.
            11. Fill in assignment name.
            12.Click on assign now"*/
            //Expected - All class sections should be displayed by default in assign to field
            new Assignment().create(89);
            new Assignment().addQuestions(89, "multipleselection", "");
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");// Navigates to 'Question Banks'
            new Click().clickbyxpath("(//div[@id = 'customAssignment'])[2]");// Click on 'Create Custom Assignment' Button
            new Click().clickByclassname("ls-ins-search-text");//Click on 'Search' Icon
            String searchKey = ReadTestData.readDataByTagName("", "searchKey", Integer.toString(89));
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
            String assignmentName = ReadTestData.readDataByTagName("", "assignmentName", Integer.toString(89));
            new TextSend().textsendbyid(assignmentName, "ls-ins-edit-assignment");// type the assignment name in 'Click to enter assignment name...' textfield
            Thread.sleep(3000);
            driver.findElement(By.id("ls-assign-now-assigment-btn")).click();
            populateAllActiveClassSections_Instructor("89");


            //Step : 13. Fill in all fields and click on assign
            //Expected - Assignment should get assigned to all class sections
            fillOtherDetailsInAssignThisForm();
            new LoginUsingLTI().ltiLogin("R17_7_4");// Login as an 'Student_A1' belonging to CS1 sections
            checkAssignmentEntryInAssignmentPageInStudentLogin(assignmentName,90);
            new LoginUsingLTI().ltiLogin("R17_7_6");// Login as an 'Student_B1'  belonging to CS2 sections
            checkAssignmentEntryInAssignmentPageInStudentLogin(assignmentName,90);
            new LoginUsingLTI().ltiLogin("R17_7_8");// Login as an 'Student_C1'  belonging to CS3 sections
            checkAssignmentEntryInAssignmentPageInStudentLogin(assignmentName,90);

        }catch(Exception e){
            Assert.fail("Exception in method 'assigningToMultipleClassSections' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }



    @Test(priority = 13,enabled = true)
    public void assigningToStudentOfVariousClassSections(){
        try{
           /* Steps : "1. Login as instructor requesting cs1
            2. Go to question bank.
            3. Click on assign this for an element.
            4. Remove class sections from assign to field
            5. Select students a1,b1 and c1.
            6. Fill in other details and click on assign"*/

            //Expected 1- Assignment entry should get added to assignment page
            //Expected 2 - Assignment entry should get added to assignment tab at relevant level
            //Driver.startDriver();
            new Assignment().create(91);// Create an assignment with true false question creation
            new Assignment().addQuestions(91, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("My Question Bank");
            driver.findElement(By.cssSelector("span[title='Question Banks']")).click();//click on question bank
            Thread.sleep(1000);
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(91));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();
            removeClassSections_Instructor("91");
            displayAutoSuggestForAllStudentForAllSections_Instructor("91");
            List<WebElement> sections_Students_ElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));
            for (int a = sections_Students_ElementsList.size(),b=0; a > 0; a--,b++) {
                if(sections_Students_ElementsList.size()==3){
                    break;
                }
                if(sections_Students_ElementsList.get(b).getText().equals("student family")){
                    driver.findElement(By.className("maininput")).clear();
                    break;
                }
                sections_Students_ElementsList.get(b).click();

                if (a != 1) {
                    new TextSend().textsendbyclass("stu", "maininput");
                    sections_Students_ElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));

                }

            }



            fillOtherDetailsInAssignThisForm();
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@assignmentname)= '"+assessmentName+"']")){
                Assert.fail("'Assignment entry is not added to assignment page in row Number 83");
            }
            Thread.sleep(20000);
            checkAssignmentEntryInAssignmentPage(assessmentName,91);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,92);


            //Steps : 6. Switch to cs2/cs3
            //Expected 1 - Assignment entry should be available at assignment page
            //Expected 2 - Assignment entry should be available at assignment tab at relevant level
            new Click().clickByclassname("sbSelector");
            new Click().clickbyxpath("//a[@title='Mathematics_2']");
            checkAssignmentEntryInAssignmentPage(assessmentName,93);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,94);


            //Steps : 8. 8. Login as a1/b1/c1
            //Expected 1 - Assignment entry should be available at assignment page
            //Expected 2 - Assignment entry should be available at assignment tab of relevant level
            new LoginUsingLTI().ltiLogin("R17_7_4");// Login as an 'Student_A1' belonging to CS1 sections
            checkAssignmentEntryInAssignmentPageInStudentLogin(assessmentName,95);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,96);




            //Steps : 8. 9. Login as a2/b2/c2
            //Expected - Assignment entry should not be available at assignment page/tab
            new LoginUsingLTI().ltiLogin("R17_7_5");// Login as an 'Student_A2' belonging to CS1 sections
            checkAssignmentEntryNotToBePresentInAssignmentPageInStudentLogin(assessmentName,97);
            checkAssignmentEntryNotToBePresentInAssignmentTabAtRelavantLevel(assessmentName,97);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'assigningToStudentOfVariousClassSections' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }





    @Test(priority = 14,enabled = true)
    public void assigningToOneClassSectionAndAStudentFromOtherClassSection(){
        try{
            /* Steps : "1. Login as instructor requesting class section cs1
            2. Go to question bank.
            3. Click on assign this.
            4. Remove all class sections except current from assign to field.
            5. Add b1 in assign to field
            6. Fill in other assignment details.
            7. Click on assign."*/

            //Expected 1- Assignment entry should get added to assignment page
            //Expected 2 - Assignment entry should get added to assignment tab at relevant level
            //Driver.startDriver();
            new Assignment().create(98);// Create an assignment with true false question creation
            new Assignment().addQuestions(98, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("My Question Bank");
            driver.findElement(By.cssSelector("span[title='Question Banks']")).click();//click on question bank
            Thread.sleep(2000);
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(98));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();

            List<WebElement> classSectionsList = driver.findElements(By.xpath("//ul[@class='holder']//li"));
            for(int a=0;a<classSectionsList.size()-1;a++){
                if(!(classSectionsList.get(a).findElement(By.tagName("span")).getText().equals("Mathematics_1"))){
                    classSectionsList.get(a).findElement(By.tagName("a")).click();
                }
            }



            new TextSend().textsendbyclass("stu", "maininput");
            Thread.sleep(5000);
            List<WebElement> sections_Students_ElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));
            sections_Students_ElementsList.get(2).click();
            fillOtherDetailsInAssignThisForm();
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@assignmentname)= '"+assessmentName+"']")){
                Assert.fail("'Assignment entry is not added to assignment page in row Number 83");
            }
            checkAssignmentEntryInAssignmentPage(assessmentName,98);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,99);


            //Steps : 8. Switch to class section cs2
            //Expected 1 - Assignment entry should be available at assignment page
            //Expected 2 - Assignment entry should be available at assignment tab at relevant level
            new Click().clickByclassname("sbSelector");
            new Click().clickbyxpath("//a[@title='Mathematics_2']");
            checkAssignmentEntryInAssignmentPage(assessmentName,100);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,101);



            //Steps : 9. Switch to class section cs3
            //Expected - Assignment entry should not be available
            new Click().clickByclassname("sbSelector");
            new Click().clickbyxpath("//a[@title='Mathematics_3']");
            checkAssignmentEntryNotToBePresentInAssignmentPage(assessmentName,102);
            checkAssignmentEntryNotToBePresentInAssignmentTabAtRelavantLevel(assessmentName,102);



            //Steps : 10. Login as a1/b1
            //Expected 1 - Assignment entry should be available at assignment page
            //Expected 2 - Assignment entry should be available at assignment tab of relevant level
            new LoginUsingLTI().ltiLogin("R17_7_4");// Login as an 'Student_A1' belonging to CS1 sections
            checkAssignmentEntryInAssignmentPageInStudentLogin(assessmentName,103);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,104);




            //Steps : 11. Login as c1
            //Expected - Assignment entry should not be available at assignment page/tab
            new LoginUsingLTI().ltiLogin("R17_7_8");// Login as an 'Student_C1'  belonging to CS3 sections
            checkAssignmentEntryNotToBePresentInAssignmentPageInStudentLogin(assessmentName,105);
            checkAssignmentEntryNotToBePresentInAssignmentTabAtRelavantLevel(assessmentName,105);

        }catch(Exception e){
            Assert.fail("Exception in method 'assigningToOneClassSectionAndAStudentFromOtherClassSection' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }





    @Test(priority = 15,enabled = true)
    public void assignmentAssignedByUserWithBothInstructorAndMentorRoleAndStudents(){
        try{
            /*Steps : "1. Login as user requesting mentor role
            2. Login as same user requesting instructor role
            3. Login as same user requesting mentor role
            4. Assign an assignment to all active class section
            5. Go to class section cs1 view"*/

            new Assignment().create(247);// Create an assignment with true false question creation
            new Assignment().addQuestions(247, "multipleselection", "");// Create Multiple Selection Question
            new LoginUsingLTI().ltiLogin("R17_7_247_7");// Login as an 'Mentor' with CS3 sections
            new LoginUsingLTI().ltiLogin("R17_7_247_8");// Login as an 'Mentor' with CS3 sections
            new LoginUsingLTI().ltiLogin("R17_7_247_9");// Login as an 'Mentor' with CS3 sections

            new LoginUsingLTI().ltiLogin("R17_7_247_1");// Login as an 'Instructor' with CS1 sections
            new LoginUsingLTI().ltiLogin("R17_7_247_2");// Login as an 'Instructor' with CS2 sections
            new LoginUsingLTI().ltiLogin("R17_7_247_3");// Login as an 'Instructor' with CS3 sections

            new LoginUsingLTI().ltiLogin("R17_7_247_7");// Login as an 'Mentor' with CS3 sections
            new LoginUsingLTI().ltiLogin("R17_7_247_1");// Login as an 'Instructor' with CS1 sections
            new LoginUsingLTI().ltiLogin("R17_7_247_7");// Login as an 'Mentor' with CS3 sections


            new Navigator().NavigateTo("Question Banks");// Navigate to 'Question Banks'
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(247));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();// Clcik on 'Assign this' button
            fillOtherDetailsInAssignThisForm();
            checkAssignmentEntryInAssignmentPage(assessmentName,247);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,247);

            //Step - 6. Switch to cs2/cs3 view
            //Expected - Assignment entry should be available at assignment page/tab
            new Click().clickByclassname("sbSelector");
            new Click().clickbyxpath("//a[@title='R_Science_2']");
            checkAssignmentEntryInAssignmentPage(assessmentName,248);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,248);



            //Steps : 7. Login as student to cs1
            //Expected - Assignment entry should be available at assignment page/tab
            new LoginUsingLTI().ltiLogin("R17_7_247_4");// Login as an 'Student_A1' belonging to CS1 sections
            checkAssignmentEntryInAssignmentPageInStudentLogin(assessmentName,249);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,249);


            //Steps : 8. Login as student to cs2
            //Expected - Assignment entry should be available at assignment page/tab
            new LoginUsingLTI().ltiLogin("R17_7_247_5");// Login as an 'Student_B1'  belonging to CS2 sections
            checkAssignmentEntryInAssignmentPageInStudentLogin(assessmentName,250);
            checkAssignmentEntryInAssignmentTabAtRelavantLevel(assessmentName,250);



            //Steps : 9. Login as mentor requesting cs1
            //Expected - Assignment entry should display not started count as 1
            new LoginUsingLTI().ltiLogin("R17_7_247_6");// Login as an 'mentor' with CS1 sections
            new Navigator().NavigateTo("Current Assignments");
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[contains(@class , 'ls-instructor-activity-cards-count') and text() = '1']")){
                Assert.fail("Assignment entry is not displaying 'Not Started' count as 1 in the row number 251");
            }


            //Step : 10 - Switch to cs2 view
            //Expected - Assignment entry should display not started count as 1
            new Click().clickByclassname("sbSelector");
            new Click().clickbyxpath("//a[@title='R_Science_2']");
            new Navigator().NavigateTo("Current Assignments");
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[contains(@class , 'ls-instructor-activity-cards-count') and text() = '1']")){
                Assert.fail("Assignment entry is not displaying 'Not Started' count as 1 in the row number 252");
            }



        }catch(Exception e){
            Assert.fail("Exception in testcase 'assignmentAssignedByUserWithBothInstructorAndMentorRoleAndStudents' in class 'EnhancementsToAssignabilityFlow_Instructor'");
        }
    }





    @Test(priority = 16,enabled = true)
    public void assignmentAssignedToMulitpleClassSection(){
        try{
            //Driver.startDriver();//Start Browser
            new Assignment().create(113);// Create an assignment with true false question creation
            new Assignment().addQuestions(113, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();//Method call to create multiple class sections
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");// Navigate to 'Question Banks'
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(113));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();// Clcik on 'Assugn this' button
            fillOtherDetailsInAssignThisForm();
            new Navigator().NavigateTo("Current Assignments");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'assignmentAssignedToMulitpleClassSection' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }



    @Test(priority = 17,enabled = true)
    public void assigningAssignmentAlreadyAssignedToClassSection(){
        try{
            //Driver.startDriver();//Start Browser
            new Assignment().create(132);// Create an assignment with true false question creation
            new Assignment().addQuestions(132, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();//Method call to create multiple class sections*/
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");// Navigate to 'Question Banks'
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(132));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();// Clcik on 'Assugn this' button
            removeClassSections_Instructor("132");
            new TextSend().textsendbyclass("Mat", "maininput");
            List<WebElement> sectionsElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));
            for (int a = sectionsElementsList.size(); a > 0; a--) {
                if(sectionsElementsList.get(0).getText().contains("puter_Science_1")){
                    sectionsElementsList.get(0).click();
                    break;
                }

            }

            Thread.sleep(5000);
            fillOtherDetailsInAssignThisForm();
            new Navigator().NavigateTo("Question Banks");
            new AssignmentsDetails().assignThis_ButtonClick();// Clcik on 'Assign this' button
            populateAllActiveClassSections_Instructor("132");

        }catch(Exception e){
            Assert.fail("Exception in testcase 'assigningAssignmentAlreadyAssignedToClassSection' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }





    @Test(priority = 18,enabled = true)
    public void assignmentAssignedToMultipleClassSection(){
        try{

            /*ROw No - 134 : "1. Login as instructor requesting cs1
            2. Assign an assignment to all class sections.
            3. Login as a1 and submit the assignment
            4. Login as a2 and submit the assignment
            5. Login back as instructor requesting cs1"*/
            //Expected- Assignment status should be needs grading

            new Assignment().create(134);// Create an assignment with true false question creation
            new Assignment().addQuestions(134, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();//Method call to create multiple class sections
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");// Navigate to 'Question Banks'
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(134));
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            Thread.sleep(10000);
            /*List<WebElement> AssignmentsElementsList = driver.findElements(By.className("resource-title"));
            List<WebElement> AssignThisButtonElementsList = driver.findElements(By.xpath("//div[normalize-space(@class) = 'tab-content tab-created tab-active']//span[@class='assign-this']"));
            for(int a = 0;a<AssignmentsElementsList.size();a++){
                if(AssignmentsElementsList.get(a).getText().equals(assessmentName.trim())){
                    AssignThisButtonElementsList.get(a).click();
                    break;
                }
            }*/


            new Click().clickbyxpath("//div[@class='ls-assessment-item-sectn']//div[@title = '"+assessmentName+"']//..//..//..//span[@class='assign-this']");
            fillOtherDetailsInAssignThisForm();
            new LoginUsingLTI().ltiLogin("R17_7_4");// Login as an 'student a1' with CS1 sections
            new Assignment().submitAssignmentAsStudent(134);
            new LoginUsingLTI().ltiLogin("R17_7_5");// Login as an 'student a2' with CS1 sections
            new Assignment().submitAssignmentAsStudent(134);
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("Current Assignments");
            Thread.sleep(3000);
            if(driver.findElements(By.xpath("//div[@class = 'ls-assignment-item-detail-section']//span[normalize-space(@title) = '(Shor) "+assessmentName+"']//..//span[text() = 'Gradable']")).size()==0){
                Assert.fail("Assignment status should Gradable");
            }


            //Row No -135 :  6. Click on view student response for the assignment
            //Expected - Details of a1,a2 only should be displayed
            new Click().clickbyxpath("//div[@class = 'ls-assignment-item-detail-section']//span[normalize-space(@title) = '(Shor) "+assessmentName+"']//..//..//span[text() = 'View Student Responses']");
            Thread.sleep(5000);
            String gradeUsers = new TextFetch().textfetchbyid("idb-gradeBook-breakdown-content-section");
            System.out.println("gradeUsers : " + gradeUsers);
            if(!(gradeUsers.contains("family, student_1")&& gradeUsers.contains("family, student_2"))){
                Assert.fail("Details of a1,a2 should be displayed");

            }

            if(gradeUsers.contains("family, student_3")||gradeUsers.contains("family, student_4")||gradeUsers.contains("family, student_5")||gradeUsers.contains("family, student_6")){
                Assert.fail("Details of a1,a2 only should be displayed");
            }
            //Row No - 137 : 8. Switch to cs2/cs3
            //Expected - Assignment status should be available for students
            new Click().clickByclassname("sbSelector");
            new Click().clickbyxpath("//a[@title='Mathematics_2']");
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


            //Row No - 138 : 9. Login as b1/c1
            //Expected - Assignment status should be not started
            new LoginUsingLTI().ltiLogin("R17_7_6");// Login as an 'student b1' with CS1 sections
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
            Assert.fail("Exception in testcase 'assignmentAssignedToMultipleClassSection' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }





    @Test(priority = 19,enabled = true)
    public void assignmentAssignedToStudentsFromMultipleClassSections(){
        try{

            /*Row No - 139 : "1. Login as instructor requesting cs1.
            2. Assign an assignment to a1,b1 and c1.
            3. Login as a1 and submit the assignment
            4. Login back as instructor requesting cs1"*/
            //Expected - Assignment status should be needs grading


            //Driver.startDriver();
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(139));
            new Assignment().create(139);// Create an assignment with true false question creation
            new Assignment().addQuestions(139, "multipleselection", "");// Create Multiple Selection Question
            createMultipleClassSections();
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("Question Banks");
            new Click().clickByid("all-resource-search-textarea");//Click on textbox search area to search for an assessment
            new TextSend().textsendbyid("\"" + assessmentName + "\"","all-resource-search-textarea");//Type an assessment name on textbox search area to search for an assessment
            new Click().clickByid("all-resource-search-button");//Click the textbox search area to search for an assessment
            new AssignmentsDetails().assignThis_ButtonClick();
            removeClassSections_Instructor("139");
            displayAutoSuggestForAllStudentForAllSections_Instructor("139");
            List<WebElement> sections_Students_ElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));
            for (int a = sections_Students_ElementsList.size(),b=0; a > 0; a--,b++) {
                if(sections_Students_ElementsList.size()==3){
                    break;
                }
                if(sections_Students_ElementsList.get(b).getText().equals("student family")){
                    driver.findElement(By.className("maininput")).clear();
                    break;
                }
                sections_Students_ElementsList.get(b).click();
                if (a != 1) {
                    new TextSend().textsendbyclass("stu", "maininput");
                    sections_Students_ElementsList = driver.findElements(By.xpath("//ul[@id = 'share-with_feed']//li"));

                }
            }
            fillOtherDetailsInAssignThisForm();
            new LoginUsingLTI().ltiLogin("R17_7_4");// Login as an 'student a1' with CS1 sections
            new Assignment().submitAssignmentAsStudent(139);
            new LoginUsingLTI().ltiLogin("R17_7_1");// Login as an 'Instructor' with CS1 sections
            new Navigator().NavigateTo("Current Assignments");
            if(driver.findElements(By.xpath("//div[@class = 'ls-assignment-item-detail-section']//span[normalize-space(@title) = '(Shor) IT17_R177_Custom Assessment_139']//..//span[text() = 'Gradable']")).size()==0){
                Assert.fail("Assignment status should Gradable");
            }



            //Row No -140 :  6. Click on view student response for the assignment
            //Expected - Details of a1 only should be displayed
            new Click().clickbyxpath("//div[@class = 'ls-assignment-item-detail-section']//span[normalize-space(@title) = '(Shor) IT17_R177_Custom Assessment_139']//..//..//span[text() = 'View Student Responses']");
            Thread.sleep(5000);
            String gradeUsers = new TextFetch().textfetchbyid("idb-gradeBook-breakdown-content-section");
            System.out.println("gradeUsers : " + gradeUsers);
            if(!(gradeUsers.contains("family, student_1"))){
                Assert.fail("Details of a1,a2 should be displayed");

            }
            if(gradeUsers.contains("family, student_2")||gradeUsers.contains("family, student_3")||gradeUsers.contains("family, student_4")||gradeUsers.contains("family, student_5")||gradeUsers.contains("family, student_6")){
                Assert.fail("Details of a1,a2 only should be displayed");
            }

            //Row No - 142 : 8. Switch to cs2/cs3
            //Expected - Assignment status should be available for students
            new Click().clickByclassname("sbSelector");
            new Click().clickbyxpath("//a[@title='Mathematics_2']");
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



            //Row No - 143 : 9. Login as b1/c1
            //Expected - Assignment status should be not started
            new LoginUsingLTI().ltiLogin("R17_7_6");// Login as an 'student b1' with CS1 sections
            new Navigator().NavigateTo("Assignments");
            Thread.sleep(2000);
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
            Assert.fail("Exception in testcase 'assignmentAssignedToStudentsFromMultipleClassSections' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }



    /*@AfterMethod
    public void TearDown()throws Exception
    {
        driver.quit();
    }*/


    public void fillOtherDetailsInAssignThisForm(){
        try{
            new TextSend().textsendbyid("Description","additional-notes");
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(83));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(83));
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", Integer.toString(83));
            String  assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", Integer.toString(83));
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(83));

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
            new UIElement().waitAndFindElement(By.id("due-date"));
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(2000);
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']"));
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText(duedate)).click();
            Thread.sleep(2000);
            if(gradeable.equals("true"))
            {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")));
                Thread.sleep(2000);            }
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
            Assert.fail("Exception in method 'fillOtherDetailsInAssignThisForm' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }


    public void checkAssignmentEntryInAssignmentPage(String assessmentName,int rowNumber){
        try{
            new Navigator().NavigateTo("Current Assignments");
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(By.xpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@assignmentname) = '"+ assessmentName + "']"));
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@assignmentname) = '"+assessmentName+"']")){
                Assert.fail("Assignment entry is not added to assignment page in the " +rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'checkAssignmentEntryInAssignmentPage' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }

    public void checkAssignmentEntryNotToBePresentInAssignmentPage(String assessmentName,int rowNumber){
        try{
            new Navigator().NavigateTo("Current Assignments");
            Thread.sleep(3000);
            if(!(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@assignmentname) = '"+assessmentName+"']"))){
                Assert.fail("Assignment entry is not added to assignment page in the " +rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'checkAssignmentEntryInAssignmentPage' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }

    public void checkAssignmentEntryInAssignmentPageInStudentLogin(String assessmentName,int rowNumber){
        try{
            new Navigator().NavigateTo("Assignments");
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(By.xpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@title) = '(Shor) "+assessmentName+"']"));
            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@title) = '(Shor) "+assessmentName+"']")){
                Assert.fail("Assignment entry is not added to assignment page in the " +rowNumber);
            }

        }catch(Exception e){
            Assert.fail("Exception in method 'checkAssignmentEntryInAssignmentPage' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }




    public void checkAssignmentEntryNotToBePresentInAssignmentPageInStudentLogin(String assessmentName,int rowNumber){
        try{
            new Navigator().NavigateTo("Assignments");
            Thread.sleep(3000);
            if(!(new BooleanValue().verifyNotElementPresentByXpath("//div[@class='ls-assignment-name-block']//span[normalize-space(@title) = '(Shor) "+assessmentName+"']"))){
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
            Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='ls_assessment_link' and normalize-space(@title) = '(Shor) "+assessmentName+"']")));
            if(new BooleanValue().verifyNotElementPresentByXpath("//a[@class='ls_assessment_link' and normalize-space(@title) = '(Shor) "+assessmentName+"']")){
                Assert.fail("Assignment entry is not added to Assignment Tab at relevant level in the " +rowNumber);
            }

        }catch(Exception e){
            Assert.fail("Exception in method 'checkAssignmentEntryInAssignmentTabAtRelavantLevel' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }


    public void checkAssignmentEntryNotToBePresentInAssignmentTabAtRelavantLevel(String assessmentName,int rowNumber){
        try{
            new Navigator().NavigateTo("e-Textbook");
            Thread.sleep(2000);
            //new TOCShow().tocHide();
            new TopicOpen().lessonOpen(0,1);
            Thread.sleep(2000);
            new Navigator().navigateToTab("Assignments");
            Thread.sleep(2000);
            if(!(new BooleanValue().verifyNotElementPresentByXpath("//a[@class='ls_assessment_link' and normalize-space(@title) = '(Shor) "+assessmentName+"']"))){
                Assert.fail("Assignment entry is not added to Assignment Tab at relevant level in the " +rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'checkAssignmentEntryInAssignmentTabAtRelavantLevel' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }

    public void checkAssignmentEntryInAssignmentTabAtRelavantLevel_ScrollView(String assessmentName,int rowNumber){
        try{
            new Navigator().NavigateTo("e-Textbook");
            WebDriverWait wait = new WebDriverWait(driver, 120);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[normalize-space(@class) = 'close-study-plan-icon close-study-plan']")));
            driver.findElement(By.className("chapter-heading-label")).click();
            Thread.sleep(2000);
            WebElement element = driver.findElement(By.cssSelector("a[title = 'Introduction']"));
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(2000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",element);
            Thread.sleep(2000);
            new Navigator().navigateToTab("Assignments");
            Thread.sleep(2000);
            if(new BooleanValue().verifyNotElementPresentByXpath("//a[@class='ls_assessment_link' and normalize-space(@title) = '(Shor) "+assessmentName+"']")){
                Assert.fail("Assignment entry is not added to Assignment Tab at relevant level in the " +rowNumber);
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'checkAssignmentEntryInAssignmentTabAtRelavantLevel_updated' in class 'EnhancementsToAssignabilityFlow_Instructor'",e);
        }
    }



}
