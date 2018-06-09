package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R55;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CourseSummaryPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class QuestionDistributionBasedOnQuestiontype extends Driver
{
	@Test
	public void questionDistributionBasedOnQuestiontype()
	{
		try
		{
			new CourseSummaryPage().courseSummaryPage();//2---go to summary page
			new ComboBox().selectValue(3, "Question Type Count");//3--select question types from dropdown
			String dropdownvalue=new TextFetch().textfetchbyid("course-content-summary-heading-wrapper");
			//4-5
			if(!dropdownvalue.contains("All Chapters"))
			{
				Assert.fail("All Chapters option not shpwn");
			}
			if(!dropdownvalue.contains("All Objectives"))
			{
				Assert.fail("All Objectives option not shpwn");
			}
			if(!dropdownvalue.contains("Show questions"))
			{
				Assert.fail("Show questions option not shpwn");
			}
			//---7
			List<WebElement> allquestion=driver.findElements(By.className("cms-QuestionsType-name"));
			if(!allquestion.get(0).getText().contains("Multiple Choice"))
			{
				Assert.fail("question1 not arrange in alphabetical order");
			}
			if(!allquestion.get(2).getText().contains("Numeric Entry"))
			{
				Assert.fail("question3 not arrange in alphabetical order");
			}
			//9--select chapter
			int tqofmultipletype=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 0));//total question count of multiple choice 
			new Click().clickBycssselector("a[title='All Chapters']");//click on all chapter
			new Click().clickbyxpath("html/body/div[6]/div[1]/div[1]/div[1]/div[2]/div/div[1]/div/ul/div[2]/div/li[2]/a");//select chapter 1
			int tqofmultipletype1=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 0));//total question of multiple choice of chapter 1
			if(tqofmultipletype<=tqofmultipletype1)
			{
				Assert.fail("after select on chapter question count not change");
			}
			//11--select  objectives
			new Click().clickBycssselector("a[title='All Objectives']");//click on all objectives
			Thread.sleep(2000);
			driver.findElement(By.xpath("html/body/div[6]/div[1]/div[1]/div[1]/div[2]/div/div[2]/div/ul/div[2]/div/li[2]/a")).click();//select objective 1
			int tqofmultipletype2=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 0));//total question of multiple choice of chapter 1
			if(tqofmultipletype1<tqofmultipletype2)
			{
				Assert.fail("after select objectives question count not change");
			}
			//10,12--show all questions
			new Click().clickByid("cms-content-question-count-show-questions-title");
			int allquestiononreview=driver.findElements(By.className("cms-content-question-review-wrapper")).size();
			if(allquestiononreview!=20)
			{
				Assert.fail("after click on show questions question not shown on review page");
			}	
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase class QuestionDistributionBasedOnQuestiontype in method questionDistributionBasedOnQuestiontype",e);
		}
	}


}
