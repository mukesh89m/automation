package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT24.R241;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CopyAllAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by durgapathi on 4/11/15.
 */
public class copyAssignmentsFromOneClassSectionToAnotherClassSection extends Driver{
    private static String uId = "";
    private static List<String> csIdList = new ArrayList<>();
    private static List<String> csNameList = new ArrayList<>();
    private static List<String> activeInActiveCSNameList = new ArrayList<>();

    public String getUIdAndCSId(int dataIndex)
    {
        try
        {
            String userName = ReadTestData.readDataByTagName("", "user_id", Integer.toString(dataIndex));
            // Get userId
            new DBConnect().Connect();
            String userQuery = "select id from t_user where username = '"+userName+"';";
            System.out.println("userIdQuery::"+userQuery);
            ResultSet uIdResult = DBConnect.st.executeQuery(userQuery);
            while (uIdResult.next())
            {
                uId=uIdResult.getString("id");
               // System.out.println("userId::"+uId);
            }
            Thread.sleep(5000);
            // get Class section Ids
            String classSectionQuery = "select class_section_id from t_class_section_permission where user_id = "+uId+";";
            System.out.println("classSectionQuery::"+classSectionQuery);
            ResultSet csIdResult = DBConnect.st.executeQuery(classSectionQuery);
            List<String> csIds = new ArrayList<String>();
            while (csIdResult.next())
            {
                String csId = csIdResult.getString("class_section_id");
                //System.out.println("csId::"+csId);
                csIds.add(csId);
            }
            csIdList = csIds;
            // get Class section names
            String classSectionNameQuery = "select name from t_class_section where id in (select class_section_id from t_class_section_permission where user_id = "+uId+");";
            System.out.println("classSectionNameQuery::"+classSectionNameQuery);
            ResultSet csNameResult = DBConnect.st.executeQuery(classSectionNameQuery);
            List<String> csNames = new ArrayList<String>();
            while (csNameResult.next())
            {
                csNames.add(csNameResult.getString("name"));
            }
            csNameList = csNames;
            // get Class section names
            String activeInActiveCSList = "select name from t_class_section where name not like '%Finished' and id in (select class_section_id from t_class_section_permission where user_id = "+uId+");";
            System.out.println("activeInActiveCSList::"+activeInActiveCSList);
            ResultSet activeInactiveCSNameResult = DBConnect.st.executeQuery(activeInActiveCSList);
            List<String> activeInactiveCSNames = new ArrayList<String>();
            while (activeInactiveCSNameResult.next())
            {
                activeInactiveCSNames.add(activeInactiveCSNameResult.getString("name"));
            }
            activeInActiveCSNameList = activeInactiveCSNames;
        }
        catch (Exception e)
        {
            Assert.fail("Exception in getUIdAndCSId of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
        return null;
    }

   @Test(priority = 0,enabled = true)
    public void cleanExistingData()
    {
        try
        {

            new DBConnect().Connect();
            // get Class section Ids
            String classSectionIdsQuery = "select id from t_class_section where display_name like 'studtitle_R241_%';";
            System.out.println("classSectionQuery::"+classSectionIdsQuery);
            ResultSet csIdResult = DBConnect.st.executeQuery(classSectionIdsQuery);
            List<String> csIds = new ArrayList<String>();
            while (csIdResult.next())
            {
                String csId = csIdResult.getString("id");
                // System.out.println("csId::"+csId);
                csIds.add(csId);
            }
            for(String str : csIds)
            {
                // delete user details
                String deleteUserOrgRef = "delete from t_user_org_reference where org_reference_id  in (select id from t_dn_org_reference where class_section_id in ('"+str+"'));";
                String deleteOrgRefAttributes = "delete from t_org_reference_attributes where org_reference_id  in (select id from t_dn_org_reference where class_section_id in ('"+str+"'));";
                String deleteOrgRef = "delete from t_dn_org_reference where class_section_id in ('"+str+"');";
                String deleteCSPermission = "delete from t_class_section_permission where class_section_id in ('"+str+"');";
                String deleteCS = "delete from t_class_section where id in ('"+str+"');";

                DBConnect.st.executeUpdate(deleteUserOrgRef);
                DBConnect.st.executeUpdate(deleteOrgRefAttributes);
                DBConnect.st.executeUpdate(deleteOrgRef);
                DBConnect.st.executeUpdate(deleteCSPermission);
                DBConnect.st.executeUpdate(deleteCS);
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in cleanExistingData of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    @Test(priority = 1,enabled = true)
    public void  viewCopyAssignmentsLinkOnCurrentAssignmentPage()
    {
        try
        {
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("10"); // Login as Instructor CS1
            new LoginUsingLTI().ltiLogin("11"); // Login as Instructor CS2
            Assert.assertTrue(dashboard.dashBoard.isDisplayed(), "The user is not successfully logged in to the application and the default landing page should be displayed");
            new Navigator().NavigateTo("Current Assignments"); // Navigate to Current Assignments
            Assert.assertTrue(currentAssignments.currentAssignmentPage.isDisplayed(), "Current Assignments page should be opened");
            Assert.assertTrue(currentAssignments.copyAssignments.isDisplayed(), "Copy Assignments link should be displayed");//Copy Assignments Link
            Thread.sleep(1000);
            // Verify Copy Assignment Color
            String color = currentAssignments.copyAssignments.getCssValue("color");
            Thread.sleep(1000);
            System.out.println("color::" + color);
            if(!color.trim().equals("rgba(12, 127, 181, 1)"))
            {
                Assert.fail("Copy Assignments link should be blue in color");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC viewCopyAssignmentsLinkOnCurrentAssignmentPage of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }
    @Test(priority = 2,enabled = true)
    public void launchCopyAssignmentsPopUpFromCurrentAssignment()
    {
        try
        {
            Dashboard dashboard = PageFactory.initElements(driver,Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver,CopyAllAssignments.class);

            new LoginUsingLTI().ltiLogin("10"); // Login as Instructor
            Assert.assertTrue(dashboard.dashBoard.isDisplayed(), "The user is not successfully logged in to the application and the default landing page should be displayed");
            new Navigator().NavigateTo("Current Assignments"); // Navigate to Current Assignments
            Assert.assertTrue(currentAssignments.currentAssignmentPage.isDisplayed(), "Current Assignments page should be opened");
            currentAssignments.copyAssignments.click(); // click on copy assignments
            Assert.assertTrue(copyAllAssignments.assignmentDialogBox.isDisplayed(), "Copy Assignments pop-up should be opened");
            System.out.println("assignmentDialogBoxBackGColor::" + driver.findElement(By.className("ls-dashboard-overlay")).getCssValue("color"));
            Assert.assertEquals("rgba(126, 126, 126, 1)", driver.findElement(By.className("ls-dashboard-overlay")).getCssValue("color"), "The screen should be masked");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC launchCopyAssignmentsPopUpFromCurrentAssignment of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void verifyTheCopyAssignmentPopUpUI()
    {
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver, CopyAllAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver,QuestionBank.class);
            int dataIndex = 10;
            String userName = ReadTestData.readDataByTagName("", "user_id", Integer.toString(dataIndex));
            new Assignment().create(10);
            new Assignment().addQuestions(10, "truefalse", "");

            new LoginUsingLTI().ltiLogin("10"); // Login as Instructor to Active CS
            new LoginUsingLTI().ltiLogin("11"); // Login as Instructor to Active CS
            new LoginUsingLTI().ltiLogin("12"); // Login as Instructor to InActive CS
            new LoginUsingLTI().ltiLogin("13"); // Login as Instructor to InActive CS
            new LoginUsingLTI().ltiLogin("14"); // Login as Instructor to Finished CS
            new LoginUsingLTI().ltiLogin("10"); // Login as Instructor to Active CS

            new Navigator().NavigateTo("Current Assignments"); // Navigate to Current Assignments
            currentAssignments.copyAssignments.click(); // click on copy assignments
            Thread.sleep(1000);
            // The dropdown should be greyed out by default
            System.out.println("BGDColor::" + copyAllAssignments.destinationClassSection.getCssValue("background-color"));
            System.out.println("BGSColor::" + copyAllAssignments.sourceClassSection.getCssValue("background-color"));
            Assert.assertEquals("rgba(219, 219, 219, 1)", copyAllAssignments.destinationClassSection.getCssValue("background-color"), "The dropdown should be greyed out by default");
            // Drop down text should be select Class section
            System.out.println("BGDColor::" + copyAllAssignments.destinationClassSection.getText());
            System.out.println("BGSColor::" + copyAllAssignments.sourceClassSection.getText());
            // Drop down default Text
            Assert.assertEquals("Select Class Section", copyAllAssignments.destinationClassSection.getText(), "Verify the default text displayed for Select Destination Class section Dropdown");
            Assert.assertEquals("Select Class Section", copyAllAssignments.sourceClassSection.getText(), "Verify the default text displayed for Select Source Class section Dropdown");
            copyAllAssignments.nextButtonInStep1.click(); //Click on Next
            Assert.assertEquals("Please select class section", copyAllAssignments.errorMessageInStep1.getText(), "Click Next, A validation message should be displayed ' Select Source Class section'");
            copyAllAssignments.sourceClassSectionDropDown.get(0).click();
            // Verify source drop down CS's
            getUIdAndCSId(10);
            int csSizeFromDB = csIdList.size();
            int csSizeFromApp = copyAllAssignments.getSourceClassSectionNames.size()-1;
            System.out.println("csSizeFromDB::" + csSizeFromDB);
            System.out.println("csSizeFromApp::" + csSizeFromApp);
            Assert.assertEquals(csSizeFromDB,csSizeFromApp,"dropdown should be displayed with all the Active, Inactive, Finished Class sections associated with the user");
            // get Class section names
            List<String> actual = new ArrayList<String>();
            List<WebElement> csNameFromApp = copyAllAssignments.getSourceClassSectionNames;
            for(WebElement ele:csNameFromApp){
                actual.add(ele.getText());
            }
            actual.remove(0);
            Assert.assertEquals(csNameList, actual);
            Thread.sleep(4000);
            //Class Sections should be alphabetically sorted
            Collections.sort(csNameList);
            Assert.assertEquals(csNameList, actual);
            // The value should be selected and it should show ellipses
            copyAllAssignments.getSourceClassSectionNames.get(2).click();
            String elipses = copyAllAssignments.sourceClassSectionDropDown.get(1).getAttribute("style");
            if(!elipses.trim().contains("text-overflow: ellipsis"))
            {
                Assert.fail("The value should be selected and it should show ellipses");
            }
            // The complete name of the Assignment should be displayed
            Actions actions = new Actions(driver);
            WebElement element = copyAllAssignments.sourceClassSectionDropDown.get(1);
            actions.moveToElement(element).build().perform();
            dataIndex = 11;
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String classSectionName = copyAllAssignments.sourceClassSectionDropDown.get(1).getAttribute("title");
            Assert.assertEquals(context_title,classSectionName,"The complete name of the CS should be displayed");
            // Verify error message if no assignments
            String errorIfNoAssignments = copyAllAssignments.errorMessageInStep1.getText();
            Assert.assertEquals(errorIfNoAssignments,"Source class section does not have any assignments. Please choose a different class section.", "A validation message should be displayed");
            copyAllAssignments.closePopup.click();
            // Assign an assignment
           /* new Navigator().NavigateTo("Question Banks");
            questionBank.questionBankDropdown.click();
            questionBank.practiceSetsOnly.click();
            questionBank.getSearchInputFieldOnQuestionBank().get(1).click();
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys(" Personalized Practice - Ch");
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            Thread.sleep(3000);
            questionBank.assignThisLink.get(0).click();*/
            Thread.sleep(2000);
            new Assignment().assignToStudent(10);
            // Select a value from the list which has an assignment in it
            new Navigator().NavigateTo("Current Assignments"); // Navigate to Current Assignments
            currentAssignments.copyAssignments.click(); // click on copy assignments
            copyAllAssignments.sourceClassSectionDropDown.get(0).click();
            copyAllAssignments.getSourceClassSectionNames.get(1).click();
            Assert.assertEquals("sbHolder", copyAllAssignments.destinationClassSection.getAttribute("class"), "The value should be displayed and Select Destination Class section should be enabled");
            // Click on Select Destination Class section dropdown
            dataIndex = 10;
            String contextTitle = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            copyAllAssignments.destinationClassSectionDropDown.get(0).click();
            List<WebElement> desClassSectionsList = copyAllAssignments.getDestinationClassSectionNames;
            for(int i = 0;i < desClassSectionsList.size(); i++ )
            {
                String csTitle = desClassSectionsList.get(i).getAttribute("title");
                if(csTitle.contains("Finished"))
                {
                    Assert.fail("Finished class sections are displayed in destination drop down");
                }
                if(csTitle.trim().equals(contextTitle))
                {
                    String sourceClassSection = desClassSectionsList.get(i).getAttribute("style");
                    System.out.println("csTitle::"+csTitle);
                    System.out.println("sourceClassSection::" + sourceClassSection);
                    if(!sourceClassSection.trim().contains("display: none;"))
                    {
                        Assert.fail("Selected source class section also displayed in destination drop down");
                    }
                }
                if( i > 0)
                {
                    if(!csTitle.trim().contains("Active") && !csTitle.trim().contains("InActive"))
                    {
                        Assert.fail("Destination drop down should be displayed with all the Active, Inactive Class Sections");
                    }
                }
            }
            // get destination Class section names
            List<String> desActual = new ArrayList<String>();
            List<WebElement> descsNameFromApp = copyAllAssignments.getDestinationClassSectionNames;
            for(WebElement ele:descsNameFromApp){
                if(ele.getText() != null && !"".equals(ele.getText())){
                    desActual.add(ele.getText());
                }
            }
            activeInActiveCSNameList.remove(0);
            System.out.println("desActual::" + desActual);
            System.out.println("activeInActiveCSNameList::" + activeInActiveCSNameList);
            Assert.assertEquals(activeInActiveCSNameList, desActual);
            Thread.sleep(4000);
            //Class Sections should be alphabetically sorted
            Collections.sort(activeInActiveCSNameList);
            Assert.assertEquals(activeInActiveCSNameList, desActual);
            // The value should be selected and it should show ellipses
            copyAllAssignments.getDestinationClassSectionNames.get(2).click();
            String desCSElipses = copyAllAssignments.destinationClassSectionDropDown.get(1).getAttribute("style");
            if(!desCSElipses.trim().contains("text-overflow: ellipsis"))
            {
                Assert.fail("Destination CS : The value should be selected and it should show ellipses");
            }
            // The complete name of the Assignment should be displayed
            actions = new Actions(driver);
            element = copyAllAssignments.destinationClassSectionDropDown.get(1);
            actions.moveToElement(element).build().perform();
            dataIndex = 11;
            context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            classSectionName = copyAllAssignments.destinationClassSectionDropDown.get(1).getAttribute("title");
            System.out.println("classSectionName::"+classSectionName);
            Assert.assertEquals(context_title,classSectionName,"The complete name of the CS should be displayed");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC verifyTheCopyAssignmentPopUpUI of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }
    @Test(priority = 4,enabled = true)
    public void cancelAndXButtonFunctionality()
    {
        try
        {
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver,CopyAllAssignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("10");
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.copyAssignments.click();
            // The dropdown should be greyed out by default
            System.out.println("BGDColor::" + copyAllAssignments.destinationClassSection.getCssValue("background-color"));
            System.out.println("BGSColor::" + copyAllAssignments.sourceClassSection.getCssValue("background-color"));
            Assert.assertEquals("rgba(219, 219, 219, 1)", copyAllAssignments.destinationClassSection.getCssValue("background-color"), "The dropdown should be greyed out by default");
            // dropdown should be displayed with all the Active, Inactive, Finished Class sections associated with the user
            copyAllAssignments.sourceClassSectionDropDown.get(0).click();
            // Verify source drop down CS's
            getUIdAndCSId(10);
            int csSizeFromDB = csIdList.size();
            int csSizeFromApp = copyAllAssignments.getSourceClassSectionNames.size()-1;
            System.out.println("csSizeFromDB::" + csSizeFromDB);
            System.out.println("csSizeFromApp::" + csSizeFromApp);
            Assert.assertEquals(csSizeFromDB,csSizeFromApp,"dropdown should be displayed with all the Active, Inactive, Finished Class sections associated with the user");
            // get Class section names
            List<String> actual = new ArrayList<String>();
            List<WebElement> csNameFromApp = copyAllAssignments.getSourceClassSectionNames;
            for(WebElement ele:csNameFromApp){
                actual.add(ele.getText());
            }
            actual.remove(0);
            Assert.assertEquals(csNameList, actual);
            Thread.sleep(4000);
            //Class Sections should be alphabetically sorted
            Collections.sort(csNameList);
            Assert.assertEquals(csNameList, actual);
            //Select a Class Section
            String CSToSelect = copyAllAssignments.getSourceClassSectionNames.get(1).getAttribute("title");
            System.out.println("CSToSelectBeforeClick::"+CSToSelect);
            copyAllAssignments.getSourceClassSectionNames.get(1).click();
            String selectedSourceCS = copyAllAssignments.sourceClassSectionDropDown.get(1).getAttribute("title");
            System.out.println("CSToSelect::"+CSToSelect);
            System.out.println("selectedSourceCS::"+selectedSourceCS);
            if(!CSToSelect.equals(selectedSourceCS))
            {
                Assert.fail("The Class section should be selected and displayed in the dropdown");
            }
            // Click on Cancel Link
            copyAllAssignments.cancelLink.click();
            Thread.sleep(2000);
            int copyAllAssignment = driver.findElements(By.className("ls-ins-copy-assignments-dialogBox")).size();
            if(copyAllAssignment > 0)
            {
                Assert.fail("The pp-up should be Closed and the user should be Navigated to Current Assignment page");
            }
            Assert.assertTrue(currentAssignments.copyAssignments.isDisplayed(), "The pp-up should be Closed and the user should be Navigated to Current Assignment page");
            currentAssignments.copyAssignments.click();
            Assert.assertTrue(copyAllAssignments.assignmentDialogBox.isDisplayed(), "Copy All Assignment popup should be opened");
            // Verify the previously selected Data
            Assert.assertEquals("Select Class Section",copyAllAssignments.sourceClassSectionDropDown.get(1).getAttribute("title"));
            // dropdown should be displayed with all the Active, Inactive, Finished Class sections associated with the user
            copyAllAssignments.sourceClassSectionDropDown.get(0).click();
            // Verify source drop down CS's
            getUIdAndCSId(10);
            csSizeFromDB = csIdList.size();
            csSizeFromApp = copyAllAssignments.getSourceClassSectionNames.size()-1;
            System.out.println("csSizeFromDB::" + csSizeFromDB);
            System.out.println("csSizeFromApp::" + csSizeFromApp);
            Assert.assertEquals(csSizeFromDB,csSizeFromApp,"dropdown should be displayed with all the Active, Inactive, Finished Class sections associated with the user");
            // get Class section names
            actual = new ArrayList<String>();
            csNameFromApp = copyAllAssignments.getSourceClassSectionNames;
            for(WebElement ele:csNameFromApp){
                actual.add(ele.getText());
            }
            actual.remove(0);
            Assert.assertEquals(csNameList, actual);
            Thread.sleep(4000);
            //Class Sections should be alphabetically sorted
            Collections.sort(csNameList);
            Assert.assertEquals(csNameList, actual);
            //Select a Class Section
            CSToSelect = copyAllAssignments.getSourceClassSectionNames.get(1).getAttribute("title");
            copyAllAssignments.getSourceClassSectionNames.get(1).click();
            selectedSourceCS = copyAllAssignments.sourceClassSectionDropDown.get(1).getAttribute("title");
            if(!CSToSelect.equals(selectedSourceCS))
            {
                Assert.fail("The Class section should be selected and displayed in the dropdown");
            }
            //Click on X button
            copyAllAssignments.closePopup.click();
            copyAllAssignment = driver.findElements(By.className("ls-ins-copy-assignments-dialogBox")).size();
            if(copyAllAssignment > 0)
            {
                Assert.fail("The pp-up should be Closed and the user should be Navigated to Current Assignment page");
            }
            //Assert.assertEquals("0", copyAllAssignments.assignmentDialogBox.getSize(), "The pp-up should be Closed and the user should be Navigated to Current Assignment page");
            Assert.assertTrue(currentAssignments.copyAssignments.isDisplayed(), "The pp-up should be Closed and the user should be Navigated to Current Assignment page");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC cancelAndXButtonFunctionality of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void nextButtonFunctionality()
    {
        try
        {
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver,CopyAllAssignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("10");
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.copyAssignments.click();
            // Click on the Next button
            copyAllAssignments.nextButtonInStep1.click();
            Assert.assertEquals("Please select class section",copyAllAssignments.errorMessageInStep1.getText(),"A Validation message should be displayed as ' Please select the Class Section'");
            //Click on Select Source Class section dropdown
            copyAllAssignments.sourceClassSectionDropDown.get(0).click();
            // Verify source drop down CS's
            getUIdAndCSId(10);
            int csSizeFromDB = csIdList.size();
            int csSizeFromApp = copyAllAssignments.getSourceClassSectionNames.size()-1;
            Assert.assertEquals(csSizeFromDB,csSizeFromApp,"dropdown should be displayed with all the Active, Inactive, Finished Class sections associated with the user");
            // get Class section names
            List<String> actual = new ArrayList<String>();
            List<WebElement> csNameFromApp = copyAllAssignments.getSourceClassSectionNames;
            for(WebElement ele:csNameFromApp){
                actual.add(ele.getText());
            }
            actual.remove(0);
            Assert.assertEquals(csNameList, actual);
            Thread.sleep(3000);
            //Class Sections should be alphabetically sorted
            Collections.sort(csNameList);
            Assert.assertEquals(csNameList, actual);
            //Select a Class Section
            String CSToSelect = copyAllAssignments.getSourceClassSectionNames.get(1).getAttribute("title");
            System.out.println("CSToSelectBeforeClick::"+CSToSelect);
            copyAllAssignments.getSourceClassSectionNames.get(1).click();
            String selectedSourceCS = copyAllAssignments.sourceClassSectionDropDown.get(1).getAttribute("title");
            if(!CSToSelect.equals(selectedSourceCS))
            {
                Assert.fail("The Class section should be selected and displayed in the dropdown");
            }
            //Click on the Next button
            copyAllAssignments.nextButtonInStep1.click();
            Assert.assertEquals("Please select class section",copyAllAssignments.errorMessageInStep1.getText(),"A Validation message should be displayed as ' Please select the Class Section'");
            // Click on Select Destination Class section dropdown
            int dataIndex = 10;
            String contextTitle = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            copyAllAssignments.destinationClassSectionDropDown.get(0).click();
            List<WebElement> desClassSectionsList = copyAllAssignments.getDestinationClassSectionNames;
            for(int i = 0;i < desClassSectionsList.size(); i++ )
            {
                String csTitle = desClassSectionsList.get(i).getAttribute("title");
                if(csTitle.contains("Finished"))
                {
                    Assert.fail("Finished class sections are displayed in destination drop down");
                }
                if(csTitle.trim().equals(contextTitle))
                {
                    String sourceClassSection = desClassSectionsList.get(i).getAttribute("style");
                    System.out.println("csTitle::"+csTitle);
                    System.out.println("sourceClassSection::" + sourceClassSection);
                    if(!sourceClassSection.trim().contains("display: none;"))
                    {
                        Assert.fail("Selected source class section also displayed in destination drop down");
                    }
                }
                if( i > 0)
                {
                    if(!csTitle.trim().contains("Active") && !csTitle.trim().contains("InActive"))
                    {
                        Assert.fail("Destination drop down should be displayed with all the Active, Inactive Class Sections");
                    }
                }
            }
            // get destination Class section names
            List<String> desActual = new ArrayList<String>();
            List<WebElement> descsNameFromApp = copyAllAssignments.getDestinationClassSectionNames;
            for(WebElement ele:descsNameFromApp){
                if(ele.getText() != null && !"".equals(ele.getText())){
                    desActual.add(ele.getText());
                }
            }
            activeInActiveCSNameList.remove(0);
            System.out.println("desActual::" + desActual);
            System.out.println("activeInActiveCSNameList::" + activeInActiveCSNameList);
            Assert.assertEquals(activeInActiveCSNameList, desActual);
            Thread.sleep(4000);
            //Class Sections should be alphabetically sorted
            Collections.sort(activeInActiveCSNameList);
            Assert.assertEquals(activeInActiveCSNameList, desActual);
            //Select a Class Section
            String destCSToSelect = copyAllAssignments.getDestinationClassSectionNames.get(2).getAttribute("title");
            System.out.println("destCSToSelect::"+destCSToSelect);
            copyAllAssignments.getDestinationClassSectionNames.get(2).click();
            String selectedDestCS = copyAllAssignments.destinationClassSectionDropDown.get(1).getAttribute("title");
            if(!destCSToSelect.equals(selectedDestCS))
            {
                Assert.fail("The Class section should be selected and displayed in the dropdown");
            }
            //Click on the Next button
            copyAllAssignments.nextButtonInStep1.click();
            Thread.sleep(2000);
            Assert.assertEquals("display: none;", copyAllAssignments.step1Header.getAttribute("style"), "The user should be Navigated to Step 2 of the pop-up");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC nextButtonFunctionality of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    public void selectDate(String sourceAccessibleDate)
    {
        try
        {
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver, CopyAllAssignments.class);
            Thread.sleep(2000);
            copyAllAssignments.sourceAccessibleDate.get(0).click();
            copyAllAssignments.clickNextMonth.click();
            driver.findElement(By.linkText(sourceAccessibleDate)).click();
        }
        catch (Exception e)
        {
            Assert.fail("Exception in selectDate of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    public void selectDateInSameMonth(String sourceAccessibleDate)
    {
        try
        {
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver, CopyAllAssignments.class);
            Thread.sleep(2000);
            copyAllAssignments.sourceAccessibleDate.get(0).click();
            driver.findElement(By.linkText(sourceAccessibleDate)).click();
        }
        catch (Exception e)
        {
            Assert.fail("Exception in selectDate of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void validationMessageForReCopyAssignments()
    {
        try
        {
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver,CopyAllAssignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("10");
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.copyAssignments.click();
            copyAllAssignments.sourceClassSectionDropDown.get(0).click();
            // Verify source drop down CS's
            getUIdAndCSId(10);
            int csSizeFromDB = csIdList.size();
            int csSizeFromApp = copyAllAssignments.getSourceClassSectionNames.size()-1;
            Assert.assertEquals(csSizeFromDB, csSizeFromApp, "dropdown should be displayed with all the Active, Inactive, Finished Class sections associated with the user");
            // get Class section names
            List<String> actual = new ArrayList<String>();
            List<WebElement> csNameFromApp = copyAllAssignments.getSourceClassSectionNames;
            for(WebElement ele:csNameFromApp){
                actual.add(ele.getText());
            }
            actual.remove(0);
            Assert.assertEquals(csNameList, actual);
            Thread.sleep(3000);
            //Class Sections should be alphabetically sorted
            Collections.sort(csNameList);
            Assert.assertEquals(csNameList, actual);
            //Select a Class Section
            String CSToSelect = copyAllAssignments.getSourceClassSectionNames.get(1).getAttribute("title");
            System.out.println("CSToSelectBeforeClick::" + CSToSelect);
            copyAllAssignments.getSourceClassSectionNames.get(1).click();
            String selectedSourceCS = copyAllAssignments.sourceClassSectionDropDown.get(1).getAttribute("title");
            if(!CSToSelect.equals(selectedSourceCS))
            {
                Assert.fail("The Class section should be selected and displayed in the dropdown");
            }
            // Click on Select Destination Class section dropdown
            int dataIndex = 10;
            String contextTitle = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            copyAllAssignments.destinationClassSectionDropDown.get(0).click();
            List<WebElement> desClassSectionsList = copyAllAssignments.getDestinationClassSectionNames;
            for(int i = 0;i < desClassSectionsList.size(); i++ )
            {
                String csTitle = desClassSectionsList.get(i).getAttribute("title");
                if(csTitle.contains("Finished"))
                {
                    Assert.fail("Finished class sections are displayed in destination drop down");
                }
                if(csTitle.trim().equals(contextTitle))
                {
                    String sourceClassSection = desClassSectionsList.get(i).getAttribute("style");
                    System.out.println("csTitle::"+csTitle);
                    System.out.println("sourceClassSection::" + sourceClassSection);
                    if(!sourceClassSection.trim().contains("display: none;"))
                    {
                        Assert.fail("Selected source class section also displayed in destination drop down");
                    }
                }
                if( i > 0)
                {
                    if(!csTitle.trim().contains("Active") && !csTitle.trim().contains("InActive"))
                    {
                        Assert.fail("Destination drop down should be displayed with all the Active, Inactive Class Sections");
                    }
                }
            }
            // get destination Class section names
            List<String> desActual = new ArrayList<String>();
            List<WebElement> descsNameFromApp = copyAllAssignments.getDestinationClassSectionNames;
            for(WebElement ele:descsNameFromApp){
                if(ele.getText() != null && !"".equals(ele.getText())){
                    desActual.add(ele.getText());
                }
            }
            activeInActiveCSNameList.remove(0);
            System.out.println("desActual::" + desActual);
            System.out.println("activeInActiveCSNameList::" + activeInActiveCSNameList);
            Assert.assertEquals(activeInActiveCSNameList, desActual);
            //Select a Class Section
            String destCSToSelect = copyAllAssignments.getDestinationClassSectionNames.get(2).getAttribute("title");
            System.out.println("destCSToSelect::" + destCSToSelect);
            copyAllAssignments.getDestinationClassSectionNames.get(2).click();
            String selectedDestCS = copyAllAssignments.destinationClassSectionDropDown.get(1).getAttribute("title");
            if(!destCSToSelect.equals(selectedDestCS))
            {
                Assert.fail("The Class section should be selected and displayed in the dropdown");
            }
            copyAllAssignments.nextButtonInStep1.click();
            // Select Accessible After
            selectDate("15");
            // Assign Assignments
            copyAllAssignments.assignAssignments.click();
            driver.findElement(By.cssSelector("div[class='notification-close-btn-section notification-message-link']")).click();
            // Assign Assignment again
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.copyAssignments.click();
            copyAllAssignments.sourceClassSectionDropDown.get(0).click();
            copyAllAssignments.getSourceClassSectionNames.get(1).click();
            copyAllAssignments.destinationClassSectionDropDown.get(0).click();
            copyAllAssignments.getDestinationClassSectionNames.get(2).click();
            // verify the alert message
            System.out.println("alertMessage::" + copyAllAssignments.errorMessageInStep1.getText());
            Assert.assertEquals("All assignments are already copied for the selected class sections.",copyAllAssignments.errorMessageInStep1.getText(),"“All assignments already copied for the selected class sections.” message should be displayed");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC validationMessageForReCopyAssignments of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void verifyUIOfStep2OfCopyAllAssignments()
    {
        try
        {
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver,CopyAllAssignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("10");
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.copyAssignments.click();
            copyAllAssignments.sourceClassSectionDropDown.get(0).click();
            String sourceClassSectionInStep1 = copyAllAssignments.getSourceClassSectionNames.get(1).getText();
            System.out.println("sourceClassSectionInStep1::" + sourceClassSectionInStep1);
            copyAllAssignments.getSourceClassSectionNames.get(1).click();
            copyAllAssignments.destinationClassSectionDropDown.get(0).click();
            Thread.sleep(1000);
            String destinationClassSectionInStep2 = copyAllAssignments.getDestinationClassSectionNames.get(3).getText();
            System.out.println("destinationClassSectionInStep2::" + destinationClassSectionInStep2);
            copyAllAssignments.getDestinationClassSectionNames.get(3).click();
            copyAllAssignments.nextButtonInStep1.click();
            System.out.println("header::" + copyAllAssignments.dialogHeader.getText().trim());
            Thread.sleep(1000);
            Assert.assertEquals("Copy all Assignments", copyAllAssignments.dialogHeader.getText().trim(), "The header label should be 'Copy all Assignments");
            Assert.assertTrue(copyAllAssignments.backButtonInStept2.isDisplayed(), "Back arrow button should be availabel at the top left hand side of the pop-up");
            Assert.assertTrue(copyAllAssignments.closePopup.isDisplayed(),"X button should be availabel at top right hand corner of the pop-up window");
            Assert.assertTrue(copyAllAssignments.cancelLink.isDisplayed(),"Cancel Link should be availabel adjacent to Next button");
            Assert.assertTrue(copyAllAssignments.assignAssignments.isDisplayed(),"Assign button should be availabel next to Cancel link");
            // Verify sourse and destination Titles in Step2
            Thread.sleep(1000);
            Assert.assertTrue(driver.findElement(By.className("ls-copy-source-title")).isDisplayed(), "Source and destination labbel should be availabel and it should have the values selected in Step 1 of the popup");
            Assert.assertTrue(driver.findElement(By.className("ls-copy-destination-title")).isDisplayed(), "Source and destination labbel should be availabel and it should have the values selected in Step 1 of the popup");
            Assert.assertEquals(sourceClassSectionInStep1, copyAllAssignments.sourceCSNameInSteps2.getText().trim(), "Source and destination labbel should be availabel and it should have the values selected in Step 1 of the popup");
            Assert.assertEquals(destinationClassSectionInStep2, copyAllAssignments.destinationCSNameInSteps2.getText().trim(), "Source and destination labbel should be availabel and it should have the values selected in Step 1 of the popup");
            //Verify for the Auto generate dates label, Checkbox and Help icon
            Assert.assertTrue(driver.findElement(By.cssSelector("label[class='ls-auto-generate-check checked']")).isDisplayed(), "Auto Generate dates checkbox should be checked by default");
            Assert.assertTrue(copyAllAssignments.additionalHelpIcon.isDisplayed(), "Help icon should be availabel next to Auto Generate dates ");
            // Click on the help icon and Verify the text
            copyAllAssignments.additionalHelpIcon.click();
            Assert.assertEquals("Select any start date to pre-populate all dates and times in the same order and over the same time span as the source class section.", driver.findElement(By.cssSelector("div[class='assign-this-help-tooltip-wrapper right-aligned']")).getText(), "Help icon should have an information message as 'Select any start date to pre-populate all dates and times in the same order and over the same time span as the source class section.'");
            //Verify the notification message displayed at the bottom of the pop-up
            System.out.println("bottomMesssage::"+copyAllAssignments.errorMessageInStep1.getText().trim());
            Assert.assertEquals("Please select date in the first date box to auto-generate dates or uncheck auto-generate dates", copyAllAssignments.errorMessageInStep1.getText().trim(), "The message should be as “Please select date in the first date box to auto-generate dates or uncheck the auto-generate dates.”");
            // Verify the content Area
            Assert.assertTrue(driver.findElement(By.className("ls-copy-assignment-row")).isDisplayed(), "The content area should contain all the Assignments assigned to the source Class section");
            //Verify the First Accessible after Date field
            Assert.assertEquals("rgba(209, 90, 49, 1)",copyAllAssignments.sourceAccessibleDate.get(0).getCssValue("border-top-color"),"The text box should be highlighted in red color");
            Assert.assertEquals("rgba(209, 90, 49, 1)",copyAllAssignments.sourceAccessibleDate.get(0).getCssValue("border-right-color"),"The text box should be highlighted in red color");
            Assert.assertEquals("rgba(209, 90, 49, 1)",copyAllAssignments.sourceAccessibleDate.get(0).getCssValue("border-bottom-color"),"The text box should be highlighted in red color");
            Assert.assertEquals("rgba(209, 90, 49, 1)",copyAllAssignments.sourceAccessibleDate.get(0).getCssValue("border-left-color"),"The text box should be highlighted in red color");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC verifyUIOfStep2OfCopyAllAssignments of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    String sourceCS1,destinationCS1 = null;

    @Test(priority = 8,enabled = true)
    public void verifyTheBackArrowFunctionality()
    {
        try
        {
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver,CopyAllAssignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("10");
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.copyAssignments.click();
            copyAllAssignments.sourceClassSectionDropDown.get(0).click();
            sourceCS1 = copyAllAssignments.getSourceClassSectionNames.get(1).getText();
            copyAllAssignments.getSourceClassSectionNames.get(1).click();
            copyAllAssignments.destinationClassSectionDropDown.get(0).click();
            destinationCS1 = copyAllAssignments.getDestinationClassSectionNames.get(4).getText();
            copyAllAssignments.getDestinationClassSectionNames.get(4).click();
            //Click on Next button
            Thread.sleep(1000);
            copyAllAssignments.nextButtonInStep1.click();
            Assert.assertTrue(copyAllAssignments.step2Header.isDisplayed(), "The user should be navigated to Step 2 of the pop-up");
            selectDate("12");
            //Click on the Back Arrow
            Thread.sleep(1000);
            copyAllAssignments.backButtonInStept2.click();
            Assert.assertTrue(copyAllAssignments.step1Header.isDisplayed(), "The user should be navigated to Step 1 of the pop-up");
            // Verify the Source and Destination dropdown Values
            Assert.assertEquals(sourceCS1, copyAllAssignments.sourceClassSectionDropDown.get(1).getText(), "Source Class Nams should be availabel and it should have the values selected in Step 1 of the popup");
            Assert.assertEquals(destinationCS1, copyAllAssignments.destinationClassSectionDropDown.get(1).getText(), "Destination Class Nams should be availabel and it should have the values selected in Step 1 of the popup");
            //Click on Next button
            copyAllAssignments.nextButtonInStep1.click();
            Assert.assertTrue(copyAllAssignments.step2Header.isDisplayed(), "The user should be navigated to Step 2 of the pop-up");
            //Verify the data on the page
            Thread.sleep(1000);
            String datePresent = copyAllAssignments.sourceAccessibleDate.get(0).getAttribute("class");
            System.out.println("datePresent::" + datePresent);
            if(datePresent.contains("not-selected"))
            {
                Assert.fail("all the data should be available as entered before");
            }
            //Click on the Back Arrow
            copyAllAssignments.backButtonInStept2.click();
            Assert.assertTrue(copyAllAssignments.step1Header.isDisplayed(), "The user should be navigated to Step 1 of the pop-up");
            //Click on Source/Destination Class Section dropdown and Change the previously selected Class section
            copyAllAssignments.destinationClassSectionDropDown.get(0).click();
            copyAllAssignments.getDestinationClassSectionNames.get(3).click();
            Assert.assertEquals("All the dates added in step 2 for the destination class section would be lost if you change the class section.", copyAllAssignments.errorMessageInStep1.getText(), "A Validation Message should be displayed as All the dates added in step 2 for the destination class section would be lost if you change the class section.");
            //Click on Next button
            copyAllAssignments.nextButtonInStep1.click();
            Assert.assertTrue(copyAllAssignments.step2Header.isDisplayed(), "The user should be navigated to Step 2 of the pop-up");
            datePresent = copyAllAssignments.sourceAccessibleDate.get(0).getAttribute("class");
            System.out.println("datePresent::" + datePresent);
            if(!datePresent.contains("not-selected"))
            {
                Assert.fail("all the data should be available as entered before");
            }
            //Change the Class sections to the previous selections
            copyAllAssignments.backButtonInStept2.click();
            copyAllAssignments.destinationClassSectionDropDown.get(0).click();
            copyAllAssignments.getDestinationClassSectionNames.get(4).click();
            String errorMessage = copyAllAssignments.errorMessageInStep1.getText();
            System.out.println("errorMessage::"+errorMessage);
            if(!errorMessage.equals(""))
            {
                Assert.fail("The message should not be displayed");
            }
            //Click on Next button

        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC verifyTheBackArrowFunctionality of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    public void navigateToStep2()
    {
        try
        {
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver,CopyAllAssignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.copyAssignments.click();
            copyAllAssignments.sourceClassSectionDropDown.get(0).click();
            copyAllAssignments.getSourceClassSectionNames.get(1).click();
            copyAllAssignments.destinationClassSectionDropDown.get(0).click();
            copyAllAssignments.getDestinationClassSectionNames.get(4).click();
            Thread.sleep(1000);
            copyAllAssignments.nextButtonInStep1.click();
        }
        catch (Exception e)
        {
            Assert.fail("Exception in navigateToStep2 of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void viewOfAssignmentCardOnStep2()
    {
        try
        {
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver,CopyAllAssignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("10");
            navigateToStep2();
            Assert.assertTrue(copyAllAssignments.step2Header.isDisplayed(), "The user should be navigated to Step 2 of the pop-up");
            //Verify the Assignment card
            int assignmentInStep2 = copyAllAssignments.assignmentCard.size();
            if(assignmentInStep2 == 0)
            {
                Assert.fail("Assignment card is not available in Step 2 page");
            }
            // Assignment Name
            Assert.assertEquals("Assignment Name", driver.findElement(By.className("ls-copy-step2-table-header-column1")).getText(), "Assignment Name is not not displayed");
            // Accessible after date and time
            Assert.assertEquals("Accessible After", driver.findElement(By.className("ls-copy-step2-table-header-column2")).getText(), "Accessible After is not not displayed");
            Assert.assertEquals("Due Date", driver.findElement(By.className("ls-copy-step2-table-header-column3")).getText(), "Due Date is not not displayed");
            //Source Accessible Date(Read Only)
            Assert.assertEquals("Source Accessible Date", driver.findElements(By.xpath(".//*[@class='ls-assignment-date-time-block']/div")).get(0).getText(), "Source Accessible Date is not not displayed");
            Assert.assertTrue(driver.findElement(By.className("ls-assignment-accessible-date-time")).isDisplayed(), "Source Accessible Time is not displayed");
            // Source Due Date(Read Only)
            Assert.assertEquals("Source Due Date", driver.findElements(By.xpath(".//*[@class='ls-assignment-date-time-block']/div")).get(1).getText(), "Source Due Date is not not displayed");
            Assert.assertTrue(driver.findElement(By.className("ls-assignment-due-date-time")).isDisplayed(), "Due date and Time is not displayed");
  /*          //Verify the format of Source Accessible date
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
            Date date = new Date();
            System.out.println(date);
            String date1= dateFormat.format(date);
            System.out.println(date1);*/
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC viewOfAssignmentCardOnStep2 of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    @Test(priority = 10,enabled = true)
    public void unCheckFunctionalityOfAutoGenerateDates()
    {
        try
        {
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver,CopyAllAssignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver,QuestionBank.class);
            new Assignment().create(11);
            new Assignment().addQuestions(11, "truefalse", "");
            new LoginUsingLTI().ltiLogin("10");
            // Assign an assignment
           /* new Navigator().NavigateTo("Question Banks");
            questionBank.questionBankDropdown.click();
            questionBank.practiceSetsOnly.click();
            questionBank.getSearchInputFieldOnQuestionBank().get(1).click();
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys(" Personalized Practice - Ch");
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            Thread.sleep(3000);
            questionBank.assignThisLink.get(2).click();*/
            Thread.sleep(2000);
            new Assignment().assignToStudent(11);
            navigateToStep2();
            //The Checkbox should be checked by default
            Assert.assertTrue(driver.findElement(By.cssSelector("label[class='ls-auto-generate-check checked']")).isDisplayed(), "Auto Generate dates checkbox should be checked by default");
            selectDate("15");
            // All the dates should be populated for all the Assignment based on the gap between the Source Accessible Date and Source Due Date
            int assignmentSize = copyAllAssignments.assignmentCard.size();
            for(int i = 0; i < assignmentSize; i++)
            {
                String accessibleDate = copyAllAssignments.sourceAccessibleDate.get(i).getAttribute("class");
                if(accessibleDate.contains("not-selected"))
                {
                    Assert.fail("All the dates should be populated for all the Assignment");
                }
            }
            //Verify the Auto generate Dates checkbox
            Assert.assertTrue(driver.findElement(By.cssSelector("label[class='ls-auto-generate-check checked']")).isDisplayed(), "Auto Generate dates checkbox should be checked");
            Assert.assertTrue(driver.findElement(By.cssSelector("label[class='ls-auto-generate-check checked']")).isEnabled(), "Auto Generate dates checkbox should be Enabled");
            // Uncheck the checkbox
            driver.findElement(By.cssSelector("label[class='ls-auto-generate-check checked']")).click();
            Assert.assertEquals(copyAllAssignments.notificationInStep2.get(0).getText(),"All the dates added for the destination class section would be lost. Do you want to continue?", "a validation message should be displayed");
            //Click on No
            driver.findElement(By.cssSelector("div[class='notification-message-no-section notification-message-link']")).click();
            Assert.assertTrue(driver.findElement(By.cssSelector("label[class='ls-auto-generate-check checked']")).isDisplayed(), "Auto Generate dates checkbox should be checked");
            Thread.sleep(1000);
            //Click on X
            driver.findElement(By.cssSelector("label[class='ls-auto-generate-check checked']")).click();
            driver.findElement(By.cssSelector("a[title='Close']")).click();
            Assert.assertTrue(driver.findElement(By.cssSelector("label[class='ls-auto-generate-check checked']")).isDisplayed(), "Auto Generate dates checkbox should be checked");
            Thread.sleep(1000);
            // Click on Yes
            driver.findElement(By.cssSelector("label[class='ls-auto-generate-check checked']")).click();
            driver.findElement(By.cssSelector("div[class='notification-message-yes-section notification-message-link']")).click();
            Thread.sleep(2000);
            assignmentSize = copyAllAssignments.assignmentCard.size();
            for(int i = 0; i < assignmentSize; i++)
            {
                String accessibleDate = copyAllAssignments.sourceAccessibleDate.get(i).getAttribute("class");
                System.out.println("accessibleDate::"+accessibleDate);
                if(accessibleDate.contains("selected"))
                {
                    Assert.fail("The checkbox should be unchecked and all the entered Dates should be cleared");
                }
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC unCheckFunctionalityOfAutoGenerateDates of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    @Test(priority = 11,enabled = true)
    public void cancelLinkXButtonFunctionalityOnStep2()
    {
        try
        {
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver,CopyAllAssignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver,QuestionBank.class);
            new LoginUsingLTI().ltiLogin("10");
            navigateToStep2();
            //Click on Cancel Link/ X button
            copyAllAssignments.cancelLink.click();
            //System.out.println("size::" + copyAllAssignments.assignmentDialogBoxDisplay.size());
            Assert.assertEquals(0, copyAllAssignments.assignmentDialogBoxDisplay.size(), "The popup should be closed");
            navigateToStep2();
            copyAllAssignments.closePopup.click();
            Assert.assertEquals(0, copyAllAssignments.assignmentDialogBoxDisplay.size(), "The popup should be closed");
            navigateToStep2();
            selectDate("10");
            //On entered Values
            //Click on Cancel Link/ X button
            copyAllAssignments.cancelLink.click();
            Assert.assertEquals("No assignments will be copied into destination class and all dates added to assignments will be lost. Do you want to cancel?", copyAllAssignments.notificationInStep2.get(0).getText(), "A validation message should be displayed as “No assignments will be copied into destination class and all dates added to assignments will be lost. Do you want to cancel?  Yes, No");
            copyAllAssignments.notificationClose.get(0).click();
            copyAllAssignments.closePopup.click();
            Assert.assertEquals("No assignments will be copied into destination class and all dates added to assignments will be lost. Do you want to cancel?", copyAllAssignments.notificationInStep2.get(0).getText(), "A validation message should be displayed as “No assignments will be copied into destination class and all dates added to assignments will be lost. Do you want to cancel?  Yes, No");
            // Click on X button
            copyAllAssignments.notificationClose.get(0).click();
            Assert.assertEquals(0, copyAllAssignments.notificationInStep2.size(), "The Validationn should be closed and no action is performed on the page");
            //Click on Yes link
            copyAllAssignments.closePopup.click();
            copyAllAssignments.notificationYes.get(0).click();
            Assert.assertEquals(0, copyAllAssignments.notificationInStep2.size(), "The Validationn should be closed and no action is performed on the page");
            //Click on No link
            copyAllAssignments.closePopup.click();
            copyAllAssignments.notificationNo.get(0).click();
            Assert.assertEquals(0, copyAllAssignments.assignmentDialogBoxDisplay.size(), "The popup should be closed");
            // Click on Copy Assignments link
            currentAssignments.copyAssignments.click();
            Assert.assertEquals("Select Class Section", copyAllAssignments.destinationClassSection.getText(), "Verify the default text displayed for Select Destination Class section Dropdown");
            Assert.assertEquals("Select Class Section", copyAllAssignments.sourceClassSection.getText(), "Verify the default text displayed for Select Source Class section Dropdown");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC cancelLinkXButtonFunctionalityOnStep2 of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    @Test(priority = 12, enabled = true)
    public void assignButtonFunctionalityOnStep2()
    {
        try
        {
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver,CopyAllAssignments.class);
            new LoginUsingLTI().ltiLogin("12");
            navigateToStep2();
            int assignmentsCount = copyAllAssignments.assignmentCard.size();
            if(assignmentsCount < 2)
            {
                Assert.fail("Multiple assignments are not exist");
            }
            selectDate("15");
            // Click on Assign button
            copyAllAssignments.assignAssignments.click();
            Thread.sleep(2000);
            Assert.assertEquals(0, copyAllAssignments.assignmentDialogBoxDisplay.size(), "The popup should be closed");
            // Verify the Validation Message
            // The current Class section is the destination Class Section
            Assert.assertEquals("All assignments created. Do you want to navigate to the destination class section.", copyAllAssignments.notificationInStep2.get(0).getText(), "“All assignments created. Do you want to navigate to the destination class section.”");
            copyAllAssignments.notificationClose.get(0).click();
            Thread.sleep(2000);
            // The current Class section is not the destination Class Section
            // Verify the Validation Message
            navigateToStep2();
            selectDate("15");
            copyAllAssignments.assignAssignments.click();
            Thread.sleep(2000);
            Assert.assertEquals(0, copyAllAssignments.assignmentDialogBoxDisplay.size(), "The popup should be closed");
            Assert.assertEquals("All assignments created. Do you want to navigate to the destination class section.", copyAllAssignments.notificationInStep2.get(0).getText(), "“All assignments created. Do you want to navigate to the destination class section.”");
            //Click on No
            String csNameBeforeNo = driver.findElement(By.className("default-cs-name")).getAttribute("title");
            copyAllAssignments.notificationNo.get(0).click();
            String csNameAfterNo = driver.findElement(By.className("default-cs-name")).getAttribute("title");
            System.out.println("csNameBeforeNo::"+csNameBeforeNo);
            System.out.println("csNameBeforeNo::"+csNameBeforeNo);
            System.out.println("csNameAfterNo::"+csNameAfterNo);
            System.out.println("csNameAfterNo::" + csNameAfterNo);
            if(!csNameAfterNo.equals(csNameBeforeNo))
            {
                Assert.fail("The user should be on the current Assignment page of the same class section");
            }
            //change class section
            driver.findElement(By.className("default-cs-name")).click();
            driver.findElement(By.id("active-cs")).click();
            driver.findElements(By.className("class-section")).get(0).click();

            // Click on Yes
            navigateToStep2();
            selectDate("15");
            copyAllAssignments.assignAssignments.click();
            Thread.sleep(2000);
            Assert.assertEquals(0, copyAllAssignments.assignmentDialogBoxDisplay.size(), "The popup should be closed");
            Assert.assertEquals("All assignments created. Do you want to navigate to the destination class section.", copyAllAssignments.notificationInStep2.get(0).getText(), "“All assignments created. Do you want to navigate to the destination class section.”");

            String csNameBeforeYes = driver.findElement(By.className("default-cs-name")).getAttribute("title");
            copyAllAssignments.notificationYes.get(0).click();
            //Verify the Validation Message
            System.out.println(" copyAllAssignments.notificationInStep2::"+ copyAllAssignments.notificationInStep2.get(0).getText());
            Assert.assertEquals("It may take a few minutes for the changes to take place. Please refresh to view changes.", copyAllAssignments.notificationInStep2.get(0).getText(), "“All assignments created.Do you want to navigate to the destination class section. Yes, No”");
            Thread.sleep(4000);
            String csNameAfterYes = driver.findElement(By.className("default-cs-name")).getAttribute("title");
            System.out.println("csNameBeforeYes::"+csNameBeforeYes);
            System.out.println("csNameBeforeYes::"+csNameBeforeYes);
            System.out.println("csNameAfterYes::"+csNameAfterYes);
            System.out.println("csNameAfterYes::" + csNameAfterYes);
            if(csNameAfterYes.equals(csNameBeforeYes))
            {
                Assert.fail("The user should be navigated to the Current Assignment page of the Destination Class  Setcion");
            }
            //Verify the Assignment card on the Current Assignment page
            Assert.assertTrue(driver.findElement(By.className("ls-assignment-item-detail-section")).isDisplayed(),"All the details of the Source Class section Assignment should be availabel");
            // Verify the total count of the Assignment and assignment properties on the Current Assignment page
            assignmentsCount = driver.findElements(By.className("ls-assignment-item-detail-section")).size();
            if(assignmentsCount < 3)
            {
                Assert.fail("The count should match with the Source assignment count");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC assignButtonFunctionalityOnStep2 of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    @Test(priority = 13, enabled = true)
    public void assignButtonFunctionalityOnStep2OfCopyAllAssignmentPopUp()
    {
        try
        {
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver,CopyAllAssignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver,QuestionBank.class);
            new LoginUsingLTI().ltiLogin("10");
            navigateToStep2();
            Thread.sleep(2000);
            // Verify the Auto generate Dates checkbox
            Assert.assertTrue(copyAllAssignments.autoGenerateDatesChecked.isDisplayed(), "The Checkbox should be checked");
            Assert.assertEquals(copyAllAssignments.autoGenerateDatesChecked.getCssValue("color"),"rgba(76, 76, 76, 1)","The Checkbox should be greyed out");
            selectDate("1");
            // All the dates should be populated for all the Assignment based on the gap between the Source Accessible Date and Source Due Date
            int assignmentSize = copyAllAssignments.assignmentCard.size();
            for(int i = 0; i < assignmentSize; i++)
            {
                String accessibleDate = copyAllAssignments.sourceAccessibleDate.get(i).getAttribute("class");
                if(accessibleDate.contains("not-selected"))
                {
                    Assert.fail("All the dates should be populated for all the Assignment");
                }
            }
            //Verify the Auto generate Dates checkbox
            Assert.assertTrue(driver.findElement(By.cssSelector("label[class='ls-auto-generate-check checked']")).isDisplayed(), "Auto Generate dates checkbox should be checked");
            Assert.assertTrue(driver.findElement(By.cssSelector("label[class='ls-auto-generate-check checked']")).isEnabled(), "Auto Generate dates checkbox should be Enabled");
            // Uncheck the checkbox
            driver.findElement(By.cssSelector("label[class='ls-auto-generate-check checked']")).click();
            Assert.assertEquals(copyAllAssignments.notificationInStep2.get(0).getText(), "All the dates added for the destination class section would be lost. Do you want to continue?", "a validation message should be displayed");
            //Click on No
            driver.findElement(By.cssSelector("div[class='notification-message-no-section notification-message-link']")).click();
            Assert.assertTrue(driver.findElement(By.cssSelector("label[class='ls-auto-generate-check checked']")).isDisplayed(), "Auto Generate dates checkbox should be checked");
            Thread.sleep(1000);
            //Click on X
            driver.findElement(By.cssSelector("label[class='ls-auto-generate-check checked']")).click();
            driver.findElement(By.cssSelector("a[title='Close']")).click();
            Assert.assertTrue(driver.findElement(By.cssSelector("label[class='ls-auto-generate-check checked']")).isDisplayed(), "Auto Generate dates checkbox should be checked");
            Thread.sleep(1000);
            // Click on Yes
            driver.findElement(By.cssSelector("label[class='ls-auto-generate-check checked']")).click();
            driver.findElement(By.cssSelector("div[class='notification-message-yes-section notification-message-link']")).click();
            Thread.sleep(2000);
            assignmentSize = copyAllAssignments.assignmentCard.size();
            for(int i = 0; i < assignmentSize; i++)
            {
                String accessibleDate = copyAllAssignments.sourceAccessibleDate.get(i).getAttribute("class");
                System.out.println("accessibleDate::"+accessibleDate);
                if(accessibleDate.contains("selected"))
                {
                    Assert.fail("The checkbox should be unchecked and all the entered Dates should be cleared");
                }
            }

           /* //Verify the gap of the Accessible After and Due Date Assignment Date and Time
            List<WebElement> accessibleDate=driver.findElements(By.xpath("//span[@class='ls-assignment-accessible-date-time']"));
            String firstAccessibleDate=accessibleDate.get(0).getAttribute("title").substring(4, accessibleDate.get(0).getAttribute("title").indexOf(","));
            String secondAccessibleDate=accessibleDate.get(1).getAttribute("title").substring(4,accessibleDate.get(1).getAttribute("title").indexOf(","));
            System.out.println("firstAccessibleDate:" + firstAccessibleDate);
            System.out.println("firstAccessibleDate:" + firstAccessibleDate);
            System.out.println("secondAccessibleDate:" + secondAccessibleDate);
            System.out.println("secondAccessibleDate:" + secondAccessibleDate);
            List<WebElement> dueDate = driver.findElements(By.className("ls-assignment-due-date-time"));
            String firstDueDate=dueDate.get(0).getAttribute("title").substring(4, dueDate.get(0).getAttribute("title").indexOf(","));
            String secondDueDate=dueDate.get(1).getAttribute("title").substring(4, dueDate.get(1).getAttribute("title").indexOf(","));
            System.out.println("firstDueDate:"+firstDueDate);
            System.out.println("firstDueDate:" + firstDueDate);
            System.out.println("secondDueDate:"+secondDueDate);
            System.out.println("secondDueDate:" + secondDueDate);
            int accessibleDateDiff1 = Integer.parseInt(firstAccessibleDate) - Integer.parseInt(secondAccessibleDate);
            System.out.println("accessibleDateDiff1:"+accessibleDateDiff1);
            System.out.println("accessibleDateDiff1:"+accessibleDateDiff1);
            int dueDateDiffAssessment1 = Integer.parseInt(firstAccessibleDate) - Integer.parseInt(firstDueDate);
            System.out.println("dueDateDiffAssessment1:"+dueDateDiffAssessment1);
            System.out.println("dueDateDiffAssessment1:"+dueDateDiffAssessment1);
            int dueDateDiffAssessment2 = Integer.parseInt(secondAccessibleDate) - Integer.parseInt(secondDueDate);
            System.out.println("dueDateDiffAssessment2:" + dueDateDiffAssessment2);
            System.out.println("dueDateDiffAssessment2:"+dueDateDiffAssessment2);*/
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC assignButtonFunctionalityOnStep2OfCopyAllAssignmentPopUp of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    @Test(priority = 14,enabled = true)
    public void copyAssignmentsFromOneClassSectionToAnother()
    {
        try
        {
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver,CopyAllAssignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver,QuestionBank.class);
            Dashboard dashboard = PageFactory.initElements(driver,Dashboard.class);
            new LoginUsingLTI().ltiLogin("10");
            //The user should be successfully logged in to the application and the default landing page should be displayed
            Assert.assertTrue(dashboard.dashBoard.isDisplayed(), "The user is not successfully logged in to the application and the default landing page should be displayed");
            //The Navigation List should be displayed to the User
            new Navigator().NavigateTo("Current Assignments"); // Navigate to Current Assignments
            //Current Assignment page should be opened
            Assert.assertTrue(currentAssignments.currentAssignmentPage.isDisplayed(), "Current Assignments page should be opened");
            //The Link should be present on "Top Right side" of the Page.
            Assert.assertTrue(currentAssignments.copyAssignments.isDisplayed(), "Copy Assignments link should be displayed");//Copy Assignments Link
            Thread.sleep(1000);
            // 5.Click on the "Copy Assignments" link.
            currentAssignments.copyAssignments.click();
            Assert.assertTrue(copyAllAssignments.assignmentDialogBox.isDisplayed(), "Copy Assignments pop-up should be opened");
            //"18.Select a value of Source Class section.
            //19.Verify the Destination Class Section dropdown"
            copyAllAssignments.sourceClassSectionDropDown.get(0).click();
            copyAllAssignments.getSourceClassSectionNames.get(1).click();
            Assert.assertEquals("sbHolder", copyAllAssignments.destinationClassSection.getAttribute("class"), "The value should be displayed and Select Destination Class section should be enabled");
            // Click on Select Destination Class section dropdown
            int dataIndex = 10;
            String contextTitle = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            copyAllAssignments.destinationClassSectionDropDown.get(0).click();
            List<WebElement> desClassSectionsList = copyAllAssignments.getDestinationClassSectionNames;
            for(int i = 0;i < desClassSectionsList.size(); i++ )
            {
                String csTitle = desClassSectionsList.get(i).getAttribute("title");
                if(csTitle.contains("Finished"))
                {
                    Assert.fail("Finished class sections are displayed in destination drop down");
                }
                if(csTitle.trim().equals(contextTitle))
                {
                    String sourceClassSection = desClassSectionsList.get(i).getAttribute("style");
                    System.out.println("csTitle::"+csTitle);
                    System.out.println("sourceClassSection::" + sourceClassSection);
                    if(!sourceClassSection.trim().contains("display: none;"))
                    {
                        Assert.fail("Selected source class section also displayed in destination drop down");
                    }
                }
                if( i > 0)
                {
                    if(!csTitle.trim().contains("Active") && !csTitle.trim().contains("InActive"))
                    {
                        Assert.fail("Destination drop down should be displayed with all the Active, Inactive Class Sections");
                    }
                }
            }
            //23.Select a Destination class section.
            copyAllAssignments.getDestinationClassSectionNames.get(2).click();
            copyAllAssignments.nextButtonInStep1.click();
            Assert.assertTrue(copyAllAssignments.step2Header.isDisplayed(), "The user should be navigated to Step 2 of the pop-up");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC copyAssignmentsFromOneClassSectionToAnother of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }


    @Test(priority = 15,enabled = true)
    public void userViewAndAccessAllTheAssignedAssignment()
    {
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver,CopyAllAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver,QuestionBank.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            Dashboard dashboard = PageFactory.initElements(driver,Dashboard.class);
            new LoginUsingLTI().ltiLogin("10");
            //The user should be successfully logged in to the application and the default landing page should be displayed
            Assert.assertTrue(dashboard.dashBoard.isDisplayed(), "The user is not successfully logged in to the application and the default landing page should be displayed");
            //2. Navigate to "Current Assignments" page from Main navigator
            new Navigator().NavigateTo("Current Assignments"); // Navigate to Current Assignments
            //Current Assignment page should be opened
            Assert.assertTrue(currentAssignments.currentAssignmentPage.isDisplayed(), "Current Assignments page should be opened");
            //The Link should be present on "Top Right side" of the Page.
            Assert.assertTrue(currentAssignments.copyAssignments.isDisplayed(), "Copy Assignments link should be displayed");//Copy Assignments Link
            Thread.sleep(1000);
            // 3. Click on "Copy Assignments" link.
            currentAssignments.copyAssignments.click();
            Assert.assertTrue(copyAllAssignments.assignmentDialogBox.isDisplayed(), "Copy Assignments pop-up should be opened");
            //4. Select Source and Destination class sections
            copyAllAssignments.sourceClassSectionDropDown.get(0).click();
            copyAllAssignments.getSourceClassSectionNames.get(1).click();
            copyAllAssignments.destinationClassSectionDropDown.get(0).click();
            copyAllAssignments.getDestinationClassSectionNames.get(2).click();
            //5. Click on "Next" button.
            copyAllAssignments.nextButtonInStep1.click();
            Assert.assertTrue(copyAllAssignments.step2Header.isDisplayed(), "The user should be navigated to Step 2 of the pop-up");
            //6. Select "Accessible After" and click on "Assign" button.
            DateFormat dateFormat = new SimpleDateFormat("dd");
            //get current date time with Date()
            Date date = new Date();
            // Now format the date
            String date1= dateFormat.format(date);
            copyAllAssignments.sourceAccessibleDate.get(0).click();
            driver.findElement(By.linkText(date1)).click();
            copyAllAssignments.assignAssignments.click();
            copyAllAssignments.notificationYes.get(0).click();
            // 7. Login as a student to the destination class section.
            new LoginUsingLTI().ltiLogin("133");
            Assert.assertTrue(dashboard.dashBoard.isDisplayed(), "The user is not successfully logged in to the application and the default landing page should be displayed");
            //8. Click on the Main Navigator on top left hand corner of the screen
            //9. Click on the "Assignments".
            new Navigator().NavigateTo("Assignments");
            //10. Verify Assignments.
            //All the assignments(copy assignments) assigned to the destination class section should be availabel.
            Assert.assertEquals(driver.findElements(By.className("ls-assignment-item-detail-section")).size(),2,"All the assignments(copy assignments) assigned to the destination class section should be availabel.");
            //"posted an assignment." message should display for each assignment with prefix of assignment instructor last name and first name.
            Assert.assertEquals(driver.findElement(By.className("ls-assignment-item-author-name")).getText(), "family, givenname", "instructor last name and first name should be displayed");
            Assert.assertEquals(driver.findElement(By.className("ls-assignment-post-label")).getText(), "posted an assignment.", "\"posted an assignment.\" message should display for each assignment with prefix of assignment instructor last name and first name.");
            //Instructor thumbnail should be displayed for each assignment.
            Assert.assertEquals(driver.findElements(By.className("prof-icon-image")).size(),2,"Instructor thumbnail should be displayed for each assignment.");
            // "Bookmark" should be availabel for each assignment.
            Assert.assertEquals(assignments.bookMarkAssignment.size(), 2, "\"Bookmark\" should be availabel for each assignment.");
            // Student should be able to bookmark and unbookmark an assignment.
            assignments.bookMarkAssignment.get(0).click();
            Assert.assertEquals(assignments.bookMarkAssignment.size(),1, "Student should be able to bookmark an assignment.");
            assignments.unBookMarkAssignment.get(0).click();
            Assert.assertEquals(assignments.bookMarkAssignment.size(), 2, "Student should be able to bookmark an assignment.");
            //Assignment icon should be availabel for each assignment.
            String assignmentIcon = driver.findElement(By.xpath(".//div[@class='ls-assignment-name-block']/img")).getAttribute("src");
            if(!assignmentIcon.contains("home-work-icon.png"))
            {
                Assert.fail("Assignment icon should be availabel for each assignment.");
            }
            // Assignment name with short label and Description should be displayed.
            Assert.assertTrue(assignments.assignmentName.isDisplayed(), "Assignment name should be displayed");
            Assert.assertTrue(driver.findElement(By.className("ls-assignment-grading-title")).isDisplayed(), "Assignment description should be displayed");
            //"Assignment Reference:" should be displayed.
            Assert.assertTrue(driver.findElement(By.cssSelector("span[data-localize='Assignment Reference']")).isDisplayed(), "\"Assignment Reference:\" should be displayed.");
            //"Gradable" should be displayed for gradable assignments.
            Assert.assertTrue(driver.findElement(By.cssSelector("span[class='ls-assignment-graded-quiz-icon assessment-block-icon']")).isDisplayed(), "\"Gradable\" should be displayed for gradable assignments.");
            //"Like" and "Comments" links should be displayed for each assignment.
            Assert.assertTrue(assignments.likeLink.isDisplayed(), "\"Like\" links should be displayed");
            Assert.assertTrue(driver.findElement(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']")).isDisplayed(), "\"Comments\" links should be displayed");
            // "Like" and "Comments" symbols should be displayed for each assignment.
            Assert.assertTrue(assignments.likeIcon.isDisplayed(), "\"Like\" symbols should be displayed");
            Assert.assertTrue(driver.findElement(By.cssSelector("i[class='ls-icon-img ls--comments-icon']")).isDisplayed(), "\"Comments\" symbols should be displayed");
            //Number of likes and comments count should be zero by default.
            Assert.assertEquals(driver.findElement(By.className("ls-post-like-count")).getText(), "0", "Number of likes count should be zero by default.");
            Assert.assertEquals(driver.findElement(By.className("ls-stream-post-comment-count")).getText(), "0", "Number of comments count should be zero by default.");//change 0
            //Student should be able to like and unlike the assignments.
            assignments.likeLink.click();
            Assert.assertEquals(driver.findElement(By.className("ls-post-like-count")).getText(), "1", "Student should be able to like the assignments.");
            assignments.likeLink.click();
            Assert.assertEquals(driver.findElement(By.className("ls-post-like-count")).getText(), "0", "Student should be able to unlike the assignments.");
            //Student should be able to post a comment.
            assignments.commentIcon.click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(".//div[@placeholder='Write your comment']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(".//div[@placeholder='Write your comment']")).sendKeys("Post comment : Test");
            //post-comment
            driver.findElement(By.className("post-comment")).click();
            Assert.assertEquals(driver.findElement(By.className("ls-stream-post-comment-count")).getText(), "1", "Student should be able to post a comment.");//change 1
            // Student should be able to like the Comments.
            driver.findElements(By.cssSelector("i[class='ls-icon-img ls--like-icon']")).get(1).click();
            Assert.assertEquals(driver.findElement(By.className("ls-comment-like-count")).getText(), "1", "Student should be able to like the Comments.");
            //Comment text should display with prefix of student last name and first name.
            Assert.assertEquals(driver.findElement(By.className("ls-comment-user-name")).getText(),"family, givenname","Comment text should display with prefix of student last name and first name.");
            Assert.assertEquals(driver.findElement(By.className("ls-stream-post__comment-text")).getText(), "family, givenname Post comment : Test", "Student should be able to Remove Comment.");
            //Student(comment posted) thumbnail should be displayed.
            Assert.assertTrue(driver.findElement(By.className("ls-media__img")).isDisplayed(), "Student(comment posted) thumbnail should be displayed.");
            //Comment posted duration should be displayed
            Assert.assertEquals(driver.findElements(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']")).get(1).getText(),"a second ago","Comment posted duration should be displayed");
            //Student should be able to Remove Comment.
            driver.findElement(By.cssSelector("div[class='ls-dropdown__toggle']")).click();
            driver.findElement(By.cssSelector("li[class='ls-hide-comment']")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector("li[class='ls-stream-post-comment ls-media']")).getText(), "This post has been removed.", "Student should be able to Remove Comment.");
            //Assigned time should be displayed for each assignment.
            Assert.assertTrue(driver.findElement(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']")).isDisplayed(),"Assigned time should be displayed for each assignment.");
            // "Due Date", "Your Status" and "Uncategorized" should be displayed with respective icons.
            Assert.assertEquals(driver.findElement(By.className("ls-assignment-not-due-date-title")).getText().trim(), "Due Date:", "Due Date is not displayed");
            Assert.assertTrue(driver.findElement(By.className("star-icon")).isDisplayed(), "Due date icon should be displayed");
            Assert.assertTrue(driver.findElement(By.className("ls-assignment-status")).isDisplayed(), "Your Status should be displayed");
            Assert.assertTrue(driver.findElement(By.className("category-weightage")).isDisplayed(), "Uncategorized should be displayed");
            //11. Click on Assignment Name
            assignments.assignmentName.click();
            Assert.assertTrue(driver.findElement(By.className("ls-question-navigator-list")).isDisplayed(), "Should be navigated to Question page.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC userViewAndAccessAllTheAssignedAssignment of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    @Test(priority = 16,enabled = true)
    public void verifyAssignmentTabOfTheeTextbook()
    {
        try
        {
            TocSearch tocSearch = PageFactory.initElements(driver,TocSearch.class);
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            Dashboard dashboard = PageFactory.initElements(driver,Dashboard.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);

            new LoginUsingLTI().ltiLogin("133");
            //The user should be successfully logged in to the application and the default landing page should be displayed
            Assert.assertTrue(dashboard.dashBoard.isDisplayed(), "The user is not successfully logged in to the application and the default landing page should be displayed");
            new Navigator().NavigateTo("Assignments"); // Navigate to Current Assignments
            String assignmentName1 = assignments.assignmentName.getAttribute("title");
            //13. Click on the "e-TextBook".
            new Navigator().NavigateTo("e-Textbook"); // Navigate to Current Assignments
            Assert.assertTrue(driver.findElement(By.className("chaptersList")).isDisplayed(),"e-TextBook/TOC page should open.");
            // 14. Click on "Close table of Content" cross icon.
            tocSearch.hideTOC.click();
            Assert.assertTrue(driver.findElement(By.cssSelector("div[class='lesson ebook']")).isDisplayed(), "Lesson page should be opened");
            Assert.assertTrue(lessonPage.getAssignmentsTab().isDisplayed(),"Assignments tab should be availabel.");
            //15. Click on Assignments tab.
            lessonPage.getAssignmentsTab().click();
            Assert.assertTrue(driver.findElement(By.className("assignment-content-posts-list")).isDisplayed(), "All the Assignments associated at chapter level and associated at specific section level should display under assignment tab.");
            // 16. Verify Assignments.
            String assignmentName2 = lessonPage.getAssignmentName().getAttribute("title");
            if(!assignmentName2.contains(assignmentName1))
            {
                Assert.fail("Latest assigned assignment should display on top.");
            }
            //"posted an assignment." message should display for each assignment with prefix of assignment instructor last name and first name.
            Assert.assertEquals(driver.findElement(By.cssSelector("a[class='ls-right-user-name ellipsis']")).getText(),"family, givenname","instructor last name and first name should be displayed");
            String postedAnAssignment = driver.findElement(By.className("ls-right-user-subhead")).getText();
            if(!postedAnAssignment.contains("posted an assignment"))
            {
                Assert.fail("\"posted an assignment.\" message should displayed");
            }
            //Instructor thumbnail should be displayed for each assignment.
            String thumbnail = driver.findElement(By.xpath(".//div[@class='ls-right-user-img']/img")).getAttribute("src");
            if(!thumbnail.contains("user-default-thumbnail.png"))
            {
                Assert.fail("Instructor thumbnail should be displayed for each assignment.");
            }
            //"Bookmark" should be availabel for each assignment.
            Assert.assertTrue(driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls--right-star-icon ls-assignment-bookmark']")).isDisplayed(),"\"Bookmark\" should be availabel for each assignment.");
            // Assignment icon should be available for each assignment.
            Assert.assertTrue(driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls-right-section-status-icon ls-right-assignment-homework']")).isDisplayed(),"Assignment icon should be available for each assignment.");
            // Assignment name with short label should be displayed.
            Assert.assertTrue(driver.findElement(By.className("ls_assessment_link")).isDisplayed(), "Assignment name with short label should be displayed.");
            // "Gradable" should be displayed for gradable assignments.
            Assert.assertTrue(driver.findElement(By.className("ls-side-gradaded-label")).isDisplayed(), "\"Gradable\" should be displayed for gradable assignments.");
            //"Like" and "Comments" symbols should be displayed for each assignment.
            Assert.assertTrue(driver.findElement(By.cssSelector("i[class='ls-icon-img ls--like-icon']")).isDisplayed(), "\"Like\"  symbols should be displayed for each assignment.");
            Assert.assertTrue(driver.findElement(By.cssSelector("i[class='ls-icon-img ls--comments-icon']")).isDisplayed(), "\"Comments\" symbols should be displayed for each assignment.");
            //Number of likes and comments count should be zero by default.
            Assert.assertEquals(driver.findElement(By.cssSelector("span[class='ls-right-post-like-count ls-post-like-count']")).getText(),"0","Number of likes count should be zero by default.");
            Assert.assertEquals(driver.findElement(By.cssSelector("span[class='ls-right-stream-post-comment-count']")).getText(), "0", "Number of comments count should be zero by default.");
            // "Due Date", "Status" should be displayed with respective icons.
            Assert.assertTrue(driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls-right-section-status-icon ls-right-assignment-duedate']")).isDisplayed(), "\"Due Date\" should be displayed with respective icons.");
            String assignmentStatus = driver.findElements(By.className("ls-right-section-status")).get(2).getText().trim();
            System.out.println("assignmentStatus::"+assignmentStatus);
            System.out.println("assignmentStatus::"+assignmentStatus);
            if(!assignmentStatus.contains("Status:"))
            {
                Assert.fail("\"Status\" should be displayed with respective icons.");
            }
            //"Due date" should be in red color.
            Assert.assertEquals(driver.findElement(By.cssSelector("div[class='ls-right-section-status ls-due-date-locale-formate']")).getCssValue("color"), "rgba(246, 75, 84, 1)");
            // 17. Click on Arrow Icon.
            lessonPage.assignmentArrow.get(0).click();
            Assert.assertTrue(lessonPage.assignmentArrowOpen.isDisplayed(), "\"Open\" button should be displayed.");
            lessonPage.assignmentArrowOpen.click();
            Assert.assertTrue(driver.findElement(By.className("ls-question-navigator-list")).isDisplayed(),"Student should be navigated to the question Page.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC verifyAssignmentTabOfTheeTextbook of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    @Test(priority = 17,enabled = true)
    public void verifyCourseStreamEntryForEachAssignment()
    {
        try
        {
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver,CourseStreamPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "198");

            new Assignment().create(198);
            new Assignment().addQuestions(198, "truefalse", "");
            new LoginUsingLTI().ltiLogin("198");
            new Navigator().NavigateTo("Question Banks");
            Thread.sleep(1000);
            new Assignment().assignToStudent(198);
            new Navigator().NavigateTo("Current Assignments");
            String assignmentName1 = currentAssignments.getList_assignmentName().get(1).getAttribute("title");
            String assignmentName2 = currentAssignments.getList_assignmentName().get(2).getAttribute("title");
            new LoginUsingLTI().ltiLogin("199");
            new Navigator().NavigateTo("Course Stream");
            //"Course Stream" page should be opened.
            Assert.assertTrue(driver.findElement(By.className("share-to-ls-body")).isDisplayed(), "\"Course Stream\" page should be opened.");
            //All the assignments(copy assignments) assigned to the destination class section should be available.
            // "Assignments should not display, incase ""Accessible After"" date is future date"
            boolean assignmentPresent1 = false;
            boolean assignmentPresent2 = false;
            boolean assignmentPresent3 = true;
            new ScrollElement().scrollBottomOfPage();
            List<WebElement> listOfAssignments = driver.findElements(By.className("ls-stream-assignment-title"));
            System.out.println("listOfAssignments.size()::"+listOfAssignments.size());
            for(int i = 0; i < listOfAssignments.size(); i++)
            {
                System.out.println("assignmentName1::" + assignmentName1);
                System.out.println("assignmentName2::" + assignmentName2);

                String assignmentNameInCS = listOfAssignments.get(i).getAttribute("title").trim();
                System.out.println("assignmentNameInCS::"+assignmentNameInCS);
                if(assignmentNameInCS.equals(assignmentName1))
                {
                    assignmentPresent1 = true;
                }
                if(assignmentNameInCS.equals(assignmentName2))
                {
                    assignmentPresent2 = true;
                }
                if(assignmentNameInCS.equals(assessmentname))
                {
                    assignmentPresent3 = false;
                }
                Thread.sleep(1000);
                System.out.println("assignmentPresent1::"+assignmentPresent1);
                System.out.println("assignmentPresent2::"+assignmentPresent2);
                System.out.println("assignmentPresent3::"+assignmentPresent3);
                if(assignmentPresent1 == true && assignmentPresent2 == true && assignmentPresent3 == false)
                {
                    System.out.println("break loop");
                    break;
                }
            }
            if(assignmentPresent1 == false || assignmentPresent2 == false || assignmentPresent2 == false )
            {
                 Assert.fail("All the assignments(copy assignments) assigned to the destination class section should be availabel.");
            }
            //All the assignments should display based on last update
            assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "200");

            new Assignment().create(200);
            new LoginUsingLTI().ltiLogin("200");
            new Assignment().assignToStudent(200);
            new LoginUsingLTI().ltiLogin("199");
            new Navigator().NavigateTo("Course Stream");
            //Number of likes and comments count should be zero by default.
            Assert.assertEquals(driver.findElement(By.className("ls-post-like-count")).getText(), "0", "Number of likes count should be zero by default.");
            Assert.assertEquals(driver.findElement(By.className("ls-stream-post-comment-count")).getText(), "0", "Number of comments count should be zero by default."); //change 0

            String recentAssignment = driver.findElement(By.className("ls-stream-assignment-title")).getAttribute("title").trim();
            Assert.assertEquals(recentAssignment, assessmentname, "All the assignments should display based on recent update");
            //"posted an assignment." message should display for each assignment with prefix of assignment instructor last name and first name.
            Assert.assertEquals(driver.findElement(By.className("ls-stream-post__username")).getText().trim(),"family, givenname","Assignment instructor last name and first name should be displayed");
            Assert.assertEquals(driver.findElement(By.className("ls-stream-post__action")).getText().trim(), "posted an assignment", "");
            //"Bookmark" should be available for each assignment.
            Assert.assertTrue(courseStreamPage.bookMark.get(0).isDisplayed(), "\"Bookmark\" should be available for each assignment.");
            // Student should be able to bookmark and unbookmark an assignment.
            int bookMarkBefore = courseStreamPage.bookMark.size();
            courseStreamPage.bookMark.get(0).click();
            int bookMarkAfter = courseStreamPage.bookMark.size();
            Assert.assertEquals(bookMarkAfter, bookMarkBefore - 1, "Student should be able to bookmark an assignment");
            int unbookMarkBefore = courseStreamPage.unbookMark.size();
            courseStreamPage.unbookMark.get(0).click();
            int unbookMarkAfter = courseStreamPage.unbookMark.size();
            Assert.assertEquals(unbookMarkAfter,unbookMarkBefore - 1,"Student should be able to bookmark an assignment");
            //Assignment icon should be availabel for each assignment.
            Assert.assertTrue(driver.findElement(By.cssSelector("i[class='ls-icon-img ls--assignment-icon']")).isDisplayed(),"Assignment icon should be availabel for each assignment.");
            //Assignment name should be displayed.
            Assert.assertTrue(courseStreamPage.assessmentName.isDisplayed(), "Assignment name should be displayed.");
            //"Like" and "Comments" links should be displayed for each assignment.
            Assert.assertTrue(courseStreamPage.likeLinkIn_CSPage.get(0).isDisplayed(),"\"Like\"  links should be displayed for each assignment.");
            Assert.assertTrue(courseStreamPage.commentLinkIn_CSPage.get(0).isDisplayed(),"\"Comments\" links should be displayed for each assignment.");
            // "Like" and "Comments" symbols should be displayed for each assignment.
            Assert.assertTrue(driver.findElement(By.cssSelector("i[class='ls-icon-img ls--like-icon']")).isDisplayed(),"\"Like\" symbols should be displayed for each assignment.");
            Assert.assertTrue(driver.findElement(By.cssSelector("i[class='ls-icon-img ls--comments-icon']")).isDisplayed(), "\"Comments\" symbols should be displayed for each assignment.");
            //Student should be able to like and unlike the assignments.
            int beforeLike = courseStreamPage.likeLinkIn_CSPage.size();
            courseStreamPage.likeLinkIn_CSPage.get(0).click();
            int afterLike = courseStreamPage.likeLinkIn_CSPage.size();
            Assert.assertEquals(afterLike, beforeLike - 1, "Student should be able to like the assignments.");
            driver.findElement(By.xpath("//a[@title='Unlike']")).click();
            int afterunLike = courseStreamPage.likeLinkIn_CSPage.size();
            Assert.assertEquals(afterunLike, beforeLike, "Student should be able to Unlike the assignments.");
            // Student should be able to post a comment.
            courseStreamPage.commentLinkIn_CSPage.get(0).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(".//div[@placeholder='Write your comment']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(".//div[@placeholder='Write your comment']")).sendKeys("Post comment In CS : Test");
            //post-comment
            driver.findElement(By.className("post-comment")).click();
            Assert.assertEquals(driver.findElement(By.className("ls-stream-post-comment-count")).getText(), "1", "Student should be able to post a comment.");
            // Student should be able to like the Comments.
            driver.findElement(By.xpath(".//li[@class='ls-stream-post-comment ls-media']//i")).click();
            Assert.assertEquals(driver.findElement(By.className("ls-comment-like-count")).getText(), "1", "Student should be able to like the Comments.");
            //Comment text should display with prefix of student last name and first name.
            Assert.assertEquals(driver.findElement(By.className("ls-comment-user-name")).getText(), "family, givenname", "Comment text should display with prefix of student last name and first name.");
            Assert.assertEquals(driver.findElement(By.className("ls-stream-post__comment-text")).getText(), "family, givenname Post comment In CS : Test", "Student should be able to Remove Comment.");
            //Student(comment posted) thumbnail should be displayed.
            Assert.assertTrue(driver.findElement(By.className("ls-media__img")).isDisplayed(), "Student(comment posted) thumbnail should be displayed.");
            //Comment posted duration should be displayed
            Assert.assertEquals(driver.findElements(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']")).get(1).getText(), "a second ago", "Comment posted duration should be displayed");
            //Student should be able to Remove Comment.
            driver.findElement(By.cssSelector("div[class='ls-dropdown__toggle']")).click();
            driver.findElement(By.cssSelector("li[class='ls-hide-comment']")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector("li[class='ls-stream-post-comment ls-media']")).getText(), "This post has been removed.", "Student should be able to Remove Comment.");
            //"Like" and "Comments" symbols should be displayed for each assignment.
            Assert.assertTrue(driver.findElement(By.cssSelector("i[class='ls-icon-img ls--like-icon']")).isDisplayed(), "\"Like\"  symbols should be displayed for each assignment.");
            Assert.assertTrue(driver.findElement(By.cssSelector("i[class='ls-icon-img ls--comments-icon']")).isDisplayed(), "\"Comments\" symbols should be displayed for each assignment.");
            //Assigned time should be displayed for each assignment.
            Assert.assertTrue(driver.findElement(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']")).isDisplayed(), "Assigned time should be displayed for each assignment.");
            // "Due Date", "Status" should be displayed with respective icons.
            Assert.assertTrue(driver.findElement(By.cssSelector("span[class='ls-stream-assignment-due-date']")).isDisplayed(), "\"Due Date\" should be displayed with respective icons.");
            //"Due date" should be in red color.
            Assert.assertEquals(driver.findElement(By.xpath("//span[@class='ls-stream-assignment-due-date']/span")).getCssValue("color"), "rgba(255, 0, 0, 1)");
            //Assignment name should be clickable.
            courseStreamPage.assessmentName.click();
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.className("ls-question-navigator-list")).isDisplayed(), "Student should be navigated to the question Page.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC verifyCourseStreamEntryForEachAssignment of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    @Test(priority = 18,enabled = true)
    public void assignmentEntriesInCourseStreamTileDashboard()
    {
        try
        {
            Dashboard dashboard = PageFactory.initElements(driver,Dashboard.class);
            new LoginUsingLTI().ltiLogin("199");
            new Navigator().NavigateTo("Dashboard");
            // "DashBoard" should be opened.
            Assert.assertTrue(dashboard.dashBoard.isDisplayed(), "The user is not successfully logged in to the application and the default landing page should be displayed");
            // "DashBoard" should be divided in four quadrants.
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ls-dashboard-container']//section[@id='course-details']")).isDisplayed(),"\"DashBoard\" should be divided in four quadrants.");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ls-dashboard-container']//section[@id='statistics']")).isDisplayed(),"\"DashBoard\" should be divided in four quadrants.");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ls-dashboard-container']//section[@class='grade-book']")).isDisplayed(),"\"DashBoard\" should be divided in four quadrants.");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ls-dashboard-container']//section[@id='course-stream']")).isDisplayed(), "\"DashBoard\" should be divided in four quadrants.");
            //Top header of quadrant 4 should be "Course Stream".
            Assert.assertEquals(driver.findElement(By.className("stream-title")).getText().trim(), "Course Stream", "Top header of quadrant 4 should be \"Course Stream\".");
            // "See All" link should be displayed.
            Assert.assertTrue(driver.findElement(By.className("course-button")).isDisplayed(), "\"See All\" link should be displayed.");
            //Recently posted activities should be displayed.
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='news news2']/div[@class='middle']")).getText().trim(),"IT241_static Assessment_200","Recently posted activities should be displayed.");
            //UI should not be overlapped.

            //Mouse hover on assignment should be highlighted the complete block with grey back ground color.

            //"Instructor posted an assignment." message should display for each assignment with prefix of assignment instructor last name and first name.
            Assert.assertEquals(driver.findElement(By.xpath("(//div[@class='news news2']/div[@class='top']/p)[1]")).getText().trim(),"family, givenname","Recently posted activities should be displayed.");
            Assert.assertEquals(driver.findElement(By.xpath("(//div[@class='news news2']/div[@class='top']/p)[2]")).getText().trim(),"Instructor","Recently posted activities should be displayed.");
            Assert.assertEquals(driver.findElement(By.xpath("(//div[@class='news news2']/div[@class='top']/p)[3]")).getText().trim(),"posted an assignment.","Recently posted activities should be displayed.");
            // Instructor thumbnail should be displayed for each assignment.
            String thumbnail = driver.findElement(By.xpath(".//div[@class='photo']/img")).getAttribute("src");
            if(!thumbnail.contains("default-profile-image.png"))
            {
                Assert.fail("Instructor thumbnail should be displayed for each assignment.");
            }
            // "Bookmark" should be availabel for each assignment.
            Assert.assertTrue(driver.findElement(By.className("course-stream-item-unbookmark")).isDisplayed(), "\"Bookmark\" should be availabel for each assignment.");
            //Assignment icon should be availabel for each assignment.
            Assert.assertTrue(driver.findElement(By.className("table")).isDisplayed(), "Assignment icon should be availabel for each assignment.");
            //Assignment name should be displayed.
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='news news2']/div[@class='middle']")).getText().trim(), "IT241_static Assessment_200", "Assignment name should be displayed.");
            // "Due Date" should be displayed for each assignment with respective icon.
            Assert.assertTrue(driver.findElement(By.className("calendar")).isDisplayed(), "\"Due Date\" should be displayed for each assignment with respective icon.");
            //"Due date" should be in red color.
            Assert.assertEquals(driver.findElement(By.xpath("(//div[@class='news news2']/div[@class='bottom']/p)[2]")).getCssValue("color"), "rgba(244, 86, 86, 1)", "\"Due date\" should be in red color.");
            // 26. Click on Assignment block.
            driver.findElement(By.cssSelector("div[class='news news2']")).click();
            Assert.assertTrue(driver.findElement(By.className("share-to-ls-body")).isDisplayed(), "\"Course Stream\" page should be opened.");
            // 27. Click on "See All" link.
            new Navigator().NavigateTo("Dashboard");
            driver.findElement(By.className("course-button")).click();
            Assert.assertTrue(driver.findElement(By.className("share-to-ls-body")).isDisplayed(), "\"Course Stream\" page should be opened.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC assignmentEntriesInCourseStreamTileDashboard of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

    @Test(priority = 19,enabled = true)
    public void viewAndAccessToAllTheAssignedAssignments()
    {
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            CopyAllAssignments copyAllAssignments = PageFactory.initElements(driver,CopyAllAssignments.class);
            TocSearch tocSearch = PageFactory.initElements(driver,TocSearch.class);
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            String assessmentname1 = ReadTestData.readDataByTagName("", "assessmentname", "246");
            String assessmentname2 = ReadTestData.readDataByTagName("", "assessmentname", "247");
            String context_title1 = ReadTestData.readDataByTagName("", "context_title", "246");
            String context_title2 = ReadTestData.readDataByTagName("", "context_title", "247");

            new Assignment().create(246);
            new Assignment().create(247);
            new LoginUsingLTI().ltiLogin("247");
            new LoginUsingLTI().ltiLogin("246");
            new Assignment().assignToStudent(246);
            new Assignment().assignToStudent(248);
            new Navigator().NavigateTo("Current Assignments");
            String assessmentNameInCA1 = currentAssignments.getList_assignmentName().get(0).getText().trim();
            String assessmentNameInCA2 = currentAssignments.getList_assignmentName().get(1).getText().trim();
            Assert.assertEquals(assessmentNameInCA1,assessmentname1,"");
            Assert.assertEquals(assessmentNameInCA2, assessmentname2, "");
            currentAssignments.copyAssignments.click();
            copyAllAssignments.sourceClassSectionDropDown.get(1).click();
            List<WebElement> sourceClassSections = copyAllAssignments.getSourceClassSectionNames;
            for(int i = 0; i < sourceClassSections.size(); i++)
            {
                String csName = sourceClassSections.get(i).getAttribute("title").trim();
                if(csName.equals(context_title1))
                {
                    copyAllAssignments.getSourceClassSectionNames.get(i).click();
                    break;
                }
            }
            copyAllAssignments.destinationClassSectionDropDown.get(1).click();
            List<WebElement> destinationClassSections = copyAllAssignments.getDestinationClassSectionNames;
            for(int a = 0; a < destinationClassSections.size(); a++)
            {
                String csDesName = destinationClassSections.get(a).getAttribute("title").trim();
                if(csDesName.equals(context_title2))
                {
                    copyAllAssignments.getDestinationClassSectionNames.get(a).click();
                    break;
                }
            }
            copyAllAssignments.nextButtonInStep1.click();
            selectDateInSameMonth("5");
            Thread.sleep(1000);
            copyAllAssignments.assignAssignments.click();
            // Navigate to the Current Assignment page of the destination class section.
            new LoginUsingLTI().ltiLogin("247");
            new Navigator().NavigateTo("Current Assignments");
            String assessmentNameInCA3 = currentAssignments.getList_assignmentName().get(0).getText().trim();
            String assessmentNameInCA4= currentAssignments.getList_assignmentName().get(1).getText().trim();
            Assert.assertEquals(assessmentNameInCA3, assessmentname1, "All the assignment should be shown.");
            Assert.assertEquals(assessmentNameInCA4, assessmentname2, "All the assignment should be shown.");
            // Verify for the gradable assignment
            Assert.assertTrue(currentAssignments.gradable_icon.isDisplayed(), "Verify for the gradable assignment");
            // Assignment policy associated should be shown.
            Assert.assertEquals(driver.findElement(By.className("assignment-policy-name")).getText().trim(), "ByDefault Policy Name", "Assignment policy associated should be shown.");
            // Verify the description of the assignment.
            Assert.assertEquals(driver.findElement(By.className("assignment-additional-note")).getText().trim(),"This is an additional note","Verify the description of the assignment.");
            // Verify the Assignment Reference.

            //Navigate to the Dashboard and verify the Assignment tile.
            new Navigator().NavigateTo("Dashboard");
            //Count should be shown for the "Scheduled assignments" for the destination class section.
            Assert.assertEquals(driver.findElements(By.className("ls-assignment-progress-count")).get(0).getText().trim(),"0","Count should be shown for the \"Scheduled assignments\" for the destination class section.");
            //Count should be shown for the "Assignments Availabel for students" for the destination class section.
            Assert.assertEquals(driver.findElements(By.className("ls-assignment-progress-count")).get(1).getText().trim(), "2", "Count should be shown for the \"Assignments Availabel for students\" for the destination class section.");
            //Recently assigned assignment should be displayed.
            //Recently assigned assignment should be displayed.
            Assert.assertEquals(driver.findElements(By.xpath(".//div[@class='middle']/p")).get(0).getText().trim(), "IT241_static Assessment_247", "Recently assigned assignment should be displayed.");
            // Recently assigned 3 assignment should be displayed.
            Assert.assertEquals(driver.findElements(By.xpath(".//div[@class='middle']/p")).get(1).getText().trim(), "IT241_static Assessment_246", "Recently assigned assignment should be displayed.");
            //Name of the assignment should be displayed.
            Assert.assertTrue(driver.findElement(By.xpath(".//div[@class='middle']/p")).isDisplayed(), "Name of the assignment should be displayed.");
            //Due Date of the assignment should be displayed.
            Assert.assertEquals(driver.findElements(By.xpath(".//div[@class='bottom']/p")).get(0).getText().trim(), "Due Date:", "Due Date of the assignment should be displayed.");
            //Navigate to the e-Textbook
            new Navigator().NavigateTo("eTextBook");
            //User should be navigated to the e-Textbook
            Assert.assertTrue(tocSearch.hideTOC.isDisplayed(), "User should be navigated to the e-Textbook");
            //Verify the chapter from which assignment is associated.
            //The assignmet should be displayed in the post chapter section.
            Assert.assertEquals(tocSearch.postChapterAssignments.get(0).getAttribute("title").trim(),assessmentname1,"The assignmet should be displayed in the post chapter section.");
            Assert.assertEquals(tocSearch.postChapterAssignments.get(1).getAttribute("title").trim(),assessmentname2,"The assignmet should be displayed in the post chapter section.");
            // Due Date of the assignment should be displayed.
            String dueDate1 = driver.findElements(By.xpath(".//ul[@class='assignment-card']//span[@class='ls-toc-due-date']")).get(0).getText().trim();
            String dueDate2 = driver.findElements(By.xpath(".//ul[@class='assignment-card']//span[@class='ls-toc-due-date']")).get(1).getText().trim();
            if(!dueDate1.contains("(Due Date:") || !dueDate2.contains("(Due Date:"))
            {
                Assert.fail("Due Date of the assignment should be displayed.");
            }
            // Navigate to the lesson page.
            tocSearch.hideTOC.click();
            // User should be navigated to the Lesson
            Assert.assertTrue(driver.findElement(By.cssSelector("div[class='lesson ebook']")).isDisplayed(), "Lesson page should be opened");
            //Navigate to the Assignment Tab.
            Assert.assertTrue(lessonPage.getAssignmentsTab().isDisplayed(), "Assignments tab should be availabel.");
            // Navigate to the Assignment Tab.
            lessonPage.getAssignmentsTab().click();
            // Assignment entry should be displayed.
            Assert.assertTrue(driver.findElement(By.className("assignment-content-posts-list")).isDisplayed(), "Assignment entry should be displayed.");
            // "Gradable" label should be shown for gradable assignment.
            Assert.assertTrue(driver.findElement(By.className("ls-side-gradaded-label")).isDisplayed(),"\"Gradable\" label should be shown for gradable assignment.");
            // Due Date of the assignment should be displayed.
            Assert.assertTrue(driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls-right-section-status-icon ls-right-assignment-duedate']")).isDisplayed(),"Due Date of the assignment should be displayed.");
            // Class Status should be displayed.
            Assert.assertTrue(driver.findElement(By.className("ls-right-section-status")).isDisplayed(), "Class Status should be displayed.");
            // Navigate to the Course stream and verify the Assignment entry.
            new Navigator().NavigateTo("Course Stream");
            //All the assignment entery should be shown.
            boolean assignmentPresent1 = false;
            boolean assignmentPresent2 = false;
            List<WebElement> assignmentsInCS = driver.findElements(By.className("ls-stream-assignment-title"));
            for(int r = 0; r < assignmentsInCS.size(); r++)
            {
                String assignmentInCS = assignmentsInCS.get(r).getAttribute("title").trim();
                if(assignmentInCS.equals(assessmentname1))
                {
                    assignmentPresent1 = true;
                }
                if(assignmentInCS.equals(assessmentname2))
                {
                    assignmentPresent2 = true;
                }
                if(assignmentPresent1 == true && assignmentPresent2 == true)
                {
                    System.out.println("Assignments are present");
                    break;
                }
            }
            if(assignmentPresent1 == false || assignmentPresent2 == false)
            {
                Assert.fail("All the assignment entery should be shown.");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC viewAndAccessToAllTheAssignedAssignments of class copyAssignmentsFromOneClassSectionToAnotherClassSection", e);
        }
    }

}
