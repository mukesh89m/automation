package com.snapwiz.selenium.tests.staf.learnon.testcases.IT22.R2213;

import com.snapwiz.selenium.tests.staf.learnon.Config;
import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.*;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.ScrollElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Created by durgapathi on 19/8/15.
 */
public class LogoChanges {

    @Test(priority = 1,enabled = true)
    public void loginPage()
    {
        try
        {
            Driver.startDriver();
            Driver.driver.get(Config.baseURL);
            boolean loginKey = Driver.driver.findElement(By.className("login-key-img")).isDisplayed();
            if(loginKey==false)
            {
                Assert.fail("Login Page is not displayed");
            }
            String logoPath = Driver.driver.findElement(By.cssSelector("img[title='jacaranda | learnon']")).getAttribute("src");
            System.out.println("baseURL::" + Config.baseURL);
            System.out.println("logoPath::" + logoPath);
            if(!logoPath.contains("/webresources/images/keduk/learnon-login-logo.png"))
            {
                Assert.fail("\"LearnOn\" logo is not replaced with new logo.");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase loginPage in class LogoChanges", e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void questionPreviewPage()
    {
        try
        {
            Driver.startDriver();
            String dataIndex = "89";
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String course = Config.course;
            System.out.println("course::"+course);
            new DirectLogin().CMSLogin();
            boolean cmsSearchPage = Driver.driver.findElement(By.id("search-course-name")).isDisplayed();
            if(cmsSearchPage==false)
            {
                Assert.fail("\"Home Page\" is not displayed.");
            }
            String logoPath = Driver.driver.findElement(By.cssSelector("img[title='jacaranda | learnon']")).getAttribute("src");
            System.out.println("logoPath::" + logoPath);
            if(!logoPath.contains("/webresources/images/keduk/learnon-app-logo.png"))
            {
                Assert.fail("\"LearnOn\" logo is not replaced with new logo in CMS home Page.");
            }
            new Assignment().create(89);
            new DirectLogin().CMSLogin();
            Driver.driver.findElement(By.cssSelector("a[course-name='" + course + "']")).click();
            List<WebElement> chapterList = Driver.driver.findElements(By.cssSelector("div[class='course-chapter-label node']"));
            chapterList.get(0).click();
            List<WebElement> assessmentList = Driver.driver.findElements(By.className("collection-assessment-name"));

            for(int i=0;i<assessmentList.size();i++)
                {
                    String assignmentName = assessmentList.get(i).getAttribute("title");
                    if(assignmentName.equals(assessmentname))
                    {
                        assessmentList.get(i).click();
                        break;
                    }
                }
            Thread.sleep(2000);
            String parentHandle = Driver.driver.getWindowHandle();
            new Click().clickByid("preview-the-image-quiz");
            for (String winHandle : Driver.driver.getWindowHandles()) {
                Driver.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
            }

            System.out.println("newWIndowURL::" + Driver.driver.getCurrentUrl());

            System.out.println("previewPageName::" + Driver.driver.findElement(By.id("cms-question-preview-header-ass-name-wrapper")).getText());
            boolean previewPage = Driver.driver.findElement(By.id("cms-question-preview-header-ass-name-wrapper")).isDisplayed();
            if(previewPage==false)
            {
                Assert.fail("Question preview page is not loaded.");
            }
            String logoInQuestioPreview = Driver.driver.findElement(By.className("learn-on-preview-logo")).getAttribute("src");
            System.out.println("logoInQuestioPreview::"+logoInQuestioPreview);
            if(!logoInQuestioPreview.contains("/webresources/images/keduk/learnon-logo-small.png"))
            {
                Assert.fail("\"LearnOn\" logo is not replaced with new logo in question preview page.");
            }
            Driver.driver.close(); // close newly opened window when done with it
            Driver.driver.switchTo().window(parentHandle);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase questionPreviewPage in class LogoChanges", e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void logoChangesOnTeacherSide()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("99");
            boolean dashBoard = Driver.driver.findElement(By.className("ls-dashboard-container")).isDisplayed();
            if(dashBoard==false)
            {
                Assert.fail("Not Navigated dashboard on Teacher Login");
            }
            String logoInTeacherDashBoard= Driver.driver.findElement(By.cssSelector("img[title='jacaranda | learnon']")).getAttribute("src");
            System.out.println("logoInTeacherDashBoard::" + logoInTeacherDashBoard);
            if(!logoInTeacherDashBoard.contains("/webresources/images/keduk/learnon-logo.png"))
            {
                Assert.fail("\"LearnOn\" logo is not replaced with new logo in Teacher DashBoard.");
            }
            // Navigate to TOC
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");
            new Click().clickbylinkText("Course");
            Thread.sleep(2000);
            // Navigate to DashBoard
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");
            new Click().clickbylinkText("Dashboard");
            String logoOnbackToDashBoard= Driver.driver.findElement(By.cssSelector("img[title='jacaranda | learnon']")).getAttribute("src");
            System.out.println("logoOnbackToDashBoard::" + logoOnbackToDashBoard);
            if(!logoOnbackToDashBoard.contains("/webresources/images/keduk/learnon-logo.png"))
            {
                Assert.fail("\"LearnOn\" logo is not present on back to dashboard from TOC");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase logoChangesOnTeacherSide in class LogoChanges", e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void tryItForAssessment()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("108");
            // Navigate to TOC
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");
            new Click().clickbylinkText("Course");
            // Click on Assignment Arrow
            List<WebElement> assignmentArrow = Driver.driver.findElements(By.xpath(".//*[@class='topic-card toc-top-border']/div/ul/li/div[@class='inner-assessment-block static_assessment']/div[@class='ls-inner-arw']"));
            System.out.println("assignmentArrow::" + assignmentArrow.get(0));
            assignmentArrow.get(1).click();
            boolean tocTryIt = Driver.driver.findElement(By.xpath(".//*[@class='toc-actions-div']/div[@class='toc-try-it']")).isDisplayed();
            if(tocTryIt==false)
            {
                Assert.fail("\"Try it\"button is not displayed on click on arrow icon");
            }
            // New Window Handle
            String parentHandler = Driver.driver.getWindowHandle();
            Driver.driver.findElement(By.xpath(".//*[@class='toc-actions-div']/div[@class='toc-try-it']")).click(); // Click on Try It
            for(String winHandle : Driver.driver.getWindowHandles())
            {
                Driver.driver.switchTo().window(winHandle);
            }
            String currentURL = Driver.driver.getCurrentUrl();
            System.out.println("New Window URL::" + currentURL);
            if(!currentURL.contains("tryItAssessmentOrAssignment"))
            {
                Assert.fail("Click on \"Try it\" is not opened a New Window");
            }
            System.out.println("Assignment on New Window::" + Driver.driver.findElement(By.id("cms-question-preview-header-ass-name")).getAttribute("title"));
            String logoOnTriItWindow = Driver.driver.findElement(By.className("learn-on-preview-logo")).getAttribute("src");
            if(!logoOnTriItWindow.contains("webresources/images/keduk/learnon-logo-small.png"))
            {
                Assert.fail("Logo is not replaced in \"Try it\" New Window");
            }
            Driver.driver.close(); // Close new Window
            Driver.driver.switchTo().window(parentHandler);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase tryItForAssessment in class LogoChanges", e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void tryItForAssessmentAsAssignment()
    {
        try
        {
            int dataIndex = 114;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            System.out.println("assessmentname::" + assessmentname);
            Driver.startDriver();
            new Assignment().create(114);
            new LoginUsingLTI().ltiLogin("114");
            new Navigator().NavigateTo("e-Textbook");
            List<WebElement> assessmentList = Driver.driver.findElements(By.xpath(".//*[@class='assessment-card']/li/div/a[@data-type='static_assessment']")); // List of assessments
            for(int i=0;i<assessmentList.size();i++)
            {
                String assessmentNameTOC = assessmentList.get(i).getAttribute("title");
                if(assessmentNameTOC.trim().equals(assessmentname))
                {
                    System.out.println("assessmentNameTOC::" + assessmentNameTOC);
                    // Click on Arrow
                    List<WebElement> assignmentArrowList = Driver.driver.findElements(By.xpath(".//*[@class='assessment-card']/li/div/div[@orionadaptiveactivetab='']"));
                    assignmentArrowList.get(i).click();
                    List<WebElement> assignThisList = Driver.driver.findElements(By.xpath(".//*[@class='assessment-card']/li/div/div/div[@class='toc-assign-this open-assign-window']"));
                    assignThisList.get(i).click();
                    new AssignLesson().assignTOCWithDefaultClassSection(114);
                    assignmentArrowList.get(i).click();
                    boolean tocTryIt = Driver.driver.findElement(By.xpath(".//*[@class='toc-actions-div']/div[@class='toc-try-it']")).isDisplayed();
                    if(tocTryIt==false)
                    {
                        Assert.fail("\"Try it\"button is not displayed on click on arrow icon");
                    }
                    break;
                }
            }
            String parentHandler = Driver.driver.getWindowHandle();
            Driver.driver.findElement(By.xpath(".//*[@class='toc-actions-div']/div[@class='toc-try-it']")).click(); // Click on Try It
            for(String winHandler : Driver.driver.getWindowHandles())
            {
                Driver.driver.switchTo().window(winHandler);
            }
            String currentURL = Driver.driver.getCurrentUrl();
            System.out.println("New Window URL::" + currentURL);
            if(!currentURL.contains("tryItAssessmentOrAssignment"))
            {
                Assert.fail("Click on \"Try it\" is not opened a New Window");
            }
            System.out.println("Assignment on New Window::" + Driver.driver.findElement(By.id("cms-question-preview-header-ass-name")).getAttribute("title"));
            String logoOnTriItWindow = Driver.driver.findElement(By.className("learn-on-preview-logo")).getAttribute("src");
            if(!logoOnTriItWindow.contains("webresources/images/keduk/learnon-logo-small.png"))
            {
                Assert.fail("Logo is not replaced in \"Try it\" New Window");
            }
            Driver.driver.close(); // Close new Window
            Driver.driver.switchTo().window(parentHandler);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase tryItForAssessmentAsAssignment in class LogoChanges", e);
        }
    }
    // pending page scroll down
    @Test(priority = 6,enabled = true)
    public void tryItForAssignment()
    {
        try
        {

            int dataIndex = 120;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            Driver.startDriver();
            new Assignment().create(120);
            new LoginUsingLTI().ltiLogin("120");
            new Assignment().createCustomeAssignment("120");
            new Assignment().assignCustomeOrFileBasedAssignment(120);
            new TopicOpen().clickOnAssignmentArrowInCourse(120);
            List<WebElement> assignmentsList = Driver.driver.findElements(By.className("toc-assignment-ellipsis"));
            for(int i = 0;i<assignmentsList.size();i++)
            {
                String assignmentName = assignmentsList.get(i).getText();
                if(assignmentName.equals(assessmentname))
                {
                    //List<WebElement> assignmentArrow = Driver.driver.findElements(By.xpath(".//*[@class='inner-assignment-block']/div[@class='ls-inner-arw']"));
                   // assignmentArrow.get(i).click();
                    boolean tocTryIt = Driver.driver.findElement(By.xpath(".//*[@class='toc-actions-div']/div[@class='toc-try-it']")).isDisplayed();
                    if(tocTryIt==false)
                    {
                        Assert.fail("\"Try it\"button is not displayed on click on arrow icon");
                    }
                    break;
                }
            }
            String parentHandler = Driver.driver.getWindowHandle();
            Driver.driver.findElement(By.xpath(".//*[@class='toc-actions-div']/div[@class='toc-try-it']")).click(); // Click on Try It
            for(String winHnadler : Driver.driver.getWindowHandles())
            {
                Driver.driver.switchTo().window(winHnadler);
            }
            String currentURL = Driver.driver.getCurrentUrl();
            System.out.println("New Window URL::" + currentURL);
            if(!currentURL.contains("tryItAssessmentOrAssignment"))
            {
                Assert.fail("Click on \"Try it\" is not opened a New Window");
            }
            System.out.println("Assignment on New Window::" + Driver.driver.findElement(By.id("cms-question-preview-header-ass-name")).getAttribute("title"));
            String logoOnTriItWindow = Driver.driver.findElement(By.className("learn-on-preview-logo")).getAttribute("src");
            if(!logoOnTriItWindow.contains("webresources/images/keduk/learnon-logo-small.png"))
            {
                Assert.fail("Logo is not replaced in \"Try it\" New Window");
            }
            Driver.driver.close(); // Close new Window
            Driver.driver.switchTo().window(parentHandler);
        }

        catch (Exception e)
        {
            Assert.fail("Exception in testcase tryItForAssignment in class LogoChanges", e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void tryItForAssignmentTab()
    {
        try
        {
            String dataIndex = "126";
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);

            Driver.startDriver();
            new Assignment().create(126);
            new LoginUsingLTI().ltiLogin("126");
            new Assignment().createCustomeAssignment("126");
            new Navigator().NavigateTo("My Question Bank");
            boolean assignment = false;
            List<WebElement> assignmentPresent = Driver.driver.findElements(By.className("resource-title"));
            for (int i=0;i<assignmentPresent.size();i++)
            {
                String assignmentName = assignmentPresent.get(i).getText();
                if(assignmentName.contains(assessmentname))
                {
                    System.out.println("assignmentName::" + assignmentName);
                    assignment = true;
                    break;
                }
            }
            if(assignment==false)
            {
                Assert.fail("Assignment is not present in \"My Question Bank\"");
            }
            new TopicOpen().clickOnCustomAssignmentArrowInAssignmentTab(126);
            List<WebElement> allOpenLink = Driver.driver.findElements(By.className("ls_assessment_link"));
            String parentHandler = Driver.driver.getWindowHandle();
            for(int a = 0; a< allOpenLink.size();a++)
            {
                String assignmentNameinTab = allOpenLink.get(a).getAttribute("title");
                if(assignmentNameinTab.contains(assessmentname))
                {
                    // Click on try it
                    List<WebElement> listOfTryIt = Driver.driver.findElements(By.xpath(".//*[@class='ls-right-tab-hover-tryit folder-cycle-bg']"));
                    listOfTryIt.get(a+1).click();
                    break;
                }
            }
            for(String winHnadler : Driver.driver.getWindowHandles())
            {
                Driver.driver.switchTo().window(winHnadler);
            }
            String currentURL = Driver.driver.getCurrentUrl();
            System.out.println("New Window URL::" + currentURL);
            if(!currentURL.contains("tryItAssessmentOrAssignment"))
            {
                Assert.fail("Click on \"Try it\" is not opened a New Window");
            }
            System.out.println("Assignment on New Window::" + Driver.driver.findElement(By.id("cms-question-preview-header-ass-name")).getAttribute("title"));
            String logoOnTriItWindow = Driver.driver.findElement(By.className("learn-on-preview-logo")).getAttribute("src");
            if(!logoOnTriItWindow.contains("webresources/images/keduk/learnon-logo-small.png"))
            {
                Assert.fail("Logo is not replaced in \"Try it\" New Window");
            }
            Driver.driver.close(); // Close new Window
            Driver.driver.switchTo().window(parentHandler);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase tryItForAssignmentTab in class LogoChanges", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void tryItForMyQuestionBank()
    {
        try
        {
            int dataIndex = 132;
            String customAssignmentName = ReadTestData.readDataByTagName("", "customAssignmentName", Integer.toString(dataIndex));
            Driver.startDriver();
            new Assignment().create(132);
            new LoginUsingLTI().ltiLogin("132");
            new Assignment().createCustomeAssignment("132");
            new Navigator().NavigateTo("My Question Bank");
            new Click().clickByid("my-resource-search-textarea");
            Driver.driver.findElement(By.id("my-resource-search-textarea")).sendKeys(customAssignmentName);
            new Click().clickByid("my-resource-search-button");
            String parentHandler = Driver.driver.getWindowHandle();
            List<WebElement> tryItLink = Driver.driver.findElements(By.className("ls-try-it"));
            tryItLink.get(0).click();
            for(String winHnadler : Driver.driver.getWindowHandles())
            {
                Driver.driver.switchTo().window(winHnadler);
            }
            String currentURL = Driver.driver.getCurrentUrl();
            System.out.println("New Window URL::" + currentURL);
            if(!currentURL.contains("tryItAssessmentOrAssignment"))
            {
                Assert.fail("Click on \"Try it\" is not opened a New Window");
            }
            System.out.println("Assignment on New Window::" + Driver.driver.findElement(By.id("cms-question-preview-header-ass-name")).getAttribute("title"));
            String logoOnTriItWindow = Driver.driver.findElement(By.className("learn-on-preview-logo")).getAttribute("src");
            if(!logoOnTriItWindow.contains("webresources/images/keduk/learnon-logo-small.png"))
            {
                Assert.fail("Logo is not replaced in \"Try it\" New Window");
            }
            Driver.driver.close(); // Close new Window
            Driver.driver.switchTo().window(parentHandler);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase tryItForMyQuestionBank in class LogoChanges", e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void tryItForQuestionBank()
    {
        try
        {
            int dataIndex = 138;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));Driver.startDriver();
            new Assignment().create(138);
            new LoginUsingLTI().ltiLogin("138");
            new Navigator().NavigateTo("Question Banks");
            new Click().clickByid("all-resource-search-textarea");
            Driver.driver.findElement(By.id("all-resource-search-textarea")).sendKeys(assessmentname);
            new Click().clickByid("all-resource-search-button");
            String parentHandler = Driver.driver.getWindowHandle();
            List<WebElement> tryItLink = Driver.driver.findElements(By.className("ls-try-it"));
            tryItLink.get(0).click();
            for(String winHnadler : Driver.driver.getWindowHandles())
            {
                Driver.driver.switchTo().window(winHnadler);
            }
            String currentURL = Driver.driver.getCurrentUrl();
            System.out.println("New Window URL::" + currentURL);
            if(!currentURL.contains("tryItAssessmentOrAssignment"))
            {
                Assert.fail("Click on \"Try it\" is not opened a New Window");
            }
            System.out.println("Assignment on New Window::" + Driver.driver.findElement(By.id("cms-question-preview-header-ass-name")).getAttribute("title"));
            String logoOnTriItWindow = Driver.driver.findElement(By.className("learn-on-preview-logo")).getAttribute("src");
            if(!logoOnTriItWindow.contains("webresources/images/keduk/learnon-logo-small.png"))
            {
                Assert.fail("Logo is not replaced in \"Try it\" New Window");
            }
            Driver.driver.close(); // Close new Window
            Driver.driver.switchTo().window(parentHandler);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase tryItForQuestionBank in class LogoChanges", e);
        }
    }

    @Test(priority = 10,enabled = true)
    public void tryItForCurrentAssignmentPage()
    {
        try
        {
            int dataIndex = 144;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            Driver.startDriver();
            new Assignment().create(144);
            new LoginUsingLTI().ltiLogin("144");
            new Assignment().createCustomeAssignment("144");
            new Assignment().assignCustomeOrFileBasedAssignment(144);
            new Navigator().NavigateTo("Current Assignments");
            new Click().clickBycssselector("a[title=All Activity']");
            new Click().clickbylinkText("Question Assignment");
            String parentHandler = Driver.driver.getWindowHandle();
            List<WebElement> assignmentList = Driver.driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for(int a = 0; a<assignmentList.size();a++)
            {
                String assignmentName = assignmentList.get(a).getAttribute("assignmentname");
                if(assignmentName.trim().equals(assessmentname))
                {
                    List<WebElement> tryItList = Driver.driver.findElements(By.className("try-it"));
                    tryItList.get(a).click();
                }
            }
            for(String winHnadler : Driver.driver.getWindowHandles())
            {
                Driver.driver.switchTo().window(winHnadler);
            }
            String currentURL = Driver.driver.getCurrentUrl();
            System.out.println("New Window URL::" + currentURL);
            if(!currentURL.contains("tryItAssessmentOrAssignment"))
            {
                Assert.fail("Click on \"Try it\" is not opened a New Window");
            }
            System.out.println("Assignment on New Window::" + Driver.driver.findElement(By.id("cms-question-preview-header-ass-name")).getAttribute("title"));
            String logoOnTriItWindow = Driver.driver.findElement(By.className("learn-on-preview-logo")).getAttribute("src");
            if(!logoOnTriItWindow.contains("webresources/images/keduk/learnon-logo-small.png"))
            {
                Assert.fail("Logo is not replaced in \"Try it\" New Window");
            }
            Driver.driver.close(); // Close new Window
            Driver.driver.switchTo().window(parentHandler);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase tryItForCurrentAssignmentPage in class LogoChanges", e);
        }
    }

    @Test(priority = 11,enabled = true)
    public void studentDashBoard ()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("156");
            boolean dashBoard = Driver.driver.findElement(By.className("ls-dashboard-container")).isDisplayed();
            if(dashBoard==false)
            {
                Assert.fail("Login as student is not loaded the dashboard");
            }
            String studentDashBoard = Driver.driver.findElement(By.cssSelector("img[title='jacaranda | learnon']")).getAttribute("src");
            if(!studentDashBoard.contains("/webresources/images/keduk/learnon-logo.png"))
            {
                Assert.fail("Logo is not replaced in student dashboard");
            }
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");
            new Click().clickbylinkText("Course");
            Thread.sleep(2000);
            // Navigate to DashBoard
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");
            new Click().clickbylinkText("Dashboard");
            String logoOnbackToDashBoard= Driver.driver.findElement(By.cssSelector("img[title='jacaranda | learnon']")).getAttribute("src");
            System.out.println("logoOnbackToDashBoard::" + logoOnbackToDashBoard);
            if(!logoOnbackToDashBoard.contains("/webresources/images/keduk/learnon-logo.png"))
            {
                Assert.fail("\"LearnOn\" logo is not present on back to student dashboard from TOC");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase studentDashBoard in class LogoChanges", e);
        }
    }
}
