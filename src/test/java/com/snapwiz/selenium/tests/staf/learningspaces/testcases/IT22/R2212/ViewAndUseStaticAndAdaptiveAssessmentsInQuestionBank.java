package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT22.R2212;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by durgapathi on 13/10/15.
 */
public class ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank extends Driver{

    @Test(priority = 1,enabled = true)
    public void ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank()
    {
        try
        {
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            int dataIndex = 10;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            // Create a static assessment
            new Assignment().create(10);
            new Assignment().addQuestions(11, "textentry", "");
            new Assignment().addQuestions(12, "multiplechoice", "");
            new Assignment().addQuestions(13,"textentry","");

            new LoginUsingLTI().ltiLogin("10"); // Login as instructor
            new Navigator().NavigateTo("Question Banks"); // Navigate to Question Bank
            // Verfiy question bank loaded
            boolean questionBanks = driver.findElement(By.cssSelector("a[title='Question Banks Only']")).isDisplayed();
            if(questionBanks == false)
            {
                Assert.fail("\"Question Banks\" Page is not displayed.");
            }
            // Drop down values
            List<WebElement> dropdownValues = driver.findElements(By.xpath(".//div[@id='ls-assessment-filter-questionset-drop-down-wrapper']//li/a"));
            System.out.println("dropdownValues.get(0).getText()::"+dropdownValues.get(0).getText());
            System.out.println("dropdownValues.get(1).getText()::"+dropdownValues.get(1).getText());

            if(!dropdownValues.get(0).getAttribute("title").trim().equals("Question Banks Only"))
            {
                Assert.fail("\"Question Banks Only\" is not available in the stop down list");
            }
            if(!dropdownValues.get(1).getAttribute("title").trim().equals("Practice Sets Only"))
            {
                Assert.fail("\"Practice Sets Only\" is not available in the stop down list");
            }
            // Click on Question Bank Only drop down
            driver.findElement(By.xpath(".//div[@id='ls-assessment-filter-questionset-drop-down-wrapper']/div/a")).click();
            // practice set Only
            boolean practiceSetOnly = driver.findElement(By.cssSelector("a[title='Practice Sets Only']")).isDisplayed();
            if(practiceSetOnly == false)
            {
                Assert.fail("\"Practice Sets Only\" is not available in the stop down list");
            }
            //click on practice set only
            driver.findElement(By.cssSelector("a[title='Practice Sets Only']")).click();
            Thread.sleep(1000);
            // Verify Static Assessment
            boolean assessment = true;
            List<WebElement> assessmentList = driver.findElements(By.className("resource-title"));
            for(int a = 0; a > assessmentList.size(); a++)
            {
                String nameOfAssessment = assessmentList.get(a).getAttribute("title");
                if(nameOfAssessment.trim().equals(assessmentname))
                {
                    System.out.println("nameOfAssessment::"+nameOfAssessment);
                    break;
                }
                else
                {
                    assessment = false;
                }
            }
            System.out.println("assessment::" + assessment);
            System.out.println("assessment::" + assessment);

            if(assessment == false)
            {
                Assert.fail("Created static assessment is not present in Question Banks");
            }
            // Verify Adaptive practice
            assessment = true;
            for(int a = 0; a > assessmentList.size(); a++)
            {
                String nameOfAssessment = assessmentList.get(a).getAttribute("title");
                if(nameOfAssessment.trim().contains("Personalized Practice - Ch"))
                {
                    System.out.println("nameOfAssessment::"+nameOfAssessment);
                    break;
                }
                else
                {
                    assessment = false;
                }
            }
            System.out.println("assessment::"+assessment);

            if(assessment == false)
            {
                Assert.fail("Adaptive Practice assessment is not present in Question Banks");
            }
            Thread.sleep(2000);
            //resource ocin
            boolean resourceIcon = driver.findElement(By.className("resource-icon")).isDisplayed();
            if(resourceIcon == false)
            {
                Assert.fail("Assessment Icon is not present");
            }
            // Question Card
            int questionCard = driver.findElements(By.id("ls-assessment-item-sectn-links-all-id")).size();
            // Actions
            int lsActions = driver.findElements(By.className("ls-actions")).size();
            if(lsActions != questionCard)
            {
                Assert.fail("LS Actions are not present for all the assessments");
            }
            String actionText = driver.findElement(By.className("ls-actions")).getText();
            if(!actionText.trim().equals("Actions :"))
            {
                Assert.fail("Label with Action is not present");
            }
            // Preview
            int lsPreview = driver.findElements(By.cssSelector("span[class='ls-preview-wrapper action-links']")).size();
            if(lsPreview != questionCard)
            {
                Assert.fail("Preview is not available for all assessments");
            }
            // Add to My Question Bank
            int addThisToMyResources = driver.findElements(By.cssSelector("i[class='addThisToMyResources not-bookmarked ls-resource-bookmark-icon']")).size();
            if(addThisToMyResources != questionCard)
            {
                Assert.fail("Add to My Question Bank is not available for all assessments");
            }
            // Assign This
            int assignThis = driver.findElements(By.cssSelector("span[class='resource-assign-class action-links']")).size();
            if(assignThis != questionCard)
            {
                Assert.fail("Assign This is not available for all assessments");
            }
            // Assign This
            int tryIt = driver.findElements(By.cssSelector("span[class='action-links try-it']")).size();
            if(tryIt != questionCard)
            {
                Assert.fail("Try It is not available for all assessments");
            }
            // Customize This
            int customizeThis = driver.findElements(By.cssSelector("i[class='customize-this ls-delete-this-customize-this-sprite']")).size();
            if(customizeThis > 0)
            {
                Assert.fail("Customize This option is available for Practice Sets");
            }
            // Click on preview
            assessmentList = driver.findElements(By.className("resource-title"));
            for(int a = 0; a < assessmentList.size(); a++)
            {
                String nameOfAssessment = assessmentList.get(a).getAttribute("title");
                List<WebElement> previewList = driver.findElements(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
                if(nameOfAssessment.trim().equals(assessmentname))
                {
                    previewList.get(a).click();
                    break;
                }
            }
            // New tab opened
            boolean newTabOfPreview = driver.findElement(By.cssSelector("span[class='tab_icon assignment-icon']")).isDisplayed();
            if(newTabOfPreview == false)
            {
                Assert.fail("Preview of static assessment is not opened in new tab");
            }
            driver.findElement(By.cssSelector("span[title='Question Banks']")).click(); // Navigate to Question Bank
            // Add To My Question Bank
            assessmentList = driver.findElements(By.className("resource-title"));
            for(int r = 0; r < assessmentList.size(); r++)
            {
                String nameOfAssessment = assessmentList.get(r).getAttribute("title");
                List<WebElement> addToMyQuestionBank = driver.findElements(By.cssSelector("i[class='addThisToMyResources not-bookmarked ls-resource-bookmark-icon']"));
                if(nameOfAssessment.trim().equals(assessmentname))
                {
                    addToMyQuestionBank.get(r).click();
                    break;
                }
            }
            driver.findElement(By.className("notification-close-btn-section")).click(); // close notification
            Thread.sleep(2000);
            // Click on My Question Bank
            driver.findElement(By.cssSelector("span[title='My Question Bank']")).click();
            String assessmentNameInQB = driver.findElement(By.className("resource-title")).getAttribute("title");
            if(!assessmentNameInQB.equals(assessmentname))
            {
                Assert.fail("Static assessment is not get added to My Question Bank tab");
            }
            // Click on Preview
            driver.findElement(By.cssSelector("span[class='ls-preview-wrapper action-links']")).click();
            Thread.sleep(2000);
            // New tab opened
            newTabOfPreview = driver.findElement(By.cssSelector("span[class='tab_icon assignment-icon']")).isDisplayed();
            if(newTabOfPreview == false)
            {
                Assert.fail("Preview of static assessment is not opened in new tab");
            }
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("span[title='My Question Bank']")).click();
            // Assign assessment from My question Bank
            driver.findElement(By.className("assign-this")).click();
            Thread.sleep(1000);
            new AssignLesson().assignTOCWithDefaultClassSection(10); // assign assessment
            new Navigator().NavigateTo("My Question Bank");
            String parentHandle = driver.getWindowHandle();
            driver.findElement(By.className("ls-try-it")).click(); // click on try it
            for(String windHandle : driver.getWindowHandles())
            {
                driver.switchTo().window(windHandle);
            }
            boolean newWindow = driver.findElement(By.id("cms-question-preview-header-ass-name")).isDisplayed();
            if(newWindow == false)
            {
                Assert.fail("On clicking Try it link, Assessment is not open in new window as product supports currently");
            }
            driver.close();
            driver.switchTo().window(parentHandle);
            Thread.sleep(1000);
            // Customize This
            questionBank.questionBankTab.click();
            questionBank.questionBankDropdown.click();
            questionBank.practiceSetsOnly.click();
            myQuestionBank.getMyQuestionBankTitle().click();
            Thread.sleep(2000);
            customizeThis = driver.findElements(By.cssSelector("i[class='customize-this ls-delete-this-customize-this-sprite']")).size();
            if(customizeThis > 0)
            {
                Assert.fail("Customize This option is available for Practice Sets");
            }
            //click on question bank
            questionBank.questionBankTab.click();
            Thread.sleep(2000);
            questionBank.assignThisIcon.get(1).click();
            boolean popup = questionBank.getPopupHeader().isDisplayed();
            if(popup == false)
            {
                Assert.fail("Assign pop-up is not displayed as the product supports currently");
            }
            new AssignLesson().assignTOCWithDefaultClassSection(10); // assign assessment
            new Navigator().NavigateTo("Question Banks");
            questionBank.questionBankDropdown.click();
            questionBank.practiceSetsOnly.click();
            questionBank.tryItLink.get(0).click();
            parentHandle = driver.getWindowHandle();
            for(String windHandle : driver.getWindowHandles())
            {
                driver.switchTo().window(windHandle);
            }
            newWindow = driver.findElement(By.id("cms-question-preview-header-ass-name")).isDisplayed();
            if(newWindow == false)
            {
                Assert.fail("On clicking Try it link, Assessment is not open in new window as product supports currently");
            }
            driver.close();
            driver.switchTo().window(parentHandle);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank testcase in ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank class", e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void adaptiveAssessmentsInQuestionBank()
    {
        try
        {
            QuestionBank questionBank = PageFactory.initElements(driver,QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver,MyQuestionBank.class);
            new LoginUsingLTI().ltiLogin("10");
            new Navigator().NavigateTo("Question Banks");
            questionBank.questionBankDropdown.click(); // Click on question bank dropdown
            questionBank.practiceSetsOnly.click(); // Select practice Only
            int practiceTest = driver.findElements(By.xpath("//div[contains(text(),'Personalized Practice')]")).size();
            while(practiceTest == 0)
            {
                new ScrollElement().scrollBottomOfPage();
                practiceTest = driver.findElements(By.xpath("//div[contains(text(),'Personalized Practice')]")).size();
            }
            WebElement element = driver.findElement(By.xpath("//div[contains(text(),'Personalized Practice')]"));
            System.out.println("element::" + element.getAttribute("title"));
            new ScrollElement().scrollToViewOfElement(element);
           // questionBank.lsActions.get(0).getText().trim().equals("Actions :");
            Thread.sleep(2000);
            String lsActions = questionBank.lsActions.get(0).getText();
            System.out.println("lsActions::"+lsActions);
            if(!lsActions.trim().equals("Actions :"))
            {
               Assert.fail("The question card does not the lable \"Actions\"");
            }
            int assessmentsCount  = questionBank.assessmentName.size();
            int previewCount = questionBank.preview.size();
            int assignThisLinkCount = questionBank.assignThisLink.size();
            int addToMyQuestionBankCount = questionBank.addToMyQuestionBanks.size();
            int tryItCount = questionBank.tryItLink.size();
            if(previewCount != assessmentsCount && addToMyQuestionBankCount != assessmentsCount && assignThisLinkCount != assessmentsCount && tryItCount != assessmentsCount)
            {
                Assert.fail("Preview/addToMyQuestionBank/assignThisLink/tryIt is not present fo all Assessments");
            }
            // Customize this
            Assert.assertEquals(questionBank.customizeThisLink.size(),0,"\"Customize This\" option is available under Actions");
            List<WebElement> practiceTestsList = questionBank.assessmentName;
            for(int a = 0; a < practiceTestsList.size(); a++)
            {
                String practiceName = practiceTestsList.get(a).getAttribute("title");
                if(practiceName.trim().contains("Personalized Practice"))
                {
                    questionBank.preview.get(a).click(); // click on practice preview
                    Thread.sleep(2000);
                    questionBank.getQuestionBankTitle().click();
                    practiceTest = driver.findElements(By.xpath("//div[contains(text(),'Personalized Practice')]")).size();
                    while(practiceTest == 0)
                    {
                        new ScrollElement().scrollBottomOfPage();
                        practiceTest = driver.findElements(By.xpath("//div[contains(text(),'Personalized Practice')]")).size();
                    }
                    questionBank.addToMyQuestionBanks.get(a).click(); // add practice to My question bank
                    break;
                }
            }
            Assert.assertTrue(questionBank.assignmentIcon.isDisplayed(), "Preview of adaptive practice assessment is not opened in new tab from Question Bank"); // New assignment tab
            myQuestionBank.getMyQuestionBankTitle().click();
            Assert.assertTrue(myQuestionBank.assessmentName.get(0).getAttribute("title").contains("Personalized Practice"), "Adaptive assessment is not added to My Question Bank tab");
            // Click on preview
            myQuestionBank.previewLink.get(0).click();
            Thread.sleep(2000);
            Assert.assertTrue(myQuestionBank.assignmentIcon.isDisplayed(), "Preview of adaptive practice assessment is not opened in new tab from My Question Bank");
            myQuestionBank.getMyQuestionBankTitle().click();
            myQuestionBank.getAssignThis().click();
            new AssignLesson().assignTOCWithDefaultClassSection(10); // assign assessment
            new Navigator().NavigateTo("Question Banks");
            questionBank.questionBankDropdown.click();
            questionBank.practiceSetsOnly.click();
            myQuestionBank.getMyQuestionBankTitle().click();
            String parentHandle = driver.getWindowHandle();
            myQuestionBank.tryItIcon.get(0).click();
            for(String windHandle : driver.getWindowHandles())
            {
                driver.switchTo().window(windHandle);
            }
            boolean newWindow = driver.findElement(By.id("cms-question-preview-header-ass-name")).isDisplayed();
            if(newWindow == false)
            {
                Assert.fail("On clicking Try it link, Assessment is not open in new window as product supports currently");
            }
            driver.close();
            driver.switchTo().window(parentHandle);
            myQuestionBank.getMyQuestionBankTitle().click();
            System.out.println("aaaaa::" + myQuestionBank.customizeThisLink.size());
            Assert.assertEquals(myQuestionBank.customizeThisLink.size(), 0, "\"Customize This\" option is available under Actions"); // Customize option
            questionBank.questionBankTab.click();

            // Assign This
            practiceTestsList = questionBank.assessmentName;
            for(int a = 0; a < practiceTestsList.size(); a++)
            {
                String practiceName = practiceTestsList.get(a).getAttribute("title");
                if(practiceName.trim().contains("Personalized Practice"))
                {
                    questionBank.getSearchInputFieldOnQuestionBank().get(1).click();
                    questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys(practiceName);
                    questionBank.getSearchButtonOnQuestionBank().get(1).click();
                    break;
                }
            }
            questionBank.assignThisIcon.get(1).click();
            Assert.assertTrue(driver.findElement(By.id("ir-ls-assign-dialog")).isDisplayed(), "Assign pop-up is not displayed as the product supports currently for adaptive practice in TOC");
            new AssignLesson().assignTOCWithDefaultClassSection(14);
            new Navigator().NavigateTo("Question Banks");
            questionBank.questionBankDropdown.click();
            questionBank.practiceSetsOnly.click();
            // tryIt
            parentHandle = driver.getWindowHandle();
            practiceTestsList = questionBank.assessmentName;
            for(int a = 0; a < practiceTestsList.size(); a++)
            {
                String practiceName = practiceTestsList.get(a).getAttribute("title");
                if(practiceName.trim().contains("Personalized Practice"))
                {
                    questionBank.getSearchInputFieldOnQuestionBank().get(1).click();
                    questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys(practiceName);
                    questionBank.getSearchButtonOnQuestionBank().get(1).click();
                    break;
                }
            }
            questionBank.tryItLink.get(1).click();
            for(String windHandle : driver.getWindowHandles())
            {
                driver.switchTo().window(windHandle);
            }
            newWindow = driver.findElement(By.id("cms-question-preview-header-ass-name")).isDisplayed();
            if(newWindow == false)
            {
                Assert.fail("On clicking Try it link, Assessment is not open in new window as product supports currently");
            }
            driver.close();
            driver.switchTo().window(parentHandle);
            new Navigator().NavigateTo("Current Assignments");
            int assignments = driver.findElements(By.className("ls-assignment-item-detail-section")).size();
            if(assignments < 2)
            {
                Assert.fail("Assigned assignments are not present in Current Assignments");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in adaptiveAssessmentsInQuestionBank testcase in ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank class", e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBankAsMentor()
    {
        try
        {
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            int dataIndex = 41;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new LoginUsingLTI().ltiLogin("41"); // Login as instructor
            new Navigator().NavigateTo("Question Banks"); // Navigate to Question Bank
            // Verfiy question bank loaded
            boolean questionBanks = driver.findElement(By.cssSelector("a[title='Question Banks Only']")).isDisplayed();
            if(questionBanks == false)
            {
                Assert.fail("\"Question Banks\" Page is not displayed.");
            }
            // Drop down values
            List<WebElement> dropdownValues = driver.findElements(By.xpath(".//div[@id='ls-assessment-filter-questionset-drop-down-wrapper']//li/a"));
            System.out.println("dropdownValues.get(0).getText()::"+dropdownValues.get(0).getText());
            System.out.println("dropdownValues.get(1).getText()::"+dropdownValues.get(1).getText());

            if(!dropdownValues.get(0).getAttribute("title").trim().equals("Question Banks Only"))
            {
                Assert.fail("\"Question Banks Only\" is not available in the stop down list");
            }
            if(!dropdownValues.get(1).getAttribute("title").trim().equals("Practice Sets Only"))
            {
                Assert.fail("\"Practice Sets Only\" is not available in the stop down list");
            }
            // Click on Question Bank Only drop down
            driver.findElement(By.xpath(".//div[@id='ls-assessment-filter-questionset-drop-down-wrapper']/div/a")).click();
            // practice set Only
            boolean practiceSetOnly = driver.findElement(By.cssSelector("a[title='Practice Sets Only']")).isDisplayed();
            if(practiceSetOnly == false)
            {
                Assert.fail("\"Practice Sets Only\" is not available in the stop down list");
            }
            //click on practice set only
            driver.findElement(By.cssSelector("a[title='Practice Sets Only']")).click();
            Thread.sleep(1000);
            // Verify Static Assessment
            boolean assessment = true;
            List<WebElement> assessmentList = driver.findElements(By.className("resource-title"));
            for(int a = 0; a > assessmentList.size(); a++)
            {
                String nameOfAssessment = assessmentList.get(a).getAttribute("title");
                if(nameOfAssessment.trim().equals(assessmentname))
                {
                    System.out.println("nameOfAssessment::"+nameOfAssessment);
                    break;
                }
                else
                {
                    assessment = false;
                }
            }
            System.out.println("assessment::" + assessment);
            System.out.println("assessment::" + assessment);

            if(assessment == false)
            {
                Assert.fail("Created static assessment is not present in Question Banks");
            }
            // Verify Adaptive practice
            assessment = true;
            for(int a = 0; a > assessmentList.size(); a++)
            {
                String nameOfAssessment = assessmentList.get(a).getAttribute("title");
                if(nameOfAssessment.trim().contains("Personalized Practice - Ch"))
                {
                    System.out.println("nameOfAssessment::"+nameOfAssessment);
                    break;
                }
                else
                {
                    assessment = false;
                }
            }
            System.out.println("assessment::"+assessment);

            if(assessment == false)
            {
                Assert.fail("Adaptive Practice assessment is not present in Question Banks");
            }
            Thread.sleep(2000);
            //resource ocin
            boolean resourceIcon = driver.findElement(By.className("resource-icon")).isDisplayed();
            if(resourceIcon == false)
            {
                Assert.fail("Assessment Icon is not present");
            }
            // Question Card
            int questionCard = driver.findElements(By.id("ls-assessment-item-sectn-links-all-id")).size();
            // Actions
            int lsActions = driver.findElements(By.className("ls-actions")).size();
            if(lsActions != questionCard)
            {
                Assert.fail("LS Actions are not present for all the assessments");
            }
            String actionText = driver.findElement(By.className("ls-actions")).getText();
            if(!actionText.trim().equals("Actions :"))
            {
                Assert.fail("Label with Action is not present");
            }
            // Preview
            int lsPreview = driver.findElements(By.cssSelector("span[class='ls-preview-wrapper action-links']")).size();
            if(lsPreview != questionCard)
            {
                Assert.fail("Preview is not available for all assessments");
            }
            // Add to My Question Bank
            int addThisToMyResources = driver.findElements(By.cssSelector("i[class='addThisToMyResources not-bookmarked ls-resource-bookmark-icon']")).size();
            if(addThisToMyResources != questionCard)
            {
                Assert.fail("Add to My Question Bank is not available for all assessments");
            }
            // Assign This
            int assignThis = driver.findElements(By.cssSelector("span[class='resource-assign-class action-links']")).size();
            if(assignThis != questionCard)
            {
                Assert.fail("Assign This is not available for all assessments");
            }
            // Assign This
            int tryIt = driver.findElements(By.cssSelector("span[class='action-links try-it']")).size();
            if(tryIt != questionCard)
            {
                Assert.fail("Try It is not available for all assessments");
            }
            // Customize This
            int customizeThis = driver.findElements(By.cssSelector("i[class='customize-this ls-delete-this-customize-this-sprite']")).size();
            if(customizeThis > 0)
            {
                Assert.fail("Customize This option is available for Practice Sets");
            }
            // Click on preview
            assessmentList = driver.findElements(By.className("resource-title"));
            for(int a = 0; a < assessmentList.size(); a++)
            {
                String nameOfAssessment = assessmentList.get(a).getAttribute("title");
                List<WebElement> previewList = driver.findElements(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
                if(nameOfAssessment.trim().equals(assessmentname))
                {
                    previewList.get(a).click();
                    break;
                }
            }
            /*questionBank.getSearchInputFieldOnQuestionBank().get(1).click();
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys(assessmentname);
            questionBank.getSearchButtonOnQuestionBank().get(1).click();

            questionBank.preview.get(0).click();*/

            // New tab opened
            boolean newTabOfPreview = driver.findElement(By.cssSelector("span[class='tab_icon assignment-icon']")).isDisplayed();
            if(newTabOfPreview == false)
            {
                Assert.fail("Preview of static assessment is not opened in new tab");
            }
            driver.findElement(By.cssSelector("span[title='Question Banks']")).click(); // Navigate to Question Bank
            // Add To My Question Bank
            assessmentList = driver.findElements(By.className("resource-title"));
            for(int r = 0; r < assessmentList.size(); r++)
            {
                String nameOfAssessment = assessmentList.get(r).getAttribute("title");
                List<WebElement> addToMyQuestionBank = driver.findElements(By.cssSelector("i[class='addThisToMyResources not-bookmarked ls-resource-bookmark-icon']"));
                if(nameOfAssessment.trim().equals(assessmentname))
                {
                    addToMyQuestionBank.get(r).click();
                    break;
                }
            }
            driver.findElement(By.className("notification-close-btn-section")).click(); // close notification
            Thread.sleep(2000);
            // Click on My Question Bank
            driver.findElement(By.cssSelector("span[title='My Question Bank']")).click();
            String assessmentNameInQB = driver.findElement(By.className("resource-title")).getAttribute("title");
            if(!assessmentNameInQB.equals(assessmentname))
            {
                Assert.fail("Static assessment is not get added to My Question Bank tab");
            }
            // Click on Preview
            driver.findElement(By.cssSelector("span[class='ls-preview-wrapper action-links']")).click();
            Thread.sleep(2000);
            // New tab opened
            newTabOfPreview = driver.findElement(By.cssSelector("span[class='tab_icon assignment-icon']")).isDisplayed();
            if(newTabOfPreview == false)
            {
                Assert.fail("Preview of static assessment is not opened in new tab");
            }
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("span[title='My Question Bank']")).click();
            // Assign assessment from My question Bank
            driver.findElement(By.className("assign-this")).click();
            Thread.sleep(1000);
            new AssignLesson().assignTOCWithDefaultClassSection(41); // assign assessment
            new Navigator().NavigateTo("My Question Bank");
            String parentHandle = driver.getWindowHandle();
            driver.findElement(By.className("ls-try-it")).click(); // click on try it
            for(String windHandle : driver.getWindowHandles())
            {
                driver.switchTo().window(windHandle);
            }
            boolean newWindow = driver.findElement(By.id("cms-question-preview-header-ass-name")).isDisplayed();
            if(newWindow == false)
            {
                Assert.fail("On clicking Try it link, Assessment is not open in new window as product supports currently");
            }
            driver.close();
            driver.switchTo().window(parentHandle);
            Thread.sleep(1000);
            // Customize This
            questionBank.questionBankTab.click();
            questionBank.questionBankDropdown.click();
            questionBank.practiceSetsOnly.click();
            myQuestionBank.getMyQuestionBankTitle().click();
            Thread.sleep(2000);
            customizeThis = driver.findElements(By.cssSelector("i[class='customize-this ls-delete-this-customize-this-sprite']")).size();
            if(customizeThis > 0)
            {
                Assert.fail("Customize This option is available for Practice Sets");
            }
            //click on question bank
            questionBank.questionBankTab.click();
            Thread.sleep(2000);
            questionBank.assignThisIcon.get(1).click();
            boolean popup = questionBank.getPopupHeader().isDisplayed();
            if(popup == false)
            {
                Assert.fail("Assign pop-up is not displayed as the product supports currently");
            }
            new AssignLesson().assignTOCWithDefaultClassSection(41); // assign assessment
            new Navigator().NavigateTo("Question Banks");
            questionBank.questionBankDropdown.click();
            questionBank.practiceSetsOnly.click();
            questionBank.tryItLink.get(0).click();
            parentHandle = driver.getWindowHandle();
            for(String windHandle : driver.getWindowHandles())
            {
                driver.switchTo().window(windHandle);
            }
            newWindow = driver.findElement(By.id("cms-question-preview-header-ass-name")).isDisplayed();
            if(newWindow == false)
            {
                Assert.fail("On clicking Try it link, Assessment is not open in new window as product supports currently");
            }
            driver.close();
            driver.switchTo().window(parentHandle);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank testcase in ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank class", e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void adaptiveAssessmentsInQuestionBankAsMentor()
    {
        try
        {
            QuestionBank questionBank = PageFactory.initElements(driver,QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver,MyQuestionBank.class);
            new LoginUsingLTI().ltiLogin("41");
            new Navigator().NavigateTo("Question Banks");
            questionBank.questionBankDropdown.click(); // Click on question bank dropdown
            questionBank.practiceSetsOnly.click(); // Select practice Only
            int practiceTest = driver.findElements(By.xpath("//div[contains(text(),'Personalized Practice')]")).size();
            while(practiceTest == 0)
            {
                new ScrollElement().scrollBottomOfPage();
                practiceTest = driver.findElements(By.xpath("//div[contains(text(),'Personalized Practice')]")).size();
            }
            WebElement element = driver.findElement(By.xpath("//div[contains(text(),'Personalized Practice')]"));
            System.out.println("element::" + element.getAttribute("title"));
            new ScrollElement().scrollToViewOfElement(element);
           // questionBank.lsActions.get(0).getText().trim().equals("Actions :");
            Thread.sleep(2000);
            String lsActions = questionBank.lsActions.get(1).getText();
            System.out.println("lsActions::"+lsActions);
            if(!lsActions.trim().equals("Actions :"))
            {
                Assert.fail("The question card does not the lable \"Actions\"");
            }
            int assessmentsCount  = questionBank.assessmentName.size();
            int previewCount = questionBank.preview.size();
            int assignThisLinkCount = questionBank.assignThisLink.size();
            int addToMyQuestionBankCount = questionBank.addToMyQuestionBanks.size();
            int tryItCount = questionBank.tryItLink.size();
            if(previewCount != assessmentsCount && addToMyQuestionBankCount != assessmentsCount && assignThisLinkCount != assessmentsCount && tryItCount != assessmentsCount)
            {
                Assert.fail("Preview/addToMyQuestionBank/assignThisLink/tryIt is not present fo all Assessments");
            }
            // Customize this
            Assert.assertEquals(questionBank.customizeThisLink.size(),0,"\"Customize This\" option is available under Actions");
            List<WebElement> practiceTestsList = questionBank.assessmentName;
            for(int a = 0; a < practiceTestsList.size(); a++)
            {
                String practiceName = practiceTestsList.get(a).getAttribute("title");
                if(practiceName.trim().contains("Personalized Practice"))
                {
                    questionBank.preview.get(a).click(); // click on practice preview
                    Thread.sleep(2000);
                    questionBank.getQuestionBankTitle().click();
                    practiceTest = driver.findElements(By.xpath("//div[contains(text(),'Personalized Practice')]")).size();
                    while(practiceTest == 0)
                    {
                        new ScrollElement().scrollBottomOfPage();
                        practiceTest = driver.findElements(By.xpath("//div[contains(text(),'Personalized Practice')]")).size();
                    }
                    questionBank.addToMyQuestionBanks.get(a).click(); // add practice to My question bank
                    break;
                }
            }
            Assert.assertTrue(questionBank.assignmentIcon.isDisplayed(), "Preview of adaptive practice assessment is not opened in new tab from Question Bank"); // New assignment tab
            myQuestionBank.getMyQuestionBankTitle().click();
            Assert.assertTrue(myQuestionBank.assessmentName.get(0).getAttribute("title").contains("Personalized Practice"), "Adaptive assessment is not added to My Question Bank tab");
            // Click on preview
            myQuestionBank.previewLink.get(0).click();
            Thread.sleep(2000);
            Assert.assertTrue(myQuestionBank.assignmentIcon.isDisplayed(), "Preview of adaptive practice assessment is not opened in new tab from My Question Bank");
            myQuestionBank.getMyQuestionBankTitle().click();
            myQuestionBank.getAssignThis().click();
            new AssignLesson().assignTOCWithDefaultClassSection(41); // assign assessment
            new Navigator().NavigateTo("Question Banks");
            questionBank.questionBankDropdown.click();
            questionBank.practiceSetsOnly.click();
            myQuestionBank.getMyQuestionBankTitle().click();
            String parentHandle = driver.getWindowHandle();
            myQuestionBank.tryItIcon.get(0).click();
            for(String windHandle : driver.getWindowHandles())
            {
                driver.switchTo().window(windHandle);
            }
            boolean newWindow = driver.findElement(By.id("cms-question-preview-header-ass-name")).isDisplayed();
            if(newWindow == false)
            {
                Assert.fail("On clicking Try it link, Assessment is not open in new window as product supports currently");
            }
            driver.close();
            driver.switchTo().window(parentHandle);
            myQuestionBank.getMyQuestionBankTitle().click();
            System.out.println("aaaaa::" + myQuestionBank.customizeThisLink.size());
            Assert.assertEquals(myQuestionBank.customizeThisLink.size(), 0, "\"Customize This\" option is available under Actions"); // Customize option
            questionBank.questionBankTab.click();

            // Assign This
            practiceTestsList = questionBank.assessmentName;
            for(int a = 0; a < practiceTestsList.size(); a++)
            {
                String practiceName = practiceTestsList.get(a).getAttribute("title");
                if(practiceName.trim().contains("Personalized Practice"))
                {
                    questionBank.getSearchInputFieldOnQuestionBank().get(1).click();
                    questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys(practiceName);
                    questionBank.getSearchButtonOnQuestionBank().get(1).click();
                    break;
                }
            }
            questionBank.assignThisIcon.get(1).click();
            Assert.assertTrue(driver.findElement(By.id("ir-ls-assign-dialog")).isDisplayed(), "Assign pop-up is not displayed as the product supports currently for adaptive practice in TOC");
            new AssignLesson().assignTOCWithDefaultClassSection(40);
            new Navigator().NavigateTo("Question Banks");
            questionBank.questionBankDropdown.click();
            questionBank.practiceSetsOnly.click();
            // tryIt
            parentHandle = driver.getWindowHandle();
            practiceTestsList = questionBank.assessmentName;
            for(int a = 0; a < practiceTestsList.size(); a++)
            {
                String practiceName = practiceTestsList.get(a).getAttribute("title");
                if(practiceName.trim().contains("Personalized Practice"))
                {
                    questionBank.getSearchInputFieldOnQuestionBank().get(1).click();
                    questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys(practiceName);
                    questionBank.getSearchButtonOnQuestionBank().get(1).click();
                    break;
                }
            }
            questionBank.tryItLink.get(1).click();
            for(String windHandle : driver.getWindowHandles())
            {
                driver.switchTo().window(windHandle);
            }
            newWindow = driver.findElement(By.id("cms-question-preview-header-ass-name")).isDisplayed();
            if(newWindow == false)
            {
                Assert.fail("On clicking Try it link, Assessment is not open in new window as product supports currently");
            }
            driver.close();
            driver.switchTo().window(parentHandle);
            new Navigator().NavigateTo("Current Assignments");
            int assignments = driver.findElements(By.className("ls-assignment-item-detail-section")).size();
            if(assignments < 2)
            {
                Assert.fail("Assigned assignments are not present in Current Assignments");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in adaptiveAssessmentsInQuestionBankAsMentor testcase in ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank class", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void  viewLabelForStaticPracticeAssignmentQuestion()
    {
        try
        {
            int dataIndex = 42;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new LoginUsingLTI().ltiLogin("42"); // Login as instructor
            new Navigator().NavigateTo("My Question Bank");
            myQuestionBank.getCustomAssignmentButton().click(); // Click Create Custom Assignment link
            newAssignment.searchButton.click(); // click on search button
            newAssignment.searchTextArea.click(); // click on search text area
            newAssignment.searchTextArea.sendKeys(questiontext);// enter question text in search text area
            newAssignment.filter.click();//click on filter
            String checkLabelText = driver.findElement(By.cssSelector("span[data-localize='non-quarantined-question']")).getText();
            if(!checkLabelText.trim().equals("Show questions from e-Textbook"))
            {
                Assert.fail("The checkbox label “Instructor only Assessments” is not replaced with “Show questions from e-Textbook” in search filter");
            }
            // Check box not checked by default
            System.out.println("checked::" + driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size());
            Assert.assertEquals(0, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "By default,checkbox label is not unchecked.");
            // Cick on Go button
            newAssignment.go_button.click();
            // verify search result
            List<WebElement> searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            // Verify Questions from e-Textbook should not be displayed
            Assert.assertEquals(0, newAssignment.questionAvailableOntextbook.size(), "Questions from e-Textbook are displayed");
            // Click on search filter
            newAssignment.filter.click();
            // Search drop down
            Assert.assertTrue(driver.findElement(By.className("browse-left-filters")).isDisplayed(), "Search dropdown is not displayed");
            //click checkbox
            newAssignment.checkboxForInstructorAssignment.click();
            newAssignment.go_button.click(); // click on Go
            // verify Available in e-Textbook
            searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            int availableIneTextBook = newAssignment.questionAvailableOntextbook.size();
            if(availableIneTextBook == 0)
            {
                Assert.fail("All the questions relative to the Search is not displayed");
            }
            //label for static question Card
            String availableIneTextBookText = newAssignment.questionAvailableOntextbook.get(0).getText();
            if(!availableIneTextBookText.trim().equals("Available in e-Textbook"))
            {
                Assert.fail("“Available in e-Textbook”  label is not displayed");
            }
            // Browse button
            newAssignment.browseButton.click();
            // Search drop down
            Assert.assertTrue(driver.findElement(By.xpath("(.//*[@class='browse-left-filters'])[2]")).isDisplayed(), "Search dropdown is not displayed");


            checkLabelText = driver.findElement(By.xpath("(//span[@data-localize='non-quarantined-question'])[2]")).getText();
            if(!checkLabelText.trim().equals("Show questions from e-Textbook"))
            {
                Assert.fail("The checkbox label “Instructor only Assessments” is not replaced with “Show questions from e-Textbook” in search filter");
            }
            // Check box not checked by default
            System.out.println("checked::" + driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size());
            Assert.assertEquals(1, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "By default,checkbox label is not unchecked.");
            // Cick on Go button
            newAssignment.goButton.get(1).click();
            // verify search result
            searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            Thread.sleep(1000);
            // Verify Questions from e-Textbook should not be displayed
            System.out.println("questionAvailableOntextbook.size()::" + newAssignment.questionAvailableOntextbook.size());
            Assert.assertEquals(0, newAssignment.questionAvailableOntextbook.size(), "Questions from e-Textbook are displayed");
            // Click on search filter
            newAssignment.browseButton.click();
            // Search drop down
            Assert.assertTrue(driver.findElement(By.xpath("(.//*[@class='browse-left-filters'])[2]")).isDisplayed(), "Search dropdown is not displayed");
            //click checkbox
            List<WebElement> browseCheckBox = driver.findElements(By.className("ls-instructor-only-assignment-check"));
            browseCheckBox.get(1).click();
            newAssignment.goButton.get(1).click(); // click on Go
            // verify Available in e-Textbook
            searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            availableIneTextBook = newAssignment.questionAvailableOntextbook.size();
            if(availableIneTextBook == 0)
            {
                Assert.fail("All the questions relative to the Search is not displayed");
            }
            //label for static question Card
            availableIneTextBookText = newAssignment.questionAvailableOntextbook.get(0).getText();
            if(!availableIneTextBookText.trim().equals("Available in e-Textbook"))
            {
                Assert.fail("“Available in e-Textbook”  label is not displayed");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in viewLabelForStaticPracticeAssignmentQuestion testcase in ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank class", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void  viewLabelForStaticPracticeAssignmentQuestionAsMentor()
    {
        try
        {
            int dataIndex = 68;
            String questiontext = ReadTestData.readDataByTagName("","questiontext",Integer.toString(dataIndex));
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver,MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver,NewAssignment.class);
            new LoginUsingLTI().ltiLogin("68"); // Login as instructor
            new Navigator().NavigateTo("My Question Bank");
            myQuestionBank.getCustomAssignmentButton().click(); // Click Create Custom Assignment link
            newAssignment.searchButton.click(); // click on search button
            newAssignment.searchTextArea.click(); // click on search text area
            newAssignment.searchTextArea.sendKeys(questiontext);// enter question text in search text area
            newAssignment.filter.click();//click on filter
            String checkLabelText = driver.findElement(By.cssSelector("span[data-localize='non-quarantined-question']")).getText();
            if(!checkLabelText.trim().equals("Show questions from e-Textbook"))
            {
                Assert.fail("The checkbox label “Instructor only Assessments” is not replaced with “Show questions from e-Textbook” in search filter");
            }
            // Check box not checked by default
            System.out.println("checked::"+driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size());
            Assert.assertEquals(0,driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(),"By default,checkbox label is not unchecked.");
            // Click on Go button
            newAssignment.go_button.click();
            // verify search result
            List<WebElement> searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            // Verify Questions from e-Textbook should not be displayed
            Assert.assertEquals(0,newAssignment.questionAvailableOntextbook.size(),"Questions from e-Textbook are displayed");
            // Click on search filter
            newAssignment.filter.click();
            // Search drop down
            Assert.assertTrue(driver.findElement(By.className("browse-left-filters")).isDisplayed(),"Search dropdown is not displayed");
            //click checkbox
            newAssignment.checkboxForInstructorAssignment.click();
            newAssignment.go_button.click(); // click on Go
            // verify Available in e-Textbook
            searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            int availableIneTextBook = newAssignment.questionAvailableOntextbook.size();
            if(availableIneTextBook == 0)
            {
                Assert.fail("All the questions relative to the Search is not displayed");
            }
            //label for static question Card
            String availableIneTextBookText = newAssignment.questionAvailableOntextbook.get(0).getText();
            if(!availableIneTextBookText.trim().equals("Available in e-Textbook"))
            {
                Assert.fail("“Available in e-Textbook”  label is not displayed");
            }
            // Browse button
            newAssignment.browseButton.click();
            // Search drop down
            Assert.assertTrue(driver.findElement(By.xpath("(.//*[@class='browse-left-filters'])[2]")).isDisplayed(), "Search dropdown is not displayed");


            checkLabelText = driver.findElement(By.xpath("(//span[@data-localize='non-quarantined-question'])[2]")).getText();
            if(!checkLabelText.trim().equals("Show questions from e-Textbook"))
            {
                Assert.fail("The checkbox label “Instructor only Assessments” is not replaced with “Show questions from e-Textbook” in search filter");
            }
            // Check box not checked by default
            System.out.println("checked::"+driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size());
            Assert.assertEquals(1, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "By default,checkbox label is not unchecked.");
            // Cick on Go button
            newAssignment.goButton.get(1).click();
            // verify search result
            searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            Thread.sleep(1000);
            // Verify Questions from e-Textbook should not be displayed
            System.out.println("questionAvailableOntextbook.size()::"+newAssignment.questionAvailableOntextbook.size());
            Assert.assertEquals(0, newAssignment.questionAvailableOntextbook.size(), "Questions from e-Textbook are displayed");
            // Click on search filter
            newAssignment.browseButton.click();
            // Search drop down
            Assert.assertTrue(driver.findElement(By.xpath("(.//*[@class='browse-left-filters'])[2]")).isDisplayed(), "Search dropdown is not displayed");
            //click checkbox
            List<WebElement> browseCheckBox = driver.findElements(By.className("ls-instructor-only-assignment-check"));
            browseCheckBox.get(1).click();
            newAssignment.goButton.get(1).click(); // click on Go
            // verify Available in e-Textbook
            searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            availableIneTextBook = newAssignment.questionAvailableOntextbook.size();
            if(availableIneTextBook == 0)
            {
                Assert.fail("All the questions relative to the Search is not displayed");
            }
            //label for static question Card
            availableIneTextBookText = newAssignment.questionAvailableOntextbook.get(0).getText();
            if(!availableIneTextBookText.trim().equals("Available in e-Textbook"))
            {
                Assert.fail("“Available in e-Textbook”  label is not displayed");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in viewLabelForStaticPracticeAssignmentQuestionAsMentor testcase in ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank class", e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void staticAndAdaptivePracticeQuestionsInEditCustomAssessmentFlow()
    {
        try
        {
            int dataIndex = 69;
            String searchquestion = ReadTestData.readDataByTagName("","searchquestion",Integer.toString(dataIndex));
            String customassignmentname = ReadTestData.readDataByTagName("","customassignmentname",Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("","questiontext",Integer.toString(dataIndex));

            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver,NewAssignment.class);

            new LoginUsingLTI().ltiLogin("69");
            new Navigator().NavigateTo("My Question Bank");
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("69");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            // Save assignment Notification message
            Assert.assertTrue(newAssignment.notification_message.isDisplayed(), "\"Saved Custom Assignment Successfully\" message is not displayed at the top of the screen");
            // Close assignment tab
            newAssignment.close_Icon.click();
            // My Question Bank
            myQuestionBank.getMyQuestionBankTitle().click();
            myQuestionBank.editThis.click();
            newAssignment.getFindQuestion().click();
            newAssignment.searchButton.click(); // click on search button
            newAssignment.searchTextArea.click(); // click on search text area
            newAssignment.searchTextArea.sendKeys(questiontext);// enter question text in search text area
            newAssignment.filter.click();//click on filter
            Thread.sleep(2000);
            String checkLabelText = driver.findElement(By.cssSelector("span[data-localize='non-quarantined-question']")).getText();
            System.out.println("checkLabelText::"+checkLabelText);
            if(!checkLabelText.trim().equals("Show questions from e-Textbook"))
            {
                Assert.fail("The checkbox label “Instructor only Assessments” is not replaced with “Show questions from e-Textbook” in search filter");
            }
            // Check box not checked by default
            System.out.println("checked::" + driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size());
            Assert.assertEquals(0, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "By default,checkbox label is not unchecked.");
            // Cick on Go button
            newAssignment.go_button.click();
            // verify search result
            List<WebElement> searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            // Verify Questions from e-Textbook should not be displayed
            Assert.assertEquals(0, newAssignment.questionAvailableOntextbook.size(), "Questions from e-Textbook are displayed");
            // Click on search filter
            newAssignment.filter.click();
            // Search drop down
            Assert.assertTrue(driver.findElement(By.className("browse-left-filters")).isDisplayed(), "Search dropdown is not displayed");
            //click checkbox
            newAssignment.checkboxForInstructorAssignment.click();
            // Check box checked
            Assert.assertEquals(1, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "Click on the checkbox in the Pop-up, Check-box is not checked");

            newAssignment.go_button.click(); // click on Go
            // verify Available in e-Textbook
            searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            int availableIneTextBook = newAssignment.questionAvailableOntextbook.size();
            if(availableIneTextBook == 0)
            {
                Assert.fail("All the questions relative to the Search is not displayed");
            }
            //label for static question Card
            String availableIneTextBookText = newAssignment.questionAvailableOntextbook.get(0).getText();
            if(!availableIneTextBookText.trim().equals("Available in e-Textbook"))
            {
                Assert.fail("“Available in e-Textbook”  label is not displayed");
            }
            // Browse button
            newAssignment.browseButton.click();
            // Search drop down
            Assert.assertTrue(driver.findElement(By.xpath("(.//*[@class='browse-left-filters'])[2]")).isDisplayed(), "Search dropdown is not displayed");
            checkLabelText = driver.findElement(By.xpath("(//span[@data-localize='non-quarantined-question'])[2]")).getText();
            if(!checkLabelText.trim().equals("Show questions from e-Textbook")) {
                Assert.fail("The checkbox label “Instructor only Assessments” is not replaced with “Show questions from e-Textbook” in search filter");
            }
            // Check box not checked by default
            System.out.println("checked::" + driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size());
            Assert.assertEquals(1, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "By default,checkbox label is not unchecked.");
            // Cick on Go button
            newAssignment.goButton.get(1).click();
            // verify search result
            searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            Thread.sleep(1000);
            // Verify Questions from e-Textbook should not be displayed
            System.out.println("questionAvailableOntextbook.size()::" + newAssignment.questionAvailableOntextbook.size());
            Assert.assertEquals(0, newAssignment.questionAvailableOntextbook.size(), "Questions from e-Textbook are displayed");
            // Click on search filter
            newAssignment.browseButton.click();
            // Search drop down
            Assert.assertTrue(driver.findElement(By.xpath("(.//*[@class='browse-left-filters'])[2]")).isDisplayed(), "Browse dropdown is not displayed");
            //click checkbox
            List<WebElement> browseCheckBox = driver.findElements(By.className("ls-instructor-only-assignment-check"));
            browseCheckBox.get(1).click();
            Assert.assertEquals(2, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "Click on the checkbox in the Pop-up, Check-box is not checked");
            newAssignment.goButton.get(1).click(); // click on Go
            // verify Available in e-Textbook
            searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            availableIneTextBook = newAssignment.questionAvailableOntextbook.size();
            if(availableIneTextBook == 0)
            {
                Assert.fail("All the questions relative to the Search is not displayed");
            }
            //label for static question Card
            availableIneTextBookText = newAssignment.questionAvailableOntextbook.get(0).getText();
            if(!availableIneTextBookText.trim().equals("Available in e-Textbook"))
            {
                Assert.fail("“Available in e-Textbook”  label is not displayed");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in staticAndAdaptivePracticeQuestionsInEditCustomAssessmentFlow testcase in ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank class", e);
        }
    }

    @Test(priority = 8,enabled = true)
    public void staticAndAdaptivePracticeQuestionsInEditCustomAssessmentFlowAsMentor()
    {
        try
        {
            int dataIndex = 96;
            String searchquestion = ReadTestData.readDataByTagName("","searchquestion",Integer.toString(dataIndex));
            String customassignmentname = ReadTestData.readDataByTagName("","customassignmentname",Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("","questiontext",Integer.toString(dataIndex));

            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver,NewAssignment.class);

            new LoginUsingLTI().ltiLogin("96");
            new Navigator().NavigateTo("My Question Bank");
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("96");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            // Save assignment Notification message
            Assert.assertTrue(newAssignment.notification_message.isDisplayed(), "\"Saved Custom Assignment Successfully\" message is not displayed at the top of the screen");
            // Close assignment tab
            newAssignment.close_Icon.click();
            // My Question Bank
            myQuestionBank.getMyQuestionBankTitle().click();
            myQuestionBank.editThis.click();
            newAssignment.getFindQuestion().click();
            newAssignment.searchButton.click(); // click on search button
            newAssignment.searchTextArea.click(); // click on search text area
            newAssignment.searchTextArea.sendKeys(questiontext);// enter question text in search text area
            newAssignment.filter.click();//click on filter
            Thread.sleep(2000);
            String checkLabelText = driver.findElement(By.cssSelector("span[data-localize='non-quarantined-question']")).getText();
            System.out.println("checkLabelText::"+checkLabelText);
            if(!checkLabelText.trim().equals("Show questions from e-Textbook"))
            {
                Assert.fail("The checkbox label “Instructor only Assessments” is not replaced with “Show questions from e-Textbook” in search filter");
            }
            // Check box not checked by default
            System.out.println("checked::" + driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size());
            Assert.assertEquals(0, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "By default,checkbox label is not unchecked.");
            // Cick on Go button
            newAssignment.go_button.click();
            // verify search result
            List<WebElement> searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            // Verify Questions from e-Textbook should not be displayed
            Assert.assertEquals(0, newAssignment.questionAvailableOntextbook.size(), "Questions from e-Textbook are displayed");
            // Click on search filter
            newAssignment.filter.click();
            // Search drop down
            Assert.assertTrue(driver.findElement(By.className("browse-left-filters")).isDisplayed(), "Search dropdown is not displayed");
            //click checkbox
            newAssignment.checkboxForInstructorAssignment.click();
            // Check box checked
            Assert.assertEquals(1, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "Click on the checkbox in the Pop-up, Check-box is not checked");

            newAssignment.go_button.click(); // click on Go
            // verify Available in e-Textbook
            searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            int availableIneTextBook = newAssignment.questionAvailableOntextbook.size();
            if(availableIneTextBook == 0)
            {
                Assert.fail("All the questions relative to the Search is not displayed");
            }
            //label for static question Card
            String availableIneTextBookText = newAssignment.questionAvailableOntextbook.get(0).getText();
            if(!availableIneTextBookText.trim().equals("Available in e-Textbook"))
            {
                Assert.fail("“Available in e-Textbook”  label is not displayed");
            }
            // Browse button
            newAssignment.browseButton.click();
            // Search drop down
            Assert.assertTrue(driver.findElement(By.xpath("(.//*[@class='browse-left-filters'])[2]")).isDisplayed(), "Search dropdown is not displayed");
            checkLabelText = driver.findElement(By.xpath("(//span[@data-localize='non-quarantined-question'])[2]")).getText();
            if(!checkLabelText.trim().equals("Show questions from e-Textbook")) {
                Assert.fail("The checkbox label “Instructor only Assessments” is not replaced with “Show questions from e-Textbook” in search filter");
            }
            // Check box not checked by default
            System.out.println("checked::" + driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size());
            Assert.assertEquals(1, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "By default,checkbox label is not unchecked.");
            // Cick on Go button
            newAssignment.goButton.get(1).click();
            // verify search result
            searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            Thread.sleep(1000);
            // Verify Questions from e-Textbook should not be displayed
            System.out.println("questionAvailableOntextbook.size()::" + newAssignment.questionAvailableOntextbook.size());
            Assert.assertEquals(0, newAssignment.questionAvailableOntextbook.size(), "Questions from e-Textbook are displayed");
            // Click on search filter
            newAssignment.browseButton.click();
            // Search drop down
            Assert.assertTrue(driver.findElement(By.xpath("(.//*[@class='browse-left-filters'])[2]")).isDisplayed(), "Browse dropdown is not displayed");
            //click checkbox
            List<WebElement> browseCheckBox = driver.findElements(By.className("ls-instructor-only-assignment-check"));
            browseCheckBox.get(1).click();
            Assert.assertEquals(2, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "Click on the checkbox in the Pop-up, Check-box is not checked");
            newAssignment.goButton.get(1).click(); // click on Go
            // verify Available in e-Textbook
            searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            availableIneTextBook = newAssignment.questionAvailableOntextbook.size();
            if(availableIneTextBook == 0)
            {
                Assert.fail("All the questions relative to the Search is not displayed");
            }
            //label for static question Card
            availableIneTextBookText = newAssignment.questionAvailableOntextbook.get(0).getText();
            if(!availableIneTextBookText.trim().equals("Available in e-Textbook"))
            {
                Assert.fail("“Available in e-Textbook”  label is not displayed");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in staticAndAdaptivePracticeQuestionsInEditCustomAssessmentFlowAsMentor testcase in ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank class", e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void instructorLogin()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("99");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in instructorLogin testcase in ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank class", e);
        }
    }
    @Test(priority = 10,enabled = true)
    public void staticAdaptivePracticeQuestionsSharedFromOtherInstructors()
    {
        try
        {

            MyQuestionBank myQuestionBank = PageFactory.initElements(driver,MyQuestionBank.class);
            Dashboard dashboard = PageFactory.initElements(driver,Dashboard.class);
            new LoginUsingLTI().ltiLogin("97");
            new Navigator().NavigateTo("My Question Bank");
            // ls Actions
            String lsActions = myQuestionBank.lsActions.get(0).getText();
            if(!lsActions.trim().equals("Actions :"))
            {
                Assert.fail("The Custom Assessment not contain \"Actions\" label");
            }
            Assert.assertTrue(myQuestionBank.getPreviewButton().isDisplayed(),"Preview Button is not displayed for Custom Assignment"); // Preview
            Assert.assertTrue(myQuestionBank.shareThis.isDisplayed(),"Share This option is not displayed for Custom Assignment"); // Share This
            Assert.assertTrue(myQuestionBank.getAssignThis().isDisplayed(),"Assign This option is not displayed for Custom Assignment");// Assign This
            Assert.assertTrue(myQuestionBank.editThis.isDisplayed(),"Edit This option is not displayed for Custom Assignment");// Edit this
            Assert.assertTrue(myQuestionBank.getDeleteButton().isDisplayed(), "Delete This option is not displayed for Custom Assignment");// Delete this
            Assert.assertTrue(myQuestionBank.tryItIcon.get(0).isDisplayed(), "tryIt option is not displayed for Custom Assignment");// try it
            myQuestionBank.shareThis.click();// Click  on share this
            Assert.assertTrue(myQuestionBank.shareWithPopUp.isDisplayed(), "A \"Share With\" Pop-up is not appear below the \"Share this\" link.");// Share with popUp
            // Share Custom Assignment
            int dataIndex = 99;
            String givenname = ReadTestData.readDataByTagName("","givenname",Integer.toString(dataIndex));
            myQuestionBank.shareTextBox.click();
            Thread.sleep(1000);
            System.out.println("Config.givenname::" + givenname);
            myQuestionBank.shareTextBox.sendKeys(givenname);
            Thread.sleep(2000);
            myQuestionBank.shareResult.get(0).click();
            Thread.sleep(1000);
            myQuestionBank.shareButton.click();
            myQuestionBank.yesOnShareMessage.click();
            myQuestionBank.shareSuccessNotificationClose.click();
        }
        catch (Exception e)
        {
            Assert.fail("Exception in staticAdaptivePracticeQuestionsSharedFromOtherInstructors testcase in ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank class", e);
        }
    }

    // Test Case : 101
    @Test(priority = 11,enabled = true)
    public void verifySharedCustomAssignment()
    {
        try
        {
            int dataIndex = 69;
            String customassignmentname = ReadTestData.readDataByTagName("","customassignmentname",Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("","questiontext",Integer.toString(dataIndex));

            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver,NewAssignment.class);

            new LoginUsingLTI().ltiLogin("99");
            new Navigator().NavigateTo("My Question Bank");
            // Custom Assignment available
            String customAssignment = myQuestionBank.assessmentName.get(0).getText();
            System.out.println("customAssignment::" + customAssignment);

            if(!customAssignment.trim().equals(customassignmentname))
            {
                Assert.fail("Shared custom Assignment is not available");
            }
            myQuestionBank.customizeThis.click(); // Click on Customize This
            Assert.assertTrue(newAssignment.tabTittle.get(0).isDisplayed(), "The particular Custom Assessment is not opened in new tab."); // New tab opened
            // Available in e-Textbook
            int availableIneTextBookText = newAssignment.questionAvailableOntextbook.size();
            if(availableIneTextBookText > 0)
            {
                Assert.fail("“Available in e-Textbook” label is displayed");
            }
            // Search/browse
            newAssignment.searchButton.click(); // click on search button
            newAssignment.searchTextArea.click(); // click on search text area
            newAssignment.searchTextArea.sendKeys(questiontext);// enter question text in search text area
            newAssignment.filter.click();//click on filter
            Thread.sleep(2000);
            // Search drop down
            Assert.assertTrue(driver.findElement(By.className("browse-left-filters")).isDisplayed(), "Search popup is not displayed");
            // Show questions from e-Textbook
            String checkLabelText = driver.findElement(By.cssSelector("span[data-localize='non-quarantined-question']")).getText();
            System.out.println("checkLabelText::" + checkLabelText);
            if(!checkLabelText.trim().equals("Show questions from e-Textbook"))
            {
                Assert.fail("The checkbox label “Instructor only Assessments” is not replaced with “Show questions from e-Textbook” in search filter");
            }
            // Check box not checked by default
            System.out.println("checked::" + driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size());
            Assert.assertEquals(0, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "By default,checkbox label is not unchecked.");
            //click checkbox
            newAssignment.checkboxForInstructorAssignment.click();
            // Check box checked
            Assert.assertEquals(1, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "Click on the checkbox in the Pop-up, Check-box is not checked");

            newAssignment.go_button.click(); // click on Go
            // verify Available in e-Textbook
            List<WebElement> searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            int availableIneTextBook = newAssignment.questionAvailableOntextbook.size();
            if(availableIneTextBook == 0)
            {
                Assert.fail("All the questions relative to the Search is not displayed");
            }

            //label for static question Card
            String availableIneTextBookTextbrowse = newAssignment.questionAvailableOntextbook.get(0).getText();
            if(!availableIneTextBookTextbrowse.trim().equals("Available in e-Textbook"))
            {
                Assert.fail("“Available in e-Textbook”  label is not displayed");
            }
            // Browse button
            newAssignment.browseButton.click();
            // Search drop down
            Assert.assertTrue(driver.findElement(By.xpath("(.//*[@class='browse-left-filters'])[2]")).isDisplayed(), "Search dropdown is not displayed");
            checkLabelText = driver.findElement(By.xpath("(//span[@data-localize='non-quarantined-question'])[2]")).getText();
            if(!checkLabelText.trim().equals("Show questions from e-Textbook")) {
                Assert.fail("The checkbox label “Instructor only Assessments” is not replaced with “Show questions from e-Textbook” in search filter");
            }
            // Check box not checked by default
            System.out.println("checked::" + driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size());
            Assert.assertEquals(1, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "By default,checkbox label is not unchecked.");
            // Cick on Go button
            newAssignment.goButton.get(1).click();
            // verify search result
            searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            Thread.sleep(1000);
            // Verify Questions from e-Textbook should not be displayed
            System.out.println("questionAvailableOntextbook.size()::" + newAssignment.questionAvailableOntextbook.size());
            Assert.assertEquals(0, newAssignment.questionAvailableOntextbook.size(), "Questions from e-Textbook are displayed");
            // Click on search filter
            newAssignment.browseButton.click();
            // Search drop down
            Assert.assertTrue(driver.findElement(By.xpath("(.//*[@class='browse-left-filters'])[2]")).isDisplayed(), "Browse dropdown is not displayed");
            //click checkbox
            List<WebElement> browseCheckBox = driver.findElements(By.className("ls-instructor-only-assignment-check"));
            browseCheckBox.get(1).click();
            Assert.assertEquals(2, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "Click on the checkbox in the Pop-up, Check-box is not checked");
            newAssignment.goButton.get(1).click(); // click on Go
            // verify Available in e-Textbook
            searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            availableIneTextBook = newAssignment.questionAvailableOntextbook.size();
            if(availableIneTextBook == 0)
            {
                Assert.fail("All the questions relative to the Search is not displayed");
            }
            //label for static question Card
            availableIneTextBookTextbrowse = newAssignment.questionAvailableOntextbook.get(0).getText();
            if(!availableIneTextBookTextbrowse.trim().equals("Available in e-Textbook"))
            {
                Assert.fail("“Available in e-Textbook”  label is not displayed");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in verifySharedCustomAssignment testcase in ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank class", e);
        }
    }

    @Test(priority = 12,enabled = true)
    public void mentorLogin()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("110");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in mentorLogin testcase in ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank class", e);
        }
    }

    @Test(priority = 13,enabled = true)
    public void staticAdaptivePracticeQuestionsSharedFromOtherMentors()
    {
        try
        {

            MyQuestionBank myQuestionBank = PageFactory.initElements(driver,MyQuestionBank.class);
            Dashboard dashboard = PageFactory.initElements(driver,Dashboard.class);
            new LoginUsingLTI().ltiLogin("96");
            new Navigator().NavigateTo("My Question Bank");
            // ls Actions
            String lsActions = myQuestionBank.lsActions.get(0).getText();
            if(!lsActions.trim().equals("Actions :"))
            {
                Assert.fail("The Custom Assessment not contain \"Actions\" label");
            }
            Assert.assertTrue(myQuestionBank.getPreviewButton().isDisplayed(),"Preview Button is not displayed for Custom Assignment"); // Preview
            Assert.assertTrue(myQuestionBank.shareThis.isDisplayed(),"Share This option is not displayed for Custom Assignment"); // Share This
            Assert.assertTrue(myQuestionBank.getAssignThis().isDisplayed(),"Assign This option is not displayed for Custom Assignment");// Assign This
            Assert.assertTrue(myQuestionBank.editThis.isDisplayed(),"Edit This option is not displayed for Custom Assignment");// Edit this
            Assert.assertTrue(myQuestionBank.getDeleteButton().isDisplayed(), "Delete This option is not displayed for Custom Assignment");// Delete this
            Assert.assertTrue(myQuestionBank.tryItIcon.get(0).isDisplayed(), "tryIt option is not displayed for Custom Assignment");// try it
            myQuestionBank.shareThis.click();// Click  on share this
            Assert.assertTrue(myQuestionBank.shareWithPopUp.isDisplayed(), "A \"Share With\" Pop-up is not appear below the \"Share this\" link.");// Share with popUp
            // Share Custom Assignment
            int dataIndex = 110;
            String givenname = ReadTestData.readDataByTagName("","givenname",Integer.toString(dataIndex));
            myQuestionBank.shareTextBox.click();
            Thread.sleep(1000);
            System.out.println("Config.givenname::" + givenname);
            myQuestionBank.shareTextBox.sendKeys(givenname);
            Thread.sleep(2000);
            myQuestionBank.shareResult.get(0).click();
            Thread.sleep(1000);
            myQuestionBank.shareButton.click();
            myQuestionBank.yesOnShareMessage.click();
            myQuestionBank.shareSuccessNotificationClose.click();
        }
        catch (Exception e)
        {
            Assert.fail("Exception in staticAdaptivePracticeQuestionsSharedFromOtherMentors testcase in ViewAndUseStaticAndAdaptiveAssessmentsInQuestionBank class", e);
        }
    }

    // 110
    @Test(priority = 14,enabled = true)
    public void verifySharedCustomAssignmentAsMentor()
    {
        try
        {
            int dataIndex = 69;
            String customassignmentname = ReadTestData.readDataByTagName("","customassignmentname",Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("","questiontext",Integer.toString(dataIndex));

            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver,NewAssignment.class);

            new LoginUsingLTI().ltiLogin("110");
            new Navigator().NavigateTo("My Question Bank");
            // Custom Assignment available
            String customAssignment = myQuestionBank.assessmentName.get(0).getText();
            System.out.println("customAssignment::" + customAssignment);

            if(!customAssignment.trim().equals(customassignmentname))
            {
                Assert.fail("Shared custom Assignment is not available");
            }
            myQuestionBank.customizeThis.click(); // Click on Customize This
            Assert.assertTrue(newAssignment.tabTittle.get(0).isDisplayed(), "The particular Custom Assessment is not opened in new tab."); // New tab opened
            // Available in e-Textbook
            int availableIneTextBookText = newAssignment.questionAvailableOntextbook.size();
            if(availableIneTextBookText > 0)
            {
                Assert.fail("“Available in e-Textbook” label is displayed");
            }
            // Search/browse
            newAssignment.searchButton.click(); // click on search button
            newAssignment.searchTextArea.click(); // click on search text area
            newAssignment.searchTextArea.sendKeys(questiontext);// enter question text in search text area
            newAssignment.filter.click();//click on filter
            Thread.sleep(2000);
            // Search drop down
            Assert.assertTrue(driver.findElement(By.className("browse-left-filters")).isDisplayed(), "Search popup is not displayed");
            // Show questions from e-Textbook
            String checkLabelText = driver.findElement(By.cssSelector("span[data-localize='non-quarantined-question']")).getText();
            System.out.println("checkLabelText::" + checkLabelText);
            if(!checkLabelText.trim().equals("Show questions from e-Textbook"))
            {
                Assert.fail("The checkbox label “Instructor only Assessments” is not replaced with “Show questions from e-Textbook” in search filter");
            }
            // Check box not checked by default
            System.out.println("checked::" + driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size());
            Assert.assertEquals(0, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "By default,checkbox label is not unchecked.");
            //click checkbox
            newAssignment.checkboxForInstructorAssignment.click();
            // Check box checked
            Assert.assertEquals(1, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "Click on the checkbox in the Pop-up, Check-box is not checked");

            newAssignment.go_button.click(); // click on Go
            // verify Available in e-Textbook
            List<WebElement> searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            int availableIneTextBook = newAssignment.questionAvailableOntextbook.size();
            if(availableIneTextBook == 0)
            {
                Assert.fail("All the questions relative to the Search is not displayed");
            }

            //label for static question Card
            String availableIneTextBookTextbrowse = newAssignment.questionAvailableOntextbook.get(0).getText();
            if(!availableIneTextBookTextbrowse.trim().equals("Available in e-Textbook"))
            {
                Assert.fail("“Available in e-Textbook”  label is not displayed");
            }
            // Browse button
            newAssignment.browseButton.click();
            // Search drop down
            Assert.assertTrue(driver.findElement(By.xpath("(.//*[@class='browse-left-filters'])[2]")).isDisplayed(), "Search dropdown is not displayed");
            checkLabelText = driver.findElement(By.xpath("(//span[@data-localize='non-quarantined-question'])[2]")).getText();
            if(!checkLabelText.trim().equals("Show questions from e-Textbook")) {
                Assert.fail("The checkbox label “Instructor only Assessments” is not replaced with “Show questions from e-Textbook” in search filter");
            }
            // Check box not checked by default
            System.out.println("checked::" + driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size());
            Assert.assertEquals(1, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "By default,checkbox label is not unchecked.");
            // Cick on Go button
            newAssignment.goButton.get(1).click();
            // verify search result
            searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            Thread.sleep(1000);
            // Verify Questions from e-Textbook should not be displayed
            System.out.println("questionAvailableOntextbook.size()::" + newAssignment.questionAvailableOntextbook.size());
            Assert.assertEquals(0, newAssignment.questionAvailableOntextbook.size(), "Questions from e-Textbook are displayed");
            // Click on search filter
            newAssignment.browseButton.click();
            // Search drop down
            Assert.assertTrue(driver.findElement(By.xpath("(.//*[@class='browse-left-filters'])[2]")).isDisplayed(), "Browse dropdown is not displayed");
            //click checkbox
            List<WebElement> browseCheckBox = driver.findElements(By.className("ls-instructor-only-assignment-check"));
            browseCheckBox.get(1).click();
            Assert.assertEquals(2, driver.findElements(By.cssSelector("label[class='ls-instructor-only-assignment-check checked']")).size(), "Click on the checkbox in the Pop-up, Check-box is not checked");
            newAssignment.goButton.get(1).click(); // click on Go
            // verify Available in e-Textbook
            searchResult = newAssignment.searchText;
            for(int i = 0; i > searchResult.size(); i++)
            {
                String questionText = searchResult.get(i).getText();
                if(!questionText.trim().contains("test") || !questionText.trim().contains("Question"))
                {
                    Assert.fail("All the questions related to the Search text are not dispalyed under \"Questions Found\" Section");
                }
            }
            availableIneTextBook = newAssignment.questionAvailableOntextbook.size();
            if(availableIneTextBook == 0)
            {
                Assert.fail("All the questions relative to the Search is not displayed");
            }
            //label for static question Card
            availableIneTextBookTextbrowse = newAssignment.questionAvailableOntextbook.get(0).getText();
            if(!availableIneTextBookTextbrowse.trim().equals("Available in e-Textbook"))
            {
                Assert.fail("“Available in e-Textbook”  label is not displayed");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in verifySharedCustomAssignmentAsMentor testcase in verifySharedCustomAssignmentAsmentor class", e);
        }
    }

    @Test(priority = 15,enabled = true)
    public void  viewHintAndSolutionForAnAssignment()
    {
        try
        {
            int dataIndex = 111;
            String assessmentname = ReadTestData.readDataByTagName("","assessmentname",Integer.toString(dataIndex));
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new Assignment().create(111);
            new Assignment().addQuestions(112, "essay", assessmentname);
            new Assignment().addQuestions(113,"essay",assessmentname);
            new LoginUsingLTI().ltiLogin("111");
            new Navigator().NavigateTo("Question Banks");
            questionBank.getSearchInputFieldOnQuestionBank().get(1).click();
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys(assessmentname);
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            questionBank.assignThisLink.get(0).click();
            new AssignLesson().assignTOCWithDefaultClassSection(111);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in viewHintAndSolutionForAnAssignment testcase in verifySharedCustomAssignmentAsmentor class", e);
        }
    }

    @Test(priority = 16,enabled = true)
    public void  viewHintAndSolutionForAnAssignmentAsStudent()
    {
        try
        {
            int dataIndex = 111;
            String assessmentname = ReadTestData.readDataByTagName("","assessmentname",Integer.toString(dataIndex));
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new LoginUsingLTI().ltiLogin("112");
            new Navigator().NavigateTo("Assignments");
            assignments.assignmentName.click();
            // Complete the test
            int confidenceLvel = driver.findElements(By.className("confidence-level-block")).size();
            while (confidenceLvel > 0)
            {
                int trueOrFalse = driver.findElements(By.className("true-false-answer-choices")).size();
                if(trueOrFalse > 0)
                {
                    List<WebElement> choices = driver.findElements(By.className("true-false-student-answer-select"));
                    choices.get(0).click();
                }
                // essay qs
                int essayQs = driver.findElements(By.id("html-editor-non-draggable")).size();
                if(essayQs > 0)
                {
                    driver.findElement(By.id("html-editor-non-draggable")).click();
                    driver.findElement(By.id("html-editor-non-draggable")).sendKeys("Essay Answer 1234545");
                }
                // Click on Next question
                int nextQuestion = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size();
                if(nextQuestion > 0)
                {
                    driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).click();
                }
                // Click on submit question
                int submitQuestion = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).size();
                if(submitQuestion > 0)
                {
                    driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).click();
                }
                confidenceLvel = driver.findElements(By.className("confidence-level-block")).size();
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in viewHintAndSolutionForAnAssignmentAsStudent testcase in verifySharedCustomAssignmentAsmentor class", e);
        }
    }

    @Test(priority = 17,enabled = true)
    public void  instructorViewHintAndSolutionForAnAssignment()
    {
        try
        {
            int dataIndex = 111;
            String assessmentname = ReadTestData.readDataByTagName("","assessmentname",Integer.toString(dataIndex));
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            // Login as mentor
            // Login as instructor
            new LoginUsingLTI().ltiLogin("111");
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click(); // View Student Responses
            Assert.assertTrue(assignmentResponsesPage.getPageTitle().isDisplayed(),"\"Assignment Responses\"  page is not displayed"); // Assignment Responses page
            // Hover on View Response
            Actions builder = new Actions(driver);
            WebElement viewResponses = assignmentResponsesPage.icon_tickMark;
            builder.moveToElement(viewResponses).perform();
            new Click().clickByclassname("ls-view-response-link");
            // Question Response
            String responseQs = driver.findElement(By.cssSelector("strong[class='question-display-label essay-question-label-type']")).getText();
            if(!responseQs.trim().equals("Q 2:"))
            {
                Assert.fail("\"Response\" page for that particular question is not displayed");
            }
            Thread.sleep(1000);
            // Solution button
            boolean solutionButton = driver.findElement(By.id("ins-manually-graded-question-solution")).isDisplayed();
            if(solutionButton == false)
            {
                Assert.fail("Below the question preview,\"SOLUTION\" button is not available.");
            }
            // Solution Text
            boolean solutionText = driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed();
            if(solutionText == false)
            {
                Assert.fail("The Solution provided by the instructor is not seen by default");
            }
            // Click on solution
            driver.findElement(By.id("ins-manually-graded-question-solution")).click();
            System.out.println("solutionTextDisplay::" + driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed());
            Thread.sleep(1000);
            Assert.assertFalse(driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed(), "Click on Solution button, All the \"Solution\" details are not hidden");
            // Hint
            driver.findElement(By.id("ins-manually-graded-question-hint")).click();
            String hintSelect = driver.findElement(By.id("ins-manually-graded-question-hint")).getAttribute("class");
            if(!hintSelect.equals("ins-manually-graded-question-hint btn-selected"))
            {
                Assert.fail("\"Hint\" button is not activated ");
            }
            // click on hint
            driver.findElement(By.id("ins-manually-graded-question-hint")).click();
            System.out.println("HintTextDisplay::" + driver.findElement(By.className("cms-question-preview-question-hint-content")).isDisplayed());
            Thread.sleep(1000);

            Assert.assertFalse(driver.findElement(By.className("cms-question-preview-question-hint-content")).isDisplayed(), "Click on Hint button, All the \"hint\" details are not hidden");
            // click on solution
            driver.findElement(By.id("ins-manually-graded-question-solution")).click();
            Thread.sleep(1000);
            String solutionSelect = driver.findElement(By.id("ins-manually-graded-question-solution")).getAttribute("class");
            if(!solutionSelect.equals("ins-manually-graded-question-solution btn-selected"))
            {
                Assert.fail("\"Solution\" button is not activated ");
            }
            // Solution display
            Thread.sleep(1000);
            solutionText = driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed();
            if(solutionText == false)
            {
                Assert.fail("\"Solution\" details are not displayed below the question");
            }
            driver.findElement(By.id("ins-manually-graded-question-hint")).click();
            Thread.sleep(1000);
            // Both Hint and Solution display
            Assert.assertTrue(driver.findElement(By.className("cms-question-preview-question-hint-content")).isDisplayed(), "Hint details are not displayed");
            Assert.assertTrue(driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed(), "Solution details are not displayed");
            // click on hint again
            // Hint
            Thread.sleep(1000);
            driver.findElement(By.id("ins-manually-graded-question-hint")).click();
            Thread.sleep(1000);
            hintSelect = driver.findElement(By.id("ins-manually-graded-question-hint")).getAttribute("class");
            if(!hintSelect.equals("ins-manually-graded-question-hint"))
            {
                Assert.fail("\"Hint\" button is not disabled ");
            }
            // hint details hidden
            Thread.sleep(1000);
            Assert.assertFalse(driver.findElement(By.className("cms-question-preview-question-hint-content")).isDisplayed(), "Click on Hint button, All the \"hint\" details are not hidden");
            // disable solution button
            driver.findElement(By.id("ins-manually-graded-question-solution")).click();
            Thread.sleep(1000);
            solutionSelect = driver.findElement(By.id("ins-manually-graded-question-solution")).getAttribute("class");
            if(!solutionSelect.equals("ins-manually-graded-question-solution"))
            {
                Assert.fail("\"Solution\" button is not activated ");
            }
            // Solution text hidden
            Thread.sleep(1000);
            Assert.assertFalse(driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed(), "\"Solution\" button are not disabled");
            // Teacher Feedback
            new Click().clickByid("view-user-question-performance-feedback-box");
            // navigate to next qs
            new Click().clickByid("next-question-performance-view");
            // next Question Q3
            responseQs = driver.findElement(By.cssSelector("strong[class='question-display-label essay-question-label-type']")).getText();
            if(!responseQs.trim().equals("Q 3:"))
            {
                Assert.fail("Next question page is not displayed");
            }
            Thread.sleep(1000);
            // Solution Text
            solutionText = driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed();
            if(solutionText == false)
            {
                Assert.fail("The Solution provided by the instructor is not seen by default");
            }
            // Hint disable
            Thread.sleep(1000);
            hintSelect = driver.findElement(By.id("ins-manually-graded-question-hint")).getAttribute("class");
            if(!hintSelect.equals("ins-manually-graded-question-hint"))
            {
                Assert.fail("\"Hint\" button is not disabled by default");
            }

            // navigate to previous qs
            new Click().clickByid("prev-question-performance-view");
            // Previous Question Q2
            responseQs = driver.findElement(By.cssSelector("strong[class='question-display-label essay-question-label-type']")).getText();
            if(!responseQs.trim().equals("Q 2:"))
            {
                Assert.fail("Next question page is not displayed");
            }
            Thread.sleep(1000);
            // Solution Text
            solutionText = driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed();
            if(solutionText == false)
            {
                Assert.fail("The Solution provided by the instructor is not seen by default");
            }
            Thread.sleep(1000);
            // Hint disable
            hintSelect = driver.findElement(By.id("ins-manually-graded-question-hint")).getAttribute("class");
            if(!hintSelect.equals("ins-manually-graded-question-hint"))
            {
                Assert.fail("\"Hint\" button is not disabled by default");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in instructorViewHintAndSolutionForAnAssignment testcase in verifySharedCustomAssignmentAsmentor class", e);
        }
    }

    @Test(priority = 18,enabled = true)
    public void  viewHintAndSolutionForAnAssignmentAsMentor()
    {
        try
        {
            int dataIndex = 129;
            String assessmentname = ReadTestData.readDataByTagName("","assessmentname",Integer.toString(dataIndex));
            System.out.println("assessmentname::"+assessmentname);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);

            new LoginUsingLTI().ltiLogin("129"); // login as mentor
            new Navigator().NavigateTo("Question Banks");
            questionBank.getSearchInputFieldOnQuestionBank().get(1).click();
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys(assessmentname);
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            questionBank.assignThisLink.get(0).click();
            new AssignLesson().assignTOCWithDefaultClassSection(129);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in viewHintAndSolutionForAnAssignmentAsMentor testcase in verifySharedCustomAssignmentAsmentor class", e);
        }
    }

    @Test(priority = 19,enabled = true)
    public void  mentorViewHintAndSolutionForAnAssignmentAsStudent()
    {
        try
        {
            int dataIndex = 129;
            String assessmentname = ReadTestData.readDataByTagName("","assessmentname",Integer.toString(dataIndex));
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new LoginUsingLTI().ltiLogin("128"); // login as student
            new Navigator().NavigateTo("Assignments");
            assignments.assignmentName.click();
            // Complete the test
            int confidenceLvel = driver.findElements(By.className("confidence-level-block")).size();
            while (confidenceLvel > 0)
            {
                int trueOrFalse = driver.findElements(By.className("true-false-answer-choices")).size();
                if(trueOrFalse > 0)
                {
                    List<WebElement> choices = driver.findElements(By.className("true-false-student-answer-select"));
                    choices.get(0).click();
                }
                // essay qs
                int essayQs = driver.findElements(By.id("html-editor-non-draggable")).size();
                if(essayQs > 0)
                {
                    driver.findElement(By.id("html-editor-non-draggable")).click();
                    driver.findElement(By.id("html-editor-non-draggable")).sendKeys("Essay Answer 1234545");
                }
                // Click on Next question
                int nextQuestion = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size();
                if(nextQuestion > 0)
                {
                    driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).click();
                }
                // Click on submit question
                int submitQuestion = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).size();
                if(submitQuestion > 0)
                {
                    driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).click();
                }
                confidenceLvel = driver.findElements(By.className("confidence-level-block")).size();
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in mentorViewHintAndSolutionForAnAssignmentAsStudent testcase in verifySharedCustomAssignmentAsmentor class", e);
        }
    }

    @Test(priority = 20,enabled = true)
    public void  mentorViewHintAndSolutionForAnAssignment()
    {
        try
        {
            int dataIndex = 129;
            String assessmentname = ReadTestData.readDataByTagName("","assessmentname",Integer.toString(dataIndex));
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            // Login as mentor
            new LoginUsingLTI().ltiLogin("129");
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click(); // View Student Responses
            Assert.assertTrue(assignmentResponsesPage.getPageTitle().isDisplayed(),"\"Assignment Responses\"  page is not displayed"); // Assignment Responses page
            // Hover on View Response
            Actions builder = new Actions(driver);
            WebElement viewResponses = assignmentResponsesPage.icon_tickMark;
            builder.moveToElement(viewResponses).perform();
            new Click().clickByclassname("ls-view-response-link");
            // Question Response
            String responseQs = driver.findElement(By.cssSelector("strong[class='question-display-label essay-question-label-type']")).getText();
            if(!responseQs.trim().equals("Q 2:"))
            {
                Assert.fail("\"Response\" page for that particular question is not displayed");
            }
            Thread.sleep(1000);
            // Solution button
            boolean solutionButton = driver.findElement(By.id("ins-manually-graded-question-solution")).isDisplayed();
            if(solutionButton == false)
            {
                Assert.fail("Below the question preview,\"SOLUTION\" button is not available.");
            }
            // Solution Text
            boolean solutionText = driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed();
            if(solutionText == false)
            {
                Assert.fail("The Solution provided by the instructor is not seen by default");
            }
            // Click on solution
            driver.findElement(By.id("ins-manually-graded-question-solution")).click();
            System.out.println("solutionTextDisplay::" + driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed());
            Thread.sleep(1000);
            Assert.assertFalse(driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed(), "Click on Solution button, All the \"Solution\" details are not hidden");
            // Hint
            driver.findElement(By.id("ins-manually-graded-question-hint")).click();
            String hintSelect = driver.findElement(By.id("ins-manually-graded-question-hint")).getAttribute("class");
            if(!hintSelect.equals("ins-manually-graded-question-hint btn-selected"))
            {
                Assert.fail("\"Hint\" button is not activated ");
            }
            // click on hint
            driver.findElement(By.id("ins-manually-graded-question-hint")).click();
            System.out.println("HintTextDisplay::" + driver.findElement(By.className("cms-question-preview-question-hint-content")).isDisplayed());
            Thread.sleep(1000);

            Assert.assertFalse(driver.findElement(By.className("cms-question-preview-question-hint-content")).isDisplayed(), "Click on Hint button, All the \"hint\" details are not hidden");
            // click on solution
            driver.findElement(By.id("ins-manually-graded-question-solution")).click();
            Thread.sleep(1000);
            String solutionSelect = driver.findElement(By.id("ins-manually-graded-question-solution")).getAttribute("class");
            if(!solutionSelect.equals("ins-manually-graded-question-solution btn-selected"))
            {
                Assert.fail("\"Solution\" button is not activated ");
            }
            // Solution display
            Thread.sleep(1000);
            solutionText = driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed();
            if(solutionText == false)
            {
                Assert.fail("\"Solution\" details are not displayed below the question");
            }
            driver.findElement(By.id("ins-manually-graded-question-hint")).click();
            Thread.sleep(1000);
            // Both Hint and Solution display
            Assert.assertTrue(driver.findElement(By.className("cms-question-preview-question-hint-content")).isDisplayed(), "Hint details are not displayed");
            Assert.assertTrue(driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed(), "Solution details are not displayed");
            // click on hint again
            // Hint
            Thread.sleep(1000);
            driver.findElement(By.id("ins-manually-graded-question-hint")).click();
            Thread.sleep(1000);
            hintSelect = driver.findElement(By.id("ins-manually-graded-question-hint")).getAttribute("class");
            if(!hintSelect.equals("ins-manually-graded-question-hint"))
            {
                Assert.fail("\"Hint\" button is not disabled ");
            }
            // hint details hidden
            Thread.sleep(1000);
            Assert.assertFalse(driver.findElement(By.className("cms-question-preview-question-hint-content")).isDisplayed(), "Click on Hint button, All the \"hint\" details are not hidden");
            // disable solution button
            driver.findElement(By.id("ins-manually-graded-question-solution")).click();
            Thread.sleep(1000);
            solutionSelect = driver.findElement(By.id("ins-manually-graded-question-solution")).getAttribute("class");
            if(!solutionSelect.equals("ins-manually-graded-question-solution"))
            {
                Assert.fail("\"Solution\" button is not activated ");
            }
            // Solution text hidden
            Thread.sleep(1000);
            Assert.assertFalse(driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed(), "\"Solution\" button are not disabled");
            // Teacher Feedback
            new Click().clickByid("view-user-question-performance-feedback-box");
            // navigate to next qs
            new Click().clickByid("next-question-performance-view");
            // next Question Q3
            responseQs = driver.findElement(By.cssSelector("strong[class='question-display-label essay-question-label-type']")).getText();
            if(!responseQs.trim().equals("Q 3:"))
            {
                Assert.fail("Next question page is not displayed");
            }
            Thread.sleep(1000);
            // Solution Text
            solutionText = driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed();
            if(solutionText == false)
            {
                Assert.fail("The Solution provided by the instructor is not seen by default");
            }
            // Hint disable
            Thread.sleep(1000);
            hintSelect = driver.findElement(By.id("ins-manually-graded-question-hint")).getAttribute("class");
            if(!hintSelect.equals("ins-manually-graded-question-hint"))
            {
                Assert.fail("\"Hint\" button is not disabled by default");
            }

            // navigate to previous qs
            new Click().clickByid("prev-question-performance-view");
            // Previous Question Q2
            responseQs = driver.findElement(By.cssSelector("strong[class='question-display-label essay-question-label-type']")).getText();
            if(!responseQs.trim().equals("Q 2:"))
            {
                Assert.fail("Next question page is not displayed");
            }
            Thread.sleep(1000);
            // Solution Text
            solutionText = driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed();
            if(solutionText == false)
            {
                Assert.fail("The Solution provided by the instructor is not seen by default");
            }
            Thread.sleep(1000);
            // Hint disable
            hintSelect = driver.findElement(By.id("ins-manually-graded-question-hint")).getAttribute("class");
            if(!hintSelect.equals("ins-manually-graded-question-hint"))
            {
                Assert.fail("\"Hint\" button is not disabled by default");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in mentorViewHintAndSolutionForAnAssignment testcase in verifySharedCustomAssignmentAsmentor class", e);
        }
    }

    @Test(priority = 21, enabled = true)
    public void  viewSolutionAsAddedByAuthorWhileManuallyGradingAQuestionAsIns()
    {
        try
        {

            int dataIndex = 130;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);

            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(130));
            WebDriverWait wait=new WebDriverWait(driver,200);
            new Assignment().createChapter(130,2);//create a chapter
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select a course
            Thread.sleep(2000);
            courseOutline.courseOutline.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@title,'Add New Chapter')]")));

            List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label node course-chapter-label-unpublished')]"));
            for (WebElement chapters : allChapters) {
                if (chapters.getText().contains(chapterName)) {
                    Locatable hoverItem = (Locatable) chapters;
                    Mouse mouse = ((HasInputDevices) driver).getMouse();
                    mouse.mouseMove(hoverItem.getCoordinates());
                }
            }
            Thread.sleep(500);
            courseOutline.editButtonAtChapter.click();
            Thread.sleep(500);
            courseOutline.checkBoxToPublishChapter.click();//click on publish
            courseOutline.saveButton.click();//click on save
            Thread.sleep(5000);
            courseOutline.saveMyChanges.click();
            Thread.sleep(2000);
            new Assignment().create(130);//Create an diag test
            new Assignment().addQuestions(131,"multipleChoice",assessmentname);
            new Assignment().create(132);//Create an practice test
            new Assignment().addQuestions(133, "essay", assessmentname);
            new Assignment().addQuestions(134, "essay", assessmentname);
            dataIndex = 132;
            assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new LoginUsingLTI().ltiLogin("130");
            new Navigator().NavigateTo("Question Banks");
            questionBank.questionBankDropdown.click();
            questionBank.practiceSetsOnly.click();
            questionBank.getSearchInputFieldOnQuestionBank().get(1).click();
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys(assessmentname);
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            new Click().clickByclassname("assign-this");
            new AssignLesson().assignTOCWithDefaultClassSection(132);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in viewSolutionAsAddedByAuthorWhileManuallyGradingAQuestionAsIns testcase in verifySharedCustomAssignmentAsmentor class", e);
        }
    }

    @Test(priority = 22,enabled = true)
    public void  viewSolutionAsAddedByAuthorWhileManuallyGradingAQuestionAsStudent()
    {
        try
        {
            int dataIndex = 132;
            String assessmentname = ReadTestData.readDataByTagName("","assessmentname",Integer.toString(dataIndex));
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new LoginUsingLTI().ltiLogin("131"); // login as student
            new Navigator().NavigateTo("Assignments");
            assignments.assignmentName.click();
            int diagtest = driver.findElements(By.className("diagnostic-test-attempt-text")).size();
            if(diagtest > 0)
            {
                driver.findElement(By.className("diagnostic-test-attempt-text")).click();
                new Click().clickBycssselector("a[confidence-level='Almost confident']");
                new Click().clickByclassname("ls-assessment-continue-btn");
                int quitButton = driver.findElements(By.className("al-quit-diag-test-icon")).size();
                while (quitButton > 0)
                {
                    Thread.sleep(2000);
                    new Click().clickByclassname("al-diag-test-submit-button");
                    quitButton = driver.findElements(By.className("al-quit-diag-test-icon")).size();
                }
            }
            new Navigator().NavigateTo("Assignments");
            assignments.assignmentName.click();
            int confidenceLvel = driver.findElements(By.className("confidence-level-block")).size();
            while (confidenceLvel > 0)
            {
                int trueOrFalse = driver.findElements(By.className("true-false-answer-choices")).size();
                if(trueOrFalse > 0)
                {
                    Thread.sleep(1000);
                    List<WebElement> choices = driver.findElements(By.className("true-false-student-answer-select"));
                    choices.get(0).click();
                }
                // essay qs
                int essayQs = driver.findElements(By.id("html-editor-non-draggable")).size();
                if(essayQs > 0)
                {
                    Thread.sleep(1000);
                    driver.findElement(By.id("html-editor-non-draggable")).click();
                    driver.findElement(By.id("html-editor-non-draggable")).sendKeys("Essay Answer 1234545");
                }
                // Click on submit question
                int submitQuestion = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button adaptive-assignment-finish-button']")).size();
                if(submitQuestion > 0)
                {
                    Thread.sleep(1000);
                    driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button adaptive-assignment-finish-button']")).click();
                    new Click().clickBycssselector("div[class='adaptive-assignment-notification-option option-i-am-finished']");
                    break;
                }
                // Click on Next question
                int nextQuestion = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size();
                if(nextQuestion > 0)
                {
                    Thread.sleep(1000);
                    driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).click();
                }
                Thread.sleep(1000);
                confidenceLvel = driver.findElements(By.className("confidence-level-block")).size();
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in viewSolutionAsAddedByAuthorWhileManuallyGradingAQuestionAsStudent testcase in verifySharedCustomAssignmentAsmentor class", e);
        }
    }

    @Test(priority = 23, enabled = true)
    public void verifySolutionAsAddedByAuthorWhileManuallyGradingAQuestionAsIns()
    {
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new LoginUsingLTI().ltiLogin("130"); // Instructor Login
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click(); // View Student Responses
            Assert.assertTrue(assignmentResponsesPage.getPageTitle().isDisplayed(),"\"Assignment Responses\"  page is not displayed"); // Assignment Responses page
            // Hover on View Response
            Actions builder = new Actions(driver);
            WebElement viewResponses = driver.findElement(By.className("idb-gradebook-questions-attempted-status-entries"));
            builder.moveToElement(viewResponses).perform();
            new Click().clickByclassname("ls-view-response-link");
            // Question Response
            String responseQs = driver.findElement(By.cssSelector("strong[class='question-display-label essay-question-label-type']")).getText();
            if(!responseQs.trim().equals("Q 1:"))
            {
                Assert.fail("\"Response\" page for that particular question is not displayed");
            }
            Thread.sleep(1000);
            // Solution button
            boolean solutionButton = driver.findElement(By.id("ins-manually-graded-question-solution")).isDisplayed();
            if(solutionButton == false)
            {
                Assert.fail("Below the question preview,\"SOLUTION\" button is not available.");
            }
            // Solution Text
            boolean solutionText = driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed();
            if(solutionText == false)
            {
                Assert.fail("The Solution provided by the instructor is not seen by default");
            }
            // Click on solution
            driver.findElement(By.id("ins-manually-graded-question-solution")).click();
            System.out.println("solutionTextDisplay::" + driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed());
            Thread.sleep(1000);
            Assert.assertFalse(driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed(), "Click on Solution button, All the \"Solution\" details are not hidden");
            // Hint
            driver.findElement(By.id("ins-manually-graded-question-hint")).click();
            String hintSelect = driver.findElement(By.id("ins-manually-graded-question-hint")).getAttribute("class");
            if(!hintSelect.equals("ins-manually-graded-question-hint btn-selected"))
            {
                Assert.fail("\"Hint\" button is not activated ");
            }
            // click on hint
            driver.findElement(By.id("ins-manually-graded-question-hint")).click();
            System.out.println("HintTextDisplay::" + driver.findElement(By.className("cms-question-preview-question-hint-content")).isDisplayed());
            Thread.sleep(1000);

            Assert.assertFalse(driver.findElement(By.className("cms-question-preview-question-hint-content")).isDisplayed(), "Click on Hint button, All the \"hint\" details are not hidden");
            // click on solution
            driver.findElement(By.id("ins-manually-graded-question-solution")).click();
            Thread.sleep(1000);
            String solutionSelect = driver.findElement(By.id("ins-manually-graded-question-solution")).getAttribute("class");
            if(!solutionSelect.equals("ins-manually-graded-question-solution btn-selected"))
            {
                Assert.fail("\"Solution\" button is not activated ");
            }
            // Solution display
            Thread.sleep(1000);
            solutionText = driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed();
            if(solutionText == false)
            {
                Assert.fail("\"Solution\" details are not displayed below the question");
            }
            driver.findElement(By.id("ins-manually-graded-question-hint")).click();
            Thread.sleep(1000);
            // Both Hint and Solution display
            Assert.assertTrue(driver.findElement(By.className("cms-question-preview-question-hint-content")).isDisplayed(), "Hint details are not displayed");
            Assert.assertTrue(driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed(), "Solution details are not displayed");
            // click on hint again
            // Hint
            Thread.sleep(1000);
            driver.findElement(By.id("ins-manually-graded-question-hint")).click();
            Thread.sleep(1000);
            hintSelect = driver.findElement(By.id("ins-manually-graded-question-hint")).getAttribute("class");
            if(!hintSelect.equals("ins-manually-graded-question-hint"))
            {
                Assert.fail("\"Hint\" button is not disabled ");
            }
            // hint details hidden
            Thread.sleep(1000);
            Assert.assertFalse(driver.findElement(By.className("cms-question-preview-question-hint-content")).isDisplayed(), "Click on Hint button, All the \"hint\" details are not hidden");
            // disable solution button
            driver.findElement(By.id("ins-manually-graded-question-solution")).click();
            Thread.sleep(1000);
            solutionSelect = driver.findElement(By.id("ins-manually-graded-question-solution")).getAttribute("class");
            if(!solutionSelect.equals("ins-manually-graded-question-solution"))
            {
                Assert.fail("\"Solution\" button is not activated ");
            }
            // Solution text hidden
            Thread.sleep(1000);
            Assert.assertFalse(driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed(), "\"Solution\" button are not disabled");
            // Teacher Feedback
            new Click().clickByid("view-user-question-performance-feedback-box");
            // navigate to next qs
            new Click().clickByid("next-question-performance-view");
            // next Question Q3
            responseQs = driver.findElement(By.cssSelector("strong[class='question-display-label essay-question-label-type']")).getText();
            if(!responseQs.trim().equals("Q 2:"))
            {
                Assert.fail("Next question page is not displayed");
            }
            Thread.sleep(1000);
            // Solution Text
            solutionText = driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed();
            if(solutionText == false)
            {
                Assert.fail("The Solution provided by the instructor is not seen by default");
            }
            // Hint disable
            Thread.sleep(1000);
            hintSelect = driver.findElement(By.id("ins-manually-graded-question-hint")).getAttribute("class");
            if(!hintSelect.equals("ins-manually-graded-question-hint"))
            {
                Assert.fail("\"Hint\" button is not disabled by default");
            }

            // navigate to previous qs
            new Click().clickByid("prev-question-performance-view");
            // Previous Question Q2
            responseQs = driver.findElement(By.cssSelector("strong[class='question-display-label essay-question-label-type']")).getText();
            if(!responseQs.trim().equals("Q 1:"))
            {
                Assert.fail("Next question page is not displayed");
            }
            Thread.sleep(1000);
            // Solution Text
            solutionText = driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed();
            if(solutionText == false)
            {
                Assert.fail("The Solution provided by the instructor is not seen by default");
            }
            Thread.sleep(1000);
            // Hint disable
            hintSelect = driver.findElement(By.id("ins-manually-graded-question-hint")).getAttribute("class");
            if(!hintSelect.equals("ins-manually-graded-question-hint"))
            {
                Assert.fail("\"Hint\" button is not disabled by default");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in verifySolutionAsAddedByAuthorWhileManuallyGradingAQuestionAsIns testcase in verifySharedCustomAssignmentAsmentor class", e);
        }
    }

    @Test(priority = 24, enabled = true)
    public void  viewSolutionAsAddedByAuthorWhileManuallyGradingAQuestionAsMentor()
    {
        try
        {

            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);

            int dataIndex = 148;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new LoginUsingLTI().ltiLogin("148"); // login  as mentor
            new Navigator().NavigateTo("Question Banks");
            questionBank.questionBankDropdown.click();
            questionBank.practiceSetsOnly.click();
            questionBank.getSearchInputFieldOnQuestionBank().get(1).click();
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys(assessmentname);
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            new Click().clickByclassname("assign-this");
            new AssignLesson().assignTOCWithDefaultClassSection(148);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in viewSolutionAsAddedByAuthorWhileManuallyGradingAQuestionAsMentor testcase in verifySharedCustomAssignmentAsmentor class", e);
        }
    }

    @Test(priority = 25,enabled = true)
    public void viewSolutionAsAddedByAuthorWhileManuallyGradingAQuestionMentortoStudent()
    {
        try
        {
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            new LoginUsingLTI().ltiLogin("147"); // login as student
            new Navigator().NavigateTo("Assignments");
            assignments.assignmentName.click();
            int diagtest = driver.findElements(By.className("diagnostic-test-attempt-text")).size();
            if(diagtest > 0)
            {
                driver.findElement(By.className("diagnostic-test-attempt-text")).click();
                new Click().clickBycssselector("a[confidence-level='Almost confident']");
                new Click().clickByclassname("ls-assessment-continue-btn");
                int quitButton = driver.findElements(By.className("al-quit-diag-test-icon")).size();
                while (quitButton > 0)
                {
                    Thread.sleep(2000);
                    new Click().clickByclassname("al-diag-test-submit-button");
                    quitButton = driver.findElements(By.className("al-quit-diag-test-icon")).size();
                }
            }
            new Navigator().NavigateTo("Assignments");
            assignments.assignmentName.click();
            int confidenceLvel = driver.findElements(By.className("confidence-level-block")).size();
            while (confidenceLvel > 0)
            {
                int trueOrFalse = driver.findElements(By.className("true-false-answer-choices")).size();
                if(trueOrFalse > 0)
                {
                    Thread.sleep(1000);
                    List<WebElement> choices = driver.findElements(By.className("true-false-student-answer-select"));
                    choices.get(0).click();
                }
                // essay qs
                int essayQs = driver.findElements(By.id("html-editor-non-draggable")).size();
                if(essayQs > 0)
                {
                    Thread.sleep(1000);
                    driver.findElement(By.id("html-editor-non-draggable")).click();
                    driver.findElement(By.id("html-editor-non-draggable")).sendKeys("Essay Answer 1234545");
                }
                // Click on submit question
                int submitQuestion = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button adaptive-assignment-finish-button']")).size();
                if(submitQuestion > 0)
                {
                    Thread.sleep(1000);
                    driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button adaptive-assignment-finish-button']")).click();
                    new Click().clickBycssselector("div[class='adaptive-assignment-notification-option option-i-am-finished']");
                    break;
                }
                // Click on Next question
                int nextQuestion = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size();
                if(nextQuestion > 0)
                {
                    Thread.sleep(1000);
                    driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).click();
                }
                Thread.sleep(1000);
                confidenceLvel = driver.findElements(By.className("confidence-level-block")).size();
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in viewSolutionAsAddedByAuthorWhileManuallyGradingAQuestionMentortoStudent testcase in verifySharedCustomAssignmentAsmentor class", e);
        }
    }

    @Test(priority = 26, enabled = true)
    public void verifySolutionAsAddedByAuthorWhileManuallyGradingAQuestionAsMentor()
    {
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new LoginUsingLTI().ltiLogin("148"); // Instructor Login
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click(); // View Student Responses
            Assert.assertTrue(assignmentResponsesPage.getPageTitle().isDisplayed(),"\"Assignment Responses\"  page is not displayed"); // Assignment Responses page
            // Hover on View Response
            Actions builder = new Actions(driver);
            WebElement viewResponses = driver.findElement(By.className("idb-gradebook-questions-attempted-status-entries"));
            builder.moveToElement(viewResponses).perform();
            new Click().clickByclassname("ls-view-response-link");
            // Question Response
            String responseQs = driver.findElement(By.cssSelector("strong[class='question-display-label essay-question-label-type']")).getText();
            if(!responseQs.trim().equals("Q 1:"))
            {
                Assert.fail("\"Response\" page for that particular question is not displayed");
            }
            Thread.sleep(1000);
            // Solution button
            boolean solutionButton = driver.findElement(By.id("ins-manually-graded-question-solution")).isDisplayed();
            if(solutionButton == false)
            {
                Assert.fail("Below the question preview,\"SOLUTION\" button is not available.");
            }
            // Solution Text
            boolean solutionText = driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed();
            if(solutionText == false)
            {
                Assert.fail("The Solution provided by the instructor is not seen by default");
            }
            // Click on solution
            driver.findElement(By.id("ins-manually-graded-question-solution")).click();
            System.out.println("solutionTextDisplay::" + driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed());
            Thread.sleep(1000);
            Assert.assertFalse(driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed(), "Click on Solution button, All the \"Solution\" details are not hidden");
            // Hint
            driver.findElement(By.id("ins-manually-graded-question-hint")).click();
            String hintSelect = driver.findElement(By.id("ins-manually-graded-question-hint")).getAttribute("class");
            if(!hintSelect.equals("ins-manually-graded-question-hint btn-selected"))
            {
                Assert.fail("\"Hint\" button is not activated ");
            }
            // click on hint
            driver.findElement(By.id("ins-manually-graded-question-hint")).click();
            System.out.println("HintTextDisplay::" + driver.findElement(By.className("cms-question-preview-question-hint-content")).isDisplayed());
            Thread.sleep(1000);

            Assert.assertFalse(driver.findElement(By.className("cms-question-preview-question-hint-content")).isDisplayed(), "Click on Hint button, All the \"hint\" details are not hidden");
            // click on solution
            driver.findElement(By.id("ins-manually-graded-question-solution")).click();
            Thread.sleep(1000);
            String solutionSelect = driver.findElement(By.id("ins-manually-graded-question-solution")).getAttribute("class");
            if(!solutionSelect.equals("ins-manually-graded-question-solution btn-selected"))
            {
                Assert.fail("\"Solution\" button is not activated ");
            }
            // Solution display
            Thread.sleep(1000);
            solutionText = driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed();
            if(solutionText == false)
            {
                Assert.fail("\"Solution\" details are not displayed below the question");
            }
            driver.findElement(By.id("ins-manually-graded-question-hint")).click();
            Thread.sleep(1000);
            // Both Hint and Solution display
            Assert.assertTrue(driver.findElement(By.className("cms-question-preview-question-hint-content")).isDisplayed(), "Hint details are not displayed");
            Assert.assertTrue(driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed(), "Solution details are not displayed");
            // click on hint again
            // Hint
            Thread.sleep(1000);
            driver.findElement(By.id("ins-manually-graded-question-hint")).click();
            Thread.sleep(1000);
            hintSelect = driver.findElement(By.id("ins-manually-graded-question-hint")).getAttribute("class");
            if(!hintSelect.equals("ins-manually-graded-question-hint"))
            {
                Assert.fail("\"Hint\" button is not disabled ");
            }
            // hint details hidden
            Thread.sleep(1000);
            Assert.assertFalse(driver.findElement(By.className("cms-question-preview-question-hint-content")).isDisplayed(), "Click on Hint button, All the \"hint\" details are not hidden");
            // disable solution button
            driver.findElement(By.id("ins-manually-graded-question-solution")).click();
            Thread.sleep(1000);
            solutionSelect = driver.findElement(By.id("ins-manually-graded-question-solution")).getAttribute("class");
            if(!solutionSelect.equals("ins-manually-graded-question-solution"))
            {
                Assert.fail("\"Solution\" button is not activated ");
            }
            // Solution text hidden
            Thread.sleep(1000);
            Assert.assertFalse(driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed(), "\"Solution\" button are not disabled");
            // Teacher Feedback
            new Click().clickByid("view-user-question-performance-feedback-box");
            // navigate to next qs
            new Click().clickByid("next-question-performance-view");
            // next Question Q3
            responseQs = driver.findElement(By.cssSelector("strong[class='question-display-label essay-question-label-type']")).getText();
            if(!responseQs.trim().equals("Q 2:"))
            {
                Assert.fail("Next question page is not displayed");
            }
            Thread.sleep(1000);
            // Solution Text
            solutionText = driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed();
            if(solutionText == false)
            {
                Assert.fail("The Solution provided by the instructor is not seen by default");
            }
            // Hint disable
            Thread.sleep(1000);
            hintSelect = driver.findElement(By.id("ins-manually-graded-question-hint")).getAttribute("class");
            if(!hintSelect.equals("ins-manually-graded-question-hint"))
            {
                Assert.fail("\"Hint\" button is not disabled by default");
            }

            // navigate to previous qs
            new Click().clickByid("prev-question-performance-view");
            // Previous Question Q2
            responseQs = driver.findElement(By.cssSelector("strong[class='question-display-label essay-question-label-type']")).getText();
            if(!responseQs.trim().equals("Q 1:"))
            {
                Assert.fail("Next question page is not displayed");
            }
            Thread.sleep(1000);
            // Solution Text
            solutionText = driver.findElement(By.className("cms-question-preview-question-solution-content")).isDisplayed();
            if(solutionText == false)
            {
                Assert.fail("The Solution provided by the instructor is not seen by default");
            }
            Thread.sleep(1000);
            // Hint disable
            hintSelect = driver.findElement(By.id("ins-manually-graded-question-hint")).getAttribute("class");
            if(!hintSelect.equals("ins-manually-graded-question-hint"))
            {
                Assert.fail("\"Hint\" button is not disabled by default");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in verifySolutionAsAddedByAuthorWhileManuallyGradingAQuestionAsIns testcase in verifySharedCustomAssignmentAsmentor class", e);
        }
    }
}
