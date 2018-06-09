package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.copyquestions;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CreateCourse;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.SearchPage;
import com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.LTILogin;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.management.ThreadMXBean;
import java.util.List;

/**
 * Created by Dharaneesha on 3/22/16.
 */
public class CopyQuestions extends Driver{
    @Test(priority = 1,enabled = true)
    public void copyQuestionsToSameCourse(){
        try{
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Copy Questions","Copying question from source to destination course of same type","info");
            int dataIndex = 3;
            CreateCourse createCourse = PageFactory.initElements(driver, CreateCourse.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            /*Row No - 3 - "1. Search for a Question
            2. Select a Question
            3. Click on 'Copy' button"*/
            //Expected - 1. A text "Copy Selected Questions To" should be displayed in the window
            //new Assignment().create(dataIndex);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.icon_search,60);
            new CMSActions().searchQuestion(questionText);
            searchPage.checkBox.click();//Click on Check box
            searchPage.button_copy.click();//Click on 'Copy' button
            CustomAssert.assertEquals(searchPage.label_copySelectedQuestiosTo.getText().trim(),"Copy Selected Questions To", "Validating the label in Copy Window", "Label 'Copy Selected Questions To' is validated","Label 'Copy Selected Questions To' is not displayed in the window");

            /*Row No - 4 : "4. Click on'Select Course' Dropdown
            5. Select 'Biology' Course
            6. Click on 'Select an option' Dropdown & select any chapter"*/
            searchPage.link_selectACourse.click();//Click on Select a Course link
            driver.findElement(By.linkText("Biology")).click();//Click on 'Biology' option
            WebDriverUtil.clickOnElementUsingJavascript(searchPage.link_selectOption);
            searchPage.link_selectOption.click();//Click on 'Select Option' dropdown
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.partialLinkText("Ch 1:")));
            driver.findElement(By.partialLinkText("Ch 1:")).click();
            //Expected - 1. 'Copy as reference only' should be left unchecked
            CustomAssert.assertEquals(searchPage.checkBox_copyAsReferenceOnly.getAttribute("disabled").trim(), "true", "Validating 'Copy as reference only' checkbox", "'Copy as reference only' is unchecked", "'Copy as reference only' Checkbox is checked");

            //2. 'Use LO Mapping' should be left unchecked
            CustomAssert.assertEquals(searchPage.checkBox_useLOMapping.getAttribute("disabled").trim(),"true","Validating 'Use LO Mapping' checkbox", "'Use LO Mapping' is unchecked","'Use LO Mapping' Checkbox is checked");


            /*7. Select any test
            8. Click on 'Done' button*/
            //Expected -  A Robonotification with the text "1 Questions have been copied." should be displayed
            new Click().clickByid("206478");
            searchPage.copyButtonDone.click();//Click on 'Copy' button
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.copyQuestionNotification,60);
            CustomAssert.assertEquals(searchPage.copyQuestionNotification.getText(),"1 Questions have been copied.","Validating Notification","Notification message '1 Questions have been copied.' is displayed","Notification message '1 Questions have been copied.' is not displayed");



