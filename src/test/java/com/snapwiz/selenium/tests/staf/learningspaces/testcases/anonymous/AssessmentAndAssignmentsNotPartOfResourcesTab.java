package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandCollapseChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandFirstChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ResourseCreate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UpdateContentIndex;

public class AssessmentAndAssignmentsNotPartOfResourcesTab extends Driver
{
	@Test(priority=1,enabled=true)
	public void assessmentnotpartofresourcestab()
	{
		try
		{
			new ResourseCreate().resourseCreate(18011,0);//create 1st resources on chapter level 1
			new ResourseCreate().resourseCreate(18012,0);//create 2st resources on chapter level 1
			new Assignment().create(18013);//Assignment create
			new LoginUsingLTI().ltiLogin("1801");//student create
			new UpdateContentIndex().updatecontentindex("18013");//update index
			driver.quit();
			startDriver("firefox");
			new LoginUsingLTI().ltiLogin("18013");//login as instructor
			new Assignment().assignToStudent(18013);////assign assignment to student
			new LoginUsingLTI().ltiLogin("1801");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TopicOpen().topicOpen("1.1: Overview");//open topic 1 oif chapter 1st
			driver.findElement(By.cssSelector("span[title='Resources']")).click();//click on resources tab
			Thread.sleep(3000);
			
			List<WebElement> allresourcesname=(List<WebElement>)((JavascriptExecutor) driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']")));//fetch all resources name
			for(WebElement element:allresourcesname)
			{
				String nameofresources=element.getText();
				if(nameofresources.equals("givenname family"))//check each and every text is instructor name
					Assert.fail("Assignments name present in the resources page.");
			}
			
			
			List<WebElement> alltext=(List<WebElement>)((JavascriptExecutor) driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("li[class='resource-content-posts-list']")));//fetch all text of one card of resources
			for(WebElement elements:alltext)
			{
				String textofresources=elements.getText();
				
				if(textofresources.contains("posted an assignment"))//check posted an assignment text which is tag with every assignment
					Assert.fail("Assignments name present in the resources page.");
			}
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase assessmentnotpartofresourcestab in class AssessmentAndAssignmentsNotPartOfResourcesTab",e);
		}
	
	}
	
