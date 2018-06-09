package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learnon.uihelper.ScrollElement;
import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class TopicOpen 
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
            Thread.sleep(10000);
			Driver.driver.findElements(By.className("chapter-heading-label")).get(chapterIndex).click();
			Thread.sleep(3000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElements(By.cssSelector("a[data-type='lesson']")).get(lessonIndex));
//			Driver.driver.findElements(By.cssSelector("a[data-type='lesson']")).get(lessonIndex).click();
			Thread.sleep(3000);
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
	
	public void ResourcesOpenInNewtab(int resourseopen,int resnumber)
	{
		try
		{
            Thread.sleep(10000);
			Driver.driver.findElement(By.cssSelector("span[title='Resources']")).click();//click on resources tab
			Thread.sleep(3000);
			Actions action = new Actions(Driver.driver);
			List<WebElement> we= (List<WebElement>) Driver.driver.findElements(By.cssSelector("li[class='resource-content-posts-list']"));
			action.moveToElement(we.get(resourseopen)).build().perform();
			//Driver.driver.findElement(By.cssSelector("a[class='toc-sprite folder-forward']")).click();
			List<WebElement> resoursenewtab = Driver.driver.findElements(By.cssSelector("a[class='toc-sprite folder-forward']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", resoursenewtab.get(resnumber));
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
	public void openLessonWithDiscussionWidget()
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
			WebElement  we=Driver.driver.findElement(By.partialLinkText("10.3"));
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",we);
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
	public void clickOnAssignThisIcon()
	{
		try
		{
			List<WebElement> assignThis =Driver.driver.findElements(By.xpath("//div[@title='Assign This']"));
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

	/*  @Author Mukesh
	 This method will open static assessment based on the assignmentName*/

	public void selectInvisibleAssignment(String assignmentname) {
		try {
			new WebDriverWait(Driver.driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@title='" + assignmentname + "']")));
			WebElement element =Driver.driver.findElement(By.xpath(".//*[@title='" + assignmentname + "']"));
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			Assert.fail("Exception in testcase selectInvisibleAssignment in class SelectCourse", e);
		}
	}

	/*  @Author Mukesh
	 This method will click on static assessment based on the staticAssessment*/
	public void clickOnStaticAssessmentArrow(int dataIndex)
	{
		String staticAssessment= ReadTestData.readDataByTagName("","staticAssessment",Integer.toString(dataIndex));
		System.out.println("staticAssessment:"+staticAssessment);
		try
		{
			new WebDriverWait(Driver.driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@title='" + staticAssessment + "']")));
			new ScrollElement().scrollToViewOfElement(Driver.driver.findElement(By.xpath(".//*[@title='" + staticAssessment + "']")));
			List<WebElement> assessments=Driver.driver.findElements(By.xpath(".//*[@title='" + staticAssessment + "']/following-sibling::div[@class='ls-inner-arw']"));
			for(WebElement test:assessments){
				if(test.isDisplayed())
				{
					test.click();
					break;
				}
			}

		}
		catch(Exception e)
		{
			Assert.fail("Exception in method clickOnStaticAssessmentArrow App helper TOCOpen",e);
		}
	}

	public void clickOnCustomAssignmentArrowInAssignmentTab(int dataIndex)
	{
		String customAssignmentName= ReadTestData.readDataByTagName("","customAssignmentName",Integer.toString(dataIndex));
		System.out.println("customAssignmentName:" + customAssignmentName);
		new Navigator().NavigateTo("e-Textbook");
		new Click().clickBycssselector("i[class='close-study-plan-icon close-study-plan']");
		new Click().clickBycssselector("span[title='Assignments']");
		int pos = 0;
		List<WebElement> allOpenLink = Driver.driver.findElements(By.className("ls_assessment_link"));
		for(int a=0;a<allOpenLink.size();a++) {
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", allOpenLink.get(a));
			if ((allOpenLink.get(a).getText().equals(customAssignmentName))) {
				System.out.println("customAssignmentName:" + customAssignmentName);
				List<WebElement> listOfArrows = Driver.driver.findElements(By.className("ls-assignment-show-assign-this-block"));
				listOfArrows.get(a+1).click();
				break;
			}
		}
		pos++;
	}

	public void clickOnAssignmentArrowInCourse(int dataIndex)
	{
		String customAssignmentName= ReadTestData.readDataByTagName("","customAssignmentName",Integer.toString(dataIndex));
		System.out.println("customAssignmentName:" + customAssignmentName);
		new Navigator().NavigateTo("e-Textbook");
		int pos = 0;
		List<WebElement> allOpenLink = Driver.driver.findElements(By.className("toc-assignment-ellipsis"));
		System.out.println("allOpenLinkSize::"+allOpenLink.size());
		for(int a=0;a<allOpenLink.size();a++) {
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", allOpenLink.get(a));
			if ((allOpenLink.get(a).getText().equals(customAssignmentName))) {
				System.out.println("customAssignmentName:" + customAssignmentName);
				 List<WebElement> assignmentArrow = Driver.driver.findElements(By.xpath(".//*[@class='inner-assignment-block']/div[@class='ls-inner-arw']"));
				 assignmentArrow.get(a).click();
				break;
			}
		}
		pos++;
	}

}
