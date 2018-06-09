package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class Navigator extends Driver
{

    public void NavigateTo(String navigateTo)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new UIElement().waitAndFindElement(By.xpath(".//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon

            if(navigateTo.equals("My Journal")) navigateTo = "My Notes";
            if(navigateTo.equals("Learning Content")) navigateTo = "e-Textbook";
            if(navigateTo.equals("eTextbook")) navigateTo = "e-Textbook";
            if(navigateTo.equals("eTextBook")) navigateTo = "e-Textbook";
            if(navigateTo.equals("Groups")) navigateTo = "Groups";

            if(navigateTo.equals("Current Assignments")  || navigateTo.equals("My Question Bank") || navigateTo.equals("Policies") || navigateTo.equals("Summary") || navigateTo.equals("New Assignment")) {
                new UIElement().waitAndFindElement(By.cssSelector("span[data-localize= 'Assignments']"));
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//span[@data-localize='Assignments']")));//click on Assignments
                Thread.sleep(2000);
                if(navigateTo.equals("Summary")){
                    navigateTo = "Class Assignments";
                }
            }
            boolean questionBank = false;
            if(navigateTo.equals("Question Banks")){
                //driver.findElement(By.linkText("Assignments")).click();//click on Assignments
                //new Click().clickbylinkText("Assignments");
                WebElement we=driver.findElement(By.linkText("Assignments"));
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
                navigateTo = "My Question Bank";
                questionBank = true;

            }

            if(navigateTo.equals("Assignment Policies"))
            {
                driver.findElement(By.linkText("Assignments")).click();//click on Assignments
                navigateTo = "Policies";
            }

            if(navigateTo.equals("Assignments")) {
                if (driver.getCurrentUrl().contains("learningSpaceInstructorDashboard")) {
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//span[@data-localize='Assignments']")));//click on Assignments
                    navigateTo = "Class Assignments";
                }
            }

            if(navigateTo.equals("Proficiency Report") || navigateTo.equals("Metacognitive Report")
                    || navigateTo.equals("Productivity Report") || navigateTo.equals("Most Challenging Activities Report") || navigateTo.equals("Engagement Report")
                    || navigateTo.equals("Performance Report") || navigateTo.equals("Most Challenging Chapters Report")){
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.linkText("My Reports")));//click on My Reports
            }

            if(navigateTo.equals("All Resources") || navigateTo.equals("My Resources")){
                driver.findElement(By.linkText("Resources")).click();//click on Resources
            }

            if(navigateTo.equals("Resources")){
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.linkText("Resources")));
                navigateTo = "All Resources";
            }

            if(navigateTo.equals("Current Assignments")){
                navigateTo = "Class Assignments";
            }

            if(navigateTo.equals("My Question Bank")){
                navigateTo = "My Assignments";
            }

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.cssSelector("span[data-localize='"+navigateTo+"']")));

            Thread.sleep(3000);
            if(navigateTo.equals("e-Textbook"))
            {
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));

            }
            if(questionBank){
                new Click().clickBycssselector("div[data-id='all-resources']");//click on Question Banks tab
            }
            if(navigateTo.equals("Learning Content"))
            {
                int helppage = driver.findElements(By.className("close-help-page")).size();
                if(helppage == 1)
                    driver.findElement(By.className("close-help-page")).click();
            }
            if(navigateTo.equals("Instructor Resources"))
            {
                if(driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources"))
                {//Opening All Resources tab if not opened after clicking on New Assignment button
                    driver.findElement(By.cssSelector("span[title='All Resources']")).click();
                }
            }


        }
        catch(Exception e)
        {
            Assert.fail("Exception in NavigateTo in AppHelper Navigator",e);
        }
    }

    public void InstructorNavigateTo(String navigateTo)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String streamvalue=driver.findElement(By.xpath("/html/body/header/div/div/div/ul/li/a")).getText();

            if(!streamvalue.trim().equals(navigateTo))
            {

                Actions action = new Actions(driver);
                WebElement we = driver.findElement(By.xpath("/html/body/header/div/div/div/ul/li/a"));
                action.moveToElement(we).build().perform();
                driver.findElement(By.linkText(navigateTo)).click();
                if(navigateTo.equals("eTextbook"))
                {
                    int helppage = driver.findElements(By.className("close-help-page")).size();
                    if(helppage == 1)
                        driver.findElement(By.className("close-help-page")).click();
                }
                Thread.sleep(5000);

            }
            else
            {

            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in InstructorNavigateTo in AppHelper class Navigator", e);
        }
    }
    public void navigateToResourceTab()
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[title='Resources']")));
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in navigateToResourceTab in AppHelper class Navigator", e);
        }
    }

    public void navigateToAssignmentsTab()
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[title='Assignments']")));
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in navigateToAssignmentsTab in AppHelper class Navigator", e);
        }
    }

    public void openResourceFromResourceTab(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
            System.out.println("resoursename: "+resoursename);
            navigateToResourceTab();
          /*  String appendChar=null;
            if(System.getProperty("UCHAR")==null)
            {
                appendChar=LoginUsingLTI.appendChar;
            }
            else {
                appendChar=System.getProperty("UCHAR");
            }*/
            String actualResources= resoursename;
            WebElement element=driver.findElement(By.xpath("//a[text()='"+actualResources+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(3000);
            int resindex = 0;
            List<WebElement> resources = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
            for(WebElement res : resources)
            {
                System.out.println("Resource Name: "+res.getText());
                if(res.getText().contains(resoursename))
                {
                    break;
                }
                resindex++;
            }
            System.out.println("resindex: "+resindex);
            //click on resource
            List<WebElement> allArrowIcon = driver.findElements(By.className("ls-resource-show-assign-this-block"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrowIcon.get(resindex));	//click on arrow icon
            Thread.sleep(2000);
            //click on resource
            List<WebElement> allOpenLink = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-cycle-bg']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOpenLink.get(resindex));
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("div.ls-main_aside > div.tabs > div.tab.active")).click();

        }
        catch(Exception e)
        {
            Assert.fail("Exception in openResourceFromResourceTab in AppHelper class Navigator", e);
        }
    }

    public void openParticularResourceFromResourceTab(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String resourcesname = ReadTestData.readDataByTagName("", "resourcesname", Integer.toString(dataIndex));
            System.out.println("resoursename: "+resourcesname);
            navigateToResourceTab();
            String appendChar=null;
            if(System.getProperty("UCHAR")==null)
            {
                appendChar=LoginUsingLTI.appendChar;
            }
            else {
                appendChar=System.getProperty("UCHAR");
            }
            String actualResources= resourcesname+appendChar;
            WebElement element=driver.findElement(By.xpath("//a[text()='"+actualResources+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(3000);
            int resindex = 0;
            List<WebElement> resources = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
            for(WebElement res : resources)
            {
                System.out.println("Resource Name: "+res.getText());
                if(res.getText().contains(resourcesname))
                {
                    break;
                }
                resindex++;
            }
            System.out.println("resindex: "+resindex);
            //click on resource
            List<WebElement> allArrowIcon = driver.findElements(By.className("ls-resource-show-assign-this-block"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrowIcon.get(resindex));	//click on arrow icon
            Thread.sleep(2000);

        }
        catch(Exception e)
        {
            Assert.fail("Exception in openResourceFromResourceTab in AppHelper class Navigator", e);
        }
    }
    public void openResourceFromResourceTabFromCMS(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
            System.out.println("resoursename: "+resoursename);
            navigateToResourceTab();
            int resindex = 0;
            List<WebElement> allOpenLink = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
            for(int a=0;a<allOpenLink.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allOpenLink.get(a));
                if((allOpenLink.get(a).getText().equals(resoursename))){
                    System.out.println("resoursename:"+resoursename);
                    break;
                }
                resindex++;
            }
            System.out.println("resindex: "+resindex);
            //click on resource
            List<WebElement> allArrowIcon = driver.findElements(By.className("ls-resource-show-assign-this-block"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrowIcon.get(resindex));	//click on arrow icon
            //click on resource
            List<WebElement> allOpenLink1 = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-cycle-bg']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOpenLink1.get(resindex));
            driver.findElement(By.cssSelector("div.ls-main_aside > div.tabs > div.tab.active")).click();

        }

        catch(Exception e)
        {
            Assert.fail("Exception in openResourceFromResourceTabFromCMS in AppHelper class Navigator", e);
        }
    }


    public void clickOnJumpOutIcon()
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickBycssselector("span[class='my-journal-media-popout-icon card-icons']");//click on jump out icon
			/*MouseHover.mouserhover("my-journal-activity-details");
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='my-journal-media-popout-icon card-icons']")));//click on jump out icon
			Thread.sleep(2000);*/
        }
        catch(Exception e)
        {
            Assert.fail("Exception in clickOnJumpOutIcon in AppHelper class Navigator", e);
        }
    }
    //@Author Sumit
    //to navigate to tabs present in RHS, pass the tab name as a string to navigate
    public void navigateToTab(String tabName) {
        try
        {
            Thread.sleep(2000);
            WebDriver driver=Driver.getWebDriver();
            if(tabName.equals("Fav") || tabName.equals("Favorite"))
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[data-id='fav']")));
            else
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[title='"+tabName+"']")));
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in navigateToResourceTab in AppHelper class Navigator", e);
        }
    }
    //@Author Sumit
    //to navigate to a page from profile drop down present on top right corner for LS+Adaptive course
    public void navigateFromProfileDropDown(String pageName)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.className("ls-user-nav__username")).click();	//click on profile dropdown
            Thread.sleep(2000);
            driver.findElement(By.linkText(pageName)).click();//click on page that you want to navigate
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in navigateFromProfileDropDown in AppHelper class Navigator", e);
        }
    }
    //@Author Sumit
    //to navigate to a page from profile drop down present on top right corner
    public void navigatorElements(String pageName)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.className("ls-user-nav__username")).click();	//click on profile dropdown
            Thread.sleep(2000);
            driver.findElement(By.linkText(pageName)).click();//click on page that you want to navigate
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in navigatorElements in AppHelper class Navigator", e);
        }
    }
    //@Author Sumit--modify brajesh
    //to navigate to a page from profile drop down present on top right corner for Orion course
    public void navigateFromProfileDropDownForOrion(String pageName)
    {
        try
        {
          /* new Click().clickByclassname("idb-user-profile-click-wrapper");	//click on profile dropdown
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("li[title='"+pageName+"']")).click();//click on page that you want to navigate
            Thread.sleep(2000);*/
            new Click().clickBycssselector("*[title='"+pageName+"']");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in navigateFromProfileDropDownForOrion in AppHelper class Navigator", e);
        }
    }
    /*
     *   @Brajesh
     *   navigate to dashboard orion
     */
    public void orionDashboard()
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.cssSelector("img[title='ORION Dashboard']")).click();
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            //new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in orionDashboard in AppHelper Navigator",e);
        }
    }

    /*
       @Author: Sumit on 18/08/2014
       This apphelper will open the 1st resource from the resource tab
     */
    public void openFirstResourceFromResourceTab(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
            navigateToResourceTab();
            new WebDriverWait(driver,400).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-resource-show-assign-this-block")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-resource-show-assign-this-block")));	//click on arrow icon for first resource
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@class='resource-content-posts-list']/div[3]/a[2]/span")));//click on open link
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in openFirstResourceFromResourceTab in AppHelper class Navigator", e);
        }
    }

    /*
      @Author: Dharaneesha on 12/07/2014
      This apphelper will open the 1st resource from the resource tab
    */
    public void openAssignmentFromAssignmentsTab(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            navigateToResourceTab();
            new WebDriverWait(driver,400).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-resource-show-assign-this-block")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-resource-show-assign-this-block")));	//click on arrow icon for first resource
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@class='resource-content-posts-list']/div[3]/a[2]/span")));//click on open link
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in openAssignmentFromAssignmentsTab in AppHelper class Navigator", e);
        }
    }

    public void myLibraryTab()
    {
        try {

            new Navigator().NavigateTo("Question Banks");
            new Click().clickBycssselector("div[data-id='my-resources']");

        }

        catch(Exception e)
        {
            Assert.fail("Exception in myLibraryTab in AppHelper class Navigator", e);
        }

    }
    /*@Author: Sumit on 11/12/2014
    Navigate from the Main Navigator as Publisher Admin*/
    public void publisherAdminNavigateTo(String pageName)
    {
        try
        {
            Thread.sleep(3000);
            new Click().clickBycssselector("a[class='navigator pa-toc-sprite']");
            new Click().clickbylinkText(pageName);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in publisherAdminNavigateTo in AppHelper class Navigator", e);

        }
    }

    public void NavigateToOrion()
    {
        try {
            new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
        } catch (Exception e) {
            Assert.fail("Exception in NavigateToOrion in AppHelper class Navigator", e);
        }

    }

    public void createQuestionPage(int dataIndex)
    {
        try {
            String course = "";
            String course_name = ReadTestData.readDataByTagName("", "course_name", Integer.toString(dataIndex));
            String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
            course = course_name;
            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }
            WebDriver driver=Driver.getWebDriver();
            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                    for (WebElement chapters : allChapters) {
                        if (chapters.getText().contains(chapterName)) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters);
                            Thread.sleep(4000);
                            break;
                        }

                    }
                }
            }
            new Click().clickByclassname("create-practice");
            Thread.sleep(1000);
            new Click().clickByclassname("create-regular-assessment-popup-item");
            Thread.sleep(1000);
            boolean createQuestionPage = driver.findElement(By.className("create-question-left-sectn")).isDisplayed();
            if(createQuestionPage==false)
            {
                Assert.fail("Not Navigated Create Question Page");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in createQuestionPage in AppHelper class Navigator", e);
        }
    }

    public void spanishItalianFrenchPaletteInStudentCS(String dataIndex)
    {
        try
        {
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", dataIndex);
            String selectShareType = ReadTestData.readDataByTagName("", "selectShareType", dataIndex);
            // boolean upload = ReadTestData.readDataByTagName("", "upload", dataIndex);
            WebDriver driver=Driver.getWebDriver();

            System.out.println("selectLanguagePalette::"+selectLanguagePalette);
            System.out.println("selectShareType::"+selectShareType);
            if(selectShareType.equals("post"))
            {
                new Click().clickByclassname("ls-post-tab");
                driver.findElement(By.className("ls-shareImg")).click();
                Thread.sleep(2000);
                boolean languages = driver.findElement(By.id("languages")).isDisplayed();
                System.out.println("languages::"+languages);
                if(languages==false)
                {
                    Assert.fail("\" Language palette icon\" is not displayed.");
                }
                new Click().clickByid("languages");
            }
            if(selectShareType.equals("link"))
            {
                driver.findElement(By.cssSelector("a[data-type='link']")).click();
                driver.findElement(By.className("ls-shareImg")).click();
                Thread.sleep(2000);
                boolean languages = driver.findElement(By.xpath("(.//div[@id='languages'])[2]")).isDisplayed();
                System.out.println("languages::"+languages);
                if(languages==false)
                {
                    Assert.fail("\" Language palette icon\" is not displayed.");
                }
                new Click().clickbyxpath("(.//div[@id='languages'])[2]");
            }
            if(selectShareType.equals("file"))
            {
                /*driver.findElement(By.cssSelector("a[data-type='file']")).click();
                Thread.sleep(1000);
                driver.findElement(By.id("file-upload-button")).click();*/

                new FileUpload().fileUpload(dataIndex,false);
                driver.findElement(By.className("ls-shareImg")).click();
                Thread.sleep(2000);
                boolean languages = driver.findElement(By.xpath("(.//div[@id='languages'])[3]")).isDisplayed();
                System.out.println("languages::"+languages);
                if(languages==false)
                {
                    Assert.fail("\" Language palette icon\" is not displayed.");
                }
                new Click().clickbyxpath("(.//div[@id='languages'])[3]");
            }
            Thread.sleep(1000);

            List<WebElement> languagesList = driver.findElements(By.xpath(".//*[@class='ls-supportive-languages-outer-wrapper']//li"));
            String spanishLanguage = languagesList.get(0).getText();
            System.out.println("spanishLanguage::"+spanishLanguage);
            if(!spanishLanguage.equals("Spanish") )
            {
                Assert.fail("Spanish language is not displayed as first in the list");
            }
            String italianLanguage = languagesList.get(1).getText();
            System.out.println("italianLanguage::"+italianLanguage);
            if(!italianLanguage.equals("Italian") )
            {
                Assert.fail("Italian language is not displayed as second option in the list");
            }
            String frenchLanguage = languagesList.get(2).getText();
            System.out.println("frenchLanguage::"+frenchLanguage);
            if(!frenchLanguage.equals("French") )
            {
                Assert.fail("French language is not displayed as third option in the list");
            }

            if(selectLanguagePalette.equals("spanish"))
            {
                new Click().clickByid("spanish");
                boolean spanishEditorpopUp = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).isDisplayed();
                if(spanishEditorpopUp==false)
                {
                    Assert.fail("Language editor popup is not enabled.");
                }

                String spanishEditor = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).getText();
                if(!spanishEditor.trim().equals("Spanish Editor"))
                {
                    Assert.fail("Spanish editor popup is not displayed.");
                }
            }
            if(selectLanguagePalette.equals("italian"))
            {
                new Click().clickByid("italian");
                boolean spanishEditorpopUp = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).isDisplayed();
                if(spanishEditorpopUp==false)
                {
                    Assert.fail("Language editor popup is not enabled.");
                }

                String spanishEditor = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).getText();
                if(!spanishEditor.trim().equals("Italian Editor"))
                {
                    Assert.fail("Italian editor popup is not displayed.");
                }
            }

            if(selectLanguagePalette.equals("french"))
            {
                new Click().clickByid("french");
                boolean spanishEditorpopUp = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).isDisplayed();
                if(spanishEditorpopUp==false)
                {
                    Assert.fail("Language editor popup is not enabled.");
                }

                String spanishEditor = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).getText();
                if(!spanishEditor.trim().equals("French Editor"))
                {
                    Assert.fail("French editor popup is not displayed.");
                }
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in spanishEditorInStudentCS in AppHelper class Navigator", e);
        }
    }
    public void closeHelp() {
        try {
            WebDriver driver=Driver.getWebDriver();
            if (driver.findElements(By.className("swhelp-button-cancel")).size() > 0)
                driver.findElement(By.className("swhelp-button-cancel")).click();
            Thread.sleep(5000);
        } catch (Exception e) {
        }
    }
    public void closeWalkMe() {
        try {
            WebDriver driver=Driver.getWebDriver();
            if (driver.findElements(By.cssSelector("div[class='wm-close-button walkme-x-button']")).size() > 0)
                driver.findElement(By.cssSelector("div[class='wm-close-button walkme-x-button']")).click();
            Thread.sleep(5000);
        } catch (Exception e) {
        }
    }
}