	@Test(priority=2,enabled=true,dependsOnMethods={"assessmentnotpartofresourcestab"})
	public void resourcestabshownallthetopicofchapter()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1801");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new ExpandFirstChapter().expandFirstChapter();//expend 1st chapter
			Thread.sleep(3000);
			new TopicOpen().topicOpen("1.1: Overview");//open 1st chapter topic 1
			boolean resources=driver.findElement(By.cssSelector("div[data-id='resources']")).isDisplayed();//check resources tab
			if(resources==false)
			Assert.fail("click on 1st topic resources tab not shown");
			driver.findElement(By.cssSelector("span[class='show-toc navigator-sprites']")).click();//TOC open
			new TopicOpen().topicOpen("1.2: Overarching ideas: Patterns, order and organisation: Physical and chemical properties");//open 1st chapter topic 2
			boolean resources1=driver.findElement(By.cssSelector("div[data-id='resources']")).isDisplayed();//check resources tab
			if(resources1==false)
				Assert.fail("click on 2nd topic resources tab not shown");
			driver.findElement(By.cssSelector("span[class='show-toc navigator-sprites']")).click();//TOC open
			new TopicOpen().topicOpen("1.5: Science understanding: Fast and slow reactions");//open 1st chapter topic 3
			boolean resources3=driver.findElement(By.cssSelector("div[data-id='resources']")).isDisplayed();//check resources tab
			if(resources3==false)
				Assert.fail("click on 5th topic resources tab not shown");
			driver.findElement(By.cssSelector("span[class='show-toc navigator-sprites']")).click();//TOC open
			new ExpandCollapseChapter().expandChapter(2);
			Thread.sleep(3000);
			new TopicOpen().topicOpen("2.1: Overview");//open 2nd chapter topic 1
			boolean resources4=driver.findElement(By.cssSelector("div[data-id='resources']")).isDisplayed();//check resources tab
			if(resources4==true)
				Assert.fail("click on 1st topic of 2nd chaptere resources tab shown");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase assessmentnotpartofresourcestab in class AssessmentAndAssignmentsNotPartOfResourcesTab",e);
		}
	}
	@Test(priority=3,enabled=true,dependsOnMethods={"assessmentnotpartofresourcestab"})
	public void Resourcetopiclevel()
	{
		try
		{
			new ResourseCreate().creatresourcesattopiclevel(18091, 1, 0);//create resource at chapter 1 topic 2
			new ResourseCreate().creatresourcesattopiclevel(1809, 7, 1);//create resource at chapter 1 topic 6
			new LoginUsingLTI().ltiLogin("1809");
			new Navigator().NavigateTo("eTextbook");
			new ExpandFirstChapter().expandFirstChapter();//expande 1st chapter
			new TopicOpen().topicOpen("1.2: Overarching ideas: Patterns, order and organisation: Physical and chemical properties");
			driver.findElement(By.cssSelector("span[title='Resources']")).click();//click on resources tab
			Thread.sleep(3000);
			String[] resourcesname=new String[3];
			int i=0;
			List<WebElement> allresourcesname=(List<WebElement>)((JavascriptExecutor) driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']")));//fetch all resources name
			for(WebElement element:allresourcesname)
			{
				resourcesname[i]=element.getText();
				i++;
			}
			String resources1=ReadTestData.readDataByTagName("AssessmentAndAssignmentsNotPartOfResourcesTab", "resoursename", "18011");
			String resources2=ReadTestData.readDataByTagName("AssessmentAndAssignmentsNotPartOfResourcesTab", "resoursename", "18012");
			String resources3=ReadTestData.readDataByTagName("AssessmentAndAssignmentsNotPartOfResourcesTab", "resoursename", "18091");
			String resources4=ReadTestData.readDataByTagName("AssessmentAndAssignmentsNotPartOfResourcesTab", "resoursename", "1809");
			if(!resourcesname[0].equals(resources3))
				Assert.fail("resources not equal to toic level resources");
			if(!resourcesname[1].equals(resources2))
				Assert.fail("resources not equal to chapter level resources");
			if(!resourcesname[2].equals(resources1))
				Assert.fail("resources not equal to chapter level resources.");
			new TopicOpen().TOCOpen();//Toc open
			new TopicOpen().topicOpen("1.1: Overview");//open topic 
			driver.findElement(By.cssSelector("span[title='Resources']")).click();//click on resources tab
			Thread.sleep(3000);
			String[] resourcesnameatchapter =new String[2];
			int j=0;
			List<WebElement> allresourcesname1=(List<WebElement>)((JavascriptExecutor) driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']")));//fetch all resources name
			for(WebElement elements:allresourcesname1)
			{
				resourcesnameatchapter[j]=elements.getText();
			
				j++;
			}
			if(!resourcesnameatchapter[0].equals(resources2))
				Assert.fail("resources not equal to chapter level resources");
			if(!resourcesnameatchapter[1].equals(resources1))
				Assert.fail("resources not equal to chapter level resources");
			new TopicOpen().TOCOpen();
			new ExpandCollapseChapter().expandChapter(2);
			new TopicOpen().topicOpen("2.3: Science understanding: Hot moves");
			driver.findElement(By.cssSelector("span[title='Resources']")).click();//click on resources tab
			Thread.sleep(3000);
			String[] resourcesnameof2ndchapterattopic=new String[1];
			int k=0;
			List<WebElement> allresourcesname2=(List<WebElement>)((JavascriptExecutor) driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']")));//fetch all resources name
			for(WebElement elements1:allresourcesname2)
			{
				resourcesnameof2ndchapterattopic[k]=elements1.getText();
				
				k++;
			}
			if(!resourcesnameof2ndchapterattopic[0].equals(resources4))
				Assert.fail("resources not equal to chapter level resources");
			new TopicOpen().TOCOpen();
			new ExpandCollapseChapter().expandChapter(2);
			new TopicOpen().topicOpen("2.1: Overview");
			boolean resourcestabvalue=driver.findElement(By.cssSelector("span[title='Resources']")).isDisplayed();
			if(resourcestabvalue==true)
				Assert.fail("resource tab shown where no resource at chapter and topiv level");
			
			
			
			
			
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase Resorceattopiclevel in class AssessmentAndAssignmentsNotPartOfResourcesTab",e);
		}
	}
	@Test(priority=4,enabled=true,dependsOnMethods={"assessmentnotpartofresourcestab"})
	public void Resourcesubtopiclevel()
	{
		try
		{
			new ResourseCreate().createresourcesatsubtopiclevel(1813, 1, 0, 3);
			new ResourseCreate().createresourcesatsubtopiclevel(18131, 0, 0, 0);
			new ResourseCreate().createresourcesatsubtopiclevel(18131, 7, 1, 9);
			new ResourseCreate().createresourcesatsubtopiclevel(18132, 5, 1, 7);
			
			
			new LoginUsingLTI().ltiLogin("1813");
			String resources1=ReadTestData.readDataByTagName("AssessmentAndAssignmentsNotPartOfResourcesTab", "resoursename", "18011");
			String resources2=ReadTestData.readDataByTagName("AssessmentAndAssignmentsNotPartOfResourcesTab", "resoursename", "18012");
			String resources3=ReadTestData.readDataByTagName("AssessmentAndAssignmentsNotPartOfResourcesTab", "resoursename", "18091");
			String resources4=ReadTestData.readDataByTagName("AssessmentAndAssignmentsNotPartOfResourcesTab", "resoursename", "1809");
			String resources5=ReadTestData.readDataByTagName("AssessmentAndAssignmentsNotPartOfResourcesTab", "resoursename", "1813");
			String resources6=ReadTestData.readDataByTagName("AssessmentAndAssignmentsNotPartOfResourcesTab", "resoursename", "18131");
			String resources7=ReadTestData.readDataByTagName("AssessmentAndAssignmentsNotPartOfResourcesTab", "resoursename", "18132");
			new Navigator().NavigateTo("eTextbook");
			new ExpandFirstChapter().expandFirstChapter();
			new TopicOpen().topicOpen("1.2.2: subtopic 1.2-2");
			driver.findElement(By.cssSelector("span[title='Resources']")).click();
			Thread.sleep(3000);
			String[] resourcesname=new String[4];
			int i=0;
			List<WebElement> allresourcesname=(List<WebElement>)((JavascriptExecutor) driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']")));//fetch all resources name
			for(WebElement element:allresourcesname)
			{
				resourcesname[i]=element.getText();
				i++;
			}
			if(!resourcesname[0].equals(resources5))
				Assert.fail("resources not equal to subtoic level resources");
			if(!resourcesname[1].equals(resources3))
				Assert.fail("resources not equal to topic level resources");
			if(!resourcesname[2].equals(resources2))
				Assert.fail("resources not equal to chapter1 level resources2");
			if(!resourcesname[3].equals(resources1))
				Assert.fail("resources not equal to chapter1 level resources1");
			new TopicOpen().TOCOpen();
			new TopicOpen().topicOpen("1.2.1: subtopic 1.2");
			driver.findElement(By.cssSelector("span[title='Resources']")).click();
			Thread.sleep(3000);
			String[] resourcesname1=new String[3];
			int j=0;
			List<WebElement> allresourcesname1=(List<WebElement>)((JavascriptExecutor) driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']")));//fetch all resources name
			for(WebElement element1:allresourcesname1)
			{
				resourcesname1[j]=element1.getText();
				System.out.println(resourcesname1[j]);
				j++;
			}
			if(!resourcesname1[0].equals(resources3))
				Assert.fail("resources not equal to subtoic level resources");
			if(!resourcesname1[1].equals(resources2))
				Assert.fail("resources not equal to topic level resources");
			if(!resourcesname1[2].equals(resources1))
				Assert.fail("resources not equal to chapter1 level resources2");
			
			new TopicOpen().TOCOpen();
			new ExpandCollapseChapter().expandChapter(2);
			new TopicOpen().topicOpen("2.3.1: subtopic 2.3");
			driver.findElement(By.cssSelector("span[title='Resources']")).click();
			Thread.sleep(3000);
			String[] resourcesname2=new String[2];
			int k=0;
			List<WebElement> allresourcesname2=(List<WebElement>)((JavascriptExecutor) driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']")));//fetch all resources name
			for(WebElement element2:allresourcesname2)
			{
				resourcesname2[k]=element2.getText();
				System.out.println(resourcesname2[k]);
				k++;
			}
			if(!resourcesname2[0].equals(resources6))
				Assert.fail("resources not equal to subtoic level resources6");
			if(!resourcesname2[1].equals(resources4))
				Assert.fail("resources not equal to topic level resources4");
			
			new TopicOpen().TOCOpen();
			new ExpandCollapseChapter().expandChapter(1);
			new TopicOpen().topicOpen("1.1.1: subtopic 1.1");
			driver.findElement(By.cssSelector("span[title='Resources']")).click();
			Thread.sleep(3000);
			String[] resourcesname3=new String[3];
			int m=0;
			List<WebElement> allresourcesname3=(List<WebElement>)((JavascriptExecutor) driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']")));//fetch all resources name
			for(WebElement element3:allresourcesname3)
			{
				resourcesname3[m]=element3.getText();
				System.out.println(resourcesname3[m]);
				m++;
			}
			if(!resourcesname3[0].equals(resources6))
				Assert.fail("resources not equal to subtoic level resources");
			if(!resourcesname3[1].equals(resources2))
				Assert.fail("resources not equal to topic level resources");
			if(!resourcesname3[2].equals(resources1))
				Assert.fail("resources not equal to chapter1 level resources2");
			new TopicOpen().TOCOpen();
			new ExpandCollapseChapter().expandChapter(2);
			new TopicOpen().topicOpen("2.1.1: subtopic 2.1");
			driver.findElement(By.cssSelector("span[title='Resources']")).click();
			Thread.sleep(3000);
			String[] resourcesname4=new String[1];
			int n=0;
			List<WebElement> allresourcesname4=(List<WebElement>)((JavascriptExecutor) driver).executeScript("return arguments[0]",driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']")));//fetch all resources name
			for(WebElement element4:allresourcesname4)
			{
				resourcesname4[n]=element4.getText();
				System.out.println(resourcesname4[n]);
				n++;
			}
			if(!resourcesname4[0].equals(resources7))
				Assert.fail("resources not equal to subtoic level resources");			
			new TopicOpen().TOCOpen();			
			new TopicOpen().topicOpen("2.2.1: subtopic 2.2");
			boolean resourcestabvalue=driver.findElement(By.cssSelector("span[title='Resources']")).isDisplayed();
			if(resourcestabvalue==true)
				Assert.fail("resource tab shown where no resource at chapter and topiv level");

		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase Resourcesubtopiclevel in class AssessmentAndAssignmentsNotPartOfResourcesTab",e);
		}
	}
	

}
