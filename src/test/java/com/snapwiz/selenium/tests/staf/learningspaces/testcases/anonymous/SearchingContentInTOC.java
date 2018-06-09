package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;


/*
 * Testcases R5.1110(744 to 764)
 */
public class SearchingContentInTOC extends Driver
{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.SearchingContentInTOC");

	@Test(priority = 1, enabled = true)
	public void searchingContentInTOC()
	{
		try
		{
		  String sendKey = ReadTestData.readDataByTagName("SearchingContentInTOC", "sendKey", "748");
          new LoginUsingLTI().ltiLogin("748");
          new Navigator().NavigateTo("eTextbook");
		  WebElement WE = driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']"));
		  
			if(!WE.getAttribute("placeholder").equals("Search Content"))				
				Assert.fail("Deault text 'Search Content' is absent");					
			
		  new SearchResultShouldAsPerStudentInput().searchResultShouldAsPerStudentInput(sendKey);
		  driver.findElement(By.cssSelector("i[class='close-search-results search-close ']")).click();
		  Thread.sleep(2000);
		  driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).clear();
		  Thread.sleep(2000);
		  String str = new RandomString().randomstring(5);
		  driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys(str);
		  driver.findElement(By.cssSelector("div[class='ls-search-icon']")).click();
		  String results = driver.findElement(By.cssSelector("li[class='ls-search-results-not-found']")).getText();
		  
		  if(results.contains("No Results Found"))
		  {
			  logger.log(Level.INFO,"'No result Found' option is present on entering a random string");
		  }
		  else
		  {
			
			  Assert.fail("'No result Found' option is absent on entering a random string");
		  }
		  driver.findElement(By.cssSelector("i[class='close-search-results search-close ']")).click();
		  Thread.sleep(3000);
		  driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).clear();
		  Thread.sleep(3000);
		  driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys(sendKey);
		  Actions ac = new Actions(driver);
		  ac.sendKeys(Keys.RETURN).build().perform();
		  Thread.sleep(20000);
		  List<WebElement> allElements = driver.findElements(By.cssSelector("a[class='toc-auto-suggest-label']"));
			 
		  String str1 = allElements.get(0).getText();
		  String str2 = allElements.get(1).getText();
		 
