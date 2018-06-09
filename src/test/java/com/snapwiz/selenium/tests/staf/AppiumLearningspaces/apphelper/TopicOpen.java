package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;

public class TopicOpen extends Driver
{

	public void topicOpen(String topicName)
	{
		try
		{
			Thread.sleep(10000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[title='"+topicName+"']")));
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper TopicOpen",e);
		}
	}


    public void chapterOpen(String topicName)
    {
        try
        {
            Thread.sleep(10000);
            List<WebElement> chaptersElementsList = Driver.driver.findElements(By.xpath(".//*[@title='"+topicName+"']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", chaptersElementsList.get(chaptersElementsList.size()-1));
            Thread.sleep(5000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chaptersElementsList.get(chaptersElementsList.size()-1));
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper TopicOpen",e);
        }
    }
	
	public void staticAssessmentOpen(int chapterIndex,int index)
	{
		try
		{
            Thread.sleep(10000);
			Driver.driver.findElements(By.className("chapter-heading-label")).get(chapterIndex).click();
			Thread.sleep(3000);
			Driver.driver.findElements(By.cssSelector("a[data-type='static_assessment']")).get(index).click();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper staticAssessmentOpen",e);
		}
	}
	
	public void lessonOpen(int chapterIndex, int lessonIndex)
	{
		try
		{
            new UIElement().waitAndFindElement(By.className("chapter-heading-label"));
            Thread.sleep(15000);
			Driver.driver.findElements(By.className("chapter-heading-label")).get(chapterIndex).click();
			Thread.sleep(5000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElements(By.cssSelector("a[data-type='lesson']")).get(lessonIndex));
//			Driver.driver.findElements(By.cssSelector("a[data-type='lesson']")).get(lessonIndex).click();
			Thread.sleep(5000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper lessonOpen",e);
		}
	}
	public void chapterOpen(int chapterIndex)
	{
		try
		{
            Thread.sleep(5000);
			//Driver.driver.findElements(By.className("chapter-heading-label")).get(chapterIndex).click();
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
		try
		{
			/*int headervalue=Driver.driver.findElements(By.id("showHeader")).size();			
			if(headervalue>=1)
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.cssSelector("span[id='showHeader']")));*/
            Thread.sleep(10000);
			Driver.driver.findElement(By.cssSelector("div[class='ls-chapter-tree']")).click();//TOC open
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper TOCOpen",e);
		}
	}
	
	public void topicopeninnewtab(String topicName,int topicnumber)
	{
		try
		{
            Thread.sleep(10000);
			Actions action = new Actions(Driver.driver);
			WebElement we= Driver.driver.findElement(By.cssSelector("a[title='"+topicName+"']"));
			action.moveToElement(we).build().perform();
			//Thread.sleep(3000);
			List<WebElement> topicopeninnewtab = Driver.driver.findElements(By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']"));
           //((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", topicopeninnewtab.get(topicnumber));
		//	topicopeninnewtab.get(topicnumber).click();
			//Driver.driver.findElement(By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']")).click(); 
			 
		//	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']"));
		//	Driver.driver.findElement(By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']")).click();
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method topicopeninnewtab App helper TOCOpen",e);
		}
	
	}
	
	public void ResourcesOpenInNewtab(int arrowIndex,int resnumber)
	{
		try
		{
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().navigateToTab("Resources");//click on resources tab
            List<WebElement> allArrowIcon = Driver.driver.findElements(By.className("ls-resource-show-assign-this-block"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allArrowIcon.get(arrowIndex));	//click on arrow icon
            Thread.sleep(2000);
            List<WebElement> allOpenLink = Driver.driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-forward-bg']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allOpenLink.get(resnumber));
            Thread.sleep(2000);
			/*Thread.sleep(3000);
			Actions action = new Actions(Driver.driver);
			List<WebElement> we= (List<WebElement>) Driver.driver.findElements(By.cssSelector("li[class='resource-content-posts-list']"));
			action.moveToElement(we.get(resourseopen)).build().perform();
			//Driver.driver.findElement(By.cssSelector("a[class='toc-sprite folder-forward']")).click();
			List<WebElement> resoursenewtab = Driver.driver.findElements(By.cssSelector("a[class='toc-sprite folder-forward']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", resoursenewtab.get(resnumber));*/
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method ResourcesOpenInNewtab App helper TOCOpen",e);
		}
	}
	public void resourcesopeninexistingtab(int resourcesno)
	{
		try
		{
            Thread.sleep(10000);
			Actions action = new Actions(Driver.driver);
			List<WebElement> we= (List<WebElement>) Driver.driver.findElements(By.cssSelector("li[class='resource-content-posts-list']"));
			action.moveToElement(we.get(resourcesno)).build().perform();
			int index=0;
			List<WebElement> allexitrestab=Driver.driver.findElements(By.cssSelector("a[class='toc-sprite folder-cycle']"));
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
		try
		{
            Thread.sleep(10000);
			Driver.driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on resources tab
			Thread.sleep(3000);
			Actions action = new Actions(Driver.driver);
			List<WebElement> we= (List<WebElement>) Driver.driver.findElements(By.cssSelector("li[class='assignment-content-posts-list']"));
			action.moveToElement(we.get(assignmentopen)).build().perform();
			//Driver.driver.findElement(By.cssSelector("a[class='toc-sprite folder-forward']")).click();
			List<WebElement> resoursenewtab = Driver.driver.findElements(By.cssSelector("a[class='toc-sprite folder-forward']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", resoursenewtab.get(assignmentopen));
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method ResourcesOpenInNewtab App helper TOCOpen",e);
		}
	}
	
	public void adativetestopeninnewtab(String topicName,int testnumbernumber)
	{
		try
		{
            Thread.sleep(10000);
			Actions action = new Actions(Driver.driver);
			WebElement we= Driver.driver.findElement(By.cssSelector("a[title='"+topicName+"']"));
			action.moveToElement(we).build().perform();
			List<WebElement> adaptiveopeninnewtab = Driver.driver.findElements(By.cssSelector("div[class='pointer-arrow toc-adaptive-icon chapter-badge']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", adaptiveopeninnewtab.get(testnumbernumber));
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method adativetestopeninnewtab App helper TOCOpen",e);
		}
	}
	
	public void topicOpenLast(String topicToOpen)
	{
		try
		{
            Thread.sleep(10000);
			new ExpandFirstChapter().expandFirstChapter();
			List<WebElement> allChNames = Driver.driver.findElements(By.cssSelector("a[title='"+topicToOpen+"']"));
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
		try
		{
			List<WebElement> topicopeninnewtab = Driver.driver.findElements(By.xpath("//div[starts-with(@id, 'lesson-')]/div"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", topicopeninnewtab.get(topicindex));
			Thread.sleep(3000);
            List<WebElement> newTabs = Driver.driver.findElements(By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']"));
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
		try
		{
            Thread.sleep(10000);
			List<WebElement> allChapter = Driver.driver.findElements(By.className("chapter-heading-label"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allChapter.get(allChapter.size()-1));
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper chapterOpen",e);
		}
	}
    public void openCMSLastChapter()
    {
        try
        {
            Thread.sleep(10000);
            List<WebElement> allChapter =Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allChapter.get(allChapter.size()-1));
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App helper chapterOpen",e);
        }
    }
	public void openLessonWithDiscussionWidget()
	{
		try
		{
            WebDriverWait wait=new WebDriverWait(driver,120);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[text()='Cell Reproduction']")));
            new Click().clickbylist("chapter-heading-label", 9);
			Thread.sleep(1000);
			WebElement scroll = Driver.driver.findElement(By.cssSelector("#chapter-details-scrollbar-toc > div.scrollbar > div.track > div.thumb"));
			Actions actions = new Actions(Driver.driver);	
			actions.moveToElement(scroll, 0, 0)
		    .clickAndHold()
		    .moveByOffset(0, 150)
		    .release()
		    .perform();
			Thread.sleep(1000);
			WebElement  we=Driver.driver.findElement(By.partialLinkText("10.3"));
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",we);
			Thread.sleep(1000);
			JavascriptExecutor js = (JavascriptExecutor)Driver.driver;
			   js.executeScript("javascript:window.scrollBy(0,4500)", "");
			   Thread.sleep(1000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper chapterOpen",e);
		}
	}
	public void openLessonWithDiscussionWidgetForStudent()
	{
		try
		{
            Thread.sleep(10000);
			new Click().clickbylist("chapter-heading-label", 9);
			Thread.sleep(3000);
			WebElement scroll = Driver.driver.findElement(By.cssSelector("#chapter-details-scrollbar-toc > div.scrollbar > div.track > div.thumb"));
			Actions actions = new Actions(Driver.driver);	
			actions.moveToElement(scroll, 0, 0)
		    .clickAndHold()
		    .moveByOffset(0, 150)
		    .release()
		    .perform();
			Thread.sleep(3000);
			WebElement  we2=Driver.driver.findElement(By.linkText("10.3: Control of the Cell Cycle"));
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",we2);
			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor)Driver.driver;
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
        try
        {
             List<WebElement> topicopeninnewtab = Driver.driver.findElements(By.xpath("//div[@class='tlo-ellipsis-content-wrapper']/following-sibling::div[2]"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", topicopeninnewtab.get(arrowIndex));
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method clickOnArrow App helper TOCOpen",e);
        }

    }
    public void clickOnLessonArrow(int arrowIndex)
    {
        try
        {
            List<WebElement> topicopeninnewtab = Driver.driver.findElements(By.xpath("//span[@class='toc-icon completed']//following-sibling::div[2]"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", topicopeninnewtab.get(arrowIndex));
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method clickOnLessonArrow App helper TOCOpen",e);
        }

    }
    public void openInnerTopic()
    {
        try
        {
            List<WebElement> newTabs = Driver.driver.findElements(By.cssSelector("div[class='pointer-arrow chapter-badge toc-sprite']"));
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
        try
        {
            //List<WebElement> assignThis = Driver.driver.findElements(By.xpath("//div[@assesssmenttype='static_assessment']"));
            List<WebElement> assignThis = Driver.driver.findElements(By.xpath("//div[@title='Assign This']"));
            for(WebElement tab: assignThis)
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

    public void clickOnAdaptivePracticeArrow()
    {
        try
        {
            new UIElement().waitAndFindElement(By.className("selected-chapter-name"));
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateToOrion();
            List<WebElement> arrowElementsList = driver.findElements(By.xpath("//span[@id='ls-adaptive-preview-button']//following-sibling::div[1]"));
            int pos =0;
            for(int a=0;a<arrowElementsList.size();a++){
                if(arrowElementsList.get(a).isDisplayed()){
                    pos = a;
                }
            }
            arrowElementsList.get(pos).click();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method clickOnAdaptivePracticeArrow App helper TOCOpen",e);
        }
    }

}
