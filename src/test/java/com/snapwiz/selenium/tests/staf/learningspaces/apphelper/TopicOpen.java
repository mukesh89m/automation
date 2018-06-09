package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
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

public class TopicOpen extends Driver
{

    public void topicOpen(String topicName)
    {
        try
        {
            Thread.sleep(10000);
            WebDriver driver=Driver.getWebDriver();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='"+topicName+"']")));
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper TopicOpen",e);
        }
    }


    public void chapterOpen(String topicName)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            Thread.sleep(10000);
            List<WebElement> chaptersElementsList = driver.findElements(By.xpath(".//*[@title='"+topicName+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", chaptersElementsList.get(chaptersElementsList.size()-1));
            Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chaptersElementsList.get(chaptersElementsList.size()-1));
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper TopicOpen",e);
        }
    }

    public void staticAssessmentOpen(int chapterIndex,int index)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            Thread.sleep(10000);
            driver.findElements(By.className("chapter-heading-label")).get(chapterIndex).click();
            Thread.sleep(3000);
            driver.findElements(By.cssSelector("a[data-type='static_assessment']")).get(index).click();
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper staticAssessmentOpen",e);
        }
    }

    public void lessonOpen(int chapterIndex, int lessonIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new UIElement().waitAndFindElement(By.className("chapter-heading-label"));
            Thread.sleep(15000);
            driver.findElements(By.className("chapter-heading-label")).get(chapterIndex).click();
            Thread.sleep(9000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElements(By.cssSelector("a[data-type='lesson']")).get(lessonIndex));
            //driver.findElements(By.cssSelector("a[data-type='lesson']")).get(lessonIndex).click();
            Thread.sleep(5000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper lessonOpen",e);
        }
    }
    public void chapterOpen(int chapterIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            Thread.sleep(5000);
            //driver.findElements(By.className("chapter-heading-label")).get(chapterIndex).click();
            new Click().clickbylist("chapter-heading-label",chapterIndex);
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper chapterOpen",e);
        }
    }

    public void TOCOpen()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
			/*int headervalue=driver.findElements(By.id("showHeader")).size();
			if(headervalue>=1)
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.cssSelector("span[id='showHeader']")));*/
            Thread.sleep(10000);
            driver.findElement(By.cssSelector("div[class='ls-chapter-tree']")).click();//TOC open
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper TOCOpen",e);
        }
    }

    public void topicopeninnewtab(String topicName,int topicnumber)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            Thread.sleep(10000);
            Actions action = new Actions(driver);
            WebElement we= driver.findElement(By.cssSelector("a[title='"+topicName+"']"));
            action.moveToElement(we).build().perform();
            //Thread.sleep(3000);
            List<WebElement> topicopeninnewtab = driver.findElements(By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']"));
            //((JavascriptExecutor) driver).executeScript("arguments[0].click();", By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", topicopeninnewtab.get(topicnumber));
            //	topicopeninnewtab.get(topicnumber).click();
            //driver.findElement(By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']")).click();

            //	((JavascriptExecutor) driver).executeScript("arguments[0].click();", By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']"));
            //	driver.findElement(By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']")).click();

        }
        catch(Exception e)
        {
            Assert.fail("Exception in method topicopeninnewtab App helper TOCOpen",e);
        }

    }

    public void ResourcesOpenInNewtab(int arrowIndex,int resnumber)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new Navigator().navigateToTab("Resources");//click on resources tab
            List<WebElement> allArrowIcon = driver.findElements(By.className("ls-resource-show-assign-this-block"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allArrowIcon.get(arrowIndex));	//click on arrow icon
            Thread.sleep(2000);
            List<WebElement> allOpenLink = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-forward-bg']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOpenLink.get(resnumber));
            Thread.sleep(2000);
			/*Thread.sleep(3000);
			Actions action = new Actions(driver);
			List<WebElement> we= (List<WebElement>) driver.findElements(By.cssSelector("li[class='resource-content-posts-list']"));
			action.moveToElement(we.get(resourseopen)).build().perform();
			//driver.findElement(By.cssSelector("a[class='toc-sprite folder-forward']")).click();
			List<WebElement> resoursenewtab = driver.findElements(By.cssSelector("a[class='toc-sprite folder-forward']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", resoursenewtab.get(resnumber));*/
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method ResourcesOpenInNewtab App helper TOCOpen",e);
        }
    }
    public void resourcesopeninexistingtab(int resourcesno)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            Thread.sleep(10000);
            Actions action = new Actions(driver);
            List<WebElement> we= (List<WebElement>) driver.findElements(By.cssSelector("li[class='resource-content-posts-list']"));
            action.moveToElement(we.get(resourcesno)).build().perform();
            int index=0;
            List<WebElement> allexitrestab=driver.findElements(By.cssSelector("a[class='toc-sprite folder-cycle']"));
            for(WebElement element:allexitrestab)
            {
                if(index==resourcesno)
                {
                    element.click();
                    break;
                }
                index++;
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method resourcesopeninexistingtab App helper TOCOpen",e);
        }
    }

    public void AssignmentOpen(int assignmentopen)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            Thread.sleep(10000);
            driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on resources tab
            Thread.sleep(3000);
            Actions action = new Actions(driver);
            List<WebElement> we= (List<WebElement>) driver.findElements(By.cssSelector("li[class='assignment-content-posts-list']"));
            action.moveToElement(we.get(assignmentopen)).build().perform();
            //driver.findElement(By.cssSelector("a[class='toc-sprite folder-forward']")).click();
            List<WebElement> resoursenewtab = driver.findElements(By.cssSelector("a[class='toc-sprite folder-forward']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", resoursenewtab.get(assignmentopen));
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method ResourcesOpenInNewtab App helper TOCOpen",e);
        }
    }

    public void adativetestopeninnewtab(String topicName,int testnumbernumber)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            Thread.sleep(10000);
            Actions action = new Actions(driver);
            WebElement we= driver.findElement(By.cssSelector("a[title='"+topicName+"']"));
            action.moveToElement(we).build().perform();
            List<WebElement> adaptiveopeninnewtab = driver.findElements(By.cssSelector("div[class='pointer-arrow toc-adaptive-icon chapter-badge']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", adaptiveopeninnewtab.get(testnumbernumber));

        }
        catch(Exception e)
        {
            Assert.fail("Exception in method adativetestopeninnewtab App helper TOCOpen",e);
        }
    }

    public void topicOpenLast(String topicToOpen)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            Thread.sleep(10000);
            new ExpandFirstChapter().expandFirstChapter();
            List<WebElement> allChNames = driver.findElements(By.cssSelector("a[title='"+topicToOpen+"']"));
            int numberOfChapters = allChNames.size();
            allChNames.get(numberOfChapters-1).click();
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method topicOpenLast App helper TOCOpen",e);
        }
    }

    public void topicOpenInNewTab(int topicindex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            List<WebElement> topicopeninnewtab = driver.findElements(By.xpath("//div[starts-with(@id, 'lesson-')]/div"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", topicopeninnewtab.get(topicindex));
            Thread.sleep(3000);
            List<WebElement> newTabs = driver.findElements(By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']"));
            for(WebElement tab: newTabs)
            {
                if(tab.isDisplayed()==true)
                {
                    tab.click();//click on new tab icon
                    break;
                }
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method topicopeninnewtab App helper TOCOpen",e);
        }

    }
    public void openLastChapter()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            Thread.sleep(10000);
            List<WebElement> allChapter = driver.findElements(By.className("chapter-heading-label"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allChapter.get(allChapter.size()-1));
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper chapterOpen",e);
        }
    }
    public void openCMSLastChapter()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            Thread.sleep(10000);
            new ScrollElement().scrollBottomOfPage();
            List<WebElement> allChapter =driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allChapter.get(allChapter.size()-1));
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper chapterOpen",e);
        }
    }
    public void openLessonWithDiscussionWidget()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            WebDriverWait wait=new WebDriverWait(driver,120);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[text()='Cell Reproduction']")));
            new Click().clickbylist("chapter-heading-label", 9);
            Thread.sleep(1000);
            WebElement scroll = driver.findElement(By.cssSelector("#chapter-details-scrollbar-toc > div.scrollbar > div.track > div.thumb"));
            Actions actions = new Actions(driver);
            actions.moveToElement(scroll)
                    .clickAndHold()
                    .moveByOffset(0, 150)
                    .release()
                    .perform();
            Thread.sleep(1000);
            WebElement  we=driver.findElement(By.partialLinkText("10.3"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
            Thread.sleep(1000);
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("window.scrollBy(0,3700)");
            Thread.sleep(1000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper chapterOpen",e);
        }
    }
    public void openLessonWithDiscussionWidgetForStudent()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            Thread.sleep(10000);
            new Click().clickbylist("chapter-heading-label", 9);
            Thread.sleep(3000);
            WebElement scroll = driver.findElement(By.cssSelector("#chapter-details-scrollbar-toc > div.scrollbar > div.track > div.thumb"));
            Actions actions = new Actions(driver);
            actions.moveToElement(scroll, 0, 0)
                    .clickAndHold()
                    .moveByOffset(0, 150)
                    .release()
                    .perform();
            Thread.sleep(3000);
            WebElement  we2=driver.findElement(By.linkText("10.3: Control of the Cell Cycle"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we2);
            Thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("javascript:window.scrollBy(0,4500)", "");
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper chapterOpen",e);
        }
    }
    public void clickOnArrow(int arrowIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            List<WebElement> topicopeninnewtab = driver.findElements(By.xpath("//div[@class='tlo-ellipsis-content-wrapper']/following-sibling::div[2]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", topicopeninnewtab.get(arrowIndex));
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method clickOnArrow App helper TOCOpen",e);
        }

    }
    public void clickOnLessonArrow(int arrowIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            List<WebElement> topicopeninnewtab = driver.findElements(By.xpath("//span[@class='toc-icon completed']//following-sibling::div[2]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", topicopeninnewtab.get(arrowIndex));
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method clickOnLessonArrow App helper TOCOpen",e);
        }

    }

    public void openInnerTopic()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            List<WebElement> newTabs = driver.findElements(By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']"));
            for(WebElement tab: newTabs)
            {
                if(tab.isDisplayed()==true)
                {
                    tab.click();//click on new tab icon
                    break;
                }
            }
            Thread.sleep(5000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method topicopeninnewtab App helper TOCOpen",e);
        }

    }

    public void clickOnAssignThisIcon()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            //List<WebElement> assignThis = driver.findElements(By.xpath("//div[@assesssmenttype='static_assessment']"));
            List<WebElement> assignThis = driver.findElements(By.xpath("//div[@title='Assign This']"));
            for(WebElement tab: assignThis)
            {
                if(tab.isDisplayed()==true)
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",tab);//click on new tab icon
                    break;
                }
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method topicopeninnewtab App helper TOCOpen",e);
        }
    }

    public void clickOnAdaptivePracticeArrow()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new UIElement().waitAndFindElement(By.className("selected-chapter-name"));
            new Navigator().NavigateToOrion();
            List<WebElement> arrowElementsList = driver.findElements(By.xpath("//span[@id='ls-adaptive-preview-button']//following-sibling::div[1]"));
            int pos =0;
            for(int a=0;a<arrowElementsList.size();a++){
                if(arrowElementsList.get(a).isDisplayed()){
                    pos = a;
                }
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", arrowElementsList.get(pos));
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method clickOnAdaptivePracticeArrow App helper TOCOpen",e);
        }
    }

    public void clickOnPreview()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new Navigator().NavigateToOrion();
            new UIElement().waitAndFindElement(By.id("preview-test-button"));
            List<WebElement> arrowElementsList = driver.findElements(By.id("preview-test-button"));
            int pos =1;
            for(int a=1;a<arrowElementsList.size();a++){
                if(arrowElementsList.get(a).isDisplayed()){
                    pos = a;
                }
            }
            System.out.println(pos);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", arrowElementsList.get(pos));
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method clickOnPreview App helper TOCOpen",e);
        }
    }

    public void clickOnStaticAssessmentArrow(String staticAssessment)
    {
        WebDriver driver=Driver.getWebDriver();
        System.out.println("staticAssessment:"+staticAssessment);
        try {
            new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@title='" + staticAssessment + "']")));
            new ScrollElement().scrollToViewOfElement(driver.findElement(By.xpath(".//*[@title='" + staticAssessment + "']")));
            List<WebElement> assessments = driver.findElements(By.xpath(".//*[@title='" + staticAssessment + "']/following-sibling::div[@class='ls-inner-arw']"));
            for (WebElement test : assessments) {
                if (test.isDisplayed()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", test);
                    break;
                }
            }

        }
        catch (Exception e){
            Assert.fail("Exception in method clickOnStaticAssessmentArrow App helper TOCOpen",e);
        }
    }

    public void filterChapterInProficiencyReport(int dataIndex,int chapterIndex){
        try {
            WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.xpath("//a[@title='Show All Chapters']")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElements(By.xpath("//div[@class='overview']/li/a")).get(chapterIndex));
        } catch (Exception e) {
            Assert.fail("Exception in method filterChapterInProficiencyReport of class topic open");
        }

    }

}