		  if(str1.length() == 0 || str2.length() == 0)
		  {
			  Assert.fail("Search results are not coming based on search text given by student");
		  }
			 
		}
	  catch(Exception e)
	    {
		  e.printStackTrace();
		  Assert.fail("Exception in searchingContentInTOC in class SearchingContentInTOC", e);
	    }
	}
	@Test(priority = 2, enabled = true)
	public void searchResultShouldBeAcrosAllAvailableContent()
	{
		 try
		 {

		     String sendKey = ReadTestData.readDataByTagName("SearchingContentInTOC", "sendKey", "757");
		     String searchText = ReadTestData.readDataByTagName("SearchingContentInTOC", "searchText", "757");
		     String chapterText = ReadTestData.readDataByTagName("SearchingContentInTOC", "chapterText", "757");
		     String assessmentText = ReadTestData.readDataByTagName("SearchingContentInTOC", "assessmentText", "757");
		     String lessonText = ReadTestData.readDataByTagName("SearchingContentInTOC", "lessonText", "757");
		     String lessonTextDetails = ReadTestData.readDataByTagName("SearchingContentInTOC", "lessonTextDetails", "757");
		     new LoginUsingLTI().ltiLogin("757");
		     new Navigator().NavigateTo("eTextbook");
		     new SearchResultShouldAsPerStudentInput().searchResultShouldAsPerStudentInput(sendKey);
			 
			 String [] resultsfetched = new SearchTextAndFetchTheResult().searchTextAndFetchTheResult(searchText);
			 boolean found = false;
			 for(String result : resultsfetched)
			 {
				 if(result.contains(chapterText))
				 {
					 found = true;
					 break;
				 }
			 }
			 if(found == true)
			 {
				 logger.log(Level.INFO,"Search Result is present across chapter level");	
			 }
			 else
			 {
				 Assert.fail("Search Result is absent across chapter level");
			 }
			 
			 for(String result : resultsfetched)
			 {
				 if(result.contains(assessmentText))
				 {
					 found = true;
					 break;
				 }
			 }
			 if(found == true)
			 {
				 logger.log(Level.INFO,"Search Result is present across assesment");	
			 }
			 else
			 {
				 Assert.fail("Search Result is absent across assesment");
			 }
			 List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@class, 'ls-result-view-title')]"));
			 for(WebElement elements: allElements)
			 {
				 String str = elements.getText();
				 if(str.contains(lessonText))
				 {
					 elements.click();
					 System.out.println("clicked");
					 Thread.sleep(5000);
					 boolean textPresent= new TextValidate().IsTextPresent(lessonTextDetails) ;
					 
					 if (textPresent == true)
					 {
						 logger.log(Level.INFO,"Search Result is present across lesson text");	
					 }
					 else
					 {
						 
						 Assert.fail("Search Result is not present across lesson text");
					 }
				 }
				
			 }
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 Assert.fail("Exception in searchResultShouldBeAcrosAllAvailableContent in class SearchingContentInTOC",e);
			 
		 }
	}
	
	@Test(priority = 3, enabled = true)
	public void glossaryInSearchResultShouldComeFirst()
	{
		try
		{
			String sendKey = ReadTestData.readDataByTagName("SearchingContentInTOC", "sendKey", "764");
			String glossaryText = ReadTestData.readDataByTagName("SearchingContentInTOC", "glossaryText", "764");
			
			new LoginUsingLTI().ltiLogin("764");
			new Navigator().NavigateTo("eTextbook");
		    String [] resultsfetched = new SearchTextAndFetchTheResult().searchTextAndFetchTheResult(sendKey);
		    
			if(resultsfetched[0].equals(glossaryText))
				{
					logger.log(Level.INFO,"Glossary text is coming first");
				}
			else
				{
					Assert.fail("Glossary text is not coming first");
				}
		}
		catch(Exception e)
		{
			 e.printStackTrace();
			 Assert.fail("Exception in glossaryInSearchResultShouldComeFirst in class SearchingContentInTOC",e);
		}
	}
	

	@Test(priority = 4, enabled = true)
	public void verifyAutosuggestFunctionality()
	{
		try
		{

			String sendKey = ReadTestData.readDataByTagName("SearchingContentInTOC", "sendKey", "771");
			String sendKey1 = ReadTestData.readDataByTagName("SearchingContentInTOC", "sendKey1", "771");
			String sendKey2 = ReadTestData.readDataByTagName("SearchingContentInTOC", "sendKey2", "771");
			String autoSuggestion = ReadTestData.readDataByTagName("SearchingContentInTOC", "autoSuggestion", "771");
			new LoginUsingLTI().ltiLogin("771");
			new Navigator().NavigateTo("eTextbook");
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys(sendKey);
			Thread.sleep(5000);
			boolean suggestionPresence1 = driver.findElement(By.cssSelector("a[class='ui-corner-all']")).isDisplayed();
			System.out.println("1st "+suggestionPresence1);
			String suggestion = driver.findElement(By.cssSelector("a[class='ui-corner-all']")).getText();
			if(suggestion.equals(autoSuggestion))
			{
				logger.log(Level.INFO,"Autosuggest appears according to the search term prefix");
			}
			else
			{
				logger.log(Level.INFO,"Autosuggest doesnt appear according to the search term prefix");
			}
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).clear();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys(sendKey1);
			Thread.sleep(5000);
			boolean suggestionPresence = driver.findElement(By.cssSelector("a[class='ui-corner-all']")).isDisplayed();
			
			if(suggestionPresence == true)
			{
				logger.log(Level.INFO,"Autosuggest present despite the searching text is not a prefix");
				Assert.fail("Autosuggest present despite the searching text is not a prefix");
			}
			else
			{
				logger.log(Level.INFO,"Autosuggest is absent as because searching text is not a prefix");
			}
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).clear();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys(sendKey2);
			Thread.sleep(5000);
			boolean suggestionPresence2 = driver.findElement(By.cssSelector("a[class='ui-corner-all']")).isDisplayed();
			
			if(suggestionPresence2 == true)
			{
				logger.log(Level.INFO,"Autosuggestion is present despite the searching text is not from name field ");
				Assert.fail("Autosuggestion is present despite the searching text is not from name field ");
			}
			else
			{
				logger.log(Level.INFO,"Autosuggest is absent as because searching text is not from name field");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in verifyAutosuggestFunctionality in class SearchingContentInTOC",e);
		}
	}
	
	@Test(priority = 5, enabled = true)
	public void verifySearchResultForResources()
	{
		try{
				new ResourseCreate().resourseCreate(833,0);
				new UpdateContentIndex().updatecontentindex("833");
				String sendKey = ReadTestData.readDataByTagName("SearchingContentInTOC", "sendKey", "833");
				String sendKey1 = ReadTestData.readDataByTagName("SearchingContentInTOC", "sendKey1", "833");
				String searchResult1 = ReadTestData.readDataByTagName("SearchingContentInTOC", "searchResult1", "833");
				String searchResult2= ReadTestData.readDataByTagName("SearchingContentInTOC", "searchResult1", "833");
				String resourceNameOnLesson= ReadTestData.readDataByTagName("SearchingContentInTOC", "resourceNameOnLesson", "833");
				new LoginUsingLTI().ltiLogin("833");
				new Navigator().NavigateTo("eTextbook");
				String [] resultsfetched = new SearchTextAndFetchTheResult().searchTextAndFetchTheResult(sendKey);
				boolean found = false;
				 for(String result : resultsfetched)
				 {
					 if(result.contains(searchResult1))
					 {
						 found = true;
						 break;
					 }
				 }
				 if(found == true)
				 {
					 logger.log(Level.INFO,"Search Result is available against resources's name");	
				 }
				 else
				 {
					 Assert.fail("Search Result is not available against resources's name");
				 }
				 driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).clear();
				 Thread.sleep(5000);
				 driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys(sendKey1);
				 Thread.sleep(5000);
				 driver.findElement(By.cssSelector("div[class='ls-search-icon']")).click();
				 Thread.sleep(5000);
				 List<String> stringarray = new ArrayList<String>();
					
				List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@class, 'toc-auto-suggest-label')]"));
				     for (WebElement element: allElements) 
				     {         
				    	 
						stringarray.add(element.getText());
				    	
				     }
				     String [] searchresults1 = stringarray.toArray(new String[stringarray.size()]);
				 for(String result : searchresults1)
				 {
					
					 if(result.contains(searchResult2))
					 {
						 found = true;
						 break;
					 }
				 }
				 if(found == true)
				 {
					 logger.log(Level.INFO,"Search Result is available against resources's description");
				 }
				 else
				 {
					 Assert.fail("Search Result is not available against resources's description");
				 }
				 boolean resclicked = false;
				 List<WebElement> searchclick = driver.findElements(By.cssSelector("li[class='search-item-wrapper']"));
				 for(WebElement res : searchclick)
				 {
					 if(res.isDisplayed() == true)
					 {
						 res.click();
						 resclicked = true;
						 break;
					 }
				 }
				 if(resclicked == false)
					 Assert.fail("Not able to click on the search result to open the resourse tab");
				 Thread.sleep(5000);
				 String resourcePresence = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
				if(resourcePresence.equals(resourceNameOnLesson) || resourcePresence.equals("Resourse2"))
				{
					logger.log(Level.INFO,"Resource tab selected with resource in visible area");
				}
				else
				{
					logger.log(Level.INFO,"Resource tab is not selected with resource in visible area");
					Assert.fail("Resource tab is not selected/Resourse tab heading incorrect, with resource in visible area");
				}
			}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in verifySearchResultForResources in class SearchingContentInTOC",e);
		}
	}
		
	@Test(priority = 6, enabled = true)
	public void verifySearchResultForAssessment()
	{
		try
		{

			String glossaryStringToSearch = ReadTestData.readDataByTagName("SearchingContentInTOC", "glossaryStringToSearch", "844");
			String diagnoticTestName = ReadTestData.readDataByTagName("SearchingContentInTOC", "diagnoticTestName", "844");
			String personalizedPracticeTest = ReadTestData.readDataByTagName("SearchingContentInTOC", "personalizedPracticeTest", "844");
			String staticPracticeTest = ReadTestData.readDataByTagName("SearchingContentInTOC", "staticPracticeTest", "844");
			new LoginUsingLTI().ltiLogin("844");
			new Navigator().NavigateTo("eTextbook");
			String[] searchResultForDiagnosticTest = new SearchTextAndFetchTheResult().searchTextAndFetchTheResult(glossaryStringToSearch);
			boolean textPresent = false;
			for (String s : searchResultForDiagnosticTest)
			{
			    
			    if(s.equals(diagnoticTestName))
				{
			    	
					logger.log(Level.INFO,"For Diagnostic Test the search using default text is valid");
					textPresent = true;
					break;
				}
				
			}
			if(textPresent == false )
			Assert.fail("For Diagnostic Test the search using default text is NOT valid");
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).clear();
			Thread.sleep(5000);
			String[] searchResultForPersonalizedPractice = new SearchTextAndFetchTheResult().searchTextAndFetchTheResult(personalizedPracticeTest);
			boolean textPresent1 = false;
			for (String s1 : searchResultForPersonalizedPractice)
			{
			   
			    if(s1.equals(personalizedPracticeTest))
				{
					logger.log(Level.INFO,"For personalized practice Test the search using default text is valid");
					textPresent1 = true;
					break;
				}				
			}
			if(textPresent1 == false )
			Assert.fail("For personalized practice Test the search using default text is NOT valid");
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).clear();
			Thread.sleep(5000);
			String[] searchResultForStaticPractice = new SearchTextAndFetchTheResult().searchTextAndFetchTheResult(staticPracticeTest);
			boolean textPresent2 = false;
			for (String s2 : searchResultForStaticPractice)
			{
			   
			    if(s2.equals(staticPracticeTest))
				{
					logger.log(Level.INFO,"For Static Practice the search using default text is valid");
					textPresent2 = true;
					break;
				}
			    
			}
			if(textPresent2 == false )
				Assert.fail("For Static Practice the search using default text is NOT valid");
			
			driver.findElement(By.xpath("/html/body/div[3]/div/div/div[5]/ul/div/div[2]/div/li/div/div")).click();
		    	Thread.sleep(5000);
		    	
			int testend=driver.findElements(By.className("al-diag-test-timer")).size();
		
			System.out.println("2-->"+testend);
			if(testend == 1)
			{
				logger.log(Level.INFO,"On clicking card it takes to practice test");
			}
			else
			{
				logger.log(Level.INFO,"On clicking card it doesnt take to practice test");
				Assert.fail("On clicking card it doesnt take to practice test");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in verifySearchResultForAssessment in class SearchingContentInTOC",e);
		}
	}
	
		
	@Test(priority = 7, enabled = true)
	public void checkCrossMarkInSearchBlock()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("862");
			new Navigator().NavigateTo("eTextbook");
			String str = new RandomString().randomstring(4);
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys(str);
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("div[class='ls-search-icon']")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("i[class='close-search-results search-close ']")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).click();
			/*Thread.sleep(5000);
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys(Keys.SPACE);
			Thread.sleep(5000);
			str = new RandomString().randomstring(4);
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys(str);
			driver.findElement(By.cssSelector("div[class='ls-search-icon']")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("i[class='close-search-results search-close ']")).click();
			Thread.sleep(5000); */
			String searchBlockText = driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).getAttribute("data-input");
			System.out.println("---"+searchBlockText);
			System.out.println(str);
			if(searchBlockText.equals(str))
			{
				logger.log(Level.INFO,"Text is present in search block after close button is pressed");
			}
			else
			{
				logger.log(Level.INFO,"Text is not present in search block after close button is pressed");
				Assert.fail("Text is not present in search block after close button is pressed");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in checkCrossMarkInSearchBlock in class SearchingContentInTOC",e);
		}
	}


	@Test(priority = 8, enabled = true)
	public void clickcrossMarkInSEarchBlockThenCheckTOC()
	{
		try
		{

			String glossaryStringToSearch = ReadTestData.readDataByTagName("SearchingContentInTOC", "glossaryStringToSearch", "863");
			
			new LoginUsingLTI().ltiLogin("863");
			new Navigator().NavigateTo("eTextbook");
			new SearchTextAndFetchTheResult().searchTextAndFetchTheResult(glossaryStringToSearch);	
			
			driver.findElement(By.cssSelector("i[class='close-search-results search-close ']")).click();
			int tocopened = driver.findElements(By.linkText("1.1: Overview")).size();
			
			if(tocopened == 1)
			{
				logger.log(Level.INFO,"TOC comes after close button in search block is pressed");
			}
			else
			{
				logger.log(Level.INFO,"TOC doesnt come after close button in search block is pressed");
				Assert.fail("TOC doesnt come after close button in search block is pressed");
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in clickcrossMarkInSEarchBlockThenCheckTOC in class SearchingContentInTOC",e);
		}
	}

	@Test(priority = 9, enabled = true)
	public void searchAndClickOnLesson()
	{
		try
		{

			String glossaryStringToSearch = ReadTestData.readDataByTagName("SearchingContentInTOC", "glossaryStringToSearch", "869");
			String lessonValidateText = ReadTestData.readDataByTagName("SearchingContentInTOC", "lessonValidateText", "869");
			new LoginUsingLTI().ltiLogin("869");
			new Navigator().NavigateTo("eTextbook");
			new SearchTextAndFetchTheResult().searchTextAndFetchTheResult(glossaryStringToSearch);
			driver.findElement(By.cssSelector("li[class='search-item-wrapper']")).click();
			Thread.sleep(5000);
			if(new TextValidate().IsTextPresent(lessonValidateText))
			{
				logger.log(Level.INFO,"On clicking lesson from search result it lands on lesson page");
			}
			else
			{
				
				Assert.fail("On clicking lesson from search result it doesn't land on lesson page");
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in searchAndClickOnLesson in class SearchingContentInTOC",e);
		}
	}
	
	@Test(priority = 10, enabled = true)
	public void searchTextThenCloseThenClickOnSearchBarAgain()
	{
		try
		{

			String glossaryStringToSearch = ReadTestData.readDataByTagName("SearchingContentInTOC", "glossaryStringToSearch", "869");
			new LoginUsingLTI().ltiLogin("898");
			new Navigator().NavigateTo("eTextbook");
			String [] resultsfetched = new SearchTextAndFetchTheResult().searchTextAndFetchTheResult(glossaryStringToSearch);
			
			driver.findElement(By.cssSelector("i[class='close-search-results search-close ']")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("div[class='ls-search-icon']")).click();
			Thread.sleep(5000);
			List<String> stringarray = new ArrayList<String>();
			
			List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@class, 'ls-result-view-title')]"));
		     for (WebElement element: allElements) 
		     {         
		    	 
				stringarray.add(element.getText());
		    	
		     }
		     String [] searchresults = stringarray.toArray(new String[stringarray.size()]);
		   
		     List<String> list = new ArrayList<String>();

		     for(String s : searchresults) {
		        if(s != null && s.length() > 0) {
		           list.add(s);
		        }
		     }

		     searchresults = list.toArray(new String[list.size()]);
		    
		     if (resultsfetched.length != searchresults.length) 
		    	 Assert.fail("Student is NOT able to view old search card again");
		     for (int i = 0; i < resultsfetched.length; i++)
		     {
		         if (!resultsfetched[i].equals(searchresults[i]))
		        	 Assert.fail("Student is NOT able to view old search card again");
		     }
		     logger.log(Level.INFO,"Student is able to view old search card again");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in searchTextThenCloseThenClickOnSearchBarAgain in class SearchingContentInTOC",e);
		}
	}

}
