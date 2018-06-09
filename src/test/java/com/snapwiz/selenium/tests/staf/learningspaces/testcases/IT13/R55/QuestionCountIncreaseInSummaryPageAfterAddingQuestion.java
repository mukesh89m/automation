package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R55;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CourseSummaryPage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.OpenSearchPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class QuestionCountIncreaseInSummaryPageAfterAddingQuestion extends Driver
{
	@Test(priority=1,enabled=true)
	public void questionCountIncreaseInSummaryPageAfterAddingQuestion()
	{
		try 
		{
			String assessmentname=ReadTestData.readDataByTagName("", "assessmentname", "13");
			String [] questiontype={"qtn-type-true-false-img","qtn-multiple-choice-img","qtn-multiple-selection-img","qtn-text-entry-img","qtn-text-entry-numeric-img","qtn-text-entry-numeric-units-img","qtn-text-entry-drop-down-img","qtn-numeric-maple-img","qtn-math-symbolic-notation-img","qtn-essay-type"};
			new Assignment().create(13);
			for(int i=1;i<=9;i++)
			{
				new Assignment().addQuestions(13, questiontype[i], assessmentname);
			}
			new CourseSummaryPage().courseSummaryPage();
			new ComboBox().selectValue(3, "Question Type Count");//3--select question types from dropdown
			int eassytype=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 0));//total question count of multiple choice
			int mapplenumeric=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 1));//total question count of multiple choice
			int mapllesymbolic=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 2));//total question count of multiple choice
			int multipletype=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 3));//total question count of multiple choice
			int multiplesection=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 4));//total question count of multiple selection
			int numericentry=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 5));//total question count of numericentry
			int numericentrywithunit=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 6));//total question count of numericentrywithunit
			int textdropdown=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 7));//total question count of textdropdown
			int textentry=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 8));//total question count of textentry
			int truefalse=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 9));//total question count of truefalse 
			//13-19--added question of different type
			for(int i=0;i<=9;i++)
			{
				new Assignment().addQuestions(13, questiontype[i], assessmentname);
			}
			new CourseSummaryPage().courseSummaryPage();
			new ComboBox().selectValue(3, "Question Type Count");//3--select question types from dropdown
			int eassytype1=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 0));//total question count of multiple choice
			int mapplenumeric1=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 1));//total question count of multiple choice
			int mapllesymbolic1=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 2));//total question count of multiple choice
			int multipletype1=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 3));//total question count of multiple choice
			int multiplesection1=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 4));//total question count of multiple selection
			int numericentry1=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 5));//total question count of numericentry
			int numericentrywithunit1=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 6));//total question count of numericentrywithunit
			int textdropdown1=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 7));//total question count of textdropdown
			int textentry1=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 8));//total question count of textentry
			int truefalse1=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 9));//total question count of truefalse 
			if(eassytype!=(eassytype1-1))
			{
				Assert.fail("eassytype1 type question count not increase");
			}
			if(mapplenumeric!=(mapplenumeric1-1))
			{
				Assert.fail("mapplenumeric1 type question count not increase");
			}
			if(mapllesymbolic!=(mapllesymbolic1-1))
			{
				Assert.fail("mapllesymbolic type question count not increase");
			}
			if(multipletype!=(multipletype1-1))
			{
				Assert.fail("mutiple type question count not increase");
			}
			if(multiplesection!=(multiplesection1-1))
			{
				Assert.fail("multiplesection type question count not increase");
			}
			if(numericentry!=(numericentry1-1))
			{
				Assert.fail("numericentry type question count not increase");
			}
			if(numericentrywithunit!=(numericentrywithunit1-1))
			{
				Assert.fail("numericentrywithunit type question count not increase");
			}
			if(textdropdown!=(textdropdown1-1))
			{
				Assert.fail("numericentrywithunit type question count not increase");
			}
			if(textentry!=(textentry1-1))
			{
				Assert.fail("textentry type question count not increase");
			}
			if(truefalse!=(truefalse1-1))
			{
				Assert.fail("truefalse1 type question count not increase");
			}
			//add various type passage based question
			for(int i=0;i<=9;i++)
			{
				new Assignment().addPassagetypequestion(13, "qtn-passage-based-img", assessmentname, questiontype[i]);
			}
			new CourseSummaryPage().courseSummaryPage();
			new ComboBox().selectValue(3, "Question Type Count");//3--select question types from dropdown
			int eassytype2=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 0));//total question count of multiple choice
			int mapplenumeric2=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 1));//total question count of multiple choice
			int mapllesymbolic2=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 2));//total question count of multiple choice
			int multipletype2=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 3));//total question count of multiple choice
			int multiplesection2=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 4));//total question count of multiple selection
			int numericentry2=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 5));//total question count of numericentry
			int numericentrywithunit2=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 6));//total question count of numericentrywithunit
			int textdropdown2=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 7));//total question count of textdropdown
			int textentry2=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 8));//total question count of textentry
			int truefalse2=Integer.parseInt(new TextFetch().textfetchbylistclass("cms-QuestionsTotal-count", 9));//total question count of truefalse 
			if(eassytype2!=(eassytype1+1))
			{
				Assert.fail("eassytype2 type question count not increase");
			}
			if(mapplenumeric2!=(mapplenumeric1+1))
			{
				Assert.fail("mapplenumeric2 type question count not increase");
			}
			if(mapllesymbolic2!=(mapllesymbolic1+1))
			{
				Assert.fail("mapllesymbolic2 type question count not increase");
			}
			if(multipletype2!=(multipletype1+1))
			{
				Assert.fail("mutiple type question count not increase");
			}
			if(multiplesection2!=(multiplesection1+1))
			{
				Assert.fail("multiplesection type question count not increase");
			}
			if(numericentry2!=(numericentry1+1))
			{
				Assert.fail("numericentry type question count not increase");
			}
			if(numericentrywithunit2!=(numericentrywithunit1+1))
			{
				Assert.fail("numericentrywithunit type question count not increase");
			}
			if(textdropdown2!=(textdropdown1+1))
			{
				Assert.fail("numericentrywithunit type question count not increase");
			}
			if(textentry2!=(textentry1+1))
			{
				Assert.fail("textentry type question count not increase");
			}
			if(truefalse2!=(truefalse1+1))
			{
				Assert.fail("truefalse2 type question count not increase");
			}			
		} 
		catch (Exception e) 
		{
			Assert.fail("Exception in testcalss QuestionCountIncreaseInSummaryPageAfterAddingQuestion in method questionCountIncreaseInSummaryPageAfterAddingQuestion ",e);
		}
	}
	@Test(priority=2,enabled=false)
	public void filterQuestionTypeSearchPage()
	{
		try
		{

			String searchtext=ReadTestData.readDataByTagName("","questiontext", "13");
			new OpenSearchPage().openSearchPage();
			new OpenSearchPage().searchquestion(searchtext);
			Thread.sleep(2000);
			int tqcount=driver.findElements(By.id("content-search-results-block")).size();
			if(tqcount==0)
			{
				Assert.fail("serach result not shown question");
			}
			//50
			new OpenSearchPage().selectQuestiontype("True / False");
			Thread.sleep(2000);
			int questioncounttruefalse=driver.findElements(By.id("content-search-results-block")).size();
			if(questioncounttruefalse!=1)
			{
				Assert.fail("true false question not filter");
			}
			//44--select multiple type question
			new OpenSearchPage().selectQuestiontype("Multiple Choice");
			Thread.sleep(2000);
			int questioncountmutiplechoice=driver.findElements(By.id("content-search-results-block")).size();
			if(questioncountmutiplechoice!=1)
			{
				Assert.fail("multiple choice question not filter");
			}
			//48
			new OpenSearchPage().selectQuestiontype("Multiple Selection");
			Thread.sleep(2000);
			int questioncountmutipleselection=driver.findElements(By.id("content-search-results-block")).size();
			if(questioncountmutipleselection!=1)
			{
				Assert.fail("multiple selection question not filter");
			}
			//51
			new OpenSearchPage().selectQuestiontype("Text Entry");
			Thread.sleep(2000);
			int questioncountTextEntry=driver.findElements(By.id("content-search-results-block")).size();
			if(questioncountTextEntry!=1)
			{
				Assert.fail("Text Entry question not filter");
			}
			//53
			new OpenSearchPage().selectQuestiontype("Numeric Entry");
			Thread.sleep(2000);
			int questioncountNumericEntry=driver.findElements(By.id("content-search-results-block")).size();
			if(questioncountNumericEntry!=1)
			{
				Assert.fail("Numeric Entry question not filter");
			}
			//54
			new OpenSearchPage().selectQuestiontype("Numeric Entry w/units");
			Thread.sleep(2000);
			int questioncountNumericEntryunit=driver.findElements(By.id("content-search-results-block")).size();
			if(questioncountNumericEntryunit!=1)
			{
				Assert.fail("Numeric Entry w/units question not filter");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase class FilterQuestionTypeSearchPage in method filterQuestionTypeSearchPage ",e);
		}
	}

	
}