            //9. Navigate to the same assessment in the chapter
            //Expected - . Copied Question should be available in the assessment"
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(manageContent.manageContent);
            createCourse.chapterName.click();//Click on Chapter name
            new Click().clickByid("206478");
            int questionNo=driver.findElements(By.xpath("(//div[@class='overview'])[2]/li/a")).size();
            new CMSActions().navigateToQuestionNo(questionNo);
            CustomAssert.assertTrue(manageContent.questionContent_area.getText().trim().contains(questionText), "Check the moved question in to assessment", "Question is  moved to the particular assessment", "Question is not moved to the particular assessment");


        }catch (Exception e){
            Assert.fail("Exception in the test method 'copyQuestionsToSameCourse' in the class", e);
        }
    }


    @Test(priority = 2,enabled = true)
    public void copyQuestionsToDifferentCourse(){
        try{
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Copy Questions","Copying question from source to destination course of different type","info");
            int dataIndex = 11;
            CreateCourse createCourse = PageFactory.initElements(driver, CreateCourse.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            /*Row No - 3 - "1. Search for a Question
            2. Select a Question
            3. Click on 'Copy' button"*/
            //Expected - 1. A text "Copy Selected Questions To" should be displayed in the window
            new Assignment().create(dataIndex);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.icon_search,60);
            new CMSActions().searchQuestion(questionText);
            new MouseHover().mouserhoverbywebelement(driver.findElement(By.id("question-check-box-div")));
            Thread.sleep(5000);
            int count = 0;
            List<WebElement> checkBoxes = driver.findElements(By.xpath("//div[@id = 'question-check-box-div']//label"));
            for(int a=0;a<checkBoxes.size();a++){
                checkBoxes = driver.findElements(By.id("question-check-box-div"));
                Thread.sleep(1000);
                WebElement element = checkBoxes.get(a);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                Thread.sleep(1000);
                count++;
            }
            checkBoxes = driver.findElements(By.xpath("//div[@id = 'question-check-box-div']//label"));
            checkBoxes.get(count-1).click();
            searchPage.button_copy.click();//Click on 'Copy' button
            CustomAssert.assertEquals(searchPage.label_copySelectedQuestiosTo.getText().trim(),"Copy Selected Questions To", "Validating the label in Copy Window", "Label 'Copy Selected Questions To' is validated","Label 'Copy Selected Questions To' is not displayed in the window");

            /*Row No - 4 : "4. Click on'Select Course' Dropdown
            5. Select 'Biology' Course
            6. Click on 'Select an option' Dropdown & select any chapter"*/
            searchPage.link_selectACourse.click();//Click on Select a Course link
            driver.findElement(By.linkText("Visualizing Human Geography: At Home in a Diverse World")).click();
            for (int i = 0; i <5 ; i++) {
                try {
                    searchPage.link_selectOption.click();
                    break;
                } catch (Exception e) {
                }

            }
            driver.findElement(By.partialLinkText("Ch")).click();
            //Expected - 1. 'Copy as reference only' should be left checked
            CustomAssert.assertEquals(searchPage.checkBox_copyAsReferenceOnly.getAttribute("class").trim(), "checked", "Validating 'Copy as reference only' checkbox", "'Copy as reference only' is unchecked", "'Copy as reference only' Checkbox is checked");

            //2. 'Use LO Mapping' should be left unchecked
            searchPage.checkBox_useLOMapping.click();
            CustomAssert.assertEquals(searchPage.checkBox_useLOMapping.getAttribute("class").trim(),"checked","Validating 'Use LO Mapping' checkbox", "'Use LO Mapping' is unchecked","'Use LO Mapping' Checkbox is checked");



            /*7. Select any test
            8. Click on 'Done' button*/
            //Expected -  A Robonotification with the text "1 Questions have been copied." should be displayed
            new Click().clickByid("206669");
            searchPage.copyButtonDone.click();//Click on 'Copy' button
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.copyQuestionNotification,60);
            CustomAssert.assertEquals(searchPage.copyQuestionNotification.getText(),"1 Questions have been copied.","Validating Notification","Notification message '1 Questions have been copied.' is displayed","Notification message '1 Questions have been copied.' is not displayed");


            //9. Navigate to the same assessment in the chapter
            //Expected - . Copied Question should be available in the assessment"
            WebDriverUtil.clickOnElementUsingJavascript(manageContent.manageContent);
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(manageContent.change_link);
            Thread.sleep(9000);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("//span[@class='border-two']/img")));
            //9. Navigate to the same assessment in the chapter
            //"2.Moved Question should be available in the assessment"
            Thread.sleep(9000);
            createCourse.chapterName.click();//Click on Chapter name
            new Click().clickByid("206669");
            int questionNo=driver.findElements(By.xpath("(//div[@class='overview'])[2]/li/a")).size();
            new CMSActions().navigateToQuestionNo(questionNo);
            CustomAssert.assertTrue(manageContent.questionContent_area.getText().trim().contains(questionText), "Check the copied question in different course", "Question is  copied to the different course assessment", "Question is not copied to the different course assessment");


        }catch (Exception e){
            Assert.fail("Exception in the test method 'copyQuestionsToSameCourse' in the class", e);
        }
    }


    @Test(priority = 3,enabled = true)
    public void copyQuestionsToDifferentCourseThroughUseLoMapping(){
        try{
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Copy Questions","Copying question from source to destination course of different type through Use LO Mapping","info");
            int dataIndex = 11;

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

           /* 1.Search for a Question
            2. Select a Question
            3. Click on 'Copy' button
            4. Click on'Select Course' Dropdown
            5. Select any LS (Other than Biology) Course
            6. Click on 'Select an option' Dropdown & select any chapter"*/

            //Expected - 1. 'Copy as reference only' should be unchecked by default

            new Assignment().create(dataIndex);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.icon_search,60);

            new CMSActions().searchQuestion(questionText);
            searchPage.checkBox.click();
            searchPage.button_copy.click();
            searchPage.link_selectACourse.click();
            driver.findElement(By.linkText("Visualizing Human Geography: At Home in a Diverse World")).click();
            Thread.sleep(5000);
            searchPage.link_selectOption.click();
            Thread.sleep(3000);
            driver.findElement(By.partialLinkText("Ch")).click();
            Thread.sleep(3000);
            searchPage.checkBox_useLOMapping.click();
            Thread.sleep(2000);
            CustomAssert.assertEquals(searchPage.checkBox_useLOMapping.getAttribute("class").trim(),"checked","Validating 'Use LO Mapping' checkbox", "'Use LO Mapping' is unchecked","'Use LO Mapping' Checkbox is checked");
            Thread.sleep(2000);

            /*"7. Check 'Use LO Mapping'
            8. Click on link '(Manage Mapping)'"*/
            //Expected - 1. 'LO Mapping Table' should be displayed
            Thread.sleep(3000);
            System.out.println("12345");
            searchPage.link_manageMapping.click();
            Thread.sleep(3000);
            Assert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "# It should display the Header as 'LO Mapping Table'");
            CustomAssert.assertEquals(searchPage.label_LoMappingTable.getText(), "LO Mapping Table", "Validating '(Use LO Mapping)","The Header as 'LO Mapping Table is displayed","The Header as 'LO Mapping Table' is not displayed");

            //Expected - 2. Under Source Course Section, Source course should be selected by default
            String TLOText = new Select(driver.findElement(By.className("select-box-mapping"))).getFirstSelectedOption().getAttribute("title");
            CustomAssert.assertEquals(TLOText.trim(), "Select an option", "Validating '(Use LO Mapping)", "TLOs is erased", "TLOs is not erased");



            //Expected - 3. Under Destination Course Section, Destination course should be selected by default


            //9. Select any TLOs for both source & destination course
            //Expected - Author should be able to select TLOs in both Source & Destination Section
            selectTLOs();



            //10. Click on '+ Add row ' link
            //Expected - Author should be able to select TLOs in both Source & Destination Section
            searchPage.link_addRow.click();
            selectTLOs();


            //11. Click on 'Remove' link
            //Author should be able to remove TLOs in both Source & Destination Section
            searchPage.link_remove.click();
            Thread.sleep(3000);
            if(driver.findElements(By.linkText("- Remove")).size()!=0){
                CustomAssert.fail("Validating '(Use LO Mapping)'","Author should be able to remove TLOs in both Source & Destination Section");
            }


            //12. Click on 'Done' button
            searchPage.button_done.click();

            /*"13 Click on link '(Manage Mapping)'
            14. Click on 'Clear all' button"*/
            //Expected  - A text "All the LO mapping would be erased. Do you want to continue?" should be displayed
            searchPage = PageFactory.initElements(driver, SearchPage.class);
            Thread.sleep(2000);
            searchPage.checkBox_useLOMapping.click();
            Thread.sleep(2000);
            searchPage.link_manageMapping.click();
            searchPage.link_clearAll.click();
            CustomAssert.assertEquals(searchPage.clearAllMessage.getText().trim(), "All the LO mapping would be erased. Do you want to continue? Yes | No", "Validating '(Use LO Mapping)", "A text message \"All the LO mapping would be erased. Do you want to continue?\" is displayed", "A text message \"All the LO mapping would be erased. Do you want to continue?\" is not displayed");


            //15. Click on 'No' link
            //Expected - TLOs should  not be erased
            //driver.findElement(By.className("select-box-mapping")).
            searchPage.link_no.click();
            Thread.sleep(5000);
            TLOText = new Select(driver.findElement(By.className("select-box-mapping"))).getFirstSelectedOption().getAttribute("title");
            System.out.println("TLOText : " + TLOText);
            CustomAssert.assertEquals(TLOText.trim(), "1.2 Explain the five main concepts that form the basis of geographical inquiry.", "Validating '(Use LO Mapping)", "TLOs is not erased", "TLOs is erased");


            //16. Click on 'Clear all' button & Click on 'Yes' link
            //Expected - TLOs should  be erased in both Source & Destination Course
            searchPage.link_clearAll.click();
            searchPage.link_yes.click();
            searchPage = PageFactory.initElements(driver, SearchPage.class);
            Thread.sleep(2000);
            searchPage.checkBox_useLOMapping.click();
            Thread.sleep(2000);
            searchPage.link_manageMapping.click();

            TLOText = new Select(driver.findElement(By.className("select-box-mapping"))).getFirstSelectedOption().getAttribute("title");
            System.out.println("TLOText : " + TLOText);
            CustomAssert.assertEquals(TLOText.trim(), "Select an option", "Validating '(Use LO Mapping)", "TLOs is erased", "TLOs is not erased");


        }catch (Exception e){
            Assert.fail("Exception in the test method 'copyQuestionsToDifferentCourse' in the class", e);
        }
    }



    @Test(priority = 4,enabled = true)
    public void MoveQuestionsToLSADAPPublished(){
        WebDriver driver=Driver.getWebDriver();
        try{
            ReportUtil.log("Move Questions","Moving question that are published","info");
            int dataIndex = 32;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            new Assignment().create(dataIndex);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
            new UIElement().waitAndFindElement(searchPage.icon_search);
            new CMSActions().searchQuestion(questionText);
            searchPage.checkBox.click();
            searchPage.button_move.click();
            Thread.sleep(2000);
            WebDriverUtil.waitTillVisibilityOfElement(manageContent.notificationTitle,50);
            CustomAssert.assertEquals(manageContent.notificationTitle.getText().trim(),"You cannot Move or Delete Questions that are Published or Deactivated - Marked in Red", "Validating Robo popup", "Robo popup 'You cannot Move or Delete Questions that are Published or Deactivated - Marked in Red' is displayed","Robo popup 'You cannot Move or Delete Questions that are Published or Deactivated - Marked in Red' is not displayed");

        }catch (Exception e){
            Assert.fail("Exception in the test method 'MoveQuestionsToLSADAP' in the class 'CopyQuestions'", e);
        }
    }



    @Test(priority = 5,enabled = true)
    public void MoveQuestionsToLSADAPDraft(){
        WebDriver driver=Driver.getWebDriver();
        try{
            ReportUtil.log("Move Questions","Moving question from source to destination course of same type","info");
            int dataIndex = 33;
            CreateCourse createCourse = PageFactory.initElements(driver, CreateCourse.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            new Assignment().create(dataIndex);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
            new UIElement().waitAndFindElement(searchPage.icon_search);
            searchPage.icon_search.click();
            new CMSActions().searchQuestion(questionText);
            searchPage.checkBox.click();
            searchPage.button_move.click();
            Thread.sleep(2000);
            WebDriverUtil.waitTillVisibilityOfElement(searchPage.label_copySelectedQuestiosTo, 50);
            CustomAssert.assertEquals(searchPage.label_copySelectedQuestiosTo.getText().trim(), "Move Selected Questions To", "Validating the label in Copy Window", "Label 'Copy Selected Questions To' is validated", "Label 'Copy Selected Questions To' is not displayed in the window");

            searchPage.link_selectACourse.click();
            driver.findElement(By.linkText("Biology")).click();
            for (int i = 0; i <5 ; i++) {
                try {
                    searchPage.link_selectOption.click();
                    break;
                } catch (Exception e) {
                }

            }
            driver.findElement(By.partialLinkText("Ch 1:")).click();


            //1. 'Copy as reference only' should be left unchecked
            //2. 'Use LO Mapping' should be left unchecked


            new Click().clickByid("206478");
            WebDriverUtil.waitTillVisibilityOfElement(searchPage.copyButtonDone, 50);
            WebDriverUtil.clickOnElementUsingJavascript(searchPage.copyButtonDone);
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.copyQuestionNotification,60);
            CustomAssert.assertEquals(searchPage.copyQuestionNotification.getText(),"1 Questions have been moved.","Validating Notification","Notification message '1 Questions have been copied.' is displayed","Notification message '1 Questions have been copied.' is not displayed");



            //9. Navigate to the same assessment in the chapter
            //"2. Moved Question should be available in the assessment"
            Thread.sleep(9000);
            createCourse.manageContent_Tab.click();//Click on 'Manage Content' Tab
            Thread.sleep(5000);
            createCourse.chapterName.click();//Click on Chapter name
            Thread.sleep(3000);
            new Click().clickByid("206478");
            int questionNo=driver.findElements(By.xpath("(//div[@class='overview'])[2]/li/a")).size();
            System.out.println(questionNo);
            new CMSActions().navigateToQuestionNo(questionNo);
            CustomAssert.assertTrue(manageContent.questionContent_area.getText().trim().contains(questionText), "Check the moved question in to assessment", "Question is  moved to the particular assessment", "Question is not moved to the particular assessment");

        }catch (Exception e){
            Assert.fail("Exception in the test method 'MoveQuestionsToLSADAP' in the class 'CopyQuestions'", e);
        }
    }


    @Test(priority = 6,enabled = true)
    public void MoveQuestionsToLS(){
        WebDriver driver=Driver.getWebDriver();
        try{

            ReportUtil.log("Move Questions","Moving question from source to destination course of different type","info");
            int dataIndex = 34;
            CreateCourse createCourse = PageFactory.initElements(driver, CreateCourse.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            new Assignment().create(dataIndex);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
            new UIElement().waitAndFindElement(searchPage.icon_search);
            searchPage.icon_search.click();
            new CMSActions().searchQuestion(questionText);
            searchPage.checkBox.click();
            searchPage.button_move.click();
            CustomAssert.assertEquals(searchPage.label_copySelectedQuestiosTo.getText().trim(),"Move Selected Questions To", "Validating the label in Copy Window", "Label 'Copy Selected Questions To' is validated","Label 'Copy Selected Questions To' is not displayed in the window");

            searchPage.link_selectACourse.click();
            driver.findElement(By.linkText("Geography: Realms, Regions, and Concepts - Sixteenth Edition")).click();
            Thread.sleep(3000);
            for (int i = 0; i <5 ; i++) {
                try {
                    searchPage.link_selectOption.click();
                    break;
                } catch (Exception e) {
                }

            }
            driver.findElement(By.partialLinkText("Ch")).click();


            //1. 'Copy as reference only' should be left unchecked
            //2. 'Use LO Mapping' should be left unchecked


            new Click().clickByid("206521");
            searchPage.copyButtonDone.click();
            new WebDriverUtil().waitTillVisibilityOfElement(searchPage.copyQuestionNotification,60);

            CustomAssert.assertEquals(searchPage.copyQuestionNotification.getText(), "1 Questions have been moved.", "Validating Notification", "Notification message '1 Questions have been copied.' is displayed", "Notification message '1 Questions have been copied.' is not displayed");
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(manageContent.manageContent);
            manageContent.change_link.click(); //click on the change link
            new SelectCourse().selectLsCourse();
            //9. Navigate to the same assessment in the chapter
            //"2. Moved Question should be available in the assessment"
            Thread.sleep(9000);
            createCourse.chapterName.click();//Click on Chapter name
            new Click().clickByid("206521");
            int questionNo=driver.findElements(By.xpath("(//div[@class='overview'])[2]/li/a")).size();
            new CMSActions().navigateToQuestionNo(questionNo);
            CustomAssert.assertTrue(manageContent.questionContent_area.getText().trim().contains(questionText), "Check the moved question in to assessment", "Question is  moved to the particular assessment", "Question is not moved to the particular assessment");


        }catch (Exception e){
            Assert.fail("Exception in the test method 'MoveQuestionsToLSADAP' in the class 'CopyQuestions'", e);
        }
    }

    public void selectTLOs(){
        WebDriver driver=Driver.getWebDriver();
        try{
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
            CustomAssert.fail("Validating TLOs","Author is not able to select TLOs in both Source & Destination Section");
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
