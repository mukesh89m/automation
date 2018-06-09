package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;

public class TOCVerify 
{   String tocvalue=null;
	String [] tocvalues;
	public String [] tocverify(int cardIndex)
	{
		try
		{
			String chapterstatus = Driver.driver.findElement(By.className("chapter-heading")).getAttribute("class");
			if(chapterstatus.equals("chapter-heading"))
				Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div")).click();
			Thread.sleep(3000);
			if(cardIndex == 1)
			tocvalue = Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div[2]/ul")).getText();	
			
			else
				tocvalue = Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div[2]/ul["+cardIndex+"]")).getText();
			
			tocvalues = tocvalue.split("\n");
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in TOCVerify",e);
			
		}
		
		return tocvalues;
	}

	
	public void tocChapterVerify(int dataIndex)
	{
		try
		{
			Thread.sleep(5000);
			new ExpandCollapseChapter().collapseChapter(1);
			String chapter1 = ReadTestData.readDataByTagName("tocdata", "chapter1", Integer.toString(dataIndex));
			String chapter2 = ReadTestData.readDataByTagName("tocdata", "chapter2", Integer.toString(dataIndex));
			ArrayList<String> list = new ArrayList<String>();
			String chaptername;
			List<WebElement> chapters = Driver.driver.findElements(By.className("media__body"));
			for(WebElement chapter : chapters)
			{	
				chaptername = chapter.getText().replace("\n", " ");
				list.add(chaptername);
			}
			String[] array = list.toArray(new String[list.size()]);
			
			if(!array[0].contains(chapter1) || !array[1].contains(chapter2))
				Assert.fail("One of the chapter names is not equal to the expected value given in testdata");
			Thread.sleep(3000);
		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper TOCChapterVerify",e);
		}
	}
	
	public void tocTopicValidate(int dataIndex,int cardIndex)
	{
		 String tocvalue=null;
			String [] tocvalues;
			try
			{
				String card1topic1 = ReadTestData.readDataByTagName("tocdata", "card1topic1", Integer.toString(dataIndex));
				String card1topic2 = ReadTestData.readDataByTagName("tocdata", "card1topic2", Integer.toString(dataIndex));
				String card1topic3 = ReadTestData.readDataByTagName("tocdata", "card1topic3", Integer.toString(dataIndex));
				String card2topic1 = ReadTestData.readDataByTagName("tocdata", "card2topic1",Integer.toString(dataIndex));
				String card2topic2 = ReadTestData.readDataByTagName("tocdata", "card2topic2", Integer.toString(dataIndex));
				String card3topic1 = ReadTestData.readDataByTagName("tocdata", "card3topic1", Integer.toString(dataIndex));
				String card3topic2 = ReadTestData.readDataByTagName("tocdata", "card3topic2", Integer.toString(dataIndex));
				String card4topic1 = ReadTestData.readDataByTagName("tocdata", "card4topic1", Integer.toString(dataIndex));
				String card4topic2 = ReadTestData.readDataByTagName("tocdata", "card4topic2", Integer.toString(dataIndex));
				
				
				/*String chapterstatus = Driver.driver.findElement(By.className("chapter-heading")).getAttribute("class");				
				if(chapterstatus.equals("chapter-heading"))
					Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div")).click();*/
				Thread.sleep(3000);
				if(cardIndex == 1)
				tocvalue = Driver.driver.findElement(By.className("chapter-card")).getText();
				else
				{
					List<WebElement> topics = Driver.driver.findElements(By.className("topic-card"));
					tocvalue = topics.get(cardIndex-2).getText();
				}
				
				/*if(cardIndex == 1)
				tocvalue = Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div[2]/ul")).getText();	
				
				else
					tocvalue = Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div[2]/ul["+cardIndex+"]")).getText();*/
				
				tocvalues = tocvalue.split("\n");
			
				if(cardIndex == 1)
				{
				if(!tocvalues[0].equals(card1topic1)) Assert.fail("Topic card doesn't have Diagnostic Test at first position");
				if(!tocvalues[1].equals(card1topic2)) Assert.fail("Topic card doesn't have Personalized Practice at second position");
				if(!tocvalues[2].equals(card1topic3)) Assert.fail("Topic in chapter 1 card 1 is not as expected at position 3");
				}
				if(cardIndex == 2)
				{	
					System.out.println(tocvalues[0]); System.out.println(tocvalues[1]);
				if(!tocvalues[0].equals(card2topic1)) Assert.fail("Topic in chapter 1 card 2 is not as expected at position 1");
				if(!tocvalues[1].equals(card2topic2)) Assert.fail("Topic in chapter 1 card 2 is not as expected at position 2");
				}
				if(cardIndex == 3)
				{
					if(!tocvalues[0].equals(card3topic1)) Assert.fail("Topic in chapter 1 card 3 is not as expected at position 1");
					if(!tocvalues[1].equals(card3topic2)) Assert.fail("Topic in chapter 1 card 3 is not as expected at position 2");
				}
				if(cardIndex == 4)
				{
					if(!tocvalues[0].equals(card4topic1)) Assert.fail("Topic in chapter 1 card 4 is not as expected at position 1");
					if(!tocvalues[1].equals(card4topic2)) Assert.fail("Topic in chapter 1 card 4 is not as expected at position 2");
				}
				Thread.sleep(3000);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Assert.fail("Exception in TOCVerify",e);
				
			}
	}
}
